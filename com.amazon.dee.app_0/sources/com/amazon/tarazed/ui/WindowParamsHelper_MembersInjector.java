package com.amazon.tarazed.ui;

import android.content.SharedPreferences;
import com.amazon.tarazed.activity.ActivityTracker;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class WindowParamsHelper_MembersInjector implements MembersInjector<WindowParamsHelper> {
    private final Provider<ActivityTracker> p0Provider;
    private final Provider<SharedPreferences> p0Provider2;

    public WindowParamsHelper_MembersInjector(Provider<ActivityTracker> provider, Provider<SharedPreferences> provider2) {
        this.p0Provider = provider;
        this.p0Provider2 = provider2;
    }

    public static MembersInjector<WindowParamsHelper> create(Provider<ActivityTracker> provider, Provider<SharedPreferences> provider2) {
        return new WindowParamsHelper_MembersInjector(provider, provider2);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(WindowParamsHelper windowParamsHelper) {
        windowParamsHelper.setActivityTracker$TarazedAndroidLibrary_release(this.p0Provider.mo10268get());
        windowParamsHelper.setSharedPreferences$TarazedAndroidLibrary_release(this.p0Provider2.mo10268get());
    }
}
