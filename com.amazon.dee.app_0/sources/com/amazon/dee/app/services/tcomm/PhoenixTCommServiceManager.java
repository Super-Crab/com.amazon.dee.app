package com.amazon.dee.app.services.tcomm;

import amazon.communication.GatewayConnectivity;
import amazon.communication.TCommServiceDownException;
import amazon.communication.connection.ConnectionClosedDetails;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.dee.app.framework.AlexaApplication;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import dagger.Lazy;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class PhoenixTCommServiceManager implements TCommServiceManager {
    private static final String TAG = "PhoenixTCommServiceManager";
    private final Context context;
    private final EventBus eventBus;
    private Lazy<FeatureServiceV2> featureServiceV2Lazy;
    @VisibleForTesting
    GatewayConnectivity gatewayConnectivity;
    @VisibleForTesting
    GatewayConnectivityFactory gatewayConnectivityFactory;
    @VisibleForTesting
    GatewayConnectivity.GatewayConnectivityMonitor gatewayConnectivityMonitor;
    private final IdentityService identityService;
    private boolean initializationRequested = false;
    private final AtomicBoolean isStarted = new AtomicBoolean(false);
    private final Mobilytics mobilytics;
    private final NetworkService networkService;
    private MobilyticsMetricsTimer timer;

    /* loaded from: classes12.dex */
    final class TcommLifecycleObserver implements MainActivityLifecycleObserver {
        TcommLifecycleObserver() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityCreated() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityDestroy() {
            PhoenixTCommServiceManager.this.stopTComm();
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityPause() {
            PhoenixTCommServiceManager.this.stopTComm();
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityResume() {
            PhoenixTCommServiceManager.this.startTComm();
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityStart() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityStop() {
            PhoenixTCommServiceManager.this.stopTComm();
        }
    }

    public PhoenixTCommServiceManager(Context context, IdentityService identityService, NetworkService networkService, EventBus eventBus, MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar, Mobilytics mobilytics, GatewayConnectivityFactory gatewayConnectivityFactory, Lazy<FeatureServiceV2> lazy) {
        this.context = context;
        this.mobilytics = mobilytics;
        this.identityService = identityService;
        this.networkService = networkService;
        this.eventBus = eventBus;
        this.gatewayConnectivityFactory = gatewayConnectivityFactory;
        this.featureServiceV2Lazy = lazy;
        this.gatewayConnectivity = gatewayConnectivityFactory.createGatewayConnectivity(context);
        initializeTCommService();
        mainActivityLifecycleObserverRegistrar.addObserver(new TcommLifecycleObserver());
    }

    private boolean canStartTComm() {
        return AlexaApplication.isAppOnForeground(this.context) && this.networkService.isConnected();
    }

    private void connectToGateway() {
        if (this.gatewayConnectivityMonitor == null) {
            this.gatewayConnectivityMonitor = getGatewayConnectivityMonitor();
        }
        this.gatewayConnectivity.registerGatewayConnectivityMonitor(this.gatewayConnectivityMonitor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initializationWasRequested() {
        return this.initializationRequested;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeTCommService() {
        if (initializationWasRequested() || !canStartTComm()) {
            Object[] objArr = {Boolean.valueOf(AlexaApplication.isAppOnForeground(this.context)), Boolean.valueOf(this.networkService.isConnected())};
            return;
        }
        this.timer = this.mobilytics.createTimer(AlexaMetricsConstants.MetricEvents.TCOMM_INITIALIZATION_LATENCY, AlexaMetricsConstants.MetricsComponents.TCOMM, PhoenixTCommServiceManager.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        TCommService.initialize(this.context);
        this.initializationRequested = true;
    }

    private boolean isNativeTCommEnabled() {
        return this.featureServiceV2Lazy.mo358get() != null && this.featureServiceV2Lazy.mo358get().hasAccess("TCOMM_ANDROID", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNetworkChanged(boolean z) {
        if (z) {
            stopTComm();
            Log.i(TAG, "Network available, attempting to start Tcomm");
            startTComm();
            return;
        }
        Log.i(TAG, "Network not available, attempting to stop Tcomm");
        stopTComm();
    }

    @VisibleForTesting
    GatewayConnectivity.GatewayConnectivityMonitor getGatewayConnectivityMonitor() {
        return new GatewayConnectivity.GatewayConnectivityMonitor() { // from class: com.amazon.dee.app.services.tcomm.PhoenixTCommServiceManager.1
            @Override // amazon.communication.GatewayConnectivity.GatewayConnectivityMonitor
            public void onGatewayConnectionClosed(ConnectionClosedDetails connectionClosedDetails) {
                String unused = PhoenixTCommServiceManager.TAG;
                PhoenixTCommServiceManager.this.stopTComm();
            }

            @Override // amazon.communication.GatewayConnectivity.GatewayConnectivityMonitor
            public void onGatewayConnectionEstablished() {
                String unused = PhoenixTCommServiceManager.TAG;
                if (!PhoenixTCommServiceManager.this.initializationWasRequested()) {
                    PhoenixTCommServiceManager.this.initializeTCommService();
                }
                PhoenixTCommServiceManager.this.startNativeTComm();
            }
        };
    }

    public /* synthetic */ void lambda$start$0$PhoenixTCommServiceManager(Message message) {
        userChanged(this.identityService.getUser(TAG));
    }

    @Override // com.amazon.dee.app.services.tcomm.TCommServiceManager
    public void start() {
        try {
            startTComm();
        } catch (Exception e) {
            Log.e(TAG, "TComm start failed", e);
            this.mobilytics.recordOperationalEvent(this.mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.TCOMM_INITIALIZATION_FAILURE, OperationalEventType.DIAGNOSTIC, AlexaMetricsConstants.MetricsComponents.TCOMM, PhoenixTCommServiceManager.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        }
        this.eventBus.getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.services.tcomm.-$$Lambda$PhoenixTCommServiceManager$f9jsQEfUOaaLW2xZwAZUvZWyT14
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                PhoenixTCommServiceManager.this.lambda$start$0$PhoenixTCommServiceManager(message);
            }
        });
        this.networkService.onConnectivityChanged().subscribe(new Action1() { // from class: com.amazon.dee.app.services.tcomm.-$$Lambda$PhoenixTCommServiceManager$oL3IgRewmY2huDwbtbyNULJcIGg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PhoenixTCommServiceManager.this.onNetworkChanged(((Boolean) obj).booleanValue());
            }
        });
    }

    void startNativeTComm() {
        if (isNativeTCommEnabled()) {
            synchronized (this.isStarted) {
                if (!this.isStarted.get()) {
                    Log.i(TAG, "TCommService desired state = started, but is stopped. Starting TCommService now.");
                    TCommService.start(this.context);
                    this.isStarted.set(true);
                }
            }
        }
    }

    void startTComm() {
        if (!canStartTComm()) {
            Object[] objArr = {Boolean.valueOf(AlexaApplication.isAppOnForeground(this.context)), Boolean.valueOf(this.networkService.isConnected())};
            return;
        }
        try {
            int gatewayConnectionState = this.gatewayConnectivity.getGatewayConnectionState();
            String str = "TComm gateway status: " + gatewayConnectionState;
            if (gatewayConnectionState != 2) {
                connectToGateway();
            } else {
                if (!initializationWasRequested()) {
                    initializeTCommService();
                }
                startNativeTComm();
            }
            this.mobilytics.recordOperationalEvent(this.mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.TCOMM_START_REQUEST, OperationalEventType.DIAGNOSTIC, AlexaMetricsConstants.MetricsComponents.TCOMM, PhoenixTCommServiceManager.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        } catch (TCommServiceDownException | NullPointerException e) {
            String str2 = "Unable to connect to TComm: " + e;
            stopTComm();
        }
    }

    void stopTComm() {
        this.mobilytics.recordOperationalEvent(this.mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.TCOMM_STOP_REQUEST, OperationalEventType.DIAGNOSTIC, AlexaMetricsConstants.MetricsComponents.TCOMM, PhoenixTCommServiceManager.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        synchronized (this.isStarted) {
            if (this.isStarted.get()) {
                Log.i(TAG, "TCommService desired state = stopped, but is started. Stopping TCommService now.");
                TCommService.stop(this.context);
                this.isStarted.set(false);
                if (this.gatewayConnectivityMonitor != null) {
                    try {
                        this.gatewayConnectivity.deregisterGatewayConnectivityMonitor(this.gatewayConnectivityMonitor);
                    } catch (IllegalArgumentException e) {
                        String str = TAG;
                        Log.e(str, "Caught exception when deregistering gateway monitor: " + e);
                    }
                    this.gatewayConnectivityMonitor = null;
                }
            }
        }
    }

    @VisibleForTesting
    void userChanged(UserIdentity userIdentity) {
        if (userIdentity == null) {
            Log.i(TAG, "User signed out, attempting to stop Tcomm");
            stopTComm();
            return;
        }
        Log.i(TAG, "User signed in, attempting to start Tcomm");
        startTComm();
    }
}
