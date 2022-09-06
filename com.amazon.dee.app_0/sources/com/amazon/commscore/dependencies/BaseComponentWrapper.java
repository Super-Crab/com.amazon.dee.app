package com.amazon.commscore.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import dagger.internal.DoubleCheck;
/* loaded from: classes12.dex */
public abstract class BaseComponentWrapper<T> {
    protected CommsCoreComponent commsCoreComponent;

    public BaseComponentWrapper(ComponentGetter componentGetter, Context context) {
        DaggerInitializer.initialize(context, DoubleCheck.lazy(componentGetter.get(IdentityService.class)), DoubleCheck.lazy(componentGetter.get(Mobilytics.class)), DoubleCheck.lazy(componentGetter.get(EventBus.class)), DoubleCheck.lazy(componentGetter.get(EnvironmentService.class)));
        this.commsCoreComponent = DaggerInitializer.get();
    }

    /* renamed from: getImplementation */
    public abstract T mo3276getImplementation();
}
