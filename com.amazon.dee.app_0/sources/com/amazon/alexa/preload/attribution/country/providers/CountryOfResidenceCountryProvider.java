package com.amazon.alexa.preload.attribution.country.providers;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPAccountManager;
/* loaded from: classes9.dex */
public class CountryOfResidenceCountryProvider extends CountryProvider {
    private static final String COUNTRY_OF_RESIDENCE_SOURCE_ID = "96";
    private static final String TAG = "CountryOfResidenceCountryProvider";
    private final Context mContext;
    private final MAPAccountManager mMapAccountManager;

    public CountryOfResidenceCountryProvider(@NonNull MAPAccountManager mAPAccountManager, @NonNull Context context) {
        super(COUNTRY_OF_RESIDENCE_SOURCE_ID, true);
        this.mMapAccountManager = mAPAccountManager;
        this.mContext = context;
    }

    @Nullable
    @VisibleForTesting
    Bundle getCorBundle(@NonNull String str) {
        return CustomerAttributeStore.getInstance(this.mContext).peekAttribute(str, CustomerAttributeKeys.KEY_COR);
    }

    @Override // com.amazon.alexa.preload.attribution.country.providers.CountryProvider
    @Nullable
    public String getCountryCode() {
        Bundle corBundle;
        String account = this.mMapAccountManager.getAccount();
        if (account == null || (corBundle = getCorBundle(account)) == null) {
            return null;
        }
        String string = corBundle.getString("value_key");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }
}
