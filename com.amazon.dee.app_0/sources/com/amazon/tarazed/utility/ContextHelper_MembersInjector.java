package com.amazon.tarazed.utility;

import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ContextHelper_MembersInjector implements MembersInjector<ContextHelper> {
    private final Provider<DeviceInfoUtility> p0Provider;

    public ContextHelper_MembersInjector(Provider<DeviceInfoUtility> provider) {
        this.p0Provider = provider;
    }

    public static MembersInjector<ContextHelper> create(Provider<DeviceInfoUtility> provider) {
        return new ContextHelper_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ContextHelper contextHelper) {
        contextHelper.setDeviceInfoUtility$TarazedAndroidLibrary_release(this.p0Provider.mo10268get());
    }
}
