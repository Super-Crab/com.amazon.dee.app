package com.amazon.alexa.device.setup.echo.softap.thrift;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$Drt63761CeTLkbMLn7oJ5AY3neI  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$ThriftClientImpl$Drt63761CeTLkbMLn7oJ5AY3neI implements Function {
    public static final /* synthetic */ $$Lambda$ThriftClientImpl$Drt63761CeTLkbMLn7oJ5AY3neI INSTANCE = new $$Lambda$ThriftClientImpl$Drt63761CeTLkbMLn7oJ5AY3neI();

    private /* synthetic */ $$Lambda$ThriftClientImpl$Drt63761CeTLkbMLn7oJ5AY3neI() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        ObservableSource flatMap;
        flatMap = ((Observable) obj).zipWith(Observable.range(1, 5), $$Lambda$ThriftClientImpl$ct1ddkXJBocs80g0xiZexkINfD0.INSTANCE).flatMap($$Lambda$ThriftClientImpl$WSwwT6slkfjLaWTbuXN8OLZBxL4.INSTANCE);
        return flatMap;
    }
}
