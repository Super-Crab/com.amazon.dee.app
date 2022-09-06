package com.amazon.wellness.io.format.abs;

import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
/* loaded from: classes13.dex */
public final class Biometric {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_BiometricDataPoint_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_BiometricDataPoint_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_BodyTemperatureV1_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_BodyTemperatureV1_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_CadenceV1_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_CadenceV1_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_CaloriesV1_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_CaloriesV1_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_HeartRateElevationV1_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_HeartRateElevationV1_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_HeartRateV1_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_HeartRateV1_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_MovementSummaryV1_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_MovementSummaryV1_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_RRIntervalV1_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_RRIntervalV1_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_SleepStateV1_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_SleepStateV1_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_StepCountV1_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_StepCountV1_fieldAccessorTable;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u000fbiometric.proto\"þ\u0003\n\u0012BiometricDataPoint\u0012%\n\rheart_rate_v1\u0018\u0001 \u0001(\u000b2\f.HeartRateV1H\u0000\u0012'\n\u000err_interval_v1\u0018\u0002 \u0001(\u000b2\r.RRIntervalV1H\u0000\u0012 \n\ncadence_v1\u0018\u0003 \u0001(\u000b2\n.CadenceV1H\u0000\u0012%\n\rstep_count_v1\u0018\u0004 \u0001(\u000b2\f.StepCountV1H\u0000\u0012\"\n\u000bactivity_v1\u0018\u0005 \u0001(\u000e2\u000b.ActivityV1H\u0000\u00121\n\u0013body_temperature_v1\u0018\u0006 \u0001(\u000b2\u0012.BodyTemperatureV1H\u0000\u00125\n\u0015activity_intensity_v1\u0018\u0007 \u0001(\u000e2\u0014.ActivityIntensityV1H\u0000\u0012\"\n\u000bcalories_v1\u0018\b \u0001(\u000b2\u000b.CaloriesV1H\u0000\u0012'\n\u000esleep_state_v1\u0018\t \u0001(\u000b2\r.SleepStateV1H\u0000\u00121\n\u0013movement_summary_v1\u0018\n \u0001(\u000b2\u0012.MovementSummaryV1H\u0000\u00128\n\u0017heart_rate_elevation_v1\u0018\u000b \u0001(\u000b2\u0015.HeartRateElevationV1H\u0000B\u0007\n\u0005value\"'\n\u000bHeartRateV1\u0012\u0018\n\u0010beats_per_minute\u0018\u0001 \u0001(\r\"\u0082\u0001\n\fRRIntervalV1\u0012 \n\u0018rr_interval_milliseconds\u0018\u0001 \u0001(\r\u0012'\n\u001fpeak_time_milliseconds_uptime_H\u0018\u0002 \u0001(\r\u0012'\n\u001fpeak_time_milliseconds_uptime_L\u0018\u0003 \u0001(\r\"%\n\tCadenceV1\u0012\u0018\n\u0010steps_per_minute\u0018\u0001 \u0001(\r\"*\n\u000bStepCountV1\u0012\u001b\n\u0013absolute_step_count\u0018\u0001 \u0001(\r\"5\n\u0011BodyTemperatureV1\u0012 \n\u0018body_temperature_celsius\u0018\u0001 \u0001(\u0002\";\n\nCaloriesV1\u0012-\n%epoch_calories_burned_past_30_seconds\u0018\u0001 \u0001(\u0002\"Ë\u0001\n\fSleepStateV1\u0012\u0019\n\u0011percentage_asleep\u0018\u0001 \u0001(\r\u0012/\n'intend_to_sleep_last_change_ms_uptime_H\u0018\u0002 \u0001(\r\u0012/\n'intend_to_sleep_last_change_ms_uptime_L\u0018\u0003 \u0001(\r\u0012%\n\u001dshould_be_processed_for_sleep\u0018\u0004 \u0001(\b\u0012\u0017\n\u000fintend_to_sleep\u0018\u0005 \u0001(\b\"Ì\u0001\n\u0011MovementSummaryV1\u0012\u001d\n\u0015movement_summary_mean\u0018\u0001 \u0001(\u0002\u0012+\n#movement_summary_standard_deviation\u0018\u0002 \u0001(\u0002\u0012\u001e\n\u0016movement_summary_total\u0018\u0003 \u0001(\u0002\u0012\"\n\u001amovement_summary_aggregate\u0018\u0004 \u0001(\u0002\u0012'\n\u001fmovement_window_size_in_seconds\u0018\u0005 \u0001(\r\"I\n\u0014HeartRateElevationV1\u0012\u0017\n\u000fepoch_elevation\u0018\u0001 \u0001(\r\u0012\u0018\n\u0010epoch_heart_rate\u0018\u0002 \u0001(\r*\u008f\u0001\n\nActivityV1\u0012\u0014\n\u0010ACTIVITY_UNKNOWN\u0010\u0000\u0012\u0011\n\rACTIVITY_IDLE\u0010\u0001\u0012\u0014\n\u0010ACTIVITY_WALKING\u0010\u0002\u0012\u0014\n\u0010ACTIVITY_RUNNING\u0010\u0003\u0012\u0016\n\u0012ACTIVITY_BICYCLING\u0010\u0004\u0012\u0014\n\u0010ACTIVITY_DRIVING\u0010\u0005*È\u0001\n\u0013ActivityIntensityV1\u0012\u001e\n\u001aACTIVITY_INTENSITY_UNKNOWN\u0010\u0000\u0012\u001b\n\u0017ACTIVITY_INTENSITY_NONE\u0010\u0001\u0012\u001a\n\u0016ACTIVITY_INTENSITY_LOW\u0010\u0002\u0012\u001d\n\u0019ACTIVITY_INTENSITY_MEDIUM\u0010\u0003\u0012\u001b\n\u0017ACTIVITY_INTENSITY_HIGH\u0010\u0004\u0012\u001c\n\u0018ACTIVITY_INTENSITY_SLEEP\u0010\u0005B.\n!com.amazon.wellness.io.format.absP\u0001 \u0001\u0001¢\u0002\u0003ABSb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.wellness.io.format.abs.Biometric.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = Biometric.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_BiometricDataPoint_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_BiometricDataPoint_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_BiometricDataPoint_descriptor, new String[]{"HeartRateV1", "RrIntervalV1", "CadenceV1", "StepCountV1", "ActivityV1", "BodyTemperatureV1", "ActivityIntensityV1", "CaloriesV1", "SleepStateV1", "MovementSummaryV1", "HeartRateElevationV1", MAPCookie.KEY_VALUE});
        internal_static_HeartRateV1_descriptor = getDescriptor().getMessageTypes().get(1);
        internal_static_HeartRateV1_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_HeartRateV1_descriptor, new String[]{"BeatsPerMinute"});
        internal_static_RRIntervalV1_descriptor = getDescriptor().getMessageTypes().get(2);
        internal_static_RRIntervalV1_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_RRIntervalV1_descriptor, new String[]{"RrIntervalMilliseconds", "PeakTimeMillisecondsUptimeH", "PeakTimeMillisecondsUptimeL"});
        internal_static_CadenceV1_descriptor = getDescriptor().getMessageTypes().get(3);
        internal_static_CadenceV1_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_CadenceV1_descriptor, new String[]{"StepsPerMinute"});
        internal_static_StepCountV1_descriptor = getDescriptor().getMessageTypes().get(4);
        internal_static_StepCountV1_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_StepCountV1_descriptor, new String[]{"AbsoluteStepCount"});
        internal_static_BodyTemperatureV1_descriptor = getDescriptor().getMessageTypes().get(5);
        internal_static_BodyTemperatureV1_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_BodyTemperatureV1_descriptor, new String[]{"BodyTemperatureCelsius"});
        internal_static_CaloriesV1_descriptor = getDescriptor().getMessageTypes().get(6);
        internal_static_CaloriesV1_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_CaloriesV1_descriptor, new String[]{"EpochCaloriesBurnedPast30Seconds"});
        internal_static_SleepStateV1_descriptor = getDescriptor().getMessageTypes().get(7);
        internal_static_SleepStateV1_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SleepStateV1_descriptor, new String[]{"PercentageAsleep", "IntendToSleepLastChangeMsUptimeH", "IntendToSleepLastChangeMsUptimeL", "ShouldBeProcessedForSleep", "IntendToSleep"});
        internal_static_MovementSummaryV1_descriptor = getDescriptor().getMessageTypes().get(8);
        internal_static_MovementSummaryV1_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_MovementSummaryV1_descriptor, new String[]{"MovementSummaryMean", "MovementSummaryStandardDeviation", "MovementSummaryTotal", "MovementSummaryAggregate", "MovementWindowSizeInSeconds"});
        internal_static_HeartRateElevationV1_descriptor = getDescriptor().getMessageTypes().get(9);
        internal_static_HeartRateElevationV1_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_HeartRateElevationV1_descriptor, new String[]{"EpochElevation", "EpochHeartRate"});
    }

    private Biometric() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
