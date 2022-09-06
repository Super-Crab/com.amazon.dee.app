package com.amazon.alexa.accessory.engagement;

import com.amazon.alexa.accessory.protocol.System;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.engagement.-$$Lambda$AccessorySessionAttributes$RNgRAjewYMptD5Ge65_6bw7PsS0  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AccessorySessionAttributes$RNgRAjewYMptD5Ge65_6bw7PsS0 implements Function {
    public static final /* synthetic */ $$Lambda$AccessorySessionAttributes$RNgRAjewYMptD5Ge65_6bw7PsS0 INSTANCE = new $$Lambda$AccessorySessionAttributes$RNgRAjewYMptD5Ge65_6bw7PsS0();

    private /* synthetic */ $$Lambda$AccessorySessionAttributes$RNgRAjewYMptD5Ge65_6bw7PsS0() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        String name;
        name = ((System.Locales) obj).getCurrentLocale().getName();
        return name;
    }
}
