package com.amazon.communication;

import amazon.communication.CommunicationFactoryBase;
import amazon.communication.DuplicateHandlerException;
import amazon.communication.ICommunicationManager;
import amazon.communication.Message;
import amazon.communication.RegistrationFailedException;
import amazon.communication.connection.Channels;
import amazon.communication.connection.KeepAlive;
import amazon.communication.connection.Policy;
import amazon.communication.connection.Purpose;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.EndpointIdentityFactory;
import amazon.communication.identity.IRServiceEndpoint;
import amazon.communication.identity.IdentityResolver;
import amazon.communication.identity.ServiceIdentity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.android.codahalemetricreporter.StandardMetricReporter;
import com.amazon.client.metrics.common.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.PeriodicMetricReporter;
import com.amazon.client.metrics.common.PeriodicMetricReporterImpl;
import com.amazon.communication.ICommunicationService;
import com.amazon.communication.IConnection;
import com.amazon.communication.IGatewayConnectivity;
import com.amazon.communication.authentication.DcpRequestSigner;
import com.amazon.communication.authentication.MapAccountManagerWrapper;
import com.amazon.communication.authentication.MapAccountManagerWrapperImpl;
import com.amazon.communication.devicetodevice.ChannelAwareD2DMessageRouter;
import com.amazon.communication.devicetodevice.ChannelAwareD2DMessageRouterImpl;
import com.amazon.communication.devicetodevice.D2DNotificationRouter;
import com.amazon.communication.devicetodevice.IntentLaunchingD2DNotificationRouter;
import com.amazon.communication.directorservice.DirectorServiceClient;
import com.amazon.communication.gw.AdpAuthenticationProvider;
import com.amazon.communication.gw.DeviceGatewayApplicationProtocol;
import com.amazon.communication.gw.DeviceGatewayHandshakeHandler;
import com.amazon.communication.gw.GatewayConnectivityListener;
import com.amazon.communication.gw.GatewayControlProtocol;
import com.amazon.communication.gw.GatewayHandshakeProtocol;
import com.amazon.communication.heartbeat.ConnectionHealthStatisticsAggregator;
import com.amazon.communication.heartbeat.ConservativeHeartbeatIntervalDeterminer;
import com.amazon.communication.heartbeat.HeartbeatBroadcastReceiver;
import com.amazon.communication.heartbeat.HeartbeatConnectionHealthManager;
import com.amazon.communication.heartbeat.HeartbeatControlApplicationProtocol;
import com.amazon.communication.heartbeat.HeartbeatControlMessageHandler;
import com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer;
import com.amazon.communication.heartbeat.HeartbeatNotificationScheduler;
import com.amazon.communication.heartbeat.HeartbeatSettings;
import com.amazon.communication.heartbeat.NetworkAwareHeartbeatIntervalDeterminer;
import com.amazon.communication.heartbeat.NosHeartbeatNotificationScheduler;
import com.amazon.communication.heartbeat.ProbingConnectionLifetimeManager;
import com.amazon.communication.heartbeat.store.HeartbeatIntervalDeterminerPersistenceStore;
import com.amazon.communication.ir.IIdentityResolver;
import com.amazon.communication.ir.RemoteSettingIdentityResolver;
import com.amazon.communication.ir.ServiceSideIdentityResolverProxy;
import com.amazon.communication.remotesetting.ConfigurationSyncReceiver;
import com.amazon.communication.remotesetting.RemoteSettingManager;
import com.amazon.communication.remotesetting.SettingUpdateListener;
import com.amazon.communication.rlm.DeviceReliableMessageProtocol;
import com.amazon.communication.rlm.IAckHandler;
import com.amazon.communication.rlm.ReliableMessagingMessageHandler;
import com.amazon.communication.rlm.ServiceSideAckHandlerProxy;
import com.amazon.communication.socket.ConnectReason;
import com.amazon.communication.socket.NoRouteToEndpointException;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
import com.amazon.communication.socket.SocketUsageAggregator;
import com.amazon.communication.socket.decisionengine.DeviceIdentitySocketDecisionEngine;
import com.amazon.communication.socket.decisionengine.ServiceIdentitySocketDecisionEngine;
import com.amazon.communication.socket.ssl.NoSecureRouteToEndpointException;
import com.amazon.communication.wifi.WifiManagerWrapper;
import com.amazon.communication.wifi.WifiManagerWrapperImpl;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.device.nos.GenericNetworkOptimizationManagerImpl;
import com.amazon.device.nos.NetworkOptimizationManager;
import com.amazon.device.nos.NoWakeUpNetworkOptimizationManagerImpl;
import com.amazon.dp.logger.DPLogger;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.codahale.metrics.SharedMetricRegistries;
import com.dp.framework.HexStreamCodec;
import com.dp.utils.FailFast;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
/* loaded from: classes12.dex */
public class TCommService implements ITCommService {
    public static final String ENABLE_BYTE_ACCOUNTANT_KEY = "Setting.EnableByteAccountant";
    public static final String ENABLE_NETWORK_STABILITY_OVER_WAN_KEY = "Setting.EnableStabilityOverWan";
    protected static final int MESSAGE_QUEUE_SIZE = 50;
    private static final String METRICS_SOURCE_NAME = "TCommService";
    public static final String NETWORK_STABILITY_THRESHOLD_MILLIS_KEY = "Setting.NetworkStabilityThresholdMillis";
    public static final String NETWORK_STABILITY_WAKE_LOCK_TAG = "TComm.NetworkStabilityMonitorWakeLock";
    private static final int NUM_HANDLER_THREADS = 7;
    private static final int PERIODIC_METRIC_REPORT_PERIOD_HRS = 1;
    public static final String STAGE_SWITCH_INTENT = "com.amazon.device.environment.action.SWITCH_STAGE";
    public static final int STR_NO_WAKEUP = 0;
    public static final int STR_WAKEUP = 1;
    public static final int STR_WAKE_UP_ENABLED_DEFAULT = 0;
    public static final String STR_WAKE_UP_ENABLED_KEY = "str.auto_wake_up_enabled";
    private static final String TCP_KEEP_ALIVE_EXECUTOR_TAG = "TComm.TcpKeepAlive";
    public static final String TRUST_ALL_SSL_HOSTS_KEY = "Setting.InternalTestTrustSslHosts";
    private static final String USE_TCOMM_PERMISSION = "amazon.permission.USE_TCOMM";
    private static final long WAIT_LATCH_TIMEOUT_MS = 5000;
    private static final String WORK_EXECUTOR_TAG = "TComm.WorkExecutor";
    protected MapAccountManagerWrapper mAccountManager;
    protected BackoffScheduler mBackoffScheduler;
    protected BufferedMessageToInputStreamResponseRouter mBufferedMessageToResponseRouter;
    protected BandwidthToolByteAccountant mByteAccountant;
    protected ChannelRestrictor mChannelRestrictor;
    protected CommunicationEngine mCommunicationEngine;
    protected HeartbeatConnectionHealthManager mConnectionHealthManager;
    protected ConnectionHealthStatisticsAggregator mConnectionHealthStatisticsAggregator;
    protected ConnectivityManagerWrapper mConnectivityManager;
    protected ConnectivityMonitor mConnectivityMonitor;
    protected HeartbeatIntervalDeterminer mConservativeHeartbeatIntervalDeterminer;
    protected Context mContext;
    protected ChannelAwareD2DMessageRouter mD2DMessageRouter;
    protected D2DNotificationRouter mD2DNotificationRouter;
    private DeviceIdentitySocketDecisionEngine mDeviceIdentitySocketDecisionEngine;
    private DeviceUndeliverableMessageHandler mDeviceUndeliverableMessageHandler;
    protected DirectorServiceClient mDirectorServiceClient;
    private GatewayConnectivityListener mGatewayConnectivityListener;
    protected DeviceGatewayHandshakeHandler mGatewayHandshakeHandler;
    protected Handler mHandler;
    protected NetworkAwareHeartbeatIntervalDeterminer mHeartbeatIntervalDeterminer;
    protected HeartbeatNotificationScheduler mHeartbeatNotificationScheduler;
    protected IdentityResolver mIdentityResolver;
    protected WakeLockHoldingScheduledThreadPoolExecutor mKeepAliveExecutor;
    protected TCommKillSwitch mKillSwitch;
    protected MessageRouterImpl mMessageRouter;
    private MetricEvent mMetricEvent;
    protected StandardMetricReporter mMetricReporter;
    protected NetworkStabilityMonitor mNetworkStabilityMonitor;
    private WakeLockHoldingScheduledThreadPoolExecutor mNetworkStabilityMonitorThreadPool;
    private NetworkStabilityUpdateListener mNetworkStabilityUpdateListener;
    protected NetworkOptimizationManager mNonWakeupNosManager;
    protected NetworkOptimizationManager mNosManager;
    protected PeriodicMetricReporter mPeriodicMetricReporter;
    protected PowerManagerWrapper mPowerManager;
    protected ProtocolHandlerManager mProtocolHandlerManager;
    protected DeviceReliableMessageProtocol mReliableMessageProtocol;
    private ReliableMessagingMessageHandler mReliableMessagingMessageHandler;
    private ReliableMessagingMessageHandler mReliableMessagingResponseMessageHandler;
    protected DcpRequestSigner mRequestSigner;
    protected ResponseRouter mResponseRouter;
    protected ScreenEventMonitor mScreenEventMonitor;
    private ServiceIdentitySocketDecisionEngine mServiceIdentitySocketDecisionEngine;
    protected ServiceSideMessageRouter mServiceSideMessageRouter;
    private DeviceSocketDecisionEngine mSocketDecisionEngine;
    protected DeviceSocketManager mSocketManager;
    protected SocketUsageAggregator mSocketUsageAggregator;
    private long mTCommServiceInitTimeStamp;
    protected NetworkOptimizationManager mWakeupNosManager;
    protected AlwaysOnSocketWatchdogManager mWatchdogManager;
    protected WifiManagerWrapper mWifiManager;
    protected WorkExecutor mWorkExecutor;
    private static final DPLogger log = new DPLogger("TComm.TCommService");
    public static final String[] ACCOUNT_CHANGE_INTENTS = {"com.amazon.dcp.sso.action.account.added", "com.amazon.dcp.sso.action.secondary.account.added", "com.amazon.dcp.sso.action.account.removed", "com.amazon.dcp.sso.action.secondary.account.removed", "com.amazon.identity.auth.account.added.on.device", "com.amazon.identity.auth.account.removed.on.device"};
    public static final IntentFilter ACCOUNT_CHANGE_INTENT_FILTER = new IntentFilter() { // from class: com.amazon.communication.TCommService.1
        {
            for (String str : TCommService.ACCOUNT_CHANGE_INTENTS) {
                addAction(str);
            }
        }
    };
    public static final String[] ARCUS_SYNC_INTENTS = {ConfigurationSyncReceiver.ACTION_PERIODIC_SYNC, ConfigurationSyncReceiver.ACTION_RETRY_SYNC, "android.intent.action.BOOT_COMPLETED", BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION};
    public static final IntentFilter ARCUS_SYNC_INTENT_FILTER = new IntentFilter() { // from class: com.amazon.communication.TCommService.2
        {
            for (String str : TCommService.ARCUS_SYNC_INTENTS) {
                addAction(str);
            }
        }
    };
    public static final IntentFilter STAGE_SWITCH_INTENT_FILTER = new IntentFilter() { // from class: com.amazon.communication.TCommService.3
        {
            addAction("com.amazon.device.environment.action.SWITCH_STAGE");
        }
    };
    public static final Boolean ENABLE_BYTE_ACCOUNTANT_DEFAULT = false;
    private final Uri STR_SYSTEM_SETTING_URI = Settings.Secure.getUriFor(STR_WAKE_UP_ENABLED_KEY);
    protected final AtomicBoolean mServiceInitialized = new AtomicBoolean(false);
    private final HexStreamCodec mHexStreamCodec = new HexStreamCodec();
    private final IBinder mBinder = new ICommunicationService.Stub() { // from class: com.amazon.communication.TCommService.7
        private void enforcePermissionOnIpc() throws RemoteException {
            if (Binder.getCallingUid() == Process.myUid() || TCommService.this.mContext.checkCallingPermission(TCommService.USE_TCOMM_PERMISSION) == 0) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Permission denied: Process: ");
            outline107.append(Binder.getCallingPid());
            outline107.append(" is missing permission: ");
            outline107.append(TCommService.USE_TCOMM_PERMISSION);
            throwRemoteSecurityException(outline107.toString());
        }

        private void enforceSameUidOnIpc() throws RemoteException {
            if (Binder.getCallingUid() != Process.myUid()) {
                throwRemoteSecurityException("Permission denied: May only be called within TComm.");
            }
        }

        private void throwRemoteSecurityException(String str) throws RemoteException {
            RemoteException remoteException = new RemoteException();
            remoteException.initCause(new SecurityException(str));
            throw remoteException;
        }

        private void validateEndpointAndPolicy(ParcelableEndpointIdentity parcelableEndpointIdentity, Policy policy) {
            if (parcelableEndpointIdentity != null) {
                if (policy != null) {
                    if (policy.getKeepAlive() == KeepAlive.ADAPTIVE && !parcelableEndpointIdentity.equals(GatewayConnectionService.GATEWAY_ENDPOINT)) {
                        throw new IllegalArgumentException("Only Gateway endpoint is allowed to use adaptive keepalives");
                    }
                    if (parcelableEndpointIdentity.getType() != EndpointIdentity.Type.SERVICE) {
                        return;
                    }
                    IRServiceEndpoint resolveServiceEndpoint = TCommService.this.mIdentityResolver.resolveServiceEndpoint((ServiceIdentity) EndpointIdentityFactory.createFromUrn(parcelableEndpointIdentity.toString()), policy.getPurpose());
                    if (resolveServiceEndpoint == null) {
                        return;
                    }
                    if (!resolveServiceEndpoint.getHostname().equals(TCommService.this.mIdentityResolver.resolveServiceEndpoint(GatewayConnectionService.GATEWAY_ENDPOINT).getHostname())) {
                        return;
                    }
                    if (policy.getReconnectOnFailure()) {
                        if (policy.getKeepAlive() != KeepAlive.NONE) {
                            if (policy.getPurpose() != null) {
                                if (Binder.getCallingPid() == Process.myPid()) {
                                    return;
                                }
                                if (!parcelableEndpointIdentity.equals(GatewayConnectionService.GATEWAY_ENDPOINT)) {
                                    if (!Purpose.REGULAR.equals(policy.getPurpose())) {
                                        return;
                                    }
                                    throw new IllegalArgumentException("Endpoint " + parcelableEndpointIdentity + " maps to the gateway. Since this request is not from within TComm, a special Purpose must be indicated in the Policy object");
                                }
                                throw new IllegalArgumentException("Gateway endpoint can only be acquired directly from within tcomm");
                            }
                            throw new IllegalArgumentException("Endpoint " + parcelableEndpointIdentity + " maps to the gateway and therefore must be acquired with a purpose");
                        }
                        throw new IllegalArgumentException("Endpoint " + parcelableEndpointIdentity + " maps to the gateway and therefore must be acquired with a keepalive");
                    }
                    throw new IllegalArgumentException("Endpoint " + parcelableEndpointIdentity + " maps to the gateway and therefore must be acquired with reconnectOnFailure");
                }
                throw new IllegalArgumentException("policy cannot be null");
            }
            throw new IllegalArgumentException("identity cannot be null");
        }

        @Override // com.amazon.communication.ICommunicationService
        public IConnection acquireConnection(ParcelableEndpointIdentity parcelableEndpointIdentity, ParcelableConnectionPolicy parcelableConnectionPolicy, IConnectionListener iConnectionListener, ParcelableStatus parcelableStatus) throws RemoteException {
            enforcePermissionOnIpc();
            return acquireConnectionEx(parcelableEndpointIdentity, new ParcelablePolicy(new Policy.Builder().fromConnectionPolicy(parcelableConnectionPolicy)), iConnectionListener, parcelableStatus);
        }

        @Override // com.amazon.communication.ICommunicationService
        public IConnection acquireConnectionEx(ParcelableEndpointIdentity parcelableEndpointIdentity, ParcelablePolicy parcelablePolicy, IConnectionListener iConnectionListener, ParcelableStatus parcelableStatus) throws RemoteException {
            IConnection acquireConnection;
            enforcePermissionOnIpc();
            TCommService.log.debug("acquireConnectionEx", "acquire", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, parcelableEndpointIdentity, "calling pid", Integer.valueOf(Binder.getCallingPid()), "myPid", Integer.valueOf(Process.myPid()));
            try {
                validateEndpointAndPolicy(parcelableEndpointIdentity, parcelablePolicy.getPolicy());
                Thread.currentThread().setUncaughtExceptionHandler(new TCommUncaughtExceptionHandler());
                if (isInitialized()) {
                    TCommService.log.verbose("acquireConnection", "entering", new Object[0]);
                    if (!TCommService.this.isConnectivityPossible() && !parcelablePolicy.getPolicy().getReconnectOnFailure()) {
                        TCommService.log.warn("acquireConnection", "Could not acquire connection. No network connection is available on the device.", new Object[0]);
                        parcelableStatus.setStatusCode(4);
                        parcelableStatus.setStatusMessage("Could not acquire connection. No network connection is available on the device.");
                        return null;
                    }
                    Policy policy = parcelablePolicy.getPolicy();
                    if (policy.isWiFiNecessary() && !TCommService.this.isWiFiAvailable() && !TCommService.this.isEthernetAvailable()) {
                        TCommService.log.warn("acquireConnection", "Could not acquire connection. No Wi-Fi or Ethernet connection is available on the device.", new Object[0]);
                        parcelableStatus.setStatusCode(5);
                        parcelableStatus.setStatusMessage("Could not acquire connection. No Wi-Fi or Ethernet connection is available on the device.");
                        return null;
                    } else if (policy.isAnonymousCredentialsAllowed() || TCommService.this.credentialsExist()) {
                        try {
                            try {
                                try {
                                    TCommService.log.debug("acquireConnection", "This thread is about to try to acquire the lock", new Object[0]);
                                    synchronized (this) {
                                        acquireConnection = TCommService.this.acquireConnection(parcelableEndpointIdentity, policy, null, iConnectionListener);
                                        parcelableStatus.setStatusCode(0);
                                    }
                                    TCommService.log.debug("acquireConnection", "this thread no longer has the lock", new Object[0]);
                                    return acquireConnection;
                                } catch (NoSecureRouteToEndpointException e) {
                                    String str = "Cannot acquire secure connection to Endpoint. If secure is not necessary, ensure the policy has isClearText=true. If a new secure connection is necessary, verify the IR has a secure port. If a re-used secure connection is expected, those were all unavailable. Reason: " + e.getMessage();
                                    TCommService.log.warn("acquireConnection", str, e);
                                    parcelableStatus.setStatusCode(6);
                                    parcelableStatus.setStatusMessage(str);
                                    TCommService.log.debug("acquireConnection", "this thread no longer has the lock", new Object[0]);
                                    return null;
                                }
                            } catch (NoRouteToEndpointException e2) {
                                parcelableStatus.setStatusCode(3);
                                parcelableStatus.setStatusMessage("Endpoint is unreachable. Check connectivity. Reason: " + e2.getMessage());
                                TCommService.log.debug("acquireConnection", "this thread no longer has the lock", new Object[0]);
                                return null;
                            } catch (Exception e3) {
                                String str2 = "Could not acquire connection. Reason: " + (e3.getMessage() == null ? "Usually because of no connectivity." : e3.getMessage());
                                TCommService.log.error("acquireConnection", str2, e3);
                                parcelableStatus.setStatusCode(1);
                                parcelableStatus.setStatusMessage(str2);
                                TCommService.log.debug("acquireConnection", "this thread no longer has the lock", new Object[0]);
                                return null;
                            }
                        } catch (Throwable th) {
                            TCommService.log.debug("acquireConnection", "this thread no longer has the lock", new Object[0]);
                            throw th;
                        }
                    } else {
                        TCommService.log.warn("acquireConnection", "Could not acquire connection. There is no Amazon account on the device.", new Object[0]);
                        parcelableStatus.setStatusCode(2);
                        parcelableStatus.setStatusMessage("Could not acquire connection. There is no Amazon account on the device.");
                        return null;
                    }
                }
                parcelableStatus.setStatusCode(8);
                parcelableStatus.setStatusMessage("CommunicationService has not been started.");
                return null;
            } catch (IllegalArgumentException e4) {
                parcelableStatus.setStatusCode(1);
                parcelableStatus.setStatusMessage(e4.getMessage());
                return null;
            }
        }

        @Override // com.amazon.communication.ICommunicationService
        public void deregisterMessageHandler(int i) throws RemoteException {
            enforcePermissionOnIpc();
            TCommService.this.mServiceSideMessageRouter.deregisterMessageHandler(i);
        }

        @Override // com.amazon.communication.ICommunicationService
        public IGatewayConnectivity getGatewayConnectivity(IConnectionListener iConnectionListener, ParcelableStatus parcelableStatus) throws RemoteException {
            enforcePermissionOnIpc();
            if (!isInitialized()) {
                parcelableStatus.setStatusCode(8);
                parcelableStatus.setStatusMessage("CommunicationService has not been started.");
                return null;
            }
            try {
                ServiceSideGatewayConnectivityProxy serviceSideGatewayConnectivityProxy = new ServiceSideGatewayConnectivityProxy(TCommService.this.mGatewayConnectivityListener, iConnectionListener);
                TCommService.this.mGatewayConnectivityListener.addListener(serviceSideGatewayConnectivityProxy);
                return IGatewayConnectivity.Stub.asInterface(serviceSideGatewayConnectivityProxy);
            } catch (RemoteException unused) {
                TCommService.log.verbose("getGatewayConnectivity", "client callback died before GatewayConnectivity object was created", new Object[0]);
                return null;
            }
        }

        @Override // com.amazon.communication.ICommunicationService
        public IIdentityResolver getIdentityResolver() throws RemoteException {
            enforcePermissionOnIpc();
            return IIdentityResolver.Stub.asInterface(new ServiceSideIdentityResolverProxy(TCommService.this.mIdentityResolver));
        }

        @Override // com.amazon.communication.ICommunicationService
        public boolean isInitialized() {
            return TCommService.this.mServiceInitialized.get();
        }

        @Override // com.amazon.communication.ICommunicationService
        public int registerMessageHandler(int i, IMessageHandler iMessageHandler) throws RemoteException {
            enforcePermissionOnIpc();
            return TCommService.this.mServiceSideMessageRouter.registerMessageHandler(i, iMessageHandler);
        }

        @Override // com.amazon.communication.ICommunicationService
        public void removeAckHandler() throws RemoteException {
            TCommService.this.mReliableMessageProtocol.removeAckHandler(Integer.toString(Binder.getCallingPid()));
        }

        @Override // com.amazon.communication.ICommunicationService
        public void routeMessage(ParcelableEndpointIdentity parcelableEndpointIdentity, MessageEnvelope messageEnvelope, int i) throws RemoteException {
            enforceSameUidOnIpc();
            TCommService.this.routeMessage(parcelableEndpointIdentity, messageEnvelope.toMessage(), i);
        }

        @Override // com.amazon.communication.ICommunicationService
        public void routeMessageFragment(ParcelableEndpointIdentity parcelableEndpointIdentity, int i, MessageEnvelope messageEnvelope, boolean z, int i2) throws RemoteException {
            enforceSameUidOnIpc();
            TCommService.this.routeMessageFragment(parcelableEndpointIdentity, i, messageEnvelope, z, i2);
        }

        @Override // com.amazon.communication.ICommunicationService
        public int setAckHandler(IAckHandler iAckHandler) throws RemoteException {
            enforcePermissionOnIpc();
            ServiceSideAckHandlerProxy serviceSideAckHandlerProxy = new ServiceSideAckHandlerProxy(iAckHandler);
            try {
                TCommService.this.mReliableMessageProtocol.setAckHandler(Integer.toString(Binder.getCallingPid()), serviceSideAckHandlerProxy);
                return 0;
            } catch (DuplicateHandlerException unused) {
                return 2000;
            } catch (RegistrationFailedException unused2) {
                return CommunicationErrorCodes.ERR_HANDLER_INTERNAL_ERROR_UPON_REGISTRATION;
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public final class NetworkStabilityUpdateListener extends SettingUpdateListener {
        private NetworkStabilityMonitorImpl mStabilityMonitor;

        private NetworkStabilityUpdateListener() {
        }

        @Override // com.amazon.communication.remotesetting.SettingUpdateListener
        public void onSettingUpdated() {
            TCommService.log.info("onSettingUpdated", "Update Setting.", new Object[0]);
            TCommService.this.updateNetworkStabilityMonitorSettings(this.mStabilityMonitor);
        }

        public void setUpNetworkStabilityMonitor(NetworkStabilityMonitorImpl networkStabilityMonitorImpl) {
            this.mStabilityMonitor = networkStabilityMonitorImpl;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IConnection acquireConnection(ParcelableEndpointIdentity parcelableEndpointIdentity, Policy policy, String str, IConnectionListener iConnectionListener) throws SocketAcquisitionFailedException {
        log.info("acquireConnection", "entering", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, parcelableEndpointIdentity.toLogSafeString(), "policy", policy, "directedIdHash", Integer.valueOf(str != null ? str.hashCode() : 0), ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, iConnectionListener);
        if (policy.getReconnectOnFailure()) {
            try {
                ServiceSideAlwaysOnConnectionProxy serviceSideAlwaysOnConnectionProxy = new ServiceSideAlwaysOnConnectionProxy(this.mResponseRouter, iConnectionListener, this.mByteAccountant, this.mPeriodicMetricReporter);
                if (parcelableEndpointIdentity.getType() == EndpointIdentity.Type.SERVICE) {
                    ServiceIdentity serviceIdentity = (ServiceIdentity) EndpointIdentityFactory.createFromUrn(parcelableEndpointIdentity.toString());
                    IRServiceEndpoint resolveServiceEndpoint = this.mIdentityResolver.resolveServiceEndpoint(serviceIdentity, policy.getPurpose() != null ? policy.getPurpose() : Purpose.REGULAR);
                    if (resolveServiceEndpoint != null) {
                        AlwaysOnSocketWatchdog andRetain = this.mWatchdogManager.getAndRetain(resolveServiceEndpoint, parcelableEndpointIdentity, policy);
                        serviceSideAlwaysOnConnectionProxy.setWatchdog(andRetain);
                        if (GatewayConnectionService.GATEWAY_ALIASES.contains(parcelableEndpointIdentity)) {
                            andRetain.addSocketStateListener(this.mGatewayHandshakeHandler);
                            andRetain.addSocketStateListener(this.mGatewayConnectivityListener);
                        }
                        andRetain.startWatching(ConnectReason.ReasonString.ClientInitiated);
                        log.verbose("acquireConnection", "letting mWatchdogManager decide whether to call maintainConnection on ConnectionHealthManager or not", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, parcelableEndpointIdentity, "policy", policy);
                        boolean hasSystemFeature = hasSystemFeature("com.amazon.tcomm.max_availability");
                        log.info("acquireConnection", "check for max tcomm availability", Boolean.valueOf(hasSystemFeature));
                        if (policy.getKeepAlive() == KeepAlive.ADAPTIVE && !hasSystemFeature) {
                            AlwaysOnSocketWatchdog andRetain2 = this.mWatchdogManager.getAndRetain(this.mIdentityResolver.resolveServiceEndpoint(GatewayConnectionService.GATEWAY_PROBING_ENDPOINT), GatewayConnectionService.GATEWAY_PROBING_ENDPOINT, GatewayConnectionService.GATEWAY_PROBING_POLICY);
                            andRetain2.setHeartbeatIntervalDeterminer(this.mHeartbeatIntervalDeterminer);
                            andRetain2.setProbingConnectionLifetimeManager(new ProbingConnectionLifetimeManager(andRetain2, this.mHeartbeatIntervalDeterminer, this.mConnectivityMonitor, this.mBackoffScheduler));
                            serviceSideAlwaysOnConnectionProxy.setProbingWatchdog(andRetain2);
                        }
                        return IConnection.Stub.asInterface(serviceSideAlwaysOnConnectionProxy);
                    }
                    throw new IllegalArgumentException("Could not find identity resolver mapping for " + serviceIdentity);
                }
                throw new IllegalArgumentException("reconnectOnFailure can only be used with Service identities");
            } catch (RemoteException unused) {
                log.verbose("acquireConnection", "client callback died before connection was acquired", new Object[0]);
                return null;
            }
        }
        try {
            ServiceSideConnectionProxy serviceSideConnectionProxy = new ServiceSideConnectionProxy(this.mResponseRouter, iConnectionListener, this.mByteAccountant, this.mPeriodicMetricReporter);
            serviceSideConnectionProxy.setProtocolSocket(this.mCommunicationEngine.acquireProtocolSocket(parcelableEndpointIdentity, policy, new ConnectReason(ConnectReason.ReasonString.ClientInitiated, 1), str));
            return IConnection.Stub.asInterface(serviceSideConnectionProxy);
        } catch (RemoteException unused2) {
            log.verbose("acquireConnection", "client callback died before connection was acquired", new Object[0]);
            return null;
        }
    }

    private ChannelRestrictor createChannelRestrictor() {
        HashMap hashMap = new HashMap();
        hashMap.put(480, GatewayConnectionService.GATEWAY_ALIASES);
        hashMap.put(Integer.valueOf((int) Channels.GW_CHANNEL), GatewayConnectionService.GATEWAY_ALIASES);
        hashMap.put(120, GatewayConnectionService.GATEWAY_ALIASES);
        hashMap.put(99, GatewayConnectionService.GATEWAY_ALIASES);
        hashMap.put(100, GatewayConnectionService.GATEWAY_ALIASES);
        return new ChannelRestrictor(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean credentialsExist() {
        return this.mAccountManager.getAccount() != null;
    }

    protected static final Boolean isByteAccountantEnabled() {
        return RemoteSettingManager.getOptBooleanValue(ENABLE_BYTE_ACCOUNTANT_KEY, ENABLE_BYTE_ACCOUNTANT_DEFAULT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isConnectivityPossible() {
        return this.mConnectivityMonitor.isConnectivityPossible();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isEthernetAvailable() {
        return this.mConnectivityMonitor.isEthernetAvailable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isWiFiAvailable() {
        return this.mConnectivityMonitor.isWiFiAvailable();
    }

    protected static final Boolean shouldTrustAllSslHosts() {
        return RemoteSettingManager.getOptBooleanValue(TRUST_ALL_SSL_HOSTS_KEY, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNetworkStabilityMonitorSettings(NetworkStabilityMonitorImpl networkStabilityMonitorImpl) {
        if (networkStabilityMonitorImpl == null) {
            log.info("updateNetworkStabilityMonitorSettings", "Failed to update, NetworkStabilityMonitor is null.", new Object[0]);
            return;
        }
        NetworkStabilityMonitorImpl.setStabilityThreshold(RemoteSettingManager.getOptLongValue(NETWORK_STABILITY_THRESHOLD_MILLIS_KEY, Long.valueOf(NetworkStabilityMonitorImpl.DEFAULT_NETWORK_STABILITY_THRESHOLD_MILLIS)).longValue());
        NetworkStabilityMonitorImpl.enableStabilityOverWan(RemoteSettingManager.getOptBooleanValue(ENABLE_NETWORK_STABILITY_OVER_WAN_KEY, NetworkStabilityMonitorImpl.DEFAULT_NETWORK_STABILITY_ENABLED_OVER_WAN));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10, types: [com.amazon.communication.socket.SocketUsageAggregator, com.amazon.communication.socket.SocketUsageAggregatedReader] */
    /* JADX WARN: Type inference failed for: r7v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    public void backgroundInitialization(CountDownLatch countDownLatch) {
        String str;
        DeviceGatewayApplicationProtocol deviceGatewayApplicationProtocol;
        GatewayControlProtocol gatewayControlProtocol;
        DeviceSocketManager deviceSocketManager;
        MessageRouterImpl messageRouterImpl;
        ResponseRouter responseRouter;
        BufferedMessageToInputStreamResponseRouter bufferedMessageToInputStreamResponseRouter;
        D2DNotificationRouter d2DNotificationRouter;
        ChannelAwareD2DMessageRouter channelAwareD2DMessageRouter;
        ProtocolHandlerManager protocolHandlerManager;
        IdentityResolver identityResolver;
        boolean z;
        log.info("backgroundInitialization", "entering", new Object[0]);
        FailFast.expectFalse(Looper.myLooper() == Looper.getMainLooper(), "backgroundInitialization must not be run on the main thread");
        try {
            this.mMetricReporter = StandardMetricReporter.create(this.mContext, SharedMetricRegistries.getOrCreate(RouteName.MAIN), null);
            if (this.mMetricReporter != null) {
                this.mMetricReporter.start();
            }
            this.mPeriodicMetricReporter = new PeriodicMetricReporterImpl(AndroidMetricsFactoryImpl.getInstance(this.mContext), TCommMetrics.PROGRAM_ID, "TCommService");
            this.mPeriodicMetricReporter.startRecordingPeriodically(1L, TimeUnit.HOURS);
            this.mMetricEvent = this.mPeriodicMetricReporter.createTrackedMetricEvent(TCommMetrics.PROGRAM_ID, "TCommService");
            this.mMetricEvent.addCounter(TCommMetrics.TCOMM_IMPL_CHOSEN_WS, 1.0d);
            this.mByteAccountant = new NullByteAccountant();
            this.mWifiManager = new WifiManagerWrapperImpl(this.mContext);
            this.mChannelRestrictor = createChannelRestrictor();
            this.mMessageRouter = new MessageRouterImpl();
            this.mResponseRouter = new ResponseRouterImpl();
            this.mProtocolHandlerManager = new ProtocolHandlerManagerImpl();
            this.mD2DNotificationRouter = new IntentLaunchingD2DNotificationRouter(this.mContext);
            this.mD2DMessageRouter = new ChannelAwareD2DMessageRouterImpl(this.mMessageRouter, this.mD2DNotificationRouter);
            this.mServiceSideMessageRouter = new ServiceSideMessageRouter(this.mMessageRouter, this.mByteAccountant);
            this.mRequestSigner = new DcpRequestSigner(this.mContext);
            this.mSocketUsageAggregator = new SocketUsageAggregator();
            this.mAccountManager = new MapAccountManagerWrapperImpl(this.mContext);
            this.mDirectorServiceClient = createDirectorServiceClient(this.mAccountManager, this.mContext, this.mWorkExecutor);
            this.mContext.registerReceiver(this.mDirectorServiceClient.getAccountsUpdatedReceiver(), ACCOUNT_CHANGE_INTENT_FILTER);
            CountDownLatch countDownLatch2 = new CountDownLatch(1);
            RemoteSettingManager.initialize(this.mContext, countDownLatch2);
            this.mContext.registerReceiver(RemoteSettingManager.mConfigSyncReceiver, ARCUS_SYNC_INTENT_FILTER);
            this.mContext.registerReceiver(RemoteSettingManager.mStageSwitchReceiver, STAGE_SWITCH_INTENT_FILTER);
            try {
                if (countDownLatch2.await(5000L, TimeUnit.MILLISECONDS)) {
                    log.info("backgroundInitialization", "RemoteSettingManager finished initialization", new Object[0]);
                } else {
                    log.error("backgroundInitialization", "RemoteSettingManager did not complete initialization before timeout. This may cause some services to fail.", new Object[0]);
                }
            } catch (InterruptedException e) {
                log.warn("backgroundInitialization", "waitLatch.await was interrupted", e);
            }
            this.mIdentityResolver = createIdentityResolver(this.mDirectorServiceClient);
            this.mConnectivityManager = newConnectivityManagerWrapperImpl();
            this.mConnectivityMonitor = createConnectivityMonitor(this.mContext);
            this.mConnectivityMonitor.start();
            this.mSocketManager = new DeviceSocketManager(this.mContext, this.mWorkExecutor, this.mProtocolHandlerManager, this.mResponseRouter, this.mRequestSigner, this.mIdentityResolver, this.mPeriodicMetricReporter, this.mSocketUsageAggregator, SelectorProvider.provider(), this.mAccountManager, this.mConnectivityMonitor, this.mPowerManager, this.mWifiManager, 50, new StrictHostnameVerifier(), this.mByteAccountant);
            if (shouldTrustAllSslHosts().booleanValue()) {
                log.info("backgroundInitialization", "configuring TComm to trust all SSL hosts", new Object[0]);
                try {
                    SSLContext sSLContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL, "HarmonyJSSE");
                    sSLContext.init(null, new TrustManager[]{new DevoTrustManager()}, null);
                    this.mSocketManager.setSslContext(sSLContext);
                } catch (Exception e2) {
                    log.error("backgroundInitialization", "Error setting DevoTrustManager", e2);
                }
            }
            this.mSocketManager.initialize();
            this.mContext.registerReceiver(this.mSocketManager.getAccountsUpdatedReceiver(), ACCOUNT_CHANGE_INTENT_FILTER);
            this.mBufferedMessageToResponseRouter = new BufferedMessageToInputStreamResponseRouter(this.mResponseRouter);
            boolean hasSystemFeature = hasSystemFeature("com.amazon.hardware.tv_screen");
            int readSTRWakeUpSetting = readSTRWakeUpSetting();
            log.debug("backgroundInitialization", "Reading STR system property", "STR Enabled/FEATURE_TV_SCREEN", Boolean.valueOf(hasSystemFeature), STR_WAKE_UP_ENABLED_KEY, Integer.valueOf(readSTRWakeUpSetting));
            if (hasSystemFeature) {
                registerSTRContentObserver();
                if (1 == readSTRWakeUpSetting) {
                    useWakeupAlarms();
                    log.info("backgroundInitialization", "Running on a STR enabled device with str setting enabled,using wakeup alarms", new Object[0]);
                } else {
                    useNonWakeupAlarms();
                    log.info("backgroundInitialization", "Running on a STR enabled device with str setting not enabled,using non-wakeup alarms", new Object[0]);
                }
            } else {
                useWakeupAlarms();
                log.info("backgroundInitialization", "Running on a non-STR enabled device, using wakeup alarms", new Object[0]);
            }
            this.mScreenEventMonitor = createScreenEventMonitor(this.mContext);
            this.mScreenEventMonitor.start();
            this.mGatewayConnectivityListener = new GatewayConnectivityListener();
            this.mBackoffScheduler = new NosBackoffScheduler(this.mContext, this.mNosManager);
            this.mNetworkStabilityMonitorThreadPool = new WakeLockHoldingScheduledThreadPoolExecutor(1, ThreadName.NETWORK_STABILITY_MONITOR, new TCommUncaughtExceptionHandler(), this.mPowerManager.newWakeLock(1, NETWORK_STABILITY_WAKE_LOCK_TAG), PowerManagerWrapperImpl.WAKE_LOCK_TIMEOUT_MS);
            this.mNetworkStabilityUpdateListener = new NetworkStabilityUpdateListener();
            this.mNetworkStabilityMonitor = createNetworkStabilityMonitor();
            RemoteSettingManager.addSettingUpdateListener(this.mNetworkStabilityUpdateListener);
            this.mNetworkStabilityMonitor.start();
            HeartbeatSettings.setConnectivityManager(this.mConnectivityManager);
            this.mHeartbeatIntervalDeterminer = createNetworkAwareHeartbeatIntervalDeterminer(this.mContext);
            this.mConnectionHealthStatisticsAggregator = this.mHeartbeatIntervalDeterminer;
            this.mConservativeHeartbeatIntervalDeterminer = new ConservativeHeartbeatIntervalDeterminer(this.mHeartbeatIntervalDeterminer);
            this.mHeartbeatNotificationScheduler = createHeartbeatNotificationScheduler(this.mContext);
            this.mConnectionHealthManager = createConnectionHealthManager();
            deviceGatewayApplicationProtocol = new DeviceGatewayApplicationProtocol(this.mHexStreamCodec, this.mMessageRouter, this.mResponseRouter);
            gatewayControlProtocol = new GatewayControlProtocol(this.mHexStreamCodec);
            this.mServiceIdentitySocketDecisionEngine = new ServiceIdentitySocketDecisionEngine(this.mConnectivityManager, deviceGatewayApplicationProtocol, gatewayControlProtocol, this.mSocketManager, this.mSocketUsageAggregator, this.mIdentityResolver, this.mWorkExecutor);
            this.mWatchdogManager = new AlwaysOnSocketWatchdogManager() { // from class: com.amazon.communication.TCommService.6
                {
                    setContext(TCommService.this.mContext);
                    setSocketDecisionEngine(TCommService.this.mServiceIdentitySocketDecisionEngine);
                    setIdentityResolver(TCommService.this.mIdentityResolver);
                    setConnectivityMonitor(TCommService.this.mConnectivityMonitor);
                    setScreenEventMonitor(TCommService.this.mScreenEventMonitor);
                    setNetworkStabilityMonitor(TCommService.this.mNetworkStabilityMonitor);
                    setBackoffScheduler(TCommService.this.mBackoffScheduler);
                    setConnectionHealthManager(TCommService.this.mConnectionHealthManager);
                    setHeartbeatIntervalDeterminer(TCommService.this.mConservativeHeartbeatIntervalDeterminer);
                    setConnectionHealthStatisticsAggregator(TCommService.this.mHeartbeatIntervalDeterminer);
                    setWifiManager(TCommService.this.mWifiManager);
                    setPowerManager(TCommService.this.mPowerManager);
                    setAccountManager(TCommService.this.mAccountManager);
                    setTCommService(TCommService.this);
                }
            };
            this.mWatchdogManager.initialize();
            GatewayHandshakeProtocol gatewayHandshakeProtocol = new GatewayHandshakeProtocol();
            gatewayHandshakeProtocol.setStreamCodec(this.mHexStreamCodec);
            this.mGatewayHandshakeHandler = new DeviceGatewayHandshakeHandler(new MapAccountManagerWrapperImpl(this.mContext), new AdpAuthenticationProvider(this.mContext), gatewayHandshakeProtocol, this.mWorkExecutor, this.mPeriodicMetricReporter);
            try {
                try {
                    this.mMessageRouter.registerMessageHandler(Channels.GW_HANDSHAKE_CHANNEL, this.mGatewayHandshakeHandler);
                } catch (RegistrationFailedException e3) {
                    log.error("backgroundInitialization", "error registering GatewayHandshakeHandler", e3);
                }
            } catch (DuplicateHandlerException e4) {
                log.error("backgroundInitialization", "error registering GatewayHandshakeHandler", e4);
            }
            this.mDeviceIdentitySocketDecisionEngine = new DeviceIdentitySocketDecisionEngine(this.mConnectivityManager, deviceGatewayApplicationProtocol, gatewayControlProtocol, this.mPeriodicMetricReporter, this.mSocketManager, this.mSocketUsageAggregator, this.mIdentityResolver, this.mWorkExecutor, this.mWatchdogManager, this.mGatewayHandshakeHandler, this.mGatewayConnectivityListener);
            this.mSocketDecisionEngine = new DeviceSocketDecisionEngine(this.mDeviceIdentitySocketDecisionEngine, this.mServiceIdentitySocketDecisionEngine);
            deviceSocketManager = this.mSocketManager;
            messageRouterImpl = this.mMessageRouter;
            responseRouter = this.mResponseRouter;
            bufferedMessageToInputStreamResponseRouter = this.mBufferedMessageToResponseRouter;
            d2DNotificationRouter = this.mD2DNotificationRouter;
            channelAwareD2DMessageRouter = this.mD2DMessageRouter;
            protocolHandlerManager = this.mProtocolHandlerManager;
            identityResolver = this.mIdentityResolver;
            str = this.mSocketUsageAggregator;
        } catch (Exception e5) {
            e = e5;
            str = "backgroundInitialization";
        }
        try {
            try {
                this.mCommunicationEngine = new DeviceCommunicationEngine(deviceSocketManager, messageRouterImpl, responseRouter, bufferedMessageToInputStreamResponseRouter, d2DNotificationRouter, channelAwareD2DMessageRouter, protocolHandlerManager, identityResolver, str, this.mConnectivityManager, this.mByteAccountant, this.mWorkExecutor, this.mChannelRestrictor, this.mSocketDecisionEngine, deviceGatewayApplicationProtocol, gatewayControlProtocol, this.mHexStreamCodec);
                this.mReliableMessageProtocol = new DeviceReliableMessageProtocol(this.mHexStreamCodec, this.mMessageRouter, this.mCommunicationEngine, this.mChannelRestrictor);
                this.mReliableMessagingMessageHandler = new ReliableMessagingMessageHandler(this.mReliableMessageProtocol, 99);
                this.mReliableMessagingResponseMessageHandler = new ReliableMessagingMessageHandler(this.mReliableMessageProtocol, 100);
                this.mDeviceUndeliverableMessageHandler = new DeviceUndeliverableMessageHandler(this.mCommunicationEngine);
                try {
                    this.mMessageRouter.registerUndeliverableMessageHandler(this.mDeviceUndeliverableMessageHandler);
                    str = "backgroundInitialization";
                } catch (DuplicateHandlerException e6) {
                    String str2 = "backgroundInitialization";
                    log.error(str2, "error registering UndeliverableMessageHandler", e6);
                    str = str2;
                } catch (RegistrationFailedException e7) {
                    String str3 = "backgroundInitialization";
                    log.error(str3, "error registering UndeliverableMessageHandler", e7);
                    str = str3;
                }
                try {
                    this.mMessageRouter.registerMessageHandler(99, this.mReliableMessagingMessageHandler);
                    this.mMessageRouter.registerMessageHandler(100, this.mReliableMessagingResponseMessageHandler);
                } catch (DuplicateHandlerException e8) {
                    z = true;
                    log.error(str, "error registering ReliableMessagingMessageHandler", e8);
                } catch (RegistrationFailedException e9) {
                    log.error(str, "error registering ReliableMessagingMessageHandler", e9);
                }
                z = true;
                this.mServiceInitialized.set(z);
                log.verbose(str, "completed", new Object[0]);
                this.mMetricEvent.addTimer("TCommServiceInitializeTime", TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.mTCommServiceInitTimeStamp));
                onInitializationComplete();
                countDownLatch.countDown();
            } catch (Exception e10) {
                e = e10;
                str = "backgroundInitialization";
                log.error(str, "initialization failed", e);
                FailFast.expectTrue(false, "initialization failed");
            }
        } catch (Exception e11) {
            e = e11;
            log.error(str, "initialization failed", e);
            FailFast.expectTrue(false, "initialization failed");
        }
    }

    public void clearNosAlarms() {
        try {
            if (this.mNonWakeupNosManager != null) {
                this.mNonWakeupNosManager.deregisterAll(new ComponentName(this.mContext, HeartbeatBroadcastReceiver.class));
                this.mNonWakeupNosManager.deregisterAll(new ComponentName(this.mContext, NosNotificationReceiver.class));
            }
            if (this.mWakeupNosManager == null) {
                return;
            }
            this.mWakeupNosManager.deregisterAll(new ComponentName(this.mContext, HeartbeatBroadcastReceiver.class));
            this.mWakeupNosManager.deregisterAll(new ComponentName(this.mContext, NosNotificationReceiver.class));
        } catch (Exception e) {
            log.warn("clearNosAlarms", GeneratedOutlineSupport1.outline68("Couldn't clear NOS alarms, got exception: ", e), new Object[0]);
        }
    }

    protected HeartbeatConnectionHealthManager createConnectionHealthManager() {
        return new HeartbeatConnectionHealthManager(this.mSocketManager, this.mHeartbeatNotificationScheduler, this.mMessageRouter, this.mPowerManager, this.mConnectivityMonitor);
    }

    protected ConnectivityMonitor createConnectivityMonitor(Context context) {
        return new ConnectivityMonitorImpl(context, this.mConnectivityManager);
    }

    protected DirectorServiceClient createDirectorServiceClient(MapAccountManagerWrapper mapAccountManagerWrapper, Context context, WorkExecutor workExecutor) {
        DirectorServiceClient directorServiceClient = new DirectorServiceClient(mapAccountManagerWrapper, workExecutor, context);
        directorServiceClient.initialize();
        return directorServiceClient;
    }

    protected NosHeartbeatNotificationScheduler createHeartbeatNotificationScheduler(Context context) {
        return new NosHeartbeatNotificationScheduler(context, this.mNosManager, this.mHeartbeatIntervalDeterminer);
    }

    protected IdentityResolver createIdentityResolver(DirectorServiceClient directorServiceClient) {
        RemoteSettingIdentityResolver remoteSettingIdentityResolver = new RemoteSettingIdentityResolver(directorServiceClient);
        remoteSettingIdentityResolver.initialize();
        return remoteSettingIdentityResolver;
    }

    protected NetworkAwareHeartbeatIntervalDeterminer createNetworkAwareHeartbeatIntervalDeterminer(Context context) {
        HeartbeatControlMessageHandler heartbeatControlMessageHandler = new HeartbeatControlMessageHandler(new HeartbeatControlApplicationProtocol(new HexStreamCodec()));
        try {
            this.mMessageRouter.registerMessageHandler(102, heartbeatControlMessageHandler);
        } catch (DuplicateHandlerException unused) {
            this.mMessageRouter.deregisterMessageHandler(102);
            try {
                this.mMessageRouter.registerMessageHandler(102, heartbeatControlMessageHandler);
            } catch (Exception unused2) {
                log.error("createNetworkAwareHeartbeatIntervalDeterminer", "unexpected failure while registering handler for heartbeat control channel", "channel", 102);
            }
        } catch (RegistrationFailedException unused3) {
            log.error("createNetworkAwareHeartbeatIntervalDeterminer", "unexpected failure while registering handler for heartbeat control channel", "channel", 102);
        }
        return new NetworkAwareHeartbeatIntervalDeterminer(this.mConnectivityManager, this.mConnectivityMonitor, this.mWifiManager, new HeartbeatIntervalDeterminerPersistenceStore(context), heartbeatControlMessageHandler);
    }

    protected NetworkStabilityMonitor createNetworkStabilityMonitor() {
        NetworkStabilityMonitorImpl networkStabilityMonitorImpl = new NetworkStabilityMonitorImpl(this.mConnectivityMonitor, this.mConnectivityManager, this.mWifiManager, this.mBackoffScheduler, this.mNetworkStabilityMonitorThreadPool);
        updateNetworkStabilityMonitorSettings(networkStabilityMonitorImpl);
        this.mNetworkStabilityUpdateListener.setUpNetworkStabilityMonitor(networkStabilityMonitorImpl);
        return networkStabilityMonitorImpl;
    }

    protected ScreenEventMonitor createScreenEventMonitor(Context context) {
        return new ScreenEventMonitorImpl(context);
    }

    @Override // com.amazon.communication.ITCommService
    public IBinder getBinder() {
        return this.mBinder;
    }

    protected boolean hasSystemFeature(String str) {
        return this.mContext.getPackageManager().hasSystemFeature(str);
    }

    @Override // com.amazon.communication.ITCommService
    public void initialize(Context context, final CountDownLatch countDownLatch) {
        log.info("initialize", "entering", new Object[0]);
        FailFast.expectTrue(Looper.myLooper() == Looper.getMainLooper(), "Initialization must be run on the main thread");
        this.mTCommServiceInitTimeStamp = System.nanoTime();
        this.mContext = context;
        this.mHandler = new Handler();
        this.mKillSwitch = new TCommKillSwitch(this.mContext);
        this.mPowerManager = newPowerManager();
        this.mWorkExecutor = new ProtocolSocketAffinitizedBackgroundWorkExecutor(7, this.mPowerManager.newWakeLock(1, WORK_EXECUTOR_TAG));
        this.mWorkExecutor.doBackgroundWork(new Callable<Void>() { // from class: com.amazon.communication.TCommService.5
            @Override // java.util.concurrent.Callable
            public Void call() {
                TCommService.this.backgroundInitialization(countDownLatch);
                return null;
            }
        });
    }

    protected ConnectivityManagerWrapperImpl newConnectivityManagerWrapperImpl() {
        return new ConnectivityManagerWrapperImpl((ConnectivityManager) this.mContext.getSystemService("connectivity"), (TelephonyManager) this.mContext.getSystemService("phone"));
    }

    protected PowerManagerWrapper newPowerManager() {
        return new PowerManagerWrapperImpl(this.mContext);
    }

    protected void onInitializationComplete() {
        log.debug("onInitializationComplete", "Performing initialization complete actions", new Object[0]);
        this.mContext.startService(new Intent(this.mContext, GatewayConnectionService.class));
        this.mContext.sendBroadcast(new Intent(CommunicationServiceConstants.COMMUNICATION_SERVICE_INITIALIZED));
        this.mWorkExecutor.doBackgroundWork(new Callable<Void>() { // from class: com.amazon.communication.TCommService.8
            @Override // java.util.concurrent.Callable
            public Void call() {
                TCommService.this.postInitialize();
                return null;
            }
        });
    }

    protected void postInitialize() {
        log.debug("postInitialize", "registering echo message handler", new Object[0]);
        if (!this.mServiceInitialized.get()) {
            log.warn("postInitialize", "service is not initialized, aborting", new Object[0]);
            return;
        }
        try {
            ICommunicationManager communicationManager = CommunicationFactoryBase.getCommunicationManager(this.mContext);
            communicationManager.registerMessageHandler(Channels.GATEWAY_ECHO_TEST_CHANNEL, new EchoMessageHandler(communicationManager, Channels.GATEWAY_ECHO_TEST_CHANNEL));
        } catch (RegistrationFailedException e) {
            log.error("postInitialize", "unable to register EchoMessageHandler", "Channels.GATEWAY_ECHO_TEST_CHANNEL", Integer.valueOf((int) Channels.GATEWAY_ECHO_TEST_CHANNEL), e);
        }
    }

    protected int readSTRWakeUpSetting() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), STR_WAKE_UP_ENABLED_KEY, 0);
    }

    protected void registerSTRContentObserver() {
        this.mContext.getContentResolver().registerContentObserver(this.STR_SYSTEM_SETTING_URI, false, new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.amazon.communication.TCommService.4
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                super.onChange(z);
                int readSTRWakeUpSetting = TCommService.this.readSTRWakeUpSetting();
                TCommService.log.info("onChange", GeneratedOutlineSupport1.outline49("Change in STR_STATUS detected, newValue: ", readSTRWakeUpSetting), new Object[0]);
                if (readSTRWakeUpSetting == 0) {
                    TCommService.this.useNonWakeupAlarms();
                } else if (readSTRWakeUpSetting != 1) {
                } else {
                    TCommService.this.useWakeupAlarms();
                }
            }
        });
    }

    @Override // com.amazon.communication.ITCommService
    public void routeMessage(EndpointIdentity endpointIdentity, Message message, int i) {
        this.mMessageRouter.routeMessage(endpointIdentity, message, i);
    }

    @Override // com.amazon.communication.ITCommService
    public void routeMessageFragment(ParcelableEndpointIdentity parcelableEndpointIdentity, int i, MessageEnvelope messageEnvelope, boolean z, int i2) {
        this.mMessageRouter.routeMessageFragment(parcelableEndpointIdentity, i, messageEnvelope.toMessage(), z, i2);
    }

    @Override // com.amazon.communication.ITCommService
    public void shutdown() {
        log.info("shutdown", "shutting down tcomm", new Object[0]);
        this.mServiceInitialized.set(false);
        if (this.mMetricEvent != null) {
            this.mMetricEvent.addTimer("TCommServiceLifeTime", TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.mTCommServiceInitTimeStamp));
        }
        try {
            this.mContext.stopService(new Intent(this.mContext, GatewayConnectionService.class));
        } catch (IllegalStateException e) {
            log.warn("shutdown", "Unable to stop GatewayConnectionService.", e);
        }
        AlwaysOnSocketWatchdogManager alwaysOnSocketWatchdogManager = this.mWatchdogManager;
        if (alwaysOnSocketWatchdogManager != null) {
            alwaysOnSocketWatchdogManager.shutdown();
            this.mWatchdogManager = null;
        }
        MessageRouterImpl messageRouterImpl = this.mMessageRouter;
        if (messageRouterImpl != null) {
            messageRouterImpl.deregisterMessageHandler(Channels.GW_HANDSHAKE_CHANNEL);
            this.mMessageRouter.deregisterMessageHandler(102);
            this.mMessageRouter.deregisterMessageHandler(99);
            this.mMessageRouter.deregisterUndeliverableMessageHandler();
        }
        DeviceReliableMessageProtocol deviceReliableMessageProtocol = this.mReliableMessageProtocol;
        if (deviceReliableMessageProtocol != null) {
            deviceReliableMessageProtocol.shutDown();
        }
        CommunicationEngine communicationEngine = this.mCommunicationEngine;
        if (communicationEngine != null) {
            communicationEngine.shutdown();
            this.mCommunicationEngine = null;
        }
        HeartbeatConnectionHealthManager heartbeatConnectionHealthManager = this.mConnectionHealthManager;
        if (heartbeatConnectionHealthManager != null) {
            heartbeatConnectionHealthManager.shutdown();
            this.mConnectionHealthManager = null;
        }
        NetworkStabilityMonitor networkStabilityMonitor = this.mNetworkStabilityMonitor;
        if (networkStabilityMonitor != null) {
            networkStabilityMonitor.stop();
            this.mNetworkStabilityMonitor = null;
        }
        WakeLockHoldingScheduledThreadPoolExecutor wakeLockHoldingScheduledThreadPoolExecutor = this.mNetworkStabilityMonitorThreadPool;
        if (wakeLockHoldingScheduledThreadPoolExecutor != null) {
            wakeLockHoldingScheduledThreadPoolExecutor.properShutdown();
            this.mNetworkStabilityMonitorThreadPool = null;
        }
        ScreenEventMonitor screenEventMonitor = this.mScreenEventMonitor;
        if (screenEventMonitor != null) {
            screenEventMonitor.stop();
            this.mScreenEventMonitor = null;
        }
        ConnectivityMonitor connectivityMonitor = this.mConnectivityMonitor;
        if (connectivityMonitor != null) {
            connectivityMonitor.stop();
            this.mConnectivityMonitor = null;
        }
        NetworkAwareHeartbeatIntervalDeterminer networkAwareHeartbeatIntervalDeterminer = this.mHeartbeatIntervalDeterminer;
        if (networkAwareHeartbeatIntervalDeterminer != null) {
            networkAwareHeartbeatIntervalDeterminer.shutdown();
            this.mHeartbeatIntervalDeterminer = null;
        }
        HeartbeatIntervalDeterminer heartbeatIntervalDeterminer = this.mConservativeHeartbeatIntervalDeterminer;
        if (heartbeatIntervalDeterminer != null) {
            heartbeatIntervalDeterminer.shutdown();
            this.mConservativeHeartbeatIntervalDeterminer = null;
        }
        DeviceSocketManager deviceSocketManager = this.mSocketManager;
        if (deviceSocketManager != null) {
            deviceSocketManager.shutdown();
            this.mContext.unregisterReceiver(this.mSocketManager.getAccountsUpdatedReceiver());
            this.mSocketManager = null;
        }
        WorkExecutor workExecutor = this.mWorkExecutor;
        if (workExecutor != null) {
            workExecutor.shutdown();
            this.mWorkExecutor = null;
        }
        DirectorServiceClient directorServiceClient = this.mDirectorServiceClient;
        if (directorServiceClient != null) {
            directorServiceClient.shutdown();
            this.mContext.unregisterReceiver(this.mDirectorServiceClient.getAccountsUpdatedReceiver());
            this.mDirectorServiceClient = null;
        }
        StandardMetricReporter standardMetricReporter = this.mMetricReporter;
        if (standardMetricReporter != null) {
            standardMetricReporter.stop();
            this.mMetricReporter = null;
        }
        TCommKillSwitch tCommKillSwitch = this.mKillSwitch;
        if (tCommKillSwitch != null) {
            tCommKillSwitch.shutdown();
            this.mKillSwitch = null;
        }
        PeriodicMetricReporter periodicMetricReporter = this.mPeriodicMetricReporter;
        if (periodicMetricReporter != null) {
            periodicMetricReporter.shutdown();
            this.mPeriodicMetricReporter = null;
        }
        try {
            this.mContext.unregisterReceiver(RemoteSettingManager.mConfigSyncReceiver);
            this.mContext.unregisterReceiver(RemoteSettingManager.mStageSwitchReceiver);
        } catch (IllegalArgumentException e2) {
            log.warn("shutdown", "Unable to unregister a RemoteSettingManager receiver, but continuing with Service shutdown", ADMRegistrationConstants.CALL_EXCEPTION, e2);
        }
        RemoteSettingManager.removeSettingUpdateListener(this.mNetworkStabilityUpdateListener);
        this.mWifiManager = null;
        this.mPowerManager = null;
    }

    protected void updateNosManager(NetworkOptimizationManager networkOptimizationManager) {
        BackoffScheduler backoffScheduler = this.mBackoffScheduler;
        if (backoffScheduler == null || this.mHeartbeatNotificationScheduler == null) {
            return;
        }
        if (backoffScheduler instanceof NosBackoffScheduler) {
            ((NosBackoffScheduler) backoffScheduler).setNosManager(networkOptimizationManager);
        }
        HeartbeatNotificationScheduler heartbeatNotificationScheduler = this.mHeartbeatNotificationScheduler;
        if (!(heartbeatNotificationScheduler instanceof NosHeartbeatNotificationScheduler)) {
            return;
        }
        ((NosHeartbeatNotificationScheduler) heartbeatNotificationScheduler).setNosManager(networkOptimizationManager);
    }

    public void useNonWakeupAlarms() {
        if (this.mNonWakeupNosManager == null) {
            this.mNonWakeupNosManager = NoWakeUpNetworkOptimizationManagerImpl.getInstance(this.mContext);
        }
        if (this.mNosManager != this.mNonWakeupNosManager) {
            log.info("useNonWakeupAlarms", "Switching to non-wakeup alarms", new Object[0]);
            this.mNosManager = this.mNonWakeupNosManager;
            updateNosManager(this.mNosManager);
            return;
        }
        log.info("useNonWakeupAlarms", "Already using non-wakeup alarms, ignoring.", new Object[0]);
    }

    public void useWakeupAlarms() {
        if (this.mWakeupNosManager == null) {
            this.mWakeupNosManager = GenericNetworkOptimizationManagerImpl.getInstance(this.mContext);
        }
        if (this.mNosManager != this.mWakeupNosManager) {
            log.info("useWakeupAlarms", "Switching to wakeup alarms", new Object[0]);
            this.mNosManager = this.mWakeupNosManager;
            updateNosManager(this.mNosManager);
            return;
        }
        log.info("useWakeupAlarms", "Already using wakeup alarms, ignoring.", new Object[0]);
    }
}
