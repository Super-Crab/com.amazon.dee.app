package com.amazon.alexa.alertsca.dependencies;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public class DataModule {
    public static final String CACHE_DIRECTORY = "CACHE_DIRECTORY";

    @Provides
    @Singleton
    @Named(CACHE_DIRECTORY)
    public File providesCacheDirectoryFile(Context context) {
        return context.getCacheDir();
    }
}
