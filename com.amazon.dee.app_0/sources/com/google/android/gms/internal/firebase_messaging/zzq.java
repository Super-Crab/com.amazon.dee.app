package com.google.android.gms.internal.firebase_messaging;
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes2.dex */
final class zzq extends zzp {
    private final zzo zza = new zzo();

    @Override // com.google.android.gms.internal.firebase_messaging.zzp
    public final void zza(Throwable th, Throwable th2) {
        if (th2 != th) {
            if (th2 != null) {
                this.zza.zza(th, true).add(th2);
                return;
            }
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
        throw new IllegalArgumentException("Self suppression is not allowed.", th2);
    }
}
