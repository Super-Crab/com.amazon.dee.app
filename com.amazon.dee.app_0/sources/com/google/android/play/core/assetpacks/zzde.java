package com.google.android.play.core.assetpacks;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.play.core.assetpacks.model.AssetPackStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzde {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("ExtractorSessionStoreView");
    private final zzbh zzb;
    private final com.google.android.play.core.internal.zzco zzc;
    private final zzco zzd;
    private final com.google.android.play.core.internal.zzco zze;
    private final Map zzf = new HashMap();
    private final ReentrantLock zzg = new ReentrantLock();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzde(zzbh zzbhVar, com.google.android.play.core.internal.zzco zzcoVar, zzco zzcoVar2, com.google.android.play.core.internal.zzco zzcoVar3) {
        this.zzb = zzbhVar;
        this.zzc = zzcoVar;
        this.zzd = zzcoVar2;
        this.zze = zzcoVar3;
    }

    private final zzdb zzq(int i) {
        Map map = this.zzf;
        Integer valueOf = Integer.valueOf(i);
        zzdb zzdbVar = (zzdb) map.get(valueOf);
        if (zzdbVar != null) {
            return zzdbVar;
        }
        throw new zzck(String.format("Could not find session %d while trying to get it", valueOf), i);
    }

    private final Object zzr(zzdd zzddVar) {
        try {
            this.zzg.lock();
            return zzddVar.zza();
        } finally {
            this.zzg.unlock();
        }
    }

    private static String zzs(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("pack_names");
        if (stringArrayList != null && !stringArrayList.isEmpty()) {
            return stringArrayList.get(0);
        }
        throw new zzck("Session without pack received.");
    }

    private static List zzt(List list) {
        return list == null ? Collections.emptyList() : list;
    }

    private final Map zzu(final List list) {
        return (Map) zzr(new zzdd() { // from class: com.google.android.play.core.assetpacks.zzcx
            @Override // com.google.android.play.core.assetpacks.zzdd
            public final Object zza() {
                return zzde.this.zzi(list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Boolean zza(Bundle bundle) {
        int i = bundle.getInt("session_id");
        if (i == 0) {
            return true;
        }
        Map map = this.zzf;
        Integer valueOf = Integer.valueOf(i);
        if (!map.containsKey(valueOf)) {
            return true;
        }
        zzdb zzdbVar = (zzdb) this.zzf.get(valueOf);
        if (zzdbVar.zzc.zzd == 6) {
            return false;
        }
        return Boolean.valueOf(!zzbg.zzc(zzdbVar.zzc.zzd, bundle.getInt(com.google.android.play.core.assetpacks.model.zzb.zza("status", zzs(bundle)))));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Boolean zzb(Bundle bundle) {
        boolean z;
        boolean z2;
        zzdc zzdcVar;
        int i = bundle.getInt("session_id");
        boolean z3 = false;
        if (i == 0) {
            return false;
        }
        Map map = this.zzf;
        Integer valueOf = Integer.valueOf(i);
        boolean z4 = true;
        if (map.containsKey(valueOf)) {
            zzdb zzq = zzq(i);
            int i2 = bundle.getInt(com.google.android.play.core.assetpacks.model.zzb.zza("status", zzq.zzc.zza));
            zzda zzdaVar = zzq.zzc;
            int i3 = zzdaVar.zzd;
            if (zzbg.zzc(i3, i2)) {
                zza.zza("Found stale update for session %s with status %d.", valueOf, Integer.valueOf(i3));
                zzda zzdaVar2 = zzq.zzc;
                String str = zzdaVar2.zza;
                int i4 = zzdaVar2.zzd;
                if (i4 == 4) {
                    ((zzy) this.zzc.zza()).zzh(i, str);
                } else if (i4 == 5) {
                    ((zzy) this.zzc.zza()).zzi(i);
                } else if (i4 == 6) {
                    ((zzy) this.zzc.zza()).zze(Arrays.asList(str));
                }
            } else {
                zzdaVar.zzd = i2;
                if (zzbg.zzd(i2)) {
                    zzn(i);
                    this.zzd.zzc(zzq.zzc.zza);
                } else {
                    for (zzdc zzdcVar2 : zzdaVar.zzf) {
                        ArrayList parcelableArrayList = bundle.getParcelableArrayList(com.google.android.play.core.assetpacks.model.zzb.zzb("chunk_intents", zzq.zzc.zza, zzdcVar2.zza));
                        if (parcelableArrayList != null) {
                            for (int i5 = 0; i5 < parcelableArrayList.size(); i5++) {
                                if (parcelableArrayList.get(i5) != null && ((Intent) parcelableArrayList.get(i5)).getData() != null) {
                                    ((zzcz) zzdcVar2.zzd.get(i5)).zza = true;
                                }
                            }
                        }
                    }
                }
            }
            z = true;
        } else {
            String zzs = zzs(bundle);
            long j = bundle.getLong(com.google.android.play.core.assetpacks.model.zzb.zza("pack_version", zzs));
            String string = bundle.getString(com.google.android.play.core.assetpacks.model.zzb.zza("pack_version_tag", zzs), "");
            int i6 = bundle.getInt(com.google.android.play.core.assetpacks.model.zzb.zza("status", zzs));
            long j2 = bundle.getLong(com.google.android.play.core.assetpacks.model.zzb.zza("total_bytes_to_download", zzs));
            ArrayList<String> stringArrayList = bundle.getStringArrayList(com.google.android.play.core.assetpacks.model.zzb.zza("slice_ids", zzs));
            ArrayList arrayList = new ArrayList();
            for (String str2 : zzt(stringArrayList)) {
                ArrayList parcelableArrayList2 = bundle.getParcelableArrayList(com.google.android.play.core.assetpacks.model.zzb.zzb("chunk_intents", zzs, str2));
                ArrayList arrayList2 = new ArrayList();
                for (Intent intent : zzt(parcelableArrayList2)) {
                    if (intent == null) {
                        z4 = z3;
                    }
                    arrayList2.add(new zzcz(z4));
                    z3 = false;
                    z4 = true;
                }
                String string2 = bundle.getString(com.google.android.play.core.assetpacks.model.zzb.zzb("uncompressed_hash_sha256", zzs, str2));
                long j3 = bundle.getLong(com.google.android.play.core.assetpacks.model.zzb.zzb("uncompressed_size", zzs, str2));
                int i7 = bundle.getInt(com.google.android.play.core.assetpacks.model.zzb.zzb("patch_format", zzs, str2), 0);
                if (i7 != 0) {
                    zzdcVar = new zzdc(str2, string2, j3, arrayList2, 0, i7);
                    z2 = false;
                } else {
                    z2 = false;
                    zzdcVar = new zzdc(str2, string2, j3, arrayList2, bundle.getInt(com.google.android.play.core.assetpacks.model.zzb.zzb("compression_format", zzs, str2), 0), 0);
                }
                arrayList.add(zzdcVar);
                z3 = z2;
                z4 = true;
            }
            this.zzf.put(Integer.valueOf(i), new zzdb(i, bundle.getInt("app_version_code"), new zzda(zzs, j, i6, j2, arrayList, string)));
            z = true;
        }
        return Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object zzc(String str, int i, long j) {
        zzdb zzdbVar = (zzdb) zzu(Arrays.asList(str)).get(str);
        if (zzdbVar == null || zzbg.zzd(zzdbVar.zzc.zzd)) {
            zza.zzb(String.format("Could not find pack %s while trying to complete it", str), new Object[0]);
        }
        this.zzb.zzE(str, i, j);
        zzdbVar.zzc.zzd = 4;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object zzd(int i, int i2) {
        zzq(i).zzc.zzd = 5;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object zze(int i) {
        zzdb zzq = zzq(i);
        zzda zzdaVar = zzq.zzc;
        if (zzbg.zzd(zzdaVar.zzd)) {
            this.zzb.zzE(zzdaVar.zza, zzq.zzb, zzdaVar.zzb);
            zzda zzdaVar2 = zzq.zzc;
            int i2 = zzdaVar2.zzd;
            if (i2 != 5 && i2 != 6) {
                return null;
            }
            this.zzb.zzF(zzdaVar2.zza, zzq.zzb, zzdaVar2.zzb);
            return null;
        }
        throw new zzck(String.format("Could not safely delete session %d because it is not in a terminal state.", Integer.valueOf(i)), i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map zzf(final List list) {
        return (Map) zzr(new zzdd() { // from class: com.google.android.play.core.assetpacks.zzcw
            @Override // com.google.android.play.core.assetpacks.zzdd
            public final Object zza() {
                return zzde.this.zzh(list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map zzg() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Map zzh(List list) {
        Map zzu = zzu(list);
        HashMap hashMap = new HashMap();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            String str = (String) it2.next();
            final zzdb zzdbVar = (zzdb) zzu.get(str);
            if (zzdbVar == null) {
                hashMap.put(str, 8);
            } else {
                zzda zzdaVar = zzdbVar.zzc;
                if (zzbg.zza(zzdaVar.zzd)) {
                    try {
                        zzdaVar.zzd = 6;
                        ((Executor) this.zze.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzcy
                            @Override // java.lang.Runnable
                            public final void run() {
                                zzde.this.zzn(zzdbVar.zza);
                            }
                        });
                        this.zzd.zzc(str);
                    } catch (zzck unused) {
                        zza.zzd("Session %d with pack %s does not exist, no need to cancel.", Integer.valueOf(zzdbVar.zza), str);
                    }
                }
                hashMap.put(str, Integer.valueOf(zzdbVar.zzc.zzd));
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Map zzi(List list) {
        HashMap hashMap = new HashMap();
        for (zzdb zzdbVar : this.zzf.values()) {
            String str = zzdbVar.zzc.zza;
            if (list.contains(str)) {
                zzdb zzdbVar2 = (zzdb) hashMap.get(str);
                if ((zzdbVar2 == null ? -1 : zzdbVar2.zza) < zzdbVar.zza) {
                    hashMap.put(str, zzdbVar);
                }
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzj() {
        this.zzg.lock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzk(final String str, final int i, final long j) {
        zzr(new zzdd() { // from class: com.google.android.play.core.assetpacks.zzcv
            @Override // com.google.android.play.core.assetpacks.zzdd
            public final Object zza() {
                zzde.this.zzc(str, i, j);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzl() {
        this.zzg.unlock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzm(final int i, @AssetPackStatus int i2) {
        zzr(new zzdd(i, 5) { // from class: com.google.android.play.core.assetpacks.zzcs
            public final /* synthetic */ int zzb;

            @Override // com.google.android.play.core.assetpacks.zzdd
            public final Object zza() {
                zzde.this.zzd(this.zzb, 5);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzn(final int i) {
        zzr(new zzdd() { // from class: com.google.android.play.core.assetpacks.zzcr
            @Override // com.google.android.play.core.assetpacks.zzdd
            public final Object zza() {
                zzde.this.zze(i);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzo(final Bundle bundle) {
        return ((Boolean) zzr(new zzdd() { // from class: com.google.android.play.core.assetpacks.zzct
            @Override // com.google.android.play.core.assetpacks.zzdd
            public final Object zza() {
                return zzde.this.zza(bundle);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzp(final Bundle bundle) {
        return ((Boolean) zzr(new zzdd() { // from class: com.google.android.play.core.assetpacks.zzcu
            @Override // com.google.android.play.core.assetpacks.zzdd
            public final Object zza() {
                return zzde.this.zzb(bundle);
            }
        })).booleanValue();
    }
}
