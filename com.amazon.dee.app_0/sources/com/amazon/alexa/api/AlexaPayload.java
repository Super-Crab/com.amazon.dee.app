package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
/* loaded from: classes6.dex */
public class AlexaPayload implements Parcelable {
    public static final Parcelable.Creator<AlexaPayload> CREATOR = new Parcelable.Creator<AlexaPayload>() { // from class: com.amazon.alexa.api.AlexaPayload.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaPayload mo801createFromParcel(Parcel parcel) {
            return new AlexaPayload(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaPayload[] mo802newArray(int i) {
            return new AlexaPayload[i];
        }
    };
    private final String payload;

    protected AlexaPayload(Parcel parcel) {
        this.payload = parcel.readString();
    }

    public AlexaPayload(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.payload = str;
            return;
        }
        throw new IllegalArgumentException(String.format("Payload (value=%s) is null or is empty.", str));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPayload() {
        return this.payload;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.payload);
    }
}
