package com.amazon.alexa.sharing.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.MetricKeys;
import com.amazon.alexa.sharing.R;
import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import com.amazon.alexa.sharing.comms.dependencies.AlexaSharingClientComponentWrapper;
import com.amazon.alexa.sharing.presenter.FallbackStrategyPresenter;
import com.amazon.alexa.sharing.presenter.ShareToSocialDeepLinkPresenter;
import com.amazon.alexa.sharing.util.FeatureServiceUtil;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.Lazy;
import java.util.Collections;
import javax.inject.Inject;
/* loaded from: classes10.dex */
public class ShareSheetActivity extends AppCompatActivity {
    private static final String METRICS_KEY_SHARE_SHEET_OPEN_FAIL_COUNT = "comms.cx.ShareSheetActivity.openShareSheetFail.count";
    private static final String METRICS_KEY_SHARE_SHEET_OPEN_SUCCEED_COUNT = "comms.cx.ShareSheetActivity.openShareSheetSuccess.count";
    @Inject
    public FallbackStrategyPresenter fallbackStrategyPresenter;
    @Inject
    FeatureServiceUtil featureServiceUtil;
    private CommsMetricsEmitter metricsEmitter;
    @Inject
    public Lazy<AlexaCommsCoreMetricsService> metricsServiceLazy;
    @Inject
    public ShareToSocialDeepLinkPresenter presenter;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ShareSheetActivity.class);
    private static final String TAG = ShareSheetActivity.class.getSimpleName();

    void enableExternalShareToShareSheet() {
        LOG.d("/share/social/external deep link is triggered");
        try {
            startActivity(getShareToSocialDeepLinkPresenter().prepareMobileShareSheet(getIntent(), getApplicationContext()));
            LOG.d("Share sheet opened.");
            getCommsMetricsEmitter().recordOccurrenceMetric(METRICS_KEY_SHARE_SHEET_OPEN_SUCCEED_COUNT, Collections.EMPTY_MAP);
            finish();
        } catch (Throwable unused) {
            getCommsMetricsEmitter().recordOccurrenceMetric(METRICS_KEY_SHARE_SHEET_OPEN_FAIL_COUNT, Collections.EMPTY_MAP);
            getFallbackStrategyPresenter().showErrorToUser(getApplicationContext(), getApplicationContext().getString(R.string.share_sheet_open_failure));
            getFallbackStrategyPresenter().startMainActivityWithRouteToHome(TAG, getApplicationContext());
        }
    }

    @VisibleForTesting
    CommsMetricsEmitter getCommsMetricsEmitter() {
        return this.metricsEmitter;
    }

    @VisibleForTesting
    FallbackStrategyPresenter getFallbackStrategyPresenter() {
        return this.fallbackStrategyPresenter;
    }

    @VisibleForTesting
    FeatureServiceUtil getFeatureServiceUtil() {
        return this.featureServiceUtil;
    }

    @VisibleForTesting
    ShareToSocialDeepLinkPresenter getShareToSocialDeepLinkPresenter() {
        return this.presenter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        AlexaSharingClientComponentWrapper.getComponent().inject(this);
        super.onCreate(bundle);
        this.metricsEmitter = new CommsMetricsEmitter(this.metricsServiceLazy, TAG);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        onStartInternal();
    }

    @VisibleForTesting
    void onStartInternal() {
        if (getFeatureServiceUtil().isExternalShareToShareSheetEnabled()) {
            getCommsMetricsEmitter().recordOccurrenceMetric(MetricKeys.METRICS_KEY_WEBLAB_EXTERNAL_SHARE_TO_SHARE_SHEET_ENABLED, Collections.EMPTY_MAP);
            enableExternalShareToShareSheet();
            return;
        }
        getCommsMetricsEmitter().recordOccurrenceMetric(MetricKeys.METRICS_KEY_WEBLAB_EXTERNAL_SHARE_TO_SHARE_SHEET_DISABLED, Collections.EMPTY_MAP);
        getFallbackStrategyPresenter().showErrorToUser(getApplicationContext(), getApplicationContext().getString(R.string.sharing_failed_unsupported_feature));
        getFallbackStrategyPresenter().startMainActivityWithRouteToHome(TAG, getApplicationContext());
    }
}
