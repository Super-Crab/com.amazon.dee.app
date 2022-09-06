package com.amazon.alexa.mobilytics.protobuf;

import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
/* loaded from: classes9.dex */
public final class MobilyticsMessageProtoOuterClass {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_protobuf_MobilyticsMessageProto_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_MobilyticsMessageProto_fieldAccessorTable;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n'mobilytics/MobilyticsMessageProto.proto\u0012\bprotobuf\u001a\u001emobilytics/CustomerProto.proto\u001a\u001cmobilytics/ClientProto.proto\u001a\u001cmobilytics/DeviceProto.proto\u001a!mobilytics/ApplicationProto.proto\u001a\u001dmobilytics/SessionProto.proto\u001a\u001bmobilytics/EventProto.proto\" \u0002\n\u0016MobilyticsMessageProto\u0012)\n\bcustomer\u0018\u0001 \u0001(\u000b2\u0017.protobuf.CustomerProto\u0012\u000e\n\u0006pivots\u0018\u0002 \u0001(\t\u0012%\n\u0006client\u0018\u0003 \u0001(\u000b2\u0015.protobuf.ClientProto\u0012/\n\u000bapplication\u0018\u0004 \u0001(\u000b2\u001a.protobuf.ApplicationProto\u0012'\n\u0007session\u0018\u0005 \u0001(\u000b2\u0016.protobuf.SessionProto\u0012%\n\u0006device\u0018\u0006 \u0001(\u000b2\u0015.protobuf.DeviceProto\u0012#\n\u0005event\u0018\u0007 \u0001(\u000b2\u0014.protobuf.EventProtoB-\n$com.amazon.alexa.mobilytics.protobufP\u0001¢\u0002\u0002MOb\u0006proto3"}, new Descriptors.FileDescriptor[]{CustomerProtoOuterClass.getDescriptor(), ClientProtoOuterClass.getDescriptor(), DeviceProtoOuterClass.getDescriptor(), ApplicationProtoOuterClass.getDescriptor(), SessionProtoOuterClass.getDescriptor(), EventProtoOuterClass.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProtoOuterClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = MobilyticsMessageProtoOuterClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_MobilyticsMessageProto_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_MobilyticsMessageProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_MobilyticsMessageProto_descriptor, new String[]{"Customer", "Pivots", "Client", "Application", MetricsConfiguration.SESSION_ID, "Device", "Event"});
        CustomerProtoOuterClass.getDescriptor();
        ClientProtoOuterClass.getDescriptor();
        DeviceProtoOuterClass.getDescriptor();
        ApplicationProtoOuterClass.getDescriptor();
        SessionProtoOuterClass.getDescriptor();
        EventProtoOuterClass.getDescriptor();
    }

    private MobilyticsMessageProtoOuterClass() {
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
