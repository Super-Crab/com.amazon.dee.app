package com.amazon.alexa.location.provider.interactor.event;

import com.amazon.alexa.location.networking.gateway.AlexaLocationPlatformServiceNetworkGateway;
import com.amazon.alexa.protocols.service.api.LazyComponent;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.provider.interactor.event.-$$Lambda$CXFD74mKhWV2Hq_3OrYAD6E16p8  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$CXFD74mKhWV2Hq_3OrYAD6E16p8 implements LazyComponent {
    public static final /* synthetic */ $$Lambda$CXFD74mKhWV2Hq_3OrYAD6E16p8 INSTANCE = new $$Lambda$CXFD74mKhWV2Hq_3OrYAD6E16p8();

    private /* synthetic */ $$Lambda$CXFD74mKhWV2Hq_3OrYAD6E16p8() {
    }

    @Override // com.amazon.alexa.protocols.service.api.LazyComponent, javax.inject.Provider
    /* renamed from: get */
    public final Object mo10268get() {
        return new AlexaLocationPlatformServiceNetworkGateway();
    }
}
