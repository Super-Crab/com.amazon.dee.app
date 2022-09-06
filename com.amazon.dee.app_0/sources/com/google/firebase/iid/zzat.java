package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
public final class zzat {
    private final SharedPreferences zza;
    private final Context zzb;
    private final zzq zzc;
    @GuardedBy("this")
    private final Map<String, zzs> zzd;

    public zzat(Context context) {
        this(context, new zzq());
    }

    private final synchronized boolean zzc() {
        return this.zza.getAll().isEmpty();
    }

    public final synchronized String zza() {
        return this.zza.getString("topic_operation_queue", "");
    }

    public final synchronized void zzb() {
        this.zzd.clear();
        zzq.zza(this.zzb);
        this.zza.edit().clear().commit();
    }

    private zzat(Context context, zzq zzqVar) {
        this.zzd = new ArrayMap();
        this.zzb = context;
        this.zza = context.getSharedPreferences("com.google.android.gms.appid", 0);
        this.zzc = zzqVar;
        File file = new File(ContextCompat.getNoBackupFilesDir(this.zzb), "com.google.android.gms.appid-no-backup");
        if (!file.exists()) {
            try {
                if (!file.createNewFile() || zzc()) {
                    return;
                }
                Log.i("FirebaseInstanceId", "App restored, clearing state");
                zzb();
                FirebaseInstanceId.getInstance().zze();
            } catch (IOException e) {
                if (!Log.isLoggable("FirebaseInstanceId", 3)) {
                    return;
                }
                String valueOf = String.valueOf(e.getMessage());
                if (valueOf.length() != 0) {
                    "Error creating file in no backup dir: ".concat(valueOf);
                } else {
                    new String("Error creating file in no backup dir: ");
                }
            }
        }
    }

    private static String zzc(String str, String str2, String str3) {
        StringBuilder outline106 = GeneratedOutlineSupport1.outline106(GeneratedOutlineSupport1.outline6(str3, GeneratedOutlineSupport1.outline6(str2, GeneratedOutlineSupport1.outline6(str, 4))), str, "|T|", str2, AccessoryMetricsConstants.DELIMITER);
        outline106.append(str3);
        return outline106.toString();
    }

    public final synchronized void zza(String str) {
        this.zza.edit().putString("topic_operation_queue", str).apply();
    }

    public final synchronized void zzc(String str) {
        String concat = String.valueOf(str).concat("|T|");
        SharedPreferences.Editor edit = this.zza.edit();
        for (String str2 : this.zza.getAll().keySet()) {
            if (str2.startsWith(concat)) {
                edit.remove(str2);
            }
        }
        edit.commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(String str, String str2) {
        return GeneratedOutlineSupport1.outline30(GeneratedOutlineSupport1.outline6(str2, GeneratedOutlineSupport1.outline6(str, 3)), str, "|S|", str2);
    }

    public final synchronized zzas zza(String str, String str2, String str3) {
        return zzas.zza(this.zza.getString(zzc(str, str2, str3), null));
    }

    public final synchronized void zzb(String str, String str2, String str3) {
        String zzc = zzc(str, str2, str3);
        SharedPreferences.Editor edit = this.zza.edit();
        edit.remove(zzc);
        edit.commit();
    }

    public final synchronized void zza(String str, String str2, String str3, String str4, String str5) {
        String zza = zzas.zza(str4, str5, System.currentTimeMillis());
        if (zza == null) {
            return;
        }
        SharedPreferences.Editor edit = this.zza.edit();
        edit.putString(zzc(str, str2, str3), zza);
        edit.commit();
    }

    public final synchronized zzs zzb(String str) {
        zzs zzb;
        zzs zzsVar = this.zzd.get(str);
        if (zzsVar != null) {
            return zzsVar;
        }
        try {
            zzb = this.zzc.zza(this.zzb, str);
        } catch (zzt unused) {
            Log.w("FirebaseInstanceId", "Stored data is corrupt, generating new identity");
            FirebaseInstanceId.getInstance().zze();
            zzb = this.zzc.zzb(this.zzb, str);
        }
        this.zzd.put(str, zzb);
        return zzb;
    }
}
