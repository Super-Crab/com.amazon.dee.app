package com.amazon.alexa.preload.attribution.country.providers;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public class SimCountryProvider extends CountryProvider {
    private static final String SIM_SOURCE_ID = "94";
    private final TelephonyManager mTelephonyManager;

    public SimCountryProvider(@NonNull TelephonyManager telephonyManager) {
        super(SIM_SOURCE_ID, false);
        this.mTelephonyManager = telephonyManager;
    }

    @Override // com.amazon.alexa.preload.attribution.country.providers.CountryProvider
    @Nullable
    public String getCountryCode() {
        String simCountryIso = this.mTelephonyManager.getSimCountryIso();
        if (!TextUtils.isEmpty(simCountryIso)) {
            return simCountryIso;
        }
        return null;
    }
}
