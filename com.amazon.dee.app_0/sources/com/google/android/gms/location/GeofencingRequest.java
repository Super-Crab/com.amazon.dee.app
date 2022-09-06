package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.location.zzbh;
import java.util.ArrayList;
import java.util.List;
@SafeParcelable.Class(creator = "GeofencingRequestCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes2.dex */
public class GeofencingRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GeofencingRequest> CREATOR = new zzq();
    public static final int INITIAL_TRIGGER_DWELL = 4;
    public static final int INITIAL_TRIGGER_ENTER = 1;
    public static final int INITIAL_TRIGGER_EXIT = 2;
    @SafeParcelable.Field(defaultValue = "", getter = "getTag", id = 3)
    private final String tag;
    @SafeParcelable.Field(getter = "getParcelableGeofences", id = 1)
    private final List<zzbh> zzap;
    @SafeParcelable.Field(getter = "getInitialTrigger", id = 2)
    private final int zzaq;

    /* loaded from: classes2.dex */
    public static final class Builder {
        private final List<zzbh> zzap = new ArrayList();
        private int zzaq = 5;
        private String tag = "";

        public final Builder addGeofence(Geofence geofence) {
            Preconditions.checkNotNull(geofence, "geofence can't be null.");
            Preconditions.checkArgument(geofence instanceof zzbh, "Geofence must be created using Geofence.Builder.");
            this.zzap.add((zzbh) geofence);
            return this;
        }

        public final Builder addGeofences(List<Geofence> list) {
            if (list != null && !list.isEmpty()) {
                for (Geofence geofence : list) {
                    if (geofence != null) {
                        addGeofence(geofence);
                    }
                }
            }
            return this;
        }

        public final GeofencingRequest build() {
            Preconditions.checkArgument(!this.zzap.isEmpty(), "No geofence has been added to this request.");
            return new GeofencingRequest(this.zzap, this.zzaq, this.tag);
        }

        public final Builder setInitialTrigger(int i) {
            this.zzaq = i & 7;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public GeofencingRequest(@SafeParcelable.Param(id = 1) List<zzbh> list, @SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) String str) {
        this.zzap = list;
        this.zzaq = i;
        this.tag = str;
    }

    public List<Geofence> getGeofences() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.zzap);
        return arrayList;
    }

    public int getInitialTrigger() {
        return this.zzaq;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("GeofencingRequest[", "geofences=");
        outline113.append(this.zzap);
        int i = this.zzaq;
        StringBuilder sb = new StringBuilder(30);
        sb.append(", initialTrigger=");
        sb.append(i);
        sb.append(", ");
        outline113.append(sb.toString());
        String valueOf = String.valueOf(this.tag);
        return GeneratedOutlineSupport1.outline91(outline113, valueOf.length() != 0 ? "tag=".concat(valueOf) : new String("tag="), "]");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zzap, false);
        SafeParcelWriter.writeInt(parcel, 2, getInitialTrigger());
        SafeParcelWriter.writeString(parcel, 3, this.tag, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
