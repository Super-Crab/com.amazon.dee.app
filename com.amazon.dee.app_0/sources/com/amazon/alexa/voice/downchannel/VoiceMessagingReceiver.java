package com.amazon.alexa.voice.downchannel;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.messaging.Message;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.voice.metrics.InternationalDateTime;
import com.amazon.alexa.voice.metrics.VoiceMetricsConstants;
import com.amazon.alexa.voice.metrics.VoiceMetricsTimer;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
final class VoiceMessagingReceiver implements MessagingReceiver {
    private static final String ACTION_KEY = "action";
    private static final String ACTION_VALUE_CONNECT = "com.amazon.alexa.CONNECT";
    private static final String CREATION_TIME_KEY = "creationTime";
    private static final String TAG = "com.amazon.alexa.voice.downchannel.VoiceMessagingReceiver";
    private final DownchannelController downchannelController;
    private final MetricsService metricsService;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class LatencyReportingRange {
        static final long MAXIMUM = 60000;
        static final long MINIMUM = 0;

        private LatencyReportingRange() {
        }

        static boolean contains(long j) {
            return j >= 0 && j <= 60000;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VoiceMessagingReceiver(DownchannelController downchannelController, MetricsService metricsService) {
        this.downchannelController = downchannelController;
        this.metricsService = metricsService;
    }

    private void reportMessageReceivedLatency(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis() - InternationalDateTime.toEpochMillisUtc(str);
            if (!LatencyReportingRange.contains(currentTimeMillis)) {
                String str2 = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Latency duration of ");
                outline107.append(Long.toString(currentTimeMillis));
                outline107.append(" was outside reportable range. Clock skew beween client and server?");
                Log.w(str2, outline107.toString());
                return;
            }
            this.metricsService.recordTimer(new VoiceMetricsTimer(VoiceMetricsConstants.WAKE_NOTIFICATION_LATENCY, VoiceMetricsConstants.COMPONENT_VOICE_INGRESS, null, currentTimeMillis, false));
        } catch (Exception e) {
            String str3 = TAG;
            Log.e(str3, "Failed to obtain message creation time from wake notification. Value was " + str, e);
        }
    }

    @Override // com.amazon.alexa.protocols.messaging.MessagingReceiver
    public void onReceive(@NonNull Message message) {
        if (message == null) {
            Log.w(TAG, "Got null message.");
            return;
        }
        Bundle bundle = message.get();
        if (bundle == null) {
            Log.w(TAG, "Got null push message bundle!");
            return;
        }
        String string = bundle.getString("action");
        if (TextUtils.isEmpty(string)) {
            Log.w(TAG, "Empty action in push message bundle.");
        } else if (!ACTION_VALUE_CONNECT.equals(string)) {
            GeneratedOutlineSupport1.outline164("Unknown action value in push message bundle: ", string, TAG);
        } else {
            reportMessageReceivedLatency(bundle.getString(CREATION_TIME_KEY));
            this.downchannelController.wakeUp();
            message.cancel();
        }
    }
}
