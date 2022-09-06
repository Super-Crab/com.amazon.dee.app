package com.amazon.deecomms.accessories;

import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.accessories.-$$Lambda$AccessoryUtilities$z8ZeuErXB172IvC0TgG2yQq56IA  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$AccessoryUtilities$z8ZeuErXB172IvC0TgG2yQq56IA implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryUtilities$z8ZeuErXB172IvC0TgG2yQq56IA INSTANCE = new $$Lambda$AccessoryUtilities$z8ZeuErXB172IvC0TgG2yQq56IA();

    private /* synthetic */ $$Lambda$AccessoryUtilities$z8ZeuErXB172IvC0TgG2yQq56IA() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        AccessoryUtilities.LOG.e("CRITICAL ERROR", (Throwable) obj);
    }
}
