package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
@SafeParcelable.Class(creator = "LocationResultCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes2.dex */
public final class LocationResult extends AbstractSafeParcelable implements ReflectedParcelable {
    @SafeParcelable.Field(defaultValueUnchecked = "LocationResult.DEFAULT_LOCATIONS", getter = "getLocations", id = 1)
    private final List<Location> zzbc;
    static final List<Location> zzbb = Collections.emptyList();
    public static final Parcelable.Creator<LocationResult> CREATOR = new zzac();

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public LocationResult(@SafeParcelable.Param(id = 1) List<Location> list) {
        this.zzbc = list;
    }

    public static LocationResult create(List<Location> list) {
        if (list == null) {
            list = zzbb;
        }
        return new LocationResult(list);
    }

    public static LocationResult extractResult(Intent intent) {
        if (!hasResult(intent)) {
            return null;
        }
        return (LocationResult) intent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
    }

    public static boolean hasResult(Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof LocationResult)) {
            return false;
        }
        LocationResult locationResult = (LocationResult) obj;
        if (locationResult.zzbc.size() != this.zzbc.size()) {
            return false;
        }
        Iterator<Location> it2 = locationResult.zzbc.iterator();
        Iterator<Location> it3 = this.zzbc.iterator();
        while (it2.hasNext()) {
            if (it3.next().getTime() != it2.next().getTime()) {
                return false;
            }
        }
        return true;
    }

    public final Location getLastLocation() {
        int size = this.zzbc.size();
        if (size == 0) {
            return null;
        }
        return this.zzbc.get(size - 1);
    }

    @NonNull
    public final List<Location> getLocations() {
        return this.zzbc;
    }

    public final int hashCode() {
        int i = 17;
        for (Location location : this.zzbc) {
            long time = location.getTime();
            i = (i * 31) + ((int) (time ^ (time >>> 32)));
        }
        return i;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzbc);
        return GeneratedOutlineSupport1.outline30(valueOf.length() + 27, "LocationResult[locations: ", valueOf, "]");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getLocations(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
