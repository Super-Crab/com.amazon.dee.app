package com.amazon.alexa.wakeword.speakerverification.errors;
/* loaded from: classes11.dex */
public enum EncryptionFailure {
    ILLEGAL_ARGUMENT_EXCEPTION(1),
    SECURITY_EXCEPTION(2),
    OTHER_EXCEPTION(3),
    INPUT_NULL(4),
    SYMMETRIC_KEY_NULL(5);
    
    private final int mErrorCode;

    EncryptionFailure(int i) {
        this.mErrorCode = i;
    }
}
