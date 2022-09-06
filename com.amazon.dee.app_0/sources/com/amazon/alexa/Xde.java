package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/* compiled from: PlayQueue.java */
/* loaded from: classes.dex */
public class Xde {
    public static final String zZm = "Xde";
    public final Queue<kQf> BIo = new LinkedList();
    public final List<kQf> zQM = new LinkedList();
    public final zZm zyO;

    /* compiled from: PlayQueue.java */
    /* loaded from: classes.dex */
    public interface zZm {
    }

    public Xde(zZm zzm) {
        this.zyO = zzm;
    }

    public synchronized boolean Qle() {
        return this.BIo.isEmpty();
    }

    public synchronized void jiA(kQf kqf) {
        C0179Pya.zZm("Removing ", (Object) kqf);
        if (this.BIo.contains(kqf) || this.zQM.contains(kqf)) {
            zZm(kqf);
        }
        this.BIo.remove(kqf);
        this.zQM.remove(kqf);
    }

    public synchronized boolean zQM(kQf kqf) {
        return this.BIo.contains(kqf);
    }

    public synchronized boolean zyO(kQf kqf) {
        boolean z;
        z = false;
        if (this.zQM.size() == 0) {
            z = true;
        }
        this.zQM.add(kqf);
        return z;
    }

    public synchronized boolean BIo(kQf kqf) {
        if (!this.zQM.contains(kqf)) {
            Log.e(zZm, "Attempting to enqueue a play item that was never prepared");
            return false;
        } else if (this.zQM.size() <= this.BIo.size()) {
            Log.e(zZm, "Mismatch between number of play items prepared and enqueued");
            return false;
        } else if (!this.zQM.get(this.BIo.size()).equals(kqf)) {
            Log.e(zZm, "Attempting to enqueue a play item that was prepared in the incorrect order");
            return false;
        } else {
            return this.BIo.offer(kqf);
        }
    }

    public synchronized kQf zQM() {
        return this.BIo.peek();
    }

    public synchronized void zZm() {
        Iterator it2 = this.BIo.iterator();
        while (it2.hasNext()) {
            kQf kqf = (kQf) it2.next();
            if (kqf != null) {
                zZm(kqf);
                this.zQM.remove(kqf);
                it2.remove();
            }
        }
        Iterator<kQf> it3 = this.zQM.iterator();
        while (it3.hasNext()) {
            zZm(it3.next());
            it3.remove();
        }
    }

    public synchronized kQf zyO() {
        if (this.zQM.size() > 1) {
            return this.zQM.get(1);
        }
        return null;
    }

    public synchronized kQf jiA() {
        if (this.zQM.isEmpty()) {
            return null;
        }
        return this.zQM.get(0);
    }

    public synchronized Iterator<kQf> BIo() {
        tPf tpf;
        tpf = new tPf(this);
        if (tpf.hasNext()) {
            tpf.next();
        }
        return tpf;
    }

    public final void zZm(@Nullable kQf kqf) {
        if (kqf != null) {
            ((Bha) this.zyO).uzr(kqf);
        }
    }
}
