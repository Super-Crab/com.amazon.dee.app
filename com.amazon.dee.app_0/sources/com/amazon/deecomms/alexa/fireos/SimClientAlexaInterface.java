package com.amazon.deecomms.alexa.fireos;

import amazon.speech.simclient.Header;
import amazon.speech.simclient.IConnectionListener;
import amazon.speech.simclient.SimClient;
import amazon.speech.simclient.SimError;
import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.models.device.DirectivePayloadVersion;
import com.amazon.deecomms.alexa.AlexaInterface;
import com.amazon.deecomms.alexa.InCallEvent;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import java.util.Set;
/* loaded from: classes12.dex */
public class SimClientAlexaInterface implements IConnectionListener, AlexaInterface {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SimClientAlexaInterface.class);
    private SimClient mSimClient;
    private final Set<SimEventListener> simEventListeners = Sets.newConcurrentHashSet();
    private ObjectMapper objectMapper = new ObjectMapper();
    private SimEventSender mSimEventSender = new SimEventSender(this);

    public SimClientAlexaInterface(Context context) {
        SimClient.createClient(context, this);
    }

    private void sendDeviceContextVersion() {
        if (this.mSimClient == null) {
            LOG.e("No sim client!");
            return;
        }
        try {
            LOG.i("Sending device context version");
            int addDeviceContext = this.mSimClient.addDeviceContext(new Header("SipClient", "SipClient"), this.objectMapper.writeValueAsString(DirectivePayloadVersion.builder().version("1.0").build()));
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Sending device context version : ");
            sb.append(SimError.getDescriptionFor(addDeviceContext));
            commsLogger.i(sb.toString());
        } catch (JsonProcessingException e) {
            LOG.e(String.format("Error updating device context: %s", e));
        }
    }

    @Override // com.amazon.deecomms.alexa.AlexaInterface
    public boolean acquireCommsFocus() {
        SimClient simClient = this.mSimClient;
        if (simClient != null) {
            int addAudioCommunicationsFocus = simClient.addAudioCommunicationsFocus("SipClient");
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Comms focus acquired with result :");
            outline1.append(SimError.getDescriptionFor(addAudioCommunicationsFocus));
            commsLogger.i(outline1.toString());
            return addAudioCommunicationsFocus == 0;
        }
        LOG.i("No active sim client. Unable to acquire comms focus");
        return false;
    }

    public synchronized void addListener(SimEventListener simEventListener) {
        this.simEventListeners.add(simEventListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized SimClient getSimClient() {
        return this.mSimClient;
    }

    public Set<SimEventListener> getSimEventListeners() {
        return this.simEventListeners;
    }

    @Override // amazon.speech.simclient.IConnectionListener
    public void onConnect(SimClient simClient) {
        LOG.i("Simclient connection established");
        this.mSimClient = simClient;
        sendDeviceContextVersion();
    }

    @Override // amazon.speech.simclient.IConnectionListener
    public void onDisconnect() {
        LOG.i("Simclient connection lost");
        this.mSimClient = null;
    }

    @Override // com.amazon.deecomms.alexa.AlexaInterface
    public boolean releaseCommsFocus() {
        SimClient simClient = this.mSimClient;
        if (simClient != null) {
            int releaseAudioCommunicationsFocus = simClient.releaseAudioCommunicationsFocus("SipClient");
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Comms focus released with result :");
            outline1.append(SimError.getDescriptionFor(releaseAudioCommunicationsFocus));
            commsLogger.i(outline1.toString());
            return releaseAudioCommunicationsFocus == 0;
        }
        LOG.i("No active sim client. Unable to acquire comms focus");
        return false;
    }

    @Override // com.amazon.deecomms.alexa.AlexaInterface
    public boolean sendCommsEvent(@NonNull InCallEvent inCallEvent) {
        return this.mSimEventSender.sendEvent(inCallEvent);
    }
}
