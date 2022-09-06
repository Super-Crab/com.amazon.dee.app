package com.google.firebase.iid;
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
final class zzu implements InstanceIdResult {
    private final String zza;
    private final String zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzu(String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public final String getId() {
        return this.zza;
    }

    @Override // com.google.firebase.iid.InstanceIdResult
    public final String getToken() {
        return this.zzb;
    }
}
