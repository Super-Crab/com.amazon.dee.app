package com.amazon.dee.app.ui.web;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class NavBarToggleBridge extends JavaScriptBridge {
    JavaScriptDelegate javaScriptDelegate;
    Map<String, JavaScriptBridgeMethod> mMethods;

    /* loaded from: classes12.dex */
    private class NotifyLoadingSpinnerDismissedMethod implements JavaScriptBridgeMethod {
        private NotifyLoadingSpinnerDismissedMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            NavBarToggleBridge.this.javaScriptDelegate.notifyLoadingDialogDismissed();
            NavBarToggleBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class SetNowPlayingInformationMethod implements JavaScriptBridgeMethod {
        private SetNowPlayingInformationMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            NavBarToggleBridge.this.javaScriptDelegate.setNowPlaying(jSONObject.optBoolean("isPlaying"));
            NavBarToggleBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public NavBarToggleBridge(JavaScriptDelegate javaScriptDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.javaScriptDelegate = javaScriptDelegate;
        this.mMethods.put("setNowPlayingInformation", new SetNowPlayingInformationMethod());
        this.mMethods.put("notifyLoadingSpinnerDismissed", new NotifyLoadingSpinnerDismissedMethod());
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return "NavBarToggleBridge";
    }
}
