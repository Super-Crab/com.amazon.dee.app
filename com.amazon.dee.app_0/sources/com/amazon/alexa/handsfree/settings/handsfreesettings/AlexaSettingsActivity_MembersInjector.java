package com.amazon.alexa.handsfree.settings.handsfreesettings;

import com.amazon.alexa.handsfree.protocols.Initializer;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class AlexaSettingsActivity_MembersInjector implements MembersInjector<AlexaSettingsActivity> {
    private final Provider<Initializer> mInitializerProvider;

    public AlexaSettingsActivity_MembersInjector(Provider<Initializer> provider) {
        this.mInitializerProvider = provider;
    }

    public static MembersInjector<AlexaSettingsActivity> create(Provider<Initializer> provider) {
        return new AlexaSettingsActivity_MembersInjector(provider);
    }

    public static void injectMInitializer(AlexaSettingsActivity alexaSettingsActivity, Initializer initializer) {
        alexaSettingsActivity.mInitializer = initializer;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlexaSettingsActivity alexaSettingsActivity) {
        injectMInitializer(alexaSettingsActivity, this.mInitializerProvider.mo10268get());
    }
}
