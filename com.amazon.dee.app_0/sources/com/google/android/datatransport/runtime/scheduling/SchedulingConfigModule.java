package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.time.WallTime;
import dagger.Module;
import dagger.Provides;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
@Module
/* loaded from: classes2.dex */
public abstract class SchedulingConfigModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public static SchedulerConfig config(@WallTime Clock clock) {
        return SchedulerConfig.getDefault(clock);
    }
}
