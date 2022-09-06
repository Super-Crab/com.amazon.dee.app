package com.amazon.alexa.voice.tta.statemachine.scxml;

import com.amazon.alexa.api.compat.TextResponse;
import com.amazon.alexa.mobilytics.event.userinteraction.InteractionType;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SIMBACallContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/scxml/SIMBACallContext;", "", "avsTextResponse", "Lcom/amazon/alexa/api/compat/TextResponse;", InteractionType.UTTERANCE, "", "(Lcom/amazon/alexa/api/compat/TextResponse;Ljava/lang/String;)V", "getAvsTextResponse", "()Lcom/amazon/alexa/api/compat/TextResponse;", "getUtterance", "()Ljava/lang/String;", "uuid", "getUuid", "equals", "", "other", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SIMBACallContext {
    @NotNull
    private final TextResponse avsTextResponse;
    @NotNull
    private final String utterance;
    @NotNull
    private final String uuid;

    public SIMBACallContext(@NotNull TextResponse avsTextResponse, @NotNull String utterance) {
        Intrinsics.checkParameterIsNotNull(avsTextResponse, "avsTextResponse");
        Intrinsics.checkParameterIsNotNull(utterance, "utterance");
        this.avsTextResponse = avsTextResponse;
        this.utterance = utterance;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.uuid = uuid;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof SIMBACallContext)) {
            obj = null;
        }
        SIMBACallContext sIMBACallContext = (SIMBACallContext) obj;
        if (sIMBACallContext != null) {
            return Intrinsics.areEqual(this.uuid, sIMBACallContext.uuid);
        }
        return false;
    }

    @NotNull
    public final TextResponse getAvsTextResponse() {
        return this.avsTextResponse;
    }

    @NotNull
    public final String getUtterance() {
        return this.utterance;
    }

    @NotNull
    public final String getUuid() {
        return this.uuid;
    }
}
