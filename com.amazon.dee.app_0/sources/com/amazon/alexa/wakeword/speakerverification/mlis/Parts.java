package com.amazon.alexa.wakeword.speakerverification.mlis;
/* loaded from: classes11.dex */
public class Parts {
    private SpeakerVerificationAudio svAudio;
    private SpeakerVerificationMetadata svMetadata;

    public Parts(SpeakerVerificationAudio speakerVerificationAudio, SpeakerVerificationMetadata speakerVerificationMetadata) {
        this.svAudio = speakerVerificationAudio;
        this.svMetadata = speakerVerificationMetadata;
    }

    public SpeakerVerificationAudio getSvAudio() {
        return this.svAudio;
    }

    public SpeakerVerificationMetadata getSvMetadata() {
        return this.svMetadata;
    }
}
