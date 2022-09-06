package com.amazon.alexa.enrollment.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class AnimationHelper {
    private static final long PULSE_DURATION = 500;
    private static final long PULSE_START_OFFSET = 333;
    private static final float TO_ALPHA_FOR_PULSE = 1.0f;
    private static final String TAG = GeneratedOutlineSupport1.outline39(AnimationHelper.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private static final float FROM_ALPHA_FOR_PULSE = 0.4f;
    private static final Interpolator CUBIC_BEZIER_INTERPOLATOR = PathInterpolatorCompat.create(0.0f, 0.6f, FROM_ALPHA_FOR_PULSE, 1.0f);

    @VisibleForTesting
    Animation getAnimation(Context context, int i) {
        return AnimationUtils.loadAnimation(context, i);
    }

    @VisibleForTesting
    Animation getPulseAnimation() {
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        AlphaAnimation alphaAnimation = new AlphaAnimation((float) FROM_ALPHA_FOR_PULSE, 1.0f);
        alphaAnimation.setInterpolator(linearInterpolator);
        alphaAnimation.setDuration(500L);
        alphaAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setStartOffset(333L);
        return alphaAnimation;
    }

    @VisibleForTesting
    Animation getVerticalFadeInAnimation(Context context) {
        Animation animation = getAnimation(context, R.anim.vertical_fade_anim);
        animation.setInterpolator(CUBIC_BEZIER_INTERPOLATOR);
        return animation;
    }

    @VisibleForTesting
    Animation getVerticalFadeInAnimationForAllSetPage(Context context) {
        Animation animation = getAnimation(context, R.anim.vertical_fade_anim_all_set);
        animation.setInterpolator(CUBIC_BEZIER_INTERPOLATOR);
        return animation;
    }

    public void renderFadeInAnimation(Context context, View view) {
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        Animation animation = getAnimation(context, R.anim.fade_in_anim);
        animation.setInterpolator(linearInterpolator);
        view.setAnimation(animation);
    }

    public void renderFadeInAnimationForButton(Context context, View view) {
        view.setAnimation(getAnimation(context, R.anim.button_anim));
    }

    public void renderProgressBarBounceAnimation(Context context, View view) {
        view.setAnimation(getAnimation(context, R.anim.image_update_anim));
    }

    public void renderPulseAnimation(Context context, View view) {
        view.setAnimation(getPulseAnimation());
    }

    public void renderTickBoxAnimation(ImageView imageView) {
        imageView.setBackgroundResource(R.drawable.done_check_box);
        ((AnimationDrawable) imageView.getBackground()).start();
    }

    public void renderVerticalFadeInAnimation(Context context, View view) {
        view.setAnimation(getVerticalFadeInAnimation(context));
    }

    public void renderVerticalFadeInAnimationForCompletionPage(Context context, View view) {
        view.setAnimation(getVerticalFadeInAnimationForAllSetPage(context));
    }

    public void renderVerticalSideWithPulseAnimation(Context context, View view) {
        Animation pulseAnimation = getPulseAnimation();
        Animation verticalFadeInAnimation = getVerticalFadeInAnimation(context);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(verticalFadeInAnimation);
        animationSet.addAnimation(pulseAnimation);
        view.setAnimation(animationSet);
    }
}
