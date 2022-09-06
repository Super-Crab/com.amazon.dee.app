package com.amazon.alexa.accessory.speechapi.events;

import com.amazon.alexa.accessory.speechapi.context.MessageHeader;
import com.amazon.alexa.accessory.speechapi.context.MessagePayload;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MessageEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/events/MessageEvent;", "", "messageHeader", "Lcom/amazon/alexa/accessory/speechapi/context/MessageHeader;", "messagePayload", "Lcom/amazon/alexa/accessory/speechapi/context/MessagePayload;", "(Lcom/amazon/alexa/accessory/speechapi/context/MessageHeader;Lcom/amazon/alexa/accessory/speechapi/context/MessagePayload;)V", "getMessageHeader", "()Lcom/amazon/alexa/accessory/speechapi/context/MessageHeader;", "getMessagePayload", "()Lcom/amazon/alexa/accessory/speechapi/context/MessagePayload;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MessageEvent {
    @NotNull
    private final MessageHeader messageHeader;
    @NotNull
    private final MessagePayload messagePayload;

    public MessageEvent(@NotNull MessageHeader messageHeader, @NotNull MessagePayload messagePayload) {
        Intrinsics.checkParameterIsNotNull(messageHeader, "messageHeader");
        Intrinsics.checkParameterIsNotNull(messagePayload, "messagePayload");
        this.messageHeader = messageHeader;
        this.messagePayload = messagePayload;
    }

    public static /* synthetic */ MessageEvent copy$default(MessageEvent messageEvent, MessageHeader messageHeader, MessagePayload messagePayload, int i, Object obj) {
        if ((i & 1) != 0) {
            messageHeader = messageEvent.messageHeader;
        }
        if ((i & 2) != 0) {
            messagePayload = messageEvent.messagePayload;
        }
        return messageEvent.copy(messageHeader, messagePayload);
    }

    @NotNull
    public final MessageHeader component1() {
        return this.messageHeader;
    }

    @NotNull
    public final MessagePayload component2() {
        return this.messagePayload;
    }

    @NotNull
    public final MessageEvent copy(@NotNull MessageHeader messageHeader, @NotNull MessagePayload messagePayload) {
        Intrinsics.checkParameterIsNotNull(messageHeader, "messageHeader");
        Intrinsics.checkParameterIsNotNull(messagePayload, "messagePayload");
        return new MessageEvent(messageHeader, messagePayload);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof MessageEvent)) {
                return false;
            }
            MessageEvent messageEvent = (MessageEvent) obj;
            return Intrinsics.areEqual(this.messageHeader, messageEvent.messageHeader) && Intrinsics.areEqual(this.messagePayload, messageEvent.messagePayload);
        }
        return true;
    }

    @NotNull
    public final MessageHeader getMessageHeader() {
        return this.messageHeader;
    }

    @NotNull
    public final MessagePayload getMessagePayload() {
        return this.messagePayload;
    }

    public int hashCode() {
        MessageHeader messageHeader = this.messageHeader;
        int i = 0;
        int hashCode = (messageHeader != null ? messageHeader.hashCode() : 0) * 31;
        MessagePayload messagePayload = this.messagePayload;
        if (messagePayload != null) {
            i = messagePayload.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MessageEvent(messageHeader=");
        outline107.append(this.messageHeader);
        outline107.append(", messagePayload=");
        outline107.append(this.messagePayload);
        outline107.append(")");
        return outline107.toString();
    }
}
