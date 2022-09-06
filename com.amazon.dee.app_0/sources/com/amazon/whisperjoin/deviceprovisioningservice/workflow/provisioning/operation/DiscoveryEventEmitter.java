package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation;

import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.DiscoveryEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.ProvisioningEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.data.ExceptionData;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.data.PeripheralDiscoveredEventData;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.error.DeviceDiscoveryError;
import com.amazon.whisperjoin.provisionerSDK.utility.Observers;
import io.reactivex.rxjava3.core.ObservableEmitter;
/* loaded from: classes13.dex */
abstract class DiscoveryEventEmitter<T> implements Observers.RunnableEvent<ProvisioningEvent<DiscoveryEvent>> {
    private static final String TAG = "DiscoveryEventEmitter";
    private ObservableEmitter<T> mEmitter;

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.DiscoveryEventEmitter$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DiscoveryEvent = new int[DiscoveryEvent.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DiscoveryEvent[DiscoveryEvent.WJ_DEVICE_DISCOVERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DiscoveryEvent[DiscoveryEvent.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DiscoveryEvent[DiscoveryEvent.WJ_DEVICE_UPDATED_RADIO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DiscoveryEvent[DiscoveryEvent.THIRD_PARTY_DEVICE_DISCOVERED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$events$DiscoveryEvent[DiscoveryEvent.THIRD_PARTY_DEVICE_UPDATED_RADIO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private boolean isEmitterActive() {
        ObservableEmitter<T> observableEmitter = this.mEmitter;
        return observableEmitter != null && !observableEmitter.isDisposed();
    }

    protected abstract void onDeviceDiscovered(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails);

    protected void onError(Throwable th) {
        if (isEmitterActive()) {
            this.mEmitter.onError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onNext(T t) {
        if (isEmitterActive()) {
            this.mEmitter.onNext(t);
        }
    }

    public void setEmitter(ObservableEmitter<T> observableEmitter) {
        this.mEmitter = observableEmitter;
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.utility.Observers.RunnableEvent
    public void run(Object obj, ProvisioningEvent<DiscoveryEvent> provisioningEvent) {
        DiscoveryEvent event = provisioningEvent.getEvent();
        int ordinal = event.ordinal();
        if (ordinal == 0) {
            onDeviceDiscovered((WhisperJoinPeripheralDeviceDetails) ((PeripheralDiscoveredEventData) provisioningEvent.getEventObject()).getDeviceDetails());
        } else if (ordinal == 1 || ordinal == 2 || ordinal == 3) {
        } else {
            if (ordinal == 4) {
                ExceptionData exceptionData = (ExceptionData) provisioningEvent.getEventObject();
                if (exceptionData != null) {
                    onError(exceptionData.getException());
                    return;
                }
                WJLog.w(TAG, "No ExceptionData found with discovery error");
                onError(new DeviceDiscoveryError());
                return;
            }
            throw new IllegalStateException("Unhandled Discovery Event State: " + event);
        }
    }
}
