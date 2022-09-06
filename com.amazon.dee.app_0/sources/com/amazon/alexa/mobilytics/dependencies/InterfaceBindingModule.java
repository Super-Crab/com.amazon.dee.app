package com.amazon.alexa.mobilytics.dependencies;

import com.amazon.alexa.mobilytics.DefaultMobilytics;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration;
import com.amazon.alexa.mobilytics.configuration.ConfigPuller;
import com.amazon.alexa.mobilytics.configuration.ConfigStorage;
import com.amazon.alexa.mobilytics.configuration.DefaultApplicationConfiguration;
import com.amazon.alexa.mobilytics.configuration.DefaultDeviceConfiguration;
import com.amazon.alexa.mobilytics.configuration.DefaultEndpointManager;
import com.amazon.alexa.mobilytics.configuration.DefaultRecordChecker;
import com.amazon.alexa.mobilytics.configuration.DeviceConfiguration;
import com.amazon.alexa.mobilytics.configuration.EndpointManager;
import com.amazon.alexa.mobilytics.configuration.PersistentConfigStorage;
import com.amazon.alexa.mobilytics.configuration.RecordChecker;
import com.amazon.alexa.mobilytics.configuration.S3ConfigPuller;
import com.amazon.alexa.mobilytics.connector.ConnectorExecutor;
import com.amazon.alexa.mobilytics.connector.DefaultConnectorExecutor;
import com.amazon.alexa.mobilytics.event.serializer.DefaultEventSerializer;
import com.amazon.alexa.mobilytics.event.serializer.DefaultProtobufSerializer;
import com.amazon.alexa.mobilytics.event.serializer.EventSerializer;
import com.amazon.alexa.mobilytics.event.serializer.ProtobufSerializer;
import com.amazon.alexa.mobilytics.executor.DefaultExecutor;
import com.amazon.alexa.mobilytics.executor.Executor;
import com.amazon.alexa.mobilytics.session.DefaultSessionStorage;
import com.amazon.alexa.mobilytics.session.SessionStorage;
import com.amazon.alexa.mobilytics.storage.PersistentStorage;
import com.amazon.alexa.mobilytics.storage.PreferencesStorage;
import dagger.Binds;
import dagger.Module;
@Module
/* loaded from: classes9.dex */
public abstract class InterfaceBindingModule {
    @Binds
    public abstract ApplicationConfiguration provideApplicationConfiguration(DefaultApplicationConfiguration defaultApplicationConfiguration);

    @Binds
    public abstract ConfigPuller provideConfigPuller(S3ConfigPuller s3ConfigPuller);

    @Binds
    public abstract ConfigStorage provideConfigStorage(PersistentConfigStorage persistentConfigStorage);

    @Binds
    public abstract ConnectorExecutor.Factory provideConnectorExecutorFactory(DefaultConnectorExecutor.Factory factory);

    @Binds
    public abstract DeviceConfiguration provideDeviceConfiguration(DefaultDeviceConfiguration defaultDeviceConfiguration);

    @Binds
    public abstract Executor provideExecutor(DefaultExecutor defaultExecutor);

    @Binds
    public abstract EndpointManager provideKinesisEndpointManager(DefaultEndpointManager defaultEndpointManager);

    @Binds
    public abstract Mobilytics provideMobilytics(DefaultMobilytics defaultMobilytics);

    @Binds
    public abstract EventSerializer provideMobilyticsEventSerialzer(DefaultEventSerializer defaultEventSerializer);

    @Binds
    public abstract ProtobufSerializer provideMobilyticsProtobufSerialzer(DefaultProtobufSerializer defaultProtobufSerializer);

    @Binds
    public abstract PersistentStorage.Factory providePersistentStorageFactory(PreferencesStorage.Factory factory);

    @Binds
    public abstract RecordChecker provideRecordChecker(DefaultRecordChecker defaultRecordChecker);

    @Binds
    public abstract SessionStorage provideSessionStorage(DefaultSessionStorage defaultSessionStorage);
}
