package com.amazon.alexa.enrollment.ui.complete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.module.library.Injector;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment;
import com.amazon.alexa.enrollment.utils.AnimationHelper;
import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import com.amazon.alexa.enrollment.utils.EnrollmentUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class EnrollmentCompleteViewFragment extends AbstractEnrollmentViewFragment {
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentCompleteViewFragment.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    @Inject
    AnimationHelper animationHelper;
    @Inject
    EnrollmentMetricsRecorder enrollmentMetricsRecorder;
    @Inject
    EnrollmentThemeUtil enrollmentThemeUtil;
    private View rootView;

    public EnrollmentCompleteViewFragment() {
    }

    public static EnrollmentCompleteViewFragment createInstance() {
        return new EnrollmentCompleteViewFragment();
    }

    private void setMosaicThemeColour() {
        this.enrollmentThemeUtil.setBackgroundColorToView(getContext(), R.attr.mosaicBackground, this.rootView);
        this.enrollmentThemeUtil.setTextColor(getContext(), R.attr.mosaicNeutral10, (TextView) this.rootView.findViewById(R.id.all_set), (TextView) this.rootView.findViewById(R.id.all_set_desc));
        this.enrollmentThemeUtil.setBackgroundToView(getContext(), R.drawable.primary_button_mosaic_background, this.rootView.findViewById(R.id.done_btn));
    }

    private void startCompletionPageAnimation() {
        this.animationHelper.renderTickBoxAnimation((ImageView) this.rootView.findViewById(R.id.all_done_icon));
        this.animationHelper.renderVerticalFadeInAnimationForCompletionPage(getContext(), this.rootView.findViewById(R.id.all_set_layout));
        this.animationHelper.renderFadeInAnimationForButton(getContext(), this.rootView.findViewById(R.id.done_btn));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void finishActivity(View view) {
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.COMPLETION_BTN_CLICKED);
        finishEnrollmentWithSuccessStatus();
    }

    @VisibleForTesting
    String getStringResource(int i) {
        return getString(i);
    }

    @VisibleForTesting
    void injectDependency() {
        Injector.inject(this);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        injectDependency();
        this.enrollmentMetricsRecorder.initializeMetricsContext(getEnrollmentContext());
        this.enrollmentMetricsRecorder.recordUserViewInteraction(MetricsConstants.UserInteractionMetrics.COMPLETION_PAGE_VIEW);
        this.enrollmentThemeUtil.setTheme(getContext());
        this.rootView = layoutInflater.inflate(R.layout.activity_enrollment_complete, viewGroup, false);
        setMosaicThemeColour();
        Button button = (Button) this.rootView.findViewById(R.id.done_btn);
        if (EnrollmentUtil.isOOBE(getEnrollmentContext())) {
            button.setText(getStringResource(R.string.complete_done_oobe));
        } else {
            button.setText(getStringResource(R.string.complete_done));
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.enrollment.ui.complete.-$$Lambda$kmsC6hhngOl4KFIKuJQNnvmJ5Mg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EnrollmentCompleteViewFragment.this.finishActivity(view);
            }
        });
        return this.rootView;
    }

    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        startCompletionPageAnimation();
    }

    @VisibleForTesting
    EnrollmentCompleteViewFragment(AnimationHelper animationHelper, EnrollmentThemeUtil enrollmentThemeUtil, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        this.animationHelper = animationHelper;
        this.enrollmentThemeUtil = enrollmentThemeUtil;
        this.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }
}
