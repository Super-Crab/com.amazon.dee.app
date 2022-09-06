package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$paJsCDjNJwPnkXYYdNL86oLJqV8  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$FirmwareCapability$paJsCDjNJwPnkXYYdNL86oLJqV8 implements Function {
    public static final /* synthetic */ $$Lambda$FirmwareCapability$paJsCDjNJwPnkXYYdNL86oLJqV8 INSTANCE = new $$Lambda$FirmwareCapability$paJsCDjNJwPnkXYYdNL86oLJqV8();

    private /* synthetic */ $$Lambda$FirmwareCapability$paJsCDjNJwPnkXYYdNL86oLJqV8() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Logger.e("Caught error while attempting to determine available updates from cloud", (Throwable) obj);
    }
}
