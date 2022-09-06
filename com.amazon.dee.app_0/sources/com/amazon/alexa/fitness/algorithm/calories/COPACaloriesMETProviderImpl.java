package com.amazon.alexa.fitness.algorithm.calories;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: COPACaloriesMETProviderImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000bR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/calories/COPACaloriesMETProviderImpl;", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesMETProvider;", "()V", "lookupTableOfMETData", "", "Lcom/amazon/alexa/fitness/algorithm/calories/COPACaloriesMETProviderImpl$METLookupEntry;", "[Lcom/amazon/alexa/fitness/algorithm/calories/COPACaloriesMETProviderImpl$METLookupEntry;", "computeMETValue", "", "workoutStatistics", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesWorkoutStatistics;", "(Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesWorkoutStatistics;)Ljava/lang/Double;", "METLookupEntry", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class COPACaloriesMETProviderImpl implements CaloriesMETProvider {
    private final METLookupEntry[] lookupTableOfMETData;

    /* compiled from: COPACaloriesMETProviderImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003J0\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\f\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/calories/COPACaloriesMETProviderImpl$METLookupEntry;", "", "minMPH", "", "maxMPH", "METValue", "(Ljava/lang/Double;Ljava/lang/Double;D)V", "getMETValue", "()D", "getMaxMPH", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getMinMPH", "component1", "component2", "component3", "contains", "", "cadenceInMPH", "copy", "(Ljava/lang/Double;Ljava/lang/Double;D)Lcom/amazon/alexa/fitness/algorithm/calories/COPACaloriesMETProviderImpl$METLookupEntry;", "equals", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes.dex */
    private static final class METLookupEntry {
        private final double METValue;
        @Nullable
        private final Double maxMPH;
        @Nullable
        private final Double minMPH;

        public METLookupEntry(@Nullable Double d, @Nullable Double d2, double d3) {
            this.minMPH = d;
            this.maxMPH = d2;
            this.METValue = d3;
        }

        public static /* synthetic */ METLookupEntry copy$default(METLookupEntry mETLookupEntry, Double d, Double d2, double d3, int i, Object obj) {
            if ((i & 1) != 0) {
                d = mETLookupEntry.minMPH;
            }
            if ((i & 2) != 0) {
                d2 = mETLookupEntry.maxMPH;
            }
            if ((i & 4) != 0) {
                d3 = mETLookupEntry.METValue;
            }
            return mETLookupEntry.copy(d, d2, d3);
        }

        @Nullable
        public final Double component1() {
            return this.minMPH;
        }

        @Nullable
        public final Double component2() {
            return this.maxMPH;
        }

        public final double component3() {
            return this.METValue;
        }

        public final boolean contains(double d) {
            Double d2 = this.minMPH;
            boolean z = d2 == null || d2.doubleValue() <= d;
            Double d3 = this.maxMPH;
            return z && (d3 == null || (d3.doubleValue() > d ? 1 : (d3.doubleValue() == d ? 0 : -1)) > 0);
        }

        @NotNull
        public final METLookupEntry copy(@Nullable Double d, @Nullable Double d2, double d3) {
            return new METLookupEntry(d, d2, d3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof METLookupEntry)) {
                    return false;
                }
                METLookupEntry mETLookupEntry = (METLookupEntry) obj;
                return Intrinsics.areEqual((Object) this.minMPH, (Object) mETLookupEntry.minMPH) && Intrinsics.areEqual((Object) this.maxMPH, (Object) mETLookupEntry.maxMPH) && Double.compare(this.METValue, mETLookupEntry.METValue) == 0;
            }
            return true;
        }

        public final double getMETValue() {
            return this.METValue;
        }

        @Nullable
        public final Double getMaxMPH() {
            return this.maxMPH;
        }

        @Nullable
        public final Double getMinMPH() {
            return this.minMPH;
        }

        public int hashCode() {
            Double d = this.minMPH;
            int i = 0;
            int hashCode = (d != null ? d.hashCode() : 0) * 31;
            Double d2 = this.maxMPH;
            if (d2 != null) {
                i = d2.hashCode();
            }
            long doubleToLongBits = Double.doubleToLongBits(this.METValue);
            return ((hashCode + i) * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("METLookupEntry(minMPH=");
            outline107.append(this.minMPH);
            outline107.append(", maxMPH=");
            outline107.append(this.maxMPH);
            outline107.append(", METValue=");
            return GeneratedOutlineSupport1.outline84(outline107, this.METValue, ")");
        }
    }

    public COPACaloriesMETProviderImpl() {
        Double valueOf = Double.valueOf(0.1d);
        Double valueOf2 = Double.valueOf(2.0d);
        Double valueOf3 = Double.valueOf(2.5d);
        Double valueOf4 = Double.valueOf(2.8d);
        Double valueOf5 = Double.valueOf(3.5d);
        Double valueOf6 = Double.valueOf(4.0d);
        Double valueOf7 = Double.valueOf(4.5d);
        Double valueOf8 = Double.valueOf(5.0d);
        this.lookupTableOfMETData = new METLookupEntry[]{new METLookupEntry(valueOf, valueOf2, 2.0d), new METLookupEntry(valueOf2, valueOf3, 2.8d), new METLookupEntry(valueOf3, valueOf4, 3.0d), new METLookupEntry(valueOf4, valueOf5, 3.5d), new METLookupEntry(valueOf5, valueOf6, 4.3d), new METLookupEntry(valueOf6, valueOf7, 5.0d), new METLookupEntry(valueOf7, valueOf8, 7.0d), new METLookupEntry(valueOf8, Double.valueOf(6.0d), 8.3d), new METLookupEntry(Double.valueOf(6.0d), Double.valueOf(6.7d), 9.8d), new METLookupEntry(Double.valueOf(6.7d), Double.valueOf(7.0d), 10.5d), new METLookupEntry(Double.valueOf(7.0d), Double.valueOf(7.5d), 11.0d), new METLookupEntry(Double.valueOf(7.5d), Double.valueOf(8.6d), 11.8d), new METLookupEntry(Double.valueOf(8.6d), Double.valueOf(9.0d), 12.3d), new METLookupEntry(Double.valueOf(9.0d), Double.valueOf(10.0d), 12.8d), new METLookupEntry(Double.valueOf(10.0d), Double.valueOf(11.0d), 14.5d), new METLookupEntry(Double.valueOf(11.0d), Double.valueOf(12.0d), 16.0d), new METLookupEntry(Double.valueOf(12.0d), Double.valueOf(13.0d), 19.0d), new METLookupEntry(Double.valueOf(13.0d), Double.valueOf(14.0d), 19.8d), new METLookupEntry(Double.valueOf(14.0d), null, 23.0d)};
    }

    @Override // com.amazon.alexa.fitness.algorithm.calories.CaloriesMETProvider
    @Nullable
    public Double computeMETValue(@NotNull CaloriesWorkoutStatistics workoutStatistics) {
        METLookupEntry mETLookupEntry;
        Intrinsics.checkParameterIsNotNull(workoutStatistics, "workoutStatistics");
        METLookupEntry[] mETLookupEntryArr = this.lookupTableOfMETData;
        int length = mETLookupEntryArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                mETLookupEntry = null;
                break;
            }
            mETLookupEntry = mETLookupEntryArr[i];
            if (mETLookupEntry.contains(workoutStatistics.getCadenceInMPH())) {
                break;
            }
            i++;
        }
        if (mETLookupEntry != null) {
            return Double.valueOf(mETLookupEntry.getMETValue());
        }
        return null;
    }
}
