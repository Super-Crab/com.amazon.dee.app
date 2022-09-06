package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import android.content.Context;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.SharedPreferencesProvider;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes13.dex */
public class SharedPreferencesModule {
    @Provides
    public SharedPreferencesProvider provideSharedPreferencesProvider(Context context) {
        return new SharedPreferencesProvider(context);
    }
}
