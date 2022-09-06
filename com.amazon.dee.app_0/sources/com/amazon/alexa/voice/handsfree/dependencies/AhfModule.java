package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import androidx.annotation.Nullable;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes11.dex */
public class AhfModule {
    private final Context mApplicationContext;

    public AhfModule(Context context) {
        this.mApplicationContext = context;
    }

    @AhfScope
    @Provides
    public Context provideApplicationContext() {
        return this.mApplicationContext;
    }

    @AhfScope
    @Provides
    public ComponentRegistry provideComponentRegistry() {
        return ComponentRegistry.getInstance();
    }

    @AhfScope
    @Provides
    public EventBus provideEventBus(ComponentRegistry componentRegistry) {
        return (EventBus) componentRegistry.get(EventBus.class).get();
    }

    @AhfScope
    @Provides
    public FeatureServiceV2 provideFeatureServiceV2(ComponentRegistry componentRegistry) {
        return (FeatureServiceV2) componentRegistry.get(FeatureServiceV2.class).get();
    }

    @Nullable
    @AhfScope
    @Provides
    public IdentityService provideIdentityService(ComponentRegistry componentRegistry) {
        return (IdentityService) componentRegistry.get(IdentityService.class).orNull();
    }

    @AhfScope
    @Provides
    public Initializer provideInitializer() {
        return InitializerProvider.getInitializer();
    }
}
