package com.amazon.alexa.api;

import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.Collection;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class y {
    private static final String a = "y";

    private y() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).clearContextCache(alexaServicesConnection.getClient(), null);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaContextsProvider alexaContextsProvider) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaContextsProvider, "alexaContextsProvider can't be null");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).registerContextsProvider(alexaServicesConnection.getClient(), alexaServicesConnection.getContextsProviderMessageReceiver(alexaContextsProvider));
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaEvent alexaEvent) {
        a(alexaServicesConnection, alexaEvent, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaEvent alexaEvent, boolean z) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        a(alexaServicesConnection, alexaEvent, z, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaEvent alexaEvent, boolean z, @Nullable AlexaApiCallbacks alexaApiCallbacks) {
        AlexaApiCallbacksWrapper alexaApiCallbacksWrapper;
        Bundle bundle;
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaEvent, "The provided AlexaEvent was null.");
        AlexaApiCallbacks bgVar = alexaApiCallbacks == null ? new bg() : alexaApiCallbacks;
        MetricBroadcastSender metricBroadcastSender = alexaServicesConnection.getMetricBroadcastSender();
        String name = AlexaServicesMessageType.SEND_ALEXA_EVENT.name();
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
                    bgVar.doOnFailure(ApiCallFailure.MessagingFailure.create("Could not send AlexaEvent", e));
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
            messageSender.sendAlexaEvent(client, alexaEvent, z, bundle);
        } catch (Exception e2) {
            e = e2;
            alexaApiCallbacksWrapper = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull Set<AlexaContext> set) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(set, "alexaContexts can't be null");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).cacheContexts(alexaServicesConnection.getClient(), BundleTransformer.getDefaultInstance().toBundle((Collection) set));
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull Set<String> set, boolean z) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(set, "namespaces can't be null");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).setContextCachingEnabled(alexaServicesConnection.getClient(), z, BundleTransformer.getDefaultInstance().toBundle((Collection) set));
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, boolean z) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).setContextCachingEnabled(alexaServicesConnection.getClient(), z, null);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaContextsProvider alexaContextsProvider) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaContextsProvider, "alexaContextProvider can't be null");
        MessageReceiver<AlexaContextsProviderMessageType> removeContextProviderMessageReceiver = alexaServicesConnection.removeContextProviderMessageReceiver(alexaContextsProvider);
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (removeContextProviderMessageReceiver == null) {
                Log.e(a, "can't deregister AlexaContextsProvider");
            } else {
                messageSender.deregisterContextsProvider(client, removeContextProviderMessageReceiver);
            }
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull Set<String> set) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaServicesConnection, "namespaces can't be null");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).clearContextCache(alexaServicesConnection.getClient(), BundleTransformer.getDefaultInstance().toBundle((Collection) set));
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }
}
