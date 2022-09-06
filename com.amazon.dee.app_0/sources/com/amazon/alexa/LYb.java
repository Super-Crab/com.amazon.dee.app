package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.utils.TimeProvider;
import dagger.Lazy;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: DialogInteractionMetricTask.java */
/* loaded from: classes.dex */
public abstract class LYb implements Runnable {
    public AlexaClientEventBus BIo;
    public Map<DialogRequestIdentifier, XWx> JTe;
    public Map<XWx, WSC> Qle;
    public IUt jiA;
    public Lazy<PersistentStorage> zQM;
    public TimeProvider zZm;
    public AtomicReference<WSC> zyO;

    public abstract String BIo();

    public abstract void BIo(WSC wsc);

    public abstract void BIo(WSC wsc, Pmp pmp, @Nullable pRk prk, @Nullable Map<String, String> map, long j);

    public boolean BIo(DialogRequestIdentifier dialogRequestIdentifier) {
        if (this.JTe.containsKey(dialogRequestIdentifier)) {
            return BIo(this.JTe.get(dialogRequestIdentifier));
        }
        return false;
    }

    public boolean zQM() {
        return !WSC.BIo.equals(this.zyO.get());
    }

    public WSC zZm() {
        return this.zyO.get();
    }

    public abstract void zZm(WSC wsc);

    public void zyO() {
        BIo(WSC.BIo);
    }

    public boolean zZm(XWx xWx) {
        WSC zZm = zZm();
        if (!zQM() || ((nFo) zZm).zQM != xWx) {
            Log.e(BIo(), String.format("Received unexpected result for %s while %s is in progress", xWx, ((nFo) zZm).zQM));
            return false;
        }
        return true;
    }

    public boolean BIo(XWx xWx) {
        if (this.Qle.containsKey(xWx)) {
            this.Qle.remove(xWx);
            if (!this.JTe.values().contains(xWx)) {
                return false;
            }
            this.JTe.values().remove(xWx);
            return true;
        }
        return false;
    }

    public boolean zZm(DialogRequestIdentifier dialogRequestIdentifier) {
        WSC wsc = this.zyO.get();
        if (!zQM() || ((nFo) wsc).zyO != dialogRequestIdentifier) {
            Log.e(BIo(), String.format("Received unexpected result for %s while %s is in progress", dialogRequestIdentifier, ((nFo) this.zyO.get()).zyO));
            return false;
        }
        return true;
    }

    public void zZm(pRk prk, long j) {
        zZm(prk, (Map<String, String>) null, j);
    }

    public void zZm(pRk prk, @Nullable Map<String, String> map, long j) {
        WSC zZm = zZm();
        Log.i(BIo(), String.format("Reporting failure for %s (%s)", ((nFo) zZm).zQM, prk));
        Pmp type = prk.getType();
        zZm(zZm);
        BIo(zZm, type, prk, map, j);
    }

    public void zZm(WSC wsc, Pmp pmp, @Nullable pRk prk, long j) {
        zZm(wsc);
        BIo(wsc, pmp, prk, null, j);
    }

    public void zZm(WSC wsc, Pmp pmp, @Nullable pRk prk, @Nullable Map<String, String> map, long j) {
        zZm(wsc);
        BIo(wsc, pmp, prk, map, j);
    }

    @Nullable
    public pRk zZm(VZt vZt, pRk[] prkArr, boolean z) {
        for (pRk prk : prkArr) {
            prk.zZm(z);
            if (prk.zZm() != null && prk.zZm().getValue().equals(((huZ) vZt).BIo)) {
                return prk;
            }
        }
        return null;
    }

    public boolean zZm(XWx xWx, WSC wsc) {
        if (!this.Qle.containsKey(xWx)) {
            this.Qle.put(xWx, wsc);
            return true;
        }
        return false;
    }

    public boolean zZm(DialogRequestIdentifier dialogRequestIdentifier, XWx xWx) {
        if (this.Qle.containsKey(xWx)) {
            this.JTe.put(dialogRequestIdentifier, xWx);
            return true;
        }
        return false;
    }
}
