package com.amazon.communication;

import amazon.communication.CommunicationFactoryBase;
import amazon.communication.DuplicateHandlerException;
import amazon.communication.ICommunicationManager;
import amazon.communication.Message;
import amazon.communication.RegistrationFailedException;
import amazon.communication.connection.Channels;
import amazon.communication.connection.Policy;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.IdentityResolver;
import amazon.speech.simclient.common.InvocationStatus;
import amazon.speech.simclient.genericconnection.GenericConnectionClient;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
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
import com.amazon.communication.gw.CsmDeviceGatewayHandshakeHandler;
import com.amazon.communication.gw.DeviceGatewayApplicationProtocol;
import com.amazon.communication.gw.GatewayConnectivityListener;
import com.amazon.communication.gw.GatewayControlProtocol;
import com.amazon.communication.gw.GatewayHandshakeProtocol;
import com.amazon.communication.ir.IIdentityResolver;
import com.amazon.communication.ir.RemoteSettingIdentityResolver;
import com.amazon.communication.ir.ServiceSideIdentityResolverProxy;
import com.amazon.communication.remotesetting.ConfigurationSyncReceiver;
import com.amazon.communication.remotesetting.ReflectionHelper;
import com.amazon.communication.remotesetting.RemoteSettingManager;
import com.amazon.communication.rlm.IAckHandler;
import com.amazon.communication.socket.SocketAcquisitionFailedException;
import com.amazon.communication.socket.SocketUsageAggregator;
import com.amazon.communication.socket.decisionengine.CsmSocketDecisionEngine;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.codahale.metrics.SharedMetricRegistries;
import com.dp.framework.HexStreamCodec;
import com.dp.utils.FailFast;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public class CsmTCommService implements ITCommService {
    public static final int MESSAGE_QUEUE_SIZE = 50;
    private static final String METRICS_SOURCE_NAME = "CsmTCommService";
    private static final int NUM_HANDLER_THREADS = 1;
    private static final int PERIODIC_METRIC_REPORT_PERIOD_HRS = 1;
    public static final String STAGE_SWITCH_INTENT = "com.amazon.device.environment.action.SWITCH_STAGE";
    private static final String USE_TCOMM_PERMISSION = "amazon.permission.USE_TCOMM";
    private static final long WAIT_LATCH_TIMEOUT_MS = 5000;
    private static final String WORK_EXECUTOR_TAG = "TComm.WorkExecutor";
    private static GenericConnectionClient mGenericConnectionClient;
    protected MapAccountManagerWrapper mAccountManager;
    protected BufferedMessageToInputStreamResponseRouter mBufferedMessageToResponseRouter;
    protected NullByteAccountant mByteAccountant;
    protected ChannelRestrictor mChannelRestrictor;
    protected CsmCommunicationEngine mCommunicationEngine;
    protected Context mContext;
    protected ChannelAwareD2DMessageRouter mD2DMessageRouter;
    protected D2DNotificationRouter mD2DNotificationRouter;
    private DeviceUndeliverableMessageHandler mDeviceUndeliverableMessageHandler;
    protected DirectorServiceClient mDirectorServiceClient;
    private GatewayConnectivityListener mGatewayConnectivityListener;
    protected CsmDeviceGatewayHandshakeHandler mGatewayHandshakeHandler;
    protected IdentityResolver mIdentityResolver;
    protected TCommKillSwitch mKillSwitch;
    protected MessageRouterImpl mMessageRouter;
    private MetricEvent mMetricEvent;
    protected StandardMetricReporter mMetricReporter;
    protected PeriodicMetricReporter mPeriodicMetricReporter;
    protected PowerManagerWrapper mPowerManager;
    protected ProtocolHandlerManager mProtocolHandlerManager;
    protected DcpRequestSigner mRequestSigner;
    protected ResponseRouter mResponseRouter;
    protected ServiceSideMessageRouter mServiceSideMessageRouter;
    protected CsmSocketDecisionEngine mSocketDecisionEngine;
    protected CsmSocketManager mSocketManager;
    protected SocketUsageAggregator mSocketUsageAggregator;
    private long mTCommServiceInitTimeStamp;
    protected WorkExecutor mWorkExecutor;
    private static final DPLogger log = new DPLogger("TComm.CsmTCommService");
    protected static ReflectionHelper mReflectionHelper = new ReflectionHelper();
    public static final String[] ARCUS_SYNC_INTENTS = {ConfigurationSyncReceiver.ACTION_PERIODIC_SYNC, ConfigurationSyncReceiver.ACTION_RETRY_SYNC, "android.intent.action.BOOT_COMPLETED", BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION};
    public static final IntentFilter ARCUS_SYNC_INTENT_FILTER = new IntentFilter() { // from class: com.amazon.communication.CsmTCommService.1
        {
            for (String str : CsmTCommService.ARCUS_SYNC_INTENTS) {
                addAction(str);
            }
        }
    };
    public static final IntentFilter STAGE_SWITCH_INTENT_FILTER = new IntentFilter() { // from class: com.amazon.communication.CsmTCommService.2
        {
            addAction("com.amazon.device.environment.action.SWITCH_STAGE");
        }
    };
    protected final AtomicBoolean mServiceInitialized = new AtomicBoolean(false);
    private final HexStreamCodec mHexStreamCodec = new HexStreamCodec();
    private final IBinder mBinder = new ICommunicationService.Stub() { // from class: com.amazon.communication.CsmTCommService.4
        private void enforcePermissionOnIpc() throws RemoteException {
            if (Binder.getCallingUid() == Process.myUid() || CsmTCommService.this.mContext.checkCallingPermission(CsmTCommService.USE_TCOMM_PERMISSION) == 0) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Permission denied: Process: ");
            outline107.append(Binder.getCallingPid());
            outline107.append(" is missing permission: ");
            outline107.append(CsmTCommService.USE_TCOMM_PERMISSION);
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

        @Override // com.amazon.communication.ICommunicationService
        public IConnection acquireConnection(ParcelableEndpointIdentity parcelableEndpointIdentity, ParcelableConnectionPolicy parcelableConnectionPolicy, IConnectionListener iConnectionListener, ParcelableStatus parcelableStatus) throws RemoteException {
            enforcePermissionOnIpc();
            return acquireConnectionEx(parcelableEndpointIdentity, new ParcelablePolicy(new Policy.Builder().fromConnectionPolicy(parcelableConnectionPolicy)), iConnectionListener, parcelableStatus);
        }

        @Override // com.amazon.communication.ICommunicationService
        public IConnection acquireConnectionEx(ParcelableEndpointIdentity parcelableEndpointIdentity, ParcelablePolicy parcelablePolicy, IConnectionListener iConnectionListener, ParcelableStatus parcelableStatus) throws RemoteException {
            IConnection acquireConnection;
            enforcePermissionOnIpc();
            if (parcelableEndpointIdentity != null) {
                CsmTCommService.log.debug("acquireConnectionEx", "acquire", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, parcelableEndpointIdentity, "calling pid", Integer.valueOf(Binder.getCallingPid()), "myPid", Integer.valueOf(Process.myPid()));
                Thread.currentThread().setUncaughtExceptionHandler(new TCommUncaughtExceptionHandler());
                try {
                    if (isInitialized()) {
                        try {
                            CsmTCommService.log.debug("acquireConnectionEx", "This thread is about to try to acquire the lock", new Object[0]);
                            synchronized (this) {
                                acquireConnection = CsmTCommService.this.acquireConnection(parcelableEndpointIdentity, null, iConnectionListener);
                                parcelableStatus.setStatusCode(0);
                            }
                            CsmTCommService.log.debug("acquireConnectionEx", "this thread no longer has the lock", new Object[0]);
                            return acquireConnection;
                        } catch (Exception e) {
                            String str = "Could not acquire connection. Reason: " + (e.getMessage() == null ? "Usually because of no connectivity." : e.getMessage());
                            CsmTCommService.log.error("acquireConnectionEx", str, e);
                            parcelableStatus.setStatusCode(1);
                            parcelableStatus.setStatusMessage(str);
                            CsmTCommService.log.debug("acquireConnectionEx", "this thread no longer has the lock", new Object[0]);
                            return null;
                        }
                    }
                    parcelableStatus.setStatusCode(8);
                    parcelableStatus.setStatusMessage("CommunicationService has not been started.");
                    return null;
                } catch (Throwable th) {
                    CsmTCommService.log.debug("acquireConnectionEx", "this thread no longer has the lock", new Object[0]);
                    throw th;
                }
            }
            throw new IllegalArgumentException("identity cannot be null");
        }

        @Override // com.amazon.communication.ICommunicationService
        public void deregisterMessageHandler(int i) throws RemoteException {
            enforcePermissionOnIpc();
            CsmTCommService.this.mServiceSideMessageRouter.deregisterMessageHandler(i);
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
                ServiceSideGatewayConnectivityProxy serviceSideGatewayConnectivityProxy = new ServiceSideGatewayConnectivityProxy(CsmTCommService.this.mGatewayConnectivityListener, iConnectionListener);
                CsmTCommService.this.mGatewayConnectivityListener.addListener(serviceSideGatewayConnectivityProxy);
                return IGatewayConnectivity.Stub.asInterface(serviceSideGatewayConnectivityProxy);
            } catch (RemoteException unused) {
                CsmTCommService.log.verbose("getGatewayConnectivity", "client callback died before GatewayConnectivity object was created", new Object[0]);
                return null;
            }
        }

        @Override // com.amazon.communication.ICommunicationService
        public IIdentityResolver getIdentityResolver() throws RemoteException {
            enforcePermissionOnIpc();
            CsmTCommService csmTCommService = CsmTCommService.this;
            if (csmTCommService.mDirectorServiceClient == null) {
                csmTCommService.mDirectorServiceClient = new DirectorServiceClient(csmTCommService.mAccountManager, csmTCommService.mWorkExecutor, csmTCommService.mContext);
                CsmTCommService.this.mDirectorServiceClient.initialize();
            }
            return IIdentityResolver.Stub.asInterface(new ServiceSideIdentityResolverProxy(CsmTCommService.this.mIdentityResolver));
        }

        @Override // com.amazon.communication.ICommunicationService
        public boolean isInitialized() {
            return CsmTCommService.this.mServiceInitialized.get();
        }

        @Override // com.amazon.communication.ICommunicationService
        public int registerMessageHandler(int i, IMessageHandler iMessageHandler) throws RemoteException {
            enforcePermissionOnIpc();
            return CsmTCommService.this.mServiceSideMessageRouter.registerMessageHandler(i, iMessageHandler);
        }

        @Override // com.amazon.communication.ICommunicationService
        public void removeAckHandler() {
            throw new UnsupportedOperationException("RLM (ReliableMessageProtocol) use cases are not supported.");
        }

        @Override // com.amazon.communication.ICommunicationService
        public void routeMessage(ParcelableEndpointIdentity parcelableEndpointIdentity, MessageEnvelope messageEnvelope, int i) throws RemoteException {
            enforceSameUidOnIpc();
            CsmTCommService.this.routeMessage(parcelableEndpointIdentity, messageEnvelope.toMessage(), i);
        }

        @Override // com.amazon.communication.ICommunicationService
        public void routeMessageFragment(ParcelableEndpointIdentity parcelableEndpointIdentity, int i, MessageEnvelope messageEnvelope, boolean z, int i2) throws RemoteException {
            enforceSameUidOnIpc();
            CsmTCommService.this.routeMessageFragment(parcelableEndpointIdentity, i, messageEnvelope, z, i2);
        }

        @Override // com.amazon.communication.ICommunicationService
        public int setAckHandler(IAckHandler iAckHandler) {
            throw new UnsupportedOperationException("RLM (ReliableMessageProtocol) use cases are not supported.");
        }
    };

    public CsmTCommService(GenericConnectionClient genericConnectionClient) {
        mGenericConnectionClient = genericConnectionClient;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IConnection acquireConnection(ParcelableEndpointIdentity parcelableEndpointIdentity, String str, IConnectionListener iConnectionListener) throws SocketAcquisitionFailedException {
        log.info("acquireConnection", "entering", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, parcelableEndpointIdentity.toLogSafeString(), "directedIdHash", Integer.valueOf(str != null ? str.hashCode() : 0), ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, iConnectionListener);
        try {
            ServiceSideConnectionProxy serviceSideConnectionProxy = new ServiceSideConnectionProxy(this.mResponseRouter, iConnectionListener, this.mByteAccountant, this.mPeriodicMetricReporter);
            serviceSideConnectionProxy.setProtocolSocket(this.mCommunicationEngine.acquireProtocolSocket(parcelableEndpointIdentity, null, null, str));
            return IConnection.Stub.asInterface(serviceSideConnectionProxy);
        } catch (RemoteException unused) {
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

    public static GenericConnectionClient getGenericConnectionClient() {
        return mGenericConnectionClient;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v23, types: [com.amazon.communication.gw.GatewayControlProtocol] */
    /* JADX WARN: Type inference failed for: r5v28, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v36 */
    /* JADX WARN: Type inference failed for: r5v37 */
    public void backgroundInitialization(CountDownLatch countDownLatch) {
        String str;
        DeviceGatewayApplicationProtocol deviceGatewayApplicationProtocol;
        boolean z;
        String str2;
        log.info("backgroundInitialization", "entering", new Object[0]);
        FailFast.expectFalse(Looper.myLooper() == Looper.getMainLooper(), "backgroundInitialization must not be run on the main thread");
        try {
            this.mMetricReporter = StandardMetricReporter.create(this.mContext, SharedMetricRegistries.getOrCreate(RouteName.MAIN), null);
            if (this.mMetricReporter != null) {
                this.mMetricReporter.start();
            }
            this.mByteAccountant = new NullByteAccountant();
            this.mChannelRestrictor = createChannelRestrictor();
            this.mProtocolHandlerManager = new ProtocolHandlerManagerImpl();
            this.mPeriodicMetricReporter = new PeriodicMetricReporterImpl(AndroidMetricsFactoryImpl.getInstance(this.mContext), TCommMetrics.PROGRAM_ID, "CsmTCommService");
            this.mPeriodicMetricReporter.startRecordingPeriodically(1L, TimeUnit.HOURS);
            this.mMetricEvent = this.mPeriodicMetricReporter.createTrackedMetricEvent(TCommMetrics.PROGRAM_ID, "CsmTCommService");
            this.mMetricEvent.addCounter(TCommMetrics.TCOMM_IMPL_CHOSEN_CSM, 1.0d);
            this.mMessageRouter = new MessageRouterImpl();
            this.mResponseRouter = new ResponseRouterImpl();
            this.mD2DNotificationRouter = new IntentLaunchingD2DNotificationRouter(this.mContext);
            this.mD2DMessageRouter = new ChannelAwareD2DMessageRouterImpl(this.mMessageRouter, this.mD2DNotificationRouter);
            this.mServiceSideMessageRouter = new ServiceSideMessageRouter(this.mMessageRouter, this.mByteAccountant);
            this.mRequestSigner = new DcpRequestSigner(this.mContext);
            this.mSocketUsageAggregator = new SocketUsageAggregator();
            this.mAccountManager = new MapAccountManagerWrapperImpl(this.mContext);
            this.mGatewayConnectivityListener = new GatewayConnectivityListener();
            this.mIdentityResolver = new RemoteSettingIdentityResolver(this.mDirectorServiceClient);
            ((RemoteSettingIdentityResolver) this.mIdentityResolver).initialize();
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
            this.mBufferedMessageToResponseRouter = new BufferedMessageToInputStreamResponseRouter(this.mResponseRouter);
            deviceGatewayApplicationProtocol = new DeviceGatewayApplicationProtocol(this.mHexStreamCodec, this.mMessageRouter, this.mResponseRouter);
            str = new GatewayControlProtocol(this.mHexStreamCodec);
            GatewayHandshakeProtocol gatewayHandshakeProtocol = new GatewayHandshakeProtocol();
            gatewayHandshakeProtocol.setStreamCodec(this.mHexStreamCodec);
            this.mGatewayHandshakeHandler = new CsmDeviceGatewayHandshakeHandler(new MapAccountManagerWrapperImpl(this.mContext), new AdpAuthenticationProvider(this.mContext), gatewayHandshakeProtocol, this.mWorkExecutor, this.mPeriodicMetricReporter);
            try {
                try {
                    this.mMessageRouter.registerMessageHandler(Channels.GW_HANDSHAKE_CHANNEL, this.mGatewayHandshakeHandler);
                } catch (DuplicateHandlerException e2) {
                    log.error("backgroundInitialization", "error registering GatewayHandshakeHandler", e2);
                }
            } catch (RegistrationFailedException e3) {
                log.error("backgroundInitialization", "error registering GatewayHandshakeHandler", e3);
            }
            this.mSocketManager = new CsmSocketManager(this.mWorkExecutor, this.mProtocolHandlerManager, this.mPeriodicMetricReporter);
            this.mSocketManager.addSocketStateListener(this.mGatewayHandshakeHandler);
            this.mSocketManager.addSocketStateListener(this.mGatewayConnectivityListener);
            this.mSocketDecisionEngine = new CsmSocketDecisionEngine(this.mSocketManager, this.mWorkExecutor, deviceGatewayApplicationProtocol, str, this.mPeriodicMetricReporter);
        } catch (Exception e4) {
            e = e4;
            str = "backgroundInitialization";
        }
        try {
            try {
                this.mCommunicationEngine = new CsmCommunicationEngine(this.mSocketManager, this.mMessageRouter, this.mResponseRouter, this.mBufferedMessageToResponseRouter, this.mD2DNotificationRouter, this.mD2DMessageRouter, this.mProtocolHandlerManager, this.mIdentityResolver, this.mByteAccountant, this.mChannelRestrictor, this.mSocketDecisionEngine, deviceGatewayApplicationProtocol, str, this.mHexStreamCodec);
                mGenericConnectionClient.queryConnectionStatus(this.mCommunicationEngine);
                mGenericConnectionClient.registerConnectionStatusCallback(this.mCommunicationEngine);
                mGenericConnectionClient.registerDownstreamMessageCallback(this.mCommunicationEngine);
                this.mDeviceUndeliverableMessageHandler = new DeviceUndeliverableMessageHandler(this.mCommunicationEngine);
                try {
                    this.mMessageRouter.registerUndeliverableMessageHandler(this.mDeviceUndeliverableMessageHandler);
                    str2 = "backgroundInitialization";
                } catch (DuplicateHandlerException e5) {
                    String str3 = "backgroundInitialization";
                    z = true;
                    log.error(str3, "error registering UndeliverableMessageHandler", e5);
                    str = str3;
                } catch (RegistrationFailedException e6) {
                    String str4 = "backgroundInitialization";
                    log.error(str4, "error registering UndeliverableMessageHandler", e6);
                    str2 = str4;
                }
                z = true;
                str = str2;
                this.mServiceInitialized.set(z);
                log.verbose(str, "completed", new Object[0]);
                this.mMetricEvent.addTimer("CsmTCommServiceInitializeTime", TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.mTCommServiceInitTimeStamp));
                onInitializationComplete();
                countDownLatch.countDown();
            } catch (Exception e7) {
                e = e7;
                str = "backgroundInitialization";
                log.error(str, "initialization failed", e);
                shutdown();
                FailFast.expectTrue(false, "initialization failed");
            }
        } catch (Exception e8) {
            e = e8;
            log.error(str, "initialization failed", e);
            shutdown();
            FailFast.expectTrue(false, "initialization failed");
        }
    }

    @Override // com.amazon.communication.ITCommService
    public IBinder getBinder() {
        return this.mBinder;
    }

    @Override // com.amazon.communication.ITCommService
    public void initialize(Context context, final CountDownLatch countDownLatch) {
        log.info("initialize", "entering", new Object[0]);
        FailFast.expectTrue(Looper.myLooper() == Looper.getMainLooper(), "Initialization must be run on the main thread");
        this.mTCommServiceInitTimeStamp = System.nanoTime();
        this.mContext = context;
        this.mKillSwitch = new TCommKillSwitch(this.mContext);
        this.mPowerManager = newPowerManager();
        this.mWorkExecutor = new ProtocolSocketAffinitizedBackgroundWorkExecutor(1, this.mPowerManager.newWakeLock(1, WORK_EXECUTOR_TAG));
        this.mWorkExecutor.doBackgroundWork(new Callable<Void>() { // from class: com.amazon.communication.CsmTCommService.3
            @Override // java.util.concurrent.Callable
            public Void call() {
                CsmTCommService.this.backgroundInitialization(countDownLatch);
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
        this.mWorkExecutor.doBackgroundWork(new Callable<Void>() { // from class: com.amazon.communication.CsmTCommService.5
            @Override // java.util.concurrent.Callable
            public Void call() {
                CsmTCommService.this.postInitialize();
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
        if (this.mMetricEvent != null) {
            this.mMetricEvent.addTimer("CsmTCommServiceLifeTime", TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.mTCommServiceInitTimeStamp));
        }
        this.mServiceInitialized.set(false);
        try {
            this.mContext.stopService(new Intent(this.mContext, GatewayConnectionService.class));
        } catch (IllegalStateException e) {
            log.warn("shutdown", "Unable to stop GatewayConnectionService.", e);
        }
        GenericConnectionClient genericConnectionClient = mGenericConnectionClient;
        if (genericConnectionClient != null) {
            InvocationStatus unregisterConnectionStatusCallback = genericConnectionClient.unregisterConnectionStatusCallback();
            if (unregisterConnectionStatusCallback != InvocationStatus.SUCCESS) {
                log.warn("shutdown", "Unable to unregister ConnectionStatusCallback", "InvocationStatus", unregisterConnectionStatusCallback);
            }
            InvocationStatus unregisterDownstreamMessageCallback = mGenericConnectionClient.unregisterDownstreamMessageCallback();
            if (unregisterDownstreamMessageCallback != InvocationStatus.SUCCESS) {
                log.warn("shutdown", "Unable to unregister DownstreamMessageCallback", "InvocationStatus", unregisterDownstreamMessageCallback);
            }
            mGenericConnectionClient = null;
        }
        MessageRouterImpl messageRouterImpl = this.mMessageRouter;
        if (messageRouterImpl != null) {
            messageRouterImpl.deregisterMessageHandler(Channels.GW_HANDSHAKE_CHANNEL);
            this.mMessageRouter.deregisterMessageHandler(102);
            this.mMessageRouter.deregisterMessageHandler(99);
            this.mMessageRouter.deregisterUndeliverableMessageHandler();
        }
        CsmCommunicationEngine csmCommunicationEngine = this.mCommunicationEngine;
        if (csmCommunicationEngine != null) {
            csmCommunicationEngine.shutdown();
            this.mCommunicationEngine = null;
        }
        CsmSocketManager csmSocketManager = this.mSocketManager;
        if (csmSocketManager != null) {
            csmSocketManager.shutdown();
            this.mSocketManager = null;
        }
        WorkExecutor workExecutor = this.mWorkExecutor;
        if (workExecutor != null) {
            workExecutor.shutdown();
            this.mWorkExecutor = null;
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
        } catch (IllegalArgumentException e2) {
            log.warn("shutdown", "Unable to unregister ConfigurationSyncReceiver, but continuing with Service shutdown", ADMRegistrationConstants.CALL_EXCEPTION, e2);
        }
        try {
            this.mContext.unregisterReceiver(RemoteSettingManager.mStageSwitchReceiver);
        } catch (IllegalArgumentException e3) {
            log.warn("shutdown", "Unable to unregister StageSwitchReceiver, but continuing with Service shutdown", ADMRegistrationConstants.CALL_EXCEPTION, e3);
        }
        this.mPowerManager = null;
    }
}
