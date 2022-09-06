package amazon.speech.simclient.context;
/* loaded from: classes.dex */
public enum DeviceContextAddResult {
    SUCCESS(false),
    FAIL_INVALID_FORMAT(false),
    FAIL_INTERNAL_ERROR(true);
    
    private final boolean mRetryable;

    DeviceContextAddResult(boolean z) {
        this.mRetryable = z;
    }

    public boolean isRetryable() {
        return this.mRetryable;
    }
}
