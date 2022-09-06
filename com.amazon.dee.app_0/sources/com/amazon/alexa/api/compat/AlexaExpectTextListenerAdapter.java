package com.amazon.alexa.api.compat;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlexaExpectTextListenerAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaExpectTextListenerAdapter;", "Lcom/amazon/alexa/api/AlexaExpectTextListener;", "compatListener", "Lcom/amazon/alexa/api/compat/AlexaExpectTextListener;", "(Lcom/amazon/alexa/api/compat/AlexaExpectTextListener;)V", "getCompatListener", "()Lcom/amazon/alexa/api/compat/AlexaExpectTextListener;", "onExpectTextReceived", "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaExpectTextListenerAdapter implements com.amazon.alexa.api.AlexaExpectTextListener {
    @NotNull
    private final AlexaExpectTextListener compatListener;

    public AlexaExpectTextListenerAdapter(@NotNull AlexaExpectTextListener compatListener) {
        Intrinsics.checkParameterIsNotNull(compatListener, "compatListener");
        this.compatListener = compatListener;
    }

    @NotNull
    public final AlexaExpectTextListener getCompatListener() {
        return this.compatListener;
    }

    @Override // com.amazon.alexa.api.AlexaExpectTextListener
    public void onExpectTextReceived() {
        this.compatListener.onExpectTextReceived();
    }
}
