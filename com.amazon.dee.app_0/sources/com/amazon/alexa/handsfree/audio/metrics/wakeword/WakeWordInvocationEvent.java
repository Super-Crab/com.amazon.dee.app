package com.amazon.alexa.handsfree.audio.metrics.wakeword;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.interprocess.InterProcessMetric;
import java.util.Objects;
/* loaded from: classes8.dex */
public class WakeWordInvocationEvent extends InterProcessMetric {
    public static final Parcelable.Creator<WakeWordInvocationEvent> CREATOR = new Parcelable.Creator<WakeWordInvocationEvent>() { // from class: com.amazon.alexa.handsfree.audio.metrics.wakeword.WakeWordInvocationEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public WakeWordInvocationEvent mo1465createFromParcel(Parcel parcel) {
            return new WakeWordInvocationEvent(parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public WakeWordInvocationEvent[] mo1466newArray(int i) {
            return new WakeWordInvocationEvent[i];
        }
    };
    public static final String WW_ABANDONED = "WW_ABANDONED";
    public static final String WW_ACCEPTED = "WW_ACCEPTED";
    private final String mEventType;
    private final String mWakeWordConfidence;
    private final String mWakeWordModelId;

    public WakeWordInvocationEvent(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.mEventType = Objects.toString(str, "");
        this.mWakeWordModelId = Objects.toString(str2, "");
        this.mWakeWordConfidence = Objects.toString(str3, "");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.interprocess.InterProcessMetric
    @Nullable
    public MetricsBuilder populateMetricsBuilder(@NonNull MetricsBuilder metricsBuilder) {
        return metricsBuilder.withWakeWordInvocationMetric(this.mEventType, this.mWakeWordModelId, this.mWakeWordConfidence);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mEventType);
        parcel.writeString(this.mWakeWordModelId);
        parcel.writeString(this.mWakeWordConfidence);
    }
}
