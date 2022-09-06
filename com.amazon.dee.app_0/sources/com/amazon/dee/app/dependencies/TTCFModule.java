package com.amazon.dee.app.dependencies;

import com.amazon.alexa.ttcf.TTCFService;
import com.amazon.alexa.ttcf.api.TTCFCheckpoint;
import com.amazon.alexa.ttcf.api.TTCFRoutingDelegate;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class TTCFModule {
    @Provides
    @ApplicationScope
    public TTCFCheckpoint provideTTCFCheckpoint(TTCFService tTCFService) {
        return tTCFService;
    }

    @Provides
    @ApplicationScope
    public TTCFRoutingDelegate provideTTCFRoutingDelegate(TTCFService tTCFService) {
        return tTCFService;
    }

    @Provides
    @ApplicationScope
    public TTCFService provideTTCFService() {
        return new TTCFService(new DefaultTTCFRecorder((MetricsService) GeneratedOutlineSupport1.outline20(MetricsService.class)));
    }
}
