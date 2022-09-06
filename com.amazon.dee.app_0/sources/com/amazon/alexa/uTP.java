package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.google.gson.Gson;
import dagger.Lazy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/* compiled from: PersistedDataLoader.java */
/* loaded from: classes.dex */
public class uTP {
    public static final String zZm = "uTP";
    public final long BIo;
    public final Gson JTe;
    public Future<?> LPk;
    public final TimeProvider Qle;
    public final ExecutorService jiA;
    public final boolean zQM;
    public final Lazy<PersistentStorage> zyO;

    /* compiled from: PersistedDataLoader.java */
    /* loaded from: classes.dex */
    public interface BIo {
        void zZm(PersistentStorage.Transaction transaction, Gson gson);
    }

    /* compiled from: PersistedDataLoader.java */
    /* loaded from: classes.dex */
    public interface zZm {
        void zZm(PersistentStorage persistentStorage, Gson gson);
    }

    public uTP(String str, Lazy<PersistentStorage> lazy, TimeProvider timeProvider, Gson gson) {
        this(str, 0L, false, lazy, ManagedExecutorFactory.newSingleThreadCachedThreadPool(str + "_data_loader"), timeProvider, gson);
    }

    public final void BIo(zZm zzm) {
        long j = this.zyO.mo358get().getLong("timestamp", 0L);
        if (!this.zQM || this.Qle.currentTimeMillis() - j < this.BIo) {
            zzm.zZm(this.zyO.mo358get(), this.JTe);
        }
    }

    public void zZm() {
        this.zyO.mo358get().edit().clear().commitSynchronously();
    }

    @VisibleForTesting
    public uTP(String str, long j, boolean z, Lazy<PersistentStorage> lazy, ExecutorService executorService, TimeProvider timeProvider, Gson gson) {
        this.BIo = j;
        this.zQM = z;
        this.zyO = lazy;
        this.jiA = executorService;
        this.Qle = timeProvider;
        this.JTe = gson;
    }

    public final void BIo(BIo bIo) {
        PersistentStorage.Transaction edit = this.zyO.mo358get().edit();
        bIo.zZm(edit, this.JTe);
        edit.set("timestamp", this.Qle.currentTimeMillis());
        edit.commitAsynchronously();
    }

    public void zZm(zZm zzm) {
        this.LPk = this.jiA.submit(new aum(this, zzm));
    }

    public void zZm(BIo bIo) {
        this.jiA.submit(new SIO(this, bIo));
    }

    public void BIo() {
        Future<?> future = this.LPk;
        if (future != null) {
            try {
                future.get(10L, TimeUnit.SECONDS);
            } catch (Exception e) {
                Log.e(zZm, "Waiting for data failed", e);
            }
        }
    }
}
