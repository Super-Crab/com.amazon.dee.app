package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: PublishCapabilitiesChain.java */
@Singleton
/* loaded from: classes.dex */
public class xQf implements yDI {
    public static final String zZm = "xQf";
    @VisibleForTesting
    public final yDI BIo;
    public IHN Qle = IHN.zZm;
    public final AlexaClientEventBus jiA;
    public final pZY zQM;
    public final WnL zyO;

    @Inject
    public xQf(pZY pzy, @Named("SYNCHRONIZE_STATE_CHAIN") yDI ydi, WnL wnL, AlexaClientEventBus alexaClientEventBus) {
        this.zQM = pzy;
        this.zyO = wnL;
        this.BIo = ydi;
        this.jiA = alexaClientEventBus;
        this.jiA.zZm(this);
    }

    @Subscribe
    public void on(AbstractC0177Pjz abstractC0177Pjz) {
        this.zyO.LPk = false;
        this.BIo.zZm(this.Qle);
        this.Qle = IHN.zZm;
    }

    @Override // com.amazon.alexa.yDI
    public void teardown() {
        this.jiA.BIo(this);
        this.BIo.teardown();
    }

    @Override // com.amazon.alexa.yDI
    public void zZm(IHN ihn) {
        if (this.zyO.LPk) {
            if (this.zyO.jiA) {
                ihn.zZm(hlN.NOT_REFRESHED_EXTERNAL_CAPABILITIES);
                return;
            } else if (this.zyO.zyO) {
                ihn.zZm(hlN.NOT_REFRESHED_INTERNAL_CAPABILITIES);
                return;
            } else {
                this.Qle = ihn;
                this.zQM.BIo();
                return;
            }
        }
        this.BIo.zZm(ihn);
    }

    @Subscribe
    public void on(DLc dLc) {
        RBR rbr = (RBR) dLc;
        int ordinal = rbr.BIo.zQM.ordinal();
        if (ordinal == 0) {
            Log.w(zZm, "Failed to publish capabilities due to no network");
            WnL wnL = this.zyO;
            wnL.BIo = true;
            wnL.LPk = true;
        } else if (ordinal == 1) {
            Log.w(zZm, "Failed to publish capabilities due to invalid authorization");
            this.zyO.zQM = true;
        } else if (ordinal == 2) {
            Log.w(zZm, "Failed to publish capabilities due to AVS request failed");
            Integer num = rbr.BIo.zyO;
            if (num != null) {
                this.zyO.lOf = num;
            }
        } else if (ordinal == 3) {
            Log.w(zZm, "Failed to publish capabilities due to IOException");
            Exception exc = rbr.BIo.jiA;
            if (exc != null) {
                this.zyO.dMe = exc;
            }
        }
        if (rbr.zQM) {
            this.Qle.zZm(hlN.FAILED_TO_PUBLISH_CAPABILITIES);
            this.Qle = IHN.zZm;
        }
    }
}
