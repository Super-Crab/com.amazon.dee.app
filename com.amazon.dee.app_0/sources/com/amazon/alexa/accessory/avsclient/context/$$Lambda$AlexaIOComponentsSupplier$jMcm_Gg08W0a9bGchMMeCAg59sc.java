package com.amazon.alexa.accessory.avsclient.context;

import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$jMcm_Gg08W0a9bGchMMeCAg59sc  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaIOComponentsSupplier$jMcm_Gg08W0a9bGchMMeCAg59sc implements Consumer {
    public static final /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$jMcm_Gg08W0a9bGchMMeCAg59sc INSTANCE = new $$Lambda$AlexaIOComponentsSupplier$jMcm_Gg08W0a9bGchMMeCAg59sc();

    private /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$jMcm_Gg08W0a9bGchMMeCAg59sc() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        User user = (User) obj;
        Logger.d("AlexaIOComponentSupplier: observed user change, IOComponents combinations cached.");
    }
}
