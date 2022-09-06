package amazon.speech.simclient.context;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public enum DeviceContextQueryType implements Parcelable {
    NAVIGATION_GO_BACK;
    
    public static final Parcelable.Creator<DeviceContextQueryType> CREATOR = new Parcelable.Creator<DeviceContextQueryType>() { // from class: amazon.speech.simclient.context.DeviceContextQueryType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public DeviceContextQueryType mo35createFromParcel(Parcel parcel) {
            DeviceContextQueryType valueOf = DeviceContextQueryType.valueOf(parcel.readString());
            valueOf.handleBundle(parcel.readBundle());
            return valueOf;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public DeviceContextQueryType[] mo36newArray(int i) {
            return new DeviceContextQueryType[i];
        }
    };
    private Bundle mBundle;

    DeviceContextQueryType(Bundle bundle) {
        this.mBundle = bundle;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void handleBundle(Bundle bundle) {
        this.mBundle = bundle;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
        parcel.writeBundle(this.mBundle);
    }

    DeviceContextQueryType() {
        this(new Bundle());
    }
}
