package com.amazon.dee.app.ui.web;

import com.amazon.dee.app.services.logging.Log;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class AppCacheBridge extends JavaScriptBridge {
    private static final String TAG = "com.amazon.dee.app.ui.web.AppCacheBridge";
    private Map<String, JavaScriptBridgeMethod> mMethods;

    /* loaded from: classes12.dex */
    private class OnAppCacheCompleteMethod implements JavaScriptBridgeMethod {
        private OnAppCacheCompleteMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            Log.i(AppCacheBridge.TAG, "appcache is complete");
            AppCacheBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public AppCacheBridge(JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.mMethods.put("onAppCacheComplete", new OnAppCacheCompleteMethod());
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return "AppCacheBridge";
    }
}
