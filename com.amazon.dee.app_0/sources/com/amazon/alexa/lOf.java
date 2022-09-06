package com.amazon.alexa;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.AlexaStateListenerProxy;
import com.amazon.alexa.api.ExtendedClient;
/* compiled from: AlexaStateAuthority.java */
/* loaded from: classes.dex */
public class lOf implements Runnable {
    public final /* synthetic */ ExtendedClient BIo;
    public final /* synthetic */ vkx zQM;
    public final /* synthetic */ AlexaStateListenerProxy zZm;

    public lOf(vkx vkxVar, AlexaStateListenerProxy alexaStateListenerProxy, ExtendedClient extendedClient) {
        this.zQM = vkxVar;
        this.zZm = alexaStateListenerProxy;
        this.BIo = extendedClient;
    }

    @Override // java.lang.Runnable
    public void run() {
        wSq wsq;
        try {
            AlexaStateListenerProxy alexaStateListenerProxy = this.zZm;
            wsq = this.zQM.JTe;
            alexaStateListenerProxy.onAlexaStateChanged(wsq.zZm());
        } catch (RemoteException e) {
            Log.e(vkx.zZm, "Remote exception while registering alexa state listener", e);
            this.zQM.BIo.zyO(xZV.zZm(this.BIo));
        }
    }
}
