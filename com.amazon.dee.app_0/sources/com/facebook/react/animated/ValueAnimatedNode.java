package com.facebook.react.animated;

import androidx.annotation.Nullable;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes2.dex */
class ValueAnimatedNode extends AnimatedNode {
    Object mAnimatedObject;
    double mOffset;
    double mValue;
    @Nullable
    private AnimatedNodeValueListener mValueListener;

    public ValueAnimatedNode() {
        this.mAnimatedObject = null;
        this.mValue = Double.NaN;
        this.mOffset = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    public void extractOffset() {
        this.mOffset += this.mValue;
        this.mValue = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    public void flattenOffset() {
        this.mValue += this.mOffset;
        this.mOffset = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    public Object getAnimatedObject() {
        return this.mAnimatedObject;
    }

    public double getValue() {
        if (Double.isNaN(this.mOffset + this.mValue)) {
            update();
        }
        return this.mOffset + this.mValue;
    }

    public void onValueUpdate() {
        AnimatedNodeValueListener animatedNodeValueListener = this.mValueListener;
        if (animatedNodeValueListener == null) {
            return;
        }
        animatedNodeValueListener.onValueUpdate(getValue());
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ValueAnimatedNode[");
        outline107.append(this.mTag);
        outline107.append("]: value: ");
        outline107.append(this.mValue);
        outline107.append(" offset: ");
        outline107.append(this.mOffset);
        return outline107.toString();
    }

    public void setValueListener(@Nullable AnimatedNodeValueListener animatedNodeValueListener) {
        this.mValueListener = animatedNodeValueListener;
    }

    public ValueAnimatedNode(ReadableMap readableMap) {
        this.mAnimatedObject = null;
        this.mValue = Double.NaN;
        this.mOffset = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.mValue = readableMap.getDouble("value");
        this.mOffset = readableMap.getDouble("offset");
    }
}
