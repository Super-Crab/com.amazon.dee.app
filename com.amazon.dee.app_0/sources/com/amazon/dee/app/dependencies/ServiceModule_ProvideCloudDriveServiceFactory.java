package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import com.amazon.dee.app.services.clouddrive.CloudDriveService;
import com.dee.app.metrics.MetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideCloudDriveServiceFactory implements Factory<CloudDriveService> {
    private final Provider<AmazonCloudDriveExtendedClient> amazonCloudDriveExtendedClientProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final ServiceModule module;
    private final Provider<PersistentStorage.Factory> storageFactoryProvider;
    private final Provider<TaskManager> taskManagerProvider;

    public ServiceModule_ProvideCloudDriveServiceFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<AmazonCloudDriveExtendedClient> provider3, Provider<PersistentStorage.Factory> provider4, Provider<Mobilytics> provider5, Provider<MetricsService> provider6, Provider<TaskManager> provider7, Provider<EventBus> provider8) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.identityServiceProvider = provider2;
        this.amazonCloudDriveExtendedClientProvider = provider3;
        this.storageFactoryProvider = provider4;
        this.mobilyticsProvider = provider5;
        this.metricsServiceProvider = provider6;
        this.taskManagerProvider = provider7;
        this.eventBusProvider = provider8;
    }

    public static ServiceModule_ProvideCloudDriveServiceFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<AmazonCloudDriveExtendedClient> provider3, Provider<PersistentStorage.Factory> provider4, Provider<Mobilytics> provider5, Provider<MetricsService> provider6, Provider<TaskManager> provider7, Provider<EventBus> provider8) {
        return new ServiceModule_ProvideCloudDriveServiceFactory(serviceModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static CloudDriveService provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<AmazonCloudDriveExtendedClient> provider3, Provider<PersistentStorage.Factory> provider4, Provider<Mobilytics> provider5, Provider<MetricsService> provider6, Provider<TaskManager> provider7, Provider<EventBus> provider8) {
        return proxyProvideCloudDriveService(serviceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get());
    }

    public static CloudDriveService proxyProvideCloudDriveService(ServiceModule serviceModule, Context context, IdentityService identityService, AmazonCloudDriveExtendedClient amazonCloudDriveExtendedClient, PersistentStorage.Factory factory, Mobilytics mobilytics, MetricsService metricsService, TaskManager taskManager, EventBus eventBus) {
        return (CloudDriveService) Preconditions.checkNotNull(serviceModule.provideCloudDriveService(context, identityService, amazonCloudDriveExtendedClient, factory, mobilytics, metricsService, taskManager, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CloudDriveService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.identityServiceProvider, this.amazonCloudDriveExtendedClientProvider, this.storageFactoryProvider, this.mobilyticsProvider, this.metricsServiceProvider, this.taskManagerProvider, this.eventBusProvider);
    }
}
