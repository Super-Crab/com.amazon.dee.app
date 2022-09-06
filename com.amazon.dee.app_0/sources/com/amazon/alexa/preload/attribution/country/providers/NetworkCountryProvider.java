package com.amazon.alexa.preload.attribution.country.providers;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public class NetworkCountryProvider extends CountryProvider {
    private static final String NETWORK_SOURCE_ID = "95";
    private final TelephonyManager mTelephonyManager;

    public NetworkCountryProvider(@NonNull TelephonyManager telephonyManager) {
        super(NETWORK_SOURCE_ID, true);
        this.mTelephonyManager = telephonyManager;
    }

    @Override // com.amazon.alexa.preload.attribution.country.providers.CountryProvider
    @Nullable
    public String getCountryCode() {
        String networkCountryIso = this.mTelephonyManager.getNetworkCountryIso();
        if (!TextUtils.isEmpty(networkCountryIso)) {
            return networkCountryIso;
        }
        return null;
    }
}
