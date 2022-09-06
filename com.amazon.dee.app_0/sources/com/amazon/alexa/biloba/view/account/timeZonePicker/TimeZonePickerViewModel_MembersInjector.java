package com.amazon.alexa.biloba.view.account.timeZonePicker;

import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.SettingsStore;
import com.amazon.alexa.biloba.storage.TimeZoneStore;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class TimeZonePickerViewModel_MembersInjector implements MembersInjector<TimeZonePickerViewModel> {
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<SettingsStore> settingsStoreProvider;
    private final Provider<TimeZoneStore> timeZoneStoreProvider;

    public TimeZonePickerViewModel_MembersInjector(Provider<CareActorsStore> provider, Provider<SettingsStore> provider2, Provider<TimeZoneStore> provider3) {
        this.careActorsStoreProvider = provider;
        this.settingsStoreProvider = provider2;
        this.timeZoneStoreProvider = provider3;
    }

    public static MembersInjector<TimeZonePickerViewModel> create(Provider<CareActorsStore> provider, Provider<SettingsStore> provider2, Provider<TimeZoneStore> provider3) {
        return new TimeZonePickerViewModel_MembersInjector(provider, provider2, provider3);
    }

    public static void injectCareActorsStore(TimeZonePickerViewModel timeZonePickerViewModel, Lazy<CareActorsStore> lazy) {
        timeZonePickerViewModel.careActorsStore = lazy;
    }

    public static void injectSettingsStore(TimeZonePickerViewModel timeZonePickerViewModel, Lazy<SettingsStore> lazy) {
        timeZonePickerViewModel.settingsStore = lazy;
    }

    public static void injectTimeZoneStore(TimeZonePickerViewModel timeZonePickerViewModel, Lazy<TimeZoneStore> lazy) {
        timeZonePickerViewModel.timeZoneStore = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TimeZonePickerViewModel timeZonePickerViewModel) {
        injectCareActorsStore(timeZonePickerViewModel, DoubleCheck.lazy(this.careActorsStoreProvider));
        injectSettingsStore(timeZonePickerViewModel, DoubleCheck.lazy(this.settingsStoreProvider));
        injectTimeZoneStore(timeZonePickerViewModel, DoubleCheck.lazy(this.timeZoneStoreProvider));
    }
}
