package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.AbstractC0181Rbt;
import com.amazon.alexa.HdS;
import com.amazon.alexa.JjI;
import com.amazon.alexa.Xde;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.chR;
import com.amazon.alexa.client.alexaservice.audioplayer.payload.AutoValue_PlaybackFailedEventPayload;
import com.amazon.alexa.client.alexaservice.audioplayer.payload.AutoValue_PlaybackFailedEventPayload_Error;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.MessageIdentifier;
import com.amazon.alexa.client.core.messages.MessageMetadata;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Payload;
import com.amazon.alexa.fcj;
import com.amazon.alexa.hFk;
import com.amazon.alexa.utils.TimeProvider;
import dagger.Lazy;
import java.util.Iterator;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AudioPlayerInteraction.java */
/* loaded from: classes.dex */
public class Bha extends OcX implements Xde.zZm {
    public static final String JTe = "Bha";
    public boolean CGv;
    public final ScheduledExecutorService HvC;
    public long JXl;
    public boolean Jhx;
    public final Xde LPk;
    public long MNR;
    public final Object Mlj;
    public final AtomicBoolean NXS;
    public final Lazy<ClientConfiguration> Qgh;
    public final Ycj Tbw;
    public final zZm XWf;
    public final shl dMe;
    public long eOP;
    public final oGE lOf;
    public final MessageMetadata noQ;
    public boolean oQJ;
    public volatile boolean uuO;
    public final Ygi uzr;
    public final JTh vkx;
    public final AtomicBoolean wDP;
    public volatile ScheduledFuture<?> wUw;
    public final VIX yPL;
    public boolean zOR;
    public final AlexaClientEventBus zzR;

    /* compiled from: AudioPlayerInteraction.java */
    /* loaded from: classes.dex */
    private class BIo implements Runnable {
        public /* synthetic */ BIo(nkN nkn) {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.i(Bha.JTe, "Playback resuming timed out. Releasing interaction");
            Bha.this.zzR.zyO(HdS.zZm.zZm(true));
            Bha.zQM(Bha.this);
            Bha.this.HvC();
        }
    }

    /* compiled from: AudioPlayerInteraction.java */
    /* loaded from: classes.dex */
    public class zZm {
        public zZm() {
        }

        @Subscribe
        public void on(YHu yHu) {
            Log.i(Bha.JTe, "Network connectivity restored before timeout. Resuming music");
            Bha.this.zzR.zyO(HdS.zZm.zZm(false));
            ((chR.zZm) Bha.this.Tbw).zZm.BIo();
            Bha.zQM(Bha.this);
            Bha.this.HvC();
        }
    }

    public Bha(VIX vix, AlexaClientEventBus alexaClientEventBus, shl shlVar, Ygi ygi, ScheduledExecutorService scheduledExecutorService, oGE oge, TimeProvider timeProvider, MessageMetadata messageMetadata, Ycj ycj, Lazy<ClientConfiguration> lazy) {
        super(alexaClientEventBus, timeProvider);
        this.wDP = new AtomicBoolean(false);
        this.uuO = false;
        this.CGv = false;
        this.zOR = false;
        this.oQJ = false;
        this.Jhx = false;
        this.JXl = System.currentTimeMillis();
        this.yPL = vix;
        this.zzR = alexaClientEventBus;
        this.lOf = oge;
        this.dMe = shlVar;
        this.uzr = ygi;
        this.LPk = new Xde(this);
        this.HvC = scheduledExecutorService;
        this.vkx = new JTh();
        this.XWf = new zZm();
        this.Tbw = ycj;
        this.NXS = new AtomicBoolean(false);
        this.noQ = messageMetadata;
        this.Qgh = lazy;
        this.Mlj = new Object();
        vix.JTe();
    }

    public final void HvC() {
        if (!this.uuO) {
            this.uuO = true;
            if (this.NXS.getAndSet(false)) {
                this.zzR.BIo(this.XWf);
            }
            LPk();
            Log.i(JTe, "Releasing audio player");
            this.yPL.lOf();
            vkx();
            synchronized (this) {
                if (this.CGv && !this.oQJ) {
                    Log.i(JTe, "Finishing interaction");
                    this.zzR.zyO(LBB.zZm(this.zZm));
                    this.oQJ = true;
                }
            }
            this.zzR.BIo(this);
        }
    }

    public final boolean JTe() {
        kQf zQM = this.LPk.zQM();
        if (zQM != null) {
            return zQM.Qle == fcj.zZm.ATTENUATE;
        }
        Log.e(JTe, "Play queue is empty, defaulting to being unable to duck audio.");
        return false;
    }

    @Override // com.amazon.alexa.NTV
    public void LPk(kQf kqf) {
        Log.i(JTe, "onPlaybackFinished");
        nLZ remove = this.zyO.remove(kqf);
        if (remove != null) {
            remove.jiA();
            this.zQM.zyO(psG.zZm(remove));
        }
        this.jiA = this.Qle.elapsedRealTime();
        this.lOf.zZm(kqf, AUQ.FINISHED);
        this.zzR.zyO(zZm(AvsApiConstants.AudioPlayer.Events.PlaybackFinished.zZm));
        this.zzR.zyO(CKO.zZm(AlexaPlayerInfoState.DONE, kqf.BIo, ((BkS) this.lOf.BIo()).BIo));
        this.uzr.BIo();
        HvC(kqf);
    }

    @Override // com.amazon.alexa.NTV
    public void Mlj(kQf kqf) {
        Log.i(JTe, "onPlaybackPaused");
        nLZ nlz = this.zyO.get(kqf);
        if (nlz != null) {
            nlz.jiA();
        }
        this.lOf.zZm(kqf, AUQ.PAUSED);
        this.zzR.zyO(zZm(AvsApiConstants.AudioPlayer.Events.PlaybackPaused.zZm));
        this.zzR.zyO(CKO.zZm(AlexaPlayerInfoState.PAUSED, kqf.BIo, ((BkS) this.lOf.BIo()).BIo));
        this.uzr.zyO();
    }

    public final void Qgh() {
        synchronized (this.Mlj) {
            VIX vix = this.yPL;
            vix.zZm("unduck");
            if (vix.Tbw != null) {
                vix.zZm("unduck", new DcM(vix));
            } else {
                throw new IllegalStateException("Initialize must be called before attempting to unduck");
            }
        }
    }

    @Override // com.amazon.alexa.jDH
    public void Qle() {
        dMe();
    }

    public boolean dMe(kQf kqf) {
        String str = JTe;
        StringBuilder zZm2 = C0179Pya.zZm("Enqueuing PlayItem: ");
        zZm2.append(kqf.mo947BIo());
        Log.i(str, zZm2.toString());
        synchronized (this.Mlj) {
            if (this.yPL.zOR) {
                Log.e(JTe, "Attempting to play audio on a released ExoAudioPlayer");
                return false;
            }
            boolean BIo2 = this.LPk.BIo(kqf);
            if (BIo2) {
                this.CGv = true;
            }
            return BIo2;
        }
    }

    public void lOf(kQf kqf) {
        String str = JTe;
        Log.i(str, "Canceling play item: " + kqf);
        synchronized (this.Mlj) {
            synchronized (this.LPk) {
                if (this.LPk.zQM(kqf)) {
                    Log.e(JTe, "Invalid state: Attempting to cancel currently active item");
                    zZm(true);
                    this.LPk.jiA(kqf);
                    this.zyO.remove(kqf);
                } else {
                    kQf jiA = this.LPk.jiA();
                    kQf zyO = this.LPk.zyO();
                    if (zyO != null && jiA != null && jiA.equals(kqf)) {
                        vkx(zyO);
                    }
                    this.LPk.jiA(kqf);
                    this.zyO.remove(kqf);
                    if (this.LPk.Qle()) {
                        zZm(false);
                    }
                }
                this.zzR.zyO(CKO.zZm(AlexaPlayerInfoState.CANCELLED, kqf.BIo, 0L));
            }
        }
    }

    public boolean noQ(kQf kqf) {
        boolean dMe;
        String str = JTe;
        StringBuilder zZm2 = C0179Pya.zZm("Replacing all enqueued PlayItems with: ");
        zZm2.append(kqf.mo947BIo());
        Log.i(str, zZm2.toString());
        synchronized (this.Mlj) {
            synchronized (this.LPk) {
                yPL();
                dMe = dMe(kqf);
            }
        }
        return dMe;
    }

    public final void uzr() {
        synchronized (this.Mlj) {
            this.yPL.zzR();
        }
    }

    public final void vkx(kQf kqf) {
        synchronized (this.Mlj) {
            this.zyO.put(kqf, new nLZ(kqf, this.jiA, this.Qle));
            this.jiA = 0L;
            this.zzR.zyO(new Psd(kqf));
            this.yPL.zZm(kqf, this);
            if (kqf.jiA != null) {
                this.uzr.zZm(kqf.jiA);
            } else {
                this.uzr.BIo();
            }
        }
    }

    public void wDP(kQf kqf) {
        if (this.LPk.zyO(kqf)) {
            vkx(kqf);
        }
    }

    public void yPL() {
        Log.i(JTe, "Clearing all enqueued items in the Play Queue");
        synchronized (this.LPk) {
            Iterator<kQf> BIo2 = this.LPk.BIo();
            while (BIo2.hasNext()) {
                this.zzR.zyO(CKO.zZm(AlexaPlayerInfoState.CANCELLED, BIo2.next().BIo, 0L));
                BIo2.remove();
            }
        }
    }

    @Override // com.amazon.alexa.ndD
    public void zQM() {
        zZm(true);
    }

    public final void zzR() {
        synchronized (this.Mlj) {
            VIX vix = this.yPL;
            vix.zZm("duck");
            if (vix.Tbw != null) {
                vix.zZm("duck", new LQm(vix));
            } else {
                throw new IllegalStateException("Initialize must be called before attempting to duck");
            }
        }
    }

    @Override // com.amazon.alexa.ndD
    public dnp BIo() {
        return AvsApiConstants.AudioPlayer.zQM;
    }

    @Override // com.amazon.alexa.NTV
    public void Qle(kQf kqf) {
        Log.i(JTe, "onPlaybackStarting");
        nLZ nlz = this.zyO.get(kqf);
        if (nlz != null) {
            nlz.zyO();
        }
    }

    @Override // com.amazon.alexa.jDH
    public void jiA() {
        if (JTe()) {
            Qgh();
        }
        wDP();
    }

    @Override // com.amazon.alexa.NTV
    public void zQM(kQf kqf) {
        Log.i(JTe, "onPlaybackStopped");
        nLZ remove = this.zyO.remove(kqf);
        if (remove != null) {
            remove.jiA();
            this.zQM.zyO(psG.zZm(remove));
        }
        this.lOf.zZm(kqf, AUQ.STOPPED);
        this.zzR.zyO(zZm(AvsApiConstants.AudioPlayer.Events.PlaybackStopped.zZm));
        this.zzR.zyO(CKO.zZm(AlexaPlayerInfoState.DONE, kqf.BIo, ((BkS) this.lOf.BIo()).BIo));
        long elapsedRealTime = this.Qle.elapsedRealTime();
        long Qle = this.yPL.Qle();
        if (!this.Jhx) {
            long j = (elapsedRealTime - this.MNR) - (Qle - this.eOP);
            if (j > 2000) {
                this.zQM.zyO(new RWT(kqf));
                String str = OcX.BIo;
                Log.w(str, "Audio is slow by " + j);
            }
        }
        this.uzr.BIo();
        this.vkx.zZm();
        if (!this.NXS.get()) {
            zZm(false);
        }
    }

    @Override // com.amazon.alexa.jDH
    public void zyO() {
        if (JTe()) {
            zzR();
            wDP();
            return;
        }
        dMe();
    }

    @Override // com.amazon.alexa.NTV
    public void BIo(kQf kqf) {
        Log.i(JTe, "onPlaybackNearlyFinished");
        this.zzR.zyO(new jzl());
        this.lOf.zZm(kqf, AUQ.PLAYING);
        this.zzR.zyO(zZm(AvsApiConstants.AudioPlayer.Events.PlaybackNearlyFinished.zZm, new nkN(this)));
    }

    public final void wDP() {
        if (this.zOR) {
            uzr();
            return;
        }
        kQf zQM = this.LPk.zQM();
        if (zQM != null) {
            String str = JTe;
            Log.i(str, "Playing item: " + zQM);
            uzr();
            this.zOR = true;
            return;
        }
        Log.e(JTe, "Could not play item. Play queue is empty");
    }

    public boolean zZm(boolean z) {
        boolean z2;
        if (this.yPL.Mlj()) {
            noQ();
            if (z) {
                Log.i(JTe, "Waiting for event to be sent");
                z2 = this.vkx.zZm(500L, TimeUnit.MILLISECONDS);
                HvC();
                return z2;
            }
        }
        z2 = false;
        HvC();
        return z2;
    }

    public void uzr(kQf kqf) {
        if (kqf.zQM()) {
            StringBuilder zZm2 = C0179Pya.zZm("Deleting attachment for play item: ");
            zZm2.append(kqf.mo947BIo());
            zZm2.toString();
            this.dMe.BIo(kqf.zZm());
        }
    }

    @Override // com.amazon.alexa.NTV
    public void JTe(kQf kqf) {
        Log.i(JTe, "onPlaybackResumed");
        nLZ nlz = this.zyO.get(kqf);
        if (nlz != null) {
            if (!nlz.JTe) {
                Log.e(nLZ.zZm, "Must call startMeasuringTimeBetweenPlaybackResumingAndPlaybackResumed first.");
            } else {
                Log.i(nLZ.zZm, "Stops measuring time between play and playback resumed");
                nlz.JTe = false;
                nlz.zzR = nlz.zZm() - nlz.yPL;
            }
            this.zQM.zyO(new UfY(nlz));
        }
        this.MNR = this.Qle.elapsedRealTime();
        this.eOP = this.yPL.Qle();
        this.lOf.zZm(kqf, AUQ.PLAYING);
        this.zzR.zyO(zZm(AvsApiConstants.AudioPlayer.Events.PlaybackResumed.zZm));
        this.zzR.zyO(CKO.zZm(AlexaPlayerInfoState.PLAYING, kqf.BIo, ((BkS) this.lOf.BIo()).BIo));
        this.uzr.zQM();
    }

    @Override // com.amazon.alexa.NTV
    public void jiA(kQf kqf) {
        Log.i(JTe, "onPlaybackResuming");
        nLZ nlz = this.zyO.get(kqf);
        if (nlz != null) {
            if (nlz.JTe) {
                Log.w(nLZ.zZm, "Already measuring time between play and playback resumed");
                return;
            }
            Log.i(nLZ.zZm, "Starts measuring time between play and playback resumed");
            nlz.JTe = true;
            nlz.yPL = nlz.zZm();
        }
    }

    @Override // com.amazon.alexa.NTV
    public void zyO(kQf kqf) {
        Log.i(JTe, "onStutterStopped");
        this.Jhx = false;
        this.MNR = this.Qle.elapsedRealTime();
        this.eOP = this.yPL.Qle();
        this.lOf.zZm(kqf, AUQ.PLAYING);
        long currentTimeMillis = System.currentTimeMillis() - this.JXl;
        AlexaClientEventBus alexaClientEventBus = this.zzR;
        BkS bkS = (BkS) this.lOf.BIo();
        alexaClientEventBus.zyO(zZm(AvsApiConstants.AudioPlayer.Events.PlaybackStutterFinished.zZm, pLw.zZm(bkS.zZm, bkS.BIo, currentTimeMillis)));
        this.zzR.zyO(CKO.zZm(AlexaPlayerInfoState.PLAYING, kqf.BIo, ((BkS) this.lOf.BIo()).BIo));
        this.uzr.zQM();
    }

    public final void noQ() {
        if (!this.wDP.getAndSet(true)) {
            synchronized (this.Mlj) {
                this.yPL.dMe();
                this.uzr.BIo();
            }
        }
    }

    @Override // com.amazon.alexa.jDH
    public void zZm(IkF ikF) {
        synchronized (this.Mlj) {
            this.yPL.zZm(ikF);
        }
    }

    @Override // com.amazon.alexa.NTV
    public void zzR(kQf kqf) {
        Log.i(JTe, "onPlaybackStarted");
        nLZ nlz = this.zyO.get(kqf);
        if (nlz != null) {
            nlz.Qle();
            nlz.jiA();
            if (nlz.zyO != 0) {
                nlz.lOf = nlz.zZm() - nlz.zyO;
            } else {
                Log.i(nLZ.zZm, "There is no last play item finished.");
            }
            if (nlz.BIo()) {
                this.zQM.zyO(paE.zZm(nlz));
            }
        }
        this.MNR = this.Qle.elapsedRealTime();
        this.eOP = this.yPL.Qle();
        this.vkx.BIo();
        this.lOf.zZm(kqf, AUQ.PLAYING);
        this.zzR.zyO(zZm(AvsApiConstants.AudioPlayer.Events.PlaybackStarted.zZm, zZm(kqf.zyO, this.lOf.BIo()), (TtM) null));
        this.zzR.zyO(CKO.zZm(AlexaPlayerInfoState.PLAYING, kqf.BIo, kqf.zyO));
        this.uzr.zQM();
    }

    public final void dMe() {
        synchronized (this.Mlj) {
            VIX vix = this.yPL;
            vix.zZm("pause");
            if (vix.Tbw != null) {
                vix.zZm("pause", new Elq(vix));
            } else {
                throw new IllegalStateException("Initialize must be called before attempting to pause");
            }
        }
    }

    @Override // com.amazon.alexa.NTV
    public void zZm(kQf kqf, long j, Exception exc) {
        String sb;
        Log.i(JTe, "onPlaybackFailed");
        nLZ remove = this.zyO.remove(kqf);
        if (zZm(exc, j)) {
            this.zQM.zyO(new Ycg(remove));
        }
        this.lOf.zZm(kqf, AUQ.STOPPED);
        AlexaClientEventBus alexaClientEventBus = this.zzR;
        Puy mo947BIo = kqf.mo947BIo();
        Vma BIo2 = this.lOf.BIo();
        Name name = AvsApiConstants.AudioPlayer.Events.PlaybackFailed.zZm;
        AbstractC0181Rbt.zZm zzm = (AbstractC0181Rbt.zZm) hFk.zZm();
        zzm.zZm = mo947BIo;
        zzm.BIo = BIo2;
        hFk.zQM zqm = hFk.zQM.MEDIA_ERROR_UNKNOWN;
        if (exc == null) {
            sb = "null";
        } else {
            StringBuilder sb2 = new StringBuilder(exc.toString());
            Throwable th = exc;
            while (th.getCause() != null) {
                th = th.getCause();
                sb2.append(" -- Caused by: ");
                sb2.append(th.toString());
            }
            sb = sb2.toString();
        }
        zzm.zQM = new AutoValue_PlaybackFailedEventPayload_Error(zqm, sb);
        alexaClientEventBus.zyO(zZm(name, new AutoValue_PlaybackFailedEventPayload(zzm.zZm, zzm.BIo, zzm.zQM)));
        this.zzR.zyO(CKO.zZm(AlexaPlayerInfoState.ERROR, kqf.BIo, j));
        this.uzr.BIo();
        if (zZm(exc, j)) {
            this.zzR.zyO(new wDd(kqf, true));
            Log.i(JTe, "Music timed out. Waiting for network connectivity");
            if (!this.NXS.getAndSet(true)) {
                this.zzR.zZm(this.XWf);
            }
            LPk();
            this.wUw = this.HvC.schedule(new BIo(null), this.Qgh.mo358get().getPlaybackResumingTimeout().longValue(), TimeUnit.SECONDS);
            return;
        }
        Log.w(JTe, "Playback failed. Attempting to play next item");
        this.zzR.zyO(new wDd(kqf, false));
        HvC(kqf);
    }

    public final Payload Mlj() {
        BkS bkS = (BkS) this.lOf.BIo();
        return lCm.zZm(bkS.zZm, bkS.BIo);
    }

    public final void LPk() {
        ScheduledFuture<?> scheduledFuture = this.wUw;
        this.wUw = null;
        if (scheduledFuture == null || scheduledFuture.isDone()) {
            return;
        }
        scheduledFuture.cancel(true);
    }

    public final void vkx() {
        synchronized (this.LPk) {
            yPL();
            this.LPk.zZm();
        }
    }

    public final void HvC(kQf kqf) {
        this.vkx.zZm();
        synchronized (this.LPk) {
            this.LPk.jiA(kqf);
            if (this.LPk.Qle()) {
                zZm(false);
            } else {
                this.vkx.BIo();
                vkx(this.LPk.zQM());
            }
        }
    }

    public boolean lOf() {
        return !this.LPk.Qle();
    }

    public static /* synthetic */ void zQM(Bha bha) {
        if (bha.NXS.getAndSet(false)) {
            bha.zzR.BIo(bha.XWf);
        }
    }

    @Override // com.amazon.alexa.NTV
    public void zZm(kQf kqf) {
        Log.i(JTe, "onStutterStarted");
        this.Jhx = true;
        this.lOf.zZm(kqf, AUQ.BUFFER_UNDERRUN);
        this.JXl = System.currentTimeMillis();
        this.zzR.zyO(zZm(AvsApiConstants.AudioPlayer.Events.PlaybackStutterStarted.zZm));
        this.zzR.zyO(CKO.zZm(AlexaPlayerInfoState.BUFFERING, kqf.BIo, ((BkS) this.lOf.BIo()).BIo));
        this.uzr.zyO();
    }

    public final Payload zZm(long j, Vma vma) {
        return lCm.zZm(((BkS) vma).zZm, j);
    }

    public final JjI zZm(Name name) {
        return zZm(name, Mlj(), (TtM) null);
    }

    public final JjI zZm(Name name, @Nullable TtM ttM) {
        return zZm(name, Mlj(), ttM);
    }

    public final JjI zZm(Name name, Payload payload) {
        return zZm(name, payload, (TtM) null);
    }

    public final JjI zZm(Name name, Payload payload, @Nullable TtM ttM) {
        JjI.zZm zZm2 = JjI.BIo().zZm(Message.create(Header.builder().setMessageIdentifier(MessageIdentifier.createRandom()).setName(name).setNamespace(AvsApiConstants.AudioPlayer.zZm).build(), payload, this.noQ));
        if (ttM != null) {
            zZm2.zZm(ttM);
        }
        return zZm2.zZm();
    }
}
