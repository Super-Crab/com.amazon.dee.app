package com.amazon.alexa.delegatedidentity.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
/* loaded from: classes6.dex */
public class LocalAndroidKeyValueStore {
    public static final String STORAGE_NAMESPACE = "com.amazon.alexa.delegatedidentity.DelegatedTokenStorage.encrypt.namespace";
    private static final String TAG = "com.amazon.alexa.delegatedidentity.storage.LocalAndroidKeyValueStore";
    private final Context context;

    public LocalAndroidKeyValueStore(Context context) {
        this.context = context;
    }

    private SharedPreferences getSharedPreferences() {
        return this.context.getSharedPreferences(STORAGE_NAMESPACE, 0);
    }

    public String getStringValue(String str, String str2) {
        return getSharedPreferences().getString(str, str2);
    }

    public boolean setValue(String str, String str2) {
        boolean commit = getSharedPreferences().edit().putString(str, str2).commit();
        if (!commit) {
            Log.e(TAG, String.format("Failed to set key %s in the local key value store %s", str, STORAGE_NAMESPACE));
        }
        return commit;
    }
}
