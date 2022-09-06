package com.amazon.deecomms.services;

import com.amazon.alexa.drivemode.api.DriveModeCardsProvider;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import dagger.Lazy;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.services.-$$Lambda$CommsServiceV2Impl$iuI4ovFqUHL4-ZB83dHs3WSfNKU  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsServiceV2Impl$iuI4ovFqUHL4ZB83dHs3WSfNKU implements Lazy {
    public static final /* synthetic */ $$Lambda$CommsServiceV2Impl$iuI4ovFqUHL4ZB83dHs3WSfNKU INSTANCE = new $$Lambda$CommsServiceV2Impl$iuI4ovFqUHL4ZB83dHs3WSfNKU();

    private /* synthetic */ $$Lambda$CommsServiceV2Impl$iuI4ovFqUHL4ZB83dHs3WSfNKU() {
    }

    @Override // dagger.Lazy
    /* renamed from: get */
    public final Object mo358get() {
        DriveModeCardsProvider commsDriveModeCardProvider;
        commsDriveModeCardProvider = CommsDaggerWrapper.getComponent().getCommsDriveModeCardProvider();
        return commsDriveModeCardProvider;
    }
}
