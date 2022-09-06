package com.amazon.devicesetup.provisioning.data.registration;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class CBLRegistrationDetails {
    private static final String HTTP_CODE_KEY = "http-code";
    private static final String MESSAGE_KEY = "message";
    private static final String STATE_KEY = "state";
    private final Integer mHttpCode;
    private final String mMessage;
    private final State mState;

    /* loaded from: classes12.dex */
    public enum State {
        NOT_REGISTERED(0, "Not Registered"),
        COMPLETING_REGISTRATION(1, "Completing Registration"),
        REGISTRATION_COMPLETE(2, "Registered"),
        REGISTRATION_FAILED_TOKEN_INVALID(-1, "Registration Failed, Token Invalid"),
        REGISTRATION_FAILED_TOKEN_EXPIRED(-2, "Registration Failed, Token Expired"),
        REGISTRATION_FAILED_SERVER_NOT_REACHABLE(-3, "Registration Failed, Server Not Reachable"),
        REGISTRATION_FAILED_SERVER_ERROR(-4, "Registration Failed, Server Error"),
        REGISTRATION_FAILED_OTHER(-5, "Registration Failed, Other");
        
        private String mDescription;
        private final int mValue;

        State(int i, String str) {
            this.mValue = i;
            this.mDescription = str;
        }

        private static State fromInt(int i) {
            switch (i) {
                case -5:
                    return REGISTRATION_FAILED_OTHER;
                case -4:
                    return REGISTRATION_FAILED_SERVER_ERROR;
                case -3:
                    return REGISTRATION_FAILED_SERVER_NOT_REACHABLE;
                case -2:
                    return REGISTRATION_FAILED_TOKEN_EXPIRED;
                case -1:
                    return REGISTRATION_FAILED_TOKEN_INVALID;
                case 0:
                    return NOT_REGISTERED;
                case 1:
                    return COMPLETING_REGISTRATION;
                case 2:
                    return REGISTRATION_COMPLETE;
                default:
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown CBLRegistrationDetails.State: ", i));
            }
        }

        public String getDescription() {
            return this.mDescription;
        }

        public int getValue() {
            return this.mValue;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mDescription;
        }
    }

    public CBLRegistrationDetails(State state) {
        this(state, null, null);
    }

    public Integer getHttpCode() {
        return this.mHttpCode;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public State getRegistrationState() {
        return this.mState;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CBLRegistrationDetails [state=");
        outline107.append(this.mState);
        outline107.append(", message=");
        outline107.append(this.mMessage);
        outline107.append(", http-code=");
        outline107.append(this.mHttpCode);
        outline107.append("]");
        return outline107.toString();
    }

    protected void validate() {
        Integer num = this.mHttpCode;
        if (num == null || num.intValue() >= 0) {
            return;
        }
        throw new IllegalArgumentException("Http code cannot be negative. Please use InputValidator methods to validate parameters.");
    }

    public CBLRegistrationDetails(State state, String str, Integer num) {
        this.mState = state;
        this.mMessage = str;
        this.mHttpCode = num;
        validate();
    }
}
