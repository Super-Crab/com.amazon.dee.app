package com.amazon.alexa.api;

import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class cf {
    private static final String a = "cf";

    private cf() {
        throw new UnsupportedOperationException("don't instantiate");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).stopListening(alexaServicesConnection.getClient());
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @Nullable AlexaDialogExtras alexaDialogExtras) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).startListening(alexaServicesConnection.getClient(), alexaDialogExtras);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaWakeWordListener alexaWakeWordListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaWakeWordListener, "The provided AlexaWakeWordListener was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).registerWakeWordListener(alexaServicesConnection.getClient(), alexaServicesConnection.getListener(alexaWakeWordListener));
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull List<String> list, @Nullable AlexaApiCallbacks alexaApiCallbacks) {
        AlexaApiCallbacksWrapper alexaApiCallbacksWrapper;
        Bundle bundle;
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(list, "The provided wakeWords list was null.");
        AlexaApiCallbacks bgVar = alexaApiCallbacks == null ? new bg() : alexaApiCallbacks;
        MetricBroadcastSender metricBroadcastSender = alexaServicesConnection.getMetricBroadcastSender();
        String name = AlexaServicesMessageType.SET_WAKE_WORDS.name();
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (alexaApiCallbacks != null) {
                AlexaConnectionProxyMapping mapping = alexaServicesConnection.getMapping();
                alexaApiCallbacksWrapper = new AlexaApiCallbacksWrapper(bgVar, name, metricBroadcastSender, mapping);
                try {
                    bundle = mapping.getAlexaApiCallbacks(alexaApiCallbacksWrapper);
                } catch (Exception e) {
                    e = e;
                    Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
                    bgVar.doOnFailure(ApiCallFailure.MessagingFailure.create("Could not setWakeWords", e));
                    if (alexaApiCallbacksWrapper != null || metricBroadcastSender == null) {
                        return;
                    }
                    metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.ApiCalls.Client.ATTEMPT.injectWith(name), "", null, bgVar.getId());
                    metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.ApiCalls.Client.FAILURE.injectWith(name).appendToAlexaMetricsName(ApiCallFailure.FailureType.MESSAGING.name()), "", null, bgVar.getId());
                    return;
                }
            } else {
                bundle = null;
                alexaApiCallbacksWrapper = null;
            }
            messageSender.setWakeWords(client, list, bundle);
        } catch (Exception e2) {
            e = e2;
            alexaApiCallbacksWrapper = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).stopListening(alexaServicesConnection.getClient());
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection, @Nullable AlexaDialogExtras alexaDialogExtras) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).startListening(alexaServicesConnection.getClient(), alexaDialogExtras);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaWakeWordListener alexaWakeWordListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaWakeWordListener, "The provided AlexaWakeWordListener was null.");
        MessageReceiver<AlexaWakeWordListenerMessageType> removeListener = alexaServicesConnection.removeListener(alexaWakeWordListener);
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (removeListener == null) {
                Log.e(a, "deregister listener failed due to the message receiver being null");
            } else {
                messageSender.deregisterWakeWordListener(client, removeListener);
            }
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }
}
