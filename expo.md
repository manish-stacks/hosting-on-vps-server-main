# 📱 Expo App Deployment Guide (Start → APK → Play Store → OTA Update)

This guide explains the complete workflow for building and deploying a **React Native app using Expo**.

You will learn:

- Create Expo project
- Run app locally
- Build APK / AAB
- Upload to Play Store
- Update app without Play Store (OTA Updates)

---

# ⚙️ Requirements

Install these first:

### Install Node.js
https://nodejs.org

Check version

```bash
node -v
npm -v
```

---

### Install Expo CLI

```bash
npm install -g expo-cli
```

Check installation

```bash
expo --version
```

---

# 🚀 Step 1: Create Expo Project

Create a new Expo project.

```bash
npx create-expo-app myApp
```

Go inside project folder

```bash
cd myApp
```

Start project

```bash
npm start
```

or

```bash
expo start
```

---

# 📱 Step 2: Run App on Mobile

Install **Expo Go** app from Play Store.

Open Expo Go → Scan QR Code shown in terminal.

Your app will run on your phone.

---

# 📂 Project Structure Example

```
myApp
 ┣ assets
 ┣ components
 ┣ screens
 ┣ App.js
 ┣ package.json
 ┗ app.json
```

---

# ⚡ Step 3: Install EAS CLI (Expo Application Services)

Expo now uses **EAS Build** instead of the old `expo build` command.

Install globally:

```bash
npm install -g eas-cli
```

Check version

```bash
eas --version
```

---

# 🔑 Step 4: Login to Expo

```bash
eas login
```

Enter your Expo account credentials.

Create account if needed:

https://expo.dev/signup

---

# ⚙️ Step 5: Configure EAS Build

Run:

```bash
eas build:configure
```

This will create a file:

```
eas.json
```

Example:

```json
{
  "build": {
    "preview": {
      "android": {
        "buildType": "apk"
      }
    },
    "production": {
      "android": {
        "buildType": "app-bundle"
      }
    }
  }
}
```

---

# 📦 Step 6: Build APK (Testing)

Run:

```bash
eas build -p android --profile preview
```

This generates:

```
APK FILE
```

APK is used for:

- Testing
- Direct installation
- Client demos

---

# 🏪 Step 7: Build AAB (Play Store)

For Play Store you must upload **AAB file**.

Run:

```bash
eas build -p android --profile production
```

This generates:

```
AAB FILE
```

---

# ⬇️ Step 8: Download Build

After build finishes Expo will show a link.

Example:

```
https://expo.dev/artifacts/.....
```

Download your:

```
APK / AAB
```

---

# 🏪 Step 9: Upload to Google Play Store

Go to:

https://play.google.com/console

Create new app.

Upload the **AAB file**.

Fill required details:

- App name
- App icon
- Screenshots
- Privacy policy
- Description

Submit for review.

---

# 🔄 Step 10: Update App Without Play Store (OTA Update)

Expo supports **Over The Air Updates**.

You can update the app **without publishing a new Play Store version**.

---

## Publish Update

```bash
eas update --branch production -p android
```

Users will receive update automatically when opening the app.

---

# 🌿 Branch Based Updates

Example

```
production
staging
development
```

Update staging branch:

```bash
eas update --branch staging -p android
```

---

# ⚙️ Step 11: Configure app.json

Example configuration:

```json
{
  "expo": {
    "name": "MyApp",
    "slug": "myapp",
    "version": "1.0.0",
    "orientation": "portrait",
    "platforms": ["android"],
    "updates": {
      "fallbackToCacheTimeout": 0
    },
    "runtimeVersion": {
      "policy": "sdkVersion"
    }
  }
}
```

---

# 📲 Step 12: Install APK on Device

Transfer APK to mobile.

Install using:

```
Install Unknown Apps
```

Open the app.

---

# 🛠 Useful EAS Commands

### Check builds

```bash
eas build:list
```

---

### Cancel build

```bash
eas build:cancel
```

---

### View logs

```bash
eas build:view
```

---

### Login again

```bash
eas login
```

---

# 🧹 Clear Expo Cache

If errors occur:

```bash
expo start -c
```

---

# 📊 Project Build Flow

```
Create Expo App
        ↓
Develop App
        ↓
Install EAS CLI
        ↓
eas build:configure
        ↓
Build APK (Testing)
        ↓
Build AAB (Play Store)
        ↓
Upload to Play Store
        ↓
Use eas update for OTA updates
```

---

# 🎉 Done

You now know how to:

✅ Create Expo App  
✅ Run on Mobile  
✅ Build APK  
✅ Build AAB  
✅ Upload to Play Store  
✅ Push OTA Updates  

🚀 Your Expo app is ready for production.
