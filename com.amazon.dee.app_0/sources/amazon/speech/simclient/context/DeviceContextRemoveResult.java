package amazon.speech.simclient.context;
/* loaded from: classes.dex */
public enum DeviceContextRemoveResult {
    SUCCESS(false),
    FAIL_INTERNAL_ERROR(true);
    
    private final boolean mRetryable;

    DeviceContextRemoveResult(boolean z) {
        this.mRetryable = z;
    }

    public boolean isRetryable() {
        return this.mRetryable;
    }
}
