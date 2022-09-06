package com.amazon.alexa.accessory.speech.display;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.speech.display.-$$Lambda$DisplayCardListener$JMHb_0vmeHFerkqMfWSgEUwhlKc  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$DisplayCardListener$JMHb_0vmeHFerkqMfWSgEUwhlKc implements Consumer {
    public static final /* synthetic */ $$Lambda$DisplayCardListener$JMHb_0vmeHFerkqMfWSgEUwhlKc INSTANCE = new $$Lambda$DisplayCardListener$JMHb_0vmeHFerkqMfWSgEUwhlKc();

    private /* synthetic */ $$Lambda$DisplayCardListener$JMHb_0vmeHFerkqMfWSgEUwhlKc() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s: Fail to set display content by chunks.", (Throwable) obj, DisplayCardListener.TAG);
    }
}
