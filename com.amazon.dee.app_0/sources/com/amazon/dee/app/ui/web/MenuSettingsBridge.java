package com.amazon.dee.app.ui.web;

import android.text.TextUtils;
import android.util.ArrayMap;
import com.amazon.dee.app.ui.menu.AlexaMenu;
import com.amazon.dee.app.ui.menu.MenuOperationStatus;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class MenuSettingsBridge extends JavaScriptBridge {
    private static final String BRIDGE_NAME = "MenuSettingsBridge";
    private static final String KEY_BADGE_ICON = "badgeIcon";
    private static final String KEY_MENU_ITEM = "menuItem";
    private static final String METHOD_ADD_BADGE = "setBadgeForMenuItem";
    private static final String METHOD_REMOVE_BADGE = "removeBadgeForMenuItem";
    private final AlexaMenu alexaMenu;
    private Map<String, JavaScriptBridgeMethod> methods;

    /* loaded from: classes12.dex */
    private class RemoveBadgeMethod implements JavaScriptBridgeMethod {
        private RemoveBadgeMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            String string = jSONObject.getString(MenuSettingsBridge.KEY_MENU_ITEM);
            if (!TextUtils.isEmpty(string)) {
                MenuOperationStatus removeBadgeFor = MenuSettingsBridge.this.alexaMenu.removeBadgeFor(string);
                if (removeBadgeFor != MenuOperationStatus.SUCCESS) {
                    javaScriptResponse.setError(true);
                    javaScriptResponse.setErrorReason(removeBadgeFor.toString());
                }
                MenuSettingsBridge.this.completeRequest(javaScriptResponse);
                return;
            }
            javaScriptResponse.setError(true);
            javaScriptResponse.setErrorReason("Invalid parameters received");
        }
    }

    /* loaded from: classes12.dex */
    private class SetBadgeMethod implements JavaScriptBridgeMethod {
        private SetBadgeMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            String string = jSONObject.getString(MenuSettingsBridge.KEY_MENU_ITEM);
            String string2 = jSONObject.getString(MenuSettingsBridge.KEY_BADGE_ICON);
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                MenuOperationStatus badgeFor = MenuSettingsBridge.this.alexaMenu.setBadgeFor(string, string2);
                if (badgeFor != MenuOperationStatus.SUCCESS) {
                    javaScriptResponse.setError(true);
                    javaScriptResponse.setErrorReason(badgeFor.toString());
                }
                MenuSettingsBridge.this.completeRequest(javaScriptResponse);
                return;
            }
            javaScriptResponse.setError(true);
            javaScriptResponse.setErrorReason("Invalid parameters received");
        }
    }

    public MenuSettingsBridge(JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, AlexaMenu alexaMenu) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.alexaMenu = alexaMenu;
        this.methods = new ArrayMap();
        this.methods.put(METHOD_ADD_BADGE, new SetBadgeMethod());
        this.methods.put(METHOD_REMOVE_BADGE, new RemoveBadgeMethod());
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.methods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public String getJavaScriptInterfaceName() {
        return BRIDGE_NAME;
    }
}
