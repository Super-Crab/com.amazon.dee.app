package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.EmptyPayload;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.gson.JsonParseException;
/* compiled from: InteractionModelPayloadTypeMapper.java */
/* loaded from: classes.dex */
public class FUN implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(Name name) throws JsonParseException {
        if (AvsApiConstants.InteractionModel.Directives.NewDialogRequest.zZm.equals(name)) {
            return JTD.class;
        }
        if (!AvsApiConstants.InteractionModel.Directives.RequestProcessingStarted.zZm.equals(name) && !AvsApiConstants.InteractionModel.Directives.RequestProcessingCompleted.zZm.equals(name)) {
            StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
            zZm.append(name.getValue());
            throw new JsonParseException(zZm.toString());
        }
        return EmptyPayload.class;
    }
}
