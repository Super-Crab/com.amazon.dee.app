package com.google.android.datatransport.runtime.scheduling;

import android.content.Context;
import android.os.Build;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.JobInfoScheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.time.Monotonic;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
@Module
/* loaded from: classes2.dex */
public abstract class SchedulingModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public static WorkScheduler workScheduler(Context context, EventStore eventStore, SchedulerConfig schedulerConfig, @Monotonic Clock clock) {
        int i = Build.VERSION.SDK_INT;
        return new JobInfoScheduler(context, eventStore, schedulerConfig);
    }

    @Binds
    abstract Scheduler scheduler(DefaultScheduler defaultScheduler);
}
