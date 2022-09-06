package com.amazon.alexa.handsfree.uservoicerecognition.ui.start;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.locale.UVRLocaleProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.ui.utils.AlertDialogBuilderFactory;
import com.amazon.alexa.handsfree.ui.utils.FontAdapter;
import com.amazon.alexa.handsfree.ui.utils.HandsFreeLogoBarProvider;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerFragment;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentTermsActivity;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentContract;
import com.amazon.alexa.mosaic.components.ThemeUtil;
/* loaded from: classes8.dex */
public class StartEnrollmentFragment extends Fragment implements StartEnrollmentContract.View {
    @VisibleForTesting
    static final String TAG = EnrollmentPrimerFragment.class.getSimpleName();
    private AlertDialogBuilderFactory mAlertDialogBuilderFactory;
    private StartEnrollmentFragmentCallback mFragmentCallback;
    private StartEnrollmentPresenter mPresenter;
    private StartEnrollmentMetricsRecorder mStartEnrollmentMetricsRecorder;
    private UVRLocaleProvider mUVRLocaleProvider;
    private DebounceOnClickListener termsClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment.8
        @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            Log.i(StartEnrollmentFragment.TAG, "User clicked terms page");
            Intent termsActivityIntent = StartEnrollmentFragment.this.getTermsActivityIntent();
            termsActivityIntent.putExtra("URL", "TERMS_URL");
            StartEnrollmentFragment.this.getActivity().startActivity(termsActivityIntent);
        }
    };
    private DebounceOnClickListener learnMoreClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment.9
        @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            Log.i(StartEnrollmentFragment.TAG, "User clicked learn more page");
            Intent termsActivityIntent = StartEnrollmentFragment.this.getTermsActivityIntent();
            termsActivityIntent.putExtra("URL", "LEARN_MORE_URL");
            StartEnrollmentFragment.this.getActivity().startActivity(termsActivityIntent);
        }
    };
    private DebounceOnClickListener privacyClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment.10
        @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            Log.i(StartEnrollmentFragment.TAG, "User clicked privacy page");
            Intent termsActivityIntent = StartEnrollmentFragment.this.getTermsActivityIntent();
            termsActivityIntent.putExtra("URL", "PRIVACY_URL");
            StartEnrollmentFragment.this.getActivity().startActivity(termsActivityIntent);
        }
    };

    /* loaded from: classes8.dex */
    public interface StartEnrollmentFragmentCallback {
        void loadStepsFragment();

        void loadVoicePrivacyScreenForEnrollmentStart();

        void skipEnrollment();
    }

    public StartEnrollmentFragment() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Intent getTermsActivityIntent() {
        Intent intent = new Intent(getActivity(), EnrollmentTermsActivity.class);
        intent.addFlags(268435456);
        return intent;
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

    /* JADX INFO: Access modifiers changed from: private */
    public void showContinueConfirmationDialog() {
        this.mAlertDialogBuilderFactory.create().setTitle(R.string.uvr_start_confirmation_dialog_title).setMessage(R.string.uvr_start_confirmation_dialog_message).setPositiveButton(R.string.uvr_start_confirmation_dialog_continue, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                StartEnrollmentFragment.this.mStartEnrollmentMetricsRecorder.logConfirmationDialogContinueClickMetric();
                StartEnrollmentFragment.this.mPresenter.continueEnrollment();
            }
        }).setNegativeButton(R.string.uvr_start_confirmation_dialog_back, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                StartEnrollmentFragment.this.mStartEnrollmentMetricsRecorder.logConfirmationDialogBackClickMetric();
            }
        }).setCancelable(false).show();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentContract.View
    public void continueEnrollment() {
        if (isAdded()) {
            this.mFragmentCallback.loadStepsFragment();
        }
    }

    @VisibleForTesting
    ClickableSpan getClickableSpan(@NonNull final DebounceOnClickListener debounceOnClickListener) {
        final int color = getResources().getColor(R.color.action10);
        return new ClickableSpan() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment.7
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

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof StartEnrollmentFragmentCallback) {
            this.mFragmentCallback = (StartEnrollmentFragmentCallback) context;
            return;
        }
        throw new ClassCastException(context.toString() + " must implement StartEnrollmentFragmentCallback");
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mStartEnrollmentMetricsRecorder = new StartEnrollmentMetricsRecorder(getContext());
        this.mPresenter = new StartEnrollmentPresenter(this, getContext(), this.mStartEnrollmentMetricsRecorder);
        this.mAlertDialogBuilderFactory = new AlertDialogBuilderFactory(getContext(), R.style.AlertDialogBackground);
        this.mUVRLocaleProvider = UVRLocaleProvider.getInstance();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        ThemeUtil.setTheme(getActivity().getApplicationContext());
        return LayoutInflater.from(new ContextThemeWrapper(getContext(), R.style.Theme_Mosaic_Jasper)).inflate(R.layout.mosaic_fragment_start_enrollment, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mFragmentCallback = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        Object voiceApplicationName;
        super.onViewCreated(view, bundle);
        HandsFreeLogoBarProvider handsFreeLogoBarProvider = new HandsFreeLogoBarProvider(getContext());
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.handsfree_logo_container);
        String voiceAppPackageName = this.mPresenter.is1PSVDecoupledEnrollment() ? null : this.mPresenter.getVoiceAppPackageName();
        View handsFreeLogoBarMosaic = handsFreeLogoBarProvider.getHandsFreeLogoBarMosaic(voiceAppPackageName);
        ((TextView) handsFreeLogoBarMosaic.findViewById(R.id.logo_title_text)).setTextColor(ThemeUtil.getColorFromAttribute(getActivity().getApplicationContext(), R.attr.mosaicNeutral10));
        viewGroup.addView(handsFreeLogoBarMosaic);
        TextView textView = (TextView) view.findViewById(R.id.start_uvr_description_text);
        if (this.mPresenter.is1PSVDecoupledEnrollment()) {
            voiceApplicationName = getString(R.string.decoupled_device_text);
        } else {
            voiceApplicationName = this.mPresenter.getVoiceApplicationName(getContext());
        }
        textView.setText(getString(R.string.uvr_start_description, voiceApplicationName));
        TextView textView2 = (TextView) view.findViewById(R.id.uvr_start_disclaimer_text);
        setupDisclaimer(voiceAppPackageName, textView2);
        View findViewById = view.findViewById(R.id.later_button);
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                StartEnrollmentFragment.this.mStartEnrollmentMetricsRecorder.logLaterButtonClickMetric();
                StartEnrollmentFragment.this.mFragmentCallback.skipEnrollment();
            }
        });
        Button button = (Button) view.findViewById(R.id.continue_button);
        setupContinueButton(button);
        if (this.mUVRLocaleProvider.getUVRLocale().isEuPFM()) {
            button.setText(R.string.primer_consent);
        }
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Bold, view.findViewById(R.id.start_uvr_title_text), findViewById, button);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Regular, textView, textView2, view.findViewById(R.id.mute_text), view.findViewById(R.id.talk_quiet_text));
        this.mPresenter.checkUserOptedInForVoicePrivacySettings();
    }

    @VisibleForTesting
    void setupContinueButton(@NonNull View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                StartEnrollmentFragment.this.mStartEnrollmentMetricsRecorder.logContinueButtonClickMetric();
                if (StartEnrollmentFragment.this.mPresenter.shouldShowContinueConfirmationDialog()) {
                    StartEnrollmentFragment.this.showContinueConfirmationDialog();
                } else {
                    StartEnrollmentFragment.this.mPresenter.continueEnrollment();
                }
            }
        });
    }

    @VisibleForTesting
    void setupDisclaimer(@NonNull final String str, @NonNull TextView textView) {
        if (this.mPresenter.is1PSVDecoupledEnrollment()) {
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
            return;
        }
        String partnerName = this.mPresenter.getPartnerName(str);
        String string6 = getString(R.string.uvr_start_disclaimer, partnerName);
        SpannableString spannableString3 = new SpannableString(string6);
        spannableString3.setSpan(new ClickableSpan() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment.6
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View view) {
                StartEnrollmentFragment.this.mStartEnrollmentMetricsRecorder.logDisclaimerClickMetric();
                Integer disclaimerUri = StartEnrollmentFragment.this.mPresenter.getDisclaimerUri(str);
                if (disclaimerUri != null) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(StartEnrollmentFragment.this.getString(disclaimerUri.intValue())));
                    if (intent.resolveActivity(StartEnrollmentFragment.this.getContext().getPackageManager()) == null) {
                        return;
                    }
                    StartEnrollmentFragment.this.startActivity(intent);
                }
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull TextPaint textPaint) {
                textPaint.setColor(ThemeUtil.getColorFromAttribute(StartEnrollmentFragment.this.getActivity().getApplicationContext(), R.attr.mosaicAction10));
            }
        }, string6.indexOf(partnerName), string6.length() - 1, 33);
        textView.setText(spannableString3);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentContract.View
    public void showStartEnrollmentError() {
        getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment.2
            @Override // java.lang.Runnable
            public void run() {
                StartEnrollmentFragment.this.mAlertDialogBuilderFactory.create().setTitle(R.string.uvr_start_error_title).setMessage(R.string.uvr_start_default_error).setPositiveButton(R.string.uvr_start_confirmation_dialog_back, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentFragment.2.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                    }
                }).setCancelable(false).show();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentContract.View
    public void showStartEnrollmentVoicePrivacyOptedIn() {
        Log.i(TAG, "showStartEnrollmentVoicePrivacyOptedIn");
        this.mFragmentCallback.loadVoicePrivacyScreenForEnrollmentStart();
    }

    @VisibleForTesting
    StartEnrollmentFragment(@NonNull StartEnrollmentPresenter startEnrollmentPresenter, @NonNull AlertDialogBuilderFactory alertDialogBuilderFactory, @NonNull UVRLocaleProvider uVRLocaleProvider, @NonNull StartEnrollmentMetricsRecorder startEnrollmentMetricsRecorder) {
        this.mPresenter = startEnrollmentPresenter;
        this.mAlertDialogBuilderFactory = alertDialogBuilderFactory;
        this.mUVRLocaleProvider = uVRLocaleProvider;
        this.mStartEnrollmentMetricsRecorder = startEnrollmentMetricsRecorder;
    }
}
