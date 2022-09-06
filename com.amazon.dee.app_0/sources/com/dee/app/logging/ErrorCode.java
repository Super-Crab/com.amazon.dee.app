package com.dee.app.logging;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
/* loaded from: classes2.dex */
public enum ErrorCode {
    NETWORK_GENERAL("A network error occurred.", ErrorType.NETWORK, ErrorType.RETRYABLE),
    NO_NETWORK("No network connection found.", ErrorType.NETWORK),
    AIRPLANE_MODE("Airplane mode is on.", ErrorType.NETWORK, ErrorType.RETRYABLE),
    UNKNOWN_HOST("Unknown host exception.", ErrorType.NETWORK),
    SSL_ERROR("SSL handshake exception.", ErrorType.NETWORK),
    CONNECT_EXCEPTION("Unable to connect to host.", ErrorType.NETWORK),
    JSON_EXCEPTION("JSON parsing exception.", ErrorType.ERROR),
    RESPONSE_5XX_LEVEL("5xx level response from server.", ErrorType.SERVER),
    RESPONSE_4XX_LEVEL("4xx level response from server.", ErrorType.SERVER),
    BAD_URL("A bad URL was specified.", ErrorType.ERROR),
    COMPONENT_ERROR("A component errored out.", new ErrorType[0]),
    EXPECTED_NATIVE_GOT_WEB("Expected native, got web.", ErrorType.SERVER);
    
    private final String defaultMessage;
    private final int flags;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public enum ErrorType {
        NONE(0),
        NOT_REPORTED(1),
        RETRYABLE(2),
        SERVER(4),
        NETWORK(8),
        FATAL(16),
        DEBUG(32),
        ASSERT(64),
        ERROR(128);
        
        private final int flag;

        ErrorType(int i) {
            this.flag = i;
        }

        public boolean isIn(int i) {
            int i2 = this.flag;
            return (i & i2) == i2;
        }

        public boolean isNotIn(int i) {
            return !isIn(i);
        }
    }

    ErrorCode(String str, ErrorType... errorTypeArr) {
        int i = 0;
        for (ErrorType errorType : errorTypeArr) {
            i |= errorType.flag;
        }
        this.defaultMessage = str;
        this.flags = i;
    }

    public String getDefaultMessage() {
        return this.defaultMessage;
    }

    public int getFlags() {
        return this.flags;
    }

    public boolean isDebug() {
        return ErrorType.DEBUG.isIn(this.flags);
    }

    public boolean isError() {
        return ErrorType.ERROR.isIn(this.flags) || shouldAssert() || isFatal();
    }

    public boolean isFatal() {
        return ErrorType.FATAL.isIn(this.flags);
    }

    public boolean isRetryable() {
        return ErrorType.RETRYABLE.isIn(this.flags);
    }

    public boolean shouldAssert() {
        return ErrorType.ASSERT.isIn(this.flags);
    }

    public WritableMap toWritableMap() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("name", name());
        createMap.putString("message", this.defaultMessage);
        createMap.putInt("errorCode", this.flags);
        return createMap;
    }
}
