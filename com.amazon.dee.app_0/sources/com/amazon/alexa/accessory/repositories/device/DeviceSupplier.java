package com.amazon.alexa.accessory.repositories.device;

import com.amazon.alexa.accessory.persistence.device.DeviceContract;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
@Deprecated
/* loaded from: classes6.dex */
public interface DeviceSupplier extends DeviceSupplierV2 {
    @Deprecated
    Single<DeviceContract.Device> addDevice(DeviceContract.Device device);

    @Deprecated
    Single<DeviceContract.Device> getDevice(String str);

    @Deprecated
    Observable<List<DeviceContract.Device>> getDevices();

    @Deprecated
    Single<Boolean> hasDevice(String str);

    @Deprecated
    Completable removeDevice(long j);

    @Deprecated
    Completable updateDevice(DeviceContract.Device device);
}
