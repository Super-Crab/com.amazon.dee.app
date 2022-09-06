package com.amazon.alexa.enrollment.unified.speakerid.api.model;
/* loaded from: classes7.dex */
public class GetVoiceEnrollmentStatusResponse {
    private final VoiceEnrollmentStatus voiceEnrollmentStatus;

    public GetVoiceEnrollmentStatusResponse(VoiceEnrollmentStatus voiceEnrollmentStatus) {
        this.voiceEnrollmentStatus = voiceEnrollmentStatus;
    }

    public VoiceEnrollmentStatus getVoiceEnrollmentStatus() {
        return this.voiceEnrollmentStatus;
    }

    public String toString() {
        return this.voiceEnrollmentStatus.toString();
    }
}
