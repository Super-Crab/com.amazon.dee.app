package com.amazon.alexa.growth.coachmark;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.res.Resources;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.TextView;
import com.amazon.alexa.growth.R;
import com.github.florent37.viewtooltip.ViewTooltip;
/* loaded from: classes8.dex */
public class CoachMarkAnimation implements ViewTooltip.TooltipAnimation {
    private final TextView customTextView;
    private final Resources resources;

    public CoachMarkAnimation(Resources resources, TextView textView) {
        this.resources = resources;
        this.customTextView = textView;
    }

    @Override // com.github.florent37.viewtooltip.ViewTooltip.TooltipAnimation
    public void animateEnter(View view, Animator.AnimatorListener animatorListener) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.SCALE_X, 0.0f, 1.05f), PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.0f, 1.05f));
        ofPropertyValuesHolder.setDuration(this.resources.getInteger(R.integer.coachmark_animate_enter_scale_up_duration));
        ofPropertyValuesHolder.setInterpolator(new PathInterpolator(0.333f, 0.0f, 0.333f, 1.0f));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f, 1.0f);
        ofFloat.setDuration(this.resources.getInteger(R.integer.coachmark_animate_enter_bubble_fade_in_duration));
        ofFloat.setInterpolator(new LinearInterpolator());
        ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f), PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f));
        ofPropertyValuesHolder2.setDuration(this.resources.getInteger(R.integer.coachmark_animate_enter_scale_down_duration));
        ofPropertyValuesHolder2.setInterpolator(new PathInterpolator(0.6f, 0.0f, 1.0f, 0.4f));
        this.customTextView.setAlpha(0.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.customTextView, View.ALPHA, 0.0f, 1.0f);
        ofFloat2.setDuration(this.resources.getInteger(R.integer.coachmark_animate_enter_text_fade_in_duration));
        ofFloat2.setInterpolator(new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(animatorListener);
        animatorSet.play(ofPropertyValuesHolder).with(ofFloat).before(ofFloat2).before(ofPropertyValuesHolder2);
        animatorSet.start();
    }

    @Override // com.github.florent37.viewtooltip.ViewTooltip.TooltipAnimation
    public void animateExit(View view, Animator.AnimatorListener animatorListener) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f);
        ofFloat.setDuration(this.resources.getInteger(R.integer.coachmark_animate_exit_text_fade_out_duration));
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.addListener(animatorListener);
        ofFloat.start();
    }
}
