package com.amazon.rtcsc.capabilityagent.avs;

import android.os.ConditionVariable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaContextsProvider;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.api.compat.AlexaServicesApis;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.rtcsc.capabilityagent.common.util.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes13.dex */
public class WrappedAlexaConnection implements AlexaServicesConnection.ConnectionListener {
    private static final int ALEXA_CONNECTION_TIMEOUT_MS = 3000;
    private static final int ALEXA_TEARDOWN_TIMEOUT_S = 3;
    private static final String TAG = "WrappedAlexaConnection";
    private final AlexaServicesConnection mAlexaServicesConnection;
    private final ConditionVariable mConnectionBlock;
    private final ScheduledExecutorService mScheduledExecutorService;

    @Inject
    public WrappedAlexaConnection(AlexaServicesConnection alexaServicesConnection, @Named("alexa connection executor") ScheduledExecutorService scheduledExecutorService) {
        this(alexaServicesConnection, scheduledExecutorService, new ConditionVariable());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ensureConnected() {
        RtcscLogger.i(TAG, "ensureConnected -- connected to alexa service ", new Object[0]);
        if (!this.mAlexaServicesConnection.isConnected()) {
            RtcscLogger.i(TAG, "ensureConnected-- Currently Not Connected to AlexaService. Connect to Alexa Service", new Object[0]);
            this.mAlexaServicesConnection.connect();
            this.mConnectionBlock.close();
            if (this.mConnectionBlock.block(DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS)) {
                return;
            }
            RtcscLogger.e(TAG, "ensureConnected -- Connecting to Alexa Service timeout", new Object[0]);
            return;
        }
        RtcscLogger.i(TAG, "ensureConnected-- Connected to AlexaService.", new Object[0]);
    }

    private void scheduleAlexaServiceApiCall(final Runnable runnable) {
        RtcscLogger.i(TAG, "scheduleAlexaServiceApiCall -- Schedule API Call on the executor", new Object[0]);
        this.mScheduledExecutorService.execute(new Runnable() { // from class: com.amazon.rtcsc.capabilityagent.avs.WrappedAlexaConnection.7
            @Override // java.lang.Runnable
            public void run() {
                RtcscLogger.i(WrappedAlexaConnection.TAG, "scheduleAlexaServiceApiCall -- Check if connected to Alexa service on Runnable", new Object[0]);
                WrappedAlexaConnection.this.ensureConnected();
                RtcscLogger.i(WrappedAlexaConnection.TAG, "scheduleAlexaServiceApiCall -- execute the api call on Runnable", new Object[0]);
                runnable.run();
            }
        });
    }

    public void connect() {
        RtcscLogger.i(TAG, "connect -- scheduling ensureConnected on runnable", new Object[0]);
        this.mScheduledExecutorService.execute(new Runnable() { // from class: com.amazon.rtcsc.capabilityagent.avs.WrappedAlexaConnection.1
            @Override // java.lang.Runnable
            public void run() {
                RtcscLogger.i(WrappedAlexaConnection.TAG, "connect -- running ensureConnected on runnable", new Object[0]);
                WrappedAlexaConnection.this.ensureConnected();
            }
        });
    }

    public void deregisterConnectionListener(AlexaServicesConnection.ConnectionListener connectionListener) {
        Preconditions.notNull(connectionListener, "connectionListener is null");
        RtcscLogger.i(TAG, "deregisterConnectionListener -- DeRegistering Alexa Service Connection Listener", new Object[0]);
        this.mAlexaServicesConnection.deregisterListener(connectionListener);
    }

    public void deregisterContextsProvider(final AlexaContextsProvider alexaContextsProvider) {
        Preconditions.notNull(alexaContextsProvider, "contextsProvider is null");
        RtcscLogger.i(TAG, "deregisterContextsProvider -- schedule deregistering contexts provider with Alexa service" + alexaContextsProvider, new Object[0]);
        scheduleAlexaServiceApiCall(new Runnable() { // from class: com.amazon.rtcsc.capabilityagent.avs.WrappedAlexaConnection.5
            @Override // java.lang.Runnable
            public void run() {
                RtcscLogger.i(WrappedAlexaConnection.TAG, "deregisterContextsProvider -- doDeregisterContextsProvider on runnable", new Object[0]);
                WrappedAlexaConnection.this.doDeregisterContextsProvider(alexaContextsProvider);
            }
        });
    }

    public void disconnect() {
        RtcscLogger.i(TAG, "disconnect -- scheduling AlexaServicesConnection disconnect on runnable", new Object[0]);
        this.mScheduledExecutorService.execute(new Runnable() { // from class: com.amazon.rtcsc.capabilityagent.avs.WrappedAlexaConnection.2
            @Override // java.lang.Runnable
            public void run() {
                RtcscLogger.i(WrappedAlexaConnection.TAG, "disconnect -- running mAlexaServicesConnection disconnect on runnable", new Object[0]);
                WrappedAlexaConnection.this.mAlexaServicesConnection.disconnect();
            }
        });
    }

    @VisibleForTesting
    void doDeregisterContextsProvider(AlexaContextsProvider alexaContextsProvider) {
        RtcscLogger.i(TAG, "doDeregisterContextsProvider -- DeRegistering Contexts provider with Alexa Service.", new Object[0]);
        AlexaServicesApis.ContextProviders.deregister(this.mAlexaServicesConnection, alexaContextsProvider);
    }

    @VisibleForTesting
    void doRegisterContextsProvider(AlexaContextsProvider alexaContextsProvider) {
        RtcscLogger.i(TAG, "doRegisterContextsProvider -- Register Contexts provider with Alexa Service. ", new Object[0]);
        AlexaServicesApis.ContextProviders.register(this.mAlexaServicesConnection, alexaContextsProvider);
    }

    @VisibleForTesting
    void doSendEvent(AlexaEvent alexaEvent) {
        RtcscLogger.i(TAG, "sending an event to Alexa Service: " + alexaEvent, new Object[0]);
        AlexaServices.EventSender.send(this.mAlexaServicesConnection, alexaEvent);
    }

    @VisibleForTesting
    void doSetContextCachingEnabled(boolean z) {
        RtcscLogger.i(TAG, "Setting contextCachingEnabled to " + z, new Object[0]);
        AlexaServicesApis.ContextProviders.setContextCachingEnabled(this.mAlexaServicesConnection, z);
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        RtcscLogger.i(TAG, "onConnected -- alexaservice connected ", new Object[0]);
        this.mConnectionBlock.open();
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        RtcscLogger.i(TAG, "onConnectingFailed -- Connection failed to alexaservice due to " + alexaConnectingFailedReason, new Object[0]);
        this.mConnectionBlock.open();
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        RtcscLogger.i(TAG, "onDisconnected -- alexaservice disconnected ", new Object[0]);
        this.mConnectionBlock.open();
    }

    public void registerConnectionListener(AlexaServicesConnection.ConnectionListener connectionListener) {
        Preconditions.notNull(connectionListener, "connectionListener is null");
        RtcscLogger.i(TAG, "registerConnectionListener -- Registering Alexa Service Connection Listener", new Object[0]);
        this.mAlexaServicesConnection.registerListener(connectionListener);
    }

    public void registerContextsProvider(final AlexaContextsProvider alexaContextsProvider) {
        Preconditions.notNull(alexaContextsProvider, "registerContextsProvider -- contextsProvider is null");
        RtcscLogger.i(TAG, "registerContextsProvider -- schedule registering contexts provider with Alexa service" + alexaContextsProvider, new Object[0]);
        scheduleAlexaServiceApiCall(new Runnable() { // from class: com.amazon.rtcsc.capabilityagent.avs.WrappedAlexaConnection.4
            @Override // java.lang.Runnable
            public void run() {
                RtcscLogger.i(WrappedAlexaConnection.TAG, "registerContextsProvider -- doRegisterContextsProvider on runnable", new Object[0]);
                WrappedAlexaConnection.this.doRegisterContextsProvider(alexaContextsProvider);
            }
        });
    }

    public void sendEvent(final AlexaEvent alexaEvent) {
        Preconditions.notNull(alexaEvent, "sendEvent -- alexaEvent is null");
        RtcscLogger.i(TAG, "sendEvent -- schedule Send alexaEvent to Alexa Service: " + alexaEvent, new Object[0]);
        scheduleAlexaServiceApiCall(new Runnable() { // from class: com.amazon.rtcsc.capabilityagent.avs.WrappedAlexaConnection.3
            @Override // java.lang.Runnable
            public void run() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("sendEvent -- invoking doSendEvent for alexaEvent: ");
                outline107.append(alexaEvent);
                outline107.append(" on runnable");
                RtcscLogger.i(WrappedAlexaConnection.TAG, outline107.toString(), new Object[0]);
                WrappedAlexaConnection.this.doSendEvent(alexaEvent);
            }
        });
    }

    public void setContextCachingEnabled(final boolean z) {
        scheduleAlexaServiceApiCall(new Runnable() { // from class: com.amazon.rtcsc.capabilityagent.avs.WrappedAlexaConnection.6
            @Override // java.lang.Runnable
            public void run() {
                WrappedAlexaConnection.this.doSetContextCachingEnabled(z);
            }
        });
    }

    public void teardown() {
        RtcscLogger.i(TAG, "teardown -- shutdown mScheduledExecutorService", new Object[0]);
        this.mScheduledExecutorService.shutdown();
        try {
            RtcscLogger.i(TAG, "teardown -- awaiting termination of mScheduledExecutorService", new Object[0]);
            this.mScheduledExecutorService.awaitTermination(3L, TimeUnit.SECONDS);
            RtcscLogger.i(TAG, "teardown -- deregisterConnectionListener", new Object[0]);
            deregisterConnectionListener(this);
            RtcscLogger.d(TAG, "teardown --  deregisterConnectionListener completed", new Object[0]);
        } catch (InterruptedException e) {
            RtcscLogger.e(TAG, "teardown -- Termination failed.", e);
        }
    }

    @VisibleForTesting
    WrappedAlexaConnection(AlexaServicesConnection alexaServicesConnection, ScheduledExecutorService scheduledExecutorService, ConditionVariable conditionVariable) {
        Preconditions.notNull(alexaServicesConnection, "mAlexaServicesConnection is null");
        Preconditions.notNull(scheduledExecutorService, "executorService is null");
        this.mAlexaServicesConnection = alexaServicesConnection;
        this.mScheduledExecutorService = scheduledExecutorService;
        this.mConnectionBlock = conditionVariable;
        RtcscLogger.d(TAG, "constructor -- mAlexaServicesConnection: " + alexaServicesConnection + "mScheduledExecutorService: " + scheduledExecutorService + "mConnectionBlock: " + conditionVariable, new Object[0]);
        RtcscLogger.i(TAG, "constructor -- Registering Connection Listener", new Object[0]);
        registerConnectionListener(this);
    }
}
