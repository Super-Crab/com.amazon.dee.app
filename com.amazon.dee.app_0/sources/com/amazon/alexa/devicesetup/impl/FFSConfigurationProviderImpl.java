package com.amazon.alexa.devicesetup.impl;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.devicesetup.sdk.whisperjoin.FFSConfigurationProvider;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.CountryCode;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.locale.LocaleConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSServiceConfiguration;
import com.google.common.base.Strings;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes7.dex */
public class FFSConfigurationProviderImpl implements FFSConfigurationProvider {
    static final String DEFAULT_DEVICE_TYPE = "A2TF17PFR55MTB";
    private static final Map<CountryCode, String> HARDCODED_LOCALES = new HashMap();
    private static final String TAG = "FFSConfigurationProviderImpl";
    private final LazyComponent<EnvironmentService> environmentService;
    private final LazyComponent<IdentityService> identityService;

    static {
        HARDCODED_LOCALES.put(CountryCode.AU, "en-AU");
        HARDCODED_LOCALES.put(CountryCode.CA, "en-CA");
        HARDCODED_LOCALES.put(CountryCode.DE, "de-DE");
        HARDCODED_LOCALES.put(CountryCode.ES, "es-ES");
        HARDCODED_LOCALES.put(CountryCode.FR, "fr-FR");
        HARDCODED_LOCALES.put(CountryCode.GB, "en-GB");
        HARDCODED_LOCALES.put(CountryCode.IN, "en-IN");
        HARDCODED_LOCALES.put(CountryCode.IT, "it-IT");
        HARDCODED_LOCALES.put(CountryCode.JP, "ja-JP");
        HARDCODED_LOCALES.put(CountryCode.MX, "es-MX");
        HARDCODED_LOCALES.put(CountryCode.US, "en-US");
        HARDCODED_LOCALES.put(CountryCode.BR, "pt-BR");
    }

    public FFSConfigurationProviderImpl(LazyComponent<EnvironmentService> lazyComponent, LazyComponent<IdentityService> lazyComponent2) {
        this.environmentService = lazyComponent;
        this.identityService = lazyComponent2;
    }

    public HashMap<CountryCode, Map<String, String>> getHardcodedDualLanguageLocales() {
        HashMap<CountryCode, Map<String, String>> hashMap = new HashMap<>();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("english", "en-CA");
        hashMap2.put("français", "fr-CA");
        hashMap.put(CountryCode.CA, hashMap2);
        if (this.identityService.mo10268get().getUser(TAG) != null) {
            HashMap hashMap3 = new HashMap();
            hashMap3.put("english", "en-US");
            hashMap3.put("español", "es-US");
            hashMap.put(CountryCode.US, hashMap3);
            HashMap hashMap4 = new HashMap();
            hashMap4.put("english", "en-IN");
            hashMap4.put("हिंदी", "hi-IN");
            hashMap.put(CountryCode.IN, hashMap4);
        }
        return hashMap;
    }

    @VisibleForTesting
    public String getLanguageLocale() {
        CountryCode fromString;
        UserIdentity user = this.identityService.mo10268get().getUser(TAG);
        String language = this.environmentService.mo10268get().getDeviceInformation().getLanguage();
        CountryCode countryCode = CountryCode.US;
        if (user != null) {
            if (user.getMarketplace() != null) {
                fromString = user.getMarketplace().getCountryCode();
            } else {
                fromString = CountryCode.fromString(user.getCountryOfResidence());
            }
            countryCode = fromString;
            if (!HARDCODED_LOCALES.containsKey(countryCode)) {
                countryCode = CountryCode.US;
            }
        }
        String str = HARDCODED_LOCALES.get(countryCode);
        HashMap<CountryCode, Map<String, String>> hardcodedDualLanguageLocales = getHardcodedDualLanguageLocales();
        if (hardcodedDualLanguageLocales.containsKey(countryCode)) {
            String lowerCase = Strings.isNullOrEmpty(language) ? null : language.toLowerCase();
            return hardcodedDualLanguageLocales.get(countryCode).containsKey(lowerCase) ? hardcodedDualLanguageLocales.get(countryCode).get(lowerCase) : str;
        }
        return str;
    }

    @Override // com.amazon.alexa.devicesetup.sdk.whisperjoin.FFSConfigurationProvider
    public ProvisioningServiceConfiguration getProvisioningServiceConfiguration() {
        DSSServiceConfiguration prod;
        EnvironmentService mo10268get = this.environmentService.mo10268get();
        String str = "A2TF17PFR55MTB";
        try {
            str = mo10268get.getDeviceInformation().getDeviceType();
            String str2 = "Provisioning service with deviceType: " + str;
        } catch (DeviceInformationException unused) {
            Log.w(TAG, "Unable to get deviceType. Defaulting to A2TF17PFR55MTB.");
        }
        Boolean valueOf = Boolean.valueOf(mo10268get.isDebugBuild());
        String buildStage = mo10268get.getBuildStage();
        String versionName = mo10268get.getVersionName();
        if (buildStage == "gamma") {
            prod = DSSServiceConfiguration.gamma(true);
        } else {
            prod = DSSServiceConfiguration.prod(true);
        }
        LocaleConfiguration localeConfiguration = new LocaleConfiguration();
        localeConfiguration.setCountryCode(mo10268get.getCountryCode());
        localeConfiguration.setMarketplace(mo10268get.getMarketplace().getObfuscatedId().toString());
        localeConfiguration.setLanguageLocale(getLanguageLocale());
        return new ProvisioningServiceConfiguration.Builder().setProvisionerApplicationName("AlexaMobileAndroid").setProvisionerVersionNumber(versionName).setProvisionerDeviceGroup(str).setLocaleConfiguration(localeConfiguration).setDssServiceConfiguration(prod).setDebugEnabled(valueOf.booleanValue()).setProvisionableBeaconType(DeviceFilter.BeaconType.ALL).createProvisioningServiceConfiguration();
    }
}
