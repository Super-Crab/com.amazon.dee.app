package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "LocationSettingsRequestCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes2.dex */
public final class LocationSettingsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LocationSettingsRequest> CREATOR = new zzag();
    @SafeParcelable.Field(getter = "getLocationRequests", id = 1)
    private final List<LocationRequest> zzbg;
    @SafeParcelable.Field(defaultValue = PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE, getter = "alwaysShow", id = 2)
    private final boolean zzbh;
    @SafeParcelable.Field(getter = "needBle", id = 3)
    private final boolean zzbi;
    @SafeParcelable.Field(getter = "getConfiguration", id = 5)
    private zzae zzbj;

    /* loaded from: classes2.dex */
    public static final class Builder {
        private final ArrayList<LocationRequest> zzbk = new ArrayList<>();
        private boolean zzbh = false;
        private boolean zzbi = false;
        private zzae zzbj = null;

        public final Builder addAllLocationRequests(Collection<LocationRequest> collection) {
            for (LocationRequest locationRequest : collection) {
                if (locationRequest != null) {
                    this.zzbk.add(locationRequest);
                }
            }
            return this;
        }

        public final Builder addLocationRequest(@NonNull LocationRequest locationRequest) {
            if (locationRequest != null) {
                this.zzbk.add(locationRequest);
            }
            return this;
        }

        public final LocationSettingsRequest build() {
            return new LocationSettingsRequest(this.zzbk, this.zzbh, this.zzbi, null);
        }

        public final Builder setAlwaysShow(boolean z) {
            this.zzbh = z;
            return this;
        }

        public final Builder setNeedBle(boolean z) {
            this.zzbi = z;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public LocationSettingsRequest(@SafeParcelable.Param(id = 1) List<LocationRequest> list, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) boolean z2, @SafeParcelable.Param(id = 5) zzae zzaeVar) {
        this.zzbg = list;
        this.zzbh = z;
        this.zzbi = z2;
        this.zzbj = zzaeVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, Collections.unmodifiableList(this.zzbg), false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzbh);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzbi);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzbj, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
