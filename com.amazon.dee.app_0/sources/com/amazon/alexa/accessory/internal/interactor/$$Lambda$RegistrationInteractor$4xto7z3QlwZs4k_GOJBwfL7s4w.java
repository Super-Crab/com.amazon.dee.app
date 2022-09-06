package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.User;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$RegistrationInteractor$4xto7z3QlwZs4k_GOJ-BwfL7s4w  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$RegistrationInteractor$4xto7z3QlwZs4k_GOJBwfL7s4w implements Predicate {
    public static final /* synthetic */ $$Lambda$RegistrationInteractor$4xto7z3QlwZs4k_GOJBwfL7s4w INSTANCE = new $$Lambda$RegistrationInteractor$4xto7z3QlwZs4k_GOJBwfL7s4w();

    private /* synthetic */ $$Lambda$RegistrationInteractor$4xto7z3QlwZs4k_GOJBwfL7s4w() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return RegistrationInteractor.lambda$observeLogins$2((User) obj);
    }
}
