package com.amazon.communication.socket;

import amazon.communication.authentication.AccountRequestContext;
import amazon.communication.authentication.RequestContext;
import amazon.communication.authentication.RequestSigner;
import amazon.communication.connection.Purpose;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IdentityResolver;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.ConnectivityMonitor;
import com.amazon.communication.NetworkType;
import com.amazon.communication.PowerManagerWrapper;
import com.amazon.communication.ProtocolHandlerManager;
import com.amazon.communication.TCommMetrics;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.authentication.MapAccountManagerWrapper;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.wifi.WifiManagerWrapper;
import com.amazon.dp.utils.Obfuscator;
import com.dp.framework.HexStreamCodec;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.spi.SelectorProvider;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
/* loaded from: classes12.dex */
public class DeviceDirectBiDiSocket extends DirectBiDiSocket {
    private static final int DEFAULT_DELAY_CLOSE_TIME_IN_MILLIS = 5000;
    private static final String METRICS_SOURCE_NAME = "DeviceDirectBiDiSocket";
    private String mDirectedId;
    private MetricEvent mMetricEvent;
    private final TelephonyManager mTelephonyManager;
    private final WifiManagerWrapper mWifiManager;

    public DeviceDirectBiDiSocket(EndpointIdentity endpointIdentity, WorkExecutor workExecutor, ProtocolHandlerManager protocolHandlerManager, Set<String> set, RequestSigner requestSigner, MapAccountManagerWrapper mapAccountManagerWrapper, SelectionKeyChangeQueue selectionKeyChangeQueue, ConnectivityMonitor connectivityMonitor, TelephonyManager telephonyManager, WifiManagerWrapper wifiManagerWrapper, PackageInfo packageInfo, IdentityResolver identityResolver, PeriodicMetricReporter periodicMetricReporter, SocketUsageWriter socketUsageWriter, SelectorProvider selectorProvider, Set<ProtocolSocket.ProtocolSocketAttribute> set2, int i, HostnameVerifier hostnameVerifier, SSLContext sSLContext, String str, PowerManagerWrapper powerManagerWrapper, Purpose purpose) {
        super(endpointIdentity, workExecutor, protocolHandlerManager, set, new HexStreamCodec(), requestSigner, selectionKeyChangeQueue, identityResolver, socketUsageWriter, selectorProvider, set2, i, 5000, hostnameVerifier, sSLContext, false, powerManagerWrapper, purpose);
        this.mTelephonyManager = telephonyManager;
        this.mWifiManager = wifiManagerWrapper;
        this.mDirectedId = str;
        this.mMetricEvent = periodicMetricReporter.createTrackedMetricEvent(TCommMetrics.PROGRAM_ID, METRICS_SOURCE_NAME);
        removeSupportedAttributes(getAttributesToFilter(mapAccountManagerWrapper));
        setEnvironmentProperties(connectivityMonitor, packageInfo);
    }

    private Set<ProtocolSocket.ProtocolSocketAttribute> getAttributesToFilter(MapAccountManagerWrapper mapAccountManagerWrapper) {
        if (mapAccountManagerWrapper.getAccount() != null) {
            return EnumSet.of(ProtocolSocket.ProtocolSocketAttribute.ANONYMOUS);
        }
        return Collections.emptySet();
    }

    private void setEnvironmentProperties(ConnectivityMonitor connectivityMonitor, PackageInfo packageInfo) {
        if (connectivityMonitor.isEthernetAvailable()) {
            setEnvironmentProperty(ProtocolSocket.EnvironmentProperty.NETWORK_TYPE, NetworkType.ETHERNET.toString());
        } else if (connectivityMonitor.isWiFiAvailable()) {
            setEnvironmentProperty(ProtocolSocket.EnvironmentProperty.NETWORK_TYPE, NetworkType.WIFI.toString());
            String oui = this.mWifiManager.getOUI();
            if (oui != null) {
                setEnvironmentProperty(ProtocolSocket.EnvironmentProperty.ACCESS_POINT_OUI, oui);
            }
            String bssid = this.mWifiManager.getBssid();
            if (bssid != null) {
                setEnvironmentProperty(ProtocolSocket.EnvironmentProperty.OBFUSCATED_BSSID, Obfuscator.obfuscate(bssid));
            }
        } else if (connectivityMonitor.isMobileAvailable()) {
            setEnvironmentProperty(ProtocolSocket.EnvironmentProperty.NETWORK_TYPE, NetworkType.MOBILE.toString());
            String simOperator = this.mTelephonyManager.getSimOperator();
            String networkOperator = this.mTelephonyManager.getNetworkOperator();
            setEnvironmentProperty(ProtocolSocket.EnvironmentProperty.CARRIER_SIM, simOperator);
            setEnvironmentProperty(ProtocolSocket.EnvironmentProperty.CARRIER_TOWER, networkOperator);
        }
        setEnvironmentProperty(ProtocolSocket.EnvironmentProperty.TCOMM_VERSION_CODE, Integer.toString(packageInfo.versionCode));
        setEnvironmentProperty(ProtocolSocket.EnvironmentProperty.TCOMM_VERSION_NAME, packageInfo.versionName);
        setEnvironmentProperty(ProtocolSocket.EnvironmentProperty.DEVICE_VERSION, Build.FINGERPRINT);
    }

    @Override // com.amazon.communication.socket.DirectBiDiSocket
    protected RequestContext getRequestContext() {
        AccountRequestContext accountRequestContext = isAttributeSupported(ProtocolSocket.ProtocolSocketAttribute.ANONYMOUS) ? AccountRequestContext.EMPTY_ACCOUNT : null;
        String str = this.mDirectedId;
        return str != null ? new AccountRequestContext(str) : accountRequestContext;
    }

    @Override // com.amazon.communication.socket.DirectBiDiSocket
    public int read(ByteBuffer byteBuffer) throws IOException, NotYetConnectedException {
        int read = super.read(byteBuffer);
        this.mMetricEvent.addCounter(TCommMetrics.COUNT_TOTAL_RX_BYTES, read);
        return read;
    }
}
