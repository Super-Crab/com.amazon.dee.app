package com.amazon.alexa.handsfree.uservoicerecognition.ui.steps;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.ui.utils.FontAdapter;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
import com.amazon.alexa.handsfree.uservoicerecognition.model.EnrollmentStep;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.EnrollmentActivity;
import com.amazon.alexa.mosaic.components.ThemeUtil;
/* loaded from: classes8.dex */
public class StepView extends FrameLayout {
    private final Context mContext;
    private ImageView mImageView;
    private final int mStepIndex;
    private TextView mTextView;
    private final int mTotalSteps;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StepView(@NonNull Context context, int i, int i2, @NonNull EnrollmentStep.TrainingState trainingState) {
        super(context);
        this.mContext = context;
        this.mStepIndex = i;
        this.mTotalSteps = i2;
        this.mImageView = new ImageView(this.mContext);
        addView(this.mImageView);
        this.mTextView = new TextView(this.mContext);
        this.mTextView.setText(String.valueOf(this.mStepIndex + 1));
        this.mTextView.setTextColor(EnrollmentActivity.getBackgroundColor());
        this.mTextView.setGravity(17);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Bold, this.mTextView);
        addView(this.mTextView);
        setStepState(trainingState, false);
    }

    @VisibleForTesting
    Activity getActivity() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    @VisibleForTesting
    AnimatorSet getNewAnimatorSet() {
        return new AnimatorSet();
    }

    @VisibleForTesting
    Animator getNewStepChangeAnimator() {
        return AnimatorInflater.loadAnimator(this.mContext, R.animator.step_change);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStepState(@NonNull EnrollmentStep.TrainingState trainingState, boolean z) {
        if (!trainingState.equals(EnrollmentStep.TrainingState.COMPLETE) && !trainingState.equals(EnrollmentStep.TrainingState.ERROR)) {
            this.mTextView.setVisibility(0);
        } else {
            this.mTextView.setVisibility(4);
        }
        this.mImageView.setImageResource(trainingState.getDrawableRes());
        this.mImageView.clearColorFilter();
        if (trainingState.equals(EnrollmentStep.TrainingState.NOT_STARTED)) {
            this.mImageView.setColorFilter(ThemeUtil.getColorFromAttribute(this.mContext, R.attr.mosaicNeutral30));
        } else if (trainingState.equals(EnrollmentStep.TrainingState.IN_PROGRESS)) {
            this.mImageView.setColorFilter(ThemeUtil.getColorFromAttribute(this.mContext, R.attr.mosaicNeutral10));
        }
        this.mTextView.bringToFront();
        if (z) {
            Animator newStepChangeAnimator = getNewStepChangeAnimator();
            newStepChangeAnimator.setTarget(this.mImageView);
            Animator newStepChangeAnimator2 = getNewStepChangeAnimator();
            newStepChangeAnimator2.setTarget(this.mTextView);
            AnimatorSet newAnimatorSet = getNewAnimatorSet();
            newAnimatorSet.playTogether(newStepChangeAnimator, newStepChangeAnimator2);
            newAnimatorSet.start();
        }
        setContentDescription(this.mContext.getString(R.string.step_content_description, Integer.valueOf(this.mStepIndex + 1), Integer.valueOf(this.mTotalSteps), this.mContext.getString(trainingState.getContentDescriptionResource())));
    }

    StepView(@NonNull Context context, int i, int i2, ImageView imageView, TextView textView) {
        super(context);
        this.mContext = context;
        this.mStepIndex = i;
        this.mTotalSteps = i2;
        this.mImageView = imageView;
        this.mTextView = textView;
    }
}
