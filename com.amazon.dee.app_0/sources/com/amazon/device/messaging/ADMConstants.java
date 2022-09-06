package com.amazon.device.messaging;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes12.dex */
public final class ADMConstants {
    @FireOsSdk
    public static final String ERROR_AUTHENTICATION_FAILED = "AUTHENTICATION_FAILED";
    @FireOsSdk
    public static final String ERROR_INVALID_SENDER = "INVALID_SENDER";
    @FireOsSdk
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String EXTRA_FROM = "from";
    @FireOsSdk
    public static final String EXTRA_MD5 = "adm_message_md5";
    @FireOsSdk
    public static final String EXTRA_PRIORITY = "adm_message_priority";
    public static final String LOG_TAG = "ADM";

    /* loaded from: classes12.dex */
    public static final class LowLevel {
        @FireOsSdk
        public static final String ACTION_APP_REGISTRATION_EVENT = "com.amazon.device.messaging.intent.REGISTRATION";
        @FireOsSdk
        public static final String ACTION_RECEIVE_ADM_MESSAGE = "com.amazon.device.messaging.intent.RECEIVE";
        @FireOsSdk
        public static final String ACTION_REGISTER = "com.amazon.device.messaging.intent.REGISTER";
        @FireOsSdk
        public static final String ACTION_SYNC = "com.amazon.device.messaging.intent.SYNC";
        @FireOsSdk
        public static final String ACTION_UNREGISTER = "com.amazon.device.messaging.intent.UNREGISTER";
        @FireOsSdk
        public static final String ADM_PACKAGE_NAME = "com.amazon.device.messaging";
        @FireOsSdk
        public static final String EXTRA_APPLICATION_PENDING_INTENT = "app";
        @FireOsSdk
        public static final String EXTRA_ERROR = "error";
        @FireOsSdk
        public static final String EXTRA_ERROR_DESCRIPTION = "error_description";
        public static final String EXTRA_OLD_REGISTRATION_ID = "old_registration_id";
        @FireOsSdk
        public static final String EXTRA_REGISTRATION_ID = "registration_id";
        @FireOsSdk
        public static final String EXTRA_SENDER = "sender";
        @FireOsSdk
        public static final String EXTRA_SYNC_STATE = "is_sync";
        @FireOsSdk
        public static final String EXTRA_UNREGISTERED = "unregistered";
        @FireOsSdk
        public static final String PERMISSION_SEND_ADM_MESSAGE = "com.amazon.device.messaging.permission.SEND";
        @FireOsSdk
        public static final String PERMISSION_TO_RECEIVE = "com.amazon.device.messaging.permission.RECEIVE";

        private LowLevel() {
        }
    }

    private ADMConstants() {
    }
}
