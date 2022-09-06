package amazon.speech.simclient.capability;
/* loaded from: classes.dex */
public enum PublishResult {
    SUCCESS(false),
    SUCCESS_NOOP(false),
    ERROR_JSON_FORMAT(false),
    ERROR_CAPABILITY_FORMAT(false),
    ERROR_PERMISSION_FAILURE(false),
    ERROR_VERSION_MISMATCH(false),
    ERROR_CONFIGURATION_MISMATCH(false),
    ERROR_NO_MANIFEST_TAG(false),
    ERROR_CAPABILITY_UNSUPPORTED(false),
    ERROR_CONTRACT_UNSUPPORTED(false),
    ERROR_SERVER_REJECTED(false),
    ERROR_SERVER_FORBIDDEN(false),
    ERROR_RUNTIME_EXCEPTION(true),
    ERROR_SERVER_ERROR(true),
    ERROR_REMOTE_EXCEPTION(true),
    ERROR_CLIENT_REFERENCE(false);
    
    private final boolean mRetriable;

    PublishResult(boolean z) {
        this.mRetriable = z;
    }

    public boolean isRetriable() {
        return this.mRetriable;
    }
}
