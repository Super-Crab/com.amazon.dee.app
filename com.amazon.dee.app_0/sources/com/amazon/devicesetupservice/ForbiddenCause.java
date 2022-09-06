package com.amazon.devicesetupservice;
/* loaded from: classes12.dex */
public class ForbiddenCause {
    public static final String OTHER = "OTHER";
    public static final String NO_ASSOCIATION_EXISTS = "NO_ASSOCIATION_EXISTS";
    public static final String NO_REGISTERED_CUSTOMER = "NO_REGISTERED_CUSTOMER";
    public static final String NOT_WHITELISTED_INTERNAL_ACCOUNT = "NOT_WHITELISTED_INTERNAL_ACCOUNT";
    public static final String AUTHENTICATED_SETUP_NOT_SUPPORTED = "AUTHENTICATED_SETUP_NOT_SUPPORTED";
    public static final String UNSUPPORTED_ASSOCIATION = "UNSUPPORTED_ASSOCIATION";
    public static final String INVALID_PROVISIONER = "INVALID_PROVISIONER";
    public static final String NOT_ASSOCIATED_TO_CUSTOMER = "NOT_ASSOCIATED_TO_CUSTOMER";
    public static final String INVALID_DHA_SIGNATURE = "INVALID_DHA_SIGNATURE";
    public static final String SIDEWALK_PROVISIONER_CUSTOMER_OPTED_OUT = "SIDEWALK_PROVISIONER_CUSTOMER_OPTED_OUT";
    public static final String UNSUPPORTED_PROVISIONEE_FOR_ANONYMOUS_PROVISIONING = "UNSUPPORTED_PROVISIONEE_FOR_ANONYMOUS_PROVISIONING";
    public static final String ANONYMOUS_PROVISIONING_BLOCKED_PROVISIONER = "ANONYMOUS_PROVISIONING_BLOCKED_PROVISIONER";
    public static final String ANONYMOUS_PROVISIONING_BLOCKED_PROVISIONEE = "ANONYMOUS_PROVISIONING_BLOCKED_PROVISIONEE";
    public static final String ANONYMOUS_PROVISIONING_BLOCKED_REGION = "ANONYMOUS_PROVISIONING_BLOCKED_REGION";
    private static final String[] values = {NO_ASSOCIATION_EXISTS, NO_REGISTERED_CUSTOMER, NOT_WHITELISTED_INTERNAL_ACCOUNT, AUTHENTICATED_SETUP_NOT_SUPPORTED, UNSUPPORTED_ASSOCIATION, INVALID_PROVISIONER, NOT_ASSOCIATED_TO_CUSTOMER, INVALID_DHA_SIGNATURE, SIDEWALK_PROVISIONER_CUSTOMER_OPTED_OUT, UNSUPPORTED_PROVISIONEE_FOR_ANONYMOUS_PROVISIONING, ANONYMOUS_PROVISIONING_BLOCKED_PROVISIONER, ANONYMOUS_PROVISIONING_BLOCKED_PROVISIONEE, ANONYMOUS_PROVISIONING_BLOCKED_REGION, "OTHER"};

    private ForbiddenCause() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
