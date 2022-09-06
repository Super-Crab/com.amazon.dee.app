package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Name;
import java.util.HashMap;
/* compiled from: AVRCPUnnamedMediaPlayer.java */
/* loaded from: classes.dex */
class ZuU extends HashMap<Name, rjL> {
    public ZuU() {
        put(AvsApiConstants.Alexa.PlaybackController.Directives.Play.zZm, rjL.Play);
        put(AvsApiConstants.Alexa.PlaybackController.Directives.Pause.zZm, rjL.Pause);
        put(AvsApiConstants.Alexa.PlaybackController.Directives.Previous.zZm, rjL.Previous);
        put(AvsApiConstants.Alexa.PlaybackController.Directives.Next.zZm, rjL.Next);
    }
}
