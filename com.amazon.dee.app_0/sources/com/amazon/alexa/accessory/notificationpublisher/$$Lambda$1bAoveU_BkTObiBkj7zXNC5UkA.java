package com.amazon.alexa.accessory.notificationpublisher;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import java.util.Set;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$1bA-oveU_BkTObiBkj7zXNC5UkA  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$1bAoveU_BkTObiBkj7zXNC5UkA implements Function {
    public static final /* synthetic */ $$Lambda$1bAoveU_BkTObiBkj7zXNC5UkA INSTANCE = new $$Lambda$1bAoveU_BkTObiBkj7zXNC5UkA();

    private /* synthetic */ $$Lambda$1bAoveU_BkTObiBkj7zXNC5UkA() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Observable.fromIterable((Set) obj);
    }
}
