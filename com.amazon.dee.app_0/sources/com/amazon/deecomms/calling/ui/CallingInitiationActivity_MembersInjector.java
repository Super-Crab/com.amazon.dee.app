package com.amazon.deecomms.calling.ui;

import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CallingInitiationActivity_MembersInjector implements MembersInjector<CallingInitiationActivity> {
    private final Provider<CommsNotificationManager> commsNotificationManagerProvider;
    private final Provider<InitiationLogicFactory> initiationLogicFactoryProvider;

    public CallingInitiationActivity_MembersInjector(Provider<InitiationLogicFactory> provider, Provider<CommsNotificationManager> provider2) {
        this.initiationLogicFactoryProvider = provider;
        this.commsNotificationManagerProvider = provider2;
    }

    public static MembersInjector<CallingInitiationActivity> create(Provider<InitiationLogicFactory> provider, Provider<CommsNotificationManager> provider2) {
        return new CallingInitiationActivity_MembersInjector(provider, provider2);
    }

    public static void injectCommsNotificationManager(CallingInitiationActivity callingInitiationActivity, CommsNotificationManager commsNotificationManager) {
        callingInitiationActivity.commsNotificationManager = commsNotificationManager;
    }

    public static void injectInitiationLogicFactory(CallingInitiationActivity callingInitiationActivity, InitiationLogicFactory initiationLogicFactory) {
        callingInitiationActivity.initiationLogicFactory = initiationLogicFactory;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CallingInitiationActivity callingInitiationActivity) {
        callingInitiationActivity.initiationLogicFactory = this.initiationLogicFactoryProvider.mo10268get();
        callingInitiationActivity.commsNotificationManager = this.commsNotificationManagerProvider.mo10268get();
    }
}
