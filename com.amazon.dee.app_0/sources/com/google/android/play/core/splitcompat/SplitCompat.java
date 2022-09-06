package com.google.android.play.core.splitcompat;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.play.core.internal.zzau;
import com.google.android.play.core.internal.zzaw;
import com.google.android.play.core.internal.zzay;
import com.google.android.play.core.internal.zzaz;
import com.google.android.play.core.internal.zzba;
import com.google.android.play.core.internal.zzbt;
import com.google.android.play.core.splitinstall.zzbe;
import com.google.android.play.core.splitinstall.zzx;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public class SplitCompat {
    public static final /* synthetic */ int zza = 0;
    private static final AtomicReference zzb = new AtomicReference(null);
    private final zze zzc;
    private final zzbe zzd;
    @GuardedBy("emulatedSplits")
    private final Set zze = new HashSet();
    private final zza zzf;

    private SplitCompat(Context context) {
        try {
            this.zzc = new zze(context);
            this.zzf = new zza(this.zzc);
            this.zzd = new zzbe(context);
        } catch (PackageManager.NameNotFoundException e) {
            throw new zzbt("Failed to initialize FileStorage", e);
        }
    }

    public static boolean install(@NonNull Context context) {
        return zzi(context, false);
    }

    public static boolean installActivity(@NonNull Context context) {
        if (zzj()) {
            return false;
        }
        SplitCompat splitCompat = (SplitCompat) zzb.get();
        if (splitCompat == null) {
            if (context.getApplicationContext() != null) {
                install(context.getApplicationContext());
            }
            return install(context);
        }
        return splitCompat.zzf.zzb(context, splitCompat.zzf());
    }

    public static boolean zzd(Context context) {
        return zzi(context, true);
    }

    public static boolean zze() {
        return zzb.get() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set zzf() {
        HashSet hashSet;
        synchronized (this.zze) {
            hashSet = new HashSet(this.zze);
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzg(Set set) throws IOException {
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            zze.zzl(this.zzc.zzg((String) it2.next()));
        }
        this.zzd.zzb();
    }

    @RequiresApi(21)
    private final synchronized void zzh(Context context, boolean z) throws IOException {
        List<String> asList;
        ZipFile zipFile;
        if (z) {
            this.zzc.zzk();
        } else {
            zzd.zza().execute(new zzp(this));
        }
        String packageName = context.getPackageName();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo.splitNames == null) {
                asList = new ArrayList();
            } else {
                asList = Arrays.asList(packageInfo.splitNames);
            }
            Set<zzs> zzj = this.zzc.zzj();
            Set zza2 = this.zzd.zza();
            HashSet hashSet = new HashSet();
            Iterator it2 = zzj.iterator();
            while (it2.hasNext()) {
                String zzb2 = ((zzs) it2.next()).zzb();
                if (asList.contains(zzb2) || zza2.contains(com.google.android.play.core.splitinstall.zzs.zzb(zzb2))) {
                    hashSet.add(zzb2);
                    it2.remove();
                }
            }
            if (z) {
                zzg(hashSet);
            } else if (!hashSet.isEmpty()) {
                zzd.zza().execute(new zzq(this, hashSet));
            }
            HashSet hashSet2 = new HashSet();
            for (zzs zzsVar : zzj) {
                String zzb3 = zzsVar.zzb();
                if (!com.google.android.play.core.splitinstall.zzs.zzf(zzb3)) {
                    hashSet2.add(zzb3);
                }
            }
            for (String str : asList) {
                if (!com.google.android.play.core.splitinstall.zzs.zzf(str)) {
                    hashSet2.add(str);
                }
            }
            HashSet<zzs> hashSet3 = new HashSet(zzj.size());
            for (zzs zzsVar2 : zzj) {
                if (com.google.android.play.core.splitinstall.zzs.zze(zzsVar2.zzb()) || hashSet2.contains(com.google.android.play.core.splitinstall.zzs.zzb(zzsVar2.zzb()))) {
                    hashSet3.add(zzsVar2);
                }
            }
            zzm zzmVar = new zzm(this.zzc);
            zzaz zza3 = zzba.zza();
            ClassLoader classLoader = context.getClassLoader();
            if (z) {
                zza3.zza(classLoader, zzmVar.zzc());
            } else {
                Iterator it3 = hashSet3.iterator();
                while (it3.hasNext()) {
                    Set zzb4 = zzmVar.zzb((zzs) it3.next());
                    if (zzb4 == null) {
                        it3.remove();
                    } else {
                        zza3.zza(classLoader, zzb4);
                    }
                }
            }
            HashSet hashSet4 = new HashSet();
            for (zzs zzsVar3 : hashSet3) {
                try {
                    zipFile = new ZipFile(zzsVar3.zza());
                    try {
                        ZipEntry entry = zipFile.getEntry("classes.dex");
                        zipFile.close();
                        if (entry != null && !zza3.zzb(classLoader, this.zzc.zza(zzsVar3.zzb()), zzsVar3.zza(), z)) {
                            Log.w("SplitCompat", "split was not installed ".concat(zzsVar3.zza().toString()));
                        }
                        hashSet4.add(zzsVar3.zza());
                    } catch (IOException e) {
                        e = e;
                        if (zipFile != null) {
                            try {
                                zipFile.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw e;
                    }
                } catch (IOException e2) {
                    e = e2;
                    zipFile = null;
                }
            }
            this.zzf.zza(context, hashSet4);
            HashSet hashSet5 = new HashSet();
            for (zzs zzsVar4 : hashSet3) {
                if (hashSet4.contains(zzsVar4.zza())) {
                    String zzb5 = zzsVar4.zzb();
                    StringBuilder sb = new StringBuilder(zzb5.length() + 30);
                    sb.append("Split '");
                    sb.append(zzb5);
                    sb.append("' installation emulated");
                    sb.toString();
                    hashSet5.add(zzsVar4.zzb());
                } else {
                    String zzb6 = zzsVar4.zzb();
                    StringBuilder sb2 = new StringBuilder(zzb6.length() + 35);
                    sb2.append("Split '");
                    sb2.append(zzb6);
                    sb2.append("' installation not emulated.");
                    sb2.toString();
                }
            }
            synchronized (this.zze) {
                this.zze.addAll(hashSet5);
            }
        } catch (PackageManager.NameNotFoundException e3) {
            throw new IOException(String.format("Cannot load data for application '%s'", packageName), e3);
        }
    }

    private static boolean zzi(final Context context, boolean z) {
        if (zzj()) {
            return false;
        }
        boolean compareAndSet = zzb.compareAndSet(null, new SplitCompat(context));
        SplitCompat splitCompat = (SplitCompat) zzb.get();
        if (compareAndSet) {
            com.google.android.play.core.splitinstall.zzo.INSTANCE.zzb(new zzaw(context, zzd.zza(), new zzay(context, splitCompat.zzc, new zzau()), splitCompat.zzc, new zzr(), null));
            com.google.android.play.core.splitinstall.zzr.zzb(new zzo(splitCompat));
            zzd.zza().execute(new Runnable() { // from class: com.google.android.play.core.splitcompat.zzn
                @Override // java.lang.Runnable
                public final void run() {
                    Context context2 = context;
                    int i = SplitCompat.zza;
                    try {
                        zzx.zzc(context2).zzg(true);
                    } catch (SecurityException unused) {
                        Log.e("SplitCompat", "Failed to set broadcast receiver to always on.");
                    }
                }
            });
        }
        try {
            splitCompat.zzh(context, z);
            return true;
        } catch (Exception e) {
            Log.e("SplitCompat", "Error installing additional splits", e);
            return false;
        }
    }

    private static boolean zzj() {
        int i = Build.VERSION.SDK_INT;
        return false;
    }
}
