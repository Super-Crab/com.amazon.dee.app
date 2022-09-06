package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.protocol.Accessories;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$KbHcOZwhLn5LPTn831ggwcBqe4k  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$KbHcOZwhLn5LPTn831ggwcBqe4k implements Function {
    public static final /* synthetic */ $$Lambda$KbHcOZwhLn5LPTn831ggwcBqe4k INSTANCE = new $$Lambda$KbHcOZwhLn5LPTn831ggwcBqe4k();

    private /* synthetic */ $$Lambda$KbHcOZwhLn5LPTn831ggwcBqe4k() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return ((Accessories.Response) obj).getFirmwareComponent();
    }
}
