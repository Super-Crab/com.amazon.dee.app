package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.C0235ujQ;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* compiled from: Channel.java */
/* loaded from: classes.dex */
public abstract class KPH<T extends C0235ujQ> {
    public final Set<zZm> BIo = Collections.synchronizedSet(new HashSet());
    public final Set<dnp> zQM;
    public final List<T> zZm;

    /* compiled from: Channel.java */
    /* loaded from: classes.dex */
    interface zZm {
    }

    public KPH(Set<dnp> set, List<T> list) {
        this.zQM = set;
        this.zZm = list;
    }

    public synchronized Set<T> BIo() {
        return new HashSet(this.zZm);
    }

    public synchronized void JTe() {
        for (T t : this.zZm) {
            if (!t.BIo()) {
                t.zQM();
            }
        }
    }

    @Nullable
    public T Qle() {
        for (T t : this.zZm) {
            if (!this.zQM.contains(t.zZm())) {
                return t;
            }
        }
        return null;
    }

    public synchronized T jiA() {
        return this.zZm.get(0);
    }

    public synchronized void zQM(T t) {
        this.zZm.add(0, t);
        zZm(!this.zQM.contains(t.zZm()));
    }

    public synchronized void zZm(T t) {
        this.zZm.add(t);
        zZm(!this.zQM.contains(t.zZm()));
    }

    public abstract void zZm(boolean z);

    public synchronized T zyO(T t) {
        T remove;
        boolean z = false;
        remove = this.zZm.remove(0);
        this.zZm.add(0, t);
        if (!this.zQM.contains(t.zZm()) || !BIo((KPH<T>) remove)) {
            z = true;
        }
        zZm(z);
        return remove;
    }

    @Nullable
    public final T BIo(IYE iye) {
        for (T t : this.zZm) {
            if (iye.equals(t.BIo.zZm())) {
                return t;
            }
        }
        return null;
    }

    public synchronized void zQM(IYE iye) {
        T BIo = BIo(iye);
        boolean z = true;
        if (BIo != null && this.zZm.remove(BIo)) {
            if (BIo((KPH<T>) BIo)) {
                z = false;
            }
            zZm(z);
        }
    }

    public synchronized boolean zZm(IYE iye) {
        return BIo(iye) != null;
    }

    public final boolean BIo(T t) {
        return this.zQM.contains(t.zZm());
    }

    public synchronized void zZm() {
        if (!zyO()) {
            boolean zQM = zQM();
            this.zZm.clear();
            zZm(zQM);
        }
    }

    public synchronized boolean zyO() {
        return this.zZm.isEmpty();
    }

    public boolean zQM() {
        return Qle() != null;
    }
}
