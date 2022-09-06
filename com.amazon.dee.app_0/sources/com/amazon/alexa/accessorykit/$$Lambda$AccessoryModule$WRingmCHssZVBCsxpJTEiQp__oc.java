package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.protocol.Common;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$WRingmCHssZVBCsxpJTEiQp__oc  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryModule$WRingmCHssZVBCsxpJTEiQp__oc implements Function {
    public static final /* synthetic */ $$Lambda$AccessoryModule$WRingmCHssZVBCsxpJTEiQp__oc INSTANCE = new $$Lambda$AccessoryModule$WRingmCHssZVBCsxpJTEiQp__oc();

    private /* synthetic */ $$Lambda$AccessoryModule$WRingmCHssZVBCsxpJTEiQp__oc() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        Completable errorCodeToCompletable;
        errorCodeToCompletable = AccessoryModule.errorCodeToCompletable((Common.ErrorCode) obj);
        return errorCodeToCompletable;
    }
}
