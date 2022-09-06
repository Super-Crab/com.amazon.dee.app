package com.amazon.alexa.mobilytics.event.metadata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
import com.amazon.comms.ringservice.MetricsSession;
/* loaded from: classes9.dex */
public class CommsMetadata implements DefaultEventMetadata {
    private String callType;
    private String direction;
    private Long duration;
    private String mediaType;
    private String messageType;
    private final String metadataType = "comms";
    private String requestId;
    private Long size;
    private Long statusCode;
    private String targetType;
    private String transport;
    private String trickleIceEnabled;

    @Nullable
    @DCMAttributeName("callType")
    public String getCallType() {
        return this.callType;
    }

    @Nullable
    public String getDirection() {
        return this.direction;
    }

    public Long getDuration() {
        return this.duration;
    }

    @Nullable
    public String getMediaType() {
        return this.mediaType;
    }

    public String getMessageType() {
        return this.messageType;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.EventMetadata
    public String getMetadataType() {
        return "comms";
    }

    @Nullable
    public String getRequestId() {
        return this.requestId;
    }

    public Long getSize() {
        return this.size;
    }

    public Long getStatusCode() {
        return this.statusCode;
    }

    @Nullable
    public String getTargetType() {
        return this.targetType;
    }

    @Nullable
    public String getTransport() {
        return this.transport;
    }

    @Nullable
    @DCMAttributeName(MetricsSession.TRICKLE_ICE_PIVOT)
    public String getTrickleIceEnabled() {
        return this.trickleIceEnabled;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.DefaultEventMetadata
    @NonNull
    public EventDetailsProto.Metadata serialize() {
        EventDetailsProto.Metadata.Builder newBuilder = EventDetailsProto.Metadata.newBuilder();
        EventDetailsProto.Metadata.Comms.Builder newBuilder2 = EventDetailsProto.Metadata.Comms.newBuilder();
        newBuilder2.setMetadataType("comms");
        String str = this.callType;
        if (str != null) {
            newBuilder2.setCallType(str);
        }
        String str2 = this.trickleIceEnabled;
        if (str2 != null) {
            newBuilder2.setTrickleIceEnabled(str2);
        }
        String str3 = this.direction;
        if (str3 != null) {
            newBuilder2.setDirection(str3);
        }
        String str4 = this.targetType;
        if (str4 != null) {
            newBuilder2.setTargetType(str4);
        }
        String str5 = this.mediaType;
        if (str5 != null) {
            newBuilder2.setMediaType(str5);
        }
        String str6 = this.requestId;
        if (str6 != null) {
            newBuilder2.setRequestId(str6);
        }
        Long l = this.duration;
        if (l != null) {
            newBuilder2.setDuration(l.longValue());
        }
        String str7 = this.transport;
        if (str7 != null) {
            newBuilder2.setTransport(str7);
        }
        Long l2 = this.size;
        if (l2 != null) {
            newBuilder2.setSize(l2.longValue());
        }
        String str8 = this.messageType;
        if (str8 != null) {
            newBuilder2.setMessageType(str8);
        }
        Long l3 = this.statusCode;
        if (l3 != null) {
            newBuilder2.setStatusCode(l3.longValue());
        }
        newBuilder.setComms(newBuilder2);
        return newBuilder.mo10084build();
    }

    public CommsMetadata withCallType(@Nullable String str) {
        this.callType = str;
        return this;
    }

    public CommsMetadata withDirection(@Nullable String str) {
        this.direction = str;
        return this;
    }

    public CommsMetadata withDuration(@Nullable Long l) {
        this.duration = l;
        return this;
    }

    public CommsMetadata withMediaType(@Nullable String str) {
        this.mediaType = str;
        return this;
    }

    public CommsMetadata withMessageType(@Nullable String str) {
        this.messageType = str;
        return this;
    }

    public CommsMetadata withRequestId(@Nullable String str) {
        this.requestId = str;
        return this;
    }

    public CommsMetadata withSize(@Nullable Long l) {
        this.size = l;
        return this;
    }

    public CommsMetadata withStatusCode(@Nullable Long l) {
        this.statusCode = l;
        return this;
    }

    public CommsMetadata withTargetType(@Nullable String str) {
        this.targetType = str;
        return this;
    }

    public CommsMetadata withTransport(@Nullable String str) {
        this.transport = str;
        return this;
    }

    public CommsMetadata withTrickleIceEnabled(@Nullable String str) {
        this.trickleIceEnabled = str;
        return this;
    }
}
