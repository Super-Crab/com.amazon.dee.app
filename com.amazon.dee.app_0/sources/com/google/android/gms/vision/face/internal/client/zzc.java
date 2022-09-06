package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "FaceSettingsParcelCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class zzc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzc> CREATOR = new zzd();
    @SafeParcelable.Field(id = 2)
    public int mode;
    @SafeParcelable.Field(id = 3)
    public int zzby;
    @SafeParcelable.Field(id = 5)
    public boolean zzbz;
    @SafeParcelable.Field(id = 4)
    public int zzca;
    @SafeParcelable.Field(id = 6)
    public boolean zzcb;
    @SafeParcelable.Field(defaultValue = "-1", id = 7)
    public float zzcc;

    public zzc() {
    }

    @SafeParcelable.Constructor
    public zzc(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) int i3, @SafeParcelable.Param(id = 5) boolean z, @SafeParcelable.Param(id = 6) boolean z2, @SafeParcelable.Param(id = 7) float f) {
        this.mode = i;
        this.zzby = i2;
        this.zzca = i3;
        this.zzbz = z;
        this.zzcb = z2;
        this.zzcc = f;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.mode);
        SafeParcelWriter.writeInt(parcel, 3, this.zzby);
        SafeParcelWriter.writeInt(parcel, 4, this.zzca);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzbz);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzcb);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzcc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
