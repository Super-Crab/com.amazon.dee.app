package com.amazon.alexa.mobilytics.protobuf;

import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
/* loaded from: classes9.dex */
public final class ApplicationProtoOuterClass {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_protobuf_ApplicationProto_Sdk_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ApplicationProto_Sdk_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_protobuf_ApplicationProto_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ApplicationProto_fieldAccessorTable;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n!mobilytics/ApplicationProto.proto\u0012\bprotobuf\"á\u0001\n\u0010ApplicationProto\u0012\r\n\u0005title\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bpackageName\u0018\u0002 \u0001(\t\u0012\r\n\u0005appId\u0018\u0003 \u0001(\t\u0012\u0013\n\u000bversionName\u0018\u0004 \u0001(\t\u0012\u001d\n\u0015cognitoIdentityPoolId\u0018\u0005 \u0001(\t\u0012\u0013\n\u000bversionCode\u0018\u0006 \u0001(\t\u0012+\n\u0003sdk\u0018\u0007 \u0001(\u000b2\u001e.protobuf.ApplicationProto.Sdk\u001a$\n\u0003Sdk\u0012\u000f\n\u0007version\u0018\u0001 \u0001(\t\u0012\f\n\u0004name\u0018\u0002 \u0001(\tB-\n$com.amazon.alexa.mobilytics.protobufP\u0001¢\u0002\u0002MOb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOuterClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ApplicationProtoOuterClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ApplicationProto_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ApplicationProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ApplicationProto_descriptor, new String[]{"Title", DatabaseHelper.appInfo_PackageName, "AppId", "VersionName", "CognitoIdentityPoolId", "VersionCode", "Sdk"});
        internal_static_protobuf_ApplicationProto_Sdk_descriptor = internal_static_protobuf_ApplicationProto_descriptor.getNestedTypes().get(0);
        internal_static_protobuf_ApplicationProto_Sdk_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ApplicationProto_Sdk_descriptor, new String[]{"Version", MAPCookie.KEY_NAME});
    }

    private ApplicationProtoOuterClass() {
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
