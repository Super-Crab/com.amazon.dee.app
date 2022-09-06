package com.amazon.alexa.accessory.internal.bluetooth;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryScanner$LowEnergyScanListener$6fvTG8m4vaxwjBlBYsRqmzfg-0g  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DefaultAccessoryScanner$LowEnergyScanListener$6fvTG8m4vaxwjBlBYsRqmzfg0g implements Consumer {
    public static final /* synthetic */ $$Lambda$DefaultAccessoryScanner$LowEnergyScanListener$6fvTG8m4vaxwjBlBYsRqmzfg0g INSTANCE = new $$Lambda$DefaultAccessoryScanner$LowEnergyScanListener$6fvTG8m4vaxwjBlBYsRqmzfg0g();

    private /* synthetic */ $$Lambda$DefaultAccessoryScanner$LowEnergyScanListener$6fvTG8m4vaxwjBlBYsRqmzfg0g() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Accessory scanner failed to filter out known accessories", (Throwable) obj);
    }
}
