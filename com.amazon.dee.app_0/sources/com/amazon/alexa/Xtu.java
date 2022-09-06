package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.capabilities.CapabilityInterface;
import com.amazon.alexa.client.core.messages.Namespace;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: OverridableInternalComponentStateProvider.java */
/* loaded from: classes.dex */
public abstract class Xtu implements dRG {
    public static final String zZm = "Xtu";
    public final lEV BIo;
    public final LrI zQM;
    public boolean zyO = false;
    public boolean jiA = false;
    public boolean Qle = false;

    public Xtu(AlexaClientEventBus alexaClientEventBus, lEV lev, LrI lrI) {
        this.BIo = lev;
        this.zQM = lrI;
        alexaClientEventBus.zZm(this);
    }

    public abstract CapabilityInterface BIo();

    public final void Qle() {
        if (this.jiA && !this.Qle) {
            if (this.zyO) {
                return;
            }
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Registering internal component state provider for: ");
            zZm2.append(BIo());
            Log.i(str, zZm2.toString());
            this.zyO = true;
            this.BIo.zZm(this);
            jiA();
        } else if (!this.zyO) {
        } else {
            String str2 = zZm;
            StringBuilder zZm3 = C0179Pya.zZm("Deregistering internal component state provider for: ");
            zZm3.append(BIo());
            Log.i(str2, zZm3.toString());
            this.zyO = false;
            this.BIo.zZm.remove(zZm());
            zyO();
        }
    }

    public void jiA() {
    }

    @Subscribe
    public void on(UMd uMd) {
        this.jiA = this.zQM.zZm(zQM());
        Qle();
    }

    public abstract Namespace zQM();

    public void zyO() {
    }

    @Subscribe
    public void on(NUK nuk) {
        xTZ xtz = (xTZ) nuk;
        if (xtz.BIo.contains(zQM())) {
            this.Qle = xtz.zQM;
        }
        Qle();
    }
}
