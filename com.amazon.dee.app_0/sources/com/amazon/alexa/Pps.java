package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.gson.JsonParseException;
/* compiled from: IOComponentsPayloadTypeMapper.java */
/* loaded from: classes.dex */
public class Pps implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(@NonNull Name name) throws JsonParseException {
        if (AvsApiConstants.Alexa.IOComponents.ComponentStates.IOComponentStates.zZm.equals(name)) {
            return Agi.class;
        }
        if (AvsApiConstants.Alexa.IOComponents.ComponentStates.TrustedStates.zZm.equals(name)) {
            return pUe.class;
        }
        StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
        zZm.append(name.getValue());
        throw new JsonParseException(zZm.toString());
    }
}
