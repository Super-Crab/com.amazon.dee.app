package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;
/* loaded from: classes.dex */
public class GradientColorKeyframeAnimation extends KeyframeAnimation<GradientColor> {
    private final GradientColor gradientColor;

    public GradientColorKeyframeAnimation(List<Keyframe<GradientColor>> list) {
        super(list);
        int i = 0;
        GradientColor gradientColor = list.get(0).startValue;
        i = gradientColor != null ? gradientColor.getSize() : i;
        this.gradientColor = new GradientColor(new float[i], new int[i]);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: getValue  reason: collision with other method in class */
    /* bridge */ /* synthetic */ Object mo270getValue(Keyframe keyframe, float f) {
        return mo270getValue((Keyframe<GradientColor>) keyframe, f);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: getValue */
    GradientColor mo270getValue(Keyframe<GradientColor> keyframe, float f) {
        this.gradientColor.lerp(keyframe.startValue, keyframe.endValue, f);
        return this.gradientColor;
    }
}
