package com.amazon.identity.kcpsdk.auth;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public enum AuthenticateAccountError {
    AuthenticateAccountErrorMissingValue(PandaError.PandaErrorMissingValue),
    AuthenticateAccountErrorInvalidValue(PandaError.PandaErrorInvalidValue),
    AuthenticateAccountErrorCredentialError(PandaError.PandaErrorCredentialError),
    AuthenticateAccountErrorServerError(PandaError.PandaErrorServerError),
    AuthenticateAccountErrorServiceUnavailable(PandaError.PandaErrorServiceUnavailable),
    AuthenticateAccountErrorUnknown(PandaError.PandaErrorUnknown),
    AuthenticateAccountErrorChallengeException(PandaError.PandaErrorChallengeException),
    AuthenticateAccountErrorForbidden(PandaError.PandaErrorForbidden);
    
    private final PandaError mBaseError;

    AuthenticateAccountError(PandaError pandaError) {
        this.mBaseError = pandaError;
    }

    public static AuthenticateAccountError fromPandaError(PandaError pandaError) {
        AuthenticateAccountError[] values;
        for (AuthenticateAccountError authenticateAccountError : values()) {
            if (authenticateAccountError.mBaseError == pandaError) {
                return authenticateAccountError;
            }
        }
        return null;
    }

    public String getErrorCode() {
        return this.mBaseError.getErrorCode();
    }

    @Override // java.lang.Enum
    public String toString() {
        return "[" + name() + " " + this.mBaseError.getErrorString() + "]";
    }
}
