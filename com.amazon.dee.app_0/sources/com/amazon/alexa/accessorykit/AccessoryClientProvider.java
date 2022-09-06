package com.amazon.alexa.accessorykit;

import android.content.Context;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessoryclient.client.AlexaAccessoryClient;
/* loaded from: classes6.dex */
public final class AccessoryClientProvider {
    private static AlexaAccessoryClient accessoryClient;

    private AccessoryClientProvider() {
    }

    public static synchronized AlexaAccessoryClient getOrCreateConnectedClient(Context context, AccessoryMetricsService accessoryMetricsService) {
        AlexaAccessoryClient alexaAccessoryClient;
        synchronized (AccessoryClientProvider.class) {
            Preconditions.notNull(context, "context");
            Preconditions.notNull(accessoryMetricsService, "metricsService");
            if (accessoryClient == null) {
                accessoryClient = new AlexaAccessoryClient(context.getApplicationContext(), accessoryMetricsService);
                accessoryClient.initialize();
            }
            alexaAccessoryClient = accessoryClient;
        }
        return alexaAccessoryClient;
    }
}
