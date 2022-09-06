package com.amazon.alexa.mobilytics.protobuf;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
/* loaded from: classes9.dex */
public final class DeviceProtoOuterClass {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_protobuf_DeviceProto_Locale_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_DeviceProto_Locale_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_protobuf_DeviceProto_Platform_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_DeviceProto_Platform_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_protobuf_DeviceProto_Screen_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_DeviceProto_Screen_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_protobuf_DeviceProto_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_DeviceProto_fieldAccessorTable;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001cmobilytics/DeviceProto.proto\u0012\bprotobuf\"º\u0003\n\u000bDeviceProto\u0012\u0010\n\bdeviceId\u0018\u0001 \u0001(\t\u0012\u0012\n\ndeviceType\u0018\u0002 \u0001(\t\u0012\f\n\u0004make\u0018\u0003 \u0001(\t\u0012\r\n\u0005model\u0018\u0004 \u0001(\t\u00120\n\bplatform\u0018\u0005 \u0001(\u000b2\u001e.protobuf.DeviceProto.Platform\u0012\u0013\n\u000bnetworkType\u0018\u0006 \u0001(\t\u0012\u000f\n\u0007carrier\u0018\u0007 \u0001(\t\u0012,\n\u0006screen\u0018\b \u0001(\u000b2\u001c.protobuf.DeviceProto.Screen\u0012,\n\u0006locale\u0018\t \u0001(\u000b2\u001c.protobuf.DeviceProto.Locale\u0012\u0010\n\btimezone\u0018\n \u0001(\t\u001a)\n\bPlatform\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007version\u0018\u0002 \u0001(\t\u001aJ\n\u0006Screen\u0012\u0013\n\u000bscreenWidth\u0018\u0001 \u0001(\u0003\u0012\u0014\n\fscreenHeight\u0018\u0002 \u0001(\u0003\u0012\u0015\n\rscreenDensity\u0018\u0003 \u0001(\u0001\u001a+\n\u0006Locale\u0012\u000f\n\u0007country\u0018\u0001 \u0001(\t\u0012\u0010\n\blanguage\u0018\u0002 \u0001(\tB-\n$com.amazon.alexa.mobilytics.protobufP\u0001¢\u0002\u0002MOb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.alexa.mobilytics.protobuf.DeviceProtoOuterClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = DeviceProtoOuterClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_DeviceProto_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_DeviceProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_DeviceProto_descriptor, new String[]{DataRecordKey.DEVICE_AGENT, "DeviceType", ExifInterface.TAG_MAKE, ExifInterface.TAG_MODEL, DataRecordKey.PLATFORM, DataRecordKey.NETWORK_TYPE, DataRecordKey.NETWORK_OPERATOR, "Screen", "Locale", "Timezone"});
        internal_static_protobuf_DeviceProto_Platform_descriptor = internal_static_protobuf_DeviceProto_descriptor.getNestedTypes().get(0);
        internal_static_protobuf_DeviceProto_Platform_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_DeviceProto_Platform_descriptor, new String[]{MAPCookie.KEY_NAME, "Version"});
        internal_static_protobuf_DeviceProto_Screen_descriptor = internal_static_protobuf_DeviceProto_descriptor.getNestedTypes().get(1);
        internal_static_protobuf_DeviceProto_Screen_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_DeviceProto_Screen_descriptor, new String[]{"ScreenWidth", "ScreenHeight", "ScreenDensity"});
        internal_static_protobuf_DeviceProto_Locale_descriptor = internal_static_protobuf_DeviceProto_descriptor.getNestedTypes().get(2);
        internal_static_protobuf_DeviceProto_Locale_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_DeviceProto_Locale_descriptor, new String[]{"Country", "Language"});
    }

    private DeviceProtoOuterClass() {
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
