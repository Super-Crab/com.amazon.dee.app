package com.amazon.alexa.accessory.repositories.firmware;

import com.amazon.alexa.accessory.kota.InventoryUpdateBundle;
import com.amazon.alexa.accessory.protocol.Firmware;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import java.util.Set;
/* loaded from: classes6.dex */
public interface FirmwareRepositoryV2 {
    Completable initiateFirmwareTransfer();

    Single<Set<Firmware.FirmwareInformation>> queryInformationSet();

    Single<Set<InventoryUpdateBundle>> queryInventoryUpdateSet(boolean z);

    Flowable<FirmwareUpdateStatus> queryUpdateStatus();
}
