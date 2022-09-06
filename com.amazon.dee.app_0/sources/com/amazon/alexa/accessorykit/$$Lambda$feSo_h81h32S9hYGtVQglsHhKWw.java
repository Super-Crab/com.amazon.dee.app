package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.protocol.Device;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$feSo_h81h32S9hYGtVQglsHhKWw  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$feSo_h81h32S9hYGtVQglsHhKWw implements Function {
    public static final /* synthetic */ $$Lambda$feSo_h81h32S9hYGtVQglsHhKWw INSTANCE = new $$Lambda$feSo_h81h32S9hYGtVQglsHhKWw();

    private /* synthetic */ $$Lambda$feSo_h81h32S9hYGtVQglsHhKWw() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Boolean.valueOf(((Device.DeviceConfiguration) obj).getNeedsSetup());
    }
}
