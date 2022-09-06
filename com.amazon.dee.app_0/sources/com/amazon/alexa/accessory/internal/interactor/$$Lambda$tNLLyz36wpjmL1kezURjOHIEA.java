package com.amazon.alexa.accessory.internal.interactor;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$tNLLyz36wpjmL-1kezURj-OHIEA  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$tNLLyz36wpjmL1kezURjOHIEA implements Function {
    public static final /* synthetic */ $$Lambda$tNLLyz36wpjmL1kezURjOHIEA INSTANCE = new $$Lambda$tNLLyz36wpjmL1kezURjOHIEA();

    private /* synthetic */ $$Lambda$tNLLyz36wpjmL1kezURjOHIEA() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Observable.fromIterable((List) obj);
    }
}
