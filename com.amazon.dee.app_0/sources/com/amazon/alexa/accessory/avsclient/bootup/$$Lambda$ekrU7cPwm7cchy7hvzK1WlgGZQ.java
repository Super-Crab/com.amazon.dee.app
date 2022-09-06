package com.amazon.alexa.accessory.avsclient.bootup;

import com.amazon.alexa.accessory.protocol.StateOuterClass;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.bootup.-$$Lambda$ekrU7cPwm7cchy7hvzK1WlgG-ZQ  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ekrU7cPwm7cchy7hvzK1WlgGZQ implements Function {
    public static final /* synthetic */ $$Lambda$ekrU7cPwm7cchy7hvzK1WlgGZQ INSTANCE = new $$Lambda$ekrU7cPwm7cchy7hvzK1WlgGZQ();

    private /* synthetic */ $$Lambda$ekrU7cPwm7cchy7hvzK1WlgGZQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Boolean.valueOf(((StateOuterClass.State) obj).getBoolean());
    }
}
