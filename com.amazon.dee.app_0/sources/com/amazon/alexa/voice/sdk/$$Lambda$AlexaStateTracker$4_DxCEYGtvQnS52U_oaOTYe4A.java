package com.amazon.alexa.voice.sdk;

import com.amazon.alexa.api.compat.AlexaState;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.sdk.-$$Lambda$AlexaStateTracker$4_DxCEYGtvQnS52U_o-aOTY-e4A  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$AlexaStateTracker$4_DxCEYGtvQnS52U_oaOTYe4A implements Function {
    public static final /* synthetic */ $$Lambda$AlexaStateTracker$4_DxCEYGtvQnS52U_oaOTYe4A INSTANCE = new $$Lambda$AlexaStateTracker$4_DxCEYGtvQnS52U_oaOTYe4A();

    private /* synthetic */ $$Lambda$AlexaStateTracker$4_DxCEYGtvQnS52U_oaOTYe4A() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        boolean isProcessing;
        isProcessing = AlexaStateTracker.isProcessing((AlexaState) obj);
        return Boolean.valueOf(isProcessing);
    }
}
