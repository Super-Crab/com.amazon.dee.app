package com.amazon.alexa.biloba.dependency;

import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.service.PasscodeApi;
import com.amazon.alexa.biloba.storage.IdentityLocalDataStore;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.dee.app.http.CoralService;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes6.dex */
public final class PersonIdentityModule {
    private PersonIdentityModule() {
    }

    @Provides
    public static IdentityLocalDataStore provideIdentityLocalDataStore(@NonNull PersistentStorage.Factory factory, @NonNull IdentityService identityService) {
        return new IdentityLocalDataStore(factory, identityService);
    }

    @Provides
    public static PasscodeApi providePasscodeApi(@NonNull CoralService coralService, @NonNull SchedulerProvider schedulerProvider) {
        return new PasscodeApi(coralService, schedulerProvider);
    }
}
