package com.amazon.alexa.accessory.capabilities.bulkdata.exceptions;

import com.amazon.alexa.accessory.protocol.Common;
/* loaded from: classes.dex */
public class BusyErrorCodeException extends BulkDataException {
    public BusyErrorCodeException(String str) {
        super(str, Common.ErrorCode.BUSY);
    }
}
