package com.amazon.alexa.handsfree.storage.initialization;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes8.dex */
public class DspApkVersionCodeStore {
    @VisibleForTesting
    static final String DSP_APK_VERSION_CODE_KEY = "com.amazon.alexa.handsfree.metrics.utils.dsp_apk_version_code";
    public static final int MIN_ANDROID_VERSION_CODE = 1;
    @VisibleForTesting
    static final String SHARED_PREFERENCES_FILE = "com.amazon.alexa.handsfree.storage.initialization.DspApkVersionCodeStore";

    public int getDspApkVersionCode(@NonNull Context context) {
        return getSharedPreferences(context).getInt(DSP_APK_VERSION_CODE_KEY, 1);
    }

    @VisibleForTesting
    SharedPreferences getSharedPreferences(@NonNull Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
    }

    public void setDspApkVersionCode(@NonNull Context context, int i) {
        getSharedPreferences(context).edit().putInt(DSP_APK_VERSION_CODE_KEY, i).apply();
    }
}
