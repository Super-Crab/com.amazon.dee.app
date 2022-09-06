package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.EmptyPayload;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.gson.JsonParseException;
/* compiled from: AudioPlayerPayloadTypeMapper.java */
/* loaded from: classes.dex */
public class nhU implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(Name name) throws JsonParseException {
        if (AvsApiConstants.AudioPlayer.Directives.Play.zZm.equals(name)) {
            return xik.class;
        }
        if (AvsApiConstants.AudioPlayer.Directives.ClearQueue.zZm.equals(name)) {
            return UVo.class;
        }
        if (AvsApiConstants.AudioPlayer.Directives.Stop.zZm.equals(name)) {
            return EmptyPayload.class;
        }
        StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
        zZm.append(name.getValue());
        throw new JsonParseException(zZm.toString());
    }
}
