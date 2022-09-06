package com.amazonaws.mobileconnectors.s3.transfermanager.exception;

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobileconnectors.s3.transfermanager.PauseStatus;
@Deprecated
/* loaded from: classes13.dex */
public class PauseException extends AmazonClientException {
    private static final long serialVersionUID = 1;
    private final PauseStatus status;

    public PauseException(PauseStatus pauseStatus) {
        super("Failed to pause operation; status=" + pauseStatus);
        if (pauseStatus != null && pauseStatus != PauseStatus.SUCCESS) {
            this.status = pauseStatus;
            return;
        }
        throw new IllegalArgumentException();
    }

    public PauseStatus getPauseStatus() {
        return this.status;
    }

    @Override // com.amazonaws.AmazonClientException
    public boolean isRetryable() {
        return false;
    }
}
