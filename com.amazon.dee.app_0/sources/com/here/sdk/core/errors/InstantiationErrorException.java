package com.here.sdk.core.errors;
/* loaded from: classes3.dex */
public final class InstantiationErrorException extends Exception {
    public final InstantiationErrorCode error;

    public InstantiationErrorException(InstantiationErrorCode instantiationErrorCode) {
        super(instantiationErrorCode.toString());
        this.error = instantiationErrorCode;
    }
}
