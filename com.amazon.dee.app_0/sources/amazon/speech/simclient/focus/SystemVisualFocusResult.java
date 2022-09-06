package amazon.speech.simclient.focus;
/* loaded from: classes.dex */
public enum SystemVisualFocusResult {
    SUCCESS(false, true),
    SUCCESS_NO_OP(false, true),
    FAIL_INTERNAL_ERROR(true, false),
    FAIL_UNEXPECTED_RESULT(false, false),
    FAIL_BAD_REQUEST(false, false),
    FAIL_SERVICE_UNAVAILABLE(false, false);
    
    private final boolean mIsSuccess;
    private final boolean mRetryable;

    SystemVisualFocusResult(boolean z, boolean z2) {
        this.mRetryable = z;
        this.mIsSuccess = z2;
    }

    public boolean isRetryable() {
        return this.mRetryable;
    }

    public boolean isSuccess() {
        return this.mIsSuccess;
    }
}
