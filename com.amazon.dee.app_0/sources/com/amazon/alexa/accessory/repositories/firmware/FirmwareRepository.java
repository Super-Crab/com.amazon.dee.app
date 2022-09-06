package com.amazon.alexa.accessory.repositories.firmware;

import com.amazon.alexa.accessory.protocol.Firmware;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface FirmwareRepository {
    Single<Firmware.FirmwareInformation> queryInformation();

    Flowable<FirmwareUpdateStatus> queryUpdateStatus();
}
