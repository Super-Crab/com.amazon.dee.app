package com.amazon.alexa;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.AlexaUserSpeechListenerProxy;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
/* compiled from: AlexaUserSpeechAuthority.java */
/* loaded from: classes.dex */
public class XWf implements Runnable {
    public final /* synthetic */ NXS BIo;
    public final /* synthetic */ AlexaUserSpeechListenerProxy zZm;

    public XWf(NXS nxs, AlexaUserSpeechListenerProxy alexaUserSpeechListenerProxy) {
        this.BIo = nxs;
        this.zZm = alexaUserSpeechListenerProxy;
    }

    @Override // java.lang.Runnable
    public void run() {
        AlexaClientEventBus alexaClientEventBus;
        CDz cDz;
        float f;
        try {
            AlexaUserSpeechListenerProxy alexaUserSpeechListenerProxy = this.zZm;
            f = this.BIo.zyO;
            alexaUserSpeechListenerProxy.onAlexaUserSpeechVolumeChanged(f);
        } catch (RemoteException e) {
            Log.e(NXS.zZm, e.getMessage(), e);
            alexaClientEventBus = this.BIo.zQM;
            cDz = this.BIo.BIo;
            alexaClientEventBus.zyO(xZV.zZm(cDz.BIo((CDz) this.zZm)));
        }
    }
}
