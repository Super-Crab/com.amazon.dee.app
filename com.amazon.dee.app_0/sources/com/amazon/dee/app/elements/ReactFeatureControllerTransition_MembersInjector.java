package com.amazon.dee.app.elements;

import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ReactFeatureControllerTransition_MembersInjector implements MembersInjector<ReactFeatureControllerTransition> {
    private final Provider<ReactFeatureManager> reactFeatureManagerProvider;

    public ReactFeatureControllerTransition_MembersInjector(Provider<ReactFeatureManager> provider) {
        this.reactFeatureManagerProvider = provider;
    }

    public static MembersInjector<ReactFeatureControllerTransition> create(Provider<ReactFeatureManager> provider) {
        return new ReactFeatureControllerTransition_MembersInjector(provider);
    }

    public static void injectReactFeatureManager(ReactFeatureControllerTransition reactFeatureControllerTransition, ReactFeatureManager reactFeatureManager) {
        reactFeatureControllerTransition.reactFeatureManager = reactFeatureManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ReactFeatureControllerTransition reactFeatureControllerTransition) {
        injectReactFeatureManager(reactFeatureControllerTransition, this.reactFeatureManagerProvider.mo10268get());
    }
}
