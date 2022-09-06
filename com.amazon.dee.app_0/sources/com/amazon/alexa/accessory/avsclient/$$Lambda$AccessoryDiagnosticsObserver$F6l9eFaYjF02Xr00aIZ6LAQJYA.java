package com.amazon.alexa.accessory.avsclient;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.-$$Lambda$AccessoryDiagnosticsObserver$F6l9eFaYjF02Xr00-aIZ6LAQJYA  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AccessoryDiagnosticsObserver$F6l9eFaYjF02Xr00aIZ6LAQJYA implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryDiagnosticsObserver$F6l9eFaYjF02Xr00aIZ6LAQJYA INSTANCE = new $$Lambda$AccessoryDiagnosticsObserver$F6l9eFaYjF02Xr00aIZ6LAQJYA();

    private /* synthetic */ $$Lambda$AccessoryDiagnosticsObserver$F6l9eFaYjF02Xr00aIZ6LAQJYA() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Error uploading accessory diagnostics", (Throwable) obj);
    }
}
