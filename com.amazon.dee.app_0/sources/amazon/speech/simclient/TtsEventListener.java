package amazon.speech.simclient;
/* loaded from: classes.dex */
public interface TtsEventListener extends TtsListener {

    /* loaded from: classes.dex */
    public enum CompletionCode {
        SUCCESS(0),
        CANCEL(1),
        INTERRUPT(2);
        
        final int mCode;

        CompletionCode(int i) {
            this.mCode = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int getCode() {
            return this.mCode;
        }
    }

    /* loaded from: classes.dex */
    public enum ErrorCode {
        GENERIC(0),
        NO_RESOURCE(1),
        MEDIA_PLAYBACK_ERROR(2),
        MEDIA_PLAYER_EXCEPTION(3),
        MEDIA_PREPARATION_TIMEOUT(4);
        
        final int mCode;

        ErrorCode(int i) {
            this.mCode = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int getCode() {
            return this.mCode;
        }
    }

    void onSpeechEnded(long j, CompletionCode completionCode);

    void onSpeechError(long j, ErrorCode errorCode, String str);

    void onSpeechStarted(long j);

    void onUriConnectionEstablished(long j);

    void onUriConnectionStarted(long j);
}
