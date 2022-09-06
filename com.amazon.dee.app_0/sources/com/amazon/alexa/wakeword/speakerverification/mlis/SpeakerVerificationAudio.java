package com.amazon.alexa.wakeword.speakerverification.mlis;
/* loaded from: classes11.dex */
public class SpeakerVerificationAudio {
    private static final String AUDIO_CONTENT_TYPE = "audio/wav";
    private static final int AUDIO_PART_ID = 2;
    private short[] audio;
    private int id = 2;
    private String contentType = "audio/wav";

    public SpeakerVerificationAudio(short[] sArr) {
        this.audio = sArr;
    }

    public short[] getAudio() {
        return this.audio;
    }

    public String getContentType() {
        return this.contentType;
    }

    public int getId() {
        return this.id;
    }
}
