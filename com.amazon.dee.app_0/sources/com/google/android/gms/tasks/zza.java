package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
final class zza extends CancellationToken {
    private final zzu<Void> zza = new zzu<>();

    public final void cancel() {
        this.zza.trySetResult(null);
    }

    @Override // com.google.android.gms.tasks.CancellationToken
    public final boolean isCancellationRequested() {
        return this.zza.isComplete();
    }

    @Override // com.google.android.gms.tasks.CancellationToken
    public final CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
        this.zza.addOnSuccessListener(new zzb(this, onTokenCanceledListener));
        return this;
    }
}
