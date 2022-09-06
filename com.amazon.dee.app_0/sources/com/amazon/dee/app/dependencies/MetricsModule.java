package com.amazon.dee.app.dependencies;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.dee.app.metrics.MetricsHelper;
import com.amazon.dee.app.services.metrics.DCMMetricsConnector;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class MetricsModule {
    @Provides
    public DCMMetricsConnector provideDCMMetricsConnector(@NonNull Context context, @NonNull EnvironmentService environmentService, @NonNull Lazy<MetricsFactory> lazy) {
        return new DCMMetricsConnector(context, environmentService, lazy);
    }

    @Provides
    public MetricsFactory provideMetricsFactory(@NonNull Context context) {
        return MetricsHelper.getMetricsFactory(context);
    }
}
