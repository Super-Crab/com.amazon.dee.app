package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import com.amazon.whisperjoin.common.sharedtypes.error.CommonErrorDetails;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
/* loaded from: classes13.dex */
public class CommonErrorDetailsProvider {
    public static int getCommonErrorDetails(Throwable th) {
        if (th instanceof TimeoutException) {
            return CommonErrorDetails.TIMEOUT_EXCEPTION;
        }
        if (th instanceof DataSerializationError) {
            return CommonErrorDetails.SERIALIZATION_ERROR;
        }
        if (th instanceof IllegalStateException) {
            return CommonErrorDetails.ILLEGAL_STATE_EXCEPTION;
        }
        if (th instanceof IllegalArgumentException) {
            return CommonErrorDetails.ILLEGAL_ARGUMENT_EXCEPTION;
        }
        if (th instanceof IOException) {
            return CommonErrorDetails.IO_EXCEPTION;
        }
        if (th instanceof NullPointerException) {
            return CommonErrorDetails.NULL_POINTER_EXCEPTION;
        }
        return CommonErrorDetails.UNKNOWN;
    }
}
