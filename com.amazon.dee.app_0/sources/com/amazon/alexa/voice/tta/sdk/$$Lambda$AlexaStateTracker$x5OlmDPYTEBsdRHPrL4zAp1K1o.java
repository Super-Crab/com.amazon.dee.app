package com.amazon.alexa.voice.tta.sdk;

import com.amazon.alexa.api.compat.AlexaState;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.tta.sdk.-$$Lambda$AlexaStateTracker$x5OlmDPYTEBsdRHPrL-4zAp1K1o  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$AlexaStateTracker$x5OlmDPYTEBsdRHPrL4zAp1K1o implements Function {
    public static final /* synthetic */ $$Lambda$AlexaStateTracker$x5OlmDPYTEBsdRHPrL4zAp1K1o INSTANCE = new $$Lambda$AlexaStateTracker$x5OlmDPYTEBsdRHPrL4zAp1K1o();

    private /* synthetic */ $$Lambda$AlexaStateTracker$x5OlmDPYTEBsdRHPrL4zAp1K1o() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        Boolean valueOf;
        AlexaState alexaState = (AlexaState) obj;
        valueOf = Boolean.valueOf(AlexaState.LISTENING == r1);
        return valueOf;
    }
}
