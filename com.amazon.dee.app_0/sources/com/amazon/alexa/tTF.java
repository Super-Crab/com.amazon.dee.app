package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.EmptyPayload;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.gson.JsonParseException;
/* compiled from: NavigationPayloadTypeMapper.java */
/* loaded from: classes.dex */
public class tTF implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(Name name) throws JsonParseException {
        if (AvsApiConstants.Navigation.Directives.SetDestination.zZm.equals(name)) {
            return Alc.class;
        }
        if (AvsApiConstants.Navigation.Directives.CancelNavigation.zZm.equals(name)) {
            return EmptyPayload.class;
        }
        StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
        zZm.append(name.getValue());
        throw new JsonParseException(zZm.toString());
    }
}
