package com.amazon.matter.data;

import lombok.Generated;
/* loaded from: classes9.dex */
public class MatterError {
    private String errorMessage;
    private MatterErrorType errorType;

    @Generated
    public MatterError(MatterErrorType matterErrorType, String str) {
        this.errorType = matterErrorType;
        this.errorMessage = str;
    }

    @Generated
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Generated
    public MatterErrorType getErrorType() {
        return this.errorType;
    }
}
