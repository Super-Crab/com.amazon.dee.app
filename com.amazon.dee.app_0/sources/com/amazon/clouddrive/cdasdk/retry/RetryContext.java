package com.amazon.clouddrive.cdasdk.retry;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import okhttp3.Response;
/* loaded from: classes11.dex */
public class RetryContext {
    @Nullable
    private IOException exception;
    private int numRetriesAttempted;
    @Nullable
    private Response response;

    public RetryContext() {
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof RetryContext;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RetryContext)) {
            return false;
        }
        RetryContext retryContext = (RetryContext) obj;
        if (!retryContext.canEqual(this) || getNumRetriesAttempted() != retryContext.getNumRetriesAttempted()) {
            return false;
        }
        IOException exception = getException();
        IOException exception2 = retryContext.getException();
        if (exception != null ? !exception.equals(exception2) : exception2 != null) {
            return false;
        }
        Response response = getResponse();
        Response response2 = retryContext.getResponse();
        return response != null ? response.equals(response2) : response2 == null;
    }

    public IOException getException() {
        return this.exception;
    }

    public int getNumRetriesAttempted() {
        return this.numRetriesAttempted;
    }

    public Response getResponse() {
        return this.response;
    }

    public int hashCode() {
        IOException exception = getException();
        int i = 43;
        int numRetriesAttempted = ((getNumRetriesAttempted() + 59) * 59) + (exception == null ? 43 : exception.hashCode());
        Response response = getResponse();
        int i2 = numRetriesAttempted * 59;
        if (response != null) {
            i = response.hashCode();
        }
        return i2 + i;
    }

    public void setException(IOException iOException) {
        this.exception = iOException;
    }

    public void setNumRetriesAttempted(int i) {
        this.numRetriesAttempted = i;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RetryContext(numRetriesAttempted=");
        outline107.append(getNumRetriesAttempted());
        outline107.append(", exception=");
        outline107.append(getException());
        outline107.append(", response=");
        outline107.append(getResponse());
        outline107.append(")");
        return outline107.toString();
    }

    public RetryContext(@NonNull RetryContext retryContext) {
        this.numRetriesAttempted = retryContext.numRetriesAttempted;
        this.exception = retryContext.exception;
        this.response = retryContext.response;
    }
}
