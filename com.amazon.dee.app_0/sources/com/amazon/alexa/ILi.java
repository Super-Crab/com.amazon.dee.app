package com.amazon.alexa;

import android.util.Log;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.inject.Singleton;
/* compiled from: WakeWordOccurrenceNotifier.java */
@Singleton
/* loaded from: classes.dex */
public class ILi {
    public static final String zZm = "ILi";
    public final HashMap<cIy, zZm> BIo = new HashMap<>();

    public void zZm(cIy ciy, long j) {
        synchronized (this.BIo) {
            if (this.BIo.containsKey(ciy)) {
                zZm zzm = this.BIo.get(ciy);
                if (zzm.BIo()) {
                    zzm.zZm.zZm(j);
                } else {
                    zzm.BIo.add(Long.valueOf(j));
                }
            } else {
                this.BIo.put(ciy, new zZm(Long.valueOf(j)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: WakeWordOccurrenceNotifier.java */
    /* loaded from: classes.dex */
    public static class zZm implements OyE {
        public final Queue<Long> BIo = new PriorityQueue();
        public OyE zZm;

        public zZm(OyE oyE) {
            this.zZm = oyE;
        }

        public final boolean BIo() {
            return this.zZm != null;
        }

        @Override // com.amazon.alexa.OyE
        public void zZm(long j) {
            if (this.zZm != null) {
                this.zZm.zZm(j);
            } else {
                this.BIo.add(Long.valueOf(j));
            }
        }

        public zZm(Long l) {
            this.BIo.add(l);
        }

        public final void zZm() {
            synchronized (this.BIo) {
                while (!this.BIo.isEmpty()) {
                    this.zZm.zZm(this.BIo.poll().longValue());
                }
            }
        }
    }

    public void zZm(OyE oyE, cIy ciy) {
        zZm zzm;
        synchronized (this.BIo) {
            if (this.BIo.containsKey(ciy)) {
                zzm = this.BIo.get(ciy);
            } else {
                zZm zzm2 = new zZm(oyE);
                this.BIo.put(ciy, zzm2);
                zzm = zzm2;
            }
            if (zzm.BIo()) {
                Log.w(zZm, "trying to add listener while already have one");
            } else {
                zzm.zZm = oyE;
                zzm.zZm();
            }
        }
    }
}
