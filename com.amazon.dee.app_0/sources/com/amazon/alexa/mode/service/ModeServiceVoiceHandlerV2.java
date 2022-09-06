package com.amazon.alexa.mode.service;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.DriveModeState;
import com.amazon.alexa.api.compat.AlexaServicesApis;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.alexa.mode.ModeConstants;
import com.amazon.alexa.mode.ModeName;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.mode.util.LifecycleHelperV2;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes9.dex */
public class ModeServiceVoiceHandlerV2 {
    private static final String TAG = "ModeServiceVoiceHandlerV2";
    private AlexaServicesConnection mAlexaServicesConnection;
    private EventBus mEventBus;
    private Disposable mLifecycleDisposable;
    private LifecycleHelperV2 mLifecycleHelper;
    ModeAlexaServicesConnectionListener mModeAlexaServicesConnectionListener;
    @VisibleForTesting
    SimpleMultiFilterSubscriber mModeChangeEventSubscriber;
    private ModeService mModeService;

    /* loaded from: classes9.dex */
    class ModeAlexaServicesConnectionListener implements AlexaServicesConnection.ConnectionListener {
        ModeAlexaServicesConnectionListener() {
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public synchronized void onConnected() {
            Log.i(ModeServiceVoiceHandlerV2.TAG, "Mode Alexa service Connection listener onConnected");
            if (ModeServiceVoiceHandlerV2.this.mModeService != null) {
                ModeServiceVoiceHandlerV2.this.setModeState(ModeServiceVoiceHandlerV2.this.mModeService.getMode());
            }
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
        }
    }

    public ModeServiceVoiceHandlerV2(LifecycleHelperV2 lifecycleHelperV2, ModeService modeService, AlexaServicesConnection alexaServicesConnection) {
        this.mLifecycleHelper = lifecycleHelperV2;
        this.mModeService = modeService;
        this.mAlexaServicesConnection = alexaServicesConnection;
    }

    private void initModeChangeListener() {
        this.mEventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(this.mEventBus);
        this.mModeChangeEventSubscriber = new SimpleMultiFilterSubscriber();
        this.mModeChangeEventSubscriber.subscribeFilter(new EventTypeMessageFilter(ModeConstants.MODE_SWITCHED_EVENT), new MessageHandler() { // from class: com.amazon.alexa.mode.service.-$$Lambda$ModeServiceVoiceHandlerV2$mWLfHhZ98x-p0kgiWo44ijgPv70
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                ModeServiceVoiceHandlerV2.this.lambda$initModeChangeListener$0$ModeServiceVoiceHandlerV2(message);
            }
        });
        this.mEventBus.subscribe(this.mModeChangeEventSubscriber);
    }

    private void subscribeForLifecycleEvents() {
        this.mLifecycleDisposable = this.mLifecycleHelper.getLifecycleState().subscribe(new Consumer() { // from class: com.amazon.alexa.mode.service.-$$Lambda$ModeServiceVoiceHandlerV2$5WpaJ1dEEgVzgxvtoPsi1Bhjt6c
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ModeServiceVoiceHandlerV2.this.lambda$subscribeForLifecycleEvents$1$ModeServiceVoiceHandlerV2((LifecycleHelperV2.LifeCycleState) obj);
            }
        });
    }

    public void initialize() {
        this.mModeAlexaServicesConnectionListener = new ModeAlexaServicesConnectionListener();
        this.mAlexaServicesConnection.registerListener(this.mModeAlexaServicesConnectionListener);
        initModeChangeListener();
        subscribeForLifecycleEvents();
    }

    public /* synthetic */ void lambda$initModeChangeListener$0$ModeServiceVoiceHandlerV2(Message message) {
        setModeState(message.getPayloadAsString());
    }

    public /* synthetic */ void lambda$subscribeForLifecycleEvents$1$ModeServiceVoiceHandlerV2(LifecycleHelperV2.LifeCycleState lifeCycleState) throws Throwable {
        if (lifeCycleState.equals(LifecycleHelperV2.LifeCycleState.ActivityStart)) {
            this.mAlexaServicesConnection.connect();
        } else if (!lifeCycleState.equals(LifecycleHelperV2.LifeCycleState.ActivityStop)) {
        } else {
            this.mAlexaServicesConnection.disconnect();
        }
    }

    public void setModeState(String str) {
        if (this.mAlexaServicesConnection.isConnected()) {
            boolean equals = ModeName.DRIVE_MODE.equals(str);
            AlexaServicesApis.DriveMode.setDriveModeEnabled(this.mAlexaServicesConnection, equals);
            DriveModeState driveModeState = DriveModeState.NONE;
            if (equals) {
                if (this.mModeService.isDriveModeAccessoryDeviceConnected().getValue().booleanValue()) {
                    driveModeState = DriveModeState.ACCESSORY;
                } else if (this.mModeService.isAutoBluetoothDeviceConnected().getValue().booleanValue()) {
                    driveModeState = DriveModeState.AUTOBLUETOOTH;
                } else {
                    driveModeState = DriveModeState.MANUAL;
                }
            }
            AlexaServicesApis.DriveMode.setDriveModeState(this.mAlexaServicesConnection, driveModeState);
        }
    }

    public void uninitialize() {
        this.mEventBus.unsubscribe(this.mModeChangeEventSubscriber);
        this.mAlexaServicesConnection.deregisterListener(this.mModeAlexaServicesConnectionListener);
        this.mAlexaServicesConnection.disconnect();
    }
}
