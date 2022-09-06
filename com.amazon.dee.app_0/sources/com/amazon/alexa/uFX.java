package com.amazon.alexa;

import java.util.HashSet;
import java.util.Set;
/* compiled from: PausedPlayersTracker.java */
/* loaded from: classes.dex */
public class uFX {
    public final Set<vQe> zZm = new HashSet();
    public final Set<vQe> BIo = new HashSet();

    public void zZm(vQe vqe) {
        this.zZm.remove(vqe);
        this.BIo.remove(vqe);
    }
}
