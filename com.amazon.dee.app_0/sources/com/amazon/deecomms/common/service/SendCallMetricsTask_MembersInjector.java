package com.amazon.deecomms.common.service;

import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class SendCallMetricsTask_MembersInjector implements MembersInjector<SendCallMetricsTask> {
    private final Provider<CallHistoryHelper> callHistoryHelperProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;

    public SendCallMetricsTask_MembersInjector(Provider<CommsIdentityManager> provider, Provider<CallHistoryHelper> provider2) {
        this.commsIdentityManagerProvider = provider;
        this.callHistoryHelperProvider = provider2;
    }

    public static MembersInjector<SendCallMetricsTask> create(Provider<CommsIdentityManager> provider, Provider<CallHistoryHelper> provider2) {
        return new SendCallMetricsTask_MembersInjector(provider, provider2);
    }

    public static void injectCallHistoryHelper(SendCallMetricsTask sendCallMetricsTask, CallHistoryHelper callHistoryHelper) {
        sendCallMetricsTask.callHistoryHelper = callHistoryHelper;
    }

    public static void injectCommsIdentityManager(SendCallMetricsTask sendCallMetricsTask, CommsIdentityManager commsIdentityManager) {
        sendCallMetricsTask.commsIdentityManager = commsIdentityManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SendCallMetricsTask sendCallMetricsTask) {
        sendCallMetricsTask.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        sendCallMetricsTask.callHistoryHelper = this.callHistoryHelperProvider.mo10268get();
    }
}
