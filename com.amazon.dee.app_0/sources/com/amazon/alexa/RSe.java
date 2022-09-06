package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
/* compiled from: ExternalMediaPlayerCapabilityAgent.java */
/* loaded from: classes.dex */
public class RSe implements Runnable {
    public final /* synthetic */ String BIo;
    public final /* synthetic */ vQe zQM;
    public final /* synthetic */ String zZm;
    public final /* synthetic */ DJw zyO;

    public RSe(DJw dJw, String str, String str2, vQe vqe) {
        this.zyO = dJw;
        this.zZm = str;
        this.BIo = str2;
        this.zQM = vqe;
    }

    @Override // java.lang.Runnable
    public void run() {
        AlexaClientEventBus alexaClientEventBus;
        Log.e(DJw.zZm, String.format("Sending out the timeout error for key = %s, directive = %s ", this.zZm, this.BIo));
        alexaClientEventBus = this.zyO.BIo;
        alexaClientEventBus.zyO(FXk.zZm(this.zQM, FGE.zQM));
    }
}
