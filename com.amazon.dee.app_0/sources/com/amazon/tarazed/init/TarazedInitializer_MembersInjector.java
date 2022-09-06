package com.amazon.tarazed.init;

import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class TarazedInitializer_MembersInjector implements MembersInjector<TarazedInitializer> {
    private final Provider<TarazedLogger> p0Provider;
    private final Provider<ActivityTracker> p0Provider2;
    private final Provider<DeviceInfoUtilityAndroid> p0Provider3;

    public TarazedInitializer_MembersInjector(Provider<TarazedLogger> provider, Provider<ActivityTracker> provider2, Provider<DeviceInfoUtilityAndroid> provider3) {
        this.p0Provider = provider;
        this.p0Provider2 = provider2;
        this.p0Provider3 = provider3;
    }

    public static MembersInjector<TarazedInitializer> create(Provider<TarazedLogger> provider, Provider<ActivityTracker> provider2, Provider<DeviceInfoUtilityAndroid> provider3) {
        return new TarazedInitializer_MembersInjector(provider, provider2, provider3);
    }

    public static void injectSetActivityTracker(TarazedInitializer tarazedInitializer, ActivityTracker activityTracker) {
        tarazedInitializer.setActivityTracker(activityTracker);
    }

    public static void injectSetDeviceInfoUtility(TarazedInitializer tarazedInitializer, DeviceInfoUtilityAndroid deviceInfoUtilityAndroid) {
        tarazedInitializer.setDeviceInfoUtility(deviceInfoUtilityAndroid);
    }

    public static void injectSetLogger(TarazedInitializer tarazedInitializer, TarazedLogger tarazedLogger) {
        tarazedInitializer.setLogger(tarazedLogger);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TarazedInitializer tarazedInitializer) {
        injectSetLogger(tarazedInitializer, this.p0Provider.mo10268get());
        injectSetActivityTracker(tarazedInitializer, this.p0Provider2.mo10268get());
        injectSetDeviceInfoUtility(tarazedInitializer, this.p0Provider3.mo10268get());
    }
}
