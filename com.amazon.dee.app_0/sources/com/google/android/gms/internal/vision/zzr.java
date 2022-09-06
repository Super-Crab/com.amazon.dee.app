package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "BoundingBoxParcelCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    @SafeParcelable.Field(id = 5)
    public final int height;
    @SafeParcelable.Field(id = 2)
    public final int left;
    @SafeParcelable.Field(id = 3)
    public final int top;
    @SafeParcelable.Field(id = 4)
    public final int width;
    @SafeParcelable.Field(id = 6)
    public final float zzdh;

    @SafeParcelable.Constructor
    public zzr(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) int i3, @SafeParcelable.Param(id = 5) int i4, @SafeParcelable.Param(id = 6) float f) {
        this.left = i;
        this.top = i2;
        this.width = i3;
        this.height = i4;
        this.zzdh = f;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.left);
        SafeParcelWriter.writeInt(parcel, 3, this.top);
        SafeParcelWriter.writeInt(parcel, 4, this.width);
        SafeParcelWriter.writeInt(parcel, 5, this.height);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzdh);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
