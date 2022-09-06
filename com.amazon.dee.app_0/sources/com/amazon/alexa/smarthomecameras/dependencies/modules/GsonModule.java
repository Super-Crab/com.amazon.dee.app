package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public abstract class GsonModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static Gson providesGson() {
        return new Gson();
    }
}
