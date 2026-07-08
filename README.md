<div align="center">
  <img src="assets/logo.png" alt="InstaEclipse" width="120" />
  <h1>InstaEclipse</h1>
  <p>A powerful LSPosed module that enhances your Instagram experience.</p>

  <p>
    <a href="https://github.com/ReSo7200/InstaEclipse/releases/latest"><img alt="GitHub Release" src="https://img.shields.io/github/v/release/ReSo7200/InstaEclipse?style=for-the-badge&logo=github&color=1a1a2e&labelColor=0d0d0d"/></a>
    <a href="https://github.com/ReSo7200/InstaEclipse/stargazers"><img alt="Stars" src="https://img.shields.io/github/stars/ReSo7200/InstaEclipse?style=for-the-badge&logo=github&color=1a1a2e&labelColor=0d0d0d"/></a>
    <a href="https://github.com/ReSo7200/InstaEclipse/releases/latest"><img alt="Downloads" src="https://img.shields.io/github/downloads/ReSo7200/InstaEclipse/total?style=for-the-badge&logo=github&color=1a1a2e&labelColor=0d0d0d"/></a>
    <a href="https://t.me/InstaEclipse"><img alt="Telegram" src="https://img.shields.io/badge/Telegram-Channel-26A5E4?style=for-the-badge&logo=telegram&labelColor=0d0d0d"/></a>
    <a href="LICENSE"><img alt="License" src="https://img.shields.io/github/license/ReSo7200/InstaEclipse?style=for-the-badge&color=1a1a2e&labelColor=0d0d0d"/></a>
  </p>

  <p>
    <a href="#-features">Features</a> •
    <a href="#-installation">Installation</a> •
    <a href="#-faq">FAQ</a> •
    <a href="#-contributors">Contributors</a>
  </p>
</div>

---

## Overview

**InstaEclipse** is an [LSPosed](https://github.com/JingMatrix/LSPosed) module built to unlock a richer Instagram experience — without root required (via [LSPatch](https://github.com/JingMatrix/LSPatch)).

It uses [DexKit](https://github.com/LuckyPray/DexKit) for dynamic class/method detection, keeping it compatible with new Instagram releases automatically.

> [!IMPORTANT]
> **InstaEclipse is being succeeded by the [Purrfect](https://t.me/purrfect_tg) project.** Development here will stay active until Purrfect reaches a stable release — follow the Telegram channel for progress updates.

---

## ✨ Features

<details>
<summary><b>👻 Ghost Mode</b> — Stay invisible without giving up functionality</summary>

<br/>

| Feature                                | Description |
|-----------------------------------------|---|
| Hide DM Seen                             | Read messages without sending the read receipt |
| Hide Typing Indicator                    | Type freely without the other person knowing |
| Hide Story Views                         | View stories without appearing in the viewer list |
| Hide Live Presence                       | Join lives anonymously |
| Bypass Screenshot Detection              | Take screenshots in DMs without triggering alerts |
| Allow Screenshots in DMs                 | Re-enable screenshot capability in restricted chats |
| Hide View Once Opened                    | Open view-once media without marking it as seen |
| Unlimited View-Once Replays              | Replay view-once media as many times as you want |
| Permanent View Once Media (Unstable)     | Prevent view-once media from disappearing |
| Keep Disappearing Messages               | Stop ephemeral messages from deleting |
| Quick Toggle                             | Enable/disable all ghost options from inside Instagram with one tap |

</details>

<details>
<summary><b>📥 Downloader</b> — Save media directly from Instagram</summary>

<br/>

| Feature | Description |
|---|---|
| Download Posts | Save single photos and carousel posts |
| Download Reels | Save reels to your device |
| Download Stories | Save stories before they disappear |
| Download Profile Pictures | Long-press a profile to save their picture |
| Custom Download Folder | Choose where files are saved |
| Username Subfolders | Organize downloads by username automatically |
| Add Timestamp to Filename | Append the download date/time to saved filenames |

</details>

<details>
<summary><b>🎬 Video Quality</b> — Watch Reels the way you want</summary>

<br/>

| Feature | Description |
|---|---|
| Force Reels Quality | Pin Reels playback to a fixed quality (Auto, 360p–1080p, or Max Available) instead of Instagram's adaptive bitrate |

</details>

<details>
<summary><b>🎨 Custom Theme</b> — Recolor Instagram to match your style</summary>

<br/>

| Feature | Description |
|---|---|
| Built-in Presets | Switch between ready-made color palettes with one tap |
| Custom Colors | Fine-tune background, surface, text, accent, icons, and more with a full color picker |
| Live Apply | Changes apply immediately across the app without restarting Instagram |

</details>

<details>
<summary><b>📍 Location Spoof</b> — Control what Instagram thinks your GPS says</summary>

<br/>

| Feature | Description |
|---|---|
| Spoof GPS Location | Fake Instagram's location reporting to a location of your choosing |
| Interactive Map Picker | Search for a place or drop a pin directly on a map to set the spoofed location |

</details>

<details>
<summary><b>🎛️ Developer Options</b> — Access Instagram's hidden internal settings</summary>

<br/>

| Feature | Description |
|---|---|
| MetaConfig Panel | Unlock the full MetaConfig developer panel |
| Import/Export Config | Import or export your config as JSON |
| Restore to Default Config | Reset the Developer Config back to the bundled defaults |
| Remove Build Expired Popup | Dismiss the "Build Expired" popup on older builds |
| Clear Hooks Cache | Force InstaEclipse to re-scan Instagram on next launch |

> **Note:** Use Beta or Alpha Instagram builds for best results. Stable builds apply obfuscation that makes some labels appear as numbers.

</details>

<details>
<summary><b>✨ Clean Feed</b> — See only what matters</summary>

<br/>

| Feature | Description |
|---|---|
| Hide Suggestions in Feed | Remove suggested posts, reels widgets, and other non-followed content from your feed |
| Hide Threads Suggestions | Remove Threads cross-promotion units from your feed independently |

</details>

<details>
<summary><b>🛡️ Ad & Analytics Blocking</b> — Browse without being tracked</summary>

<br/>

| Feature | Description |
|---|---|
| Block Ads | Block sponsored posts and ads from your feed |
| Block Analytics | Block Instagram's analytics and telemetry |
| Disable Tracking Links | Disable tracking links in DMs and posts |

</details>

<details>
<summary><b>🧘 Distraction-Free Mode</b> — Take back control of your attention</summary>

<br/>

| Feature | Description |
|---|---|
| Disable Sections | Disable Stories, Feed, Reels, Explore, or Comments independently |
| Extreme Mode | Permanently removes distractions until reinstall |

> After enabling, force stop Instagram and clear its cache.

</details>

<details>
<summary><b>⚙️ Miscellaneous</b> — Quality of life improvements</summary>

<br/>

| Feature | Description |
|---|---|
| Disable Story Auto-Swipe | Stop stories from flipping automatically |
| Disable Video Autoplay | Videos don't play until you tap them |
| Spoof Last Seen (freeze) | Freeze your "last active" status instead of updating it live |
| Follower Toast | See if someone follows you back when you visit their profile |
| Copy Comment | Copy any comment text with one tap |
| Copy Caption | Copy a post or reel's caption from the overflow menu |
| Photo Zoom (Long-Press) | Long-press a feed photo to view it full-screen with pinch-to-zoom |
| View Story Mentions | See all @mentions in a story at once |
| Disable Discover People | Remove the "People you may know" section |
| Disable Double Tap to Like | Prevent accidentally liking posts and reels by double tapping |

</details>

<details>
<summary><b>📋 Log Viewer</b> — See exactly what InstaEclipse is doing</summary>

<br/>

| Feature | Description |
|---|---|
| In-App Logs | View hook status and activity logs from both Instagram and the companion app, without adb |

</details>

<details>
<summary><b>💾 Backup & Restore</b> — Keep your settings safe</summary>

<br/>

| Feature | Description |
|---|---|
| Export Settings | Save all your InstaEclipse settings to a file |
| Restore Settings | Restore settings from a backup — useful when switching devices or reinstalling |

</details>

---

## 📱 Instagram Compatibility

While InstaEclipse is built to stay compatible with new Instagram releases automatically, some features may be unstable on specific versions. If something stops working after an Instagram update, check the [Telegram channel](https://t.me/InstaEclipse) for status updates before opening an issue.

| |                                                                                                                 |
|---|-----------------------------------------------------------------------------------------------------------------|
| **Latest tested version** | [`436.0.0.14.73`](https://www.apkmirror.com/apk/instagram/instagram-instagram/instagram-436-0-0-14-73-release/) |
| **Recommended build type** | Beta or Alpha (from APKMirror)                                                                                  |

---

## 📲 Installation

> ⚠️ **Use Instagram from [APKMirror](https://www.apkmirror.com/apk/instagram/instagram-instagram/)** — the Google Play version may not be fully supported.

Download the latest InstaEclipse APK from [**Releases →**](https://github.com/ReSo7200/InstaEclipse/releases/latest)

---

### ✅ Root — LSPosed

> Requires [JingMatrix's LSPosed](https://github.com/JingMatrix/LSPosed/releases/latest)

**1. Install InstaEclipse**
Download and install the InstaEclipse APK.

**2. Enable the module**
Open **LSPosed Manager** → **Modules** → find **InstaEclipse** → enable it and scope it to **Instagram**.

**3. Restart Instagram**
Force stop Instagram, then reopen it.

**4. Open InstaEclipse**
Inside Instagram, **long-press the search icon** to open the InstaEclipse menu.

 > [!CAUTION]
> **Hide My Applist users:** Do **not** add InstaEclipse to the hidden apps list. InstaEclipse must remain visible to Instagram — hiding it will cause crashes or features to stop working entirely.

---

### 🟡 No Root — LSPatch

> Requires [JingMatrix's LSPatch](https://github.com/JingMatrix/LSPatch/releases/latest)

**1. Install InstaEclipse**
Download and install the InstaEclipse APK.

**2. Install LSPatch**
Download and install LSPatch (JingMatrix fork).

**3. Patch Instagram**
- Open LSPatch → tap **+** → select the Instagram APK (or the installed app)
- Choose **Local Patch Mode**
- Enable **"Inject loader dex"**
- Tap **Start Patch** and wait

**4. Install the patched APK**
Install the output APK and log in to Instagram.

**5. Enable the module**
Reopen LSPatch → **Manage** → find Instagram → **Modules** → enable **InstaEclipse**.

**6. Open InstaEclipse**
Inside Instagram, **long-press the search icon** to open the InstaEclipse menu.

---

## ❓ FAQ

**Module not working / features not applying?**
Disable and re-enable the module in LSPosed/LSPatch, then force stop and restart Instagram.

**Developer options labels look like numbers?**
This is obfuscation from Instagram's Stable build. Switch to a Beta or Alpha version from APKMirror.

**Distraction-Free enabled but content still shows?**
Force stop Instagram and clear its cache after enabling.

**Not working on the Google Play version?**
Download Instagram from [APKMirror](https://www.apkmirror.com/apk/instagram/instagram-instagram/) instead.

**Some features not working even after enabling them?**
Certain Instagram internal configurations can silently break specific features. A ready-to-use config that fixes known issues is available on Telegram — [**grab it here**](https://t.me/InstaEclipse/52).

**Still stuck?**
Join the [Telegram group](https://t.me/instaEclipse_discussion) and ask — someone will help.

---

## 🗺️ Using the Features

Once InstaEclipse is installed and active, **long-press the search icon** inside Instagram to open the InstaEclipse menu. From there you can toggle any feature on or off without restarting.

For guides on specific features, tips, and video walkthroughs:

- 📢 **Announcements & updates** → [Telegram Channel](https://t.me/InstaEclipse)
- 💬 **Questions & community help** → [Telegram Discussion Group](https://t.me/instaEclipse_discussion)

---

## 👥 Contributors

<div align="center">

### Project Owner

<a href="https://github.com/ReSo7200">
  <img src="https://github.com/ReSo7200.png" width="80" alt="ReSo7200" style="border-radius:50%"/><br/>
  <b>ReSo7200</b>
</a>

<br/><br/>

### All Contributors

<a href="https://github.com/ReSo7200/InstaEclipse/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=ReSo7200/InstaEclipse" alt="Contributors"/>
</a>

<sub>Made with <a href="https://contrib.rocks">contrib.rocks</a></sub>

<br/>

**Translation Contributors**
A big thank you to everyone who helped translate InstaEclipse into multiple languages — you make this accessible to users around the world.

</div>

---

## 🛠️ Built With

- [JingMatrix/LSPosed](https://github.com/JingMatrix/LSPosed) — Xposed framework foundation
- [LuckyPray/DexKit](https://github.com/LuckyPray/DexKit) — Dynamic DEX analysis for Instagram compatibility

---

## 🤝 Contributing

Contributions are welcome — whether it's a bug report, feature request, translation, or pull request.

- **Bug?** → [Open a bug report](https://github.com/ReSo7200/InstaEclipse/issues/new/choose)
- **Idea?** → [Submit a feature request](https://github.com/ReSo7200/InstaEclipse/issues/new/choose)
- **Code?** → Fork the repo and open a PR

---

<div align="center">
  <sub>Made with ❤️ by the InstaEclipse team</sub><br/>
  <sub>InstaEclipse is not affiliated with Meta or Instagram. See <a href="DISCLAIMER.md">Disclaimer</a>.</sub>
</div>
