package com.amazon.deecomms.calling.incallcommands;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.alexa.CommsAudioInteraction;
import com.amazon.deecomms.alexa.CommsAudioInteractionScheduler;
import com.amazon.deecomms.alexa.CommsEventSender;
import com.amazon.deecomms.alexa.connection.enpoint.a2a.A2AConnectionEndpointHandler;
import com.amazon.deecomms.alexa.unsent.event.a2a.A2AQueuedEvents;
import com.amazon.deecomms.calling.incallcommands.models.InCallCommandModel;
import com.amazon.deecomms.calling.incallcommands.models.InCallCommandModelFactory;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.core.LibraryModule;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class RingServiceBroadcastReceiver extends BroadcastReceiver {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, RingServiceBroadcastReceiver.class);
    @Inject
    protected A2AConnectionEndpointHandler a2AEndpointHandler;
    @Inject
    protected A2AQueuedEvents a2AUnsentEventsManager;
    @Inject
    @Named(LibraryModule.COMMS_ALEXA_SERVICE_CONNECTION)
    protected AlexaServicesConnection alexaServicesConnection;
    @Inject
    protected CommsAudioInteraction commsAudioInteraction;
    @Inject
    protected CommsAudioInteractionScheduler commsAudioInteractionScheduler;
    @Inject
    protected CommsEventSender commsEventSender;
    @Inject
    protected InCallCommandModelFactory factory;
    private InCallCommandModel inCallCommandModel;
    private BroadcastReceiver.PendingResult result;
    private AtomicBoolean resultFinished = new AtomicBoolean(false);
    @VisibleForTesting
    protected final AlexaServicesConnection.ConnectionListener connectionListener = new AlexaServicesConnection.ConnectionListener() { // from class: com.amazon.deecomms.calling.incallcommands.RingServiceBroadcastReceiver.1
        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            GeneratedOutlineSupport.outline4("onConnected() for command ", RingServiceBroadcastReceiver.this.inCallCommandModel.getInCallCommandName(), RingServiceBroadcastReceiver.LOG);
            RingServiceBroadcastReceiver ringServiceBroadcastReceiver = RingServiceBroadcastReceiver.this;
            CommsEventSender commsEventSender = ringServiceBroadcastReceiver.commsEventSender;
            InCallCommandModel inCallCommandModel = ringServiceBroadcastReceiver.inCallCommandModel;
            RingServiceBroadcastReceiver ringServiceBroadcastReceiver2 = RingServiceBroadcastReceiver.this;
            commsEventSender.manageInCallCommands(inCallCommandModel, this, ringServiceBroadcastReceiver2.alexaServicesConnection, ringServiceBroadcastReceiver2.a2AUnsentEventsManager, ringServiceBroadcastReceiver2.a2AEndpointHandler);
            RingServiceBroadcastReceiver.this.cleanUp();
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
            String inCallCommandName = RingServiceBroadcastReceiver.this.inCallCommandModel.getInCallCommandName();
            CommsLogger commsLogger = RingServiceBroadcastReceiver.LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("onConnectingFailed() to AlexaService failed. Reason: ");
            sb.append(alexaConnectingFailedReason);
            sb.append(". Message: ");
            sb.append(str);
            sb.append(". InCallCommandName: ");
            GeneratedOutlineSupport1.outline177(sb, inCallCommandName, commsLogger);
            RingServiceBroadcastReceiver.this.cleanUp();
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
            GeneratedOutlineSupport.outline4("disconnected for inCallCommandName ", RingServiceBroadcastReceiver.this.inCallCommandModel.getInCallCommandName(), RingServiceBroadcastReceiver.LOG);
        }
    };

    public RingServiceBroadcastReceiver() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void cleanUp() {
        this.alexaServicesConnection.deregisterListener(this.connectionListener);
        if (!this.commsAudioInteractionScheduler.shouldAcquireCommsFocus()) {
            AlexaServices.InteractionScheduler.unschedule(this.alexaServicesConnection, this.commsAudioInteraction);
        }
        LOG.i("deregistered connection listener");
        boolean z = true;
        if (this.result != null && !this.resultFinished.get()) {
            this.result.finish();
            this.resultFinished.set(true);
        } else {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("result is null: ");
            if (this.result != null) {
                z = false;
            }
            outline1.append(z);
            outline1.append(", or result is already finished");
            commsLogger.i(outline1.toString());
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (intent == null) {
            LOG.e("null intent");
            return;
        }
        CommsDaggerWrapper.getComponent().inject(this);
        this.inCallCommandModel = this.factory.createInCallCommandModel(intent);
        boolean isConnected = this.alexaServicesConnection.isConnected();
        GeneratedOutlineSupport.outline5("isConnected() ", isConnected, LOG);
        if (!isConnected) {
            this.result = goAsync();
        }
        this.commsEventSender.manageInCallCommands(this.inCallCommandModel, this.connectionListener, this.alexaServicesConnection, this.a2AUnsentEventsManager, this.a2AEndpointHandler);
        LOG.i("onReceive finished but should not exit until unregistering connection listener");
    }

    @VisibleForTesting
    RingServiceBroadcastReceiver(@NonNull CommsAudioInteraction commsAudioInteraction, @NonNull InCallCommandModelFactory inCallCommandModelFactory, @NonNull AlexaServicesConnection alexaServicesConnection, @NonNull CommsEventSender commsEventSender, @NonNull A2AQueuedEvents a2AQueuedEvents) {
        this.commsAudioInteraction = commsAudioInteraction;
        this.factory = inCallCommandModelFactory;
        this.alexaServicesConnection = alexaServicesConnection;
        this.commsEventSender = commsEventSender;
        this.a2AUnsentEventsManager = a2AQueuedEvents;
    }
}
