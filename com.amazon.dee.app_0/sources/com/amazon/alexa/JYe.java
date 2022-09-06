package com.amazon.alexa;

import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.media.session.MediaControllerCompat;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.Roh;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.AutoValue_PlayerError;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.zmg;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: MediaBrowserPlayer.java */
/* loaded from: classes.dex */
public class JYe implements Xpk {
    public final AlexaClientEventBus BIo;
    public final zZm JTe;
    public final gSO LPk;
    public HandlerThread Mlj;
    public final qKe Qle;
    public boolean dMe;
    public final OWw jiA;
    public int lOf;
    public Handler yPL;
    public final vQe zQM;
    public String zZm;
    public final bYx zyO;
    public ConditionVariable zzR;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MediaBrowserPlayer.java */
    /* loaded from: classes.dex */
    public interface zZm {
    }

    @VisibleForTesting
    public JYe(AlexaClientEventBus alexaClientEventBus, yfS yfs, bYx byx, OWw oWw, zZm zzm, qKe qke, gSO gso) {
        this.zZm = JYe.class.getSimpleName();
        this.BIo = alexaClientEventBus;
        this.zyO = byx;
        this.jiA = oWw;
        this.JTe = zzm;
        this.zQM = ((bve) yfs).BIo;
        this.Qle = qke;
        this.LPk = gso;
        alexaClientEventBus.zZm(this);
        this.zyO.uzr = this;
        this.zZm = this.zZm.concat(String.format(" [%s]", this.zQM.getValue()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void JXl() {
        bYx byx = this.zyO;
        if (byx != null) {
            byx.BIo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Jhx() {
        bYx byx = this.zyO;
        if (byx != null) {
            byx.BIo();
            this.zyO.yPL();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void MNR() {
        bYx byx = this.zyO;
        if (byx != null) {
            byx.BIo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void wUw() {
        this.zyO.BIo();
        this.zyO.zZm();
    }

    public static JYe zZm(AlexaClientEventBus alexaClientEventBus, yfS yfs, bYx byx, Hir hir, OWw oWw, zZm zzm, qKe qke, gSO gso) {
        bve bveVar = (bve) yfs;
        vQe vqe = bveVar.BIo;
        oWw.BIo(vqe, new Roh.zZm().zZm(vqe).zZm("").zyO(true).BIo("").BIo(false).zQM(true).zZm(true).zZm(AbstractC0188bKf.zZm).zZm(zYH.zZm).zZm(ZYY.zZm).zZm(Hir.zZm).zZm(GWl.zZm).zZm(hir).zZm(bveVar.jiA).zZm(bveVar.Qle).zZm(bveVar.JTe).zZm());
        return new JYe(alexaClientEventBus, yfs, byx, oWw, zzm, qke, gso);
    }

    public synchronized boolean BIo() {
        if (uzr()) {
            Log.i(this.zZm, "MediaBrowser already connected. Not attempting to reconnect");
            return true;
        }
        Log.i(this.zZm, "Starting the MediaBrowserPlayer connection");
        if (this.Mlj == null) {
            this.Mlj = new HandlerThread(String.format("media-browser-thread-%s", this.zQM.getValue()));
            this.Mlj.start();
            this.yPL = new Handler(this.Mlj.getLooper());
        }
        this.zzR = new ConditionVariable();
        this.lOf = 0;
        this.dMe = false;
        if (this.LPk.zZm(Feature.ALEXA_VOX_ANDROID_MEDIA_BROWSER_CONNECTION_RETRY_LOGIC) && "AmazonMusic".equals(this.zQM.getValue())) {
            Log.i(this.zZm, "Feature ALEXA_VOX_ANDROID_MEDIA_BROWSER_CONNECTION_RETRY_LOGIC is enabled. Attempting to connect WITH retries.");
            this.dMe = true;
        }
        Qgh();
        if (!this.zzR.block(10000L)) {
            Log.w(this.zZm, String.format("The MediaBrowserClient connection timed out after [%d ms]", 10000));
            zZm(new Runnable() { // from class: com.amazon.alexa.-$$Lambda$JYe$XRr8NLmSK9NIk7ZisOsRyFM_JE8
                @Override // java.lang.Runnable
                public final void run() {
                    JYe.this.JXl();
                }
            });
            this.BIo.zyO(FXk.zZm(this.zQM, FGE.zQM));
        }
        return uzr();
    }

    public void CGv() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new AAV(this, dMe));
        }
    }

    public boolean HvC() {
        zmg.BIo bIo = (zmg.BIo) this.JTe;
        return zmg.this.zQM().equals(bIo.zZm) && this.zyO.JTe();
    }

    public void JTe() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new fBz(this, dMe));
        }
    }

    public void LPk() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new PYI(this, dMe));
        }
    }

    public final void Mlj() {
        String str = this.zZm;
        Object[] objArr = new Object[2];
        objArr[0] = uzr() ? "SUCCEEDED" : "FAILED";
        objArr[1] = Integer.valueOf(this.lOf);
        Log.i(str, String.format("The MediaBrowserClient connection [%s] after [%d] attempt(s)", objArr));
        this.BIo.zyO(new FeU(this.zQM, uzr(), this.lOf));
        this.zzR.open();
    }

    public void NXS() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new czU(this, dMe));
        }
    }

    public final void Qgh() {
        this.lOf++;
        if (this.lOf > 1) {
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e) {
                Log.e(this.zZm, String.format("The %d ms sleep before connection attempt [%d] was interrupted.", 300, Integer.valueOf(this.lOf)), e);
            }
        }
        Log.i(this.zZm, String.format("Performing %s MediaBrowserClient connection attempt [%d of %d]", this.zQM.getValue(), Integer.valueOf(this.lOf), 3));
        zZm(new Runnable() { // from class: com.amazon.alexa.-$$Lambda$JYe$6CEUiZ1BWubByRpQCyD_zqFBgMo
            @Override // java.lang.Runnable
            public final void run() {
                JYe.this.wUw();
            }
        });
    }

    public void Qle() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new IbZ(this, dMe));
        }
    }

    public void Tbw() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new aBZ(this, dMe));
        }
    }

    public void XWf() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new WxS(this, dMe));
        }
    }

    public final MediaControllerCompat.TransportControls dMe() {
        if (BIo()) {
            return this.zyO.zyO().getTransportControls();
        }
        return null;
    }

    public void jiA() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new FpJ(this, dMe));
        }
    }

    public Kyp lOf() {
        return this.zyO.jiA();
    }

    public void noQ() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new NQY(this, dMe));
        }
    }

    public void oQJ() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new lDx(this, dMe));
        }
    }

    @Subscribe
    public void on(SuB suB) {
        if (HvC()) {
            noQ();
        }
    }

    @Override // com.amazon.alexa.Xpk
    public void onConnected() {
        Mlj();
    }

    @Override // com.amazon.alexa.Xpk
    public void onConnectionSuspended() {
    }

    public void uuO() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new kHH(this, dMe));
        }
    }

    public boolean uzr() {
        bYx byx = this.zyO;
        return byx != null && byx.Mlj;
    }

    public boolean vkx() {
        return uzr() && this.zyO.Qle() == 2;
    }

    public void wDP() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new zwg(this, dMe));
        }
    }

    public void yPL() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new RunnableC0227rZl(this, dMe));
        }
    }

    public void zOR() {
        zZm(new Runnable() { // from class: com.amazon.alexa.-$$Lambda$JYe$0xp5kNHP9ZhT1NyXWFC1GZKk-II
            @Override // java.lang.Runnable
            public final void run() {
                JYe.this.MNR();
            }
        });
        HandlerThread handlerThread = this.Mlj;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        this.BIo.BIo(this);
        if (this.Qle.zyO(this.zQM) != null) {
            this.Qle.BIo((qKe) this.zQM);
        }
    }

    public void zQM() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new rDb(this, dMe));
        }
    }

    public void zyO() {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new HVf(this, dMe));
        }
    }

    public HkJ zzR() {
        return this.jiA.zyO(this.zQM);
    }

    public void zZm(uWW uww) {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new UhW(this, uww, dMe));
        }
    }

    public void zZm(STS sts) {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new BRf(this, sts, dMe));
        }
    }

    public void zZm(ErD erD) {
        MediaControllerCompat.TransportControls dMe = dMe();
        if (dMe != null) {
            zZm(new YbF(this, dMe, erD));
        }
    }

    public void zZm() {
        zZm(new Runnable() { // from class: com.amazon.alexa.-$$Lambda$JYe$Kk9rJc25uFv9WCyW2tUt6fbUd1Q
            @Override // java.lang.Runnable
            public final void run() {
                JYe.this.Jhx();
            }
        });
    }

    @VisibleForTesting
    public void zZm(Runnable runnable) {
        Handler handler = this.yPL;
        if (handler != null) {
            handler.post(runnable);
        } else {
            Log.w(this.zZm, "The handler thread is null. Can't proceed.");
        }
    }

    @Override // com.amazon.alexa.Xpk
    public void zZm(String str) {
        Log.w(this.zZm, String.format("MediaBrowserClient connection failed with reason: %s", str));
        if (this.dMe && this.lOf < 3) {
            Qgh();
            return;
        }
        pfe pfeVar = FGE.BIo;
        StringBuilder sb = new StringBuilder();
        PUa pUa = (PUa) pfeVar;
        String str2 = pUa.jiA;
        if (str2 != null && !str2.isEmpty()) {
            sb.append(pUa.jiA);
            if (!pUa.jiA.endsWith(".") && !str.isEmpty()) {
                sb.append(". ");
            }
        }
        if (!str.isEmpty()) {
            sb.append(str);
        }
        this.BIo.zyO(FXk.zZm(this.zQM, new AutoValue_PlayerError(pUa.zZm, pUa.BIo, pUa.zQM, pUa.zyO, sb.toString())));
        Mlj();
    }
}
