package com.amazon.alexa.enrollment.unified.model;

import androidx.annotation.Nullable;
import com.amazon.alexa.wakeword.WakeWordData;
/* loaded from: classes7.dex */
public class EnrollmentStateProvider {
    private static final int DEFAULT_NUM_WAKE_WORD_PHRASES = 4;
    private WakeWordData mCurrentWakeWordData;
    private int mNumUtterances = 4;

    @Nullable
    public WakeWordData getCurrentWakeWordData() {
        return this.mCurrentWakeWordData;
    }

    public int getNumUtterances() {
        return this.mNumUtterances;
    }

    public void setCurrentWakeWordData(@Nullable WakeWordData wakeWordData) {
        this.mCurrentWakeWordData = wakeWordData;
    }

    public void setNumUtterances(int i) {
        this.mNumUtterances = i;
    }
}
