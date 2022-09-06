package com.amazon.alexa.biloba.model;

import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.generated.network.api.ActivityApi;
import com.amazon.alexa.biloba.generated.network.api.AlertConfigurationApi;
import com.amazon.alexa.biloba.generated.network.api.CardApi;
import com.amazon.alexa.biloba.generated.network.api.CommsApi;
import com.amazon.alexa.biloba.generated.network.api.EmergencySettingsApi;
import com.amazon.alexa.biloba.generated.network.api.GroupApi;
import com.amazon.alexa.biloba.generated.network.api.SettingsApi;
import com.amazon.alexa.biloba.generated.network.api.TodaysActivitiesApi;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.dee.app.http.CoralService;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes6.dex */
public final class Configuration {
    private Configuration() {
    }

    @Provides
    public static ActivityApi provideActivityApi(@NonNull CoralService coralService, @NonNull PersonIdProvider personIdProvider, @NonNull SchedulerProvider schedulerProvider) {
        return new ActivityApi(coralService, personIdProvider, schedulerProvider);
    }

    @Provides
    public static AlertConfigurationApi provideAlertConfigurationApi(@NonNull CoralService coralService, @NonNull PersonIdProvider personIdProvider, @NonNull SchedulerProvider schedulerProvider) {
        return new AlertConfigurationApi(coralService, personIdProvider, schedulerProvider);
    }

    @Provides
    public static CardApi provideCardApi(@NonNull CoralService coralService, @NonNull PersonIdProvider personIdProvider, @NonNull SchedulerProvider schedulerProvider) {
        return new CardApi(coralService, personIdProvider, schedulerProvider);
    }

    @Provides
    public static CommsApi provideCommsApi(@NonNull CoralService coralService, @NonNull PersonIdProvider personIdProvider, @NonNull SchedulerProvider schedulerProvider) {
        return new CommsApi(coralService, personIdProvider, schedulerProvider);
    }

    @Provides
    public static EmergencySettingsApi provideEmergencySettingsApi(@NonNull CoralService coralService, @NonNull PersonIdProvider personIdProvider, @NonNull SchedulerProvider schedulerProvider) {
        return new EmergencySettingsApi(coralService, personIdProvider, schedulerProvider);
    }

    @Provides
    public static GroupApi provideGroupApi(@NonNull CoralService coralService, @NonNull PersonIdProvider personIdProvider, @NonNull SchedulerProvider schedulerProvider) {
        return new GroupApi(coralService, personIdProvider, schedulerProvider);
    }

    @Provides
    public static SettingsApi provideSettingsApi(@NonNull CoralService coralService, @NonNull PersonIdProvider personIdProvider, @NonNull SchedulerProvider schedulerProvider) {
        return new SettingsApi(coralService, personIdProvider, schedulerProvider);
    }

    @Provides
    public static TodaysActivitiesApi provideTodaysActivitiesApi(@NonNull CoralService coralService, @NonNull PersonIdProvider personIdProvider, @NonNull SchedulerProvider schedulerProvider) {
        return new TodaysActivitiesApi(coralService, personIdProvider, schedulerProvider);
    }
}
