package com.google.android.play.core.assetpacks;

import org.bouncycastle.asn1.cmc.BodyPartID;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzbr {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzc(byte[] bArr, int i) {
        return ((zza(bArr, i + 2) << 16) | zza(bArr, i)) & BodyPartID.bodyIdMax;
    }
}
