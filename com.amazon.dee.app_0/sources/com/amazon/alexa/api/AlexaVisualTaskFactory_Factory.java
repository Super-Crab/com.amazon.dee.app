package com.amazon.alexa.api;

import com.amazon.alexa.KvZ;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlexaVisualTaskFactory_Factory implements Factory<AlexaVisualTaskFactory> {
    public final Provider<KvZ> messageReceiverAuthorityProvider;

    public AlexaVisualTaskFactory_Factory(Provider<KvZ> provider) {
        this.messageReceiverAuthorityProvider = provider;
    }

    public static AlexaVisualTaskFactory_Factory create(Provider<KvZ> provider) {
        return new AlexaVisualTaskFactory_Factory(provider);
    }

    public static AlexaVisualTaskFactory newAlexaVisualTaskFactory(KvZ kvZ) {
        return new AlexaVisualTaskFactory(kvZ);
    }

    public static AlexaVisualTaskFactory provideInstance(Provider<KvZ> provider) {
        return new AlexaVisualTaskFactory(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaVisualTaskFactory mo10268get() {
        return provideInstance(this.messageReceiverAuthorityProvider);
    }
}
