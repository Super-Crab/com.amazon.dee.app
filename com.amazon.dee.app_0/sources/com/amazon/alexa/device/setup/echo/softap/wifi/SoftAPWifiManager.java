package com.amazon.alexa.device.setup.echo.softap.wifi;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.MacAddress;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.net.wifi.WifiNetworkSuggestion;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.devicesetupservice.smarthome.ProtocolType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import okhttp3.OkHttpClient;
/* loaded from: classes6.dex */
public class SoftAPWifiManager {
    private static final String TAG = "SoftAPWifiManager";
    private static ConnectivityManager.NetworkCallback networkCallback;
    private AutoConnectState autoConnectState = AutoConnectState.IDLE;
    private ReplaySubject<Boolean> autoConnectSuccess = ReplaySubject.create();
    private ConnectivityManager connectivityManager;
    private Context context;
    private BroadcastReceiver echoConnectionReceiver;
    private LazyComponent<IdentityService> identityService;
    private WifiManager wifiManager;
    private BroadcastReceiver wifiScanReceiver;

    /* renamed from: com.amazon.alexa.device.setup.echo.softap.wifi.SoftAPWifiManager$4  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$android$net$NetworkInfo$State = new int[NetworkInfo.State.values().length];

        static {
            try {
                $SwitchMap$android$net$NetworkInfo$State[NetworkInfo.State.DISCONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$State[NetworkInfo.State.DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$State[NetworkInfo.State.CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes6.dex */
    public class AutoConnectException extends Exception {
        public AutoConnectException(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public enum AutoConnectState {
        SCANNING,
        IDLE,
        CONNECTING,
        CONNECTED,
        TERMINATED
    }

    public SoftAPWifiManager(Context context, LazyComponent<IdentityService> lazyComponent) {
        this.wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.context = context;
        this.identityService = lazyComponent;
    }

    private ScanResult findClosestDevice(List<ScanResult> list) {
        ScanResult scanResult = null;
        if (list != null) {
            for (ScanResult scanResult2 : list) {
                if (!TextUtils.isEmpty(scanResult2.SSID) && isDopplerSSID(scanResult2.SSID) && (scanResult == null || scanResult.level < scanResult2.level)) {
                    scanResult = scanResult2;
                }
            }
        }
        return scanResult;
    }

    private Network findDopplerNetwork() {
        Network[] allNetworks;
        for (Network network : this.connectivityManager.getAllNetworks()) {
            if (this.connectivityManager.getNetworkInfo(network).getTypeName().equals(ProtocolType.WIFI) && isDopplerSSID(this.wifiManager.getConnectionInfo().getSSID())) {
                return network;
            }
        }
        return null;
    }

    private BroadcastReceiver getEchoConnectionReceiver() {
        if (this.echoConnectionReceiver == null) {
            this.echoConnectionReceiver = new BroadcastReceiver() { // from class: com.amazon.alexa.device.setup.echo.softap.wifi.SoftAPWifiManager.3
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (!intent.getAction().equals("android.net.wifi.action.WIFI_NETWORK_SUGGESTION_POST_CONNECTION")) {
                        return;
                    }
                    String replace = SoftAPWifiManager.this.wifiManager.getConnectionInfo().getSSID().replace(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, "");
                    int i = AnonymousClass4.$SwitchMap$android$net$NetworkInfo$State[((NetworkInfo) intent.getParcelableExtra("networkInfo")).getState().ordinal()];
                    if (i == 1 || i == 2) {
                        String unused = SoftAPWifiManager.TAG;
                        GeneratedOutlineSupport1.outline158("Disconnected from network: ", replace);
                        SoftAPWifiManager.this.unbindNetwork();
                        if (!SoftAPWifiManager.this.isDopplerSSID(replace)) {
                            return;
                        }
                        SoftAPWifiManager.this.autoConnectSuccess.onError(new AutoConnectException("Autoconnect to echo failed"));
                        context.unregisterReceiver(this);
                    } else if (i != 3) {
                    } else {
                        if (Build.VERSION.SDK_INT >= 29 || !SoftAPWifiManager.this.getHasAccessToAutoConnect()) {
                            String unused2 = SoftAPWifiManager.TAG;
                            return;
                        }
                        String unused3 = SoftAPWifiManager.TAG;
                        GeneratedOutlineSupport1.outline158("Connected to ", replace);
                        if (SoftAPWifiManager.this.isDopplerSSID(replace)) {
                            SoftAPWifiManager.this.bindToEchoNetwork();
                            SoftAPWifiManager.this.autoConnectState = AutoConnectState.CONNECTED;
                            SoftAPWifiManager.this.autoConnectSuccess.onNext(true);
                            return;
                        }
                        SoftAPWifiManager.this.unbindNetwork();
                    }
                }
            };
        }
        return this.echoConnectionReceiver;
    }

    private BroadcastReceiver getWifiScanReceiver() {
        if (this.wifiScanReceiver == null) {
            this.wifiScanReceiver = new BroadcastReceiver() { // from class: com.amazon.alexa.device.setup.echo.softap.wifi.SoftAPWifiManager.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (SoftAPWifiManager.this.autoConnectState == null || !SoftAPWifiManager.this.autoConnectState.equals(AutoConnectState.SCANNING)) {
                        return;
                    }
                    SoftAPWifiManager.this.autoConnectState = AutoConnectState.IDLE;
                    SoftAPWifiManager.this.context.unregisterReceiver(this);
                    if (!intent.getBooleanExtra("resultsUpdated", false)) {
                        SoftAPWifiManager.this.autoConnectSuccess.onError(new AutoConnectException("Autoconnect to echo failed"));
                    } else if (Build.VERSION.SDK_INT < 29 || !SoftAPWifiManager.this.getHasAccessToAutoConnect()) {
                        SoftAPWifiManager.this.scanSuccessOldSDK();
                    } else {
                        SoftAPWifiManager.this.scanSuccessUpdatedSDK();
                    }
                }
            };
        }
        return this.wifiScanReceiver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public void scanSuccessOldSDK() {
        WifiConfiguration wifiConfiguration;
        ScanResult findClosestDevice;
        int i;
        this.context.registerReceiver(getEchoConnectionReceiver(), new IntentFilter("android.net.wifi.STATE_CHANGE"));
        try {
            List<ScanResult> scanResults = this.wifiManager.getScanResults();
            wifiConfiguration = new WifiConfiguration();
            findClosestDevice = findClosestDevice(scanResults);
        } catch (SecurityException e) {
            Log.e(TAG, e.getMessage());
        }
        if (findClosestDevice == null) {
            this.autoConnectSuccess.onError(new AutoConnectException("Autoconnect to echo failed"));
            return;
        }
        wifiConfiguration.SSID = EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED + findClosestDevice.SSID + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED;
        wifiConfiguration.allowedKeyManagement.set(0);
        this.wifiManager.addNetwork(wifiConfiguration);
        List<WifiConfiguration> configuredNetworks = this.wifiManager.getConfiguredNetworks();
        if (configuredNetworks != null && !configuredNetworks.isEmpty()) {
            for (WifiConfiguration wifiConfiguration2 : configuredNetworks) {
                if (wifiConfiguration2.SSID != null) {
                    if (wifiConfiguration2.SSID.equals(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED + findClosestDevice.SSID + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) && this.autoConnectState.equals(AutoConnectState.IDLE)) {
                        String str = "Attempting to connect to: " + wifiConfiguration2.SSID;
                        wifiConfiguration2.status = 0;
                        wifiConfiguration2.SSID = findClosestDevice.SSID;
                        wifiConfiguration2.priority = 99999;
                        this.wifiManager.updateNetwork(wifiConfiguration2);
                        this.autoConnectState = AutoConnectState.CONNECTING;
                        i = wifiConfiguration2.networkId;
                        this.wifiManager.disconnect();
                        this.wifiManager.enableNetwork(wifiConfiguration2.networkId, true);
                        this.wifiManager.reconnect();
                        break;
                    }
                }
            }
        }
        i = -1;
        if (i == -1) {
            WifiConfiguration wifiConfiguration3 = new WifiConfiguration();
            wifiConfiguration3.SSID = EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED + findClosestDevice.SSID + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED;
            wifiConfiguration3.allowedKeyManagement.set(0);
            wifiConfiguration3.status = 0;
            int addNetwork = this.wifiManager.addNetwork(wifiConfiguration3);
            if (addNetwork == -1) {
                this.autoConnectSuccess.onError(new AutoConnectException("Auto connect to echo failed"));
                return;
            }
            this.wifiManager.disconnect();
            this.wifiManager.enableNetwork(addNetwork, true);
            this.wifiManager.reconnect();
        }
        if (this.autoConnectState == AutoConnectState.CONNECTING) {
            return;
        }
        this.autoConnectSuccess.onError(new AutoConnectException("Autoconnect to echo failed"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"MissingPermission", "NewApi"})
    public void scanSuccessUpdatedSDK() {
        ArrayList arrayList;
        WifiConfiguration wifiConfiguration;
        ScanResult findClosestDevice;
        try {
            List<ScanResult> scanResults = this.wifiManager.getScanResults();
            arrayList = new ArrayList();
            wifiConfiguration = new WifiConfiguration();
            findClosestDevice = findClosestDevice(scanResults);
        } catch (SecurityException e) {
            Log.e(TAG, e.getMessage());
        }
        if (findClosestDevice == null) {
            this.autoConnectSuccess.onError(new AutoConnectException("Autoconnect to echo failed"));
            return;
        }
        wifiConfiguration.SSID = EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED + findClosestDevice.SSID + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED;
        wifiConfiguration.allowedKeyManagement.set(0);
        arrayList.add(new WifiNetworkSuggestion.Builder().setSsid(findClosestDevice.SSID).setIsAppInteractionRequired(true).build());
        this.autoConnectState = AutoConnectState.CONNECTING;
        if (this.wifiManager.addNetworkSuggestions(arrayList) != 0) {
            this.autoConnectSuccess.onError(new AutoConnectException("Auto connect to echo failed"));
            return;
        }
        NetworkRequest build = new NetworkRequest.Builder().addTransportType(1).removeCapability(12).setNetworkSpecifier(new WifiNetworkSpecifier.Builder().setSsid(findClosestDevice.SSID).setBssid(MacAddress.fromString(findClosestDevice.BSSID)).build()).build();
        if (networkCallback != null) {
            this.connectivityManager.unregisterNetworkCallback(networkCallback);
        }
        networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.amazon.alexa.device.setup.echo.softap.wifi.SoftAPWifiManager.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                String unused = SoftAPWifiManager.TAG;
                SoftAPWifiManager.this.connectivityManager.bindProcessToNetwork(network);
                SoftAPWifiManager.this.autoConnectState = AutoConnectState.CONNECTED;
                SoftAPWifiManager.this.autoConnectSuccess.onNext(true);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                SoftAPWifiManager.this.unbindNetwork();
                SoftAPWifiManager.this.connectivityManager.bindProcessToNetwork(null);
                SoftAPWifiManager.this.connectivityManager.unregisterNetworkCallback(SoftAPWifiManager.networkCallback);
                String unused = SoftAPWifiManager.TAG;
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onUnavailable() {
                super.onUnavailable();
                String unused = SoftAPWifiManager.TAG;
                SoftAPWifiManager.this.unbindNetwork();
                SoftAPWifiManager.this.autoConnectSuccess.onError(new AutoConnectException("Autoconnect to echo failed"));
            }
        };
        this.connectivityManager.requestNetwork(build, networkCallback);
        this.connectivityManager.registerNetworkCallback(build, networkCallback);
        if (this.autoConnectState == AutoConnectState.CONNECTING) {
            return;
        }
        this.autoConnectSuccess.onError(new AutoConnectException("Autoconnect to echo failed"));
    }

    public boolean bindToEchoNetwork() {
        Network[] allNetworks;
        int i = Build.VERSION.SDK_INT;
        for (Network network : this.connectivityManager.getAllNetworks()) {
            if (this.connectivityManager.getNetworkInfo(network).getTypeName().equals(ProtocolType.WIFI) && isDopplerSSID(this.wifiManager.getConnectionInfo().getSSID())) {
                return this.connectivityManager.bindProcessToNetwork(network);
            }
        }
        return false;
    }

    public void clearAutoConnectReceivers() {
        try {
            try {
                if (this.wifiScanReceiver != null) {
                    this.context.unregisterReceiver(this.wifiScanReceiver);
                }
            } catch (Exception e) {
                Log.w(TAG, "Failed to unregister wifiScanReceiver", e);
            }
            try {
                try {
                    if (this.echoConnectionReceiver != null) {
                        this.context.unregisterReceiver(this.echoConnectionReceiver);
                    }
                } catch (Exception e2) {
                    Log.w(TAG, "Failed to unregister echoConnectionReceiver", e2);
                }
            } finally {
                this.echoConnectionReceiver = null;
            }
        } finally {
            this.wifiScanReceiver = null;
        }
    }

    @SuppressLint({"MissingPermission", "NewApi"})
    public void disassociateFromDoppler() {
        if (Build.VERSION.SDK_INT >= 29 && getHasAccessToAutoConnect()) {
            this.wifiManager.removeNetworkSuggestions(Collections.emptyList());
            return;
        }
        String replace = this.wifiManager.getConnectionInfo().getSSID().replace(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, "");
        if (!isDopplerSSID(replace)) {
            return;
        }
        for (WifiConfiguration wifiConfiguration : this.wifiManager.getConfiguredNetworks()) {
            if (!TextUtils.isEmpty(wifiConfiguration.SSID) && wifiConfiguration.SSID.replace(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, "").equals(replace) && wifiConfiguration.status == 0) {
                wifiConfiguration.status = 2;
                this.wifiManager.updateNetwork(wifiConfiguration);
            }
        }
    }

    public ReplaySubject<Boolean> getAutoConnectSuccessSubject() {
        return this.autoConnectSuccess;
    }

    public OkHttpClient getBoundClient() {
        SocketFactory socketFactory;
        Network findDopplerNetwork = findDopplerNetwork();
        if (findDopplerNetwork == null || (socketFactory = findDopplerNetwork.getSocketFactory()) == null) {
            return null;
        }
        return new OkHttpClient.Builder().socketFactory(socketFactory).connectTimeout(20L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).build();
    }

    public boolean getHasAccessToAutoConnect() {
        return this.identityService.mo10268get().getUser(TAG).getFeatures().contains("DEVICE_SETUP_AUTOCONNECT_ANDROID");
    }

    public void initializeAutoConnectToEcho() {
        this.context.registerReceiver(getWifiScanReceiver(), GeneratedOutlineSupport1.outline10("android.net.wifi.SCAN_RESULTS"));
        if (!this.wifiManager.startScan()) {
            clearAutoConnectReceivers();
            this.autoConnectSuccess.onError(new AutoConnectException("Scan failure"));
            return;
        }
        this.autoConnectState = AutoConnectState.SCANNING;
    }

    boolean isDopplerSSID(@NonNull String str) {
        String[] strArr;
        for (String str2 : new String[]{"Amazon-", "DIRECT-dp-", "Echo-"}) {
            if (str.replace(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, "").contains(str2) || str.contains(str2)) {
                return true;
            }
        }
        return false;
    }

    public void unbindNetwork() {
        int i = Build.VERSION.SDK_INT;
        if (this.connectivityManager.getBoundNetworkForProcess() != null) {
            this.connectivityManager.bindProcessToNetwork(null);
        }
    }
}
