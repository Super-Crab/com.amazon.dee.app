package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$RegistrationInteractor$1$uBv-MCjrGoiVCWDepU14FvmWdK8  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$RegistrationInteractor$1$uBvMCjrGoiVCWDepU14FvmWdK8 implements Consumer {
    public static final /* synthetic */ $$Lambda$RegistrationInteractor$1$uBvMCjrGoiVCWDepU14FvmWdK8 INSTANCE = new $$Lambda$RegistrationInteractor$1$uBvMCjrGoiVCWDepU14FvmWdK8();

    private /* synthetic */ $$Lambda$RegistrationInteractor$1$uBvMCjrGoiVCWDepU14FvmWdK8() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("RegistrationInteractor: On session connected listener registration caught exception", (Throwable) obj);
    }
}
