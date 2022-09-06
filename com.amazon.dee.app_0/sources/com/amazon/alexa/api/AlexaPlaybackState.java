package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes6.dex */
public enum AlexaPlaybackState implements Parcelable {
    STOPPABLE,
    STOPPABLE_AND_NAVIGABLE,
    NONE;
    
    public static final Parcelable.Creator<AlexaPlaybackState> CREATOR = new Parcelable.Creator<AlexaPlaybackState>() { // from class: com.amazon.alexa.api.AlexaPlaybackState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaPlaybackState mo803createFromParcel(Parcel parcel) {
            return (AlexaPlaybackState) parcel.readSerializable();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaPlaybackState[] mo804newArray(int i) {
            return new AlexaPlaybackState[i];
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
