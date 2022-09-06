package com.amazon.alexa.device.setup.echo.softap.thrift;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$g-QhJLP7o9L5o6C_uVl1aL3zlHI  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$ThriftClientImpl$gQhJLP7o9L5o6C_uVl1aL3zlHI implements Function {
    public static final /* synthetic */ $$Lambda$ThriftClientImpl$gQhJLP7o9L5o6C_uVl1aL3zlHI INSTANCE = new $$Lambda$ThriftClientImpl$gQhJLP7o9L5o6C_uVl1aL3zlHI();

    private /* synthetic */ $$Lambda$ThriftClientImpl$gQhJLP7o9L5o6C_uVl1aL3zlHI() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        ObservableSource flatMap;
        flatMap = ((Observable) obj).zipWith(Observable.range(1, 5), $$Lambda$ThriftClientImpl$Hdqmqd24eFmgbrlQ9B6WNfjXEqA.INSTANCE).flatMap($$Lambda$ThriftClientImpl$z6qZH676wcS8opusb09jL3hJuG8.INSTANCE);
        return flatMap;
    }
}
