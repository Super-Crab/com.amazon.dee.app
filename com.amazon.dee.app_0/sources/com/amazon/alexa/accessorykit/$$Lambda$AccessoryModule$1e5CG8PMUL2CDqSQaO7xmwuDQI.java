package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$1e5CG8PMUL2CDqSQaO7x-mwuDQI  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryModule$1e5CG8PMUL2CDqSQaO7xmwuDQI implements Function {
    public static final /* synthetic */ $$Lambda$AccessoryModule$1e5CG8PMUL2CDqSQaO7xmwuDQI INSTANCE = new $$Lambda$AccessoryModule$1e5CG8PMUL2CDqSQaO7xmwuDQI();

    private /* synthetic */ $$Lambda$AccessoryModule$1e5CG8PMUL2CDqSQaO7xmwuDQI() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Logger.e("%s: Error uploading application logs. Continuing...", (Throwable) obj, AccessoryModule.TAG);
    }
}
