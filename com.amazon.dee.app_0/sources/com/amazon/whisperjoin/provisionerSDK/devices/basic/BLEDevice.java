package com.amazon.whisperjoin.provisionerSDK.devices.basic;

import com.amazon.whisperjoin.provisionerSDK.devices.basic.client.BasicBLEGattServiceClient;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.UUID;
/* loaded from: classes13.dex */
public interface BLEDevice {
    Completable bindServiceClient(BasicBLEGattServiceClient basicBLEGattServiceClient);

    Completable disconnect();

    Completable discoverServices();

    Single<byte[]> readCharacteristic(UUID uuid, UUID uuid2);

    Observable<byte[]> subscribeToNotifications(UUID uuid, UUID uuid2);

    Completable waitForConnection(boolean z);

    Completable writeCharacteristic(UUID uuid, UUID uuid2, byte[] bArr);
}
