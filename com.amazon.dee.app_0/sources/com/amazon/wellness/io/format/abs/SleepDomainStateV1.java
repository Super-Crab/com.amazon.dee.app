package com.amazon.wellness.io.format.abs;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
/* loaded from: classes13.dex */
public enum SleepDomainStateV1 implements ProtocolMessageEnum {
    UNKNOWN(0),
    AWAKE(1),
    INTENT(2),
    STOPINTENT(3),
    ASLEEP(4),
    UNRECOGNIZED(-1);
    
    public static final int ASLEEP_VALUE = 4;
    public static final int AWAKE_VALUE = 1;
    public static final int INTENT_VALUE = 2;
    public static final int STOPINTENT_VALUE = 3;
    public static final int UNKNOWN_VALUE = 0;
    private final int value;
    private static final Internal.EnumLiteMap<SleepDomainStateV1> internalValueMap = new Internal.EnumLiteMap<SleepDomainStateV1>() { // from class: com.amazon.wellness.io.format.abs.SleepDomainStateV1.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.Internal.EnumLiteMap
        /* renamed from: findValueByNumber */
        public SleepDomainStateV1 mo9850findValueByNumber(int i) {
            return SleepDomainStateV1.forNumber(i);
        }
    };
    private static final SleepDomainStateV1[] VALUES = values();

    SleepDomainStateV1(int i) {
        this.value = i;
    }

    public static SleepDomainStateV1 forNumber(int i) {
        if (i != 0) {
            if (i == 1) {
                return AWAKE;
            }
            if (i == 2) {
                return INTENT;
            }
            if (i == 3) {
                return STOPINTENT;
            }
            if (i == 4) {
                return ASLEEP;
            }
            return null;
        }
        return UNKNOWN;
    }

    public static final Descriptors.EnumDescriptor getDescriptor() {
        return Sleep.getDescriptor().getEnumTypes().get(0);
    }

    public static Internal.EnumLiteMap<SleepDomainStateV1> internalGetValueMap() {
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
    public static SleepDomainStateV1 valueOf(int i) {
        return forNumber(i);
    }

    public static SleepDomainStateV1 valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() == getDescriptor()) {
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }
        throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
    }
}
