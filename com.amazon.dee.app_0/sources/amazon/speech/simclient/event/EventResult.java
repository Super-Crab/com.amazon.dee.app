package amazon.speech.simclient.event;
/* loaded from: classes.dex */
public enum EventResult {
    SUCCESS(false),
    ALEXA_DISCONNECTED(true),
    INTERNAL_ERROR(true),
    TIMEOUT(true),
    INVALID_EVENT(false),
    UNKNOWN_ERROR(true);
    
    private final boolean mIsRetryable;

    EventResult(boolean z) {
        this.mIsRetryable = z;
    }

    public boolean isRetryable() {
        return this.mIsRetryable;
    }
}
