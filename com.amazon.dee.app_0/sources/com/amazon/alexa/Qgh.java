package com.amazon.alexa;

import com.amazon.alexa.PJz;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AlexaSuppressionAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class Qgh {
    public IYE BIo;
    public final AlexaClientEventBus zZm;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AlexaSuppressionAuthority.java */
    /* loaded from: classes.dex */
    public class zZm extends jDH {
        public /* synthetic */ zZm(noQ noq) {
        }

        @Override // com.amazon.alexa.ndD
        public dnp BIo() {
            return AvsApiConstants.SpeechSynthesizer.zQM;
        }

        @Override // com.amazon.alexa.jDH
        public void Qle() {
        }

        @Override // com.amazon.alexa.jDH
        public void jiA() {
        }

        @Override // com.amazon.alexa.ndD
        public void zQM() {
            synchronized (Qgh.this) {
                Qgh.this.BIo = null;
            }
        }

        @Override // com.amazon.alexa.jDH
        public void zyO() {
        }
    }

    @Inject
    public Qgh(AlexaClientEventBus alexaClientEventBus) {
        this.zZm = alexaClientEventBus;
        alexaClientEventBus.zZm(this);
    }

    @Subscribe
    public synchronized void on(ZBK zbk) {
        IYE iye;
        if (!((dZg) zbk).BIo && (iye = this.BIo) != null) {
            this.zZm.zyO(LBB.zZm(iye));
            this.BIo = null;
        }
    }

    public synchronized void zZm() {
        if (this.BIo == null) {
            zZm zzm = new zZm(null);
            this.BIo = zzm.zZm;
            this.zZm.zyO(mZe.zZm(aVo.DIALOG, zzm, PJz.zZm(PJz.zQM.MUSIC, PJz.BIo.TRANSIENT_EXCLUSIVE, PJz.zyO.MEDIA, PJz.zZm.MUSIC), DialogRequestIdentifier.NONE));
        }
    }
}
