package com.amazon.dee.app.ui.web;

import android.content.Context;
import android.view.accessibility.AccessibilityManager;
import com.facebook.react.modules.accessibilityinfo.AccessibilityInfoModule;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class AccessibilityInfoBridge extends JavaScriptBridge {
    private static final String ACCESSIBILITY_INFO = "accessibilityInfo";
    private static final int FEEDBACK_SPOKEN = 1;
    private static final String TALKBACK_ANDROID = "talkback";
    private Context mContext;
    private Map<String, JavaScriptBridgeMethod> mMethods;

    /* loaded from: classes12.dex */
    private class GetAccessibilityInfoMethod implements JavaScriptBridgeMethod {
        private GetAccessibilityInfoMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            javaScriptResponse.setResponse(AccessibilityInfoBridge.this.getAccessibilityInfoJSON());
            AccessibilityInfoBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public AccessibilityInfoBridge(Context context, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.mContext = context;
        this.mMethods.put("getAccessibilityInfo", new GetAccessibilityInfoMethod());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject getAccessibilityInfoJSON() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(TALKBACK_ANDROID, isTalkBackEnabled());
        jSONObject.put(ACCESSIBILITY_INFO, jSONObject2);
        return jSONObject;
    }

    private boolean isTalkBackEnabled() {
        return ((AccessibilityManager) this.mContext.getSystemService("accessibility")).getEnabledAccessibilityServiceList(1).size() > 0;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return AccessibilityInfoModule.NAME;
    }
}
