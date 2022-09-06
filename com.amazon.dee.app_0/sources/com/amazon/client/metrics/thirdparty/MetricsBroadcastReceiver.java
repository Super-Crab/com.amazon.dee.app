package com.amazon.client.metrics.thirdparty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.dp.logger.DPLogger;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class MetricsBroadcastReceiver extends BroadcastReceiver {
    private static final DPLogger log = new DPLogger(com.amazon.alexa.client.alexaservice.metrics.client.MetricsBroadcastReceiver.zZm);
    private static List<UploadIntentListener> sUploadIntentListenerList = new ArrayList();

    /* JADX INFO: Access modifiers changed from: protected */
    public static void addUploadIntentListener(UploadIntentListener uploadIntentListener) {
        sUploadIntentListenerList.add(uploadIntentListener);
    }

    public static void shutdown() {
        sUploadIntentListenerList.clear();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent == null ? null : intent.getAction();
        if (MetricsServiceConstants.ACTION_UPLOAD_METRICS.equals(action)) {
            if (context.checkCallingOrSelfPermission(MetricsServicePermissions.UPLOAD_METRICS) == -1) {
                log.error("onReceive", "Metrics upload permission denied.", new Object[0]);
                return;
            }
            log.info("onReceive", "Upload metrics intent received. Notifying listeners.", new Object[0]);
            for (UploadIntentListener uploadIntentListener : sUploadIntentListenerList) {
                uploadIntentListener.onUploadIntentReceived();
            }
            return;
        }
        log.verbose("onReceive", "Received unexpected intent.", " intent: ", intent, " action: ", action);
    }
}
