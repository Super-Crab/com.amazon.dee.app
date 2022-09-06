package com.amazon.alexa.presence.service;

import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class AlexaBeaconDetectorService_MembersInjector implements MembersInjector<AlexaBeaconDetectorService> {
    private final Provider<PresenceIntentHandler> mHandlerProvider;

    public AlexaBeaconDetectorService_MembersInjector(Provider<PresenceIntentHandler> provider) {
        this.mHandlerProvider = provider;
    }

    public static MembersInjector<AlexaBeaconDetectorService> create(Provider<PresenceIntentHandler> provider) {
        return new AlexaBeaconDetectorService_MembersInjector(provider);
    }

    public static void injectMHandler(AlexaBeaconDetectorService alexaBeaconDetectorService, PresenceIntentHandler presenceIntentHandler) {
        alexaBeaconDetectorService.mHandler = presenceIntentHandler;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlexaBeaconDetectorService alexaBeaconDetectorService) {
        injectMHandler(alexaBeaconDetectorService, this.mHandlerProvider.mo10268get());
    }
}
