package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzbe {
    private final Context zza;

    public zzbe(@NonNull Context context) {
        this.zza = context;
    }

    private final SharedPreferences zze() {
        return this.zza.getSharedPreferences("playcore_split_install_internal", 0);
    }

    public final Set zza() {
        Set<String> hashSet;
        synchronized (zzbe.class) {
            try {
                hashSet = zze().getStringSet("modules_to_uninstall_if_emulated", new HashSet());
                if (hashSet == null) {
                    hashSet = new HashSet<>();
                }
            } catch (Exception unused) {
                hashSet = new HashSet<>();
            }
        }
        return hashSet;
    }

    public final void zzb() {
        synchronized (zzbe.class) {
            zze().edit().putStringSet("modules_to_uninstall_if_emulated", new HashSet()).apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(Collection collection) {
        synchronized (zzbe.class) {
            HashSet hashSet = new HashSet(zza());
            Iterator it2 = collection.iterator();
            boolean z = false;
            while (it2.hasNext()) {
                z |= hashSet.add((String) it2.next());
            }
            if (z) {
                try {
                    zze().edit().putStringSet("modules_to_uninstall_if_emulated", hashSet).apply();
                } catch (Exception unused) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzd(Collection collection) {
        synchronized (zzbe.class) {
            Set<String> zza = zza();
            HashSet hashSet = new HashSet();
            boolean z = false;
            for (String str : zza) {
                if (collection.contains(str)) {
                    z = true;
                } else {
                    hashSet.add(str);
                }
            }
            if (z) {
                try {
                    zze().edit().putStringSet("modules_to_uninstall_if_emulated", hashSet).apply();
                } catch (Exception unused) {
                }
            }
        }
    }
}
