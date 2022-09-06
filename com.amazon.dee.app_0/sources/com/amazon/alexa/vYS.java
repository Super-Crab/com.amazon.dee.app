package com.amazon.alexa;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.UiModeManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import com.amazon.alexa.api.UserInterfaceOptionsUtils;
import com.amazon.alexa.client.alexaservice.ui.UnlockDeviceActivity;
import com.amazon.alexa.drive.navigation.MappingApplication;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.alexa.voice.ui.VoiceActivity;
import com.amazon.alexa.voiceui.voice.VoiceInteractor;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: UserInterfaceManager.java */
@Singleton
/* loaded from: classes.dex */
public class vYS {
    public static final String zZm = "vYS";
    public final Context BIo;
    public final ActivityManager JTe;
    public final rqw LPk;
    public final Msx Qle;
    public final IYJ jiA;
    public final ScheduledExecutorService zQM;
    public final peZ zyO;

    @Inject
    public vYS(Context context, peZ pez, IYJ iyj, Msx msx, ActivityManager activityManager, rqw rqwVar) {
        ScheduledExecutorService newScheduledExecutor = ExecutorFactory.newScheduledExecutor(1, "launch_maps_intent_executor");
        this.BIo = context;
        this.zyO = pez;
        this.jiA = iyj;
        this.Qle = msx;
        this.JTe = activityManager;
        this.LPk = rqwVar;
        this.zQM = newScheduledExecutor;
    }

    public void jiA() {
        Log.i(zZm, "Stopping Waze navigation");
        Context context = this.BIo;
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://waze.com/ul?a=stop_navigate"));
        intent.setPackage(MappingApplication.WAZE_PACKAGENAME);
        intent.setFlags(67108864);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public void zQM(Intent intent) {
        Log.i(zZm, "Launching navigation over lock screen");
        ComponentName componentName = new ComponentName(MappingApplication.GOOGLE_MAPS_PACKAGENAME, "com.google.android.maps.MapsActivity");
        intent.setFlags(268435456);
        intent.setComponent(componentName);
        Intent intent2 = new Intent();
        Uri parse = Uri.parse("google.maps:?act=17");
        intent2.setAction("android.intent.action.VIEW");
        intent2.setComponent(componentName);
        intent2.setData(parse);
        intent2.setFlags(268435456);
        try {
            this.BIo.startActivity(intent2);
            this.zQM.schedule(new RNp(this, intent), 5000L, TimeUnit.MILLISECONDS);
        } catch (ActivityNotFoundException e) {
            Log.w(zZm, String.format("Activity not found, Message: %s", e.getMessage()));
        }
    }

    public void zyO() {
        Log.i(zZm, "Stopping any ongoing Google Maps navigation");
        Context context = this.BIo;
        ComponentName componentName = new ComponentName(MappingApplication.GOOGLE_MAPS_PACKAGENAME, "com.google.android.maps.MapsActivity");
        Intent intent = new Intent();
        Uri parse = Uri.parse("google.maps:?act=9");
        intent.setAction("android.intent.action.VIEW");
        intent.setComponent(componentName);
        intent.setData(parse);
        intent.setFlags(67108864);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public static boolean zZm(String str) {
        return "HandsFree.MOD".equals(str);
    }

    public void BIo(Intent intent) {
        Log.i(zZm, "Launching Google Maps");
        if (((UiModeManager) this.BIo.getSystemService("uimode")).getCurrentModeType() == 3) {
            zyO();
            this.zQM.schedule(new RNp(this, intent), 5000L, TimeUnit.MILLISECONDS);
            return;
        }
        this.zQM.schedule(new RunnableC0226rVO(this, intent), DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS, TimeUnit.MILLISECONDS);
    }

    public void zZm(esV esv, AlexaDialogExtras alexaDialogExtras) {
        if (!alexaDialogExtras.suppressUserInterface()) {
            zZm(this.BIo, esv, alexaDialogExtras.getAlexaUserInterfaceOptions(), alexaDialogExtras.isUserVoiceVerified() && this.zyO.zZm().preferDisplayOverLockscreenWithVerifiedVoice(), zZm(alexaDialogExtras.getInvocationType()), this.LPk.zQM());
        }
    }

    @VisibleForTesting
    public String BIo() {
        return this.BIo.getPackageName();
    }

    public static void zZm(Context context, esV esv, AlexaUserInterfaceOptions alexaUserInterfaceOptions, boolean z, boolean z2, boolean z3) {
        boolean isKeyguardLocked = ((KeyguardManager) context.getSystemService("keyguard")).isKeyguardLocked();
        String str = zZm;
        Log.i(str, "show UI " + esv + " showOverLockscreen? " + z + " is screen locked? " + isKeyguardLocked);
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        intent.setAction("amazon.intent.action.SHOW_ALEXA_VOICE_UI");
        intent.addFlags(268435456);
        intent.addFlags(65536);
        intent.putExtra(VoiceActivity.EXTRA_LAUNCH_SOURCE, esv.zZm());
        intent.putExtra(VoiceInteractor.EXTRA_SHOW_OVER_LOCKSCREEN, z);
        intent.putExtra("is_screen_locked", isKeyguardLocked);
        intent.putExtra(VoiceActivity.EXTRA_VOICE_ALLOW_LANDSCAPE, z2);
        intent.putExtra(VoiceInteractor.EXTRA_UI_OPTIONS, UserInterfaceOptionsUtils.zZm(alexaUserInterfaceOptions));
        intent.putExtra(VoiceInteractor.EXTRA_IS_DRIVE_MODE_ENABLED, z3);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Log.e(zZm, "Unable to show UI. Could not find UI Activity");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0047, code lost:
        if (r1.pid != android.os.Process.myPid()) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean zQM() {
        /*
            r7 = this;
            android.app.ActivityManager r0 = r7.JTe
            java.util.List r0 = r0.getRunningAppProcesses()
            java.util.Iterator r0 = r0.iterator()
        La:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L53
            java.lang.Object r1 = r0.next()
            android.app.ActivityManager$RunningAppProcessInfo r1 = (android.app.ActivityManager.RunningAppProcessInfo) r1
            java.lang.String r3 = r1.processName
            java.lang.String r4 = r7.BIo()
            boolean r3 = r3.equals(r4)
            r4 = 1
            if (r3 != 0) goto L49
            java.lang.String r3 = r1.processName
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r7.BIo()
            r5.append(r6)
            java.lang.String r6 = ":"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            boolean r3 = r3.startsWith(r5)
            if (r3 != 0) goto L49
            int r3 = r1.pid
            int r5 = android.os.Process.myPid()
            if (r3 != r5) goto L4a
        L49:
            r2 = r4
        L4a:
            if (r2 == 0) goto La
            int r1 = r1.importance
            r2 = 100
            if (r1 != r2) goto La
            return r4
        L53:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.vYS.zQM():boolean");
    }

    public void zZm(Intent intent) {
        Intent intent2 = new Intent();
        intent2.setPackage(this.BIo.getPackageName());
        intent2.setAction("com.amazon.intent.action.SHOW_OVER_LOCK_SCREEN");
        intent2.addFlags(268435456);
        intent2.putExtra(MAPAccountManager.KEY_INTENT, intent);
        try {
            this.BIo.startActivity(intent2);
        } catch (RuntimeException e) {
            Log.w(zZm, String.format("Unable to show UI. Could not find Show Over Lock Screen Activity. Message: %s", e.getMessage()));
        }
    }

    public void zZm(SFx sFx) {
        if (!sFx.getValue().isEmpty()) {
            try {
                Context context = this.BIo;
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sFx.getValue()));
                intent.setFlags(268435456);
                intent.addFlags(2097152);
                intent.addFlags(67108864);
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Log.w(zZm, String.format("Unable to open Google Play store. Activity not found, Message: %s", e.getMessage()));
            }
        }
    }

    public void zZm(Intent intent, AlexaEvent alexaEvent, AlexaEvent alexaEvent2) {
        Log.i(zZm, "requesting unlock device.");
        Intent intent2 = new Intent(this.BIo, UnlockDeviceActivity.class);
        intent2.putExtra("launchIntent", intent);
        intent2.putExtra("unlockSuccessEvent", alexaEvent);
        intent2.putExtra("unlockFailureEvent", alexaEvent2);
        intent2.addFlags(268435456);
        intent2.addFlags(65536);
        this.BIo.startActivity(intent2);
    }

    public boolean zZm() {
        if (!(Build.VERSION.SDK_INT <= 28) && !this.jiA.jiA()) {
            return zQM();
        }
        return true;
    }
}
