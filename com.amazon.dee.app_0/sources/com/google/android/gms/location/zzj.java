package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import kotlinx.serialization.json.internal.JsonReaderKt;
@SafeParcelable.Class(creator = "DeviceOrientationRequestCreator")
/* loaded from: classes2.dex */
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR = new zzk();
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_SHOULD_USE_MAG", id = 1)
    private boolean zzt;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_MINIMUM_SAMPLING_PERIOD_MS", id = 2)
    private long zzu;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_SMALLEST_ANGLE_CHANGE_RADIANS", id = 3)
    private float zzv;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_EXPIRE_AT_MS", id = 4)
    private long zzw;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_NUM_UPDATES", id = 5)
    private int zzx;

    public zzj() {
        this(true, 50L, 0.0f, Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public zzj(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) float f, @SafeParcelable.Param(id = 4) long j2, @SafeParcelable.Param(id = 5) int i) {
        this.zzt = z;
        this.zzu = j;
        this.zzv = f;
        this.zzw = j2;
        this.zzx = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzj)) {
            return false;
        }
        zzj zzjVar = (zzj) obj;
        return this.zzt == zzjVar.zzt && this.zzu == zzjVar.zzu && Float.compare(this.zzv, zzjVar.zzv) == 0 && this.zzw == zzjVar.zzw && this.zzx == zzjVar.zzx;
    }

    public final int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zzt), Long.valueOf(this.zzu), Float.valueOf(this.zzv), Long.valueOf(this.zzw), Integer.valueOf(this.zzx));
    }

    public final String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceOrientationRequest[mShouldUseMag=");
        outline107.append(this.zzt);
        outline107.append(" mMinimumSamplingPeriodMs=");
        outline107.append(this.zzu);
        outline107.append(" mSmallestAngleChangeRadians=");
        outline107.append(this.zzv);
        long j = this.zzw;
        if (j != Long.MAX_VALUE) {
            outline107.append(" expireIn=");
            outline107.append(j - SystemClock.elapsedRealtime());
            outline107.append("ms");
        }
        if (this.zzx != Integer.MAX_VALUE) {
            outline107.append(" num=");
            outline107.append(this.zzx);
        }
        outline107.append(JsonReaderKt.END_LIST);
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzt);
        SafeParcelWriter.writeLong(parcel, 2, this.zzu);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzv);
        SafeParcelWriter.writeLong(parcel, 4, this.zzw);
        SafeParcelWriter.writeInt(parcel, 5, this.zzx);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
