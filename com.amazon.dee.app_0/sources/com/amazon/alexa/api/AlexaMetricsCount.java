package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes6.dex */
public class AlexaMetricsCount implements Parcelable {
    public static final Parcelable.Creator<AlexaMetricsCount> CREATOR = new Parcelable.Creator<AlexaMetricsCount>() { // from class: com.amazon.alexa.api.AlexaMetricsCount.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaMetricsCount mo796createFromParcel(Parcel parcel) {
            return new AlexaMetricsCount(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaMetricsCount[] mo797newArray(int i) {
            return new AlexaMetricsCount[i];
        }
    };
    private final int counterValue;

    public AlexaMetricsCount(int i) {
        this.counterValue = i;
    }

    protected AlexaMetricsCount(Parcel parcel) {
        this.counterValue = parcel.readInt();
    }

    public static AlexaMetricsCount create(int i) {
        return new AlexaMetricsCount(i);
    }

    public static AlexaMetricsCount createOne() {
        return create(1);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return hashCode();
    }

    public int getCounterValue() {
        return this.counterValue;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.counterValue);
    }
}
