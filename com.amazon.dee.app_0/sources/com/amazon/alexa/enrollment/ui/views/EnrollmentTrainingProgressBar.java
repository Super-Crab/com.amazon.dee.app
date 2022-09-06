package com.amazon.alexa.enrollment.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.module.library.Injector;
import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class EnrollmentTrainingProgressBar extends LinearLayout {
    private static final int DEFAULT_NUM_TRAINING_PHRASES = 4;
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentTrainingProgressBar.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private Integer currentTrainingIndex;
    private final Integer doneImageRes;
    private final List<View> enrollmentProgressViewList;
    @Inject
    EnrollmentThemeUtil enrollmentThemeUtil;
    private final Integer errorImageRes;
    private final Integer inProgressImageRes;
    private final Integer nonDoneImageRes;

    public EnrollmentTrainingProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.enrollmentProgressViewList = new ArrayList();
        Injector.inject(this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.EnrollmentTrainingProgressBar, 0, 0);
        int integer = obtainStyledAttributes.getInteger(R.styleable.EnrollmentTrainingProgressBar_numPhrases, 4);
        this.currentTrainingIndex = Integer.valueOf(obtainStyledAttributes.getInteger(R.styleable.EnrollmentTrainingProgressBar_currTrainingIndex, 0));
        this.nonDoneImageRes = Integer.valueOf(obtainStyledAttributes.getResourceId(R.styleable.EnrollmentTrainingProgressBar_nonDoneImage, 0));
        this.inProgressImageRes = Integer.valueOf(obtainStyledAttributes.getResourceId(R.styleable.EnrollmentTrainingProgressBar_inProgressImage, 0));
        this.doneImageRes = Integer.valueOf(obtainStyledAttributes.getResourceId(R.styleable.EnrollmentTrainingProgressBar_doneImage, 0));
        this.errorImageRes = Integer.valueOf(obtainStyledAttributes.getResourceId(R.styleable.EnrollmentTrainingProgressBar_errorImage, 0));
        if (this.currentTrainingIndex.intValue() >= integer) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid currentTrainingIndex ");
            outline107.append(this.currentTrainingIndex);
            outline107.append(" and numOfPhrases");
            outline107.append(integer);
            Log.e(str, outline107.toString());
            return;
        }
        setOrientation(0);
        initializeProgressBar(integer);
        updateCurrentTrainingIndex(this.currentTrainingIndex.intValue());
        obtainStyledAttributes.recycle();
    }

    private View buildEnrollmentProgressView() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.enrollment_progress_view, (ViewGroup) null);
        inflate.setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 1.0f));
        ((TextView) inflate.findViewById(R.id.utterence_status)).setBackground(getContext().getDrawable(R.drawable.icon_enrollment_phrase_not_completed));
        return inflate;
    }

    private void initializeProgressBar(int i) {
        this.enrollmentProgressViewList.clear();
        removeAllViews();
        for (int i2 = 0; i2 < i; i2++) {
            View buildEnrollmentProgressView = buildEnrollmentProgressView();
            addView(buildEnrollmentProgressView);
            this.enrollmentProgressViewList.add(buildEnrollmentProgressView);
        }
    }

    public void updateCurrentTrainingIndex(int i, boolean z) {
        int size = this.enrollmentProgressViewList.size();
        if (i >= 0 && i <= size) {
            this.currentTrainingIndex = Integer.valueOf(i);
            int i2 = 0;
            while (i2 < i) {
                TextView textView = (TextView) this.enrollmentProgressViewList.get(i2).findViewById(R.id.utterence_status);
                textView.setText("");
                this.enrollmentThemeUtil.setBackgroundToView(getContext(), R.drawable.icon_enrollment_phrase_completed_mosaic, textView);
                if (!z && i != 0 && i2 == i - 1) {
                    textView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.image_update_anim));
                }
                i2++;
            }
            while (i2 < size) {
                TextView textView2 = (TextView) this.enrollmentProgressViewList.get(i2).findViewById(R.id.utterence_status);
                this.enrollmentThemeUtil.setTextColor(getContext(), R.attr.mosaicBackground, textView2);
                this.enrollmentThemeUtil.setBackgroundToView(getContext(), R.drawable.icon_enrollment_phrase_not_completed_mosaic, textView2);
                i2++;
                textView2.setText(String.valueOf(i2));
            }
            if (i < size) {
                TextView textView3 = (TextView) this.enrollmentProgressViewList.get(i).findViewById(R.id.utterence_status);
                if (z) {
                    textView3.setBackground(getContext().getDrawable(this.errorImageRes.intValue()));
                    textView3.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.image_update_anim));
                    textView3.setText("");
                } else {
                    textView3.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_anim));
                    this.enrollmentThemeUtil.setTextColor(getContext(), R.attr.mosaicBackground, textView3);
                    this.enrollmentThemeUtil.setBackgroundToView(getContext(), R.drawable.icon_enrollment_phrase_in_progress_mosaic, textView3);
                }
            }
        } else {
            Log.e(TAG, "Invalid current training index");
        }
        invalidate();
        requestLayout();
    }

    public void updateNumPhrases(int i) {
        if (this.enrollmentProgressViewList.size() == i) {
            return;
        }
        initializeProgressBar(i);
    }

    public void updateCurrentTrainingIndex(int i) {
        updateCurrentTrainingIndex(i, false);
    }
}
