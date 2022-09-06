package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: VisualInteractionScheduler.java */
@Singleton
/* loaded from: classes.dex */
public class gMz {
    public static final String zZm = "gMz";
    public final Rpb BIo;
    public final ExecutorService jiA;
    public final YnK zQM;
    public final AlexaClientEventBus zyO;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: VisualInteractionScheduler.java */
    /* loaded from: classes.dex */
    public class BIo implements Runnable {
        public /* synthetic */ BIo(ydD ydd) {
        }

        @Override // java.lang.Runnable
        public void run() {
            gMz.this.BIo.zZm();
        }
    }

    /* compiled from: VisualInteractionScheduler.java */
    /* loaded from: classes.dex */
    private class zQM implements Runnable {
        public final qUD zZm;

        public zQM(qUD qud) {
            this.zZm = qud;
        }

        @Override // java.lang.Runnable
        public void run() {
            IYE iye = ((AIx) this.zZm).zQM.zZm;
            C0179Pya.zZm("ScheduleVisualInteractionEvent ", (Object) iye);
            if (!gMz.this.BIo.zZm(iye)) {
                JiQ jiQ = ((AIx) this.zZm).BIo;
                AOq zZm = gMz.this.zQM.zZm(((AIx) this.zZm).zQM);
                if (gMz.this.BIo.zQM()) {
                    gMz.this.BIo.zZm(jiQ, zZm);
                    return;
                }
                AOq zyO = gMz.this.BIo.zyO();
                gMz.this.BIo.zZm(jiQ, zZm);
                if (zyO.equals(gMz.this.BIo.zyO())) {
                    return;
                }
                zyO.zQM();
                return;
            }
            String str = gMz.zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Attempted to schedule an interaction which has already been scheduled: ");
            zZm2.append(((AIx) this.zZm).zQM);
            Log.w(str, zZm2.toString());
        }
    }

    /* compiled from: VisualInteractionScheduler.java */
    /* loaded from: classes.dex */
    private class zZm implements Runnable {
        public final dOG zZm;

        public zZm(dOG dog) {
            this.zZm = dog;
        }

        @Override // java.lang.Runnable
        public void run() {
            IYE iye = ((kwP) this.zZm).BIo;
            C0179Pya.zZm("FinishInteractionEvent ", (Object) iye);
            gMz.zZm(gMz.this, iye);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: VisualInteractionScheduler.java */
    /* loaded from: classes.dex */
    public class zyO implements Runnable {
        public /* synthetic */ zyO(ydD ydd) {
        }

        @Override // java.lang.Runnable
        public void run() {
            gMz.this.BIo.jiA();
            gMz.this.BIo.zZm();
        }
    }

    @Inject
    public gMz(Rpb rpb, AlexaClientEventBus alexaClientEventBus) {
        YnK ynK = new YnK();
        ExecutorService newSingleThreadExecutor = ExecutorFactory.newSingleThreadExecutor("interaction-scheduler");
        this.BIo = rpb;
        this.zyO = alexaClientEventBus;
        this.zQM = ynK;
        this.jiA = newSingleThreadExecutor;
        this.zyO.zZm(this);
    }

    @Subscribe
    public void on(qUD qud) {
        this.jiA.submit(new zQM(qud));
    }

    public void BIo() {
        this.jiA.submit(new zyO(null));
        this.jiA.shutdown();
    }

    @Subscribe
    public void on(dOG dog) {
        this.jiA.submit(new zZm(dog));
    }

    public void zZm() {
        this.jiA.submit(new BIo(null));
    }

    public static /* synthetic */ void zZm(gMz gmz, IYE iye) {
        if (gmz.BIo.zZm(iye)) {
            gmz.BIo.zyO();
            gmz.BIo.BIo(iye);
            return;
        }
        String str = zZm;
        Log.w(str, "Attempted to unschedule an interaction which has not been scheduled: " + iye);
    }
}
