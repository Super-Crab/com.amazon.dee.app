package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.greenrobot.eventbus.EventBus;
/* compiled from: EventBusModule_ProvidesEventBusFactory.java */
/* loaded from: classes.dex */
public final class KCK implements Factory<EventBus> {
    public final WMj zZm;

    public KCK(WMj wMj) {
        this.zZm = wMj;
    }

    public static KCK zZm(WMj wMj) {
        return new KCK(wMj);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (EventBus) Preconditions.checkNotNull(this.zZm.zZm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
