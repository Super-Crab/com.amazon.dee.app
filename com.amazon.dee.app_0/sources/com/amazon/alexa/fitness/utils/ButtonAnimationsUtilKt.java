package com.amazon.alexa.fitness.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.view.startTab.CustomStopButtonView;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ButtonAnimationsUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0002\b\u000b\u001a\n\u0010\r\u001a\u00020\u000e*\u00020\u000f\u001a\n\u0010\u0010\u001a\u00020\u000e*\u00020\u000f\u001a\u0018\u0010\u0011\u001a\u00020\u000e*\b\u0012\u0004\u0012\u00020\u000f0\u00122\u0006\u0010\u0013\u001a\u00020\u0014\u001a\n\u0010\u0015\u001a\u00020\u000e*\u00020\u000f\u001a\n\u0010\u0016\u001a\u00020\u000e*\u00020\u000f\u001a\n\u0010\u0017\u001a\u00020\u000e*\u00020\u0018\u001a\n\u0010\u0017\u001a\u00020\u000e*\u00020\u0019\u001a\n\u0010\u001a\u001a\u00020\u000e*\u00020\u000f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t\"\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u001b"}, d2 = {"DONE_BUTTON_ANIM_DURATION_MS", "", "FADE_IN_VIEW_DELAY_MS", "FADE_IN_VIEW_DURATION_MS", "FADE_OUT_VIEW_DURATION_MS", "TAG", "", "resumeButtonAnimatorListener", "com/amazon/alexa/fitness/utils/ButtonAnimationsUtilKt$resumeButtonAnimatorListener$1", "Lcom/amazon/alexa/fitness/utils/ButtonAnimationsUtilKt$resumeButtonAnimatorListener$1;", "stopButtonAnimationListener", "com/amazon/alexa/fitness/utils/ButtonAnimationsUtilKt$stopButtonAnimationListener$1", "Lcom/amazon/alexa/fitness/utils/ButtonAnimationsUtilKt$stopButtonAnimationListener$1;", "fadeInView", "", "Landroid/view/View;", "fadeInViewWithoutDelay", "fadeOutViews", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/animation/Animator$AnimatorListener;", "hideResumeButton", "hideStopButton", "showButton", "Landroid/widget/ImageButton;", "Lcom/amazon/alexa/fitness/view/startTab/CustomStopButtonView;", "showCustomButton", "AlexaMobileAndroidFitnessUI_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ButtonAnimationsUtilKt {
    private static final long DONE_BUTTON_ANIM_DURATION_MS = 416;
    private static final long FADE_IN_VIEW_DELAY_MS = 166;
    private static final long FADE_IN_VIEW_DURATION_MS = 333;
    private static final long FADE_OUT_VIEW_DURATION_MS = 166;
    private static final String TAG = "AFX-RevealButtonAnim";
    private static final ButtonAnimationsUtilKt$stopButtonAnimationListener$1 stopButtonAnimationListener = new Animation.AnimationListener() { // from class: com.amazon.alexa.fitness.utils.ButtonAnimationsUtilKt$stopButtonAnimationListener$1
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(@Nullable Animation animation) {
            View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
            ImageButton imageButton = fullScreenView != null ? (ImageButton) fullScreenView.findViewById(R.id.btn_stop) : null;
            if (imageButton != null) {
                imageButton.setAlpha(0.0f);
            }
            if (imageButton != null) {
                imageButton.setVisibility(8);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(@Nullable Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(@Nullable Animation animation) {
        }
    };
    private static final ButtonAnimationsUtilKt$resumeButtonAnimatorListener$1 resumeButtonAnimatorListener = new Animation.AnimationListener() { // from class: com.amazon.alexa.fitness.utils.ButtonAnimationsUtilKt$resumeButtonAnimatorListener$1
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(@Nullable Animation animation) {
            ImageButton imageButton;
            View fullScreenView = FullScreenUtil.Companion.getFullScreenView();
            ImageButton imageButton2 = fullScreenView != null ? (ImageButton) fullScreenView.findViewById(R.id.btn_resume) : null;
            if (imageButton2 != null) {
                imageButton2.setAlpha(0.0f);
            }
            if (imageButton2 != null) {
                imageButton2.setVisibility(8);
            }
            View fullScreenView2 = FullScreenUtil.Companion.getFullScreenView();
            if (fullScreenView2 == null || (imageButton = (ImageButton) fullScreenView2.findViewById(R.id.btn_pause)) == null) {
                return;
            }
            imageButton.setVisibility(0);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(@Nullable Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(@Nullable Animation animation) {
        }
    };

    public static final void fadeInView(@NotNull View fadeInView) {
        Intrinsics.checkParameterIsNotNull(fadeInView, "$this$fadeInView");
        fadeInView.setAlpha(0.0f);
        fadeInView.animate().alpha(1.0f).setDuration(333L).setStartDelay(166L);
    }

    public static final void fadeInViewWithoutDelay(@NotNull View fadeInViewWithoutDelay) {
        Intrinsics.checkParameterIsNotNull(fadeInViewWithoutDelay, "$this$fadeInViewWithoutDelay");
        fadeInViewWithoutDelay.setAlpha(0.0f);
        fadeInViewWithoutDelay.setVisibility(0);
        fadeInViewWithoutDelay.animate().alpha(1.0f).setDuration(333L);
    }

    public static final void fadeOutViews(@NotNull List<? extends View> fadeOutViews, @NotNull Animator.AnimatorListener listener) {
        Intrinsics.checkParameterIsNotNull(fadeOutViews, "$this$fadeOutViews");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        for (View view : fadeOutViews) {
            ObjectAnimator duration = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f).setDuration(166L);
            Intrinsics.checkExpressionValueIsNotNull(duration, "ObjectAnimator.ofFloat(i…ADE_OUT_VIEW_DURATION_MS)");
            arrayList.add(duration);
        }
        animatorSet.playTogether(arrayList);
        animatorSet.addListener(listener);
        animatorSet.start();
    }

    public static final void hideResumeButton(@NotNull final View hideResumeButton) {
        Intrinsics.checkParameterIsNotNull(hideResumeButton, "$this$hideResumeButton");
        hideResumeButton.setAnimation(AnimationUtils.loadAnimation(hideResumeButton.getContext(), R.anim.slide_left_hide));
        hideResumeButton.getAnimation().setAnimationListener(resumeButtonAnimatorListener);
        hideResumeButton.getHandler().post(new Runnable() { // from class: com.amazon.alexa.fitness.utils.ButtonAnimationsUtilKt$hideResumeButton$2
            @Override // java.lang.Runnable
            public final void run() {
                hideResumeButton.invalidate();
                hideResumeButton.requestLayout();
                hideResumeButton.getAnimation().start();
            }
        });
    }

    public static final void hideStopButton(@NotNull final View hideStopButton) {
        Intrinsics.checkParameterIsNotNull(hideStopButton, "$this$hideStopButton");
        hideStopButton.setAnimation(AnimationUtils.loadAnimation(hideStopButton.getContext(), R.anim.slide_right_hide));
        hideStopButton.getAnimation().setAnimationListener(stopButtonAnimationListener);
        hideStopButton.getHandler().post(new Runnable() { // from class: com.amazon.alexa.fitness.utils.ButtonAnimationsUtilKt$hideStopButton$2
            @Override // java.lang.Runnable
            public final void run() {
                hideStopButton.invalidate();
                hideStopButton.requestLayout();
                hideStopButton.getAnimation().start();
            }
        });
    }

    public static final void showButton(@NotNull ImageButton showButton) {
        Intrinsics.checkParameterIsNotNull(showButton, "$this$showButton");
        showButton.setAlpha(1.0f);
        showButton.setAnimation(AnimationUtils.loadAnimation(showButton.getContext(), R.anim.slide_right));
        Animation animation = showButton.getAnimation();
        Intrinsics.checkExpressionValueIsNotNull(animation, "this.animation");
        animation.setFillAfter(true);
        showButton.getAnimation().start();
    }

    public static final void showCustomButton(@NotNull View showCustomButton) {
        Intrinsics.checkParameterIsNotNull(showCustomButton, "$this$showCustomButton");
        showCustomButton.setAlpha(0.0f);
        showCustomButton.setVisibility(0);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator duration = ObjectAnimator.ofFloat(showCustomButton, ViewProps.SCALE_X, 0.7f, 1.0f).setDuration(DONE_BUTTON_ANIM_DURATION_MS);
        Intrinsics.checkExpressionValueIsNotNull(duration, "ObjectAnimator.ofFloat(t…_BUTTON_ANIM_DURATION_MS)");
        duration.setInterpolator(new DecelerateInterpolator(1.72f));
        duration.setStartDelay(166L);
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(showCustomButton, ViewProps.SCALE_Y, 0.7f, 1.0f).setDuration(DONE_BUTTON_ANIM_DURATION_MS);
        Intrinsics.checkExpressionValueIsNotNull(duration2, "ObjectAnimator.ofFloat(t…_BUTTON_ANIM_DURATION_MS)");
        duration2.setInterpolator(new DecelerateInterpolator(1.72f));
        duration2.setStartDelay(166L);
        ObjectAnimator duration3 = ObjectAnimator.ofFloat(showCustomButton, "alpha", 0.0f, 1.0f).setDuration(DONE_BUTTON_ANIM_DURATION_MS);
        Intrinsics.checkExpressionValueIsNotNull(duration3, "ObjectAnimator.ofFloat(t…_BUTTON_ANIM_DURATION_MS)");
        animatorSet.playTogether(duration, duration2, duration3);
        animatorSet.start();
    }

    public static final void showButton(@NotNull CustomStopButtonView showButton) {
        Intrinsics.checkParameterIsNotNull(showButton, "$this$showButton");
        showButton.setAlpha(1.0f);
        showButton.setVisibility(0);
        showButton.setAnimation(AnimationUtils.loadAnimation(showButton.getContext(), R.anim.slide_left));
        Animation animation = showButton.getAnimation();
        Intrinsics.checkExpressionValueIsNotNull(animation, "this.animation");
        animation.setFillAfter(true);
        showButton.getAnimation().start();
    }
}
