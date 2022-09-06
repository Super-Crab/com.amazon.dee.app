package com.amazon.ptz.physical;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.ptzcontroller.lib.model.api.ptz.directive.CameraPtzInstance;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nonnull;
import lombok.Generated;
/* loaded from: classes13.dex */
public class ParcelableCameraPtzInstance implements Parcelable {
    public static final Parcelable.Creator<ParcelableCameraPtzInstance> CREATOR = new Parcelable.Creator<ParcelableCameraPtzInstance>() { // from class: com.amazon.ptz.physical.ParcelableCameraPtzInstance.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ParcelableCameraPtzInstance mo4481createFromParcel(Parcel parcel) {
            return new ParcelableCameraPtzInstance(CameraPtzInstance.values()[parcel.readInt()]);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ParcelableCameraPtzInstance[] mo4482newArray(int i) {
            return new ParcelableCameraPtzInstance[i];
        }
    };
    @Nonnull
    private final CameraPtzInstance ptzInstance;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public ParcelableCameraPtzInstance(@Nonnull CameraPtzInstance cameraPtzInstance) {
        if (cameraPtzInstance != null) {
            this.ptzInstance = cameraPtzInstance;
            return;
        }
        throw new IllegalArgumentException("ptzInstance is null");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ParcelableCameraPtzInstance) {
            return this.ptzInstance.equals(((ParcelableCameraPtzInstance) obj).getPtzInstance());
        }
        return false;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Nonnull
    @Generated
    public CameraPtzInstance getPtzInstance() {
        return this.ptzInstance;
    }

    public int hashCode() {
        return this.ptzInstance.ordinal();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ParcelableCameraPtzInstance(ptzInstance=");
        outline107.append(getPtzInstance());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.ptzInstance.ordinal());
    }
}
