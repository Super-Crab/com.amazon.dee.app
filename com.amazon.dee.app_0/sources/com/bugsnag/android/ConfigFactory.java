package com.bugsnag.android;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
class ConfigFactory {
    private static final String BUGSNAG_NAMESPACE = "com.bugsnag.android";
    private static final String MF_API_KEY = "com.bugsnag.android.API_KEY";
    private static final String MF_APP_VERSION = "com.bugsnag.android.APP_VERSION";
    private static final String MF_AUTO_CAPTURE_SESSIONS = "com.bugsnag.android.AUTO_CAPTURE_SESSIONS";
    static final String MF_BUILD_UUID = "com.bugsnag.android.BUILD_UUID";
    private static final String MF_DETECT_ANRS = "com.bugsnag.android.DETECT_ANRS";
    private static final String MF_DETECT_NDK_CRASHES = "com.bugsnag.android.DETECT_NDK_CRASHES";
    private static final String MF_ENABLE_EXCEPTION_HANDLER = "com.bugsnag.android.ENABLE_EXCEPTION_HANDLER";
    private static final String MF_ENDPOINT = "com.bugsnag.android.ENDPOINT";
    private static final String MF_PERSIST_USER_BETWEEN_SESSIONS = "com.bugsnag.android.PERSIST_USER_BETWEEN_SESSIONS";
    private static final String MF_RELEASE_STAGE = "com.bugsnag.android.RELEASE_STAGE";
    private static final String MF_SEND_THREADS = "com.bugsnag.android.SEND_THREADS";
    private static final String MF_SESSIONS_ENDPOINT = "com.bugsnag.android.SESSIONS_ENDPOINT";
    private static final String MF_VERSION_CODE = "com.bugsnag.android.VERSION_CODE";

    ConfigFactory() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static Configuration createNewConfiguration(@NonNull Context context, String str, boolean z) {
        Context applicationContext = context.getApplicationContext();
        boolean isEmpty = TextUtils.isEmpty(str);
        if (isEmpty) {
            try {
                str = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128).metaData.getString(MF_API_KEY);
            } catch (Exception unused) {
                Logger.warn("Bugsnag is unable to read api key from manifest.");
            }
        }
        if (str != null) {
            Configuration configuration = new Configuration(str);
            configuration.setEnableExceptionHandler(z);
            if (isEmpty) {
                try {
                    populateConfigFromManifest(configuration, applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128).metaData);
                } catch (Exception unused2) {
                    Logger.warn("Bugsnag is unable to read config from manifest.");
                }
            }
            return configuration;
        }
        throw new NullPointerException("You must provide a Bugsnag API key");
    }

    static void populateConfigFromManifest(@NonNull Configuration configuration, @NonNull Bundle bundle) {
        configuration.setBuildUUID(bundle.getString(MF_BUILD_UUID));
        configuration.setAppVersion(bundle.getString(MF_APP_VERSION));
        configuration.setReleaseStage(bundle.getString(MF_RELEASE_STAGE));
        if (bundle.containsKey(MF_VERSION_CODE)) {
            configuration.setVersionCode(Integer.valueOf(bundle.getInt(MF_VERSION_CODE)));
        }
        if (bundle.containsKey(MF_ENDPOINT)) {
            configuration.setEndpoints(bundle.getString(MF_ENDPOINT), bundle.getString(MF_SESSIONS_ENDPOINT));
        }
        configuration.setSendThreads(bundle.getBoolean(MF_SEND_THREADS, true));
        configuration.setPersistUserBetweenSessions(bundle.getBoolean(MF_PERSIST_USER_BETWEEN_SESSIONS, false));
        if (bundle.containsKey(MF_DETECT_NDK_CRASHES)) {
            configuration.setDetectNdkCrashes(bundle.getBoolean(MF_DETECT_NDK_CRASHES));
        }
        if (bundle.containsKey(MF_DETECT_ANRS)) {
            configuration.setDetectAnrs(bundle.getBoolean(MF_DETECT_ANRS));
        }
        if (bundle.containsKey(MF_AUTO_CAPTURE_SESSIONS)) {
            configuration.setAutoCaptureSessions(bundle.getBoolean(MF_AUTO_CAPTURE_SESSIONS));
        }
        configuration.setEnableExceptionHandler(bundle.getBoolean(MF_ENABLE_EXCEPTION_HANDLER, true));
    }
}
