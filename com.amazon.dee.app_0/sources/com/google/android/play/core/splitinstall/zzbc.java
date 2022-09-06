package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.play.core.internal.zzce;
import com.google.android.play.core.internal.zzch;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzbc {
    private static final com.google.android.play.core.internal.zzag zzb = new com.google.android.play.core.internal.zzag("SplitInstallService");
    private static final Intent zzc = new Intent("com.google.android.play.core.splitinstall.BIND_SPLIT_INSTALL_SERVICE").setPackage("com.android.vending");
    @Nullable
    @VisibleForTesting
    com.google.android.play.core.internal.zzas zza;
    private final String zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbc(Context context, String str) {
        this.zzd = str;
        if (zzch.zzb(context)) {
            this.zza = new com.google.android.play.core.internal.zzas(zzce.zza(context), zzb, "SplitInstallService", zzc, zzak.zza, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ Bundle zza() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore_version_code", 11003);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ ArrayList zzl(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it2 = collection.iterator();
        while (it2.hasNext()) {
            Bundle bundle = new Bundle();
            bundle.putString("language", (String) it2.next());
            arrayList.add(bundle);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ ArrayList zzm(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it2 = collection.iterator();
        while (it2.hasNext()) {
            Bundle bundle = new Bundle();
            bundle.putString("module_name", (String) it2.next());
            arrayList.add(bundle);
        }
        return arrayList;
    }

    private static Task zzn() {
        zzb.zzb("onError(%d)", -14);
        return Tasks.zza(new SplitInstallException(-14));
    }

    public final Task zzc(int i) {
        if (this.zza == null) {
            return zzn();
        }
        com.google.android.play.core.tasks.zzi outline14 = GeneratedOutlineSupport1.outline14(zzb, "cancelInstall(%d)", new Object[]{Integer.valueOf(i)});
        this.zza.zzq(new zzas(this, outline14, i, outline14), outline14);
        return outline14.zza();
    }

    public final Task zzd(List list) {
        if (this.zza == null) {
            return zzn();
        }
        com.google.android.play.core.tasks.zzi outline14 = GeneratedOutlineSupport1.outline14(zzb, "deferredInstall(%s)", new Object[]{list});
        this.zza.zzq(new zzan(this, outline14, list, outline14), outline14);
        return outline14.zza();
    }

    public final Task zze(List list) {
        if (this.zza == null) {
            return zzn();
        }
        com.google.android.play.core.tasks.zzi outline14 = GeneratedOutlineSupport1.outline14(zzb, "deferredLanguageInstall(%s)", new Object[]{list});
        this.zza.zzq(new zzao(this, outline14, list, outline14), outline14);
        return outline14.zza();
    }

    public final Task zzf(List list) {
        if (this.zza == null) {
            return zzn();
        }
        com.google.android.play.core.tasks.zzi outline14 = GeneratedOutlineSupport1.outline14(zzb, "deferredLanguageUninstall(%s)", new Object[]{list});
        this.zza.zzq(new zzap(this, outline14, list, outline14), outline14);
        return outline14.zza();
    }

    public final Task zzg(List list) {
        if (this.zza == null) {
            return zzn();
        }
        com.google.android.play.core.tasks.zzi outline14 = GeneratedOutlineSupport1.outline14(zzb, "deferredUninstall(%s)", new Object[]{list});
        this.zza.zzq(new zzam(this, outline14, list, outline14), outline14);
        return outline14.zza();
    }

    public final Task zzh(int i) {
        if (this.zza == null) {
            return zzn();
        }
        com.google.android.play.core.tasks.zzi outline14 = GeneratedOutlineSupport1.outline14(zzb, "getSessionState(%d)", new Object[]{Integer.valueOf(i)});
        this.zza.zzq(new zzaq(this, outline14, i, outline14), outline14);
        return outline14.zza();
    }

    public final Task zzi() {
        if (this.zza == null) {
            return zzn();
        }
        com.google.android.play.core.tasks.zzi outline14 = GeneratedOutlineSupport1.outline14(zzb, "getSessionStates", new Object[0]);
        this.zza.zzq(new zzar(this, outline14, outline14), outline14);
        return outline14.zza();
    }

    public final Task zzj(Collection collection, Collection collection2) {
        if (this.zza == null) {
            return zzn();
        }
        com.google.android.play.core.tasks.zzi outline14 = GeneratedOutlineSupport1.outline14(zzb, "startInstall(%s,%s)", new Object[]{collection, collection2});
        this.zza.zzq(new zzal(this, outline14, collection, collection2, outline14), outline14);
        return outline14.zza();
    }
}
