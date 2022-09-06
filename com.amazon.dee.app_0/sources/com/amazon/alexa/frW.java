package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.gson.JsonParseException;
/* compiled from: SpeechSynthesizerPayloadTypeMapper.java */
/* loaded from: classes.dex */
public class frW implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(Name name) throws JsonParseException {
        if (AvsApiConstants.SpeechSynthesizer.Directives.Speak.zZm.equals(name)) {
            return DEe.class;
        }
        if (!AvsApiConstants.SpeechSynthesizer.Events.SpeechStarted.zZm.equals(name) && !AvsApiConstants.SpeechSynthesizer.Events.SpeechFinished.zZm.equals(name)) {
            StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
            zZm.append(name.getValue());
            throw new JsonParseException(zZm.toString());
        }
        return yXU.class;
    }
}
