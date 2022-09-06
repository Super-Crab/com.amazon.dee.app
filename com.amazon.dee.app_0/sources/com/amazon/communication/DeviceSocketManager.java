package com.amazon.communication;

import amazon.communication.authentication.RequestSigner;
import amazon.communication.connection.Purpose;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IdentityResolver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.http.AndroidHttpClient;
import android.telephony.TelephonyManager;
import com.amazon.alexa.mode.debug.EmulateConnection;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.communication.PowerManagerWrapper;
import com.amazon.communication.authentication.MapAccountManagerWrapper;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.communication.socket.DeviceDirectBiDiSocket;
import com.amazon.communication.socket.ProtocolSocket;
import com.amazon.communication.socket.RemoteDeviceGatewaySocket;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
import com.amazon.communication.socket.SocketManagerBase;
import com.amazon.communication.socket.SocketUsageWriter;
import com.amazon.communication.websocket.CloseDetail;
import com.amazon.communication.websocket.CloseStatusCodes;
import com.amazon.communication.wifi.WifiManagerWrapper;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.IOException;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.net.ssl.HostnameVerifier;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class DeviceSocketManager extends SocketManagerBase {
    private static final String METRICS_SOURCE_NAME = "DeviceSocketManager";
    private final MapAccountManagerWrapper mAccountManager;
    private final BroadcastReceiver mAccountsUpdatedReceiver;
    private final BandwidthToolByteAccountant mByteAccountant;
    private final ConnectivityMonitor mConnectivityMonitor;
    private final AndroidHttpClient mHttpClient;
    private final Set<ProtocolSocket.ProtocolSocketStateListener> mListenerSet;
    private final int mMessageQueueSize;
    private final MetricEvent mMetricEvent;
    private final PackageInfo mPackageInfo;
    private final PeriodicMetricReporter mPeriodicMetricReporter;
    private final PowerManagerWrapper mPowerManager;
    private final RequestSigner mRequestSigner;
    private final ResponseRouter mResponseRouter;
    private final SocketUsageWriter mSocketUsageWriter;
    private final TelephonyManager mTelephonyManager;
    private final WifiManagerWrapper mWifiManager;
    private final WorkExecutor mWorkExecutor;
    private static final String WAKE_LOCK_TAG = "TComm.DeviceSocketManager";
    private static final DPLogger log = new DPLogger(WAKE_LOCK_TAG);
    protected static final CloseDetail ACCOUNT_CHANGE_DETAIL = new CloseDetail(CloseStatusCodes.ACCOUNT_CHANGE, "Amazon Account status change");

    /* loaded from: classes12.dex */
    private class AccountsUpdatedReceiver extends BroadcastReceiver {
        private AccountsUpdatedReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeviceSocketManager.log.verbose("onReceive", "accounts updated", MAPAccountManager.KEY_INTENT, intent);
            if (!DeviceSocketManager.isPrimaryAccountAddedOrRemovedIntent(intent) || DeviceSocketManager.this.mAccountManager.getAccount() != null) {
                return;
            }
            DeviceSocketManager.log.info("onReceive", "the Amazon account was de-registered - closing all active protocol sockets", new Object[0]);
            DeviceSocketManager.this.closeAllActiveProtocolSockets(DeviceSocketManager.ACCOUNT_CHANGE_DETAIL);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeviceSocketManager(Context context, WorkExecutor workExecutor, ProtocolHandlerManager protocolHandlerManager, ResponseRouter responseRouter, RequestSigner requestSigner, IdentityResolver identityResolver, PeriodicMetricReporter periodicMetricReporter, SocketUsageWriter socketUsageWriter, SelectorProvider selectorProvider, MapAccountManagerWrapper mapAccountManagerWrapper, ConnectivityMonitor connectivityMonitor, PowerManagerWrapper powerManagerWrapper, WifiManagerWrapper wifiManagerWrapper, int i, HostnameVerifier hostnameVerifier, BandwidthToolByteAccountant bandwidthToolByteAccountant) throws IOException {
        super(workExecutor, protocolHandlerManager, identityResolver, selectorProvider, hostnameVerifier);
        PackageInfo packageInfo;
        this.mListenerSet = new CopyOnWriteArraySet();
        this.mHttpClient = AndroidHttpClient.newInstance((String) null);
        this.mWorkExecutor = workExecutor;
        this.mRequestSigner = requestSigner;
        this.mResponseRouter = responseRouter;
        this.mPeriodicMetricReporter = periodicMetricReporter;
        this.mMetricEvent = this.mPeriodicMetricReporter.createTrackedMetricEvent(TCommMetrics.PROGRAM_ID, METRICS_SOURCE_NAME);
        this.mSocketUsageWriter = socketUsageWriter;
        this.mAccountManager = mapAccountManagerWrapper;
        this.mConnectivityMonitor = connectivityMonitor;
        this.mPowerManager = powerManagerWrapper;
        this.mWifiManager = wifiManagerWrapper;
        this.mMessageQueueSize = i;
        this.mByteAccountant = bandwidthToolByteAccountant;
        this.mAccountsUpdatedReceiver = new AccountsUpdatedReceiver();
        this.mTelephonyManager = (TelephonyManager) context.getSystemService("phone");
        try {
            try {
                packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                if (packageInfo == null) {
                    packageInfo = new PackageInfo();
                }
            } catch (PackageManager.NameNotFoundException e) {
                log.warn(METRICS_SOURCE_NAME, "Could not get TComm package name! Will use blank PackageInfo", e);
                packageInfo = new PackageInfo();
            }
            this.mPackageInfo = packageInfo;
        } catch (Throwable th) {
            this.mPackageInfo = new PackageInfo();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeAllActiveProtocolSockets(CloseDetail closeDetail) {
        for (ProtocolSocket protocolSocket : this.mActiveProtocolSockets.getAllSockets()) {
            log.info("closeAllActiveProtocolSockets", "removing socket state listeners from socket and closing it", "socket", protocolSocket);
            this.mActiveProtocolSockets.removeSocket(protocolSocket);
            protocolSocket.removeStateListener(this);
            protocolSocket.close(closeDetail);
        }
    }

    private ProtocolSocket createDirectBiDiSocket(EndpointIdentity endpointIdentity, Set<ProtocolSocket.ProtocolSocketAttribute> set, Purpose purpose, ConnectReason connectReason, String str) {
        log.verbose("createDirectBiDiSocket", "Create BiDi protocol socket", "destination", EndpointIdentity.logSafe(endpointIdentity), "attributes", set);
        WorkExecutor workExecutor = this.mWorkExecutor;
        ProtocolHandlerManager protocolHandlerManager = this.mProtocolHandlerManager;
        DeviceDirectBiDiSocket deviceDirectBiDiSocket = new DeviceDirectBiDiSocket(endpointIdentity, workExecutor, protocolHandlerManager, protocolHandlerManager.getAllProtocolHandlerNames(), this.mRequestSigner, this.mAccountManager, this, this.mConnectivityMonitor, this.mTelephonyManager, this.mWifiManager, this.mPackageInfo, this.mIdentityResolver, this.mPeriodicMetricReporter, this.mSocketUsageWriter, this.mSelectorProvider, set, this.mMessageQueueSize, this.mHostnameVerifier, this.mSslContext, str, this.mPowerManager, purpose);
        deviceDirectBiDiSocket.setConnectReason(connectReason);
        return deviceDirectBiDiSocket;
    }

    private RawHttpSocket createRawHttpSocket(EndpointIdentity endpointIdentity, Set<ProtocolSocket.ProtocolSocketAttribute> set, String str) {
        log.verbose("createRawHttpSocket", "Create Raw Http socket", "destination", EndpointIdentity.logSafe(endpointIdentity));
        return new RawHttpSocket(set, this.mIdentityResolver, endpointIdentity, this.mRequestSigner, this.mWorkExecutor, this.mHttpClient, this.mResponseRouter, this.mSocketUsageWriter, this.mPeriodicMetricReporter, this.mByteAccountant, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPrimaryAccountAddedOrRemovedIntent(Intent intent) {
        if (intent == null) {
            return false;
        }
        return "com.amazon.dcp.sso.action.account.added".equals(intent.getAction()) || "com.amazon.dcp.sso.action.account.removed".equals(intent.getAction());
    }

    public void addSocketStateListener(ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener) {
        log.verbose("addSocketStateListener", "adding listener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, protocolSocketStateListener.toString());
        this.mListenerSet.add(protocolSocketStateListener);
    }

    @Override // com.amazon.communication.socket.SocketManagerBase, com.amazon.communication.socket.SocketManager
    public void connect(ProtocolSocket protocolSocket) throws SocketAcquisitionFailedException {
        log.debug(EmulateConnection.EXTRA_CONNECT, "Connecting socket", "socket", protocolSocket);
        if (!(protocolSocket instanceof RawHttpSocket) && !(protocolSocket instanceof RemoteDeviceGatewaySocket)) {
            ensureWakeLockDuringConnect(protocolSocket, 10000L);
            super.connect(protocolSocket);
            return;
        }
        this.mActiveProtocolSockets.addSocket(protocolSocket.getUniqueEndpointIdentifier(), protocolSocket);
        log.debug(EmulateConnection.EXTRA_CONNECT, "added socket to active list", "identifier", protocolSocket.getUniqueEndpointIdentifier());
        protocolSocket.addStateListener(this);
    }

    @Override // com.amazon.communication.socket.SocketManager
    public ProtocolSocket createProtocolSocket(EndpointIdentity endpointIdentity, Set<ProtocolSocket.ProtocolSocketAttribute> set, ConnectReason connectReason, String str) {
        throw new UnsupportedOperationException("Cannot create a protocol socket without Purpose");
    }

    void ensureWakeLockDuringConnect(final ProtocolSocket protocolSocket, long j) {
        final PowerManagerWrapper.WakeLock newWakeLock = this.mPowerManager.newWakeLock(1, WAKE_LOCK_TAG);
        newWakeLock.acquire(j);
        try {
            log.info("ensureWakeLockDuringConnect", "acquired wakelock till socket transitions from connecting state", "connectingSocket", protocolSocket, "wakeLock", newWakeLock, "WAKE_LOCK_TAG", WAKE_LOCK_TAG);
            protocolSocket.addStateListener(new ProtocolSocket.ProtocolSocketStateListener() { // from class: com.amazon.communication.DeviceSocketManager.1
                @Override // com.amazon.communication.socket.ProtocolSocket.ProtocolSocketStateListener
                public void notifyStateChanged(ProtocolSocket protocolSocket2) {
                    DeviceSocketManager.log.debug("ensureWakeLockDuringConnect.notifyStateChanged", "got notification about socket state change", "notifiedSocket", protocolSocket2);
                    if (protocolSocket2 != protocolSocket || ProtocolSocket.ProtocolSocketState.CONNECTING == protocolSocket2.socketState()) {
                        return;
                    }
                    synchronized (newWakeLock) {
                        if (newWakeLock.isHeld()) {
                            DeviceSocketManager.log.info("ensureWakeLockDuringConnect.notifyStateChanged", "releasing wake lock as socket is no longer connecting", "notifiedSocket.socketState()", protocolSocket2.socketState(), "wakeLock", newWakeLock, "WAKE_LOCK_TAG", DeviceSocketManager.WAKE_LOCK_TAG);
                            newWakeLock.release();
                        }
                    }
                }
            });
        } catch (Throwable th) {
            synchronized (newWakeLock) {
                if (newWakeLock.isHeld()) {
                    log.warn("ensureWakeLockDuringConnect", "wakelock being released in finally block due to unexpected exception", "wakeLock", newWakeLock, "WAKE_LOCK_TAG", WAKE_LOCK_TAG);
                    newWakeLock.release();
                }
                throw th;
            }
        }
    }

    public BroadcastReceiver getAccountsUpdatedReceiver() {
        return this.mAccountsUpdatedReceiver;
    }

    public void removeSocketStateListener(ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener) {
        log.verbose("removeSocketStateListener", "Removing listener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, protocolSocketStateListener.toString());
        this.mListenerSet.remove(protocolSocketStateListener);
    }

    @Override // com.amazon.communication.socket.SocketManagerBase
    public void shutdown() {
        this.mHttpClient.close();
        super.shutdown();
    }

    @Override // com.amazon.communication.socket.SocketManagerBase, com.amazon.communication.socket.SocketManager
    public ProtocolSocket createProtocolSocket(EndpointIdentity endpointIdentity, Set<ProtocolSocket.ProtocolSocketAttribute> set, Purpose purpose, ConnectReason connectReason, String str) {
        ProtocolSocket createDirectBiDiSocket;
        log.verbose("createProtocolSocket", "Create a new ProtocolSocket", "destination", EndpointIdentity.logSafe(endpointIdentity), "attributes", set, "purpose", purpose);
        if (set == null) {
            set = ProtocolSocket.ProtocolSocketAttribute.EMPTY_ATTRIBUTES;
        }
        Set<ProtocolSocket.ProtocolSocketAttribute> set2 = set;
        if (set2.contains(ProtocolSocket.ProtocolSocketAttribute.REQUEST_RESPONSE_ONLY)) {
            createDirectBiDiSocket = createRawHttpSocket(endpointIdentity, set2, str);
        } else {
            createDirectBiDiSocket = createDirectBiDiSocket(endpointIdentity, set2, purpose, connectReason, str);
        }
        for (ProtocolSocket.ProtocolSocketStateListener protocolSocketStateListener : this.mListenerSet) {
            createDirectBiDiSocket.addStateListener(protocolSocketStateListener);
        }
        return createDirectBiDiSocket;
    }
}
