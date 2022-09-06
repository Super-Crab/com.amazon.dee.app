package com.amazon.alexa.accessorykit.interprocess.mobilytics;

import android.annotation.SuppressLint;
import android.util.Pair;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.interprocess.identity.Person;
import com.amazon.alexa.accessorykit.interprocess.identity.PersonSupplier;
import com.amazon.alexa.accessorykit.utils.MarketplaceServiceUtils;
import com.amazon.alexa.marketplace.api.MarketplaceService;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public class AccessoryMobilyticsUserProvider implements MobilyticsUserProvider {
    private static final String TAG = "AccessoryMobilyticsUserProvider:";
    private boolean active = false;
    private MobilyticsUser currentUser;
    private final StringFeatureChecker featureChecker;
    private final List<MobilyticsUserProvider.Listener> listeners;
    private final Object lock;
    private final MarketplaceService marketplaceService;
    private final PersonSupplier personSupplier;
    private final UserSupplier userSupplier;

    /* loaded from: classes6.dex */
    public interface StringFeatureChecker {
        boolean hasAccess(String str);
    }

    public AccessoryMobilyticsUserProvider(UserSupplier userSupplier, PersonSupplier personSupplier, MarketplaceService marketplaceService, StringFeatureChecker stringFeatureChecker) {
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(personSupplier, "personSupplier");
        Preconditions.notNull(marketplaceService, "marketplaceService");
        Preconditions.notNull(stringFeatureChecker, "featureChecker");
        this.userSupplier = userSupplier;
        this.personSupplier = personSupplier;
        this.marketplaceService = marketplaceService;
        this.featureChecker = stringFeatureChecker;
        this.listeners = new ArrayList();
        this.lock = new Object();
        this.currentUser = AccessoryMobilyticsUser.ABSENT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void commitUser(MobilyticsUser mobilyticsUser) {
        synchronized (this.lock) {
            this.currentUser = mobilyticsUser;
            Logger.d("%s commitUser: %s", TAG, mobilyticsUser);
            for (int size = this.listeners.size() - 1; size >= 0; size--) {
                this.listeners.get(size).onUserChanged(mobilyticsUser);
            }
        }
    }

    static /* synthetic */ String lambda$null$1(Throwable th) throws Throwable {
        return "";
    }

    @SuppressLint({"CheckResult"})
    public AccessoryMobilyticsUserProvider activate() {
        synchronized (this.lock) {
            if (this.active) {
                return this;
            }
            this.active = true;
            Logger.d("%s activate()", TAG);
            Observable.combineLatest(this.userSupplier.queryUser(), this.personSupplier.queryPerson(), $$Lambda$skhr7ig4zxlP8tefoP0IL4gto9Y.INSTANCE).concatMap(new Function() { // from class: com.amazon.alexa.accessorykit.interprocess.mobilytics.-$$Lambda$AccessoryMobilyticsUserProvider$zMuEe1df6oKz4wudWdi87j7DQSU
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return AccessoryMobilyticsUserProvider.this.lambda$activate$3$AccessoryMobilyticsUserProvider((Pair) obj);
                }
            }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.mobilytics.-$$Lambda$AccessoryMobilyticsUserProvider$3Ngk4DRaTHXPBCirkhPmVyNsVCQ
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    AccessoryMobilyticsUserProvider.this.commitUser((AccessoryMobilyticsUser) obj);
                }
            }, $$Lambda$AccessoryMobilyticsUserProvider$H_a9kufOKnzaVLN5jO8MkPTixOg.INSTANCE);
            return this;
        }
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider
    public void addListener(@NonNull MobilyticsUserProvider.Listener listener) {
        synchronized (this.lock) {
            this.listeners.add(listener);
        }
    }

    public /* synthetic */ ObservableSource lambda$activate$3$AccessoryMobilyticsUserProvider(Pair pair) throws Throwable {
        final User user = (User) pair.first;
        final Person person = (Person) pair.second;
        if (user == User.ABSENT) {
            return Observable.just(AccessoryMobilyticsUser.ABSENT);
        }
        return MarketplaceServiceUtils.getMarketplace(this.marketplaceService).toObservable().map($$Lambda$AccessoryMobilyticsUserProvider$sC8cKPRdoiKernD4v8l0ASxLNWE.INSTANCE).onErrorReturn($$Lambda$AccessoryMobilyticsUserProvider$iNPlx3ZTm_dEXRdb4iWjoADA5U8.INSTANCE).map(new Function() { // from class: com.amazon.alexa.accessorykit.interprocess.mobilytics.-$$Lambda$AccessoryMobilyticsUserProvider$vBAc7ftAXasP04YypSSaznNkxHM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessoryMobilyticsUserProvider.this.lambda$null$2$AccessoryMobilyticsUserProvider(user, person, (String) obj);
            }
        });
    }

    public /* synthetic */ AccessoryMobilyticsUser lambda$null$2$AccessoryMobilyticsUserProvider(User user, Person person, String str) throws Throwable {
        return new AccessoryMobilyticsUser(user.getDirectedCustomerId(), str, person, this.featureChecker);
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider
    public void removeListener(@NonNull MobilyticsUserProvider.Listener listener) {
        synchronized (this.lock) {
            this.listeners.remove(listener);
        }
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider
    public MobilyticsUser user() {
        MobilyticsUser mobilyticsUser;
        synchronized (this.lock) {
            mobilyticsUser = this.currentUser;
        }
        return mobilyticsUser;
    }
}
