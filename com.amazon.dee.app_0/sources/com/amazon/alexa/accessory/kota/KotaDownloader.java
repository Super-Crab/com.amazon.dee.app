package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes.dex */
public interface KotaDownloader {
    Completable downloadPackage(UpdateRequest updateRequest, InventoryUpdate inventoryUpdate, boolean z);

    Single<UpdateRequest> generateUpdateRequest(Device.DeviceInformation deviceInformation, Firmware.FirmwareInformation firmwareInformation);

    Maybe<InventoryUpdate> getAvailableInventoryUpdate(UpdateRequest updateRequest, boolean z);
}
