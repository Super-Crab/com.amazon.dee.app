package com.amazon.wellness.io.format.abs;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
/* loaded from: classes13.dex */
public final class HeaderOuterClass {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_Header_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_Header_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_SoftwareVersionV1_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_SoftwareVersionV1_fieldAccessorTable;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\fheader.proto\"¢\u0001\n\u0006Header\u00124\n\u0015compression_algorithm\u0018\u0001 \u0001(\u000e2\u0015.CompressionAlgorithm\u00120\n\u0014software_version1_v1\u0018\u0002 \u0001(\u000b2\u0012.SoftwareVersionV1\u00120\n\u0014software_version2_v1\u0018\u0003 \u0001(\u000b2\u0012.SoftwareVersionV1\"\u008b\u0001\n\u0011SoftwareVersionV1\u0012\u0015\n\rmajor_version\u0018\u0001 \u0001(\r\u0012\u0015\n\rminor_version\u0018\u0002 \u0001(\r\u0012\u0015\n\rpatch_version\u0018\u0003 \u0001(\r\u0012\u0015\n\rbuild_version\u0018\u0004 \u0001(\r\u0012\u001a\n\u0012version_descriptor\u0018\u0005 \u0001(\t* \n\u0014CompressionAlgorithm\u0012\b\n\u0004NONE\u0010\u0000B.\n!com.amazon.wellness.io.format.absP\u0001 \u0001\u0001¢\u0002\u0003ABSb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.wellness.io.format.abs.HeaderOuterClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = HeaderOuterClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_Header_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_Header_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Header_descriptor, new String[]{"CompressionAlgorithm", "SoftwareVersion1V1", "SoftwareVersion2V1"});
        internal_static_SoftwareVersionV1_descriptor = getDescriptor().getMessageTypes().get(1);
        internal_static_SoftwareVersionV1_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SoftwareVersionV1_descriptor, new String[]{"MajorVersion", "MinorVersion", "PatchVersion", "BuildVersion", "VersionDescriptor"});
    }

    private HeaderOuterClass() {
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
