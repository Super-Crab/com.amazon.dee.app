package com.amazon.alexa.mobilytics.event.metadata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
/* loaded from: classes9.dex */
public class DreamMetadata implements DefaultEventMetadata {
    private final String dreamError;
    private final String dreamNumber;
    private final String dreamUuid;
    private final String metadataType = EventMetadataType.DREAM;

    public DreamMetadata(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.dreamUuid = str;
        this.dreamNumber = str2;
        this.dreamError = str3;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.EventMetadata
    public String getMetadataType() {
        return EventMetadataType.DREAM;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.DefaultEventMetadata
    @NonNull
    public EventDetailsProto.Metadata serialize() {
        EventDetailsProto.Metadata.Builder newBuilder = EventDetailsProto.Metadata.newBuilder();
        EventDetailsProto.Metadata.Dream.Builder newBuilder2 = EventDetailsProto.Metadata.Dream.newBuilder();
        String str = this.dreamUuid;
        if (str != null) {
            newBuilder2.setDreamUuid(str);
        }
        String str2 = this.dreamError;
        if (str2 != null) {
            newBuilder2.setDreamError(str2);
        }
        String str3 = this.dreamNumber;
        if (str3 != null) {
            newBuilder2.setDreamNumber(str3);
        }
        newBuilder.setDream(newBuilder2);
        return newBuilder.mo10084build();
    }
}
