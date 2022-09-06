package com.amazon.alexa.enrollment.unified.speakerid.api.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class VoiceEnrollmentGUIConfiguration {
    private final long getVoiceEnrollmentStatusApiTimeout;
    private final boolean guiEnrollmentEnabled;
    private final int maxRetriesPerTrainingPhrase;
    private final long timeIntervalBetweenEnrollmentPhrases;

    public VoiceEnrollmentGUIConfiguration(int i, long j, long j2, boolean z) {
        this.maxRetriesPerTrainingPhrase = i;
        this.getVoiceEnrollmentStatusApiTimeout = j;
        this.timeIntervalBetweenEnrollmentPhrases = j2;
        this.guiEnrollmentEnabled = z;
    }

    public boolean getIsGuiEnrollmentEnabled() {
        return this.guiEnrollmentEnabled;
    }

    public int getMaxRetriesPerTrainingPhrase() {
        return this.maxRetriesPerTrainingPhrase;
    }

    public long getTimeIntervalBetweenEnrollmentPhrases() {
        return this.timeIntervalBetweenEnrollmentPhrases;
    }

    public long getVoiceEnrollmentStatusApiTimeout() {
        return this.getVoiceEnrollmentStatusApiTimeout;
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("MaxRetriesPerTrainingPhrase: ");
        outline103.append(this.maxRetriesPerTrainingPhrase);
        outline103.append(" GetVoiceEnrollmentStatusApiTimeout: ");
        outline103.append(this.getVoiceEnrollmentStatusApiTimeout);
        outline103.append(" TimeIntervalBetweenEnrollmentPhrases: ");
        outline103.append(this.timeIntervalBetweenEnrollmentPhrases);
        outline103.append(" GuiEnrollmentEnabled: ");
        outline103.append(this.guiEnrollmentEnabled);
        return outline103.toString();
    }
}
