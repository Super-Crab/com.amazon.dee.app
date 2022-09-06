package com.amazon.alexa.voiceui.cards;

import dagger.internal.Factory;
/* loaded from: classes11.dex */
public final class DefaultMarketplaceAuthority_Factory implements Factory<DefaultMarketplaceAuthority> {
    private static final DefaultMarketplaceAuthority_Factory INSTANCE = new DefaultMarketplaceAuthority_Factory();

    public static DefaultMarketplaceAuthority_Factory create() {
        return INSTANCE;
    }

    public static DefaultMarketplaceAuthority newDefaultMarketplaceAuthority() {
        return new DefaultMarketplaceAuthority();
    }

    public static DefaultMarketplaceAuthority provideInstance() {
        return new DefaultMarketplaceAuthority();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultMarketplaceAuthority mo10268get() {
        return provideInstance();
    }
}
