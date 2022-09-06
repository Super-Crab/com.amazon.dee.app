package com.amazon.alexa.voice.sdk;

import com.amazon.alexa.api.compat.AlexaState;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.sdk.-$$Lambda$AlexaStateTracker$nWKgogo-kCFfEe7Q9uz41gsoJZI  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$AlexaStateTracker$nWKgogokCFfEe7Q9uz41gsoJZI implements Function {
    public static final /* synthetic */ $$Lambda$AlexaStateTracker$nWKgogokCFfEe7Q9uz41gsoJZI INSTANCE = new $$Lambda$AlexaStateTracker$nWKgogokCFfEe7Q9uz41gsoJZI();

    private /* synthetic */ $$Lambda$AlexaStateTracker$nWKgogokCFfEe7Q9uz41gsoJZI() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        Boolean valueOf;
        AlexaState alexaState = (AlexaState) obj;
        valueOf = Boolean.valueOf(AlexaState.SPEAKING == r1);
        return valueOf;
    }
}
