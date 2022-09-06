package com.amazon.alexa;

import android.util.Log;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: EnsureInitializationChain.java */
@Singleton
/* loaded from: classes.dex */
public class pzz implements yDI {
    public static final String zZm = "pzz";
    public final yDI BIo;
    public final WnL zQM;

    @Inject
    public pzz(@Named("REFRESH_INTERNAL_CAPABILITIES_CHAIN") yDI ydi, WnL wnL) {
        this.BIo = ydi;
        this.zQM = wnL;
    }

    @Override // com.amazon.alexa.yDI
    public void teardown() {
        this.BIo.teardown();
    }

    @Override // com.amazon.alexa.yDI
    public void zZm(IHN ihn) {
        if (!(!this.zQM.zZm)) {
            Log.e(zZm, "AVS Connection chain shouldn't be initiated until InitializationCompletedEvent is published");
            ihn.zZm(hlN.NOT_INITIALIZED);
            return;
        }
        this.BIo.zZm(ihn);
    }
}
