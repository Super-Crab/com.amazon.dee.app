package com.amazon.tarazed.receiver;

import android.content.SharedPreferences;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ToggleQAModeReceiver_MembersInjector implements MembersInjector<ToggleQAModeReceiver> {
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public ToggleQAModeReceiver_MembersInjector(Provider<TarazedSessionLogger> provider, Provider<SharedPreferences> provider2) {
        this.loggerProvider = provider;
        this.sharedPreferencesProvider = provider2;
    }

    public static MembersInjector<ToggleQAModeReceiver> create(Provider<TarazedSessionLogger> provider, Provider<SharedPreferences> provider2) {
        return new ToggleQAModeReceiver_MembersInjector(provider, provider2);
    }

    public static void injectLogger(ToggleQAModeReceiver toggleQAModeReceiver, TarazedSessionLogger tarazedSessionLogger) {
        toggleQAModeReceiver.logger = tarazedSessionLogger;
    }

    public static void injectSharedPreferences(ToggleQAModeReceiver toggleQAModeReceiver, SharedPreferences sharedPreferences) {
        toggleQAModeReceiver.sharedPreferences = sharedPreferences;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ToggleQAModeReceiver toggleQAModeReceiver) {
        injectLogger(toggleQAModeReceiver, this.loggerProvider.mo10268get());
        injectSharedPreferences(toggleQAModeReceiver, this.sharedPreferencesProvider.mo10268get());
    }
}
