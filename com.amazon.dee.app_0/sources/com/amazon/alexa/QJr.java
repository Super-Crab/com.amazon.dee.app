package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.KPH;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArraySet;
/* compiled from: Channels.java */
/* loaded from: classes.dex */
public class QJr {
    public static final String zZm = "QJr";
    public final Map<aVo, Tzd> BIo = new TreeMap();
    public final Map<Tzd, Sga> zQM = new HashMap();
    public final Set<BIo> zyO = new CopyOnWriteArraySet();

    /* compiled from: Channels.java */
    /* loaded from: classes.dex */
    public interface BIo {
        void zZm();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Channels.java */
    /* loaded from: classes.dex */
    public class zZm implements KPH.zZm {
        public /* synthetic */ zZm(lfF lff) {
        }
    }

    public synchronized void BIo() {
        boolean z = false;
        for (Tzd tzd : this.BIo.values()) {
            if (!tzd.zyO() && !z) {
                z = true;
                tzd.yPL();
            } else {
                tzd.LPk();
            }
        }
    }

    public synchronized void HvC() {
        for (Tzd tzd : this.BIo.values()) {
            if (aVo.CONTENT.equals(tzd.jiA)) {
                tzd.JTe();
            }
        }
    }

    public synchronized Map<aVo, aNh> JTe() {
        HashMap hashMap;
        hashMap = new HashMap();
        for (aVo avo : this.BIo.keySet()) {
            aNh zzR = this.BIo.get(avo).zzR();
            if (zzR != null) {
                hashMap.put(avo, zzR);
            }
        }
        return hashMap;
    }

    public synchronized boolean LPk() {
        boolean z;
        Iterator<Tzd> it2 = this.BIo.values().iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            } else if (it2.next().lOf()) {
                z = true;
                break;
            }
        }
        return z;
    }

    public final void Mlj() {
        synchronized (this.zyO) {
            for (BIo bIo : this.zyO) {
                bIo.zZm();
            }
        }
    }

    public synchronized Set<tYL> Qle() {
        HashSet hashSet;
        hashSet = new HashSet();
        for (Tzd tzd : this.BIo.values()) {
            hashSet.addAll(tzd.BIo());
        }
        return hashSet;
    }

    public synchronized void dMe() {
        for (Tzd tzd : this.BIo.values()) {
            tzd.JTe();
        }
    }

    public synchronized void jiA() {
        if (!yPL()) {
            lOf().jiA();
        }
    }

    public synchronized tYL lOf() {
        Tzd next;
        Iterator<Tzd> it2 = this.BIo.values().iterator();
        while (it2.hasNext()) {
            next = it2.next();
            if (!next.zyO()) {
            }
        }
        throw new IllegalStateException("Must call isEmpty before calling peek.");
        return next.jiA();
    }

    public synchronized void uzr() {
        for (Tzd tzd : this.BIo.values()) {
            tzd.uzr();
        }
    }

    public synchronized boolean yPL() {
        boolean z;
        z = true;
        for (Tzd tzd : this.BIo.values()) {
            z &= tzd.zyO();
        }
        return z;
    }

    public synchronized void zQM() {
        for (Tzd tzd : this.BIo.values()) {
            tzd.zZm();
        }
    }

    public synchronized void zyO() {
        for (Tzd tzd : this.BIo.values()) {
            tzd.Mlj();
        }
    }

    public synchronized void zzR() {
        for (Tzd tzd : this.BIo.values()) {
            tzd.dMe();
        }
    }

    public synchronized void zZm(Tzd tzd, Sga sga) {
        tzd.BIo.add(new zZm(null));
        this.BIo.put(tzd.jiA, tzd);
        this.zQM.put(tzd, sga);
    }

    public synchronized void BIo(IYE iye) {
        Tzd tzd;
        Iterator<Tzd> it2 = this.BIo.values().iterator();
        while (true) {
            if (!it2.hasNext()) {
                tzd = null;
                break;
            }
            tzd = it2.next();
            if (tzd.zZm(iye)) {
                break;
            }
        }
        if (tzd != null) {
            this.zQM.get(tzd).zZm(tzd, iye);
        }
    }

    public synchronized void zZm(aVo avo, tYL tyl) {
        if (this.BIo.containsKey(avo)) {
            Tzd tzd = this.BIo.get(avo);
            this.zQM.get(tzd).zZm(tzd, tyl);
        }
    }

    public synchronized void zZm() {
        for (Tzd tzd : this.BIo.values()) {
            tzd.LPk();
        }
    }

    public synchronized boolean zZm(aVo avo) {
        Tzd tzd = this.BIo.get(avo);
        if (tzd == null) {
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("requiresAudioFocus: no channel found for ");
            zZm2.append(avo.name());
            Log.w(str, zZm2.toString());
            return false;
        }
        return tzd.lOf();
    }

    public synchronized boolean zZm(IYE iye) {
        boolean z;
        Iterator<Tzd> it2 = this.BIo.values().iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            } else if (it2.next().zZm(iye)) {
                z = true;
                break;
            }
        }
        return z;
    }
}
