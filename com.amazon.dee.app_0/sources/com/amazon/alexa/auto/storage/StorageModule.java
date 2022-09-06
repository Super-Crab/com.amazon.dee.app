package com.amazon.alexa.auto.storage;

import android.content.Context;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public class StorageModule {
    public static final String NAVIGATION_STORE = "navigation_store";

    @Provides
    @Singleton
    @Named(NAVIGATION_STORE)
    public PersistentStorage provideNavigationPreferenceStorage(Context context) {
        return new SharedPreferencesStorage(context.getSharedPreferences(NAVIGATION_STORE, 0));
    }
}
