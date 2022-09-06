package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import retrofit2.HttpException;
/* loaded from: classes11.dex */
public class CloudDriveException extends RuntimeException {
    private int code;
    @Nullable
    private String message;

    public CloudDriveException(@NonNull HttpException httpException) {
        this(httpException.code(), httpException.message());
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof CloudDriveException;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CloudDriveException)) {
            return false;
        }
        CloudDriveException cloudDriveException = (CloudDriveException) obj;
        if (!cloudDriveException.canEqual(this) || !super.equals(obj) || getCode() != cloudDriveException.getCode()) {
            return false;
        }
        String message = getMessage();
        String message2 = cloudDriveException.getMessage();
        return message != null ? message.equals(message2) : message2 == null;
    }

    public int getCode() {
        return this.code;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public int hashCode() {
        int code = getCode() + (super.hashCode() * 59);
        String message = getMessage();
        return (code * 59) + (message == null ? 43 : message.hashCode());
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CloudDriveException(code=");
        outline107.append(getCode());
        outline107.append(", message=");
        outline107.append(getMessage());
        outline107.append(")");
        return outline107.toString();
    }

    public CloudDriveException(int i, String str) {
        this.code = i;
        this.message = str;
    }
}
