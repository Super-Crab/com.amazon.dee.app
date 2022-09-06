package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning;

import com.amazon.whisperjoin.common.sharedtypes.ble.events.CBLRegistrationUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisioningDoneFailureEvent;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.WifiConnectionUpdatedEvent;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.DeviceEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.ProvisioningEvent;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.ProvisioningManagerProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisionableProvisioningDoneFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.UnexpectedConnectionFailure;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.provisionerSDK.utility.Observers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.observables.ConnectableObservable;
/* loaded from: classes13.dex */
public class DeviceEventStream {
    private static final String TAG = "DeviceEventStream";
    private EventObserver mEventObserver;
    private final ConnectableObservable<WJResult> mEventsStream;
    private final Disposable mStreamDisposable;

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream$4  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DeviceEvent = new int[DeviceEvent.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DeviceEvent[DeviceEvent.DISCONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DeviceEvent[DeviceEvent.NETWORK_SCAN_COMPLETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DeviceEvent[DeviceEvent.NETWORK_STATE_UPDATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DeviceEvent[DeviceEvent.REGISTRATION_STATE_UPDATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DeviceEvent[DeviceEvent.PROVISIONING_DONE_SUCCESS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DeviceEvent[DeviceEvent.PROVISIONING_DONE_FAILURE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes13.dex */
    private static class EventObserver implements Observers.RunnableEvent<ProvisioningEvent<DeviceEvent>> {
        private final ObservableEmitter<WJResult> mDeviceResultObservableEmitter;
        private final ProvisioningManagerProvider mProvisioningManager;

        public EventObserver(ObservableEmitter<WJResult> observableEmitter, ProvisioningManagerProvider provisioningManagerProvider) {
            this.mDeviceResultObservableEmitter = observableEmitter;
            this.mProvisioningManager = provisioningManagerProvider;
        }

        @Override // com.amazon.whisperjoin.provisionerSDK.utility.Observers.RunnableEvent
        public void run(Object obj, ProvisioningEvent<DeviceEvent> provisioningEvent) {
            WJProvisionee createWJProvsionee = this.mProvisioningManager.createWJProvsionee((WhisperJoinPeripheralDeviceDetails) obj);
            String str = DeviceEventStream.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Handling Event: ");
            outline107.append(provisioningEvent.getEvent().name());
            outline107.append(" for Device: ");
            outline107.append(createWJProvsionee);
            WJLog.d(str, outline107.toString());
            int ordinal = provisioningEvent.getEvent().ordinal();
            if (ordinal == 0) {
                WifiConnectionDetails eventData = ((WifiConnectionUpdatedEvent) provisioningEvent.getEventObject()).getEventData();
                WJLog.i(DeviceEventStream.TAG, "Wifi connection state updated received");
                String str2 = DeviceEventStream.TAG;
                WJLog.d(str2, "WifiConnectionDetails Updated: " + eventData);
                this.mDeviceResultObservableEmitter.onNext(WJResult.WifiConnectionStateChange.update(createWJProvsionee, eventData));
            } else if (ordinal == 1) {
                this.mDeviceResultObservableEmitter.onNext(WJResult.NetworkScanComplete.update(createWJProvsionee));
            } else if (ordinal == 2) {
                CBLRegistrationDetails eventData2 = ((CBLRegistrationUpdatedEvent) provisioningEvent.getEventObject()).getEventData();
                WJLog.i(DeviceEventStream.TAG, "Registration state updated received");
                String str3 = DeviceEventStream.TAG;
                WJLog.d(str3, "CBLRegistrationDetails Updated: " + eventData2);
                this.mDeviceResultObservableEmitter.onNext(WJResult.RegistrationStateChange.update(createWJProvsionee, eventData2));
            } else if (ordinal == 3) {
                WJLog.i(DeviceEventStream.TAG, "Provisioning done success received");
                this.mDeviceResultObservableEmitter.onNext(WJResult.ProvisioningDoneStateChange.success(createWJProvsionee));
            } else if (ordinal == 4) {
                WJLog.e(DeviceEventStream.TAG, "Provisioning done failure received");
                this.mDeviceResultObservableEmitter.onNext(WJResult.ProvisioningDoneStateChange.error(createWJProvsionee, new ProvisionableProvisioningDoneFailureException(((ProvisioningDoneFailureEvent) provisioningEvent.getEventObject()).getEventData())));
            } else if (ordinal == 5) {
                this.mDeviceResultObservableEmitter.onNext(WJResult.Disconnection.error(createWJProvsionee, new UnexpectedConnectionFailure(((Integer) provisioningEvent.getEventObject()).intValue())));
            } else {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("This event isn't supported: ");
                outline1072.append(provisioningEvent.toString());
                throw new IllegalArgumentException(outline1072.toString());
            }
        }
    }

    public DeviceEventStream(final ProvisioningManagerProvider provisioningManagerProvider) {
        this.mEventsStream = Observable.create(new ObservableOnSubscribe<WJResult>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream.2
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public void subscribe(@NonNull ObservableEmitter<WJResult> observableEmitter) throws Exception {
                WJLog.d(DeviceEventStream.TAG, "Subscribe, adding observer");
                DeviceEventStream.this.mEventObserver = new EventObserver(observableEmitter, provisioningManagerProvider);
                provisioningManagerProvider.addDeviceEventObserver(DeviceEventStream.this.mEventObserver);
            }
        }).doFinally(new io.reactivex.rxjava3.functions.Action() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream.1
            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Exception {
                WJLog.d(DeviceEventStream.TAG, "Removing event observer");
                provisioningManagerProvider.removeDeviceEventObserver(DeviceEventStream.this.mEventObserver);
            }
        }).publish();
        this.mStreamDisposable = this.mEventsStream.connect();
    }

    public void dispose() {
        WJLog.d(TAG, "Disposing of DeviceEventStream");
        this.mStreamDisposable.dispose();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <TDeviceResult extends WJResult> Observable<TDeviceResult> filterResultForDevice(Class<TDeviceResult> cls, final WJProvisionee wJProvisionee) {
        return getStream().ofType(cls).filter(new Predicate<TDeviceResult>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream.3
            /* JADX WARN: Incorrect types in method signature: (TTDeviceResult;)Z */
            @Override // io.reactivex.rxjava3.functions.Predicate
            public boolean test(@NonNull WJResult wJResult) throws Exception {
                return wJProvisionee.getPeripheralDeviceDetails().getDeviceIdentity().equals(wJResult.getWJProvisionee().getPeripheralDeviceDetails().getDeviceIdentity());
            }
        });
    }

    public Observable<WJResult> getStream() {
        if (!this.mStreamDisposable.isDisposed()) {
            return this.mEventsStream;
        }
        throw new IllegalStateException("Stream is disposed");
    }

    public Disposable getStreamDisposable() {
        return this.mStreamDisposable;
    }

    public DeviceEventStream(Observable<WJResult> observable) {
        this.mEventsStream = observable.publish();
        this.mStreamDisposable = this.mEventsStream.connect();
    }
}
