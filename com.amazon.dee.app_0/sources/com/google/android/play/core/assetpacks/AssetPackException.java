package com.google.android.play.core.assetpacks;

import com.google.android.play.core.assetpacks.model.AssetPackErrorCode;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public class AssetPackException extends com.google.android.play.core.tasks.zzj {
    @AssetPackErrorCode
    private final int zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AssetPackException(@AssetPackErrorCode int i) {
        super(String.format("Asset Pack Download Error(%d): %s", Integer.valueOf(i), com.google.android.play.core.assetpacks.model.zza.zza(i)));
        if (i != 0) {
            this.zza = i;
            return;
        }
        throw new IllegalArgumentException("errorCode should not be 0.");
    }

    @Override // com.google.android.play.core.tasks.zzj
    @AssetPackErrorCode
    public int getErrorCode() {
        return this.zza;
    }
}
