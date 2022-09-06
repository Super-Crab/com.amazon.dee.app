package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.gson.JsonParseException;
/* compiled from: SpeakerPayloadTypeMapper.java */
/* loaded from: classes.dex */
public class KED implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(Name name) throws JsonParseException {
        if (AvsApiConstants.Speaker.Directives.SetMute.zZm.equals(name)) {
            return RhW.class;
        }
        if (AvsApiConstants.Speaker.Directives.SetVolume.zZm.equals(name)) {
            return tRN.class;
        }
        if (AvsApiConstants.Speaker.Directives.AdjustVolume.zZm.equals(name)) {
            return sci.class;
        }
        StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
        zZm.append(name.getValue());
        throw new JsonParseException(zZm.toString());
    }
}
