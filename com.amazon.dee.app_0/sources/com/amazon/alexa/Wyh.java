package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.QYV;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.DialogExtras;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.fuM;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: MultiTurnDialogAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class Wyh {
    public static final String zZm = "Wyh";
    public final AlexaClientEventBus BIo;
    public qSf jiA;
    public final QCK zQM;
    public final Map<qSf, NEe> zyO = new HashMap();

    @Inject
    public Wyh(AlexaClientEventBus alexaClientEventBus, QCK qck) {
        this.BIo = alexaClientEventBus;
        this.zQM = qck;
        alexaClientEventBus.zZm(this);
    }

    public synchronized NEe BIo(ExtendedClient extendedClient, Xtl xtl, LjN ljN, eeF eef) {
        NEe nEe;
        QCK qck = this.zQM;
        nEe = new NEe(extendedClient, xtl, ljN, qck.zZm, qck.BIo, eef, true);
        this.zyO.put(nEe.zzR(), nEe);
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Created dialog: ");
        zZm2.append(nEe.zzR());
        Log.i(str, zZm2.toString());
        return nEe;
    }

    @Subscribe
    public synchronized void on(fuM.zQM zqm) {
        if (zyO()) {
            BIo().zZm(((VJa) zqm).BIo);
        }
    }

    public synchronized boolean zQM(NEe nEe) {
        if (!this.zyO.containsKey(nEe.zzR())) {
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Attempted to start a dialog not created by the authority: ");
            zZm2.append(nEe.zzR());
            Log.e(str, zZm2.toString());
            nEe.zQM();
            return false;
        } else if (nEe.XWf()) {
            String str2 = zZm;
            StringBuilder zZm3 = C0179Pya.zZm("Attempted to start a dialog which has already started: ");
            zZm3.append(nEe.zzR());
            Log.e(str2, zZm3.toString());
            return false;
        } else {
            if (zyO()) {
                NEe zZm4 = zZm(this.jiA);
                if (zZm4.noQ()) {
                    nEe.zQM();
                    return false;
                } else if (zZm4.XWf()) {
                    zZm4.zQM();
                }
            }
            this.jiA = nEe.zzR();
            nEe.CGv();
            C0179Pya.zZm(C0179Pya.zZm("Started dialog: "), this.jiA, zZm);
            return true;
        }
    }

    public synchronized NEe zZm(ExtendedClient extendedClient, Xtl xtl, LjN ljN, eeF eef) {
        NEe zZm2;
        zZm2 = this.zQM.zZm(extendedClient, xtl, ljN, eef, false);
        this.zyO.put(zZm2.zzR(), zZm2);
        String str = zZm;
        StringBuilder zZm3 = C0179Pya.zZm("Created dialog: ");
        zZm3.append(zZm2.zzR());
        Log.i(str, zZm3.toString());
        return zZm2;
    }

    public synchronized boolean zyO() {
        StringBuilder zZm2 = C0179Pya.zZm("    hasCurrentAudioProvider: ");
        zZm2.append(this.jiA);
        zZm2.toString();
        return this.jiA != null;
    }

    public synchronized boolean zZm(esV esv) {
        Log.i(zZm, "Requesting turn.");
        if (zyO()) {
            NEe BIo = BIo();
            if (!BIo.vkx() && !BIo.noQ()) {
                if (BIo.XWf()) {
                    String str = zZm;
                    StringBuilder zZm2 = C0179Pya.zZm("Requesting from the current dialog: ");
                    zZm2.append(BIo.zzR());
                    Log.i(str, zZm2.toString());
                    BIo.zZm(esv);
                    return true;
                }
                Log.i(zZm, "A new dialog has been created already, waiting for its first turn.");
                return false;
            }
            Log.i(zZm, "The current dialog is already in a turn, ignoring this request.");
            return false;
        }
        Log.i(zZm, "No current dialog, requesting a new one from available providers.");
        this.BIo.zyO(QYV.BIo.zZm(esv, AlexaDialogRequest.builder().setInvocationType(AlexaMetricsConstants.EventConstants.INVOCATION_TYPE_NEW_DIALOG_REQUEST).build(), DialogExtras.zZm));
        return true;
    }

    public synchronized boolean BIo(NEe nEe) {
        boolean z;
        qSf qsf = this.jiA;
        if (qsf != null) {
            if (qsf.equals(nEe.zzR())) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    public synchronized NEe BIo() {
        return zZm(this.jiA);
    }

    @Nullable
    public synchronized OGm BIo(DialogRequestIdentifier dialogRequestIdentifier) {
        for (NEe nEe : this.zyO.values()) {
            if (nEe.zZm(dialogRequestIdentifier)) {
                return nEe.BIo(dialogRequestIdentifier);
            }
        }
        return null;
    }

    @Nullable
    public synchronized XWx zQM() {
        if (zyO()) {
            return BIo().Mlj();
        }
        return null;
    }

    public synchronized void zZm(NEe nEe) {
        if (!this.zyO.containsKey(nEe.zzR())) {
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Attempted to finish a dialog not created by the authority");
            zZm2.append(nEe.zzR());
            zZm2.append(". Finishing anyways.");
            Log.w(str, zZm2.toString());
        }
        if (zyO() && this.jiA.equals(nEe.zzR())) {
            this.jiA = null;
        }
        String str2 = zZm;
        StringBuilder zZm3 = C0179Pya.zZm("Finishing dialog: ");
        zZm3.append(nEe.zzR());
        Log.i(str2, zZm3.toString());
        nEe.zQM();
    }

    public synchronized void zZm() {
        if (zyO()) {
            NEe BIo = BIo();
            DialogRequestIdentifier LPk = BIo.LPk();
            zZm(BIo);
            if (LPk != null && !DialogRequestIdentifier.NONE.equals(LPk)) {
                this.BIo.zyO(new YAN(LPk));
            }
        }
    }

    @Nullable
    public synchronized NEe zZm(qSf qsf) {
        return this.zyO.get(qsf);
    }

    @Nullable
    public synchronized NEe zZm(DialogRequestIdentifier dialogRequestIdentifier) {
        for (NEe nEe : this.zyO.values()) {
            if (nEe.zZm(dialogRequestIdentifier)) {
                return nEe;
            }
        }
        return null;
    }
}
