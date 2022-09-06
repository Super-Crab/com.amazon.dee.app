package com.amazon.alexa.voiceui.cards;

import android.app.Activity;
import android.content.pm.PackageManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceNavigator_Factory implements Factory<VoiceNavigator> {
    private final Provider<Activity> activityProvider;
    private final Provider<PackageManager> packageManagerProvider;

    public VoiceNavigator_Factory(Provider<Activity> provider, Provider<PackageManager> provider2) {
        this.activityProvider = provider;
        this.packageManagerProvider = provider2;
    }

    public static VoiceNavigator_Factory create(Provider<Activity> provider, Provider<PackageManager> provider2) {
        return new VoiceNavigator_Factory(provider, provider2);
    }

    public static VoiceNavigator newVoiceNavigator(Activity activity, PackageManager packageManager) {
        return new VoiceNavigator(activity, packageManager);
    }

    public static VoiceNavigator provideInstance(Provider<Activity> provider, Provider<PackageManager> provider2) {
        return new VoiceNavigator(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceNavigator mo10268get() {
        return provideInstance(this.activityProvider, this.packageManagerProvider);
    }
}
