package amazon.speech.simclient.metrics.upl.data;

import amazon.speech.simclient.metrics.upl.data.RequestData;
/* loaded from: classes.dex */
public class InteractionRequestData extends RequestData {
    private long mEventRequestTimestamp;
    private long mInitiationTimestamp;
    private String mInteractionLabel;

    public InteractionRequestData() {
        super(RequestData.Type.INTERACTION);
        this.mInitiationTimestamp = -1L;
        this.mEventRequestTimestamp = -1L;
    }

    public long getEventRequestTimestamp() {
        return this.mEventRequestTimestamp;
    }

    public long getInitiationTimestamp() {
        return this.mInitiationTimestamp;
    }

    public String getInteractionLabel() {
        return this.mInteractionLabel;
    }

    public InteractionRequestData setEventRequestTimestamp(long j) {
        this.mEventRequestTimestamp = j;
        return this;
    }

    public InteractionRequestData setInitiationTimestamp(long j) {
        this.mInitiationTimestamp = j;
        return this;
    }

    public InteractionRequestData setInteractionLabel(String str) {
        this.mInteractionLabel = str;
        return this;
    }
}
