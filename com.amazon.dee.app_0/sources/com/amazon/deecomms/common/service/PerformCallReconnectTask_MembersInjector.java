package com.amazon.deecomms.common.service;

import com.amazon.deecomms.calling.controller.CommandProcessor;
import com.amazon.deecomms.common.sip.SipClientState;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class PerformCallReconnectTask_MembersInjector implements MembersInjector<PerformCallReconnectTask> {
    private final Provider<SipClientState> clientStateProvider;
    private final Provider<CommandProcessor> commandProcessorProvider;

    public PerformCallReconnectTask_MembersInjector(Provider<SipClientState> provider, Provider<CommandProcessor> provider2) {
        this.clientStateProvider = provider;
        this.commandProcessorProvider = provider2;
    }

    public static MembersInjector<PerformCallReconnectTask> create(Provider<SipClientState> provider, Provider<CommandProcessor> provider2) {
        return new PerformCallReconnectTask_MembersInjector(provider, provider2);
    }

    public static void injectClientState(PerformCallReconnectTask performCallReconnectTask, SipClientState sipClientState) {
        performCallReconnectTask.clientState = sipClientState;
    }

    public static void injectCommandProcessor(PerformCallReconnectTask performCallReconnectTask, CommandProcessor commandProcessor) {
        performCallReconnectTask.commandProcessor = commandProcessor;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(PerformCallReconnectTask performCallReconnectTask) {
        performCallReconnectTask.clientState = this.clientStateProvider.mo10268get();
        performCallReconnectTask.commandProcessor = this.commandProcessorProvider.mo10268get();
    }
}
