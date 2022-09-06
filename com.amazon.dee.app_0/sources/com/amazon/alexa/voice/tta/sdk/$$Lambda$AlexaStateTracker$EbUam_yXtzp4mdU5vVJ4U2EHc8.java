package com.amazon.alexa.voice.tta.sdk;

import com.amazon.alexa.api.compat.AlexaState;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.tta.sdk.-$$Lambda$AlexaStateTracker$EbUam_yXtzp4mdU5vVJ4U2-EHc8  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$AlexaStateTracker$EbUam_yXtzp4mdU5vVJ4U2EHc8 implements Function {
    public static final /* synthetic */ $$Lambda$AlexaStateTracker$EbUam_yXtzp4mdU5vVJ4U2EHc8 INSTANCE = new $$Lambda$AlexaStateTracker$EbUam_yXtzp4mdU5vVJ4U2EHc8();

    private /* synthetic */ $$Lambda$AlexaStateTracker$EbUam_yXtzp4mdU5vVJ4U2EHc8() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        Boolean valueOf;
        AlexaState alexaState = (AlexaState) obj;
        valueOf = Boolean.valueOf(AlexaState.THINKING == r1);
        return valueOf;
    }
}
