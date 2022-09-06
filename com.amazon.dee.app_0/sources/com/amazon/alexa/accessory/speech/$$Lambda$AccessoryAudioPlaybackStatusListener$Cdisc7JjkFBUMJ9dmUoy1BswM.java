package com.amazon.alexa.accessory.speech;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryAudioPlaybackStatusListener$Cdisc7JjkFBUMJ-9dmUo-y1BswM  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryAudioPlaybackStatusListener$Cdisc7JjkFBUMJ9dmUoy1BswM implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryAudioPlaybackStatusListener$Cdisc7JjkFBUMJ9dmUoy1BswM INSTANCE = new $$Lambda$AccessoryAudioPlaybackStatusListener$Cdisc7JjkFBUMJ9dmUoy1BswM();

    private /* synthetic */ $$Lambda$AccessoryAudioPlaybackStatusListener$Cdisc7JjkFBUMJ9dmUoy1BswM() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s failed to dispatch AudioPlaybackStatus to session", (Throwable) obj, AccessoryAudioPlaybackStatusListener.TAG);
    }
}
