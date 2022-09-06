package com.amazon.alexa.voice.handsfree.dependencies;

import com.amazon.alexa.handsfree.protocols.sync.DataSyncIncomingMessageHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes11.dex */
public final class DataSyncModule_ProvideDataSyncIncomingMessageHandlerFactory implements Factory<DataSyncIncomingMessageHandler> {
    private final DataSyncModule module;

    public DataSyncModule_ProvideDataSyncIncomingMessageHandlerFactory(DataSyncModule dataSyncModule) {
        this.module = dataSyncModule;
    }

    public static DataSyncModule_ProvideDataSyncIncomingMessageHandlerFactory create(DataSyncModule dataSyncModule) {
        return new DataSyncModule_ProvideDataSyncIncomingMessageHandlerFactory(dataSyncModule);
    }

    public static DataSyncIncomingMessageHandler provideInstance(DataSyncModule dataSyncModule) {
        return proxyProvideDataSyncIncomingMessageHandler(dataSyncModule);
    }

    public static DataSyncIncomingMessageHandler proxyProvideDataSyncIncomingMessageHandler(DataSyncModule dataSyncModule) {
        return (DataSyncIncomingMessageHandler) Preconditions.checkNotNull(dataSyncModule.provideDataSyncIncomingMessageHandler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DataSyncIncomingMessageHandler mo10268get() {
        return provideInstance(this.module);
    }
}
