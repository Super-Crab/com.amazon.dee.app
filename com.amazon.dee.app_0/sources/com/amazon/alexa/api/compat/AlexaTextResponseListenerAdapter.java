package com.amazon.alexa.api.compat;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaTextResponseListenerAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaTextResponseListenerAdapter;", "Lcom/amazon/alexa/api/AlexaTextResponseListener;", "compatListener", "Lcom/amazon/alexa/api/compat/AlexaTextResponseListener;", "(Lcom/amazon/alexa/api/compat/AlexaTextResponseListener;)V", "getCompatListener", "()Lcom/amazon/alexa/api/compat/AlexaTextResponseListener;", "onTextReceived", "", "textResponse", "Lcom/amazon/alexa/api/TextResponse;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaTextResponseListenerAdapter implements com.amazon.alexa.api.AlexaTextResponseListener {
    @NotNull
    private final AlexaTextResponseListener compatListener;

    public AlexaTextResponseListenerAdapter(@NotNull AlexaTextResponseListener compatListener) {
        Intrinsics.checkParameterIsNotNull(compatListener, "compatListener");
        this.compatListener = compatListener;
    }

    @NotNull
    public final AlexaTextResponseListener getCompatListener() {
        return this.compatListener;
    }

    @Override // com.amazon.alexa.api.AlexaTextResponseListener
    public void onTextReceived(@Nullable com.amazon.alexa.api.TextResponse textResponse) {
        if (textResponse != null) {
            AlexaTextResponseListener alexaTextResponseListener = this.compatListener;
            String title = textResponse.getTitle();
            Intrinsics.checkExpressionValueIsNotNull(title, "it.title");
            alexaTextResponseListener.onTextReceived(new TextResponse(title, textResponse.getDescription(), textResponse.getUrl(), textResponse.getMetadata()));
        }
    }
}
