package com.amazon.CoralAndroidClient.ClientBase;

import com.amazon.CoralAndroidClient.Exception.CoralException;
/* loaded from: classes.dex */
public class ClientResponse {
    private final CoralException mCoralException;
    private final boolean mIsSuccess;
    private final ClientOutput mOutput;

    public ClientResponse(ClientOutput clientOutput) {
        this.mCoralException = null;
        this.mOutput = clientOutput;
        this.mIsSuccess = true;
    }

    public CoralException getException() {
        return this.mCoralException;
    }

    public ClientOutput getOutput() {
        return this.mOutput;
    }

    public boolean isSuccessful() {
        return this.mIsSuccess;
    }

    public ClientResponse(CoralException coralException) {
        this.mCoralException = coralException;
        this.mOutput = null;
        this.mIsSuccess = false;
    }
}
