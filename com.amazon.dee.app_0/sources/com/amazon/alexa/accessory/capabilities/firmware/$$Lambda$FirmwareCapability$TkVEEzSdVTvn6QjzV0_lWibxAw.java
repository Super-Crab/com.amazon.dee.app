package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$TkVEEzSdVTvn6QjzV0_lWibxA-w  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$FirmwareCapability$TkVEEzSdVTvn6QjzV0_lWibxAw implements Consumer {
    public static final /* synthetic */ $$Lambda$FirmwareCapability$TkVEEzSdVTvn6QjzV0_lWibxAw INSTANCE = new $$Lambda$FirmwareCapability$TkVEEzSdVTvn6QjzV0_lWibxAw();

    private /* synthetic */ $$Lambda$FirmwareCapability$TkVEEzSdVTvn6QjzV0_lWibxAw() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s No device wanted candidate firmware update. No update will occur this session.", (Throwable) obj, FirmwareCapability.TAG);
    }
}
