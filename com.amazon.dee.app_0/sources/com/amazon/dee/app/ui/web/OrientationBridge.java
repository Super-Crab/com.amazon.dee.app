package com.amazon.dee.app.ui.web;

import android.app.Activity;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class OrientationBridge extends JavaScriptBridge {
    private static final String TAG = AppInfoBridge.class.getName();
    Activity activity;
    DeviceInformation deviceInformation;
    private Map<String, JavaScriptBridgeMethod> mMethods;

    /* loaded from: classes12.dex */
    class SetOrientationLockMethod implements JavaScriptBridgeMethod {
        SetOrientationLockMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            Log.i(OrientationBridge.TAG, "setOrientationLock");
            String optString = jSONObject.optString("orientation");
            if (optString != null && !optString.isEmpty()) {
                OrientationBridge orientationBridge = OrientationBridge.this;
                if (orientationBridge.deviceInformation == null) {
                    orientationBridge.deviceInformation = (DeviceInformation) GeneratedOutlineSupport1.outline20(DeviceInformation.class);
                }
                if (!OrientationBridge.this.deviceInformation.isPhoneFormFactor()) {
                    String unused = OrientationBridge.TAG;
                } else if ("portrait".equals(optString)) {
                    OrientationBridge.this.activity.setRequestedOrientation(1);
                } else if ("landscape".equals(optString)) {
                    OrientationBridge.this.activity.setRequestedOrientation(0);
                } else {
                    javaScriptResponse.setError(true);
                    javaScriptResponse.setErrorReason("Unexpected 'orientation' value " + optString + ".  Please use 'portrait' or 'landscape'");
                }
            } else {
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason("Required field 'orientation' missing.");
            }
            OrientationBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public OrientationBridge(Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.activity = activity;
        this.mMethods.put("setOrientationLock", new SetOrientationLockMethod());
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return "OrientationBridge";
    }
}
