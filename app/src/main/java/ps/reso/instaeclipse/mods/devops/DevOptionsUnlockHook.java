package ps.reso.instaeclipse.mods.devops;

import org.luckypray.dexkit.DexKitBridge;
import org.luckypray.dexkit.query.FindClass;
import org.luckypray.dexkit.query.FindMethod;
import org.luckypray.dexkit.query.matchers.ClassMatcher;
import org.luckypray.dexkit.query.matchers.MethodMatcher;
import org.luckypray.dexkit.result.ClassData;
import org.luckypray.dexkit.result.MethodData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import ps.reso.instaeclipse.Xposed.Module;
import ps.reso.instaeclipse.utils.core.DexKitCache;
import ps.reso.instaeclipse.utils.feature.FeatureFlags;
import ps.reso.instaeclipse.utils.feature.FeatureStatusTracker;
import ps.reso.instaeclipse.utils.log.ModuleLog;

public class DevOptionsUnlockHook {

    // MobileConfig param ID backing the "is employee" gate (LX/02il;->A00 in IG 429,
    // renamed to LX/5sG;->A00 in IG 437+ after Meta re-generated the config schema).
    // Both static (UserSession)Z accessors share this exact shape: null-check ->
    // resolve UserSession to a MobileConfigUnsafeContext -> boolean getter with a
    // hardcoded param ID. Try known IDs newest-first so current versions resolve fast.
    private static final long[] IS_EMPLOYEE_CONFIG_IDS = {
            36310830341423371L, // IG 437+ (LX/5sG;->A00, 0x8100820000010b)
            36310856111227168L, // IG <= 429 (LX/02il;->A00, 0x81008800000120)
    };

    public void handleDevOptions(DexKitBridge bridge) {
        if (DexKitCache.isCacheValid()) {
            String cachedClass = DexKitCache.loadString("DevOptionsClass");
            if (cachedClass != null) {
                hookBooleanMethodsViaReflection(cachedClass);
                return;
            }
        }
        try {
            findAndHookDynamicMethod(bridge);
        } catch (Exception e) {
            ModuleLog.line("(InstaEclipse | DevOptionsEnable): ❌ Error handling Dev Options: " + e.getMessage());
        }
    }

    private void findAndHookDynamicMethod(DexKitBridge bridge) {
        try {
            // Tier 1: Existing String-based search
            ModuleLog.line("(InstaEclipse | DevOptionsEnable): 🔍 Discovery Tier 1 (String)...");
            List<ClassData> classes = bridge.findClass(FindClass.create()
                    .matcher(ClassMatcher.create().usingStrings("is_employee"))
            );

            boolean found = false;
            if (!classes.isEmpty()) {
                for (ClassData classData : classes) {
                    String className = classData.getName();
                    if (!className.startsWith("X.")) continue;

                    List<MethodData> methods = bridge.findMethod(FindMethod.create()
                            .matcher(MethodMatcher.create()
                                    .declaredClass(className)
                                    .usingStrings("is_employee"))
                    );

                    for (MethodData method : methods) {
                        if (inspectInvokedMethods(bridge, method)) {
                            found = true;
                            break;
                        }
                    }
                    if (found) break;
                }
            }

            // Tier 2: Failover to MobileConfig ID (The "Golden Anchor")
            if (!found) {
                ModuleLog.line("(InstaEclipse | DevOptionsEnable): ⚠️ Tier 1 failed. Discovery Tier 2 (Config ID)...");
                for (long configId : IS_EMPLOYEE_CONFIG_IDS) {
                    List<MethodData> idMethods = bridge.findMethod(FindMethod.create()
                            .matcher(MethodMatcher.create()
                                    .usingNumbers(configId)
                                    .returnType("boolean")
                                    .paramCount(1))
                    );

                    if (!idMethods.isEmpty()) {
                        String targetClass = idMethods.get(0).getClassName();
                        ModuleLog.line("(InstaEclipse | DevOptionsEnable): 🎯 Found via Config ID " + configId + " in: " + targetClass);
                        DexKitCache.saveString("DevOptionsClass", targetClass);
                        hookAllBooleanMethodsInClass(bridge, targetClass);
                        found = true;
                        break;
                    }
                }
            }

            // Final Debug Trace: If both fail, log where the string is used ANYWHERE
            if (!found) {
                ModuleLog.line("(InstaEclipse | DevOptionsEnable): ❌ Tier 2 failed. Debugging global references...");
                List<MethodData> debugMethods = bridge.findMethod(FindMethod.create()
                        .matcher(MethodMatcher.create().usingStrings("is_employee")));
                for (MethodData m : debugMethods) {
                    ModuleLog.line("(InstaEclipse | DevOptionsDebug): String 'is_employee' found in: " + m.getClassName() + "." + m.getName());
                }
            }

        } catch (Exception e) {
            ModuleLog.line("(InstaEclipse | DevOptionsEnable): ❌ Error during discovery: " + e.getMessage());
        }
    }

    private boolean inspectInvokedMethods(DexKitBridge bridge, MethodData method) {
        try {
            List<MethodData> invokedMethods = method.getInvokes();
            if (invokedMethods.isEmpty()) return false;

            for (MethodData invokedMethod : invokedMethods) {
                String returnType = String.valueOf(invokedMethod.getReturnType());
                if (!returnType.contains("boolean")) continue;

                List<String> paramTypes = new ArrayList<>();
                for (Object param : invokedMethod.getParamTypes()) {
                    paramTypes.add(String.valueOf(param));
                }

                if (paramTypes.size() == 1 && paramTypes.get(0).contains("com.instagram.common.session.UserSession")) {
                    String targetClass = invokedMethod.getClassName();
                    ModuleLog.line("(InstaEclipse | DevOptionsEnable): 📦 Hooking via String detection: " + targetClass);
                    DexKitCache.saveString("DevOptionsClass", targetClass);
                    hookAllBooleanMethodsInClass(bridge, targetClass);
                    return true;
                }
            }
        } catch (Exception e) {
            ModuleLog.line("(InstaEclipse | DevOptionsEnable): ❌ Error inspecting invoked methods: " + e.getMessage());
        }
        return false;
    }

    private void hookBooleanMethodsViaReflection(String className) {
        try {
            Class<?> clazz = Module.hostClassLoader.loadClass(className);
            XC_MethodHook hook = new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    if (FeatureFlags.isDevEnabled) {
                        param.setResult(true);
                        FeatureStatusTracker.setHooked("DevOptions");
                    }
                }
            };
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.getReturnType() != boolean.class) continue;
                Class<?>[] params = m.getParameterTypes();
                if (params.length != 1) continue;
                if (!params[0].getName().equals("com.instagram.common.session.UserSession")) continue;
                m.setAccessible(true);
                XposedBridge.hookMethod(m, hook);
                ModuleLog.line("(InstaEclipse | DevOptionsEnable): ✅ Hooked (cache): " + className + "." + m.getName());
            }
        } catch (Throwable e) {
            ModuleLog.line("(InstaEclipse | DevOptionsEnable): ❌ Reflection fallback failed: " + e.getMessage());
        }
    }

    private void hookAllBooleanMethodsInClass(DexKitBridge bridge, String className) {
        try {
            List<MethodData> methods = bridge.findMethod(FindMethod.create()
                    .matcher(MethodMatcher.create().declaredClass(className))
            );

            for (MethodData method : methods) {
                String returnType = String.valueOf(method.getReturnType());
                List<String> paramTypes = new ArrayList<>();
                for (Object param : method.getParamTypes()) {
                    paramTypes.add(String.valueOf(param));
                }

                if (returnType.contains("boolean") && paramTypes.size() == 1 && paramTypes.get(0).contains("com.instagram.common.session.UserSession")) {
                    try {
                        Method targetMethod = method.getMethodInstance(Module.hostClassLoader);
                        XposedHelpers.findAndHookMethod(targetMethod.getDeclaringClass(), targetMethod.getName(), targetMethod.getParameterTypes()[0], new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) {
                                if (FeatureFlags.isDevEnabled) {
                                    param.setResult(true);
                                    FeatureStatusTracker.setHooked("DevOptions");
                                }
                            }
                        });
                        ModuleLog.line("(InstaEclipse | DevOptionsEnable): ✅ Hooked: " + method.getClassName() + "." + method.getName());
                    } catch (Throwable e) {
                        ModuleLog.line("(InstaEclipse | DevOptionsEnable): ❌ Failed to hook " + method.getName() + ": " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            ModuleLog.line("(InstaEclipse | DevOptionsEnable): ❌ Error while hooking class: " + className + " → " + e.getMessage());
        }
    }
}