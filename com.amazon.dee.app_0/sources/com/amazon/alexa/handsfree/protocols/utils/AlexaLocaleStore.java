package com.amazon.alexa.handsfree.protocols.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes8.dex */
public class AlexaLocaleStore {
    private static final String SEPARATOR = ",";
    @VisibleForTesting
    static final String SHARED_PREFERENCE_FILE = "AMPD_ALEXA_LOCALE_STORE";
    @VisibleForTesting
    static final String SHARED_PREFERENCE_KEY = "AMPD_CURRENT_ALEXA_LOCALE";
    private static final String TAG = "AlexaLocaleStore";
    private final SharedPreferences mSharedPreferences;

    public AlexaLocaleStore(@NonNull Context context) {
        this.mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE, 0);
    }

    @NonNull
    public List<Locale> getLocales() {
        String string = this.mSharedPreferences.getString(SHARED_PREFERENCE_KEY, Locale.getDefault().toLanguageTag());
        Log.d(TAG, "Pulling AlexaLocale: " + string);
        String[] split = string.split(",");
        ArrayList arrayList = new ArrayList();
        for (String str : split) {
            arrayList.add(Locale.forLanguageTag(str));
        }
        return arrayList;
    }

    public void setLocales(@NonNull List<Locale> list) {
        String str = TAG;
        Log.d(str, "Storing AlexaLocale: " + list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toLanguageTag());
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        this.mSharedPreferences.edit().putString(SHARED_PREFERENCE_KEY, sb.toString()).apply();
    }
}
