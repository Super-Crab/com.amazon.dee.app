package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.amazon.alexa.client.core.messages.RawStringPayload;
import com.google.gson.JsonParseException;
/* compiled from: CardRendererPayloadTypeMapper.java */
/* loaded from: classes.dex */
public class RmH implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(Name name) throws JsonParseException {
        if (AvsApiConstants.CardRenderer.Directives.RenderCard.zZm.equals(name) || AvsApiConstants.CardRenderer.Directives.PlayerInfo.zZm.equals(name)) {
            return RawStringPayload.class;
        }
        StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
        zZm.append(name.getValue());
        throw new JsonParseException(zZm.toString());
    }
}
