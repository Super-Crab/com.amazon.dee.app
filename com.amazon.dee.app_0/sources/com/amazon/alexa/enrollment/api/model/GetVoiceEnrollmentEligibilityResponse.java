package com.amazon.alexa.enrollment.api.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class GetVoiceEnrollmentEligibilityResponse {
    private final boolean voiceEnrollmentEligibility;

    public GetVoiceEnrollmentEligibilityResponse(boolean z) {
        this.voiceEnrollmentEligibility = z;
    }

    public boolean isVoiceEnrollmentEligibility() {
        return this.voiceEnrollmentEligibility;
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103(" VoiceEnrollmentEligibility: ");
        outline103.append(this.voiceEnrollmentEligibility);
        return outline103.toString();
    }
}
