package com.amazon.alexa.accessory.persistence;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.persistence.-$$Lambda$FileBackedJsonRxMapStore$C20uNeLthl0tL7f8zc902cc496g  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$FileBackedJsonRxMapStore$C20uNeLthl0tL7f8zc902cc496g implements Consumer {
    public static final /* synthetic */ $$Lambda$FileBackedJsonRxMapStore$C20uNeLthl0tL7f8zc902cc496g INSTANCE = new $$Lambda$FileBackedJsonRxMapStore$C20uNeLthl0tL7f8zc902cc496g();

    private /* synthetic */ $$Lambda$FileBackedJsonRxMapStore$C20uNeLthl0tL7f8zc902cc496g() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s Exception clearing cache: ", (Throwable) obj, FileBackedJsonRxMapStore.TAG);
    }
}
