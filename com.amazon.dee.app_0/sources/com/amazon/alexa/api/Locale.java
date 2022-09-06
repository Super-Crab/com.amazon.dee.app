package com.amazon.alexa.api;

import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class Locale {
    private static final String TAG = "Locale";

    private Locale() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deregisterListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaArtifactDownloadListener, "The provided ArtifactDownloadListener was null.");
        MessageReceiver<ApiType_ArtifactDownloadListenerMessageType> removeListener = alexaServicesConnection.removeListener(alexaArtifactDownloadListener);
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (removeListener == null) {
                Log.e(TAG, "can't deregister ArtifactDownloadListener");
            } else {
                messageSender.deregisterArtifactDownloadListener(client, removeListener);
            }
        } catch (Exception e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deregisterListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaLocalesListener alexaLocalesListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaLocalesListener, "The provided AlexaLocaleListener was null.");
        MessageReceiver<ax> removeListener = alexaServicesConnection.removeListener(alexaLocalesListener);
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (removeListener == null) {
                Log.e(TAG, "can't deregister AlexaLocalesListener");
            } else {
                messageSender.deregisterLocaleListener(client, removeListener);
            }
        } catch (Exception e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deregisterListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaSupportedLocalesListener, "The provided AlexaSupportedLocalesListener was null.");
        MessageReceiver<bs> removeListener = alexaServicesConnection.removeListener(alexaSupportedLocalesListener);
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (removeListener == null) {
                Log.e(TAG, "can't deregister AlexaSupportedLocalesListener");
            } else {
                messageSender.deregisterSupportedLocalesListener(client, removeListener);
            }
        } catch (Exception e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void registerListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaArtifactDownloadListener, "The provided ArtifactDownloadListener was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).registerArtifactDownloadListener(alexaServicesConnection.getClient(), alexaServicesConnection.getListener(alexaArtifactDownloadListener));
        } catch (Exception e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void registerListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaLocalesListener alexaLocalesListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaLocalesListener, "The provided AlexaLocaleListener was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).registerLocaleListener(alexaServicesConnection.getClient(), alexaServicesConnection.getListener(alexaLocalesListener));
        } catch (Exception e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void registerListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaSupportedLocalesListener, "The provided AlexaSupportedLocalesListener was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).registerSupportedLocalesListener(alexaServicesConnection.getClient(), alexaServicesConnection.getListener(alexaSupportedLocalesListener));
        } catch (Exception e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setLocales(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull List<java.util.Locale> list) {
        setLocales(alexaServicesConnection, list, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setLocales(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull List<java.util.Locale> list, @Nullable AlexaApiCallbacks alexaApiCallbacks) {
        AlexaApiCallbacksWrapper alexaApiCallbacksWrapper;
        Bundle bundle;
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(list, "The provided List<Locale> was null.");
        AlexaApiCallbacks bgVar = alexaApiCallbacks == null ? new bg() : alexaApiCallbacks;
        MetricBroadcastSender metricBroadcastSender = alexaServicesConnection.getMetricBroadcastSender();
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (alexaApiCallbacks != null) {
                AlexaConnectionProxyMapping mapping = alexaServicesConnection.getMapping();
                alexaApiCallbacksWrapper = new AlexaApiCallbacksWrapper(bgVar, "SET_LOCALES", metricBroadcastSender, mapping);
                try {
                    bundle = mapping.getAlexaApiCallbacks(alexaApiCallbacksWrapper);
                } catch (Exception e) {
                    e = e;
                    Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                    bgVar.doOnFailure(ApiCallFailure.MessagingFailure.create("Could not send locale to AlexaService", e));
                    if (alexaApiCallbacksWrapper != null) {
                        return;
                    }
                    metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.ApiCalls.Client.ATTEMPT.injectWith("SET_LOCALES"), "", null, bgVar.getId());
                    metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.ApiCalls.Client.FAILURE.injectWith("SET_LOCALES").appendToAlexaMetricsName(ApiCallFailure.FailureType.MESSAGING.name()), "", null, bgVar.getId());
                    return;
                }
            } else {
                bundle = null;
                alexaApiCallbacksWrapper = null;
            }
            ArrayList arrayList = new ArrayList();
            for (java.util.Locale locale : list) {
                arrayList.add(locale.toLanguageTag());
            }
            messageSender.setLocales(client, arrayList, bundle);
        } catch (Exception e2) {
            e = e2;
            alexaApiCallbacksWrapper = null;
        }
    }
}
