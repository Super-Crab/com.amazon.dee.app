package com.amazon.alexa.client.alexaservice.componentstate;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.client.core.componentstate.ComponentStatePayload;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.client.core.messages.RawStringPayload;
import com.google.auto.value.AutoValue;
import java.util.HashSet;
import java.util.Set;
@AutoValue
/* loaded from: classes6.dex */
public abstract class ComponentState {
    public static ComponentState create(ComponentStateHeader componentStateHeader, ComponentStatePayload componentStatePayload) {
        return new AutoValue_ComponentState(componentStateHeader, componentStatePayload);
    }

    public static Set<ComponentState> createMultiple(Set<AlexaContext> set) {
        HashSet hashSet = new HashSet();
        for (AlexaContext alexaContext : set) {
            ComponentState create = create(alexaContext);
            if (create != null) {
                hashSet.add(create);
            }
        }
        return hashSet;
    }

    public abstract ComponentStateHeader getHeader();

    public abstract ComponentStatePayload getPayload();

    public String toString() {
        return String.format("%s: %s.%s", getClass().getSimpleName(), getHeader().BIo().getValue(), getHeader().zZm().getValue());
    }

    public static ComponentState create(ExternalComponentStateEntity externalComponentStateEntity) {
        return new AutoValue_ComponentState(ComponentStateHeader.zZm(externalComponentStateEntity.zyO(), externalComponentStateEntity.zQM()), RawStringPayload.create(externalComponentStateEntity.JTe()));
    }

    @Nullable
    public static ComponentState create(@Nullable AlexaContext alexaContext) {
        if (alexaContext == null || alexaContext.getAlexaHeader() == null || alexaContext.getAlexaPayload() == null) {
            return null;
        }
        return create(ComponentStateHeader.zZm(Namespace.create(alexaContext.getAlexaHeader().getNamespace()), Name.create(alexaContext.getAlexaHeader().getName())), RawStringPayload.create(alexaContext.getAlexaPayload().getPayload()));
    }
}
