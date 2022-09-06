package com.amazon.alexa.enrollment.unified.model;

import androidx.annotation.Nullable;
/* loaded from: classes7.dex */
public class Speaker {
    private String firstName;
    private String personId;
    private String speakerCID;
    private String speakerUUID;
    private VoiceTrainingStatus voiceTrainingStatus;

    public String getFirstName() {
        return this.firstName;
    }

    public String getPersonId() {
        return this.personId;
    }

    public String getSpeakerCID() {
        return this.speakerCID;
    }

    @Nullable
    public String getSpeakerUUID() {
        return this.speakerUUID;
    }

    public VoiceTrainingStatus getVoiceTrainingStatus() {
        return this.voiceTrainingStatus;
    }
}
