package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$-Hh_8OGYtZ5NT8lmdmqZtgaym1s  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryModule$Hh_8OGYtZ5NT8lmdmqZtgaym1s implements Function {
    public static final /* synthetic */ $$Lambda$AccessoryModule$Hh_8OGYtZ5NT8lmdmqZtgaym1s INSTANCE = new $$Lambda$AccessoryModule$Hh_8OGYtZ5NT8lmdmqZtgaym1s();

    private /* synthetic */ $$Lambda$AccessoryModule$Hh_8OGYtZ5NT8lmdmqZtgaym1s() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Logger.e("%s: Error uploading accessory logs. Continuing...", (Throwable) obj, AccessoryModule.TAG);
    }
}
