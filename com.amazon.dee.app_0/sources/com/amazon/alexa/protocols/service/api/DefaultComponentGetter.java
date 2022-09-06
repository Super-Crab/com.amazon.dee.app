package com.amazon.alexa.protocols.service.api;

import androidx.annotation.NonNull;
/* loaded from: classes9.dex */
public final class DefaultComponentGetter implements ComponentGetter {
    private final DefaultComponentRegistry componentRegistry;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultComponentGetter(DefaultComponentRegistry defaultComponentRegistry) {
        this.componentRegistry = defaultComponentRegistry;
    }

    @Override // com.amazon.alexa.protocols.service.api.ComponentGetter
    @NonNull
    public <T> LazyComponent<T> get(@NonNull Class<T> cls) {
        return this.componentRegistry.getLazy(cls);
    }
}
