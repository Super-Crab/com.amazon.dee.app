package com.amazon.alexa.accessory.capabilities.state.exceptions;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Common;
import java.io.IOException;
/* loaded from: classes.dex */
public class StateException extends IOException {
    private final Common.ErrorCode errorCode;

    public StateException(String str, Common.ErrorCode errorCode) {
        super(str);
        Preconditions.notNull(errorCode, "errorCode");
        this.errorCode = errorCode;
    }

    public Common.ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
