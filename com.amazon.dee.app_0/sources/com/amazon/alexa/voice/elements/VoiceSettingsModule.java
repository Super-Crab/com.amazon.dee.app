package com.amazon.alexa.voice.elements;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.R;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.navigation.NavigationAppInfo;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.List;
/* loaded from: classes11.dex */
public class VoiceSettingsModule extends ReactContextBaseJavaModule {
    @VisibleForTesting
    static final String KEY_PAGE_TITLE = "pageTitle";
    @VisibleForTesting
    static final String KEY_SETTING_DESCRIPTION = "settingDescription";
    @VisibleForTesting
    static final String KEY_SETTING_ENABLED = "settingEnabled";
    @VisibleForTesting
    static final String KEY_SETTING_TITLE = "settingTitle";
    @VisibleForTesting
    static final String MAP_APP_ARG_APP_DISPLAY_NAME = "appDisplayName";
    @VisibleForTesting
    static final String MAP_APP_ARG_APP_FULL_NAME = "appFullName";
    @VisibleForTesting
    static final String MAP_APP_ARG_APP_ICON_NAME = "appIconName";
    @VisibleForTesting
    static final String MAP_APP_ARG_IS_PREFERRED = "isPreferred";
    private static final String MODULE_NAME = "VoiceSettingsBridge";
    private final CollectionsFactory collectionsFactory;
    private final VoiceService voiceService;

    public VoiceSettingsModule(ReactApplicationContext reactApplicationContext, VoiceService voiceService) {
        this(reactApplicationContext, voiceService, new ReactCollectionsFactory());
    }

    private WritableMap createMapAppInfo(String str, String str2, boolean z, String str3) {
        WritableMap createMap = this.collectionsFactory.createMap();
        createMap.putString(MAP_APP_ARG_APP_DISPLAY_NAME, str);
        createMap.putString(MAP_APP_ARG_APP_FULL_NAME, str2);
        createMap.putBoolean(MAP_APP_ARG_IS_PREFERRED, z);
        if (str3 != null) {
            createMap.putString(MAP_APP_ARG_APP_ICON_NAME, str3);
        }
        return createMap;
    }

    private boolean isHandsfreeEnabled() {
        return this.voiceService.isHandsfreeEnabled();
    }

    private void setHandsfreeEnabled(boolean z) {
        this.voiceService.enableHandsfreeSettings(z);
    }

    @ReactMethod
    public void getHandsfreeSettingsInfo(Promise promise) {
        WritableMap createMap = this.collectionsFactory.createMap();
        createMap.putString(KEY_PAGE_TITLE, getReactApplicationContext().getString(R.string.voice_ui_od_handsfree_settings_page_title));
        createMap.putString(KEY_SETTING_DESCRIPTION, getReactApplicationContext().getString(R.string.voice_ui_od_handsfree_settings_description));
        createMap.putString(KEY_SETTING_TITLE, getReactApplicationContext().getString(R.string.voice_ui_od_handsfree_settings_title));
        createMap.putBoolean(KEY_SETTING_ENABLED, isHandsfreeEnabled());
        promise.resolve(createMap);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void getNavigationApps(Promise promise) {
        List<NavigationAppInfo> allNavigationApps = this.voiceService.getAllNavigationApps();
        WritableArray createArray = this.collectionsFactory.createArray();
        for (NavigationAppInfo navigationAppInfo : allNavigationApps) {
            createArray.pushMap(createMapAppInfo(navigationAppInfo.getAppDisplayName(), navigationAppInfo.getAppFullName(), navigationAppInfo.isPreferred(), navigationAppInfo.getAppIconAsset()));
        }
        promise.resolve(createArray);
    }

    @ReactMethod
    public void hasMinimumPermission(Promise promise) {
        promise.resolve(Boolean.valueOf(this.voiceService.isVoiceAllowed()));
    }

    @ReactMethod
    public void isHandsfreeSupported(Promise promise) {
        promise.resolve(Boolean.valueOf(this.voiceService.isHandsfreeSupported()));
    }

    @ReactMethod
    public void isVoiceAllowed(Promise promise) {
        promise.resolve(Boolean.valueOf(this.voiceService.isVoiceAllowed()));
    }

    @ReactMethod
    public void setHandsfreeSettingsEnabled(boolean z, Promise promise) {
        setHandsfreeEnabled(z);
        promise.resolve(null);
    }

    @ReactMethod
    public void setNavigationApp(String str, Promise promise) {
        this.voiceService.setPreferredNavigationApp(str);
        promise.resolve(null);
    }

    @VisibleForTesting
    VoiceSettingsModule(ReactApplicationContext reactApplicationContext, VoiceService voiceService, CollectionsFactory collectionsFactory) {
        super(reactApplicationContext);
        this.voiceService = voiceService;
        this.collectionsFactory = collectionsFactory;
    }
}
