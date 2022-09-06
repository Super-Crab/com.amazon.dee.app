package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaMediaPlaybackAuthority_Factory.java */
/* renamed from: com.amazon.alexa.eEN  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0202eEN implements Factory<NDW> {
    public final Provider<AlexaClientEventBus> zZm;

    public C0202eEN(Provider<AlexaClientEventBus> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new NDW(this.zZm.mo10268get());
    }
}
