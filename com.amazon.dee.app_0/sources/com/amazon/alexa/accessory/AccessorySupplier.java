package com.amazon.alexa.accessory;

import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes.dex */
public interface AccessorySupplier {
    Single<Boolean> isConnectible(Accessory accessory);

    void link(Accessory accessory, AccessoryLinkListener accessoryLinkListener);

    Completable monitorUnexpiredStandbyTimeoutAccessory(Accessory accessory);

    Observable<Accessory> queryExpiredStandbyAccessories();

    Observable<Accessory> queryStandbyAccessories();

    Completable standby(Accessory accessory, int i, DeviceGroup.StandbyReason standbyReason);

    void unlink(Accessory accessory, AccessoryLinkListener accessoryLinkListener);
}
