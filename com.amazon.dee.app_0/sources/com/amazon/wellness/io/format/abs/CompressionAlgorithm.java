package com.amazon.wellness.io.format.abs;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
/* loaded from: classes13.dex */
public enum CompressionAlgorithm implements ProtocolMessageEnum {
    NONE(0),
    UNRECOGNIZED(-1);
    
    public static final int NONE_VALUE = 0;
    private final int value;
    private static final Internal.EnumLiteMap<CompressionAlgorithm> internalValueMap = new Internal.EnumLiteMap<CompressionAlgorithm>() { // from class: com.amazon.wellness.io.format.abs.CompressionAlgorithm.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.Internal.EnumLiteMap
        /* renamed from: findValueByNumber */
        public CompressionAlgorithm mo9850findValueByNumber(int i) {
            return CompressionAlgorithm.forNumber(i);
        }
    };
    private static final CompressionAlgorithm[] VALUES = values();

    CompressionAlgorithm(int i) {
        this.value = i;
    }

    public static CompressionAlgorithm forNumber(int i) {
        if (i != 0) {
            return null;
        }
        return NONE;
    }

    public static final Descriptors.EnumDescriptor getDescriptor() {
        return HeaderOuterClass.getDescriptor().getEnumTypes().get(0);
    }

    public static Internal.EnumLiteMap<CompressionAlgorithm> internalGetValueMap() {
        return internalValueMap;
    }

    @Override // com.google.protobuf.ProtocolMessageEnum
    public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }

    @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    @Override // com.google.protobuf.ProtocolMessageEnum
    public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        return getDescriptor().getValues().get(ordinal());
    }

    @Deprecated
    public static CompressionAlgorithm valueOf(int i) {
        return forNumber(i);
    }

    public static CompressionAlgorithm valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() == getDescriptor()) {
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }
        throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
    }
}
