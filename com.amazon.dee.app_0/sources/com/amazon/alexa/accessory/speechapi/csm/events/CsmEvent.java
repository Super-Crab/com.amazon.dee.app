package com.amazon.alexa.accessory.speechapi.csm.events;

import amazon.speech.simclient.event.EventMetadata;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CsmEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/events/CsmEvent;", "", "eventMetadata", "Lamazon/speech/simclient/event/EventMetadata;", "payload", "", "(Lamazon/speech/simclient/event/EventMetadata;Ljava/lang/String;)V", "getEventMetadata", "()Lamazon/speech/simclient/event/EventMetadata;", "getPayload", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmEvent {
    @NotNull
    private final EventMetadata eventMetadata;
    @NotNull
    private final String payload;

    public CsmEvent(@NotNull EventMetadata eventMetadata, @NotNull String payload) {
        Intrinsics.checkParameterIsNotNull(eventMetadata, "eventMetadata");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        this.eventMetadata = eventMetadata;
        this.payload = payload;
    }

    public static /* synthetic */ CsmEvent copy$default(CsmEvent csmEvent, EventMetadata eventMetadata, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            eventMetadata = csmEvent.eventMetadata;
        }
        if ((i & 2) != 0) {
            str = csmEvent.payload;
        }
        return csmEvent.copy(eventMetadata, str);
    }

    @NotNull
    public final EventMetadata component1() {
        return this.eventMetadata;
    }

    @NotNull
    public final String component2() {
        return this.payload;
    }

    @NotNull
    public final CsmEvent copy(@NotNull EventMetadata eventMetadata, @NotNull String payload) {
        Intrinsics.checkParameterIsNotNull(eventMetadata, "eventMetadata");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        return new CsmEvent(eventMetadata, payload);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof CsmEvent)) {
                return false;
            }
            CsmEvent csmEvent = (CsmEvent) obj;
            return Intrinsics.areEqual(this.eventMetadata, csmEvent.eventMetadata) && Intrinsics.areEqual(this.payload, csmEvent.payload);
        }
        return true;
    }

    @NotNull
    public final EventMetadata getEventMetadata() {
        return this.eventMetadata;
    }

    @NotNull
    public final String getPayload() {
        return this.payload;
    }

    public int hashCode() {
        EventMetadata eventMetadata = this.eventMetadata;
        int i = 0;
        int hashCode = (eventMetadata != null ? eventMetadata.hashCode() : 0) * 31;
        String str = this.payload;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CsmEvent(eventMetadata=");
        outline107.append(this.eventMetadata);
        outline107.append(", payload=");
        return GeneratedOutlineSupport1.outline91(outline107, this.payload, ")");
    }
}
