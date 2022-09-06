package com.amazon.alexa.handsfree.settings.handsfreesettings;

import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class AlexaSettingsPreferenceFragment_MembersInjector implements MembersInjector<AlexaSettingsPreferenceFragment> {
    private final Provider<EnrollmentTypeResolver> mEnrollmentTypeResolverLazyProvider;
    private final Provider<HandsFreeUserIdentityProvider> mHandsFreeUserIdentityProvider;

    public AlexaSettingsPreferenceFragment_MembersInjector(Provider<EnrollmentTypeResolver> provider, Provider<HandsFreeUserIdentityProvider> provider2) {
        this.mEnrollmentTypeResolverLazyProvider = provider;
        this.mHandsFreeUserIdentityProvider = provider2;
    }

    public static MembersInjector<AlexaSettingsPreferenceFragment> create(Provider<EnrollmentTypeResolver> provider, Provider<HandsFreeUserIdentityProvider> provider2) {
        return new AlexaSettingsPreferenceFragment_MembersInjector(provider, provider2);
    }

    public static void injectMEnrollmentTypeResolverLazy(AlexaSettingsPreferenceFragment alexaSettingsPreferenceFragment, Lazy<EnrollmentTypeResolver> lazy) {
        alexaSettingsPreferenceFragment.mEnrollmentTypeResolverLazy = lazy;
    }

    public static void injectMHandsFreeUserIdentityProvider(AlexaSettingsPreferenceFragment alexaSettingsPreferenceFragment, HandsFreeUserIdentityProvider handsFreeUserIdentityProvider) {
        alexaSettingsPreferenceFragment.mHandsFreeUserIdentityProvider = handsFreeUserIdentityProvider;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlexaSettingsPreferenceFragment alexaSettingsPreferenceFragment) {
        injectMEnrollmentTypeResolverLazy(alexaSettingsPreferenceFragment, DoubleCheck.lazy(this.mEnrollmentTypeResolverLazyProvider));
        injectMHandsFreeUserIdentityProvider(alexaSettingsPreferenceFragment, this.mHandsFreeUserIdentityProvider.mo10268get());
    }
}
