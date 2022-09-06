package com.amazon.alexa.fitness.algorithm.aggregatedDistance;

import android.location.Location;
import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.LocationSampleData;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.util.ConversionUtilsKt;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LocationBasedDistanceDeltaCalculator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u0016J\u0016\u0010\u0016\u001a\u00020\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0016\u0010\u001b\u001a\u00020\u001c2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0018H\u0002J\u0014\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e*\u00020\u0005H\u0002J\f\u0010\u001e\u001a\u00020\u001f*\u00020\u000fH\u0002R\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0011\u0010\u0012¨\u0006 "}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/LocationBasedDistanceDeltaCalculator;", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/DistanceDeltaCalculator;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "referenceSample", "Lcom/amazon/alexa/fitness/sdk/Sample;", "(Lcom/amazon/alexa/fitness/logs/ILog;Lcom/amazon/alexa/fitness/sdk/Sample;)V", "isActive", "", "()Z", "setActive", "(Z)V", "locationSamples", "", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/SampleDataWithTimestamp;", "Lcom/amazon/alexa/fitness/sdk/LocationSampleData;", "value", "setReferenceSample", "(Lcom/amazon/alexa/fitness/sdk/Sample;)V", "addSample", "", "sample", "addSamples", "samples", "", "calculate", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/DistanceDelta;", "calculateGpsReliabilityCoefficient", "", "getLocationSample", "toAndroidLocation", "Landroid/location/Location;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LocationBasedDistanceDeltaCalculator implements DistanceDeltaCalculator {
    private boolean isActive;
    private List<SampleDataWithTimestamp<LocationSampleData>> locationSamples;
    private final ILog log;
    private Sample referenceSample;

    public LocationBasedDistanceDeltaCalculator(@NotNull ILog log, @Nullable Sample sample) {
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.log = log;
        setReferenceSample(sample);
        this.locationSamples = new ArrayList();
    }

    private final double calculateGpsReliabilityCoefficient(List<LocationSampleData> list) {
        int collectionSizeOrDefault;
        float sumOfFloat;
        int collectionSizeOrDefault2;
        float sumOfFloat2;
        ILog.DefaultImpls.debug$default(this.log, "LocationBasedDistanceDeltaCalculator", "Calculating GRC...", null, 4, null);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (LocationSampleData locationSampleData : list) {
            arrayList.add(Float.valueOf(locationSampleData.getHorizontalAccuracy()));
        }
        sumOfFloat = CollectionsKt___CollectionsKt.sumOfFloat(arrayList);
        double size = sumOfFloat / list.size();
        ILog iLog = this.log;
        ILog.DefaultImpls.debug$default(iLog, "LocationBasedDistanceDeltaCalculator", "averageHorizontalAccuracy: " + size, null, 4, null);
        collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
        for (LocationSampleData locationSampleData2 : list) {
            arrayList2.add(Float.valueOf(locationSampleData2.getSpeed()));
        }
        sumOfFloat2 = CollectionsKt___CollectionsKt.sumOfFloat(arrayList2);
        double size2 = sumOfFloat2 / list.size();
        ILog iLog2 = this.log;
        ILog.DefaultImpls.debug$default(iLog2, "LocationBasedDistanceDeltaCalculator", "averageGPSSpeed: " + size2, null, 4, null);
        double d = size2 > 0.5d ? 1.0d : 0.0d;
        ILog iLog3 = this.log;
        ILog.DefaultImpls.debug$default(iLog3, "LocationBasedDistanceDeltaCalculator", "speedCoefficient: " + d, null, 4, null);
        double size3 = d * (((double) list.size()) / size);
        ILog iLog4 = this.log;
        ILog.DefaultImpls.debug$default(iLog4, "LocationBasedDistanceDeltaCalculator", "GRC value: " + size3, null, 4, null);
        return Double.isNaN(size3) ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : size3;
    }

    private final SampleDataWithTimestamp<LocationSampleData> getLocationSample(@NotNull Sample sample) {
        if (!(sample instanceof Sample.LocationSample)) {
            sample = null;
        }
        Sample.LocationSample locationSample = (Sample.LocationSample) sample;
        if (locationSample != null) {
            return new SampleDataWithTimestamp<>(locationSample.getSampleData(), locationSample.getReceivedAtTimestamp());
        }
        return null;
    }

    private final void setReferenceSample(Sample sample) {
        boolean z = (sample != null ? getLocationSample(sample) : null) != null;
        if (!z) {
            ILog.DefaultImpls.error$default(this.log, "LocationBasedDistanceDeltaCalculator", "Invalid sample used to set referenceSample", null, 4, null);
        } else {
            ILog.DefaultImpls.debug$default(this.log, "LocationBasedDistanceDeltaCalculator", "Valid referenceSample set, calculator is active", null, 4, null);
        }
        setActive(z);
        this.referenceSample = sample;
    }

    private final Location toAndroidLocation(@NotNull LocationSampleData locationSampleData) {
        Location location = new Location("LocationBasedDistanceDeltaCalculator");
        location.setLatitude(locationSampleData.getLatitude());
        location.setLongitude(locationSampleData.getLongitude());
        location.setAccuracy(locationSampleData.getHorizontalAccuracy());
        return location;
    }

    @Override // com.amazon.alexa.fitness.algorithm.aggregatedDistance.DistanceDeltaCalculator
    public void addSample(@NotNull Sample sample) {
        Intrinsics.checkParameterIsNotNull(sample, "sample");
        if (!isActive()) {
            ILog.DefaultImpls.debug$default(this.log, "LocationBasedDistanceDeltaCalculator", "Currently inactive, setting sample as reference", null, 4, null);
            setReferenceSample(sample);
            return;
        }
        SampleDataWithTimestamp<LocationSampleData> locationSample = getLocationSample(sample);
        if (locationSample != null) {
            this.locationSamples.add(locationSample);
        } else {
            ILog.DefaultImpls.debug$default(this.log, "LocationBasedDistanceDeltaCalculator", "Ignoring sample of non-location type", null, 4, null);
        }
    }

    @Override // com.amazon.alexa.fitness.algorithm.aggregatedDistance.DistanceDeltaCalculator
    public void addSamples(@NotNull List<? extends Sample> samples) {
        Intrinsics.checkParameterIsNotNull(samples, "samples");
        for (Sample sample : samples) {
            addSample(sample);
        }
    }

    @Override // com.amazon.alexa.fitness.algorithm.aggregatedDistance.DistanceDeltaCalculator
    @NotNull
    public DistanceDelta calculate() {
        List<SampleDataWithTimestamp> sortedWith;
        int collectionSizeOrDefault;
        SampleDataWithTimestamp<LocationSampleData> locationSample;
        ILog.DefaultImpls.debug$default(this.log, "LocationBasedDistanceDeltaCalculator", "Performing location based distance calculation", null, 4, null);
        if (!isActive()) {
            ILog.DefaultImpls.debug$default(this.log, "LocationBasedDistanceDeltaCalculator", "Currently inactive. DistanceDelta = 0.0", null, 4, null);
            return new DistanceDelta(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, Units.Feet, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        } else if (this.locationSamples.isEmpty()) {
            ILog.DefaultImpls.debug$default(this.log, "LocationBasedDistanceDeltaCalculator", "Not enough location data samples. DistanceDelta = 0.0", null, 4, null);
            return new DistanceDelta(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, Units.Feet, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        } else {
            Sample sample = this.referenceSample;
            LocationSampleData sampleData = (sample == null || (locationSample = getLocationSample(sample)) == null) ? null : locationSample.getSampleData();
            if (sampleData == null) {
                ILog.DefaultImpls.debug$default(this.log, "LocationBasedDistanceDeltaCalculator", "referenceLocation is null. DistanceDelta = 0.0", null, 4, null);
                return new DistanceDelta(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, Units.Feet, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
            }
            double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            Location androidLocation = toAndroidLocation(sampleData);
            ILog.DefaultImpls.debug$default(this.log, "LocationBasedDistanceDeltaCalculator", "Calculating cumulative distance", null, 4, null);
            sortedWith = CollectionsKt___CollectionsKt.sortedWith(this.locationSamples, new Comparator<T>() { // from class: com.amazon.alexa.fitness.algorithm.aggregatedDistance.LocationBasedDistanceDeltaCalculator$calculate$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int compareValues;
                    compareValues = ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((SampleDataWithTimestamp) t).getTimestamp()), Long.valueOf(((SampleDataWithTimestamp) t2).getTimestamp()));
                    return compareValues;
                }
            });
            for (SampleDataWithTimestamp sampleDataWithTimestamp : sortedWith) {
                double distanceTo = toAndroidLocation((LocationSampleData) sampleDataWithTimestamp.getSampleData()).distanceTo(androidLocation);
                ILog.DefaultImpls.debug$default(this.log, "LocationBasedDistanceDeltaCalculator", "Distance from previous location: " + distanceTo + " (m)", null, 4, null);
                d += distanceTo;
                androidLocation = toAndroidLocation((LocationSampleData) sampleDataWithTimestamp.getSampleData());
            }
            ILog.DefaultImpls.debug$default(this.log, "LocationBasedDistanceDeltaCalculator", "Cumulative GPS distance: " + d + " (m)", null, 4, null);
            double metersToFeet = ConversionUtilsKt.metersToFeet(d);
            Units units = Units.Feet;
            List<SampleDataWithTimestamp<LocationSampleData>> list = this.locationSamples;
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            Iterator<T> it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList.add((LocationSampleData) ((SampleDataWithTimestamp) it2.next()).getSampleData());
            }
            return new DistanceDelta(metersToFeet, units, calculateGpsReliabilityCoefficient(arrayList));
        }
    }

    @Override // com.amazon.alexa.fitness.algorithm.aggregatedDistance.DistanceDeltaCalculator
    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean z) {
        this.isActive = z;
    }
}
