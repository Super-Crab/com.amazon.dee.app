package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.amazon.alexa.client.core.messages.RawStringPayload;
import com.google.gson.JsonParseException;
/* compiled from: ExternalMediaPlayerPayloadTypeMapper.java */
/* loaded from: classes.dex */
public class FcM implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(Name name) throws JsonParseException {
        if (AvsApiConstants.ExternalMediaPlayer.Directives.Play.zZm.equals(name)) {
            return uWW.class;
        }
        if (AvsApiConstants.ExternalMediaPlayer.Directives.AuthorizeDiscoveredPlayers.zZm.equals(name)) {
            return Ims.class;
        }
        if (!AvsApiConstants.ExternalMediaPlayer.Directives.Login.zZm.equals(name) && !AvsApiConstants.ExternalMediaPlayer.Directives.Logout.zZm.equals(name)) {
            StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
            zZm.append(name.getValue());
            throw new JsonParseException(zZm.toString());
        }
        return RawStringPayload.class;
    }
}
