package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class ErrorInfo implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.ErrorInfo");
    private String cause;
    private String code;
    private String details;
    private String operation;

    public boolean equals(Object obj) {
        if (!(obj instanceof ErrorInfo)) {
            return false;
        }
        ErrorInfo errorInfo = (ErrorInfo) obj;
        return Helper.equals(this.details, errorInfo.details) && Helper.equals(this.cause, errorInfo.cause) && Helper.equals(this.code, errorInfo.code) && Helper.equals(this.operation, errorInfo.operation);
    }

    public String getCause() {
        return this.cause;
    }

    public String getCode() {
        return this.code;
    }

    public String getDetails() {
        return this.details;
    }

    public String getOperation() {
        return this.operation;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.details, this.cause, this.code, this.operation);
    }

    public void setCause(String str) {
        this.cause = str;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setDetails(String str) {
        this.details = str;
    }

    public void setOperation(String str) {
        this.operation = str;
    }
}
