package amazon.speech.simclient.metrics.upl.data;

import amazon.speech.simclient.metrics.upl.data.RequestData;
/* loaded from: classes.dex */
public class SpeechRequestData extends RequestData {
    private long mEndOfSpeechTimestamp;
    private long mEndPointTimestamp;
    private long mWakewordTimestamp;

    public SpeechRequestData() {
        super(RequestData.Type.SPEECH);
        this.mWakewordTimestamp = -1L;
        this.mEndOfSpeechTimestamp = -1L;
        this.mEndPointTimestamp = -1L;
    }

    public long getEndOfSpeechTimestamp() {
        return this.mEndOfSpeechTimestamp;
    }

    public long getEndPointTimestamp() {
        return this.mEndPointTimestamp;
    }

    public long getWakewordTimestamp() {
        return this.mWakewordTimestamp;
    }

    public SpeechRequestData setEndOfSpeechTimestamp(long j) {
        this.mEndOfSpeechTimestamp = j;
        return this;
    }

    public SpeechRequestData setEndPointTimestamp(long j) {
        this.mEndPointTimestamp = j;
        return this;
    }

    public SpeechRequestData setWakewordTimestamp(long j) {
        this.mWakewordTimestamp = j;
        return this;
    }
}
