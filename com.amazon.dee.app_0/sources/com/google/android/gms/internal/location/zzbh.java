package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.Geofence;
import java.util.Locale;
@VisibleForTesting
@SafeParcelable.Class(creator = "ParcelableGeofenceCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes2.dex */
public final class zzbh extends AbstractSafeParcelable implements Geofence {
    public static final Parcelable.Creator<zzbh> CREATOR = new zzbi();
    @SafeParcelable.Field(getter = "getRequestId", id = 1)
    private final String zzad;
    @SafeParcelable.Field(getter = "getTransitionTypes", id = 7)
    private final int zzae;
    @SafeParcelable.Field(getter = "getType", id = 3)
    private final short zzag;
    @SafeParcelable.Field(getter = "getLatitude", id = 4)
    private final double zzah;
    @SafeParcelable.Field(getter = "getLongitude", id = 5)
    private final double zzai;
    @SafeParcelable.Field(getter = "getRadius", id = 6)
    private final float zzaj;
    @SafeParcelable.Field(defaultValue = "0", getter = "getNotificationResponsiveness", id = 8)
    private final int zzak;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getLoiteringDelay", id = 9)
    private final int zzal;
    @SafeParcelable.Field(getter = "getExpirationTime", id = 2)
    private final long zzdo;

    @SafeParcelable.Constructor
    public zzbh(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 7) int i, @SafeParcelable.Param(id = 3) short s, @SafeParcelable.Param(id = 4) double d, @SafeParcelable.Param(id = 5) double d2, @SafeParcelable.Param(id = 6) float f, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 8) int i2, @SafeParcelable.Param(id = 9) int i3) {
        if (str == null || str.length() > 100) {
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? "requestId is null or too long: ".concat(valueOf) : new String("requestId is null or too long: "));
        } else if (f <= 0.0f) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("invalid radius: ");
            sb.append(f);
            throw new IllegalArgumentException(sb.toString());
        } else if (d > 90.0d || d < -90.0d) {
            StringBuilder sb2 = new StringBuilder(42);
            sb2.append("invalid latitude: ");
            sb2.append(d);
            throw new IllegalArgumentException(sb2.toString());
        } else if (d2 > 180.0d || d2 < -180.0d) {
            StringBuilder sb3 = new StringBuilder(43);
            sb3.append("invalid longitude: ");
            sb3.append(d2);
            throw new IllegalArgumentException(sb3.toString());
        } else {
            int i4 = i & 7;
            if (i4 == 0) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline27(46, "No supported transition specified: ", i));
            }
            this.zzag = s;
            this.zzad = str;
            this.zzah = d;
            this.zzai = d2;
            this.zzaj = f;
            this.zzdo = j;
            this.zzae = i4;
            this.zzak = i2;
            this.zzal = i3;
        }
    }

    public static zzbh zza(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        zzbh createFromParcel = CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return createFromParcel;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzbh)) {
            return false;
        }
        zzbh zzbhVar = (zzbh) obj;
        return this.zzaj == zzbhVar.zzaj && this.zzah == zzbhVar.zzah && this.zzai == zzbhVar.zzai && this.zzag == zzbhVar.zzag;
    }

    @Override // com.google.android.gms.location.Geofence
    public final String getRequestId() {
        return this.zzad;
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.zzah);
        long doubleToLongBits2 = Double.doubleToLongBits(this.zzai);
        return ((((Float.floatToIntBits(this.zzaj) + ((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31) * 31) + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2))) * 31)) * 31) + this.zzag) * 31) + this.zzae;
    }

    public final String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[9];
        objArr[0] = this.zzag != 1 ? null : "CIRCLE";
        objArr[1] = this.zzad.replaceAll("\\p{C}", WebConstants.UriConstants.QUESTIONMARK_KEY);
        objArr[2] = Integer.valueOf(this.zzae);
        objArr[3] = Double.valueOf(this.zzah);
        objArr[4] = Double.valueOf(this.zzai);
        objArr[5] = Float.valueOf(this.zzaj);
        objArr[6] = Integer.valueOf(this.zzak / 1000);
        objArr[7] = Integer.valueOf(this.zzal);
        objArr[8] = Long.valueOf(this.zzdo);
        return String.format(locale, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", objArr);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getRequestId(), false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzdo);
        SafeParcelWriter.writeShort(parcel, 3, this.zzag);
        SafeParcelWriter.writeDouble(parcel, 4, this.zzah);
        SafeParcelWriter.writeDouble(parcel, 5, this.zzai);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzaj);
        SafeParcelWriter.writeInt(parcel, 7, this.zzae);
        SafeParcelWriter.writeInt(parcel, 8, this.zzak);
        SafeParcelWriter.writeInt(parcel, 9, this.zzal);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
