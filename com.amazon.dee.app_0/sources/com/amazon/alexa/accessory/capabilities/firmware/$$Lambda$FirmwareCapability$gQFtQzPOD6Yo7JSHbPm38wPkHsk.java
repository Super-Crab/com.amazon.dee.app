package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$gQFtQzPOD6Yo7JSHbPm38wPkHsk  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$FirmwareCapability$gQFtQzPOD6Yo7JSHbPm38wPkHsk implements Consumer {
    public static final /* synthetic */ $$Lambda$FirmwareCapability$gQFtQzPOD6Yo7JSHbPm38wPkHsk INSTANCE = new $$Lambda$FirmwareCapability$gQFtQzPOD6Yo7JSHbPm38wPkHsk();

    private /* synthetic */ $$Lambda$FirmwareCapability$gQFtQzPOD6Yo7JSHbPm38wPkHsk() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Caught error while attempting silent DFU check", (Throwable) obj);
    }
}
