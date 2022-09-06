package com.amazon.alexa.mobilytics.connector;

import com.amazon.alexa.mobilytics.auth.CognitoCredentialsProvider;
import com.amazon.alexa.mobilytics.configuration.RecordChecker;
import com.amazon.alexa.mobilytics.connector.DefaultKinesisConnector;
import com.amazon.alexa.mobilytics.event.serializer.EventSerializer;
import com.amazon.alexa.mobilytics.event.serializer.ProtobufSerializer;
import com.amazon.alexa.mobilytics.recorder.KinesisEventRecorder;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DefaultKinesisConnector_Factory_Factory implements Factory<DefaultKinesisConnector.Factory> {
    private final Provider<CognitoCredentialsProvider.Factory> credentialsProviderFactoryProvider;
    private final Provider<RecordChecker> defaultRecordCheckerProvider;
    private final Provider<KinesisEventRecorder.Factory> eventRecorderFactoryProvider;
    private final Provider<String> installationIdProvider;
    private final Provider<ProtobufSerializer> protobufSerializerProvider;
    private final Provider<EventSerializer> serializerProvider;

    public DefaultKinesisConnector_Factory_Factory(Provider<RecordChecker> provider, Provider<EventSerializer> provider2, Provider<ProtobufSerializer> provider3, Provider<String> provider4, Provider<KinesisEventRecorder.Factory> provider5, Provider<CognitoCredentialsProvider.Factory> provider6) {
        this.defaultRecordCheckerProvider = provider;
        this.serializerProvider = provider2;
        this.protobufSerializerProvider = provider3;
        this.installationIdProvider = provider4;
        this.eventRecorderFactoryProvider = provider5;
        this.credentialsProviderFactoryProvider = provider6;
    }

    public static DefaultKinesisConnector_Factory_Factory create(Provider<RecordChecker> provider, Provider<EventSerializer> provider2, Provider<ProtobufSerializer> provider3, Provider<String> provider4, Provider<KinesisEventRecorder.Factory> provider5, Provider<CognitoCredentialsProvider.Factory> provider6) {
        return new DefaultKinesisConnector_Factory_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static DefaultKinesisConnector.Factory newFactory(RecordChecker recordChecker, EventSerializer eventSerializer, ProtobufSerializer protobufSerializer, String str, KinesisEventRecorder.Factory factory, CognitoCredentialsProvider.Factory factory2) {
        return new DefaultKinesisConnector.Factory(recordChecker, eventSerializer, protobufSerializer, str, factory, factory2);
    }

    public static DefaultKinesisConnector.Factory provideInstance(Provider<RecordChecker> provider, Provider<EventSerializer> provider2, Provider<ProtobufSerializer> provider3, Provider<String> provider4, Provider<KinesisEventRecorder.Factory> provider5, Provider<CognitoCredentialsProvider.Factory> provider6) {
        return new DefaultKinesisConnector.Factory(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultKinesisConnector.Factory mo10268get() {
        return provideInstance(this.defaultRecordCheckerProvider, this.serializerProvider, this.protobufSerializerProvider, this.installationIdProvider, this.eventRecorderFactoryProvider, this.credentialsProviderFactoryProvider);
    }
}
