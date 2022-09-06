package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes.dex */
public class PointKeyframeAnimation extends KeyframeAnimation<PointF> {
    private final PointF point;

    public PointKeyframeAnimation(List<Keyframe<PointF>> list) {
        super(list);
        this.point = new PointF();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: getValue  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object mo270getValue(Keyframe keyframe, float f) {
        return mo270getValue((Keyframe<PointF>) keyframe, f);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: getValue */
    public PointF mo270getValue(Keyframe<PointF> keyframe, float f) {
        PointF pointF;
        PointF pointF2;
        PointF pointF3 = keyframe.startValue;
        if (pointF3 != null && (pointF = keyframe.endValue) != null) {
            PointF pointF4 = pointF3;
            PointF pointF5 = pointF;
            LottieValueCallback<A> lottieValueCallback = this.valueCallback;
            if (lottieValueCallback != 0 && (pointF2 = (PointF) lottieValueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), pointF4, pointF5, f, getLinearCurrentKeyframeProgress(), getProgress())) != null) {
                return pointF2;
            }
            PointF pointF6 = this.point;
            float f2 = pointF4.x;
            float outline0 = GeneratedOutlineSupport1.outline0(pointF5.x, f2, f, f2);
            float f3 = pointF4.y;
            pointF6.set(outline0, ((pointF5.y - f3) * f) + f3);
            return this.point;
        }
        throw new IllegalStateException("Missing values for keyframe.");
    }
}
