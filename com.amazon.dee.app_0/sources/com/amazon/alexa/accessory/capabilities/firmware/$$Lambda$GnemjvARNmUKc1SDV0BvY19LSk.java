package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.capabilities.firmware.FirmwareUpdateTask;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$GnemjvARN-mUKc1SDV0BvY19LSk  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$GnemjvARNmUKc1SDV0BvY19LSk implements FirmwareUpdateTask.Callback {
    private final /* synthetic */ TaskManager f$0;

    @Override // com.amazon.alexa.accessory.capabilities.firmware.FirmwareUpdateTask.Callback
    public final void onDispose(FirmwareUpdateTask firmwareUpdateTask) {
        this.f$0.dispose(firmwareUpdateTask);
    }
}
