package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.alexa.drive.metrics.LandingPageMetrics;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.drive.userstudy.DriverDistractionLog;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.ttcf.api.TTCFCheckpoint;
import com.google.common.base.Preconditions;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes7.dex */
class ModeMetricsModule {
    @Provides
    @Singleton
    public DriveModeMetrics provideDriveModeMetrics() {
        return new DriveModeMetrics();
    }

    @Provides
    @Singleton
    public DriverDistractionLog provideDriverDistraction(Context context) {
        return new DriverDistractionLog(context);
    }

    @Provides
    @Singleton
    public EntertainmentMetrics provideEntertainmentnMetrics() {
        return new EntertainmentMetrics();
    }

    @Provides
    @Singleton
    public LandingPageMetrics provideLandingPageMetrics() {
        return new LandingPageMetrics();
    }

    @Provides
    @Singleton
    public DriveModeMetricsHelper provideMetricsHelper(Lazy<LandingPageMetrics> lazy, DriverDistractionLog driverDistractionLog, ModeService modeService) {
        return new DriveModeMetricsHelper(lazy, driverDistractionLog, modeService);
    }

    @Provides
    @Singleton
    public NavigationMetrics provideNavigationMetrics() {
        return new NavigationMetrics();
    }

    @Provides
    @Singleton
    public LazyComponent<TTCFCheckpoint> provideTTCFCheckPoint() {
        LazyComponent<TTCFCheckpoint> lazy = ComponentRegistry.getInstance().getLazy(TTCFCheckpoint.class);
        Preconditions.checkNotNull(lazy);
        return lazy;
    }

    @Provides
    public TTCFRecordOnce provideTTCFRecordOnce(LazyComponent<TTCFCheckpoint> lazyComponent) {
        return new TTCFRecordOnce(lazyComponent);
    }
}
