package com.amazon.device.setup.thrift;

import org.apache.thrift.orig.TEnum;
/* loaded from: classes12.dex */
public enum ReturnError implements TEnum {
    NO_ERROR(0),
    UNKNOWN_ERROR(1),
    INVALID_ARGUMENTS_ERROR(2),
    BAD_PASSWORD_ERROR(3),
    TIMEOUT_ERROR(4),
    SERVER_ERROR(5),
    ROUTE_CONFLICT_ERROR(6),
    CAPTIVE_PORTAL_ERROR(7),
    CONNECTED_LOCAL_ERROR(8),
    DELAYED_CONNECTION_STARTED(9);
    
    private final int value;

    ReturnError(int i) {
        this.value = i;
    }

    public static ReturnError findByValue(int i) {
        switch (i) {
            case 0:
                return NO_ERROR;
            case 1:
                return UNKNOWN_ERROR;
            case 2:
                return INVALID_ARGUMENTS_ERROR;
            case 3:
                return BAD_PASSWORD_ERROR;
            case 4:
                return TIMEOUT_ERROR;
            case 5:
                return SERVER_ERROR;
            case 6:
                return ROUTE_CONFLICT_ERROR;
            case 7:
                return CAPTIVE_PORTAL_ERROR;
            case 8:
                return CONNECTED_LOCAL_ERROR;
            case 9:
                return DELAYED_CONNECTION_STARTED;
            default:
                return null;
        }
    }

    @Override // org.apache.thrift.orig.TEnum
    public int getValue() {
        return this.value;
    }
}
