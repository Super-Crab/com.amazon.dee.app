package com.amazon.alexa.mobilytics.protobuf;

import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
/* loaded from: classes9.dex */
public final class ClientProtoOuterClass {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_protobuf_ClientProto_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ClientProto_fieldAccessorTable;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001cmobilytics/ClientProto.proto\u0012\bprotobuf\"2\n\u000bClientProto\u0012\u0011\n\tcognitoId\u0018\u0001 \u0001(\t\u0012\u0010\n\bclientId\u0018\u0002 \u0001(\tB-\n$com.amazon.alexa.mobilytics.protobufP\u0001¢\u0002\u0002MOb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.alexa.mobilytics.protobuf.ClientProtoOuterClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ClientProtoOuterClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ClientProto_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ClientProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ClientProto_descriptor, new String[]{"CognitoId", DatabaseHelper.appInfo_ClientId});
    }

    private ClientProtoOuterClass() {
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
