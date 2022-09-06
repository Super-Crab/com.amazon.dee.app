package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaProfile;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.fuM;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import dagger.Lazy;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: SoundEffectPlayer.java */
@Singleton
/* loaded from: classes.dex */
public class VEQ {
    public static final String zZm = "VEQ";
    public final Lazy<jcJ> BIo;
    public final ExecutorService JTe;
    public DialogRequestIdentifier LPk;
    public final vVi Qle;
    public final Queue<DialogRequestIdentifier> jiA;
    public final Lazy<Wyh> zQM;
    public final Lazy<ClientConfiguration> zyO;

    /* compiled from: SoundEffectPlayer.java */
    /* loaded from: classes.dex */
    private class BIo implements Runnable {
        public final fuM.zZm BIo;
        public final DialogRequestIdentifier zZm;

        public BIo(DialogRequestIdentifier dialogRequestIdentifier, fuM.zZm zzm) {
            this.zZm = dialogRequestIdentifier;
            this.BIo = zzm;
        }

        @Override // java.lang.Runnable
        public void run() {
            NEe zZm = ((Wyh) VEQ.this.zQM.mo358get()).zZm(this.zZm);
            if (VEQ.this.BIo(zZm)) {
                VEQ.this.zZm(this.BIo, zZm);
            }
            if (!VEQ.this.zZm(zZm)) {
                if (!VEQ.this.jiA.contains(this.zZm)) {
                    VEQ.this.jiA.add(this.zZm);
                }
                if (VEQ.this.jiA.size() <= 5) {
                    return;
                }
                VEQ.this.jiA.poll();
            }
        }
    }

    /* compiled from: SoundEffectPlayer.java */
    /* loaded from: classes.dex */
    private class zZm implements Runnable {
        public final DialogRequestIdentifier BIo;
        public final fuM.zyO zZm;

        public zZm(DialogRequestIdentifier dialogRequestIdentifier, fuM.zyO zyo) {
            this.zZm = zyo;
            this.BIo = dialogRequestIdentifier;
        }

        @Override // java.lang.Runnable
        public void run() {
            NEe zZm = ((Wyh) VEQ.this.zQM.mo358get()).zZm(this.BIo);
            if (!VEQ.this.jiA.contains(this.BIo)) {
                if (VEQ.this.LPk != null && VEQ.this.LPk.equals(this.BIo)) {
                    return;
                }
                VEQ.this.LPk = this.BIo;
                if (!VEQ.this.zZm(zZm)) {
                    return;
                }
                VEQ.this.zZm(this.zZm, zZm);
                return;
            }
            VEQ.this.jiA.remove(this.BIo);
        }
    }

    @Inject
    public VEQ(AlexaClientEventBus alexaClientEventBus, Lazy<jcJ> lazy, Lazy<Wyh> lazy2, Lazy<ClientConfiguration> lazy3, vVi vvi) {
        ExecutorService newSingleThreadExecutor = ExecutorFactory.newSingleThreadExecutor("sound-effect-player");
        this.BIo = lazy;
        this.zQM = lazy2;
        this.zyO = lazy3;
        this.jiA = new ArrayDeque(6);
        this.Qle = vvi;
        this.JTe = newSingleThreadExecutor;
        alexaClientEventBus.zZm(this);
    }

    @Subscribe
    public void on(fuM.BIo bIo) {
        Don don = (Don) bIo;
        this.JTe.submit(new BIo(don.zyO, don.zQM));
    }

    public final boolean BIo(@Nullable NEe nEe) {
        if (nEe == null) {
            return false;
        }
        AlexaDialogExtras jiA = nEe.jiA();
        if (jiA.suppressWakeSound()) {
            return false;
        }
        if (nEe.Qgh()) {
            return true;
        }
        return !jiA.suppressWakeSoundOnlyOnFirstTurn();
    }

    @Subscribe
    public void on(fuM.jiA jia) {
        C0214kwy c0214kwy = (C0214kwy) jia;
        this.JTe.submit(new zZm(c0214kwy.zQM, c0214kwy.BIo));
    }

    public final void zZm(fuM.zZm zzm, NEe nEe) {
        boolean zZm2 = zZm(nEe.LPk());
        if (fuM.zZm.WAKEWORD.equals(zzm) && this.zyO.mo358get().isEnabled(Feature.WAKEWORD_ACTIVATION_SOUND)) {
            this.BIo.mo358get().zZm(gOC.WAKESOUND, zZm2);
        } else if (fuM.zZm.BUTTON_PRESS.equals(zzm) && this.zyO.mo358get().isEnabled(Feature.TOUCH_ACTIVATION_SOUND)) {
            this.BIo.mo358get().zZm(gOC.WAKESOUND_TOUCH, zZm2);
        } else {
            String str = zZm;
            Log.i(str, "Ignoring wake sound effect for event: " + zzm);
        }
    }

    @Subscribe
    public void on(qTm qtm) {
        this.BIo.mo358get().zZm(((Xvi) qtm).BIo ? gOC.MUTE_ON : gOC.MUTE_OFF, false);
    }

    public final boolean zZm(@Nullable NEe nEe) {
        if (nEe == null) {
            return false;
        }
        AlexaDialogExtras jiA = nEe.jiA();
        if (jiA.suppressEndpointSound()) {
            return false;
        }
        if (nEe.Qgh()) {
            return true;
        }
        return !jiA.suppressEndpointSoundOnlyOnFirstTurn();
    }

    public final void zZm(fuM.zyO zyo, NEe nEe) {
        boolean zZm2 = zZm(nEe.LPk());
        AlexaAudioMetadata zyO = nEe.zyO();
        AlexaProfile alexaProfile = zyO != null ? zyO.getAlexaProfile() : null;
        if (fuM.zyO.STOP_CAPTURE.equals(zyo)) {
            this.BIo.mo358get().zZm(gOC.ENDPOINTING, zZm2);
        } else if (fuM.zyO.BUTTON_PRESS.equals(zyo)) {
            this.BIo.mo358get().zZm(gOC.ENDPOINTING_TOUCH, zZm2);
        } else if (fuM.zyO.OTHER.equals(zyo) && AlexaProfile.CLOSE_TALK.equals(alexaProfile)) {
            this.BIo.mo358get().zZm(gOC.ENDPOINTING_TOUCH, zZm2);
        } else {
            String str = zZm;
            Log.i(str, "Ignoring endpoint sound effect for event: " + zyo);
        }
    }

    public final boolean zZm(DialogRequestIdentifier dialogRequestIdentifier) {
        vVi vvi = this.Qle;
        if (vvi.zZm(dialogRequestIdentifier)) {
            vvi.BIo.startSco();
            return true;
        }
        return false;
    }
}
