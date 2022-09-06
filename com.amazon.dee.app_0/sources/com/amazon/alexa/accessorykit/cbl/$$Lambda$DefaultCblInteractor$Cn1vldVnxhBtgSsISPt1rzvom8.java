package com.amazon.alexa.accessorykit.cbl;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.cbl.-$$Lambda$DefaultCblInteractor$Cn1vldVnx-hBtgSsISPt1rzvom8  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$DefaultCblInteractor$Cn1vldVnxhBtgSsISPt1rzvom8 implements Consumer {
    public static final /* synthetic */ $$Lambda$DefaultCblInteractor$Cn1vldVnxhBtgSsISPt1rzvom8 INSTANCE = new $$Lambda$DefaultCblInteractor$Cn1vldVnxhBtgSsISPt1rzvom8();

    private /* synthetic */ $$Lambda$DefaultCblInteractor$Cn1vldVnxhBtgSsISPt1rzvom8() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s:: Feature load error", (Throwable) obj, DefaultCblInteractor.TAG);
    }
}
