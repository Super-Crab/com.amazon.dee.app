package com.amazon.alexa.accessory.repositories.firmware;

import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.kota.InventoryUpdateBundle;
import com.amazon.alexa.accessory.repositories.Producer;
import java.util.Set;
/* loaded from: classes6.dex */
public interface FirmwareProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleCheckInventoryUpdate(boolean z, Producer.Result<Set<InventoryUpdateBundle>> result);

        void initiateFirmwareTransfer(Producer.Result<CompletableResult.Value> result);
    }
}
