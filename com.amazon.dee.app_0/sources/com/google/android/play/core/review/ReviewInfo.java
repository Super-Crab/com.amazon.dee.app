package com.google.android.play.core.review;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
/* compiled from: com.google.android.play:core@@1.10.3 */
@SuppressLint({"RestrictedApi"})
/* loaded from: classes2.dex */
public abstract class ReviewInfo implements Parcelable {
    public static final Parcelable.Creator<ReviewInfo> CREATOR = new zzb();

    public static ReviewInfo zzc(PendingIntent pendingIntent, boolean z) {
        return new zza(pendingIntent, false);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(zza(), 0);
        parcel.writeInt(zzb() ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract PendingIntent zza();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zzb();
}
