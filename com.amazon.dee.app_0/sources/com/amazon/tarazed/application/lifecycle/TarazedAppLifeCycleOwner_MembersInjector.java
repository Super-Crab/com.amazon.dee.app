package com.amazon.tarazed.application.lifecycle;

import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class TarazedAppLifeCycleOwner_MembersInjector implements MembersInjector<TarazedAppLifeCycleOwner> {
    private final Provider<TarazedAppLifeCycleObserver> p0Provider;
    private final Provider<DeviceInfoUtility> p0Provider2;

    public TarazedAppLifeCycleOwner_MembersInjector(Provider<TarazedAppLifeCycleObserver> provider, Provider<DeviceInfoUtility> provider2) {
        this.p0Provider = provider;
        this.p0Provider2 = provider2;
    }

    public static MembersInjector<TarazedAppLifeCycleOwner> create(Provider<TarazedAppLifeCycleObserver> provider, Provider<DeviceInfoUtility> provider2) {
        return new TarazedAppLifeCycleOwner_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TarazedAppLifeCycleOwner tarazedAppLifeCycleOwner) {
        tarazedAppLifeCycleOwner.setObserver$TarazedAndroidLibrary_release(this.p0Provider.mo10268get());
        tarazedAppLifeCycleOwner.setDeviceInfo$TarazedAndroidLibrary_release(this.p0Provider2.mo10268get());
    }
}
