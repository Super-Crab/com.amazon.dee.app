package com.amazon.alexa.fitness.context;

import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaContextsProvider;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.service.InstrumentedAlexaServicesConnection;
import com.amazon.alexa.fitness.session.FitnessSessionStateService;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaFitnessContextManagerImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u00007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000*\u0001\u000e\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/fitness/context/AlexaFitnessContextManagerImpl;", "Lcom/amazon/alexa/fitness/context/AlexaFitnessContextManager;", "instrumentedAlexaServicesConnection", "Lcom/amazon/alexa/fitness/service/InstrumentedAlexaServicesConnection;", "alexaContextsProvider", "Lcom/amazon/alexa/api/AlexaContextsProvider;", "fitnessSessionStateService", "Lcom/amazon/alexa/fitness/session/FitnessSessionStateService;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "(Lcom/amazon/alexa/fitness/service/InstrumentedAlexaServicesConnection;Lcom/amazon/alexa/api/AlexaContextsProvider;Lcom/amazon/alexa/fitness/session/FitnessSessionStateService;Lcom/amazon/alexa/fitness/logs/ILog;Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;)V", "connectionListener", "com/amazon/alexa/fitness/context/AlexaFitnessContextManagerImpl$connectionListener$1", "Lcom/amazon/alexa/fitness/context/AlexaFitnessContextManagerImpl$connectionListener$1;", "start", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AlexaFitnessContextManagerImpl implements AlexaFitnessContextManager {
    private final AlexaContextsProvider alexaContextsProvider;
    private final AlexaFitnessContextManagerImpl$connectionListener$1 connectionListener;
    private final FitnessSessionStateService fitnessSessionStateService;
    private final InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection;
    private final ILog log;
    private final MetricEventFactory metricEventFactory;

    /* JADX WARN: Type inference failed for: r2v1, types: [com.amazon.alexa.fitness.context.AlexaFitnessContextManagerImpl$connectionListener$1] */
    @Inject
    public AlexaFitnessContextManagerImpl(@NotNull InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection, @NotNull AlexaContextsProvider alexaContextsProvider, @NotNull FitnessSessionStateService fitnessSessionStateService, @NotNull ILog log, @NotNull MetricEventFactory metricEventFactory) {
        Intrinsics.checkParameterIsNotNull(instrumentedAlexaServicesConnection, "instrumentedAlexaServicesConnection");
        Intrinsics.checkParameterIsNotNull(alexaContextsProvider, "alexaContextsProvider");
        Intrinsics.checkParameterIsNotNull(fitnessSessionStateService, "fitnessSessionStateService");
        Intrinsics.checkParameterIsNotNull(log, "log");
        Intrinsics.checkParameterIsNotNull(metricEventFactory, "metricEventFactory");
        this.instrumentedAlexaServicesConnection = instrumentedAlexaServicesConnection;
        this.alexaContextsProvider = alexaContextsProvider;
        this.fitnessSessionStateService = fitnessSessionStateService;
        this.log = log;
        this.metricEventFactory = metricEventFactory;
        this.connectionListener = new AlexaServicesConnection.ConnectionListener() { // from class: com.amazon.alexa.fitness.context.AlexaFitnessContextManagerImpl$connectionListener$1
            @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
            public void onConnected() {
                ILog iLog;
                InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection2;
                AlexaContextsProvider alexaContextsProvider2;
                iLog = AlexaFitnessContextManagerImpl.this.log;
                ILog.DefaultImpls.debug$default(iLog, "AlexaFitnessContextManager", "connectionListener::onConnected() invoked...", null, 4, null);
                instrumentedAlexaServicesConnection2 = AlexaFitnessContextManagerImpl.this.instrumentedAlexaServicesConnection;
                alexaContextsProvider2 = AlexaFitnessContextManagerImpl.this.alexaContextsProvider;
                instrumentedAlexaServicesConnection2.registerContextsProvider(alexaContextsProvider2);
                instrumentedAlexaServicesConnection2.setContextCachingEnabled(false);
            }

            @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
            public void onConnectingFailed(@Nullable AlexaConnectingFailedReason alexaConnectingFailedReason, @Nullable String str) {
                ILog iLog;
                iLog = AlexaFitnessContextManagerImpl.this.log;
                ILog.DefaultImpls.error$default(iLog, "AlexaFitnessContextManager", "connectionListener::onConnectingFailed invoked, reason: " + alexaConnectingFailedReason + ", message: " + str, null, 4, null);
            }

            @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
            public void onDisconnected() {
                ILog iLog;
                InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection2;
                AlexaContextsProvider alexaContextsProvider2;
                FitnessSessionStateService fitnessSessionStateService2;
                ILog iLog2;
                InstrumentedAlexaServicesConnection instrumentedAlexaServicesConnection3;
                MetricEventFactory metricEventFactory2;
                iLog = AlexaFitnessContextManagerImpl.this.log;
                ILog.DefaultImpls.info$default(iLog, "AlexaFitnessContextManager", "connectionListener::onDisconnected() invoked...", null, 4, null);
                instrumentedAlexaServicesConnection2 = AlexaFitnessContextManagerImpl.this.instrumentedAlexaServicesConnection;
                alexaContextsProvider2 = AlexaFitnessContextManagerImpl.this.alexaContextsProvider;
                instrumentedAlexaServicesConnection2.deregisterContextsProvider(alexaContextsProvider2);
                fitnessSessionStateService2 = AlexaFitnessContextManagerImpl.this.fitnessSessionStateService;
                if (fitnessSessionStateService2.isFitnessSessionInProgress()) {
                    iLog2 = AlexaFitnessContextManagerImpl.this.log;
                    ILog.DefaultImpls.error$default(iLog2, "AlexaFitnessContextManager", "AlexaServicesConnection disconnected while in an active fitness session, trying to reconnect.", null, 4, null);
                    instrumentedAlexaServicesConnection3 = AlexaFitnessContextManagerImpl.this.instrumentedAlexaServicesConnection;
                    metricEventFactory2 = AlexaFitnessContextManagerImpl.this.metricEventFactory;
                    instrumentedAlexaServicesConnection3.connect(metricEventFactory2.createMetricEvent(MetricsClass.FITNESS_CONTEXT_MANAGER));
                }
            }
        };
    }

    @Override // com.amazon.alexa.fitness.service.Startable
    public void start() {
        ILog.DefaultImpls.debug$default(this.log, "AlexaFitnessContextManager", "Registering connection listener...", null, 4, null);
        this.instrumentedAlexaServicesConnection.registerConnectionListener(this.connectionListener);
    }
}
