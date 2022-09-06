package com.amazon.alexa.accessory.capabilities.transport;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.capabilities.transport.TransportCapability;
import com.amazon.alexa.accessory.internal.bluetooth.BluetoothUtils;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Transport;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.device.DeviceRepository;
import com.amazon.alexa.accessory.repositories.transport.TransportProducer;
import com.amazon.alexa.accessory.streams.control.ControlMessage;
import com.amazon.alexa.accessory.streams.control.ControlResponseHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.amazon.alexa.accessorykit.ModelTransformer;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
/* loaded from: classes.dex */
public final class TransportCapability extends AccessoryCapability {
    private static final int SWITCH_MAX_ATTEMPTS = 3;
    private Accessory accessory;
    private final TransportActionHandler actionHandler;
    private ControlStream controlStream;
    private final DeviceRepository deviceRepository;
    private final TransportProducer transportProducer;
    private final TransportUpgradeListener transportUpgradeListener;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class BusyErrorCodeException extends Exception {
        private BusyErrorCodeException() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class TransportActionHandler implements TransportProducer.ActionHandler {
        private Disposable upgradeDisposable;

        private TransportActionHandler() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Single<Accessory> attemptTransportUpgrade(final Common.Transport transport) {
            return issueUpgradeTransport(transport).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$TransportCapability$TransportActionHandler$R6ppfOI1bYcXfIgp9hDmYfeJNHU
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    Single requestAccessoryConnection;
                    requestAccessoryConnection = TransportCapability.TransportActionHandler.this.requestAccessoryConnection((Accessory) obj);
                    return requestAccessoryConnection;
                }
            }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$TransportCapability$TransportActionHandler$h-Q_1q7GNOgLlbLIjjjsb9KWgvU
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    Single switchTransport;
                    switchTransport = TransportCapability.TransportActionHandler.this.switchTransport((Accessory) obj);
                    return switchTransport;
                }
            }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$TransportCapability$TransportActionHandler$2r8eFJ2-vsxwoX9-jZ6aC5MdS50
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    TransportCapability.TransportActionHandler.this.lambda$attemptTransportUpgrade$3$TransportCapability$TransportActionHandler(transport, (Throwable) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getAddressFromResponse(Accessories.Response response) {
            return BluetoothUtils.byteArrayToAddress(response.getConnectionDetails().getIdentifier().toByteArray());
        }

        private ControlMessage getSwitchControlMessage(Accessory accessory) {
            return new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.SWITCH_TRANSPORT).setSwitchTransport(Transport.SwitchTransport.newBuilder().setNewTransport(TransportCapability.getTransport(accessory.getTransport()))).mo10084build());
        }

        private ControlMessage getUpgradeControlMessage(Common.Transport transport) {
            return new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.UPGRADE_TRANSPORT).setUpgradeTransport(Transport.UpgradeTransport.newBuilder().setTransport(transport)).mo10084build());
        }

        private Single<Accessory> issueUpgradeTransport(final Common.Transport transport) {
            return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$TransportCapability$TransportActionHandler$lc2xHgsrsFxd1YWQlZ4P1fmzCbI
                @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
                public final void subscribe(SingleEmitter singleEmitter) {
                    TransportCapability.TransportActionHandler.this.lambda$issueUpgradeTransport$5$TransportCapability$TransportActionHandler(transport, singleEmitter);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Single<Accessory> requestAccessoryConnection(final Accessory accessory) {
            return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$TransportCapability$TransportActionHandler$j43r0UPgTcwNFx5CeZ-ndG9Rmts
                @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
                public final void subscribe(SingleEmitter singleEmitter) {
                    TransportCapability.TransportActionHandler.this.lambda$requestAccessoryConnection$6$TransportCapability$TransportActionHandler(accessory, singleEmitter);
                }
            });
        }

        private Single<Common.ErrorCode> requestSwitchTransport(final Accessory accessory) {
            return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$TransportCapability$TransportActionHandler$i08Pu9qnKW1FTt_HKuHAztOOAf4
                @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
                public final void subscribe(SingleEmitter singleEmitter) {
                    TransportCapability.TransportActionHandler.this.lambda$requestSwitchTransport$10$TransportCapability$TransportActionHandler(accessory, singleEmitter);
                }
            });
        }

        private Single<Boolean> shouldUpgrade() {
            return TransportCapability.this.deviceRepository.queryDeviceInformation().firstOrError().map($$Lambda$0QfvpyKLjs6njZ5BEKI816Ggqs.INSTANCE).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$TransportCapability$TransportActionHandler$BF0tE-6n8M3FU-62JtcYMCwQxwE
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return TransportCapability.TransportActionHandler.this.lambda$shouldUpgrade$0$TransportCapability$TransportActionHandler((List) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Single<Accessory> switchTransport(final Accessory accessory) {
            return switchTransportAttempt(accessory).retryWhen(new Function() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$TransportCapability$TransportActionHandler$xyH9nrwtg29IuI-JaksqhqvJTsk
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return TransportCapability.TransportActionHandler.this.lambda$switchTransport$7$TransportCapability$TransportActionHandler(accessory, (Flowable) obj);
                }
            });
        }

        private Single<Accessory> switchTransportAttempt(final Accessory accessory) {
            return requestSwitchTransport(accessory).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$TransportCapability$TransportActionHandler$FHI9xUxUSJa8aUDZpdBuxdxYG8Q
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return TransportCapability.TransportActionHandler.this.lambda$switchTransportAttempt$8$TransportCapability$TransportActionHandler(accessory, (Common.ErrorCode) obj);
                }
            });
        }

        private Completable upgradeTransport() {
            return TransportCapability.this.deviceRepository.queryDeviceInformation().firstOrError().map($$Lambda$0QfvpyKLjs6njZ5BEKI816Ggqs.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$TransportCapability$TransportActionHandler$zfD0pDjuzvj6HioCxm5jH8L5f8M
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return TransportCapability.TransportActionHandler.this.lambda$upgradeTransport$2$TransportCapability$TransportActionHandler((List) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$TransportCapability$TransportActionHandler$2KXIBPxCxGtbB3A2-3Tess6nu0Y
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    Single attemptTransportUpgrade;
                    attemptTransportUpgrade = TransportCapability.TransportActionHandler.this.attemptTransportUpgrade((Common.Transport) obj);
                    return attemptTransportUpgrade;
                }
            }).ignoreElement();
        }

        public void cancelUpgrade() {
            ObservableUtils.dispose(this.upgradeDisposable);
        }

        @Override // com.amazon.alexa.accessory.repositories.transport.TransportProducer.ActionHandler
        public void handleRequestUpgrade(final Producer.Result<CompletableResult.Value> result) {
            Preconditions.notNull(result, "result");
            Disposable disposable = this.upgradeDisposable;
            if (disposable != null && !disposable.isDisposed()) {
                result.completeWithError(new IllegalStateException("An upgrade has already been requested!"));
                return;
            }
            Completable observeOn = upgradeTransport().observeOn(AndroidSchedulers.mainThread());
            Action action = new Action() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$TransportCapability$TransportActionHandler$h7dgKQOtMCnuuV2zrK5krDavmTk
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    Producer.Result.this.complete(CompletableResult.Value.complete());
                }
            };
            result.getClass();
            this.upgradeDisposable = observeOn.subscribe(action, new $$Lambda$25dTK8BRpWxdQZXVVmIUFgH5OIU(result));
        }

        @Override // com.amazon.alexa.accessory.repositories.transport.TransportProducer.ActionHandler
        public void handleShouldUpgrade(final Producer.Result<Boolean> result) {
            Preconditions.notNull(result, "result");
            Single<Boolean> observeOn = shouldUpgrade().observeOn(AndroidSchedulers.mainThread());
            result.getClass();
            Consumer<? super Boolean> consumer = new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$G2VZsF7GpUK5shIZhpeiVtgNje0
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    Producer.Result.this.complete((Boolean) obj);
                }
            };
            result.getClass();
            observeOn.subscribe(consumer, new $$Lambda$25dTK8BRpWxdQZXVVmIUFgH5OIU(result));
        }

        public /* synthetic */ void lambda$attemptTransportUpgrade$3$TransportCapability$TransportActionHandler(Common.Transport transport, Throwable th) throws Throwable {
            TransportCapability.this.transportUpgradeListener.onTransportUpgradeFailed(TransportCapability.getTransport(transport), th);
        }

        public /* synthetic */ void lambda$issueUpgradeTransport$5$TransportCapability$TransportActionHandler(final Common.Transport transport, final SingleEmitter singleEmitter) throws Throwable {
            final ControlResponseHandler controlResponseHandler = new ControlResponseHandler() { // from class: com.amazon.alexa.accessory.capabilities.transport.TransportCapability.TransportActionHandler.1
                @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
                public void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) throws Exception {
                    if (singleEmitter.isDisposed()) {
                        return;
                    }
                    controlStream.removeResponseHandler(this);
                    if (response.getErrorCode() != Common.ErrorCode.SUCCESS) {
                        SingleEmitter singleEmitter2 = singleEmitter;
                        singleEmitter2.onError(new IllegalStateException("Response was not success: " + response));
                        return;
                    }
                    singleEmitter.onSuccess(new Accessory(TransportActionHandler.this.getAddressFromResponse(response), TransportCapability.getTransport(transport)));
                }
            };
            TransportCapability.this.controlStream.addResponseHandler(Accessories.Command.UPGRADE_TRANSPORT, controlResponseHandler);
            TransportCapability.this.controlStream.dispatch(getUpgradeControlMessage(transport));
            singleEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$TransportCapability$TransportActionHandler$9-GnAyzYxzOo_7NexYJrGd7VRp0
                @Override // io.reactivex.rxjava3.functions.Cancellable
                public final void cancel() {
                    TransportCapability.TransportActionHandler.this.lambda$null$4$TransportCapability$TransportActionHandler(controlResponseHandler);
                }
            });
            TransportCapability.this.transportUpgradeListener.onTransportUpgradeRequested(TransportCapability.getTransport(transport));
        }

        public /* synthetic */ void lambda$null$4$TransportCapability$TransportActionHandler(ControlResponseHandler controlResponseHandler) throws Throwable {
            TransportCapability.this.controlStream.removeResponseHandler(controlResponseHandler);
        }

        public /* synthetic */ void lambda$null$9$TransportCapability$TransportActionHandler(ControlResponseHandler controlResponseHandler) throws Throwable {
            TransportCapability.this.controlStream.removeResponseHandler(controlResponseHandler);
        }

        public /* synthetic */ void lambda$requestAccessoryConnection$6$TransportCapability$TransportActionHandler(final Accessory accessory, final SingleEmitter singleEmitter) throws Throwable {
            TransportCapability.this.transportUpgradeListener.onTransportConnectRequested(accessory, new TransportConnectedListener() { // from class: com.amazon.alexa.accessory.capabilities.transport.TransportCapability.TransportActionHandler.2
                @Override // com.amazon.alexa.accessory.capabilities.transport.TransportCapability.TransportConnectedListener
                public void onConnected() {
                    singleEmitter.onSuccess(accessory);
                }

                @Override // com.amazon.alexa.accessory.capabilities.transport.TransportCapability.TransportConnectedListener
                public void onConnectionFailed(Throwable th) {
                    singleEmitter.onError(th);
                }
            });
        }

        public /* synthetic */ void lambda$requestSwitchTransport$10$TransportCapability$TransportActionHandler(Accessory accessory, final SingleEmitter singleEmitter) throws Throwable {
            final ControlResponseHandler controlResponseHandler = new ControlResponseHandler() { // from class: com.amazon.alexa.accessory.capabilities.transport.TransportCapability.TransportActionHandler.4
                @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
                public void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) throws Exception {
                    if (singleEmitter.isDisposed()) {
                        return;
                    }
                    controlStream.removeResponseHandler(this);
                    singleEmitter.onSuccess(response.getErrorCode());
                }
            };
            TransportCapability.this.controlStream.addResponseHandler(Accessories.Command.SWITCH_TRANSPORT, controlResponseHandler);
            TransportCapability.this.controlStream.dispatch(getSwitchControlMessage(accessory));
            singleEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$TransportCapability$TransportActionHandler$SDr3GOTe9jwMTEa2xJMxRgDMi8o
                @Override // io.reactivex.rxjava3.functions.Cancellable
                public final void cancel() {
                    TransportCapability.TransportActionHandler.this.lambda$null$9$TransportCapability$TransportActionHandler(controlResponseHandler);
                }
            });
            TransportCapability.this.transportUpgradeListener.onTransportSwitchRequested();
        }

        public /* synthetic */ Boolean lambda$shouldUpgrade$0$TransportCapability$TransportActionHandler(List list) throws Throwable {
            return Boolean.valueOf(list.contains(Common.Transport.BLUETOOTH_RFCOMM) && TransportCapability.this.accessory.getTransport() == AccessoryTransport.Type.GATT);
        }

        public /* synthetic */ Publisher lambda$switchTransport$7$TransportCapability$TransportActionHandler(final Accessory accessory, Flowable flowable) throws Throwable {
            return flowable.observeOn(AndroidSchedulers.mainThread()).flatMap(new Function<Throwable, Publisher<Long>>() { // from class: com.amazon.alexa.accessory.capabilities.transport.TransportCapability.TransportActionHandler.3
                int attempts = 1;

                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public Publisher<Long> mo10358apply(@NonNull Throwable th) throws Exception {
                    if (th instanceof BusyErrorCodeException) {
                        int i = this.attempts;
                        this.attempts = i + 1;
                        if (3 > i) {
                            TransportCapability.this.transportUpgradeListener.onTransportSwitchWillRetry(accessory.getTransport());
                            return Flowable.timer((this.attempts * 2) + 1, TimeUnit.SECONDS);
                        }
                    }
                    return Flowable.error(th);
                }
            });
        }

        public /* synthetic */ SingleSource lambda$switchTransportAttempt$8$TransportCapability$TransportActionHandler(Accessory accessory, Common.ErrorCode errorCode) throws Throwable {
            if (errorCode == Common.ErrorCode.SUCCESS) {
                TransportCapability.this.transportUpgradeListener.onTransportSwitched(accessory);
                TransportCapability.this.accessory = accessory;
                return Single.just(accessory);
            } else if (errorCode == Common.ErrorCode.BUSY) {
                return Single.error(new BusyErrorCodeException());
            } else {
                return Single.error(new IllegalStateException("Received non-busy error code in transport switch: + " + errorCode));
            }
        }

        public /* synthetic */ SingleSource lambda$upgradeTransport$2$TransportCapability$TransportActionHandler(List list) throws Throwable {
            if (list.contains(Common.Transport.BLUETOOTH_RFCOMM) && TransportCapability.this.accessory.getTransport() == AccessoryTransport.Type.GATT) {
                return Single.just(Common.Transport.BLUETOOTH_RFCOMM);
            }
            return Single.error(new IllegalStateException("There is no available transport to upgrade to"));
        }
    }

    /* loaded from: classes.dex */
    public interface TransportConnectedListener {
        void onConnected();

        void onConnectionFailed(Throwable th);
    }

    /* loaded from: classes.dex */
    public interface TransportUpgradeListener {
        void onTransportConnectRequested(Accessory accessory, TransportConnectedListener transportConnectedListener);

        void onTransportSwitchRequested();

        void onTransportSwitchWillRetry(AccessoryTransport.Type type);

        void onTransportSwitched(Accessory accessory);

        void onTransportUpgradeFailed(AccessoryTransport.Type type, Throwable th);

        void onTransportUpgradeRequested(AccessoryTransport.Type type);
    }

    public TransportCapability(TransportProducer transportProducer, Accessory accessory, DeviceRepository deviceRepository, TransportUpgradeListener transportUpgradeListener) {
        Preconditions.notNull(transportProducer, "transportProducer");
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        Preconditions.notNull(deviceRepository, "deviceRepository");
        Preconditions.notNull(transportUpgradeListener, "transportUpgradeListener");
        this.transportProducer = transportProducer;
        this.deviceRepository = deviceRepository;
        this.accessory = accessory;
        this.transportUpgradeListener = transportUpgradeListener;
        this.actionHandler = new TransportActionHandler();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Common.Transport getTransport(AccessoryTransport.Type type) {
        if (type == AccessoryTransport.Type.GATT) {
            return Common.Transport.BLUETOOTH_LOW_ENERGY;
        }
        if (type == AccessoryTransport.Type.RFCOMM) {
            return Common.Transport.BLUETOOTH_RFCOMM;
        }
        throw new IllegalArgumentException("Unsupported transport type " + type);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.transportProducer.detachActionHandler(this.actionHandler);
        this.actionHandler.cancelUpgrade();
        accessoryDescriptor.remove(this.controlStream);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.transportProducer.attachActionHandler(this.actionHandler);
        this.controlStream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        accessoryDescriptor.add(this.controlStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AccessoryTransport.Type getTransport(Common.Transport transport) {
        if (transport == Common.Transport.BLUETOOTH_LOW_ENERGY) {
            return AccessoryTransport.Type.GATT;
        }
        if (transport == Common.Transport.BLUETOOTH_RFCOMM) {
            return AccessoryTransport.Type.RFCOMM;
        }
        throw new IllegalArgumentException("Unsupported transport " + transport);
    }
}
