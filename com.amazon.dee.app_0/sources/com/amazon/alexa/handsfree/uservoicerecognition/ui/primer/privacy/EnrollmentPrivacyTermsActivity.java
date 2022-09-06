package com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.privacy;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerMetricsRecorder;
import com.amazon.alexa.mosaic.components.ThemeUtil;
/* loaded from: classes8.dex */
public class EnrollmentPrivacyTermsActivity extends AppCompatActivity {
    private EnrollmentPrimerMetricsRecorder mEnrollmentPrimerMetricsRecorder;
    private EnrollmentThemeUtil mEnrollmentThemeUtil;
    private ThemeUtilWrapper mThemeUtilWrapper;
    private View.OnClickListener okButtonOnClickListener = new View.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.privacy.EnrollmentPrivacyTermsActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(@NonNull View view) {
            EnrollmentPrivacyTermsActivity.this.mEnrollmentPrimerMetricsRecorder.logVoicePrivacyScreenClosed();
            EnrollmentPrivacyTermsActivity.this.finish();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes8.dex */
    public class ThemeUtilWrapper {
        private Context mContext;

        ThemeUtilWrapper(Context context) {
            this.mContext = context;
        }

        public void setTheme() {
            ThemeUtil.setTheme(this.mContext);
        }
    }

    private void setMosaicThemeColor() {
        this.mEnrollmentThemeUtil.setBackgroundColorToView(this, R.attr.mosaicBackground, findViewById(R.id.privacy_page_content));
        this.mEnrollmentThemeUtil.setTextColor(this, R.attr.mosaicNeutral10, (TextView) findViewById(R.id.privacy_page_warning_header), (TextView) findViewById(R.id.privacy_page_warning_desc_one), (TextView) findViewById(R.id.privacy_page_warning_desc_two));
        this.mEnrollmentThemeUtil.setBackgroundToView(this, R.drawable.primary_button_mosaic_background, findViewById(R.id.privacy_page_ok_btn));
        this.mEnrollmentThemeUtil.setTintColorToImageView((ImageView) findViewById(R.id.privacy_page_back), this, R.attr.mosaicNeutral10);
        this.mEnrollmentThemeUtil.setTintColorToImageView((ImageView) findViewById(R.id.privacy_page_warning), this, R.attr.mosaicNeutral10);
    }

    @VisibleForTesting
    void configure() {
        this.mEnrollmentPrimerMetricsRecorder = getEnrollmentPrimerMetricsRecorder();
        this.mEnrollmentThemeUtil = getEnrollmentThemeUtil();
        this.mThemeUtilWrapper = getThemeUtilWrapper();
    }

    @VisibleForTesting
    EnrollmentPrimerMetricsRecorder getEnrollmentPrimerMetricsRecorder() {
        return new EnrollmentPrimerMetricsRecorder(this);
    }

    @VisibleForTesting
    EnrollmentThemeUtil getEnrollmentThemeUtil() {
        return new EnrollmentThemeUtil();
    }

    @VisibleForTesting
    ThemeUtilWrapper getThemeUtilWrapper() {
        return new ThemeUtilWrapper(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        configure();
        super.onCreate(bundle);
        setContentView(R.layout.activity_enrollment_privacy_terms);
        this.mThemeUtilWrapper.setTheme();
        setMosaicThemeColor();
        findViewById(R.id.privacy_page_ok_btn).setOnClickListener(this.okButtonOnClickListener);
        findViewById(R.id.privacy_page_back).setOnClickListener(this.okButtonOnClickListener);
        this.mEnrollmentPrimerMetricsRecorder.logVoicePrivacyScreenShown();
    }
}
