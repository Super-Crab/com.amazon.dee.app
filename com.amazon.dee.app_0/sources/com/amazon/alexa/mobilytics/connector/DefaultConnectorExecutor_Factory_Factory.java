package com.amazon.alexa.mobilytics.connector;

import com.amazon.alexa.mobilytics.connector.DefaultConnectorExecutor;
import dagger.internal.Factory;
/* loaded from: classes9.dex */
public final class DefaultConnectorExecutor_Factory_Factory implements Factory<DefaultConnectorExecutor.Factory> {
    private static final DefaultConnectorExecutor_Factory_Factory INSTANCE = new DefaultConnectorExecutor_Factory_Factory();

    public static DefaultConnectorExecutor_Factory_Factory create() {
        return INSTANCE;
    }

    public static DefaultConnectorExecutor.Factory newFactory() {
        return new DefaultConnectorExecutor.Factory();
    }

    public static DefaultConnectorExecutor.Factory provideInstance() {
        return new DefaultConnectorExecutor.Factory();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultConnectorExecutor.Factory mo10268get() {
        return provideInstance();
    }
}
