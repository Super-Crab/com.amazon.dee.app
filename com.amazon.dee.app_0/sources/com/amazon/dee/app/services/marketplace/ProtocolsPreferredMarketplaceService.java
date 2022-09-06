package com.amazon.dee.app.services.marketplace;

import com.amazon.alexa.protocols.marketplace.Marketplace;
import com.amazon.alexa.protocols.marketplace.MarketplaceService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.services.marketplace.PreferredMarketplaceService;
import com.dee.app.http.CoralService;
import com.google.gson.annotations.SerializedName;
import dagger.Lazy;
import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
@Deprecated
/* loaded from: classes12.dex */
public class ProtocolsPreferredMarketplaceService implements MarketplaceService {
    private final Lazy<CoralService> coralService;
    private final PersistentStorage persistentStorage;

    /* loaded from: classes12.dex */
    static class CustomerEffectiveMarketplace {
        @SerializedName("effectiveMarketPlaceId")
        String effectiveMarketPlaceId;

        CustomerEffectiveMarketplace() {
        }
    }

    public ProtocolsPreferredMarketplaceService(PersistentStorage.Factory factory, Lazy<CoralService> lazy) {
        this.coralService = lazy;
        this.persistentStorage = factory.create("service.marketplace");
    }

    @Override // com.amazon.alexa.protocols.marketplace.MarketplaceService
    public Observable<Marketplace> getEffectivePFM() {
        return Observable.defer(new Func0() { // from class: com.amazon.dee.app.services.marketplace.-$$Lambda$ProtocolsPreferredMarketplaceService$YogRLPCW4DY7l7VNG19WFXulaxI
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            /* renamed from: call */
            public final Object mo13098call() {
                return ProtocolsPreferredMarketplaceService.this.lambda$getEffectivePFM$1$ProtocolsPreferredMarketplaceService();
            }
        }).map($$Lambda$ProtocolsPreferredMarketplaceService$ZCLtLEVGA9DiPQcLap3VkfLfmQ.INSTANCE);
    }

    public /* synthetic */ Observable lambda$getEffectivePFM$1$ProtocolsPreferredMarketplaceService() {
        String string = this.persistentStorage.getString("user.effectiveMarketplaceId");
        if (string == null) {
            return this.coralService.mo358get().request("/api/get-customer-pfm").get().as(PreferredMarketplaceService.CustomerEffectiveMarketplace.class).toObservable().subscribeOn(Schedulers.io()).map(new Func1() { // from class: com.amazon.dee.app.services.marketplace.-$$Lambda$ProtocolsPreferredMarketplaceService$4HWsxXnvFiQurUmRb3TRQn1hNYo
                @Override // rx.functions.Func1
                /* renamed from: call */
                public final Object mo13102call(Object obj) {
                    return ProtocolsPreferredMarketplaceService.this.lambda$null$0$ProtocolsPreferredMarketplaceService((PreferredMarketplaceService.CustomerEffectiveMarketplace) obj);
                }
            });
        }
        return Observable.just(string);
    }

    public /* synthetic */ String lambda$null$0$ProtocolsPreferredMarketplaceService(PreferredMarketplaceService.CustomerEffectiveMarketplace customerEffectiveMarketplace) {
        this.persistentStorage.edit().set("user.effectiveMarketplaceId", customerEffectiveMarketplace.effectiveMarketPlaceId).commit();
        return customerEffectiveMarketplace.effectiveMarketPlaceId;
    }

    @Override // com.amazon.alexa.protocols.marketplace.MarketplaceService
    public void reset() {
        this.persistentStorage.edit().remove("user.effectiveMarketplaceId").commit();
    }
}
