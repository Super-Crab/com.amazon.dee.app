package com.google.android.play.core.splitinstall.testing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.internal.zzaf;
import com.google.android.play.core.internal.zzcf;
import com.google.android.play.core.internal.zzcj;
import com.google.android.play.core.internal.zzco;
import com.google.android.play.core.splitinstall.SplitInstallException;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener;
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public class FakeSplitInstallManager implements SplitInstallManager {
    public static final /* synthetic */ int zza = 0;
    private static final long zzb = TimeUnit.SECONDS.toMillis(1);
    private final Handler zzc;
    private final Context zzd;
    private final com.google.android.play.core.splitinstall.zzs zze;
    private final zzco zzf;
    private final zzcf zzg;
    private final zzaf zzh;
    private final zzaf zzi;
    private final Executor zzj;
    private final com.google.android.play.core.splitinstall.zzg zzk;
    private final File zzl;
    private final AtomicReference zzm;
    private final Set zzn;
    private final Set zzo;
    private final AtomicBoolean zzp;
    private final zzd zzq;

    @Deprecated
    public FakeSplitInstallManager(Context context, File file) {
        this(context, file, new com.google.android.play.core.splitinstall.zzs(context, context.getPackageName()), zzj.zza);
    }

    private final com.google.android.play.core.splitinstall.zzk zzk() {
        com.google.android.play.core.splitinstall.zzk zza2 = this.zze.zza();
        if (zza2 != null) {
            return zza2;
        }
        throw new IllegalStateException("Language information could not be found. Make sure you are using the target application context, not the tests context, and the app is built as a bundle.");
    }

    @Nullable
    private final SplitInstallSessionState zzl() {
        return (SplitInstallSessionState) this.zzm.get();
    }

    @Nullable
    private final synchronized SplitInstallSessionState zzm(zzp zzpVar) {
        SplitInstallSessionState zzl = zzl();
        SplitInstallSessionState zza2 = zzpVar.zza(zzl);
        if (this.zzm.compareAndSet(zzl, zza2)) {
            return zza2;
        }
        return null;
    }

    private final Task zzn(@SplitInstallErrorCode final int i) {
        zzm(new zzp() { // from class: com.google.android.play.core.splitinstall.testing.zzg
            @Override // com.google.android.play.core.splitinstall.testing.zzp
            public final SplitInstallSessionState zza(SplitInstallSessionState splitInstallSessionState) {
                int i2 = i;
                int i3 = FakeSplitInstallManager.zza;
                if (splitInstallSessionState == null) {
                    return null;
                }
                return SplitInstallSessionState.create(splitInstallSessionState.sessionId(), 6, i2, splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.moduleNames(), splitInstallSessionState.languages());
            }
        });
        return Tasks.zza(new SplitInstallException(i));
    }

    private static String zzo(String str) {
        return str.split("\\.config\\.", 2)[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzp(List list, List list2, List list3, long j, boolean z) {
        this.zzk.zza().zzd(list, new zzo(this, list2, list3, j, z, list));
    }

    private final void zzq(final SplitInstallSessionState splitInstallSessionState) {
        this.zzc.post(new Runnable() { // from class: com.google.android.play.core.splitinstall.testing.zzm
            @Override // java.lang.Runnable
            public final void run() {
                FakeSplitInstallManager.this.zzg(splitInstallSessionState);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzr(List list, List list2, long j) {
        this.zzn.addAll(list);
        this.zzo.addAll(list2);
        Long valueOf = Long.valueOf(j);
        zzs(5, 0, valueOf, valueOf, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzs(final int i, final int i2, @Nullable final Long l, @Nullable final Long l2, @Nullable final List list, @Nullable final Integer num, @Nullable final List list2) {
        SplitInstallSessionState zzm = zzm(new zzp() { // from class: com.google.android.play.core.splitinstall.testing.zzi
            @Override // com.google.android.play.core.splitinstall.testing.zzp
            public final SplitInstallSessionState zza(SplitInstallSessionState splitInstallSessionState) {
                long longValue;
                Integer num2 = num;
                int i3 = i;
                int i4 = i2;
                Long l3 = l;
                Long l4 = l2;
                List<String> list3 = list;
                List<String> list4 = list2;
                int i5 = FakeSplitInstallManager.zza;
                SplitInstallSessionState create = splitInstallSessionState == null ? SplitInstallSessionState.create(0, 0, 0, 0L, 0L, new ArrayList(), new ArrayList()) : splitInstallSessionState;
                int sessionId = num2 == null ? create.sessionId() : num2.intValue();
                long bytesDownloaded = l3 == null ? create.bytesDownloaded() : l3.longValue();
                if (l4 == null) {
                    longValue = create.totalBytesToDownload();
                } else {
                    longValue = l4.longValue();
                }
                return SplitInstallSessionState.create(sessionId, i3, i4, bytesDownloaded, longValue, list3 == null ? create.moduleNames() : list3, list4 == null ? create.languages() : list4);
            }
        });
        if (zzm != null) {
            zzq(zzm);
            return true;
        }
        return false;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> cancelInstall(final int i) {
        try {
            SplitInstallSessionState zzm = zzm(new zzp() { // from class: com.google.android.play.core.splitinstall.testing.zzf
                @Override // com.google.android.play.core.splitinstall.testing.zzp
                public final SplitInstallSessionState zza(SplitInstallSessionState splitInstallSessionState) {
                    int status;
                    int i2 = i;
                    int i3 = FakeSplitInstallManager.zza;
                    if (splitInstallSessionState != null && i2 == splitInstallSessionState.sessionId() && ((status = splitInstallSessionState.status()) == 1 || status == 2 || status == 8 || status == 9 || status == 7)) {
                        return SplitInstallSessionState.create(i2, 7, splitInstallSessionState.errorCode(), splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.moduleNames(), splitInstallSessionState.languages());
                    }
                    throw new SplitInstallException(-3);
                }
            });
            if (zzm != null) {
                zzq(zzm);
            }
            return Tasks.zzb(null);
        } catch (SplitInstallException e) {
            return Tasks.zza(e);
        }
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredInstall(List<String> list) {
        return Tasks.zza(new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredLanguageInstall(List<Locale> list) {
        return Tasks.zza(new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredLanguageUninstall(List<Locale> list) {
        return Tasks.zza(new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredUninstall(List<String> list) {
        return Tasks.zza(new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set<String> getInstalledLanguages() {
        HashSet hashSet = new HashSet();
        if (this.zze.zzd() != null) {
            hashSet.addAll(this.zze.zzd());
        }
        hashSet.addAll(this.zzo);
        return hashSet;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set<String> getInstalledModules() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.zze.zzc());
        hashSet.addAll(this.zzn);
        return hashSet;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<SplitInstallSessionState> getSessionState(int i) {
        SplitInstallSessionState zzl = zzl();
        if (zzl != null && zzl.sessionId() == i) {
            return Tasks.zzb(zzl);
        }
        return Tasks.zza(new SplitInstallException(-4));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<List<SplitInstallSessionState>> getSessionStates() {
        SplitInstallSessionState zzl = zzl();
        return Tasks.zzb(zzl != null ? Collections.singletonList(zzl) : Collections.emptyList());
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void registerListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzi.zza(splitInstallStateUpdatedListener);
    }

    public void setShouldNetworkError(boolean z) {
        this.zzp.set(z);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, Activity activity, int i) throws IntentSender.SendIntentException {
        return false;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, IntentSenderForResultStarter intentSenderForResultStarter, int i) throws IntentSender.SendIntentException {
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0130, code lost:
        if (r0.contains(r13) == false) goto L51;
     */
    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.play.core.tasks.Task<java.lang.Integer> startInstall(final com.google.android.play.core.splitinstall.SplitInstallRequest r21) {
        /*
            Method dump skipped, instructions count: 594
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager.startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest):com.google.android.play.core.tasks.Task");
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void unregisterListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzi.zzb(splitInstallStateUpdatedListener);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void zza(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzh.zza(splitInstallStateUpdatedListener);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void zzb(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.zzh.zzb(splitInstallStateUpdatedListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File zzc() {
        return this.zzl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(final long j, final List list, final List list2, final List list3) {
        long j2 = j / 3;
        long j3 = 0;
        for (int i = 0; i < 3; i++) {
            j3 = Math.min(j, j3 + j2);
            zzs(2, 0, Long.valueOf(j3), Long.valueOf(j), null, null, null);
            SystemClock.sleep(zzb);
            SplitInstallSessionState zzl = zzl();
            if (zzl.status() == 9 || zzl.status() == 7 || zzl.status() == 6) {
                return;
            }
        }
        this.zzj.execute(new Runnable() { // from class: com.google.android.play.core.splitinstall.testing.zze
            @Override // java.lang.Runnable
            public final void run() {
                FakeSplitInstallManager.this.zzh(list, list2, list3, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(SplitInstallSessionState splitInstallSessionState) {
        this.zzh.zzc(splitInstallSessionState);
        this.zzi.zzc(splitInstallSessionState);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzh(List list, List list2, List list3, long j) {
        if (this.zzp.get()) {
            zzs(6, -6, null, null, null, null, null);
        } else if (this.zzk.zza() != null) {
            zzp(list, list2, list3, j, false);
        } else {
            zzr(list2, list3, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzi(List list, final List list2) {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            File file = (File) it2.next();
            String zza2 = zzcj.zza(file);
            Uri fromFile = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(fromFile, this.zzd.getContentResolver().getType(fromFile));
            intent.addFlags(1);
            intent.putExtra("module_name", zzo(zza2));
            intent.putExtra("split_id", zza2);
            arrayList.add(intent);
            arrayList2.add(zzo(zzcj.zza(file)));
        }
        SplitInstallSessionState zzl = zzl();
        if (zzl == null) {
            return;
        }
        final long j = zzl.totalBytesToDownload();
        this.zzj.execute(new Runnable() { // from class: com.google.android.play.core.splitinstall.testing.zzl
            @Override // java.lang.Runnable
            public final void run() {
                FakeSplitInstallManager.this.zzf(j, arrayList, arrayList2, list2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FakeSplitInstallManager(Context context, @Nullable File file, com.google.android.play.core.splitinstall.zzs zzsVar, zzco zzcoVar) {
        Executor zza2 = com.google.android.play.core.splitcompat.zzd.zza();
        zzcf zzcfVar = new zzcf(context);
        zzd zzdVar = zzd.zza;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzm = new AtomicReference();
        this.zzn = Collections.synchronizedSet(new HashSet());
        this.zzo = Collections.synchronizedSet(new HashSet());
        this.zzp = new AtomicBoolean(false);
        this.zzd = context;
        this.zzl = file;
        this.zze = zzsVar;
        this.zzf = zzcoVar;
        this.zzj = zza2;
        this.zzg = zzcfVar;
        this.zzq = zzdVar;
        this.zzi = new zzaf();
        this.zzh = new zzaf();
        this.zzk = com.google.android.play.core.splitinstall.zzo.INSTANCE;
    }
}
