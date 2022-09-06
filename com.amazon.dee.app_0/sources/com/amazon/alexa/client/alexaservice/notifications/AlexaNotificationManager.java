package com.amazon.alexa.client.alexaservice.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.AgS;
import com.amazon.alexa.AhI;
import com.amazon.alexa.AlexaService;
import com.amazon.alexa.Bch;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.C0193chr;
import com.amazon.alexa.CKO;
import com.amazon.alexa.CYr;
import com.amazon.alexa.DxN;
import com.amazon.alexa.Ehk;
import com.amazon.alexa.HDT;
import com.amazon.alexa.IKN;
import com.amazon.alexa.IYJ;
import com.amazon.alexa.IyB;
import com.amazon.alexa.JLU;
import com.amazon.alexa.JaC;
import com.amazon.alexa.JfO;
import com.amazon.alexa.KqU;
import com.amazon.alexa.MiL;
import com.amazon.alexa.MuN;
import com.amazon.alexa.NEv;
import com.amazon.alexa.NId;
import com.amazon.alexa.R;
import com.amazon.alexa.RUl;
import com.amazon.alexa.Rby;
import com.amazon.alexa.UuG;
import com.amazon.alexa.WgM;
import com.amazon.alexa.Xoa;
import com.amazon.alexa.Xvi;
import com.amazon.alexa.ZBK;
import com.amazon.alexa.api.AlexaPlaybackState;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.cer;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.dyd;
import com.amazon.alexa.gOn;
import com.amazon.alexa.jiA;
import com.amazon.alexa.jkT;
import com.amazon.alexa.kAu;
import com.amazon.alexa.kdZ;
import com.amazon.alexa.kwY;
import com.amazon.alexa.liS;
import com.amazon.alexa.oqD;
import com.amazon.alexa.pPd;
import com.amazon.alexa.qTm;
import com.amazon.alexa.qTx;
import com.amazon.alexa.qZM;
import com.amazon.alexa.shm;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.amazon.alexa.wLb;
import com.amazon.alexa.wSq;
import com.amazon.alexa.wkf;
import com.amazon.alexa.xNT;
import com.amazon.alexa.xSe;
import com.amazon.alexa.xZV;
import dagger.Lazy;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
@Singleton
/* loaded from: classes6.dex */
public class AlexaNotificationManager extends MediaSessionCompat.Callback {
    public static final String zZm = "AlexaNotificationManager";
    public final Context BIo;
    public boolean HvC;
    public final ScheduledExecutorService JTe;
    public final wLb LPk;
    public final Lazy<ClientConfiguration> Mlj;
    public xNT NXS;
    public boolean Qgh;
    public final liS Qle;
    public volatile boolean Tbw;
    public xNT XWf;
    public volatile zQM dMe;
    public final NotificationManager jiA;
    public PendingIntent lOf;
    public AlexaPlayerInfoState noQ;
    public qTx uzr;
    public wSq vkx;
    public AlexaPlaybackState wDP;
    public final IYJ yPL;
    public final Provider<gOn> zQM;
    public final AlexaClientEventBus zyO;
    public final Bch zzR;

    /* loaded from: classes6.dex */
    public class BIo extends MediaSessionCompat.Callback {
        public BIo() {
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPause() {
            AlexaNotificationManager.this.zyO();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onPlay() {
            AlexaNotificationManager.this.jiA();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSkipToNext() {
            AlexaNotificationManager.this.zQM();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onSkipToPrevious() {
            AlexaNotificationManager.this.Qle();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.Callback
        public void onStop() {
            AlexaNotificationManager.this.zyO();
        }
    }

    /* loaded from: classes6.dex */
    public static class DismissNotificationReceiver extends BroadcastReceiver {
        public static final String zZm = "DismissNotificationReceiver";
        @Inject
        public Bch BIo;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Xoa.zZm(this, ((jiA) new jiA.zZm(null).zZm(new dyd(context)).zZm(new JaC()).zZm()).Xft.mo10268get());
            if (!AlexaNotificationManager.zZm(intent)) {
                String str = zZm;
                StringBuilder zZm2 = C0179Pya.zZm("illegal intent action ");
                zZm2.append(intent.getAction());
                Log.e(str, zZm2.toString());
                return;
            }
            zZm zzm = (zZm) intent.getSerializableExtra("EXTRA_COMMAND_NAME");
            if (zzm != zZm.DISMISSED) {
                String str2 = zZm;
                Log.e(str2, "illegal command: " + zzm);
                return;
            }
            this.BIo.zZm.mo358get().edit().set("played_media", false).commitSynchronously();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public enum zQM {
        NONE,
        IDLE_NOT_DISMISSIBLE,
        IDLE_DISMISSIBLE,
        BUSY;

        public boolean zZm() {
            return equals(IDLE_DISMISSIBLE) || equals(NONE);
        }
    }

    /* loaded from: classes6.dex */
    public enum zZm {
        TAP_TO_TALK,
        MEDIA_PAUSE,
        MEDIA_PLAY,
        MEDIA_PREVIOUS,
        MEDIA_NEXT,
        MEDIA_NOOP,
        DISMISSED
    }

    @Inject
    public AlexaNotificationManager(Context context, Provider<gOn> provider, AlexaClientEventBus alexaClientEventBus, NotificationManager notificationManager, wLb wlb, IYJ iyj, liS lis, Lazy<ClientConfiguration> lazy, Bch bch) {
        ScheduledExecutorService newSingleThreadScheduledExecutor = ManagedExecutorFactory.newSingleThreadScheduledExecutor("notification-manager");
        this.BIo = context;
        this.zQM = provider;
        this.zyO = alexaClientEventBus;
        this.jiA = notificationManager;
        this.Qle = lis;
        this.LPk = wlb;
        this.yPL = iyj;
        this.dMe = zQM.NONE;
        this.vkx = wSq.UNKNOWN;
        this.Mlj = lazy;
        this.zzR = bch;
        this.HvC = false;
        this.wDP = AlexaPlaybackState.NONE;
        int i = Build.VERSION.SDK_INT;
        this.Qgh = false;
        this.Tbw = false;
        this.JTe = newSingleThreadScheduledExecutor;
        xNT xnt = xNT.zZm;
        this.XWf = xnt;
        this.NXS = xnt;
        lis.zZm(new BIo());
        int i2 = Build.VERSION.SDK_INT;
        String string = this.BIo.getString(R.string.amazon_avs_notification_channel_name);
        String string2 = this.BIo.getString(R.string.amazon_avs_notification_channel_description);
        NotificationChannel notificationChannel = new NotificationChannel("alexa_notification_channel", string, 2);
        notificationChannel.setDescription(string2);
        notificationChannel.enableLights(false);
        notificationChannel.enableVibration(false);
        this.jiA.createNotificationChannel(notificationChannel);
        alexaClientEventBus.zZm(this);
    }

    public boolean JTe() {
        return this.zzR.zZm.mo358get().getBoolean("played_media", false);
    }

    public void LPk() {
        this.jiA.cancel(75624873);
        this.dMe = zQM.NONE;
        this.uzr = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void Mlj() {
        /*
            Method dump skipped, instructions count: 328
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.client.alexaservice.notifications.AlexaNotificationManager.Mlj():void");
    }

    public final void Qle() {
        this.zyO.zyO(new C0193chr());
    }

    @Subscribe
    public void on(Ehk ehk) {
        Intent intent = ((xSe) ehk).BIo;
        if ("com.amazon.alexa.intent.action.NOTIFICATION_ACTION_TAKEN".equals(intent.getAction())) {
            zZm zzm = (zZm) intent.getSerializableExtra("EXTRA_COMMAND_NAME");
            if (zzm == null) {
                String str = zZm;
                Log.e(str, "No command attached to intent: " + intent);
                return;
            }
            int ordinal = zzm.ordinal();
            if (ordinal == 0) {
                int ordinal2 = this.vkx.ordinal();
                if (ordinal2 == 1) {
                    this.zyO.zyO(new wkf());
                } else if (ordinal2 != 5 && ordinal2 != 6 && ordinal2 != 7) {
                } else {
                    this.zyO.zyO(new kdZ());
                }
            } else if (ordinal == 1) {
                zyO();
            } else if (ordinal == 2) {
                jiA();
            } else if (ordinal == 3) {
                Qle();
            } else if (ordinal != 4) {
                String str2 = zZm;
                Log.e(str2, "Unrecognized command: " + zzm);
            } else {
                zQM();
            }
        }
    }

    public void yPL() {
        this.Tbw = true;
        if (this.Mlj.mo358get().shouldRemoveNotificationOnTeardown().booleanValue() || !JTe()) {
            this.jiA.cancel(75624873);
        }
        this.dMe = zQM.NONE;
        this.uzr = null;
        this.Qle.teardown();
        this.zyO.BIo(this);
    }

    public MiL BIo() {
        qTx zZm2 = zZm();
        this.dMe = zQM.BUSY;
        JfO jfO = (JfO) zZm2;
        return new oqD(jfO.BIo, jfO.zZm);
    }

    public final void jiA() {
        this.zyO.zyO(new JLU());
    }

    public final void zQM() {
        this.zyO.zyO(new IKN());
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x011d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.amazon.alexa.qTx zZm() {
        /*
            Method dump skipped, instructions count: 880
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.client.alexaservice.notifications.AlexaNotificationManager.zZm():com.amazon.alexa.qTx");
    }

    public final void zyO() {
        this.zyO.zyO(new KqU());
    }

    @Subscribe
    public void on(cer cerVar) {
        Mlj();
    }

    @Subscribe
    public void on(jkT jkt) {
        Mlj();
    }

    @Subscribe
    public void on(ZBK zbk) {
        this.Qgh = false;
        Mlj();
    }

    @Subscribe
    public void on(xZV xzv) {
        Mlj();
    }

    @Subscribe
    public void on(qZM qzm) {
        this.wDP = ((CYr) qzm).BIo;
        Mlj();
    }

    @Subscribe(sticky = true)
    public void on(HDT hdt) {
        this.vkx = ((RUl) hdt).BIo;
        Mlj();
    }

    @Subscribe(sticky = true)
    public void on(qTm qtm) {
        this.HvC = ((Xvi) qtm).BIo;
        Mlj();
    }

    @Subscribe
    public void on(AhI ahI) {
        Mlj();
    }

    @Subscribe
    public void on(CKO cko) {
        xNT xnt = this.XWf;
        IyB iyB = (IyB) cko;
        this.noQ = iyB.BIo;
        switch (shm.BIo[this.noQ.ordinal()]) {
            case 1:
            case 2:
                zZm(xNT.zZm);
                break;
            case 3:
            case 4:
            case 5:
                zZm(iyB.zQM);
                break;
            case 6:
                break;
            default:
                String str = zZm;
                StringBuilder zZm2 = C0179Pya.zZm("unexpected AlexaPlayerInfoState: ");
                zZm2.append(this.noQ);
                Log.wtf(str, zZm2.toString());
                break;
        }
        if (!xNT.zZm.equals(this.XWf) && !this.XWf.equals(xNT.BIo) && !this.XWf.equals(this.NXS) && (xnt.equals(xNT.zZm) || !xnt.equals(iyB.zQM))) {
            this.JTe.schedule(new kwY(this), 1500L, TimeUnit.MILLISECONDS);
            return;
        }
        StringBuilder zZm3 = C0179Pya.zZm("Audio item state change. New state: ");
        zZm3.append(this.noQ);
        zZm3.toString();
        Mlj();
    }

    @Subscribe
    public void on(NId nId) {
        if (!nId.BIo().equals(this.XWf) || !this.noQ.equals(AlexaPlayerInfoState.DONE)) {
            return;
        }
        this.XWf = xNT.zZm;
        Mlj();
    }

    @Subscribe
    public void on(pPd ppd) {
        this.NXS = ((MuN) ppd).BIo;
        if (this.NXS.equals(this.XWf)) {
            Mlj();
        }
    }

    @Subscribe
    public void on(AgS agS) {
        if (((UuG) agS).BIo) {
            zZm(xNT.zZm);
        }
        Mlj();
    }

    @Subscribe
    public void on(WgM wgM) {
        if (xNT.zZm.equals(this.XWf) || xNT.zZm.equals(this.NXS) || !this.XWf.equals(this.NXS) || !((Rby) wgM).BIo.equals(this.NXS)) {
            return;
        }
        Mlj();
    }

    @Subscribe
    public void on(kAu kau) {
        Log.i(zZm, "on: SetMediaNotificationContentIntentEvent");
        DxN dxN = (DxN) kau;
        zZm(dxN.jiA);
        this.zyO.zyO(NEv.zQM.zZm(dxN.BIo));
    }

    public final void zZm(xNT xnt) {
        this.XWf = xnt;
        this.zzR.zZm.mo358get().edit().set("played_media", this.XWf != xNT.zZm).commitSynchronously();
    }

    public void zZm(PendingIntent pendingIntent) {
        this.lOf = pendingIntent;
        Mlj();
    }

    public static boolean zZm(@Nullable Intent intent) {
        return intent != null && "com.amazon.alexa.intent.action.NOTIFICATION_ACTION_TAKEN".equals(intent.getAction());
    }

    public static Intent zZm(Context context, @Nullable Class<?> cls, zZm zzm) {
        Intent intent = cls == null ? new Intent() : new Intent(context, cls);
        intent.setAction("com.amazon.alexa.intent.action.NOTIFICATION_ACTION_TAKEN");
        intent.putExtra("EXTRA_COMMAND_NAME", zzm);
        return intent;
    }

    public final PendingIntent zZm(zZm zzm) {
        int i = Build.VERSION.SDK_INT >= 31 ? 201326592 : 134217728;
        if (zzm.ordinal() != 6) {
            return PendingIntent.getService(this.BIo, zzm.ordinal(), zZm(this.BIo, AlexaService.class, zzm), i);
        }
        return PendingIntent.getBroadcast(this.BIo, zzm.ordinal(), zZm(this.BIo, DismissNotificationReceiver.class, zzm), i);
    }
}
