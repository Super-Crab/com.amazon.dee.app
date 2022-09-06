package com.amazon.alexa.biloba.dependency;

import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.service.TimeZoneApi;
import com.dee.app.http.CoralService;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes6.dex */
public final class TimeZoneModule {
    private TimeZoneModule() {
    }

    @Provides
    public static TimeZoneApi provideTimeZoneApi(@NonNull CoralService coralService, @NonNull SchedulerProvider schedulerProvider) {
        return new TimeZoneApi(coralService, schedulerProvider);
    }
}
