

generate the android folder keystore
keytool -genkey -v -keystore my-key.keystore -alias my-key-alias -keyalg RSA -keysize 2048 -validity 10000



2.android/app folder ke andar keystore copy karo.
3.android/gradle.properties me ye add karo:

Step 1: gradle.properties update karo
android/gradle.properties me ye 4 lines add karo (apne password/alias ke hisaab se):

MYAPP_UPLOAD_STORE_FILE=my-key.keystore
MYAPP_UPLOAD_KEY_ALIAS=my-key-alias
MYAPP_UPLOAD_STORE_PASSWORD=YourKeystorePassword
MYAPP_UPLOAD_KEY_PASSWORD=YourKeystorePassword


Step 2: build.gradle (app-level) update karo
File: android/app/build.gradle

signingConfigs {
    release {
        if (project.hasProperty('MYAPP_UPLOAD_STORE_FILE')) {
            storeFile file(MYAPP_UPLOAD_STORE_FILE)
            storePassword MYAPP_UPLOAD_STORE_PASSWORD
            keyAlias MYAPP_UPLOAD_KEY_ALIAS
            keyPassword MYAPP_UPLOAD_KEY_PASSWORD
        }
    }
}

buildTypes {
    release {
        signingConfig signingConfigs.release
        minifyEnabled enableProguardInReleaseBuilds
        shrinkResources enableProguardInReleaseBuilds
        proguardFiles getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro"
    }
}



Step 3: APK build karo
cd android
./gradlew assembleDebug ya // gradlew assembleRelease

Iske baad aapko APK milega:
android/app/build/outputs/apk/debug/app-debug.apk