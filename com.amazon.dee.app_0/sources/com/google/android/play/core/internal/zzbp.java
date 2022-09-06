package com.google.android.play.core.internal;

import com.amazon.alexa.routing.api.RouteParameter;
import java.io.File;
import java.util.Set;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzbp implements zzaz {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzc(ClassLoader classLoader, Set set) {
        zzbk.zzc(classLoader, set, new zzbn());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzd(ClassLoader classLoader, File file, File file2, boolean z) {
        return zzbf.zze(classLoader, file, file2, z, new zzbh(), RouteParameter.PATH, new zzbo());
    }

    @Override // com.google.android.play.core.internal.zzaz
    public final void zza(ClassLoader classLoader, Set set) {
        zzc(classLoader, set);
    }

    @Override // com.google.android.play.core.internal.zzaz
    public final boolean zzb(ClassLoader classLoader, File file, File file2, boolean z) {
        return zzd(classLoader, file, file2, z);
    }
}
