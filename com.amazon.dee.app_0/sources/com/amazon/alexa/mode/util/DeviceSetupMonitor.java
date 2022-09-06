package com.amazon.alexa.mode.util;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.alexa.mode.Constants;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingObserver;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class DeviceSetupMonitor implements RoutingObserver {
    private static final String TAG = LogTag.forClass(DeviceSetupMonitor.class);
    private static DeviceSetupMonitor sInstance;
    SimpleMultiFilterSubscriber deviceSetupStartEventListener;
    SimpleMultiFilterSubscriber driveModeAllDoneChoiceEventListener;
    SimpleMultiFilterSubscriber driveModeFTUEEventListener;
    SimpleMultiFilterSubscriber loginStartEventListener;
    private String mLastNavigatedRoute;
    BehaviorSubject<Boolean> inDeviceSetupSubject = BehaviorSubject.createDefault(false);
    BehaviorSubject<Boolean> inLoginOOBESubject = BehaviorSubject.createDefault(false);
    PublishSubject<Boolean> inDriveModeAllDoneChoiceSubject = PublishSubject.create();
    PublishSubject<DriveModeFTUEState> inDriveModeFTUESubject = PublishSubject.create();

    /* loaded from: classes9.dex */
    public enum DriveModeFTUEState {
        Completed_DriveMode,
        Completed_StandaloneMode,
        Started,
        Cancelled
    }

    @VisibleForTesting
    DeviceSetupMonitor() {
    }

    public static DeviceSetupMonitor getInstance() {
        if (sInstance == null) {
            sInstance = new DeviceSetupMonitor();
        }
        return sInstance;
    }

    private void sendRouteChangedEvent() {
        Message build = new Message.Builder().setEventType(Constants.ON_ROUTE_CHANGE).build();
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(eventBus);
        eventBus.publish(build);
    }

    private void subscribeDriveModeAllDoneEvents() {
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(eventBus);
        this.driveModeAllDoneChoiceEventListener = new SimpleMultiFilterSubscriber();
        this.driveModeAllDoneChoiceEventListener.subscribe(new EventTypeMessageFilter(Constants.DRIVE_MODE_ALLDONE_CHOICE), new MessageHandler() { // from class: com.amazon.alexa.mode.util.-$$Lambda$DeviceSetupMonitor$nE9U2nc9vXMTRuuT0Cw_aBk5Q4E
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DeviceSetupMonitor.this.lambda$subscribeDriveModeAllDoneEvents$5$DeviceSetupMonitor(message);
            }
        });
        eventBus.subscribe(this.driveModeAllDoneChoiceEventListener);
    }

    private void subscribeForFTUEEvents() {
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(eventBus);
        this.driveModeFTUEEventListener = new SimpleMultiFilterSubscriber();
        this.driveModeFTUEEventListener.subscribe(new EventTypeMessageFilter(Constants.DRIVE_MODE_FTUE_COMPLETED), new MessageHandler() { // from class: com.amazon.alexa.mode.util.-$$Lambda$DeviceSetupMonitor$SEHNoXI3CQ01hPYp4NJt4-gFCVU
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DeviceSetupMonitor.this.lambda$subscribeForFTUEEvents$2$DeviceSetupMonitor(message);
            }
        });
        this.driveModeFTUEEventListener.subscribe(new EventTypeMessageFilter(Constants.DRIVE_MODE_FTUE_CANCELLED), new MessageHandler() { // from class: com.amazon.alexa.mode.util.-$$Lambda$DeviceSetupMonitor$TCwowu3GXNMsUg9mB0MY1SaOKrA
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DeviceSetupMonitor.this.lambda$subscribeForFTUEEvents$3$DeviceSetupMonitor(message);
            }
        });
        this.driveModeFTUEEventListener.subscribe(new EventTypeMessageFilter(Constants.DRIVE_MODE_FTUE_STARTED), new MessageHandler() { // from class: com.amazon.alexa.mode.util.-$$Lambda$DeviceSetupMonitor$nZAyUz7TvM53VPNxxgdp8scFoMQ
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DeviceSetupMonitor.this.lambda$subscribeForFTUEEvents$4$DeviceSetupMonitor(message);
            }
        });
        eventBus.subscribe(this.driveModeFTUEEventListener);
    }

    private void subscribeForRouteChanges() {
        RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline21(RoutingService.class);
        Preconditions.checkNotNull(routingService);
        routingService.registerObserver(this);
    }

    private void subscribeSetupStartEvents() {
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(eventBus);
        this.deviceSetupStartEventListener = new SimpleMultiFilterSubscriber();
        this.deviceSetupStartEventListener.subscribe(new EventTypeMessageFilter(Constants.DEVICE_SETUP_STARTED_EVENT), new MessageHandler() { // from class: com.amazon.alexa.mode.util.-$$Lambda$DeviceSetupMonitor$JpEx-aG5_HJdonw7hVz0hIOfv6I
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DeviceSetupMonitor.this.lambda$subscribeSetupStartEvents$0$DeviceSetupMonitor(message);
            }
        });
        eventBus.subscribe(this.deviceSetupStartEventListener);
        this.loginStartEventListener = new SimpleMultiFilterSubscriber();
        this.loginStartEventListener.subscribe(new EventTypeMessageFilter(Constants.DEVICE_LOGIN_EVENT), new MessageHandler() { // from class: com.amazon.alexa.mode.util.-$$Lambda$DeviceSetupMonitor$602V4seU73T0KEz3y4MtkFpnZ7g
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DeviceSetupMonitor.this.lambda$subscribeSetupStartEvents$1$DeviceSetupMonitor(message);
            }
        });
        eventBus.subscribe(this.loginStartEventListener);
    }

    private void unsubscribeDriveModeAllDoneEvents() {
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(eventBus);
        SimpleMultiFilterSubscriber simpleMultiFilterSubscriber = this.driveModeAllDoneChoiceEventListener;
        if (simpleMultiFilterSubscriber != null) {
            eventBus.unsubscribe(simpleMultiFilterSubscriber);
            this.driveModeAllDoneChoiceEventListener = null;
        }
    }

    private void unsubscribeForFTUEEvents() {
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(eventBus);
        SimpleMultiFilterSubscriber simpleMultiFilterSubscriber = this.driveModeFTUEEventListener;
        if (simpleMultiFilterSubscriber != null) {
            eventBus.unsubscribe(simpleMultiFilterSubscriber);
            this.driveModeFTUEEventListener = null;
        }
    }

    private void unsubscribeForRouteChanges() {
        RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline21(RoutingService.class);
        Preconditions.checkNotNull(routingService);
        routingService.unregisterObserver(this);
    }

    private void unsubscribeSetupStartEvents() {
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(eventBus);
        SimpleMultiFilterSubscriber simpleMultiFilterSubscriber = this.deviceSetupStartEventListener;
        if (simpleMultiFilterSubscriber != null) {
            eventBus.unsubscribe(simpleMultiFilterSubscriber);
            this.deviceSetupStartEventListener = null;
        }
        SimpleMultiFilterSubscriber simpleMultiFilterSubscriber2 = this.loginStartEventListener;
        if (simpleMultiFilterSubscriber2 != null) {
            eventBus.unsubscribe(simpleMultiFilterSubscriber2);
            this.loginStartEventListener = null;
        }
    }

    public String getLastNavigatedRoute() {
        return this.mLastNavigatedRoute;
    }

    public Observable<Boolean> isInDeviceSetup() {
        return this.inDeviceSetupSubject.distinctUntilChanged();
    }

    public Observable<Boolean> isInDriveModeAllDoneChoice() {
        return this.inDriveModeAllDoneChoiceSubject;
    }

    public Observable<DriveModeFTUEState> isInDriveModeFTUE() {
        return this.inDriveModeFTUESubject.distinctUntilChanged();
    }

    public Observable<Boolean> isInLoginOOBE() {
        return this.inLoginOOBESubject.distinctUntilChanged();
    }

    public /* synthetic */ void lambda$subscribeDriveModeAllDoneEvents$5$DeviceSetupMonitor(Message message) {
        String payloadAsString = message.getPayloadAsString();
        GeneratedOutlineSupport1.outline158("driveModeAllDoneChoiceEventListener | payload: ", payloadAsString);
        try {
            this.inDriveModeAllDoneChoiceSubject.onNext(Boolean.valueOf(Constants.DRIVE_MODE_ALLDONE_CHOICE_PAYLOAD_VALUE.equals(new JSONObject(payloadAsString).getString(Constants.DRIVE_MODE_ALLDONE_CHOICE_PAYLOAD_KEY))));
        } catch (JSONException unused) {
            Log.e(TAG, "failed to parse DRIVE_MODE_ALLDONE_CHOICE event payload");
        }
    }

    public /* synthetic */ void lambda$subscribeForFTUEEvents$2$DeviceSetupMonitor(Message message) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("driveModeFTUEEventListener | FTUE Completed | msg: ");
        outline107.append(message.getPayloadAsString());
        outline107.toString();
        try {
            if (new JSONObject(message.getPayloadAsString()).getString(Constants.DRIVE_MODE_FTUE_COMPLETED_TYPE_KEY).equals(Constants.DRIVE_MODE_FTUE_COMPLETED_TYPE_STANDALONE_MODE)) {
                this.inDriveModeFTUESubject.onNext(DriveModeFTUEState.Completed_StandaloneMode);
            } else {
                this.inDriveModeFTUESubject.onNext(DriveModeFTUEState.Completed_DriveMode);
            }
        } catch (JSONException e) {
            String str = TAG;
            Log.e(str, "driveModeFTUEEventListener | issue processing ftue completed | e : " + e);
            this.inDriveModeFTUESubject.onNext(DriveModeFTUEState.Completed_DriveMode);
        }
    }

    public /* synthetic */ void lambda$subscribeForFTUEEvents$3$DeviceSetupMonitor(Message message) {
        this.inDriveModeFTUESubject.onNext(DriveModeFTUEState.Cancelled);
    }

    public /* synthetic */ void lambda$subscribeForFTUEEvents$4$DeviceSetupMonitor(Message message) {
        this.inDriveModeFTUESubject.onNext(DriveModeFTUEState.Started);
    }

    public /* synthetic */ void lambda$subscribeSetupStartEvents$0$DeviceSetupMonitor(Message message) {
        this.inDeviceSetupSubject.onNext(true);
    }

    public /* synthetic */ void lambda$subscribeSetupStartEvents$1$DeviceSetupMonitor(Message message) {
        this.inLoginOOBESubject.onNext(true);
    }

    @Override // com.amazon.alexa.routing.api.RoutingObserver
    public void onRouteChanged(@NonNull RouteContext routeContext) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onRouteChanged | route: ");
        outline107.append(routeContext.getRoute());
        outline107.toString();
        sendRouteChangedEvent();
        this.mLastNavigatedRoute = routeContext.getRoute().getName();
        if (routeContext.getRoute().is(RouteName.HOME)) {
            this.inDeviceSetupSubject.onNext(false);
            this.inLoginOOBESubject.onNext(false);
            this.inDriveModeFTUESubject.onNext(DriveModeFTUEState.Cancelled);
        }
    }

    public synchronized void startMonitoring() {
        subscribeSetupStartEvents();
        subscribeForRouteChanges();
        subscribeDriveModeAllDoneEvents();
        subscribeForFTUEEvents();
    }

    public synchronized void stopMonitoring() {
        unsubscribeSetupStartEvents();
        unsubscribeForFTUEEvents();
        unsubscribeForRouteChanges();
        unsubscribeDriveModeAllDoneEvents();
    }
}
