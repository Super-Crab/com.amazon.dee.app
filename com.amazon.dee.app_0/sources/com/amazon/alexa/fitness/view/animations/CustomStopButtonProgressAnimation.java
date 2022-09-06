package com.amazon.alexa.fitness.view.animations;

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import com.amazon.alexa.fitness.view.startTab.CustomStopButtonView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CustomStopButtonProgressAnimation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\tR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/view/animations/CustomStopButtonProgressAnimation;", "", "stopButton", "Lcom/amazon/alexa/fitness/view/startTab/CustomStopButtonView;", "(Lcom/amazon/alexa/fitness/view/startTab/CustomStopButtonView;)V", "animation", "Landroid/animation/ValueAnimator;", "kotlin.jvm.PlatformType", "start", "", "stop", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class CustomStopButtonProgressAnimation {
    private final ValueAnimator animation;
    private final CustomStopButtonView stopButton;

    public CustomStopButtonProgressAnimation(@NotNull CustomStopButtonView stopButton) {
        Intrinsics.checkParameterIsNotNull(stopButton, "stopButton");
        this.stopButton = stopButton;
        ValueAnimator ofInt = ValueAnimator.ofInt(0, 100);
        ofInt.setDuration(1500L);
        ofInt.setInterpolator(new LinearInterpolator());
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.amazon.alexa.fitness.view.animations.CustomStopButtonProgressAnimation$$special$$inlined$apply$lambda$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                CustomStopButtonView customStopButtonView;
                customStopButtonView = CustomStopButtonProgressAnimation.this.stopButton;
                Intrinsics.checkExpressionValueIsNotNull(valueAnimator, "valueAnimator");
                Object animatedValue = valueAnimator.getAnimatedValue();
                if (animatedValue != null) {
                    customStopButtonView.setCurrentAnimatedProgress(((Integer) animatedValue).intValue());
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
        });
        this.animation = ofInt;
    }

    public final void start() {
        this.animation.start();
    }

    public final void stop() {
        this.animation.cancel();
        this.stopButton.setCurrentAnimatedProgress(0);
    }
}
