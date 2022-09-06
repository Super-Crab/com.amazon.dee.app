package com.amazon.alexa.accessorykit.metrics;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.metrics.-$$Lambda$MtbfReporter$qr5Sq7SL_uAxl-sAhWk7h_59mdA  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$MtbfReporter$qr5Sq7SL_uAxlsAhWk7h_59mdA implements Consumer {
    public static final /* synthetic */ $$Lambda$MtbfReporter$qr5Sq7SL_uAxlsAhWk7h_59mdA INSTANCE = new $$Lambda$MtbfReporter$qr5Sq7SL_uAxlsAhWk7h_59mdA();

    private /* synthetic */ $$Lambda$MtbfReporter$qr5Sq7SL_uAxlsAhWk7h_59mdA() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Caught error while attempting to get device information for mtbf metrics.", (Throwable) obj);
    }
}
