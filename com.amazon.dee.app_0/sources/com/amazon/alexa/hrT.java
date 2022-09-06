package com.amazon.alexa;

import com.amazon.alexa.api.AlexaAttentionSystemSettingsListener;
import com.amazon.alexa.api.ExtendedClient;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AttentionSystemAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class hrT {
    public Boolean jiA;
    public final yGK zZm;
    public Boolean zyO;
    public final Object zQM = new Object();
    public final Shr<AlexaAttentionSystemSettingsListener> BIo = new Shr<>();

    @Inject
    public hrT(yGK ygk) {
        this.zZm = ygk;
    }

    public void BIo(boolean z) {
        synchronized (this.zQM) {
            if (this.zyO == null) {
                this.zyO = Boolean.valueOf(this.zZm.BIo());
            }
            if (this.zyO.booleanValue() != z) {
                this.zyO = Boolean.valueOf(z);
                this.zZm.BIo(z);
                zyO(z);
            }
        }
    }

    public final void zQM(boolean z) {
        synchronized (this.BIo) {
            for (AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener : this.BIo.zZm()) {
                alexaAttentionSystemSettingsListener.onEndpointSoundEnabledChanged(z);
            }
        }
    }

    public void zZm(boolean z) {
        synchronized (this.zQM) {
            if (this.jiA == null) {
                this.jiA = Boolean.valueOf(this.zZm.zZm());
            }
            if (this.jiA.booleanValue() != z) {
                this.jiA = Boolean.valueOf(z);
                this.zZm.zZm(z);
                zQM(z);
            }
        }
    }

    public final void zyO(boolean z) {
        synchronized (this.BIo) {
            for (AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener : this.BIo.zZm()) {
                alexaAttentionSystemSettingsListener.onWakeSoundEnabledChanged(z);
            }
        }
    }

    public boolean BIo() {
        boolean booleanValue;
        synchronized (this.zQM) {
            if (this.zyO == null) {
                this.zyO = Boolean.valueOf(this.zZm.BIo());
            }
            booleanValue = this.zyO.booleanValue();
        }
        return booleanValue;
    }

    public boolean zZm() {
        boolean booleanValue;
        synchronized (this.zQM) {
            if (this.jiA == null) {
                this.jiA = Boolean.valueOf(this.zZm.zZm());
            }
            booleanValue = this.jiA.booleanValue();
        }
        return booleanValue;
    }

    public void zZm(ExtendedClient extendedClient, AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        synchronized (this.BIo) {
            this.BIo.zZm(extendedClient, alexaAttentionSystemSettingsListener);
        }
    }

    public void zZm(AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        synchronized (this.BIo) {
            this.BIo.remove(alexaAttentionSystemSettingsListener);
        }
    }
}
