package com.amazon.alexa;

import android.os.Build;
import android.util.Log;
import com.amazon.alexa.WnL;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.eDG;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.LongUnaryOperator;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AVSConnectionStateAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class eDG implements IHN {
    public static final long BIo = TimeUnit.MILLISECONDS.convert(30, TimeUnit.SECONDS);
    public static final String zZm = "eDG";
    public final ScheduledExecutorService Qle;
    public final AlexaClientEventBus jiA;
    public final yDI zQM;
    public final WnL zyO;
    public final zZm JTe = new zZm(null);
    public final AtomicReference<ScheduledFuture<?>> LPk = new AtomicReference<>();
    public final AtomicLong yPL = new AtomicLong(200);

    @Inject
    public eDG(@Named("ENSURE_INITIALIZATION_CHAIN") yDI ydi, WnL wnL, AlexaClientEventBus alexaClientEventBus, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService) {
        this.zQM = ydi;
        this.zyO = wnL;
        this.jiA = alexaClientEventBus;
        this.Qle = scheduledExecutorService;
        this.jiA.zZm(this);
    }

    @Subscribe
    public void on(MyZ myZ) {
        this.zyO.zZm = false;
        this.zQM.zZm(this);
    }

    @Override // com.amazon.alexa.IHN
    public void onSuccess() {
        Log.i(zZm, "AVS Connection is available.");
        WnL wnL = this.zyO;
        wnL.zzR = null;
        wnL.Mlj = null;
        wnL.dMe = null;
        wnL.lOf = null;
        wnL.HvC = null;
        wnL.uzr = null;
        wnL.zZm = false;
        wnL.jiA = false;
        wnL.zyO = false;
        wnL.BIo = false;
        wnL.zQM = false;
        wnL.Qle = false;
        wnL.JTe = false;
        wnL.LPk = false;
        wnL.yPL = false;
        this.jiA.zyO(new kjy(uuj.AVAILABLE));
        zZm((ScheduledFuture<?>) null);
        this.yPL.set(200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AVSConnectionStateAuthority.java */
    /* loaded from: classes.dex */
    public class zZm implements Runnable {
        public /* synthetic */ zZm(inU inu) {
        }

        @Override // java.lang.Runnable
        public void run() {
            eDG.this.jiA.zyO(new tii());
            zZm();
            eDG.this.BIo();
        }

        public final void zZm() {
            int i = Build.VERSION.SDK_INT;
            eDG.this.yPL.getAndUpdate(new LongUnaryOperator() { // from class: com.amazon.alexa.-$$Lambda$0uIDvdP6SbAmZccI6A8r5xWnwfY
                @Override // java.util.function.LongUnaryOperator
                public final long applyAsLong(long j) {
                    return eDG.zZm.this.zZm(j);
                }
            });
        }

        public final long zZm(long j) {
            return Math.min(eDG.BIo, j * 2);
        }
    }

    public final void BIo() {
        StringBuilder zZm2 = C0179Pya.zZm("Downchannel not established. Attempting to retry establishing downchannel in ");
        zZm2.append(this.yPL.get());
        zZm2.append("ms");
        zZm2.toString();
        zZm(this.Qle.schedule(this.JTe, this.yPL.get(), TimeUnit.MILLISECONDS));
    }

    public void zQM() {
        zZm((ScheduledFuture<?>) null);
        this.jiA.BIo(this);
        this.zQM.teardown();
    }

    @Override // com.amazon.alexa.IHN
    public void zZm() {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Current Downchannel Status: ");
        zZm2.append(this.zyO.zZm().yPL.name());
        Log.i(str, zZm2.toString());
    }

    @Subscribe(priority = 100, sticky = true)
    public void on(YHu yHu) {
        WnL wnL = this.zyO;
        wnL.BIo = false;
        wnL.jiA();
        WnL wnL2 = this.zyO;
        wnL2.Qle = true;
        wnL2.JTe = true;
        this.zQM.zZm(this);
    }

    @Override // com.amazon.alexa.IHN
    public void zZm(hlN hln) {
        String str = zZm;
        Log.w(str, "AVSConnection Failed due to " + hln);
        switch (hln.ordinal()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            default:
                return;
            case 6:
                this.jiA.zyO(new kjy(uuj.UNAVAILABLE));
                return;
        }
    }

    @Subscribe(priority = 100)
    public void on(ycT yct) {
        Log.i(zZm, "AVS Connection is not available due to network lost.");
        WnL wnL = this.zyO;
        wnL.BIo = true;
        wnL.jiA();
        WnL wnL2 = this.zyO;
        wnL2.Qle = true;
        wnL2.JTe = true;
    }

    public final void zZm(@Nullable ScheduledFuture<?> scheduledFuture) {
        ScheduledFuture<?> andSet = this.LPk.getAndSet(scheduledFuture);
        if (andSet == null || andSet.isDone() || andSet.isCancelled()) {
            return;
        }
        andSet.cancel(true);
    }

    @Subscribe(sticky = true)
    public void on(ZBK zbk) {
        if (((dZg) zbk).BIo) {
            this.zyO.jiA = true;
            this.zQM.zZm(this);
        }
    }

    @Subscribe
    public void on(QTn qTn) {
        this.zyO.jiA = true;
        this.zQM.zZm(this);
    }

    @Subscribe
    public void on(WBQ wbq) {
        this.zyO.LPk = true;
        this.zQM.zZm(this);
    }

    @Subscribe
    public void on(xaz xazVar) {
        WnL wnL = this.zyO;
        wnL.JTe = true;
        wnL.Qle = true;
        wnL.jiA();
        this.zQM.zZm(this);
    }

    @Subscribe
    public void on(JjI jjI) {
        if (!this.zyO.yPL || AvsApiConstants.zZm(AvsApiConstants.System.zZm, AvsApiConstants.System.Events.ExceptionEncountered.zZm, ((WXj) jjI).zQM)) {
            return;
        }
        this.zQM.zZm(this);
    }

    @Subscribe
    public void on(TTH tth) {
        if (((nAN) tth).jiA || this.zyO.zZm().yPL.equals(WnL.zyO.AVAILABLE)) {
            return;
        }
        this.zyO.jiA();
        WnL wnL = this.zyO;
        wnL.Qle = true;
        wnL.JTe = true;
        this.jiA.zyO(new Ehj());
    }

    @Subscribe
    public void on(uXi uxi) {
        this.zQM.zZm(this);
    }

    @Subscribe
    public void on(mUQ muq) {
        if (!((hVb) muq).BIo) {
            ScheduledFuture<?> scheduledFuture = this.LPk.get();
            if (scheduledFuture != null && !scheduledFuture.isCancelled() && !scheduledFuture.isDone()) {
                return;
            }
            BIo();
        }
    }
}
