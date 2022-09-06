package com.amazon.alexa;

import android.content.Context;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceBridgePayloadUtil;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.EventLogger;
import dagger.Lazy;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: ExoAudioPlayer.java */
/* loaded from: classes.dex */
public class VIX implements uxN {
    public static final String zZm = "VIX";
    public ggV CGv;
    public final uXm HvC;
    public final TimeProvider JTe;
    public volatile zQM JXl;
    public ScheduledFuture Jhx;
    public final gSO LPk;
    public long MNR;
    public final AnalyticsListener Mlj;
    public HandlerThread NXS;
    public final Object Qgh;
    public final Context Qle;
    public SimpleExoPlayer Tbw;
    public Handler XWf;
    public final AtomicInteger dMe;
    public final Lazy<shl> lOf;
    public final DefaultTrackSelector noQ;
    public final ScheduledExecutorService oQJ;
    public KSk uuO;
    public final Lazy<onD> uzr;
    public final Lazy<dAN> vkx;
    public final DefaultBandwidthMeter wDP;
    public volatile boolean wUw;
    public final Player.EventListener yPL;
    public volatile boolean zOR;
    public final String zzR;
    public static final long BIo = TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS);
    public static final long zQM = TimeUnit.MILLISECONDS.convert(3, TimeUnit.SECONDS);
    public static final int zyO = (int) TimeUnit.MILLISECONDS.convert(240, TimeUnit.SECONDS);
    @VisibleForTesting
    public static final long jiA = TimeUnit.MILLISECONDS.convert(3, TimeUnit.SECONDS);

    /* compiled from: ExoAudioPlayer.java */
    /* loaded from: classes.dex */
    private class BIo extends Player.DefaultEventListener {
        public int zZm = 1;

        public /* synthetic */ BIo(KKC kkc) {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPlayerError(ExoPlaybackException exoPlaybackException) {
            VIX.zZm(VIX.this, exoPlaybackException);
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onPlayerStateChanged(boolean z, int i) {
            int i2 = this.zZm;
            if (i2 == i && i2 != 3) {
                VIX vix = VIX.this;
                vix.zZm("Play when ready state changed: " + z);
                return;
            }
            if (i == 1) {
                VIX vix2 = VIX.this;
                vix2.zZm("Player state changed: IDLE. Play when ready? " + z);
                VIX.zyO(VIX.this);
            } else if (i == 2) {
                VIX vix3 = VIX.this;
                vix3.zZm("Player state changed: BUFFERING. Play when ready? " + z);
                VIX.Mlj(VIX.this);
            } else if (i == 3) {
                VIX vix4 = VIX.this;
                vix4.zZm("Player state changed: READY. Play when ready? " + z);
                if (!z) {
                    if (VIX.this.wUw) {
                        VIX.dMe(VIX.this);
                    } else {
                        VIX.zyO(VIX.this);
                    }
                } else {
                    VIX.lOf(VIX.this);
                }
            } else if (i != 4) {
                VIX vix5 = VIX.this;
                vix5.zZm("Unknown playback state: " + i);
            } else {
                VIX vix6 = VIX.this;
                vix6.zZm("Player state changed: ENDED. Play when ready? " + z);
                VIX.zzR(VIX.this);
            }
            this.zZm = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ExoAudioPlayer.java */
    /* loaded from: classes.dex */
    public static class zQM {
        public final kQf BIo;
        public boolean JTe;
        public boolean LPk;
        public boolean Mlj;
        public boolean Qle;
        public boolean jiA;
        public boolean yPL;
        public final NTV zQM;
        public final ExecutorService zZm;
        public boolean zyO;

        public zQM(ExecutorService executorService, kQf kqf, NTV ntv) {
            this.zZm = executorService;
            this.BIo = kqf;
            this.zQM = ntv;
        }

        public boolean BIo() {
            return this.JTe;
        }

        public void Qle() {
            if (!this.JTe || this.Mlj) {
                return;
            }
            this.Mlj = true;
            zZm(new OFx(this));
        }

        public void jiA() {
            zZm(new RVO(this));
        }

        public boolean zQM() {
            return this.jiA;
        }

        public kQf zZm() {
            return this.BIo;
        }

        public boolean zyO() {
            return this.Mlj;
        }

        public void zZm(Exception exc, long j) {
            zZm(new Dri(this, exc, j));
        }

        public final void zZm(Runnable runnable) {
            if (this.zQM == null) {
                return;
            }
            this.zZm.execute(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ExoAudioPlayer.java */
    @FunctionalInterface
    @VisibleForTesting
    /* loaded from: classes.dex */
    public interface zZm<T> {
        T zZm(SimpleExoPlayer simpleExoPlayer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ExoAudioPlayer.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public class zyO implements Runnable {
        public final long zZm;

        public /* synthetic */ zyO(long j, KKC kkc) {
            this.zZm = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            SimpleExoPlayer simpleExoPlayer = VIX.this.Tbw;
            if (simpleExoPlayer == null || !VIX.this.Mlj()) {
                return;
            }
            long currentPosition = simpleExoPlayer.getCurrentPosition();
            if (currentPosition != this.zZm) {
                long elapsedRealTime = VIX.this.JTe.elapsedRealTime();
                zQM zqm = VIX.this.JXl;
                if (zqm.JTe) {
                    zqm.zZm(new kAI(zqm, elapsedRealTime, currentPosition));
                }
            }
            VIX.zZm(VIX.this, currentPosition);
        }
    }

    public VIX(Context context, TimeProvider timeProvider, gSO gso, String str, Lazy<shl> lazy, Lazy<dAN> lazy2, @Nullable Lazy<onD> lazy3, uXm uxm) {
        ScheduledExecutorService newSingleThreadScheduledExecutor = ManagedExecutorFactory.newSingleThreadScheduledExecutor(str + "-eventthread");
        this.Qle = context;
        this.JTe = timeProvider;
        this.LPk = gso;
        this.zzR = str;
        this.oQJ = newSingleThreadScheduledExecutor;
        this.lOf = lazy;
        this.uzr = lazy3;
        this.HvC = uxm;
        this.vkx = lazy2;
        this.dMe = new AtomicInteger(0);
        this.wDP = new DefaultBandwidthMeter();
        this.noQ = new DefaultTrackSelector();
        this.yPL = new BIo(null);
        this.Mlj = new EventLogger(this.noQ);
        this.Qgh = new Object();
        zZm("Created ExoAudioPlayer");
    }

    public static /* synthetic */ void JTe(VIX vix) {
        vix.zZm("Releasing ExoAudioPlayer");
        vix.BIo();
        SimpleExoPlayer simpleExoPlayer = vix.Tbw;
        if (simpleExoPlayer != null) {
            vix.zZm("    releasing audio player");
            simpleExoPlayer.release();
            simpleExoPlayer.removeListener(vix.yPL);
            simpleExoPlayer.removeAnalyticsListener(vix.Mlj);
            vix.Tbw = null;
        }
        vix.CGv = null;
        vix.uuO = null;
        vix.JXl = null;
        vix.zZm("    released audio player");
    }

    @VisibleForTesting
    public boolean LPk() {
        Boolean bool = (Boolean) zZm("isCurrentStreamLive", new Rej(this));
        zZm("Is current stream live? " + bool);
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public boolean Mlj() {
        if (!yPL()) {
            StringBuilder zZm2 = C0179Pya.zZm("isPaused? ");
            zZm2.append(this.wUw);
            zZm(zZm2.toString());
            if (!this.wUw) {
                return false;
            }
        }
        return true;
    }

    public void dMe() {
        zZm("stop");
        if (this.Tbw != null) {
            zZm("stop", new Wns(this));
            return;
        }
        throw new IllegalStateException("Initialize must be called before attempting to stop");
    }

    public void lOf() {
        synchronized (this.Qgh) {
            if (!this.zOR) {
                this.zOR = true;
                zZm("releasePlayer", new xFM(this));
                if (this.NXS != null) {
                    Handler handler = this.XWf;
                    if (handler != null && handler.getLooper() == Looper.myLooper()) {
                        this.NXS.quit();
                        Log.w(zZm, "Did not safely quit the ExoPlayer internal HandlerThread");
                    } else {
                        this.NXS.quitSafely();
                    }
                }
                this.NXS = null;
                this.XWf = null;
            } else {
                zZm("ExoAudioPlayer already released, ignoring the release call");
            }
        }
    }

    public final long zyO() {
        zZm("getCurrentPosition");
        Long l = (Long) zZm("getCurrentPosition", new fFR(this));
        if (l != null) {
            return l.longValue();
        }
        return -1L;
    }

    public void zzR() {
        zZm(VoiceBridgePayloadUtil.PayloadCommand.PLAY);
        if (this.Tbw != null) {
            zZm(VoiceBridgePayloadUtil.PayloadCommand.PLAY, new jPi(this));
            return;
        }
        throw new IllegalStateException("Initialize must be called before attempting to play");
    }

    public long Qle() {
        long zyO2 = this.Tbw == null ? -1L : zyO();
        zZm("getPosition - " + zyO2);
        return zyO2;
    }

    @VisibleForTesting
    public long jiA() {
        Long l = (Long) zZm("getLiveStreamPosition", new Xqd(this));
        if (l != null) {
            return l.longValue();
        }
        return -1L;
    }

    public final void uzr() {
        SimpleExoPlayer simpleExoPlayer = this.Tbw;
        if (simpleExoPlayer != null) {
            long currentPosition = simpleExoPlayer.getCurrentPosition();
            long elapsedRealTime = this.JTe.elapsedRealTime();
            zQM zqm = this.JXl;
            if (!zqm.JTe) {
                return;
            }
            zqm.zZm(new kAI(zqm, elapsedRealTime, currentPosition));
        }
    }

    public boolean yPL() {
        zZm("isPlaying");
        Boolean bool = (Boolean) zZm("isPlaying", new Ofn(this));
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final Handler zQM() {
        this.NXS = new HandlerThread(this.zzR + "-event-thread-" + this.dMe.getAndIncrement());
        this.NXS.start();
        Handler handler = new Handler(this.NXS.getLooper());
        handler.post(new KKC(this));
        return handler;
    }

    public final void BIo(SimpleExoPlayer simpleExoPlayer, kQf kqf, NTV ntv) {
        try {
            this.JXl = this.CGv.zZm(kqf, ntv);
            zZm(simpleExoPlayer, kqf, ntv);
        } catch (IOException e) {
            Log.e(zZm, "Failed to create PlayItem wrapper", e);
            ntv.zZm(kqf, -1L, e);
        }
    }

    public static /* synthetic */ void Mlj(VIX vix) {
        vix.zZm("onBuffering");
        vix.BIo();
        if (vix.JXl != null) {
            vix.JXl.jiA();
            if (!vix.JXl.BIo()) {
                return;
            }
            zQM zqm = vix.JXl;
            if (!zqm.JTe || zqm.jiA) {
                return;
            }
            zqm.jiA = true;
            zqm.zZm(new NmH(zqm));
        }
    }

    public static /* synthetic */ void LPk(VIX vix) {
        vix.zZm("onPlaybackNearlyFinished");
        if (vix.JXl != null) {
            vix.JXl.Qle();
        }
    }

    public static /* synthetic */ void dMe(VIX vix) {
        vix.zZm("onPlaybackPaused");
        vix.BIo();
        if (vix.JXl != null) {
            zQM zqm = vix.JXl;
            if (!zqm.JTe || zqm.zyO) {
                return;
            }
            zqm.zyO = true;
            zqm.zZm(new cKQ(zqm));
        }
    }

    public static /* synthetic */ void zyO(VIX vix) {
        vix.zZm("onPlaybackStopped");
        vix.BIo();
        if (vix.JXl != null) {
            vix.uzr();
            zQM zqm = vix.JXl;
            if (!zqm.JTe || zqm.LPk) {
                return;
            }
            zqm.LPk = true;
            zqm.zZm(new lcV(zqm));
        }
    }

    public static /* synthetic */ void zzR(VIX vix) {
        vix.zZm("onPlaybackFinished");
        vix.BIo();
        vix.MNR = 0L;
        if (vix.JXl != null) {
            vix.uzr();
            vix.JXl.Qle();
            zQM zqm = vix.JXl;
            if (!zqm.JTe || zqm.yPL) {
                return;
            }
            zqm.yPL = true;
            zqm.zZm(new uRm(zqm));
        }
    }

    public static /* synthetic */ void zZm(VIX vix, long j) {
        vix.XWf.postDelayed(new zyO(j, null), 250L);
    }

    public final void BIo() {
        ScheduledFuture scheduledFuture = this.Jhx;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.Jhx = null;
        }
    }

    public final void zZm(long j) {
        this.XWf.postDelayed(new zyO(j, null), 250L);
    }

    public final void zZm() {
        zZm("addDefaultListeners", new Xft(this));
    }

    public void zZm(kQf kqf, NTV ntv) {
        StringBuilder zZm2 = C0179Pya.zZm("prepare: ");
        zZm2.append(kqf.BIo);
        zZm(zZm2.toString());
        if (this.Tbw != null) {
            zZm("prepare", new ZBj(this, kqf, ntv));
            return;
        }
        throw new IllegalStateException("Initialize must be called before attempting to prepare");
    }

    public void JTe() {
        KSk jaU;
        synchronized (this.Qgh) {
            if (this.XWf == null) {
                this.XWf = zQM();
            }
            if (this.uuO == null) {
                Context context = this.Qle;
                Handler handler = this.XWf;
                DefaultBandwidthMeter defaultBandwidthMeter = this.wDP;
                gSO gso = this.LPk;
                Lazy<shl> lazy = this.lOf;
                Lazy<dAN> lazy2 = this.vkx;
                if (gso.zZm(Feature.ALEXA_VOX_ANDROID_MEDIA_PLAYLIST_FIX_CONTROL)) {
                    jaU = new osw(handler, context, defaultBandwidthMeter, this.uzr, lazy, lazy2, new cCP());
                } else {
                    jaU = new JaU(handler, context, defaultBandwidthMeter, this.uzr, lazy, lazy2);
                }
                this.uuO = jaU;
            }
            if (this.CGv == null) {
                this.CGv = new ggV(this.oQJ);
            }
            if (this.Tbw == null) {
                zZm("Initializing ExoPlayer");
                ConditionVariable conditionVariable = new ConditionVariable();
                this.XWf.post(new MvS(this, conditionVariable));
                this.zOR = false;
                if (!conditionVariable.block(zQM)) {
                    zZm("Unable to create the ExoPlayer instance", new IllegalStateException());
                    this.Tbw = null;
                }
            }
        }
    }

    public static /* synthetic */ void lOf(VIX vix) {
        vix.BIo();
        if (vix.JXl != null) {
            if (vix.JXl != null && !vix.JXl.zyO()) {
                Long l = (Long) vix.zZm("getDuration", new nvu(vix));
                long longValue = l != null ? l.longValue() : 0L;
                if (longValue != C.TIME_UNSET && longValue != 0) {
                    vix.zZm("Scheduling nearly finished runnable");
                    vix.zZm("Media duration: " + longValue);
                    vix.Jhx = vix.oQJ.scheduleAtFixedRate(new Fzo(vix, longValue), Math.max(0L, (longValue - vix.zyO()) - BIo), 1000L, TimeUnit.MILLISECONDS);
                } else {
                    vix.zZm("Media duration: UNKNOWN");
                }
            }
            if (!vix.JXl.BIo()) {
                long j = vix.JXl.zZm().zyO;
                if (vix.LPk()) {
                    vix.MNR = vix.jiA();
                }
                vix.zZm(j);
                zQM zqm = vix.JXl;
                if (zqm.JTe) {
                    return;
                }
                zqm.JTe = true;
                zqm.zyO = false;
                zqm.zZm(new UYz(zqm));
            } else if (vix.JXl.zyO && !vix.JXl.zQM()) {
                zQM zqm2 = vix.JXl;
                if (!zqm2.JTe || !zqm2.zyO) {
                    return;
                }
                zqm2.zyO = false;
                zqm2.zZm(new eUL(zqm2));
            } else if (!vix.JXl.zQM()) {
            } else {
                zQM zqm3 = vix.JXl;
                if (!zqm3.JTe || !zqm3.jiA) {
                    return;
                }
                zqm3.jiA = false;
                zqm3.zZm(new ZVW(zqm3));
            }
        }
    }

    public final void zZm(SimpleExoPlayer simpleExoPlayer, kQf kqf, NTV ntv) {
        StringBuilder zZm2 = C0179Pya.zZm("prepareInternal: ");
        zZm2.append(kqf.BIo);
        zZm(zZm2.toString());
        try {
            simpleExoPlayer.prepare(this.uuO.zZm(kqf));
            long j = kqf.zyO;
            if (j <= 0) {
                return;
            }
            zZm("seekTo");
            if (this.Tbw != null) {
                zZm("seekTo", new CgA(this, j));
                return;
            }
            throw new IllegalStateException("Initialize must be called before attempting to seek");
        } catch (IOException e) {
            Log.e(zZm, "Failed to prepare play item", e);
            ntv.zZm(kqf, -1L, e);
        }
    }

    public void zZm(IkF ikF) {
        StringBuilder zZm2 = C0179Pya.zZm("Setting ExoPlayerAttributes ");
        zZm2.append(ikF.zZm);
        zZm(zZm2.toString());
        if (this.Tbw != null) {
            zZm("setAudioAttributes", new bkE(this, ikF));
            return;
        }
        throw new IllegalStateException("Initialize must be called before attempting to set audio attributes");
    }

    @Nullable
    @VisibleForTesting
    public <T> T zZm(String str, zZm<T> zzm) {
        AtomicReference atomicReference = new AtomicReference();
        Handler handler = this.XWf;
        if (handler != null && handler.getLooper() != Looper.myLooper()) {
            ConditionVariable conditionVariable = new ConditionVariable();
            conditionVariable.close();
            zZm("Posting execution of " + str);
            handler.post(new CKS(this, str, atomicReference, zzm, conditionVariable));
            if (!conditionVariable.block(jiA)) {
                handler.removeCallbacksAndMessages(null);
                zZm("Unable to execute " + str + " within the timeout", new InterruptedException());
            }
        } else {
            SimpleExoPlayer simpleExoPlayer = this.Tbw;
            if (simpleExoPlayer == null) {
                String str2 = zZm;
                Log.w(str2, "Attempting to execute command " + str + " before initializing the player.");
                return null;
            }
            zZm("Executing " + str);
            atomicReference.set(zzm.zZm(simpleExoPlayer));
        }
        return (T) atomicReference.get();
    }

    public static /* synthetic */ void zZm(VIX vix, long j, long j2) {
        if (vix.JXl == null || vix.JXl.zyO() || j2 - BIo > j) {
            return;
        }
        vix.XWf.post(new UjR(vix));
    }

    public static /* synthetic */ void zZm(VIX vix, Exception exc) {
        vix.zZm("onPlaybackFailed");
        vix.BIo();
        long zyO2 = vix.zyO();
        vix.MNR = 0L;
        if (vix.JXl != null) {
            vix.JXl.zZm(exc, zyO2);
        }
    }

    public final void zZm(String str) {
        String str2 = zZm;
        Log.i(str2, this.zzR + RealTimeTextConstants.COLON_SPACE + str);
    }

    public final void zZm(String str, Exception exc) {
        Log.e(zZm, GeneratedOutlineSupport1.outline92(new StringBuilder(), this.zzR, RealTimeTextConstants.COLON_SPACE, str), exc);
    }
}
