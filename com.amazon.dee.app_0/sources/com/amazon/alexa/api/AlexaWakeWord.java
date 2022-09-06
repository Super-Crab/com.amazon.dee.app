package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes6.dex */
public class AlexaWakeWord implements Parcelable {
    public static final Parcelable.Creator<AlexaWakeWord> CREATOR = new Parcelable.Creator<AlexaWakeWord>() { // from class: com.amazon.alexa.api.AlexaWakeWord.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaWakeWord mo824createFromParcel(Parcel parcel) {
            return new AlexaWakeWord(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaWakeWord[] mo825newArray(int i) {
            return new AlexaWakeWord[i];
        }
    };
    private final long endIndexInSamples;
    private final String name;
    private final long startIndexInSamples;

    protected AlexaWakeWord(Parcel parcel) {
        this(parcel.readString(), parcel.readLong(), parcel.readLong());
    }

    public AlexaWakeWord(String str, long j, long j2) {
        this.name = str;
        this.startIndexInSamples = j;
        this.endIndexInSamples = j2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getEndIndexInSamples() {
        return this.endIndexInSamples;
    }

    public long getStartIndexInSamples() {
        return this.startIndexInSamples;
    }

    public String getWakeWordName() {
        return this.name;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeLong(this.startIndexInSamples);
        parcel.writeLong(this.endIndexInSamples);
    }
}
