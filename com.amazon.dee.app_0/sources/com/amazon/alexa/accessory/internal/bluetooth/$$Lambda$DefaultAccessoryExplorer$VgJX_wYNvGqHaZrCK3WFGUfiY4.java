package com.amazon.alexa.accessory.internal.bluetooth;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$VgJX_wYNvGqHa-ZrCK3WFGUfiY4  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DefaultAccessoryExplorer$VgJX_wYNvGqHaZrCK3WFGUfiY4 implements Consumer {
    public static final /* synthetic */ $$Lambda$DefaultAccessoryExplorer$VgJX_wYNvGqHaZrCK3WFGUfiY4 INSTANCE = new $$Lambda$DefaultAccessoryExplorer$VgJX_wYNvGqHaZrCK3WFGUfiY4();

    private /* synthetic */ $$Lambda$DefaultAccessoryExplorer$VgJX_wYNvGqHaZrCK3WFGUfiY4() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("DefaultAccessoryExplorer: Unable to observe LE devices.", (Throwable) obj);
    }
}
