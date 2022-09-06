package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.EmptyPayload;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.gson.JsonParseException;
/* compiled from: SpeechRecognizerPayloadTypeMapper.java */
/* loaded from: classes.dex */
public class zEd implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(Name name) throws JsonParseException {
        if (AvsApiConstants.SpeechRecognizer.Events.Recognize.zZm.equals(name)) {
            return AbstractC0183Xff.class;
        }
        if (AvsApiConstants.SpeechRecognizer.Directives.StopCapture.zZm.equals(name)) {
            return EmptyPayload.class;
        }
        if (AvsApiConstants.SpeechRecognizer.Directives.ExpectSpeech.zZm.equals(name)) {
            return nNv.class;
        }
        if (AvsApiConstants.SpeechRecognizer.Directives.SetEndOfSpeechOffset.zZm.equals(name)) {
            return NYz.class;
        }
        StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
        zZm.append(name.getValue());
        throw new JsonParseException(zZm.toString());
    }
}
