package com.amazon.alexa.fitness.sdk.database;

import com.amazon.alexa.drive.navigation.MappingApplication;
import com.amazon.alexa.fitness.api.fitnessSdk.ActivityType;
import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.sdk.Aggregation;
import com.amazon.alexa.fitness.sdk.LocationSampleData;
import com.amazon.alexa.fitness.sdk.Measurement;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.sdk.SampleType;
import com.amazon.alexa.presence.bleconn.service.PresenceBleService;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessDatabaseUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0007\u001a\n\u0010\b\u001a\u00020\u0007*\u00020\u0006\u001a\n\u0010\t\u001a\u00020\u0002*\u00020\u0001\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\f\u001a\n\u0010\r\u001a\u00020\f*\u00020\u000b\u001a\n\u0010\u000e\u001a\u00020\u000f*\u00020\u0010\u001a\n\u0010\u0011\u001a\u00020\u0010*\u00020\u000f\u001a\n\u0010\u0012\u001a\u00020\u0013*\u00020\u0002\u001a\n\u0010\u0014\u001a\u00020\u0015*\u00020\u0002¨\u0006\u0016"}, d2 = {"toActivityType", "Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;", "", "toAggregation", "Lcom/amazon/alexa/fitness/sdk/Aggregation;", "toEchoBudSample", "Lcom/amazon/alexa/fitness/sdk/Sample$EchoBudSample;", "Lcom/amazon/alexa/fitness/sdk/database/EchoBudSampleEntity;", "toEchoBudSampleEntity", "toKnownActivity", "toLocationSample", "Lcom/amazon/alexa/fitness/sdk/Sample$LocationSample;", "Lcom/amazon/alexa/fitness/sdk/database/LocationSampleEntity;", "toLocationSampleEntity", "toMeasurementSample", "Lcom/amazon/alexa/fitness/sdk/Sample$MeasurementSample;", "Lcom/amazon/alexa/fitness/sdk/database/MeasurementSampleEntity;", "toMeasurementSampleEntity", "toSampleType", "Lcom/amazon/alexa/fitness/sdk/SampleType;", "toUnits", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Units;", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessDatabaseUtilKt {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ActivityType.values().length];

        static {
            $EnumSwitchMapping$0[ActivityType.IDLE.ordinal()] = 1;
            $EnumSwitchMapping$0[ActivityType.WALKING.ordinal()] = 2;
            $EnumSwitchMapping$0[ActivityType.RUNNING.ordinal()] = 3;
        }
    }

    @NotNull
    public static final ActivityType toActivityType(@NotNull String toActivityType) {
        Intrinsics.checkParameterIsNotNull(toActivityType, "$this$toActivityType");
        int hashCode = toActivityType.hashCode();
        if (hashCode != -2026200673) {
            if (hashCode != 386623668) {
                if (hashCode == 1836798297 && toActivityType.equals(MappingApplication.WALKING)) {
                    return ActivityType.WALKING;
                }
            } else if (toActivityType.equals("STATIONARY")) {
                return ActivityType.IDLE;
            }
        } else if (toActivityType.equals(PresenceBleService.ServiceState.RUNNING)) {
            return ActivityType.RUNNING;
        }
        return ActivityType.UNKNOWN;
    }

    @NotNull
    public static final Aggregation toAggregation(@NotNull String toAggregation) {
        Aggregation[] values;
        Intrinsics.checkParameterIsNotNull(toAggregation, "$this$toAggregation");
        for (Aggregation aggregation : Aggregation.values()) {
            if (aggregation.name().equals(toAggregation)) {
                return aggregation;
            }
        }
        return Aggregation.Total;
    }

    @NotNull
    public static final Sample.EchoBudSample toEchoBudSample(@NotNull EchoBudSampleEntity toEchoBudSample) {
        Intrinsics.checkParameterIsNotNull(toEchoBudSample, "$this$toEchoBudSample");
        UUID fromString = UUID.fromString(toEchoBudSample.sessionId);
        Intrinsics.checkExpressionValueIsNotNull(fromString, "UUID.fromString(this.sessionId)");
        String str = toEchoBudSample.sensorId;
        Intrinsics.checkExpressionValueIsNotNull(str, "this.sensorId");
        Long l = toEchoBudSample.receivedAt;
        Intrinsics.checkExpressionValueIsNotNull(l, "this.receivedAt");
        long longValue = l.longValue();
        Long l2 = toEchoBudSample.collectedAt;
        Intrinsics.checkExpressionValueIsNotNull(l2, "this.collectedAt");
        long longValue2 = l2.longValue();
        String str2 = toEchoBudSample.activity;
        Intrinsics.checkExpressionValueIsNotNull(str2, "this.activity");
        ActivityType activityType = toActivityType(str2);
        Integer num = toEchoBudSample.steps;
        Intrinsics.checkExpressionValueIsNotNull(num, "this.steps");
        int intValue = num.intValue();
        Integer num2 = toEchoBudSample.cadence;
        Intrinsics.checkExpressionValueIsNotNull(num2, "this.cadence");
        return new Sample.EchoBudSample(fromString, str, longValue, longValue2, activityType, intValue, num2.intValue());
    }

    @NotNull
    public static final EchoBudSampleEntity toEchoBudSampleEntity(@NotNull Sample.EchoBudSample toEchoBudSampleEntity) {
        Intrinsics.checkParameterIsNotNull(toEchoBudSampleEntity, "$this$toEchoBudSampleEntity");
        return new EchoBudSampleEntity(toEchoBudSampleEntity.getSessionId().toString(), toEchoBudSampleEntity.getSensorId(), Long.valueOf(toEchoBudSampleEntity.getCollectionTimestamp()), Long.valueOf(toEchoBudSampleEntity.getReceivedAtTimestamp()), toKnownActivity(toEchoBudSampleEntity.getActivity()), Integer.valueOf(toEchoBudSampleEntity.getSteps()), Integer.valueOf(toEchoBudSampleEntity.getCadence()));
    }

    @NotNull
    public static final String toKnownActivity(@NotNull ActivityType toKnownActivity) {
        Intrinsics.checkParameterIsNotNull(toKnownActivity, "$this$toKnownActivity");
        int i = WhenMappings.$EnumSwitchMapping$0[toKnownActivity.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? "UNKNOWN" : PresenceBleService.ServiceState.RUNNING : MappingApplication.WALKING : "STATIONARY";
    }

    @NotNull
    public static final Sample.LocationSample toLocationSample(@NotNull LocationSampleEntity toLocationSample) {
        Intrinsics.checkParameterIsNotNull(toLocationSample, "$this$toLocationSample");
        UUID fromString = UUID.fromString(toLocationSample.sessionId);
        Intrinsics.checkExpressionValueIsNotNull(fromString, "UUID.fromString(this.sessionId)");
        String str = toLocationSample.sourceId;
        Intrinsics.checkExpressionValueIsNotNull(str, "this.sourceId");
        Long l = toLocationSample.receivedAt;
        Intrinsics.checkExpressionValueIsNotNull(l, "this.receivedAt");
        long longValue = l.longValue();
        Double latitude = toLocationSample.latitude;
        Intrinsics.checkExpressionValueIsNotNull(latitude, "latitude");
        double doubleValue = latitude.doubleValue();
        Double longitude = toLocationSample.longitude;
        Intrinsics.checkExpressionValueIsNotNull(longitude, "longitude");
        double doubleValue2 = longitude.doubleValue();
        Double altitude = toLocationSample.altitude;
        Intrinsics.checkExpressionValueIsNotNull(altitude, "altitude");
        double doubleValue3 = altitude.doubleValue();
        Float horizontalAccuracy = toLocationSample.horizontalAccuracy;
        Intrinsics.checkExpressionValueIsNotNull(horizontalAccuracy, "horizontalAccuracy");
        float floatValue = horizontalAccuracy.floatValue();
        Float verticalAccuracy = toLocationSample.verticalAccuracy;
        Intrinsics.checkExpressionValueIsNotNull(verticalAccuracy, "verticalAccuracy");
        float floatValue2 = verticalAccuracy.floatValue();
        Float heading = toLocationSample.heading;
        Intrinsics.checkExpressionValueIsNotNull(heading, "heading");
        float floatValue3 = heading.floatValue();
        Float speed = toLocationSample.speed;
        Intrinsics.checkExpressionValueIsNotNull(speed, "speed");
        float floatValue4 = speed.floatValue();
        Long collectedAt = toLocationSample.collectedAt;
        Intrinsics.checkExpressionValueIsNotNull(collectedAt, "collectedAt");
        return new Sample.LocationSample(fromString, str, longValue, new LocationSampleData(doubleValue, doubleValue2, doubleValue3, floatValue, floatValue2, floatValue3, floatValue4, collectedAt.longValue()));
    }

    @NotNull
    public static final LocationSampleEntity toLocationSampleEntity(@NotNull Sample.LocationSample toLocationSampleEntity) {
        Intrinsics.checkParameterIsNotNull(toLocationSampleEntity, "$this$toLocationSampleEntity");
        return new LocationSampleEntity(toLocationSampleEntity.getSessionId().toString(), toLocationSampleEntity.getSensorId(), Long.valueOf(toLocationSampleEntity.getReceivedAtTimestamp()), Long.valueOf(toLocationSampleEntity.getReceivedAtTimestamp()), Double.valueOf(toLocationSampleEntity.getSampleData().getLatitude()), Double.valueOf(toLocationSampleEntity.getSampleData().getLongitude()), Double.valueOf(toLocationSampleEntity.getSampleData().getAltitude()), Float.valueOf(toLocationSampleEntity.getSampleData().getHorizontalAccuracy()), Float.valueOf(toLocationSampleEntity.getSampleData().getVerticalAccuracy()), Float.valueOf(toLocationSampleEntity.getSampleData().getHeading()), Float.valueOf(toLocationSampleEntity.getSampleData().getSpeed()));
    }

    @NotNull
    public static final Sample.MeasurementSample toMeasurementSample(@NotNull MeasurementSampleEntity toMeasurementSample) {
        Intrinsics.checkParameterIsNotNull(toMeasurementSample, "$this$toMeasurementSample");
        UUID fromString = UUID.fromString(toMeasurementSample.sessionId);
        Intrinsics.checkExpressionValueIsNotNull(fromString, "UUID.fromString(this.sessionId)");
        String str = toMeasurementSample.algorithmId;
        Intrinsics.checkExpressionValueIsNotNull(str, "this.algorithmId");
        Long l = toMeasurementSample.receivedAt;
        Intrinsics.checkExpressionValueIsNotNull(l, "this.receivedAt");
        long longValue = l.longValue();
        String str2 = toMeasurementSample.type;
        Intrinsics.checkExpressionValueIsNotNull(str2, "this.type");
        SampleType sampleType = toSampleType(str2);
        String str3 = toMeasurementSample.value;
        Intrinsics.checkExpressionValueIsNotNull(str3, "this.value");
        Measurement.Discrete discrete = new Measurement.Discrete(Double.parseDouble(str3));
        String str4 = toMeasurementSample.units;
        Intrinsics.checkExpressionValueIsNotNull(str4, "this.units");
        Units units = toUnits(str4);
        String str5 = toMeasurementSample.aggregation;
        Intrinsics.checkExpressionValueIsNotNull(str5, "this.aggregation");
        return new Sample.MeasurementSample(fromString, str, longValue, sampleType, discrete, units, toAggregation(str5));
    }

    @NotNull
    public static final MeasurementSampleEntity toMeasurementSampleEntity(@NotNull Sample.MeasurementSample toMeasurementSampleEntity) {
        Intrinsics.checkParameterIsNotNull(toMeasurementSampleEntity, "$this$toMeasurementSampleEntity");
        return new MeasurementSampleEntity(toMeasurementSampleEntity.getSessionId().toString(), toMeasurementSampleEntity.getSensorId(), Long.valueOf(toMeasurementSampleEntity.getReceivedAtTimestamp()), String.valueOf(toMeasurementSampleEntity.getValue().getValue()), toMeasurementSampleEntity.getSampleType().toString(), toMeasurementSampleEntity.getUnits().toString(), toMeasurementSampleEntity.getAggregation().toString());
    }

    @NotNull
    public static final SampleType toSampleType(@NotNull String toSampleType) {
        SampleType[] values;
        Intrinsics.checkParameterIsNotNull(toSampleType, "$this$toSampleType");
        for (SampleType sampleType : SampleType.values()) {
            if (sampleType.name().equals(toSampleType)) {
                return sampleType;
            }
        }
        return SampleType.EchoBud;
    }

    @NotNull
    public static final Units toUnits(@NotNull String toUnits) {
        Units[] values;
        Intrinsics.checkParameterIsNotNull(toUnits, "$this$toUnits");
        for (Units units : Units.values()) {
            if (units.name().equals(toUnits)) {
                return units;
            }
        }
        return Units.Count;
    }
}
