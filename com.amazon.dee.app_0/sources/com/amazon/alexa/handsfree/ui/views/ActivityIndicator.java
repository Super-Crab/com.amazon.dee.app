package com.amazon.alexa.handsfree.ui.views;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.ui.R;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class ActivityIndicator extends LinearLayout {
    private static final float INITIAL_CIRCLE_SCALE = 0.5f;
    private static final int NUM_CIRCLES = 3;
    private List<Animator> mAnimatorList;

    public ActivityIndicator(@NonNull Context context) {
        super(context);
        this.mAnimatorList = new ArrayList();
        initialize();
    }

    private void animateCircle(final int i) {
        this.mAnimatorList.get(i).setTarget(getChildAt(i));
        this.mAnimatorList.get(i).setStartDelay(getContext().getResources().getInteger(R.integer.alexa_handsfree_activity_indicator_circle_offset) * i);
        final long integer = getContext().getResources().getInteger(R.integer.alexa_handsfree_activity_indicator_animation_duration);
        this.mAnimatorList.get(i).addListener(new AnimatorListenerAdapter() { // from class: com.amazon.alexa.handsfree.ui.views.ActivityIndicator.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(@NonNull Animator animator) {
                super.onAnimationEnd(animator);
                ((Animator) ActivityIndicator.this.mAnimatorList.get(i)).setStartDelay(integer);
                ((Animator) ActivityIndicator.this.mAnimatorList.get(i)).start();
            }
        });
        this.mAnimatorList.get(i).start();
    }

    private void initialize() {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.alexa_handsfree_activity_indicator_margin);
        setOrientation(0);
        initializeCircles(dimensionPixelSize);
    }

    private void initializeCircles(int i) {
        for (int i2 = 0; i2 < 3; i2++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.ic_loading_circle);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            if (i2 > 0) {
                layoutParams.setMarginStart(i);
            }
            imageView.setLayoutParams(layoutParams);
            imageView.setAlpha(0.0f);
            imageView.setScaleX(INITIAL_CIRCLE_SCALE);
            imageView.setScaleY(INITIAL_CIRCLE_SCALE);
            addView(imageView);
            this.mAnimatorList.add(i2, AnimatorInflater.loadAnimator(getContext(), R.animator.activity_indicator_circle));
        }
    }

    public void startAnimation() {
        for (int i = 0; i < getChildCount(); i++) {
            if (!this.mAnimatorList.get(i).isStarted()) {
                animateCircle(i);
            }
        }
    }

    public ActivityIndicator(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAnimatorList = new ArrayList();
        initialize();
    }
}
