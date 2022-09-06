package com.amazon.dee.app.util;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Func1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.util.-$$Lambda$Observables$VMRDaXPR3BCTgwBN46tYN6WMKNM  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$Observables$VMRDaXPR3BCTgwBN46tYN6WMKNM implements Func1 {
    public static final /* synthetic */ $$Lambda$Observables$VMRDaXPR3BCTgwBN46tYN6WMKNM INSTANCE = new $$Lambda$Observables$VMRDaXPR3BCTgwBN46tYN6WMKNM();

    private /* synthetic */ $$Lambda$Observables$VMRDaXPR3BCTgwBN46tYN6WMKNM() {
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public final Object mo13102call(Object obj) {
        Observable timer;
        Integer num = (Integer) obj;
        timer = Observable.timer((long) Math.floor((Math.pow(2.0d, num.intValue()) - 1.0d) / 2.0d), TimeUnit.SECONDS);
        return timer;
    }
}
