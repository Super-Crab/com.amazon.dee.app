package com.amazon.deecomms.core;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.deecomms.api.CommsDelegateBase;
import com.google.common.annotations.VisibleForTesting;
import dagger.Lazy;
/* loaded from: classes12.dex */
public final class CommsDaggerWrapper {
    private static CommsComponent libraryComponent;

    private CommsDaggerWrapper() {
    }

    public static CommsComponent getComponent() {
        return libraryComponent;
    }

    public static CommsComponent initialize(@NonNull Lazy<Context> lazy, @NonNull Lazy<IdentityService> lazy2, @NonNull Lazy<CommsDelegateBase> lazy3, @NonNull String str, @NonNull Lazy<DeviceInformation> lazy4) {
        libraryComponent = DaggerCommsComponent.builder().applicationModule(new ApplicationModule(lazy, lazy2, lazy3, str, lazy4)).build();
        return libraryComponent;
    }

    @VisibleForTesting
    public static CommsComponent initialize(Context context, CommsDelegateBase commsDelegateBase) {
        libraryComponent = DaggerCommsComponent.builder().applicationModule(new ApplicationModule(context, commsDelegateBase)).build();
        return libraryComponent;
    }

    public static CommsComponent initialize(ApplicationModule applicationModule, LibraryModule libraryModule, SipModule sipModule) {
        libraryComponent = DaggerCommsComponent.builder().applicationModule(applicationModule).libraryModule(libraryModule).sipModule(sipModule).build();
        return libraryComponent;
    }

    public static CommsComponent initialize(ApplicationModule applicationModule, LibraryModule libraryModule, SipModule sipModule, TelecomModule telecomModule, AndroidModule androidModule) {
        libraryComponent = DaggerCommsComponent.builder().applicationModule(applicationModule).libraryModule(libraryModule).sipModule(sipModule).telecomModule(telecomModule).androidModule(androidModule).build();
        return libraryComponent;
    }

    public static CommsComponent initialize(ApplicationModule applicationModule, LibraryModule libraryModule, SipModule sipModule, AndroidModule androidModule, AlexaModule alexaModule) {
        libraryComponent = DaggerCommsComponent.builder().applicationModule(applicationModule).libraryModule(libraryModule).sipModule(sipModule).androidModule(androidModule).alexaModule(alexaModule).build();
        return libraryComponent;
    }

    public static CommsComponent initialize(ApplicationModule applicationModule, LibraryModule libraryModule, SipModule sipModule, AndroidModule androidModule, AlexaModule alexaModule, TelecomModule telecomModule, AudioModule audioModule) {
        libraryComponent = DaggerCommsComponent.builder().applicationModule(applicationModule).libraryModule(libraryModule).sipModule(sipModule).androidModule(androidModule).alexaModule(alexaModule).telecomModule(telecomModule).audioModule(audioModule).build();
        return libraryComponent;
    }

    public static CommsComponent initialize(ApplicationModule applicationModule, LibraryModule libraryModule, SipModule sipModule, AndroidModule androidModule) {
        libraryComponent = DaggerCommsComponent.builder().applicationModule(applicationModule).libraryModule(libraryModule).sipModule(sipModule).androidModule(androidModule).build();
        return libraryComponent;
    }
}
