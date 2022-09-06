package com.amazon.alexa;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
/* compiled from: VisualChannels.java */
/* loaded from: classes.dex */
public class Rpb {
    public final Map<JiQ, dfe> zZm = new TreeMap();
    public final Map<dfe, Sga> BIo = new HashMap();

    public synchronized Map<JiQ, aNh> BIo() {
        HashMap hashMap;
        hashMap = new HashMap();
        for (JiQ jiQ : this.zZm.keySet()) {
            aNh LPk = this.zZm.get(jiQ).LPk();
            if (LPk != null) {
                hashMap.put(jiQ, LPk);
            }
        }
        return hashMap;
    }

    public synchronized void jiA() {
        for (dfe dfeVar : this.zZm.values()) {
            dfeVar.JTe();
        }
    }

    public synchronized boolean zQM() {
        boolean z;
        z = true;
        for (dfe dfeVar : this.zZm.values()) {
            z &= dfeVar.zyO();
        }
        return z;
    }

    public synchronized void zZm(dfe dfeVar, Sga sga) {
        this.zZm.put(dfeVar.jiA, dfeVar);
        this.BIo.put(dfeVar, sga);
    }

    public synchronized AOq zyO() {
        dfe next;
        Iterator<dfe> it2 = this.zZm.values().iterator();
        while (it2.hasNext()) {
            next = it2.next();
            if (!next.zyO()) {
            }
        }
        throw new IllegalStateException("Must call isEmpty before calling peek.");
        return next.jiA();
    }

    public synchronized void BIo(IYE iye) {
        dfe dfeVar;
        Iterator<dfe> it2 = this.zZm.values().iterator();
        while (true) {
            if (!it2.hasNext()) {
                dfeVar = null;
                break;
            }
            dfeVar = it2.next();
            if (dfeVar.zZm(iye)) {
                break;
            }
        }
        if (dfeVar != null) {
            this.BIo.get(dfeVar).zZm(dfeVar, iye);
        }
    }

    public synchronized void zZm(JiQ jiQ, AOq aOq) {
        if (this.zZm.containsKey(jiQ)) {
            dfe dfeVar = this.zZm.get(jiQ);
            this.BIo.get(dfeVar).zZm(dfeVar, aOq);
        }
    }

    public synchronized void zZm() {
        for (dfe dfeVar : this.zZm.values()) {
            dfeVar.zZm();
        }
    }

    public synchronized boolean zZm(IYE iye) {
        boolean z;
        Iterator<dfe> it2 = this.zZm.values().iterator();
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
