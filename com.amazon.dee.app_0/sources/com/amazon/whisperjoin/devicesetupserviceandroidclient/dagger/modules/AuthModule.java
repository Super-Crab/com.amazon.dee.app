package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import android.content.Context;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.identity.MapAccessTokenProvider;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes13.dex */
public class AuthModule {
    @Provides
    public MapAccessTokenProvider providesAccessTokenProvider(MAPAccountManager mAPAccountManager, TokenManagement tokenManagement, String str) {
        return new MapAccessTokenProvider(mAPAccountManager, tokenManagement, str);
    }

    @Provides
    public MAPAccountManager providesMapAccountManager(Context context) {
        return new MAPAccountManager(context);
    }

    @Provides
    public String providesTokenKeys(Context context) {
        return TokenKeys.getAccessTokenKeyForPackage(context.getPackageName());
    }

    @Provides
    public TokenManagement providesTokenManagement(Context context) {
        return new TokenManagement(context);
    }
}
