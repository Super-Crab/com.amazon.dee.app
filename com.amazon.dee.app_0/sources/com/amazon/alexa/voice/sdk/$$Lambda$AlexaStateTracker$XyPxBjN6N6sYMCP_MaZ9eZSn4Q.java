package com.amazon.alexa.voice.sdk;

import com.amazon.alexa.api.compat.AlexaState;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.sdk.-$$Lambda$AlexaStateTracker$X-yPxBjN6N6sYMCP_MaZ9eZSn4Q  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$AlexaStateTracker$XyPxBjN6N6sYMCP_MaZ9eZSn4Q implements Function {
    public static final /* synthetic */ $$Lambda$AlexaStateTracker$XyPxBjN6N6sYMCP_MaZ9eZSn4Q INSTANCE = new $$Lambda$AlexaStateTracker$XyPxBjN6N6sYMCP_MaZ9eZSn4Q();

    private /* synthetic */ $$Lambda$AlexaStateTracker$XyPxBjN6N6sYMCP_MaZ9eZSn4Q() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        boolean isNotRunning;
        isNotRunning = AlexaStateTracker.isNotRunning((AlexaState) obj);
        return Boolean.valueOf(isNotRunning);
    }
}
