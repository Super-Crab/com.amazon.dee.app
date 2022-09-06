package com.amazon.devicesetupservice.reporting;
/* loaded from: classes12.dex */
public class RegistrationState {
    public static final String NOT_REGISTERED = "NOT_REGISTERED";
    public static final String REGISTERED = "REGISTERED";
    public static final String REGISTRATION_COMPLETE = "REGISTRATION_COMPLETE";
    public static final String COMPLETING_REGISTRATION = "COMPLETING_REGISTRATION";
    public static final String REGISTRATION_FAILED_TOKEN_INVALID = "REGISTRATION_FAILED_TOKEN_INVALID";
    public static final String REGISTRATION_FAILED_TOKEN_EXPIRED = "REGISTRATION_FAILED_TOKEN_EXPIRED";
    public static final String REGISTRATION_FAILED_SERVER_NOT_REACHABLE = "REGISTRATION_FAILED_SERVER_NOT_REACHABLE";
    public static final String REGISTRATION_FAILED_SERVER_ERROR = "REGISTRATION_FAILED_SERVER_ERROR";
    public static final String REGISTRATION_FAILED_OTHER = "REGISTRATION_FAILED_OTHER";
    private static final String[] values = {"NOT_REGISTERED", COMPLETING_REGISTRATION, "REGISTRATION_COMPLETE", REGISTRATION_FAILED_TOKEN_INVALID, REGISTRATION_FAILED_TOKEN_EXPIRED, REGISTRATION_FAILED_SERVER_NOT_REACHABLE, REGISTRATION_FAILED_SERVER_ERROR, REGISTRATION_FAILED_OTHER, "REGISTERED"};

    private RegistrationState() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
