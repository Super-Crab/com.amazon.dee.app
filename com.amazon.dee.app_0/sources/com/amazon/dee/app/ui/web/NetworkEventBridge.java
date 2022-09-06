package com.amazon.dee.app.ui.web;

import android.content.Context;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.dee.app.services.wifi.WifiService;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
/* loaded from: classes12.dex */
public class NetworkEventBridge extends JavaScriptBridge {
    Context context;
    NetworkService networkService;
    WifiService wifiService;

    public NetworkEventBridge(Context context, NetworkService networkService, WifiService wifiService, JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue) {
        super(javaScriptInjector, javaScriptResponseQueue);
        this.networkService = networkService;
        this.wifiService = wifiService;
        this.context = context;
    }

    void fireNetworkEvent(boolean z, boolean z2) {
        invokeJavaScriptFunction("fireNetworkEvent", new JSONArray((Collection) Arrays.asList(Boolean.valueOf(z), Boolean.valueOf(z2))));
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return new HashMap();
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public String getJavaScriptInterfaceName() {
        return "NetworkEventBridge";
    }

    public void notifyConnected() {
        boolean isConnectedToDoppler = this.wifiService.isConnectedToDoppler();
        fireNetworkEvent(!isConnectedToDoppler && this.networkService.isConnected(), isConnectedToDoppler);
    }

    public void notifyDisconnected() {
        fireNetworkEvent(false, false);
    }
}
