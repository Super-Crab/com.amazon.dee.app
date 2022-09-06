package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.externalmediaplayer.mediacontroller.payload.AlexaMediaPayload;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.gson.JsonParseException;
/* compiled from: AlexaFavoritesControllerPayloadTypeMapper.java */
/* loaded from: classes.dex */
public class xzy implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(Name name) throws JsonParseException {
        if (!AvsApiConstants.Alexa.FavoritesController.Directives.Favorite.zZm.equals(name) && !AvsApiConstants.Alexa.FavoritesController.Directives.Unfavorite.zZm.equals(name)) {
            StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
            zZm.append(name.getValue());
            throw new JsonParseException(zZm.toString());
        }
        return AlexaMediaPayload.class;
    }
}
