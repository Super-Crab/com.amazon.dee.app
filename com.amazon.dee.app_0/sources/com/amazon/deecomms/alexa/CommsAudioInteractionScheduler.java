package com.amazon.deecomms.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CapabilitiesManager;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class CommsAudioInteractionScheduler {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsAudioInteractionScheduler.class);
    private final CallManager callManager;
    private final CapabilitiesManager capabilitiesManager;
    private final CommsAudioInteraction commsAudioInteraction;

    @Inject
    public CommsAudioInteractionScheduler(@NonNull CommsAudioInteraction commsAudioInteraction, @NonNull CallManager callManager, @NonNull CapabilitiesManager capabilitiesManager) {
        this.commsAudioInteraction = commsAudioInteraction;
        this.callManager = callManager;
        this.capabilitiesManager = capabilitiesManager;
    }

    public synchronized void acquireCommsFocus(@NonNull AlexaServicesConnection alexaServicesConnection) {
        LOG.i("Scheduled comms focus");
        AlexaServices.InteractionScheduler.schedule(alexaServicesConnection, this.commsAudioInteraction);
    }

    public synchronized void acquireOrReleaseCommsFocus(@NonNull AlexaServicesConnection alexaServicesConnection) {
        if (shouldAcquireCommsFocus()) {
            acquireCommsFocus(alexaServicesConnection);
        } else {
            releaseCommsFocus(alexaServicesConnection);
        }
    }

    public synchronized void releaseCommsFocus(@NonNull AlexaServicesConnection alexaServicesConnection) {
        LOG.i("Unscheduled comms focus");
        AlexaServices.InteractionScheduler.unschedule(alexaServicesConnection, this.commsAudioInteraction);
    }

    public synchronized boolean shouldAcquireCommsFocus() {
        return this.callManager.isInAlexaCallMode() || this.callManager.isPCCCallInProgress(this.capabilitiesManager);
    }
}
