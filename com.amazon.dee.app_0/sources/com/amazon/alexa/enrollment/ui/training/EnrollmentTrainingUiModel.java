package com.amazon.alexa.enrollment.ui.training;

import com.amazon.alexa.enrollment.api.model.EnrollmentStates;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes7.dex */
public class EnrollmentTrainingUiModel {
    private final EnrollmentStates currentState;
    private final Integer currentTrainingIndex;
    private final long lastUpdatedTime;
    private final boolean shouldRequestDialogFromUserSpeechProvider;
    private final List<String> trainingPhrases;

    public EnrollmentTrainingUiModel(List<String> list, Integer num, EnrollmentStates enrollmentStates, long j, boolean z) {
        this.trainingPhrases = list;
        this.currentTrainingIndex = num;
        this.currentState = enrollmentStates;
        this.lastUpdatedTime = j;
        this.shouldRequestDialogFromUserSpeechProvider = z;
    }

    public EnrollmentStates getCurrentState() {
        return this.currentState;
    }

    public Integer getCurrentTrainingIndex() {
        return this.currentTrainingIndex;
    }

    public long getLastUpdatedTime() {
        return this.lastUpdatedTime;
    }

    public List<String> getTrainingPhrases() {
        return this.trainingPhrases;
    }

    public boolean shouldRequestDialogFromUserSpeechProvider() {
        return this.shouldRequestDialogFromUserSpeechProvider;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : this.trainingPhrases) {
            stringBuffer.append(str);
            stringBuffer.append("\n");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("currentTrainingIndex: ");
        outline107.append(this.currentTrainingIndex);
        outline107.append(" enrollment phrases: ");
        outline107.append(stringBuffer.toString());
        outline107.append(" last updated time : ");
        outline107.append(this.lastUpdatedTime);
        outline107.append(" current state: ");
        outline107.append(this.currentState);
        outline107.append(" should request dialog: ");
        outline107.append(this.shouldRequestDialogFromUserSpeechProvider);
        return outline107.toString();
    }
}
