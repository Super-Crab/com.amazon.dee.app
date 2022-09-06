package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$RegistrationInteractor$-xoK3xsz50gUBlsSCMd-prl-y9I  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$RegistrationInteractor$xoK3xsz50gUBlsSCMdprly9I implements Function {
    public static final /* synthetic */ $$Lambda$RegistrationInteractor$xoK3xsz50gUBlsSCMdprly9I INSTANCE = new $$Lambda$RegistrationInteractor$xoK3xsz50gUBlsSCMdprly9I();

    private /* synthetic */ $$Lambda$RegistrationInteractor$xoK3xsz50gUBlsSCMdprly9I() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Logger.d("RegistrationInteractor: Register sessions swallowed exception: ", (Throwable) obj);
    }
}
