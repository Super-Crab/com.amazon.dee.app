package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.google.gson.Gson;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: SpeechmarkId3Parser.java */
@Singleton
/* loaded from: classes.dex */
public class fUD implements xXb {
    public static final String zZm = xXb.class.getSimpleName();
    public final byte[] BIo = new byte[10];
    public final AlexaClientEventBus Qle;
    public final Gson jiA;
    public final Eaz zQM;
    public final DJX zyO;

    /* compiled from: SpeechmarkId3Parser.java */
    /* loaded from: classes.dex */
    static class zZm {
        public static final byte[] zZm = {84, 88, 88, 88};
        public static final byte[] BIo = {100, 97, 116, 97};
    }

    @Inject
    public fUD(Eaz eaz, DJX djx, Gson gson, AlexaClientEventBus alexaClientEventBus) {
        this.zQM = eaz;
        this.zyO = djx;
        this.jiA = gson;
        this.Qle = alexaClientEventBus;
    }
}
