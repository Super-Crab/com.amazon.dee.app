package com.amazon.alexa;

import android.media.AudioManager;
import android.os.Build;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: VolumeAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class QMz {
    public static final String zZm = "QMz";
    public final AudioManager BIo;
    public boolean Qle;
    public final AlexaClientEventBus zQM;
    public final wVr zyO;
    public boolean jiA = zQM();
    public int JTe = zyO();

    @Inject
    public QMz(AlexaClientEventBus alexaClientEventBus, AudioManager audioManager, wVr wvr) {
        this.zQM = alexaClientEventBus;
        this.BIo = audioManager;
        this.zyO = wvr;
    }

    public final void BIo(int i, boolean z) {
        this.zQM.zyO(JjI.BIo().zZm(Message.create(Header.builder().setNamespace(AvsApiConstants.Speaker.zZm).setName(AvsApiConstants.Speaker.Events.VolumeChanged.zZm).build(), VCD.zZm().zZm(z).zZm(i).zZm())).zZm());
    }

    public final boolean zQM() {
        int i = Build.VERSION.SDK_INT;
        return this.BIo.isStreamMute(BIo());
    }

    public int zZm() {
        return BIo(zyO());
    }

    public final int zyO() {
        return this.BIo.getStreamVolume(BIo());
    }

    public void zZm(boolean z) {
        String.format("setMute: %d %s", Integer.valueOf(zyO()), Boolean.valueOf(z));
        String.format("updateSystemMute: %d %s %s", Integer.valueOf(zyO()), Boolean.valueOf(this.jiA), this);
        this.jiA = z;
        int i = Build.VERSION.SDK_INT;
        this.BIo.adjustSuggestedStreamVolume(this.jiA ? -100 : 100, BIo(), 0);
        this.zyO.zZm(z);
        zZm(BIo(zyO()), this.jiA);
        String.format("updateSystemMute after: %d %s %s", Integer.valueOf(zyO()), Boolean.valueOf(this.jiA), this);
    }

    public final int BIo(long j) {
        return (int) Math.round((j * 100.0d) / this.BIo.getStreamMaxVolume(BIo()));
    }

    public final void zZm(int i, boolean z) {
        this.zQM.zyO(JjI.BIo().zZm(Message.create(Header.builder().setNamespace(AvsApiConstants.Speaker.zZm).setName(AvsApiConstants.Speaker.Events.MuteChanged.zZm).build(), VCD.zZm().zZm(z).zZm(i).zZm())).zZm());
    }

    public final int BIo() {
        if (this.Qle) {
            return 6;
        }
        int mode = this.BIo.getMode();
        if (mode != 2 && mode != 3) {
            return 3;
        }
        return this.BIo.isBluetoothScoOn() ? 6 : 0;
    }

    public final int zZm(long j) {
        return (int) ((j * this.BIo.getStreamMaxVolume(BIo())) / 100.0d);
    }

    public final void zZm(int i) {
        String.format("updateSystemVolume: %d %s %s", Integer.valueOf(zyO()), Boolean.valueOf(this.jiA), this);
        this.BIo.setStreamVolume(BIo(), i, 0);
        BIo(BIo(zyO()), zQM());
        String.format("updateSystemVolume after: %d %s %s", Integer.valueOf(zyO()), Boolean.valueOf(this.jiA), this);
    }
}
