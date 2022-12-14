package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobInfo;
import androidx.annotation.RequiresApi;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.AutoValue_SchedulerConfig_ConfigValue;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.auto.value.AutoValue;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
@AutoValue
/* loaded from: classes2.dex */
public abstract class SchedulerConfig {
    private static final long ONE_SECOND = 1000;
    private static final long THIRTY_SECONDS = 30000;
    private static final long TWENTY_FOUR_HOURS = 86400000;

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    /* loaded from: classes2.dex */
    public static class Builder {
        private Clock clock;
        private Map<Priority, ConfigValue> values = new HashMap();

        public Builder addConfig(Priority priority, ConfigValue configValue) {
            this.values.put(priority, configValue);
            return this;
        }

        public SchedulerConfig build() {
            if (this.clock != null) {
                if (this.values.keySet().size() >= Priority.values().length) {
                    Map<Priority, ConfigValue> map = this.values;
                    this.values = new HashMap();
                    return SchedulerConfig.create(this.clock, map);
                }
                throw new IllegalStateException("Not all priorities have been configured");
            }
            throw new NullPointerException("missing required property: clock");
        }

        public Builder setClock(Clock clock) {
            this.clock = clock;
            return this;
        }
    }

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    @AutoValue
    /* loaded from: classes2.dex */
    public static abstract class ConfigValue {

        /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
        @AutoValue.Builder
        /* loaded from: classes2.dex */
        public static abstract class Builder {
            public abstract ConfigValue build();

            public abstract Builder setDelta(long j);

            public abstract Builder setFlags(Set<Flag> set);

            public abstract Builder setMaxAllowedDelay(long j);
        }

        public static Builder builder() {
            return new AutoValue_SchedulerConfig_ConfigValue.Builder().setFlags(Collections.emptySet());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract long getDelta();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Set<Flag> getFlags();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract long getMaxAllowedDelay();
    }

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    /* loaded from: classes2.dex */
    public enum Flag {
        NETWORK_UNMETERED,
        DEVICE_IDLE,
        DEVICE_CHARGING
    }

    public static Builder builder() {
        return new Builder();
    }

    static SchedulerConfig create(Clock clock, Map<Priority, ConfigValue> map) {
        return new AutoValue_SchedulerConfig(clock, map);
    }

    public static SchedulerConfig getDefault(Clock clock) {
        return builder().addConfig(Priority.DEFAULT, ConfigValue.builder().setDelta(30000L).setMaxAllowedDelay(86400000L).build()).addConfig(Priority.HIGHEST, ConfigValue.builder().setDelta(1000L).setMaxAllowedDelay(86400000L).build()).addConfig(Priority.VERY_LOW, ConfigValue.builder().setDelta(86400000L).setMaxAllowedDelay(86400000L).setFlags(immutableSetOf(Flag.NETWORK_UNMETERED, Flag.DEVICE_IDLE)).build()).setClock(clock).build();
    }

    private static <T> Set<T> immutableSetOf(T... tArr) {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(tArr)));
    }

    @RequiresApi(api = 21)
    private void populateFlags(JobInfo.Builder builder, Set<Flag> set) {
        if (set.contains(Flag.NETWORK_UNMETERED)) {
            builder.setRequiredNetworkType(2);
        } else {
            builder.setRequiredNetworkType(1);
        }
        if (set.contains(Flag.DEVICE_CHARGING)) {
            builder.setRequiresCharging(true);
        }
        if (set.contains(Flag.DEVICE_IDLE)) {
            builder.setRequiresDeviceIdle(true);
        }
    }

    @RequiresApi(api = 21)
    public JobInfo.Builder configureJob(JobInfo.Builder builder, Priority priority, long j, int i) {
        builder.setMinimumLatency(getScheduleDelay(priority, j, i));
        populateFlags(builder, getValues().get(priority).getFlags());
        return builder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Clock getClock();

    public Set<Flag> getFlags(Priority priority) {
        return getValues().get(priority).getFlags();
    }

    public long getScheduleDelay(Priority priority, long j, int i) {
        long time = j - getClock().getTime();
        ConfigValue configValue = getValues().get(priority);
        return Math.min(Math.max(((long) Math.pow(2.0d, i - 1)) * configValue.getDelta(), time), configValue.getMaxAllowedDelay());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Map<Priority, ConfigValue> getValues();
}
