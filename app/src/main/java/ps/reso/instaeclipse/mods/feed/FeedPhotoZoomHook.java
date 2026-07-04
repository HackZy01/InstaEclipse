package ps.reso.instaeclipse.mods.feed;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import ps.reso.instaeclipse.utils.feature.FeatureFlags;
import ps.reso.instaeclipse.utils.feature.FeatureStatusTracker;

/**
 * Adds a long-press-to-zoom overlay to feed photos (issue #174) so photos can be examined
 * full-screen without navigating away to the post's profile/permalink view — Instagram's
 * classic feed row (com.instagram.feed.widget.IgProgressImageView) has no zoom machinery of
 * its own, so this hook grabs the currently-displayed Drawable and shows it in a
 * self-contained pinch/pan zoom overlay (ZoomableImageView) rather than trying to graft in
 * Instagram's own zoom classes (used for profile-picture/Stories zoom), which are tightly
 * coupled to their own render/animation callbacks.
 */
public class FeedPhotoZoomHook {

    private static final Handler MAIN = new Handler(Looper.getMainLooper());
    private static final String VIEW_CLASS = "com.instagram.feed.widget.IgProgressImageView";

    public void install(ClassLoader classLoader) {
        try {
            Class<?> viewClass = classLoader.loadClass(VIEW_CLASS);

            XposedBridge.hookAllConstructors(viewClass, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    try {
                        View view = (View) param.thisObject;
                        view.setOnLongClickListener(v -> {
                            if (!FeatureFlags.enablePhotoZoom) return false;
                            try {
                                Object imgViewObj = XposedHelpers.callMethod(param.thisObject, "getIgImageView");
                                if (!(imgViewObj instanceof ImageView)) return false;
                                Bitmap snapshot = viewToBitmap((ImageView) imgViewObj);
                                if (snapshot == null) return false;
                                showZoomOverlay(v.getContext(), snapshot);
                                return true;
                            } catch (Throwable t) {
                                XposedBridge.log("(IE|PhotoZoom) ❌ long-press: " + t);
                                return false;
                            }
                        });
                    } catch (Throwable t) {
                        XposedBridge.log("(IE|PhotoZoom) ❌ ctor hook: " + t);
                    }
                }
            });

            FeatureStatusTracker.setHooked("PhotoZoom");
            XposedBridge.log("(IE|PhotoZoom) ✅ hook installed");
        } catch (Throwable t) {
            XposedBridge.log("(IE|PhotoZoom) ❌ install: " + t);
        }
    }

    private static void showZoomOverlay(Context ctx, Bitmap bitmap) {
        MAIN.post(() -> {
            try {
                Dialog dialog = new Dialog(ctx, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                ZoomableImageView zoomView = new ZoomableImageView(ctx);
                zoomView.setImageBitmap(bitmap);
                zoomView.setBackgroundColor(Color.BLACK);
                zoomView.setOnDismissRequest(dialog::dismiss);

                dialog.setContentView(zoomView);
                Window w = dialog.getWindow();
                if (w != null) {
                    w.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
                    w.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                }
                dialog.show();
            } catch (Throwable t) {
                XposedBridge.log("(IE|PhotoZoom) ❌ overlay: " + t);
            }
        });
    }

    // Instagram's feed image view is backed by a Fresco-style drawable that manages its own
    // internal scaling and ignores ImageView's matrix/scaleType, so re-drawing the Drawable
    // directly onto a bare canvas didn't work. Screenshotting the actual View instead captures
    // exactly what's on screen, regardless of how the underlying drawable renders itself.
    private static Bitmap viewToBitmap(View v) {
        int w = v.getWidth(), h = v.getHeight();
        if (w <= 0 || h <= 0) return null;
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }
}
