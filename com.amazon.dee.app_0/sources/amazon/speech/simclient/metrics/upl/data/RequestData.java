package amazon.speech.simclient.metrics.upl.data;
/* loaded from: classes.dex */
public class RequestData {
    private String mAlexaIntentName;
    private String mEventId;
    private final Type mType;
    private long mLastAudioDirectiveTimestamp = -1;
    private long mLastVisualDirectiveTimestamp = -1;
    private boolean mIsOfflineInteraction = false;

    /* loaded from: classes.dex */
    public enum Type {
        SPEECH("VoiceRequest"),
        INTERACTION("InteractionRequest");
        
        private String mDescription;

        Type(String str) {
            this.mDescription = str;
        }

        public String getDescription() {
            return this.mDescription;
        }
    }

    public RequestData(Type type) {
        this.mType = type;
    }

    public String getAlexaIntentName() {
        return this.mAlexaIntentName;
    }

    public final String getEventId() {
        return this.mEventId;
    }

    public long getLastAudioDirectiveTimestamp() {
        return this.mLastAudioDirectiveTimestamp;
    }

    public long getLastVisualDirectiveTimestamp() {
        return this.mLastVisualDirectiveTimestamp;
    }

    public final Type getType() {
        return this.mType;
    }

    public boolean isOfflineInteraction() {
        return this.mIsOfflineInteraction;
    }

    public RequestData setAlexaIntentName(String str) {
        this.mAlexaIntentName = str;
        return this;
    }

    public final RequestData setEventId(String str) {
        this.mEventId = str;
        return this;
    }

    public void setInteractionAsOffline() {
        this.mIsOfflineInteraction = true;
    }

    public RequestData setLastAudioDirectiveTimestamp(long j) {
        this.mLastAudioDirectiveTimestamp = j;
        return this;
    }

    public RequestData setLastVisualDirectiveTimestamp(long j) {
        this.mLastVisualDirectiveTimestamp = j;
        return this;
    }
}
