package com.amazon.alexa.enrollment.api.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class StartVoiceEnrollmentResponse {
    private final VoiceEnrollmentGUIConfiguration voiceEnrollmentGUIConfiguration;
    private final VoiceEnrollmentStatus voiceEnrollmentStatus;

    public StartVoiceEnrollmentResponse(VoiceEnrollmentStatus voiceEnrollmentStatus, VoiceEnrollmentGUIConfiguration voiceEnrollmentGUIConfiguration) {
        this.voiceEnrollmentStatus = voiceEnrollmentStatus;
        this.voiceEnrollmentGUIConfiguration = voiceEnrollmentGUIConfiguration;
    }

    public VoiceEnrollmentGUIConfiguration getVoiceEnrollmentGUIConfiguration() {
        return this.voiceEnrollmentGUIConfiguration;
    }

    public VoiceEnrollmentStatus getVoiceEnrollmentStatus() {
        return this.voiceEnrollmentStatus;
    }

    public String toString() {
        String str = "";
        if (this.voiceEnrollmentStatus != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
            outline107.append(this.voiceEnrollmentStatus.toString());
            str = outline107.toString();
        }
        if (this.voiceEnrollmentGUIConfiguration != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(str);
            outline1072.append(this.voiceEnrollmentGUIConfiguration.toString());
            return outline1072.toString();
        }
        return str;
    }
}
