package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.components;

import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.AuthModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.ClockModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.ContextModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.NetworkingModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.RxModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.SharedPreferencesModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.scopes.NetworkingScope;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl;
import dagger.Component;
@Component(modules = {ContextModule.class, AuthModule.class, NetworkingModule.class, RxModule.class, ClockModule.class, SharedPreferencesModule.class})
@NetworkingScope
/* loaded from: classes13.dex */
public interface DSSComponent {
    void inject(DSSClientImpl dSSClientImpl);
}
