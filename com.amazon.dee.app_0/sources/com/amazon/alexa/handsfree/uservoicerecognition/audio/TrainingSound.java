package com.amazon.alexa.handsfree.uservoicerecognition.audio;

import androidx.annotation.RawRes;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
/* loaded from: classes8.dex */
public enum TrainingSound {
    NEW_UTTERANCE(R.raw.avs_med_ui_wakesound_touch),
    UTTERANCE_COMPLETED(R.raw.avs_med_ui_endpointing_touch_500ms),
    UTTERANCE_ERROR(R.raw.med_ui_error_generic_2);
    
    private final int mResId;

    TrainingSound(@RawRes int i) {
        this.mResId = i;
    }

    @RawRes
    public int getResourceId() {
        return this.mResId;
    }
}
