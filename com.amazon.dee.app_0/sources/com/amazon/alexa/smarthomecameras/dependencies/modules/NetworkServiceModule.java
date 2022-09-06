package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.protocols.network.NetworkService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public class NetworkServiceModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static NetworkService provideNetworkService() {
        return (NetworkService) GeneratedOutlineSupport1.outline20(NetworkService.class);
    }
}
