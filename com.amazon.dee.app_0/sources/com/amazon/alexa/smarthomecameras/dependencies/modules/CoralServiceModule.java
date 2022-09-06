package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public abstract class CoralServiceModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static CoralService provideCoralService() {
        return (CoralService) GeneratedOutlineSupport1.outline20(CoralService.class);
    }
}
