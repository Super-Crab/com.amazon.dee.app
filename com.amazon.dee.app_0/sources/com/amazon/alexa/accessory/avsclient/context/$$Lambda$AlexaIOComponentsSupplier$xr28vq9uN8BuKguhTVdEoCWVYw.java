package com.amazon.alexa.accessory.avsclient.context;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$xr28vq-9uN8BuKguhTVdEoCWVYw  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaIOComponentsSupplier$xr28vq9uN8BuKguhTVdEoCWVYw implements Consumer {
    public static final /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$xr28vq9uN8BuKguhTVdEoCWVYw INSTANCE = new $$Lambda$AlexaIOComponentsSupplier$xr28vq9uN8BuKguhTVdEoCWVYw();

    private /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$xr28vq9uN8BuKguhTVdEoCWVYw() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Failed to generate and cache IOComponents", (Throwable) obj);
    }
}
