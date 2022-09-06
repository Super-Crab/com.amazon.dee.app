package com.amazon.alexa;

import com.amazon.alexa.api.AlexaAttentionSystemListener;
/* compiled from: AlexaStateAuthority.java */
/* loaded from: classes.dex */
public class dMe implements Runnable {
    public final /* synthetic */ vkx BIo;
    public final /* synthetic */ AlexaAttentionSystemListener zZm;

    public dMe(vkx vkxVar, AlexaAttentionSystemListener alexaAttentionSystemListener) {
        this.BIo = vkxVar;
        this.zZm = alexaAttentionSystemListener;
    }

    @Override // java.lang.Runnable
    public void run() {
        wSq wsq;
        AlexaAttentionSystemListener alexaAttentionSystemListener = this.zZm;
        wsq = this.BIo.JTe;
        alexaAttentionSystemListener.onAlexaStateChanged(wsq.zZm(), vkx.zQM(this.BIo));
    }
}
