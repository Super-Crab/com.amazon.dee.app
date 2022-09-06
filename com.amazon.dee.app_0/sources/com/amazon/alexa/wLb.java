package com.amazon.alexa;

import com.amazon.alexa.api.AlexaPlaybackState;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: ServiceActivityAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class wLb {
    public static final String zZm = "wLb";
    public final qxC BIo;
    public boolean JTe;
    public final IYJ zQM;
    public final Set<RrI> zyO = new HashSet();
    public final AtomicReference<xNT> jiA = new AtomicReference<>(xNT.zZm);
    public final AtomicReference<AlexaPlaybackState> Qle = new AtomicReference<>();
    public boolean LPk = false;

    @Inject
    public wLb(AlexaClientEventBus alexaClientEventBus, qxC qxc, IYJ iyj) {
        this.BIo = qxc;
        this.zQM = iyj;
        alexaClientEventBus.zZm(this);
    }

    public boolean BIo() {
        return zZm(new StringBuilder("Service is busy. Reason(s): "), false);
    }

    @Subscribe(priority = 100)
    public void on(YQk yQk) {
        synchronized (this.zyO) {
            this.zyO.add(((aeu) yQk).BIo);
        }
    }

    public boolean zZm() {
        return zZm(new StringBuilder("Service is active. Reason(s): "), true);
    }

    public final boolean BIo(StringBuilder sb, boolean z) {
        int size;
        synchronized (this.zyO) {
            size = this.zyO.size();
        }
        if (size > 0) {
            if (z) {
                sb.append(", ");
            }
            sb.append("waiting for event");
            if (size > 1) {
                sb.append("s");
            }
            sb.append(" to finish");
            return true;
        }
        return false;
    }

    public final boolean zZm(StringBuilder sb, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = false;
        if (this.zQM.zyO()) {
            sb.append("has connected client");
            Set<ExtendedClient> BIo = this.zQM.BIo();
            int size = BIo.size();
            if (size > 1) {
                sb.append("s (");
                sb.append(size);
            } else if (size == 1) {
                sb.append(" (");
                for (ExtendedClient extendedClient : BIo) {
                    sb.append(extendedClient.getId());
                }
            }
            sb.append(")");
            z2 = true;
        } else {
            z2 = false;
        }
        if (this.BIo.zQM()) {
            if (this.JTe) {
                if (z2) {
                    sb.append(", ");
                }
                sb.append("service has an interaction scheduled");
                z3 = true;
            } else {
                z3 = false;
            }
            z2 |= z3;
            if (z) {
                boolean BIo2 = BIo(sb, z2) | z2;
                if (!xNT.zZm.equals(this.jiA.get())) {
                    if (BIo2) {
                        sb.append(", ");
                    }
                    sb.append("service is playing music");
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z6 = BIo2 | z4;
                if (this.LPk) {
                    if (z6) {
                        sb.append(", ");
                    }
                    sb.append("ExternalMediaPlayer is playing music");
                    z5 = true;
                }
                z2 = z6 | z5;
            }
        }
        if (z2) {
            sb.toString();
        }
        return z2;
    }

    @Subscribe(priority = 100)
    public void on(AhI ahI) {
        synchronized (this.zyO) {
            this.zyO.remove(((Kzp) ahI).BIo);
        }
    }

    @Subscribe(priority = 100)
    public synchronized void on(jkT jkt) {
        this.JTe = ((iNr) jkt).BIo;
    }

    @Subscribe(priority = 100)
    public void on(FXk fXk) {
        YLU ylu = (YLU) fXk;
        String name = ((PUa) ylu.zQM).zZm.name();
        StringBuilder zZm2 = C0179Pya.zZm("externalMediaPlayer: ");
        zZm2.append(ylu.BIo);
        zZm2.append(" is in error state: ");
        zZm2.append(name);
        zZm2.toString();
        this.LPk = false;
    }

    @Subscribe(priority = 100)
    public void on(CKO cko) {
        StringBuilder zZm2 = C0179Pya.zZm("AudioItemStateChanged to: ");
        IyB iyB = (IyB) cko;
        zZm2.append(iyB.BIo);
        zZm2.toString();
        int i = zIj.zZm[iyB.BIo.ordinal()];
        if (i == 1 || i == 2) {
            this.jiA.set(xNT.zZm);
        } else if (i != 3 && i != 4 && i != 5) {
            if (xNT.zZm.equals(this.jiA.get())) {
                return;
            }
            this.jiA.set(iyB.zQM);
        } else {
            this.jiA.set(iyB.zQM);
        }
    }

    @Subscribe(priority = 100)
    public void on(AgS agS) {
        if (((UuG) agS).BIo) {
            this.jiA.set(xNT.zZm);
        }
    }

    @Subscribe(priority = 100)
    public void on(Ust ust) {
        this.jiA.set(xNT.zZm);
    }

    @Subscribe(priority = 100)
    public void on(qZM qzm) {
        this.Qle.set(((CYr) qzm).BIo);
    }

    @Subscribe(priority = 100)
    public void on(ddC ddc) {
        String zZm2 = ((SLO) ddc).zQM.zZm();
        this.LPk = !vat.PLAYBACK_STOPPED.zZm().equals(zZm2) && !vat.PLAYBACK_SESSION_ENDED.zZm().equals(zZm2);
    }
}
