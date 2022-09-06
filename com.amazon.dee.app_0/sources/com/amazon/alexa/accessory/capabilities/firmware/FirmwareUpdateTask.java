package com.amazon.alexa.accessory.capabilities.firmware;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.capabilities.firmware.FirmwareUpdateTask;
import com.amazon.alexa.accessory.davs.DeviceArtifactContract;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.MetricsUtils;
import com.amazon.alexa.accessory.internal.util.ObservableStream;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.FirmwareSupplier;
import com.amazon.alexa.accessory.metrics.FirmwareTaskMetricsReporter;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.metrics.MetricsReporter;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareProvider;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareUpdateStatus;
import com.amazon.alexa.accessory.streams.control.ControlMessage;
import com.amazon.alexa.accessory.streams.control.ControlResponseHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.amazon.alexa.accessory.transport.TransportDispatcher;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.io.IOException;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class FirmwareUpdateTask implements TaskManager.Task {
    private static final long DEFAULT_SEGMENT_COMPLETION_TIMEOUT_MILLIS = 60000;
    private static final long DEFAULT_SEGMENT_RETRY_DELAY_MILLIS = 5000;
    private static final int SEGMENT_RETRY_COUNT = 3;
    private static final String TAG = "FirmwareUpdateTask:";
    private static final int TRANSPORT_PACKET_PAYLOAD_SIZE = 512;
    private final Callback callback;
    private final List<Firmware.FirmwareComponent> components;
    private final List<DeviceArtifactContract.ArtifactPackage> davsArtifacts;
    private final int deviceId;
    private final DfuCandidateInformation dfuCandidateInformation;
    private final TransportDispatcher dispatcher;
    private Disposable disposable;
    private final FirmwareTaskMetricsReporter metricsReporter;
    private final FirmwareProvider provider;
    private final long segmentCompletionTimeoutMillis;
    private final long segmentRetryDelayMillis;
    private final int segmentSize;
    private final ControlStream stream;
    private final FirmwareSupplier supplier;

    /* loaded from: classes.dex */
    public interface Callback {
        void onDispose(FirmwareUpdateTask firmwareUpdateTask);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class FirmwareTransmitter extends Thread implements ControlResponseHandler {
        private static final int RESET_CACHED_COMPONENT_AWAIT = 0;
        private static final int RESET_CACHED_COMPONENT_FAILED = 2;
        private static final int RESET_CACHED_COMPONENT_SUCCESS = 1;
        private static final int SEGMENT_AWAIT = 0;
        private static final int SEGMENT_FAILED = 2;
        private static final int SEGMENT_SUCCESS = 1;
        private final Firmware.FirmwareComponent cachedComponent;
        private final Callback callback;
        private final TransportDispatcher dispatcher;
        private final Object lock = new Object();
        private final FirmwareTaskMetricsReporter metricsReporter;
        private final ProgressReporter progressReporter;
        private volatile boolean released;
        private volatile int resetCachedComponentStatus;
        private final long segmentCompletionTimeoutMillis;
        private final int segmentSize;
        private volatile int segmentStatus;
        private final ControlStream stream;
        private final FirmwareComponentSupplier supplier;

        /* loaded from: classes.dex */
        public interface Callback {
            void onComponentCacheInvalidated();

            void onTransmissionCompleted(int i);

            void onTransmissionFailed(Throwable th);
        }

        @VisibleForTesting
        FirmwareTransmitter(TransportDispatcher transportDispatcher, ControlStream controlStream, FirmwareComponentSupplier firmwareComponentSupplier, Firmware.FirmwareComponent firmwareComponent, ProgressReporter progressReporter, Callback callback, long j, FirmwareTaskMetricsReporter firmwareTaskMetricsReporter, int i) {
            this.stream = controlStream;
            this.cachedComponent = firmwareComponent;
            this.supplier = firmwareComponentSupplier;
            this.dispatcher = transportDispatcher;
            this.progressReporter = progressReporter;
            this.callback = callback;
            this.segmentCompletionTimeoutMillis = j;
            this.metricsReporter = firmwareTaskMetricsReporter;
            this.segmentSize = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$null$0(ControlStream controlStream, FirmwareTransmitter firmwareTransmitter) throws Throwable {
            controlStream.removeResponseHandler(firmwareTransmitter);
            firmwareTransmitter.release();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$single$1(TransportDispatcher transportDispatcher, final ControlStream controlStream, final FirmwareComponentSupplier firmwareComponentSupplier, final Firmware.FirmwareComponent firmwareComponent, ProgressReporter progressReporter, final FirmwareTaskMetricsReporter firmwareTaskMetricsReporter, long j, int i, final SingleEmitter singleEmitter) throws Throwable {
            final FirmwareTransmitter firmwareTransmitter = new FirmwareTransmitter(transportDispatcher, controlStream, firmwareComponentSupplier, firmwareComponent, progressReporter, new Callback() { // from class: com.amazon.alexa.accessory.capabilities.firmware.FirmwareUpdateTask.FirmwareTransmitter.1
                @Override // com.amazon.alexa.accessory.capabilities.firmware.FirmwareUpdateTask.FirmwareTransmitter.Callback
                public void onComponentCacheInvalidated() {
                    FirmwareTaskMetricsReporter.this.onComponentCacheInvalidated();
                }

                @Override // com.amazon.alexa.accessory.capabilities.firmware.FirmwareUpdateTask.FirmwareTransmitter.Callback
                public void onTransmissionCompleted(int i2) {
                    FirmwareTaskMetricsReporter.this.onComponentTransmissionCompleted(firmwareComponent.getName(), i2);
                    singleEmitter.onSuccess(firmwareComponentSupplier);
                }

                @Override // com.amazon.alexa.accessory.capabilities.firmware.FirmwareUpdateTask.FirmwareTransmitter.Callback
                public void onTransmissionFailed(Throwable th) {
                    FirmwareTaskMetricsReporter.this.onComponentTransmissionError();
                    singleEmitter.onError(th);
                }
            }, j, firmwareTaskMetricsReporter, i);
            controlStream.addResponseHandler(Accessories.Command.UPDATE_COMPONENT_SEGMENT, firmwareTransmitter);
            controlStream.addResponseHandler(Accessories.Command.RESET_CACHED_COMPONENT, firmwareTransmitter);
            singleEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$FirmwareTransmitter$xkVwxT2gfjTRgnurhdFnw1jGEQM
                @Override // io.reactivex.rxjava3.functions.Cancellable
                public final void cancel() {
                    FirmwareUpdateTask.FirmwareTransmitter.lambda$null$0(ControlStream.this, firmwareTransmitter);
                }
            });
            firmwareTransmitter.start();
        }

        public static Single<FirmwareComponentSupplier> single(final TransportDispatcher transportDispatcher, final ControlStream controlStream, final FirmwareComponentSupplier firmwareComponentSupplier, final Firmware.FirmwareComponent firmwareComponent, final ProgressReporter progressReporter, final long j, final FirmwareTaskMetricsReporter firmwareTaskMetricsReporter, final int i) {
            return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$FirmwareTransmitter$-SFCUZpE8dxGb5OvY3VrYWVW2oM
                @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
                public final void subscribe(SingleEmitter singleEmitter) {
                    FirmwareUpdateTask.FirmwareTransmitter.lambda$single$1(TransportDispatcher.this, controlStream, firmwareComponentSupplier, firmwareComponent, progressReporter, firmwareTaskMetricsReporter, j, i, singleEmitter);
                }
            });
        }

        /* JADX WARN: Code restructure failed: missing block: B:9:0x0017, code lost:
            r5.dispatcher.dispatch(com.amazon.alexa.accessory.transport.TransportTransaction.newBuilder().stream(2).priority(com.amazon.alexa.accessory.transport.TransportPriority.LOW).commit(true).build());
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void transmitSegmentData(int r6, int r7) throws java.io.IOException {
            /*
                r5 = this;
                com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier r0 = r5.supplier     // Catch: java.lang.Throwable -> L74
                com.amazon.alexa.accessory.io.Source r6 = r0.open(r6, r7)     // Catch: java.lang.Throwable -> L74
                r7 = 512(0x200, float:7.175E-43)
                byte[] r7 = new byte[r7]     // Catch: java.lang.Throwable -> L62
            La:
                boolean r0 = r5.released     // Catch: java.lang.Throwable -> L62
                if (r0 != 0) goto L5e
                int r0 = r7.length     // Catch: java.lang.Throwable -> L62
                r1 = 0
                int r0 = r6.read(r7, r1, r0)     // Catch: java.lang.Throwable -> L62
                r2 = 2
                if (r0 >= 0) goto L34
                com.amazon.alexa.accessory.transport.TransportDispatcher r7 = r5.dispatcher     // Catch: java.lang.Throwable -> L62
                com.amazon.alexa.accessory.transport.TransportTransaction$Builder r0 = com.amazon.alexa.accessory.transport.TransportTransaction.newBuilder()     // Catch: java.lang.Throwable -> L62
                com.amazon.alexa.accessory.transport.TransportTransaction$Builder r0 = r0.stream(r2)     // Catch: java.lang.Throwable -> L62
                com.amazon.alexa.accessory.transport.TransportPriority r1 = com.amazon.alexa.accessory.transport.TransportPriority.LOW     // Catch: java.lang.Throwable -> L62
                com.amazon.alexa.accessory.transport.TransportTransaction$Builder r0 = r0.priority(r1)     // Catch: java.lang.Throwable -> L62
                r1 = 1
                com.amazon.alexa.accessory.transport.TransportTransaction$Builder r0 = r0.commit(r1)     // Catch: java.lang.Throwable -> L62
                com.amazon.alexa.accessory.transport.TransportTransaction r0 = r0.build()     // Catch: java.lang.Throwable -> L62
                r7.dispatch(r0)     // Catch: java.lang.Throwable -> L62
                goto L5e
            L34:
                com.amazon.alexa.accessory.io.ByteArraySource r3 = new com.amazon.alexa.accessory.io.ByteArraySource     // Catch: java.lang.Throwable -> L62
                byte[] r4 = java.util.Arrays.copyOf(r7, r0)     // Catch: java.lang.Throwable -> L62
                r3.<init>(r4)     // Catch: java.lang.Throwable -> L62
                java.lang.String r4 = "Dispatching segment data"
                com.amazon.alexa.accessory.internal.util.Logger.d(r4, r7, r1, r0)     // Catch: java.lang.Throwable -> L62
                com.amazon.alexa.accessory.transport.TransportDispatcher r0 = r5.dispatcher     // Catch: java.lang.Throwable -> L62
                com.amazon.alexa.accessory.transport.TransportTransaction$Builder r1 = com.amazon.alexa.accessory.transport.TransportTransaction.newBuilder()     // Catch: java.lang.Throwable -> L62
                com.amazon.alexa.accessory.transport.TransportTransaction$Builder r1 = r1.stream(r2)     // Catch: java.lang.Throwable -> L62
                com.amazon.alexa.accessory.transport.TransportPriority r2 = com.amazon.alexa.accessory.transport.TransportPriority.LOW     // Catch: java.lang.Throwable -> L62
                com.amazon.alexa.accessory.transport.TransportTransaction$Builder r1 = r1.priority(r2)     // Catch: java.lang.Throwable -> L62
                com.amazon.alexa.accessory.transport.TransportTransaction$Builder r1 = r1.data(r3)     // Catch: java.lang.Throwable -> L62
                com.amazon.alexa.accessory.transport.TransportTransaction r1 = r1.build()     // Catch: java.lang.Throwable -> L62
                r0.dispatch(r1)     // Catch: java.lang.Throwable -> L62
                goto La
            L5e:
                com.amazon.alexa.accessory.internal.util.IOUtils.closeQuietly(r6)
                return
            L62:
                r7 = move-exception
                java.lang.String r0 = "AccessoryDfuTransmitData"
                java.lang.String r0 = com.amazon.alexa.accessory.internal.util.MetricsUtils.createMetricNameFromThrowable(r0, r7)     // Catch: java.lang.Throwable -> L6f
                com.amazon.alexa.accessory.metrics.FirmwareTaskMetricsReporter r1 = r5.metricsReporter     // Catch: java.lang.Throwable -> L6f
                r1.recordMetric(r0)     // Catch: java.lang.Throwable -> L6f
                throw r7     // Catch: java.lang.Throwable -> L6f
            L6f:
                r7 = move-exception
                com.amazon.alexa.accessory.internal.util.IOUtils.closeQuietly(r6)
                throw r7
            L74:
                r6 = move-exception
                java.lang.String r7 = "AccessoryDfuFileOpen"
                java.lang.String r7 = com.amazon.alexa.accessory.internal.util.MetricsUtils.createMetricNameFromThrowable(r7, r6)
                com.amazon.alexa.accessory.metrics.FirmwareTaskMetricsReporter r0 = r5.metricsReporter
                r0.recordMetric(r7)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.capabilities.firmware.FirmwareUpdateTask.FirmwareTransmitter.transmitSegmentData(int, int):void");
        }

        private int validateComponentCache() throws IOException {
            if (this.cachedComponent.getSize() == 0) {
                return 0;
            }
            try {
                String signature = this.supplier.getSignature(0, this.cachedComponent.getSize());
                if (signature.equals(this.cachedComponent.getSignature())) {
                    return this.cachedComponent.getSize();
                }
                this.metricsReporter.recordMetric(MetricsConstants.Dfu.SIGNATURE_MISMATCH);
                Logger.d("Cached component signature mismatch! Expect %s received %s. Resetting component", signature, this.cachedComponent.getSignature());
                this.callback.onComponentCacheInvalidated();
                this.resetCachedComponentStatus = 0;
                this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.RESET_CACHED_COMPONENT).setResetCachedComponent(Firmware.ResetCachedComponent.newBuilder().setName(this.cachedComponent.getName())).mo10084build()));
                try {
                    synchronized (this.lock) {
                        if (!this.released && this.resetCachedComponentStatus == 0) {
                            IOUtils.waitUntilNotified(this.lock, this.segmentCompletionTimeoutMillis);
                        }
                        if (!this.released && this.resetCachedComponentStatus != 1) {
                            Callback callback = this.callback;
                            callback.onTransmissionFailed(new IOException("Transmission of RESET_CACHED_COMPONENT for component (" + this.cachedComponent.getName() + ") failed, received a non-success ack"));
                        }
                    }
                } catch (Throwable th) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Transmission of RESET_CACHED_COMPONENT for component (");
                    outline107.append(this.cachedComponent.getName());
                    outline107.append(") failed");
                    Logger.e(outline107.toString(), th);
                    this.metricsReporter.recordMetric(MetricsUtils.createMetricNameFromThrowable(MetricsConstants.Dfu.VALIDATE_CACHED_COMPONENT_EXCEPTION_PREFIX, th));
                    if (!this.released) {
                        this.callback.onTransmissionFailed(th);
                    }
                }
                return 0;
            } catch (IOException e) {
                Logger.e("Failed to read the signature for %s: ", this.cachedComponent.getName(), e);
                this.metricsReporter.recordMetric(MetricsUtils.createMetricNameFromThrowable(MetricsConstants.Dfu.SIGNATURE_VERIFICATION_PREFIX, e));
                throw e;
            }
        }

        @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
        public void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
            if (command == Accessories.Command.UPDATE_COMPONENT_SEGMENT) {
                synchronized (this.lock) {
                    Logger.d("Received response for a component segment (%s)", response.getErrorCode());
                    if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
                        this.segmentStatus = 1;
                    } else {
                        this.segmentStatus = 2;
                    }
                    this.metricsReporter.recordMetric(MetricsConstants.Dfu.UPDATE_SEGMENT_RESPONSE_PREFIX + response.getErrorCode().name());
                    this.lock.notifyAll();
                }
            } else if (command != Accessories.Command.RESET_CACHED_COMPONENT) {
            } else {
                synchronized (this.lock) {
                    Logger.d("Received response for reset cached component for a component segment (%s)", response.getErrorCode());
                    if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
                        this.resetCachedComponentStatus = 1;
                    } else {
                        this.resetCachedComponentStatus = 2;
                    }
                    this.metricsReporter.recordMetric(MetricsConstants.Dfu.RESET_CACHED_COMPONENT_RESPONSE_PREFIX + response.getErrorCode().name());
                    this.lock.notifyAll();
                }
            }
        }

        public void release() {
            synchronized (this.lock) {
                if (this.released) {
                    return;
                }
                this.released = true;
                this.lock.notifyAll();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Logger.d("Transmission of a component (%s) started", this.cachedComponent.getName());
            try {
                int size = this.supplier.getSize();
                int validateComponentCache = validateComponentCache();
                int i = size - validateComponentCache;
                float f = size;
                float f2 = validateComponentCache / f;
                this.progressReporter.onProgressChanged(f2);
                float f3 = f2;
                int i2 = validateComponentCache;
                int i3 = i;
                while (i3 > 0 && !this.released) {
                    int min = Math.min(this.segmentSize, i3);
                    try {
                        String signature = this.supplier.getSignature(i2, min);
                        this.segmentStatus = 0;
                        this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.UPDATE_COMPONENT_SEGMENT).setUpdateComponentSegment(Firmware.UpdateComponentSegment.newBuilder().setComponentName(this.supplier.getName()).setComponentOffset(i2).setSegmentSize(min).setSegmentSignature(signature)).mo10084build()));
                        transmitSegmentData(i2, min);
                        synchronized (this.lock) {
                            if (!this.released && this.segmentStatus == 0) {
                                IOUtils.waitUntilNotified(this.lock, this.segmentCompletionTimeoutMillis);
                            }
                            if (!this.released && this.segmentStatus != 1) {
                                this.progressReporter.onProgressChanged(f3 * (-1.0f));
                                this.callback.onTransmissionFailed(new IOException("Segment failed to transmit"));
                                Logger.d("Transmission of a component (%s) ended", this.cachedComponent.getName());
                                return;
                            }
                        }
                        i2 += min;
                        i3 -= min;
                        float f4 = min / f;
                        f3 += f4;
                        this.progressReporter.onProgressChanged(f4);
                    } catch (IOException e) {
                        Logger.e("Failed to read the signature for %s: ", this.cachedComponent.getName(), e);
                        this.metricsReporter.recordMetric(MetricsUtils.createMetricNameFromThrowable(MetricsConstants.Dfu.SIGNATURE_VERIFICATION_IN_RUN_PREFIX, e));
                        throw e;
                    }
                }
                if (!this.released) {
                    this.callback.onTransmissionCompleted(i);
                }
                Logger.d("Transmission of a component (%s) ended", this.cachedComponent.getName());
            } catch (Throwable th) {
                try {
                    Logger.e("Transmission of a component (" + this.cachedComponent.getName() + ") failed", th);
                    if (!this.released) {
                        this.callback.onTransmissionFailed(th);
                    }
                    Logger.d("Transmission of a component (%s) ended", this.cachedComponent.getName());
                } catch (Throwable th2) {
                    Logger.d("Transmission of a component (%s) ended", this.cachedComponent.getName());
                    throw th2;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface ProgressReporter {
        void onProgressChanged(float f);
    }

    public FirmwareUpdateTask(TransportDispatcher transportDispatcher, ControlStream controlStream, FirmwareSupplier firmwareSupplier, FirmwareProvider firmwareProvider, List<Firmware.FirmwareComponent> list, Callback callback, MetricsReporter metricsReporter, List<DeviceArtifactContract.ArtifactPackage> list2, DfuCandidateInformation dfuCandidateInformation, int i, int i2) {
        this(transportDispatcher, controlStream, firmwareSupplier, firmwareProvider, list, callback, 60000L, 5000L, metricsReporter, list2, dfuCandidateInformation, i, i2);
    }

    private void dispose() {
        ObservableUtils.dispose(this.disposable);
        this.callback.onDispose(this);
        this.provider.provideUpdateStatus(FirmwareUpdateStatus.idle());
        this.metricsReporter.taskDisposed();
    }

    private Accessories.Response emitStatusMetricAndThrowIfNotSuccessful(String str, Accessories.Response response) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, ":");
        outline113.append(response.getErrorCode().name());
        this.metricsReporter.recordMetric(outline113.toString());
        this.metricsReporter.recordMetric(str);
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
            return response;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetCachedComponent failed with response: ");
        outline107.append(response.getErrorCode().getNumber());
        throw new RuntimeException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handleApplyFirmwareResponse */
    public Accessories.Response lambda$null$2$FirmwareUpdateTask(Accessories.Response response, ControlMessage controlMessage) throws Exception {
        this.metricsReporter.recordApplyFirmwareErrorCode(response.getErrorCode().toString());
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
            return response;
        }
        this.metricsReporter.applyFirmwareErrorResponse();
        this.metricsReporter.recordMetric(MetricsConstants.Dfu.ERROR_ON_APPLY_FIRMWARE);
        StringBuilder sb = new StringBuilder();
        sb.append("Response for ");
        sb.append(controlMessage);
        sb.append(" failed with error ");
        throw new Exception(GeneratedOutlineSupport1.outline34(response, sb));
    }

    private void recordMetricsForDavsRetryLimitExceeded() {
        this.metricsReporter.recordMetric(MetricsConstants.DAVS.NO_MORE_RETRIES);
        this.metricsReporter.componentTransmissionRanOutOfRetriesForDavs();
    }

    private void recordMetricsForRetryLimitExceeded() {
        this.metricsReporter.recordMetric(MetricsConstants.Dfu.NO_MORE_RETRIES);
        this.metricsReporter.componentTransmissionRanOutOfRetries();
    }

    private void resume() {
        if (this.components.isEmpty() && this.davsArtifacts.isEmpty()) {
            Logger.e("A firmware update was started with no components to update. Cancelling update");
            dispose();
            return;
        }
        ObservableUtils.dispose(this.disposable);
        this.metricsReporter.onTaskResumed();
        final int size = this.davsArtifacts.size() + this.components.size();
        final ProgressReporter progressReporter = new ProgressReporter() { // from class: com.amazon.alexa.accessory.capabilities.firmware.FirmwareUpdateTask.1
            float progress;

            @Override // com.amazon.alexa.accessory.capabilities.firmware.FirmwareUpdateTask.ProgressReporter
            public void onProgressChanged(float f) {
                this.progress += f;
                FirmwareUpdateTask.this.provider.provideUpdateStatus(FirmwareUpdateStatus.transferring(this.progress / size, FirmwareUpdateTask.this.deviceId));
            }
        };
        progressReporter.onProgressChanged(0.0f);
        this.metricsReporter.recordFirmwareTaskStart(this.components.size(), this.davsArtifacts.size());
        this.disposable = transferFirmwareComponents(progressReporter).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$LbVKH2XIUD97aGzaVzZrLOI0AOw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareUpdateTask.this.lambda$resume$0$FirmwareUpdateTask(progressReporter, (List) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$ccA5QOh8NvntLb2-u2ksevCjUhw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareUpdateTask.this.lambda$resume$1$FirmwareUpdateTask((List) obj);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$VJgSwxXb3wyyKCBV6PnZOMEKvRQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareUpdateTask.this.lambda$resume$3$FirmwareUpdateTask((ProtobufControlMessage) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$4NgK5NIzfv50fMJUMP2uaqChWW4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareUpdateTask.this.lambda$resume$4$FirmwareUpdateTask((Accessories.Response) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$gGVtLCJo6QywPkm6fCMC1Q-XRBM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareUpdateTask.this.lambda$resume$5$FirmwareUpdateTask((Throwable) obj);
            }
        });
    }

    private void suspend() {
        ObservableUtils.dispose(this.disposable);
        this.metricsReporter.onTaskSuspended();
    }

    private Single<List<String>> transferDAVSArtifacts(List<DeviceArtifactContract.ArtifactPackage> list, final ProgressReporter progressReporter) {
        return Observable.fromIterable(list).concatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$iupbrTDuR0DYzF6KEvZlO_qiToY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareUpdateTask.this.lambda$transferDAVSArtifacts$22$FirmwareUpdateTask(progressReporter, (DeviceArtifactContract.ArtifactPackage) obj);
            }
        }).map($$Lambda$9AsuFbl7Ei_ym27UcWYJR5AAF0.INSTANCE).toList();
    }

    private Single<List<String>> transferFirmwareComponents(final ProgressReporter progressReporter) {
        return Observable.fromIterable(this.components).concatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$UjQGRusbnC92ibBiV-uk_x0Flr8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareUpdateTask.this.lambda$transferFirmwareComponents$12$FirmwareUpdateTask(progressReporter, (Firmware.FirmwareComponent) obj);
            }
        }).map($$Lambda$9AsuFbl7Ei_ym27UcWYJR5AAF0.INSTANCE).toList();
    }

    public /* synthetic */ SingleSource lambda$null$10$FirmwareUpdateTask(final ProgressReporter progressReporter, final Firmware.FirmwareComponent firmwareComponent) throws Throwable {
        return this.supplier.queryComponent(this.dfuCandidateInformation.getComponent(firmwareComponent.getName())).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$EtlsSmyMrw1yNS-yaZyjzhCl4RI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareUpdateTask.this.lambda$null$9$FirmwareUpdateTask(firmwareComponent, progressReporter, (FirmwareComponentSupplier) obj);
            }
        });
    }

    public /* synthetic */ void lambda$null$11$FirmwareUpdateTask(Throwable th) throws Throwable {
        recordMetricsForRetryLimitExceeded();
    }

    public /* synthetic */ Accessories.Response lambda$null$13$FirmwareUpdateTask(Accessories.Response response) throws Throwable {
        return emitStatusMetricAndThrowIfNotSuccessful(MetricsConstants.Dfu.ON_GET_CACHED_COMPONENT_DAVS_RESPONSE, response);
    }

    public /* synthetic */ void lambda$null$14$FirmwareUpdateTask(DeviceArtifactContract.ArtifactPackage artifactPackage, Throwable th) throws Throwable {
        Logger.e("%s Error calling GET_CACHED_COMPONENT for DAVS artifact: %s", th, TAG, artifactPackage.getArtifactName());
        this.metricsReporter.onGetCachedComponentErrorForDAVS();
        this.metricsReporter.recordMetric(MetricsConstants.Dfu.ERROR_ON_GET_CACHED_COMPONENT_DAVS);
    }

    public /* synthetic */ void lambda$null$15$FirmwareUpdateTask(Throwable th) throws Throwable {
        this.metricsReporter.recordMetric(MetricsConstants.Dfu.ERROR_ON_UPDATE_SEGMENT_DAVS);
    }

    public /* synthetic */ SingleSource lambda$null$16$FirmwareUpdateTask(Firmware.FirmwareComponent firmwareComponent, ProgressReporter progressReporter, FirmwareComponentSupplier firmwareComponentSupplier) throws Throwable {
        return FirmwareTransmitter.single(this.dispatcher, this.stream, firmwareComponentSupplier, firmwareComponent, progressReporter, this.segmentCompletionTimeoutMillis, this.metricsReporter, this.segmentSize).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$RThNbYurvFUqnYc9WbxDgu8eWEM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareUpdateTask.this.lambda$null$15$FirmwareUpdateTask((Throwable) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$null$17$FirmwareUpdateTask(DeviceArtifactContract.ArtifactPackage artifactPackage, final ProgressReporter progressReporter, final Firmware.FirmwareComponent firmwareComponent) throws Throwable {
        return this.supplier.queryDAVSArtifact(artifactPackage).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$moj11-3xTqzQ_NIjOkbv9lk4lXs
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareUpdateTask.this.lambda$null$16$FirmwareUpdateTask(firmwareComponent, progressReporter, (FirmwareComponentSupplier) obj);
            }
        });
    }

    public /* synthetic */ void lambda$null$18$FirmwareUpdateTask(Throwable th) throws Throwable {
        recordMetricsForDavsRetryLimitExceeded();
    }

    public /* synthetic */ FirmwareComponentSupplier lambda$null$19$FirmwareUpdateTask(FirmwareComponentSupplier firmwareComponentSupplier, Accessories.Response response) throws Throwable {
        emitStatusMetricAndThrowIfNotSuccessful(MetricsConstants.DAVS.ON_VERIFY_ARTIFACT_RESPONSE, response);
        return firmwareComponentSupplier;
    }

    public /* synthetic */ void lambda$null$20$FirmwareUpdateTask(DeviceArtifactContract.ArtifactPackage artifactPackage, Throwable th) throws Throwable {
        Logger.e("%s Error calling VERIFY_ARTIFACT_SIGNATURE for Davs artifact: %s", th, TAG, artifactPackage.getArtifactName());
        this.metricsReporter.onVerifyArtifactSignatureError();
        this.metricsReporter.recordMetric(MetricsConstants.DAVS.ERROR_VERIFY_ARTIFACT_SIGNATURE);
    }

    public /* synthetic */ SingleSource lambda$null$21$FirmwareUpdateTask(final DeviceArtifactContract.ArtifactPackage artifactPackage, final FirmwareComponentSupplier firmwareComponentSupplier) throws Throwable {
        return ObservableStream.dispatchSingleSuccessOnErrorResponse(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.VERIFY_ARTIFACT_SIGNATURE).setVerifyArtifactSignature(Firmware.VerifyArtifactSignature.newBuilder().setArtifactName(artifactPackage.getArtifactName()).setSignature(ByteString.copyFromUtf8(artifactPackage.getDavsArtifactSignature().getSignature()))).mo10084build())).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$v1t4wyKMRbXlOGePefuENH95ov0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareUpdateTask.this.lambda$null$19$FirmwareUpdateTask(firmwareComponentSupplier, (Accessories.Response) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$JMDMKjw8kCwc33M_kdjfZC2nRH8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareUpdateTask.this.lambda$null$20$FirmwareUpdateTask(artifactPackage, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ Accessories.Response lambda$null$6$FirmwareUpdateTask(Accessories.Response response) throws Throwable {
        return emitStatusMetricAndThrowIfNotSuccessful(MetricsConstants.Dfu.ON_GET_CACHED_COMPONENT_RESPONSE, response);
    }

    public /* synthetic */ void lambda$null$7$FirmwareUpdateTask(Throwable th) throws Throwable {
        this.metricsReporter.onGetCachedComponentError();
        this.metricsReporter.recordMetric(MetricsConstants.Dfu.ERROR_ON_GET_CACHED_COMPONENT);
    }

    public /* synthetic */ void lambda$null$8$FirmwareUpdateTask(Throwable th) throws Throwable {
        this.metricsReporter.recordMetric(MetricsConstants.Dfu.ERROR_ON_UPDATE_SEGMENT);
    }

    public /* synthetic */ SingleSource lambda$null$9$FirmwareUpdateTask(Firmware.FirmwareComponent firmwareComponent, ProgressReporter progressReporter, FirmwareComponentSupplier firmwareComponentSupplier) throws Throwable {
        return FirmwareTransmitter.single(this.dispatcher, this.stream, firmwareComponentSupplier, firmwareComponent, progressReporter, this.segmentCompletionTimeoutMillis, this.metricsReporter, this.segmentSize).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$A0VF-Wvni4zaadG75vZQ5UOhdAA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareUpdateTask.this.lambda$null$8$FirmwareUpdateTask((Throwable) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$resume$0$FirmwareUpdateTask(ProgressReporter progressReporter, List list) throws Throwable {
        return transferDAVSArtifacts(this.davsArtifacts, progressReporter);
    }

    public /* synthetic */ ProtobufControlMessage lambda$resume$1$FirmwareUpdateTask(List list) throws Throwable {
        return new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.APPLY_FIRMWARE).setApplyFirmware(Firmware.ApplyFirmware.newBuilder().setFirmwareInformation(Firmware.FirmwareInformation.newBuilder().setVersion(this.dfuCandidateInformation.getVersion()).setName(this.dfuCandidateInformation.getName()).addAllComponents(this.components))).mo10084build());
    }

    public /* synthetic */ SingleSource lambda$resume$3$FirmwareUpdateTask(final ProtobufControlMessage protobufControlMessage) throws Throwable {
        return ObservableStream.dispatchSingleSuccessOnErrorResponse(this.stream, protobufControlMessage).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$soBwH3tp7duCtQdOZ4LbAUPnSfs
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareUpdateTask.this.lambda$null$2$FirmwareUpdateTask(protobufControlMessage, (Accessories.Response) obj);
            }
        });
    }

    public /* synthetic */ void lambda$resume$4$FirmwareUpdateTask(Accessories.Response response) throws Throwable {
        Logger.d("Applying firmware...");
        this.metricsReporter.recordDfuSuccessMetric(true);
        this.metricsReporter.taskCompleted();
        this.metricsReporter.recordFirmwareTaskSuccess(this.components.size(), this.davsArtifacts.size());
        this.callback.onDispose(this);
        this.provider.provideUpdateStatus(FirmwareUpdateStatus.transferring(1.0f, this.deviceId));
        this.provider.provideUpdateStatus(FirmwareUpdateStatus.completed(this.deviceId));
    }

    public /* synthetic */ void lambda$resume$5$FirmwareUpdateTask(Throwable th) throws Throwable {
        Logger.e("Failed to update components", th);
        this.metricsReporter.recordMetric(MetricsUtils.createMetricNameFromThrowable(MetricsConstants.Dfu.DFU_FAILURE_PREFIX, th));
        this.metricsReporter.recordDfuSuccessMetric(false);
        this.metricsReporter.recordFirmwareTaskError(this.components.size(), this.davsArtifacts.size());
        this.callback.onDispose(this);
        this.provider.provideUpdateStatus(FirmwareUpdateStatus.completedWithError(th, this.deviceId));
        this.metricsReporter.taskDisposed();
    }

    public /* synthetic */ ObservableSource lambda$transferDAVSArtifacts$22$FirmwareUpdateTask(final ProgressReporter progressReporter, final DeviceArtifactContract.ArtifactPackage artifactPackage) throws Throwable {
        return ObservableStream.dispatchSingleSuccessOnErrorResponse(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_CACHED_COMPONENT).setGetCachedComponent(Firmware.GetCachedComponent.newBuilder().setName(artifactPackage.getArtifactName())).mo10084build())).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$JDObcKe70CHcaFQI_ErLekCHwxY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareUpdateTask.this.lambda$null$13$FirmwareUpdateTask((Accessories.Response) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$hAVkpogmbKDx0QDJyUtZP8Q4Lbs
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareUpdateTask.this.lambda$null$14$FirmwareUpdateTask(artifactPackage, (Throwable) obj);
            }
        }).map($$Lambda$KbHcOZwhLn5LPTn831ggwcBqe4k.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$o3TE2Qlhuxyclxq2bf4c9WGG5Vw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareUpdateTask.this.lambda$null$17$FirmwareUpdateTask(artifactPackage, progressReporter, (Firmware.FirmwareComponent) obj);
            }
        }).retryWhen(ObservableUtils.retryBackoff(3, this.segmentRetryDelayMillis)).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$qJ9fY3mJF130hzWEfqcf_huYZQ4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareUpdateTask.this.lambda$null$18$FirmwareUpdateTask((Throwable) obj);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$BiQk4JwjomTBLM4Lsl-ClOILoJo
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareUpdateTask.this.lambda$null$21$FirmwareUpdateTask(artifactPackage, (FirmwareComponentSupplier) obj);
            }
        }).toObservable();
    }

    public /* synthetic */ ObservableSource lambda$transferFirmwareComponents$12$FirmwareUpdateTask(final ProgressReporter progressReporter, Firmware.FirmwareComponent firmwareComponent) throws Throwable {
        return ObservableStream.dispatchSingleSuccessOnErrorResponse(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_CACHED_COMPONENT).setGetCachedComponent(Firmware.GetCachedComponent.newBuilder().setName(firmwareComponent.getName())).mo10084build())).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$77oLywIG8Wddp2Cl5LohXzisDig
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareUpdateTask.this.lambda$null$6$FirmwareUpdateTask((Accessories.Response) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$tXP4CgVks7MlFUUh4w1gPfSD7Pw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareUpdateTask.this.lambda$null$7$FirmwareUpdateTask((Throwable) obj);
            }
        }).map($$Lambda$KbHcOZwhLn5LPTn831ggwcBqe4k.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$4bKr4la62IteaJ2u2Se7pVWpeXk
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareUpdateTask.this.lambda$null$10$FirmwareUpdateTask(progressReporter, (Firmware.FirmwareComponent) obj);
            }
        }).retryWhen(ObservableUtils.retryBackoff(3, this.segmentRetryDelayMillis)).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareUpdateTask$YOJNmeva3ae6YqoFqJzm7FohYdk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareUpdateTask.this.lambda$null$11$FirmwareUpdateTask((Throwable) obj);
            }
        }).toObservable();
    }

    @Override // com.amazon.alexa.accessory.TaskManager.Task
    public void onStateChanged(int i, int i2) {
        if ((i == 0 || i == 1 || i == 2) && i2 == 3) {
            resume();
        } else if (i2 == 2) {
            suspend();
        } else if (i2 != 4) {
        } else {
            dispose();
        }
    }

    public String toString() {
        return "FirmwareUpdateTask";
    }

    public FirmwareUpdateTask(TransportDispatcher transportDispatcher, ControlStream controlStream, FirmwareSupplier firmwareSupplier, FirmwareProvider firmwareProvider, List<Firmware.FirmwareComponent> list, Callback callback, long j, long j2, MetricsReporter metricsReporter, List<DeviceArtifactContract.ArtifactPackage> list2, DfuCandidateInformation dfuCandidateInformation, int i, int i2) {
        Preconditions.notNull(transportDispatcher, "dispatcher");
        Preconditions.notNull(controlStream, "stream");
        Preconditions.notNull(firmwareSupplier, "supplier");
        Preconditions.notNull(firmwareProvider, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER);
        Preconditions.notNull(list, "components");
        Preconditions.notNull(callback, "callback");
        Preconditions.notNull(metricsReporter, "metricsReporter");
        Preconditions.notNull(list2, "davsArtifacts");
        Preconditions.notNull(dfuCandidateInformation, "dfuCandidateInformation");
        this.dispatcher = transportDispatcher;
        this.components = list;
        this.stream = controlStream;
        this.supplier = firmwareSupplier;
        this.provider = firmwareProvider;
        this.callback = callback;
        this.segmentCompletionTimeoutMillis = j;
        this.segmentRetryDelayMillis = j2;
        this.metricsReporter = new FirmwareTaskMetricsReporter(metricsReporter, dfuCandidateInformation, list2.size() + list.size(), $$Lambda$D1z_ytlzYvthAoeLOQGoy8VTB4U.INSTANCE);
        this.davsArtifacts = list2;
        this.dfuCandidateInformation = dfuCandidateInformation;
        this.deviceId = i;
        this.segmentSize = i2;
    }
}
