package com.amazon.alexa.handsfree.latencyreporter;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.Objects;
/* loaded from: classes8.dex */
public class LatencyTimestamp implements Parcelable {
    public static final Parcelable.Creator<LatencyTimestamp> CREATOR = new Parcelable.Creator<LatencyTimestamp>() { // from class: com.amazon.alexa.handsfree.latencyreporter.LatencyTimestamp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NonNull
        /* renamed from: createFromParcel */
        public LatencyTimestamp mo1467createFromParcel(@NonNull Parcel parcel) {
            return new LatencyTimestamp(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NonNull
        /* renamed from: newArray */
        public LatencyTimestamp[] mo1468newArray(int i) {
            return new LatencyTimestamp[i];
        }
    };
    private final String mIdentifier;
    private final Latency mLatency;
    private final long mTimestamp;
    private final TimestampType mTimestampType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LatencyTimestamp(@NonNull Latency latency, @NonNull TimestampType timestampType, @Nullable String str, long j) {
        this.mLatency = latency;
        this.mTimestampType = timestampType;
        this.mIdentifier = str;
        this.mTimestamp = j;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatencyTimestamp)) {
            return false;
        }
        LatencyTimestamp latencyTimestamp = (LatencyTimestamp) obj;
        return this.mIdentifier == null ? this.mLatency == latencyTimestamp.getLatency() && this.mTimestampType == latencyTimestamp.mTimestampType && latencyTimestamp.getIdentifier() == null : this.mLatency == latencyTimestamp.getLatency() && this.mTimestampType == latencyTimestamp.mTimestampType && this.mIdentifier.equals(latencyTimestamp.getIdentifier());
    }

    @Nullable
    public String getIdentifier() {
        return this.mIdentifier;
    }

    @NonNull
    public Latency getLatency() {
        return this.mLatency;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    @NonNull
    public TimestampType getTimestampType() {
        return this.mTimestampType;
    }

    public int hashCode() {
        return Objects.hash(this.mLatency, this.mTimestampType, this.mIdentifier);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(this.mTimestamp);
        parcel.writeString(this.mLatency.name());
        parcel.writeString(this.mTimestampType.name());
        parcel.writeString(this.mIdentifier);
    }

    protected LatencyTimestamp(@NonNull Parcel parcel) {
        this.mTimestamp = parcel.readLong();
        this.mLatency = Latency.valueOf(parcel.readString());
        this.mTimestampType = TimestampType.valueOf(parcel.readString());
        this.mIdentifier = parcel.readString();
    }
}
