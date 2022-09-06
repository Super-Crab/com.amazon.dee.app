package com.amazon.alexa.device.setup.echo.softap.thrift;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$oqd3Cbz4JGUkgWHHhXG1JZm4yn0  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$ThriftClientImpl$oqd3Cbz4JGUkgWHHhXG1JZm4yn0 implements Function {
    public static final /* synthetic */ $$Lambda$ThriftClientImpl$oqd3Cbz4JGUkgWHHhXG1JZm4yn0 INSTANCE = new $$Lambda$ThriftClientImpl$oqd3Cbz4JGUkgWHHhXG1JZm4yn0();

    private /* synthetic */ $$Lambda$ThriftClientImpl$oqd3Cbz4JGUkgWHHhXG1JZm4yn0() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        ObservableSource flatMap;
        flatMap = ((Observable) obj).zipWith(Observable.range(1, 5), $$Lambda$ThriftClientImpl$ssTgjzvLNv6lHko0hKUvoulHXPg.INSTANCE).flatMap($$Lambda$ThriftClientImpl$QnxwYlWDmuUlHTQ2E998iW_ry2s.INSTANCE);
        return flatMap;
    }
}
