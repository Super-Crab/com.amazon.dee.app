package com.amazon.alexa.sharing.sharingsdk.payload;

import com.amazon.alexa.sharing.Constants;
/* loaded from: classes10.dex */
public enum SharingPayloadError {
    noPayloadData("payloadHasNoData"),
    genericDecodingError("decodeError"),
    unknownTypeDecodingError("decodeError.unknownType"),
    missingRequiredFieldDecodingError("decodeError.missingRequiredField"),
    sharedMediaDecodingError("decodeError.sharedMedia"),
    sharedContentDecodingError("decodeError.sharedContent");
    
    public final String errorCode;

    /* renamed from: com.amazon.alexa.sharing.sharingsdk.payload.SharingPayloadError$1  reason: invalid class name */
    /* loaded from: classes10.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$sharing$sharingsdk$payload$SharingPayloadError = new int[SharingPayloadError.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$sharing$sharingsdk$payload$SharingPayloadError[SharingPayloadError.noPayloadData.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$sharing$sharingsdk$payload$SharingPayloadError[SharingPayloadError.missingRequiredFieldDecodingError.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$sharing$sharingsdk$payload$SharingPayloadError[SharingPayloadError.sharedMediaDecodingError.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$sharing$sharingsdk$payload$SharingPayloadError[SharingPayloadError.sharedContentDecodingError.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$sharing$sharingsdk$payload$SharingPayloadError[SharingPayloadError.unknownTypeDecodingError.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    SharingPayloadError(String str) {
        this.errorCode = str;
    }

    public String getErrorDescription() {
        int ordinal = ordinal();
        return ordinal != 0 ? ordinal != 2 ? ordinal != 3 ? ordinal != 4 ? ordinal != 5 ? Constants.ERROR_GENERIC_PARSE_FAILURE : Constants.ERROR_SHARED_CONTENT_PARSE_FAILURE : Constants.ERROR_SHARED_MEDIA_PARSE_FAILURE : Constants.ERROR_REQUEST_MISSING_REQUIRED_FIELD : Constants.ERROR_PAYLOAD_UNKNOWN_TYPE : Constants.ERROR_PAYLOAD_JSON_PARSE_FAILURE;
    }
}
