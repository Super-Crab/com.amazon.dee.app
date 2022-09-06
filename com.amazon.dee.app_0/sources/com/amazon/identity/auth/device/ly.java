package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.common.KindleWebserviceErrorType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ly {
    private static final String TAG = "com.amazon.identity.auth.device.ly";

    private ly() {
    }

    public static lx e(Document document) {
        KindleWebserviceErrorType kindleWebserviceErrorType;
        Element documentElement = document.getDocumentElement();
        if (documentElement == null || !documentElement.getTagName().equals("KindleWebServicesError")) {
            return null;
        }
        if (ml.c(documentElement, "FileNotFoundError")) {
            kindleWebserviceErrorType = KindleWebserviceErrorType.KindleWebserviceErrorTypeFileNotFound;
        } else if (ml.c(documentElement, "DeviceAlreadyRegistered")) {
            kindleWebserviceErrorType = KindleWebserviceErrorType.KindleWebserviceErrorTypeDeviceAlreadyRegistered;
        } else if (ml.c(documentElement, "CredentialsRequired")) {
            kindleWebserviceErrorType = KindleWebserviceErrorType.KindleWebserviceErrorTypeCredentialsRequired;
        } else if (ml.c(documentElement, "InvalidAsin")) {
            kindleWebserviceErrorType = KindleWebserviceErrorType.KindleWebserviceErrorTypeInvalidAsin;
        } else if (ml.c(documentElement, "InvalidOrder")) {
            kindleWebserviceErrorType = KindleWebserviceErrorType.KindleWebserviceErrorTypeInvalidOrder;
        } else if (ml.c(documentElement, "InsufficientFunds")) {
            kindleWebserviceErrorType = KindleWebserviceErrorType.KindleWebserviceErrorTypeInsufficientFunds;
        } else if (ml.c(documentElement, "UnknownError")) {
            kindleWebserviceErrorType = KindleWebserviceErrorType.KindleWebserviceErrorTypeUnknownError;
        } else if (ml.c(documentElement, "UnBuyError")) {
            kindleWebserviceErrorType = KindleWebserviceErrorType.KindleWebserviceErrorTypeUnbuyError;
        } else if (ml.c(documentElement, "DuplicateDeviceName")) {
            kindleWebserviceErrorType = KindleWebserviceErrorType.KindleWebserviceErrorTypeDuplicateDeviceName;
        } else if (ml.c(documentElement, "InternalError")) {
            kindleWebserviceErrorType = KindleWebserviceErrorType.KindleWebserviceErrorTypeInternalError;
        } else {
            kindleWebserviceErrorType = KindleWebserviceErrorType.KindleWebserviceErrorTypeUnrecognized;
        }
        io.w(TAG, "KindleWebserviceError type=".concat(String.valueOf(kindleWebserviceErrorType)));
        return new lx(kindleWebserviceErrorType);
    }
}
