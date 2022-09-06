package com.amazon.wellness.io.format.abs;

import com.amazon.wellness.io.format.abs.BiometricDataPoint;
import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes13.dex */
public interface BiometricDataPointOrBuilder extends MessageOrBuilder {
    ActivityIntensityV1 getActivityIntensityV1();

    int getActivityIntensityV1Value();

    ActivityV1 getActivityV1();

    int getActivityV1Value();

    BodyTemperatureV1 getBodyTemperatureV1();

    BodyTemperatureV1OrBuilder getBodyTemperatureV1OrBuilder();

    CadenceV1 getCadenceV1();

    CadenceV1OrBuilder getCadenceV1OrBuilder();

    CaloriesV1 getCaloriesV1();

    CaloriesV1OrBuilder getCaloriesV1OrBuilder();

    HeartRateElevationV1 getHeartRateElevationV1();

    HeartRateElevationV1OrBuilder getHeartRateElevationV1OrBuilder();

    HeartRateV1 getHeartRateV1();

    HeartRateV1OrBuilder getHeartRateV1OrBuilder();

    MovementSummaryV1 getMovementSummaryV1();

    MovementSummaryV1OrBuilder getMovementSummaryV1OrBuilder();

    RRIntervalV1 getRrIntervalV1();

    RRIntervalV1OrBuilder getRrIntervalV1OrBuilder();

    SleepStateV1 getSleepStateV1();

    SleepStateV1OrBuilder getSleepStateV1OrBuilder();

    StepCountV1 getStepCountV1();

    StepCountV1OrBuilder getStepCountV1OrBuilder();

    BiometricDataPoint.ValueCase getValueCase();

    boolean hasBodyTemperatureV1();

    boolean hasCadenceV1();

    boolean hasCaloriesV1();

    boolean hasHeartRateElevationV1();

    boolean hasHeartRateV1();

    boolean hasMovementSummaryV1();

    boolean hasRrIntervalV1();

    boolean hasSleepStateV1();

    boolean hasStepCountV1();
}
