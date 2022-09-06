package com.amazon.alexa.mobilytics.event.metadata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
/* loaded from: classes9.dex */
public class A4ASdkMetadata implements DefaultEventMetadata {
    private String dialogRequestId;
    private String invocationIdentifier;
    private String invocationNamespace;
    private String invocationType;
    private final String metadataType = EventMetadataType.A4ASDK;

    @Nullable
    public String getDialogRequestId() {
        return this.dialogRequestId;
    }

    @Nullable
    public String getInvocationIdentifier() {
        return this.invocationIdentifier;
    }

    @Nullable
    public String getInvocationNamespace() {
        return this.invocationNamespace;
    }

    @Nullable
    public String getInvocationType() {
        return this.invocationType;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.EventMetadata
    public String getMetadataType() {
        return EventMetadataType.A4ASDK;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.DefaultEventMetadata
    @NonNull
    public EventDetailsProto.Metadata serialize() {
        EventDetailsProto.Metadata.Builder newBuilder = EventDetailsProto.Metadata.newBuilder();
        EventDetailsProto.Metadata.A4aSdk.Builder newBuilder2 = EventDetailsProto.Metadata.A4aSdk.newBuilder();
        String str = this.dialogRequestId;
        if (str != null) {
            newBuilder2.setDialogRequestId(str);
        }
        String str2 = this.invocationNamespace;
        if (str2 != null) {
            newBuilder2.setInvocationNamespace(str2);
        }
        String str3 = this.invocationIdentifier;
        if (str3 != null) {
            newBuilder2.setInvocationIdentifier(str3);
        }
        String str4 = this.invocationType;
        if (str4 != null) {
            newBuilder2.setInvocationType(str4);
        }
        newBuilder.setA4ASdk(newBuilder2);
        return newBuilder.mo10084build();
    }

    public A4ASdkMetadata withDialogRequestId(@Nullable String str) {
        this.dialogRequestId = str;
        return this;
    }

    public A4ASdkMetadata withInvocationIdentifier(@Nullable String str) {
        this.invocationIdentifier = str;
        return this;
    }

    public A4ASdkMetadata withInvocationNamespace(@Nullable String str) {
        this.invocationNamespace = str;
        return this;
    }

    public A4ASdkMetadata withInvocationType(@Nullable String str) {
        this.invocationType = str;
        return this;
    }
}
