package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.IntegerKeyframeAnimation;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;
/* loaded from: classes.dex */
public class AnimatableIntegerValue extends BaseAnimatableValue<Integer, Integer> {
    public AnimatableIntegerValue() {
        super(100);
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    /* renamed from: createAnimation */
    public BaseKeyframeAnimation<Integer, Integer> mo271createAnimation() {
        return new IntegerKeyframeAnimation(this.keyframes);
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

    public AnimatableIntegerValue(List<Keyframe<Integer>> list) {
        super((List) list);
    }
}
