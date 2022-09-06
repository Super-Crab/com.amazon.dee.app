package com.amazon.wellness.io.format.abs;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
/* loaded from: classes13.dex */
public enum ActivityV1 implements ProtocolMessageEnum {
    ACTIVITY_UNKNOWN(0),
    ACTIVITY_IDLE(1),
    ACTIVITY_WALKING(2),
    ACTIVITY_RUNNING(3),
    ACTIVITY_BICYCLING(4),
    ACTIVITY_DRIVING(5),
    UNRECOGNIZED(-1);
    
    public static final int ACTIVITY_BICYCLING_VALUE = 4;
    public static final int ACTIVITY_DRIVING_VALUE = 5;
    public static final int ACTIVITY_IDLE_VALUE = 1;
    public static final int ACTIVITY_RUNNING_VALUE = 3;
    public static final int ACTIVITY_UNKNOWN_VALUE = 0;
    public static final int ACTIVITY_WALKING_VALUE = 2;
    private final int value;
    private static final Internal.EnumLiteMap<ActivityV1> internalValueMap = new Internal.EnumLiteMap<ActivityV1>() { // from class: com.amazon.wellness.io.format.abs.ActivityV1.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.Internal.EnumLiteMap
        /* renamed from: findValueByNumber */
        public ActivityV1 mo9850findValueByNumber(int i) {
            return ActivityV1.forNumber(i);
        }
    };
    private static final ActivityV1[] VALUES = values();

    ActivityV1(int i) {
        this.value = i;
    }

    public static ActivityV1 forNumber(int i) {
        if (i != 0) {
            if (i == 1) {
                return ACTIVITY_IDLE;
            }
            if (i == 2) {
                return ACTIVITY_WALKING;
            }
            if (i == 3) {
                return ACTIVITY_RUNNING;
            }
            if (i == 4) {
                return ACTIVITY_BICYCLING;
            }
            if (i == 5) {
                return ACTIVITY_DRIVING;
            }
            return null;
        }
        return ACTIVITY_UNKNOWN;
    }

    public static final Descriptors.EnumDescriptor getDescriptor() {
        return Biometric.getDescriptor().getEnumTypes().get(0);
    }

    public static Internal.EnumLiteMap<ActivityV1> internalGetValueMap() {
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
    public static ActivityV1 valueOf(int i) {
        return forNumber(i);
    }

    public static ActivityV1 valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() == getDescriptor()) {
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }
        throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
    }
}
