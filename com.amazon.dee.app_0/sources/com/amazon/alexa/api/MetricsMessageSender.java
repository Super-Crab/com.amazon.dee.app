package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
public class MetricsMessageSender extends AlexaMessageSender<be> implements AlexaMetricsListener {
    private static final String TAG = "MetricsMessageSender";
    private final ExtendedClient client;

    public MetricsMessageSender(IBinder iBinder, ExtendedClient extendedClient) {
        super(iBinder);
        this.client = extendedClient;
    }

    @Override // com.amazon.alexa.api.AlexaMetricsListener
    public void onMetricsReport(AlexaMetricsData alexaMetricsData) {
        try {
            sendMessage(be.ON_METRICS_REPORT, AlexaMetricsData.toBundle(alexaMetricsData));
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send on metrics report event ", e);
        }
    }
}
