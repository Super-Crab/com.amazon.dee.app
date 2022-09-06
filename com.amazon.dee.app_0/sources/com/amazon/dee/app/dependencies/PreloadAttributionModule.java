package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.preload.attribution.DefaultPreloadAttributionManager;
import com.amazon.alexa.preload.attribution.FeatureQueryBridge;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.ui.preload.DefaultPreloadAttributionUIManager;
import com.amazon.dee.app.ui.preload.PreloadAttributionUIManager;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class PreloadAttributionModule {
    @Provides
    @ApplicationScope
    public PreloadAttributionManager providePreloadAttributionManager(Context context, final FeatureServiceV2 featureServiceV2) {
        return new DefaultPreloadAttributionManager(context, null, new FeatureQueryBridge() { // from class: com.amazon.dee.app.dependencies.PreloadAttributionModule.1
            @Override // com.amazon.alexa.preload.attribution.FeatureQueryBridge
            public boolean isActive(String str) {
                return featureServiceV2.hasAccess(str, false);
            }
        });
    }

    @Provides
    @ApplicationScope
    public PreloadAttributionUIManager providePreloadAttributionUIManager(Context context, RoutingService routingService, PreloadAttributionManager preloadAttributionManager) {
        return new DefaultPreloadAttributionUIManager(context, routingService, preloadAttributionManager);
    }
}
