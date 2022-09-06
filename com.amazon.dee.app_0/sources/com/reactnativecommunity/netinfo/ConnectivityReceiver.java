package com.reactnativecommunity.netinfo;

import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.reactnativecommunity.netinfo.types.CellularGeneration;
import com.reactnativecommunity.netinfo.types.ConnectionType;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public abstract class ConnectivityReceiver {
    private final ConnectivityManager mConnectivityManager;
    private Boolean mIsInternetReachableOverride;
    private final ReactApplicationContext mReactContext;
    private final TelephonyManager mTelephonyManager;
    private final WifiManager mWifiManager;
    public boolean hasListener = false;
    @Nonnull
    private ConnectionType mConnectionType = ConnectionType.UNKNOWN;
    @Nullable
    private CellularGeneration mCellularGeneration = null;
    private boolean mIsInternetReachable = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectivityReceiver(ReactApplicationContext reactApplicationContext) {
        this.mReactContext = reactApplicationContext;
        this.mConnectivityManager = (ConnectivityManager) reactApplicationContext.getSystemService("connectivity");
        this.mWifiManager = (WifiManager) reactApplicationContext.getApplicationContext().getSystemService("wifi");
        this.mTelephonyManager = (TelephonyManager) reactApplicationContext.getSystemService("phone");
    }

    private WritableMap createDetailsMap(@Nonnull String str) {
        boolean z;
        WifiInfo connectionInfo;
        WritableMap createMap = Arguments.createMap();
        int hashCode = str.hashCode();
        if (hashCode != -916596374) {
            if (hashCode == 3649301 && str.equals("wifi")) {
                z = true;
            }
            z = true;
        } else {
            if (str.equals("cellular")) {
                z = false;
            }
            z = true;
        }
        if (z) {
            if (z && NetInfoUtils.isAccessWifiStatePermissionGranted(getReactContext()) && (connectionInfo = this.mWifiManager.getConnectionInfo()) != null) {
                try {
                    String ssid = connectionInfo.getSSID();
                    if (ssid != null && !ssid.contains("<unknown ssid>")) {
                        createMap.putString("ssid", ssid.replace(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, ""));
                    }
                } catch (Exception unused) {
                }
                try {
                    String bssid = connectionInfo.getBSSID();
                    if (bssid != null) {
                        createMap.putString("bssid", bssid);
                    }
                } catch (Exception unused2) {
                }
                try {
                    createMap.putInt("strength", WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 100));
                } catch (Exception unused3) {
                }
                try {
                    int i = Build.VERSION.SDK_INT;
                    createMap.putInt(EventBusConstants.FREQUENCY_KEY, connectionInfo.getFrequency());
                } catch (Exception unused4) {
                }
                try {
                    byte[] byteArray = BigInteger.valueOf(connectionInfo.getIpAddress()).toByteArray();
                    NetInfoUtils.reverseByteArray(byteArray);
                    createMap.putString("ipAddress", InetAddress.getByAddress(byteArray).getHostAddress());
                } catch (Exception unused5) {
                }
                try {
                    byte[] byteArray2 = BigInteger.valueOf(connectionInfo.getIpAddress()).toByteArray();
                    NetInfoUtils.reverseByteArray(byteArray2);
                    int networkPrefixLength = (-1) << (32 - NetworkInterface.getByInetAddress(InetAddress.getByAddress(byteArray2)).getInterfaceAddresses().get(1).getNetworkPrefixLength());
                    createMap.putString("subnet", String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf((networkPrefixLength >> 24) & 255), Integer.valueOf((networkPrefixLength >> 16) & 255), Integer.valueOf((networkPrefixLength >> 8) & 255), Integer.valueOf(networkPrefixLength & 255)));
                } catch (Exception unused6) {
                }
            }
        } else {
            CellularGeneration cellularGeneration = this.mCellularGeneration;
            if (cellularGeneration != null) {
                createMap.putString("cellularGeneration", cellularGeneration.label);
            }
            String networkOperatorName = this.mTelephonyManager.getNetworkOperatorName();
            if (networkOperatorName != null) {
                createMap.putString(AMPDInformationProvider.DEVICE_CARRIER_KEY, networkOperatorName);
            }
        }
        return createMap;
    }

    public void clearIsInternetReachableOverride() {
        this.mIsInternetReachableOverride = null;
    }

    protected WritableMap createConnectivityEventMap(@Nullable String str) {
        WritableMap createMap = Arguments.createMap();
        if (NetInfoUtils.isAccessWifiStatePermissionGranted(getReactContext())) {
            createMap.putBoolean("isWifiEnabled", this.mWifiManager.isWifiEnabled());
        }
        createMap.putString("type", str != null ? str : this.mConnectionType.label);
        boolean z = false;
        boolean z2 = true;
        boolean z3 = !this.mConnectionType.equals(ConnectionType.NONE) && !this.mConnectionType.equals(ConnectionType.UNKNOWN);
        createMap.putBoolean("isConnected", z3);
        if (this.mIsInternetReachable && (str == null || str.equals(this.mConnectionType.label))) {
            z = true;
        }
        createMap.putBoolean("isInternetReachable", z);
        if (str == null) {
            str = this.mConnectionType.label;
        }
        WritableMap createDetailsMap = createDetailsMap(str);
        if (z3) {
            if (getConnectivityManager() != null) {
                z2 = getConnectivityManager().isActiveNetworkMetered();
            }
            createDetailsMap.putBoolean("isConnectionExpensive", z2);
        }
        createMap.putMap("details", createDetailsMap);
        return createMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectivityManager getConnectivityManager() {
        return this.mConnectivityManager;
    }

    public void getCurrentState(@Nullable String str, Promise promise) {
        promise.resolve(createConnectivityEventMap(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReactApplicationContext getReactContext() {
        return this.mReactContext;
    }

    public abstract void register();

    protected void sendConnectivityChangedEvent() {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("netInfo.networkStatusDidChange", createConnectivityEventMap(null));
    }

    public void setIsInternetReachableOverride(boolean z) {
        this.mIsInternetReachableOverride = Boolean.valueOf(z);
        updateConnectivity(this.mConnectionType, this.mCellularGeneration, this.mIsInternetReachable);
    }

    public abstract void unregister();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateConnectivity(@Nonnull ConnectionType connectionType, @Nullable CellularGeneration cellularGeneration, boolean z) {
        Boolean bool = this.mIsInternetReachableOverride;
        if (bool != null) {
            z = bool.booleanValue();
        }
        boolean z2 = true;
        boolean z3 = connectionType != this.mConnectionType;
        boolean z4 = cellularGeneration != this.mCellularGeneration;
        if (z == this.mIsInternetReachable) {
            z2 = false;
        }
        if (z3 || z4 || z2) {
            this.mConnectionType = connectionType;
            this.mCellularGeneration = cellularGeneration;
            this.mIsInternetReachable = z;
            if (!this.hasListener) {
                return;
            }
            sendConnectivityChangedEvent();
        }
    }
}
