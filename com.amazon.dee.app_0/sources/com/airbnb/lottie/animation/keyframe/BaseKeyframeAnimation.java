package com.airbnb.lottie.animation.keyframe;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class BaseKeyframeAnimation<K, A> {
    @Nullable
    private Keyframe<K> cachedGetValueKeyframe;
    @Nullable
    private Keyframe<K> cachedKeyframe;
    private final List<? extends Keyframe<K>> keyframes;
    @Nullable
    protected LottieValueCallback<A> valueCallback;
    final List<AnimationListener> listeners = new ArrayList(1);
    private boolean isDiscrete = false;
    private float progress = 0.0f;
    private float cachedGetValueProgress = -1.0f;
    @Nullable
    private A cachedGetValue = null;
    private float cachedStartDelayProgress = -1.0f;
    private float cachedEndProgress = -1.0f;

    /* loaded from: classes.dex */
    public interface AnimationListener {
        void onValueChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseKeyframeAnimation(List<? extends Keyframe<K>> list) {
        this.keyframes = list;
    }

    @FloatRange(from = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, to = 1.0d)
    private float getStartDelayProgress() {
        if (this.cachedStartDelayProgress == -1.0f) {
            this.cachedStartDelayProgress = this.keyframes.isEmpty() ? 0.0f : this.keyframes.get(0).getStartProgress();
        }
        return this.cachedStartDelayProgress;
    }

    public void addUpdateListener(AnimationListener animationListener) {
        this.listeners.add(animationListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Keyframe<K> getCurrentKeyframe() {
        Keyframe<K> keyframe = this.cachedKeyframe;
        if (keyframe != null && keyframe.containsProgress(this.progress)) {
            return this.cachedKeyframe;
        }
        Keyframe<K> keyframe2 = (Keyframe) GeneratedOutlineSupport1.outline24(this.keyframes, -1);
        if (this.progress < keyframe2.getStartProgress()) {
            int size = this.keyframes.size();
            do {
                size--;
                if (size < 0) {
                    break;
                }
                keyframe2 = this.keyframes.get(size);
            } while (!keyframe2.containsProgress(this.progress));
        }
        this.cachedKeyframe = keyframe2;
        return keyframe2;
    }

    @FloatRange(from = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, to = 1.0d)
    float getEndProgress() {
        if (this.cachedEndProgress == -1.0f) {
            this.cachedEndProgress = this.keyframes.isEmpty() ? 1.0f : ((Keyframe) GeneratedOutlineSupport1.outline24(this.keyframes, -1)).getEndProgress();
        }
        return this.cachedEndProgress;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float getInterpolatedCurrentKeyframeProgress() {
        Keyframe<K> currentKeyframe = getCurrentKeyframe();
        if (currentKeyframe.isStatic()) {
            return 0.0f;
        }
        return currentKeyframe.interpolator.getInterpolation(getLinearCurrentKeyframeProgress());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getLinearCurrentKeyframeProgress() {
        if (this.isDiscrete) {
            return 0.0f;
        }
        Keyframe<K> currentKeyframe = getCurrentKeyframe();
        if (!currentKeyframe.isStatic()) {
            return (this.progress - currentKeyframe.getStartProgress()) / (currentKeyframe.getEndProgress() - currentKeyframe.getStartProgress());
        }
        return 0.0f;
    }

    public float getProgress() {
        return this.progress;
    }

    /* renamed from: getValue */
    public A mo268getValue() {
        Keyframe<K> currentKeyframe = getCurrentKeyframe();
        float interpolatedCurrentKeyframeProgress = getInterpolatedCurrentKeyframeProgress();
        if (this.valueCallback == null && currentKeyframe == this.cachedGetValueKeyframe && this.cachedGetValueProgress == interpolatedCurrentKeyframeProgress) {
            return this.cachedGetValue;
        }
        this.cachedGetValueKeyframe = currentKeyframe;
        this.cachedGetValueProgress = interpolatedCurrentKeyframeProgress;
        A mo270getValue = mo270getValue(currentKeyframe, interpolatedCurrentKeyframeProgress);
        this.cachedGetValue = mo270getValue;
        return mo270getValue;
    }

    /* renamed from: getValue */
    abstract A mo270getValue(Keyframe<K> keyframe, float f);

    public void notifyListeners() {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).onValueChanged();
        }
    }

    public void setIsDiscrete() {
        this.isDiscrete = true;
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.keyframes.isEmpty()) {
            return;
        }
        Keyframe<K> currentKeyframe = getCurrentKeyframe();
        if (f < getStartDelayProgress()) {
            f = getStartDelayProgress();
        } else if (f > getEndProgress()) {
            f = getEndProgress();
        }
        if (f == this.progress) {
            return;
        }
        this.progress = f;
        Keyframe<K> currentKeyframe2 = getCurrentKeyframe();
        if (currentKeyframe == currentKeyframe2 && currentKeyframe2.isStatic()) {
            return;
        }
        notifyListeners();
    }

    public void setValueCallback(@Nullable LottieValueCallback<A> lottieValueCallback) {
        LottieValueCallback<A> lottieValueCallback2 = this.valueCallback;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.setAnimation(null);
        }
        this.valueCallback = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation(this);
        }
    }
}
