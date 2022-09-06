package com.amazon.alexa.hho.dependency;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.dee.app.http.CoralService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes8.dex */
public final class ApplicationModule {
    private final ComponentGetter registry;

    public ApplicationModule(@NonNull ComponentGetter componentGetter) {
        this.registry = componentGetter;
    }

    @Provides
    @Singleton
    public CoralService provideCoralService() {
        return (CoralService) this.registry.get(CoralService.class).mo10268get();
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return (EventBus) this.registry.get(EventBus.class).mo10268get();
    }

    @Provides
    @Singleton
    public IdentityService provideIdentityService() {
        return (IdentityService) this.registry.get(IdentityService.class).mo10268get();
    }

    @Provides
    @Singleton
    public LazyComponent<Mobilytics> provideMobilytics() {
        return this.registry.get(Mobilytics.class);
    }
}
