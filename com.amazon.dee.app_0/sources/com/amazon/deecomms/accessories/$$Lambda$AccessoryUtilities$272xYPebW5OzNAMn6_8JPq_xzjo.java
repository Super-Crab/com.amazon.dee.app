package com.amazon.deecomms.accessories;

import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.accessories.-$$Lambda$AccessoryUtilities$272xYPebW5OzNAMn6_8JPq_xzjo  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$AccessoryUtilities$272xYPebW5OzNAMn6_8JPq_xzjo implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryUtilities$272xYPebW5OzNAMn6_8JPq_xzjo INSTANCE = new $$Lambda$AccessoryUtilities$272xYPebW5OzNAMn6_8JPq_xzjo();

    private /* synthetic */ $$Lambda$AccessoryUtilities$272xYPebW5OzNAMn6_8JPq_xzjo() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        AccessoryUtilities.LOG.e("CRITICAL ERROR", (Throwable) obj);
    }
}
