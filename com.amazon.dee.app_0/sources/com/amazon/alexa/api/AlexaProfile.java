package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes6.dex */
public enum AlexaProfile implements Parcelable {
    CLOSE_TALK,
    NEAR_FIELD,
    FAR_FIELD;
    
    public static final Parcelable.Creator<AlexaProfile> CREATOR = new Parcelable.Creator<AlexaProfile>() { // from class: com.amazon.alexa.api.AlexaProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaProfile mo807createFromParcel(Parcel parcel) {
            return (AlexaProfile) parcel.readSerializable();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaProfile[] mo808newArray(int i) {
            return new AlexaProfile[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this);
    }
}
