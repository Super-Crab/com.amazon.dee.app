package com.amazon.alexa.fitness.service;

import com.amazon.alexa.api.AlexaContextsProvider;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.mode.debug.EmulateConnection;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: InstrumentedAlexaServicesConnection.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u000bH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000bH&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u000bH&¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/fitness/service/InstrumentedAlexaServicesConnection;", "Lcom/amazon/alexa/fitness/service/Startable;", EmulateConnection.EXTRA_CONNECT, "", "metricEventInput", "Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "deregisterContextsProvider", "alexaContextsProvider", "Lcom/amazon/alexa/api/AlexaContextsProvider;", Metrics.DISCONNECT, "isConnected", "", "isDisconnected", "registerConnectionListener", "connectionListener", "Lcom/amazon/alexa/api/AlexaServicesConnection$ConnectionListener;", "registerContextsProvider", "send", "alexaEvent", "Lcom/amazon/alexa/api/AlexaEvent;", "requiresContext", "setContextCachingEnabled", "enableContextCaching", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface InstrumentedAlexaServicesConnection extends Startable {
    void connect(@NotNull MetricEvent metricEvent);

    void deregisterContextsProvider(@NotNull AlexaContextsProvider alexaContextsProvider);

    void disconnect();

    boolean isConnected();

    boolean isDisconnected();

    void registerConnectionListener(@NotNull AlexaServicesConnection.ConnectionListener connectionListener);

    void registerContextsProvider(@NotNull AlexaContextsProvider alexaContextsProvider);

    void send(@NotNull AlexaEvent alexaEvent, boolean z);

    void setContextCachingEnabled(boolean z);
}
