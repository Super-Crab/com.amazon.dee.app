package com.amazon.whisperjoin.provisionerSDK.devices.basic.client;

import com.amazon.whisperbridge.ble.BleGattCharacteristic;
import com.amazon.whisperbridge.ble.BleGattServiceClient;
import com.amazon.whisperbridge.ble.command.BleUpdateNotificationsCommand;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BLETransportOperationError;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.UUID;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public class BasicBLEGattServiceClient extends BleGattServiceClient {
    private PublishSubject<BleGattCharacteristic> mBleGattCharacteristicSubject;

    public BasicBLEGattServiceClient(UUID uuid) {
        super(uuid);
        this.mBleGattCharacteristicSubject = PublishSubject.create();
    }

    protected Future<BleUpdateNotificationsCommand.Result> enableCharacteristicNotifiability(UUID uuid, boolean z, boolean z2) {
        return updateCharacteristicNotifiability(uuid, z, z2);
    }

    public Completable enableNotifications(final UUID uuid) {
        return Single.defer(new Supplier<SingleSource<BleUpdateNotificationsCommand.Result>>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.client.BasicBLEGattServiceClient.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public SingleSource<BleUpdateNotificationsCommand.Result> mo10365get() throws Exception {
                return Single.fromFuture(BasicBLEGattServiceClient.this.enableCharacteristicNotifiability(uuid, true, false));
            }
        }).onErrorResumeNext(new Function<Throwable, SingleSource<BleUpdateNotificationsCommand.Result>>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.client.BasicBLEGattServiceClient.2
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public SingleSource<BleUpdateNotificationsCommand.Result> mo10358apply(@NonNull Throwable th) throws Exception {
                return Single.error(new BLETransportOperationError(BLETransportOperationError.Operation.ENABLE_NOTIFICATION_PROVISIONING_COMMAND_RESPONSE));
            }
        }).flatMapCompletable(new Function<BleUpdateNotificationsCommand.Result, CompletableSource>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.basic.client.BasicBLEGattServiceClient.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public CompletableSource mo10358apply(@NonNull BleUpdateNotificationsCommand.Result result) throws Exception {
                if (result.status != 0) {
                    return Completable.error(new BLETransportOperationError(BLETransportOperationError.Operation.ENABLE_NOTIFICATION_PROVISIONING_COMMAND_RESPONSE));
                }
                return Completable.complete();
            }
        });
    }

    public Observable<BleGattCharacteristic> getCharacteristicUpdates() {
        return this.mBleGattCharacteristicSubject.hide();
    }

    @Override // com.amazon.whisperbridge.ble.BleGattServiceClient
    protected void onCharacteristicUpdated(BleGattCharacteristic bleGattCharacteristic) {
        this.mBleGattCharacteristicSubject.onNext(bleGattCharacteristic);
    }
}
