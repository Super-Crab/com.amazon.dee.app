package com.amazon.wellness.io.format.abs;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
/* loaded from: classes13.dex */
public enum ActivityIntensityV1 implements ProtocolMessageEnum {
    ACTIVITY_INTENSITY_UNKNOWN(0),
    ACTIVITY_INTENSITY_NONE(1),
    ACTIVITY_INTENSITY_LOW(2),
    ACTIVITY_INTENSITY_MEDIUM(3),
    ACTIVITY_INTENSITY_HIGH(4),
    ACTIVITY_INTENSITY_SLEEP(5),
    UNRECOGNIZED(-1);
    
    public static final int ACTIVITY_INTENSITY_HIGH_VALUE = 4;
    public static final int ACTIVITY_INTENSITY_LOW_VALUE = 2;
    public static final int ACTIVITY_INTENSITY_MEDIUM_VALUE = 3;
    public static final int ACTIVITY_INTENSITY_NONE_VALUE = 1;
    public static final int ACTIVITY_INTENSITY_SLEEP_VALUE = 5;
    public static final int ACTIVITY_INTENSITY_UNKNOWN_VALUE = 0;
    private final int value;
    private static final Internal.EnumLiteMap<ActivityIntensityV1> internalValueMap = new Internal.EnumLiteMap<ActivityIntensityV1>() { // from class: com.amazon.wellness.io.format.abs.ActivityIntensityV1.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.Internal.EnumLiteMap
        /* renamed from: findValueByNumber */
        public ActivityIntensityV1 mo9850findValueByNumber(int i) {
            return ActivityIntensityV1.forNumber(i);
        }
    };
    private static final ActivityIntensityV1[] VALUES = values();

    ActivityIntensityV1(int i) {
        this.value = i;
    }

    public static ActivityIntensityV1 forNumber(int i) {
        if (i != 0) {
            if (i == 1) {
                return ACTIVITY_INTENSITY_NONE;
            }
            if (i == 2) {
                return ACTIVITY_INTENSITY_LOW;
            }
            if (i == 3) {
                return ACTIVITY_INTENSITY_MEDIUM;
            }
            if (i == 4) {
                return ACTIVITY_INTENSITY_HIGH;
            }
            if (i == 5) {
                return ACTIVITY_INTENSITY_SLEEP;
            }
            return null;
        }
        return ACTIVITY_INTENSITY_UNKNOWN;
    }

    public static final Descriptors.EnumDescriptor getDescriptor() {
        return Biometric.getDescriptor().getEnumTypes().get(1);
    }

    public static Internal.EnumLiteMap<ActivityIntensityV1> internalGetValueMap() {
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
    public static ActivityIntensityV1 valueOf(int i) {
        return forNumber(i);
    }

    public static ActivityIntensityV1 valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() == getDescriptor()) {
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }
        throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
    }
}
