package com.amazon.alexa;

import java.util.Iterator;
/* compiled from: PlayQueue.java */
/* loaded from: classes.dex */
public class tPf implements Iterator<kQf> {
    public kQf BIo;
    public final /* synthetic */ Xde zQM;
    public final Iterator<kQf> zZm;

    public tPf(Xde xde) {
        this.zQM = xde;
        this.zZm = this.zQM.BIo.iterator();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.zZm.hasNext();
    }

    @Override // java.util.Iterator
    public kQf next() {
        this.BIo = this.zZm.next();
        return this.BIo;
    }

    @Override // java.util.Iterator
    public void remove() {
        kQf kqf = this.BIo;
        if (kqf != null) {
            this.zQM.zZm(kqf);
            this.zQM.zQM.remove(this.BIo);
            this.zZm.remove();
        }
    }
}
