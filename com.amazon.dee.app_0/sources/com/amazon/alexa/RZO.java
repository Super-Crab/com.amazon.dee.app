package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.externalmediaplayer.mediacontroller.payload.AlexaMediaPayload;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.google.gson.JsonParseException;
/* compiled from: AlexaPlaybackControllerPayloadTypeMapper.java */
/* loaded from: classes.dex */
public class RZO implements GwO {
    @Override // com.amazon.alexa.GwO
    public Class<? extends Payload> zZm(Name name) throws JsonParseException {
        if (!AvsApiConstants.Alexa.PlaybackController.Directives.Play.zZm.equals(name) && !AvsApiConstants.Alexa.PlaybackController.Directives.Stop.zZm.equals(name) && !AvsApiConstants.Alexa.PlaybackController.Directives.Pause.zZm.equals(name) && !AvsApiConstants.Alexa.PlaybackController.Directives.StartOver.zZm.equals(name) && !AvsApiConstants.Alexa.PlaybackController.Directives.Previous.zZm.equals(name) && !AvsApiConstants.Alexa.PlaybackController.Directives.Next.zZm.equals(name) && !AvsApiConstants.Alexa.PlaybackController.Directives.Rewind.zZm.equals(name) && !AvsApiConstants.Alexa.PlaybackController.Directives.FastForward.zZm.equals(name)) {
            StringBuilder zZm = C0179Pya.zZm("Unknown name: ");
            zZm.append(name.getValue());
            throw new JsonParseException(zZm.toString());
        }
        return AlexaMediaPayload.class;
    }
}
