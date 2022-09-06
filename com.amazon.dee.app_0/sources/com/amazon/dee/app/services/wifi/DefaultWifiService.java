package com.amazon.dee.app.services.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.wifi.WifiService;
import com.amazon.dee.app.util.WebUtils;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.devicesetupservice.smarthome.ProtocolType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.net.HttpHeaders;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.net.SocketFactory;
import okhttp3.OkHttpClient;
import okhttp3.Response;
/* loaded from: classes12.dex */
public class DefaultWifiService implements WifiService {
    static final int STATE_CONNECTING = 2;
    static final int STATE_IDLE = 0;
    static final int STATE_SCANNING = 1;
    static final String TAG = Log.tag(WifiService.class);
    String connectingSsid;
    ConnectivityManager connectivityManager;
    Context context;
    EnvironmentService environmentService;
    private final Mobilytics mobilytics;
    WifiManager wifiManager;
    int state = 0;
    WifiScanResultsReceiver wifiScanResultsReceiver = new WifiScanResultsReceiver();
    ConnectionReceiver connectionReceiver = new ConnectionReceiver();
    DopplerConnectionReceiver dopplerConnectionReceiver = new DopplerConnectionReceiver();
    final LinkedList<WifiService.ConnectionListener> connectionListeners = new LinkedList<>();
    Handler handler = new Handler();
    Runnable cancelAutoConnect = new Runnable() { // from class: com.amazon.dee.app.services.wifi.-$$Lambda$RuN0Gs7g8yljkeuMMs63ze8nDXU
        @Override // java.lang.Runnable
        public final void run() {
            DefaultWifiService.this.cancelAutoConnect();
        }
    };
    Boolean listeningForDoppler = false;
    Network dopplerNetwork = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class ConnectionReceiver extends BroadcastReceiver {
        ConnectionReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (DefaultWifiService.this.state == 2) {
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                String replace = DefaultWifiService.this.wifiManager.getConnectionInfo().getSSID().replace(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, "");
                if (networkInfo == null || networkInfo.getState() != NetworkInfo.State.CONNECTED || !TextUtils.equals(DefaultWifiService.this.connectingSsid, replace)) {
                    return;
                }
                DefaultWifiService.this.state = 0;
                context.unregisterReceiver(this);
                DefaultWifiService.this.notifyConnectionConnected();
            }
        }
    }

    /* loaded from: classes12.dex */
    class DopplerConnectionReceiver extends BroadcastReceiver {
        DopplerConnectionReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                if (DefaultWifiService.this.isDopplerSSID(DefaultWifiService.this.wifiManager.getConnectionInfo().getSSID().replace(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, ""))) {
                    DefaultWifiService.this.bindNetworkToDopplerWifi();
                }
            }
            if (networkInfo.getState() == NetworkInfo.State.DISCONNECTED) {
                DefaultWifiService.this.unbindNetwork();
            }
        }
    }

    /* loaded from: classes12.dex */
    class WifiScanResultsReceiver extends BroadcastReceiver {
        WifiScanResultsReceiver() {
        }

        ScanResult findClosestDevice(List<ScanResult> list) {
            ScanResult scanResult = null;
            if (list != null) {
                for (ScanResult scanResult2 : list) {
                    if (!TextUtils.isEmpty(scanResult2.SSID) && DefaultWifiService.this.isDopplerSSID(scanResult2.SSID)) {
                        String str = DefaultWifiService.TAG;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Found Amazon access point: ");
                        outline107.append(scanResult2.SSID);
                        outline107.toString();
                        if (scanResult == null || scanResult.level < scanResult2.level) {
                            scanResult = scanResult2;
                        }
                    }
                }
            }
            return scanResult;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DefaultWifiService defaultWifiService = DefaultWifiService.this;
            if (defaultWifiService.state == 1) {
                defaultWifiService.state = 0;
                context.unregisterReceiver(this);
                ScanResult findClosestDevice = findClosestDevice(DefaultWifiService.this.wifiManager.getScanResults());
                if (findClosestDevice != null && !TextUtils.isEmpty(findClosestDevice.SSID)) {
                    DefaultWifiService.this.connect(findClosestDevice.SSID);
                } else {
                    DefaultWifiService.this.notifyConnectionFailed(new Exception("Failed to find wifi access point"));
                }
            }
        }
    }

    public DefaultWifiService(Context context, EnvironmentService environmentService, Mobilytics mobilytics) {
        this.context = context;
        this.environmentService = environmentService;
        this.wifiManager = (WifiManager) context.getSystemService("wifi");
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mobilytics = mobilytics;
    }

    private void addOriginIfMissing(Map<String, String> map) {
        if (map.get(HttpHeaders.ORIGIN) == null && map.get("origin") == null) {
            map.put("origin", this.environmentService.getWebEndpoint());
            Log.i(TAG, "Adding missing origin header to intercepted request.");
        }
    }

    private Network getDopplerNetwork() {
        Network[] allNetworks;
        Network network = null;
        for (Network network2 : this.connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo = this.connectivityManager.getNetworkInfo(network2);
            if (networkInfo != null) {
                String extraInfo = networkInfo.getExtraInfo();
                if (Build.VERSION.SDK_INT >= 28 && extraInfo == null) {
                    this.mobilytics.recordOperationalEvent(this.mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.SOFT_AP_PIE_WIFI_PASSTHROUGH, OperationalEventType.DIAGNOSTIC, AlexaMetricsConstants.MetricsComponents.WIFI_SERVICE, DefaultWifiService.class.getSimpleName()));
                    extraInfo = this.wifiManager.getConnectionInfo().getSSID();
                }
                if (extraInfo != null) {
                    String replace = extraInfo.replace(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, "");
                    String typeName = networkInfo.getTypeName();
                    if (isDopplerSSID(replace) && typeName.equals(ProtocolType.WIFI)) {
                        network = network2;
                    }
                }
            }
        }
        return network;
    }

    private String getSSID(Network network) {
        NetworkInfo networkInfo = this.connectivityManager.getNetworkInfo(network);
        return (networkInfo == null || networkInfo.getExtraInfo() == null) ? "" : networkInfo.getExtraInfo().replace(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, "");
    }

    void bindNetworkToDopplerWifi() {
        int i = Build.VERSION.SDK_INT;
        Network dopplerNetwork = getDopplerNetwork();
        if (dopplerNetwork != null) {
            this.dopplerNetwork = dopplerNetwork;
            this.connectivityManager.bindProcessToNetwork(dopplerNetwork);
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bound process to network: ");
            outline107.append(getSSID(dopplerNetwork));
            outline107.append(":");
            outline107.append(dopplerNetwork);
            Log.i(str, outline107.toString());
        }
    }

    @Override // com.amazon.dee.app.services.wifi.WifiService
    public void cancelAutoConnect() {
        int i = this.state;
        if (i == 1) {
            this.state = 0;
            this.handler.removeCallbacks(this.cancelAutoConnect);
            this.context.unregisterReceiver(this.wifiScanResultsReceiver);
            notifyConnectionCanceled();
        } else if (i == 2) {
            this.state = 0;
            this.handler.removeCallbacks(this.cancelAutoConnect);
            this.context.unregisterReceiver(this.connectionReceiver);
            notifyConnectionCanceled();
        }
        dissassociateFromDoppler();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0074  */
    @Override // com.amazon.dee.app.services.wifi.WifiService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void connect(@androidx.annotation.NonNull java.lang.String r11) {
        /*
            r10 = this;
            int r0 = r10.state
            r1 = 2
            if (r0 != 0) goto Lbe
            android.net.wifi.WifiManager r0 = r10.wifiManager
            java.util.List r0 = r0.getConfiguredNetworks()
            r2 = 1
            r3 = 0
            java.lang.String r4 = "\""
            java.lang.String r5 = "Failed to configure wifi"
            r6 = -1
            if (r0 == 0) goto L71
            java.util.Iterator r0 = r0.iterator()
        L18:
            boolean r7 = r0.hasNext()
            if (r7 == 0) goto L71
            java.lang.Object r7 = r0.next()
            android.net.wifi.WifiConfiguration r7 = (android.net.wifi.WifiConfiguration) r7
            java.lang.String r8 = r7.SSID
            if (r8 != 0) goto L30
            java.lang.String r7 = com.amazon.dee.app.services.wifi.DefaultWifiService.TAG
            java.lang.String r8 = "WiFiConfiguration SSID was null"
            com.amazon.dee.app.services.logging.Log.e(r7, r8)
            goto L18
        L30:
            java.lang.String r9 = ""
            java.lang.String r8 = r8.replace(r4, r9)
            boolean r8 = android.text.TextUtils.equals(r11, r8)
            if (r8 == 0) goto L18
            r7.status = r3
            android.net.wifi.WifiManager r0 = r10.wifiManager
            int r0 = r0.updateNetwork(r7)
            if (r0 != r6) goto L54
            java.lang.String r11 = com.amazon.dee.app.services.wifi.DefaultWifiService.TAG
            com.amazon.dee.app.services.logging.Log.e(r11, r5)
            java.lang.Exception r11 = new java.lang.Exception
            r11.<init>(r5)
            r10.notifyConnectionFailed(r11)
            return
        L54:
            int r0 = r7.networkId
            android.net.wifi.WifiManager r7 = r10.wifiManager
            r7.disconnect()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Connecting to network: "
            r7.append(r8)
            r7.append(r0)
            r7.toString()
            android.net.wifi.WifiManager r7 = r10.wifiManager
            r7.enableNetwork(r0, r2)
            goto L72
        L71:
            r0 = r6
        L72:
            if (r0 != r6) goto La6
            android.net.wifi.WifiConfiguration r0 = new android.net.wifi.WifiConfiguration
            r0.<init>()
            java.lang.String r4 = com.android.tools.r8.GeneratedOutlineSupport1.outline75(r4, r11, r4)
            r0.SSID = r4
            java.util.BitSet r4 = r0.allowedKeyManagement
            r4.set(r3)
            r0.status = r3
            android.net.wifi.WifiManager r3 = r10.wifiManager
            int r0 = r3.addNetwork(r0)
            if (r0 != r6) goto L9c
            java.lang.String r11 = com.amazon.dee.app.services.wifi.DefaultWifiService.TAG
            com.amazon.dee.app.services.logging.Log.e(r11, r5)
            java.lang.Exception r11 = new java.lang.Exception
            r11.<init>(r5)
            r10.notifyConnectionFailed(r11)
            return
        L9c:
            android.net.wifi.WifiManager r3 = r10.wifiManager
            r3.disconnect()
            android.net.wifi.WifiManager r3 = r10.wifiManager
            r3.enableNetwork(r0, r2)
        La6:
            r10.connectingSsid = r11
            r10.state = r1
            android.content.Context r11 = r10.context
            com.amazon.dee.app.services.wifi.DefaultWifiService$ConnectionReceiver r0 = r10.connectionReceiver
            android.content.IntentFilter r1 = new android.content.IntentFilter
            java.lang.String r2 = "android.net.wifi.STATE_CHANGE"
            r1.<init>(r2)
            r11.registerReceiver(r0, r1)
            android.net.wifi.WifiManager r11 = r10.wifiManager
            r11.reconnect()
            goto Lca
        Lbe:
            if (r0 == r1) goto Lca
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "Wifi service is busy"
            r11.<init>(r0)
            r10.notifyConnectionFailed(r11)
        Lca:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.dee.app.services.wifi.DefaultWifiService.connect(java.lang.String):void");
    }

    @Override // com.amazon.dee.app.services.wifi.WifiService
    public void connectToDoppler(@NonNull WifiService.ConnectionListener connectionListener, long j) {
        synchronized (this.connectionListeners) {
            this.connectionListeners.add(connectionListener);
        }
        bindNetworkToDopplerWifi();
        this.context.registerReceiver(this.dopplerConnectionReceiver, new IntentFilter("android.net.wifi.STATE_CHANGE"));
        this.listeningForDoppler = true;
        int i = this.state;
        if (i == 0) {
            if (ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                notifyConnectionFailed(new IllegalStateException("android.permission.ACCESS_COARSE_LOCATION permission is not granted"));
                return;
            } else if (this.wifiManager.setWifiEnabled(true) && this.wifiManager.startScan()) {
                this.state = 1;
                this.context.registerReceiver(this.wifiScanResultsReceiver, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
            } else {
                notifyConnectionFailed(new IllegalStateException("Failed to start scanning wifi networks"));
                return;
            }
        } else if (i != 1) {
            notifyConnectionFailed(new IllegalStateException("Wifi service is busy"));
            return;
        }
        this.handler.removeCallbacks(this.cancelAutoConnect);
        this.handler.postDelayed(this.cancelAutoConnect, j);
    }

    @Override // com.amazon.dee.app.services.wifi.WifiService
    public void disconnectAndReset() {
        int i = this.state;
        if (i == 2) {
            this.state = 0;
            this.handler.removeCallbacks(this.cancelAutoConnect);
            this.context.unregisterReceiver(this.connectionReceiver);
            notifyConnectionCanceled();
        } else if (i == 1) {
            this.state = 0;
            this.handler.removeCallbacks(this.cancelAutoConnect);
            this.context.unregisterReceiver(this.wifiScanResultsReceiver);
            notifyConnectionCanceled();
        }
        if (this.listeningForDoppler.booleanValue()) {
            this.context.unregisterReceiver(this.dopplerConnectionReceiver);
            this.listeningForDoppler = false;
        }
        unbindNetwork();
        dissassociateFromDoppler();
    }

    void dissassociateFromDoppler() {
        String replace = this.wifiManager.getConnectionInfo().getSSID().replace(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, "");
        if (isDopplerSSID(replace)) {
            for (WifiConfiguration wifiConfiguration : this.wifiManager.getConfiguredNetworks()) {
                if (!TextUtils.isEmpty(wifiConfiguration.SSID) && wifiConfiguration.SSID.replace(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, "").equals(replace) && wifiConfiguration.status == 0) {
                    wifiConfiguration.status = 2;
                    this.wifiManager.updateNetwork(wifiConfiguration);
                    String str = TAG;
                    Log.i(str, "Preference for Amazon SSID \"" + replace + "\" removed.");
                }
            }
        }
    }

    @Override // com.amazon.dee.app.services.wifi.WifiService
    public String getConnectedSSID() {
        String ssid;
        WifiInfo connectionInfo = this.wifiManager.getConnectionInfo();
        if (connectionInfo == null || (ssid = connectionInfo.getSSID()) == null) {
            return null;
        }
        return ssid.replaceAll(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, "");
    }

    int getCurrentAutoNetworkSwitchValue() {
        return Settings.Global.getInt(this.context.getContentResolver(), "wifi_watchdog_poor_network_test_enabled", -1);
    }

    OkHttpClient.Builder getDopplerHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        SocketFactory dopplerSocketFactory = getDopplerSocketFactory();
        if (dopplerSocketFactory != null) {
            builder.socketFactory(dopplerSocketFactory);
        }
        return builder;
    }

    SocketFactory getDopplerSocketFactory() {
        Network dopplerNetwork = getDopplerNetwork();
        if (dopplerNetwork == null) {
            return null;
        }
        return dopplerNetwork.getSocketFactory();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0099  */
    @Override // com.amazon.dee.app.services.wifi.WifiService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.dee.app.services.wifi.WifiService.NetworkSwitchInformation getNetworkSwitchInformation() {
        /*
            r5 = this;
            android.content.Context r0 = r5.context
            java.lang.String r1 = "com.android.settings"
            android.content.res.Resources r0 = com.amazon.dee.app.util.ResourceUtils.getResources(r0, r1)
            if (r0 == 0) goto L38
            java.lang.String r2 = "wifi_watchdog_connectivity_check"
            java.lang.String r3 = "string"
            int r1 = r0.getIdentifier(r2, r3, r1)
            if (r1 == 0) goto L38
            java.lang.String r0 = r0.getString(r1)     // Catch: android.content.res.Resources.NotFoundException -> L1b
            goto L39
        L1b:
            r0 = move-exception
            java.lang.String r1 = com.amazon.dee.app.services.wifi.DefaultWifiService.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = com.amazon.dee.app.services.logging.Log.line()
            r2.append(r3)
            java.lang.String r0 = r0.getMessage()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.amazon.dee.app.services.logging.Log.e(r1, r0)
        L38:
            r0 = 0
        L39:
            com.amazon.alexa.protocols.environment.EnvironmentService r1 = r5.environmentService
            com.amazon.alexa.device.api.DeviceInformation r1 = r1.getDeviceInformation()
            java.lang.String r1 = r1.getManufacturer()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Device manufacturer: "
            r2.append(r3)
            r2.append(r1)
            r2.toString()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Device setting name: "
            r2.append(r3)
            r2.append(r0)
            r2.toString()
            java.lang.String r2 = "samsung"
            boolean r1 = r2.equalsIgnoreCase(r1)
            r2 = 0
            if (r1 == 0) goto L93
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L93
            r1 = -1
            int r3 = r5.getCurrentAutoNetworkSwitchValue()
            java.lang.String r4 = "Auto network switch value is "
            com.android.tools.r8.GeneratedOutlineSupport1.outline149(r4, r3)
            if (r3 == r1) goto L89
            com.amazon.dee.app.services.wifi.WifiService$NetworkSwitchInformation r1 = new com.amazon.dee.app.services.wifi.WifiService$NetworkSwitchInformation
            r4 = 1
            if (r3 != r4) goto L85
            r2 = r4
        L85:
            r1.<init>(r0, r2)
            return r1
        L89:
            com.amazon.dee.app.services.wifi.WifiService$NetworkSwitchInformation r1 = new com.amazon.dee.app.services.wifi.WifiService$NetworkSwitchInformation
            boolean r2 = r5.isAutoNetworkSwitchEnabledByDefault()
            r1.<init>(r0, r2)
            return r1
        L93:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L9c
            java.lang.String r0 = "unknown"
        L9c:
            com.amazon.dee.app.services.wifi.WifiService$NetworkSwitchInformation r1 = new com.amazon.dee.app.services.wifi.WifiService$NetworkSwitchInformation
            r1.<init>(r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.dee.app.services.wifi.DefaultWifiService.getNetworkSwitchInformation():com.amazon.dee.app.services.wifi.WifiService$NetworkSwitchInformation");
    }

    boolean isAutoNetworkSwitchEnabledByDefault() {
        try {
            Field field = Class.forName("android.net.wifi.WifiWatchdogStateMachine").getField("DEFAULT_POOR_NETWORK_AVOIDANCE_ENABLED");
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field.getBoolean(null);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            Log.e(TAG, e, "Failed to retrieve default settings", new Object[0]);
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.wifi.WifiService
    public boolean isConnected() {
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting() && activeNetworkInfo.getType() == 1;
    }

    @Override // com.amazon.dee.app.services.wifi.WifiService
    public boolean isConnectedToDoppler() {
        String connectedSSID = getConnectedSSID();
        return !TextUtils.isEmpty(connectedSSID) && isDopplerSSID(connectedSSID);
    }

    boolean isDopplerSSID(@NonNull String str) {
        for (String str2 : new String[]{"Amazon-", "DIRECT-dp-", "Echo-"}) {
            if (TextUtils.indexOf(str, str2) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.dee.app.services.wifi.WifiService
    public Response makeRequestToDoppler(String str, Uri uri, Map<String, String> map, String str2) {
        String builder = uri.buildUpon().scheme("http").toString();
        if (!isConnectedToDoppler()) {
            return null;
        }
        String str3 = "Intercepting " + builder + " because connected to doppler";
        OkHttpClient build = getDopplerHttpClient().build();
        addOriginIfMissing(map);
        try {
            return build.newCall(WebUtils.buildHttpRequest(str, map, builder, str2).build()).execute();
        } catch (Exception e) {
            String str4 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ERROR making doppler request ");
            outline107.append(uri.toString());
            outline107.append(RealTimeTextConstants.COLON_SPACE);
            outline107.append(e.toString());
            Log.e(str4, outline107.toString());
            return null;
        }
    }

    void notifyConnectionCanceled() {
        ArrayList arrayList;
        synchronized (this.connectionListeners) {
            arrayList = new ArrayList(this.connectionListeners);
            this.connectionListeners.clear();
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((WifiService.ConnectionListener) it2.next()).onCanceled();
        }
    }

    void notifyConnectionConnected() {
        ArrayList arrayList;
        synchronized (this.connectionListeners) {
            arrayList = new ArrayList(this.connectionListeners);
            this.connectionListeners.clear();
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((WifiService.ConnectionListener) it2.next()).onConnected();
        }
    }

    void notifyConnectionFailed(Throwable th) {
        ArrayList arrayList;
        synchronized (this.connectionListeners) {
            arrayList = new ArrayList(this.connectionListeners);
            this.connectionListeners.clear();
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((WifiService.ConnectionListener) it2.next()).onConnectFailed(th);
        }
    }

    void unbindNetwork() {
        Network boundNetworkForProcess = this.connectivityManager.getBoundNetworkForProcess();
        if (boundNetworkForProcess != null) {
            this.connectivityManager.bindProcessToNetwork(null);
            NetworkInfo networkInfo = this.connectivityManager.getNetworkInfo(boundNetworkForProcess);
            if (networkInfo == null) {
                return;
            }
            String extraInfo = networkInfo.getExtraInfo();
            String str = TAG;
            Log.i(str, "Process no longer bound network: " + extraInfo);
        }
    }
}
