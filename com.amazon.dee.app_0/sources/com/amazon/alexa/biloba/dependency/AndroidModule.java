package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.utils.PermissionUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public class AndroidModule {
    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    public PermissionUtils providePermissionUtils() {
        return new PermissionUtils();
    }
}
