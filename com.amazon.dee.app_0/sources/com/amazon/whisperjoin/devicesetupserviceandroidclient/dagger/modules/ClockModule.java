package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules;

import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.Clock;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes13.dex */
public class ClockModule {
    @Provides
    public Clock providesClock() {
        return new Clock();
    }
}
