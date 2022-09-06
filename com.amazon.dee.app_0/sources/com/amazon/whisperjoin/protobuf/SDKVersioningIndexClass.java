package com.amazon.whisperjoin.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
/* loaded from: classes13.dex */
public final class SDKVersioningIndexClass {
    private static Descriptors.FileDescriptor descriptor;

    /* loaded from: classes13.dex */
    public enum SDKVersioningIndex implements ProtocolMessageEnum {
        UNKNOWN(0),
        V1_14_0(1);
        
        public static final int UNKNOWN_VALUE = 0;
        public static final int V1_14_0_VALUE = 1;
        private final int value;
        private static final Internal.EnumLiteMap<SDKVersioningIndex> internalValueMap = new Internal.EnumLiteMap<SDKVersioningIndex>() { // from class: com.amazon.whisperjoin.protobuf.SDKVersioningIndexClass.SDKVersioningIndex.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public SDKVersioningIndex mo9850findValueByNumber(int i) {
                return SDKVersioningIndex.forNumber(i);
            }
        };
        private static final SDKVersioningIndex[] VALUES = values();

        SDKVersioningIndex(int i) {
            this.value = i;
        }

        public static SDKVersioningIndex forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return V1_14_0;
                }
                return null;
            }
            return UNKNOWN;
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return SDKVersioningIndexClass.getDescriptor().getEnumTypes().get(0);
        }

        public static Internal.EnumLiteMap<SDKVersioningIndex> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(ordinal());
        }

        @Deprecated
        public static SDKVersioningIndex valueOf(int i) {
            return forNumber(i);
        }

        public static SDKVersioningIndex valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nGWhisperJoinProtocolBuffersModel/schema/version/SDKVersioningIndex.proto\u0012\bprotobuf*.\n\u0012SDKVersioningIndex\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\u000b\n\u0007V1_14_0\u0010\u0001B@\n\u001fcom.amazon.whisperjoin.protobufB\u0017SDKVersioningIndexClass¢\u0002\u0003PWJ"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.SDKVersioningIndexClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = SDKVersioningIndexClass.descriptor = fileDescriptor;
                return null;
            }
        });
    }

    private SDKVersioningIndexClass() {
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
