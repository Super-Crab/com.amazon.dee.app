package com.amazon.alexa.mobilytics.executor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.configuration.ConfigManager;
import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazon.alexa.mobilytics.configuration.EndpointManager;
import com.amazon.alexa.mobilytics.configuration.RecordChecker;
import com.amazon.alexa.mobilytics.connector.ConnectorExecutor;
import com.amazon.alexa.mobilytics.connector.ConnectorManager;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import com.amazon.alexa.mobilytics.lifecycle.Lifecycle;
import com.amazon.alexa.mobilytics.session.MobilyticsSession;
import com.amazon.alexa.mobilytics.session.SessionManager;
import com.amazon.alexa.mobilytics.timeline.TimelineDataPublisher;
import com.amazon.alexa.mobilytics.timeline.TimelineEvent;
import com.amazon.alexa.mobilytics.timeline.TimelineManager;
import com.amazon.alexa.mobilytics.timeline.TimelineMessage;
import com.amazon.alexa.mobilytics.timeline.TimelineStorage;
import com.amazon.alexa.mobilytics.util.Log;
import com.google.common.base.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
@Singleton
/* loaded from: classes9.dex */
public class DefaultExecutor implements Executor, Handler.Callback {
    private static final String TAG = Log.tag(DefaultExecutor.class);
    private final ConfigManager configManager;
    private final MobilyticsConfiguration configuration;
    private Observable<ConnectorExecutor> connectorExecutorsObservable;
    private final ConnectorManager connectorManager;
    private final EndpointManager endpointManager;
    @VisibleForTesting(otherwise = 4)
    public Handler handler;
    private HandlerThread handlerThread;
    private final Lifecycle lifecycle;
    private final RecordChecker recordChecker;
    private final SessionManager sessionManager;
    private Subscription subscription;
    private TimelineDataPublisher timelineDataPublisher;
    private final TimelineManager timelineManager;
    private final TimelineStorage timelineStorage;
    private List<ConnectorExecutor> connectorExecutors = new ArrayList();
    private Observable<Boolean> configDownloader = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.alexa.mobilytics.executor.-$$Lambda$DefaultExecutor$eV0wRZ7G4A9Uf3Hw_0mivDT-aNw
        @Override // rx.functions.Action1
        public final void call(Object obj) {
            DefaultExecutor.this.lambda$new$0$DefaultExecutor((Subscriber) obj);
        }
    });

    /* renamed from: com.amazon.alexa.mobilytics.executor.DefaultExecutor$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$mobilytics$lifecycle$Lifecycle$Event = new int[Lifecycle.Event.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$mobilytics$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mobilytics$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mobilytics$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mobilytics$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mobilytics$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mobilytics$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface MessageType {
        public static final int EVENT = 6;
        public static final int FINALIZE = 1;
        public static final int INITIALIZE = 0;
        public static final int MARKER_EVENT = 9;
        public static final int PAUSE_SESSION = 3;
        public static final int START_SESSION = 2;
        public static final int TIMELINE_EVENT = 8;
        public static final int USER_CHANGED = 7;
    }

    @Inject
    public DefaultExecutor(@NonNull MobilyticsConfiguration mobilyticsConfiguration, @NonNull ConfigManager configManager, @NonNull Lifecycle lifecycle, @NonNull SessionManager sessionManager, @NonNull TimelineManager timelineManager, @NonNull TimelineStorage timelineStorage, @NonNull TimelineDataPublisher timelineDataPublisher, @NonNull RecordChecker recordChecker, @NonNull ConnectorManager connectorManager, @NonNull EndpointManager endpointManager) {
        this.configuration = (MobilyticsConfiguration) Preconditions.checkNotNull(mobilyticsConfiguration);
        this.configManager = (ConfigManager) Preconditions.checkNotNull(configManager);
        this.lifecycle = (Lifecycle) Preconditions.checkNotNull(lifecycle);
        this.sessionManager = (SessionManager) Preconditions.checkNotNull(sessionManager);
        this.timelineManager = timelineManager;
        this.timelineStorage = timelineStorage;
        this.timelineDataPublisher = timelineDataPublisher;
        this.recordChecker = (RecordChecker) Preconditions.checkNotNull(recordChecker);
        this.connectorManager = (ConnectorManager) Preconditions.checkNotNull(connectorManager);
        this.endpointManager = (EndpointManager) Preconditions.checkNotNull(endpointManager);
        initialize();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanUp(Throwable th) {
        if (!this.subscription.isUnsubscribed()) {
            this.subscription.unsubscribe();
        }
        Log.e(TAG, th, "Error downloading config", new Object[0]);
    }

    private void download() {
        if (this.configuration.userProvider().user() == null || !this.configuration.userProvider().user().hasFeature("ALEXA_ANDROID_MOBILYTICS_HIGH_PRIORITY_METRICS")) {
            return;
        }
        this.subscription = this.configDownloader.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.from(this.handlerThread.getLooper())).subscribe(new Action1() { // from class: com.amazon.alexa.mobilytics.executor.-$$Lambda$DefaultExecutor$z2mTLxDZ7V208qtNh-vcWjEYxoI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultExecutor.this.reinitializeConnectors(((Boolean) obj).booleanValue());
            }
        }, new Action1() { // from class: com.amazon.alexa.mobilytics.executor.-$$Lambda$DefaultExecutor$Y9nFMd7o2e1qqD-Xru_htHmAoBk
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultExecutor.this.cleanUp((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLifecycleEvent(Lifecycle.Event event) {
        int ordinal = event.ordinal();
        if (ordinal == 0) {
            Log.d(TAG, "Received ON_CREATE");
            postMarkerEventMsg(event.name());
        } else if (ordinal == 1) {
            Log.d(TAG, "Received ON_DESTROY");
            postMarkerEventMsg(event.name());
        } else if (ordinal == 2) {
            Log.d(TAG, "Received ON_START");
            postMessage(2);
            postMarkerEventMsg(event.name());
        } else if (ordinal == 3) {
            Log.d(TAG, "Received ON_STOP");
            postMessage(3);
            postMarkerEventMsg(event.name());
        } else if (ordinal == 4) {
            Log.d(TAG, "Received ON_RESUME");
            postMarkerEventMsg(event.name());
        } else if (ordinal != 5) {
        } else {
            Log.d(TAG, "Received ON_PAUSE");
            postMarkerEventMsg(event.name());
        }
    }

    private void handleSessionLifecycle(Message message) {
        int i = message.what;
        if (i == 2) {
            this.sessionManager.startSession();
            download();
        } else if (i != 3) {
        } else {
            this.sessionManager.pauseSession();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSessionStateChange(final MobilyticsSession mobilyticsSession) {
        Observable.from(this.connectorExecutors).forEach(new Action1() { // from class: com.amazon.alexa.mobilytics.executor.-$$Lambda$DefaultExecutor$LZyTbzdT8pJdDqFWK9rozEh_-Rw
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultExecutor.lambda$handleSessionStateChange$8(MobilyticsSession.this, (ConnectorExecutor) obj);
            }
        });
    }

    private void initialize() {
        this.connectorManager.initialize(this.configuration);
        this.connectorExecutors.addAll(this.connectorManager.connectorExecutors());
        this.connectorExecutorsObservable = Observable.from(this.connectorExecutors);
        this.handlerThread = new HandlerThread("DefaultExecutor");
        this.handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper(), this);
        postMessage(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$handleSessionStateChange$8(MobilyticsSession mobilyticsSession, ConnectorExecutor connectorExecutor) {
        int state = mobilyticsSession.state();
        if (state == 0) {
            connectorExecutor.executeSessionStop(mobilyticsSession);
        } else if (state == 1) {
            connectorExecutor.executeSessionStart(mobilyticsSession);
        } else if (state == 2) {
            connectorExecutor.executeSessionPause(mobilyticsSession);
        } else if (state != 3) {
        } else {
            connectorExecutor.executeSessionResume(mobilyticsSession);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$runOnConnectors$7(Action1 action1, int i, ConnectorExecutor connectorExecutor) {
        try {
            action1.call(connectorExecutor);
        } catch (Exception e) {
            Log.e(TAG, e, "Error executing action [%d] on connector [%s]", Integer.valueOf(i), connectorExecutor.connectorName());
        }
    }

    private void postMarkerEventMsg(String str) {
        TimelineMessage timelineMessage = new TimelineMessage();
        timelineMessage.name = str;
        timelineMessage.currentTimeMillis = System.currentTimeMillis();
        timelineMessage.elapsedRealtime = SystemClock.elapsedRealtime();
        postMessage(9, timelineMessage);
    }

    private void recordEvent(int i, final MobilyticsEvent mobilyticsEvent) {
        runOnConnectors(i, new Action1() { // from class: com.amazon.alexa.mobilytics.executor.-$$Lambda$DefaultExecutor$EL3WiaTFLCB24VMn_t1V4xsKJ2E
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ((ConnectorExecutor) obj).executeRecordEvent(MobilyticsEvent.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reinitializeConnectors(boolean z) {
        if (!this.subscription.isUnsubscribed()) {
            this.subscription.unsubscribe();
        }
        if (z) {
            Log.d(TAG, "Configurations Downloaded");
            this.endpointManager.initialize();
            ArrayList<ConnectorExecutor> arrayList = new ArrayList(this.connectorManager.connectorExecutors());
            this.recordChecker.initialize();
            for (ConnectorExecutor connectorExecutor : arrayList) {
                if (connectorExecutor.connectorName().contains("KinesisConnector") || connectorExecutor.connectorName().contains("DcmConnector")) {
                    connectorExecutor.executeOnInitialize(this.configuration);
                }
            }
            for (ConnectorExecutor connectorExecutor2 : this.connectorExecutors) {
                if (connectorExecutor2.connectorName().contains("KinesisConnector") || connectorExecutor2.connectorName().contains("DcmConnector")) {
                    connectorExecutor2.executeOnFinalize();
                }
            }
            this.connectorExecutors.clear();
            this.connectorExecutors.addAll(arrayList);
            this.connectorExecutorsObservable = Observable.from(this.connectorExecutors);
            Log.d(TAG, "Configurations Set");
        }
    }

    private void runOnConnectors(final int i, final Action1<ConnectorExecutor> action1) {
        this.connectorExecutorsObservable.forEach(new Action1() { // from class: com.amazon.alexa.mobilytics.executor.-$$Lambda$DefaultExecutor$HZmYcgBectf1rpNgCS3w7Fbpd14
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultExecutor.lambda$runOnConnectors$7(Action1.this, i, (ConnectorExecutor) obj);
            }
        });
    }

    private void sendMarkerEvent(int i, TimelineMessage timelineMessage) {
        TimelineStorage timelineStorage = this.timelineStorage;
        if (timelineStorage == null || timelineStorage.isEmpty()) {
            return;
        }
        Iterator<TimelineEvent> iterator = this.timelineStorage.getIterator();
        while (iterator.hasNext()) {
            TimelineEvent next = iterator.next();
            if (next.isMarkerRecordable()) {
                DefaultMobilyticsOperationalEvent defaultMobilyticsOperationalEvent = new DefaultMobilyticsOperationalEvent(timelineMessage.name, Constants.MARKER, "mobilytics.events", Constants.MARKER);
                defaultMobilyticsOperationalEvent.setEventTimestamp(timelineMessage.currentTimeMillis);
                defaultMobilyticsOperationalEvent.timelineElapsedDuration(next.calculateTimelineElapsedDuration(timelineMessage.elapsedRealtime));
                defaultMobilyticsOperationalEvent.timelineId(next.timelineId());
                defaultMobilyticsOperationalEvent.timelineName(next.timelineName());
                defaultMobilyticsOperationalEvent.timelineNamespace(next.timelineNamespace());
                next.incrementEventCount();
                recordEvent(i, defaultMobilyticsOperationalEvent);
            }
        }
    }

    @Override // com.amazon.alexa.mobilytics.executor.Executor
    public void executeRecordEvent(@NonNull MobilyticsEvent mobilyticsEvent) {
        postMessage(6, mobilyticsEvent);
    }

    @Override // com.amazon.alexa.mobilytics.executor.Executor
    public void executeTimelineEvent(TimelineMessage timelineMessage) {
        postMessage(8, timelineMessage);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(final Message message) {
        handleSessionLifecycle(message);
        int i = message.what;
        if (i != 2 && i != 3) {
            if (i == 0) {
                runOnConnectors(i, new Action1() { // from class: com.amazon.alexa.mobilytics.executor.-$$Lambda$DefaultExecutor$h6op-_xFKYBLv3wMA30geh7ko2I
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        DefaultExecutor.this.lambda$handleMessage$1$DefaultExecutor((ConnectorExecutor) obj);
                    }
                });
            } else if (i != 1) {
                switch (i) {
                    case 6:
                        recordEvent(i, (MobilyticsEvent) message.obj);
                        break;
                    case 7:
                        runOnConnectors(i, new Action1() { // from class: com.amazon.alexa.mobilytics.executor.-$$Lambda$DefaultExecutor$uWqGAW9ug2PZ1puJnH7Dma2uBP8
                            @Override // rx.functions.Action1
                            public final void call(Object obj) {
                                ((ConnectorExecutor) obj).executeUserChanged((MobilyticsUser) message.obj);
                            }
                        });
                        break;
                    case 8:
                        final MobilyticsEvent process = this.timelineManager.process((TimelineMessage) message.obj);
                        if (process != null) {
                            runOnConnectors(message.what, new Action1() { // from class: com.amazon.alexa.mobilytics.executor.-$$Lambda$DefaultExecutor$aF89HxvFCrh65jEoZvJLJhqP_a4
                                @Override // rx.functions.Action1
                                public final void call(Object obj) {
                                    ((ConnectorExecutor) obj).executeRecordEvent(MobilyticsEvent.this);
                                }
                            });
                            break;
                        }
                        break;
                    case 9:
                        sendMarkerEvent(i, (TimelineMessage) message.obj);
                        break;
                }
            } else {
                runOnConnectors(i, $$Lambda$DefaultExecutor$fMMkf4sU4qdRlM_QkwbHU9MFx6s.INSTANCE);
            }
            if (message.what == 0) {
                this.lifecycle.onEvent().subscribe(new Action1() { // from class: com.amazon.alexa.mobilytics.executor.-$$Lambda$DefaultExecutor$ZAFQqVoLLwrtXbjm6HPTBK0Ewic
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        DefaultExecutor.this.handleLifecycleEvent((Lifecycle.Event) obj);
                    }
                });
                this.sessionManager.onSessionStateChange().subscribe(new Action1() { // from class: com.amazon.alexa.mobilytics.executor.-$$Lambda$DefaultExecutor$mceW8FG7dPFTpmxPCp1Br3HzDqA
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        DefaultExecutor.this.handleSessionStateChange((MobilyticsSession) obj);
                    }
                });
                this.configuration.userProvider().addListener(new MobilyticsUserProvider.Listener() { // from class: com.amazon.alexa.mobilytics.executor.-$$Lambda$DefaultExecutor$t8Mqr4dZog8HqUMKnE2ccBqP5tA
                    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider.Listener
                    public final void onUserChanged(MobilyticsUser mobilyticsUser) {
                        DefaultExecutor.this.lambda$handleMessage$5$DefaultExecutor(mobilyticsUser);
                    }
                });
                download();
                try {
                    this.configManager.initialize(this.configuration);
                } catch (Exception e) {
                    Log.e(TAG, e, "Error initializing config manager", new Object[0]);
                }
                try {
                    this.recordChecker.initialize();
                } catch (Exception e2) {
                    Log.e(TAG, e2, "Error initializing record checker", new Object[0]);
                }
                Log.d(TAG, "MobilyticsSession is being initialized on Mobilytics Initialization");
                this.sessionManager.startSessionWithForce(true);
                this.timelineDataPublisher.publishExistingEvents(this);
            }
            if (message.what == 7) {
                if (this.configuration.userProvider().user() != null && this.configuration.userProvider().user().hasFeature("ALEXA_ANDROID_MOBILYTICS_HIGH_PRIORITY_METRICS")) {
                    try {
                        this.configManager.onUserChanged(this.configuration.userProvider().user());
                    } catch (Exception e3) {
                        Log.e(TAG, e3, "Error calling onUserChanged on config manager", new Object[0]);
                    }
                }
                download();
            }
        }
        return true;
    }

    public /* synthetic */ void lambda$handleMessage$1$DefaultExecutor(ConnectorExecutor connectorExecutor) {
        connectorExecutor.executeOnInitialize(this.configuration);
    }

    public /* synthetic */ void lambda$handleMessage$5$DefaultExecutor(MobilyticsUser mobilyticsUser) {
        postMessage(7, mobilyticsUser);
    }

    public /* synthetic */ void lambda$new$0$DefaultExecutor(Subscriber subscriber) {
        subscriber.onNext(Boolean.valueOf(this.configManager.readConfig()));
        subscriber.onCompleted();
    }

    protected void postMessage(int i) {
        this.handler.sendEmptyMessage(i);
    }

    protected void postMessage(int i, @NonNull Object obj) {
        this.handler.sendMessage(this.handler.obtainMessage(i, obj));
    }
}
