package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes2.dex */
final class zaao extends zabf {
    private final /* synthetic */ ConnectionResult zagm;
    private final /* synthetic */ zaan zagn;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaao(zaan zaanVar, zabd zabdVar, ConnectionResult connectionResult) {
        super(zabdVar);
        this.zagn = zaanVar;
        this.zagm = connectionResult;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    @GuardedBy("mLock")
    public final void zaan() {
        this.zagn.zagj.zae(this.zagm);
    }
}
