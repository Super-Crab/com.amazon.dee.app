package com.amazon.alexa.accessory.avsclient;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.-$$Lambda$AlexaScoSessionCache$A0x_Tu2I0UiGOExit3gayAMdt94  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaScoSessionCache$A0x_Tu2I0UiGOExit3gayAMdt94 implements Consumer {
    public static final /* synthetic */ $$Lambda$AlexaScoSessionCache$A0x_Tu2I0UiGOExit3gayAMdt94 INSTANCE = new $$Lambda$AlexaScoSessionCache$A0x_Tu2I0UiGOExit3gayAMdt94();

    private /* synthetic */ $$Lambda$AlexaScoSessionCache$A0x_Tu2I0UiGOExit3gayAMdt94() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("AlexaScoSessionCache: failed to cache all SCO preferences.", (Throwable) obj);
    }
}
