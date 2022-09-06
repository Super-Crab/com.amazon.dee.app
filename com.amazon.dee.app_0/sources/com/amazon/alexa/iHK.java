package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.MessageMetadata;
import com.amazon.alexa.utils.TimeProvider;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: SpeechInteractionFactory.java */
@Singleton
/* loaded from: classes.dex */
public class iHK {
    public final vkx BIo;
    public final ILi jiA;
    public final Wyh zQM;
    public final shl zZm;
    public final TimeProvider zyO;

    @Inject
    public iHK(shl shlVar, vkx vkxVar, Wyh wyh, TimeProvider timeProvider, ILi iLi) {
        this.zZm = shlVar;
        this.BIo = vkxVar;
        this.zQM = wyh;
        this.zyO = timeProvider;
        this.jiA = iLi;
    }

    public WvJ zZm(dDK ddk, AlexaClientEventBus alexaClientEventBus, QeM qeM, MessageMetadata messageMetadata, boolean z) {
        if (z) {
            vkx vkxVar = this.BIo;
            shl shlVar = this.zZm;
            TimeProvider timeProvider = this.zyO;
            Wyh wyh = this.zQM;
            return new Xew(alexaClientEventBus, vkxVar, qeM, shlVar, timeProvider, wyh, wyh.BIo(), messageMetadata);
        }
        vkx vkxVar2 = this.BIo;
        shl shlVar2 = this.zZm;
        TimeProvider timeProvider2 = this.zyO;
        Wyh wyh2 = this.zQM;
        return new aDU(ddk, alexaClientEventBus, qeM, vkxVar2, shlVar2, timeProvider2, wyh2, wyh2.BIo(), messageMetadata, this.jiA);
    }
}
