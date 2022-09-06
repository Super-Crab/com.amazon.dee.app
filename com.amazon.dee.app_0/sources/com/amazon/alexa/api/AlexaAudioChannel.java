package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes6.dex */
public enum AlexaAudioChannel implements Parcelable {
    IMPORTANT,
    DIALOG,
    COMMUNICATIONS,
    ALERTS,
    CONTENT;
    
    public static final Parcelable.Creator<AlexaAudioChannel> CREATOR = new Parcelable.Creator<AlexaAudioChannel>() { // from class: com.amazon.alexa.api.AlexaAudioChannel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaAudioChannel mo762createFromParcel(Parcel parcel) {
            return (AlexaAudioChannel) parcel.readSerializable();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaAudioChannel[] mo763newArray(int i) {
            return new AlexaAudioChannel[i];
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
