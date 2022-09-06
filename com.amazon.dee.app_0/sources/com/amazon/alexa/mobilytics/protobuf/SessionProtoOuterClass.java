package com.amazon.alexa.mobilytics.protobuf;

import com.amazon.deecomms.common.Constants;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
/* loaded from: classes9.dex */
public final class SessionProtoOuterClass {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_protobuf_SessionProto_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_SessionProto_fieldAccessorTable;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001dmobilytics/SessionProto.proto\u0012\bprotobuf\"b\n\fSessionProto\u0012\u0011\n\tsessionId\u0018\u0001 \u0001(\t\u0012\u0016\n\u000estartTimestamp\u0018\u0002 \u0001(\u0003\u0012\u0015\n\rstopTimestamp\u0018\u0003 \u0001(\u0003\u0012\u0010\n\bduration\u0018\u0004 \u0001(\u0003B-\n$com.amazon.alexa.mobilytics.protobufP\u0001¢\u0002\u0002MOb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.alexa.mobilytics.protobuf.SessionProtoOuterClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = SessionProtoOuterClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_SessionProto_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_SessionProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_SessionProto_descriptor, new String[]{"SessionId", "StartTimestamp", "StopTimestamp", Constants.CALL_DURATION_KEY});
    }

    private SessionProtoOuterClass() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
