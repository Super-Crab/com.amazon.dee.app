package com.amazon.deecomms.drivemode.cards;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.drivemode.api.DriveModeCard;
import com.amazon.alexa.drivemode.api.SingleIconCard;
import com.amazon.alexa.voice.tta.Constants;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.alexa.voice.IEventSender;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.nativemodules.CommsEventEmitterBridge;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Named;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class CallingCard extends SingleIconCard {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallingCard.class);
    private AlexaServicesConnection mAlexaServicesConnection;
    private AlexaServicesConnectionListener mAlexaServicesConnectionListener;
    private CapabilitiesManager mCapabilitiesManager;
    private IEventSender mEventSender;
    private AtomicBoolean mIsCallEventPending;

    /* loaded from: classes12.dex */
    public class AlexaServicesConnectionListener implements AlexaServicesConnection.ConnectionListener {
        public AlexaServicesConnectionListener() {
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            if (CallingCard.this.mIsCallEventPending.get()) {
                CallingCard.this.sendCallingEvent();
            }
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
            if (CallingCard.this.mIsCallEventPending.get()) {
                CommsEventEmitterBridge.sendDriveModeCallingCardClickedEvent();
                CallingCard.this.mIsCallEventPending.set(false);
            }
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
        }
    }

    public CallingCard(@NonNull Context context, @NonNull IEventSender iEventSender, @NonNull @Named("commsAlexaServiceConnection") AlexaServicesConnection alexaServicesConnection, @NonNull CapabilitiesManager capabilitiesManager) {
        super(context.getResources().getString(R.string.calling_card_title), "", ContextCompat.getDrawable(context, R.drawable.ic_call_24dp));
        this.mIsCallEventPending = new AtomicBoolean(false);
        this.mEventSender = iEventSender;
        this.mCapabilitiesManager = capabilitiesManager;
        this.mAlexaServicesConnection = alexaServicesConnection;
        this.mAlexaServicesConnectionListener = new AlexaServicesConnectionListener();
        this.mAlexaServicesConnection.registerListener(this.mAlexaServicesConnectionListener);
    }

    private AlexaEvent getCallEvent() {
        AlexaHeader create = AlexaHeader.create(Constants.Namespaces.TEXT, "TextMessage");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("textMessage", "Make a call");
            jSONObject.put("processMode", "tokenized");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new AlexaEvent(create, new AlexaPayload(jSONObject.toString()));
    }

    private void performCallOperation() {
        if (this.mCapabilitiesManager.tapToCallEnabled()) {
            if (this.mAlexaServicesConnection.isConnected()) {
                sendCallingEvent();
                return;
            }
            this.mIsCallEventPending.set(true);
            this.mAlexaServicesConnection.connect();
            return;
        }
        CommsEventEmitterBridge.sendDriveModeCallingCardClickedEvent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCallingEvent() {
        this.mEventSender.sendEvent(getCallEvent(), false);
        this.mIsCallEventPending.set(false);
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public DriveModeCard.CardDomain getCardDomain() {
        return DriveModeCard.CardDomain.COMMS;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public int getCardId() {
        return 1;
    }

    @Override // com.amazon.alexa.drivemode.api.SingleIconCard
    public void onCardClicked() {
        LOG.i("[drive mode] calling card clicked");
        MetricsHelper.recordCounterMetricOperational(MetricKeys.DRIVE_MODE_CALLING_CARD_CLICK, 1.0d);
        performCallOperation();
    }
}
