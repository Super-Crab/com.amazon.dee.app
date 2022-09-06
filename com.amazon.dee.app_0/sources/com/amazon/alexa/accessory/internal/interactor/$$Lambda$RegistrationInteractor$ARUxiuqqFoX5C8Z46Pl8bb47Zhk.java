package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$RegistrationInteractor$ARUxiuqqFoX5C8Z46Pl8bb47Zhk  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$RegistrationInteractor$ARUxiuqqFoX5C8Z46Pl8bb47Zhk implements Consumer {
    public static final /* synthetic */ $$Lambda$RegistrationInteractor$ARUxiuqqFoX5C8Z46Pl8bb47Zhk INSTANCE = new $$Lambda$RegistrationInteractor$ARUxiuqqFoX5C8Z46Pl8bb47Zhk();

    private /* synthetic */ $$Lambda$RegistrationInteractor$ARUxiuqqFoX5C8Z46Pl8bb47Zhk() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Critical: RegistrationInteractor: Login observer onError", (Throwable) obj);
    }
}
