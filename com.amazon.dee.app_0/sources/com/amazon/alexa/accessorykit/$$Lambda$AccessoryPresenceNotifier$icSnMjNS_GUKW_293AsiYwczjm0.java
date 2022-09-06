package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryPresenceNotifier$icSnMjNS_GUKW_293AsiYwczjm0  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryPresenceNotifier$icSnMjNS_GUKW_293AsiYwczjm0 implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryPresenceNotifier$icSnMjNS_GUKW_293AsiYwczjm0 INSTANCE = new $$Lambda$AccessoryPresenceNotifier$icSnMjNS_GUKW_293AsiYwczjm0();

    private /* synthetic */ $$Lambda$AccessoryPresenceNotifier$icSnMjNS_GUKW_293AsiYwczjm0() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%sError in query device groups", (Throwable) obj, AccessoryPresenceNotifier.TAG);
    }
}
