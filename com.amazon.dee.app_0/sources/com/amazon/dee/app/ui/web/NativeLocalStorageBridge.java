package com.amazon.dee.app.ui.web;

import com.amazon.dee.app.services.datastore.DataStoreService;
import com.amazon.dee.app.services.logging.Log;
import com.dee.app.metrics.MetricsConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class NativeLocalStorageBridge extends JavaScriptBridge {
    private static final String TAG = "com.amazon.dee.app.ui.web.NativeLocalStorageBridge";
    private final DataStoreService mDataStoreService;
    private final Map<String, JavaScriptBridgeMethod> mMethods;

    /* loaded from: classes12.dex */
    private class ClearMethod implements JavaScriptBridgeMethod {
        private ClearMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                NativeLocalStorageBridge.this.mDataStoreService.clear();
                String unused = NativeLocalStorageBridge.TAG;
                javaScriptResponse.setResponse(jSONObject2);
            } catch (Exception e) {
                Log.e(NativeLocalStorageBridge.TAG, e, "Unable to delete rows from the local database", new Object[0]);
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(e.getMessage());
            }
            NativeLocalStorageBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class GetItemMethod implements JavaScriptBridgeMethod {
        private GetItemMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            try {
                JSONObject jSONObject2 = new JSONObject();
                String string = jSONObject.getString("key");
                String unused = NativeLocalStorageBridge.TAG;
                String str = "NativeLocalStorage GetItem for key: " + string;
                jSONObject2.put("value", NativeLocalStorageBridge.this.mDataStoreService.retrieveValue(string));
                javaScriptResponse.setResponse(jSONObject2);
            } catch (Exception e) {
                Log.e(NativeLocalStorageBridge.TAG, e, "Unable to retrieve item from the local database", new Object[0]);
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(e.getMessage());
            }
            NativeLocalStorageBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class SetItemMethod implements JavaScriptBridgeMethod {
        private SetItemMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) {
            try {
                String string = jSONObject.getString("key");
                String string2 = jSONObject.getString("value");
                String unused = NativeLocalStorageBridge.TAG;
                String str = "NativeLocalStorage setting item for key: " + string;
                NativeLocalStorageBridge.this.mDataStoreService.storeValue(string, string2);
                javaScriptResponse.setResponse(new JSONObject());
            } catch (Exception e) {
                Log.e(NativeLocalStorageBridge.TAG, e, "Unable to update the local database", new Object[0]);
                javaScriptResponse.setError(true);
                javaScriptResponse.setErrorReason(e.getMessage());
            }
            NativeLocalStorageBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public NativeLocalStorageBridge(DataStoreService dataStoreService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.mDataStoreService = dataStoreService;
        this.mMethods.put("getItem", new GetItemMethod());
        this.mMethods.put("setItem", new SetItemMethod());
        this.mMethods.put(MetricsConstants.Method.CACHE_CLEAR, new ClearMethod());
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return "NativeLocalStorage";
    }
}
