package com.amazon.dee.app.ui.web;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.util.ArrayMap;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.web.PermissionsBridge;
import com.amazon.dee.app.util.PermissionsUtils;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.google.gson.Gson;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class PermissionsBridge extends JavaScriptBridge {
    private static final String JS_INTERFACE_NAME = "PermissionsBridge";
    private static final String TAG = Log.tag(PermissionsBridge.class);
    private Activity activity;
    private Gson gson;
    private final ArrayMap<String, JavaScriptBridgeMethod> mMethods;

    /* loaded from: classes12.dex */
    private class HasLocationPermission implements JavaScriptBridgeMethod {
        private HasLocationPermission() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            boolean hasLocationPermission = PermissionsUtils.hasLocationPermission(PermissionsBridge.this.activity);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(BizMetricsConstants.OS_PERMISSION_GRANTED_TRUE_VALUE, hasLocationPermission);
            javaScriptResponse.setResponse(jSONObject2);
            PermissionsBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public PermissionsBridge(Activity activity, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, Gson gson) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new ArrayMap<>();
        this.activity = activity;
        this.mMethods.put("requestCameraPermission", new CheckCameraPermission());
        this.mMethods.put("requestBluetoothPermission", new CheckBluetoothPermission());
        this.mMethods.put("requestLocationPermission", new CheckLocationPermission());
        this.mMethods.put("hasLocationPermission", new HasLocationPermission());
        this.gson = gson;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public String getJavaScriptInterfaceName() {
        return JS_INTERFACE_NAME;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class CheckLocationPermission implements JavaScriptBridgeMethod, PermissionsUtils.Listener {
        static final String RATIONALE_BUTTON_TEXT = "rationaleButtonText";
        static final String RATIONALE_HEADER = "rationaleHeader";
        static final String RATIONALE_TEXT = "rationaleText";
        static final String RESPONSE_GRANTED = "granted";
        static final String RESPONSE_REASON = "reason";
        private JavaScriptResponse permissionResponse;

        private CheckLocationPermission() {
            this.permissionResponse = new JavaScriptResponse();
        }

        private void presentPrimerDialogAndRequestLocationPermission(final JSONObject jSONObject) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$PermissionsBridge$CheckLocationPermission$qfzvAuBUmNe87RJ0rC09ugfTEBs
                @Override // java.lang.Runnable
                public final void run() {
                    PermissionsBridge.CheckLocationPermission.this.lambda$presentPrimerDialogAndRequestLocationPermission$1$PermissionsBridge$CheckLocationPermission(jSONObject);
                }
            });
        }

        private boolean requestHasPrimerDialogVars(JSONObject jSONObject) {
            return jSONObject != null && jSONObject.has(RATIONALE_HEADER) && jSONObject.has(RATIONALE_TEXT) && jSONObject.has(RATIONALE_BUTTON_TEXT);
        }

        private void requestLocationPermission() {
            PermissionsUtils.requestLocationPermission(PermissionsBridge.this.activity, this, null);
        }

        @Override // com.amazon.dee.app.util.PermissionsUtils.Listener
        public void complete(boolean z, String str) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("granted", z);
                jSONObject.put("reason", str);
                this.permissionResponse.setResponse(jSONObject);
            } catch (JSONException unused) {
                this.permissionResponse.setError(true);
            }
            PermissionsBridge.this.completeRequest(this.permissionResponse);
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            if (javaScriptResponse != null) {
                this.permissionResponse = javaScriptResponse;
            } else {
                Log.e(PermissionsBridge.TAG, "CheckLocationPermission::execute(): response is null");
            }
            if (PermissionsUtils.hasLocationPermission(PermissionsBridge.this.activity)) {
                complete(true);
            } else if (requestHasPrimerDialogVars(jSONObject)) {
                presentPrimerDialogAndRequestLocationPermission(jSONObject);
            } else {
                requestLocationPermission();
            }
        }

        public /* synthetic */ void lambda$null$0$PermissionsBridge$CheckLocationPermission(DialogInterface dialogInterface, int i) {
            requestLocationPermission();
        }

        public /* synthetic */ void lambda$presentPrimerDialogAndRequestLocationPermission$1$PermissionsBridge$CheckLocationPermission(JSONObject jSONObject) {
            try {
                new AlertDialog.Builder(PermissionsBridge.this.activity, 5).setMessage(jSONObject.getString(RATIONALE_TEXT)).setTitle(jSONObject.getString(RATIONALE_HEADER)).setPositiveButton(jSONObject.getString(RATIONALE_BUTTON_TEXT), new DialogInterface.OnClickListener() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$PermissionsBridge$CheckLocationPermission$mb1Cqn0Wg1Ttokas5QyB1FS9AuA
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        PermissionsBridge.CheckLocationPermission.this.lambda$null$0$PermissionsBridge$CheckLocationPermission(dialogInterface, i);
                    }
                }).show();
            } catch (JSONException e) {
                Log.e(PermissionsBridge.TAG, "CheckLocationPermission::presentPrimerDialogAndRequestLocationPermission: Can't display primer dialog", e);
            }
        }

        @Override // com.amazon.dee.app.util.PermissionsUtils.Listener
        public void complete(boolean z) {
            complete(z, null);
        }
    }

    /* loaded from: classes12.dex */
    private class CheckBluetoothPermission implements JavaScriptBridgeMethod, PermissionsUtils.Listener {
        private JavaScriptResponse permissionResponse;

        private CheckBluetoothPermission() {
        }

        @Override // com.amazon.dee.app.util.PermissionsUtils.Listener
        public void complete(boolean z, String str) {
            try {
                JSONObject jSONObject = new JSONObject(PermissionsBridge.this.gson.toJson(Boolean.valueOf(z)));
                if (this.permissionResponse != null) {
                    this.permissionResponse.setResponse(jSONObject);
                    this.permissionResponse.setError(!z);
                    this.permissionResponse.setErrorReason(str);
                }
            } catch (JSONException unused) {
                this.permissionResponse.setError(true);
            }
            PermissionsBridge.this.completeRequest(this.permissionResponse);
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            this.permissionResponse = javaScriptResponse;
            PermissionsUtils.checkBluetoothPermission(PermissionsBridge.this.activity, this, null);
        }

        @Override // com.amazon.dee.app.util.PermissionsUtils.Listener
        public void complete(boolean z) {
            complete(z, null);
        }
    }

    /* loaded from: classes12.dex */
    private class CheckCameraPermission implements JavaScriptBridgeMethod, PermissionsUtils.Listener {
        private JavaScriptResponse permissionResponse;

        private CheckCameraPermission() {
        }

        @Override // com.amazon.dee.app.util.PermissionsUtils.Listener
        public void complete(boolean z, String str) {
            try {
                JSONObject jSONObject = new JSONObject(PermissionsBridge.this.gson.toJson(Boolean.valueOf(z)));
                if (this.permissionResponse != null) {
                    this.permissionResponse.setResponse(jSONObject);
                    this.permissionResponse.setError(!z);
                    this.permissionResponse.setErrorReason(str);
                }
            } catch (JSONException unused) {
                this.permissionResponse.setError(true);
            }
            PermissionsBridge.this.completeRequest(this.permissionResponse);
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            this.permissionResponse = javaScriptResponse;
            PermissionsUtils.checkCameraPermission(PermissionsBridge.this.activity, this, null);
        }

        @Override // com.amazon.dee.app.util.PermissionsUtils.Listener
        public void complete(boolean z) {
            complete(z, null);
        }
    }
}
