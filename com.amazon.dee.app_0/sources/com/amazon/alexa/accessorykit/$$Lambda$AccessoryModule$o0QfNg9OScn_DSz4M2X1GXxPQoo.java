package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$o0QfNg9OScn_DSz4M2X1GXxPQoo  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryModule$o0QfNg9OScn_DSz4M2X1GXxPQoo implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryModule$o0QfNg9OScn_DSz4M2X1GXxPQoo INSTANCE = new $$Lambda$AccessoryModule$o0QfNg9OScn_DSz4M2X1GXxPQoo();

    private /* synthetic */ $$Lambda$AccessoryModule$o0QfNg9OScn_DSz4M2X1GXxPQoo() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("queryAccessoryState failed.", (Throwable) obj);
    }
}
