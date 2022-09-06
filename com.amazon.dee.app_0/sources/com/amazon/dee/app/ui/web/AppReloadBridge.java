package com.amazon.dee.app.ui.web;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class AppReloadBridge extends JavaScriptBridge {
    private Map<String, JavaScriptBridgeMethod> mMethods;
    WebViewDelegate webViewDelegate;

    /* loaded from: classes12.dex */
    private class ReloadMethod implements JavaScriptBridgeMethod {
        private ReloadMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            AppReloadBridge.this.webViewDelegate.reload();
            AppReloadBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public AppReloadBridge(WebViewDelegate webViewDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.webViewDelegate = webViewDelegate;
        this.mMethods.put("reload", new ReloadMethod());
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return "AppReload";
    }
}
