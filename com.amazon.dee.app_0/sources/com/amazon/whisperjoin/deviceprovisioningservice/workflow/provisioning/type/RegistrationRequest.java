package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class RegistrationRequest implements Parcelable {
    public static final Parcelable.Creator<RegistrationRequest> CREATOR = new Parcelable.Creator<RegistrationRequest>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.RegistrationRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RegistrationRequest mo5698createFromParcel(Parcel parcel) {
            return new RegistrationRequest(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RegistrationRequest[] mo5699newArray(int i) {
            return new RegistrationRequest[i];
        }
    };
    private final Scheme mScheme;

    /* loaded from: classes13.dex */
    public enum Scheme {
        CODE_BASED_LINKING
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && RegistrationRequest.class == obj.getClass()) {
            return new EqualsBuilder().append(this.mScheme, ((RegistrationRequest) obj).mScheme).isEquals();
        }
        return false;
    }

    public Scheme getScheme() {
        return this.mScheme;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.mScheme).toHashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RegistrationRequest{mScheme=");
        outline107.append(this.mScheme);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mScheme.name());
    }

    public RegistrationRequest(Scheme scheme) {
        if (scheme != null) {
            this.mScheme = scheme;
            return;
        }
        throw new IllegalArgumentException("Scheme can't be null");
    }

    private RegistrationRequest(Parcel parcel) {
        this.mScheme = Scheme.valueOf(parcel.readString());
    }
}
