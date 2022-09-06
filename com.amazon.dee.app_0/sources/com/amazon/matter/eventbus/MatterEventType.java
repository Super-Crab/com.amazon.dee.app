package com.amazon.matter.eventbus;
/* loaded from: classes9.dex */
public enum MatterEventType {
    MANUAL_CODE_PARSER_REQUEST("Matter:parseManualCode:request"),
    MANUAL_CODE_PARSER_RESPONSE_SUCCESS("Matter:parseManualCode:response:success"),
    MANUAL_CODE_PARSER_RESPONSE_ERROR("Matter:parseManualCode:response:error"),
    QR_CODE_PARSER_REQUEST("Matter:parseQRCode:request"),
    QR_CODE_PARSER_RESPONSE_SUCCESS("Matter:parseQRCode:response:success"),
    QR_CODE_PARSER_RESPONSE_ERROR("Matter:parseQRCode:response:error"),
    PAIR_DEVICE_REQUEST("Matter:pairDevice:request"),
    PAIR_DEVICE_RESPONSE_SUCCESS("Matter:pairDevice:response:success"),
    PAIR_DEVICE_RESPONSE_ERROR("Matter:pairDevice:response:error"),
    ADD_AND_ENABLE_NETWORK_REQUEST("Matter:addAndEnableNetwork:request"),
    ADD_AND_ENABLE_NETWORK_RESPONSE_SUCCESS("Matter:addAndEnableNetwork:response:success"),
    ADD_AND_ENABLE_NETWORK_RESPONSE_ERROR("Matter:addAndEnableNetwork:response:error"),
    GENERIC_EVENT("Matter:*");
    
    private final String eventTypeString;

    MatterEventType(String str) {
        this.eventTypeString = str;
    }

    public static MatterEventType getEventTypeFromString(String str) {
        MatterEventType[] values;
        for (MatterEventType matterEventType : values()) {
            if (matterEventType.eventTypeString.equals(str)) {
                return matterEventType;
            }
        }
        return GENERIC_EVENT;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.eventTypeString;
    }
}
