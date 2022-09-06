package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.vision.Frame;
@SafeParcelable.Class(creator = "FrameMetadataParcelCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class zzm extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzm> CREATOR = new zzn();
    @SafeParcelable.Field(id = 3)
    public int height;
    @SafeParcelable.Field(id = 4)
    private int id;
    @SafeParcelable.Field(id = 6)
    public int rotation;
    @SafeParcelable.Field(id = 2)
    public int width;
    @SafeParcelable.Field(id = 5)
    private long zzat;

    public zzm() {
    }

    @SafeParcelable.Constructor
    public zzm(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) int i3, @SafeParcelable.Param(id = 5) long j, @SafeParcelable.Param(id = 6) int i4) {
        this.width = i;
        this.height = i2;
        this.id = i3;
        this.zzat = j;
        this.rotation = i4;
    }

    public static zzm zzc(Frame frame) {
        zzm zzmVar = new zzm();
        zzmVar.width = frame.getMetadata().getWidth();
        zzmVar.height = frame.getMetadata().getHeight();
        zzmVar.rotation = frame.getMetadata().getRotation();
        zzmVar.id = frame.getMetadata().getId();
        zzmVar.zzat = frame.getMetadata().getTimestampMillis();
        return zzmVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.width);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 4, this.id);
        SafeParcelWriter.writeLong(parcel, 5, this.zzat);
        SafeParcelWriter.writeInt(parcel, 6, this.rotation);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
