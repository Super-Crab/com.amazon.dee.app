package amazon.speech.simclient.directive;
/* loaded from: classes.dex */
public enum AttachmentError {
    DOES_NOT_EXIST(false),
    WRITE_FAILURE(true),
    READ_TIMEOUT(true),
    SERVICE_EXCEPTION(true),
    UNKNOWN(false);
    
    private final boolean mRetryable;

    AttachmentError(boolean z) {
        this.mRetryable = z;
    }

    public boolean isRetryable() {
        return this.mRetryable;
    }
}
