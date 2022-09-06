package com.amazon.alexa.accessory.notificationpublisher;

import com.amazon.alexa.accessory.protocol.StateOuterClass;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$AR-kVSPIf2OWqlhRmUOw5OSUd1A  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ARkVSPIf2OWqlhRmUOw5OSUd1A implements Function {
    public static final /* synthetic */ $$Lambda$ARkVSPIf2OWqlhRmUOw5OSUd1A INSTANCE = new $$Lambda$ARkVSPIf2OWqlhRmUOw5OSUd1A();

    private /* synthetic */ $$Lambda$ARkVSPIf2OWqlhRmUOw5OSUd1A() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Integer.valueOf(((StateOuterClass.State) obj).getInteger());
    }
}
