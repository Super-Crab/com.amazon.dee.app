package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes6.dex */
public enum AlexaPlayerInfoState implements Parcelable {
    PLAYING,
    PAUSED,
    DONE,
    BUFFERING,
    ERROR,
    CANCELLED,
    IDLE;
    
    public static final Parcelable.Creator<AlexaPlayerInfoState> CREATOR = new Parcelable.Creator<AlexaPlayerInfoState>() { // from class: com.amazon.alexa.api.AlexaPlayerInfoState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaPlayerInfoState mo805createFromParcel(Parcel parcel) {
            return (AlexaPlayerInfoState) parcel.readSerializable();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaPlayerInfoState[] mo806newArray(int i) {
            return new AlexaPlayerInfoState[i];
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
