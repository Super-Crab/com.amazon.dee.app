package com.amazon.alexa.handsfree.settings.locale;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class LocalePreferencesManager {
    @VisibleForTesting
    static final String USER_LOCALE_UPDATE_LAST_LOCALE_CERTIFIED_AND_SUPPORTED = "last_updated_locale_certified_and_supported";
    private static final String USER_LOCALE_UPDATE_PREFS_FILE_NAME = "user_locale_prefs";
    @VisibleForTesting
    static final String USER_LOCALE_UPDATE_PREFS_KEY = "locale_updated_by_user";
    private final SharedPreferences mSharedPreferences;

    public LocalePreferencesManager(@NonNull Context context) {
        this(context.getSharedPreferences(USER_LOCALE_UPDATE_PREFS_FILE_NAME, 0));
    }

    public void settingLastUpdatedLocaleWasCertifiedAndSupported(boolean z) {
        GeneratedOutlineSupport1.outline143(this.mSharedPreferences, USER_LOCALE_UPDATE_LAST_LOCALE_CERTIFIED_AND_SUPPORTED, z);
    }

    public void settingUpdatedByUser() {
        GeneratedOutlineSupport1.outline143(this.mSharedPreferences, USER_LOCALE_UPDATE_PREFS_KEY, true);
    }

    public boolean wasLastUpdatedLocaleCertifiedAndSupported() {
        return this.mSharedPreferences.getBoolean(USER_LOCALE_UPDATE_LAST_LOCALE_CERTIFIED_AND_SUPPORTED, false);
    }

    public boolean wasUpdatedByUser() {
        return this.mSharedPreferences.getBoolean(USER_LOCALE_UPDATE_PREFS_KEY, false);
    }

    @VisibleForTesting
    LocalePreferencesManager(@NonNull SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
    }
}
