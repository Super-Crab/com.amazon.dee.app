package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "DeviceOrientationRequestInternalCreator")
/* loaded from: classes2.dex */
public final class zzm extends AbstractSafeParcelable {
    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = "null", id = 3)
    private String tag;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequestInternal.DEFAULT_DEVICE_ORIENTATION_REQUEST", id = 1)
    private com.google.android.gms.location.zzj zzcf;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequestInternal.DEFAULT_CLIENTS", id = 2)
    private List<ClientIdentity> zzm;
    @VisibleForTesting
    static final List<ClientIdentity> zzcd = Collections.emptyList();
    static final com.google.android.gms.location.zzj zzce = new com.google.android.gms.location.zzj();
    public static final Parcelable.Creator<zzm> CREATOR = new zzn();

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public zzm(@SafeParcelable.Param(id = 1) com.google.android.gms.location.zzj zzjVar, @SafeParcelable.Param(id = 2) List<ClientIdentity> list, @SafeParcelable.Param(id = 3) String str) {
        this.zzcf = zzjVar;
        this.zzm = list;
        this.tag = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzm)) {
            return false;
        }
        zzm zzmVar = (zzm) obj;
        return Objects.equal(this.zzcf, zzmVar.zzcf) && Objects.equal(this.zzm, zzmVar.zzm) && Objects.equal(this.tag, zzmVar.tag);
    }

    public final int hashCode() {
        return this.zzcf.hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzcf, i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzm, false);
        SafeParcelWriter.writeString(parcel, 3, this.tag, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
