package com.amazon.alexa.voice.locale;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class UserLocalePreference implements LocalePreference {
    @VisibleForTesting
    static final String USER_LOCALE_UPDATE_PREFS_FILE_NAME = "user_locale_prefs";
    @VisibleForTesting
    static final String USER_LOCALE_UPDATE_PREFS_KEY = "locale_updated_by_user";
    private final SharedPreferences sharedPreferences;

    public UserLocalePreference(Context context) {
        this.sharedPreferences = context.getSharedPreferences(USER_LOCALE_UPDATE_PREFS_FILE_NAME, 0);
    }

    @Override // com.amazon.alexa.voice.locale.LocalePreference
    public void clearUpdatedByUserFlag() {
        this.sharedPreferences.edit().remove(USER_LOCALE_UPDATE_PREFS_KEY).apply();
    }

    @Override // com.amazon.alexa.voice.locale.LocalePreference
    public void settingUpdatedByUser(boolean z) {
        GeneratedOutlineSupport1.outline143(this.sharedPreferences, USER_LOCALE_UPDATE_PREFS_KEY, z);
    }

    @Override // com.amazon.alexa.voice.locale.LocalePreference
    public boolean wasUpdatedByUser() {
        return this.sharedPreferences.getBoolean(USER_LOCALE_UPDATE_PREFS_KEY, false);
    }
}
