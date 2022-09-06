package com.amazon.alexa.handsfree.protocols.metrics.interprocess.operational;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.interprocess.InterProcessMetric;
/* loaded from: classes8.dex */
public class IPCPerformanceMetric extends InterProcessMetric {
    public static final Parcelable.Creator<IPCPerformanceMetric> CREATOR = new Parcelable.Creator<IPCPerformanceMetric>() { // from class: com.amazon.alexa.handsfree.protocols.metrics.interprocess.operational.IPCPerformanceMetric.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public IPCPerformanceMetric mo1530createFromParcel(Parcel parcel) {
            return new IPCPerformanceMetric(parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public IPCPerformanceMetric[] mo1531newArray(int i) {
            return new IPCPerformanceMetric[i];
        }
    };
    private final String mEventName;
    private final String mSource;

    public IPCPerformanceMetric(@Nullable String str, @Nullable String str2) {
        this.mEventName = str;
        this.mSource = str2;
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
        return metricsBuilder.withPerformanceMetric(str2, str);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mEventName);
        parcel.writeString(this.mSource);
    }
}
