package com.amazon.alexa.voice.sdk;

import com.amazon.alexa.api.compat.AlexaState;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.sdk.-$$Lambda$AlexaStateTracker$RX4cH7DkFpmsrpINFKt2wGPznPM  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$AlexaStateTracker$RX4cH7DkFpmsrpINFKt2wGPznPM implements Function {
    public static final /* synthetic */ $$Lambda$AlexaStateTracker$RX4cH7DkFpmsrpINFKt2wGPznPM INSTANCE = new $$Lambda$AlexaStateTracker$RX4cH7DkFpmsrpINFKt2wGPznPM();

    private /* synthetic */ $$Lambda$AlexaStateTracker$RX4cH7DkFpmsrpINFKt2wGPznPM() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        boolean isListeningOrProcessing;
        isListeningOrProcessing = AlexaStateTracker.isListeningOrProcessing((AlexaState) obj);
        return Boolean.valueOf(isListeningOrProcessing);
    }
}
