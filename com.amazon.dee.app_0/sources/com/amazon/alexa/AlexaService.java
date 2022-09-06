package com.amazon.alexa;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaClient;
import com.amazon.alexa.api.AlexaPlaybackState;
import com.amazon.alexa.api.AlexaServicesApisV2_4;
import com.amazon.alexa.api.AlexaVisualTaskFactory;
import com.amazon.alexa.api.Client;
import com.amazon.alexa.api.ClientRole;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.notifications.AlexaNotificationManager;
import com.amazon.alexa.client.crashreporting.BreadcrumbType;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.jiA;
import com.amazon.alexa.system.UserInactivityAuthority;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.internal.Preconditions;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import org.greenrobot.eventbus.Subscribe;
/* loaded from: classes.dex */
public class AlexaService extends Service {
    public static final String zZm = "AlexaService";
    @Inject
    public AlexaClient BIo;
    @Inject
    public AlexaVisualTaskFactory HvC;
    @Inject
    public AHr JTe;
    @Inject
    public UserInactivityAuthority LPk;
    @Inject
    public AlexaNotificationManager Mlj;
    public zZm NXS;
    @Inject
    public MBE Qgh;
    @Inject
    public AlexaClientEventBus Qle;
    @Inject
    public zDj Tbw;
    public MessageReceiver XWf;
    @Inject
    public CGv dMe;
    @Inject
    public tol jiA;
    @Inject
    public KvZ lOf;
    @Inject
    public gSO noQ;
    @Inject
    public Mlj uzr;
    @Inject
    @Named("shared_scheduler")
    public ScheduledExecutorService vkx;
    @Inject
    public pYn wDP;
    @Inject
    public IYJ yPL;
    @Inject
    public UYN zQM;
    @Inject
    public CrashReporter zyO;
    @Inject
    public wLb zzR;

    @VisibleForTesting
    /* loaded from: classes.dex */
    class BIo implements Runnable {
        public BIo() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!AlexaService.this.zzR.zZm()) {
                String str = AlexaService.zZm;
                AlexaService alexaService = AlexaService.this;
                alexaService.BIo(!alexaService.Mlj.JTe());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class zZm {
        public final ScheduledExecutorService BIo;
        public long jiA;
        public final Runnable zQM;
        public final TimeProvider zZm;
        public ScheduledFuture<?> zyO;

        public zZm(TimeProvider timeProvider, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
            this.zZm = timeProvider;
            this.BIo = scheduledExecutorService;
            this.zQM = runnable;
        }

        public synchronized void BIo() {
            this.jiA = this.zZm.elapsedRealTime();
        }

        public synchronized void zZm() {
            long j;
            ScheduledFuture<?> scheduledFuture;
            long elapsedRealTime = this.zZm.elapsedRealTime();
            long j2 = this.jiA;
            if (j2 > 0) {
                j = 30000 - (elapsedRealTime - j2);
                if (j < 1000) {
                    this.jiA = 0L;
                }
                scheduledFuture = this.zyO;
                if (scheduledFuture != null && !scheduledFuture.isDone()) {
                    this.zyO.cancel(false);
                }
                this.zyO = this.BIo.schedule(this.zQM, j, TimeUnit.MILLISECONDS);
            }
            j = 1000;
            scheduledFuture = this.zyO;
            if (scheduledFuture != null) {
                this.zyO.cancel(false);
            }
            this.zyO = this.BIo.schedule(this.zQM, j, TimeUnit.MILLISECONDS);
        }
    }

    public static void wakeUp(Context context) {
        Intent intent = new Intent("ACTION_WAKE_UP");
        intent.setComponent(new ComponentName(context, AlexaService.class));
        intent.putExtra("EXTRA_CLIENT", new Client(context.getPackageName(), ClientRole.WAKE_UP));
        int i = Build.VERSION.SDK_INT;
        context.startForegroundService(intent);
    }

    public final ComponentName BIo() {
        return new ComponentName(this, "com.amazon.alexa.alertsca.AlertsCapabilityAgentService");
    }

    @Subscribe(sticky = true)
    public void on(Bob bob) {
        if (!bob.BIo()) {
            zQM();
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        String str = zZm;
        Log.i(str, "onBind " + intent);
        zZm(intent);
        return this.XWf.getMessenger().getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("onCreate ");
        zZm2.append(getPackageName());
        zZm2.append(" instance: ");
        zZm2.append(this);
        Log.i(str, zZm2.toString());
        long elapsedRealtime = SystemClock.elapsedRealtime();
        jiA.zZm zzm = new jiA.zZm(null);
        Preconditions.checkNotNull(new zVs(getApplication()));
        jiA jia = (jiA) zzm.zZm(new dyd(getApplicationContext())).zZm();
        zQM.zZm(this, jia.srS.mo10268get());
        zQM.zZm(this, jia.noQ.mo10268get());
        zQM.zZm(this, jia.wDP.mo10268get());
        zQM.zZm(this, jia.mdH.mo10268get());
        zQM.zZm(this, jia.JTe.mo10268get());
        zQM.zZm(this, jia.Ygi.mo10268get());
        zQM.zZm(this, jia.cWz.mo10268get());
        zQM.zZm(this, jia.Mlj.mo10268get());
        zQM.zZm(this, jia.ZBj.mo10268get());
        zQM.zZm(this, jia.xFM.mo10268get());
        zQM.zZm(this, jia.Qgh.mo10268get());
        zQM.zZm(this, jia.lsL.mo10268get());
        zQM.zZm(this, jia.RmH.mo10268get());
        zQM.zZm(this, jia.qgo.mo10268get());
        zQM.zZm(this, jia.zZm.mo10268get());
        zQM.zZm(this, jia.Arv.mo10268get());
        zQM.zZm(this, jia.LPk.mo10268get());
        zQM.zZm(this, jia.bqj.mo10268get());
        zQM.zZm(this, jia.oGE.mo10268get());
        ((Qqv) this.wDP).zZm();
        this.NXS = new zZm(new TimeProvider(), this.vkx, new BIo());
        MessageReceiversManager zZm3 = this.lOf.zZm();
        this.XWf = zZm3.createMessageReceiver(new AlexaServicesApisV2_4(this.BIo, this.zQM, this.dMe, this.uzr, this.HvC, zZm3));
        this.Qle.zZm(this);
        this.Qle.zyO(new QNM(elapsedRealtime, SystemClock.elapsedRealtime()));
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i(zZm, "onDestroy");
        this.zyO.clearBreadcrumbs();
        this.Qle.BIo(this);
        this.BIo.teardown();
        this.lOf.BIo();
        this.vkx.shutdown();
        ManagedExecutorFactory.clear();
        zZm(!this.Mlj.JTe());
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        String str = zZm;
        Log.i(str, "onRebind " + intent);
        zZm(intent);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        ExtendedClient extendedClient;
        String str = zZm;
        Log.i(str, "onStartCommand: " + intent);
        HashMap hashMap = new HashMap();
        if (intent != null) {
            hashMap.put("ACTION", Objects.toString(intent.getAction()));
            hashMap.put("PACKAGE", Objects.toString(intent.getPackage()));
            hashMap.put("TYPE", Objects.toString(intent.getType()));
            hashMap.put("URI", Objects.toString(intent.getData()));
            Bundle extras = intent.getExtras();
            if (extras != null) {
                for (String str2 : extras.keySet()) {
                    hashMap.put(C0179Pya.zZm("EXTRAS_", str2), Objects.toString(extras.get(str2)));
                }
            }
        }
        this.zyO.leaveBreadcrumb("SERVICE_START_INTENT", BreadcrumbType.STATE, hashMap);
        if (intent != null && intent.getAction() != null && !intent.getAction().isEmpty()) {
            if (BIo(intent)) {
                zZm();
            }
            if (!"ACTION_CLIENT_STARTING_SERVICE".equals(intent.getAction())) {
                if ("ACTION_CLIENT_CONNECTING".equals(intent.getAction())) {
                    Bundle bundleExtra = intent.getBundleExtra("EXTRA_EXTENDED_CLIENT");
                    if (bundleExtra == null) {
                        Client client = (Client) intent.getParcelableExtra("EXTRA_CLIENT");
                        if (client == null) {
                            Log.e(zZm, "Intent did not contain a Client");
                        } else {
                            extendedClient = ExtendedClient.from(client);
                        }
                    } else {
                        extendedClient = new ExtendedClient(bundleExtra);
                    }
                    this.yPL.BIo(extendedClient, BIo(intent));
                    this.vkx.submit(new com.amazon.alexa.BIo(this, extendedClient));
                } else if ("ACTION_WAKE_UP".equals(intent.getAction())) {
                    if (((Client) intent.getParcelableExtra("EXTRA_CLIENT")) == null) {
                        Log.e(zZm, "Intent did not contain a Client");
                    } else {
                        this.NXS.BIo();
                        this.Qle.zyO(uXi.BIo());
                        zZm();
                    }
                } else {
                    StringBuilder zZm2 = C0179Pya.zZm("Intent is unhandled, broadcasting internally: ");
                    zZm2.append(intent.getAction());
                    zZm2.toString();
                    this.Qle.zyO(new xSe(intent));
                }
            }
            StringBuilder zZm3 = C0179Pya.zZm("AlexaClient is : ");
            zZm3.append(this.BIo);
            zZm3.toString();
            return 2;
        }
        String str3 = zZm;
        Log.e(str3, "Intent ignored because it was null, was missing an action, or had an empty action. Intent: " + intent);
        zQM();
        return 2;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        String str = zZm;
        Log.i(str, "onUnbind " + intent);
        if (!"ACTION_SEND_USER_INACTIVITY_REPORT".equals(intent.getAction())) {
            for (ExtendedClient extendedClient : this.yPL.zZm()) {
                this.Qle.zyO(xZV.zZm(extendedClient));
            }
            this.LPk.zQM();
            this.Qle.jiA(new dZg(false));
        }
        zQM();
        return true;
    }

    public final void zQM() {
        if (this.zzR.zZm()) {
            return;
        }
        this.NXS.zZm();
    }

    public final boolean BIo(Intent intent) {
        return !(intent.getSerializableExtra("EXTRA_COMMAND_NAME") != null && ((AlexaNotificationManager.zZm) intent.getSerializableExtra("EXTRA_COMMAND_NAME")).equals(AlexaNotificationManager.zZm.MEDIA_PAUSE)) && intent.getBooleanExtra("EXTRA_REQUIRES_FOREGROUND", true);
    }

    public final void zZm() {
        int i = Build.VERSION.SDK_INT;
        on(this.Mlj.BIo());
        this.Qle.zyO(AgS.BIo());
    }

    @Subscribe
    public void on(qZM qzm) {
        if (AlexaPlaybackState.NONE.equals(((CYr) qzm).BIo)) {
            zQM();
        }
    }

    public final void BIo(boolean z) {
        Log.i(zZm, "stopping service");
        zZm(z);
        stopSelf();
    }

    public final void zZm(boolean z) {
        GeneratedOutlineSupport1.outline172("Stop foreground. Remove notification? ", z);
        int i = Build.VERSION.SDK_INT;
        stopForeground(z ? 1 : 2);
    }

    @Subscribe
    public void on(xZV xzv) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("Client Disconnected: ");
        zZm2.append(((uyC) xzv).BIo.getId());
        Log.i(str, zZm2.toString());
        zQM();
    }

    public final void zZm(Intent intent) {
        if (!"ACTION_SEND_USER_INACTIVITY_REPORT".equals(intent.getAction())) {
            this.LPk.zQM();
            this.Qle.jiA(new dZg(true));
        }
    }

    @Subscribe
    public void on(bFb bfb) {
        Log.i(zZm, "User logged out, stopping service");
        try {
            getPackageManager().getServiceInfo(BIo(), 131072);
            Intent intent = new Intent();
            intent.setAction("com.amazon.alexa.alertsca.intent.action.LOG_OUT");
            intent.setComponent(BIo());
            StringBuilder sb = new StringBuilder();
            sb.append("Sending log out to: ");
            sb.append(BIo());
            sb.toString();
            try {
                startService(intent);
            } catch (Exception e) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Couldn't send log out to Alerts Service due to exception: ");
                sb2.append(e);
                sb2.toString();
            }
        } catch (PackageManager.NameNotFoundException unused) {
            String str = zZm;
            Log.i(str, BIo() + " package does not exist");
        }
        this.BIo.onLogOut();
        BIo(true);
    }

    @Subscribe
    public void on(ZnH znH) {
        BIo(true);
    }

    @Subscribe
    public void on(MiL miL) {
        oqD oqd = (oqD) miL;
        startForeground(oqd.BIo, oqd.zQM);
    }

    @Subscribe
    public void on(Ust ust) {
        zZm(((Noz) ust).BIo);
        if (!this.jiA.zyO()) {
            Log.i(zZm, "No user is logged in. Triggering user state cleanup");
            this.BIo.onLogOut();
        }
        zQM();
    }
}
