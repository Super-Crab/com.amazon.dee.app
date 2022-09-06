package com.amazon.alexa.client.core.messages;

import com.amazon.alexa.api.AlexaDirective;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.alexa.client.core.messages.Header;
import javax.inject.Singleton;
import org.json.JSONObject;
@Singleton
/* loaded from: classes6.dex */
public class MessageTransformer {
    private static final String TAG = "MessageTransformer";

    public Message convertAlexaEventToMessage(AlexaEvent alexaEvent) {
        AlexaHeader alexaHeader = alexaEvent.getAlexaHeader();
        RawStringPayload create = RawStringPayload.create(alexaEvent.getAlexaPayload().getPayload());
        Header.Builder name = Header.builder().setMessageIdentifier(MessageIdentifier.create(alexaHeader.getMessageId())).setNamespace(Namespace.create(alexaHeader.getNamespace())).setName(Name.create(alexaHeader.getName()));
        if (alexaHeader.getCorrelationToken() != null) {
            name.setCorrelationToken(CorrelationToken.create(alexaHeader.getCorrelationToken()));
        }
        if (alexaHeader.getPayloadVersion() != null) {
            name.setPayloadVersion(PayloadVersion.create(alexaHeader.getPayloadVersion()));
        }
        return Message.create(name.build(), create);
    }

    public AlexaDirective convertMessageToAlexaDirective(Message message) {
        String jSONObject;
        Header header = message.getHeader();
        String value = header.getNamespace().getValue();
        String value2 = header.getName().getValue();
        AlexaHeader.Builder messageId = AlexaHeader.builder().setName(value2).setNamespace(value).setMessageId(header.getMessageIdentifier().getValue());
        if (message.getHeader().getCorrelationToken() != null) {
            messageId.setCorrelationToken(message.getHeader().getCorrelationToken().getValue());
        }
        if (message.getHeader().getPayloadVersion() != null) {
            messageId.setPayloadVersion(message.getHeader().getPayloadVersion().getValue());
        }
        AlexaHeader build = messageId.build();
        if (message.getPayload() instanceof RawStringPayload) {
            jSONObject = ((RawStringPayload) message.getPayload()).getValue();
        } else {
            jSONObject = new JSONObject().toString();
        }
        return new AlexaDirective(build, new AlexaPayload(jSONObject));
    }
}
