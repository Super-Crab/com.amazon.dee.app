package com.amazon.alexa.handsfree.protocols.sync;

import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class DataSyncService_MembersInjector implements MembersInjector<DataSyncService> {
    private final Provider<DataSyncIncomingMessageHandler> mDataSyncIncomingMessageHandlerProvider;

    public DataSyncService_MembersInjector(Provider<DataSyncIncomingMessageHandler> provider) {
        this.mDataSyncIncomingMessageHandlerProvider = provider;
    }

    public static MembersInjector<DataSyncService> create(Provider<DataSyncIncomingMessageHandler> provider) {
        return new DataSyncService_MembersInjector(provider);
    }

    public static void injectMDataSyncIncomingMessageHandler(DataSyncService dataSyncService, DataSyncIncomingMessageHandler dataSyncIncomingMessageHandler) {
        dataSyncService.mDataSyncIncomingMessageHandler = dataSyncIncomingMessageHandler;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DataSyncService dataSyncService) {
        injectMDataSyncIncomingMessageHandler(dataSyncService, this.mDataSyncIncomingMessageHandlerProvider.mo10268get());
    }
}
