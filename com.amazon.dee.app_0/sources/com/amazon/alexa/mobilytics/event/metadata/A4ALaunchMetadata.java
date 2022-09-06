package com.amazon.alexa.mobilytics.event.metadata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
/* loaded from: classes9.dex */
public class A4ALaunchMetadata implements DefaultEventMetadata {
    private final String metadataType = EventMetadataType.A4ALAUNCH;
    private String outcome;
    private String reasons;
    private String target;
    private String token;

    @Override // com.amazon.alexa.mobilytics.event.metadata.EventMetadata
    public String getMetadataType() {
        return EventMetadataType.A4ALAUNCH;
    }

    @Nullable
    public String getOutcome() {
        return this.outcome;
    }

    @Nullable
    public String getReasons() {
        return this.reasons;
    }

    @Nullable
    public String getTarget() {
        return this.target;
    }

    @Nullable
    public String getToken() {
        return this.token;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.DefaultEventMetadata
    @NonNull
    public EventDetailsProto.Metadata serialize() {
        EventDetailsProto.Metadata.Builder newBuilder = EventDetailsProto.Metadata.newBuilder();
        EventDetailsProto.Metadata.A4aLaunch.Builder newBuilder2 = EventDetailsProto.Metadata.A4aLaunch.newBuilder();
        String str = this.outcome;
        if (str != null) {
            newBuilder2.setOutcome(str);
        }
        String str2 = this.target;
        if (str2 != null) {
            newBuilder2.setTarget(str2);
        }
        String str3 = this.reasons;
        if (str3 != null) {
            newBuilder2.setReasons(str3);
        }
        String str4 = this.token;
        if (str4 != null) {
            newBuilder2.setToken(str4);
        }
        newBuilder.setA4ALaunch(newBuilder2);
        return newBuilder.mo10084build();
    }

    public A4ALaunchMetadata withOutcome(@Nullable String str) {
        this.outcome = str;
        return this;
    }

    public A4ALaunchMetadata withReasons(@Nullable String str) {
        this.reasons = str;
        return this;
    }

    public A4ALaunchMetadata withTarget(@Nullable String str) {
        this.target = str;
        return this;
    }

    public A4ALaunchMetadata withToken(@Nullable String str) {
        this.token = str;
        return this;
    }
}
