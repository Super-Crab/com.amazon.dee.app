package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.api.AlexaReadinessListener;
import com.amazon.alexa.api.AlexaReadyState;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: ReadinessAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class XUD {
    public static final String zZm = "XUD";
    public boolean HvC;
    public boolean LPk;
    public boolean Mlj;
    public long dMe;
    public final TimeProvider jiA;
    public final long lOf;
    public boolean uzr;
    public boolean yPL;
    public final AlexaClientEventBus zQM;
    public final qxC zyO;
    public boolean zzR;
    public final Object Qle = new Object();
    public final Object JTe = new Object();
    public final Shr<AlexaReadinessListener> BIo = new Shr<>();

    @Inject
    public XUD(AlexaClientEventBus alexaClientEventBus, qxC qxc, TimeProvider timeProvider) {
        this.zQM = alexaClientEventBus;
        this.zyO = qxc;
        this.jiA = timeProvider;
        this.lOf = timeProvider.elapsedRealTime();
        alexaClientEventBus.zZm(this);
    }

    public final void BIo() {
        if (this.HvC || !this.LPk) {
            return;
        }
        this.HvC = true;
        if (!this.uzr) {
            zQM();
        }
        long elapsedRealTime = this.jiA.elapsedRealTime();
        this.zQM.zyO(new VLd(elapsedRealTime - this.lOf));
        this.zQM.zyO(new WRN(elapsedRealTime - this.dMe));
    }

    public final void Qle() {
        boolean z;
        synchronized (this.JTe) {
            z = true;
            boolean z2 = this.Mlj && this.yPL;
            if (z2 != this.zzR) {
                String str = zZm;
                StringBuilder sb = new StringBuilder();
                sb.append("isReady? ");
                sb.append(z2);
                Log.i(str, sb.toString());
                this.zzR = z2;
                zQM();
            } else {
                z = false;
            }
        }
        if (z) {
            jiA();
        }
    }

    public final void jiA() {
        AlexaReadyState zZm2 = zZm();
        synchronized (this.Qle) {
            for (AlexaReadinessListener alexaReadinessListener : this.BIo.zZm()) {
                alexaReadinessListener.onReadinessChanged(zZm2);
            }
        }
    }

    @Subscribe
    public void on(Bob bob) {
        boolean z;
        synchronized (this.JTe) {
            z = true;
            if (this.LPk != bob.BIo()) {
                this.LPk = bob.BIo();
                if (this.LPk) {
                    Log.i(zZm, "Readiness is in connected state.");
                    this.zzR = true;
                    this.Mlj = true;
                }
                BIo();
            } else {
                z = false;
            }
        }
        if (z) {
            jiA();
        }
    }

    public final void zQM() {
        if (this.uzr || !this.zzR) {
            return;
        }
        this.uzr = true;
        this.dMe = this.jiA.elapsedRealTime();
        this.zQM.zyO(new QEq(this.dMe - this.lOf));
    }

    public void zZm(ExtendedClient extendedClient, AlexaReadinessListener alexaReadinessListener) {
        synchronized (this.Qle) {
            this.BIo.zZm(extendedClient, alexaReadinessListener);
        }
        alexaReadinessListener.onReadinessChanged(zZm());
    }

    public void zyO() {
        this.zQM.BIo(this);
    }

    public void zZm(AlexaReadinessListener alexaReadinessListener) {
        synchronized (this.Qle) {
            this.BIo.remove(alexaReadinessListener);
        }
    }

    public AlexaReadyState zZm() {
        AlexaReadyState create;
        synchronized (this.JTe) {
            create = AlexaReadyState.create(this.LPk, this.zzR);
        }
        return create;
    }

    @Subscribe
    public void on(YHu yHu) {
        boolean z;
        synchronized (this.JTe) {
            z = true;
            if (!this.Mlj) {
                this.Mlj = true;
            } else {
                z = false;
            }
        }
        if (z) {
            Qle();
        }
    }

    @Subscribe
    public void on(ycT yct) {
        boolean z;
        synchronized (this.JTe) {
            z = false;
            if (this.Mlj) {
                Log.i(zZm, "Readiness is not in ready state.");
                this.Mlj = false;
                this.LPk = false;
                z = true;
            }
        }
        if (z) {
            Qle();
        }
    }

    @Subscribe
    public void on(MyZ myZ) {
        boolean z;
        synchronized (this.JTe) {
            z = true;
            this.yPL = true;
            if (this.zyO.zQM()) {
                this.Mlj = true;
            } else {
                z = false;
            }
        }
        if (z) {
            Qle();
        }
    }
}
