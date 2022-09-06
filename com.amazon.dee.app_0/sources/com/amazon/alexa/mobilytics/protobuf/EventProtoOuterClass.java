package com.amazon.alexa.mobilytics.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
/* loaded from: classes9.dex */
public final class EventProtoOuterClass {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_protobuf_EventProto_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_EventProto_fieldAccessorTable;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001bmobilytics/EventProto.proto\u0012\bprotobuf\u001a\"mobilytics/EventDetailsProto.proto\"Â\u0001\n\nEventProto\u0012\u0011\n\teventType\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007eventId\u0018\u0002 \u0001(\t\u0012\u0012\n\nsourceName\u0018\u0003 \u0001(\t\u0012\u0016\n\u000eeventTimestamp\u0018\u0004 \u0001(\u0003\u0012\u001f\n\u0017applicationForegrounded\u0018\u0005 \u0001(\b\u00121\n\feventDetails\u0018\u0006 \u0001(\u000b2\u001b.protobuf.EventDetailsProto\u0012\u0010\n\bdebugUrl\u0018\u0007 \u0001(\tB-\n$com.amazon.alexa.mobilytics.protobufP\u0001¢\u0002\u0002MOb\u0006proto3"}, new Descriptors.FileDescriptor[]{EventDetailsProtoOuterClass.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.alexa.mobilytics.protobuf.EventProtoOuterClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = EventProtoOuterClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_EventProto_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_EventProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_EventProto_descriptor, new String[]{"EventType", "EventId", "SourceName", "EventTimestamp", "ApplicationForegrounded", "EventDetails", "DebugUrl"});
        EventDetailsProtoOuterClass.getDescriptor();
    }

    private EventProtoOuterClass() {
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
