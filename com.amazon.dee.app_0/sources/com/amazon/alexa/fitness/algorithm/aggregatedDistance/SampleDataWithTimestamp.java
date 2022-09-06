package com.amazon.alexa.fitness.algorithm.aggregatedDistance;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DistanceDeltaCalculator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\f\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0005HÆ\u0003J(\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/SampleDataWithTimestamp;", ExifInterface.GPS_DIRECTION_TRUE, "", "sampleData", "timestamp", "", "(Ljava/lang/Object;J)V", "getSampleData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getTimestamp", "()J", "component1", "component2", "copy", "(Ljava/lang/Object;J)Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/SampleDataWithTimestamp;", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SampleDataWithTimestamp<T> {
    private final T sampleData;
    private final long timestamp;

    public SampleDataWithTimestamp(T t, long j) {
        this.sampleData = t;
        this.timestamp = j;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SampleDataWithTimestamp copy$default(SampleDataWithTimestamp sampleDataWithTimestamp, Object obj, long j, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = sampleDataWithTimestamp.sampleData;
        }
        if ((i & 2) != 0) {
            j = sampleDataWithTimestamp.timestamp;
        }
        return sampleDataWithTimestamp.copy(obj, j);
    }

    public final T component1() {
        return this.sampleData;
    }

    public final long component2() {
        return this.timestamp;
    }

    @NotNull
    public final SampleDataWithTimestamp<T> copy(T t, long j) {
        return new SampleDataWithTimestamp<>(t, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SampleDataWithTimestamp)) {
                return false;
            }
            SampleDataWithTimestamp sampleDataWithTimestamp = (SampleDataWithTimestamp) obj;
            return Intrinsics.areEqual(this.sampleData, sampleDataWithTimestamp.sampleData) && this.timestamp == sampleDataWithTimestamp.timestamp;
        }
        return true;
    }

    public final T getSampleData() {
        return this.sampleData;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        T t = this.sampleData;
        int hashCode = t != null ? t.hashCode() : 0;
        long j = this.timestamp;
        return (hashCode * 31) + ((int) (j ^ (j >>> 32)));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SampleDataWithTimestamp(sampleData=");
        outline107.append(this.sampleData);
        outline107.append(", timestamp=");
        return GeneratedOutlineSupport1.outline87(outline107, this.timestamp, ")");
    }
}
