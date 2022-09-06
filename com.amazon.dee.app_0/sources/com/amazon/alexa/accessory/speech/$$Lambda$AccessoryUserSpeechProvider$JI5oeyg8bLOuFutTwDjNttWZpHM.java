package com.amazon.alexa.accessory.speech;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryUserSpeechProvider$JI5oeyg8bLOuFutTwDjNttWZpHM  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryUserSpeechProvider$JI5oeyg8bLOuFutTwDjNttWZpHM implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryUserSpeechProvider$JI5oeyg8bLOuFutTwDjNttWZpHM INSTANCE = new $$Lambda$AccessoryUserSpeechProvider$JI5oeyg8bLOuFutTwDjNttWZpHM();

    private /* synthetic */ $$Lambda$AccessoryUserSpeechProvider$JI5oeyg8bLOuFutTwDjNttWZpHM() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s Failed to query device features", (Throwable) obj, AccessoryUserSpeechProvider.TAG);
    }
}
