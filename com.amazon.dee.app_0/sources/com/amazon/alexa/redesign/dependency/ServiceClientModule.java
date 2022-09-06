package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.redesign.client.HomeFeedServiceClient;
import com.dee.app.http.CoralService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public final class ServiceClientModule {
    @Provides
    @Singleton
    public HomeFeedServiceClient provideHomeFeedServiceClient(CoralService coralService) {
        return new HomeFeedServiceClient(coralService);
    }
}
