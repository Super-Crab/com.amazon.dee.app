package com.amazon.rtcsc.wrappers;

import com.amazon.alexa.smarthomecameras.constants.ErrorConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public final class RTCSCErrorCode {
    private final String swigName;
    private final int swigValue;
    public static final RTCSCErrorCode SUCCESS = new RTCSCErrorCode("SUCCESS");
    public static final RTCSCErrorCode NOT_INITIALIZED = new RTCSCErrorCode("NOT_INITIALIZED");
    public static final RTCSCErrorCode ALREADY_INITIALIZED = new RTCSCErrorCode("ALREADY_INITIALIZED");
    public static final RTCSCErrorCode NULL_LISTENER = new RTCSCErrorCode("NULL_LISTENER");
    public static final RTCSCErrorCode UNKNOWN_DIRECTIVE = new RTCSCErrorCode(ErrorConstants.UNKNOWN_DIRECTIVE);
    public static final RTCSCErrorCode DIRECTIVE_PARSE_ERROR = new RTCSCErrorCode("DIRECTIVE_PARSE_ERROR");
    public static final RTCSCErrorCode INITIALIZE_FAILED = new RTCSCErrorCode("INITIALIZE_FAILED");
    public static final RTCSCErrorCode LISTENER_REGISTRATION_FAILED = new RTCSCErrorCode("LISTENER_REGISTRATION_FAILED");
    public static final RTCSCErrorCode SESSION_ID_NOT_FOUND = new RTCSCErrorCode("SESSION_ID_NOT_FOUND");
    public static final RTCSCErrorCode LISTENER_PREVIOUSLY_REGISTERED = new RTCSCErrorCode("LISTENER_PREVIOUSLY_REGISTERED");
    public static final RTCSCErrorCode LISTENER_NOT_FOUND = new RTCSCErrorCode("LISTENER_NOT_FOUND");
    public static final RTCSCErrorCode NULL_METRICS_PUBLISHER_ADAPTER = new RTCSCErrorCode("NULL_METRICS_PUBLISHER_ADAPTER");
    public static final RTCSCErrorCode METRICS_PUBLISHER_ADAPTER_PREVIOUSLY_REGISTERED = new RTCSCErrorCode("METRICS_PUBLISHER_ADAPTER_PREVIOUSLY_REGISTERED");
    public static final RTCSCErrorCode EMPTY_APP_IDENTIFIER = new RTCSCErrorCode("EMPTY_APP_IDENTIFIER");
    public static final RTCSCErrorCode INVALID_DEVICE_CONFIGURATION = new RTCSCErrorCode("INVALID_DEVICE_CONFIGURATION");
    public static final RTCSCErrorCode INTERNAL_SESSION_ERROR = new RTCSCErrorCode("INTERNAL_SESSION_ERROR");
    public static final RTCSCErrorCode CAMERA_NOT_AVAILABLE = new RTCSCErrorCode("CAMERA_NOT_AVAILABLE");
    public static final RTCSCErrorCode MEDIA_CONNECTION_FAILED = new RTCSCErrorCode("MEDIA_CONNECTION_FAILED");
    public static final RTCSCErrorCode MEDIA_CONNECTION_LOST = new RTCSCErrorCode("MEDIA_CONNECTION_LOST");
    public static final RTCSCErrorCode SESSION_TIMEOUT = new RTCSCErrorCode("SESSION_TIMEOUT");
    public static final RTCSCErrorCode VIDEO_EFFECT_FAILED = new RTCSCErrorCode("VIDEO_EFFECT_FAILED");
    private static RTCSCErrorCode[] swigValues = {SUCCESS, NOT_INITIALIZED, ALREADY_INITIALIZED, NULL_LISTENER, UNKNOWN_DIRECTIVE, DIRECTIVE_PARSE_ERROR, INITIALIZE_FAILED, LISTENER_REGISTRATION_FAILED, SESSION_ID_NOT_FOUND, LISTENER_PREVIOUSLY_REGISTERED, LISTENER_NOT_FOUND, NULL_METRICS_PUBLISHER_ADAPTER, METRICS_PUBLISHER_ADAPTER_PREVIOUSLY_REGISTERED, EMPTY_APP_IDENTIFIER, INVALID_DEVICE_CONFIGURATION, INTERNAL_SESSION_ERROR, CAMERA_NOT_AVAILABLE, MEDIA_CONNECTION_FAILED, MEDIA_CONNECTION_LOST, SESSION_TIMEOUT, VIDEO_EFFECT_FAILED};
    private static int swigNext = 0;

    private RTCSCErrorCode(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static RTCSCErrorCode swigToEnum(int i) {
        RTCSCErrorCode[] rTCSCErrorCodeArr = swigValues;
        if (i >= rTCSCErrorCodeArr.length || i < 0 || rTCSCErrorCodeArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                RTCSCErrorCode[] rTCSCErrorCodeArr2 = swigValues;
                if (i2 < rTCSCErrorCodeArr2.length) {
                    if (rTCSCErrorCodeArr2[i2].swigValue == i) {
                        return rTCSCErrorCodeArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", RTCSCErrorCode.class, " with value ", i));
                }
            }
        } else {
            return rTCSCErrorCodeArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private RTCSCErrorCode(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private RTCSCErrorCode(String str, RTCSCErrorCode rTCSCErrorCode) {
        this.swigName = str;
        this.swigValue = rTCSCErrorCode.swigValue;
        swigNext = this.swigValue + 1;
    }
}
