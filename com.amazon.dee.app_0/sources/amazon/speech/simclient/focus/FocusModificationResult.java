package amazon.speech.simclient.focus;
/* loaded from: classes.dex */
public enum FocusModificationResult {
    SUCCESS(false),
    FAIL_NOT_ALLOWED(false),
    FAIL_INTERNAL_ERROR(true);
    
    private final boolean mRetryable;

    FocusModificationResult(boolean z) {
        this.mRetryable = z;
    }

    public boolean isRetryable() {
        return this.mRetryable;
    }
}
