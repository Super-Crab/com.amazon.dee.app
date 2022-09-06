package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.DiscoveryEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.ProvisioningEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.data.ExceptionData;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.data.PeripheralDiscoveredEventData;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.ProvisioningManagerProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.error.DeviceDiscoveryError;
import com.amazon.whisperjoin.provisionerSDK.utility.Observers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
/* loaded from: classes13.dex */
public class DeviceDiscoveryStream {
    private static final String TAG = "DeviceDiscoveryStream";
    private final ConnectableObservable<DeviceDiscoveryEvent> mDeviceDiscoveryEventStream;
    private EventObserver mEventObserver;
    private boolean mIsDiscovering;
    private final ProvisioningManagerProvider mProvisioningManagerProvider;
    private Scheduler mObserveOnScheduler = AndroidSchedulers.mainThread();
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private Subject<DeviceDiscoveryEvent> mDiscoveryStateChangeSubject = PublishSubject.create();

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream$4  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DiscoveryEvent = new int[DiscoveryEvent.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DiscoveryEvent[DiscoveryEvent.WJ_DEVICE_DISCOVERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DiscoveryEvent[DiscoveryEvent.THIRD_PARTY_DEVICE_DISCOVERED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DiscoveryEvent[DiscoveryEvent.WJ_DEVICE_UPDATED_RADIO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DiscoveryEvent[DiscoveryEvent.THIRD_PARTY_DEVICE_UPDATED_RADIO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DiscoveryEvent[DiscoveryEvent.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes13.dex */
    private static class EventObserver implements Observers.RunnableEvent<ProvisioningEvent<DiscoveryEvent>> {
        private ObservableEmitter<ProvisioningEvent<DiscoveryEvent>> mEmitter;

        public EventObserver(ObservableEmitter<ProvisioningEvent<DiscoveryEvent>> observableEmitter) {
            this.mEmitter = observableEmitter;
        }

        private boolean isEmitterActive() {
            ObservableEmitter<ProvisioningEvent<DiscoveryEvent>> observableEmitter = this.mEmitter;
            return observableEmitter != null && !observableEmitter.isDisposed();
        }

        protected void onError(Throwable th) {
            if (isEmitterActive()) {
                this.mEmitter.onError(th);
            }
        }

        protected void onNext(ProvisioningEvent<DiscoveryEvent> provisioningEvent) {
            if (isEmitterActive()) {
                this.mEmitter.onNext(provisioningEvent);
            }
        }

        @Override // com.amazon.whisperjoin.provisionerSDK.utility.Observers.RunnableEvent
        public void run(Object obj, ProvisioningEvent<DiscoveryEvent> provisioningEvent) {
            onNext(provisioningEvent);
        }
    }

    public DeviceDiscoveryStream(final ProvisioningManagerProvider provisioningManagerProvider) {
        this.mProvisioningManagerProvider = provisioningManagerProvider;
        this.mDeviceDiscoveryEventStream = Observable.create(new ObservableOnSubscribe<ProvisioningEvent<DiscoveryEvent>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream.3
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public void subscribe(@NonNull ObservableEmitter<ProvisioningEvent<DiscoveryEvent>> observableEmitter) throws Exception {
                WJLog.d(DeviceDiscoveryStream.TAG, "Subscribe, adding discovery observer");
                DeviceDiscoveryStream.this.mEventObserver = new EventObserver(observableEmitter);
                provisioningManagerProvider.addDiscoveryEventObserver(DeviceDiscoveryStream.this.mEventObserver);
            }
        }).map(new Function<ProvisioningEvent<DiscoveryEvent>, DeviceDiscoveryEvent>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public DeviceDiscoveryEvent mo10358apply(ProvisioningEvent<DiscoveryEvent> provisioningEvent) throws Exception {
                Exception exception;
                int ordinal = provisioningEvent.getEvent().ordinal();
                if (ordinal != 0) {
                    if (ordinal != 1) {
                        if (ordinal != 2) {
                            if (ordinal != 3) {
                                if (ordinal == 4) {
                                    ExceptionData exceptionData = (ExceptionData) provisioningEvent.getEventObject();
                                    if (exceptionData == null) {
                                        WJLog.w(DeviceDiscoveryStream.TAG, "No ExceptionData found with discovery error");
                                        exception = new DeviceDiscoveryError();
                                    } else {
                                        exception = exceptionData.getException();
                                    }
                                    return DeviceDiscoveryEvent.error(exception);
                                }
                                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unhandled event: ");
                                outline107.append(provisioningEvent.getEvent());
                                throw new IllegalStateException(outline107.toString());
                            }
                        }
                    }
                    return DeviceDiscoveryEvent.update(((PeripheralDiscoveredEventData) provisioningEvent.getEventObject()).getDeviceDetails());
                }
                return DeviceDiscoveryEvent.success(((PeripheralDiscoveredEventData) provisioningEvent.getEventObject()).getDeviceDetails());
            }
        }).doFinally(new io.reactivex.rxjava3.functions.Action() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream.1
            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Exception {
                WJLog.d(DeviceDiscoveryStream.TAG, "Removing discovery observer");
                if (DeviceDiscoveryStream.this.mIsDiscovering) {
                    DeviceDiscoveryStream.this.mProvisioningManagerProvider.stopDiscovery();
                }
                provisioningManagerProvider.removeDiscoveryEventObserver(DeviceDiscoveryStream.this.mEventObserver);
            }
        }).mergeWith(this.mDiscoveryStateChangeSubject).observeOn(this.mObserveOnScheduler).publish();
        this.mCompositeDisposable.add(this.mDeviceDiscoveryEventStream.connect());
    }

    public void dispose() {
        WJLog.d(TAG, "Disposing of DeviceDiscoveryStream");
        this.mCompositeDisposable.dispose();
    }

    public Observable<DeviceDiscoveryEvent> getEventStream() {
        return this.mDeviceDiscoveryEventStream;
    }

    public Disposable getStreamDisposable() {
        return this.mCompositeDisposable;
    }

    public void pause() {
        this.mProvisioningManagerProvider.pauseDiscovery();
        this.mIsDiscovering = false;
        this.mDiscoveryStateChangeSubject.onNext(DeviceDiscoveryEvent.idle());
    }

    public void resume() {
        this.mIsDiscovering = true;
        this.mProvisioningManagerProvider.resumeDiscovery();
        this.mDiscoveryStateChangeSubject.onNext(DeviceDiscoveryEvent.inProgress());
    }

    public void start(DiscoverySettings discoverySettings) {
        try {
            this.mIsDiscovering = true;
            this.mProvisioningManagerProvider.startDiscovery(discoverySettings);
            this.mDiscoveryStateChangeSubject.onNext(DeviceDiscoveryEvent.inProgress());
        } catch (Exception e) {
            this.mDiscoveryStateChangeSubject.onNext(DeviceDiscoveryEvent.error(e));
        }
    }

    public void stop() {
        this.mProvisioningManagerProvider.stopDiscovery();
        this.mIsDiscovering = false;
        this.mDiscoveryStateChangeSubject.onNext(DeviceDiscoveryEvent.idle());
    }
}
