package com.amazon.alexa;

import android.media.AudioManager;
import android.os.Build;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.PJz;
import com.amazon.alexa.QJr;
import com.amazon.alexa.api.AlexaPlaybackState;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AudioFocusManager.java */
@Singleton
/* loaded from: classes.dex */
public class OPl implements AudioManager.OnAudioFocusChangeListener {
    public static final String zZm = "OPl";
    public final QJr JTe;
    public final SKB LPk;
    public final AudioManager Qle;
    public boolean dMe;
    public final AlexaClientEventBus jiA;
    public pWO lOf;
    public boolean uzr;
    @VisibleForTesting
    public final Khf zQM;
    @VisibleForTesting
    public PJz zyO;
    @VisibleForTesting
    public int BIo = 0;
    public final AtomicBoolean yPL = new AtomicBoolean();
    public final AtomicBoolean Mlj = new AtomicBoolean();
    public zyO HvC = zyO.DONE;
    public TreeMap<PJz.BIo, TreeSet<PJz>> zzR = new TreeMap<>(new zZm());

    /* compiled from: AudioFocusManager.java */
    /* loaded from: classes.dex */
    private class BIo implements QJr.BIo {
        public /* synthetic */ BIo(nhT nht) {
        }

        @Override // com.amazon.alexa.QJr.BIo
        public void zZm() {
            synchronized (OPl.this) {
                OPl.this.zyO();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AudioFocusManager.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class zQM implements Comparator<PJz> {
        @Override // java.util.Comparator
        public int compare(PJz pJz, PJz pJz2) {
            PJz pJz3 = pJz;
            PJz pJz4 = pJz2;
            PJz.BIo bIo = ((PNy) pJz3).BIo;
            PJz.BIo bIo2 = ((PNy) pJz4).BIo;
            boolean zZm = pJz3.zZm();
            boolean zZm2 = pJz4.zZm();
            boolean z = ((PNy) pJz3).jiA;
            boolean z2 = ((PNy) pJz4).jiA;
            if ((bIo != bIo2 || zZm != zZm2 || z != z2) && (zZm || !zZm2)) {
                if (!zZm || zZm2) {
                    int zZm3 = new zZm().zZm(bIo, bIo2);
                    if (zZm3 != 0) {
                        return zZm3;
                    }
                    if (z2) {
                    }
                }
                return -1;
            }
            return 1;
        }
    }

    /* compiled from: AudioFocusManager.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    static class zZm implements Comparator<PJz.BIo> {
        @Override // java.util.Comparator
        public int compare(PJz.BIo bIo, PJz.BIo bIo2) {
            return bIo.ordinal() - bIo2.ordinal();
        }

        public int zZm(PJz.BIo bIo, PJz.BIo bIo2) {
            return bIo.ordinal() - bIo2.ordinal();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AudioFocusManager.java */
    /* loaded from: classes.dex */
    public enum zyO {
        UNKNOWN,
        HAS_NEXT_ITEM,
        DONE
    }

    @Inject
    public OPl(AlexaClientEventBus alexaClientEventBus, AudioManager audioManager, QJr qJr, SKB skb, Khf khf) {
        this.jiA = alexaClientEventBus;
        this.Qle = audioManager;
        this.JTe = qJr;
        this.LPk = skb;
        this.zQM = khf;
        QJr qJr2 = this.JTe;
        qJr2.zyO.add(new BIo(null));
        this.jiA.zZm(this);
    }

    @VisibleForTesting
    public boolean BIo() {
        boolean z;
        if (this.zyO == null) {
            Log.e(zZm, "Requesting focus with a null AudioMetadata");
        }
        Log.i(zZm, "Requesting audio focus");
        boolean zZm2 = this.LPk.zZm();
        if (!this.yPL.get()) {
            z = this.Qle.isMusicActive();
            GeneratedOutlineSupport1.outline172("wasAnotherAppPlayingMusic: ", z);
        } else {
            z = false;
        }
        pWO pwo = null;
        int i = Build.VERSION.SDK_INT;
        if (this.lOf != null) {
            C0179Pya.zZm(C0179Pya.zZm("Setting old request as inactive: "), this.lOf, zZm);
            this.lOf.zQM.zZm(false);
            pwo = this.lOf;
        }
        this.lOf = this.zQM.zZm(this.zyO, this);
        C0179Pya.zZm(C0179Pya.zZm("Current audio focus request: "), this.lOf, zZm);
        int requestAudioFocus = this.Qle.requestAudioFocus(this.lOf.BIo);
        int focusGain = this.lOf.BIo.getFocusGain();
        if (requestAudioFocus == 1) {
            GeneratedOutlineSupport1.outline149("focusValue for the requested audiofocus: ", focusGain);
            this.BIo = focusGain;
            StringBuilder zZm3 = C0179Pya.zZm("currentAudioFocus: ");
            zZm3.append(this.BIo);
            zZm3.toString();
            this.jiA.jiA(new ApR(((PNy) this.zyO).BIo, z, zZm2));
            if (pwo != null) {
                int i2 = Build.VERSION.SDK_INT;
                this.Qle.abandonAudioFocusRequest(pwo.BIo);
            }
            return true;
        }
        return false;
    }

    @Subscribe
    public void on(CKO cko) {
        if (!this.HvC.equals(zyO.UNKNOWN)) {
            int i = nhT.BIo[((IyB) cko).BIo.ordinal()];
            if (i != 1 && i != 2) {
                this.HvC = zyO.DONE;
            }
            zQM();
        }
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) {
        GeneratedOutlineSupport1.outline149("onAudioFocusChange: ", i);
        synchronized (this.JTe) {
            this.BIo = i;
            StringBuilder sb = new StringBuilder();
            sb.append("currentAudioFocus: ");
            sb.append(this.BIo);
            sb.toString();
            if (i == -3) {
                this.yPL.set(false);
                this.JTe.zZm();
            } else if (i == -2) {
                this.yPL.set(false);
                this.JTe.zzR();
            } else if (i == -1) {
                this.jiA.jiA(new DeR());
                this.yPL.set(false);
                this.Mlj.set(false);
                this.JTe.uzr();
                this.JTe.zyO();
            } else if (i == 1) {
                this.yPL.set(true);
                this.JTe.jiA();
                this.JTe.BIo();
            }
        }
    }

    public final void zQM() {
        PJz first;
        if (this.JTe.yPL()) {
            Log.i(zZm, "Nothing requires audio focus");
            zZm();
        } else {
            Iterator<TreeSet<PJz>> it2 = this.zzR.values().iterator();
            while (true) {
                if (it2.hasNext()) {
                    first = it2.next().first();
                    if (first.zZm()) {
                        break;
                    }
                } else {
                    first = this.zzR.firstEntry().getValue().first();
                    break;
                }
            }
            if (!this.Mlj.get() && first.zZm(this.zyO)) {
                if (first.zZm()) {
                    this.zyO = first;
                    if (!BIo()) {
                        this.JTe.dMe();
                        StringBuilder zZm2 = C0179Pya.zZm("Failed to acquire audio focus ");
                        zZm2.append(((PNy) first).BIo);
                        zZm2.toString();
                        this.yPL.set(false);
                    } else {
                        StringBuilder zZm3 = C0179Pya.zZm("Succeeded in acquiring audio focus ");
                        PNy pNy = (PNy) first;
                        zZm3.append(pNy.BIo);
                        zZm3.toString();
                        this.yPL.set(true);
                        if (PJz.BIo.PERSISTENT == pNy.BIo) {
                            this.Mlj.set(true);
                        }
                    }
                } else {
                    zZm();
                }
            }
        }
        boolean z = false;
        boolean z2 = false;
        for (TreeSet<PJz> treeSet : this.zzR.values()) {
            Iterator<PJz> it3 = treeSet.iterator();
            while (it3.hasNext()) {
                PNy pNy2 = (PNy) it3.next();
                if (pNy2.jiA) {
                    if (pNy2.BIo == PJz.BIo.PERSISTENT) {
                        z = true;
                    } else {
                        z2 = true;
                    }
                }
            }
        }
        boolean z3 = !this.dMe && z;
        boolean z4 = this.dMe && !z;
        boolean z5 = !this.uzr && z2;
        boolean z6 = this.uzr && !z2;
        if (z3) {
            this.jiA.zyO(qZM.zZm(AlexaPlaybackState.STOPPABLE_AND_NAVIGABLE));
        } else if ((z5 && !this.dMe) || (z4 && this.uzr)) {
            this.jiA.zyO(qZM.zZm(AlexaPlaybackState.STOPPABLE));
        } else if (z4 || (z6 && !this.dMe)) {
            int ordinal = this.HvC.ordinal();
            if (ordinal == 0 || ordinal == 1) {
                return;
            }
            this.jiA.zyO(qZM.zZm(AlexaPlaybackState.NONE));
        }
        if (z4) {
            this.dMe = false;
        } else if (z3) {
            this.dMe = true;
        } else if (z6) {
            this.uzr = false;
        } else if (z5) {
            this.uzr = true;
        }
    }

    public void zZm(tYL tyl, boolean z) {
        if (!AvsApiConstants.AudioPlayer.zQM.equals(tyl.zZm())) {
            if (z) {
                tyl.jiA();
                return;
            } else {
                tyl.zyO();
                return;
            }
        }
        synchronized (this.JTe) {
            int i = this.BIo;
            if (i == -3) {
                tyl.zyO();
            } else if (i == -2) {
                tyl.yPL();
            } else if (i != -1) {
                if (z) {
                    tyl.jiA();
                } else {
                    tyl.zyO();
                }
            } else if (tyl.Qle.zZm()) {
                tyl.zQM();
            }
        }
    }

    @VisibleForTesting
    public void zyO() {
        this.zzR = new TreeMap<>();
        for (tYL tyl : this.JTe.Qle()) {
            PJz pJz = tyl.Qle;
            PJz.BIo bIo = ((PNy) pJz).BIo;
            TreeSet<PJz> treeSet = this.zzR.get(bIo);
            if (treeSet == null) {
                treeSet = new TreeSet<>(new zQM());
            }
            treeSet.add(pJz);
            this.zzR.put(bIo, treeSet);
        }
        zQM();
    }

    @Subscribe
    public void on(BBo bBo) {
        this.HvC = zyO.UNKNOWN;
        zQM();
    }

    @Subscribe
    public void on(bOH boh) {
        this.HvC = ((C0212jfo) boh).BIo ? zyO.HAS_NEXT_ITEM : zyO.DONE;
        zQM();
    }

    public final void zZm() {
        if (this.Mlj.get()) {
            this.zyO = null;
            return;
        }
        if (this.yPL.get()) {
            Log.i(zZm, "Abandoning audio focus");
            int i = Build.VERSION.SDK_INT;
            if (this.zyO != null) {
                C0179Pya.zZm(C0179Pya.zZm("Abandoned focus request was: "), this.lOf, zZm);
                this.Qle.abandonAudioFocusRequest(this.lOf.BIo);
                this.lOf = null;
            }
            this.yPL.set(false);
            this.jiA.jiA(new DeR());
        }
        this.zyO = null;
    }
}
