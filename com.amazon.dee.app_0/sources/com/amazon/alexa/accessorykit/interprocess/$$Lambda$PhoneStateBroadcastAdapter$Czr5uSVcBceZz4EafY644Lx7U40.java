package com.amazon.alexa.accessorykit.interprocess;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$PhoneStateBroadcastAdapter$Czr5uSVcBceZz4EafY644Lx7U40  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$PhoneStateBroadcastAdapter$Czr5uSVcBceZz4EafY644Lx7U40 implements Consumer {
    public static final /* synthetic */ $$Lambda$PhoneStateBroadcastAdapter$Czr5uSVcBceZz4EafY644Lx7U40 INSTANCE = new $$Lambda$PhoneStateBroadcastAdapter$Czr5uSVcBceZz4EafY644Lx7U40();

    private /* synthetic */ $$Lambda$PhoneStateBroadcastAdapter$Czr5uSVcBceZz4EafY644Lx7U40() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s error received in emitStates", (Throwable) obj, PhoneStateBroadcastAdapter.TAG);
    }
}
