package com.amazon.alexa;
/* compiled from: ChannelAlwaysReplacePolicy.java */
/* loaded from: classes.dex */
public class hxU implements Sga {
    public static hxU zZm() {
        return new hxU();
    }

    @Override // com.amazon.alexa.Sga
    public void zZm(KPH kph, C0235ujQ c0235ujQ) {
        if (kph.zyO()) {
            kph.zQM((KPH) c0235ujQ);
        } else {
            kph.zyO(c0235ujQ).zQM();
        }
    }

    @Override // com.amazon.alexa.Sga
    public void zZm(KPH kph, IYE iye) {
        kph.zQM(iye);
    }
}
