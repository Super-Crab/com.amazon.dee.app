package com.amazon.alexa.accessory.repositories.device;

import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface DeviceProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleCompleteSetup(boolean z, Producer.Result<Common.ErrorCode> result);

        void handleOverrideAssistant(boolean z, Producer.Result<Common.ErrorCode> result);

        void handleStartSetup(Producer.Result<Common.ErrorCode> result);

        void handleUpdateDeviceInformation(String str, int i, Producer.Result<Common.ErrorCode> result);
    }
}
