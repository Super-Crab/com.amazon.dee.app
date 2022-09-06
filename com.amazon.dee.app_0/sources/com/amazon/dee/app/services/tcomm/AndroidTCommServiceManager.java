package com.amazon.dee.app.services.tcomm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.amazon.communication.CommunicationServiceConstants;
import com.amazon.dee.app.framework.AlexaApplication;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import dagger.Lazy;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class AndroidTCommServiceManager implements TCommServiceManager {
    private static final String TAG = "AndroidTCommServiceManager";
    private final Context context;
    private final EventBus eventBus;
    private Lazy<FeatureServiceV2> featureServiceV2Lazy;
    private final IdentityService identityService;
    private boolean initializationRequested = false;
    private final AtomicBoolean isStarted;
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
            AndroidTCommServiceManager.this.stopTComm();
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityPause() {
            AndroidTCommServiceManager.this.stopTComm();
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityResume() {
            AndroidTCommServiceManager.this.startTComm();
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityStart() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityStop() {
            AndroidTCommServiceManager.this.stopTComm();
        }
    }

    public AndroidTCommServiceManager(Context context, IdentityService identityService, NetworkService networkService, EventBus eventBus, MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar, Mobilytics mobilytics, Lazy<FeatureServiceV2> lazy) {
        this.context = context;
        this.mobilytics = mobilytics;
        registerTCommIntentListener();
        this.identityService = identityService;
        this.networkService = networkService;
        this.eventBus = eventBus;
        this.isStarted = new AtomicBoolean(false);
        this.featureServiceV2Lazy = lazy;
        initializeTCommService();
        mainActivityLifecycleObserverRegistrar.addObserver(new TcommLifecycleObserver());
    }

    private boolean canStartTComm() {
        return AlexaApplication.isAppOnForeground(this.context) && this.networkService.isConnected();
    }

    private boolean initializationWasRequested() {
        return this.initializationRequested;
    }

    private void initializeTCommService() {
        if (initializationWasRequested() || !canStartTComm()) {
            Object[] objArr = {Boolean.valueOf(AlexaApplication.isAppOnForeground(this.context)), Boolean.valueOf(this.networkService.isConnected())};
            return;
        }
        this.timer = this.mobilytics.createTimer(AlexaMetricsConstants.MetricEvents.TCOMM_INITIALIZATION_LATENCY, AlexaMetricsConstants.MetricsComponents.TCOMM, AndroidTCommServiceManager.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
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

    private void registerTCommIntentListener() {
        this.context.getApplicationContext().registerReceiver(new BroadcastReceiver() { // from class: com.amazon.dee.app.services.tcomm.AndroidTCommServiceManager.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String unused = AndroidTCommServiceManager.TAG;
                if (CommunicationServiceConstants.COMMUNICATION_SERVICE_INITIALIZED.equals(intent.getAction())) {
                    TCommService.receivedTCommIntent(context);
                    if (AndroidTCommServiceManager.this.timer != null) {
                        AndroidTCommServiceManager.this.timer.finishTimer();
                        AndroidTCommServiceManager.this.mobilytics.recordTimer(AndroidTCommServiceManager.this.timer);
                    }
                    context.getApplicationContext().unregisterReceiver(this);
                }
            }
        }, new IntentFilter(CommunicationServiceConstants.COMMUNICATION_SERVICE_INITIALIZED));
    }

    public /* synthetic */ void lambda$start$0$AndroidTCommServiceManager(Message message) {
        userChanged(this.identityService.getUser(TAG));
    }

    @Override // com.amazon.dee.app.services.tcomm.TCommServiceManager
    public void start() {
        try {
            startTComm();
        } catch (Exception e) {
            Log.e(TAG, "TComm start failed", e);
            this.mobilytics.recordOperationalEvent(this.mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.TCOMM_INITIALIZATION_FAILURE, OperationalEventType.DIAGNOSTIC, AlexaMetricsConstants.MetricsComponents.TCOMM, AndroidTCommServiceManager.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        }
        this.networkService.onConnectivityChanged().subscribe(new Action1() { // from class: com.amazon.dee.app.services.tcomm.-$$Lambda$AndroidTCommServiceManager$1ugL4UWNLOSrg4-M0VRua5eQu0I
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AndroidTCommServiceManager.this.onNetworkChanged(((Boolean) obj).booleanValue());
            }
        });
        this.eventBus.getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.services.tcomm.-$$Lambda$AndroidTCommServiceManager$mYGCCImtxptBxOE3a41UgUQfxyk
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AndroidTCommServiceManager.this.lambda$start$0$AndroidTCommServiceManager(message);
            }
        });
    }

    void startTComm() {
        if (!canStartTComm()) {
            Object[] objArr = {Boolean.valueOf(AlexaApplication.isAppOnForeground(this.context)), Boolean.valueOf(this.networkService.isConnected())};
            return;
        }
        if (!initializationWasRequested()) {
            initializeTCommService();
        }
        this.mobilytics.recordOperationalEvent(this.mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.TCOMM_START_REQUEST, OperationalEventType.DIAGNOSTIC, AlexaMetricsConstants.MetricsComponents.TCOMM, AndroidTCommServiceManager.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        if (!isNativeTCommEnabled()) {
            return;
        }
        synchronized (this.isStarted) {
            if (!this.isStarted.get()) {
                Log.i(TAG, "TCommService desired state = started, but is stopped. Starting TCommService now.");
                TCommService.start(this.context);
                this.isStarted.set(true);
            }
        }
    }

    void stopTComm() {
        this.mobilytics.recordOperationalEvent(this.mobilytics.createOperationalEvent(AlexaMetricsConstants.MetricEvents.TCOMM_STOP_REQUEST, OperationalEventType.DIAGNOSTIC, AlexaMetricsConstants.MetricsComponents.TCOMM, AndroidTCommServiceManager.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        synchronized (this.isStarted) {
            if (this.isStarted.get()) {
                Log.i(TAG, "TCommService desired state = stopped, but is started. Stopping TCommService now.");
                TCommService.stop(this.context);
                this.isStarted.set(false);
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
