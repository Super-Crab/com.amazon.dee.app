package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.AbstractC0238xdr;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: InteractionScheduler.java */
@Singleton
/* loaded from: classes.dex */
public class brA {
    public static final String zZm = "brA";
    public final QJr BIo;
    public final ZIZ JTe;
    public final OPl LPk;
    public final ExecutorService Qle;
    public final AlexaClientEventBus jiA;
    public boolean yPL;
    public final YnK zQM;
    public final vVi zyO;

    /* compiled from: InteractionScheduler.java */
    /* loaded from: classes.dex */
    private class BIo implements Runnable {
        public final AbstractC0238xdr zZm;

        public BIo(AbstractC0238xdr abstractC0238xdr) {
            this.zZm = abstractC0238xdr;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!brA.this.BIo.yPL()) {
                tYL lOf = brA.this.BIo.lOf();
                if (AbstractC0238xdr.zZm.PAUSE_CONTROL == ((TWY) this.zZm).BIo) {
                    brA.this.BIo.HvC();
                    return;
                }
                lOf.zQM();
                brA.zZm(brA.this, lOf.BIo.zZm());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: InteractionScheduler.java */
    /* loaded from: classes.dex */
    public class jiA implements Runnable {
        public /* synthetic */ jiA(lcu lcuVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            brA.this.BIo.dMe();
            brA.this.BIo.zQM();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: InteractionScheduler.java */
    /* loaded from: classes.dex */
    public class zQM implements Runnable {
        public /* synthetic */ zQM(lcu lcuVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            brA.this.BIo.zQM();
        }
    }

    /* compiled from: InteractionScheduler.java */
    /* loaded from: classes.dex */
    private class zZm implements Runnable {
        public final LBB zZm;

        public zZm(LBB lbb) {
            this.zZm = lbb;
        }

        @Override // java.lang.Runnable
        public void run() {
            IYE iye = ((IDp) this.zZm).BIo;
            C0179Pya.zZm("FinishInteractionEvent ", (Object) iye);
            brA.zZm(brA.this, iye);
        }
    }

    /* compiled from: InteractionScheduler.java */
    /* loaded from: classes.dex */
    private class zyO implements Runnable {
        public final mZe zZm;

        public zyO(mZe mze) {
            this.zZm = mze;
        }

        @Override // java.lang.Runnable
        public void run() {
            IYE zZm = ((RNS) this.zZm).zQM.zZm();
            C0179Pya.zZm("ScheduleInteractionEvent ", (Object) zZm);
            if (!brA.this.BIo.zZm(zZm)) {
                aVo avo = ((RNS) this.zZm).BIo;
                YnK ynK = brA.this.zQM;
                RNS rns = (RNS) this.zZm;
                tYL zZm2 = ynK.zZm(rns.zQM, rns.zyO, rns.jiA, brA.this.JTe);
                if (brA.this.BIo.yPL()) {
                    brA.this.BIo.zZm(avo, zZm2);
                    brA.this.zyO.zZm(brA.this.BIo.Qle());
                    zZm2.Qle();
                    brA.this.LPk.zZm(zZm2, true);
                } else {
                    tYL lOf = brA.this.BIo.lOf();
                    brA.this.BIo.zZm(avo, zZm2);
                    brA.this.zyO.zZm(brA.this.BIo.Qle());
                    tYL lOf2 = brA.this.BIo.lOf();
                    boolean z = false;
                    if (!lOf.equals(lOf2)) {
                        PJz pJz = lOf2.Qle;
                        if (pJz != null && !pJz.zZm()) {
                            z = true;
                        }
                        brA.this.LPk.zZm(lOf, z);
                        lOf2.Qle();
                        brA.this.LPk.zZm(lOf2, true);
                    } else {
                        zZm2.Qle();
                        brA.this.LPk.zZm(zZm2, false);
                    }
                }
                if (brA.this.yPL) {
                    return;
                }
                brA.this.yPL = true;
                brA.this.jiA.zyO(new iNr(true));
                return;
            }
            String str = brA.zZm;
            StringBuilder zZm3 = C0179Pya.zZm("Attempted to schedule an interaction which has already been scheduled: ");
            zZm3.append(((RNS) this.zZm).zQM);
            Log.w(str, zZm3.toString());
        }
    }

    @Inject
    public brA(QJr qJr, AlexaClientEventBus alexaClientEventBus, vVi vvi, ZIZ ziz, OPl oPl, gSO gso) {
        YnK ynK = new YnK();
        ExecutorService newSingleThreadExecutor = ExecutorFactory.newSingleThreadExecutor("interaction-scheduler");
        this.BIo = qJr;
        this.zQM = ynK;
        this.zyO = vvi;
        this.JTe = ziz;
        this.jiA = alexaClientEventBus;
        this.Qle = newSingleThreadExecutor;
        this.LPk = oPl;
        alexaClientEventBus.zZm(this);
    }

    @Subscribe
    public void on(mZe mze) {
        this.Qle.submit(new zyO(mze));
    }

    public void BIo() {
        this.Qle.submit(new jiA(null));
        this.Qle.shutdown();
    }

    @Subscribe
    public void on(LBB lbb) {
        this.Qle.submit(new zZm(lbb));
    }

    @Subscribe
    public void on(AbstractC0238xdr abstractC0238xdr) {
        this.Qle.submit(new BIo(abstractC0238xdr));
    }

    public void zZm() {
        this.Qle.submit(new zQM(null));
    }

    public static /* synthetic */ void zZm(brA bra, IYE iye) {
        if (bra.BIo.zZm(iye)) {
            tYL lOf = bra.BIo.lOf();
            bra.BIo.BIo(iye);
            if (!bra.BIo.yPL()) {
                tYL lOf2 = bra.BIo.lOf();
                if (!lOf.equals(lOf2)) {
                    bra.zyO.zZm(bra.BIo.Qle());
                    lOf2.Qle();
                    bra.LPk.zZm(lOf2, true);
                }
            } else {
                bra.zyO.zZm(bra.BIo.Qle());
            }
        } else {
            String str = zZm;
            Log.w(str, "Attempted to unschedule an interaction which has not been scheduled: " + iye);
        }
        if (!bra.BIo.yPL() || !bra.yPL) {
            return;
        }
        bra.yPL = false;
        bra.jiA.zyO(jkT.zZm(false));
    }
}
