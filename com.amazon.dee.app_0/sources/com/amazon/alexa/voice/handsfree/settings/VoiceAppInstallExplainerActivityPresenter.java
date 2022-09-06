package com.amazon.alexa.voice.handsfree.settings;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.voice.handsfree.metrics.MetricType;
import com.amazon.alexa.voice.handsfree.metrics.MetricsConstants;
/* loaded from: classes11.dex */
class VoiceAppInstallExplainerActivityPresenter {
    @VisibleForTesting
    static final String GOOGLE_PLAY_STORE_DEEPLINK_TEMPLATE = "https://play.google.com/store/apps/details?id=%s";
    @VisibleForTesting
    static final String GOOGLE_PLAY_STORE_PACKAGE_NAME = "com.android.vending";
    @VisibleForTesting
    static final String LG_APP_DETAIL_PAGE_CANONICAL_CLASSNAME = "com.lge.appbox.client.AppBoxClient";
    @VisibleForTesting
    static final String LG_EXTRA_KEY_PACKAGENAME = "packagename";
    @VisibleForTesting
    static final String LG_EXTRA_KEY_TYPE = "type";
    @VisibleForTesting
    static final String LG_EXTRA_VALUE_DOWNLOAD = "download";
    @VisibleForTesting
    static final String LG_UPDATE_CENTER_PACKAGE_NAME = "com.lge.appbox.client";
    private static final String TAG = "VoiceAppInstallExplainerActivityPresenter";
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final VoiceAppInstallExplainerMetricRecorder mVoiceAppInstallExplainerMetricRecorder;

    @VisibleForTesting
    VoiceAppInstallExplainerActivityPresenter(@NonNull VoiceAppInstallExplainerMetricRecorder voiceAppInstallExplainerMetricRecorder, @NonNull AMPDInformationProvider aMPDInformationProvider) {
        this.mVoiceAppInstallExplainerMetricRecorder = voiceAppInstallExplainerMetricRecorder;
        this.mAMPDInformationProvider = aMPDInformationProvider;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Intent constructDownloadIntent(@NonNull String str) {
        Intent intent = new Intent();
        if (str.equals(VoiceAppInfo.QUALCOMM.getPackageName())) {
            intent.setComponent(new ComponentName(LG_UPDATE_CENTER_PACKAGE_NAME, LG_APP_DETAIL_PAGE_CANONICAL_CLASSNAME));
            intent.putExtra("type", LG_EXTRA_VALUE_DOWNLOAD);
            intent.putExtra(LG_EXTRA_KEY_PACKAGENAME, str);
            return intent;
        }
        String format = String.format(GOOGLE_PLAY_STORE_DEEPLINK_TEMPLATE, str);
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(format));
        intent.setPackage("com.android.vending");
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public VoiceAppInfo getVoiceAppInfoForDevice() {
        String str = this.mAMPDInformationProvider.getDeviceInformation().get("manufacturer");
        if (str == null) {
            Log.e(TAG, "Unable to detect device manufacturer");
            this.mVoiceAppInstallExplainerMetricRecorder.reportOperationalMetric(MetricType.MANUFACTURER_NOT_DETECTED.getValue());
            return null;
        }
        VoiceAppInfo voiceAppInfo = VoiceAppInfo.getVoiceAppInfo(str);
        if (voiceAppInfo == null) {
            this.mVoiceAppInstallExplainerMetricRecorder.reportOperationalMetric(MetricType.MANUFACTURER_NOT_SUPPORTED.getValue());
        }
        return voiceAppInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onClick(@NonNull MetricsConstants.SubPageType subPageType) {
        this.mVoiceAppInstallExplainerMetricRecorder.recordClick(subPageType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onPageView() {
        this.mVoiceAppInstallExplainerMetricRecorder.recordPageView();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VoiceAppInstallExplainerActivityPresenter(@NonNull Context context) {
        this(new VoiceAppInstallExplainerMetricRecorder(context), AMPDInformationProvider.getInstance(context));
    }
}
