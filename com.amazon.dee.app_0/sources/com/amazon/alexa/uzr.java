package com.amazon.alexa;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.AlexaState;
import com.amazon.alexa.api.AlexaStateListenerProxy;
import com.amazon.alexa.api.ExtendedClient;
/* compiled from: AlexaStateAuthority.java */
/* loaded from: classes.dex */
public class uzr implements Runnable {
    public final /* synthetic */ AlexaState BIo;
    public final /* synthetic */ vkx zQM;
    public final /* synthetic */ AlexaStateListenerProxy zZm;

    public uzr(vkx vkxVar, AlexaStateListenerProxy alexaStateListenerProxy, AlexaState alexaState) {
        this.zQM = vkxVar;
        this.zZm = alexaStateListenerProxy;
        this.BIo = alexaState;
    }

    @Override // java.lang.Runnable
    public void run() {
        CDz cDz;
        try {
            this.zZm.onAlexaStateChanged(this.BIo);
        } catch (RemoteException e) {
            cDz = this.zQM.zyO;
            ExtendedClient BIo = cDz.BIo((CDz) this.zZm);
            String str = vkx.zZm;
            StringBuilder zZm = C0179Pya.zZm("RemoteException for client: ");
            zZm.append(BIo == null ? "client is null" : BIo.getId());
            zZm.append(". Disconnecting");
            Log.e(str, zZm.toString(), e);
            this.zQM.BIo.zyO(xZV.zZm(BIo));
        }
    }
}
