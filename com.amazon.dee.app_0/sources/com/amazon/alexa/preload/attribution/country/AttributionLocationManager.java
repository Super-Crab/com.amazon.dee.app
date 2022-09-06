package com.amazon.alexa.preload.attribution.country;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.preload.attribution.country.providers.CountryOfResidenceCountryProvider;
import com.amazon.alexa.preload.attribution.country.providers.CountryProvider;
import com.amazon.alexa.preload.attribution.country.providers.NetworkCountryProvider;
import com.amazon.alexa.preload.attribution.country.providers.SimCountryProvider;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes9.dex */
public class AttributionLocationManager implements AccountAddedListener {
    public static final String COUNTRY_REGION_FORMAT = "%s-%s";
    private static final String TAG = "AttributionLocationManager";
    @VisibleForTesting
    static final String UNKNOWN_COUNTRY = "xx";
    @VisibleForTesting
    static final String UNKNOWN_REGION_ID = "99";
    private final Context mContext;
    private final List<CountryProvider> mCountryProviders;
    private final CountryStorage mCountryStorage;
    private LocationChangeListener mLocationChangeListener;
    private final MAPAccountManager mMAPAccountManager;

    /* loaded from: classes9.dex */
    public interface LocationChangeListener {
        void onLocationChanged(@NonNull String str);
    }

    public AttributionLocationManager(@NonNull Context context, @NonNull SharedPreferences sharedPreferences) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        this.mMAPAccountManager = new MAPAccountManager(context);
        this.mCountryStorage = new CountryStorage(sharedPreferences);
        this.mCountryProviders = Collections.unmodifiableList(Arrays.asList(new SimCountryProvider(telephonyManager), new NetworkCountryProvider(telephonyManager), new CountryOfResidenceCountryProvider(this.mMAPAccountManager, context)));
        this.mContext = context;
        registerForAccountAddedBroadcasts(context);
    }

    private void registerForAccountAddedBroadcasts(@NonNull Context context) {
        context.registerReceiver(new MAPBroadcastReceiver(this), GeneratedOutlineSupport1.outline10("com.amazon.dcp.sso.action.account.added"), AccountConstants.PERMISSION_AMAZON_ACCOUNT_CHANGED, null);
    }

    @NonNull
    public String getLocationString() {
        String country = this.mCountryStorage.getCountry();
        String sourceId = this.mCountryStorage.getSourceId();
        if (!TextUtils.isEmpty(country) && TextUtils.isEmpty(sourceId)) {
            return getLocationString(country);
        }
        for (CountryProvider countryProvider : this.mCountryProviders) {
            if (!TextUtils.isEmpty(country) && countryProvider.getSourceId().equals(sourceId)) {
                String.format("Already used %s to calculate country information", countryProvider.getClass().getSimpleName());
                return getLocationString(country.toLowerCase(), sourceId);
            } else if (!TextUtils.isEmpty(countryProvider.getCountryCode())) {
                String.format("Obtained new country information using %s", countryProvider.getClass().getSimpleName());
                this.mCountryStorage.saveCountry(countryProvider);
                return getLocationString(countryProvider);
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No country information obtained from ");
                outline107.append(countryProvider.getClass().getSimpleName());
                outline107.toString();
            }
        }
        return getLocationString(UNKNOWN_COUNTRY, UNKNOWN_REGION_ID);
    }

    @Override // com.amazon.alexa.preload.attribution.country.AccountAddedListener
    public void onAccountAdded() {
        if (this.mLocationChangeListener != null) {
            String account = this.mMAPAccountManager.getAccount();
            if (TextUtils.isEmpty(account)) {
                return;
            }
            CustomerAttributeStore.getInstance(this.mContext).getAttribute(account, CustomerAttributeKeys.KEY_COR, new Callback() { // from class: com.amazon.alexa.preload.attribution.country.AttributionLocationManager.1
                @Override // com.amazon.identity.auth.device.api.Callback
                public void onError(@NonNull Bundle bundle) {
                    Log.w(AttributionLocationManager.TAG, "Failed to retrieve customer CoR");
                }

                @Override // com.amazon.identity.auth.device.api.Callback
                public void onSuccess(@NonNull Bundle bundle) {
                    AttributionLocationManager.this.mLocationChangeListener.onLocationChanged(AttributionLocationManager.this.getLocationString());
                }
            });
        }
    }

    public void registerLocationChangeListener(@NonNull LocationChangeListener locationChangeListener) {
        this.mLocationChangeListener = locationChangeListener;
    }

    @VisibleForTesting
    AttributionLocationManager(@NonNull Context context, @NonNull MAPAccountManager mAPAccountManager, @NonNull CountryStorage countryStorage, @NonNull List<CountryProvider> list) {
        this.mContext = context;
        this.mMAPAccountManager = mAPAccountManager;
        this.mCountryStorage = countryStorage;
        this.mCountryProviders = list;
    }

    @NonNull
    private String getLocationString(@NonNull String str, @NonNull String str2) {
        return String.format(COUNTRY_REGION_FORMAT, str, str2);
    }

    @NonNull
    private String getLocationString(@NonNull String str) {
        MarketplaceCode fromString = MarketplaceCode.fromString(str);
        if (fromString != null) {
            return getLocationString(fromString.getCountryCode(), fromString.getRegionId());
        }
        return getLocationString(str, UNKNOWN_REGION_ID);
    }

    @NonNull
    private String getLocationString(@NonNull CountryProvider countryProvider) {
        if (countryProvider.shouldUseSourceAsRegionId()) {
            return getLocationString(countryProvider.getCountryCode(), countryProvider.getSourceId());
        }
        return getLocationString(countryProvider.getCountryCode());
    }
}
