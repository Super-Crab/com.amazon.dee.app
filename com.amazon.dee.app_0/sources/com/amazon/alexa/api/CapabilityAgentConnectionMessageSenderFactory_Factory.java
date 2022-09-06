package com.amazon.alexa.api;

import com.amazon.alexa.KvZ;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CapabilityAgentConnectionMessageSenderFactory_Factory implements Factory<CapabilityAgentConnectionMessageSenderFactory> {
    public final Provider<KvZ> zZm;

    public CapabilityAgentConnectionMessageSenderFactory_Factory(Provider<KvZ> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new CapabilityAgentConnectionMessageSenderFactory(this.zZm.mo10268get());
    }
}
