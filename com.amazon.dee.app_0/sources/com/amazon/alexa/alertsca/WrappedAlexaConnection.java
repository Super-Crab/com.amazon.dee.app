package com.amazon.alexa.alertsca;

import android.os.ConditionVariable;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioInteraction;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaContextsProvider;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.api.compat.AlexaServicesApis;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
@Deprecated
/* loaded from: classes6.dex */
public class WrappedAlexaConnection implements AlexaServicesConnection.ConnectionListener {
    private static final int ALEXA_CONNECTION_TIMEOUT_MS = 3000;
    private static final int ALEXA_TEARDOWN_TIMEOUT_S = 3;
    private static final String TAG = "WrappedAlexaConnection";
    private final AlexaServicesConnection alexaServicesConnection;
    private final ConditionVariable connectionBlock;
    private final ScheduledExecutorService executor;

    @Inject
    public WrappedAlexaConnection(AlexaServicesConnection alexaServicesConnection, @Named("alexa_connection_executor") ScheduledExecutorService scheduledExecutorService) {
        this(alexaServicesConnection, scheduledExecutorService, new ConditionVariable());
    }

    private void doApiCall(final Runnable runnable) {
        this.executor.execute(new Runnable() { // from class: com.amazon.alexa.alertsca.WrappedAlexaConnection.10
            @Override // java.lang.Runnable
            public void run() {
                WrappedAlexaConnection.this.ensureConnected();
                runnable.run();
            }
        });
    }

    private void doApiCallWithoutConnecting(final Runnable runnable) {
        this.executor.execute(new Runnable() { // from class: com.amazon.alexa.alertsca.WrappedAlexaConnection.11
            @Override // java.lang.Runnable
            public void run() {
                runnable.run();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ensureConnected() {
        if (!this.alexaServicesConnection.isConnected()) {
            this.alexaServicesConnection.connect();
            this.connectionBlock.close();
            if (this.connectionBlock.block(DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS)) {
                return;
            }
            Log.e(TAG, "Connection timeout");
        }
    }

    public void cacheContexts(final Set<AlexaContext> set) {
        Preconditions.notNull(set, "alexaContexts is null");
        doApiCall(new Runnable() { // from class: com.amazon.alexa.alertsca.WrappedAlexaConnection.9
            @Override // java.lang.Runnable
            public void run() {
                WrappedAlexaConnection.this.doCacheContexts(set);
            }
        });
    }

    public void connect() {
        this.executor.execute(new Runnable() { // from class: com.amazon.alexa.alertsca.WrappedAlexaConnection.1
            @Override // java.lang.Runnable
            public void run() {
                WrappedAlexaConnection.this.ensureConnected();
            }
        });
    }

    public void deregisterConnectionListener(AlexaServicesConnection.ConnectionListener connectionListener) {
        Preconditions.notNull(connectionListener, "connectionListener is null");
        this.alexaServicesConnection.deregisterListener(connectionListener);
    }

    public void deregisterContextsProvider(final AlexaContextsProvider alexaContextsProvider) {
        Preconditions.notNull(alexaContextsProvider, "contextsProvider is null");
        doApiCallWithoutConnecting(new Runnable() { // from class: com.amazon.alexa.alertsca.WrappedAlexaConnection.7
            @Override // java.lang.Runnable
            public void run() {
                WrappedAlexaConnection.this.doDeregisterContextsProvider(alexaContextsProvider);
            }
        });
    }

    public void disconnect() {
        this.executor.execute(new Runnable() { // from class: com.amazon.alexa.alertsca.WrappedAlexaConnection.2
            @Override // java.lang.Runnable
            public void run() {
                WrappedAlexaConnection.this.alexaServicesConnection.disconnect();
            }
        });
    }

    @VisibleForTesting
    void doCacheContexts(Set<AlexaContext> set) {
        AlexaServicesApis.ContextProviders.cacheContexts(this.alexaServicesConnection, set);
    }

    @VisibleForTesting
    void doDeregisterContextsProvider(AlexaContextsProvider alexaContextsProvider) {
        AlexaServicesApis.ContextProviders.deregister(this.alexaServicesConnection, alexaContextsProvider);
    }

    @VisibleForTesting
    void doRegisterContextsProvider(AlexaContextsProvider alexaContextsProvider) {
        AlexaServicesApis.ContextProviders.register(this.alexaServicesConnection, alexaContextsProvider);
    }

    @VisibleForTesting
    void doScheduleInteraction(AlexaAudioInteraction alexaAudioInteraction) {
        String str = TAG;
        Log.i(str, "scheduling the interaction " + alexaAudioInteraction);
        AlexaServices.InteractionScheduler.schedule(this.alexaServicesConnection, alexaAudioInteraction);
    }

    @VisibleForTesting
    void doSendEvent(AlexaEvent alexaEvent, boolean z) {
        String str = TAG;
        Log.i(str, "sending an event " + alexaEvent);
        AlexaServices.EventSender.send(this.alexaServicesConnection, alexaEvent, z);
    }

    @VisibleForTesting
    void doSetContextCachingEnabled(boolean z) {
        GeneratedOutlineSupport1.outline172("Setting contextCachingEnabled to ", z);
        AlexaServicesApis.ContextProviders.setContextCachingEnabled(this.alexaServicesConnection, Collections.singleton("Alerts"), z);
    }

    @VisibleForTesting
    void doUnscheduleInteraction(AlexaAudioInteraction alexaAudioInteraction) {
        String str = TAG;
        Log.i(str, "unscheduling the interaction " + alexaAudioInteraction);
        AlexaServices.InteractionScheduler.unschedule(this.alexaServicesConnection, alexaAudioInteraction);
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        this.connectionBlock.open();
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        String str2 = "Connection failed to alexaservice due to " + alexaConnectingFailedReason;
        this.connectionBlock.open();
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        this.connectionBlock.open();
    }

    public void registerConnectionListener(AlexaServicesConnection.ConnectionListener connectionListener) {
        Preconditions.notNull(connectionListener, "connectionListener is null");
        this.alexaServicesConnection.registerListener(connectionListener);
    }

    public void registerContextsProvider(final AlexaContextsProvider alexaContextsProvider) {
        Preconditions.notNull(alexaContextsProvider, "contextProvider is null");
        doApiCall(new Runnable() { // from class: com.amazon.alexa.alertsca.WrappedAlexaConnection.6
            @Override // java.lang.Runnable
            public void run() {
                WrappedAlexaConnection.this.doRegisterContextsProvider(alexaContextsProvider);
            }
        });
    }

    public void scheduleInteraction(final AlexaAudioInteraction alexaAudioInteraction) {
        Preconditions.notNull(alexaAudioInteraction, "alexaAudioInteraction is null");
        doApiCall(new Runnable() { // from class: com.amazon.alexa.alertsca.WrappedAlexaConnection.4
            @Override // java.lang.Runnable
            public void run() {
                WrappedAlexaConnection.this.doScheduleInteraction(alexaAudioInteraction);
            }
        });
    }

    public void sendEvent(final AlexaEvent alexaEvent, final boolean z) {
        Preconditions.notNull(alexaEvent, "alexaEvent is null");
        doApiCall(new Runnable() { // from class: com.amazon.alexa.alertsca.WrappedAlexaConnection.3
            @Override // java.lang.Runnable
            public void run() {
                WrappedAlexaConnection.this.doSendEvent(alexaEvent, z);
            }
        });
    }

    public void setContextCachingEnabled(final boolean z) {
        doApiCall(new Runnable() { // from class: com.amazon.alexa.alertsca.WrappedAlexaConnection.8
            @Override // java.lang.Runnable
            public void run() {
                WrappedAlexaConnection.this.doSetContextCachingEnabled(z);
            }
        });
    }

    public void teardown() {
        this.executor.shutdown();
        try {
            this.executor.awaitTermination(3L, TimeUnit.SECONDS);
            deregisterConnectionListener(this);
        } catch (InterruptedException e) {
            Log.e(TAG, "Termination failed.", e);
        }
    }

    public void unscheduleInteraction(final AlexaAudioInteraction alexaAudioInteraction) {
        Preconditions.notNull(alexaAudioInteraction, "alexaAudioInteraction is null");
        doApiCall(new Runnable() { // from class: com.amazon.alexa.alertsca.WrappedAlexaConnection.5
            @Override // java.lang.Runnable
            public void run() {
                WrappedAlexaConnection.this.doUnscheduleInteraction(alexaAudioInteraction);
            }
        });
    }

    @VisibleForTesting
    WrappedAlexaConnection(AlexaServicesConnection alexaServicesConnection, ScheduledExecutorService scheduledExecutorService, ConditionVariable conditionVariable) {
        Preconditions.notNull(alexaServicesConnection, "alexaServicesConnection is null");
        Preconditions.notNull(scheduledExecutorService, "executorService is null");
        this.alexaServicesConnection = alexaServicesConnection;
        this.executor = scheduledExecutorService;
        this.connectionBlock = conditionVariable;
        registerConnectionListener(this);
    }
}
