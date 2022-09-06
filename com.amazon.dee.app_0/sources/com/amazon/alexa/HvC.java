package com.amazon.alexa;

import com.amazon.alexa.api.AlexaAttentionSystemListener;
import com.amazon.alexa.api.AlexaState;
/* compiled from: AlexaStateAuthority.java */
/* loaded from: classes.dex */
public class HvC implements Runnable {
    public final /* synthetic */ AlexaState BIo;
    public final /* synthetic */ vkx zQM;
    public final /* synthetic */ AlexaAttentionSystemListener zZm;

    public HvC(vkx vkxVar, AlexaAttentionSystemListener alexaAttentionSystemListener, AlexaState alexaState) {
        this.zQM = vkxVar;
        this.zZm = alexaAttentionSystemListener;
        this.BIo = alexaState;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.zZm.onAlexaStateChanged(this.BIo, vkx.zQM(this.zQM));
    }
}
