package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.common.FIRSErrorType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class lv {
    private static final String TAG = "com.amazon.identity.auth.device.lv";

    private lv() {
    }

    public static lu d(Document document) {
        FIRSErrorType fIRSErrorType;
        Element documentElement = document.getDocumentElement();
        if (documentElement == null || !documentElement.getTagName().equals("error")) {
            return null;
        }
        if (ml.c(documentElement, FIRSErrorType.FIRSErrorTypeCustomerNotFound.getErrorCode())) {
            fIRSErrorType = FIRSErrorType.FIRSErrorTypeCustomerNotFound;
        } else if (ml.c(documentElement, FIRSErrorType.FIRSErrorTypeDeviceAlreadyRegistered.getErrorCode())) {
            fIRSErrorType = FIRSErrorType.FIRSErrorTypeDeviceAlreadyRegistered;
        } else if (ml.c(documentElement, FIRSErrorType.FIRSErrorTypeDuplicateAccountName.getErrorCode())) {
            fIRSErrorType = FIRSErrorType.FIRSErrorTypeDuplicateAccountName;
        } else if (ml.c(documentElement, FIRSErrorType.FIRSErrorTypeInternalError.getErrorCode())) {
            fIRSErrorType = FIRSErrorType.FIRSErrorTypeInternalError;
        } else if (ml.c(documentElement, FIRSErrorType.FIRSErrorTypeInvalidAccountFound.getErrorCode())) {
            fIRSErrorType = FIRSErrorType.FIRSErrorTypeInvalidAccountFound;
        } else {
            fIRSErrorType = FIRSErrorType.FIRSErrorTypeUnrecognized;
        }
        io.w(TAG, "FIRSError type=".concat(String.valueOf(fIRSErrorType)));
        return new lu(fIRSErrorType);
    }
}
