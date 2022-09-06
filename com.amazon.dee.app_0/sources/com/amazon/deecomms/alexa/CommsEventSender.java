package com.amazon.deecomms.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.alexa.connection.enpoint.ConnectionEndpointHandler;
import com.amazon.deecomms.alexa.unsent.event.QueuedAlexaEvents;
import com.amazon.deecomms.calling.incallcommands.models.InCallCommandModel;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes12.dex */
public class CommsEventSender {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsEventSender.class);
    private final CommsAudioInteraction commsAudioInteraction;
    private final CommsAudioInteractionScheduler commsAudioInteractionScheduler;

    public CommsEventSender(@NonNull CommsAudioInteraction commsAudioInteraction, @NonNull CommsAudioInteractionScheduler commsAudioInteractionScheduler) {
        this.commsAudioInteraction = commsAudioInteraction;
        this.commsAudioInteractionScheduler = commsAudioInteractionScheduler;
    }

    public synchronized void manageInCallCommands(@NonNull InCallCommandModel inCallCommandModel, @NonNull AlexaServicesConnection.ConnectionListener connectionListener, @NonNull AlexaServicesConnection alexaServicesConnection, @NonNull QueuedAlexaEvents queuedAlexaEvents, @NonNull ConnectionEndpointHandler connectionEndpointHandler) {
        if (alexaServicesConnection.isConnected()) {
            LOG.i("ManageInCallCommands: ASC connected");
            sendEvent(inCallCommandModel, alexaServicesConnection, connectionEndpointHandler);
        } else {
            LOG.i("ManageInCallCommands: not connected. Adding as pending event.");
            queuedAlexaEvents.add(inCallCommandModel);
            alexaServicesConnection.registerListener(connectionListener);
            alexaServicesConnection.connect();
        }
    }

    public synchronized void sendEvent(@NonNull InCallCommandModel inCallCommandModel, @NonNull AlexaServicesConnection alexaServicesConnection, @NonNull ConnectionEndpointHandler connectionEndpointHandler) {
        if (!alexaServicesConnection.isConnected()) {
            LOG.i("Connection not connected. Event cannot be sent.");
        }
        String inCallCommandName = inCallCommandModel.getInCallCommandName();
        AlexaEvent alexaEvent = new AlexaEvent(inCallCommandModel.getHeader(), inCallCommandModel.getPayload());
        boolean hasContext = inCallCommandModel.hasContext();
        if (!connectionEndpointHandler.shouldDisconnect()) {
            if (hasContext) {
                LOG.i("registering context provider");
                AlexaServices.ContextProvider.register(alexaServicesConnection, inCallCommandModel.getContext());
            }
            this.commsAudioInteractionScheduler.acquireOrReleaseCommsFocus(alexaServicesConnection);
            AlexaServices.EventSender.send(alexaServicesConnection, alexaEvent, true);
        } else {
            GeneratedOutlineSupport.outline4("unscheduling comms focus for incallCommandName ", inCallCommandName, LOG);
            this.commsAudioInteractionScheduler.releaseCommsFocus(alexaServicesConnection);
            if (hasContext) {
                AlexaServices.ContextProvider.register(alexaServicesConnection, inCallCommandModel.getContext());
            }
            AlexaServices.EventSender.send(alexaServicesConnection, alexaEvent, true);
            if (hasContext && !inCallCommandModel.getInCallCommandName().contains("PhoneCallController")) {
                LOG.i("deregistering context provider");
                AlexaServices.ContextProvider.deregister(alexaServicesConnection, inCallCommandModel.getContext());
            }
            LOG.i("disconnecting alexa service");
            alexaServicesConnection.disconnect();
        }
    }
}
