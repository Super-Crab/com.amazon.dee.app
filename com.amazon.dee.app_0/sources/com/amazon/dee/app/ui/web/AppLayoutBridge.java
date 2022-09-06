package com.amazon.dee.app.ui.web;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class AppLayoutBridge extends JavaScriptBridge {
    static final String IS_FULLSCREEN = "isFullScreen";
    JavaScriptDelegate javaScriptDelegate;
    Map<String, JavaScriptBridgeMethod> methods;

    /* loaded from: classes12.dex */
    class SetOptions implements JavaScriptBridgeMethod {
        SetOptions() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            AppLayoutBridge.this.javaScriptDelegate.setFullScreen(jSONObject.optBoolean(AppLayoutBridge.IS_FULLSCREEN, false));
            AppLayoutBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public AppLayoutBridge(JavaScriptDelegate javaScriptDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.methods = new HashMap();
        this.javaScriptDelegate = javaScriptDelegate;
        this.methods.put("setOptions", new SetOptions());
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.methods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public String getJavaScriptInterfaceName() {
        return "LayoutOptionsBridge";
    }
}
