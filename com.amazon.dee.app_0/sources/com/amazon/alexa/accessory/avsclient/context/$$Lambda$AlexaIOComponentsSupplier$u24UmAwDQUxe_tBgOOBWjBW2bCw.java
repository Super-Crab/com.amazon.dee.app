package com.amazon.alexa.accessory.avsclient.context;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$u24UmAwDQUxe_tBgOOBWjBW2bCw  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaIOComponentsSupplier$u24UmAwDQUxe_tBgOOBWjBW2bCw implements Consumer {
    public static final /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$u24UmAwDQUxe_tBgOOBWjBW2bCw INSTANCE = new $$Lambda$AlexaIOComponentsSupplier$u24UmAwDQUxe_tBgOOBWjBW2bCw();

    private /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$u24UmAwDQUxe_tBgOOBWjBW2bCw() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("AlexaIOComponentSupplier: Critical: Error caught after registration change observed, attempted to cache IOComponents combinations.", (Throwable) obj);
    }
}
