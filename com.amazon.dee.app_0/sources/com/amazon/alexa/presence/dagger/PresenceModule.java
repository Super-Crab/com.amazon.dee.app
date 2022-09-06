package com.amazon.alexa.presence.dagger;

import android.app.AlarmManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.PowerManager;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.PresenceApplicationLifecycleObserver;
import com.amazon.alexa.presence.PresenceController;
import com.amazon.alexa.presence.PresenceSubComponents;
import com.amazon.alexa.presence.alarm.PresenceAlarmManager;
import com.amazon.alexa.presence.battery.BatteryOptimization;
import com.amazon.alexa.presence.bleconn.identity.clients.RoamingClient;
import com.amazon.alexa.presence.detection.BLEScannerCallback;
import com.amazon.alexa.presence.detection.BeaconFormatLogic;
import com.amazon.alexa.presence.detection.BlePacketConsumer;
import com.amazon.alexa.presence.eventbus.BatteryOptimizationSubscriber;
import com.amazon.alexa.presence.eventbus.EventBusHelper;
import com.amazon.alexa.presence.eventbus.EventMessageFilter;
import com.amazon.alexa.presence.eventbus.PresenceSubscriber;
import com.amazon.alexa.presence.eventbus.PushNotificationSubscriber;
import com.amazon.alexa.presence.identity.PresenceAccessTokenProvider;
import com.amazon.alexa.presence.library.storage.PersistentLocalStorage;
import com.amazon.alexa.presence.receiver.AlexaPresenceBluetoothReceiver;
import com.amazon.alexa.presence.receiver.ScanCheckAlarmReceiver;
import com.amazon.alexa.presence.reporter.HttpAsyncTaskInstanceFactory;
import com.amazon.alexa.presence.reporter.HttpAsyncTaskInstanceProvider;
import com.amazon.alexa.presence.reporter.PresenceBeaconResolverClient;
import com.amazon.alexa.presence.retry.PresenceRetryStrategy;
import com.amazon.alexa.presence.service.PresenceForegroundServiceStateAdviser;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.dee.app.BuildConfig;
import com.dee.app.metrics.MetricsServiceV2;
import com.google.common.base.Optional;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
@Module
/* loaded from: classes9.dex */
public class PresenceModule {
    private static final String TAG = Presence.tag();
    private Context context;

    public PresenceModule(Context context) {
        this.context = context;
    }

    @Provides
    public static PresenceController presenceController(RoamingClient roamingClient, PresenceSubComponents presenceSubComponents, PersistentLocalStorage.PresenceDataStore presenceDataStore, PresenceForegroundServiceStateAdviser presenceForegroundServiceStateAdviser, MetricsServiceV2 metricsServiceV2) {
        return new PresenceController(roamingClient, presenceSubComponents, presenceDataStore, presenceForegroundServiceStateAdviser, metricsServiceV2);
    }

    @Provides
    public static ComponentRegistry provideApplicationComponentRegistry() {
        return ComponentRegistry.getInstance();
    }

    @Provides
    public static ApplicationLifecycleService provideApplicationLifecycleService(ComponentRegistry componentRegistry) {
        ApplicationLifecycleService applicationLifecycleService = (ApplicationLifecycleService) componentRegistry.get(ApplicationLifecycleService.class).orNull();
        if (applicationLifecycleService != null) {
            return applicationLifecycleService;
        }
        throw new RuntimeException("Null ApplicationLifecycleService instance obtained from ComponentRegistry");
    }

    @Provides
    public static DeviceInformation provideDeviceInformation(ComponentRegistry componentRegistry) {
        DeviceInformation deviceInformation = (DeviceInformation) componentRegistry.get(DeviceInformation.class).orNull();
        if (deviceInformation != null) {
            return deviceInformation;
        }
        throw new RuntimeException("Null DeviceInformation instance obtained from ComponentRegistry");
    }

    @Provides
    public static EventBus provideEventBus(ComponentRegistry componentRegistry) {
        EventBus eventBus = (EventBus) componentRegistry.get(EventBus.class).orNull();
        if (eventBus != null) {
            return eventBus;
        }
        throw new RuntimeException("Null EventBus instance obtained from ComponentRegistry");
    }

    @Provides
    public static IdentityService provideIdentityService(ComponentRegistry componentRegistry) {
        IdentityService identityService = (IdentityService) componentRegistry.get(IdentityService.class).orNull();
        if (identityService != null) {
            return identityService;
        }
        throw new RuntimeException("Null IdentityService instance obtained from ComponentRegistry");
    }

    @Provides
    public static MetricsServiceV2 provideMetricsServiceV2(ComponentRegistry componentRegistry) {
        MetricsServiceV2 metricsServiceV2 = (MetricsServiceV2) componentRegistry.get(MetricsServiceV2.class).orNull();
        if (metricsServiceV2 != null) {
            return metricsServiceV2;
        }
        throw new RuntimeException("Null MetricsServiceV2 instance obtained from ComponentRegistry");
    }

    @Provides
    public static PersonIdProvider providePersonId(ComponentRegistry componentRegistry) {
        PersonIdProvider personIdProvider = (PersonIdProvider) componentRegistry.get(PersonIdProvider.class).orNull();
        if (personIdProvider != null) {
            return personIdProvider;
        }
        throw new RuntimeException("Null PersonIdProvider instance obtained from ComponentRegistry");
    }

    @Provides
    public static RoamingClient roamingClient() {
        return new RoamingClient(new RoamingClient.Config((String) ComponentRegistry.getInstance().get(EnvironmentService.class).transform($$Lambda$US9u04j9t2BUXN0h7nnYJrve0Fs.INSTANCE).or((Optional) BuildConfig.HOST)), new OkHttpClient());
    }

    @Provides
    public PersistentLocalStorage.PresenceDataStore presenceDataStore() {
        return PersistentLocalStorage.getWrapper();
    }

    @Provides
    @Singleton
    public PresenceForegroundServiceStateAdviser presenceForegroundServiceStateAdviser() {
        return new PresenceForegroundServiceStateAdviser(this.context);
    }

    @Provides
    public AlarmManager provideAlarmManager() {
        return (AlarmManager) this.context.getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    @Provides
    @Singleton
    public AlexaPresenceBluetoothReceiver provideAlexaPresenceBluetoothReceiver(MetricsServiceV2 metricsServiceV2, PresenceController presenceController) {
        return new AlexaPresenceBluetoothReceiver(metricsServiceV2, presenceController);
    }

    @Provides
    public BatteryOptimization provideBatteryOptimization(PowerManager powerManager, MetricsServiceV2 metricsServiceV2) {
        return new BatteryOptimization(this.context, powerManager, metricsServiceV2);
    }

    @Provides
    @Named("BatteryOptimizationSubscriberFilter")
    public EventMessageFilter provideBatteryOptimizationEventMessageFilter() {
        return new EventMessageFilter(BatteryOptimizationSubscriber.EVENT_FILTER);
    }

    @Provides
    @Singleton
    public BatteryOptimizationSubscriber provideBatteryOptimizationSubscriber(EventBus eventBus, BatteryOptimization batteryOptimization, @Named("BatteryOptimizationSubscriberFilter") EventMessageFilter eventMessageFilter, MetricsServiceV2 metricsServiceV2) {
        return new BatteryOptimizationSubscriber(eventBus, batteryOptimization, eventMessageFilter, metricsServiceV2);
    }

    @Provides
    public BeaconFormatLogic provideBeaconFormatLogic(SimpleDateFormat simpleDateFormat) {
        return new BeaconFormatLogic(simpleDateFormat);
    }

    @Provides
    @Singleton
    public BLEScannerCallback provideBleScannerCallback(BlePacketConsumer blePacketConsumer) {
        return new BLEScannerCallback.Builder().withConsumer(blePacketConsumer).build();
    }

    @Provides
    @Singleton
    public PresenceSubComponents provideComponentInitialization(Lazy<ApplicationLifecycleService> lazy, Lazy<PresenceApplicationLifecycleObserver> lazy2, Lazy<AlexaPresenceBluetoothReceiver> lazy3, Lazy<PresenceAlarmManager> lazy4, Lazy<ScanCheckAlarmReceiver> lazy5) {
        return new PresenceSubComponents(lazy, lazy2, lazy3, lazy4, lazy5, this.context);
    }

    @Provides
    public Context provideContext() {
        return this.context;
    }

    @Provides
    public EventBusHelper provideEventBusHelper(EventBus eventBus, PresenceSubscriber presenceSubscriber, BatteryOptimizationSubscriber batteryOptimizationSubscriber, PushNotificationSubscriber pushNotificationSubscriber) {
        return new EventBusHelper(eventBus, presenceSubscriber, batteryOptimizationSubscriber, pushNotificationSubscriber);
    }

    @Provides
    public HttpAsyncTaskInstanceFactory provideHttpAsyncTaskInstanceFactory(PresenceAccessTokenProvider presenceAccessTokenProvider, PresenceRetryStrategy presenceRetryStrategy, MetricsServiceV2 metricsServiceV2) {
        return new HttpAsyncTaskInstanceProvider(new OkHttpClient(), presenceAccessTokenProvider, presenceRetryStrategy, metricsServiceV2);
    }

    @Provides
    @Singleton
    public BlePacketConsumer providePostDetectionLogic(MetricsServiceV2 metricsServiceV2, BeaconFormatLogic beaconFormatLogic, PresenceBeaconResolverClient presenceBeaconResolverClient) {
        return new BlePacketConsumer(metricsServiceV2, beaconFormatLogic, presenceBeaconResolverClient);
    }

    @Provides
    public PowerManager providePowerManager() {
        return (PowerManager) this.context.getSystemService("power");
    }

    @Provides
    public PresenceAccessTokenProvider providePresenceAccessTokenProvider() {
        return new PresenceAccessTokenProvider();
    }

    @Provides
    public PresenceAlarmManager providePresenceAlarmManager(AlarmManager alarmManager) {
        return new PresenceAlarmManager(this.context, alarmManager);
    }

    @Provides
    public PresenceApplicationLifecycleObserver providePresenceApplicationLifecycleObserver(MetricsServiceV2 metricsServiceV2, PresenceController presenceController) {
        return new PresenceApplicationLifecycleObserver(metricsServiceV2, this.context, presenceController);
    }

    @Provides
    @Named("PresenceSubscriberFilter")
    public EventMessageFilter providePresenceEventMessageFilter() {
        return new EventMessageFilter(PresenceSubscriber.EVENT_FILTER);
    }

    @Provides
    public PresenceRetryStrategy providePresenceRetryStrategy() {
        return new PresenceRetryStrategy();
    }

    @Provides
    @Singleton
    public PresenceSubscriber providePresenceSubscriber(@Named("PresenceSubscriberFilter") EventMessageFilter eventMessageFilter, MetricsServiceV2 metricsServiceV2, PresenceController presenceController) {
        return new PresenceSubscriber(this.context, eventMessageFilter, presenceController, metricsServiceV2);
    }

    @Provides
    @Named("PushNotificationSubscriberFilter")
    public EventMessageFilter providePushNotificationEventMessageFilter() {
        return new EventMessageFilter(PushNotificationSubscriber.EVENT_FILTER);
    }

    @Provides
    @Singleton
    public PushNotificationSubscriber providePushNotificationSubscriber(@Named("PushNotificationSubscriberFilter") EventMessageFilter eventMessageFilter, MetricsServiceV2 metricsServiceV2, PresenceController presenceController) {
        return new PushNotificationSubscriber(this.context, eventMessageFilter, metricsServiceV2, presenceController);
    }

    @Provides
    @Singleton
    public ScanCheckAlarmReceiver provideScanCheckAlarmReceiver(MetricsServiceV2 metricsServiceV2, PresenceController presenceController) {
        return new ScanCheckAlarmReceiver(metricsServiceV2, presenceController);
    }

    @Provides
    public SimpleDateFormat provideSimpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    }

    @Provides
    @Nullable
    public BluetoothAdapter providesBluetoothAdapter(Context context) {
        return ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter();
    }
}
