package com.amazon.alexa.enrollment.api.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes7.dex */
public class VoiceEnrollmentStatus {
    private final int currentTrainingPhraseIndex;
    private final EnrollmentStates enrollmentState;
    private final ArrayList<String> enrollmentTrainingPhrases;
    private final long lastUpdatedTime;

    public VoiceEnrollmentStatus(EnrollmentStates enrollmentStates, int i, long j, ArrayList<String> arrayList) {
        this.enrollmentState = enrollmentStates;
        this.currentTrainingPhraseIndex = i;
        this.lastUpdatedTime = j;
        this.enrollmentTrainingPhrases = arrayList;
    }

    public int getCurrentTrainingPhraseIndex() {
        return this.currentTrainingPhraseIndex;
    }

    public EnrollmentStates getEnrollmentState() {
        return this.enrollmentState;
    }

    public ArrayList<String> getEnrollmentTrainingPhrases() {
        return this.enrollmentTrainingPhrases;
    }

    public long getLastUpdatedTime() {
        return this.lastUpdatedTime;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<String> arrayList = this.enrollmentTrainingPhrases;
        if (arrayList != null) {
            Iterator<String> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                stringBuffer.append(it2.next());
                stringBuffer.append("\n");
            }
        }
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("EnrollmentState: ");
        outline103.append(this.enrollmentState);
        outline103.append(" CurrentTrainingPhraseIndex: ");
        outline103.append(this.currentTrainingPhraseIndex);
        outline103.append(" LastUpdatedTime: ");
        outline103.append(this.lastUpdatedTime);
        outline103.append(" EnrollmentTrainingPhrases: ");
        outline103.append(stringBuffer);
        return outline103.toString();
    }
}
