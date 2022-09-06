package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.Namespace;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
/* compiled from: ComponentStateModule_ProvideNamespacesWithoutCapabilitiesFactory.java */
/* loaded from: classes.dex */
public final class JGY implements Factory<Set<Namespace>> {
    public final yxr zZm;

    public JGY(yxr yxrVar) {
        this.zZm = yxrVar;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (Set) Preconditions.checkNotNull(this.zZm.zZm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
