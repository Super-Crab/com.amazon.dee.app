package com.amazon.rtcsc.interfaces;

import android.os.Parcel;
import android.os.Parcelable;
import lombok.NonNull;
/* loaded from: classes13.dex */
public final class RtcscCustomMetric implements Parcelable {
    public static final Parcelable.Creator<RtcscCustomMetric> CREATOR = new Parcelable.Creator<RtcscCustomMetric>() { // from class: com.amazon.rtcsc.interfaces.RtcscCustomMetric.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RtcscCustomMetric mo4502createFromParcel(Parcel parcel) {
            return new RtcscCustomMetric(parcel.readString(), parcel.readString(), (Timer[]) parcel.createTypedArray(Timer.CREATOR), (Counter[]) parcel.createTypedArray(Counter.CREATOR), (Metadata[]) parcel.createTypedArray(Metadata.CREATOR), Priority.valueOf(parcel.readString()));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RtcscCustomMetric[] mo4503newArray(int i) {
            return new RtcscCustomMetric[i];
        }
    };
    public Counter[] mCounters;
    public Metadata[] mMetadata;
    public Priority mPriority;
    public String mProgramName;
    public String mSourceName;
    public Timer[] mTimers;

    /* loaded from: classes13.dex */
    public static class Counter implements Parcelable {
        public static final Parcelable.Creator<Counter> CREATOR = new Parcelable.Creator<Counter>() { // from class: com.amazon.rtcsc.interfaces.RtcscCustomMetric.Counter.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public Counter mo4504createFromParcel(Parcel parcel) {
                return new Counter(parcel.readString(), parcel.readInt());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public Counter[] mo4505newArray(int i) {
                return new Counter[i];
            }
        };
        public String mName;
        public int mValue;

        public Counter(@NonNull String str, int i) {
            if (str != null) {
                this.mName = str;
                this.mValue = i;
                return;
            }
            throw new IllegalArgumentException("name is null");
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mName);
            parcel.writeInt(this.mValue);
        }
    }

    /* loaded from: classes13.dex */
    public static class Metadata implements Parcelable {
        public static final Parcelable.Creator<Metadata> CREATOR = new Parcelable.Creator<Metadata>() { // from class: com.amazon.rtcsc.interfaces.RtcscCustomMetric.Metadata.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public Metadata mo4506createFromParcel(Parcel parcel) {
                return new Metadata(parcel.readString(), parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public Metadata[] mo4507newArray(int i) {
                return new Metadata[i];
            }
        };
        public String mName;
        public String mValue;

        public Metadata(@NonNull String str, @NonNull String str2) {
            if (str != null) {
                if (str2 != null) {
                    this.mName = str;
                    this.mValue = str2;
                    return;
                }
                throw new IllegalArgumentException("value is null");
            }
            throw new IllegalArgumentException("name is null");
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mName);
            parcel.writeString(this.mValue);
        }
    }

    /* loaded from: classes13.dex */
    public enum Priority {
        NORMAL,
        HIGH,
        CRITICAL
    }

    /* loaded from: classes13.dex */
    public static class Timer implements Parcelable {
        public static final Parcelable.Creator<Timer> CREATOR = new Parcelable.Creator<Timer>() { // from class: com.amazon.rtcsc.interfaces.RtcscCustomMetric.Timer.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public Timer mo4508createFromParcel(Parcel parcel) {
                return new Timer(parcel.readString(), parcel.readDouble());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public Timer[] mo4509newArray(int i) {
                return new Timer[i];
            }
        };
        public String mName;
        public double mValue;

        public Timer(@NonNull String str, double d) {
            if (str != null) {
                this.mName = str;
                this.mValue = d;
                return;
            }
            throw new IllegalArgumentException("name is null");
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mName);
            parcel.writeDouble(this.mValue);
        }
    }

    public RtcscCustomMetric(@NonNull String str, @NonNull String str2, Timer[] timerArr, Counter[] counterArr, Metadata[] metadataArr, Priority priority) {
        if (str != null) {
            if (str2 != null) {
                this.mProgramName = str;
                this.mSourceName = str2;
                this.mTimers = timerArr;
                this.mCounters = counterArr;
                this.mMetadata = metadataArr;
                this.mPriority = priority;
                return;
            }
            throw new IllegalArgumentException("sourceName is null");
        }
        throw new IllegalArgumentException("programName is null");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Counter[] getCounters() {
        return this.mCounters;
    }

    public Metadata[] getMetadata() {
        return this.mMetadata;
    }

    public Priority getPriority() {
        return this.mPriority;
    }

    public String getProgramName() {
        return this.mProgramName;
    }

    public String getSourceName() {
        return this.mSourceName;
    }

    public Timer[] getTimers() {
        return this.mTimers;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mProgramName);
        parcel.writeString(this.mSourceName);
        parcel.writeTypedArray(this.mTimers, 0);
        parcel.writeTypedArray(this.mCounters, 0);
        parcel.writeTypedArray(this.mMetadata, 0);
        parcel.writeString(this.mPriority.name());
    }
}
