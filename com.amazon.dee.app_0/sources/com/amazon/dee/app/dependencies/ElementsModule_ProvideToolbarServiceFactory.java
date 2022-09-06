package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.dee.app.services.toolbar.ToolbarService;
import com.facebook.react.ReactInstanceManager;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ElementsModule_ProvideToolbarServiceFactory implements Factory<ToolbarService> {
    private final Provider<Activity> activityProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<Gson> gsonProvider;
    private final ElementsModule module;
    private final Provider<ReactInstanceManager> reactInstanceManagerProvider;

    public ElementsModule_ProvideToolbarServiceFactory(ElementsModule elementsModule, Provider<EventBus> provider, Provider<Gson> provider2, Provider<Activity> provider3, Provider<ReactInstanceManager> provider4) {
        this.module = elementsModule;
        this.eventBusProvider = provider;
        this.gsonProvider = provider2;
        this.activityProvider = provider3;
        this.reactInstanceManagerProvider = provider4;
    }

    public static ElementsModule_ProvideToolbarServiceFactory create(ElementsModule elementsModule, Provider<EventBus> provider, Provider<Gson> provider2, Provider<Activity> provider3, Provider<ReactInstanceManager> provider4) {
        return new ElementsModule_ProvideToolbarServiceFactory(elementsModule, provider, provider2, provider3, provider4);
    }

    public static ToolbarService provideInstance(ElementsModule elementsModule, Provider<EventBus> provider, Provider<Gson> provider2, Provider<Activity> provider3, Provider<ReactInstanceManager> provider4) {
        return proxyProvideToolbarService(elementsModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static ToolbarService proxyProvideToolbarService(ElementsModule elementsModule, EventBus eventBus, Gson gson, Activity activity, ReactInstanceManager reactInstanceManager) {
        return (ToolbarService) Preconditions.checkNotNull(elementsModule.provideToolbarService(eventBus, gson, activity, reactInstanceManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ToolbarService mo10268get() {
        return provideInstance(this.module, this.eventBusProvider, this.gsonProvider, this.activityProvider, this.reactInstanceManagerProvider);
    }
}
