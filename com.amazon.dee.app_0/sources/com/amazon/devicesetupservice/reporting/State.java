package com.amazon.devicesetupservice.reporting;
/* loaded from: classes12.dex */
public class State {
    public static final String CONNECTED = "CONNECTED";
    public static final String DONE = "DONE";
    public static final String REGISTERED = "REGISTERED";
    public static final String REGISTRATION_COMPLETE = "REGISTRATION_COMPLETE";
    public static final String TOKEN_REDEEMED = "TOKEN_REDEEMED";
    public static final String DISCOVERED = "DISCOVERED";
    public static final String SECURE_CHANNEL_ESTABLISHED = "SECURE_CHANNEL_ESTABLISHED";
    public static final String RETRIEVED_PROVISIONING_DETAILS = "RETRIEVED_PROVISIONING_DETAILS";
    public static final String PROVISIONED = "PROVISIONED";
    public static final String NETWORKED = "NETWORKED";
    public static final String PROVISIONER_COMPLETE = "PROVISIONER_COMPLETE";
    public static final String STARTED_AUTHENTICATION = "STARTED_AUTHENTICATION";
    public static final String FINALIZED_AUTHENTICATION = "FINALIZED_AUTHENTICATION";
    public static final String GET_CREDENTIALS = "GET_CREDENTIALS";
    public static final String SAVED_NETWORK = "SAVED_NETWORK";
    private static final String[] values = {DISCOVERED, "CONNECTED", SECURE_CHANNEL_ESTABLISHED, RETRIEVED_PROVISIONING_DETAILS, PROVISIONED, NETWORKED, "REGISTERED", "DONE", PROVISIONER_COMPLETE, "REGISTRATION_COMPLETE", "TOKEN_REDEEMED", STARTED_AUTHENTICATION, FINALIZED_AUTHENTICATION, GET_CREDENTIALS, SAVED_NETWORK};

    private State() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
