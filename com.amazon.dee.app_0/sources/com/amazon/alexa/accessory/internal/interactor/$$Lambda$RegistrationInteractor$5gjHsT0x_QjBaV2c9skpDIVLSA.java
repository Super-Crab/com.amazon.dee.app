package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$RegistrationInteractor$5gjHsT0x_QjBaV2c9skpDI-VLSA  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$RegistrationInteractor$5gjHsT0x_QjBaV2c9skpDIVLSA implements Consumer {
    public static final /* synthetic */ $$Lambda$RegistrationInteractor$5gjHsT0x_QjBaV2c9skpDIVLSA INSTANCE = new $$Lambda$RegistrationInteractor$5gjHsT0x_QjBaV2c9skpDIVLSA();

    private /* synthetic */ $$Lambda$RegistrationInteractor$5gjHsT0x_QjBaV2c9skpDIVLSA() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        List list = (List) obj;
        Logger.d("RegistrationInteractor: Login observed");
    }
}
