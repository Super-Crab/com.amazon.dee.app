package com.amazon.alexa;

import android.content.Intent;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Name;
import java.util.HashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AVRCPUnnamedMediaPlayer.java */
/* loaded from: classes.dex */
public class FTQ extends HashMap<Name, Intent> {
    public FTQ() {
        put(AvsApiConstants.Alexa.PlaybackController.Directives.Play.zZm, EIa.BIo);
        put(AvsApiConstants.Alexa.PlaybackController.Directives.Pause.zZm, EIa.zQM);
        put(AvsApiConstants.Alexa.PlaybackController.Directives.Previous.zZm, EIa.zyO);
        put(AvsApiConstants.Alexa.PlaybackController.Directives.Next.zZm, EIa.jiA);
    }
}
