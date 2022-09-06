package com.amazon.alexa.accessory.capabilities.bulkdata.exceptions;

import com.amazon.alexa.accessory.protocol.Common;
/* loaded from: classes.dex */
public class IdentifierNotFoundException extends BulkDataException {
    public IdentifierNotFoundException(String str) {
        super(str, Common.ErrorCode.NOT_FOUND);
    }
}
