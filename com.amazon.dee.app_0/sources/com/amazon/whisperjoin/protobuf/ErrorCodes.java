package com.amazon.whisperjoin.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
/* loaded from: classes13.dex */
public final class ErrorCodes {
    private static Descriptors.FileDescriptor descriptor;

    /* loaded from: classes13.dex */
    public enum BLECommandErrorType implements ProtocolMessageEnum {
        UNKNOWN(0),
        EXCHANGE_ECDHE_KEY(1),
        EXCHANGE_AUTH_ECDHE_KEY(2),
        JPAKE_ROUND_1(3),
        JPAKE_ROUND_2(4),
        JPAKE_ROUND_3(5),
        JPAKE_CERT_VALIDATION(6),
        GET_VISIBLE_NETWORKS(7),
        GET_DEVICE_DETAILS(8),
        SAVE_NETWORK(9),
        GET_CONNECTION_STATUS(10),
        SET_CONFIGURATION(11),
        SET_REGISTRATION_TOKEN(12),
        GET_REGISTRATION_STATUS(13),
        COMPLETE_PROVISIONING(14),
        GET_SUPPORTED_NOTIFICATIONS(15),
        GET_NOTIFICATION_EVENT_DATA(16);
        
        public static final int COMPLETE_PROVISIONING_VALUE = 14;
        public static final int EXCHANGE_AUTH_ECDHE_KEY_VALUE = 2;
        public static final int EXCHANGE_ECDHE_KEY_VALUE = 1;
        public static final int GET_CONNECTION_STATUS_VALUE = 10;
        public static final int GET_DEVICE_DETAILS_VALUE = 8;
        public static final int GET_NOTIFICATION_EVENT_DATA_VALUE = 16;
        public static final int GET_REGISTRATION_STATUS_VALUE = 13;
        public static final int GET_SUPPORTED_NOTIFICATIONS_VALUE = 15;
        public static final int GET_VISIBLE_NETWORKS_VALUE = 7;
        public static final int JPAKE_CERT_VALIDATION_VALUE = 6;
        public static final int JPAKE_ROUND_1_VALUE = 3;
        public static final int JPAKE_ROUND_2_VALUE = 4;
        public static final int JPAKE_ROUND_3_VALUE = 5;
        public static final int SAVE_NETWORK_VALUE = 9;
        public static final int SET_CONFIGURATION_VALUE = 11;
        public static final int SET_REGISTRATION_TOKEN_VALUE = 12;
        public static final int UNKNOWN_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<BLECommandErrorType> internalValueMap = new Internal.EnumLiteMap<BLECommandErrorType>() { // from class: com.amazon.whisperjoin.protobuf.ErrorCodes.BLECommandErrorType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public BLECommandErrorType mo9850findValueByNumber(int i) {
                return BLECommandErrorType.forNumber(i);
            }
        };
        private static final BLECommandErrorType[] VALUES = values();

        BLECommandErrorType(int i) {
            this.value = i;
        }

        public static BLECommandErrorType forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN;
                case 1:
                    return EXCHANGE_ECDHE_KEY;
                case 2:
                    return EXCHANGE_AUTH_ECDHE_KEY;
                case 3:
                    return JPAKE_ROUND_1;
                case 4:
                    return JPAKE_ROUND_2;
                case 5:
                    return JPAKE_ROUND_3;
                case 6:
                    return JPAKE_CERT_VALIDATION;
                case 7:
                    return GET_VISIBLE_NETWORKS;
                case 8:
                    return GET_DEVICE_DETAILS;
                case 9:
                    return SAVE_NETWORK;
                case 10:
                    return GET_CONNECTION_STATUS;
                case 11:
                    return SET_CONFIGURATION;
                case 12:
                    return SET_REGISTRATION_TOKEN;
                case 13:
                    return GET_REGISTRATION_STATUS;
                case 14:
                    return COMPLETE_PROVISIONING;
                case 15:
                    return GET_SUPPORTED_NOTIFICATIONS;
                case 16:
                    return GET_NOTIFICATION_EVENT_DATA;
                default:
                    return null;
            }
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ErrorCodes.getDescriptor().getEnumTypes().get(1);
        }

        public static Internal.EnumLiteMap<BLECommandErrorType> internalGetValueMap() {
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
        public static BLECommandErrorType valueOf(int i) {
            return forNumber(i);
        }

        public static BLECommandErrorType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
    }

    /* loaded from: classes13.dex */
    public enum ConnectionErrorType implements ProtocolMessageEnum {
        UNKNOWN_CONNECTION_ERROR(0),
        UNABLE_TO_ESTABLISH_CONNECTION(1),
        UNABLE_TO_ESTABLISH_SECURE_CHANNEL(2),
        START_PROVISIONING_REQUEST_FAILED(3),
        UNEXPECTED_CONNECTION_DROP(4),
        PROVISIONING_COMMAND_DESERIALIZATION_ERROR(5);
        
        public static final int PROVISIONING_COMMAND_DESERIALIZATION_ERROR_VALUE = 5;
        public static final int START_PROVISIONING_REQUEST_FAILED_VALUE = 3;
        public static final int UNABLE_TO_ESTABLISH_CONNECTION_VALUE = 1;
        public static final int UNABLE_TO_ESTABLISH_SECURE_CHANNEL_VALUE = 2;
        public static final int UNEXPECTED_CONNECTION_DROP_VALUE = 4;
        public static final int UNKNOWN_CONNECTION_ERROR_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<ConnectionErrorType> internalValueMap = new Internal.EnumLiteMap<ConnectionErrorType>() { // from class: com.amazon.whisperjoin.protobuf.ErrorCodes.ConnectionErrorType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public ConnectionErrorType mo9850findValueByNumber(int i) {
                return ConnectionErrorType.forNumber(i);
            }
        };
        private static final ConnectionErrorType[] VALUES = values();

        ConnectionErrorType(int i) {
            this.value = i;
        }

        public static ConnectionErrorType forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return UNABLE_TO_ESTABLISH_CONNECTION;
                }
                if (i == 2) {
                    return UNABLE_TO_ESTABLISH_SECURE_CHANNEL;
                }
                if (i == 3) {
                    return START_PROVISIONING_REQUEST_FAILED;
                }
                if (i == 4) {
                    return UNEXPECTED_CONNECTION_DROP;
                }
                if (i == 5) {
                    return PROVISIONING_COMMAND_DESERIALIZATION_ERROR;
                }
                return null;
            }
            return UNKNOWN_CONNECTION_ERROR;
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ErrorCodes.getDescriptor().getEnumTypes().get(2);
        }

        public static Internal.EnumLiteMap<ConnectionErrorType> internalGetValueMap() {
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
        public static ConnectionErrorType valueOf(int i) {
            return forNumber(i);
        }

        public static ConnectionErrorType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
    }

    /* loaded from: classes13.dex */
    public enum Domain implements ProtocolMessageEnum {
        PRECONDITION_FAILURE(1),
        CONNECTION_FAILURE(2),
        PROVISIONING_FAILURE(3),
        BLE_COMMAND_FAILURE(4),
        WEB_SERVICE_CALL_FAILUE(5),
        WORKFLOW_FAILURE(6),
        OTHER_FAILURE(99),
        INTERNAL_FAILURE(100);
        
        public static final int BLE_COMMAND_FAILURE_VALUE = 4;
        public static final int CONNECTION_FAILURE_VALUE = 2;
        public static final int INTERNAL_FAILURE_VALUE = 100;
        public static final int OTHER_FAILURE_VALUE = 99;
        public static final int PRECONDITION_FAILURE_VALUE = 1;
        public static final int PROVISIONING_FAILURE_VALUE = 3;
        public static final int WEB_SERVICE_CALL_FAILUE_VALUE = 5;
        public static final int WORKFLOW_FAILURE_VALUE = 6;
        private final int value;
        private static final Internal.EnumLiteMap<Domain> internalValueMap = new Internal.EnumLiteMap<Domain>() { // from class: com.amazon.whisperjoin.protobuf.ErrorCodes.Domain.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public Domain mo9850findValueByNumber(int i) {
                return Domain.forNumber(i);
            }
        };
        private static final Domain[] VALUES = values();

        Domain(int i) {
            this.value = i;
        }

        public static Domain forNumber(int i) {
            if (i != 99) {
                if (i != 100) {
                    switch (i) {
                        case 1:
                            return PRECONDITION_FAILURE;
                        case 2:
                            return CONNECTION_FAILURE;
                        case 3:
                            return PROVISIONING_FAILURE;
                        case 4:
                            return BLE_COMMAND_FAILURE;
                        case 5:
                            return WEB_SERVICE_CALL_FAILUE;
                        case 6:
                            return WORKFLOW_FAILURE;
                        default:
                            return null;
                    }
                }
                return INTERNAL_FAILURE;
            }
            return OTHER_FAILURE;
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ErrorCodes.getDescriptor().getEnumTypes().get(0);
        }

        public static Internal.EnumLiteMap<Domain> internalGetValueMap() {
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
        public static Domain valueOf(int i) {
            return forNumber(i);
        }

        public static Domain valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
    }

    /* loaded from: classes13.dex */
    public enum InternalErrorType implements ProtocolMessageEnum {
        SCAP_BLE_ERROR(1);
        
        public static final int SCAP_BLE_ERROR_VALUE = 1;
        private final int value;
        private static final Internal.EnumLiteMap<InternalErrorType> internalValueMap = new Internal.EnumLiteMap<InternalErrorType>() { // from class: com.amazon.whisperjoin.protobuf.ErrorCodes.InternalErrorType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public InternalErrorType mo9850findValueByNumber(int i) {
                return InternalErrorType.forNumber(i);
            }
        };
        private static final InternalErrorType[] VALUES = values();

        InternalErrorType(int i) {
            this.value = i;
        }

        public static InternalErrorType forNumber(int i) {
            if (i != 1) {
                return null;
            }
            return SCAP_BLE_ERROR;
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ErrorCodes.getDescriptor().getEnumTypes().get(8);
        }

        public static Internal.EnumLiteMap<InternalErrorType> internalGetValueMap() {
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
        public static InternalErrorType valueOf(int i) {
            return forNumber(i);
        }

        public static InternalErrorType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
    }

    /* loaded from: classes13.dex */
    public enum OtherErrorType implements ProtocolMessageEnum {
        UNKNOWN_ERROR(1);
        
        public static final int UNKNOWN_ERROR_VALUE = 1;
        private final int value;
        private static final Internal.EnumLiteMap<OtherErrorType> internalValueMap = new Internal.EnumLiteMap<OtherErrorType>() { // from class: com.amazon.whisperjoin.protobuf.ErrorCodes.OtherErrorType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public OtherErrorType mo9850findValueByNumber(int i) {
                return OtherErrorType.forNumber(i);
            }
        };
        private static final OtherErrorType[] VALUES = values();

        OtherErrorType(int i) {
            this.value = i;
        }

        public static OtherErrorType forNumber(int i) {
            if (i != 1) {
                return null;
            }
            return UNKNOWN_ERROR;
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ErrorCodes.getDescriptor().getEnumTypes().get(7);
        }

        public static Internal.EnumLiteMap<OtherErrorType> internalGetValueMap() {
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
        public static OtherErrorType valueOf(int i) {
            return forNumber(i);
        }

        public static OtherErrorType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
    }

    /* loaded from: classes13.dex */
    public enum PreconditionErrorType implements ProtocolMessageEnum {
        UNKNOWN_PRECONDITION_ERROR(0),
        BLUETOOTH_NOT_ENABLED(1),
        BLUETOOTH_LOW_ENERGY_NOT_SUPPORTED(2),
        NO_USER_LOGGED_IN(3),
        REQUIRED_PERMISSIONS_NOT_GRANTED(4);
        
        public static final int BLUETOOTH_LOW_ENERGY_NOT_SUPPORTED_VALUE = 2;
        public static final int BLUETOOTH_NOT_ENABLED_VALUE = 1;
        public static final int NO_USER_LOGGED_IN_VALUE = 3;
        public static final int REQUIRED_PERMISSIONS_NOT_GRANTED_VALUE = 4;
        public static final int UNKNOWN_PRECONDITION_ERROR_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<PreconditionErrorType> internalValueMap = new Internal.EnumLiteMap<PreconditionErrorType>() { // from class: com.amazon.whisperjoin.protobuf.ErrorCodes.PreconditionErrorType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public PreconditionErrorType mo9850findValueByNumber(int i) {
                return PreconditionErrorType.forNumber(i);
            }
        };
        private static final PreconditionErrorType[] VALUES = values();

        PreconditionErrorType(int i) {
            this.value = i;
        }

        public static PreconditionErrorType forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return BLUETOOTH_NOT_ENABLED;
                }
                if (i == 2) {
                    return BLUETOOTH_LOW_ENERGY_NOT_SUPPORTED;
                }
                if (i == 3) {
                    return NO_USER_LOGGED_IN;
                }
                if (i == 4) {
                    return REQUIRED_PERMISSIONS_NOT_GRANTED;
                }
                return null;
            }
            return UNKNOWN_PRECONDITION_ERROR;
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ErrorCodes.getDescriptor().getEnumTypes().get(3);
        }

        public static Internal.EnumLiteMap<PreconditionErrorType> internalGetValueMap() {
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
        public static PreconditionErrorType valueOf(int i) {
            return forNumber(i);
        }

        public static PreconditionErrorType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
    }

    /* loaded from: classes13.dex */
    public enum ProvisioningErrorType implements ProtocolMessageEnum {
        UNKNOWN_PROVISIONING_ERROR(0),
        NO_CONFIGURED_NETWORKS(1),
        WIFI_CONN_ERROR_BAD_PSK(2),
        WIFI_CONN_ERROR_INTERNAL_ERROR(3),
        WIFI_CONN_ERROR_CAPTIVE_PORTAL(4),
        WIFI_CONN_ERROR_LIMITED_CONNECTIVITY(5),
        WIFI_CONN_UNKNOWN_ERROR(6),
        REG_ERROR_FAILED_TO_GET_TOKEN_FROM_MAP(7),
        REG_ERROR_TOKEN_INVALID(8),
        REG_ERROR_TOKEN_EXPIRED(9),
        REG_ERROR_SERVER_NOT_REACHABLE(10),
        REG_ERROR_SERVER_ERROR(11),
        REG_ERROR_FAILED_OTHER(12),
        REG_ERROR_UNKNOWN_ERROR(13),
        PROVISIONING_DONE_FAILURE(14),
        PROVISIONING_VERIFICATION_TIMEOUT(15),
        WIFI_CONN_ERROR_AP_NOT_FOUND(16),
        NO_ASSOCIATED_DEVICE_CREDENTIALS(17),
        PROVISIONING_FAILURE_REGISTRATION_ERROR_FAILED_DESERIALIZATION(18),
        PROVISIONING_FAILURE_WIFI_ERROR_FAILED_DESERIALIZATION(19),
        PROVISIONING_FAILURE_NETWORK_SYNC_TOKEN_INVALID(24),
        PROVISIONING_FAILURE_NETWORK_SYNC_TOKEN_NOT_FOUND(25);
        
        public static final int NO_ASSOCIATED_DEVICE_CREDENTIALS_VALUE = 17;
        public static final int NO_CONFIGURED_NETWORKS_VALUE = 1;
        public static final int PROVISIONING_DONE_FAILURE_VALUE = 14;
        public static final int PROVISIONING_FAILURE_NETWORK_SYNC_TOKEN_INVALID_VALUE = 24;
        public static final int PROVISIONING_FAILURE_NETWORK_SYNC_TOKEN_NOT_FOUND_VALUE = 25;
        public static final int PROVISIONING_FAILURE_REGISTRATION_ERROR_FAILED_DESERIALIZATION_VALUE = 18;
        public static final int PROVISIONING_FAILURE_WIFI_ERROR_FAILED_DESERIALIZATION_VALUE = 19;
        public static final int PROVISIONING_VERIFICATION_TIMEOUT_VALUE = 15;
        public static final int REG_ERROR_FAILED_OTHER_VALUE = 12;
        public static final int REG_ERROR_FAILED_TO_GET_TOKEN_FROM_MAP_VALUE = 7;
        public static final int REG_ERROR_SERVER_ERROR_VALUE = 11;
        public static final int REG_ERROR_SERVER_NOT_REACHABLE_VALUE = 10;
        public static final int REG_ERROR_TOKEN_EXPIRED_VALUE = 9;
        public static final int REG_ERROR_TOKEN_INVALID_VALUE = 8;
        public static final int REG_ERROR_UNKNOWN_ERROR_VALUE = 13;
        public static final int UNKNOWN_PROVISIONING_ERROR_VALUE = 0;
        public static final int WIFI_CONN_ERROR_AP_NOT_FOUND_VALUE = 16;
        public static final int WIFI_CONN_ERROR_BAD_PSK_VALUE = 2;
        public static final int WIFI_CONN_ERROR_CAPTIVE_PORTAL_VALUE = 4;
        public static final int WIFI_CONN_ERROR_INTERNAL_ERROR_VALUE = 3;
        public static final int WIFI_CONN_ERROR_LIMITED_CONNECTIVITY_VALUE = 5;
        public static final int WIFI_CONN_UNKNOWN_ERROR_VALUE = 6;
        private final int value;
        private static final Internal.EnumLiteMap<ProvisioningErrorType> internalValueMap = new Internal.EnumLiteMap<ProvisioningErrorType>() { // from class: com.amazon.whisperjoin.protobuf.ErrorCodes.ProvisioningErrorType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public ProvisioningErrorType mo9850findValueByNumber(int i) {
                return ProvisioningErrorType.forNumber(i);
            }
        };
        private static final ProvisioningErrorType[] VALUES = values();

        ProvisioningErrorType(int i) {
            this.value = i;
        }

        public static ProvisioningErrorType forNumber(int i) {
            if (i != 24) {
                if (i != 25) {
                    switch (i) {
                        case 0:
                            return UNKNOWN_PROVISIONING_ERROR;
                        case 1:
                            return NO_CONFIGURED_NETWORKS;
                        case 2:
                            return WIFI_CONN_ERROR_BAD_PSK;
                        case 3:
                            return WIFI_CONN_ERROR_INTERNAL_ERROR;
                        case 4:
                            return WIFI_CONN_ERROR_CAPTIVE_PORTAL;
                        case 5:
                            return WIFI_CONN_ERROR_LIMITED_CONNECTIVITY;
                        case 6:
                            return WIFI_CONN_UNKNOWN_ERROR;
                        case 7:
                            return REG_ERROR_FAILED_TO_GET_TOKEN_FROM_MAP;
                        case 8:
                            return REG_ERROR_TOKEN_INVALID;
                        case 9:
                            return REG_ERROR_TOKEN_EXPIRED;
                        case 10:
                            return REG_ERROR_SERVER_NOT_REACHABLE;
                        case 11:
                            return REG_ERROR_SERVER_ERROR;
                        case 12:
                            return REG_ERROR_FAILED_OTHER;
                        case 13:
                            return REG_ERROR_UNKNOWN_ERROR;
                        case 14:
                            return PROVISIONING_DONE_FAILURE;
                        case 15:
                            return PROVISIONING_VERIFICATION_TIMEOUT;
                        case 16:
                            return WIFI_CONN_ERROR_AP_NOT_FOUND;
                        case 17:
                            return NO_ASSOCIATED_DEVICE_CREDENTIALS;
                        case 18:
                            return PROVISIONING_FAILURE_REGISTRATION_ERROR_FAILED_DESERIALIZATION;
                        case 19:
                            return PROVISIONING_FAILURE_WIFI_ERROR_FAILED_DESERIALIZATION;
                        default:
                            return null;
                    }
                }
                return PROVISIONING_FAILURE_NETWORK_SYNC_TOKEN_NOT_FOUND;
            }
            return PROVISIONING_FAILURE_NETWORK_SYNC_TOKEN_INVALID;
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ErrorCodes.getDescriptor().getEnumTypes().get(4);
        }

        public static Internal.EnumLiteMap<ProvisioningErrorType> internalGetValueMap() {
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
        public static ProvisioningErrorType valueOf(int i) {
            return forNumber(i);
        }

        public static ProvisioningErrorType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
    }

    /* loaded from: classes13.dex */
    public enum Resolution implements ProtocolMessageEnum {
        CUSTOMER(1),
        CLIENT_APPLICATION(2),
        RETRY_SETUP(3),
        CLOUD_DETERMINED(4),
        UNRESOLVABLE(99);
        
        public static final int CLIENT_APPLICATION_VALUE = 2;
        public static final int CLOUD_DETERMINED_VALUE = 4;
        public static final int CUSTOMER_VALUE = 1;
        public static final int RETRY_SETUP_VALUE = 3;
        public static final int UNRESOLVABLE_VALUE = 99;
        private final int value;
        private static final Internal.EnumLiteMap<Resolution> internalValueMap = new Internal.EnumLiteMap<Resolution>() { // from class: com.amazon.whisperjoin.protobuf.ErrorCodes.Resolution.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public Resolution mo9850findValueByNumber(int i) {
                return Resolution.forNumber(i);
            }
        };
        private static final Resolution[] VALUES = values();

        Resolution(int i) {
            this.value = i;
        }

        public static Resolution forNumber(int i) {
            if (i != 1) {
                if (i == 2) {
                    return CLIENT_APPLICATION;
                }
                if (i == 3) {
                    return RETRY_SETUP;
                }
                if (i == 4) {
                    return CLOUD_DETERMINED;
                }
                if (i == 99) {
                    return UNRESOLVABLE;
                }
                return null;
            }
            return CUSTOMER;
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ErrorCodes.getDescriptor().getEnumTypes().get(9);
        }

        public static Internal.EnumLiteMap<Resolution> internalGetValueMap() {
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
        public static Resolution valueOf(int i) {
            return forNumber(i);
        }

        public static Resolution valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
    }

    /* loaded from: classes13.dex */
    public enum WebServiceErrorType implements ProtocolMessageEnum {
        UNKNOWN_WEB_SERVICE_ERROR(0),
        DISCOVERED_PROVISIONABLE_DEVICE(1),
        START_ECDHE_AUTHENTICATION_SESSION(2),
        FINALIZE_ECDHE_AUTHENTICATION_SESSION(3),
        GENERATE_PROVISIONING_SESSION(4),
        REPORT_EVENT(5),
        GET_PROVISIONABLE_STATUS(6),
        GET_WHITELIST_POLICY(7),
        COMPUTE_CONFIGURATION_DATA(8),
        GET_DEVICE_REGISTRATION_STATUS(9),
        GET_CUSTOMER_PROVISIONEES_SETUP_STATUS(10),
        GET_PROVISIONEE_DATA_FROM_SESSION_TOKEN(11),
        DISCOVERED_PROVISIONEE_DEVICE(12),
        VALIDATE_WIFI_SYNC_AUTH_TOKEN(13);
        
        public static final int COMPUTE_CONFIGURATION_DATA_VALUE = 8;
        public static final int DISCOVERED_PROVISIONABLE_DEVICE_VALUE = 1;
        public static final int DISCOVERED_PROVISIONEE_DEVICE_VALUE = 12;
        public static final int FINALIZE_ECDHE_AUTHENTICATION_SESSION_VALUE = 3;
        public static final int GENERATE_PROVISIONING_SESSION_VALUE = 4;
        public static final int GET_CUSTOMER_PROVISIONEES_SETUP_STATUS_VALUE = 10;
        public static final int GET_DEVICE_REGISTRATION_STATUS_VALUE = 9;
        public static final int GET_PROVISIONABLE_STATUS_VALUE = 6;
        public static final int GET_PROVISIONEE_DATA_FROM_SESSION_TOKEN_VALUE = 11;
        public static final int GET_WHITELIST_POLICY_VALUE = 7;
        public static final int REPORT_EVENT_VALUE = 5;
        public static final int START_ECDHE_AUTHENTICATION_SESSION_VALUE = 2;
        public static final int UNKNOWN_WEB_SERVICE_ERROR_VALUE = 0;
        public static final int VALIDATE_WIFI_SYNC_AUTH_TOKEN_VALUE = 13;
        private final int value;
        private static final Internal.EnumLiteMap<WebServiceErrorType> internalValueMap = new Internal.EnumLiteMap<WebServiceErrorType>() { // from class: com.amazon.whisperjoin.protobuf.ErrorCodes.WebServiceErrorType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public WebServiceErrorType mo9850findValueByNumber(int i) {
                return WebServiceErrorType.forNumber(i);
            }
        };
        private static final WebServiceErrorType[] VALUES = values();

        WebServiceErrorType(int i) {
            this.value = i;
        }

        public static WebServiceErrorType forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN_WEB_SERVICE_ERROR;
                case 1:
                    return DISCOVERED_PROVISIONABLE_DEVICE;
                case 2:
                    return START_ECDHE_AUTHENTICATION_SESSION;
                case 3:
                    return FINALIZE_ECDHE_AUTHENTICATION_SESSION;
                case 4:
                    return GENERATE_PROVISIONING_SESSION;
                case 5:
                    return REPORT_EVENT;
                case 6:
                    return GET_PROVISIONABLE_STATUS;
                case 7:
                    return GET_WHITELIST_POLICY;
                case 8:
                    return COMPUTE_CONFIGURATION_DATA;
                case 9:
                    return GET_DEVICE_REGISTRATION_STATUS;
                case 10:
                    return GET_CUSTOMER_PROVISIONEES_SETUP_STATUS;
                case 11:
                    return GET_PROVISIONEE_DATA_FROM_SESSION_TOKEN;
                case 12:
                    return DISCOVERED_PROVISIONEE_DEVICE;
                case 13:
                    return VALIDATE_WIFI_SYNC_AUTH_TOKEN;
                default:
                    return null;
            }
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ErrorCodes.getDescriptor().getEnumTypes().get(5);
        }

        public static Internal.EnumLiteMap<WebServiceErrorType> internalGetValueMap() {
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
        public static WebServiceErrorType valueOf(int i) {
            return forNumber(i);
        }

        public static WebServiceErrorType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
    }

    /* loaded from: classes13.dex */
    public enum WorkflowErrorType implements ProtocolMessageEnum {
        UNKNOWN_WORKFLOW_ERROR(0),
        DEVICE_RECENTLY_PROVISIONED(1);
        
        public static final int DEVICE_RECENTLY_PROVISIONED_VALUE = 1;
        public static final int UNKNOWN_WORKFLOW_ERROR_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<WorkflowErrorType> internalValueMap = new Internal.EnumLiteMap<WorkflowErrorType>() { // from class: com.amazon.whisperjoin.protobuf.ErrorCodes.WorkflowErrorType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public WorkflowErrorType mo9850findValueByNumber(int i) {
                return WorkflowErrorType.forNumber(i);
            }
        };
        private static final WorkflowErrorType[] VALUES = values();

        WorkflowErrorType(int i) {
            this.value = i;
        }

        public static WorkflowErrorType forNumber(int i) {
            if (i != 0) {
                if (i == 1) {
                    return DEVICE_RECENTLY_PROVISIONED;
                }
                return null;
            }
            return UNKNOWN_WORKFLOW_ERROR;
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return ErrorCodes.getDescriptor().getEnumTypes().get(6);
        }

        public static Internal.EnumLiteMap<WorkflowErrorType> internalGetValueMap() {
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
        public static WorkflowErrorType valueOf(int i) {
            return forNumber(i);
        }

        public static WorkflowErrorType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nYWhisperJoinProtocolBuffersModel/schema/provisioning/data/errorcodes/ProtoErrorCodes.proto\u0012\bprotobuf*É\u0001\n\u0006Domain\u0012\u0018\n\u0014PRECONDITION_FAILURE\u0010\u0001\u0012\u0016\n\u0012CONNECTION_FAILURE\u0010\u0002\u0012\u0018\n\u0014PROVISIONING_FAILURE\u0010\u0003\u0012\u0017\n\u0013BLE_COMMAND_FAILURE\u0010\u0004\u0012\u001b\n\u0017WEB_SERVICE_CALL_FAILUE\u0010\u0005\u0012\u0014\n\u0010WORKFLOW_FAILURE\u0010\u0006\u0012\u0011\n\rOTHER_FAILURE\u0010c\u0012\u0014\n\u0010INTERNAL_FAILURE\u0010d*·\u0003\n\u0013BLECommandErrorType\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\u0016\n\u0012EXCHANGE_ECDHE_KEY\u0010\u0001\u0012\u001b\n\u0017EXCHANGE_AUTH_ECDHE_KEY\u0010\u0002\u0012\u0011\n\rJPAKE_ROUND_1\u0010\u0003\u0012\u0011\n\rJPAKE_ROUND_2\u0010\u0004\u0012\u0011\n\rJPAKE_ROUND_3\u0010\u0005\u0012\u0019\n\u0015JPAKE_CERT_VALIDATION\u0010\u0006\u0012\u0018\n\u0014GET_VISIBLE_NETWORKS\u0010\u0007\u0012\u0016\n\u0012GET_DEVICE_DETAILS\u0010\b\u0012\u0010\n\fSAVE_NETWORK\u0010\t\u0012\u0019\n\u0015GET_CONNECTION_STATUS\u0010\n\u0012\u0015\n\u0011SET_CONFIGURATION\u0010\u000b\u0012\u001a\n\u0016SET_REGISTRATION_TOKEN\u0010\f\u0012\u001b\n\u0017GET_REGISTRATION_STATUS\u0010\r\u0012\u0019\n\u0015COMPLETE_PROVISIONING\u0010\u000e\u0012\u001f\n\u001bGET_SUPPORTED_NOTIFICATIONS\u0010\u000f\u0012\u001f\n\u001bGET_NOTIFICATION_EVENT_DATA\u0010\u0010*ö\u0001\n\u0013ConnectionErrorType\u0012\u001c\n\u0018UNKNOWN_CONNECTION_ERROR\u0010\u0000\u0012\"\n\u001eUNABLE_TO_ESTABLISH_CONNECTION\u0010\u0001\u0012&\n\"UNABLE_TO_ESTABLISH_SECURE_CHANNEL\u0010\u0002\u0012%\n!START_PROVISIONING_REQUEST_FAILED\u0010\u0003\u0012\u001e\n\u001aUNEXPECTED_CONNECTION_DROP\u0010\u0004\u0012.\n*PROVISIONING_COMMAND_DESERIALIZATION_ERROR\u0010\u0005*·\u0001\n\u0015PreconditionErrorType\u0012\u001e\n\u001aUNKNOWN_PRECONDITION_ERROR\u0010\u0000\u0012\u0019\n\u0015BLUETOOTH_NOT_ENABLED\u0010\u0001\u0012&\n\"BLUETOOTH_LOW_ENERGY_NOT_SUPPORTED\u0010\u0002\u0012\u0015\n\u0011NO_USER_LOGGED_IN\u0010\u0003\u0012$\n REQUIRED_PERMISSIONS_NOT_GRANTED\u0010\u0004*Ø\u0006\n\u0015ProvisioningErrorType\u0012\u001e\n\u001aUNKNOWN_PROVISIONING_ERROR\u0010\u0000\u0012\u001a\n\u0016NO_CONFIGURED_NETWORKS\u0010\u0001\u0012\u001b\n\u0017WIFI_CONN_ERROR_BAD_PSK\u0010\u0002\u0012\"\n\u001eWIFI_CONN_ERROR_INTERNAL_ERROR\u0010\u0003\u0012\"\n\u001eWIFI_CONN_ERROR_CAPTIVE_PORTAL\u0010\u0004\u0012(\n$WIFI_CONN_ERROR_LIMITED_CONNECTIVITY\u0010\u0005\u0012\u001b\n\u0017WIFI_CONN_UNKNOWN_ERROR\u0010\u0006\u0012*\n&REG_ERROR_FAILED_TO_GET_TOKEN_FROM_MAP\u0010\u0007\u0012\u001b\n\u0017REG_ERROR_TOKEN_INVALID\u0010\b\u0012\u001b\n\u0017REG_ERROR_TOKEN_EXPIRED\u0010\t\u0012\"\n\u001eREG_ERROR_SERVER_NOT_REACHABLE\u0010\n\u0012\u001a\n\u0016REG_ERROR_SERVER_ERROR\u0010\u000b\u0012\u001a\n\u0016REG_ERROR_FAILED_OTHER\u0010\f\u0012\u001b\n\u0017REG_ERROR_UNKNOWN_ERROR\u0010\r\u0012\u001d\n\u0019PROVISIONING_DONE_FAILURE\u0010\u000e\u0012%\n!PROVISIONING_VERIFICATION_TIMEOUT\u0010\u000f\u0012 \n\u001cWIFI_CONN_ERROR_AP_NOT_FOUND\u0010\u0010\u0012$\n NO_ASSOCIATED_DEVICE_CREDENTIALS\u0010\u0011\u0012B\n>PROVISIONING_FAILURE_REGISTRATION_ERROR_FAILED_DESERIALIZATION\u0010\u0012\u0012:\n6PROVISIONING_FAILURE_WIFI_ERROR_FAILED_DESERIALIZATION\u0010\u0013\u00123\n/PROVISIONING_FAILURE_NETWORK_SYNC_TOKEN_INVALID\u0010\u0018\u00125\n1PROVISIONING_FAILURE_NETWORK_SYNC_TOKEN_NOT_FOUND\u0010\u0019*ü\u0003\n\u0013WebServiceErrorType\u0012\u001d\n\u0019UNKNOWN_WEB_SERVICE_ERROR\u0010\u0000\u0012#\n\u001fDISCOVERED_PROVISIONABLE_DEVICE\u0010\u0001\u0012&\n\"START_ECDHE_AUTHENTICATION_SESSION\u0010\u0002\u0012)\n%FINALIZE_ECDHE_AUTHENTICATION_SESSION\u0010\u0003\u0012!\n\u001dGENERATE_PROVISIONING_SESSION\u0010\u0004\u0012\u0010\n\fREPORT_EVENT\u0010\u0005\u0012\u001c\n\u0018GET_PROVISIONABLE_STATUS\u0010\u0006\u0012\u0018\n\u0014GET_WHITELIST_POLICY\u0010\u0007\u0012\u001e\n\u001aCOMPUTE_CONFIGURATION_DATA\u0010\b\u0012\"\n\u001eGET_DEVICE_REGISTRATION_STATUS\u0010\t\u0012*\n&GET_CUSTOMER_PROVISIONEES_SETUP_STATUS\u0010\n\u0012+\n'GET_PROVISIONEE_DATA_FROM_SESSION_TOKEN\u0010\u000b\u0012!\n\u001dDISCOVERED_PROVISIONEE_DEVICE\u0010\f\u0012!\n\u001dVALIDATE_WIFI_SYNC_AUTH_TOKEN\u0010\r*P\n\u0011WorkflowErrorType\u0012\u001a\n\u0016UNKNOWN_WORKFLOW_ERROR\u0010\u0000\u0012\u001f\n\u001bDEVICE_RECENTLY_PROVISIONED\u0010\u0001*#\n\u000eOtherErrorType\u0012\u0011\n\rUNKNOWN_ERROR\u0010\u0001*'\n\u0011InternalErrorType\u0012\u0012\n\u000eSCAP_BLE_ERROR\u0010\u0001*k\n\nResolution\u0012\f\n\bCUSTOMER\u0010\u0001\u0012\u0016\n\u0012CLIENT_APPLICATION\u0010\u0002\u0012\u000f\n\u000bRETRY_SETUP\u0010\u0003\u0012\u0014\n\u0010CLOUD_DETERMINED\u0010\u0004\u0012\u0010\n\fUNRESOLVABLE\u0010cB-\n\u001fcom.amazon.whisperjoin.protobufB\nErrorCodes"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ErrorCodes.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ErrorCodes.descriptor = fileDescriptor;
                return null;
            }
        });
    }

    private ErrorCodes() {
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
