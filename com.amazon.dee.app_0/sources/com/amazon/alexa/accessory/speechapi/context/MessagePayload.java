package com.amazon.alexa.accessory.speechapi.context;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MessagePayload.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/context/MessagePayload;", "", "payload", "", "(Ljava/lang/String;)V", "getPayload", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MessagePayload {
    @NotNull
    private final String payload;

    public MessagePayload(@NotNull String payload) {
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        this.payload = payload;
    }

    public static /* synthetic */ MessagePayload copy$default(MessagePayload messagePayload, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = messagePayload.payload;
        }
        return messagePayload.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.payload;
    }

    @NotNull
    public final MessagePayload copy(@NotNull String payload) {
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        return new MessagePayload(payload);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof MessagePayload) && Intrinsics.areEqual(this.payload, ((MessagePayload) obj).payload);
        }
        return true;
    }

    @NotNull
    public final String getPayload() {
        return this.payload;
    }

    public int hashCode() {
        String str = this.payload;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("MessagePayload(payload="), this.payload, ")");
    }
}
