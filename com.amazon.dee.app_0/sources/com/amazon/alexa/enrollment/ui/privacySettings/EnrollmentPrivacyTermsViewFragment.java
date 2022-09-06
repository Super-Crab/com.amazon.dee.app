package com.amazon.alexa.enrollment.ui.privacySettings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.module.library.Injector;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment;
import com.amazon.alexa.enrollment.ui.DebounceOnClickListener;
import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class EnrollmentPrivacyTermsViewFragment extends AbstractEnrollmentViewFragment {
    @Inject
    EnrollmentMetricsRecorder enrollmentMetricsRecorder;
    @Inject
    EnrollmentThemeUtil enrollmentThemeUtil;
    private DebounceOnClickListener privacyTermsPageOkButtonClickListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.enrollment.ui.privacySettings.EnrollmentPrivacyTermsViewFragment.1
        @Override // com.amazon.alexa.enrollment.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            EnrollmentPrivacyTermsViewFragment.this.continueButtonClick(view);
        }
    };
    private View rootView;

    public EnrollmentPrivacyTermsViewFragment() {
    }

    public static EnrollmentPrivacyTermsViewFragment createInstance() {
        return new EnrollmentPrivacyTermsViewFragment();
    }

    private void setMosaicThemeColour() {
        this.enrollmentThemeUtil.setBackgroundColorToView(getContext(), R.attr.mosaicBackground, this.rootView);
        this.enrollmentThemeUtil.setTextColor(getContext(), R.attr.mosaicNeutral10, (TextView) this.rootView.findViewById(R.id.privacy_page_warning_header), (TextView) this.rootView.findViewById(R.id.privacy_page_warning_desc_one), (TextView) this.rootView.findViewById(R.id.privacy_page_warning_desc_two));
        this.enrollmentThemeUtil.setBackgroundToView(getContext(), R.drawable.primary_button_mosaic_background, this.rootView.findViewById(R.id.privacy_page_ok_btn));
        this.enrollmentThemeUtil.setTintColorToImageView((ImageView) this.rootView.findViewById(R.id.privacy_page_back), getContext(), R.attr.mosaicNeutral10);
        this.enrollmentThemeUtil.setTintColorToImageView((ImageView) this.rootView.findViewById(R.id.privacy_page_warning), getContext(), R.attr.mosaicNeutral10);
    }

    public void continueButtonClick(View view) {
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.PRIVACY_TERMS_PAGE_OK_CLICK);
        finishEnrollmentWithFailureStatus();
    }

    @VisibleForTesting
    void injectDependency() {
        Injector.inject(this);
    }

    public /* synthetic */ void lambda$onCreateView$0$EnrollmentPrivacyTermsViewFragment(View view) {
        this.privacyTermsPageOkButtonClickListener.onClick(view);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        injectDependency();
        this.enrollmentThemeUtil.setTheme(getContext());
        this.rootView = layoutInflater.inflate(R.layout.enrollment_privacy_terms_view, viewGroup, false);
        this.rootView.findViewById(R.id.privacy_page_ok_btn).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.enrollment.ui.privacySettings.-$$Lambda$EnrollmentPrivacyTermsViewFragment$2dFp2TCdRym88d27BzSikorcWEc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EnrollmentPrivacyTermsViewFragment.this.lambda$onCreateView$0$EnrollmentPrivacyTermsViewFragment(view);
            }
        });
        setMosaicThemeColour();
        return this.rootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.privacyTermsPageOkButtonClickListener.cleanUp();
    }

    @VisibleForTesting
    EnrollmentPrivacyTermsViewFragment(EnrollmentThemeUtil enrollmentThemeUtil, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        this.enrollmentThemeUtil = enrollmentThemeUtil;
        this.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }
}
