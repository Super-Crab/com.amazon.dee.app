package com.amazon.whisperjoin.provisionerSDK.devices.basic;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.amazon.whisperbridge.ble.BleException;
import com.amazon.whisperbridge.ble.BleGattCharacteristic;
import com.amazon.whisperbridge.ble.BleGattClient;
import com.amazon.whisperbridge.ble.BleGattServiceClient;
import com.amazon.whisperbridge.ble.BleManager;
import com.amazon.whisperbridge.ble.command.BleDiscoverServicesCommand;
import com.amazon.whisperbridge.ble.command.BleReadCharacteristicCommand;
import com.amazon.whisperbridge.ble.command.BleWriteCharacteristicCommand;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BLETransportOperationError;
import com.amazon.whisperjoin.provisionerSDK.devices.basic.client.BasicBLEGattServiceClient;
import com.amazon.whisperjoin.util.rx.RxLog;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.Locale;
import java.util.UUID;
/* loaded from: classes13.dex */
public class BasicBLEDevice implements BLEDevice, BleGattClient.Callback {
    private static final String TAG = "BasicBLEDevice";
    private BleGattClient mBleGattClient;
    private final BleManager mBleManager;
    private BluetoothDevice mBluetoothDevice;
    private Context mContext;
    private PublishSubject<Integer> mNewConnectionStateSubject;
    private Scheduler mObserverOnScheduler;
    private Scheduler mSubscribeOnScheduler;

    public BasicBLEDevice(BluetoothDevice bluetoothDevice, BleManager bleManager, Context context) {
        this.mBleManager = bleManager;
        this.mNewConnectionStateSubject = PublishSubject.create();
        this.mBleGattClient = null;
        this.mBluetoothDevice = bluetoothDevice;
        this.mContext = context;
        this.mSubscribeOnScheduler = Schedulers.io();
        this.mObserverOnScheduler = AndroidSchedulers.mainThread();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkOperationSuccess(int i, BLETransportOperationError.Operation operation) throws BLETransportOperationError {
        if (i == 0) {
            return;
        }
        throw new BLETransportOperationError(operation);
    }

    private Observable<Integer> getConnectionUpdates() {
        return this.mNewConnectionStateSubject.hide();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Completable waitForConnectedState() {
        return getConnectionUpdates().doOnNext(RxLog.doOnNext(TAG, "New Connection Update")).filter(new Predicate<Integer>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.4
            @Override // io.reactivex.rxjava3.functions.Predicate
            public boolean test(@NonNull Integer num) throws Exception {
                return num.equals(2);
            }
        }).take(1L).flatMapCompletable(new Function<Integer, CompletableSource>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.3
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(@NonNull Integer num) throws Exception {
                return Completable.complete();
            }
        });
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.basic.BLEDevice
    public Completable bindServiceClient(final BasicBLEGattServiceClient basicBLEGattServiceClient) {
        return Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public CompletableSource mo10365get() throws Exception {
                if (!BasicBLEDevice.this.mBleGattClient.bindServiceClient(basicBLEGattServiceClient)) {
                    return Completable.error(new BLETransportOperationError(BLETransportOperationError.Operation.BIND_SERVICE_CLIENT));
                }
                return Completable.complete();
            }
        }).doOnSubscribe(RxLog.doOnSubscribe(TAG, "Binding new service client"));
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.basic.BLEDevice
    public Completable disconnect() {
        return Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public CompletableSource mo10365get() throws Exception {
                BasicBLEDevice.this.mBleGattClient.close();
                return Completable.complete();
            }
        }).doOnComplete(RxLog.doOnComplete(TAG, "Successfully disconnected")).onErrorResumeNext(new Function<Throwable, CompletableSource>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.5
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(@NonNull Throwable th) throws Exception {
                return Completable.error(new BleException("Error when trying to disconnect."));
            }
        });
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.basic.BLEDevice
    public Completable discoverServices() {
        return Single.defer(new Supplier<SingleSource<BleDiscoverServicesCommand.Result>>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.9
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public SingleSource<BleDiscoverServicesCommand.Result> mo10365get() throws Exception {
                return Single.fromFuture(BasicBLEDevice.this.mBleGattClient.enqueueDiscoverServices());
            }
        }).doOnSubscribe(RxLog.doOnSubscribe(TAG, "Enqueuing Discovery of Services")).subscribeOn(this.mSubscribeOnScheduler).observeOn(this.mObserverOnScheduler).onErrorResumeNext(new Function<Throwable, SingleSource<BleDiscoverServicesCommand.Result>>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public SingleSource<BleDiscoverServicesCommand.Result> mo10358apply(@NonNull Throwable th) throws Exception {
                return Single.error(new BLETransportOperationError(BLETransportOperationError.Operation.DISCOVER_GATT_SERVICES));
            }
        }).flatMapCompletable(new Function<BleDiscoverServicesCommand.Result, CompletableSource>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.7
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(@NonNull BleDiscoverServicesCommand.Result result) throws Exception {
                BasicBLEDevice.this.checkOperationSuccess(result.status, BLETransportOperationError.Operation.DISCOVER_GATT_SERVICES);
                return Completable.complete();
            }
        }).doOnComplete(RxLog.doOnComplete(TAG, "Discover Services Complete")).doOnError(RxLog.doOnError(TAG, "An error occurred while discovering services"));
    }

    @Override // com.amazon.whisperbridge.ble.BleGattClient.Callback
    public void onConnectionStateChanged(BleGattClient bleGattClient, int i, int i2) {
        if (bleGattClient != this.mBleGattClient) {
            this.mNewConnectionStateSubject.onError(new AssertionError("Client callback mismatch"));
        }
        this.mNewConnectionStateSubject.onNext(Integer.valueOf(i2));
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.basic.BLEDevice
    public Single<byte[]> readCharacteristic(final UUID uuid, final UUID uuid2) {
        return Single.defer(new Supplier<SingleSource<BleReadCharacteristicCommand.Result>>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.13
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public SingleSource<BleReadCharacteristicCommand.Result> mo10365get() throws Exception {
                return Single.fromFuture(BasicBLEDevice.this.mBleGattClient.getServiceClient(uuid).readCharacteristic(uuid2));
            }
        }).doOnSubscribe(RxLog.doOnSubscribe(TAG, String.format(Locale.ENGLISH, "Reading characteristic: Service: %s, Characteristic: %s", uuid.toString(), uuid2.toString()))).subscribeOn(this.mSubscribeOnScheduler).observeOn(this.mObserverOnScheduler).onErrorResumeNext(new Function<Throwable, SingleSource<BleReadCharacteristicCommand.Result>>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.12
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public SingleSource<BleReadCharacteristicCommand.Result> mo10358apply(@NonNull Throwable th) throws Exception {
                return Single.error(new BLETransportOperationError(th, BLETransportOperationError.Operation.INITIATE_READ_CHARACTERISTIC));
            }
        }).flatMap(new Function<BleReadCharacteristicCommand.Result, SingleSource<byte[]>>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.11
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public SingleSource<byte[]> mo10358apply(@NonNull BleReadCharacteristicCommand.Result result) throws Exception {
                BleGattServiceClient serviceClient = BasicBLEDevice.this.mBleGattClient.getServiceClient(uuid);
                BasicBLEDevice.this.checkOperationSuccess(result.status, BLETransportOperationError.Operation.CONFIRM_READ_CHARACTERISTIC);
                return Single.just(serviceClient.getBleGattCharacteristic(uuid2).getStoredData());
            }
        }).doOnSuccess(RxLog.doOnSuccess(TAG, "Reading characteristic complete")).doOnError(RxLog.doOnError(TAG, String.format(Locale.ENGLISH, "An Error Occurred while reading characteristic: Service: %s, Characteristic: %s", uuid.toString(), uuid2.toString())));
    }

    protected void setBleGattClient(BleGattClient bleGattClient) {
        this.mBleGattClient = bleGattClient;
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.basic.BLEDevice
    public Observable<byte[]> subscribeToNotifications(final UUID uuid, final UUID uuid2) {
        return Completable.defer(new Supplier<CompletableSource>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.21
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public CompletableSource mo10365get() throws Exception {
                return ((BasicBLEGattServiceClient) BasicBLEDevice.this.mBleGattClient.getServiceClient(uuid)).enableNotifications(uuid2);
            }
        }).doOnSubscribe(RxLog.doOnSubscribe(TAG, String.format(Locale.ENGLISH, "Subscribing to characteristic notifications: Service: %s, Characteristic: %s", uuid.toString(), uuid2.toString()))).onErrorResumeNext(new Function<Throwable, CompletableSource>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.20
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(@NonNull Throwable th) throws Exception {
                return Completable.error(new BLETransportOperationError(th, BLETransportOperationError.Operation.ENABLE_NOTIFICATIONS));
            }
        }).andThen(Observable.defer(new Supplier<ObservableSource<BleGattCharacteristic>>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.19
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public ObservableSource<BleGattCharacteristic> mo10365get() throws Exception {
                return ((BasicBLEGattServiceClient) BasicBLEDevice.this.mBleGattClient.getServiceClient(uuid)).getCharacteristicUpdates();
            }
        })).filter(new Predicate<BleGattCharacteristic>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.18
            @Override // io.reactivex.rxjava3.functions.Predicate
            public boolean test(@NonNull BleGattCharacteristic bleGattCharacteristic) throws Exception {
                return bleGattCharacteristic.getUuid().equals(uuid2);
            }
        }).flatMap(new Function<BleGattCharacteristic, ObservableSource<byte[]>>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.17
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<byte[]> mo10358apply(@NonNull BleGattCharacteristic bleGattCharacteristic) throws Exception {
                return Observable.just(bleGattCharacteristic.getStoredData());
            }
        }).doOnError(RxLog.doOnError(TAG, String.format(Locale.ENGLISH, "An error occurred while subscribing to characteristic notifications: Service: %s, Characteristic: %s", uuid.toString(), uuid2.toString())));
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.basic.BLEDevice
    public Completable waitForConnection(final boolean z) {
        return Single.defer(new Supplier<SingleSource<BleGattClient>>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public SingleSource<BleGattClient> mo10365get() throws Exception {
                return Single.just(BasicBLEDevice.this.mBleManager.connectToServer(BasicBLEDevice.this.mContext, BasicBLEDevice.this.mBluetoothDevice, this, z));
            }
        }).doOnSubscribe(RxLog.doOnSubscribe(TAG, "Starting connection to device.")).flatMapCompletable(new Function<BleGattClient, CompletableSource>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(@NonNull BleGattClient bleGattClient) throws Exception {
                this.mBleGattClient = bleGattClient;
                return BasicBLEDevice.this.waitForConnectedState();
            }
        }).doOnComplete(RxLog.doOnComplete(TAG, "Successfully connected to device")).doOnError(RxLog.doOnError(TAG, "An error occurred while connecting"));
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.basic.BLEDevice
    public Completable writeCharacteristic(final UUID uuid, final UUID uuid2, final byte[] bArr) {
        return Single.defer(new Supplier<SingleSource<BleWriteCharacteristicCommand.Result>>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.16
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public SingleSource<BleWriteCharacteristicCommand.Result> mo10365get() throws Exception {
                BleGattServiceClient serviceClient = BasicBLEDevice.this.mBleGattClient.getServiceClient(uuid);
                serviceClient.getBleGattCharacteristic(uuid2).setStoredData(bArr);
                return Single.fromFuture(serviceClient.writeCharacteristic(uuid2));
            }
        }).doOnSubscribe(RxLog.doOnSubscribe(TAG, String.format(Locale.ENGLISH, "Writing characteristic: Service: %s, Characteristic: %s", uuid.toString(), uuid2.toString()))).subscribeOn(this.mSubscribeOnScheduler).observeOn(this.mObserverOnScheduler).onErrorResumeNext(new Function<Throwable, SingleSource<BleWriteCharacteristicCommand.Result>>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.15
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public SingleSource<BleWriteCharacteristicCommand.Result> mo10358apply(@NonNull Throwable th) throws Exception {
                return Single.error(new BLETransportOperationError(th, BLETransportOperationError.Operation.INITIATE_WRITE_CHARACTERISTIC));
            }
        }).flatMapCompletable(new Function<BleWriteCharacteristicCommand.Result, CompletableSource>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.BasicBLEDevice.14
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(@NonNull BleWriteCharacteristicCommand.Result result) throws Exception {
                BasicBLEDevice.this.checkOperationSuccess(result.status, BLETransportOperationError.Operation.CONFIRM_WRITE_CHARACTERISTIC);
                return Completable.complete();
            }
        }).doOnComplete(RxLog.doOnComplete(TAG, "Writing characteristic complete")).doOnError(RxLog.doOnError(TAG, String.format(Locale.ENGLISH, "An Error Occurred while writing characteristic: Service: %s, Characteristic: %s", uuid.toString(), uuid2.toString())));
    }

    BasicBLEDevice(BluetoothDevice bluetoothDevice, BleManager bleManager, Context context, Scheduler scheduler, Scheduler scheduler2) {
        this.mBleManager = bleManager;
        this.mNewConnectionStateSubject = PublishSubject.create();
        this.mBleGattClient = null;
        this.mBluetoothDevice = bluetoothDevice;
        this.mContext = context;
        this.mSubscribeOnScheduler = scheduler;
        this.mObserverOnScheduler = scheduler2;
    }
}
