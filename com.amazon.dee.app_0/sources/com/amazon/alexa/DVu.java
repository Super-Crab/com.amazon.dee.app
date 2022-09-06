package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.metrics.core.AppInformation;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: DownchannelScheduler.java */
/* loaded from: classes.dex */
public class DVu {
    public static final String zZm = "DVu";
    public final ExecutorService BIo;
    public final AlexaClientEventBus JTe;
    public fau LPk;
    public volatile Future<?> Mlj;
    public final Provider<fau> Qle;
    public boolean dMe;
    public final RZN jiA;
    public volatile boolean lOf;
    public fau yPL;
    public final ScheduledExecutorService zQM;
    public final ScheduledExecutorService zyO;
    public volatile Future<?> zzR;

    public DVu(Provider<fau> provider, RZN rzn, AlexaClientEventBus alexaClientEventBus) {
        ScheduledExecutorService newScheduledExecutor = ExecutorFactory.newScheduledExecutor(2, "downchannel");
        ScheduledExecutorService newSingleThreadScheduledExecutor = ExecutorFactory.newSingleThreadScheduledExecutor("downchannelCleanup");
        ScheduledExecutorService newSingleThreadScheduledExecutor2 = ExecutorFactory.newSingleThreadScheduledExecutor("ping");
        this.Mlj = null;
        this.zzR = null;
        this.Qle = provider;
        this.BIo = newScheduledExecutor;
        this.zQM = newSingleThreadScheduledExecutor;
        this.zyO = newSingleThreadScheduledExecutor2;
        this.jiA = rzn;
        this.JTe = alexaClientEventBus;
        this.JTe.zZm(this);
    }

    public static String zZm(@Nullable fau fauVar) {
        return fauVar == null ? AppInformation.NULL : fauVar.zZm.BIo.getValue();
    }

    public void BIo() {
        if (this.lOf) {
            Log.i(zZm, "Tried to establish downchannel when torn down");
            return;
        }
        fau fauVar = this.LPk;
        if (!(fauVar != null && !fauVar.isDone())) {
            String str = zZm;
            Log.i(str, "Establishing downchannel from downchannelScheduler " + this);
            this.JTe.zyO(new oiq());
            this.LPk = this.Qle.mo10268get();
            this.BIo.submit(this.LPk);
            this.Mlj = this.zyO.scheduleWithFixedDelay(new NFm(this), 0L, 5L, TimeUnit.MINUTES);
            return;
        }
        Log.i(zZm, "downchannel cleanup cancelled, currentDownchannel is running");
        zZm(this.zzR);
    }

    public void jiA() {
        zQM();
        this.JTe.BIo(this);
        this.lOf = true;
        String str = zZm;
        Log.i(str, "Teardown DownchannelScheduler: " + this);
        this.BIo.shutdown();
        this.zQM.shutdown();
        this.zyO.shutdown();
    }

    @Subscribe
    public void on(WBQ wbq) {
        zQM();
    }

    public void zQM() {
        if (this.dMe) {
            zyO();
        }
        Log.i(zZm, "Killing downchannel");
        zZm(this.zzR);
        zZm(this.Mlj);
        zZm((Future<?>) this.LPk);
        this.zzR = null;
        this.Mlj = null;
        this.LPk = null;
    }

    public final void zyO() {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Downchannel closed: ");
        zZm2.append(zZm(this.LPk));
        Log.i(str, zZm2.toString());
        this.dMe = false;
        this.JTe.zyO(new dpf(false));
    }

    @Subscribe
    public void on(bFb bfb) {
        zQM();
    }

    @Subscribe(priority = 100)
    public void on(xaz xazVar) {
        zZm();
    }

    @Subscribe(priority = 100)
    public void on(mUQ muq) {
        hVb hvb = (hVb) muq;
        if (hvb.BIo) {
            if (this.dMe) {
                Log.i(zZm, "Downchannel established. Not notifying because downchannel availability has not changed");
                return;
            } else if (!zZm(hvb.zQM)) {
                Log.i(zZm, "Downchannel established. Not notifying because not current downchannel");
                return;
            } else {
                String str = zZm;
                StringBuilder zZm2 = C0179Pya.zZm("Downchannel established: ");
                zZm2.append(zZm(this.LPk));
                Log.i(str, zZm2.toString());
                this.dMe = true;
                this.JTe.zyO(yHQ.zZm(true));
                return;
            }
        }
        if (!this.dMe) {
            Log.i(zZm, "Downchannel not established. Not notifying because downchannel availability has not changed");
        } else if (!zZm(hvb.zQM)) {
            Log.i(zZm, "Downchannel not established. Not notifying because not current downchannel");
        } else {
            zyO();
        }
        zZm((Future<?>) this.LPk);
        this.LPk = null;
        zZm(this.Mlj);
        this.Mlj = null;
    }

    @VisibleForTesting
    public void zZm() {
        fau fauVar = this.LPk;
        if (fauVar != null && !fauVar.isDone()) {
            fau fauVar2 = this.yPL;
            if (fauVar2 != null && !fauVar2.isDone()) {
                String str = zZm;
                StringBuilder zZm2 = C0179Pya.zZm("Cancelling abandoned downchannel: ");
                zZm2.append(zZm(this.LPk));
                Log.i(str, zZm2.toString());
                zZm((Future<?>) this.yPL);
            }
            this.yPL = this.LPk;
            String str2 = zZm;
            StringBuilder zZm3 = C0179Pya.zZm("Abandoning downchannel: ");
            zZm3.append(zZm(this.LPk));
            Log.i(str2, zZm3.toString());
            zZm(this.zzR);
            this.zQM.submit(new Bag(this.yPL));
            this.yPL.zZm.BIo();
            if (this.dMe) {
                zyO();
            }
            this.LPk = null;
            this.zzR = null;
            zZm(this.Mlj);
        }
    }

    public final boolean zZm(tux tuxVar) {
        fau fauVar = this.LPk;
        return fauVar != null && fauVar.zZm.BIo.equals(tuxVar);
    }

    public static void zZm(@Nullable Future<?> future) {
        if (future != null) {
            future.cancel(true);
        }
    }
}
