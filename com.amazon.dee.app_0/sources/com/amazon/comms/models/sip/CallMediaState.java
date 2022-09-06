package com.amazon.comms.models.sip;

import com.amazon.dee.application.service.common.logging.RedactInLogs;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
@RedactInLogs
/* loaded from: classes11.dex */
public class CallMediaState {
    public static final String MEDIA_OFF = "OFF";
    public static final String MEDIA_ON = "ON";
    public static final String VIDEO_FROST = "FROST";
    private String outboundAudio;
    private String outboundVideo;

    /* loaded from: classes11.dex */
    public static class CallMediaStateBuilder {
        private String outboundAudio;
        private String outboundVideo;

        CallMediaStateBuilder() {
        }

        public CallMediaState build() {
            return new CallMediaState(this.outboundAudio, this.outboundVideo);
        }

        public CallMediaStateBuilder outboundAudio(String str) {
            this.outboundAudio = str;
            return this;
        }

        public CallMediaStateBuilder outboundVideo(String str) {
            this.outboundVideo = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CallMediaState.CallMediaStateBuilder(outboundAudio=");
            outline107.append(this.outboundAudio);
            outline107.append(", outboundVideo=");
            return GeneratedOutlineSupport1.outline91(outline107, this.outboundVideo, ")");
        }
    }

    public CallMediaState() {
    }

    public static CallMediaStateBuilder builder() {
        return new CallMediaStateBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof CallMediaState;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CallMediaState)) {
            return false;
        }
        CallMediaState callMediaState = (CallMediaState) obj;
        if (!callMediaState.canEqual(this)) {
            return false;
        }
        String outboundAudio = getOutboundAudio();
        String outboundAudio2 = callMediaState.getOutboundAudio();
        if (outboundAudio != null ? !outboundAudio.equals(outboundAudio2) : outboundAudio2 != null) {
            return false;
        }
        String outboundVideo = getOutboundVideo();
        String outboundVideo2 = callMediaState.getOutboundVideo();
        return outboundVideo != null ? outboundVideo.equals(outboundVideo2) : outboundVideo2 == null;
    }

    public String getOutboundAudio() {
        return this.outboundAudio;
    }

    public String getOutboundVideo() {
        return this.outboundVideo;
    }

    public int hashCode() {
        String outboundAudio = getOutboundAudio();
        int i = 43;
        int hashCode = outboundAudio == null ? 43 : outboundAudio.hashCode();
        String outboundVideo = getOutboundVideo();
        int i2 = (hashCode + 59) * 59;
        if (outboundVideo != null) {
            i = outboundVideo.hashCode();
        }
        return i2 + i;
    }

    public void setOutboundAudio(String str) {
        this.outboundAudio = str;
    }

    public void setOutboundVideo(String str) {
        this.outboundVideo = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CallMediaState(outboundAudio=");
        outline107.append(getOutboundAudio());
        outline107.append(", outboundVideo=");
        outline107.append(getOutboundVideo());
        outline107.append(")");
        return outline107.toString();
    }

    private CallMediaState(String str, String str2) {
        this.outboundAudio = str;
        this.outboundVideo = str2;
    }
}
