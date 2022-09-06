package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.PresenceLifecycleManager;
import com.amazon.alexa.presence.receiver.BeaconReceiver;
import com.amazon.alexa.presence.service.AlexaBeaconDetectorService;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {PresenceModule.class})
@Singleton
/* loaded from: classes9.dex */
public interface PresenceComponent {
    void inject(PresenceLifecycleManager presenceLifecycleManager);

    void inject(BeaconReceiver beaconReceiver);

    void inject(AlexaBeaconDetectorService alexaBeaconDetectorService);
}
