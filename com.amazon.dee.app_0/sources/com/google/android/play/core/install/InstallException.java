package com.google.android.play.core.install;

import com.google.android.play.core.install.model.InstallErrorCode;
import com.google.android.play.core.tasks.zzj;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public class InstallException extends zzj {
    @InstallErrorCode
    private final int zza;

    public InstallException(@InstallErrorCode int i) {
        super(String.format("Install Error(%d): %s", Integer.valueOf(i), com.google.android.play.core.install.model.zza.zza(i)));
        if (i != 0) {
            this.zza = i;
            return;
        }
        throw new IllegalArgumentException("errorCode should not be 0.");
    }

    @Override // com.google.android.play.core.tasks.zzj
    @InstallErrorCode
    public int getErrorCode() {
        return this.zza;
    }
}
