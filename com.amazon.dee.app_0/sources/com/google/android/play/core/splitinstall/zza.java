package com.google.android.play.core.splitinstall;

import android.app.PendingIntent;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus;
import java.util.List;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zza extends SplitInstallSessionState {
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final long zzd;
    private final long zze;
    private final List zzf;
    private final List zzg;
    private final PendingIntent zzh;
    private final List zzi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(int i, int i2, int i3, long j, long j2, @Nullable List list, @Nullable List list2, @Nullable PendingIntent pendingIntent, @Nullable List list3) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = j;
        this.zze = j2;
        this.zzf = list;
        this.zzg = list2;
        this.zzh = pendingIntent;
        this.zzi = list3;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    public final long bytesDownloaded() {
        return this.zzd;
    }

    public final boolean equals(Object obj) {
        List list;
        List list2;
        PendingIntent pendingIntent;
        List list3;
        if (obj == this) {
            return true;
        }
        if (obj instanceof SplitInstallSessionState) {
            SplitInstallSessionState splitInstallSessionState = (SplitInstallSessionState) obj;
            if (this.zza == splitInstallSessionState.sessionId() && this.zzb == splitInstallSessionState.status() && this.zzc == splitInstallSessionState.errorCode() && this.zzd == splitInstallSessionState.bytesDownloaded() && this.zze == splitInstallSessionState.totalBytesToDownload() && ((list = this.zzf) != null ? list.equals(splitInstallSessionState.zzb()) : splitInstallSessionState.zzb() == null) && ((list2 = this.zzg) != null ? list2.equals(splitInstallSessionState.zza()) : splitInstallSessionState.zza() == null) && ((pendingIntent = this.zzh) != null ? pendingIntent.equals(splitInstallSessionState.resolutionIntent()) : splitInstallSessionState.resolutionIntent() == null) && ((list3 = this.zzi) != null ? list3.equals(splitInstallSessionState.zzc()) : splitInstallSessionState.zzc() == null)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    @SplitInstallErrorCode
    public final int errorCode() {
        return this.zzc;
    }

    public final int hashCode() {
        int i = this.zza;
        int i2 = this.zzb;
        int i3 = this.zzc;
        long j = this.zzd;
        long j2 = this.zze;
        int i4 = (((((((((i ^ 1000003) * 1000003) ^ i2) * 1000003) ^ i3) * 1000003) ^ ((int) ((j >>> 32) ^ j))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003;
        List list = this.zzf;
        int i5 = 0;
        int hashCode = (i4 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        List list2 = this.zzg;
        int hashCode2 = (hashCode ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
        PendingIntent pendingIntent = this.zzh;
        int hashCode3 = (hashCode2 ^ (pendingIntent == null ? 0 : pendingIntent.hashCode())) * 1000003;
        List list3 = this.zzi;
        if (list3 != null) {
            i5 = list3.hashCode();
        }
        return hashCode3 ^ i5;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    @Nullable
    @Deprecated
    public final PendingIntent resolutionIntent() {
        return this.zzh;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    public final int sessionId() {
        return this.zza;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    @SplitInstallSessionStatus
    public final int status() {
        return this.zzb;
    }

    public final String toString() {
        int i = this.zza;
        int i2 = this.zzb;
        int i3 = this.zzc;
        long j = this.zzd;
        long j2 = this.zze;
        String valueOf = String.valueOf(this.zzf);
        String valueOf2 = String.valueOf(this.zzg);
        String valueOf3 = String.valueOf(this.zzh);
        String valueOf4 = String.valueOf(this.zzi);
        int length = valueOf.length();
        int length2 = valueOf2.length();
        StringBuilder sb = new StringBuilder(length + 251 + length2 + valueOf3.length() + valueOf4.length());
        sb.append("SplitInstallSessionState{sessionId=");
        sb.append(i);
        sb.append(", status=");
        sb.append(i2);
        sb.append(", errorCode=");
        sb.append(i3);
        sb.append(", bytesDownloaded=");
        sb.append(j);
        sb.append(", totalBytesToDownload=");
        sb.append(j2);
        sb.append(", moduleNamesNullable=");
        GeneratedOutlineSupport1.outline181(sb, valueOf, ", languagesNullable=", valueOf2, ", resolutionIntent=");
        return GeneratedOutlineSupport1.outline93(sb, valueOf3, ", splitFileIntents=", valueOf4, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    public final long totalBytesToDownload() {
        return this.zze;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    @Nullable
    final List zza() {
        return this.zzg;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    @Nullable
    final List zzb() {
        return this.zzf;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallSessionState
    @Nullable
    final List zzc() {
        return this.zzi;
    }
}
