package com.amazon.alexa.voice.handsfree.settings;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.alexa.handsfree.notification.metrics.NotificationClickMetricMetadata;
import com.amazon.alexa.handsfree.notification.metrics.NotificationMetricReporter;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.ui.utils.HandsFreeLogoBarProvider;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.voice.handsfree.R;
import com.amazon.alexa.voice.handsfree.metrics.MetricsConstants;
/* loaded from: classes11.dex */
public class VoiceAppInstallExplainerActivity extends AppCompatActivity {
    private static final String TAG = VoiceAppInstallExplainerActivity.class.getSimpleName();
    private HandsFreeLogoBarProvider mHandsFreeLogoBarProvider;
    private Initializer mInitializer;
    private NotificationMetricReporter mNotificationMetricReporter;

    public VoiceAppInstallExplainerActivity() {
    }

    private void setupHandsFreeLogoBar(@NonNull String str) {
        View handsFreeLogoBarMosaic = this.mHandsFreeLogoBarProvider.getHandsFreeLogoBarMosaic(str);
        ((TextView) handsFreeLogoBarMosaic.findViewById(R.id.logo_title_text)).setTextColor(ThemeUtil.getColorFromAttribute(this, R.attr.mosaicNeutral10));
        ((ViewGroup) findViewById(R.id.handsfree_logo_container)).addView(handsFreeLogoBarMosaic);
    }

    @VisibleForTesting
    Spanned fromHtml(@NonNull String str) {
        int i = Build.VERSION.SDK_INT;
        return Html.fromHtml(str, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mInitializer = InitializerProvider.getInitializer();
        this.mInitializer.initialize(this);
        ThemeUtil.setTheme(this);
        setContentView(R.layout.mosaic_voice_app_install_explainer_layout);
        this.mHandsFreeLogoBarProvider = new HandsFreeLogoBarProvider(this);
        this.mNotificationMetricReporter = new NotificationMetricReporter(this);
        VoiceAppInstallExplainerActivityPresenter voiceAppInstallExplainerActivityPresenter = new VoiceAppInstallExplainerActivityPresenter(this);
        onCreateUiElements(voiceAppInstallExplainerActivityPresenter, voiceAppInstallExplainerActivityPresenter.getVoiceAppInfoForDevice());
        reportNotificationMetrics();
        voiceAppInstallExplainerActivityPresenter.onPageView();
    }

    @VisibleForTesting
    void onCreateUiElements(@NonNull VoiceAppInstallExplainerActivityPresenter voiceAppInstallExplainerActivityPresenter, @Nullable VoiceAppInfo voiceAppInfo) {
        if (voiceAppInfo == null) {
            Log.e(TAG, "No voice app associated with this device. Likely the activity was triggered incorrectly.");
            finish();
            return;
        }
        setActivityTextFormat(voiceAppInfo.getAppName(), voiceAppInfo.getAbbreviatedName());
        setupHandsFreeLogoBar(voiceAppInfo.getPackageName());
        setupLaterButtonOnClickListener(findViewById(R.id.later_button), voiceAppInstallExplainerActivityPresenter);
        setupGetAppButtonOnClickListener(findViewById(R.id.get_app_button), voiceAppInstallExplainerActivityPresenter, voiceAppInfo);
    }

    @VisibleForTesting
    void reportNotificationMetrics() {
        if (getIntent().hasExtra("extra_notification_text")) {
            this.mNotificationMetricReporter.reportNotificationClickMetric(NotificationClickMetricMetadata.PageType.PARTNER_VOICE_APP_DOWNLOAD_NOTIFICATION, getIntent().getStringExtra("extra_notification_text"));
        }
    }

    @VisibleForTesting
    void setActivityTextFormat(@NonNull String str, @NonNull String str2) {
        setupTextforTextViews(str, str2, (TextView) findViewById(R.id.handsfree_available_message), (TextView) findViewById(R.id.alexa_handsfree_download_prompt_text));
    }

    @VisibleForTesting
    void setupGetAppButtonOnClickListener(View view, @NonNull final VoiceAppInstallExplainerActivityPresenter voiceAppInstallExplainerActivityPresenter, @Nullable final VoiceAppInfo voiceAppInfo) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.settings.VoiceAppInstallExplainerActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view2) {
                voiceAppInstallExplainerActivityPresenter.onClick(MetricsConstants.SubPageType.GET_THE_APP_BUTTON);
                VoiceAppInstallExplainerActivity.this.startActivity(voiceAppInstallExplainerActivityPresenter.constructDownloadIntent(voiceAppInfo.getPackageName()));
                VoiceAppInstallExplainerActivity.this.finish();
            }
        });
    }

    @VisibleForTesting
    void setupLaterButtonOnClickListener(View view, @NonNull final VoiceAppInstallExplainerActivityPresenter voiceAppInstallExplainerActivityPresenter) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.settings.VoiceAppInstallExplainerActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view2) {
                voiceAppInstallExplainerActivityPresenter.onClick(MetricsConstants.SubPageType.LATER_BUTTON);
                VoiceAppInstallExplainerActivity.this.finish();
            }
        });
    }

    @VisibleForTesting
    void setupTextforTextViews(@NonNull String str, @NonNull String str2, @NonNull TextView textView, @NonNull TextView textView2) {
        textView.setText(getResources().getString(R.string.alexa_handsfree_available_text, str2));
        textView2.setText(fromHtml(getResources().getString(R.string.alexa_handsfree_download_prompt_text, str)));
    }

    @VisibleForTesting
    VoiceAppInstallExplainerActivity(Initializer initializer, HandsFreeLogoBarProvider handsFreeLogoBarProvider, NotificationMetricReporter notificationMetricReporter) {
        this.mInitializer = initializer;
        this.mHandsFreeLogoBarProvider = handsFreeLogoBarProvider;
        this.mNotificationMetricReporter = notificationMetricReporter;
    }
}
