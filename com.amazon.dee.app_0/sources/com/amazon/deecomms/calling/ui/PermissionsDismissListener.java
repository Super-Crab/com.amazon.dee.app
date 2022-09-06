package com.amazon.deecomms.calling.ui;

import android.app.Activity;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import java.util.HashMap;
/* loaded from: classes12.dex */
public class PermissionsDismissListener implements DialogInterface.OnDismissListener {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PermissionsDismissListener.class);
    final Activity activity;
    final boolean closeView;
    final String metricKey;
    final String metricSource;

    public PermissionsDismissListener(@NonNull Activity activity, @NonNull String str, @NonNull String str2, @NonNull boolean z) {
        this.activity = activity;
        this.metricKey = str;
        this.closeView = z;
        this.metricSource = str2;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        LOG.i("Dismissing permissions dialog");
        HashMap hashMap = new HashMap();
        hashMap.put("source", this.metricSource + "dismissed");
        MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, this.metricKey, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, hashMap);
        if (this.closeView) {
            this.activity.finish();
        }
    }
}
