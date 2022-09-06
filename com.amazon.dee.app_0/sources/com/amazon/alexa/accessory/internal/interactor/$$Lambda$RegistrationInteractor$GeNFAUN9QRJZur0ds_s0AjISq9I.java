package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$RegistrationInteractor$GeNFAUN9QRJZur0ds_s0AjISq9I  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$RegistrationInteractor$GeNFAUN9QRJZur0ds_s0AjISq9I implements Consumer {
    public static final /* synthetic */ $$Lambda$RegistrationInteractor$GeNFAUN9QRJZur0ds_s0AjISq9I INSTANCE = new $$Lambda$RegistrationInteractor$GeNFAUN9QRJZur0ds_s0AjISq9I();

    private /* synthetic */ $$Lambda$RegistrationInteractor$GeNFAUN9QRJZur0ds_s0AjISq9I() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        List list = (List) obj;
        Logger.d("RegistrationInteractor: Device groups observed");
    }
}
