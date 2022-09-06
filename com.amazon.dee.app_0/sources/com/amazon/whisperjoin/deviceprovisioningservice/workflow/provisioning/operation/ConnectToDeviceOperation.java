package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DeviceConnectionConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderObservableSourceTransformer;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.Action;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ConnectionOperationStatusUpdate;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricSourceName;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
/* loaded from: classes13.dex */
public class ConnectToDeviceOperation extends DeviceOperation<Action.ConnectToDevice> {
    private static final String TAG = "ConnectToDeviceOperation";
    private final MetricsRecorderProvider mMetricsRecorderProvider;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class ConnectionOperationError extends Exception {
        private final ConnectionOperationStatusUpdate mConnectionStatusUpdate;
        private final Throwable mFailureCause;

        public ConnectionOperationError(Throwable th, ConnectionOperationStatusUpdate connectionOperationStatusUpdate) {
            this.mFailureCause = th;
            this.mConnectionStatusUpdate = connectionOperationStatusUpdate;
        }

        public ConnectionOperationStatusUpdate getConnectionStatusUpdate() {
            return this.mConnectionStatusUpdate;
        }

        public Throwable getFailureCause() {
            return this.mFailureCause;
        }
    }

    public ConnectToDeviceOperation(MetricsRecorderProvider metricsRecorderProvider) {
        this.mMetricsRecorderProvider = metricsRecorderProvider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<WJResult> connectToDevice(WJProvisionee wJProvisionee, final DeviceConnectionConfiguration deviceConnectionConfiguration) {
        return wJProvisionee.connectToDevice(deviceConnectionConfiguration).andThen(Observable.just(WJResult.Connection.update(wJProvisionee, new ConnectionOperationStatusUpdate(deviceConnectionConfiguration, ConnectionOperationStatusUpdate.State.CONNECTED_TO_DEVICE)))).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.ConnectToDeviceOperation.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<? extends WJResult> mo10358apply(Throwable th) throws Exception {
                return Observable.error(new ConnectionOperationError(th, new ConnectionOperationStatusUpdate(deviceConnectionConfiguration, ConnectionOperationStatusUpdate.State.CONNECTING_TO_DEVICE_FAILURE)));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<WJResult> startProvisioningEstablishingSecureChannel(WJProvisionee wJProvisionee, final DeviceConnectionConfiguration deviceConnectionConfiguration) {
        return wJProvisionee.startProvisioning().andThen(Observable.just(WJResult.Connection.success(wJProvisionee, new ConnectionOperationStatusUpdate(deviceConnectionConfiguration, ConnectionOperationStatusUpdate.State.SECURE_CHANNEL_ESTABLISHED)))).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.ConnectToDeviceOperation.3
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<? extends WJResult> mo10358apply(Throwable th) throws Exception {
                return Observable.error(new ConnectionOperationError(th, new ConnectionOperationStatusUpdate(deviceConnectionConfiguration, ConnectionOperationStatusUpdate.State.SECURE_CHANNEL_ESTABLISHMENT_FAILURE)));
            }
        });
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<WJResult> apply(@NonNull Observable<Action.ConnectToDevice> observable) {
        return observable.flatMap(new Function<Action.ConnectToDevice, ObservableSource<WJResult>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.ConnectToDeviceOperation.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<WJResult> mo10358apply(@NonNull Action.ConnectToDevice connectToDevice) {
                final WJProvisionee wJProvisionee = connectToDevice.getWJProvisionee();
                final DeviceConnectionConfiguration data = connectToDevice.getData();
                return Observable.concat(ConnectToDeviceOperation.this.connectToDevice(wJProvisionee, data), ConnectToDeviceOperation.this.startProvisioningEstablishingSecureChannel(wJProvisionee, data)).compose(new MetricsRecorderObservableSourceTransformer(ConnectToDeviceOperation.this.mMetricsRecorderProvider, WhisperJoinMetricSourceName.CONNECT_TO_DEVICE_OPERATION)).onErrorReturn(new Function<Throwable, WJResult>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.ConnectToDeviceOperation.1.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply  reason: avoid collision after fix types in other method */
                    public WJResult mo10358apply(@NonNull Throwable th) {
                        WJLog.e(ConnectToDeviceOperation.TAG, "Connection was unsuccessful, cleaning up connection.");
                        wJProvisionee.disconnect();
                        if (th instanceof ConnectionOperationError) {
                            ConnectionOperationError connectionOperationError = (ConnectionOperationError) th;
                            return WJResult.Connection.error(wJProvisionee, connectionOperationError.getConnectionStatusUpdate(), connectionOperationError.getFailureCause());
                        }
                        return WJResult.Connection.error(wJProvisionee, new ConnectionOperationStatusUpdate(data, ConnectionOperationStatusUpdate.State.OTHER_FAILURE), th);
                    }
                }).startWithItem(WJResult.Connection.inProgress(wJProvisionee, new ConnectionOperationStatusUpdate(data, ConnectionOperationStatusUpdate.State.CONNECTING)));
            }
        });
    }
}
