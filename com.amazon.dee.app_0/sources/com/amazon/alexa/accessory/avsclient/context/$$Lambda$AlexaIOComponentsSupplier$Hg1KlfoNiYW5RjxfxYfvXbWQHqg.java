package com.amazon.alexa.accessory.avsclient.context;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaIOComponentsSupplier$Hg1KlfoNiYW5RjxfxYfvXbWQHqg  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaIOComponentsSupplier$Hg1KlfoNiYW5RjxfxYfvXbWQHqg implements Consumer {
    public static final /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$Hg1KlfoNiYW5RjxfxYfvXbWQHqg INSTANCE = new $$Lambda$AlexaIOComponentsSupplier$Hg1KlfoNiYW5RjxfxYfvXbWQHqg();

    private /* synthetic */ $$Lambda$AlexaIOComponentsSupplier$Hg1KlfoNiYW5RjxfxYfvXbWQHqg() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("AlexaIOComponentSupplier: Error caught after user change detected, attempted to cache IOComponents combinations.", (Throwable) obj);
    }
}
