package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes6.dex */
public enum AlexaAudioTaskChannelPriority implements Parcelable {
    LOW(0),
    MEDIUM(1),
    HIGH(2);
    
    public static final Parcelable.Creator<AlexaAudioTaskChannelPriority> CREATOR = new Parcelable.Creator<AlexaAudioTaskChannelPriority>() { // from class: com.amazon.alexa.api.AlexaAudioTaskChannelPriority.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaAudioTaskChannelPriority mo771createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            return readInt != 1 ? readInt != 2 ? AlexaAudioTaskChannelPriority.LOW : AlexaAudioTaskChannelPriority.HIGH : AlexaAudioTaskChannelPriority.MEDIUM;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaAudioTaskChannelPriority[] mo772newArray(int i) {
            return new AlexaAudioTaskChannelPriority[i];
        }
    };
    private final int priority;

    AlexaAudioTaskChannelPriority(int i) {
        this.priority = i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.priority);
    }
}
