package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.User;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceAccountInteractor$N_0QTEqPSPYOXebYvW5cnee6J40  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DeviceAccountInteractor$N_0QTEqPSPYOXebYvW5cnee6J40 implements Predicate {
    public static final /* synthetic */ $$Lambda$DeviceAccountInteractor$N_0QTEqPSPYOXebYvW5cnee6J40 INSTANCE = new $$Lambda$DeviceAccountInteractor$N_0QTEqPSPYOXebYvW5cnee6J40();

    private /* synthetic */ $$Lambda$DeviceAccountInteractor$N_0QTEqPSPYOXebYvW5cnee6J40() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return DeviceAccountInteractor.lambda$observeLogins$4((User) obj);
    }
}
