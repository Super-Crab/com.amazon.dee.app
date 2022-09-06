package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.play.core.assetpacks.model.AssetPackStatus;
import com.google.android.play.core.common.LocalTestingException;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzdo implements zzy {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("FakeAssetPackService");
    private static final AtomicInteger zzb = new AtomicInteger(1);
    private final String zzc;
    private final zzbb zzd;
    private final zzco zze;
    private final Context zzf;
    private final zzed zzg;
    private final com.google.android.play.core.internal.zzco zzh;
    private final zzeb zzi;
    private final Handler zzj = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public zzdo(File file, zzbb zzbbVar, zzco zzcoVar, Context context, zzed zzedVar, com.google.android.play.core.internal.zzco zzcoVar2, zzeb zzebVar) {
        this.zzc = file.getAbsolutePath();
        this.zzd = zzbbVar;
        this.zze = zzcoVar;
        this.zzf = context;
        this.zzg = zzedVar;
        this.zzh = zzcoVar2;
        this.zzi = zzebVar;
    }

    @VisibleForTesting
    static long zzk(@AssetPackStatus int i, long j) {
        if (i != 2) {
            if (i != 3 && i != 4) {
                return 0L;
            }
            return j;
        }
        return j / 2;
    }

    private final Bundle zzp(int i, String str, @AssetPackStatus int i2) throws LocalTestingException {
        Bundle bundle = new Bundle();
        bundle.putInt("app_version_code", this.zzg.zza());
        bundle.putInt("session_id", i);
        File[] zzs = zzs(str);
        ArrayList<String> arrayList = new ArrayList<>();
        long j = 0;
        for (File file : zzs) {
            j += file.length();
            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
            arrayList2.add(i2 == 3 ? new Intent().setData(Uri.EMPTY) : null);
            String zza2 = com.google.android.play.core.internal.zzcj.zza(file);
            bundle.putParcelableArrayList(com.google.android.play.core.assetpacks.model.zzb.zzb("chunk_intents", str, zza2), arrayList2);
            bundle.putString(com.google.android.play.core.assetpacks.model.zzb.zzb("uncompressed_hash_sha256", str, zza2), zzr(file));
            bundle.putLong(com.google.android.play.core.assetpacks.model.zzb.zzb("uncompressed_size", str, zza2), file.length());
            arrayList.add(zza2);
        }
        bundle.putStringArrayList(com.google.android.play.core.assetpacks.model.zzb.zza("slice_ids", str), arrayList);
        bundle.putLong(com.google.android.play.core.assetpacks.model.zzb.zza("pack_version", str), this.zzg.zza());
        bundle.putInt(com.google.android.play.core.assetpacks.model.zzb.zza("status", str), i2);
        bundle.putInt(com.google.android.play.core.assetpacks.model.zzb.zza("error_code", str), 0);
        bundle.putLong(com.google.android.play.core.assetpacks.model.zzb.zza("bytes_downloaded", str), zzk(i2, j));
        bundle.putLong(com.google.android.play.core.assetpacks.model.zzb.zza("total_bytes_to_download", str), j);
        bundle.putStringArrayList("pack_names", new ArrayList<>(Arrays.asList(str)));
        bundle.putLong("bytes_downloaded", zzk(i2, j));
        bundle.putLong("total_bytes_to_download", j);
        final Intent putExtra = new Intent("com.google.android.play.core.assetpacks.receiver.ACTION_SESSION_UPDATE").putExtra("com.google.android.play.core.assetpacks.receiver.EXTRA_SESSION_STATE", bundle);
        this.zzj.post(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzdl
            @Override // java.lang.Runnable
            public final void run() {
                zzdo.this.zzl(putExtra);
            }
        });
        return bundle;
    }

    private final AssetPackState zzq(String str, @AssetPackStatus int i) throws LocalTestingException {
        long j = 0;
        for (File file : zzs(str)) {
            j += file.length();
        }
        return AssetPackState.zzb(str, i, 0, zzk(i, j), j, this.zze.zza(str), 1, String.valueOf(this.zzg.zza()), this.zzi.zza(str));
    }

    private static String zzr(File file) throws LocalTestingException {
        try {
            return zzdq.zza(Arrays.asList(file));
        } catch (IOException e) {
            throw new LocalTestingException(String.format("Could not digest file: %s.", file), e);
        } catch (NoSuchAlgorithmException e2) {
            throw new LocalTestingException("SHA256 algorithm not supported.", e2);
        }
    }

    private final File[] zzs(final String str) throws LocalTestingException {
        File file = new File(this.zzc);
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.google.android.play.core.assetpacks.zzdj
                @Override // java.io.FilenameFilter
                public final boolean accept(File file2, String str2) {
                    return str2.startsWith(String.valueOf(str).concat(ProcessIdUtil.DEFAULT_PROCESSID)) && str2.endsWith(".apk");
                }
            });
            if (listFiles != null) {
                if (listFiles.length != 0) {
                    for (File file2 : listFiles) {
                        if (com.google.android.play.core.internal.zzcj.zza(file2).equals(str)) {
                            return listFiles;
                        }
                    }
                    throw new LocalTestingException(String.format("No main slice available for pack '%s'.", str));
                }
                throw new LocalTestingException(String.format("No APKs available for pack '%s'.", str));
            }
            throw new LocalTestingException(String.format("Failed fetching APKs for pack '%s'.", str));
        }
        throw new LocalTestingException(String.format("Local testing directory '%s' not found.", file));
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final Task zza(int i, String str, String str2, int i2) {
        File[] zzs;
        int i3;
        com.google.android.play.core.tasks.zzi outline14 = GeneratedOutlineSupport1.outline14(zza, "getChunkFileDescriptor(session=%d, %s, %s, %d)", new Object[]{Integer.valueOf(i), str, str2, Integer.valueOf(i2)});
        try {
        } catch (LocalTestingException e) {
            zza.zze("getChunkFileDescriptor failed", e);
            outline14.zzb(e);
        } catch (FileNotFoundException e2) {
            zza.zze("getChunkFileDescriptor failed", e2);
            outline14.zzb(new LocalTestingException("Asset Slice file not found.", e2));
        }
        for (File file : zzs(str)) {
            if (com.google.android.play.core.internal.zzcj.zza(file).equals(str2)) {
                outline14.zzc(ParcelFileDescriptor.open(file, 268435456));
                return outline14.zza();
            }
        }
        throw new LocalTestingException(String.format("Local testing slice for '%s' not found.", str2));
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final Task zzb(final List list, final zzbe zzbeVar, Map map) {
        final com.google.android.play.core.tasks.zzi outline14 = GeneratedOutlineSupport1.outline14(zza, "getPackStates(%s)", new Object[]{list});
        ((Executor) this.zzh.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzdm
            @Override // java.lang.Runnable
            public final void run() {
                zzdo.this.zzm(list, zzbeVar, outline14);
            }
        });
        return outline14.zza();
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final Task zzc(final List list, final List list2, Map map) {
        final com.google.android.play.core.tasks.zzi outline14 = GeneratedOutlineSupport1.outline14(zza, "startDownload(%s)", new Object[]{list2});
        ((Executor) this.zzh.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzdn
            @Override // java.lang.Runnable
            public final void run() {
                zzdo.this.zzo(list2, outline14, list);
            }
        });
        return outline14.zza();
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final Task zzd(Map map) {
        zza.zzd("syncPacks()", new Object[0]);
        return Tasks.zzb(new ArrayList());
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zze(List list) {
        zza.zzd("cancelDownload(%s)", list);
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zzf() {
        zza.zzd("keepAlive", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zzg(int i, String str, String str2, int i2) {
        zza.zzd("notifyChunkTransferred", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zzh(final int i, final String str) {
        zza.zzd("notifyModuleCompleted", new Object[0]);
        ((Executor) this.zzh.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzdk
            @Override // java.lang.Runnable
            public final void run() {
                zzdo.this.zzn(i, str);
            }
        });
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zzi(int i) {
        zza.zzd("notifySessionFailed", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zzj(String str) {
        zza.zzd("removePack(%s)", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzl(Intent intent) {
        this.zzd.zza(this.zzf, intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzm(List list, zzbe zzbeVar, com.google.android.play.core.tasks.zzi zziVar) {
        HashMap hashMap = new HashMap();
        Iterator it2 = list.iterator();
        long j = 0;
        while (it2.hasNext()) {
            String str = (String) it2.next();
            try {
                AssetPackState zzq = zzq(str, ((zze) zzbeVar).zza.zza(8, str));
                j += zzq.totalBytesToDownload();
                hashMap.put(str, zzq);
            } catch (LocalTestingException e) {
                zziVar.zzb(e);
                return;
            }
        }
        zziVar.zzc(new zzbo(j, hashMap));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzn(int i, String str) {
        try {
            zzp(i, str, 4);
        } catch (LocalTestingException e) {
            zza.zze("notifyModuleCompleted failed", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzo(List list, com.google.android.play.core.tasks.zzi zziVar, List list2) {
        HashMap hashMap = new HashMap();
        Iterator it2 = list.iterator();
        long j = 0;
        while (it2.hasNext()) {
            String str = (String) it2.next();
            try {
                AssetPackState zzq = zzq(str, 1);
                j += zzq.totalBytesToDownload();
                hashMap.put(str, zzq);
            } catch (LocalTestingException e) {
                zziVar.zzb(e);
                return;
            }
        }
        Iterator it3 = list.iterator();
        while (it3.hasNext()) {
            String str2 = (String) it3.next();
            try {
                int andIncrement = zzb.getAndIncrement();
                zzp(andIncrement, str2, 1);
                zzp(andIncrement, str2, 2);
                zzp(andIncrement, str2, 3);
            } catch (LocalTestingException e2) {
                zziVar.zzb(e2);
                return;
            }
        }
        Iterator it4 = list2.iterator();
        while (it4.hasNext()) {
            String str3 = (String) it4.next();
            hashMap.put(str3, AssetPackState.zzb(str3, 4, 0, 0L, 0L, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, 1, String.valueOf(this.zzg.zza()), String.valueOf(this.zzg.zza())));
        }
        zziVar.zzc(new zzbo(j, hashMap));
    }
}
