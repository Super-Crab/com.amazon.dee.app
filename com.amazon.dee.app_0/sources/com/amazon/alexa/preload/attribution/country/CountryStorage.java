package com.amazon.alexa.preload.attribution.country;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.preload.attribution.country.providers.CountryProvider;
/* loaded from: classes9.dex */
class CountryStorage {
    @VisibleForTesting
    static final String SHARED_PREFS_COUNTRY_KEY = "Country";
    @VisibleForTesting
    static final String SHARED_PREFS_SOURCE_ID = "SourceId";
    private final SharedPreferences mSharedPreferences;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CountryStorage(@NonNull SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getCountry() {
        return this.mSharedPreferences.getString(SHARED_PREFS_COUNTRY_KEY, "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getSourceId() {
        return this.mSharedPreferences.getString(SHARED_PREFS_SOURCE_ID, "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void saveCountry(@NonNull CountryProvider countryProvider) {
        this.mSharedPreferences.edit().putString(SHARED_PREFS_COUNTRY_KEY, countryProvider.getCountryCode()).putString(SHARED_PREFS_SOURCE_ID, countryProvider.getSourceId()).apply();
    }
}
