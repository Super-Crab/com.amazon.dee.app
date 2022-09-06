package com.amazon.alexa.accessory.protocol;

import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;
/* loaded from: classes6.dex */
public final class Common {

    /* loaded from: classes6.dex */
    public enum ErrorCode implements Internal.EnumLite {
        SUCCESS(0),
        UNKNOWN(1),
        INTERNAL(2),
        UNSUPPORTED(3),
        USER_CANCELLED(4),
        NOT_FOUND(5),
        INVALID(6),
        BUSY(7),
        UNRECOGNIZED(-1);
        
        public static final int BUSY_VALUE = 7;
        public static final int INTERNAL_VALUE = 2;
        public static final int INVALID_VALUE = 6;
        public static final int NOT_FOUND_VALUE = 5;
        public static final int SUCCESS_VALUE = 0;
        public static final int UNKNOWN_VALUE = 1;
        public static final int UNSUPPORTED_VALUE = 3;
        public static final int USER_CANCELLED_VALUE = 4;
        private static final Internal.EnumLiteMap<ErrorCode> internalValueMap = new Internal.EnumLiteMap<ErrorCode>() { // from class: com.amazon.alexa.accessory.protocol.Common.ErrorCode.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public ErrorCode mo9850findValueByNumber(int i) {
                return ErrorCode.forNumber(i);
            }
        };
        private final int value;

        ErrorCode(int i) {
            this.value = i;
        }

        public static ErrorCode forNumber(int i) {
            switch (i) {
                case 0:
                    return SUCCESS;
                case 1:
                    return UNKNOWN;
                case 2:
                    return INTERNAL;
                case 3:
                    return UNSUPPORTED;
                case 4:
                    return USER_CANCELLED;
                case 5:
                    return NOT_FOUND;
                case 6:
                    return INVALID;
                case 7:
                    return BUSY;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<ErrorCode> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static ErrorCode valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public enum SpeechInitiationType implements Internal.EnumLite {
        PRESS_AND_HOLD(0),
        TAP(1),
        WAKEWORD(2),
        SERVER(3),
        UNRECOGNIZED(-1);
        
        public static final int PRESS_AND_HOLD_VALUE = 0;
        public static final int SERVER_VALUE = 3;
        public static final int TAP_VALUE = 1;
        public static final int WAKEWORD_VALUE = 2;
        private static final Internal.EnumLiteMap<SpeechInitiationType> internalValueMap = new Internal.EnumLiteMap<SpeechInitiationType>() { // from class: com.amazon.alexa.accessory.protocol.Common.SpeechInitiationType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public SpeechInitiationType mo9850findValueByNumber(int i) {
                return SpeechInitiationType.forNumber(i);
            }
        };
        private final int value;

        SpeechInitiationType(int i) {
            this.value = i;
        }

        public static SpeechInitiationType forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return TAP;
                }
                if (i == 2) {
                    return WAKEWORD;
                }
                if (i == 3) {
                    return SERVER;
                }
                return null;
            }
            return PRESS_AND_HOLD;
        }

        public static Internal.EnumLiteMap<SpeechInitiationType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static SpeechInitiationType valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public enum Transport implements Internal.EnumLite {
        BLUETOOTH_LOW_ENERGY(0),
        BLUETOOTH_RFCOMM(1),
        BLUETOOTH_IAP(2),
        UNRECOGNIZED(-1);
        
        public static final int BLUETOOTH_IAP_VALUE = 2;
        public static final int BLUETOOTH_LOW_ENERGY_VALUE = 0;
        public static final int BLUETOOTH_RFCOMM_VALUE = 1;
        private static final Internal.EnumLiteMap<Transport> internalValueMap = new Internal.EnumLiteMap<Transport>() { // from class: com.amazon.alexa.accessory.protocol.Common.Transport.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public Transport mo9850findValueByNumber(int i) {
                return Transport.forNumber(i);
            }
        };
        private final int value;

        Transport(int i) {
            this.value = i;
        }

        public static Transport forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return BLUETOOTH_RFCOMM;
                }
                if (i == 2) {
                    return BLUETOOTH_IAP;
                }
                return null;
            }
            return BLUETOOTH_LOW_ENERGY;
        }

        public static Internal.EnumLiteMap<Transport> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static Transport valueOf(int i) {
            return forNumber(i);
        }
    }

    private Common() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
