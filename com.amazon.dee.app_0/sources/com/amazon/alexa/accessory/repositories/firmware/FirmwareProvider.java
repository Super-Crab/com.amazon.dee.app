package com.amazon.alexa.accessory.repositories.firmware;

import com.amazon.alexa.accessory.kota.InventoryUpdateBundle;
import com.amazon.alexa.accessory.protocol.Firmware;
import java.util.Set;
/* loaded from: classes6.dex */
public interface FirmwareProvider {
    void informationNotAvailable(Throwable th);

    void provideInformation(Set<Firmware.FirmwareInformation> set);

    void provideInventoryUpdate(Set<InventoryUpdateBundle> set);

    void provideInventoryUpdateError(Throwable th);

    void provideUpdateStatus(FirmwareUpdateStatus firmwareUpdateStatus);
}
