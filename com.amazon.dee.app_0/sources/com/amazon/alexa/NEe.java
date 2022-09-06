package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.DialogExtras;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.fuM;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
/* compiled from: MultiTurnDialog.java */
/* loaded from: classes.dex */
public class NEe {
    public static final String zZm = "NEe";
    public final ExtendedClient BIo;
    public final jSO JTe;
    public final rJn LPk;
    public final LjN Qle;
    public final eeF dMe;
    public final Xtl jiA;
    public final boolean uzr;
    public AlexaDialogExtras yPL;
    public boolean zzR;
    public final qSf zQM = qSf.zZm();
    public XWx lOf = XWx.zZm;
    public final LinkedList<OGm> zyO = new LinkedList<>();
    public zZm Mlj = zZm.CREATED;

    /* compiled from: MultiTurnDialog.java */
    /* loaded from: classes.dex */
    public enum zZm {
        UNKNOWN,
        CREATED,
        READY,
        STARTED,
        FINISHED
    }

    public NEe(ExtendedClient extendedClient, Xtl xtl, LjN ljN, jSO jso, rJn rjn, eeF eef, boolean z) {
        this.BIo = extendedClient;
        this.jiA = xtl;
        this.Qle = ljN;
        this.JTe = jso;
        this.LPk = rjn;
        this.dMe = eef;
        this.uzr = z;
    }

    public static /* synthetic */ void zZm(NEe nEe) {
        nEe.NXS();
    }

    @Nullable
    public OGm BIo(DialogRequestIdentifier dialogRequestIdentifier) {
        Iterator<OGm> it2 = this.zyO.iterator();
        while (it2.hasNext()) {
            OGm next = it2.next();
            DialogRequestIdentifier zyO = next.zyO();
            if (zyO != null && zyO.equals(dialogRequestIdentifier)) {
                return next;
            }
        }
        return null;
    }

    public synchronized boolean CGv() {
        if (this.Mlj == zZm.READY) {
            this.Mlj = zZm.STARTED;
            this.jiA.zZm(this.zQM);
            return true;
        }
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Attempted to start dialog when dialogState was: ");
        zZm2.append(this.Mlj);
        Log.e(str, zZm2.toString());
        return false;
    }

    public synchronized boolean HvC() {
        boolean z;
        z = true;
        if (this.zyO.size() != 1) {
            z = false;
        }
        return z;
    }

    @Nullable
    public synchronized cIy JTe() {
        if (!dMe()) {
            return null;
        }
        return yPL().zQM();
    }

    public synchronized DialogRequestIdentifier LPk() {
        if (!dMe()) {
            return DialogRequestIdentifier.NONE;
        }
        return yPL().zyO();
    }

    public synchronized XWx Mlj() {
        if (!dMe()) {
            return XWx.zZm;
        }
        return yPL().Qle();
    }

    public final void NXS() {
        this.Mlj = zZm.FINISHED;
        this.jiA.BIo(this.zQM);
        Iterator<OGm> it2 = this.zyO.iterator();
        while (it2.hasNext()) {
            it2.next().zyO.yPL();
        }
        this.zyO.clear();
    }

    public synchronized boolean Qgh() {
        boolean z;
        z = true;
        if (this.zyO.size() <= 1) {
            z = false;
        }
        return z;
    }

    @Nullable
    public synchronized cIy Qle() {
        if (!dMe()) {
            return null;
        }
        return yPL().BIo();
    }

    public synchronized boolean Tbw() {
        return this.Mlj == zZm.READY;
    }

    public synchronized boolean XWf() {
        return this.Mlj == zZm.STARTED;
    }

    public synchronized boolean dMe() {
        if (!this.zyO.isEmpty() && this.Mlj != zZm.FINISHED) {
            return !this.zyO.getLast().LPk();
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && NEe.class == obj.getClass()) {
            return Objects.equals(this.zQM, ((NEe) obj).zQM);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.zQM);
    }

    public synchronized AlexaDialogExtras jiA() {
        AlexaDialogExtras alexaDialogExtras = this.yPL;
        if (alexaDialogExtras == null) {
            return DialogExtras.zZm;
        }
        return alexaDialogExtras;
    }

    public synchronized eeF lOf() {
        return this.dMe;
    }

    public synchronized boolean noQ() {
        if (!dMe()) {
            return false;
        }
        return yPL().Mlj();
    }

    public synchronized void uuO() {
        this.zzR = true;
    }

    public synchronized boolean uzr() {
        boolean z = false;
        if (!dMe()) {
            return false;
        }
        if (JTe() != null) {
            z = true;
        }
        return z;
    }

    public synchronized boolean vkx() {
        boolean z;
        if (this.Mlj != zZm.FINISHED) {
            if (!XWx.zZm.equals(this.lOf)) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    public synchronized boolean wDP() {
        boolean z;
        if (this.Mlj != zZm.FINISHED) {
            if (this.zzR) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    @Nullable
    public synchronized OGm yPL() {
        if (dMe()) {
            return this.zyO.getLast();
        }
        return null;
    }

    public synchronized boolean zOR() {
        if (this.Mlj == zZm.STARTED && dMe() && !yPL().Mlj()) {
            yPL().zZm(this.JTe.zZm(this.uzr ? DialogRequestIdentifier.createRandomForText() : DialogRequestIdentifier.createRandom()));
            return true;
        }
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Attempted to start a dialog turn when dialogState was: ");
        zZm2.append(this.Mlj);
        Log.e(str, zZm2.toString());
        return false;
    }

    public synchronized void zQM() {
        zZm zzm = this.Mlj;
        if (zzm != zZm.FINISHED) {
            if (zzm != zZm.STARTED) {
                this.jiA.zZm(this.zQM);
            }
            if (dMe()) {
                OGm yPL = yPL();
                if (!yPL.LPk()) {
                    yPL.BIo(new UjQ(this));
                }
            } else {
                NXS();
            }
        } else {
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Attempting to finish a dialog when already finished. Dialog: ");
            zZm2.append(zzR());
            Log.w(str, zZm2.toString());
        }
    }

    @Nullable
    public synchronized AlexaAudioMetadata zyO() {
        AlexaAudioMetadata alexaAudioMetadata;
        if (!this.zyO.isEmpty()) {
            OGm yPL = yPL();
            return (yPL == null || (alexaAudioMetadata = yPL.lOf) == null) ? this.zyO.getFirst().lOf : alexaAudioMetadata;
        }
        return null;
    }

    public synchronized qSf zzR() {
        return this.zQM;
    }

    public synchronized boolean zZm(esV esv, AlexaDialogRequest alexaDialogRequest) {
        if (this.Mlj == zZm.CREATED) {
            this.zzR = true;
            this.lOf = this.uzr ? XWx.BIo() : XWx.zZm();
            this.jiA.zZm(this.LPk.zZm(this, esv, this.Qle, this.lOf), alexaDialogRequest);
            return true;
        }
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Attempting to request the first turn when in state: ");
        zZm2.append(this.Mlj);
        zZm2.append(". Dialog: ");
        zZm2.append(zzR());
        Log.e(str, zZm2.toString());
        return false;
    }

    public synchronized void BIo() {
        this.zzR = false;
    }

    public synchronized boolean zZm(OGm oGm) {
        if (wDP() && this.lOf.equals(oGm.Qle())) {
            if (dMe()) {
                OGm yPL = yPL();
                if (!yPL.LPk()) {
                    Log.w(zZm, "Current turn was finished by being replaced. This is not expected.");
                    yPL.zZm();
                }
            }
            this.zyO.add(oGm);
            this.zzR = false;
            this.lOf = XWx.zZm;
            zZm();
            return true;
        }
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("This should never happen, but an unexpected turn (");
        zZm2.append(oGm.Qle());
        zZm2.append(") was added to dialog (");
        zZm2.append(this.zQM);
        zZm2.append("). Abandoning...");
        Log.e(str, zZm2.toString());
        this.lOf = XWx.zZm;
        oGm.zZm();
        zQM();
        return false;
    }

    public synchronized void zZm(esV esv) {
        if (this.Mlj == zZm.STARTED) {
            this.zzR = true;
            this.lOf = XWx.zZm();
            this.jiA.zZm(this.LPk.BIo(this, esv, this.Qle, this.lOf));
        } else {
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Attempting to request the next turn when in state: ");
            zZm2.append(this.Mlj);
            zZm2.append(". Dialog: ");
            zZm2.append(zzR());
            Log.e(str, zZm2.toString());
        }
    }

    public synchronized void zZm(fuM.zyO zyo) {
        if (dMe()) {
            OGm yPL = yPL();
            if (yPL != null && yPL.yPL()) {
                yPL.zZm(zyo);
            }
        } else {
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Attempting to stop recording for a dialog when there is no current turn. Dialog: ");
            zZm2.append(zzR());
            Log.e(str, zZm2.toString());
        }
    }

    public synchronized boolean zZm(DialogRequestIdentifier dialogRequestIdentifier) {
        return BIo(dialogRequestIdentifier) != null;
    }

    public synchronized void zZm(AlexaDialogExtras alexaDialogExtras) {
        this.yPL = alexaDialogExtras;
        zZm();
    }

    public final void zZm() {
        if ((this.uzr || zyO() != null) && this.yPL != null && this.zyO.size() == 1) {
            this.Mlj = zZm.READY;
        }
    }
}
