package com.amazon.dee.app.ui.web;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public final class AppInfoBridge extends JavaScriptBridge {
    private static final String KEY_AMAZON_SERIAL = "dsn";
    private static final String KEY_DEVICE_TYPE = "deviceType";
    private static final String KEY_FIREOS = "fireos";
    private static final String KEY_MANUFACTURER = "manufacturer";
    private static final String KEY_MODEL = "model";
    private static final String KEY_REGISTERED = "registered";
    private static final String KEY_SERIAL = "serial";
    private static final String KEY_SHOW_AUTOCAST_SCREEN = "showAutocastOptInScreen";
    private static final String TAG = "com.amazon.dee.app.ui.web.AppInfoBridge";
    private final Context context;
    private final DeviceInformation deviceInformation;
    private FireOsVersion mFireOsVersion;
    private Map<String, JavaScriptBridgeMethod> mMethods;
    private SharedPreferences mSharedPreferences;

    /* loaded from: classes12.dex */
    private class GetDeviceInfoMethod implements JavaScriptBridgeMethod {
        private GetDeviceInfoMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            javaScriptResponse.setResponse(AppInfoBridge.this.getDeviceInfoJSON());
            AppInfoBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class SetDeviceRegisteredMethod implements JavaScriptBridgeMethod {
        private SetDeviceRegisteredMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) {
            GeneratedOutlineSupport1.outline143(AppInfoBridge.this.mSharedPreferences, AppInfoBridge.KEY_REGISTERED, jSONObject.optBoolean(AppInfoBridge.KEY_REGISTERED));
            AppInfoBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class SetShowAutocastScreenMethod implements JavaScriptBridgeMethod {
        private SetShowAutocastScreenMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) {
            GeneratedOutlineSupport1.outline143(AppInfoBridge.this.mSharedPreferences, AppInfoBridge.KEY_SHOW_AUTOCAST_SCREEN, jSONObject.optBoolean(AppInfoBridge.KEY_SHOW_AUTOCAST_SCREEN));
            AppInfoBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public AppInfoBridge(Context context, Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, DeviceInformation deviceInformation) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mFireOsVersion = new FireOsVersion();
        this.mMethods = new HashMap();
        this.context = context;
        this.deviceInformation = deviceInformation;
        this.mSharedPreferences = activity.getPreferences(0);
        this.mMethods.put("getDeviceInfo", new GetDeviceInfoMethod());
        this.mMethods.put("setDeviceRegistered", new SetDeviceRegisteredMethod());
        this.mMethods.put("setShowAutocastScreen", new SetShowAutocastScreenMethod());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject getDeviceInfoJSON() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        boolean z = this.mSharedPreferences.getBoolean(KEY_REGISTERED, false);
        boolean z2 = this.mSharedPreferences.getBoolean(KEY_SHOW_AUTOCAST_SCREEN, true);
        jSONObject.put(KEY_REGISTERED, z);
        jSONObject.put(KEY_SHOW_AUTOCAST_SCREEN, z2);
        jSONObject.put("model", Build.MODEL);
        jSONObject.put("manufacturer", Build.MANUFACTURER);
        jSONObject.put(KEY_SERIAL, Build.SERIAL);
        try {
            jSONObject.put("deviceType", this.deviceInformation.getDeviceType());
            jSONObject.put("dsn", this.deviceInformation.getDeviceSerialNumber());
        } catch (DeviceInformationException e) {
            Log.e(TAG, e, "Failed to get device type/serial", new Object[0]);
        }
        String version = this.mFireOsVersion.getVersion();
        if (version != null) {
            jSONObject.put(KEY_FIREOS, version);
        }
        return jSONObject;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public String getJavaScriptInterfaceName() {
        return DatabaseHelper.appInfoTable;
    }
}
