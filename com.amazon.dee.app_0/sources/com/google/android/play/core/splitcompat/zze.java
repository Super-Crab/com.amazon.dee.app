package com.google.android.play.core.splitcompat;

import android.content.Context;
import android.content.pm.PackageManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.play.core.internal.zzci;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zze {
    private final long zza;
    private final Context zzb;
    private File zzc;

    public zze(Context context) throws PackageManager.NameNotFoundException {
        this.zzb = context;
        this.zza = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
    }

    public static void zzl(File file) throws IOException {
        File[] listFiles;
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                zzl(file2);
            }
        }
        if (!file.exists() || file.delete()) {
            return;
        }
        throw new IOException(String.format("Failed to delete '%s'", file.getAbsolutePath()));
    }

    public static void zzm(File file) {
        file.setWritable(false, true);
        file.setWritable(false, false);
    }

    public static boolean zzp(File file) {
        return !file.canWrite();
    }

    private static File zzq(File file, String str) throws IOException {
        File file2 = new File(file, str);
        if (file2.getCanonicalPath().startsWith(file.getCanonicalPath())) {
            return file2;
        }
        throw new IllegalArgumentException("split ID cannot be placed in target directory");
    }

    private final File zzr() throws IOException {
        File file = new File(zzw(), "native-libraries");
        zzu(file);
        return file;
    }

    private final File zzs(String str) throws IOException {
        File zzq = zzq(zzr(), str);
        zzu(zzq);
        return zzq;
    }

    private final File zzt() throws IOException {
        File file = new File(zzw(), "verified-splits");
        zzu(file);
        return file;
    }

    private static File zzu(File file) throws IOException {
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new IllegalArgumentException("File input must be directory when it exists.");
            }
            return file;
        }
        file.mkdirs();
        if (file.isDirectory()) {
            return file;
        }
        String valueOf = String.valueOf(file.getAbsolutePath());
        throw new IOException(valueOf.length() != 0 ? "Unable to create directory: ".concat(valueOf) : new String("Unable to create directory: "));
    }

    private final File zzv() throws IOException {
        if (this.zzc == null) {
            Context context = this.zzb;
            if (context != null) {
                this.zzc = context.getFilesDir();
            } else {
                throw new IllegalStateException("context must be non-null to populate null filesDir");
            }
        }
        File file = new File(this.zzc, "splitcompat");
        zzu(file);
        return file;
    }

    private final File zzw() throws IOException {
        File file = new File(zzv(), Long.toString(this.zza));
        zzu(file);
        return file;
    }

    private static String zzx(String str) {
        return String.valueOf(str).concat(".apk");
    }

    public final File zza(String str) throws IOException {
        File file = new File(zzw(), "dex");
        zzu(file);
        File zzq = zzq(file, str);
        zzu(zzq);
        return zzq;
    }

    public final File zzb() throws IOException {
        File file = new File(zzw(), "unverified-splits");
        zzu(file);
        return file;
    }

    public final File zzc(String str, String str2) throws IOException {
        return zzq(zzs(str), str2);
    }

    public final File zzd() throws IOException {
        return new File(zzw(), "lock.tmp");
    }

    public final File zze(String str) throws IOException {
        return zzq(zzb(), zzx(str));
    }

    public final File zzf(File file) throws IOException {
        return zzq(zzt(), file.getName());
    }

    public final File zzg(String str) throws IOException {
        return zzq(zzt(), zzx(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List zzh() throws IOException {
        ArrayList arrayList = new ArrayList();
        File[] listFiles = zzr().listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    arrayList.add(file.getName());
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set zzi(String str) throws IOException {
        HashSet hashSet = new HashSet();
        File[] listFiles = zzs(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    hashSet.add(file);
                }
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set zzj() throws IOException {
        String name;
        File zzt = zzt();
        HashSet hashSet = new HashSet();
        File[] listFiles = zzt.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile() && file.getName().endsWith(".apk") && zzp(file)) {
                    hashSet.add(new zzb(file, file.getName().substring(0, name.length() - 4)));
                }
            }
        }
        return hashSet;
    }

    public final void zzk() throws IOException {
        File zzv = zzv();
        String[] list = zzv.list();
        if (list != null) {
            for (String str : list) {
                if (!str.equals(Long.toString(this.zza))) {
                    File file = new File(zzv, str);
                    String obj = file.toString();
                    long j = this.zza;
                    StringBuilder sb = new StringBuilder(obj.length() + 118);
                    GeneratedOutlineSupport1.outline180(sb, "FileStorage: removing directory for different version code (directory = ", obj, ", current version code = ");
                    sb.append(j);
                    sb.append(")");
                    sb.toString();
                    zzl(file);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzn(String str) throws IOException {
        zzl(zzs(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzo(File file) throws IOException {
        zzci.zzb(file.getParentFile().getParentFile().equals(zzr()), "File to remove is not a native library");
        zzl(file);
    }
}
