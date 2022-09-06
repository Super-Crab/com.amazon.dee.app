package com.amazon.alexa.client.alexaservice.ui;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.IBg;
import com.amazon.alexa.Mit;
import com.amazon.alexa.PXQ;
import com.amazon.alexa.TVC;
import com.amazon.alexa.adM;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaServices;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.dyd;
import com.amazon.alexa.hgH;
import com.amazon.alexa.rtr;
import com.amazon.alexa.utils.TimeProvider;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public class UnlockDeviceActivity extends Activity {
    public static final long BIo = TimeUnit.MILLISECONDS.convert(20, TimeUnit.SECONDS);
    public static final IntentFilter zQM = new IntentFilter("android.intent.action.USER_PRESENT");
    public static final String zZm = "UnlockDeviceActivity";
    @VisibleForTesting
    public BIo JTe;
    @VisibleForTesting
    public zZm LPk;
    public boolean Mlj;
    @Inject
    public TimeProvider Qle;
    @Inject
    public PowerManager jiA;
    public AlexaServicesConnection lOf;
    public PowerManager.WakeLock yPL;
    @Inject
    public KeyguardManager zyO;
    public volatile boolean zzR;
    public final Queue<AlexaEvent> dMe = new LinkedList();
    public boolean uzr = false;

    @VisibleForTesting
    /* loaded from: classes6.dex */
    class BIo extends BroadcastReceiver {
        @VisibleForTesting
        public final TimeProvider BIo;
        public long zZm;

        public BIo(TimeProvider timeProvider) {
            this.BIo = timeProvider;
            this.zZm = timeProvider.elapsedRealTime();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            context.unregisterReceiver(this);
            UnlockDeviceActivity.this.zzR = false;
            if (this.BIo.elapsedRealTime() - this.zZm < UnlockDeviceActivity.BIo) {
                UnlockDeviceActivity.this.BIo();
                return;
            }
            UnlockDeviceActivity.this.zZm(false);
            UnlockDeviceActivity.zZm(UnlockDeviceActivity.this);
        }
    }

    @VisibleForTesting
    /* loaded from: classes6.dex */
    class zZm implements AlexaServicesConnection.ConnectionListener {
        public zZm() {
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            Log.i(UnlockDeviceActivity.zZm, "alexaServicesConnection connected.");
            while (UnlockDeviceActivity.this.dMe.size() > 0) {
                String str = UnlockDeviceActivity.zZm;
                UnlockDeviceActivity unlockDeviceActivity = UnlockDeviceActivity.this;
                unlockDeviceActivity.zZm((AlexaEvent) unlockDeviceActivity.dMe.remove());
            }
            if (UnlockDeviceActivity.this.uzr) {
                UnlockDeviceActivity.this.finish();
            }
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
            String str2 = UnlockDeviceActivity.zZm;
            StringBuilder zZm = C0179Pya.zZm("alexaServicesConnection failed to connect: ");
            zZm.append(alexaConnectingFailedReason.name());
            Log.w(str2, zZm.toString());
            if (UnlockDeviceActivity.this.dMe.size() > 0) {
                Log.e(UnlockDeviceActivity.zZm, "failed to send events in queue, alexa service connection failed.");
            }
            if (UnlockDeviceActivity.this.uzr) {
                UnlockDeviceActivity.this.finish();
            }
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
            Log.i(UnlockDeviceActivity.zZm, "alexaServicesConnection disconnected.");
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        zQM();
        dyd dydVar = (dyd) Preconditions.checkNotNull(new dyd(getApplicationContext()));
        Mit mit = (Mit) Preconditions.checkNotNull(new Mit());
        Preconditions.checkBuilderRequirement(dydVar, dyd.class);
        if (mit == null) {
            mit = new Mit();
        }
        Provider provider = DoubleCheck.provider(PXQ.zZm(dydVar));
        Provider provider2 = DoubleCheck.provider(new adM(dydVar, provider));
        Provider provider3 = DoubleCheck.provider(new TVC(dydVar, provider));
        Provider provider4 = DoubleCheck.provider(new hgH(mit));
        IBg.zZm(this, (KeyguardManager) provider2.mo10268get());
        IBg.zZm(this, (PowerManager) provider3.mo10268get());
        IBg.zZm(this, (TimeProvider) provider4.mo10268get());
        this.yPL = this.jiA.newWakeLock(268435466, "vox:unlockDeviceActivity");
        this.JTe = new BIo(this.Qle);
        int i = Build.VERSION.SDK_INT;
        this.LPk = new zZm();
        this.lOf = new AlexaServicesConnection(getApplicationContext());
        this.lOf.registerListener(this.LPk);
        this.lOf.connect();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.zzR) {
            unregisterReceiver(this.JTe);
            this.zzR = false;
        }
        this.lOf.deregisterListener(this.LPk);
        this.lOf.disconnect();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        zQM();
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        this.yPL.acquire();
        if (this.zyO.isKeyguardLocked()) {
            if (this.Mlj) {
                return;
            }
            this.Mlj = true;
            int i = Build.VERSION.SDK_INT;
            this.zyO.requestDismissKeyguard(this, new rtr(this));
            return;
        }
        BIo();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        zQM();
        this.yPL.release();
        int i = Build.VERSION.SDK_INT;
    }

    @VisibleForTesting
    public void BIo() {
        Intent intent = getIntent();
        Intent intent2 = intent == null ? null : (Intent) intent.getParcelableExtra("launchIntent");
        if (intent2 != null) {
            try {
                startActivity(intent2);
                zZm(true);
            } catch (ActivityNotFoundException e) {
                String str = zZm;
                StringBuilder zZm2 = C0179Pya.zZm("Activity not found: ");
                zZm2.append(e.getMessage());
                Log.w(str, zZm2.toString());
                zZm(false);
            }
        }
        this.uzr = true;
        if (this.dMe.size() == 0) {
            finish();
        }
    }

    public final void zQM() {
        overridePendingTransition(0, 0);
    }

    @VisibleForTesting
    public void zZm(boolean z) {
        AlexaEvent alexaEvent;
        Intent intent = getIntent();
        if (intent == null) {
            alexaEvent = null;
        } else if (z) {
            alexaEvent = (AlexaEvent) intent.getParcelableExtra("unlockSuccessEvent");
        } else {
            alexaEvent = (AlexaEvent) intent.getParcelableExtra("unlockFailureEvent");
        }
        if (alexaEvent == null) {
            return;
        }
        if (zZm()) {
            zZm(alexaEvent);
        } else {
            BIo(alexaEvent);
        }
    }

    @VisibleForTesting
    public boolean zZm() {
        return this.lOf.isConnected();
    }

    @VisibleForTesting
    public void zZm(AlexaEvent alexaEvent) {
        AlexaServices.EventSender.send(this.lOf, alexaEvent);
    }

    public static /* synthetic */ void zZm(UnlockDeviceActivity unlockDeviceActivity) {
        unlockDeviceActivity.uzr = true;
        if (unlockDeviceActivity.dMe.size() == 0) {
            unlockDeviceActivity.finish();
        }
    }

    @VisibleForTesting
    public void BIo(AlexaEvent alexaEvent) {
        if (this.dMe.size() > 20) {
            Log.e(zZm, "max pending event reached. dropping the event.");
        } else {
            this.dMe.add(alexaEvent);
        }
    }
}
