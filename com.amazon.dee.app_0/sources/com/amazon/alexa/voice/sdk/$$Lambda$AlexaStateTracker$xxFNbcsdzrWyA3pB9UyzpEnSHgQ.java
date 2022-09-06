package com.amazon.alexa.voice.sdk;

import com.amazon.alexa.api.compat.AlexaState;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.sdk.-$$Lambda$AlexaStateTracker$xxFNbcsdzrWyA3pB9UyzpEnSHgQ  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$AlexaStateTracker$xxFNbcsdzrWyA3pB9UyzpEnSHgQ implements Function {
    public static final /* synthetic */ $$Lambda$AlexaStateTracker$xxFNbcsdzrWyA3pB9UyzpEnSHgQ INSTANCE = new $$Lambda$AlexaStateTracker$xxFNbcsdzrWyA3pB9UyzpEnSHgQ();

    private /* synthetic */ $$Lambda$AlexaStateTracker$xxFNbcsdzrWyA3pB9UyzpEnSHgQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        boolean isListening;
        isListening = AlexaStateTracker.isListening((AlexaState) obj);
        return Boolean.valueOf(isListening);
    }
}
