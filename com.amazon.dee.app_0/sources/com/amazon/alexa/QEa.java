package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.QYV;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaDataSink;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogTurnMetricsCallback;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import com.amazon.alexa.api.LaunchType;
import com.amazon.alexa.client.alexaservice.audioprovider.AlexaAudioSource;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.kbp;
/* compiled from: AbstractStartDialogTurnCallbacks.java */
/* loaded from: classes.dex */
public abstract class QEa {
    public final esV BIo;
    public final iKQ JTe;
    public final LjN LPk;
    public final snw Qle;
    public final yaw jiA;
    public final XWx yPL;
    public final NEe zQM;
    public final AlexaClientEventBus zZm;
    public final shl zyO;

    public QEa(AlexaClientEventBus alexaClientEventBus, esV esv, NEe nEe, shl shlVar, yaw yawVar, snw snwVar, iKQ ikq, LjN ljN, XWx xWx) {
        this.zZm = alexaClientEventBus;
        this.BIo = esv;
        this.zQM = nEe;
        this.zyO = shlVar;
        this.jiA = yawVar;
        this.Qle = snwVar;
        this.JTe = ikq;
        this.LPk = ljN;
        this.yPL = xWx;
        LaunchType launchType = this.zQM.jiA().getLaunchType();
        this.zZm.zyO(new CSi(this.yPL, this.zQM.jiA().getInvocationType(), ((NND) this.zQM.lOf()).BIo, LaunchType.TEXT.equals(launchType)));
    }

    public String getDialogTurnId() {
        return this.yPL.getValue();
    }

    public void zZm(AlexaAudioSink alexaAudioSink, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, AlexaAudioMetadata alexaAudioMetadata, AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        zZm(false, alexaAudioSink, alexaDialogTurnStopCallback, alexaAudioMetadata, alexaDialogTurnMetricsCallback, null, null);
    }

    public abstract boolean zZm();

    public void zZm(AlexaAudioSink alexaAudioSink, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        zZm(false, alexaAudioSink, alexaDialogTurnStopCallback, null, alexaDialogTurnMetricsCallback, null, null);
    }

    public final void zZm(boolean z, AlexaAudioSink alexaAudioSink, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaAudioMetadata alexaAudioMetadata, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback, @Nullable AlexaDataSink alexaDataSink, @Nullable AlexaDialogExtras alexaDialogExtras) {
        AlexaAudioSource zZm;
        Lzl zZm2;
        Kqq zZm3;
        if (alexaAudioMetadata != null) {
            zZm = this.jiA.zZm(alexaAudioSink, alexaAudioMetadata.getAlexaAudioFormat());
        } else {
            zZm = this.jiA.zZm(alexaAudioSink);
        }
        if (alexaDataSink != null) {
            zZm2 = this.JTe.zZm(this.zyO, zZm, this.jiA.zZm(alexaDataSink));
        } else {
            zZm2 = this.JTe.zZm(this.zyO, zZm);
        }
        OGm zZm4 = this.Qle.zZm(this.zZm, this.BIo, zZm2, this.LPk, this.yPL, alexaDialogTurnStopCallback, alexaDialogTurnMetricsCallback);
        if (alexaAudioMetadata != null) {
            zZm4.lOf = alexaAudioMetadata;
        }
        boolean z2 = false;
        if (alexaDialogExtras != null) {
            z2 = alexaDialogExtras.suppressWakewordVerification();
            this.zQM.zZm(alexaDialogExtras);
            this.zZm.zyO(new ghu(zZm4.Qle(), null, alexaDialogExtras.getInvocationType()));
        }
        if (this.zQM.zZm(zZm4)) {
            if (z && !z2) {
                zZm3 = new xWg(this.zQM);
            } else {
                zZm4.jiA().LPk();
                zZm3 = QYV.Qle.zZm(this.zQM);
            }
            this.zZm.zyO(zZm3);
            return;
        }
        this.zZm.zyO(kbp.zZm.zZm(zZm4.Qle(), zZm() ? mMl.OUT_OF_TURN_UNEXPECTED_TURN : mMl.OUT_OF_TURN_UNEXPECTED_NEXT_TURN));
    }

    public void zZm(cIy ciy, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, AlexaAudioMetadata alexaAudioMetadata, AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback, AlexaDialogExtras alexaDialogExtras) {
        OGm zZm = this.Qle.zZm(this.zZm, this.BIo, this.JTe.zZm(this.zyO, ciy), this.LPk, this.yPL, alexaDialogTurnStopCallback, alexaDialogTurnMetricsCallback);
        if (alexaAudioMetadata != null) {
            zZm.lOf = alexaAudioMetadata;
        }
        if (alexaDialogExtras != null) {
            this.zQM.zZm(alexaDialogExtras);
            this.zZm.zyO(GUc.zZm(zZm.Qle(), alexaDialogExtras.getInvocationType()));
        }
        if (this.zQM.zZm(zZm)) {
            this.zZm.zyO(QYV.Qle.zZm(this.zQM));
        } else {
            this.zZm.zyO(kbp.zZm.zZm(zZm.Qle(), zZm() ? mMl.OUT_OF_TURN_UNEXPECTED_TURN : mMl.OUT_OF_TURN_UNEXPECTED_NEXT_TURN));
        }
    }
}
