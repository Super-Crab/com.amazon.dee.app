package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes13.dex */
public class MapAuthModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public MapAuthenticationProvider providesMapProvider(Context context) {
        return new MapAuthenticationProvider.DefaultMapAuthAccountProvider(context);
    }
}
