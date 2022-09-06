package com.amazon.alexa;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.AlexaAudioPlaybackListenerProxy;
import com.amazon.alexa.api.AlexaPlaybackState;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
/* compiled from: AlexaAudioPlaybackAuthority.java */
/* loaded from: classes.dex */
public class JTe implements Runnable {
    public final /* synthetic */ LPk BIo;
    public final /* synthetic */ AlexaAudioPlaybackListenerProxy zZm;

    public JTe(LPk lPk, AlexaAudioPlaybackListenerProxy alexaAudioPlaybackListenerProxy) {
        this.BIo = lPk;
        this.zZm = alexaAudioPlaybackListenerProxy;
    }

    @Override // java.lang.Runnable
    public void run() {
        AlexaClientEventBus alexaClientEventBus;
        CDz cDz;
        AlexaPlaybackState alexaPlaybackState;
        try {
            AlexaAudioPlaybackListenerProxy alexaAudioPlaybackListenerProxy = this.zZm;
            alexaPlaybackState = this.BIo.yPL;
            alexaAudioPlaybackListenerProxy.onAudioPlaybackChanged(alexaPlaybackState);
        } catch (RemoteException e) {
            Log.e(LPk.zZm, "Remote exception while updating audio playback listener", e);
            alexaClientEventBus = this.BIo.Qle;
            cDz = this.BIo.BIo;
            alexaClientEventBus.zyO(xZV.zZm(cDz.BIo((CDz) this.zZm)));
        }
    }
}
