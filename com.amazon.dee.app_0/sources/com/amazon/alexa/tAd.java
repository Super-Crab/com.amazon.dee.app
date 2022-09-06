package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.gson.JsonParseException;
/* compiled from: DisplayWindowStatePayloadTypeMapper.java */
/* loaded from: classes.dex */
public class tAd implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(@NonNull Name name) throws JsonParseException {
        if (AvsApiConstants.Alexa.Display.Window.ComponentStates.WindowState.zZm.equals(name)) {
            return Bsa.class;
        }
        StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
        zZm.append(name.getValue());
        throw new JsonParseException(zZm.toString());
    }
}
