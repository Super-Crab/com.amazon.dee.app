package com.amazon.alexa.enrollment.ui.kidsenrollment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentManager;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.module.library.Injector;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment;
import com.amazon.alexa.enrollment.ui.DebounceOnClickListener;
import com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionViewModel;
import com.amazon.alexa.enrollment.ui.terms.EnrollmentTermsActivity;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingDialogHelper;
import com.amazon.alexa.enrollment.utils.ActivityConstants;
import com.amazon.alexa.enrollment.utils.ActivityUtils;
import com.amazon.alexa.enrollment.utils.AnimationHelper;
import com.amazon.alexa.enrollment.utils.EnrollmentThemeUtil;
import com.amazon.alexa.enrollment.utils.TermsTextGenerator;
import com.amazon.alexa.identity.api.IdentityService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class KidsEnrollmentPrimerViewFragment extends AbstractEnrollmentViewFragment {
    private static final int ALERT_POPUP_BUTTONS = 2;
    private static final long DELAY_MILLIS = 3000;
    private static final String TAG = GeneratedOutlineSupport1.outline39(KidsEnrollmentPrimerViewFragment.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private static final float TOP_Y_AXIS = 0.0f;
    @Inject
    AnimationHelper animationHelper;
    @Inject
    Context context;
    @Inject
    KidsEnrollmentDialogHelper dialogHelper;
    @Inject
    EnrollmentTrainingDialogHelper enrollmentDialogHelper;
    @Inject
    EnrollmentMetricsRecorder enrollmentMetricsRecorder;
    @Inject
    EnrollmentThemeUtil enrollmentThemeUtil;
    @Inject
    EnrollmentIntroductionViewModel enrollmentViewModel;
    private Bundle extras;
    @Inject
    IdentityService identityService;
    @Inject
    KidsEnrollmentPopup popupFragment;
    private View rootView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private DebounceOnClickListener continueButtonOnclickListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPrimerViewFragment.1
        @Override // com.amazon.alexa.enrollment.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            KidsEnrollmentPrimerViewFragment.this.continueButtonOnClick(view);
        }
    };
    private DebounceOnClickListener closeButtonOnClickListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPrimerViewFragment.2
        @Override // com.amazon.alexa.enrollment.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            KidsEnrollmentPrimerViewFragment.this.onCloseButtonPressed();
        }
    };
    private DebounceOnClickListener termsClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPrimerViewFragment.3
        @Override // com.amazon.alexa.enrollment.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            Log.i(KidsEnrollmentPrimerViewFragment.TAG, "user clicked Alexa Terms of Use link");
            KidsEnrollmentPrimerViewFragment.this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.KIDS_PRIMER_PRIVACY_CLICK);
            Intent termsActivityIntent = KidsEnrollmentPrimerViewFragment.this.getTermsActivityIntent(view);
            termsActivityIntent.putExtra("URL", "TERMS_URL");
            KidsEnrollmentPrimerViewFragment.this.startActivity(termsActivityIntent);
        }
    };
    private DebounceOnClickListener learnMoreClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPrimerViewFragment.4
        @Override // com.amazon.alexa.enrollment.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            Log.i(KidsEnrollmentPrimerViewFragment.TAG, "user clicked Learn More link");
            KidsEnrollmentPrimerViewFragment.this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.KIDS_PRIMER_LEARN_MORE_CLICK);
            Intent termsActivityIntent = KidsEnrollmentPrimerViewFragment.this.getTermsActivityIntent(view);
            termsActivityIntent.putExtra("URL", "LEARN_MORE_URL");
            KidsEnrollmentPrimerViewFragment.this.startActivity(termsActivityIntent);
        }
    };

    public KidsEnrollmentPrimerViewFragment() {
    }

    private void animateIntroPage() {
        this.animationHelper.renderVerticalFadeInAnimation(getContext(), this.rootView.findViewById(R.id.kids_enrollment_primer_description));
    }

    public static KidsEnrollmentPrimerViewFragment createInstance() {
        return new KidsEnrollmentPrimerViewFragment();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Intent getTermsActivityIntent(View view) {
        String enrollmentContext = ActivityUtils.getEnrollmentContext(getActivity());
        Intent intent = new Intent(view.getContext(), EnrollmentTermsActivity.class);
        intent.putExtra(ActivityConstants.ENROLLMENT_CONTEXT, enrollmentContext);
        return intent;
    }

    private void setMosaicThemeColor() {
        this.enrollmentThemeUtil.setBackgroundColorToView(getContext(), R.attr.mosaicBackground, this.rootView);
        this.enrollmentThemeUtil.setBackgroundColorToView(getContext(), R.attr.mosaicAction40, this.rootView.findViewById(R.id.toast_message));
        this.enrollmentThemeUtil.setTextColor(getContext(), R.attr.mosaicNeutral10, (TextView) this.rootView.findViewById(R.id.kids_enrollment_primer_title));
        this.enrollmentThemeUtil.setTextColor(getContext(), R.attr.mosaicNeutral10, (TextView) this.rootView.findViewById(R.id.nav_title));
        this.enrollmentThemeUtil.setTextColor(getContext(), R.attr.mosaicNeutral20, (TextView) this.rootView.findViewById(R.id.kids_enrollment_primer_description), (TextView) this.rootView.findViewById(R.id.kids_enrollment_primer_intro_terms_text_view));
        this.enrollmentThemeUtil.setTintColorToImageView((ImageView) this.rootView.findViewById(R.id.kids_enrollment_close_button), getContext(), R.attr.mosaicNeutral10);
        this.enrollmentThemeUtil.setBackgroundToView(getContext(), R.drawable.primary_button_mosaic_background, this.rootView.findViewById(R.id.kids_enrollment_primer_continue_btn));
    }

    private void updateUI() {
        TermsTextGenerator termsTextGenerator = new TermsTextGenerator(this, false);
        ((TextView) this.rootView.findViewById(R.id.kids_enrollment_primer_title)).setText(getTitle());
        ((Button) this.rootView.findViewById(R.id.kids_enrollment_primer_continue_btn)).setText(termsTextGenerator.getStartButtonText(this.identityService));
        this.rootView.findViewById(R.id.kids_enrollment_primer_continue_btn).setOnClickListener(this.continueButtonOnclickListener);
        this.rootView.findViewById(R.id.kids_enrollment_close_button).setOnClickListener(this.closeButtonOnClickListener);
        final int color = getColor(R.color.accent);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color);
        String stringResource = getStringResource(R.string.kids_intro_learn_more);
        String stringResource2 = getStringResource(R.string.kids_description);
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPrimerViewFragment.6
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View view) {
                KidsEnrollmentPrimerViewFragment.this.learnMoreClickSpanListener.onClick(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(color);
                textPaint.setUnderlineText(false);
            }
        };
        String format = String.format(stringResource2, stringResource);
        TextView textView = (TextView) this.rootView.findViewById(R.id.kids_enrollment_primer_description);
        int indexOf = format.indexOf(stringResource);
        int length = stringResource.length() + indexOf;
        SpannableString spannableString = new SpannableString(format);
        spannableString.setSpan(clickableSpan, indexOf, length, 33);
        spannableString.setSpan(foregroundColorSpan, indexOf, length, 33);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textView2 = (TextView) this.rootView.findViewById(R.id.kids_enrollment_primer_intro_terms_text_view);
        textView2.setText(termsTextGenerator.generateTerms(this.identityService, color));
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        animateIntroPage();
    }

    @VisibleForTesting
    void continueButtonOnClick(View view) {
        Log.i(TAG, "inside primerContinueButtonOnClick");
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.KIDS_PRIMER_CONTINUE_CLICK);
        if (isFeatureEnabledForUser("KIDS_ENROLLMENT_AUDIO_RECORDINGS_FEATURE_CHECK_ANDROID")) {
            showLoading(null);
            this.compositeDisposable.add(this.enrollmentViewModel.isUserOptInForVoicePrivacySettings().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.enrollment.ui.kidsenrollment.-$$Lambda$wAXYTJVfyzJZ81DLWfjVDi_A0rY
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    KidsEnrollmentPrimerViewFragment.this.onVoicePrivacyVoiceSettingsResult(((Boolean) obj).booleanValue());
                }
            }, new Consumer() { // from class: com.amazon.alexa.enrollment.ui.kidsenrollment.-$$Lambda$KidsEnrollmentPrimerViewFragment$CNVjH_mTDYdvmmGq4xuB0Zkhv9E
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    KidsEnrollmentPrimerViewFragment.this.lambda$continueButtonOnClick$0$KidsEnrollmentPrimerViewFragment((Throwable) obj);
                }
            }));
            return;
        }
        onVoicePrivacyVoiceSettingsResult(false);
    }

    @VisibleForTesting
    int getColor(int i) {
        if (isAdded()) {
            return getResources().getColor(i);
        }
        return -1;
    }

    @VisibleForTesting
    FragmentManager getPrimerFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    @VisibleForTesting
    String getStringResource(int i) {
        return getString(i);
    }

    String getTitle() {
        return String.format(getStringResource(R.string.fe_teach_alexa_your_kids_voice), this.extras.getString(ActivityConstants.CHILD_NAME));
    }

    @VisibleForTesting
    void hideToastAndShowBackAndSkip(FrameLayout frameLayout, RelativeLayout relativeLayout) {
        Log.i(TAG, "inside hideToastAndShowBackAndSkip, toast message will gets hidden and show back and skip");
        relativeLayout.setVisibility(0);
        frameLayout.animate().translationY(0.0f);
        frameLayout.setVisibility(4);
    }

    @VisibleForTesting
    void injectDependency() {
        Injector.inject(this);
    }

    public /* synthetic */ void lambda$continueButtonOnClick$0$KidsEnrollmentPrimerViewFragment(Throwable th) throws Throwable {
        showGenericErrorPopup();
    }

    @VisibleForTesting
    void moveToPopupScreen() {
        Log.i(TAG, "inside moveToPopupScreen");
        this.popupFragment.setArguments(this.extras);
        this.popupFragment.show(getPrimerFragmentManager(), TAG);
    }

    @VisibleForTesting
    void onCloseButtonPressed() {
        Log.i(TAG, "inside onCloseButtonPressed");
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.KIDS_PRIMER_CLOSE_CLICK);
        this.dialogHelper.showAlertPopup(this, 2);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        injectDependency();
        this.enrollmentThemeUtil.setTheme(getContext());
        this.rootView = layoutInflater.inflate(R.layout.activity_kids_enrollment_primer, viewGroup, false);
        setMosaicThemeColor();
        this.extras = getArguments();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Not received extras: ");
        outline107.append(this.extras.isEmpty());
        Log.i(str, outline107.toString());
        updateUI();
        boolean z = this.extras.getBoolean(ActivityConstants.CONSENT_COLLECTED_TOAST);
        GeneratedOutlineSupport1.outline173("Show parental consent toast: ", z, TAG);
        if (z) {
            showToastAndHideBackAndSkip();
        }
        return this.rootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "inside onDestroy");
        this.learnMoreClickSpanListener.cleanUp();
        this.termsClickSpanListener.cleanUp();
        this.continueButtonOnclickListener.cleanUp();
        this.compositeDisposable.clear();
    }

    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment, com.amazon.alexa.enrollment.dialogs.AlertDialogFragment.OnDialogActionCallback
    public void onNegativeButtonTap(String str, int i) {
        String str2 = TAG;
        Log.i(str2, "inside onNegativeButtonTap with tag: " + str + " and requestCode: " + i);
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.KIDS_SKIP_ALERT_POPUP_NO_CLICK);
    }

    @Override // com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment, com.amazon.alexa.enrollment.dialogs.AlertDialogFragment.OnDialogActionCallback
    public void onPositiveButtonTap(String str, int i) {
        String str2 = TAG;
        Log.i(str2, "inside onPositiveButtonTap with tag: " + str + " and requestCode: " + i);
        this.enrollmentMetricsRecorder.recordUserClickInteraction(MetricsConstants.UserInteractionMetrics.KIDS_SKIP_ALERT_POPUP_YES_CLICK);
        Log.i(TAG, "finish enrollment with failure status, as user clicked YES in alert popup");
        finishEnrollmentWithFailureStatus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void onVoicePrivacyVoiceSettingsResult(boolean z) {
        String str = TAG;
        Log.i(str, "inside onVoicePrivacySettingsResult: " + z);
        hideLoading(null);
        if (z) {
            this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.VOICE_PRIVACY_SETTINGS_OPT_IN);
            this.enrollmentViewModel.moveToPrivacyTermsScreen(this);
            return;
        }
        this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.VOICE_PRIVACY_SETTINGS_OPT_OUT);
        moveToPopupScreen();
    }

    @VisibleForTesting
    void showGenericErrorPopup() {
        this.enrollmentMetricsRecorder.recordCounter(MetricsConstants.UserInteractionMetrics.VOICE_PRIVACY_SETTINGS_FETCH_ERROR);
        hideLoading(null);
        this.enrollmentDialogHelper.showErrorDialog(this, null);
    }

    @VisibleForTesting
    void showToastAndHideBackAndSkip() {
        Log.i(TAG, "inside showToastAndHideBackAndSkip");
        final FrameLayout frameLayout = (FrameLayout) this.rootView.findViewById(R.id.toast_message);
        final RelativeLayout relativeLayout = (RelativeLayout) this.rootView.findViewById(R.id.backAndSkipButton);
        Log.i(TAG, "obtained both layouts, hide the backAndSkip and show the toastMessage layout");
        frameLayout.setVisibility(0);
        relativeLayout.setVisibility(4);
        new Handler().postDelayed(new Runnable() { // from class: com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentPrimerViewFragment.5
            @Override // java.lang.Runnable
            public void run() {
                Log.i(KidsEnrollmentPrimerViewFragment.TAG, "inside delay creation for hiding the toast message");
                KidsEnrollmentPrimerViewFragment.this.hideToastAndShowBackAndSkip(frameLayout, relativeLayout);
            }
        }, 3000L);
    }

    public KidsEnrollmentPrimerViewFragment(KidsEnrollmentPopup kidsEnrollmentPopup, AnimationHelper animationHelper, KidsEnrollmentDialogHelper kidsEnrollmentDialogHelper, EnrollmentThemeUtil enrollmentThemeUtil, EnrollmentTrainingDialogHelper enrollmentTrainingDialogHelper, EnrollmentIntroductionViewModel enrollmentIntroductionViewModel, EnrollmentMetricsRecorder enrollmentMetricsRecorder, IdentityService identityService, Context context, Bundle bundle, View view) {
        this.popupFragment = kidsEnrollmentPopup;
        this.animationHelper = animationHelper;
        this.dialogHelper = kidsEnrollmentDialogHelper;
        this.enrollmentThemeUtil = enrollmentThemeUtil;
        this.enrollmentDialogHelper = enrollmentTrainingDialogHelper;
        this.enrollmentViewModel = enrollmentIntroductionViewModel;
        this.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
        this.identityService = identityService;
        this.context = context;
        this.extras = bundle;
        this.rootView = view;
    }
}
