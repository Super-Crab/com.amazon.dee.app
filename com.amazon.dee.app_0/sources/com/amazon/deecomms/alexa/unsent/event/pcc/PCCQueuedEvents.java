package com.amazon.deecomms.alexa.unsent.event.pcc;

import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.alexa.CommsEventSender;
import com.amazon.deecomms.alexa.connection.enpoint.ConnectionEndpointHandler;
import com.amazon.deecomms.alexa.unsent.event.QueuedAlexaEvents;
import com.amazon.deecomms.calling.incallcommands.models.InCallCommandModel;
import com.amazon.deecomms.calling.incallcommands.models.PCCInCallCommandModel;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
/* loaded from: classes12.dex */
public class PCCQueuedEvents implements QueuedAlexaEvents {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PCCQueuedEvents.class);
    private final AlexaServicesConnection alexaServicesConnection;
    private final CommsEventSender commsEventSender;
    private final ConnectionEndpointHandler connectionEndpointHandler;
    private final Queue<InCallCommandModel> queue = new LinkedBlockingQueue();

    public PCCQueuedEvents(@NonNull CommsEventSender commsEventSender, @NonNull AlexaServicesConnection alexaServicesConnection, @NonNull ConnectionEndpointHandler connectionEndpointHandler) {
        this.commsEventSender = commsEventSender;
        this.alexaServicesConnection = alexaServicesConnection;
        this.connectionEndpointHandler = connectionEndpointHandler;
    }

    @Override // com.amazon.deecomms.alexa.unsent.event.QueuedAlexaEvents
    public synchronized void add(@NonNull InCallCommandModel inCallCommandModel) {
        if (inCallCommandModel instanceof PCCInCallCommandModel) {
            this.queue.add(inCallCommandModel);
        } else {
            LOG.i("Attempt to add non pcc event to PCCQueuedEvents. Skipping.");
        }
    }

    @Override // com.amazon.deecomms.alexa.unsent.event.QueuedAlexaEvents
    public synchronized void clear() {
        this.queue.clear();
    }

    @Override // com.amazon.deecomms.alexa.unsent.event.QueuedAlexaEvents
    public synchronized int getSize() {
        return this.queue.size();
    }

    @Override // com.amazon.deecomms.alexa.unsent.event.QueuedAlexaEvents
    public synchronized void send() {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Events size ");
        outline1.append(this.queue.size());
        commsLogger.i(outline1.toString());
        while (!this.queue.isEmpty()) {
            InCallCommandModel poll = this.queue.poll();
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Processing pending PCC event: ");
            outline12.append(poll.getInCallCommandName());
            commsLogger2.i(outline12.toString());
            this.commsEventSender.sendEvent(poll, this.alexaServicesConnection, this.connectionEndpointHandler);
        }
    }
}
