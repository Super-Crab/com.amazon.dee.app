package com.amazon.alexa.mode.statemachine;

import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mode.Constants;
import com.amazon.alexa.mode.ModeConstants;
import com.amazon.alexa.mode.ModeName;
import com.amazon.alexa.mode.bluetooth.AutoBluetoothObserver;
import com.amazon.alexa.mode.util.AutomotiveAccessoryConnectivityObserver;
import com.amazon.alexa.mode.util.CatapultTtsDeviceMonitor;
import com.amazon.alexa.mode.util.DeviceSetupMonitor;
import com.amazon.alexa.mode.util.LifecycleHelperV2;
import com.amazon.alexa.mode.util.LogTag;
import com.amazon.alexa.mode.util.ScreenDisplayHelper;
import com.amazon.alexa.mode.util.charging.wireless.WirelessChargingStatusObserver;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
/* loaded from: classes9.dex */
public class StateMachineEventObserver {
    private static final String TAG = LogTag.forClass(StateMachineEventObserver.class);
    private Disposable mAccessoryDisposable;
    @VisibleForTesting
    AutoBluetoothObserver mAutoBluetoothObserver;
    private final AutomotiveAccessoryConnectivityObserver mAutomotiveDeviceObserver;
    private Disposable mCarBluetoothDisposable;
    @VisibleForTesting
    CatapultTtsDeviceMonitor mCatapultTtsDeviceMonitor;
    private final Context mContext;
    @VisibleForTesting
    SimpleMultiFilterSubscriber mDriveModeNotificationDeepLinkViewSubscriber;
    private final DriveModeStateMachine mDriveModeStateMachine;
    private boolean mIsEliseEnabled;
    private boolean mIsSubscribedToManualIngressEvents;
    private final LifecycleHelperV2 mLifeCycleHelper;
    private Disposable mLifecycleDisposable;
    @VisibleForTesting
    ScreenDisplayHelper mScreenDisplayHelper;
    private Disposable mScreenDisplayHelperDisposable;
    private CompositeDisposable mWirelessChargingErrorStatusDisposable;
    @VisibleForTesting
    WirelessChargingStatusObserver mWirelessChargingStatusObserver;
    @VisibleForTesting
    SimpleMultiFilterSubscriber mDiveModeEventSubscriber = new SimpleMultiFilterSubscriber();
    @VisibleForTesting
    SimpleMultiFilterSubscriber mManualIngressEventSubscriber = new SimpleMultiFilterSubscriber();
    @VisibleForTesting
    DeviceSetupMonitor mDeviceSetupMonitor = DeviceSetupMonitor.getInstance();
    private CompositeDisposable mDeviceSetupMonitorDisposable = new CompositeDisposable();
    private FeatureServiceV2 mFeatureServiceV2 = (FeatureServiceV2) GeneratedOutlineSupport1.outline21(FeatureServiceV2.class);
    @VisibleForTesting
    FeatureServiceV2.FeatureUpdateListener mEliseFeatureUpdateListener = createEliseFeatureUpdateListener();

    public StateMachineEventObserver(Context context, DriveModeStateMachine driveModeStateMachine, AutomotiveAccessoryConnectivityObserver automotiveAccessoryConnectivityObserver, LifecycleHelperV2 lifecycleHelperV2, CatapultTtsDeviceMonitor catapultTtsDeviceMonitor, AutoBluetoothObserver autoBluetoothObserver, WirelessChargingStatusObserver wirelessChargingStatusObserver) {
        this.mContext = context;
        this.mDriveModeStateMachine = driveModeStateMachine;
        this.mAutomotiveDeviceObserver = automotiveAccessoryConnectivityObserver;
        this.mLifeCycleHelper = lifecycleHelperV2;
        this.mCatapultTtsDeviceMonitor = catapultTtsDeviceMonitor;
        this.mWirelessChargingStatusObserver = wirelessChargingStatusObserver;
        this.mScreenDisplayHelper = new ScreenDisplayHelper(context, (PowerManager) context.getSystemService("power"));
        this.mAutoBluetoothObserver = autoBluetoothObserver;
        this.mFeatureServiceV2.observeFeature("ALEXA_AUTO_ANDROID_ELISE_ENABLED", this.mEliseFeatureUpdateListener);
    }

    private FeatureServiceV2.FeatureUpdateListener createEliseFeatureUpdateListener() {
        return new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.alexa.mode.statemachine.StateMachineEventObserver.1
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public void onFeatureUpdate(String str) {
                String unused = StateMachineEventObserver.TAG;
                GeneratedOutlineSupport1.outline158("onFeatureUpdate called on ", str);
                StateMachineEventObserver stateMachineEventObserver = StateMachineEventObserver.this;
                stateMachineEventObserver.mIsEliseEnabled = stateMachineEventObserver.mFeatureServiceV2.hasAccess("ALEXA_AUTO_ANDROID_ELISE_ENABLED", false);
                if (StateMachineEventObserver.this.mIsEliseEnabled) {
                    StateMachineEventObserver.this.subscribeForManualIngressEvents();
                } else {
                    StateMachineEventObserver.this.unsubscribeForManualIngressEvents();
                }
            }
        };
    }

    private boolean isManualIngressEnabled() {
        return this.mIsEliseEnabled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$subscribeForDeviceSetupEvents$10(Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error encountered: " + th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$subscribeForDeviceSetupEvents$12(Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error encountered: " + th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$subscribeForDeviceSetupEvents$14(Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error encountered: " + th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$subscribeForDeviceSetupEvents$16(Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error encountered: " + th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$subscribeForWirelessChargingStatusChanges$2(Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "Error encountered: " + th);
    }

    private void subscribeForAccessoryDeviceConnection() {
        if (this.mAccessoryDisposable != null) {
            Log.e(TAG, "subscribeForAccessoryDeviceConnection | Should not subscribe again");
        } else {
            this.mAccessoryDisposable = this.mAutomotiveDeviceObserver.connectedAutomotiveDeviceGroups().subscribe(new Consumer() { // from class: com.amazon.alexa.mode.statemachine.-$$Lambda$StateMachineEventObserver$Zxie3IMofv9DfXESKSfFO7F9nco
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    StateMachineEventObserver.this.lambda$subscribeForAccessoryDeviceConnection$3$StateMachineEventObserver((List) obj);
                }
            }, $$Lambda$StateMachineEventObserver$qkA9fiJ34uBNnvwOzr26lAbJLVE.INSTANCE);
        }
    }

    private void subscribeForAutoBluetoothConnection() {
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this.mContext, "android.permission.BLUETOOTH_CONNECT") == 0) {
            this.mAutoBluetoothObserver.initialize();
            this.mCarBluetoothDisposable = this.mAutoBluetoothObserver.isConnectedToAutoBluetooth().subscribe(new Consumer() { // from class: com.amazon.alexa.mode.statemachine.-$$Lambda$StateMachineEventObserver$p4xpqtOdOQVDpPdqSRv2TMn6HmQ
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    StateMachineEventObserver.this.lambda$subscribeForAutoBluetoothConnection$5$StateMachineEventObserver((Boolean) obj);
                }
            });
        }
    }

    private void subscribeForDeepLinkNotification() {
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(eventBus);
        this.mDriveModeNotificationDeepLinkViewSubscriber = new SimpleMultiFilterSubscriber();
        this.mDriveModeNotificationDeepLinkViewSubscriber.subscribe(new EventTypeMessageFilter("notification::DeepLinkView"), new MessageHandler() { // from class: com.amazon.alexa.mode.statemachine.-$$Lambda$StateMachineEventObserver$dQ1zlY5BDSsyx6DVSAqfL3oBY9A
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                StateMachineEventObserver.this.lambda$subscribeForDeepLinkNotification$0$StateMachineEventObserver(message);
            }
        });
        eventBus.subscribe(this.mDriveModeNotificationDeepLinkViewSubscriber);
    }

    private void subscribeForDeviceSetupEvents() {
        this.mDeviceSetupMonitor.startMonitoring();
        this.mDeviceSetupMonitorDisposable.add(this.mDeviceSetupMonitor.isInDeviceSetup().subscribe(new Consumer() { // from class: com.amazon.alexa.mode.statemachine.-$$Lambda$StateMachineEventObserver$NH2OEW89XiUEdCsnYYLn4PoFQB4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StateMachineEventObserver.this.lambda$subscribeForDeviceSetupEvents$9$StateMachineEventObserver((Boolean) obj);
            }
        }, $$Lambda$StateMachineEventObserver$adnuMw77AYaNVi8XP4Xk29y0Wuk.INSTANCE));
        this.mDeviceSetupMonitorDisposable.add(this.mDeviceSetupMonitor.isInLoginOOBE().subscribe(new Consumer() { // from class: com.amazon.alexa.mode.statemachine.-$$Lambda$StateMachineEventObserver$Wu4qP4vvcIevWL4cgubW4glhRSc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StateMachineEventObserver.this.lambda$subscribeForDeviceSetupEvents$11$StateMachineEventObserver((Boolean) obj);
            }
        }, $$Lambda$StateMachineEventObserver$P30CsDcaRGTKKuOTN0hRSe4KgeA.INSTANCE));
        this.mDeviceSetupMonitorDisposable.add(this.mDeviceSetupMonitor.isInDriveModeAllDoneChoice().subscribe(new Consumer() { // from class: com.amazon.alexa.mode.statemachine.-$$Lambda$StateMachineEventObserver$cMYYvnO1ZRpdrOUYUfnnDtnPc3g
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StateMachineEventObserver.this.lambda$subscribeForDeviceSetupEvents$13$StateMachineEventObserver((Boolean) obj);
            }
        }, $$Lambda$StateMachineEventObserver$2B6JiMgJP0tA9r9iQaVdOF7f5kc.INSTANCE));
        this.mDeviceSetupMonitorDisposable.add(this.mDeviceSetupMonitor.isInDriveModeFTUE().subscribe(new Consumer() { // from class: com.amazon.alexa.mode.statemachine.-$$Lambda$StateMachineEventObserver$ykqQo_VMDQ5hDSK2vWpiHxoFC_g
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StateMachineEventObserver.this.lambda$subscribeForDeviceSetupEvents$15$StateMachineEventObserver((DeviceSetupMonitor.DriveModeFTUEState) obj);
            }
        }, $$Lambda$StateMachineEventObserver$ky_gSPnV1Z6LWvugG79SBVRL_E.INSTANCE));
    }

    private void subscribeForDriveModeEvents() {
        this.mDiveModeEventSubscriber.subscribe(new EventTypeMessageFilter(ModeConstants.MODE_SWITCHED_EVENT), new MessageHandler() { // from class: com.amazon.alexa.mode.statemachine.-$$Lambda$StateMachineEventObserver$BxETCEM_MNyJnheYNsNCpQ7kiQc
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                StateMachineEventObserver.this.lambda$subscribeForDriveModeEvents$6$StateMachineEventObserver(message);
            }
        });
        EventBus eventBus = (EventBus) ComponentRegistry.getInstance().get(EventBus.class).orNull();
        if (eventBus != null) {
            eventBus.subscribe(this.mDiveModeEventSubscriber);
        }
    }

    private void subscribeForLifecycleEvents() {
        this.mLifeCycleHelper.init();
        this.mLifecycleDisposable = this.mLifeCycleHelper.getLifecycleState().subscribe(new Consumer() { // from class: com.amazon.alexa.mode.statemachine.-$$Lambda$StateMachineEventObserver$aBAJXr1NruKGb0E_dUrMJp2sAsY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StateMachineEventObserver.this.lambda$subscribeForLifecycleEvents$8$StateMachineEventObserver((LifecycleHelperV2.LifeCycleState) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void subscribeForManualIngressEvents() {
        if (!isManualIngressEnabled() || this.mIsSubscribedToManualIngressEvents) {
            return;
        }
        this.mManualIngressEventSubscriber.subscribe(new EventTypeMessageFilter(Constants.DRIVE_MODE_MANUAL_INGRESS_REQUEST), new MessageHandler() { // from class: com.amazon.alexa.mode.statemachine.-$$Lambda$StateMachineEventObserver$jPxkca2CR0Nh82UsjnDhe3Hph6E
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                StateMachineEventObserver.this.lambda$subscribeForManualIngressEvents$17$StateMachineEventObserver(message);
            }
        });
        EventBus eventBus = (EventBus) ComponentRegistry.getInstance().get(EventBus.class).orNull();
        if (eventBus != null) {
            eventBus.subscribe(this.mManualIngressEventSubscriber);
        }
        this.mIsSubscribedToManualIngressEvents = true;
    }

    private void subscribeForScreenOnEvents() {
        this.mScreenDisplayHelperDisposable = this.mScreenDisplayHelper.subscribeToScreenOn().subscribe(new Consumer() { // from class: com.amazon.alexa.mode.statemachine.-$$Lambda$StateMachineEventObserver$QMZp4t8-7C307x4cnAdWjwpoZFE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StateMachineEventObserver.this.lambda$subscribeForScreenOnEvents$7$StateMachineEventObserver((Boolean) obj);
            }
        });
    }

    private void subscribeForWirelessChargingStatusChanges() {
        this.mWirelessChargingStatusObserver.startObservingForDeviceConnectionChanges();
        if (this.mWirelessChargingErrorStatusDisposable == null) {
            this.mWirelessChargingErrorStatusDisposable = new CompositeDisposable();
        }
        this.mWirelessChargingErrorStatusDisposable.add(this.mWirelessChargingStatusObserver.getWirelessChargingErrorStatusObservable().subscribe(new Consumer() { // from class: com.amazon.alexa.mode.statemachine.-$$Lambda$StateMachineEventObserver$-w9qdwqkW5CUQR-DFE8QoEhL22M
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StateMachineEventObserver.this.lambda$subscribeForWirelessChargingStatusChanges$1$StateMachineEventObserver((Boolean) obj);
            }
        }, $$Lambda$StateMachineEventObserver$_oq8cbMKoM88r58zXair0F5GOKo.INSTANCE));
    }

    private void unsubscribeForAutoBluetoothConnection() {
        Disposable disposable = this.mCarBluetoothDisposable;
        if (disposable == null) {
            Log.e(TAG, "unsubscribeForAutoBluetoothConnection |  nothing to unsubscribe from");
            return;
        }
        disposable.dispose();
        this.mCarBluetoothDisposable = null;
    }

    private void unsubscribeForDeepLinkNotification() {
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        if (eventBus != null) {
            eventBus.unsubscribe(this.mDriveModeNotificationDeepLinkViewSubscriber);
        }
    }

    private void unsubscribeForDeviceSetupEvents() {
        CompositeDisposable compositeDisposable = this.mDeviceSetupMonitorDisposable;
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            this.mDeviceSetupMonitorDisposable = null;
            this.mDeviceSetupMonitor.stopMonitoring();
        }
    }

    private void unsubscribeForDriveModeEvents() {
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        if (eventBus != null) {
            eventBus.unsubscribe(this.mDiveModeEventSubscriber);
        }
    }

    private void unsubscribeForLifecycleEvents() {
        this.mLifeCycleHelper.unInitialize();
        Disposable disposable = this.mLifecycleDisposable;
        if (disposable != null) {
            disposable.dispose();
            this.mLifecycleDisposable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unsubscribeForManualIngressEvents() {
        if (!this.mIsSubscribedToManualIngressEvents) {
            return;
        }
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        if (eventBus != null) {
            eventBus.unsubscribe(this.mManualIngressEventSubscriber);
        }
        this.mIsSubscribedToManualIngressEvents = false;
    }

    private void unsubscribeForScreenOnEvents() {
        Disposable disposable = this.mScreenDisplayHelperDisposable;
        if (disposable != null) {
            disposable.dispose();
            this.mScreenDisplayHelperDisposable = null;
        }
    }

    private void unsubscribeForWirelessChargingStatusChanges() {
        CompositeDisposable compositeDisposable = this.mWirelessChargingErrorStatusDisposable;
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            this.mWirelessChargingErrorStatusDisposable = null;
            WirelessChargingStatusObserver wirelessChargingStatusObserver = this.mWirelessChargingStatusObserver;
            if (wirelessChargingStatusObserver == null) {
                return;
            }
            wirelessChargingStatusObserver.stopObservingForDeviceConnectionChanges();
        }
    }

    private void unsubscribeFromAccessoryDeviceConnection() {
        Disposable disposable = this.mAccessoryDisposable;
        if (disposable == null) {
            Log.e(TAG, "unsubscribeFromAccessoryDeviceConnection |  nothing to unsubscribe from");
            return;
        }
        disposable.dispose();
        this.mAccessoryDisposable = null;
    }

    public /* synthetic */ void lambda$subscribeForAccessoryDeviceConnection$3$StateMachineEventObserver(List list) throws Throwable {
        this.mCatapultTtsDeviceMonitor.updateConnectedDevice(list);
        if (list.size() > 0) {
            this.mDriveModeStateMachine.onEvent(Event.AccessoryConnected);
        } else {
            this.mDriveModeStateMachine.onEvent(Event.AccessoryDisconnected);
        }
    }

    public /* synthetic */ void lambda$subscribeForAutoBluetoothConnection$5$StateMachineEventObserver(Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            this.mDriveModeStateMachine.onEvent(Event.AutoBluetoothConnected);
        } else {
            this.mDriveModeStateMachine.onEvent(Event.AutoBluetoothDisconnected);
        }
    }

    public /* synthetic */ void lambda$subscribeForDeepLinkNotification$0$StateMachineEventObserver(Message message) {
        this.mDriveModeStateMachine.onEvent(Event.DeepLinkAppLaunch);
    }

    public /* synthetic */ void lambda$subscribeForDeviceSetupEvents$11$StateMachineEventObserver(Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            Log.i(TAG, "Entering Login OOBE workflow.");
            this.mDriveModeStateMachine.onEvent(Event.LoginOOBEStarted);
            return;
        }
        Log.i(TAG, "Exited from login oobe");
        this.mDriveModeStateMachine.onEvent(Event.LoginOOBEEnded);
    }

    public /* synthetic */ void lambda$subscribeForDeviceSetupEvents$13$StateMachineEventObserver(Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            Log.i(TAG, "Yes is pressed in Drive Mode All Done");
            this.mCatapultTtsDeviceMonitor.setSetupStatus();
            this.mDriveModeStateMachine.onEvent(Event.AutoDeviceOOBEDriveModeAllDoneYes);
            return;
        }
        Log.i(TAG, "No is pressed in Drive Mode All Done");
        this.mDriveModeStateMachine.onEvent(Event.AutoDeviceOOBEDriveModeAllDoneNo);
    }

    public /* synthetic */ void lambda$subscribeForDeviceSetupEvents$15$StateMachineEventObserver(DeviceSetupMonitor.DriveModeFTUEState driveModeFTUEState) throws Throwable {
        String str = TAG;
        Log.i(str, "isInDriveModeFTUE | state: " + driveModeFTUEState);
        if (driveModeFTUEState.equals(DeviceSetupMonitor.DriveModeFTUEState.Started)) {
            this.mDriveModeStateMachine.onEvent(Event.FTUEStartedEvent);
        } else if (driveModeFTUEState.equals(DeviceSetupMonitor.DriveModeFTUEState.Cancelled)) {
            this.mDriveModeStateMachine.onEvent(Event.FTUECancelledEvent);
        } else if (driveModeFTUEState.equals(DeviceSetupMonitor.DriveModeFTUEState.Completed_DriveMode)) {
            this.mDriveModeStateMachine.onEvent(Event.FTUECompletedDriveModeEvent);
        } else if (!driveModeFTUEState.equals(DeviceSetupMonitor.DriveModeFTUEState.Completed_StandaloneMode)) {
        } else {
            this.mDriveModeStateMachine.onEvent(Event.FTUECompletedStandaloneModeEvent);
        }
    }

    public /* synthetic */ void lambda$subscribeForDeviceSetupEvents$9$StateMachineEventObserver(Boolean bool) throws Throwable {
        if (!bool.booleanValue()) {
            Log.i(TAG, "Exited from device setup");
            this.mDriveModeStateMachine.onEvent(Event.AutoDeviceOOBEEnded);
            return;
        }
        Log.i(TAG, "Entering device setup");
        this.mDriveModeStateMachine.onEvent(Event.AutoDeviceOOBEStarted);
    }

    public /* synthetic */ void lambda$subscribeForDriveModeEvents$6$StateMachineEventObserver(Message message) {
        String payloadAsString = message.getPayloadAsString();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("driveModeEventSubscriber | event: ");
        outline107.append(message.getEventType());
        outline107.append(" mode: ");
        outline107.append(payloadAsString);
        outline107.toString();
        if (ModeName.DRIVE_MODE.equals(payloadAsString)) {
            this.mDriveModeStateMachine.onEvent(Event.ModeSwitchedDriveMode);
        } else if (!ModeName.MAIN_MODE.equals(payloadAsString)) {
        } else {
            this.mDriveModeStateMachine.onEvent(Event.ModeSwitchedMainMode);
            this.mCatapultTtsDeviceMonitor.resetSetupStatus();
        }
    }

    public /* synthetic */ void lambda$subscribeForLifecycleEvents$8$StateMachineEventObserver(LifecycleHelperV2.LifeCycleState lifeCycleState) throws Throwable {
        if (lifeCycleState.equals(LifecycleHelperV2.LifeCycleState.ActivityStart)) {
            this.mDriveModeStateMachine.onEvent(Event.AppForegrounded);
        } else if (lifeCycleState.equals(LifecycleHelperV2.LifeCycleState.ActivityStop)) {
            this.mDriveModeStateMachine.onEvent(Event.AppBackgrounded);
        } else if (!lifeCycleState.equals(LifecycleHelperV2.LifeCycleState.ActivityDestroy)) {
        } else {
            this.mDriveModeStateMachine.onEvent(Event.ActivityDestroyed);
        }
    }

    public /* synthetic */ void lambda$subscribeForManualIngressEvents$17$StateMachineEventObserver(Message message) {
        this.mDriveModeStateMachine.onEvent(Event.ManualIngressStarted);
    }

    public /* synthetic */ void lambda$subscribeForScreenOnEvents$7$StateMachineEventObserver(Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            this.mDriveModeStateMachine.onEvent(Event.ScreenOn);
        } else {
            this.mDriveModeStateMachine.onEvent(Event.ScreenOff);
        }
    }

    public /* synthetic */ void lambda$subscribeForWirelessChargingStatusChanges$1$StateMachineEventObserver(Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            this.mDriveModeStateMachine.onEvent(Event.WirelessChargingError);
        } else {
            this.mDriveModeStateMachine.onEvent(Event.WirelessChargingNoError);
        }
    }

    public void subscribeForEvents() {
        subscribeForWirelessChargingStatusChanges();
        subscribeForAccessoryDeviceConnection();
        subscribeForDriveModeEvents();
        subscribeForScreenOnEvents();
        subscribeForLifecycleEvents();
        subscribeForDeviceSetupEvents();
        subscribeForDeepLinkNotification();
        subscribeForAutoBluetoothConnection();
        subscribeForManualIngressEvents();
    }

    public void unsubscribeFromEvents() {
        unsubscribeForWirelessChargingStatusChanges();
        unsubscribeFromAccessoryDeviceConnection();
        unsubscribeForDriveModeEvents();
        unsubscribeForScreenOnEvents();
        unsubscribeForLifecycleEvents();
        unsubscribeForDeviceSetupEvents();
        unsubscribeForDeepLinkNotification();
        unsubscribeForAutoBluetoothConnection();
        unsubscribeForManualIngressEvents();
    }
}
