package com.amazon.alexa.accessory.capabilities.system;

import com.amazon.alexa.accessory.protocol.Accessories;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.system.-$$Lambda$EUvIBqh0MD2_LBZIOxKP7Ob4jvE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$EUvIBqh0MD2_LBZIOxKP7Ob4jvE implements Function {
    public static final /* synthetic */ $$Lambda$EUvIBqh0MD2_LBZIOxKP7Ob4jvE INSTANCE = new $$Lambda$EUvIBqh0MD2_LBZIOxKP7Ob4jvE();

    private /* synthetic */ $$Lambda$EUvIBqh0MD2_LBZIOxKP7Ob4jvE() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return ((Accessories.Response) obj).getLocales();
    }
}
