package com.amazon.alexa.fitness.view.animations;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.amazon.alexa.fitness.utils.ActivityViewMetrics;
import com.amazon.alexa.fitness.utils.EventType;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.fitness.view.startTab.CustomStopButtonView;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StopButtonAnimation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u000f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/fitness/view/animations/StopButtonAnimation;", "", "()V", "isStopButtonAnimCancelled", "", "mainHandler", "Landroid/os/Handler;", "metricHelper", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "kotlin.jvm.PlatformType", "showProgressRunnable", "Ljava/lang/Runnable;", "stopButtonProgressAnimation", "Lcom/amazon/alexa/fitness/view/animations/CustomStopButtonProgressAnimation;", "stopButtonScaleAnimatorListener", "com/amazon/alexa/fitness/view/animations/StopButtonAnimation$stopButtonScaleAnimatorListener$1", "Lcom/amazon/alexa/fitness/view/animations/StopButtonAnimation$stopButtonScaleAnimatorListener$1;", "stopButtonView", "Lcom/amazon/alexa/fitness/view/startTab/CustomStopButtonView;", "setup", "", "stopButton", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class StopButtonAnimation {
    private boolean isStopButtonAnimCancelled;
    private CustomStopButtonProgressAnimation stopButtonProgressAnimation;
    private CustomStopButtonView stopButtonView;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private Mobilytics metricHelper = (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
    private final Runnable showProgressRunnable = new Runnable() { // from class: com.amazon.alexa.fitness.view.animations.StopButtonAnimation$showProgressRunnable$1
        /* JADX WARN: Code restructure failed: missing block: B:4:0x0008, code lost:
            r0 = r1.this$0.stopButtonProgressAnimation;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void run() {
            /*
                r1 = this;
                com.amazon.alexa.fitness.view.animations.StopButtonAnimation r0 = com.amazon.alexa.fitness.view.animations.StopButtonAnimation.this
                boolean r0 = com.amazon.alexa.fitness.view.animations.StopButtonAnimation.access$isStopButtonAnimCancelled$p(r0)
                if (r0 != 0) goto L13
                com.amazon.alexa.fitness.view.animations.StopButtonAnimation r0 = com.amazon.alexa.fitness.view.animations.StopButtonAnimation.this
                com.amazon.alexa.fitness.view.animations.CustomStopButtonProgressAnimation r0 = com.amazon.alexa.fitness.view.animations.StopButtonAnimation.access$getStopButtonProgressAnimation$p(r0)
                if (r0 == 0) goto L13
                r0.start()
            L13:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.fitness.view.animations.StopButtonAnimation$showProgressRunnable$1.run():void");
        }
    };
    private final StopButtonAnimation$stopButtonScaleAnimatorListener$1 stopButtonScaleAnimatorListener = new Animator.AnimatorListener() { // from class: com.amazon.alexa.fitness.view.animations.StopButtonAnimation$stopButtonScaleAnimatorListener$1
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(@Nullable Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(@Nullable Animator animator) {
            boolean z;
            Handler handler;
            Runnable runnable;
            Mobilytics metricHelper;
            z = StopButtonAnimation.this.isStopButtonAnimCancelled;
            if (!z) {
                handler = StopButtonAnimation.this.mainHandler;
                runnable = StopButtonAnimation.this.showProgressRunnable;
                handler.post(runnable);
                metricHelper = StopButtonAnimation.this.metricHelper;
                Intrinsics.checkExpressionValueIsNotNull(metricHelper, "metricHelper");
                MetricHelperKt.recordUserInteractionEvent(metricHelper, ActivityViewMetrics.Companion.getSTOP_BUTTON(), EventType.TAP);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(@Nullable Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(@Nullable Animator animator) {
        }
    };

    @SuppressLint({"ClickableViewAccessibility"})
    public final void setup(@NotNull final CustomStopButtonView stopButton) {
        Intrinsics.checkParameterIsNotNull(stopButton, "stopButton");
        this.stopButtonView = stopButton;
        this.stopButtonProgressAnimation = new CustomStopButtonProgressAnimation(stopButton);
        stopButton.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.alexa.fitness.view.animations.StopButtonAnimation$setup$1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent event) {
                Handler handler;
                Runnable runnable;
                CustomStopButtonProgressAnimation customStopButtonProgressAnimation;
                Handler handler2;
                StopButtonAnimation$stopButtonScaleAnimatorListener$1 stopButtonAnimation$stopButtonScaleAnimatorListener$1;
                final AnimatorSet animatorSet = new AnimatorSet();
                Intrinsics.checkExpressionValueIsNotNull(event, "event");
                if (event.getAction() == 0) {
                    ObjectAnimator duration = ObjectAnimator.ofFloat(stopButton, ViewProps.SCALE_X, 1.33f).setDuration(200L);
                    Intrinsics.checkExpressionValueIsNotNull(duration, "ObjectAnimator.ofFloat(s…uration(ANIM_DURATION_MS)");
                    duration.setInterpolator(new DecelerateInterpolator(1.72f));
                    ObjectAnimator duration2 = ObjectAnimator.ofFloat(stopButton, ViewProps.SCALE_Y, 1.33f).setDuration(200L);
                    Intrinsics.checkExpressionValueIsNotNull(duration2, "ObjectAnimator.ofFloat(s…uration(ANIM_DURATION_MS)");
                    duration2.setInterpolator(new DecelerateInterpolator(1.72f));
                    animatorSet.playTogether(duration, duration2);
                    animatorSet.start();
                    StopButtonAnimation.this.isStopButtonAnimCancelled = false;
                    stopButtonAnimation$stopButtonScaleAnimatorListener$1 = StopButtonAnimation.this.stopButtonScaleAnimatorListener;
                    animatorSet.addListener(stopButtonAnimation$stopButtonScaleAnimatorListener$1);
                }
                if (event.getAction() == 1) {
                    StopButtonAnimation.this.isStopButtonAnimCancelled = true;
                    handler = StopButtonAnimation.this.mainHandler;
                    runnable = StopButtonAnimation.this.showProgressRunnable;
                    handler.removeCallbacks(runnable);
                    customStopButtonProgressAnimation = StopButtonAnimation.this.stopButtonProgressAnimation;
                    if (customStopButtonProgressAnimation != null) {
                        customStopButtonProgressAnimation.stop();
                    }
                    handler2 = StopButtonAnimation.this.mainHandler;
                    handler2.post(new Runnable() { // from class: com.amazon.alexa.fitness.view.animations.StopButtonAnimation$setup$1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            animatorSet.cancel();
                            AnimatorSet animatorSet2 = new AnimatorSet();
                            ObjectAnimator duration3 = ObjectAnimator.ofFloat(stopButton, ViewProps.SCALE_X, 1.0f).setDuration(200L);
                            Intrinsics.checkExpressionValueIsNotNull(duration3, "ObjectAnimator.ofFloat(s…uration(ANIM_DURATION_MS)");
                            duration3.setInterpolator(new DecelerateInterpolator(1.72f));
                            ObjectAnimator duration4 = ObjectAnimator.ofFloat(stopButton, ViewProps.SCALE_Y, 1.0f).setDuration(200L);
                            Intrinsics.checkExpressionValueIsNotNull(duration4, "ObjectAnimator.ofFloat(s…uration(ANIM_DURATION_MS)");
                            duration4.setInterpolator(new DecelerateInterpolator(1.72f));
                            animatorSet2.playTogether(duration3, duration4);
                            animatorSet2.start();
                        }
                    });
                }
                return false;
            }
        });
    }
}
