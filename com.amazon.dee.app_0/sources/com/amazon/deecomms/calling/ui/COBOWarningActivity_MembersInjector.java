package com.amazon.deecomms.calling.ui;

import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class COBOWarningActivity_MembersInjector implements MembersInjector<COBOWarningActivity> {
    private final Provider<InitiationLogicFactory> initiationLogicFactoryProvider;

    public COBOWarningActivity_MembersInjector(Provider<InitiationLogicFactory> provider) {
        this.initiationLogicFactoryProvider = provider;
    }

    public static MembersInjector<COBOWarningActivity> create(Provider<InitiationLogicFactory> provider) {
        return new COBOWarningActivity_MembersInjector(provider);
    }

    public static void injectInitiationLogicFactory(COBOWarningActivity cOBOWarningActivity, InitiationLogicFactory initiationLogicFactory) {
        cOBOWarningActivity.initiationLogicFactory = initiationLogicFactory;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(COBOWarningActivity cOBOWarningActivity) {
        cOBOWarningActivity.initiationLogicFactory = this.initiationLogicFactoryProvider.mo10268get();
    }
}
