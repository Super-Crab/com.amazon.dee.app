package com.amazon.alexa.handsfree.uservoicerecognition.ui.steps;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract;
/* loaded from: classes8.dex */
public class UnifiedUVRVoiceChromeProvider implements StepsContract.VoiceChrome {
    private static final String TAG = "UnifiedUVRVoiceChromeProvider";

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.VoiceChrome
    public void dismissVoiceChrome(@NonNull StepsContract.ViewListener viewListener) {
        viewListener.onVoiceChromeDismissed();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.VoiceChrome
    public void setVoiceChromeLevelOnFeedback(float f) {
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.VoiceChrome
    public void setVoiceChromeNotRecording() {
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.VoiceChrome
    public void setVoiceChromeRecording() {
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.steps.StepsContract.VoiceChrome
    public void showVoiceChrome() {
    }
}
