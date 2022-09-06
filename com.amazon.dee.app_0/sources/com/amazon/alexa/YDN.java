package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.gson.JsonParseException;
/* compiled from: AlexaSeekControllerPayloadTypeMapper.java */
/* loaded from: classes.dex */
public class YDN implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(Name name) throws JsonParseException {
        if (AvsApiConstants.Alexa.SeekController.Directives.AdjustSeekPosition.zZm.equals(name)) {
            return STS.class;
        }
        if (AvsApiConstants.Alexa.SeekController.Directives.SetSeekPosition.zZm.equals(name)) {
            return ErD.class;
        }
        StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
        zZm.append(name.getValue());
        throw new JsonParseException(zZm.toString());
    }
}
