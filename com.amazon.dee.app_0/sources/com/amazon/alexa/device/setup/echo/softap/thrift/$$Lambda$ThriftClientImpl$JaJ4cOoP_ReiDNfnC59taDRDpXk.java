package com.amazon.alexa.device.setup.echo.softap.thrift;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$JaJ4cOoP_ReiDNfnC59taDRDpXk  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$ThriftClientImpl$JaJ4cOoP_ReiDNfnC59taDRDpXk implements Function {
    public static final /* synthetic */ $$Lambda$ThriftClientImpl$JaJ4cOoP_ReiDNfnC59taDRDpXk INSTANCE = new $$Lambda$ThriftClientImpl$JaJ4cOoP_ReiDNfnC59taDRDpXk();

    private /* synthetic */ $$Lambda$ThriftClientImpl$JaJ4cOoP_ReiDNfnC59taDRDpXk() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        ObservableSource flatMap;
        flatMap = ((Observable) obj).zipWith(Observable.range(1, 5), $$Lambda$ThriftClientImpl$kgBKCh57mkIKMELgdqViTqya2oY.INSTANCE).flatMap($$Lambda$ThriftClientImpl$mwAc0HnhAtPBdL4N7OuKW4GaV5U.INSTANCE);
        return flatMap;
    }
}
