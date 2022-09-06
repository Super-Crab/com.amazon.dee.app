package com.amazon.dee.app.ui.web;

import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import org.json.JSONArray;
/* loaded from: classes12.dex */
public abstract class JavaScriptBridge {
    static final String TAG = "JavaScriptBridge";
    JavaScriptInjector javaScriptInjector;
    JavaScriptResponseQueue javaScriptResponseQueue;

    public JavaScriptBridge(JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        this.javaScriptInjector = javaScriptInjector;
        this.javaScriptResponseQueue = javaScriptResponseQueue;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void completeRequest(JavaScriptResponse javaScriptResponse) {
        if (javaScriptResponse == null || this.javaScriptResponseQueue.enqueue(javaScriptResponse)) {
            return;
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("JS response queue overflowing! Responses are not being polled. Request ID ");
        outline107.append(javaScriptResponse.getRequestId());
        Log.e(str, outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void errorResponse(JavaScriptResponse javaScriptResponse, String str, Throwable th) {
        if (th != null) {
            getJavaScriptInterfaceName();
            new Object[1][0] = th;
        } else {
            getJavaScriptInterfaceName();
        }
        javaScriptResponse.setError(true);
        javaScriptResponse.setErrorReason(str);
    }

    public JavaScriptBridgeMethod findMethod(String str) {
        return getExposedMethods().get(str);
    }

    public abstract Map<String, JavaScriptBridgeMethod> getExposedMethods();

    public JavaScriptInjector getJavaScriptInjector() {
        return this.javaScriptInjector;
    }

    public abstract String getJavaScriptInterfaceName();

    protected final void invokeJavaScriptFunction(String str) {
        invokeJavaScriptFunction(str, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void invokeJavaScriptFunction(String str, JSONArray jSONArray) {
        StringBuilder sb = new StringBuilder();
        sb.append(getJavaScriptInterfaceName());
        sb.append(".");
        sb.append(str);
        sb.append(".apply(");
        sb.append(getJavaScriptInterfaceName());
        if (jSONArray != null) {
            sb.append(", ");
            sb.append(jSONArray.toString());
        }
        sb.append(");");
        this.javaScriptInjector.inject(sb.toString());
    }
}
