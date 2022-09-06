package com.amazon.alexa.cantilever;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.amazon.alexa.cantilever.utils.HelpFeatureChecker;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.logging.Lib;
import com.amazon.alexa.logging.Tag;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Provider;
import okhttp3.HttpUrl;
/* loaded from: classes6.dex */
public class HelpCookiesService {
    private static final String ALEXA = "alexa";
    private static final String ALEXA_THEME = "alexa";
    private static final String ALEXA_THEME_DARK = "alexa_dark";
    private static final String ALEXA_THEME_LIGHT = "alexa_light";
    private static final String AMAZON_STORE = "amazon";
    private static final String CLIENT_NAME = "AlexaAndroid";
    private static final String EMPTY_COOKIE = "";
    private static final String GOOGLE_PLAY_STORE = "google-play";
    private static final String NETWORK_TYPE = "NETWORK_TYPE";
    private static final String OS_ANDROID = "Android";
    private static final String OS_FIREOS = "FireOS";
    private static final String SERVICE_NAME = "AlexaCantilever";
    private final Context context;
    private final Provider<DeviceInformation> deviceInformation;
    private final Provider<EnvironmentService> environmentService;
    private final Provider<FeatureServiceV2> featureServiceV2;
    private final HelpFeatureChecker helpFeatureChecker;
    private final Provider<IdentityService> identityService;
    private String osName;
    private static final Tag TAG = LogConfig.TAGGER.tag(HelpCookiesService.class);
    private static final String CALLER = HelpCookiesService.class.getSimpleName();

    public HelpCookiesService(Context context, HelpFeatureChecker helpFeatureChecker) {
        this(context, ComponentRegistry.getInstance(), helpFeatureChecker);
    }

    private String getAmznAppInfo(String str) {
        String str2;
        if (TextUtils.isEmpty(this.osName) && this.deviceInformation.mo10268get().isFireOS()) {
            this.osName = "FireOS";
            str2 = "amazon";
        } else {
            this.osName = "Android";
            str2 = GOOGLE_PLAY_STORE;
        }
        return String.format(HelpCookieKeys.AMZN_APP_INFO_COOKIE, "alexa", this.osName.toLowerCase(Locale.ENGLISH), str2, str);
    }

    private String getBaseUrl(String str) {
        return HttpUrl.get(str).topPrivateDomain();
    }

    private String getTheme() {
        return isLightMode() ? ALEXA_THEME_LIGHT : ALEXA_THEME_DARK;
    }

    private String getUserCountry(IdentityService identityService) {
        UserIdentity user = identityService.getUser(CALLER);
        if (user == null) {
            Lib.Log.w(TAG, "User identity is null.");
            return "";
        }
        return user.getCountryOfResidence();
    }

    private String getUserMarketplaceId(IdentityService identityService) {
        UserIdentity user = identityService.getUser(CALLER);
        if (user == null) {
            Lib.Log.w(TAG, "User identity is null.");
            return "";
        }
        Marketplace marketplace = user.getMarketplace();
        if (marketplace == null) {
            Lib.Log.w(TAG, "Marketplace is null.");
            return "";
        }
        return marketplace.getObfuscatedId().toString();
    }

    private boolean isLightMode() {
        return ThemeUtil.isLightMode(this.context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<String> getCookies() {
        Lib.Log.i(TAG, "Getting cantilever cookies");
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.format(HelpCookieKeys.APP_NAME_COOKIE, this.context.getPackageName()));
        arrayList.add(String.format(HelpCookieKeys.APP_VERSION_CODE_COOKIE, this.environmentService.mo10268get().getVersionName()));
        arrayList.add(String.format(HelpCookieKeys.OS_VERSION_COOKIE, Integer.valueOf(this.deviceInformation.mo10268get().getVersionSdkInt())));
        Object[] objArr = new Object[1];
        objArr[0] = this.deviceInformation.mo10268get().isFireOS() ? "FireOS" : "Android";
        arrayList.add(String.format(HelpCookieKeys.OS_NAME_COOKIE, objArr));
        arrayList.add(String.format(HelpCookieKeys.DEVICE_NAME_COOKIE, this.deviceInformation.mo10268get().getModel()));
        try {
            arrayList.add(String.format(HelpCookieKeys.DEVICE_SN_COOKIE, this.deviceInformation.mo10268get().getDeviceSerialNumber()));
        } catch (DeviceInformationException unused) {
            arrayList.add(String.format(HelpCookieKeys.DEVICE_SN_COOKIE, ""));
        }
        try {
            arrayList.add(String.format(HelpCookieKeys.DEVICE_TYPE_COOKIE, this.deviceInformation.mo10268get().getDeviceType()));
        } catch (DeviceInformationException unused2) {
            arrayList.add(String.format(HelpCookieKeys.DEVICE_TYPE_COOKIE, ""));
        }
        arrayList.add(String.format(HelpCookieKeys.MANUFACTURER_COOKIE, this.deviceInformation.mo10268get().getManufacturer()));
        arrayList.add(String.format(HelpCookieKeys.CONNECTIVITY_COOKIE, (String) this.deviceInformation.mo10268get().getDynamicDeviceProfileData(true).get("NETWORK_TYPE")));
        arrayList.add(String.format(HelpCookieKeys.DISPLAY_LOCALE_COOKIE, this.context.getResources().getConfiguration().locale));
        arrayList.add(String.format(HelpCookieKeys.APP_MARKETPLACE_COOKIE, getUserMarketplaceId(this.identityService.mo10268get())));
        arrayList.add(String.format(HelpCookieKeys.APP_COUNTRY_COOKIE, getUserCountry(this.identityService.mo10268get())));
        arrayList.add(String.format(HelpCookieKeys.THEME_COOKIE, getTheme()));
        arrayList.add(String.format(HelpCookieKeys.IS_CHAT_SUPPORTED_COOKIE, Boolean.valueOf(this.featureServiceV2.mo10268get() != null ? this.featureServiceV2.mo10268get().hasAccess("CANTILEVER_MESSAGE_US_ANDROID", false) : false)));
        arrayList.add(String.format(HelpCookieKeys.SERVICE_NAME_COOKIE, SERVICE_NAME));
        arrayList.add(String.format(HelpCookieKeys.CLIENT_NAME_COOKIE, CLIENT_NAME));
        Lib.Log.i(TAG, "Retrieved cantilever cookies");
        return arrayList;
    }

    public void setAmznAppInfoCookie(String str) {
        CookieManager cookieManager;
        if (this.helpFeatureChecker.hasAmazonForumAccess() && (cookieManager = CookieManager.getInstance()) != null && this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.AMAZON_RETAIL_NON_ROOT, str)) {
            String baseUrl = getBaseUrl(str);
            cookieManager.setCookie(baseUrl, getAmznAppInfo(baseUrl));
        }
    }

    public void setOsName(String str) {
        this.osName = str;
    }

    public HelpCookiesService(Context context, ComponentRegistry componentRegistry, HelpFeatureChecker helpFeatureChecker) {
        this.context = context;
        this.deviceInformation = componentRegistry.getLazy(DeviceInformation.class);
        this.environmentService = componentRegistry.getLazy(EnvironmentService.class);
        this.identityService = componentRegistry.getLazy(IdentityService.class);
        this.featureServiceV2 = componentRegistry.getLazy(FeatureServiceV2.class);
        this.helpFeatureChecker = helpFeatureChecker;
    }
}
