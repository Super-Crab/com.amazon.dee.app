package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
@SafeParcelable.Class(creator = "ActivityTransitionRequestCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes2.dex */
public class ActivityTransitionRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ActivityTransitionRequest> CREATOR = new zzf();
    public static final Comparator<ActivityTransition> IS_SAME_TRANSITION = new zze();
    @Nullable
    @SafeParcelable.Field(getter = "getTag", id = 2)
    private final String tag;
    @SafeParcelable.Field(getter = "getActivityTransitions", id = 1)
    private final List<ActivityTransition> zzl;
    @SafeParcelable.Field(getter = "getClients", id = 3)
    private final List<ClientIdentity> zzm;

    public ActivityTransitionRequest(List<ActivityTransition> list) {
        this(list, null, null);
    }

    @SafeParcelable.Constructor
    public ActivityTransitionRequest(@SafeParcelable.Param(id = 1) List<ActivityTransition> list, @Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) List<ClientIdentity> list2) {
        Preconditions.checkNotNull(list, "transitions can't be null");
        Preconditions.checkArgument(list.size() > 0, "transitions can't be empty.");
        TreeSet treeSet = new TreeSet(IS_SAME_TRANSITION);
        for (ActivityTransition activityTransition : list) {
            Preconditions.checkArgument(treeSet.add(activityTransition), String.format("Found duplicated transition: %s.", activityTransition));
        }
        this.zzl = Collections.unmodifiableList(list);
        this.tag = str;
        this.zzm = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && ActivityTransitionRequest.class == obj.getClass()) {
            ActivityTransitionRequest activityTransitionRequest = (ActivityTransitionRequest) obj;
            if (Objects.equal(this.zzl, activityTransitionRequest.zzl) && Objects.equal(this.tag, activityTransitionRequest.tag) && Objects.equal(this.zzm, activityTransitionRequest.zzm)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.zzl.hashCode() * 31;
        String str = this.tag;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        List<ClientIdentity> list = this.zzm;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode2 + i;
    }

    public void serializeToIntentExtra(Intent intent) {
        SafeParcelableSerializer.serializeToIntentExtra(this, intent, "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_REQUEST");
    }

    public String toString() {
        String valueOf = String.valueOf(this.zzl);
        String str = this.tag;
        String valueOf2 = String.valueOf(this.zzm);
        StringBuilder outline106 = GeneratedOutlineSupport1.outline106(valueOf2.length() + GeneratedOutlineSupport1.outline6(str, valueOf.length() + 61), "ActivityTransitionRequest [mTransitions=", valueOf, ", mTag='", str);
        outline106.append(Chars.QUOTE);
        outline106.append(", mClients=");
        outline106.append(valueOf2);
        outline106.append(JsonReaderKt.END_LIST);
        return outline106.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zzl, false);
        SafeParcelWriter.writeString(parcel, 2, this.tag, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
