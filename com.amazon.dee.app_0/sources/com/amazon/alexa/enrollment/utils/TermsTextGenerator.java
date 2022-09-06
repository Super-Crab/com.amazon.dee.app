package com.amazon.alexa.enrollment.utils;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.ui.DebounceOnClickListener;
import com.amazon.alexa.enrollment.ui.terms.EnrollmentTermsActivity;
import com.amazon.alexa.identity.api.IdentityService;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public final class TermsTextGenerator {
    private static final String TAG = GeneratedOutlineSupport1.outline39(TermsTextGenerator.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private final boolean forAdultEnrollment;
    private final Fragment fragment;
    private DebounceOnClickListener termsClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.enrollment.utils.TermsTextGenerator.1
        @Override // com.amazon.alexa.enrollment.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            Log.i(TermsTextGenerator.TAG, "User clicked terms page");
            TermsTextGenerator.this.fragment.getActivity().startActivity(TermsTextGenerator.this.getTermsActivityIntent());
        }
    };
    private DebounceOnClickListener learnMoreClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.enrollment.utils.TermsTextGenerator.2
        @Override // com.amazon.alexa.enrollment.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            Log.i(TermsTextGenerator.TAG, "User clicked terms page");
            TermsTextGenerator.this.learnMoreClickActivity();
        }
    };
    private DebounceOnClickListener privacyClickSpanListener = new DebounceOnClickListener() { // from class: com.amazon.alexa.enrollment.utils.TermsTextGenerator.3
        @Override // com.amazon.alexa.enrollment.ui.DebounceOnClickListener
        public void debounceClick(View view) {
            Log.i(TermsTextGenerator.TAG, "User clicked privacy link");
            TermsTextGenerator.this.startPrivacyClickActivity();
        }
    };

    public TermsTextGenerator(Fragment fragment, boolean z) {
        this.fragment = fragment;
        this.forAdultEnrollment = z;
    }

    @VisibleForTesting
    private String getTermsString(IdentityService identityService) {
        String simpleName = TermsTextGenerator.class.getSimpleName();
        String string = this.fragment.getString(R.string.terms_click_span);
        String string2 = this.fragment.getString(R.string.learn_more_click_span);
        String string3 = this.fragment.getString(R.string.bipa_privacy_span);
        boolean isBipa = VoiceEnrollmentMarketplaceUtils.isBipa(identityService, simpleName);
        if (this.forAdultEnrollment) {
            String string4 = this.fragment.getString(R.string.fe_intro_terms);
            String string5 = this.fragment.getString(R.string.fe_intro_terms_bipa);
            if (isBipa) {
                return String.format(string5, string3, string, string2);
            }
            return String.format(string4, string, string2);
        }
        String string6 = this.fragment.getString(R.string.fe_kids_intro_terms);
        String string7 = this.fragment.getString(R.string.fe_kids_intro_terms_bipa);
        if (isBipa) {
            return String.format(string7, string3, string, string2);
        }
        return String.format(string6, string);
    }

    public SpannableString generateTerms(IdentityService identityService, final int i) {
        String termsString = getTermsString(identityService);
        String string = this.fragment.getString(R.string.terms_click_span);
        String string2 = this.fragment.getString(R.string.learn_more_click_span);
        String string3 = this.fragment.getString(R.string.bipa_privacy_span);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(i);
        SpannableString spannableString = new SpannableString(termsString);
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.amazon.alexa.enrollment.utils.TermsTextGenerator.4
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                TermsTextGenerator.this.termsClickSpanListener.onClick(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(i);
                textPaint.setUnderlineText(false);
            }
        };
        ClickableSpan clickableSpan2 = new ClickableSpan() { // from class: com.amazon.alexa.enrollment.utils.TermsTextGenerator.5
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                TermsTextGenerator.this.learnMoreClickSpanListener.onClick(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(i);
                textPaint.setUnderlineText(false);
            }
        };
        ClickableSpan clickableSpan3 = new ClickableSpan() { // from class: com.amazon.alexa.enrollment.utils.TermsTextGenerator.6
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                TermsTextGenerator.this.privacyClickSpanListener.onClick(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(i);
                textPaint.setUnderlineText(false);
            }
        };
        int indexOf = termsString.indexOf(string);
        int length = string.length() + indexOf;
        if (indexOf > 0 && indexOf < length) {
            String str = "Start span index: " + indexOf + " End span index: " + length;
            spannableString.setSpan(clickableSpan, indexOf, length, 33);
            spannableString.setSpan(foregroundColorSpan, indexOf, length, 33);
        }
        int indexOf2 = termsString.indexOf(string2);
        int length2 = string2.length() + indexOf2;
        if (indexOf2 > 0 && indexOf2 < length2) {
            String str2 = "Start span index: " + indexOf2 + " End span index: " + length2;
            spannableString.setSpan(clickableSpan2, indexOf2, length2, 33);
            spannableString.setSpan(foregroundColorSpan, indexOf2, length2, 33);
        }
        int indexOf3 = termsString.indexOf(string3);
        int length3 = string3.length() + indexOf3;
        if (indexOf3 > 0 && indexOf3 < length3) {
            String str3 = "Start span index: " + indexOf3 + " End span index: " + length3;
            spannableString.setSpan(clickableSpan3, indexOf3, length3, 33);
            spannableString.setSpan(foregroundColorSpan, indexOf3, length3, 33);
        }
        return spannableString;
    }

    public String getStartButtonText(IdentityService identityService) {
        return VoiceEnrollmentMarketplaceUtils.isBipa(identityService, TermsTextGenerator.class.getSimpleName()) ? this.fragment.getString(R.string.intro_consent) : this.fragment.getString(R.string.fe_intro_continue);
    }

    @VisibleForTesting
    Intent getTermsActivityIntent() {
        String enrollmentContext = ActivityUtils.getEnrollmentContext(this.fragment.getActivity());
        Intent intent = new Intent(this.fragment.getActivity(), EnrollmentTermsActivity.class);
        intent.putExtra(ActivityConstants.ENROLLMENT_CONTEXT, enrollmentContext);
        return intent;
    }

    @VisibleForTesting
    void learnMoreClickActivity() {
        Intent termsActivityIntent = getTermsActivityIntent();
        Bundle bundle = new Bundle();
        bundle.putString("URL", "LEARN_MORE_URL");
        termsActivityIntent.putExtras(bundle);
        this.fragment.getActivity().startActivity(termsActivityIntent);
    }

    @VisibleForTesting
    void startPrivacyClickActivity() {
        Intent termsActivityIntent = getTermsActivityIntent();
        Bundle bundle = new Bundle();
        bundle.putString("URL", "PRIVACY_URL");
        termsActivityIntent.putExtras(bundle);
        this.fragment.getActivity().startActivity(termsActivityIntent);
    }
}
