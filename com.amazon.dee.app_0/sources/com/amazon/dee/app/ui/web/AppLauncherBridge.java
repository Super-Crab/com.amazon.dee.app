package com.amazon.dee.app.ui.web;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.actions.SearchIntents;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class AppLauncherBridge extends JavaScriptBridge {
    private static final Map<String, Integer> DEVICE_TO_MP3_VERSION = new DefaultValueMap<String, Integer>(Integer.MIN_VALUE) { // from class: com.amazon.dee.app.ui.web.AppLauncherBridge.1
        {
            put("Kindle Fire", Integer.MAX_VALUE);
            Integer valueOf = Integer.valueOf((int) AppLauncherBridge.GEN_FIVE_MIN_ROBIN_VERSIONCODE);
            put("KFOT", valueOf);
            put("KFTT", valueOf);
            put("KFJWA", valueOf);
            put("KFJWI", valueOf);
            Integer valueOf2 = Integer.valueOf((int) AppLauncherBridge.GEN_SIX_MIN_ROBIN_VERSIONCODE);
            put("KFSOWI", valueOf2);
            put("KFTHWA", valueOf2);
            put("KFTHWI", valueOf2);
            put("KFAPWA", valueOf2);
            put("KFAPWI", valueOf2);
        }
    };
    private static final int GEN_FIVE_MIN_ROBIN_VERSIONCODE = 3056410;
    private static final int GEN_SIX_MIN_ROBIN_VERSIONCODE = 4032110;
    private static final String INTENT_MP3_ACTION_EXTERNAL_EVENT = "com.amazon.mp3.action.EXTERNAL_EVENT";
    private static final String INTENT_MP3_EXTRA_CAMPAIGN_ROUTE = "com.amazon.mp3.extra.EXTRA_CAMPAIGN_ROUTE";
    private static final String INTENT_MP3_EXTRA_EXTERNAL_EVENT_TYPE = "com.amazon.mp3.extra.EXTERNAL_EVENT_TYPE";
    private static final String INTENT_MP3_EXTRA_SEARCH_STRING = "com.amazon.mp3.extra.SEARCH_STRING";
    private static final String INTENT_MP3_EXTRA_TAG_NAME = "com.amazon.mp3.extra.TAG";
    private static final String INTENT_MP3_TYPE_CAMPAIGN_DETAIL = "com.amazon.mp3.type.SHOW_CAMPAIGN_DETAIL";
    private static final String INTENT_MP3_TYPE_SEARCH = "com.amazon.mp3.type.SEARCH";
    private static final String KEY_AFFILIATE_ID = "affiliateId";
    private static final String KEY_AMAZON_STORE_URL = "amazonStoreURL";
    private static final String KEY_APP_URL = "launchURL";
    private static final String KEY_ASIN = "asin";
    private static final String KEY_BACKUP_URL = "backupURL";
    private static final String KEY_ORDER_ID = "orderId";
    private static final String KEY_PLAY_STORE_URL = "playStoreURL";
    private static final String KEY_SEARCH_STRING = "searchString";
    private static final String M_SHOP_PACKAGE_NAME = "com.amazon.mShop.android";
    private static final String M_SHOP_RETAIL_SEARCH_ACTIVITY = "com.amazon.mShop.search.retail.RetailSearchActivity";
    private static final String M_SHOP_SEARCH_ACTIVITY = "com.amazon.mShop.search.SearchActivity";
    private static final String M_SHOP_SEARCH_DEEP_LINK_URI = "com.amazon.mobile.shopping://amazon.com/products?ref=DAT&q=";
    private static final String REFERENCE_STRING = "DAT";
    private static final String REFERER_ID = "AlexaApp";
    private static final String SEARCH_STRING_PLACEHOLDER = "robinSearchString";
    private static final String SEARCH_STRING_PREFIX = "search/";
    private static final String TAG = "com.amazon.dee.app.ui.web.AppLauncherBridge";
    private static final String URL_DESKTOP_ORDER_DETAIL = "https://www.amazon.com/gp/your-account/order-details?ref_=DAT&orderID=";
    private static final String URL_DESKTOP_PRIME_SEARCH = "https://www.amazon.com/s/ref=DAT?rh=n%3A163856011%2Cp_27%3ArobinSearchString%2Cp_85%3A8755839011&bbn=163856011";
    private static final String URL_DESKTOP_PRODUCT_DETAIL = "https://www.amazon.com/dp/";
    private static final String URL_GREENPOINT_SEARCH = "https://www.amazon.com/gp/dmusic/device/mp3/store/search/";
    private static final String URL_ORDER_DETAIL = "com.amazon.mobile.shopping://www.amazon.com/orders/";
    private static final String URL_PARAM_DEVICE = "android_browser_alpha";
    private static final String URL_PARAM_ROBIN_ONLY = "+robinOnly:1";
    private static final String URL_PRODUCT_DETAIL = "com.amazon.mobile.shopping://www.amazon.com/products/";
    private static final String URL_RETAIL_SEARCH = "https://www.amazon.com/s/?field-keywords=";
    private static final String WINDOWSHOP_PACKAGE_NAME = "com.amazon.windowshop";
    private static final String WINDOWSHOP_SEARCH_ACTIVITY = "com.amazon.windowshop.search.SearchResultsGridActivity";
    Activity activity;
    Context context;
    private Map<String, JavaScriptBridgeMethod> mMethods;

    /* loaded from: classes12.dex */
    public static class DefaultValueMap<K, V> extends HashMap<K, V> {
        private final V mDefaultValue;

        public DefaultValueMap(V v) {
            this.mDefaultValue = v;
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            if (containsKey(obj)) {
                return (V) super.get(obj);
            }
            return this.mDefaultValue;
        }
    }

    /* loaded from: classes12.dex */
    private class LaunchAdvancedWifiSettingsMethod implements JavaScriptBridgeMethod {
        private LaunchAdvancedWifiSettingsMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            Log.i(AppLauncherBridge.TAG, "Launching advanced wifi settings");
            if (!AppLauncherBridge.this.launchActivity(new Intent("android.settings.WIFI_IP_SETTINGS"))) {
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(AppLauncherBridge.this.context.getString(R.string.alexa_app_wifi_settings_error));
            }
            AppLauncherBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class LaunchMp3StoreAppMethod implements JavaScriptBridgeMethod {
        private LaunchMp3StoreAppMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            String optString = jSONObject.optString(AppLauncherBridge.KEY_SEARCH_STRING);
            String optString2 = jSONObject.optString(AppLauncherBridge.KEY_AFFILIATE_ID);
            String optString3 = jSONObject.optString("greenPointUrl");
            try {
                Log.i(AppLauncherBridge.TAG, String.format("Launching MP3 store (search criteria '%s', affiliate tag '%s')", optString, optString2));
                AppLauncherBridge.this.launchMP3SearchActivity(optString, optString2, optString3);
            } catch (Exception e) {
                Log.e(AppLauncherBridge.TAG, e, "Couldn't create or launch the intent.", new Object[0]);
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(e.getMessage());
            }
            AppLauncherBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class LaunchNativeAppMethod implements JavaScriptBridgeMethod {
        private LaunchNativeAppMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            String optString = jSONObject.optString(AppLauncherBridge.KEY_APP_URL);
            String optString2 = jSONObject.optString(AppLauncherBridge.KEY_PLAY_STORE_URL);
            String optString3 = jSONObject.optString(AppLauncherBridge.KEY_AMAZON_STORE_URL);
            String optString4 = jSONObject.optString(AppLauncherBridge.KEY_BACKUP_URL);
            try {
                String str = AppLauncherBridge.TAG;
                Log.i(str, "Launching app with url: " + optString);
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(optString));
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(optString3));
                Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(optString2));
                Intent intent4 = new Intent("android.intent.action.VIEW", Uri.parse(optString4));
                if (!AppLauncherBridge.this.launchActivity(intent) && !AppLauncherBridge.this.launchActivity(intent2) && !AppLauncherBridge.this.launchActivity(intent3) && !AppLauncherBridge.this.launchActivity(intent4)) {
                    Log.i(AppLauncherBridge.TAG, "Unable to launch any app, store, or backupURL");
                    throw new ActivityNotFoundException();
                }
            } catch (Exception e) {
                Log.e(AppLauncherBridge.TAG, e, "Couldn't create or launch the intent.", new Object[0]);
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(e.getMessage());
            }
            AppLauncherBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class LaunchOrderDetailShoppingAppMethod implements JavaScriptBridgeMethod {
        private LaunchOrderDetailShoppingAppMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            String optString = jSONObject.optString(AppLauncherBridge.KEY_ORDER_ID);
            try {
                String str = AppLauncherBridge.TAG;
                Log.i(str, "Launching Amazon Shopping app order detail page for: " + optString);
                AppLauncherBridge.this.launchMShopOrderDetailActivity(optString);
            } catch (Exception e) {
                Log.e(AppLauncherBridge.TAG, e, "Couldn't create or launch the intent.", new Object[0]);
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(e.getMessage());
            }
            AppLauncherBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class LaunchProductDetailShoppingAppMethod implements JavaScriptBridgeMethod {
        private LaunchProductDetailShoppingAppMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            String optString = jSONObject.optString("asin");
            try {
                String str = AppLauncherBridge.TAG;
                Log.i(str, "Launching Amazon Shopping app product detail page for: " + optString);
                AppLauncherBridge.this.launchMShopProductDetailActivity(optString);
            } catch (Exception e) {
                Log.e(AppLauncherBridge.TAG, e, "Couldn't create or launch the intent.", new Object[0]);
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(e.getMessage());
            }
            AppLauncherBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class LaunchRobinMP3StoreAppMethod implements JavaScriptBridgeMethod {
        private LaunchRobinMP3StoreAppMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            String optString = jSONObject.optString(AppLauncherBridge.KEY_SEARCH_STRING);
            String optString2 = jSONObject.optString(AppLauncherBridge.KEY_AFFILIATE_ID);
            try {
                Log.i(AppLauncherBridge.TAG, String.format("Launching MP3 store (search criteria '%s', affiliate tag '%s')", optString, optString2));
                AppLauncherBridge.this.launchRobinMP3SearchActivity(optString, optString2);
            } catch (Exception e) {
                Log.e(AppLauncherBridge.TAG, e, "Couldn't create or launch the intent.", new Object[0]);
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(e.getMessage());
            }
            AppLauncherBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class LaunchShoppingAppMethod implements JavaScriptBridgeMethod {
        private LaunchShoppingAppMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            String optString = jSONObject.optString(AppLauncherBridge.KEY_SEARCH_STRING);
            try {
                String str = AppLauncherBridge.TAG;
                Log.i(str, "Launching Amazon Shopping app with search criteria: " + optString);
                AppLauncherBridge.this.launchSearchActivity(optString);
            } catch (Exception e) {
                Log.e(AppLauncherBridge.TAG, e, "Couldn't create or launch the intent.", new Object[0]);
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(e.getMessage());
            }
            AppLauncherBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class LaunchWifiSettingsMethod implements JavaScriptBridgeMethod {
        private LaunchWifiSettingsMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            Log.i(AppLauncherBridge.TAG, "Launching wifi settings");
            if (!AppLauncherBridge.this.launchActivity(new Intent("android.settings.WIFI_SETTINGS"))) {
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(AppLauncherBridge.this.context.getString(R.string.alexa_app_wifi_error));
            }
            AppLauncherBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public AppLauncherBridge(Context context, Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.context = context;
        this.activity = activity;
        this.mMethods.put("launchNativeApp", new LaunchNativeAppMethod());
        this.mMethods.put("launchShoppingApp", new LaunchShoppingAppMethod());
        this.mMethods.put("launchProductDetailShoppingApp", new LaunchProductDetailShoppingAppMethod());
        this.mMethods.put("launchOrderDetailShoppingApp", new LaunchOrderDetailShoppingAppMethod());
        this.mMethods.put("launchMp3StoreApp", new LaunchMp3StoreAppMethod());
        this.mMethods.put("launchRobinMP3StoreApp", new LaunchRobinMP3StoreAppMethod());
        this.mMethods.put("launchWifiSettings", new LaunchWifiSettingsMethod());
        this.mMethods.put("launchAdvancedWifiSettings", new LaunchAdvancedWifiSettingsMethod());
    }

    private Intent createBrowserIntent(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(URL_RETAIL_SEARCH);
        outline107.append(Uri.encode(str));
        return new Intent("android.intent.action.VIEW", Uri.parse(outline107.toString()));
    }

    private Intent createDeepLinkSearchMShopIntent(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(M_SHOP_SEARCH_DEEP_LINK_URI);
        outline107.append(Uri.encode(str));
        return new Intent("android.intent.action.VIEW", Uri.parse(outline107.toString()));
    }

    private Intent createDesktopRobinIntent(String str) {
        Uri.Builder desktopRobinUriBuilder = getDesktopRobinUriBuilder(str);
        Log.i(TAG, String.format("creating browser intent, url: %s", desktopRobinUriBuilder.toString()));
        return new Intent("android.intent.action.VIEW", desktopRobinUriBuilder.build());
    }

    private Intent createDukeMShopIntent(String str) {
        Intent intent = new Intent("android.intent.action.SEARCH");
        intent.setPackage(M_SHOP_PACKAGE_NAME);
        intent.setComponent(new ComponentName(M_SHOP_PACKAGE_NAME, M_SHOP_RETAIL_SEARCH_ACTIVITY));
        intent.putExtra(SearchIntents.EXTRA_QUERY, str);
        return intent;
    }

    private Intent createGreenpointIntent(String str, String str2, String str3) {
        Uri.Builder greenPointUriBuilderForMP3Search = getGreenPointUriBuilderForMP3Search(str, str2, str3, false);
        Log.i(TAG, String.format("creating browser intent, url: %s", greenPointUriBuilderForMP3Search.toString()));
        return new Intent("android.intent.action.VIEW", greenPointUriBuilderForMP3Search.build());
    }

    private Intent createMShopIntent(String str) {
        Intent intent = new Intent("android.intent.action.SEARCH");
        intent.setPackage(M_SHOP_PACKAGE_NAME);
        intent.setComponent(new ComponentName(M_SHOP_PACKAGE_NAME, M_SHOP_SEARCH_ACTIVITY));
        intent.putExtra(SearchIntents.EXTRA_QUERY, str);
        return intent;
    }

    private Intent createMp3SearchIntent(String str, String str2) {
        Intent putExtra = new Intent().setAction(INTENT_MP3_ACTION_EXTERNAL_EVENT).putExtra(INTENT_MP3_EXTRA_EXTERNAL_EVENT_TYPE, INTENT_MP3_TYPE_SEARCH).putExtra(INTENT_MP3_EXTRA_SEARCH_STRING, str);
        if (str2 != null && !str2.isEmpty()) {
            putExtra.putExtra(INTENT_MP3_EXTRA_TAG_NAME, str2);
        }
        return putExtra;
    }

    private Intent createRobinGreenpointIntent(String str, String str2) {
        Uri.Builder greenPointUriBuilderForRobinSearch = getGreenPointUriBuilderForRobinSearch(GeneratedOutlineSupport1.outline72(str, URL_PARAM_ROBIN_ONLY), str2, true);
        greenPointUriBuilderForRobinSearch.appendQueryParameter("device", URL_PARAM_DEVICE);
        Log.i(TAG, String.format("creating browser intent, url: %s", greenPointUriBuilderForRobinSearch.toString()));
        Intent intent = new Intent("android.intent.action.VIEW", greenPointUriBuilderForRobinSearch.build());
        intent.putExtra(INTENT_MP3_EXTRA_EXTERNAL_EVENT_TYPE, INTENT_MP3_TYPE_SEARCH);
        return intent;
    }

    private Intent createRobinSearchIntent(String str, String str2) {
        Integer num;
        Intent createDesktopRobinIntent;
        try {
            num = Integer.valueOf(this.context.getPackageManager().getPackageInfo("com.amazon.mp3", 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e, "Amazon MP3 package was not found", new Object[0]);
            num = null;
        }
        if (isRobinSupported(num)) {
            Intent putExtra = new Intent().setAction(INTENT_MP3_ACTION_EXTERNAL_EVENT).putExtra(INTENT_MP3_EXTRA_EXTERNAL_EVENT_TYPE, INTENT_MP3_TYPE_CAMPAIGN_DETAIL);
            createDesktopRobinIntent = putExtra.putExtra(INTENT_MP3_EXTRA_CAMPAIGN_ROUTE, SEARCH_STRING_PREFIX + str + URL_PARAM_ROBIN_ONLY);
        } else {
            createDesktopRobinIntent = createDesktopRobinIntent(str);
        }
        if (str2 != null && !str2.isEmpty()) {
            createDesktopRobinIntent.putExtra(INTENT_MP3_EXTRA_TAG_NAME, str2);
        }
        return createDesktopRobinIntent;
    }

    private Intent createWindowShopIntent(String str) {
        Intent intent = new Intent("android.intent.action.SEARCH");
        intent.setPackage(WINDOWSHOP_PACKAGE_NAME);
        intent.setComponent(new ComponentName(WINDOWSHOP_PACKAGE_NAME, WINDOWSHOP_SEARCH_ACTIVITY));
        intent.putExtra(SearchIntents.EXTRA_QUERY, str);
        return intent;
    }

    private Uri.Builder formGreenPointUriBuilder(String str, String str2, boolean z) {
        Uri.Builder buildUpon = Uri.parse(URL_GREENPOINT_SEARCH).buildUpon();
        if (str != null && !str.isEmpty()) {
            if (z) {
                buildUpon.appendEncodedPath(str);
            } else {
                buildUpon.appendPath(str);
            }
        }
        if (str2 != null && !str2.isEmpty()) {
            buildUpon.appendQueryParameter("tag", str2);
        }
        return buildUpon;
    }

    private Uri.Builder getDesktopRobinUriBuilder(String str) {
        return Uri.parse(URL_DESKTOP_PRIME_SEARCH.replaceFirst(SEARCH_STRING_PLACEHOLDER, str)).buildUpon();
    }

    private Uri.Builder getGreenPointUriBuilderForMP3Search(String str, String str2, String str3, boolean z) {
        if (str3 != null && !str3.isEmpty()) {
            return Uri.parse(str3).buildUpon();
        }
        return formGreenPointUriBuilder(str, str2, z);
    }

    private Uri.Builder getGreenPointUriBuilderForRobinSearch(String str, String str2, boolean z) {
        return formGreenPointUriBuilder(str, str2, z);
    }

    private boolean isAmazonDevice() {
        return "Amazon".equalsIgnoreCase(Build.MANUFACTURER);
    }

    private boolean isFirePhone() {
        return "SD4930UR".equalsIgnoreCase(Build.MODEL);
    }

    private boolean isRobinSupported(Integer num) {
        return num != null && num.intValue() >= DEVICE_TO_MP3_VERSION.get(Build.MODEL).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean launchActivity(Intent intent) {
        if (intent == null) {
            return false;
        }
        try {
            this.activity.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException unused) {
            Log.i(TAG, String.format("User does not have an app installed that satisfies the provided intent: %s", intent));
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchMP3SearchActivity(String str, String str2, String str3) {
        Intent createGreenpointIntent;
        if (isAmazonDevice()) {
            createGreenpointIntent = createMp3SearchIntent(str, str2);
        } else {
            createGreenpointIntent = createGreenpointIntent(str, str2, str3);
        }
        if (launchActivity(createGreenpointIntent)) {
            return;
        }
        Log.i(TAG, "Unable to launch MP3 app store or web browser.");
        throw new ActivityNotFoundException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchMShopOrderDetailActivity(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(URL_ORDER_DETAIL);
        outline107.append(Uri.encode(str));
        outline107.append("?ref=");
        outline107.append(REFERENCE_STRING);
        if (!launchActivity(new Intent("android.intent.action.VIEW", Uri.parse(outline107.toString())))) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(URL_DESKTOP_ORDER_DETAIL);
            outline1072.append(Uri.encode(str));
            launchActivity(new Intent("android.intent.action.VIEW", Uri.parse(outline1072.toString())));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchMShopProductDetailActivity(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(URL_PRODUCT_DETAIL);
        outline107.append(Uri.encode(str));
        outline107.append("?ref=");
        outline107.append(REFERENCE_STRING);
        if (!launchActivity(new Intent("android.intent.action.VIEW", Uri.parse(outline107.toString())))) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(URL_DESKTOP_PRODUCT_DETAIL);
            outline1072.append(Uri.encode(str));
            launchActivity(new Intent("android.intent.action.VIEW", Uri.parse(outline1072.toString())));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchRobinMP3SearchActivity(String str, String str2) {
        Intent createRobinGreenpointIntent;
        if (isAmazonDevice() && !isFirePhone()) {
            createRobinGreenpointIntent = createRobinSearchIntent(str, str2);
        } else {
            createRobinGreenpointIntent = createRobinGreenpointIntent(str, str2);
        }
        if (launchActivity(createRobinGreenpointIntent)) {
            return;
        }
        Log.i(TAG, "Unable to launch MP3 app store or web browser.");
        throw new ActivityNotFoundException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchSearchActivity(String str) {
        if (launchActivity(createDeepLinkSearchMShopIntent(str)) || launchActivity(createWindowShopIntent(str)) || launchActivity(createMShopIntent(str)) || launchActivity(createDukeMShopIntent(str)) || launchActivity(createBrowserIntent(str))) {
            return;
        }
        Log.i(TAG, "Unable to launch any shopping or web browser app.");
        throw new ActivityNotFoundException();
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return "AppLauncherBridge";
    }
}
