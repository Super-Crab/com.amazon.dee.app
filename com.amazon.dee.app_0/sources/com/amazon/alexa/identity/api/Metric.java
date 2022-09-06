package com.amazon.alexa.identity.api;
/* loaded from: classes9.dex */
public final class Metric {

    /* loaded from: classes9.dex */
    public static final class Attribute {
        public static final String STATUS_CODE = "statusCode";

        private Attribute() {
        }
    }

    /* loaded from: classes9.dex */
    public static final class Component {
        public static final String IDENTITY_SERVICE = "IdentityService";
        public static final String PERSONID = "PersonID";
        public static final String REAUTHENTICATION = "Reauthentication";

        private Component() {
        }
    }

    /* loaded from: classes9.dex */
    public static final class Event {
        public static final String ACCESS_TOKEN_REQUEST_EMPTY_TOKEN_FROM_MAP = "ACCESS_TOKEN_REQUEST_EMPTY_TOKEN_FROM_MAP";
        public static final String ACCESS_TOKEN_REQUEST_EMPTY_TOKEN_ON_IDENTITY = "ACCESS_TOKEN_REQUEST_EMPTY_TOKEN_ON_IDENTITY";
        public static final String ACCESS_TOKEN_REQUEST_EMPTY_TOKEN_WHEN_NOT_CONNECTED = "ACCESS_TOKEN_REQUEST_EMPTY_TOKEN_WHEN_CONNECTED";
        public static final String ACCESS_TOKEN_REQUEST_NULL_IDENTITY = "ACCESS_TOKEN_REQUEST_NULL_IDENTITY";
        public static final String ACCOUNT_ALREADY_EXISTS = "ACCOUNT_ALREADY_EXISTS";
        public static final String ALREADY_DEREGISTERED = "ALREADY_DEREGISTERED";
        public static final String APP_COLD_SIGN_IN_SUCCESS = "APP_COLD_SIGN_IN_SUCCESS";
        public static final String APP_WARM_SIGN_IN_SUCCESS = "APP_WARM_SIGN_IN_SUCCESS";
        public static final String AUTHENTICATION_CHALLENGED = "AUTHENTICATION_CHALLENGED";
        public static final String BAD_SECRET = "BAD_SECRET";
        public static final String CALL_APES_PARSE_EXCEPTION = "CALL_APES_PARSE_EXCEPTION";
        public static final String CALL_APES_SUCCESS_RATE = "CALL_APES_SUCCESS_RATE";
        public static final String COMMS_OOBE_PROFILE_SELECTION_SUCCESS = "COMMS_USER_PROFILE_SELECTION_SUCCESS";
        public static final String CUSTOMER_NOT_FOUND = "CUSTOMER_NOT_FOUND";
        public static final String DELEGATEE_ACCOUNT_ALREADY_DEREGISTERED = "DELEGATEE_ACCOUNT_ALREADY_DEREGISTERED";
        public static final String DEREGISTER_FAILED = "DEREGISTER_FAILED";
        public static final String DEVICE_ALREADY_REGISTERED = "DEVICE_ALREADY_REGISTERED";
        public static final String DUPLICATE_DEVICE_NAME = "DUPLICATE_DEVICE_NAME";
        public static final String GET_ACTOR_TOKEN_ACTOR_NOT_ASSOCIATED = "GET_ACTOR_TOKEN_ACTOR_NOT_ASSOCIATED";
        public static final String GET_ACTOR_TOKEN_DURATION = "GET_ACTOR_TOKEN_DURATION";
        public static final String GET_ACTOR_TOKEN_SUCCESS = "GET_ACTOR_TOKEN_SUCCESS";
        public static final String GET_DELEGATION_TOKEN_DURATION = "GET_DELEGATION_TOKEN_DURATION";
        public static final String GET_DELEGATION_TOKEN_SUCCESS_RATE = "GET_DELEGATION_TOKEN_SUCCESS_RATE";
        public static final String GET_DELEGATION_TOKEN_WITHOUT_ACTIVE_DELEGATION = "GET_DELEGATION_TOKEN_WITHOUT_ACTIVE_DELEGATION";
        public static final String GET_PERSONID_DURATION = "GET_PERSONID_DURATION";
        public static final String GET_SESSION_ID_SUCCESS_RATE = "GET_SESSION_ID_SUCCESS_RATE";
        public static final String INITIATE_DELEGATION_DURATION = "INITIATE_DELEGATION_DURATION";
        public static final String INITIATE_DELEGATION_SUCCESS_RATE = "INITIATE_DELEGATION_SUCCESS_RATE";
        public static final String IS_AUTHENTICATED_DURATION = "IS_AUTHENTICATED_DURATION";
        public static final String IS_AUTHENTICATED_V2_DURATION = "IS_AUTHENTICATED_V2_DURATION";
        public static final String LOGIN_AUTH_ERROR = "LOGIN_AUTH_ERROR";
        public static final String LOGIN_COOKIE_ERROR = "LOGIN_COOKIE_ERROR";
        public static final String LOGIN_TOKEN_ERROR = "LOGIN_TOKEN_ERROR";
        public static final String LOGIN_USER_CANCEL = "LOGIN_USER_CANCEL";
        @Deprecated
        public static final String LOGOUT_FAILED_ERROR = "LOGOUT_FAILED_ERROR";
        public static final String LOGOUT_SUCCESS = "LOGOUT_SUCCESS";
        public static final String MAP_COOKIES_FETCH_FAILURE = "MAP_COOKIES_FETCH_FAILURE";
        public static final String MATCH_PERSONID_SUCCESS_RATE = "MATCH_PERSONID_SUCCESS_RATE";
        public static final String NETWORK_FAILURE = "NETWORK_FAILURE";
        public static final String NETWORK_REQUEST_ERROR = "NETWORK_REQUEST_ERROR";
        public static final String NETWORK_SSL_ERROR = "NETWORK_SSL_ERROR";
        public static final String NO_ACCOUNT = "NO_ACCOUNT";
        public static final String PERSONID_DIRID_NULL = "PERSONID_DIRID_NULL";
        public static final String PERSONID_REQUESTED = "PERSONID_REQUESTED";
        public static final String PERSONID_USR_PRFL_DIRID_NULL = "PERSONID_USR_PRFL_DIRID_NULL";
        public static final String PROFILE_UNAUTHENTICATED_LOGOUT_SUCCESS = "PROFILE_UNAUTHENTICATED_LOGOUT_SUCCESS";
        public static final String REAUTHENTICATION_ATTEMPT = "reauthentication";
        public static final String REAUTHENTICATION_CANCELLED = "reauthentication.cancel";
        public static final String REAUTHENTICATION_ERROR = "reauthentication.error";
        public static final String REAUTHENTICATION_SUCCESS = "reauthentication.success";
        public static final String REFRESH_AUTH_TOKEN_SUCCESS_RATE = "REFRESH_AUTH_TOKEN_SUCCESS_RATE";
        public static final String REFRESH_COOKIES_SUCCESS_RATE = "REFRESH_COOKIES_SUCCESS_RATE";
        public static final String REFRESH_COOKIE_FAILURE_WHEN_NOT_CONNECTED = "REFRESH_COOKIE_FAILURE_WHEN_NOT_CONNECTED";
        public static final String REGISTER_SECONDARY_ACCOUNT_DURATION = "REGISTER_SECONDARY_ACCOUNT_DURATION";
        public static final String REGISTER_SECONDARY_ACCOUNT_SUCCESS = "REGISTER_SECONDARY_ACCOUNT_SUCCESS";
        public static final String REQUIRED_3P_AUTHENTICATION = "REQUIRED_3P_AUTHENTICATION";
        public static final String RETRIEVE_ACTOR_TOKEN_COMPLETE = "RETRIEVE_ACTOR_TOKEN_COMPLETE";
        public static final String RETRIEVE_ACTOR_TOKEN_CONNECTIVITY_FAIL = "RETRIEVE_ACTOR_TOKEN_CONNECTIVITY_FAIL";
        public static final String RETRIEVE_ACTOR_TOKEN_EXECUTE_GET_FAIL = "RETRIEVE_ACTOR_TOKEN_EXECUTE_GET_FAIL";
        public static final String RETRIEVE_ACTOR_TOKEN_FAIL_NO_USERPROFILE_SELECTED = "RETRIEVE_ACTOR_TOKEN_FAIL_NO_USERPROFILE_SELECTED";
        public static final String RETRIEVE_ACTOR_TOKEN_FAIL_NULL_PERSONID = "RETRIEVE_ACTOR_TOKEN_FAIL_NULL_PERSONID";
        public static final String RETRIEVE_ACTOR_TOKEN_GET_FAIL = "RETRIEVE_ACTOR_TOKEN_GET_FAIL";
        public static final String RETRIEVE_ACTOR_TOKEN_INTERRUPT_GET_FAIL = "RETRIEVE_ACTOR_TOKEN_INTERRUPT_GET_FAIL";
        public static final String RETRIEVE_ACTOR_TOKEN_MAP_GET_FAIL = "RETRIEVE_ACTOR_TOKEN_MAP_GET_FAIL";
        public static final String RETRIEVE_ACTOR_TOKEN_REGISTER_FAIL = "RETRIEVE_ACTOR_TOKEN_REGISTER_FAIL";
        public static final String RETRIEVE_ACTOR_TOKEN_START = "RETRIEVE_ACTOR_TOKEN_START";
        public static final String RETRIEVE_AUTH_TOKEN_COMPLETE = "RETRIEVE_AUTH_TOKEN_COMPLETE";
        public static final String RETRIEVE_AUTH_TOKEN_CONNECTIVITY_FAIL = "RETRIEVE_AUTH_TOKEN_CONNECTIVITY_FAIL";
        public static final String RETRIEVE_AUTH_TOKEN_EXECUTE_GET_FAIL = "RETRIEVE_AUTH_TOKEN_EXECUTE_GET_FAIL";
        public static final String RETRIEVE_AUTH_TOKEN_GET_FAIL = "RETRIEVE_AUTH_TOKEN_GET_FAIL";
        public static final String RETRIEVE_AUTH_TOKEN_INTERRUPT_GET_FAIL = "RETRIEVE_AUTH_TOKEN_INTERRUPT_GET_FAIL";
        public static final String RETRIEVE_AUTH_TOKEN_MAP_GET_FAIL = "RETRIEVE_AUTH_TOKEN_MAP_GET_FAIL";
        public static final String RETRIEVE_AUTH_TOKEN_REGISTER_FAIL = "RETRIEVE_AUTH_TOKEN_REGISTER_FAIL";
        public static final String RETRIEVE_AUTH_TOKEN_START = "RETRIEVE_AUTH_TOKEN_START";
        public static final String RETRIEVE_AUTH_TOKEN_UPDATE = "RETRIEVE_AUTH_TOKEN_UPDATE";
        public static final String RETRIEVE_DELEGATED_TOKEN_COMPLETE = "RETRIEVE_DELEGATED_TOKEN_COMPLETE";
        public static final String RETRIEVE_DELEGATED_TOKEN_FAIL_NO_DELEGATEDPROFILE = "RETRIEVE_DELEGATED_TOKEN_FAIL_NO_DELEGATEDPROFILE";
        public static final String RETRIEVE_DELEGATED_TOKEN_FAIL_NO_RM_ACTOR_TOKEN = "RETRIEVE_DELEGATED_TOKEN_FAIL_NO_RM_ACTOR_TOKEN";
        public static final String RETRIEVE_DELEGATED_TOKEN_FAIL_NO_RM_SESSION = "RETRIEVE_DELEGATED_TOKEN_FAIL_NO_RM_SESSION";
        public static final String RETRIEVE_DELEGATED_TOKEN_FAIL_NULL_DELAGATED_PERSONID = "RETRIEVE_DELEGATED_TOKEN_FAIL_NULL_DELAGATED_PERSONID";
        public static final String RETRIEVE_DELEGATED_TOKEN_GET_FAIL = "RETRIEVE_DELEGATED_TOKEN_GET_FAIL";
        public static final String RETRIEVE_DELEGATED_TOKEN_REGISTER_FAIL = "RETRIEVE_DELEGATED_TOKEN_REGISTER_FAIL";
        public static final String RETRIEVE_DELEGATED_TOKEN_START = "RETRIEVE_DELEGATED_TOKEN_START";
        public static final String SERVER_IDENTITY_FETCH_SUCCEEDED = "SERVER_IDENTITY_FETCH_SUCCEEDED";
        public static final String SIGN_IN_ATTEMPT = "SIGN_IN_ATTEMPT";
        public static final String SIGN_IN_DURATION = "SIGN_IN_DURATION";
        public static final String SIGN_IN_SUCCESS = "SIGN_IN_SUCCESS";
        public static final String TERMINATE_DELEGATION_DURATION = "TERMINATE_DELEGATION_DURATION";
        public static final String TERMINATE_DELEGATION_SUCCESS_RATE = "TERMINATE_DELEGATION_SUCCESS_RATE";
        public static final String UNKNOWN_MAP_FAILURE = "UNKNOWN_MAP_FAILURE";
        public static final String USER_PROFILE_SELECTION_SUCCESS = "USER_PROFILE_SELECTION_SUCCESS";

        private Event() {
        }
    }

    private Metric() {
    }
}
