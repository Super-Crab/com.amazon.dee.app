package com.amazon.alexa.handsfree.uservoicerecognition.ui.primer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.locale.UVRLocale;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.locale.UVRLocaleProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.ui.utils.AlertDialogBuilderFactory;
import com.amazon.alexa.handsfree.ui.utils.AnimationHelper;
import com.amazon.alexa.handsfree.ui.utils.FontAdapter;
import com.amazon.alexa.handsfree.ui.views.ActivityIndicator;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class EnrollmentPrimerFragment extends Fragment implements EnrollmentPrimerContract.View {
    @VisibleForTesting
    static final String TAG = EnrollmentPrimerFragment.class.getSimpleName();
    private View.OnClickListener continueButtonOnClickListener;
    private DebounceOnClickListener learnMoreClickSpanListener;
    private ActivityIndicator mActivityIndicator;
    private AlertDialogBuilderFactory mAlertDialogBuilderFactory;
    private final AnimationHelper mAnimationHelper;
    @Nullable
    private EnrollmentPrimerFragmentCallback mFragmentCallback;
    private View mGettingReadyContainer;
    private View mMainContainer;
    private EnrollmentPrimerPresenter mPresenter;
    private UVRLocaleProvider mUVRLocaleProvider;
    private DebounceOnClickListener privacyClickSpanListener;
    private View.OnClickListener skipButtonOnClickListener;
    private DebounceOnClickListener termsClickSpanListener;

    /* loaded from: classes8.dex */
    public interface EnrollmentPrimerFragmentCallback {
        void loadStepsFragmentForEnrollmentPrimer();

        void loadVoicePrivacyScreenForEnrollmentPrimer();

        void skipEnrollmentForEnrollmentPrimer();
    }

    public EnrollmentPrimerFragment() {
        this.continueButtonOnClickListener = new View.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view) {
                EnrollmentPrimerFragment.this.mPresenter.continueButtonOnClick(view.getContext());
            }
        };
        this.skipButtonOnClickListener = new View.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view) {
                EnrollmentPrimerFragment.this.mPresenter.skipButtonOnClick(view.getContext());
            }
        };
        this.termsClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.5
            @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener
            public void debounceClick(View view) {
                Log.i(EnrollmentPrimerFragment.TAG, "User clicked terms page");
                Intent termsActivityIntent = EnrollmentPrimerFragment.this.getTermsActivityIntent();
                termsActivityIntent.putExtra("URL", "TERMS_URL");
                EnrollmentPrimerFragment.this.getActivity().startActivity(termsActivityIntent);
            }
        };
        this.learnMoreClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.6
            @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener
            public void debounceClick(View view) {
                Log.i(EnrollmentPrimerFragment.TAG, "User clicked learn more page");
                Intent termsActivityIntent = EnrollmentPrimerFragment.this.getTermsActivityIntent();
                termsActivityIntent.putExtra("URL", "LEARN_MORE_URL");
                EnrollmentPrimerFragment.this.getActivity().startActivity(termsActivityIntent);
            }
        };
        this.privacyClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.7
            @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener
            public void debounceClick(View view) {
                Log.i(EnrollmentPrimerFragment.TAG, "User clicked privacy page");
                Intent termsActivityIntent = EnrollmentPrimerFragment.this.getTermsActivityIntent();
                termsActivityIntent.putExtra("URL", "PRIVACY_URL");
                EnrollmentPrimerFragment.this.getActivity().startActivity(termsActivityIntent);
            }
        };
        this.mAnimationHelper = new AnimationHelper();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Intent getTermsActivityIntent() {
        Intent intent = new Intent(getActivity(), EnrollmentTermsActivity.class);
        intent.addFlags(268435456);
        return intent;
    }

    private void runOnUiThread(@NonNull Runnable runnable) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(runnable);
        }
    }

    private void setSpannableString(@NonNull String str, @NonNull String str2, @NonNull SpannableString spannableString, @NonNull ClickableSpan clickableSpan) {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.action10));
        int indexOf = str.indexOf(str2);
        int length = str2.length() + indexOf;
        if (indexOf <= 0 || indexOf >= length) {
            return;
        }
        String str3 = TAG;
        Log.d(str3, "Start span index: " + indexOf + " End span index: " + length);
        spannableString.setSpan(clickableSpan, indexOf, length, 33);
        spannableString.setSpan(foregroundColorSpan, indexOf, length, 33);
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentContract.View
    public void continueEnrollment() {
        EnrollmentPrimerFragmentCallback enrollmentPrimerFragmentCallback;
        Log.d(TAG, "continueEnrollment");
        if (!isAdded() || (enrollmentPrimerFragmentCallback = this.mFragmentCallback) == null) {
            return;
        }
        enrollmentPrimerFragmentCallback.loadStepsFragmentForEnrollmentPrimer();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerContract.View
    public void fetchEnrollmentStatus() {
        Log.i(TAG, "fetchEnrollmentStatus");
        this.mPresenter.isSpeakerIDEnrolled(new ResultCallback<Boolean>() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.3
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                String str = EnrollmentPrimerFragment.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error when checking isSpeakerIDEnrolled: ");
                outline107.append(callbackErrorMetadata.getErrorMessage());
                Log.e(str, outline107.toString());
                EnrollmentPrimerFragment.this.hideLoading();
                EnrollmentPrimerFragment.this.mPresenter.getEnrollmentPrimerMetricsRecorder().logIsSpeakerIDEnrolledErrorMetric();
                EnrollmentPrimerFragment.this.showInternetAlertDialogWithRetry();
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onResult(Boolean bool) {
                EnrollmentPrimerFragment.this.mPresenter.setIsEnrollmentCheckSuccessful(true);
                SharedPreferences sharedPreferences = EnrollmentPrimerFragment.this.getActivity().getSharedPreferences(EnrollmentTypeResolver.PREFERENCE_FILE_NAME, 0);
                if (bool.booleanValue()) {
                    Log.i(EnrollmentPrimerFragment.TAG, "Voice Profile created, showing information dialog");
                    sharedPreferences.edit().putBoolean(EnrollmentTypeResolver.IS_SPEAKER_ID_ALREADY_CREATED, true).apply();
                    EnrollmentPrimerFragment.this.showInformationDialog();
                    EnrollmentPrimerFragment.this.hideLoading();
                } else {
                    Log.i(EnrollmentPrimerFragment.TAG, "Voice Profile not already created, won't show the information dialog");
                    sharedPreferences.edit().putBoolean(EnrollmentTypeResolver.IS_SPEAKER_ID_ALREADY_CREATED, false).apply();
                    if (EnrollmentPrimerFragment.this.mPresenter.getIsContinueButtonPressed()) {
                        Log.i(EnrollmentPrimerFragment.TAG, "isSpeakerIDEnrolled completed; running continueButtonOnClick");
                        EnrollmentPrimerFragment.this.mPresenter.continueButtonOnClick(EnrollmentPrimerFragment.this.getContext());
                    }
                }
                EnrollmentPrimerFragment.this.mPresenter.getEnrollmentPrimerMetricsRecorder().logIsSpeakerIDEnrolledSuccessMetric();
            }
        });
    }

    @VisibleForTesting
    Button getButton(@NonNull View view, int i) {
        return (Button) view.findViewById(i);
    }

    @VisibleForTesting
    ClickableSpan getClickableSpan(@NonNull final DebounceOnClickListener debounceOnClickListener) {
        final int color = getResources().getColor(R.color.action10);
        return new ClickableSpan() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.4
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View view) {
                debounceOnClickListener.onClick(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(color);
                textPaint.setUnderlineText(false);
            }
        };
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerContract.View
    public void hideLoading() {
        runOnUiThread(new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.9
            @Override // java.lang.Runnable
            public void run() {
                EnrollmentPrimerFragment.this.mAnimationHelper.applyAnimatorResource(R.animator.fade_in_early, EnrollmentPrimerFragment.this.mMainContainer);
                EnrollmentPrimerFragment.this.mAnimationHelper.applyAnimatorResource(R.animator.exit_fade_out, EnrollmentPrimerFragment.this.mGettingReadyContainer);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EnrollmentPrimerFragmentCallback) {
            this.mFragmentCallback = (EnrollmentPrimerFragmentCallback) context;
        } else {
            Log.e(TAG, "Steps fragment callback is null, activity must implement EnrollmentPrimerFragmentCallbackfor steps to load");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mPresenter = new EnrollmentPrimerPresenter(this, getContext());
        this.mAlertDialogBuilderFactory = new AlertDialogBuilderFactory(getContext(), R.style.AlertDialogBackground);
        this.mUVRLocaleProvider = UVRLocaleProvider.getInstance();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Log.d(TAG, "inside onCreateView");
        return LayoutInflater.from(new ContextThemeWrapper(getContext(), R.style.Theme_Mosaic_Jasper)).inflate(R.layout.mosaic_enrollment_primer_fragment, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mFragmentCallback = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        updateUI(view);
    }

    @VisibleForTesting
    void setEnrollmentPrimerFragmentCallback(@NonNull EnrollmentPrimerFragmentCallback enrollmentPrimerFragmentCallback) {
        this.mFragmentCallback = enrollmentPrimerFragmentCallback;
    }

    @VisibleForTesting
    void setSpannableTextToTermsTextView(@NonNull TextView textView) {
        String string = getString(R.string.primer_your_privacy_click_span);
        String string2 = getString(R.string.primer_terms_click_span);
        String string3 = getString(R.string.primer_learn_more_click_span);
        ClickableSpan clickableSpan = getClickableSpan(this.privacyClickSpanListener);
        ClickableSpan clickableSpan2 = getClickableSpan(this.termsClickSpanListener);
        ClickableSpan clickableSpan3 = getClickableSpan(this.learnMoreClickSpanListener);
        String string4 = getString(R.string.primer_intro_terms);
        String string5 = getString(R.string.primer_intro_terms_eu);
        String format = String.format(string4, string2, string3);
        String format2 = String.format(string5, string, string2, string3);
        SpannableString spannableString = new SpannableString(format);
        SpannableString spannableString2 = new SpannableString(format2);
        setSpannableString(format, string2, spannableString, clickableSpan2);
        setSpannableString(format, string3, spannableString, clickableSpan3);
        setSpannableString(format2, string, spannableString2, clickableSpan);
        setSpannableString(format2, string2, spannableString2, clickableSpan2);
        setSpannableString(format2, string3, spannableString2, clickableSpan3);
        if (this.mUVRLocaleProvider.getUVRLocale().isEuPFM()) {
            textView.setText(spannableString2);
        } else {
            textView.setText(spannableString);
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @VisibleForTesting
    void showInformationDialog() {
        Log.i(TAG, "Showing Information AlertDialog");
        this.mPresenter.getEnrollmentPrimerMetricsRecorder().logAlreadyEnrolledDialogShownMetric();
        this.mAlertDialogBuilderFactory.create().setTitle(R.string.enrollment_primer_dialog_title).setMessage(R.string.enrollment_primer_dialog_description).setPositiveButton(R.string.enrollment_primer_dialog_button, (DialogInterface.OnClickListener) null).show();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerContract.View
    public void showInternetAlertDialogWithRetry() {
        Log.e(TAG, "Showing Internet AlertDialog");
        this.mPresenter.showInternetAlertDialogWithRetry(getContext());
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerContract.View
    public void showLoading() {
        runOnUiThread(new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.8
            @Override // java.lang.Runnable
            public void run() {
                EnrollmentPrimerFragment.this.mAnimationHelper.applyAnimatorResource(R.animator.fade_in_early, EnrollmentPrimerFragment.this.mGettingReadyContainer);
                EnrollmentPrimerFragment.this.mAnimationHelper.applyAnimatorResource(R.animator.exit_fade_out, EnrollmentPrimerFragment.this.mMainContainer);
                EnrollmentPrimerFragment.this.mActivityIndicator.startAnimation();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentContract.View
    public void showStartEnrollmentError() {
        Log.e(TAG, "Showing error dialog");
        this.mPresenter.getEnrollmentPrimerMetricsRecorder().logStartEnrollmentErrorDialogShown();
        this.mAlertDialogBuilderFactory.create().setTitle(R.string.handle_non_retry_title).setMessage(R.string.handle_non_retry_msg).setPositiveButton(R.string.enrollment_primer_dialog_button, (DialogInterface.OnClickListener) null).show();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerContract.View, com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentContract.View
    public void showStartEnrollmentVoicePrivacyOptedIn() {
        Log.i(TAG, "showStartEnrollmentVoicePrivacyOptedIn");
        this.mFragmentCallback.loadVoicePrivacyScreenForEnrollmentPrimer();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerContract.View
    public void skipEnrollment() {
        EnrollmentPrimerFragmentCallback enrollmentPrimerFragmentCallback;
        Log.i(TAG, "skipEnrollment");
        if (!isAdded() || (enrollmentPrimerFragmentCallback = this.mFragmentCallback) == null) {
            return;
        }
        enrollmentPrimerFragmentCallback.skipEnrollmentForEnrollmentPrimer();
    }

    @VisibleForTesting
    void updateUI(@NonNull View view) {
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Bold, view.findViewById(R.id.enrollment_primer_title), view.findViewById(R.id.enrollment_primer_later_btn), view.findViewById(R.id.enrollment_primer_continue_btn));
        view.findViewById(R.id.enrollment_primer_later_btn).setOnClickListener(this.skipButtonOnClickListener);
        view.findViewById(R.id.enrollment_primer_continue_btn).setOnClickListener(this.continueButtonOnClickListener);
        this.mActivityIndicator = (ActivityIndicator) view.findViewById(R.id.getting_ready_activity_indicator);
        this.mGettingReadyContainer = view.findViewById(R.id.getting_ready_container);
        this.mMainContainer = view.findViewById(R.id.main_container);
        UVRLocale uVRLocale = this.mUVRLocaleProvider.getUVRLocale();
        Button button = getButton(view, R.id.enrollment_primer_continue_btn);
        if (uVRLocale.isEuPFM()) {
            button.setText(R.string.primer_consent);
        }
        if (!this.mPresenter.is1PSVDecoupledEnrollment()) {
            fetchEnrollmentStatus();
        } else {
            hideLoading();
        }
        this.mPresenter.checkUserOptedInForVoicePrivacySettings();
        setSpannableTextToTermsTextView((TextView) view.findViewById(R.id.enrollment_primer_intro_terms_text_view));
    }

    @SuppressLint({"ValidFragment"})
    @VisibleForTesting
    EnrollmentPrimerFragment(@NonNull EnrollmentPrimerPresenter enrollmentPrimerPresenter, @NonNull AlertDialogBuilderFactory alertDialogBuilderFactory, @NonNull UVRLocaleProvider uVRLocaleProvider, @NonNull AnimationHelper animationHelper) {
        this.continueButtonOnClickListener = new View.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view) {
                EnrollmentPrimerFragment.this.mPresenter.continueButtonOnClick(view.getContext());
            }
        };
        this.skipButtonOnClickListener = new View.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view) {
                EnrollmentPrimerFragment.this.mPresenter.skipButtonOnClick(view.getContext());
            }
        };
        this.termsClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.5
            @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener
            public void debounceClick(View view) {
                Log.i(EnrollmentPrimerFragment.TAG, "User clicked terms page");
                Intent termsActivityIntent = EnrollmentPrimerFragment.this.getTermsActivityIntent();
                termsActivityIntent.putExtra("URL", "TERMS_URL");
                EnrollmentPrimerFragment.this.getActivity().startActivity(termsActivityIntent);
            }
        };
        this.learnMoreClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.6
            @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener
            public void debounceClick(View view) {
                Log.i(EnrollmentPrimerFragment.TAG, "User clicked learn more page");
                Intent termsActivityIntent = EnrollmentPrimerFragment.this.getTermsActivityIntent();
                termsActivityIntent.putExtra("URL", "LEARN_MORE_URL");
                EnrollmentPrimerFragment.this.getActivity().startActivity(termsActivityIntent);
            }
        };
        this.privacyClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment.7
            @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener
            public void debounceClick(View view) {
                Log.i(EnrollmentPrimerFragment.TAG, "User clicked privacy page");
                Intent termsActivityIntent = EnrollmentPrimerFragment.this.getTermsActivityIntent();
                termsActivityIntent.putExtra("URL", "PRIVACY_URL");
                EnrollmentPrimerFragment.this.getActivity().startActivity(termsActivityIntent);
            }
        };
        this.mPresenter = enrollmentPrimerPresenter;
        this.mAlertDialogBuilderFactory = alertDialogBuilderFactory;
        this.mUVRLocaleProvider = uVRLocaleProvider;
        this.mAnimationHelper = animationHelper;
    }
}
