package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$RegistrationInteractor$oRuSODiW-YbzCPKnzLHWfL-DVkc  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$RegistrationInteractor$oRuSODiWYbzCPKnzLHWfLDVkc implements Consumer {
    public static final /* synthetic */ $$Lambda$RegistrationInteractor$oRuSODiWYbzCPKnzLHWfLDVkc INSTANCE = new $$Lambda$RegistrationInteractor$oRuSODiWYbzCPKnzLHWfLDVkc();

    private /* synthetic */ $$Lambda$RegistrationInteractor$oRuSODiWYbzCPKnzLHWfLDVkc() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Critical: RegistrationInteractor: Observe devices caught exception:", (Throwable) obj);
    }
}
