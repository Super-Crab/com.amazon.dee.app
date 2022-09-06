package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$ujSAaut1tFhkY7dUiR77Wou0fac  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ConnectivityInteractor$ujSAaut1tFhkY7dUiR77Wou0fac implements Consumer {
    public static final /* synthetic */ $$Lambda$ConnectivityInteractor$ujSAaut1tFhkY7dUiR77Wou0fac INSTANCE = new $$Lambda$ConnectivityInteractor$ujSAaut1tFhkY7dUiR77Wou0fac();

    private /* synthetic */ $$Lambda$ConnectivityInteractor$ujSAaut1tFhkY7dUiR77Wou0fac() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Failed to activate known accessories", (Throwable) obj);
    }
}
