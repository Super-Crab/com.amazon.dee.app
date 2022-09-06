package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.Nyy;
import com.amazon.alexa.api.UserPerceivedLatencyData;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: UserPerceivedLatencyAudioTracker.java */
/* loaded from: classes.dex */
public class Ado implements LFH {
    public static final String zZm = "Ado";
    public final AlexaClientEventBus BIo;
    public final OGm JTe;
    public final long LPk;
    public long Mlj;
    public final DialogRequestIdentifier Qle;
    public final ZVp jiA;
    public boolean lOf;
    public long yPL;
    public final vrF zQM;
    public final String zyO;
    public long zzR;

    @Inject
    public Ado(AlexaClientEventBus alexaClientEventBus, vrF vrf, @Nullable String str, DialogRequestIdentifier dialogRequestIdentifier, OGm oGm, ZVp zVp, long j) {
        this.BIo = alexaClientEventBus;
        this.zQM = vrf;
        this.jiA = zVp;
        this.Qle = dialogRequestIdentifier;
        this.JTe = oGm;
        this.LPk = j;
        if (str != null) {
            this.zyO = str;
        } else {
            this.zyO = "UNDEFINED_INVOCATION_TYPE";
        }
        alexaClientEventBus.zZm(this);
    }

    @Override // com.amazon.alexa.LFH
    public synchronized void BIo(int i, long j) {
        zQM(i, j);
    }

    @Subscribe
    public synchronized void on(Yud yud) {
        this.Mlj = ((Sdw) yud).BIo;
        StringBuilder zZm2 = C0179Pya.zZm("on EndOfSpeechOffsetReceivedEvent: ");
        zZm2.append(this.Mlj);
        zZm2.toString();
        zZm();
    }

    @Override // com.amazon.alexa.LFH
    public synchronized void onError(Exception exc) {
    }

    public final void zQM(int i, long j) {
        String str = "updateStartAndEndRecordingTimes bytesWritten: " + i + ", elapsedRealTimeOfLastWrite: " + j + ", userSpeechFormat.getBytesPerSecond:" + this.jiA.zQM();
        long BIo = (long) (i / this.jiA.BIo());
        if (this.jiA.jiA()) {
            this.yPL = j - BIo;
            StringBuilder zZm2 = C0179Pya.zZm("Calculated start time as: ");
            zZm2.append(this.yPL);
            zZm2.append("ms");
            zZm2.toString();
        } else {
            this.yPL = this.LPk;
            StringBuilder zZm3 = C0179Pya.zZm("Falling back start time to: ");
            zZm3.append(this.yPL);
            zZm3.append("ms");
            zZm3.toString();
        }
        vrF vrf = this.zQM;
        long j2 = this.yPL;
        vrf.zyO = j2;
        if (vrf.zQM.isCurrentDeviceHandsFree()) {
            vrf.zZm.zZm(Nyy.zZm.AUDIO_START_TIME, null, (vrf.BIo.currentTimeMillis() - vrf.BIo.elapsedRealTime()) + j2);
        }
        zZm();
    }

    @Override // com.amazon.alexa.LFH
    public synchronized void zZm(int i, long j) {
        zQM(i, j);
    }

    public void BIo() {
        if (!this.lOf) {
            Log.i(zZm, "Finishing a UPL tracker without reporting a metric. This is expected during any barge in or if no speak directive arrived in response.");
        }
        this.lOf = true;
        this.BIo.BIo(this);
    }

    public final void zZm() {
        long j = this.yPL;
        if (j != 0) {
            long j2 = this.Mlj;
            if (j2 == 0) {
                return;
            }
            long j3 = this.zzR;
            if (j3 == 0) {
                return;
            }
            long j4 = j3 - (j + j2);
            StringBuilder outline111 = GeneratedOutlineSupport1.outline111("Calculated enhanced upl as: ", j4, "ms for: ");
            outline111.append(this.Qle);
            outline111.toString();
            if (this.lOf) {
                return;
            }
            this.BIo.zyO(new MTI(this.zyO, this.Qle, this.JTe.Qle(), this.Mlj, this.zzR, j4, this.jiA.Qle()));
            this.JTe.zZm(new UserPerceivedLatencyData(this.Qle.getValue(), j4, this.Mlj, j4, this.zzR));
            BIo();
        }
    }

    @Subscribe
    public synchronized void on(aJD ajd) {
        this.zzR = ((odt) ajd).zyO;
        StringBuilder zZm2 = C0179Pya.zZm("on PreciseDialogResponseEvent elapsedRealTimeOfSpeechStart: ");
        zZm2.append(this.zzR);
        zZm2.toString();
        zZm();
    }

    @Subscribe
    public synchronized void on(Obt obt) {
        if (!this.Qle.equals(((rtX) obt).BIo)) {
            BIo();
        }
    }
}
