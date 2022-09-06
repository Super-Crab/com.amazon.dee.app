package com.amazon.alexa.protocols.marketplace;

import rx.Observable;
/* loaded from: classes9.dex */
public interface MarketplaceService {
    Observable<Marketplace> getEffectivePFM();

    void reset();
}
