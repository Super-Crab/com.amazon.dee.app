package amazon.speech.simclient.settings;
/* loaded from: classes.dex */
public final class Settings {
    public static final String CONNECTED = "connected";
    public static final String DND = "dnd";
    public static final String INTERACTING = "interacting";
    public static final String LISTENING = "listening";
    public static final String NETWORK_UP = "network-up";
    public static final String OOBE = "oobe";
    public static final String PRIVACY = "privacy";
    public static final String SPEAKING = "speaking";
    public static final String THINKING = "thinking";

    /* loaded from: classes.dex */
    public final class ListeningSettings {
        public static final String EXTRA_REASON = "reason";
        public static final String REASON_FOLLOW_UP = "follow-up";
        public static final String REASON_MIC_BUTTON = "mic-button";
        public static final String REASON_MULTI_TURN = "multi-turn";
        public static final String REASON_SHORT_BUTTON_PRESS = "short-button-press";
        public static final String REASON_SPEECH_PROMPT = "speech-prompt";
        public static final String REASON_UNKNOWN = "unknown";
        public static final String REASON_WAKEWORD = "wakeword";

        private ListeningSettings() {
            throw new UnsupportedOperationException();
        }
    }

    private Settings() {
        throw new UnsupportedOperationException();
    }
}
