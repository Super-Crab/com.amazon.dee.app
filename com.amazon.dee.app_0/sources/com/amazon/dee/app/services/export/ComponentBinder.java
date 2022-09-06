package com.amazon.dee.app.services.export;

import android.content.Context;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import java.util.Map;
/* loaded from: classes12.dex */
public interface ComponentBinder {
    Map<Class<?>, ComponentRegistry.ConcreteComponentFactory<?>> publishTransitionalObjectsOwnedByDagger();

    Map<Class, String> registerComponents(Context context);
}
