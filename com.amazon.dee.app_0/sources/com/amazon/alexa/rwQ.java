package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.Name;
/* compiled from: ProgressReportRunnable.java */
/* loaded from: classes.dex */
public class rwQ implements Runnable {
    public final AlexaClientEventBus BIo;
    public final oGE zQM;
    public final Name zZm;

    public rwQ(Name name, AlexaClientEventBus alexaClientEventBus, oGE oge) {
        this.zZm = name;
        this.BIo = alexaClientEventBus;
        this.zQM = oge;
    }

    @Override // java.lang.Runnable
    public void run() {
        Vma BIo = this.zQM.BIo();
        BkS bkS = (BkS) BIo;
        this.BIo.zyO(JjI.BIo().zZm(Message.create(Header.builder().setNamespace(AvsApiConstants.AudioPlayer.zZm).setName(this.zZm).build(), lCm.zZm(bkS.zZm, bkS.BIo))).zZm());
    }
}
