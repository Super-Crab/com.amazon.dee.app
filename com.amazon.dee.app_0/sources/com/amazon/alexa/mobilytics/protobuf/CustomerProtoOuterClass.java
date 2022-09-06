package com.amazon.alexa.mobilytics.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
/* loaded from: classes9.dex */
public final class CustomerProtoOuterClass {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_protobuf_CustomerProto_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_CustomerProto_fieldAccessorTable;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001emobilytics/CustomerProto.proto\u0012\bprotobuf\"¨\u0001\n\rCustomerProto\u0012\u0015\n\rhashedCommsId\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bhouseholdId\u0018\u0002 \u0001(\t\u0012\u0012\n\ndirectedId\u0018\u0003 \u0001(\t\u0012\u0015\n\rmarketplaceId\u0018\u0004 \u0001(\t\u0012\u0010\n\bpersonId\u0018\u0005 \u0001(\t\u0012\u001a\n\u0012countryOfResidence\u0018\u0006 \u0001(\t\u0012\u0012\n\npersonIdv2\u0018\u0007 \u0001(\tB-\n$com.amazon.alexa.mobilytics.protobufP\u0001¢\u0002\u0002MOb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.alexa.mobilytics.protobuf.CustomerProtoOuterClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = CustomerProtoOuterClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_CustomerProto_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_CustomerProto_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_CustomerProto_descriptor, new String[]{"HashedCommsId", "HouseholdId", "DirectedId", "MarketplaceId", "PersonId", "CountryOfResidence", "PersonIdv2"});
    }

    private CustomerProtoOuterClass() {
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
