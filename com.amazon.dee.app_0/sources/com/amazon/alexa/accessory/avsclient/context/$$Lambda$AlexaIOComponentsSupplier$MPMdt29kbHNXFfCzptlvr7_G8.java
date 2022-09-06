package com.amazon.alexa.accessory.avsclient.context;

import com.amazon.alexa.accessory.User;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$-MP-Mdt29kbHNXFfCzptlvr7_G8  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaIOComponentsSupplier$MPMdt29kbHNXFfCzptlvr7_G8 implements Predicate {
    public static final /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$MPMdt29kbHNXFfCzptlvr7_G8 INSTANCE = new $$Lambda$AlexaIOComponentsSupplier$MPMdt29kbHNXFfCzptlvr7_G8();

    private /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$MPMdt29kbHNXFfCzptlvr7_G8() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return AlexaIOComponentsSupplier.lambda$observeUserChanges$9((User) obj);
    }
}
