package com.amazon.alexa.client.alexaservice.externalmediaplayer;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.amazon.alexa.AlexaService;
import com.amazon.alexa.api.Client;
import com.amazon.alexa.api.ClientRole;
import com.amazon.alexa.api.LeaderSelectionAuthority;
import com.amazon.alexa.pYC;
/* loaded from: classes6.dex */
public class ExternalMediaPlayerBroadcastReceiver extends BroadcastReceiver {
    public static final String zZm = "ExternalMediaPlayerBroadcastReceiver";

    /* loaded from: classes6.dex */
    private static class BIo implements LeaderSelectionAuthority.LeaderSelectionListener {
        public final Context BIo;
        public final Intent zQM;
        public final ExternalMediaPlayerBroadcastReceiver zZm;
        public final BroadcastReceiver.PendingResult zyO;

        public BIo(ExternalMediaPlayerBroadcastReceiver externalMediaPlayerBroadcastReceiver, Context context, Intent intent, BroadcastReceiver.PendingResult pendingResult) {
            this.zZm = externalMediaPlayerBroadcastReceiver;
            this.BIo = context;
            this.zQM = intent;
            this.zyO = pendingResult;
        }

        @Override // com.amazon.alexa.api.LeaderSelectionAuthority.LeaderSelectionListener
        public void onLeaderSelected(ComponentName componentName) {
            this.zZm.zZm(this.BIo, componentName, this.zQM);
            this.zyO.finish();
        }

        @Override // com.amazon.alexa.api.LeaderSelectionAuthority.LeaderSelectionListener
        public void onLeaderSelectionFailed(LeaderSelectionAuthority.LeaderSelectionFailureReason leaderSelectionFailureReason, Throwable th) {
            this.zZm.zZm(leaderSelectionFailureReason, th);
            this.zyO.finish();
        }
    }

    /* loaded from: classes6.dex */
    public static class zZm {
        public static final String zZm;

        static {
            int i = Build.VERSION.SDK_INT;
            zZm = "android.intent.extra.COMPONENT_NAME";
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("com.amazon.alexa.externalmediaplayer.CONNECT".equals(intent.getAction())) {
            ComponentName componentName = (ComponentName) intent.getParcelableExtra(zZm.zZm);
            abortBroadcast();
            if (componentName == null) {
                return;
            }
            BroadcastReceiver.PendingResult goAsync = goAsync();
            if (context.getPackageName().equals(intent.getPackage())) {
                zZm(context, context.getPackageName(), intent);
                goAsync.finish();
                return;
            }
            new LeaderSelectionAuthority(context).pickAlexaService(AlexaService.class.getName(), new BIo(this, context, intent, goAsync));
        }
    }

    public void zZm(Context context, ComponentName componentName, Intent intent) {
        String str = zZm;
        Log.i(str, "Leading Alexa service: " + componentName);
        if (context.getPackageName().equals(componentName.getPackageName())) {
            zZm(context, componentName.getPackageName(), intent);
            return;
        }
        intent.setPackage(componentName.getPackageName());
        context.sendBroadcast(intent);
    }

    public void zZm(LeaderSelectionAuthority.LeaderSelectionFailureReason leaderSelectionFailureReason, Throwable th) {
        Log.e(zZm, "Failed to select leading Alexa service!", th);
        int i = pYC.zZm[leaderSelectionFailureReason.ordinal()];
        if (i == 1) {
            Log.e(zZm, "Signature verification error");
        } else if (i == 2) {
            Log.e(zZm, "Signature verification error - package name was null");
        } else if (i == 3) {
            Log.e(zZm, "Timed out finding an AlexaServices to connect to.");
        } else if (i == 4) {
            Log.e(zZm, "Leader Selection Service disabled");
        } else if (i != 5) {
            Log.e(zZm, "Unknown error encountered while searching for AlexaServices to connect to");
        } else {
            Log.e(zZm, "Could not find an AlexaServices to connect to.");
        }
    }

    public static void zZm(Context context, String str, Intent intent) {
        Intent intent2 = new Intent(context, AlexaService.class);
        intent2.setAction("com.amazon.alexa.externalmediaplayer.ACTION_START_SERVICE_FOR_MSP");
        intent2.setPackage(str);
        intent2.putExtra("EXTRA_CLIENT", new Client(context.getPackageName(), ClientRole.WAKE_UP));
        intent2.putExtra(zZm.zZm, (ComponentName) intent.getParcelableExtra(zZm.zZm));
        int i = Build.VERSION.SDK_INT;
        context.startForegroundService(intent2);
    }
}
