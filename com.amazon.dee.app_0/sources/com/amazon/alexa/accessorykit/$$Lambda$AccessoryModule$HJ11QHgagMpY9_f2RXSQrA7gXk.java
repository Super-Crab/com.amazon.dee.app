package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$HJ11QHgagMpY9_f2RXSQrA7gX-k  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryModule$HJ11QHgagMpY9_f2RXSQrA7gXk implements Function {
    public static final /* synthetic */ $$Lambda$AccessoryModule$HJ11QHgagMpY9_f2RXSQrA7gXk INSTANCE = new $$Lambda$AccessoryModule$HJ11QHgagMpY9_f2RXSQrA7gXk();

    private /* synthetic */ $$Lambda$AccessoryModule$HJ11QHgagMpY9_f2RXSQrA7gXk() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Logger.e("%s: Error uploading crash reports. Continuing...", (Throwable) obj, AccessoryModule.TAG);
    }
}
