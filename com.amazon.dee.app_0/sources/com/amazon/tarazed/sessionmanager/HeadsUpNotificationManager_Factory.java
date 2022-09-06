package com.amazon.tarazed.sessionmanager;

import android.content.Context;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class HeadsUpNotificationManager_Factory implements Factory<HeadsUpNotificationManager> {
    private final Provider<ActivityTracker> activityTrackerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeviceInfoUtilityAndroid> deviceInfoProvider;
    private final Provider<TarazedSessionNotifier> notifierProvider;

    public HeadsUpNotificationManager_Factory(Provider<Context> provider, Provider<ActivityTracker> provider2, Provider<DeviceInfoUtilityAndroid> provider3, Provider<TarazedSessionNotifier> provider4) {
        this.contextProvider = provider;
        this.activityTrackerProvider = provider2;
        this.deviceInfoProvider = provider3;
        this.notifierProvider = provider4;
    }

    public static HeadsUpNotificationManager_Factory create(Provider<Context> provider, Provider<ActivityTracker> provider2, Provider<DeviceInfoUtilityAndroid> provider3, Provider<TarazedSessionNotifier> provider4) {
        return new HeadsUpNotificationManager_Factory(provider, provider2, provider3, provider4);
    }

    public static HeadsUpNotificationManager newHeadsUpNotificationManager(Context context, ActivityTracker activityTracker, DeviceInfoUtilityAndroid deviceInfoUtilityAndroid, TarazedSessionNotifier tarazedSessionNotifier) {
        return new HeadsUpNotificationManager(context, activityTracker, deviceInfoUtilityAndroid, tarazedSessionNotifier);
    }

    public static HeadsUpNotificationManager provideInstance(Provider<Context> provider, Provider<ActivityTracker> provider2, Provider<DeviceInfoUtilityAndroid> provider3, Provider<TarazedSessionNotifier> provider4) {
        return new HeadsUpNotificationManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HeadsUpNotificationManager mo10268get() {
        return provideInstance(this.contextProvider, this.activityTrackerProvider, this.deviceInfoProvider, this.notifierProvider);
    }
}
