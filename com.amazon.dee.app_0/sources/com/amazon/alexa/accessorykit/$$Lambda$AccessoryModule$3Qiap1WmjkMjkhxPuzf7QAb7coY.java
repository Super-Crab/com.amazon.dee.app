package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$3Qiap1WmjkMjkhxPuzf7QAb7coY  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryModule$3Qiap1WmjkMjkhxPuzf7QAb7coY implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryModule$3Qiap1WmjkMjkhxPuzf7QAb7coY INSTANCE = new $$Lambda$AccessoryModule$3Qiap1WmjkMjkhxPuzf7QAb7coY();

    private /* synthetic */ $$Lambda$AccessoryModule$3Qiap1WmjkMjkhxPuzf7QAb7coY() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s CRITICAL unexpected exception", (Throwable) obj, AccessoryModule.TAG);
    }
}
