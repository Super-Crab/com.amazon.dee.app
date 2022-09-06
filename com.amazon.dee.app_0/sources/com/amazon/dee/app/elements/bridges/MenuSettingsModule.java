package com.amazon.dee.app.elements.bridges;

import android.text.TextUtils;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.menu.AlexaMenu;
import com.amazon.dee.app.ui.menu.MenuOperationStatus;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
@ReactModule(name = "MenuSettings")
/* loaded from: classes12.dex */
public class MenuSettingsModule extends ReactContextBaseJavaModule {
    private static final String CHANNELS_IDENTIFIER_SUFFIX = "_channels";
    private static final String KEY_BADGE_ICON = "badgeIcon";
    private static final String KEY_MENU_ITEM = "menuItem";
    private static final String MODULE_NAME = "MenuSettingsModule";
    private static final String REMOVE_BADGE_FAILURE_MESSAGE = "MENU_REMOVE_BADGE_ICON_FAILED";
    private static final String SET_BADGE_FAILURE_MESSAGE = "MENU_SET_BADGE_ICON_FAILED";
    private static final String TAG = Log.tag(MenuSettingsModule.class);
    private final AlexaMenu alexaMenu;

    public MenuSettingsModule(ReactApplicationContext reactApplicationContext, AlexaMenu alexaMenu) {
        super(reactApplicationContext);
        this.alexaMenu = alexaMenu;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    void removeBadgeForMenuItem(ReadableMap readableMap, Promise promise) {
        String string = readableMap.getString(KEY_MENU_ITEM);
        new Object[1][0] = string;
        if (TextUtils.isEmpty(string)) {
            Log.e(TAG, "removeBadgeForMenuItem failed due to invalid parameters");
            promise.reject(REMOVE_BADGE_FAILURE_MESSAGE, "Invalid parameters received");
            return;
        }
        MenuOperationStatus removeBadgeFor = this.alexaMenu.removeBadgeFor(string);
        AlexaMenu alexaMenu = this.alexaMenu;
        MenuOperationStatus removeBadgeFor2 = alexaMenu.removeBadgeFor(string + CHANNELS_IDENTIFIER_SUFFIX);
        MenuOperationStatus menuOperationStatus = MenuOperationStatus.SUCCESS;
        if (removeBadgeFor != menuOperationStatus && removeBadgeFor2 != menuOperationStatus) {
            Log.e(TAG, "removeBadgeForMenuItem failed due to %s", removeBadgeFor);
            promise.reject(REMOVE_BADGE_FAILURE_MESSAGE, removeBadgeFor.toString());
            return;
        }
        promise.resolve(null);
    }

    @ReactMethod
    void setBadgeForMenuItem(ReadableMap readableMap, Promise promise) {
        String string = readableMap.getString(KEY_MENU_ITEM);
        String string2 = readableMap.getString(KEY_BADGE_ICON);
        Object[] objArr = {string, string2};
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
            MenuOperationStatus badgeFor = this.alexaMenu.setBadgeFor(string, string2);
            AlexaMenu alexaMenu = this.alexaMenu;
            MenuOperationStatus badgeFor2 = alexaMenu.setBadgeFor(string + CHANNELS_IDENTIFIER_SUFFIX, string2);
            MenuOperationStatus menuOperationStatus = MenuOperationStatus.SUCCESS;
            if (badgeFor != menuOperationStatus && badgeFor2 != menuOperationStatus) {
                Log.e(TAG, "setBadgeForMenuItem failed due to %s", badgeFor);
                promise.reject(SET_BADGE_FAILURE_MESSAGE, badgeFor.toString());
                return;
            }
            promise.resolve(null);
            return;
        }
        Log.e(TAG, "setBadgeForMenuItem failed due to invalid parameters");
        promise.reject(SET_BADGE_FAILURE_MESSAGE, "Invalid parameters received");
    }
}
