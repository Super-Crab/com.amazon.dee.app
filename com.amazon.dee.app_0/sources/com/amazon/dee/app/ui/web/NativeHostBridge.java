package com.amazon.dee.app.ui.web;

import com.amazon.dee.app.R;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public final class NativeHostBridge extends JavaScriptBridge {
    private static final String NATIVE_TARGET_MAP = "nativeTargets";
    private static final String TAG = "com.amazon.dee.app.ui.web.NativeHostBridge";
    JavaScriptDelegate javaScriptDelegate;
    private Map<String, JavaScriptBridgeMethod> mMethods;
    private String mRegisterTargetsAndMethods;

    /* loaded from: classes12.dex */
    private class OnWebAppReadyMethod implements JavaScriptBridgeMethod {
        private OnWebAppReadyMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            NativeHostBridge.this.getJavaScriptInjector().inject(R.raw.nativehost);
            String unused = NativeHostBridge.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onWebAppReady: JavaScript bridge registration: ");
            outline107.append(NativeHostBridge.this.mRegisterTargetsAndMethods);
            outline107.toString();
            NativeHostBridge.this.getJavaScriptInjector().inject(NativeHostBridge.this.mRegisterTargetsAndMethods);
            Log.i(NativeHostBridge.TAG, "injected all native JavaScript");
            NativeHostBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public NativeHostBridge(JavaScriptDelegate javaScriptDelegate, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.javaScriptDelegate = javaScriptDelegate;
        this.mMethods.put("onWebAppReady", new OnWebAppReadyMethod());
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public String getJavaScriptInterfaceName() {
        return "NativeHost";
    }

    public void registerNativeTargets(Collection<JavaScriptBridge> collection) {
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116("window.", NATIVE_TARGET_MAP, "=window.", NATIVE_TARGET_MAP, "||{};");
        for (JavaScriptBridge javaScriptBridge : collection) {
            String javaScriptInterfaceName = javaScriptBridge.getJavaScriptInterfaceName();
            outline116.append("window.");
            outline116.append(NATIVE_TARGET_MAP);
            outline116.append("['");
            outline116.append(javaScriptInterfaceName);
            outline116.append("']=[];");
            for (String str : javaScriptBridge.getExposedMethods().keySet()) {
                String str2 = "registering method: '" + str + "'";
                outline116.append("window.");
                GeneratedOutlineSupport1.outline181(outline116, NATIVE_TARGET_MAP, "['", javaScriptInterfaceName, "'].push('");
                outline116.append(str);
                outline116.append("');");
            }
        }
        this.mRegisterTargetsAndMethods = outline116.toString();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("registerNativeTargets: JavaScript bridge registration: ");
        outline107.append(this.mRegisterTargetsAndMethods);
        outline107.toString();
        getJavaScriptInjector().inject(this.mRegisterTargetsAndMethods);
    }
}
