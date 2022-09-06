package com.amazon.alexa.biloba.dependency;

import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.generated.network.api.DevicesApi;
import com.amazon.alexa.biloba.service.AlertConfigurationApi;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.dee.app.http.CoralService;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes6.dex */
public final class AlertConfigurationModule {
    private AlertConfigurationModule() {
    }

    @Provides
    public static AlertConfigurationApi provideAlertConfigurationApi(@NonNull CoralService coralService, @NonNull PersonIdProvider personIdProvider, @NonNull SchedulerProvider schedulerProvider) {
        return new AlertConfigurationApi(coralService, personIdProvider, schedulerProvider);
    }

    @Provides
    public static DevicesApi provideDevicesApi(@NonNull CoralService coralService, @NonNull PersonIdProvider personIdProvider, @NonNull SchedulerProvider schedulerProvider) {
        return new DevicesApi(coralService, personIdProvider, schedulerProvider);
    }
}
