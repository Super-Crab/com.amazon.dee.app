package com.facebook.react.views.slider;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
/* loaded from: classes2.dex */
public class ReactSlider extends AppCompatSeekBar {
    private static int DEFAULT_TOTAL_STEPS = 128;
    private double mMaxValue;
    private double mMinValue;
    private double mStep;
    private double mStepCalculated;
    private double mValue;

    public ReactSlider(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMinValue = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.mMaxValue = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.mValue = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.mStep = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.mStepCalculated = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        disableStateListAnimatorIfNeeded();
    }

    private double getStepValue() {
        double d = this.mStep;
        return d > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR ? d : this.mStepCalculated;
    }

    private int getTotalSteps() {
        return (int) Math.ceil((this.mMaxValue - this.mMinValue) / getStepValue());
    }

    private void updateAll() {
        if (this.mStep == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            this.mStepCalculated = (this.mMaxValue - this.mMinValue) / DEFAULT_TOTAL_STEPS;
        }
        setMax(getTotalSteps());
        updateValue();
    }

    private void updateValue() {
        double d = this.mValue;
        double d2 = this.mMinValue;
        setProgress((int) Math.round(((d - d2) / (this.mMaxValue - d2)) * getTotalSteps()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void disableStateListAnimatorIfNeeded() {
        int i = Build.VERSION.SDK_INT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMaxValue(double d) {
        this.mMaxValue = d;
        updateAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMinValue(double d) {
        this.mMinValue = d;
        updateAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStep(double d) {
        this.mStep = d;
        updateAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValue(double d) {
        this.mValue = d;
        updateValue();
    }

    public double toRealProgress(int i) {
        if (i == getMax()) {
            return this.mMaxValue;
        }
        return (i * getStepValue()) + this.mMinValue;
    }
}
