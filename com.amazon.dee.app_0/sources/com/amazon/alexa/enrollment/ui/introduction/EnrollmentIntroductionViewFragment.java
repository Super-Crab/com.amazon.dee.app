package com.amazon.alexa.enrollment.ui.introduction;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.dialogs.DialogConstants;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.module.library.Injector;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment;
import com.amazon.alexa.enrollment.ui.DebounceOnClickListener;
import com.amazon.alexa.enrollment.ui.terms.EnrollmentTermsActivity;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingDialogHelper;
import com.amazon.alexa.enrollment.utils.ActivityConstants;
import com.amazon.alexa.enrollment.utils.ActivityUtils;
import com.amazon.alexa.enrollment.utils.AnimationHelper;
import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import com.amazon.alexa.enrollment.utils.EnrollmentUtil;
import com.amazon.alexa.enrollment.utils.PermissionsHelper;
import com.amazon.alexa.enrollment.utils.TermsTextGenerator;
import com.amazon.alexa.identity.api.IdentityService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class EnrollmentIntroductionViewFragment extends AbstractEnrollmentViewFragment {
    private static final int AUDIO_PERMISSION_REQUEST_CODE = 1;
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentIntroductionViewFragment.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    @Inject
    AnimationHelper animationHelper;
    @Inject
    DeviceInformation deviceInformation;
    @Inject
    EnrollmentTrainingDialogHelper dialogHelper;
    @Inject
    EnrollmentMetricsRecorder enrollmentMetricsRecorder;
    @Inject
    EnrollmentThemeUtil enrollmentThemeUtil;
    @Inject
    IdentityService identityService;
    @Inject
    PermissionsHelper permissionsHelper;
    private View rootView;
    private EnrollmentIntroductionUiModel uiModel;
    @Inject
    EnrollmentIntroductionViewModel viewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private DebounceOnClickListener continueButtonClickListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionViewFragment.1
        @Override // com.amazon.alexa.enrollment.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            EnrollmentIntroductionViewFragment.this.continueButtonClick(view);
        }
    };
    private DebounceOnClickListener termsClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionViewFragment.2
        @Override // com.amazon.alexa.enrollment.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            Log.i(EnrollmentIntroductionViewFragment.TAG, "User clicked terms page");
            EnrollmentIntroductionViewFragment.this.getActivity().startActivity(EnrollmentIntroductionViewFragment.this.getTermsActivityIntent());
        }
    };
    private DebounceOnClickListener learnMoreClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionViewFragment.3
        @Override // com.amazon.alexa.enrollment.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            Log.i(EnrollmentIntroductionViewFragment.TAG, "User clicked terms page");
            EnrollmentIntroductionViewFragment.this.learnMoreClickActivity();
        }
    };
    private DebounceOnClickListener privacyClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionViewFragment.4
        @Override // com.amazon.alexa.enrollment.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            Log.i(EnrollmentIntroductionViewFragment.TAG, "User clicked privacy link");
            EnrollmentIntroductionViewFragment.this.startPrivacyClickActivity();
        }
    };

    @VisibleForTesting
    public EnrollmentIntroductionViewFragment(EnrollmentIntroductionViewModel enrollmentIntroductionViewModel, PermissionsHelper permissionsHelper, IdentityService identityService, AnimationHelper animationHelper, DeviceInformation deviceInformation, EnrollmentTrainingDialogHelper enrollmentTrainingDialogHelper, EnrollmentThemeUtil enrollmentThemeUtil, EnrollmentMetricsRecorder enrollmentMetricsRecorder, View view) {
        this.viewModel = enrollmentIntroductionViewModel;
        this.permissionsHelper = permissionsHelper;
        this.identityService = identityService;
        this.animationHelper = animationHelper;
        this.deviceInformation = deviceInformation;
        this.dialogHelper = enrollmentTrainingDialogHelper;
        this.enrollmentThemeUtil = enrollmentThemeUtil;
        this.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
        this.rootView = view;
    }

    private void animateIntroPage() {
        this.animationHelper.renderVerticalFadeInAnimation(getContext(), this.rootView.findViewById(R.id.desc));
    }

    public static EnrollmentIntroductionViewFragment createInstance() {
        return new EnrollmentIntroductionViewFragment();
    }

    private void setMosaicThemeColour() {
        this.enrollmentThemeUtil.setImage((ImageView) this.rootView.findViewById(R.id.teach_alexa_image), R.drawable.img_oobe_primer_voice_mosaic_theme);
        this.enrollmentThemeUtil.setBackgroundColorToView(getContext(), R.attr.mosaicBackground, this.rootView);
        this.enrollmentThemeUtil.setTextColor(getContext(), R.attr.mosaicNeutral10, (TextView) this.rootView.findViewById(R.id.title));
        this.enrollmentThemeUtil.setTextColor(getContext(), R.attr.mosaicNeutral10, (TextView) this.rootView.findViewById(R.id.nav_title));
        this.enrollmentThemeUtil.setTextColor(getContext(), R.attr.mosaicNeutral20, (TextView) this.rootView.findViewById(R.id.desc), (TextView) this.rootView.findViewById(R.id.intro_terms_text_view));
        this.enrollmentThemeUtil.setTextColor(getContext(), R.attr.mosaicAction10, (TextView) this.rootView.findViewById(R.id.skip_footer), (TextView) this.rootView.findViewById(R.id.skip));
        this.enrollmentThemeUtil.setBackgroundToView(getContext(), R.drawable.primary_button_mosaic_background, this.rootView.findViewById(R.id.continue_btn));
        this.enrollmentThemeUtil.setTintColorToImageView((ImageView) this.rootView.findViewById(R.id.back), getContext(), R.attr.mosaicNeutral10);
    }

    private void showSkipOptionInFooter() {
        this.rootView.findViewById(R.id.skip_footer).setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1.0f));
        this.rootView.findViewById(R.id.skip_footer).setVisibility(0);
    }

    @VisibleForTesting
    void checkForVoiceEnrollmentEligibility() {
        DeviceInformation deviceInformation = this.deviceInformation;
        if (deviceInformation != null && deviceInformation.isFireOS()) {
            Log.i(TAG, "Vox enrollment in Fire Tablet is restricted");
            EnrollmentMetricsRecorder enrollmentMetricsRecorder = this.enrollmentMetricsRecorder;
            if (enrollmentMetricsRecorder != null) {
                enrollmentMetricsRecorder.recordCounter(MetricsConstants.OperationalMetrics.VOICE_ENROLLMENT_RESTRICTED_IN_FIRE_TABLET);
            }
            onEligibilityCheckResult(false);
        } else if (EnrollmentUtil.isOOBE(getEnrollmentContext())) {
            this.rootView.findViewById(R.id.introduction_view).setVisibility(4);
            showLoading(null);
            getDecorViewForActivity().setBackgroundColor(getResources().getColor(R.color.training_background));
            this.enrollmentThemeUtil.setBackgroundColorToView(getContext(), R.attr.mosaicBackground, getDecorViewForActivity());
            this.compositeDisposable.add(this.viewModel.isEligibleForVoiceEnrollment(getEnrollmentContext()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.enrollment.ui.introduction.-$$Lambda$7JcT5_VNzRbmRqvabDiuziXP1-0
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    EnrollmentIntroductionViewFragment.this.onEligibilityCheckResult(((Boolean) obj).booleanValue());
                }
            }, new Consumer() { // from class: com.amazon.alexa.enrollment.ui.introduction.-$$Lambda$EnrollmentIntroductionViewFragment$QEWFaZcQOm0ziFTXJny-vOBsZf0
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    EnrollmentIntroductionViewFragment.this.lambda$checkForVoiceEnrollmentEligibility$1$EnrollmentIntroductionViewFragment((Throwable) obj);
                }
            }));
        } else {
            updateUI();
        }
    }

    @VisibleForTesting
    void checkMicPermissionAndStartEnrollment() {
        if (this.viewModel.isAudioPermissionGranted()) {
            Log.i(TAG, "Audio permissions already granted, moving to training screen");
            this.viewModel.moveToTrainingScreen(this);
            return;
        }
        this.viewModel.requestAudioPermission(this, 1);
    }

    public void continueButtonClick(View view) {
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.INTRO_PAGE_CONTINUE_CLICK);
        if (isFeatureEnabledForUser("VOX_ENROLLMENT_AUDIO_RECORDINGS_FEATURE_CHECK_ANDROID")) {
            showLoading(null);
            this.compositeDisposable.add(this.viewModel.isUserOptInForVoicePrivacySettings().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.enrollment.ui.introduction.-$$Lambda$Uispd8ljI6MAJDpEugQCNKronRk
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    EnrollmentIntroductionViewFragment.this.onVoicePrivacySettingsCheckResult(((Boolean) obj).booleanValue());
                }
            }, new Consumer() { // from class: com.amazon.alexa.enrollment.ui.introduction.-$$Lambda$EnrollmentIntroductionViewFragment$ZdFktYVO5n0KOxbploo7LiEYChE
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    EnrollmentIntroductionViewFragment.this.lambda$continueButtonClick$2$EnrollmentIntroductionViewFragment((Throwable) obj);
                }
            }));
            return;
        }
        onVoicePrivacySettingsCheckResult(false);
    }

    @VisibleForTesting
    int getColorResource(int i) {
        return getResources().getColor(i);
    }

    @VisibleForTesting
    View getDecorViewForActivity() {
        return getActivity().getWindow().getDecorView();
    }

    @VisibleForTesting
    int getRetryCount() {
        return getActivity().getIntent().getIntExtra(ActivityConstants.RETRY_COUNT, 0);
    }

    @VisibleForTesting
    Intent getTermsActivityIntent() {
        String enrollmentContext = ActivityUtils.getEnrollmentContext(getActivity());
        Intent intent = new Intent(getActivity(), EnrollmentTermsActivity.class);
        intent.putExtra(ActivityConstants.ENROLLMENT_CONTEXT, enrollmentContext);
        return intent;
    }

    @VisibleForTesting
    void injectDependency() {
        Injector.inject(this);
    }

    public /* synthetic */ void lambda$checkForVoiceEnrollmentEligibility$1$EnrollmentIntroductionViewFragment(Throwable th) throws Throwable {
        onEligibilityCheckResult(false);
    }

    public /* synthetic */ void lambda$continueButtonClick$2$EnrollmentIntroductionViewFragment(Throwable th) throws Throwable {
        showGenericErrorPopup();
    }

    public /* synthetic */ void lambda$updateUI$0$EnrollmentIntroductionViewFragment(View view) {
        this.continueButtonClickListener.onClick(view);
    }

    void learnMoreClickActivity() {
        Intent termsActivityIntent = getTermsActivityIntent();
        Bundle bundle = new Bundle();
        bundle.putString("URL", "LEARN_MORE_URL");
        termsActivityIntent.putExtras(bundle);
        startActivityForIntent(termsActivityIntent);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        injectDependency();
        this.enrollmentMetricsRecorder.initializeMetricsContext(getEnrollmentContext());
        this.uiModel = this.viewModel.getUIModel();
        this.enrollmentThemeUtil.setTheme(getContext());
        this.rootView = layoutInflater.inflate(R.layout.activity_enrollment_introduction, viewGroup, false);
        setMosaicThemeColour();
        checkForVoiceEnrollmentEligibility();
        return this.rootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.continueButtonClickListener.cleanUp();
        this.termsClickSpanListener.cleanUp();
        this.learnMoreClickSpanListener.cleanUp();
        this.privacyClickSpanListener.cleanUp();
        this.compositeDisposable.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void onEligibilityCheckResult(boolean z) {
        hideLoading(null);
        if (z) {
            this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.OperationalMetrics.VOICE_ENROLL_ELIGIBILITY_SUCCESS);
            Log.i(TAG, "Showing voice enrollment as the user is eligible");
            this.rootView.findViewById(R.id.introduction_view).setVisibility(0);
            updateUI();
            return;
        }
        this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.OperationalMetrics.VOICE_ENROLL_ELIGIBILITY_FAILURE);
        Log.i(TAG, "Skipping voice enrollment as the user is not eligible");
        finishEnrollmentWithFailureStatus();
    }

    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment, com.amazon.alexa.enrollment.dialogs.AlertDialogFragment.OnDialogActionCallback
    public void onNegativeButtonTap(String str, int i) {
        String str2 = TAG;
        Log.i(str2, "Negative button tapped for dialog: " + str);
        if (DialogConstants.PERMISSIONS_FRAGMENT_TAG.equals(str)) {
            finishEnrollmentWithFailureStatus();
        } else if (!DialogConstants.SKIP_DIALOG_TAG.equalsIgnoreCase(str)) {
            if (i != 4) {
                return;
            }
            finishEnrollmentWithFailureStatus();
        } else if (i != 5) {
        } else {
            this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.INTRO_PAGE_SKIP_DIALOG_SKIP_CLICKED);
            int retryCount = getRetryCount();
            EnrollmentMetricsRecorder enrollmentMetricsRecorder = this.enrollmentMetricsRecorder;
            enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.INTRO_PAGE_SKIP_DIALOG_SKIP_CLICKED + retryCount);
            finishEnrollmentWithFailureStatus();
        }
    }

    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment, com.amazon.alexa.enrollment.dialogs.AlertDialogFragment.OnDialogActionCallback
    public void onPositiveButtonTap(String str, int i) {
        String str2 = TAG;
        Log.i(str2, "Positive button tapped for dialog: " + str);
        if (DialogConstants.PERMISSIONS_FRAGMENT_TAG.equals(str)) {
            this.permissionsHelper.openAppSettingsPermissions(this);
        } else if (DialogConstants.SKIP_DIALOG_TAG.equalsIgnoreCase(str)) {
            if (i != 5) {
                return;
            }
            this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.INTRO_PAGE_SKIP_DIALOG_BACK_CLICKED);
        } else if (i != 4) {
        } else {
            finishEnrollmentWithFailureStatus();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1) {
            if (iArr.length > 0 && iArr[0] == 0) {
                Log.i(TAG, "User granted audio permissions, moving to training screens");
                this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.AUDIO_PERMISSION_GRANTED);
                this.viewModel.moveToTrainingScreen(this);
                return;
            }
            Log.i(TAG, "User denied audio permissions, finishing activity");
            this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.AUDIO_PERMISSION_NOT_GRANTED);
            finishEnrollmentWithFailureStatus();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void onVoicePrivacySettingsCheckResult(boolean z) {
        String str = TAG;
        Log.i(str, "onVoicePrivacySettingsCheckResult " + z);
        hideLoading(null);
        if (z) {
            this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.VOICE_PRIVACY_SETTINGS_OPT_IN);
            this.viewModel.moveToPrivacyTermsScreen(this);
            return;
        }
        this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.VOICE_PRIVACY_SETTINGS_OPT_OUT);
        checkMicPermissionAndStartEnrollment();
    }

    @VisibleForTesting
    void showGenericErrorPopup() {
        this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.VOICE_PRIVACY_SETTINGS_FETCH_ERROR);
        hideLoading(null);
        this.dialogHelper.showErrorDialog(this, null);
    }

    @VisibleForTesting
    void startActivityForIntent(Intent intent) {
        getActivity().startActivity(intent);
    }

    void startPrivacyClickActivity() {
        Intent termsActivityIntent = getTermsActivityIntent();
        Bundle bundle = new Bundle();
        bundle.putString("URL", "PRIVACY_URL");
        termsActivityIntent.putExtras(bundle);
        startActivityForIntent(termsActivityIntent);
    }

    @VisibleForTesting
    void updateUI() {
        int i = 0;
        int i2 = 4;
        if (EnrollmentUtil.isOOBE(getEnrollmentContext())) {
            if (isFeatureEnabledForUser(ActivityConstants.VOX_ENROLLMENT_ANDROID_OOBE_SKIP_LOGIC)) {
                showSkipOptionInFooter();
                i = 4;
            } else {
                i2 = 0;
                i = 4;
            }
        }
        this.rootView.findViewById(R.id.skip).setVisibility(i2);
        this.rootView.findViewById(R.id.back).setVisibility(i);
        int colorResource = getColorResource(R.color.action10);
        this.rootView.findViewById(R.id.continue_btn).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.enrollment.ui.introduction.-$$Lambda$EnrollmentIntroductionViewFragment$-Syp6TWTpuaWuS8y-VMZSxbpb24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EnrollmentIntroductionViewFragment.this.lambda$updateUI$0$EnrollmentIntroductionViewFragment(view);
            }
        });
        TermsTextGenerator termsTextGenerator = new TermsTextGenerator(this, true);
        ((Button) this.rootView.findViewById(R.id.continue_btn)).setText(termsTextGenerator.getStartButtonText(this.identityService));
        TextView textView = (TextView) this.rootView.findViewById(R.id.intro_terms_text_view);
        textView.setText(termsTextGenerator.generateTerms(this.identityService, colorResource));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.enrollmentMetricsRecorder.recordUserViewInteraction(MetricsConstants.UserInteractionMetrics.INTRO_PAGE_VIEW);
        animateIntroPage();
    }

    public EnrollmentIntroductionViewFragment() {
    }
}
