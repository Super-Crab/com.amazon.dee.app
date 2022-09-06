package com.amazon.deecomms.calling.datachannel.handler;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.calling.service.DataChannelDTO;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.controller.CallDowngradedObservable;
import com.amazon.deecomms.calling.datachannel.AmcIncallDataChannelApplication;
import com.amazon.deecomms.calling.datachannel.AmcIncallDataChannelEventListenerImpl;
import com.amazon.deecomms.calling.datachannel.CallingEventName;
import com.amazon.deecomms.calling.datachannel.Capability;
import com.amazon.deecomms.calling.datachannel.CommsDataChannelEvent;
import com.amazon.deecomms.calling.datachannel.CommsDataChannelHeader;
import com.amazon.deecomms.calling.datachannel.Namespace;
import com.amazon.deecomms.calling.datachannel.PayloadVersion;
import com.amazon.deecomms.calling.datachannel.payload.CallSetupEvent;
import com.amazon.deecomms.calling.datachannel.payload.PipEvent;
import com.amazon.deecomms.calling.enums.DataChannelLabel;
import com.amazon.deecomms.calling.incallexperiences.effects.ReactionsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads.AckEvent;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads.SepiaEventName;
import com.amazon.deecomms.calling.ui.PipVisibilityObservable;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
/* loaded from: classes12.dex */
public class CallingDataChannelEventHandler implements AmcIncallDataChannelApplication {
    private static final String APPLICATION_NAME = "Calling";
    private static final String AUDIO = "audio";
    private static final CommsLogger LOG = CommsLogger.getLogger(ReactionsMenuPresenter.class);
    final CallDowngradedObservable callDowngradedObservable;
    final CapabilitiesManager capabilitiesManager;
    final AmcIncallDataChannelEventListenerImpl dataChannelEventListener;
    final Gson gson;
    private boolean isLocalPipShowing;
    final PipVisibilityObservable pipVisibilityObservable;
    final SipClientState sipClientState;

    /* renamed from: com.amazon.deecomms.calling.datachannel.handler.CallingDataChannelEventHandler$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$calling$datachannel$CallingEventName = new int[CallingEventName.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$calling$datachannel$CallingEventName[CallingEventName.CALL_SETUP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$datachannel$CallingEventName[CallingEventName.PIPACTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public CallingDataChannelEventHandler(@NonNull CallDowngradedObservable callDowngradedObservable, @NonNull PipVisibilityObservable pipVisibilityObservable, @NonNull CapabilitiesManager capabilitiesManager, @NonNull SipClientState sipClientState) {
        this(callDowngradedObservable, pipVisibilityObservable, capabilitiesManager, sipClientState, new GsonBuilder().setVersion(PayloadVersion.LATEST.getValue()).create());
    }

    private void handleCallSetupEvent(CallSetupEvent callSetupEvent) {
        if (callSetupEvent.getCallMode() == null || !callSetupEvent.getCallMode().equals("audio")) {
            return;
        }
        this.callDowngradedObservable.onCallDowngradeStateUpdated(true);
    }

    private void sendDataChannelEvent(@NonNull CommsDataChannelHeader commsDataChannelHeader, JsonElement jsonElement) {
        String json = this.gson.toJson(CommsDataChannelEvent.builder().withHeader(commsDataChannelHeader).withPayload(jsonElement).build());
        this.sipClientState.getCurrentActiveCall().sendData(new DataChannelDTO(DataChannelLabel.IN_CALL_APPLICATION.getName(), json.getBytes(StandardCharsets.UTF_8), false));
        GeneratedOutlineSupport.outline4(" Sent event through DataChannel data=", json, LOG);
    }

    @Override // com.amazon.deecomms.calling.datachannel.AmcIncallDataChannelApplication
    public String getApplicationName() {
        return APPLICATION_NAME;
    }

    public boolean isLocalPipShowing() {
        return this.isLocalPipShowing;
    }

    @Override // com.amazon.deecomms.calling.datachannel.AmcIncallDataChannelApplication
    public void onDataChannelEventReceived(CommsDataChannelEvent commsDataChannelEvent) {
        String name = (commsDataChannelEvent.getHeader() == null || commsDataChannelEvent.getHeader().getName() == null) ? "" : commsDataChannelEvent.getHeader().getName();
        LOG.i("Calling data channel handler received new message");
        CallingEventName lookupByEventName = CallingEventName.lookupByEventName(name.toUpperCase(Locale.getDefault()));
        if (lookupByEventName != null) {
            int ordinal = lookupByEventName.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    LOG.e("Unknown datachannel event received");
                } else {
                    handleCallSetupEvent((CallSetupEvent) this.gson.fromJson(commsDataChannelEvent.getPayload(), (Class<Object>) CallSetupEvent.class));
                }
            } else if (!this.capabilitiesManager.isMPUDynamicPipEnabled()) {
            } else {
                PipEvent pipEvent = (PipEvent) this.gson.fromJson(commsDataChannelEvent.getPayload(), (Class<Object>) PipEvent.class);
                if (pipEvent.getVisibility() == null || pipEvent.getRequestId() == null) {
                    return;
                }
                sendAcknowledgment(pipEvent.getRequestId());
                this.isLocalPipShowing = pipEvent.getVisibility().equals(PipEvent.Visibility.VISIBLE.name());
                this.pipVisibilityObservable.onPipVisibilityUpdated(pipEvent.getVisibility());
            }
        }
    }

    void sendAcknowledgment(@NonNull String str) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("Sending PIP ACK event for requestID" + str);
        sendDataChannelEvent(CommsDataChannelHeader.builder().withNamespace(Namespace.IN_CALL_PIP).withInstance(APPLICATION_NAME).withCapability(Capability.SCREEN_EVENTS).withName(SepiaEventName.ACK_STATUS.toString()).build(), this.gson.toJsonTree(AckEvent.builder().requestId(str).build()));
    }

    public void shutdown() {
        this.dataChannelEventListener.unregisterDataChannelEventListener();
    }

    @VisibleForTesting
    CallingDataChannelEventHandler(CallDowngradedObservable callDowngradedObservable, PipVisibilityObservable pipVisibilityObservable, CapabilitiesManager capabilitiesManager, SipClientState sipClientState, Gson gson) {
        this.isLocalPipShowing = false;
        this.callDowngradedObservable = callDowngradedObservable;
        this.pipVisibilityObservable = pipVisibilityObservable;
        this.capabilitiesManager = capabilitiesManager;
        this.sipClientState = sipClientState;
        this.gson = gson;
        this.dataChannelEventListener = AmcIncallDataChannelEventListenerImpl.getInstance(sipClientState.getCurrentActiveCall());
        this.dataChannelEventListener.registerApplication(this);
    }
}
