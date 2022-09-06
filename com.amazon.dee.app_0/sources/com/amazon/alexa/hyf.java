package com.amazon.alexa;

import com.amazon.alexa.BOa;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;
/* compiled from: ExternalAudioWakeWordDetector.java */
/* loaded from: classes.dex */
public class hyf implements Runnable {
    public final /* synthetic */ uqp BIo;
    public final /* synthetic */ Sjd zZm;

    public hyf(uqp uqpVar, Sjd sjd) {
        this.BIo = uqpVar;
        this.zZm = sjd;
    }

    @Override // java.lang.Runnable
    public void run() {
        ExecutorService executorService;
        AlexaClientEventBus alexaClientEventBus;
        ExecutorService executorService2;
        executorService = this.BIo.Mlj;
        if (executorService != null) {
            executorService2 = this.BIo.Mlj;
            executorService2.shutdownNow();
            this.BIo.Mlj = null;
        }
        alexaClientEventBus = this.BIo.zyO;
        alexaClientEventBus.zyO(new sGI());
        ((BOa.BIo) this.zZm).zZm(new TimeoutException("Wake word validation has timed out"));
    }
}
