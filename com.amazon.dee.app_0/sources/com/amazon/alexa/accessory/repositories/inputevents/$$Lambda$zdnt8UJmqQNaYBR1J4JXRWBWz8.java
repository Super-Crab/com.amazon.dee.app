package com.amazon.alexa.accessory.repositories.inputevents;

import com.amazon.alexa.accessory.repositories.inputevents.MemoryInputRepository;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.repositories.inputevents.-$$Lambda$zdnt8UJmqQNaYBR1J-4JXRWBWz8  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$zdnt8UJmqQNaYBR1J4JXRWBWz8 implements Function {
    public static final /* synthetic */ $$Lambda$zdnt8UJmqQNaYBR1J4JXRWBWz8 INSTANCE = new $$Lambda$zdnt8UJmqQNaYBR1J4JXRWBWz8();

    private /* synthetic */ $$Lambda$zdnt8UJmqQNaYBR1J4JXRWBWz8() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return ((MemoryInputRepository.DeviceInputConfiguration) obj).getConfiguration();
    }
}
