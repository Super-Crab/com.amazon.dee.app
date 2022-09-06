package com.amazon.alexa.device.setup.echo.softap.thrift;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$rirodOjAMDkJ1o-PRAqNT4W6ZmI  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$ThriftClientImpl$rirodOjAMDkJ1oPRAqNT4W6ZmI implements Function {
    public static final /* synthetic */ $$Lambda$ThriftClientImpl$rirodOjAMDkJ1oPRAqNT4W6ZmI INSTANCE = new $$Lambda$ThriftClientImpl$rirodOjAMDkJ1oPRAqNT4W6ZmI();

    private /* synthetic */ $$Lambda$ThriftClientImpl$rirodOjAMDkJ1oPRAqNT4W6ZmI() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        ObservableSource flatMap;
        flatMap = ((Observable) obj).zipWith(Observable.range(1, 5), $$Lambda$ThriftClientImpl$f1bIb4DSrKvMVeSIA3sCx80k9G8.INSTANCE).flatMap($$Lambda$ThriftClientImpl$obl19pg3DGLoJIBzPMl4Ul309_E.INSTANCE);
        return flatMap;
    }
}
