package amazon.speech.tts;
/* loaded from: classes.dex */
public interface SpeechEndListener extends TtsListener {
    public static final int CANCEL = 1;
    public static final int ERROR_MEDIAPLAYER_EXCEPTION = 3;
    public static final int ERROR_MEDIA_PLAYBACK = 2;
    public static final int ERROR_MEDIA_PREPARATION_TIMEOUT = 4;
    public static final int ERROR_NO_RESOURCE = 1;
    public static final int INTERRUPT = 2;
    public static final int SUCCESS = 0;

    void onSpeechEnded(int i);

    void onSpeechError(int i, String str);
}
