package com.google.firebase.iid;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
public final class zzp implements Continuation<Bundle, String> {
    private final /* synthetic */ zzl zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzp(zzl zzlVar) {
        this.zza = zzlVar;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ String then(@NonNull Task<Bundle> task) throws Exception {
        String zza;
        zzl zzlVar = this.zza;
        zza = zzl.zza(task.getResult(IOException.class));
        return zza;
    }
}
