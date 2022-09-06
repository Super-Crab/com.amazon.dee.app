package com.amazon.alexa.accessory.speech.events.statechange;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.speech.events.AbstractAccessoryEventSender;
import com.amazon.alexa.accessory.speech.events.MessageEventProvider;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.context.MessageHeader;
import com.amazon.alexa.accessory.speechapi.context.MessagePayload;
import com.amazon.alexa.accessory.speechapi.events.MessageEvent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
/* loaded from: classes6.dex */
public class StateChangeEventSender extends AbstractAccessoryEventSender {
    private static final String EVENT_NAME = "AccessoryStateChanged";
    private static final String IOCOMPONENTS_NAME_SPACE = "Alexa.IOComponents";
    private static final String TAG = "speech.StateChangeEventSender";
    private final MessageEventProvider messageEventProvider;

    /* loaded from: classes6.dex */
    private static final class StateChangeMessageEventProvider implements MessageEventProvider {
        private StateChangeMessageEventProvider() {
        }

        @Override // com.amazon.alexa.accessory.speech.events.MessageEventProvider
        public MessageEvent getMessageEvent(@NonNull String str) {
            Logger.d("%s: getMessageEvent for %s", StateChangeEventSender.TAG, StateChangeEventSender.EVENT_NAME);
            return new MessageEvent(MessageHeader.create("Alexa.IOComponents", StateChangeEventSender.EVENT_NAME, UUID.randomUUID().toString()), new MessagePayload(str));
        }
    }

    public StateChangeEventSender(AlexaConnection alexaConnection, SessionSupplier sessionSupplier) {
        super(alexaConnection, sessionSupplier, EVENT_NAME);
        this.messageEventProvider = new StateChangeMessageEventProvider();
    }

    @Override // com.amazon.alexa.accessory.speech.events.AbstractAccessoryEventSender
    protected void recordSendEventFailure(String str) {
        GeneratedOutlineSupport1.outline171(AccessoryMetricsConstants.STATE_CHANGE_EVENT_PUBLISH_STATUS, str, false, null);
    }

    @Override // com.amazon.alexa.accessory.speech.events.AbstractAccessoryEventSender
    protected void recordSendEventSuccess(String str) {
        GeneratedOutlineSupport1.outline171(AccessoryMetricsConstants.STATE_CHANGE_EVENT_PUBLISH_STATUS, str, true, null);
    }

    @Override // com.amazon.alexa.accessory.speechapi.events.AccessoryEventSender
    public void sendEvent(@NonNull String str, @NonNull String str2) {
        Preconditions.notNull(str, "payload");
        Preconditions.notNull(str2, "deviceType");
        ensureAlexaConnectionAndSendEvent(this.messageEventProvider.getMessageEvent(str), str2);
    }
}
