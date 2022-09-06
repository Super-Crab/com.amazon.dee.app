package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.Namespace;
import java.util.HashSet;
import java.util.Set;
/* compiled from: ExternalComponentStateAuthority.java */
/* loaded from: classes.dex */
public class gZg extends HashSet<Namespace> {
    public final /* synthetic */ YKQ zZm;

    public gZg(YKQ ykq) {
        LrI lrI;
        Set set;
        this.zZm = ykq;
        lrI = this.zZm.BIo;
        addAll(lrI.BIo());
        set = this.zZm.Mlj;
        addAll(set);
    }
}
