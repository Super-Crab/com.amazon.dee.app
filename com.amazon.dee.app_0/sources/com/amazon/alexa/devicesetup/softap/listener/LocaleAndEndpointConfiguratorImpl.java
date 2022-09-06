package com.amazon.alexa.devicesetup.softap.listener;

import android.content.Context;
import com.amazon.alexa.device.setup.echo.softap.locale.LocaleAndEndpointConfigurator;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.CountryCode;
import com.amazon.alexa.marketplace.api.Region;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.device.setup.thrift.DeviceDetails;
import com.amazon.device.setup.thrift.LocaleAndEndpointInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes7.dex */
public class LocaleAndEndpointConfiguratorImpl implements LocaleAndEndpointConfigurator {
    private static final String APP_ENDPOINT_REFIX = ".APP_";
    private static final String ENDPOINT_DELIMETER = ".";
    private static final Map<CountryCode, String> HARDCODED_LOCALES = new HashMap();
    private static final String PANDA_APP_ENDPOINT_PREFIX = ".PANDA_";
    private static final String PANDA_CODE_SOURCE = "PANDA";
    private static final String TAG = "LocaleAndEndpointConfiguratorImpl";
    private Context context;
    private LazyComponent<EnvironmentService> environmentServiceLazyComponent;
    private LazyComponent<IdentityService> identityServiceLazyComponent;
    private String preferredLocale = null;

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
        HARDCODED_LOCALES.put(CountryCode.NL, "en-GB");
    }

    public LocaleAndEndpointConfiguratorImpl(LazyComponent<EnvironmentService> lazyComponent, LazyComponent<IdentityService> lazyComponent2, Context context) {
        this.environmentServiceLazyComponent = lazyComponent;
        this.identityServiceLazyComponent = lazyComponent2;
        this.context = context;
    }

    private String getEchoDeviceCompilantRealm() {
        EnvironmentService mo10268get = this.environmentServiceLazyComponent.mo10268get();
        Region region = mo10268get.getMarketplace().getRegion();
        return region.equals(Region.EUROPE) ? region.toString() : mo10268get.getMarketplace().getMarketplaceName().toString();
    }

    private CountryCode getEchoDeviceCountryCode() {
        UserIdentity user = this.identityServiceLazyComponent.mo10268get().getUser(TAG);
        CountryCode countryCode = CountryCode.US;
        if (user != null) {
            if (user.getMarketplace() != null) {
                countryCode = user.getMarketplace().getCountryCode();
            }
            return user.getCountryOfResidence() != null ? CountryCode.fromString(user.getCountryOfResidence()) : countryCode;
        }
        return countryCode;
    }

    private HashMap<CountryCode, Map<String, String>> getHardcodedDualLanguageLocales() {
        HashMap<CountryCode, Map<String, String>> hashMap = new HashMap<>();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("english", "en-CA");
        hashMap2.put("français", "fr-CA");
        hashMap.put(CountryCode.CA, hashMap2);
        if (this.identityServiceLazyComponent.mo10268get().getUser(TAG) != null) {
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

    private String getLocaleFromCountry(CountryCode countryCode, String str) {
        if (!HARDCODED_LOCALES.containsKey(countryCode)) {
            countryCode = CountryCode.US;
        }
        String str2 = HARDCODED_LOCALES.get(countryCode);
        HashMap<CountryCode, Map<String, String>> hardcodedDualLanguageLocales = getHardcodedDualLanguageLocales();
        return (!hardcodedDualLanguageLocales.containsKey(countryCode) || !hardcodedDualLanguageLocales.get(countryCode).containsKey(str)) ? str2 : hardcodedDualLanguageLocales.get(countryCode).get(str);
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.locale.LocaleAndEndpointConfigurator
    public LocaleAndEndpointInfo generateLocaleAndEndpointInfo(DeviceDetails deviceDetails) {
        Locale forLanguageTag;
        EnvironmentService mo10268get = this.environmentServiceLazyComponent.mo10268get();
        String buildStage = mo10268get.getBuildStage();
        String outline91 = GeneratedOutlineSupport1.outline91(new StringBuilder(), getEchoDeviceCompilantRealm(), "Amazon");
        String language = mo10268get.getDeviceInformation().getLanguage();
        CountryCode echoDeviceCountryCode = getEchoDeviceCountryCode();
        LocaleAndEndpointInfo localeAndEndpointInfo = new LocaleAndEndpointInfo();
        String str = this.preferredLocale;
        if (str == null) {
            forLanguageTag = Locale.forLanguageTag(getLocaleFromCountry(echoDeviceCountryCode, language));
        } else {
            forLanguageTag = Locale.forLanguageTag(str);
        }
        localeAndEndpointInfo.setLocale(forLanguageTag.toLanguageTag());
        localeAndEndpointInfo.setCountryOfResidence(echoDeviceCountryCode.toString());
        localeAndEndpointInfo.setAlexaEndpoint(buildStage + "." + outline91);
        localeAndEndpointInfo.setAlexaPort(443);
        if (deviceDetails.getCodeSource() != null) {
            if (deviceDetails.getCodeSource().name().equals(PANDA_CODE_SOURCE)) {
                localeAndEndpointInfo.setCompanionAppEndpoint(buildStage + PANDA_APP_ENDPOINT_PREFIX + outline91);
            } else {
                localeAndEndpointInfo.setCompanionAppEndpoint(buildStage + APP_ENDPOINT_REFIX + outline91);
            }
        } else {
            localeAndEndpointInfo.setCompanionAppEndpoint(buildStage + "." + outline91);
        }
        localeAndEndpointInfo.setCompanionAppPort(443);
        localeAndEndpointInfo.setFIRSEndpoint(buildStage + "." + outline91);
        localeAndEndpointInfo.setPandaEndpoint(buildStage + "." + outline91);
        localeAndEndpointInfo.setTodoEndpoint(buildStage + "." + outline91);
        return localeAndEndpointInfo;
    }

    public void setPreferredLocale(String str) {
        this.preferredLocale = str;
    }
}
