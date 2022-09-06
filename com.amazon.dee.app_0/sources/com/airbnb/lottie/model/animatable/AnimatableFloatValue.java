package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;
/* loaded from: classes.dex */
public class AnimatableFloatValue extends BaseAnimatableValue<Float, Float> {
    AnimatableFloatValue() {
        super(Float.valueOf(0.0f));
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    /* renamed from: createAnimation */
    public BaseKeyframeAnimation<Float, Float> mo271createAnimation() {
        return new FloatKeyframeAnimation(this.keyframes);
    }

    @Override // com.airbnb.lottie.model.animatable.BaseAnimatableValue, com.airbnb.lottie.model.animatable.AnimatableValue
    public /* bridge */ /* synthetic */ List getKeyframes() {
        return super.getKeyframes();
    }

    @Override // com.airbnb.lottie.model.animatable.BaseAnimatableValue, com.airbnb.lottie.model.animatable.AnimatableValue
    public /* bridge */ /* synthetic */ boolean isStatic() {
        return super.isStatic();
    }

    @Override // com.airbnb.lottie.model.animatable.BaseAnimatableValue
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public AnimatableFloatValue(List<Keyframe<Float>> list) {
        super((List) list);
    }
}
