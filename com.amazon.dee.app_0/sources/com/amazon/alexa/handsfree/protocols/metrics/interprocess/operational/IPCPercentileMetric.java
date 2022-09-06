package com.amazon.alexa.handsfree.protocols.metrics.interprocess.operational;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.interprocess.InterProcessMetric;
/* loaded from: classes8.dex */
public class IPCPercentileMetric extends InterProcessMetric {
    public static final Parcelable.Creator<IPCPercentileMetric> CREATOR = new Parcelable.Creator<IPCPercentileMetric>() { // from class: com.amazon.alexa.handsfree.protocols.metrics.interprocess.operational.IPCPercentileMetric.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public IPCPercentileMetric mo1528createFromParcel(Parcel parcel) {
            return new IPCPercentileMetric(parcel.readString(), parcel.readString(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public IPCPercentileMetric[] mo1529newArray(int i) {
            return new IPCPercentileMetric[i];
        }
    };
    private final int mCount;
    private final String mEventName;
    private final String mSource;

    public IPCPercentileMetric(@Nullable String str, @Nullable String str2, int i) {
        this.mEventName = str;
        this.mSource = str2;
        this.mCount = i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.interprocess.InterProcessMetric
    @Nullable
    public MetricsBuilder populateMetricsBuilder(@NonNull MetricsBuilder metricsBuilder) {
        String str;
        String str2 = this.mSource;
        if (str2 == null || (str = this.mEventName) == null) {
            return null;
        }
        int i = this.mCount;
        if (i == 0) {
            return metricsBuilder.withPercentileMetricFailure(str2, str);
        }
        if (i == 1) {
            return metricsBuilder.withPercentileMetricSuccess(str2, str);
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mEventName);
        parcel.writeString(this.mSource);
        parcel.writeInt(this.mCount);
    }
}
