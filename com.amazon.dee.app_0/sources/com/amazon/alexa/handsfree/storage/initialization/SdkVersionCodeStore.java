package com.amazon.alexa.handsfree.storage.initialization;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes8.dex */
public class SdkVersionCodeStore {
    @VisibleForTesting
    static final String SDK_VERSION_CODE_KEY = "com.amazon.alexa.handsfree.storage.initialization.SDK_VERSION_CODE_KEY";
    @VisibleForTesting
    static final String SHARED_PREFERENCES_FILE = "com.amazon.alexa.handsfree.storage.initialization.SDK_VERSION_CODE_STORE";

    public int getSdkVersionCode(@NonNull Context context) {
        return getSharedPreferences(context).getInt(SDK_VERSION_CODE_KEY, 0);
    }

    @VisibleForTesting
    SharedPreferences getSharedPreferences(@NonNull Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
    }

    public void setSdkVersionCode(@NonNull Context context, int i) {
        getSharedPreferences(context).edit().putInt(SDK_VERSION_CODE_KEY, i).apply();
    }
}
