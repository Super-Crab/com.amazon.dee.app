package com.amazon.dee.app.services.marketplace;

import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.marketplace.api.MarketplaceService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.services.marketplace.PreferredMarketplaceService;
import com.dee.app.http.CoralService;
import com.google.gson.annotations.SerializedName;
import dagger.Lazy;
import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
/* loaded from: classes12.dex */
public class PreferredMarketplaceService implements MarketplaceService {
    private final Lazy<CoralService> coralService;
    private final PersistentStorage persistentStorage;

    /* loaded from: classes12.dex */
    static class CustomerEffectiveMarketplace {
        @SerializedName("effectiveMarketPlaceId")
        String effectiveMarketPlaceId;

        CustomerEffectiveMarketplace() {
        }
    }

    public PreferredMarketplaceService(PersistentStorage.Factory factory, Lazy<CoralService> lazy) {
        this.coralService = lazy;
        this.persistentStorage = factory.create("service.marketplace");
    }

    @Override // com.amazon.alexa.marketplace.api.MarketplaceService
    public Observable<Marketplace> getEffectivePFM() {
        return Observable.defer(new Func0() { // from class: com.amazon.dee.app.services.marketplace.-$$Lambda$PreferredMarketplaceService$VzGg7ax13Of-rbOAIuRYIdRRDnM
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            /* renamed from: call */
            public final Object mo13098call() {
                return PreferredMarketplaceService.this.lambda$getEffectivePFM$1$PreferredMarketplaceService();
            }
        }).map($$Lambda$PreferredMarketplaceService$9l2illCPhonpLdnPtPMP30j9QIA.INSTANCE);
    }

    public /* synthetic */ Observable lambda$getEffectivePFM$1$PreferredMarketplaceService() {
        String string = this.persistentStorage.getString("user.effectiveMarketplaceId");
        if (string == null) {
            return this.coralService.mo358get().request("/api/get-customer-pfm").get().as(CustomerEffectiveMarketplace.class).toObservable().subscribeOn(Schedulers.io()).map(new Func1() { // from class: com.amazon.dee.app.services.marketplace.-$$Lambda$PreferredMarketplaceService$hM0qlgS1zifn20C-USxzKSf2Rns
                @Override // rx.functions.Func1
                /* renamed from: call */
                public final Object mo13102call(Object obj) {
                    return PreferredMarketplaceService.this.lambda$null$0$PreferredMarketplaceService((PreferredMarketplaceService.CustomerEffectiveMarketplace) obj);
                }
            });
        }
        return Observable.just(string);
    }

    public /* synthetic */ String lambda$null$0$PreferredMarketplaceService(CustomerEffectiveMarketplace customerEffectiveMarketplace) {
        this.persistentStorage.edit().set("user.effectiveMarketplaceId", customerEffectiveMarketplace.effectiveMarketPlaceId).commit();
        return customerEffectiveMarketplace.effectiveMarketPlaceId;
    }
}
