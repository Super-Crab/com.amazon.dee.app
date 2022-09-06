package com.amazon.dee.app.ui.web;

import android.text.TextUtils;
import android.util.ArrayMap;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.settings.IdentityPreferencesProvider;
import com.dee.app.metrics.MetricsConstants;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class TachyonSettingsBridge extends JavaScriptBridge {
    static final String TAG = "TachyonSettingsBridge";
    CommsManager commsManager;
    ArrayMap<String, JavaScriptBridgeMethod> methods;
    ArrayMap<String, String> preferenceKeyMap;
    IdentityPreferencesProvider preferencesProvider;

    /* loaded from: classes12.dex */
    class GetAccountSetting implements JavaScriptBridgeMethod {
        GetAccountSetting() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            String preferenceName = TachyonSettingsBridge.this.getPreferenceName(jSONObject);
            if (preferenceName == null) {
                TachyonSettingsBridge.this.completeRequest(javaScriptResponse.setErrorReason("name is required."));
                return;
            }
            String commsId = TachyonSettingsBridge.this.commsManager.getCommsId();
            if (TextUtils.isEmpty(commsId)) {
                javaScriptResponse.setErrorReason("No current user");
                javaScriptResponse.setError(true);
                TachyonSettingsBridge.this.completeRequest(javaScriptResponse);
                return;
            }
            try {
                javaScriptResponse.setResponse(new JSONObject().put("value", TachyonSettingsBridge.this.preferencesProvider.get(commsId, preferenceName)));
                TachyonSettingsBridge.this.completeRequest(javaScriptResponse);
            } catch (ServiceException e) {
                Log.e(TachyonSettingsBridge.TAG, e, "Error retrieving identity preference value [%s].", preferenceName);
                javaScriptResponse.setErrorReason(String.format("Unable to retrieve the key '%s'.", preferenceName));
                TachyonSettingsBridge.this.completeRequest(javaScriptResponse);
            }
        }
    }

    /* loaded from: classes12.dex */
    class SetAccountSetting implements JavaScriptBridgeMethod {
        SetAccountSetting() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            String preferenceName = TachyonSettingsBridge.this.getPreferenceName(jSONObject);
            if (preferenceName == null) {
                TachyonSettingsBridge.this.completeRequest(javaScriptResponse.setErrorReason("name is required."));
                return;
            }
            Object obj = jSONObject.get("value");
            String commsId = TachyonSettingsBridge.this.commsManager.getCommsId();
            if (TextUtils.isEmpty(commsId)) {
                javaScriptResponse.setErrorReason("No current user");
                javaScriptResponse.setError(true);
                TachyonSettingsBridge.this.completeRequest(javaScriptResponse);
                return;
            }
            try {
                TachyonSettingsBridge.this.preferencesProvider.set(commsId, preferenceName, obj);
                TachyonSettingsBridge.this.completeRequest(javaScriptResponse);
            } catch (ServiceException e) {
                Log.e(TachyonSettingsBridge.TAG, e, "Error setting identity preference value [%s].", preferenceName);
                javaScriptResponse.setErrorReason(String.format("Unable to retrieve the key '%s'.", preferenceName));
                TachyonSettingsBridge.this.completeRequest(javaScriptResponse);
            }
        }
    }

    public TachyonSettingsBridge(JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, CommsManager commsManager, IdentityPreferencesProvider identityPreferencesProvider) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.preferenceKeyMap = new ArrayMap<>();
        this.commsManager = commsManager;
        this.preferencesProvider = identityPreferencesProvider;
        this.preferenceKeyMap.put("hide_alphap", "hide_my_presence");
        this.methods = new ArrayMap<>();
        this.methods.put(MetricsConstants.Method.CACHE_GET, new GetAccountSetting());
        this.methods.put("set", new SetAccountSetting());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getPreferenceName(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("name");
            return this.preferenceKeyMap.containsKey(string) ? this.preferenceKeyMap.get(string) : string;
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.methods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public String getJavaScriptInterfaceName() {
        return "TachyonSettings";
    }
}
