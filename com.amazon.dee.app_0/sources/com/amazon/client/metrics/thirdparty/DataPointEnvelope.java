package com.amazon.client.metrics.thirdparty;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class DataPointEnvelope implements Parcelable {
    public static final Parcelable.Creator<DataPointEnvelope> CREATOR = new Parcelable.Creator<DataPointEnvelope>() { // from class: com.amazon.client.metrics.thirdparty.DataPointEnvelope.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public DataPointEnvelope mo2887createFromParcel(Parcel parcel) {
            return new DataPointEnvelope(new DataPoint(parcel.readString(), parcel.readString(), parcel.readInt(), DataPointType.fromInt(parcel.readInt())));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public DataPointEnvelope[] mo2888newArray(int i) {
            return new DataPointEnvelope[i];
        }
    };
    private DataPoint dataPoint;

    DataPointEnvelope(DataPoint dataPoint) {
        this.dataPoint = dataPoint;
    }

    public static List<DataPoint> convertFromEnvelopes(List<DataPointEnvelope> list) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(list.get(i).toDataPoint());
        }
        return arrayList;
    }

    public static List<DataPointEnvelope> convertToEnvelopes(List<DataPoint> list) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new DataPointEnvelope(list.get(i)));
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 1;
    }

    public DataPoint toDataPoint() {
        DataPoint dataPoint = this.dataPoint;
        this.dataPoint = null;
        return dataPoint;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.dataPoint.getName());
        parcel.writeString(this.dataPoint.getValue());
        parcel.writeInt(this.dataPoint.getSamples());
        parcel.writeInt(this.dataPoint.getType().ordinal());
    }
}
