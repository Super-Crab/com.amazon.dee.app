package com.amazon.alexa.dnssd.dependencies;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import androidx.annotation.NonNull;
import com.amazon.alexa.dnssd.RxNsd;
import com.amazon.alexa.dnssd.subscribers.StartDiscoverySubscriber;
import com.amazon.alexa.dnssd.util.Metrics;
import com.amazon.alexa.dnssd.util.ObjectMapperFactory;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.tasks.api.TaskManager;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes7.dex */
public class DnssdModule {
    private final Context context;
    private final ComponentGetter registry;

    public DnssdModule(@NonNull ComponentGetter componentGetter, @NonNull Context context) {
        this.registry = componentGetter;
        this.context = context.getApplicationContext();
    }

    @Provides
    public EventBus provideEventBus() {
        return (EventBus) this.registry.get(EventBus.class).mo10268get();
    }

    @Provides
    @Singleton
    public Metrics provideMetrics(MetricsServiceV2 metricsServiceV2) {
        return new Metrics(metricsServiceV2);
    }

    @Provides
    public MetricsServiceV2 provideMetricsService() {
        return (MetricsServiceV2) this.registry.get(MetricsServiceV2.class).mo10268get();
    }

    @Provides
    @Singleton
    public ObjectMapperFactory provideObjectMapperFactory() {
        return new ObjectMapperFactory();
    }

    @Provides
    @Singleton
    public RxNsd provideRxNsd(Metrics metrics, NsdManager nsdManager, WifiManager wifiManager, WifiManager.MulticastLock multicastLock) {
        return new RxNsd(metrics, nsdManager, wifiManager, multicastLock);
    }

    @Provides
    @Singleton
    public StartDiscoverySubscriber provideStartDiscoverySubscriber(EventBus eventBus, Metrics metrics, ObjectMapperFactory objectMapperFactory, RxNsd rxNsd, TaskManager taskManager) {
        return new StartDiscoverySubscriber(eventBus, metrics, objectMapperFactory, rxNsd, taskManager);
    }

    @Provides
    public TaskManager provideTaskManager() {
        return (TaskManager) this.registry.get(TaskManager.class).mo10268get();
    }

    @Provides
    public WifiManager.MulticastLock providesMulticastLock(WifiManager wifiManager) {
        WifiManager.MulticastLock createMulticastLock = wifiManager.createMulticastLock(DnssdModule.class.getSimpleName());
        createMulticastLock.setReferenceCounted(true);
        return createMulticastLock;
    }

    @Provides
    public NsdManager providesNsdManager() {
        return (NsdManager) this.context.getSystemService("servicediscovery");
    }

    @Provides
    public WifiManager providesWifiManager() {
        return (WifiManager) this.context.getSystemService("wifi");
    }
}
