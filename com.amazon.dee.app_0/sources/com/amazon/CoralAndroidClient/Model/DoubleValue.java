package com.amazon.CoralAndroidClient.Model;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
/* loaded from: classes.dex */
public class DoubleValue implements Value {
    private double mValue;

    public DoubleValue(double d) {
        this.mValue = d;
    }

    public double getValue() {
        return this.mValue;
    }

    public void setValue(double d) {
        this.mValue = d;
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return Double.valueOf(getValue());
    }

    public DoubleValue() {
        this(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }
}
