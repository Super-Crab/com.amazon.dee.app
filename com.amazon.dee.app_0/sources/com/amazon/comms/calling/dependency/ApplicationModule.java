package com.amazon.comms.calling.dependency;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.dee.app.metrics.MetricsService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
/* JADX INFO: Access modifiers changed from: package-private */
@Module
/* loaded from: classes10.dex */
public class ApplicationModule {
    private final Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApplicationModule(@NonNull Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public ComponentRegistry provideComponentRegistry() {
        return ComponentRegistry.getInstance();
    }

    @Provides
    @Singleton
    public MetricsService provideMetricsService(ComponentRegistry componentRegistry) {
        return (MetricsService) componentRegistry.getLazy(MetricsService.class).mo10268get();
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return this.context;
    }
}
