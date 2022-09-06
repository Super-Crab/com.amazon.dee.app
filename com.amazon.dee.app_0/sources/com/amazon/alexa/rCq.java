package com.amazon.alexa;

import android.content.ComponentName;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: ExternalCapabilityAgentRegistry.java */
/* loaded from: classes.dex */
public class rCq implements Runnable {
    public final /* synthetic */ String BIo;
    public final /* synthetic */ LrI Qle;
    public final /* synthetic */ AtomicInteger jiA;
    public final /* synthetic */ Set zQM;
    public final /* synthetic */ ComponentName zZm;
    public final /* synthetic */ Map zyO;

    public rCq(LrI lrI, ComponentName componentName, String str, Set set, Map map, AtomicInteger atomicInteger) {
        this.Qle = lrI;
        this.zZm = componentName;
        this.BIo = str;
        this.zQM = set;
        this.zyO = map;
        this.jiA = atomicInteger;
    }

    @Override // java.lang.Runnable
    public void run() {
        Map map;
        Map map2;
        Map map3;
        Map map4;
        zEy zZm = this.Qle.zZm(this.zZm);
        if (zZm == null) {
            return;
        }
        map = this.Qle.uzr;
        synchronized (map) {
            map2 = this.Qle.uzr;
            if (!map2.containsKey(this.BIo)) {
                map4 = this.Qle.uzr;
                map4.put(this.BIo, new HashSet());
            }
            map3 = this.Qle.uzr;
            ((Set) map3.get(this.BIo)).addAll(zZm.zQM());
        }
        synchronized (this.zQM) {
            this.zQM.addAll(zZm.jiA);
        }
        synchronized (this.zyO) {
            this.zyO.put(zZm, zZm.zQM());
        }
        this.jiA.incrementAndGet();
    }
}
