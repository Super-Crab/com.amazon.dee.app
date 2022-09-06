package amazon.speech.model;

import amazon.speech.model.DeviceContext;
import android.os.Parcel;
/* loaded from: classes.dex */
public final class PayloadDeviceContext extends DeviceContext {
    private String mPayload;

    public PayloadDeviceContext(String str, String str2, String str3) {
        super(str, str2);
        this.mPayload = str3;
    }

    @Override // amazon.speech.model.DeviceContext
    public String getPayload() {
        return this.mPayload;
    }

    @Override // amazon.speech.model.DeviceContext
    public DeviceContext.Type getType() {
        return DeviceContext.Type.Payload;
    }

    @Override // amazon.speech.model.DeviceContext
    public int hashCode() {
        int hashCode = super.hashCode();
        String str = this.mPayload;
        return str != null ? (hashCode * 33) + str.hashCode() : hashCode;
    }

    @Override // amazon.speech.model.DeviceContext, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getType().toString());
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mPayload);
    }

    public PayloadDeviceContext(String str, String str2, String str3, boolean z) {
        super(str, str2, z);
        this.mPayload = str3;
    }

    public PayloadDeviceContext(Parcel parcel) {
        super(parcel);
        this.mPayload = parcel.readString();
    }
}
