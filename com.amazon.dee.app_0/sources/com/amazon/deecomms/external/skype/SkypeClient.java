package com.amazon.deecomms.external.skype;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
/* loaded from: classes12.dex */
public class SkypeClient {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SkypeClient.class);

    private boolean startActivityWithIntent(@NonNull Context context, @NonNull Intent intent, @NonNull CounterMetric counterMetric, @NonNull CounterMetric counterMetric2) {
        try {
            context.startActivity(intent);
            MetricsHelper.recordSingleOccurrence(counterMetric);
            return true;
        } catch (ActivityNotFoundException e) {
            LOG.e("Unexpected failure occurred during startActivity", e);
            MetricsHelper.recordSingleOccurrence(counterMetric2);
            return false;
        }
    }

    public String createSkypeUri(@NonNull String str, @NonNull String str2) {
        return String.format(Constants.SKYPE_ACTIVITY_FORMAT, str2, str);
    }

    @VisibleForTesting
    boolean isSkypeClientInstalled(@NonNull Context context) {
        try {
            context.getPackageManager().getPackageInfo(Constants.SKYPE_PACKAGE_NAME, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    @VisibleForTesting
    void navigateToPlayStore(@NonNull Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(String.format(Constants.PLAY_STORE_ACTIVITY_FORMAT, Constants.SKYPE_PACKAGE_NAME)));
        intent.setFlags(268435456);
        startActivityWithIntent(context, intent, CounterMetric.generateOperational(MetricKeys.SKYPE_REDIRECT_TO_MARKET_COUNT), CounterMetric.generateOperational(MetricKeys.SKYPE_REDIRECT_TO_MARKET_FAILED));
    }

    public boolean startSkypeActivity(@NonNull Context context, @NonNull String str) {
        if (!isSkypeClientInstalled(context)) {
            navigateToPlayStore(context);
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setComponent(new ComponentName(Constants.SKYPE_PACKAGE_NAME, "com.skype.raider.Main"));
        intent.setFlags(268435456);
        CommsLogger commsLogger = LOG;
        commsLogger.i("Preparing to start a Skype Activity using URI: " + str);
        return startActivityWithIntent(context, intent, CounterMetric.generateOperational(MetricKeys.SKYPE_APP_ACTION), CounterMetric.generateOperational(MetricKeys.SKYPE_APP_ACTION_FAILED));
    }
}
