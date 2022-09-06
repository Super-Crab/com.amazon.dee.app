package com.amazon.alexa.accessory.internal.session;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryCapabilities;
import com.amazon.alexa.accessory.AccessoryConnection;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionOptions;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.PhonePolicy;
import com.amazon.alexa.accessory.SessionListener;
import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.capabilities.speech.AccessoryIdentifierProvider;
import com.amazon.alexa.accessory.capabilities.transport.TransportCapability;
import com.amazon.alexa.accessory.crypto.AccessoryBoundCryptoKeyDataStore;
import com.amazon.alexa.accessory.crypto.CryptoBundleProvider;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.internal.DefaultTaskManager;
import com.amazon.alexa.accessory.internal.connection.QueueTransportDispatcher;
import com.amazon.alexa.accessory.internal.connection.UnsupportedTransportReceiver;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.functions.Supplier;
import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.ConnectionMetricsReporter;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessory.repositories.calling.CallingRepository;
import com.amazon.alexa.accessory.repositories.cbl.CblRepository;
import com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingRepository;
import com.amazon.alexa.accessory.repositories.device.DeviceRepository;
import com.amazon.alexa.accessory.repositories.device.DeviceSupplier;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessory.repositories.diagnostics.DiagnosticsRepository;
import com.amazon.alexa.accessory.repositories.display.DisplayContentRepository;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareRepository;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2;
import com.amazon.alexa.accessory.repositories.fitness.FitnessRepository;
import com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementRepository;
import com.amazon.alexa.accessory.repositories.inputevents.InputRepository;
import com.amazon.alexa.accessory.repositories.instrumentation.InstrumentationRepository;
import com.amazon.alexa.accessory.repositories.media.MediaRepository;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallingRepository;
import com.amazon.alexa.accessory.repositories.notification.NotificationRepository;
import com.amazon.alexa.accessory.repositories.speech.SpeechRepository;
import com.amazon.alexa.accessory.repositories.state.StateRepository;
import com.amazon.alexa.accessory.repositories.system.SystemRepository;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.amazon.alexa.accessory.transport.TransportReceiver;
import com.amazon.alexa.accessory.transport.TransportVersion;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.UUID;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class BaseAccessorySession implements AccessorySession {
    private final Accessory accessory;
    private final AccessorySessionOptions accessorySessionOptions;
    private final QueueTransportDispatcher.StatefulDrainCondition<Boolean> awaitingKeysCondition;
    private final AccessoryCapabilities capabilities;
    private final AccessoryConnection.ConnectionListener connectionListener;
    private ConnectionMetricsReporter connectionMetricsReporter;
    private final CryptoBundleProvider cryptoBundleProvider;
    private final TransportReceiver defaultReceiver;
    private final QueueTransportDispatcher dispatcherAuthenticationAgnostic;
    private final QueueTransportDispatcher dispatcherAuthenticationAware;
    private final FeatureChecker featureChecker;
    private Disposable keyExchangeDisposable;
    private AccessoryConnection primaryConnection;
    protected boolean released;
    private AccessoryConnection secondaryConnection;
    private AccessoryConnection.ConnectionListener secondaryConnectionListener;
    private final SessionListener sessionListener;
    private final DefaultTaskManager taskManager;
    private final AccessoryTransport.Factory transportFactory;
    private final String uuid;

    /* loaded from: classes.dex */
    public static abstract class Builder<T extends BaseAccessorySession> {
        protected Accessory accessory;
        protected CryptoKeyDataStore accessoryKeyStore;
        protected AccessorySessionOptions accessorySessionOptions;
        protected DeviceSupplier deviceSupplier;
        protected FeatureChecker featureChecker;
        protected SessionListener sessionListener;
        protected AccessoryTransport.Factory transportFactory;

        public final Builder accessory(Accessory accessory) {
            this.accessory = accessory;
            return this;
        }

        public final Builder accessoryKeyStore(CryptoKeyDataStore cryptoKeyDataStore) {
            this.accessoryKeyStore = cryptoKeyDataStore;
            return this;
        }

        public final Builder accessorySessionOptions(AccessorySessionOptions accessorySessionOptions) {
            this.accessorySessionOptions = accessorySessionOptions;
            return this;
        }

        public abstract T build();

        public final Builder deviceSupplier(DeviceSupplier deviceSupplier) {
            this.deviceSupplier = deviceSupplier;
            return this;
        }

        public final Builder featureChecker(FeatureChecker featureChecker) {
            this.featureChecker = featureChecker;
            return this;
        }

        public final Builder sessionListener(SessionListener sessionListener) {
            this.sessionListener = sessionListener;
            return this;
        }

        public final Builder transportFactory(AccessoryTransport.Factory factory) {
            this.transportFactory = factory;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseAccessorySession(Builder builder) {
        Preconditions.mainThread();
        Preconditions.notNull(builder, "builder");
        this.accessory = builder.accessory;
        this.sessionListener = builder.sessionListener;
        this.transportFactory = builder.transportFactory;
        this.accessorySessionOptions = builder.accessorySessionOptions;
        this.featureChecker = builder.featureChecker;
        this.dispatcherAuthenticationAware = new QueueTransportDispatcher();
        this.awaitingKeysCondition = this.dispatcherAuthenticationAware.addDrainCondition(true, $$Lambda$BaseAccessorySession$P4JOxs6shw2pUp1TWb6soSa22UM.INSTANCE);
        this.dispatcherAuthenticationAgnostic = new QueueTransportDispatcher();
        this.capabilities = new AccessoryCapabilities(this.dispatcherAuthenticationAware, this.dispatcherAuthenticationAgnostic);
        this.taskManager = new DefaultTaskManager();
        this.defaultReceiver = new UnsupportedTransportReceiver(this.dispatcherAuthenticationAgnostic, ControlStream.MessageAuthenticationMode.FORCE_UNAUTHENTICATED, new Supplier() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$E8weY1aOnkj_YJMowD2OcDHOIis
            @Override // com.amazon.alexa.accessory.internal.util.functions.Supplier
            public final Object get() {
                return BaseAccessorySession.this.getDeviceRepositoryV2();
            }
        }, AccessorySessionType.ACCESSORY_SESSION);
        this.uuid = UUID.randomUUID().toString();
        this.cryptoBundleProvider = new AccessoryBoundCryptoKeyDataStore(new AccessoryIdentifierProvider() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$-CMKCi3psCyoIXkdcQqTmhkCqpY
            @Override // com.amazon.alexa.accessory.capabilities.speech.AccessoryIdentifierProvider
            public final String getIdentifier() {
                return BaseAccessorySession.this.getAddress();
            }
        }, builder.accessoryKeyStore);
        this.connectionMetricsReporter = new ConnectionMetricsReporter(AccessoryMetricsServiceHolder.getInstance().get(), $$Lambda$D1z_ytlzYvthAoeLOQGoy8VTB4U.INSTANCE);
        this.connectionListener = new AccessoryConnection.ConnectionListener() { // from class: com.amazon.alexa.accessory.internal.session.BaseAccessorySession.1
            @Override // com.amazon.alexa.accessory.AccessoryConnection.ConnectionListener
            public void onConnectionClosed(AccessoryConnection accessoryConnection) {
                Preconditions.mainThread();
                Preconditions.notNull(BaseAccessorySession.this.connectionMetricsReporter, "connectionMetricsReporter");
                BaseAccessorySession.this.release();
                BaseAccessorySession.this.connectionMetricsReporter.connectionClosed();
                BaseAccessorySession.this.sessionListener.onSessionDisconnected();
                AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Session.SESSION_RELEASED, MetricsConstants.Session.SESSION_RELEASED_REASON_CONNECTION_CLOSED, true, null);
            }

            @Override // com.amazon.alexa.accessory.AccessoryConnection.ConnectionListener
            public void onConnectionError(AccessoryConnection accessoryConnection, Throwable th) {
                Preconditions.mainThread();
                Preconditions.notNull(BaseAccessorySession.this.connectionMetricsReporter, "connectionMetricsReporter");
                BaseAccessorySession.this.release();
                BaseAccessorySession.this.connectionMetricsReporter.connectionError(th);
                BaseAccessorySession.this.sessionListener.onSessionFailed(th);
                AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Session.SESSION_RELEASED, MetricsConstants.Session.SESSION_RELEASED_REASON_CONNECTION_ERROR, true, null);
            }

            @Override // com.amazon.alexa.accessory.AccessoryConnection.ConnectionListener
            public void onConnectionKeepAlive(AccessoryConnection accessoryConnection) {
                ControlStream.dispatch(BaseAccessorySession.this.dispatcherAuthenticationAgnostic, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.KEEP_ALIVE).setKeepAlive(System.KeepAlive.getDefaultInstance()).mo10084build()), ControlStream.MessageAuthenticationMode.FORCE_UNAUTHENTICATED);
            }

            @Override // com.amazon.alexa.accessory.AccessoryConnection.ConnectionListener
            public void onConnectionOpened(AccessoryConnection accessoryConnection) {
                Preconditions.mainThread();
                Preconditions.notNull(BaseAccessorySession.this.connectionMetricsReporter, "connectionMetricsReporter");
                BaseAccessorySession.this.connectionMetricsReporter.connectionOpened();
                BaseAccessorySession.this.sessionListener.onSessionConnected();
            }
        };
    }

    private void attachCapabilities(AccessoryConnection accessoryConnection) {
        this.dispatcherAuthenticationAware.setDispatcher(accessoryConnection.getDispatcher());
        this.dispatcherAuthenticationAgnostic.setDispatcher(accessoryConnection.getDispatcher());
        this.capabilities.initialize();
        accessoryConnection.setReceiver(new TransportReceiver() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$BaseAccessorySession$0zcPLIitWlludcguMMDuDvKT7aw
            @Override // com.amazon.alexa.accessory.transport.TransportReceiver
            public final void onDataReceived(SizedSource sizedSource, int i) {
                BaseAccessorySession.this.lambda$attachCapabilities$1$BaseAccessorySession(sizedSource, i);
            }
        });
    }

    private AccessoryConnection createConnection(Accessory accessory, TransportVersion transportVersion) {
        return new AccessoryConnection(this.transportFactory.createTransport(accessory, this.accessorySessionOptions), transportVersion, this.cryptoBundleProvider, getTransportFeaturesProvider(), this.accessorySessionOptions, this.featureChecker);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$new$0(Boolean bool) {
        return !bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resumeDispatchers(AccessoryTransport.Type type, AccessoryTransport.Type type2) {
        if (type == AccessoryTransport.Type.GATT && type2 == AccessoryTransport.Type.RFCOMM && PhonePolicy.suspendLeWritesWhileConnectingRfcomm()) {
            Logger.d("Resuming transport dispatchers");
            this.dispatcherAuthenticationAware.resume();
            this.dispatcherAuthenticationAgnostic.resume();
        }
    }

    private void suspendDispatchers(AccessoryTransport.Type type, AccessoryTransport.Type type2) {
        if (type == AccessoryTransport.Type.GATT && type2 == AccessoryTransport.Type.RFCOMM && PhonePolicy.suspendLeWritesWhileConnectingRfcomm()) {
            Logger.d("Suspending transport dispatchers");
            this.dispatcherAuthenticationAware.suspend();
            this.dispatcherAuthenticationAgnostic.suspend();
        }
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public final void connect() {
        Preconditions.mainThread();
        Preconditions.notNull(this.connectionMetricsReporter, "connectionMetricsReporter");
        if (!this.released) {
            if (this.primaryConnection != null) {
                Logger.d("Session is already being established... connect() was called more than once!");
                return;
            }
            Observable<Boolean> observeOn = getKeyExchangeRepository().queryIsAwaitingDerivedKeys().distinctUntilChanged().observeOn(AndroidSchedulers.mainThread());
            final QueueTransportDispatcher.StatefulDrainCondition<Boolean> statefulDrainCondition = this.awaitingKeysCondition;
            statefulDrainCondition.getClass();
            this.keyExchangeDisposable = observeOn.subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$njBHfOptQjuAW89WEcjdelTvDpg
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    QueueTransportDispatcher.StatefulDrainCondition.this.setState((Boolean) obj);
                }
            });
            this.primaryConnection = createConnection(this.accessory, null);
            this.primaryConnection.addConnectionListener(this.connectionListener);
            this.primaryConnection.addConnectionListener(new AccessoryConnection.SimpleConnectionListener() { // from class: com.amazon.alexa.accessory.internal.session.BaseAccessorySession.2
                @Override // com.amazon.alexa.accessory.AccessoryConnection.SimpleConnectionListener, com.amazon.alexa.accessory.AccessoryConnection.ConnectionListener
                public void onConnectionOpened(AccessoryConnection accessoryConnection) {
                    Preconditions.mainThread();
                    BaseAccessorySession baseAccessorySession = BaseAccessorySession.this;
                    if (baseAccessorySession.released) {
                        return;
                    }
                    baseAccessorySession.taskManager.start();
                }
            });
            attachCapabilities(this.primaryConnection);
            this.connectionMetricsReporter.onOpenCalled();
            this.primaryConnection.open();
            return;
        }
        throw new IllegalStateException("This accessory session was released!");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public final Accessory getAccessory() {
        return this.accessory;
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public final String getAddress() {
        AccessoryConnection accessoryConnection = this.primaryConnection;
        return accessoryConnection == null ? this.accessory.getAddress() : accessoryConnection.getAccessory().getAddress();
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public CallingRepository getCallingRepository() {
        throw new IllegalStateException("This accessory session does not support calling capability.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final AccessoryCapabilities getCapabilities() {
        return this.capabilities;
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public CblRepository getCblRepository() {
        throw new IllegalStateException("This accessory session does support CBL capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    /* renamed from: getCloudPairingRepository */
    public CloudPairingRepository mo312getCloudPairingRepository() {
        throw new IllegalStateException("This accessory session does not support cloud pairing capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public final Accessory getConnectedAccessory() {
        AccessoryConnection accessoryConnection = this.primaryConnection;
        return accessoryConnection != null ? accessoryConnection.getAccessory() : this.accessory;
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public DeviceRepository getDeviceRepository() {
        throw new IllegalStateException("This accessory session does not support device capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public abstract DeviceRepositoryV2 getDeviceRepositoryV2();

    @Override // com.amazon.alexa.accessory.AccessorySession
    public DiagnosticsRepository getDiagnosticsRepository() {
        throw new IllegalStateException("This accessory session does not support diagnostics capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public DisplayContentRepository getDisplayContentRepository() {
        throw new IllegalStateException("This accessory session does not support display capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public final FeatureChecker getFeatureChecker() {
        return this.featureChecker;
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    @Deprecated
    public FirmwareRepository getFirmwareRepository() {
        throw new IllegalStateException("This accessory session does support firmware capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public FirmwareRepositoryV2 getFirmwareRepositoryV2() {
        throw new IllegalStateException("This accessory session does support firmware capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public FitnessRepository getFitnessRepository() {
        throw new IllegalStateException("This accessory session does not support fitness capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public HearingEnhancementRepository getHearingEnhancementRepository() {
        throw new IllegalStateException("This accessory session does not support hearing enhancement capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public InputRepository getInputRepository() {
        throw new IllegalStateException("This accessory session does not support input capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public InstrumentationRepository getInstrumentationRepository() {
        throw new IllegalStateException("This accessory session does not support instrumentation capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public MediaRepository getMediaRepository() {
        throw new IllegalStateException("This accessory session does not media repository.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public NonHfpCallingRepository getNonHfpCallingRepository() {
        throw new IllegalStateException("This accessory session does not support non-HFP calling capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public NotificationRepository getNotificationRepository() {
        throw new IllegalStateException("This accessory session does not support notification capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    /* renamed from: getSpeechRepository */
    public SpeechRepository mo313getSpeechRepository() {
        throw new IllegalStateException("This accessory session does not support speech capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public StateRepository getStateRepository() {
        throw new IllegalStateException("This accessory session does not support state capability.");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public SystemRepository getSystemRepository() {
        throw new IllegalStateException("This accessory session does not support system capability.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final TaskManager getTaskManager() {
        return this.taskManager;
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public final AccessoryTransport.Type getTransport() {
        AccessoryConnection accessoryConnection = this.primaryConnection;
        return accessoryConnection == null ? this.accessory.getTransport() : accessoryConnection.getAccessory().getTransport();
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public final String getUuid() {
        return this.uuid;
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public final boolean isConnected() {
        AccessoryConnection accessoryConnection = this.primaryConnection;
        return accessoryConnection != null && accessoryConnection.isConnected();
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public final boolean isConnecting() {
        AccessoryConnection accessoryConnection = this.primaryConnection;
        return accessoryConnection != null && accessoryConnection.isConnecting();
    }

    public /* synthetic */ void lambda$attachCapabilities$1$BaseAccessorySession(SizedSource sizedSource, int i) throws Exception {
        if (!this.capabilities.handleData(sizedSource, i)) {
            this.defaultReceiver.onDataReceived(sizedSource, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onTransportSwitchWillRetry(AccessoryTransport.Type type) {
        resumeDispatchers(this.primaryConnection.getAccessory().getTransport(), type);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onTransportUpgradeFailed(AccessoryTransport.Type type) {
        resumeDispatchers(this.accessory.getTransport(), type);
        AccessoryConnection accessoryConnection = this.secondaryConnection;
        if (accessoryConnection != null) {
            accessoryConnection.close();
            this.secondaryConnection = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void prepareSetupSecondaryConnection(AccessoryTransport.Type type) {
        suspendDispatchers(this.primaryConnection.getAccessory().getTransport(), type);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void prepareSwitchPrimaryConnection() {
        if (this.secondaryConnection != null) {
            suspendDispatchers(this.primaryConnection.getAccessory().getTransport(), this.secondaryConnection.getAccessory().getTransport());
            return;
        }
        throw new IllegalStateException("Secondary connection was never setup!");
    }

    @Override // com.amazon.alexa.accessory.AccessorySession
    public void release() {
        Preconditions.mainThread();
        if (this.released) {
            return;
        }
        this.released = true;
        AccessoryConnection accessoryConnection = this.primaryConnection;
        if (accessoryConnection != null) {
            accessoryConnection.close();
            this.primaryConnection = null;
        }
        AccessoryConnection accessoryConnection2 = this.secondaryConnection;
        if (accessoryConnection2 != null) {
            accessoryConnection2.removeConnectionListener(this.secondaryConnectionListener);
            this.secondaryConnection.close();
            this.secondaryConnection = null;
        }
        Disposable disposable = this.keyExchangeDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.capabilities.release();
        this.taskManager.release();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setupSecondaryConnection(Accessory accessory, final TransportCapability.TransportConnectedListener transportConnectedListener) {
        if (this.primaryConnection != null) {
            if (this.secondaryConnection == null) {
                this.secondaryConnectionListener = new AccessoryConnection.SimpleConnectionListener() { // from class: com.amazon.alexa.accessory.internal.session.BaseAccessorySession.3
                    @Override // com.amazon.alexa.accessory.AccessoryConnection.SimpleConnectionListener, com.amazon.alexa.accessory.AccessoryConnection.ConnectionListener
                    public void onConnectionError(AccessoryConnection accessoryConnection, Throwable th) {
                        Preconditions.mainThread();
                        Logger.d("Secondary connection failed", th);
                        BaseAccessorySession baseAccessorySession = BaseAccessorySession.this;
                        baseAccessorySession.resumeDispatchers(baseAccessorySession.primaryConnection.getAccessory().getTransport(), accessoryConnection.getAccessory().getTransport());
                        BaseAccessorySession.this.secondaryConnection = null;
                        BaseAccessorySession.this.secondaryConnectionListener = null;
                        transportConnectedListener.onConnectionFailed(th);
                    }

                    @Override // com.amazon.alexa.accessory.AccessoryConnection.SimpleConnectionListener, com.amazon.alexa.accessory.AccessoryConnection.ConnectionListener
                    public void onConnectionOpened(AccessoryConnection accessoryConnection) {
                        Preconditions.mainThread();
                        Logger.d("Secondary connection opened");
                        BaseAccessorySession baseAccessorySession = BaseAccessorySession.this;
                        baseAccessorySession.resumeDispatchers(baseAccessorySession.primaryConnection.getAccessory().getTransport(), accessoryConnection.getAccessory().getTransport());
                        transportConnectedListener.onConnected();
                    }
                };
                this.secondaryConnection = createConnection(accessory, this.primaryConnection.getTransportVersion());
                this.secondaryConnection.addConnectionListener(this.secondaryConnectionListener);
                this.secondaryConnection.open();
                return;
            }
            throw new IllegalStateException("Already connected over a secondary connection");
        }
        throw new IllegalStateException("Transport upgrade has not been initiated properly!");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void switchPrimaryConnection(Accessory accessory) {
        AccessoryConnection accessoryConnection = this.secondaryConnection;
        if (accessoryConnection != null) {
            AccessoryConnection accessoryConnection2 = this.primaryConnection;
            this.primaryConnection = accessoryConnection;
            accessoryConnection.removeConnectionListener(this.secondaryConnectionListener);
            this.secondaryConnection = null;
            this.secondaryConnectionListener = null;
            accessoryConnection2.removeConnectionListener(this.connectionListener);
            accessoryConnection2.close();
            attachCapabilities(this.primaryConnection);
            this.primaryConnection.addConnectionListener(this.connectionListener);
            resumeDispatchers(accessoryConnection2.getAccessory().getTransport(), this.primaryConnection.getAccessory().getTransport());
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Hooray!!! Primary transport has been upgraded to ");
            outline107.append(this.primaryConnection.getAccessory());
            Logger.d(outline107.toString());
            this.sessionListener.onSessionTransportChanged(accessory, accessoryConnection2.getAccessory().getTransport(), this.primaryConnection.getAccessory().getTransport());
            return;
        }
        throw new IllegalStateException("Secondary connection was never setup!");
    }
}
