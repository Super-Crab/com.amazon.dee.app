package com.amazon.alexa.accessory.avsclient.context;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import java.util.Set;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaTrustedStatesSupplier$WbGGvOIfkHoQ41OvIGCE30N3OcQ  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaTrustedStatesSupplier$WbGGvOIfkHoQ41OvIGCE30N3OcQ implements Function {
    public static final /* synthetic */ $$Lambda$AlexaTrustedStatesSupplier$WbGGvOIfkHoQ41OvIGCE30N3OcQ INSTANCE = new $$Lambda$AlexaTrustedStatesSupplier$WbGGvOIfkHoQ41OvIGCE30N3OcQ();

    private /* synthetic */ $$Lambda$AlexaTrustedStatesSupplier$WbGGvOIfkHoQ41OvIGCE30N3OcQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        ObservableSource just;
        just = Observable.just(Boolean.valueOf(AlexaTrustedStatesSupplier.isPresent((Set) obj)));
        return just;
    }
}
