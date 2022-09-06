package com.amazon.alexa;

import com.amazon.alexa.PJz;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: SpeechInteractionAudioMetadataFactory.java */
@Singleton
/* loaded from: classes.dex */
public class yqV {
    public PJz zyO;
    public static final PJz zZm = PJz.zZm(PJz.zQM.MUSIC, PJz.BIo.TRANSIENT_EXCLUSIVE, PJz.zyO.MEDIA, PJz.zZm.MUSIC);
    public static final PJz BIo = PJz.zZm(PJz.zQM.MUSIC, PJz.BIo.NO_AUDIOFOCUS, PJz.zyO.MEDIA, PJz.zZm.MUSIC);
    public static final PJz zQM = PJz.zZm(PJz.zQM.MUSIC, PJz.BIo.NO_AUDIOFOCUS);

    /* compiled from: SpeechInteractionAudioMetadataFactory.java */
    /* loaded from: classes.dex */
    public class zZm {
        public zZm() {
        }

        @Subscribe
        public void on(TSb tSb) {
            C0169GLa c0169GLa = (C0169GLa) tSb;
            if (aVo.COMMUNICATIONS == c0169GLa.BIo) {
                if (c0169GLa.zQM) {
                    yqV.this.zyO = yqV.BIo;
                    return;
                }
                yqV.this.zyO = yqV.zZm;
            }
        }
    }

    @Inject
    public yqV(AlexaClientEventBus alexaClientEventBus) {
        alexaClientEventBus.zZm(new zZm());
        this.zyO = zZm;
    }

    public PJz zZm() {
        return zZm(false);
    }

    public PJz zZm(boolean z) {
        return z ? zQM : this.zyO;
    }
}
