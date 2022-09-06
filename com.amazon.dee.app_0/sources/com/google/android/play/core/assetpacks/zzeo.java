package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FilenameFilter;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final /* synthetic */ class zzeo implements FilenameFilter {
    public static final /* synthetic */ zzeo zza = new zzeo();

    private /* synthetic */ zzeo() {
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        boolean matches;
        matches = zzep.zza.matcher(str).matches();
        return matches;
    }
}
