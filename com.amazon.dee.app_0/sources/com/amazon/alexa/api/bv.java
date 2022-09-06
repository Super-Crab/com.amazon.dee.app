package com.amazon.alexa.api;

import android.util.Log;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class bv {
    private static final String a = "bv";

    private bv() {
        throw new UnsupportedOperationException("don't instantiate");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaExpectTextListener alexaExpectTextListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaExpectTextListener, "The provided AlexaExpectTextListener was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).registerExpectTextListener(alexaServicesConnection.getClient(), alexaServicesConnection.getListener(alexaExpectTextListener));
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaTextResponseListener alexaTextResponseListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaTextResponseListener, "The provided TextResponseListener was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).registerTextResponseListener(alexaServicesConnection.getClient(), alexaServicesConnection.getListener(alexaTextResponseListener));
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull String str) {
        a(alexaServicesConnection, str, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull String str, TextAlexaDialogExtras textAlexaDialogExtras) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(str, "The provided message was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).sendTextMessage(alexaServicesConnection.getClient(), str, textAlexaDialogExtras);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
            bg bgVar = new bg();
            MetricBroadcastSender metricBroadcastSender = alexaServicesConnection.getMetricBroadcastSender();
            metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.ApiCalls.Client.ATTEMPT.injectWith("SEND_TEXT_MESSAGE"), "", null, bgVar.getId());
            metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.ApiCalls.Client.FAILURE.injectWith("SEND_TEXT_MESSAGE").appendToAlexaMetricsName(ApiCallFailure.FailureType.MESSAGING.name()), "", null, bgVar.getId());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaExpectTextListener alexaExpectTextListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaExpectTextListener, "The provided AlexaExpectTextListener was null.");
        MessageReceiver<ExpectTextMessageType> removeListener = alexaServicesConnection.removeListener(alexaExpectTextListener);
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (removeListener == null) {
                Log.e(a, "can't deregister AlexaExpectTextListener");
            } else {
                messageSender.deregisterExpectTextListener(client, removeListener);
            }
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaTextResponseListener alexaTextResponseListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaTextResponseListener, "The provided TextResponseListener was null.");
        MessageReceiver<TextResponseListenerMessageType> removeListener = alexaServicesConnection.removeListener(alexaTextResponseListener);
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (removeListener == null) {
                Log.e(a, "can't deregister TextResponseListener");
            } else {
                messageSender.deregisterTextResponseListener(client, removeListener);
            }
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }
}
