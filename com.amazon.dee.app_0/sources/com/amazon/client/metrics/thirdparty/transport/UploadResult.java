package com.amazon.client.metrics.thirdparty.transport;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public class UploadResult {
    public static final int CLIENT_ERROR = 3;
    public static final int CREDENTIALS_ERROR = 5;
    public static final int EMPTY_DATA_ERROR = 8;
    public static final int FAILURE = -1;
    public static final int IO_ERROR = 10;
    public static final int NETWORK_ERROR = 2;
    public static final int NO_USABLE_CONNECTION = 7;
    public static final int SERVER_ERROR = 4;
    public static final int SUCCESS = 1;
    public static final int UNEXPECTED_ERROR = 6;
    private Exception mExceptionThrown;
    private int mResponseCode;
    private int mUploadStatus;

    public UploadResult(int i, int i2, Exception exc) {
        this.mUploadStatus = -1;
        this.mResponseCode = -1;
        this.mUploadStatus = i;
        this.mResponseCode = i2;
        this.mExceptionThrown = exc;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || UploadResult.class != obj.getClass()) {
            return false;
        }
        UploadResult uploadResult = (UploadResult) obj;
        if (this.mUploadStatus != uploadResult.mUploadStatus || this.mResponseCode != uploadResult.mResponseCode) {
            return false;
        }
        Exception exc = this.mExceptionThrown;
        Exception exc2 = uploadResult.mExceptionThrown;
        return exc != null ? exc.equals(exc2) : exc2 == null;
    }

    public Exception getExceptionThrown() {
        return this.mExceptionThrown;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    public int getUploadStatus() {
        return this.mUploadStatus;
    }

    public int hashCode() {
        int i = ((this.mUploadStatus * 31) + this.mResponseCode) * 31;
        Exception exc = this.mExceptionThrown;
        return i + (exc != null ? exc.hashCode() : 0);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UploadResult{mUploadStatus=");
        outline107.append(this.mUploadStatus);
        outline107.append(", mResponseCode=");
        outline107.append(this.mResponseCode);
        outline107.append(", mExceptionThrown=");
        outline107.append(this.mExceptionThrown);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    public UploadResult(int i, Exception exc) {
        this(i, -1, exc);
    }

    public UploadResult(int i, int i2) {
        this(i, i2, null);
    }

    public UploadResult(int i) {
        this(i, -1, null);
    }
}
