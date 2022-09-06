package com.amazon.alexa;

import com.amazon.alexa.api.ExtendedClient;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: MultiTurnDialogProvider.java */
@Singleton
/* loaded from: classes.dex */
public class QCK {
    public final rJn BIo;
    public final jSO zZm;

    @Inject
    public QCK(jSO jso, rJn rjn) {
        this.zZm = jso;
        this.BIo = rjn;
    }

    public NEe zZm(ExtendedClient extendedClient, Xtl xtl, LjN ljN, eeF eef, boolean z) {
        return new NEe(extendedClient, xtl, ljN, this.zZm, this.BIo, eef, z);
    }
}
