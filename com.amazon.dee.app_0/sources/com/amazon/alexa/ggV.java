package com.amazon.alexa;

import com.amazon.alexa.VIX;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
/* compiled from: PlayItemWrapperFactory.java */
/* loaded from: classes.dex */
public class ggV {
    public final ExecutorService zZm;

    public ggV(ExecutorService executorService) {
        this.zZm = executorService;
    }

    public VIX.zQM zZm(kQf kqf, NTV ntv) throws IOException {
        return new VIX.zQM(this.zZm, kqf, ntv);
    }
}
