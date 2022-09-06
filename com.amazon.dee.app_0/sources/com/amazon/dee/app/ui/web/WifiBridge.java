package com.amazon.dee.app.ui.web;

import android.app.Activity;
import android.os.Build;
import androidx.annotation.NonNull;
import com.amazon.dee.app.services.wifi.WifiService;
import com.amazon.dee.app.ui.web.WifiBridge;
import com.amazon.dee.app.util.PermissionsUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class WifiBridge extends JavaScriptBridge {
    private static final String KEY_AUTO_NETWORK_SWITCH_SETTING_LABEL_NAME = "autoNetworkSwitchSettingLabelName";
    private static final String KEY_IS_AUTO_NETWORK_SWITCH_ENABLED = "isAutoNetworkSwitchEnabled";
    private static final String KEY_SSID = "ssid";
    private static final String KEY_TIMEOUT = "timeout";
    Activity activity;
    private Map<String, JavaScriptBridgeMethod> mMethods;
    Runnable pendingRunnable;
    WifiService wifiService;

    /* loaded from: classes12.dex */
    private class CheckAutoNetworkSwitchMethod implements JavaScriptBridgeMethod {
        private CheckAutoNetworkSwitchMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            WifiService.NetworkSwitchInformation networkSwitchInformation = WifiBridge.this.wifiService.getNetworkSwitchInformation();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(WifiBridge.KEY_IS_AUTO_NETWORK_SWITCH_ENABLED, networkSwitchInformation.isEnabled());
            jSONObject2.put(WifiBridge.KEY_AUTO_NETWORK_SWITCH_SETTING_LABEL_NAME, networkSwitchInformation.getSettingLabelName());
            javaScriptResponse.setResponse(jSONObject2);
            WifiBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class DisconnectFromDopplerMethod implements JavaScriptBridgeMethod {
        private DisconnectFromDopplerMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            WifiBridge.this.wifiService.disconnectAndReset();
            WifiBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    private class GetCurrentNetworkMethod implements JavaScriptBridgeMethod {
        private GetCurrentNetworkMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("ssid", WifiBridge.this.wifiService.getConnectedSSID());
            javaScriptResponse.setResponse(jSONObject2);
            WifiBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public WifiBridge(Activity activity, WifiService wifiService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.mMethods = new HashMap();
        this.activity = activity;
        this.wifiService = wifiService;
        this.mMethods.put("getCurrentNetwork", new GetCurrentNetworkMethod());
        this.mMethods.put("connectToDoppler", new ConnectToDopplerMethod());
        this.mMethods.put("disconnectFromDoppler", new DisconnectFromDopplerMethod());
        this.mMethods.put("checkAutoNetworkSwitch", new CheckAutoNetworkSwitchMethod());
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.mMethods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public final String getJavaScriptInterfaceName() {
        return "WifiBridge";
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        Runnable runnable;
        if (i != 1 || (runnable = this.pendingRunnable) == null) {
            return;
        }
        runnable.run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class ConnectToDopplerMethod implements JavaScriptBridgeMethod, PermissionsUtils.Listener {
        private ConnectToDopplerMethod() {
        }

        @Override // com.amazon.dee.app.util.PermissionsUtils.Listener
        public void complete(boolean z) {
            Runnable runnable = WifiBridge.this.pendingRunnable;
            if (runnable != null) {
                runnable.run();
            }
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, final JavaScriptResponse javaScriptResponse) throws JSONException {
            final long j = jSONObject.getLong("timeout");
            WifiBridge wifiBridge = WifiBridge.this;
            wifiBridge.pendingRunnable = new Runnable() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$WifiBridge$ConnectToDopplerMethod$oQdHsdPyICSNZU_gnMR5ecJkNDc
                @Override // java.lang.Runnable
                public final void run() {
                    WifiBridge.ConnectToDopplerMethod.this.lambda$execute$0$WifiBridge$ConnectToDopplerMethod(javaScriptResponse, j);
                }
            };
            if (!PermissionsUtils.hasLocationPermission(wifiBridge.activity.getApplicationContext()) && Build.VERSION.SDK_INT >= 27) {
                PermissionsUtils.requestLocationPermission(WifiBridge.this.activity, this, "softAPAutoConnect");
            } else {
                WifiBridge.this.pendingRunnable.run();
            }
        }

        public /* synthetic */ void lambda$execute$0$WifiBridge$ConnectToDopplerMethod(final JavaScriptResponse javaScriptResponse, long j) {
            WifiBridge.this.wifiService.connectToDoppler(new WifiService.ConnectionListener() { // from class: com.amazon.dee.app.ui.web.WifiBridge.ConnectToDopplerMethod.1
                @Override // com.amazon.dee.app.services.wifi.WifiService.ConnectionListener
                public void onCanceled() {
                    javaScriptResponse.setError(true);
                    WifiBridge.this.completeRequest(javaScriptResponse);
                }

                @Override // com.amazon.dee.app.services.wifi.WifiService.ConnectionListener
                public void onConnectFailed(Throwable th) {
                    javaScriptResponse.setError(true);
                    WifiBridge.this.completeRequest(javaScriptResponse);
                }

                @Override // com.amazon.dee.app.services.wifi.WifiService.ConnectionListener
                public void onConnected() {
                    javaScriptResponse.setResponse(new JSONObject());
                    WifiBridge.this.completeRequest(javaScriptResponse);
                }
            }, j);
        }

        @Override // com.amazon.dee.app.util.PermissionsUtils.Listener
        public void complete(boolean z, String str) {
            Runnable runnable = WifiBridge.this.pendingRunnable;
            if (runnable != null) {
                runnable.run();
            }
        }
    }
}
