package com.google.android.play.core.splitinstall;

import android.app.Activity;
import android.content.IntentSender;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.tasks.Task;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzaa implements SplitInstallManager {
    private final zzbc zza;
    private final zzx zzb;
    private final zzs zzc;
    private final zzbe zzd;
    private final Handler zze = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(zzbc zzbcVar, zzx zzxVar, zzs zzsVar, zzbe zzbeVar) {
        this.zza = zzbcVar;
        this.zzb = zzxVar;
        this.zzc = zzsVar;
        this.zzd = zzbeVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List zze(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            int i = Build.VERSION.SDK_INT;
            arrayList.add(((Locale) it2.next()).toLanguageTag());
        }
        return arrayList;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> cancelInstall(int i) {
        return this.zza.zzc(i);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredInstall(List<String> list) {
        return this.zza.zzd(list);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredLanguageInstall(List<Locale> list) {
        int i = Build.VERSION.SDK_INT;
        return this.zza.zze(zze(list));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredLanguageUninstall(List<Locale> list) {
        int i = Build.VERSION.SDK_INT;
        return this.zza.zzf(zze(list));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredUninstall(List<String> list) {
        this.zzd.zzc(list);
        return this.zza.zzg(list);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set<String> getInstalledLanguages() {
        Set<String> zzd = this.zzc.zzd();
        return zzd == null ? Collections.emptySet() : zzd;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set<String> getInstalledModules() {
        return this.zzc.zzc();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<SplitInstallSessionState> getSessionState(int i) {
        return this.zza.zzh(i);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<List<SplitInstallSessionState>> getSessionStates() {
        return this.zza.zzi();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final synchronized void registerListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzb.zzf(splitInstallStateUpdatedListener);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, Activity activity, int i) throws IntentSender.SendIntentException {
        return startConfirmationDialogForResult(splitInstallSessionState, new zzz(this, activity), i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x003a, code lost:
        if (r1.containsAll(r2) != false) goto L16;
     */
    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.play.core.tasks.Task<java.lang.Integer> startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest r5) {
        /*
            r4 = this;
            java.util.List r0 = r5.getLanguages()
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto Lc
            int r0 = android.os.Build.VERSION.SDK_INT
        Lc:
            java.util.List r0 = r5.getLanguages()
            com.google.android.play.core.splitinstall.zzs r1 = r4.zzc
            java.util.Set r1 = r1.zzd()
            if (r1 != 0) goto L19
            goto L3c
        L19:
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.Iterator r0 = r0.iterator()
        L22:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L36
            java.lang.Object r3 = r0.next()
            java.util.Locale r3 = (java.util.Locale) r3
            java.lang.String r3 = r3.getLanguage()
            r2.add(r3)
            goto L22
        L36:
            boolean r0 = r1.containsAll(r2)
            if (r0 == 0) goto L73
        L3c:
            java.util.List r0 = r5.getModuleNames()
            com.google.android.play.core.splitinstall.zzs r1 = r4.zzc
            java.util.Set r1 = r1.zzc()
            boolean r0 = r1.containsAll(r0)
            if (r0 == 0) goto L73
            int r0 = android.os.Build.VERSION.SDK_INT
            java.util.List r0 = r5.getModuleNames()
            com.google.android.play.core.splitinstall.zzbe r1 = r4.zzd
            java.util.Set r1 = r1.zza()
            boolean r0 = java.util.Collections.disjoint(r0, r1)
            if (r0 != 0) goto L5f
            goto L73
        L5f:
            android.os.Handler r0 = r4.zze
            com.google.android.play.core.splitinstall.zzy r1 = new com.google.android.play.core.splitinstall.zzy
            r1.<init>(r4, r5)
            r0.post(r1)
            r5 = 0
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            com.google.android.play.core.tasks.Task r5 = com.google.android.play.core.tasks.Tasks.zzb(r5)
            return r5
        L73:
            com.google.android.play.core.splitinstall.zzbe r0 = r4.zzd
            java.util.List r1 = r5.getModuleNames()
            r0.zzd(r1)
            com.google.android.play.core.splitinstall.zzbc r0 = r4.zza
            java.util.List r1 = r5.getModuleNames()
            java.util.List r5 = r5.getLanguages()
            java.util.List r5 = zze(r5)
            com.google.android.play.core.tasks.Task r5 = r0.zzj(r1, r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.zzaa.startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest):com.google.android.play.core.tasks.Task");
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final synchronized void unregisterListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzb.zzh(splitInstallStateUpdatedListener);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final synchronized void zza(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzb.zzk(splitInstallStateUpdatedListener);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final synchronized void zzb(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzb.zzl(splitInstallStateUpdatedListener);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, IntentSenderForResultStarter intentSenderForResultStarter, int i) throws IntentSender.SendIntentException {
        if (splitInstallSessionState.status() != 8 || splitInstallSessionState.resolutionIntent() == null) {
            return false;
        }
        intentSenderForResultStarter.startIntentSenderForResult(splitInstallSessionState.resolutionIntent().getIntentSender(), i, null, 0, 0, 0, null);
        return true;
    }
}
