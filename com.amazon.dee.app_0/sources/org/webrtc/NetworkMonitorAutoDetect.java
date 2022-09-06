package org.webrtc;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiInfo;
import android.net.wifi.p2p.WifiP2pGroup;
import android.os.Build;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes5.dex */
public class NetworkMonitorAutoDetect extends BroadcastReceiver {
    static final long INVALID_NET_ID = -1;
    private static final String TAG = "NetworkMonitorAutoDetect";
    private final ConnectivityManager.NetworkCallback allNetworkCallback;
    private ConnectionType connectionType;
    private ConnectivityManagerDelegate connectivityManagerDelegate;
    private final Context context;
    private final IntentFilter intentFilter;
    private boolean isRegistered;
    private final ConnectivityManager.NetworkCallback mobileNetworkCallback;
    private final Observer observer;
    private WifiDirectManagerDelegate wifiDirectManagerDelegate;
    private WifiManagerDelegate wifiManagerDelegate;
    private String wifiSSID;

    /* loaded from: classes5.dex */
    public enum ConnectionType {
        CONNECTION_UNKNOWN,
        CONNECTION_ETHERNET,
        CONNECTION_WIFI,
        CONNECTION_4G,
        CONNECTION_3G,
        CONNECTION_2G,
        CONNECTION_UNKNOWN_CELLULAR,
        CONNECTION_BLUETOOTH,
        CONNECTION_NONE
    }

    /* loaded from: classes5.dex */
    public static class IPAddress {
        public final byte[] address;

        public IPAddress(byte[] bArr) {
            this.address = bArr;
        }
    }

    /* loaded from: classes5.dex */
    public static class NetworkInformation {
        public final long handle;
        public boolean hasInternetCapability;
        public final IPAddress[] ipAddresses;
        public final String name;
        public final ConnectionType type;

        public NetworkInformation(String str, ConnectionType connectionType, long j, IPAddress[] iPAddressArr, boolean z) {
            this.name = str;
            this.type = connectionType;
            this.handle = j;
            this.ipAddresses = iPAddressArr;
            this.hasInternetCapability = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class NetworkState {
        private final boolean connected;
        private final int subtype;
        private final int type;

        public NetworkState(boolean z, int i, int i2) {
            this.connected = z;
            this.type = i;
            this.subtype = i2;
        }

        public int getNetworkSubType() {
            return this.subtype;
        }

        public int getNetworkType() {
            return this.type;
        }

        public boolean isConnected() {
            return this.connected;
        }
    }

    /* loaded from: classes5.dex */
    public interface Observer {
        void onConnectionTypeChanged(ConnectionType connectionType);

        void onNetworkConnect(NetworkInformation networkInformation);

        void onNetworkDisconnect(long j);
    }

    @SuppressLint({"NewApi"})
    /* loaded from: classes5.dex */
    private class SimpleNetworkCallback extends ConnectivityManager.NetworkCallback {
        private SimpleNetworkCallback() {
        }

        private void onNetworkChanged(Network network) {
            NetworkInformation networkToInfo = NetworkMonitorAutoDetect.this.connectivityManagerDelegate.networkToInfo(network);
            if (networkToInfo != null) {
                NetworkMonitorAutoDetect.this.observer.onNetworkConnect(networkToInfo);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Network becomes available: ");
            outline107.append(network.toString());
            Logging.d(NetworkMonitorAutoDetect.TAG, outline107.toString());
            onNetworkChanged(network);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("capabilities changed: ");
            outline107.append(networkCapabilities.toString());
            Logging.d(NetworkMonitorAutoDetect.TAG, outline107.toString());
            onNetworkChanged(network);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("link properties changed: ");
            outline107.append(linkProperties.toString());
            Logging.d(NetworkMonitorAutoDetect.TAG, outline107.toString());
            onNetworkChanged(network);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLosing(Network network, int i) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Network ");
            outline107.append(network.toString());
            outline107.append(" is about to lose in ");
            outline107.append(i);
            outline107.append("ms");
            Logging.d(NetworkMonitorAutoDetect.TAG, outline107.toString());
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Network ");
            outline107.append(network.toString());
            outline107.append(" is disconnected");
            Logging.d(NetworkMonitorAutoDetect.TAG, outline107.toString());
            NetworkMonitorAutoDetect.this.observer.onNetworkDisconnect(NetworkMonitorAutoDetect.networkToNetId(network));
        }
    }

    /* loaded from: classes5.dex */
    static class WifiDirectManagerDelegate extends BroadcastReceiver {
        private static final int WIFI_P2P_NETWORK_HANDLE = 0;
        private final Context context;
        private final Observer observer;
        private NetworkInformation wifiP2pNetworkInfo = null;

        WifiDirectManagerDelegate(Observer observer, Context context) {
            this.context = context;
            this.observer = observer;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
            context.registerReceiver(this, intentFilter);
        }

        private void onWifiP2pGroupChange(WifiP2pGroup wifiP2pGroup) {
            if (wifiP2pGroup == null || wifiP2pGroup.getInterface() == null) {
                return;
            }
            try {
                ArrayList list = Collections.list(NetworkInterface.getByName(wifiP2pGroup.getInterface()).getInetAddresses());
                IPAddress[] iPAddressArr = new IPAddress[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    iPAddressArr[i] = new IPAddress(((InetAddress) list.get(i)).getAddress());
                }
                this.wifiP2pNetworkInfo = new NetworkInformation(wifiP2pGroup.getInterface(), ConnectionType.CONNECTION_WIFI, 0L, iPAddressArr, true);
                this.observer.onNetworkConnect(this.wifiP2pNetworkInfo);
            } catch (SocketException e) {
                Logging.e(NetworkMonitorAutoDetect.TAG, "Unable to get WifiP2p network interface", e);
            }
        }

        private void onWifiP2pStateChange(int i) {
            if (i == 1) {
                this.wifiP2pNetworkInfo = null;
                this.observer.onNetworkDisconnect(0L);
            }
        }

        public List<NetworkInformation> getActiveNetworkList() {
            NetworkInformation networkInformation = this.wifiP2pNetworkInfo;
            if (networkInformation != null) {
                return Collections.singletonList(networkInformation);
            }
            return Collections.emptyList();
        }

        @Override // android.content.BroadcastReceiver
        @SuppressLint({"InlinedApi"})
        public void onReceive(Context context, Intent intent) {
            if ("android.net.wifi.p2p.CONNECTION_STATE_CHANGE".equals(intent.getAction())) {
                onWifiP2pGroupChange((WifiP2pGroup) intent.getParcelableExtra("p2pGroupInfo"));
            } else if (!"android.net.wifi.p2p.STATE_CHANGED".equals(intent.getAction())) {
            } else {
                onWifiP2pStateChange(intent.getIntExtra("wifi_p2p_state", 0));
            }
        }

        public void release() {
            this.context.unregisterReceiver(this);
        }
    }

    @SuppressLint({"NewApi"})
    public NetworkMonitorAutoDetect(Observer observer, Context context) {
        this.observer = observer;
        this.context = context;
        this.connectivityManagerDelegate = new ConnectivityManagerDelegate(context);
        this.wifiManagerDelegate = new WifiManagerDelegate(context);
        NetworkState networkState = this.connectivityManagerDelegate.getNetworkState();
        this.connectionType = getConnectionType(networkState);
        this.wifiSSID = getWifiSSID(networkState);
        this.intentFilter = new IntentFilter(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION);
        if (PeerConnectionFactory.fieldTrialsFindFullName("IncludeWifiDirect").equals("Enabled")) {
            this.wifiDirectManagerDelegate = new WifiDirectManagerDelegate(observer, context);
        }
        registerReceiver();
        if (this.connectivityManagerDelegate.supportNetworkCallback()) {
            ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback();
            try {
                this.connectivityManagerDelegate.requestMobileNetwork(networkCallback);
            } catch (SecurityException unused) {
                Logging.w(TAG, "Unable to obtain permission to request a cellular network.");
                networkCallback = null;
            }
            this.mobileNetworkCallback = networkCallback;
            this.allNetworkCallback = new SimpleNetworkCallback();
            this.connectivityManagerDelegate.registerNetworkCallback(this.allNetworkCallback);
            return;
        }
        this.mobileNetworkCallback = null;
        this.allNetworkCallback = null;
    }

    private void connectionTypeChanged(NetworkState networkState) {
        ConnectionType connectionType = getConnectionType(networkState);
        String wifiSSID = getWifiSSID(networkState);
        if (connectionType != this.connectionType || !wifiSSID.equals(this.wifiSSID)) {
            this.connectionType = connectionType;
            this.wifiSSID = wifiSSID;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Network connectivity changed, type is: ");
            outline107.append(this.connectionType);
            Logging.d(TAG, outline107.toString());
            this.observer.onConnectionTypeChanged(connectionType);
        }
    }

    public static ConnectionType getConnectionType(NetworkState networkState) {
        if (!networkState.isConnected()) {
            return ConnectionType.CONNECTION_NONE;
        }
        int networkType = networkState.getNetworkType();
        if (networkType == 0) {
            switch (networkState.getNetworkSubType()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return ConnectionType.CONNECTION_2G;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return ConnectionType.CONNECTION_3G;
                case 13:
                    return ConnectionType.CONNECTION_4G;
                default:
                    return ConnectionType.CONNECTION_UNKNOWN_CELLULAR;
            }
        } else if (networkType == 1) {
            return ConnectionType.CONNECTION_WIFI;
        } else {
            if (networkType == 6) {
                return ConnectionType.CONNECTION_4G;
            }
            if (networkType == 7) {
                return ConnectionType.CONNECTION_BLUETOOTH;
            }
            if (networkType != 9) {
                return ConnectionType.CONNECTION_UNKNOWN;
            }
            return ConnectionType.CONNECTION_ETHERNET;
        }
    }

    private String getWifiSSID(NetworkState networkState) {
        return getConnectionType(networkState) != ConnectionType.CONNECTION_WIFI ? "" : this.wifiManagerDelegate.getWifiSSID();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public static long networkToNetId(Network network) {
        int i = Build.VERSION.SDK_INT;
        return network.getNetworkHandle();
    }

    private void registerReceiver() {
        if (this.isRegistered) {
            return;
        }
        this.isRegistered = true;
        this.context.registerReceiver(this, this.intentFilter);
    }

    private void unregisterReceiver() {
        if (!this.isRegistered) {
            return;
        }
        this.isRegistered = false;
        this.context.unregisterReceiver(this);
    }

    public void destroy() {
        ConnectivityManager.NetworkCallback networkCallback = this.allNetworkCallback;
        if (networkCallback != null) {
            this.connectivityManagerDelegate.releaseCallback(networkCallback);
        }
        ConnectivityManager.NetworkCallback networkCallback2 = this.mobileNetworkCallback;
        if (networkCallback2 != null) {
            this.connectivityManagerDelegate.releaseCallback(networkCallback2);
        }
        WifiDirectManagerDelegate wifiDirectManagerDelegate = this.wifiDirectManagerDelegate;
        if (wifiDirectManagerDelegate != null) {
            wifiDirectManagerDelegate.release();
        }
        unregisterReceiver();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<NetworkInformation> getActiveNetworkList() {
        List<NetworkInformation> activeNetworkList = this.connectivityManagerDelegate.getActiveNetworkList();
        if (activeNetworkList == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(activeNetworkList);
        WifiDirectManagerDelegate wifiDirectManagerDelegate = this.wifiDirectManagerDelegate;
        if (wifiDirectManagerDelegate != null) {
            arrayList.addAll(wifiDirectManagerDelegate.getActiveNetworkList());
        }
        return arrayList;
    }

    public NetworkState getCurrentNetworkState() {
        return this.connectivityManagerDelegate.getNetworkState();
    }

    public long getDefaultNetId() {
        return this.connectivityManagerDelegate.getDefaultNetId();
    }

    boolean isReceiverRegisteredForTesting() {
        return this.isRegistered;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        NetworkState currentNetworkState = getCurrentNetworkState();
        if (BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            connectionTypeChanged(currentNetworkState);
        }
    }

    void setConnectivityManagerDelegateForTests(ConnectivityManagerDelegate connectivityManagerDelegate) {
        this.connectivityManagerDelegate = connectivityManagerDelegate;
    }

    void setWifiManagerDelegateForTests(WifiManagerDelegate wifiManagerDelegate) {
        this.wifiManagerDelegate = wifiManagerDelegate;
    }

    public boolean supportNetworkCallback() {
        return this.connectivityManagerDelegate.supportNetworkCallback();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class ConnectivityManagerDelegate {
        private final ConnectivityManager connectivityManager;

        ConnectivityManagerDelegate(Context context) {
            this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        }

        /* JADX INFO: Access modifiers changed from: private */
        @SuppressLint({"NewApi"})
        public NetworkInformation networkToInfo(Network network) {
            LinkProperties linkProperties = this.connectivityManager.getLinkProperties(network);
            if (linkProperties == null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Detected unknown network: ");
                outline107.append(network.toString());
                Logging.w(NetworkMonitorAutoDetect.TAG, outline107.toString());
                return null;
            } else if (linkProperties.getInterfaceName() == null) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Null interface name for network ");
                outline1072.append(network.toString());
                Logging.w(NetworkMonitorAutoDetect.TAG, outline1072.toString());
                return null;
            } else {
                NetworkState networkState = getNetworkState(network);
                if (networkState.connected && networkState.getNetworkType() == 17) {
                    networkState = getNetworkState();
                }
                ConnectionType connectionType = NetworkMonitorAutoDetect.getConnectionType(networkState);
                if (connectionType == ConnectionType.CONNECTION_NONE) {
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Network ");
                    outline1073.append(network.toString());
                    outline1073.append(" is disconnected");
                    Logging.d(NetworkMonitorAutoDetect.TAG, outline1073.toString());
                    return null;
                }
                if (connectionType == ConnectionType.CONNECTION_UNKNOWN || connectionType == ConnectionType.CONNECTION_UNKNOWN_CELLULAR) {
                    StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Network ");
                    outline1074.append(network.toString());
                    outline1074.append(" connection type is ");
                    outline1074.append(connectionType);
                    outline1074.append(" because it has type ");
                    outline1074.append(networkState.getNetworkType());
                    outline1074.append(" and subtype ");
                    outline1074.append(networkState.getNetworkSubType());
                    Logging.d(NetworkMonitorAutoDetect.TAG, outline1074.toString());
                }
                return new NetworkInformation(linkProperties.getInterfaceName(), connectionType, NetworkMonitorAutoDetect.networkToNetId(network), getIPAddresses(linkProperties), hasInternetCapability(network));
            }
        }

        List<NetworkInformation> getActiveNetworkList() {
            if (!supportNetworkCallback()) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Network network : getAllNetworks()) {
                NetworkInformation networkToInfo = networkToInfo(network);
                if (networkToInfo != null) {
                    arrayList.add(networkToInfo);
                }
            }
            return arrayList;
        }

        @SuppressLint({"NewApi"})
        Network[] getAllNetworks() {
            ConnectivityManager connectivityManager = this.connectivityManager;
            return connectivityManager == null ? new Network[0] : connectivityManager.getAllNetworks();
        }

        @SuppressLint({"NewApi"})
        long getDefaultNetId() {
            NetworkInfo activeNetworkInfo;
            Network[] allNetworks;
            NetworkInfo networkInfo;
            if (supportNetworkCallback() && (activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo()) != null) {
                long j = -1;
                for (Network network : getAllNetworks()) {
                    if (hasInternetCapability(network) && (networkInfo = this.connectivityManager.getNetworkInfo(network)) != null && networkInfo.getType() == activeNetworkInfo.getType()) {
                        if (j == -1) {
                            j = NetworkMonitorAutoDetect.networkToNetId(network);
                        } else {
                            throw new RuntimeException("Multiple connected networks of same type are not supported.");
                        }
                    }
                }
                return j;
            }
            return -1L;
        }

        @SuppressLint({"NewApi"})
        IPAddress[] getIPAddresses(LinkProperties linkProperties) {
            IPAddress[] iPAddressArr = new IPAddress[linkProperties.getLinkAddresses().size()];
            int i = 0;
            for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
                iPAddressArr[i] = new IPAddress(linkAddress.getAddress().getAddress());
                i++;
            }
            return iPAddressArr;
        }

        NetworkState getNetworkState() {
            ConnectivityManager connectivityManager = this.connectivityManager;
            if (connectivityManager == null) {
                return new NetworkState(false, -1, -1);
            }
            return getNetworkState(connectivityManager.getActiveNetworkInfo());
        }

        @SuppressLint({"NewApi"})
        boolean hasInternetCapability(Network network) {
            NetworkCapabilities networkCapabilities;
            ConnectivityManager connectivityManager = this.connectivityManager;
            return (connectivityManager == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(network)) == null || !networkCapabilities.hasCapability(12)) ? false : true;
        }

        @SuppressLint({"NewApi"})
        public void registerNetworkCallback(ConnectivityManager.NetworkCallback networkCallback) {
            this.connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addCapability(12).build(), networkCallback);
        }

        @SuppressLint({"NewApi"})
        public void releaseCallback(ConnectivityManager.NetworkCallback networkCallback) {
            if (supportNetworkCallback()) {
                Logging.d(NetworkMonitorAutoDetect.TAG, "Unregister network callback");
                this.connectivityManager.unregisterNetworkCallback(networkCallback);
            }
        }

        @SuppressLint({"NewApi"})
        public void requestMobileNetwork(ConnectivityManager.NetworkCallback networkCallback) {
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            builder.addCapability(12).addTransportType(0);
            this.connectivityManager.requestNetwork(builder.build(), networkCallback);
        }

        public boolean supportNetworkCallback() {
            int i = Build.VERSION.SDK_INT;
            return this.connectivityManager != null;
        }

        ConnectivityManagerDelegate() {
            this.connectivityManager = null;
        }

        @SuppressLint({"NewApi"})
        NetworkState getNetworkState(Network network) {
            ConnectivityManager connectivityManager = this.connectivityManager;
            if (connectivityManager == null) {
                return new NetworkState(false, -1, -1);
            }
            return getNetworkState(connectivityManager.getNetworkInfo(network));
        }

        NetworkState getNetworkState(NetworkInfo networkInfo) {
            if (networkInfo != null && networkInfo.isConnected()) {
                return new NetworkState(true, networkInfo.getType(), networkInfo.getSubtype());
            }
            return new NetworkState(false, -1, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class WifiManagerDelegate {
        private final Context context;

        WifiManagerDelegate(Context context) {
            this.context = context;
        }

        String getWifiSSID() {
            WifiInfo wifiInfo;
            String ssid;
            Intent registerReceiver = this.context.registerReceiver(null, new IntentFilter("android.net.wifi.STATE_CHANGE"));
            return (registerReceiver == null || (wifiInfo = (WifiInfo) registerReceiver.getParcelableExtra("wifiInfo")) == null || (ssid = wifiInfo.getSSID()) == null) ? "" : ssid;
        }

        WifiManagerDelegate() {
            this.context = null;
        }
    }
}
