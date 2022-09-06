package com.google.android.datatransport.runtime.time;

import dagger.Module;
import dagger.Provides;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
@Module
/* loaded from: classes2.dex */
public abstract class TimeModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @WallTime
    public static Clock eventClock() {
        return new WallTimeClock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Monotonic
    public static Clock uptimeClock() {
        return new UptimeClock();
    }
}
