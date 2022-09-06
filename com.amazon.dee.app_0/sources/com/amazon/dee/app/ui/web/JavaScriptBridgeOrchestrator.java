package com.amazon.dee.app.ui.web;

import android.os.AsyncTask;
import android.webkit.JsPromptResult;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class JavaScriptBridgeOrchestrator {
    private static final String KEY_REQUEST_ID = "requestId";
    private static final String TAG = "JavaScriptBridgeOrchestrator";
    JavaScriptResponseQueue javaScriptResponseQueue;
    private Map<String, JavaScriptBridge> mRegisteredBridges = new HashMap();

    public JavaScriptBridgeOrchestrator(JavaScriptResponseQueue javaScriptResponseQueue) {
        this.javaScriptResponseQueue = javaScriptResponseQueue;
    }

    private void completeRequest(JavaScriptResponse javaScriptResponse) {
        this.javaScriptResponseQueue.enqueue(javaScriptResponse);
    }

    private void executeMethod(final String str, final JavaScriptBridgeMethod javaScriptBridgeMethod, final JSONObject jSONObject, final JavaScriptResponse javaScriptResponse) {
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.dee.app.ui.web.JavaScriptBridgeOrchestrator.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                try {
                    javaScriptBridgeMethod.execute(jSONObject, javaScriptResponse);
                    return null;
                } catch (JSONException e) {
                    String outline91 = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Method: "), str, " threw a JSONException");
                    Log.e(JavaScriptBridgeOrchestrator.TAG, e, outline91, new Object[0]);
                    JavaScriptBridgeOrchestrator.this.handleError(outline91, javaScriptResponse);
                    return null;
                }
            }
        }.execute(new Void[0]);
    }

    private JSONObject getParameters(JSONObject jSONObject) {
        try {
            return jSONObject.getJSONObject("parameters");
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleError(String str, JavaScriptResponse javaScriptResponse) {
        javaScriptResponse.setError(true);
        javaScriptResponse.setErrorReason(str);
        completeRequest(javaScriptResponse);
    }

    private boolean isPollingRequest(String str) {
        return "pollJavaScriptResponse".equals(str);
    }

    private JavaScriptBridge lookupBridge(String str) {
        return this.mRegisteredBridges.get(str);
    }

    private void processBridgeRequest(JavaScriptResponse javaScriptResponse, String str, String str2, JSONObject jSONObject) {
        javaScriptResponse.setRequestId(jSONObject.optString("requestId"));
        JavaScriptBridge lookupBridge = lookupBridge(str);
        if (lookupBridge == null) {
            handleError(GeneratedOutlineSupport1.outline72("Unable to find nativeInterface: ", str), javaScriptResponse);
            return;
        }
        JavaScriptBridgeMethod findMethod = lookupBridge.findMethod(str2);
        if (findMethod == null) {
            handleError(GeneratedOutlineSupport1.outline72("Unable to find method: ", str2), javaScriptResponse);
        } else {
            executeMethod(str2, findMethod, jSONObject, javaScriptResponse);
        }
    }

    public void execute(String str, JsPromptResult jsPromptResult) {
        JavaScriptResponse javaScriptResponse = new JavaScriptResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("nativeInterface");
            if (isPollingRequest(string)) {
                pollJavaScriptResponse(jsPromptResult);
            } else {
                processBridgeRequest(javaScriptResponse, string, jSONObject.getString(MetricsConstants.NativeFetch.METHOD), getParameters(jSONObject));
                if (jsPromptResult != null) {
                    jsPromptResult.confirm("");
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, e, "Unable to parse incoming message from JavaScript client.", new Object[0]);
            handleError("Unable to parse incoming message from JavaScript client.", javaScriptResponse);
            if (jsPromptResult == null) {
                return;
            }
            jsPromptResult.confirm("");
        }
    }

    public Collection<JavaScriptBridge> getRegisteredBridges() {
        return this.mRegisteredBridges.values();
    }

    public void pollJavaScriptResponse(JsPromptResult jsPromptResult) {
        JavaScriptResponse poll = this.javaScriptResponseQueue.poll();
        if (poll != null) {
            jsPromptResult.confirm(poll.toString());
        } else {
            jsPromptResult.confirm("");
        }
    }

    public void registerBridge(JavaScriptBridge javaScriptBridge) {
        this.mRegisteredBridges.put(javaScriptBridge.getJavaScriptInterfaceName(), javaScriptBridge);
    }
}
