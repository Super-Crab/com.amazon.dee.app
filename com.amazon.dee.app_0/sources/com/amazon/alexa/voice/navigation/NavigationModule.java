package com.amazon.alexa.voice.navigation;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class NavigationModule {
    private NavigationModule() {
    }

    @Provides
    @Singleton
    public static PreferredNavigationAppContentAccessor providePreferredNavigationAppContentAccessor(Context context) {
        return new PreferredNavigationAppContentAccessor(context);
    }

    @Provides
    @Singleton
    public static PreferredNavigationAppRepository providePreferredNavigationAppRepository(Context context, PreferredNavigationAppContentAccessor preferredNavigationAppContentAccessor) {
        return new PreferredNavigationAppRepository(context.getPackageManager(), preferredNavigationAppContentAccessor);
    }
}
