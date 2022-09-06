package com.amazon.alexa.accessory.repositories.device.v2;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
/* loaded from: classes6.dex */
public interface DeviceSupplierV2 {
    Single<DeviceGroup> addDeviceGroup(DeviceGroup deviceGroup);

    Single<DeviceGroup> getDeviceGroup(String str);

    Single<Boolean> hasDeviceGroup(String str);

    Observable<List<DeviceGroup>> queryDeviceGroups();

    Completable removeDeviceGroup(long j);

    Completable removeDeviceGroup(DeviceGroup deviceGroup);

    Completable updateDeviceGroup(DeviceGroup deviceGroup);
}
