package com.amazon.alexa.accessorykit.accessories.session.supplier;

import com.amazon.alexa.accessoryclient.common.api.ConnectionStatus;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.accessories.session.supplier.-$$Lambda$SessionSupplierModule$LWDHEZdyWhgitzmQVLkaTgCJSgc  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$SessionSupplierModule$LWDHEZdyWhgitzmQVLkaTgCJSgc implements Function {
    public static final /* synthetic */ $$Lambda$SessionSupplierModule$LWDHEZdyWhgitzmQVLkaTgCJSgc INSTANCE = new $$Lambda$SessionSupplierModule$LWDHEZdyWhgitzmQVLkaTgCJSgc();

    private /* synthetic */ $$Lambda$SessionSupplierModule$LWDHEZdyWhgitzmQVLkaTgCJSgc() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        Boolean valueOf;
        ConnectionStatus connectionStatus = (ConnectionStatus) obj;
        valueOf = Boolean.valueOf(r1 != ConnectionStatus.NONEXISTENT);
        return valueOf;
    }
}
