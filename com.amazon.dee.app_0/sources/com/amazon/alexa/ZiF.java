package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.EmptyPayload;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.gson.JsonParseException;
/* compiled from: SystemPayloadTypeMapper.java */
/* loaded from: classes.dex */
public class ZiF implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(Name name) throws JsonParseException {
        if (AvsApiConstants.System.Exception.zZm.equals(name)) {
            return pTS.class;
        }
        if (AvsApiConstants.System.Directives.SetEndpoint.zZm.equals(name)) {
            return mPf.class;
        }
        if (AvsApiConstants.System.Directives.ResetUserInactivity.zZm.equals(name)) {
            return EmptyPayload.class;
        }
        StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
        zZm.append(name.getValue());
        throw new JsonParseException(zZm.toString());
    }
}
