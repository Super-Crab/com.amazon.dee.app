package com.amazon.alexa.capabilities;

import android.content.Context;
import com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class FireOsCapabilityAgentsModule_ProvidesFireOsCapabilityAgentsFactory implements Factory<Set<CapabilityAgent>> {
    private final Provider<Context> contextProvider;

    public FireOsCapabilityAgentsModule_ProvidesFireOsCapabilityAgentsFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static FireOsCapabilityAgentsModule_ProvidesFireOsCapabilityAgentsFactory create(Provider<Context> provider) {
        return new FireOsCapabilityAgentsModule_ProvidesFireOsCapabilityAgentsFactory(provider);
    }

    public static Set<CapabilityAgent> provideInstance(Provider<Context> provider) {
        return proxyProvidesFireOsCapabilityAgents(provider.mo10268get());
    }

    public static Set<CapabilityAgent> proxyProvidesFireOsCapabilityAgents(Context context) {
        return (Set) Preconditions.checkNotNull(FireOsCapabilityAgentsModule.providesFireOsCapabilityAgents(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Set<CapabilityAgent> mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
