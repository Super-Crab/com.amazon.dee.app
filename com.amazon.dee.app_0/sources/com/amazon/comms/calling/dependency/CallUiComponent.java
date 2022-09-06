package com.amazon.comms.calling.dependency;

import android.content.Context;
import com.dee.app.metrics.MetricsService;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {ApplicationModule.class})
@Singleton
/* loaded from: classes10.dex */
public interface CallUiComponent {
    Context getContext();

    MetricsService getMetricsService();
}
