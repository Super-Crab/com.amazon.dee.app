package com.amazon.alexa.drive.smart.device.guard.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class ModeResult {
    @SerializedName("entity")
    private Identifier entity;
    @SerializedName("errorCode")
    private String errorCode;
    @SerializedName("errorMessage")
    private String errorMessage;
    @SerializedName("mode")
    private String mode;
    @SerializedName("success")
    private boolean success;

    public Identifier getEntity() {
        return this.entity;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getMode() {
        return this.mode;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setEntity(Identifier identifier) {
        this.entity = identifier;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public void setMode(String str) {
        this.mode = str;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }
}
