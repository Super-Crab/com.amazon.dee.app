package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "FaceParcelCreator")
/* loaded from: classes2.dex */
public class FaceParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<FaceParcel> CREATOR = new zzb();
    @SafeParcelable.Field(id = 3)
    public final float centerX;
    @SafeParcelable.Field(id = 4)
    public final float centerY;
    @SafeParcelable.Field(id = 6)
    public final float height;
    @SafeParcelable.Field(id = 2)
    public final int id;
    @SafeParcelable.VersionField(id = 1)
    private final int versionCode;
    @SafeParcelable.Field(id = 5)
    public final float width;
    @SafeParcelable.Field(id = 10)
    public final float zzbs;
    @SafeParcelable.Field(id = 11)
    public final float zzbt;
    @SafeParcelable.Field(id = 12)
    public final float zzbu;
    @SafeParcelable.Field(id = 7)
    public final float zzcf;
    @SafeParcelable.Field(id = 8)
    public final float zzcg;
    @SafeParcelable.Field(id = 9)
    public final LandmarkParcel[] zzch;

    @SafeParcelable.Constructor
    public FaceParcel(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) float f, @SafeParcelable.Param(id = 4) float f2, @SafeParcelable.Param(id = 5) float f3, @SafeParcelable.Param(id = 6) float f4, @SafeParcelable.Param(id = 7) float f5, @SafeParcelable.Param(id = 8) float f6, @SafeParcelable.Param(id = 9) LandmarkParcel[] landmarkParcelArr, @SafeParcelable.Param(id = 10) float f7, @SafeParcelable.Param(id = 11) float f8, @SafeParcelable.Param(id = 12) float f9) {
        this.versionCode = i;
        this.id = i2;
        this.centerX = f;
        this.centerY = f2;
        this.width = f3;
        this.height = f4;
        this.zzcf = f5;
        this.zzcg = f6;
        this.zzch = landmarkParcelArr;
        this.zzbs = f7;
        this.zzbt = f8;
        this.zzbu = f9;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel, 2, this.id);
        SafeParcelWriter.writeFloat(parcel, 3, this.centerX);
        SafeParcelWriter.writeFloat(parcel, 4, this.centerY);
        SafeParcelWriter.writeFloat(parcel, 5, this.width);
        SafeParcelWriter.writeFloat(parcel, 6, this.height);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzcf);
        SafeParcelWriter.writeFloat(parcel, 8, this.zzcg);
        SafeParcelWriter.writeTypedArray(parcel, 9, this.zzch, i, false);
        SafeParcelWriter.writeFloat(parcel, 10, this.zzbs);
        SafeParcelWriter.writeFloat(parcel, 11, this.zzbt);
        SafeParcelWriter.writeFloat(parcel, 12, this.zzbu);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
