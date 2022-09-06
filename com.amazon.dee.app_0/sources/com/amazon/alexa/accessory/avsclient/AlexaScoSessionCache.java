package com.amazon.alexa.accessory.avsclient;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class AlexaScoSessionCache extends AccessorySessionListener {
    private final Set<String> scoNotPrioritizedSessionsCache;
    private final Set<String> scoPrioritizedSessionsCache;
    private final SessionSupplier sessionSupplier;

    public AlexaScoSessionCache(SessionSupplier sessionSupplier) {
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        this.sessionSupplier = sessionSupplier;
        this.scoPrioritizedSessionsCache = Collections.synchronizedSet(new HashSet());
        this.scoNotPrioritizedSessionsCache = Collections.synchronizedSet(new HashSet());
        cacheAccessorySessionsScoPreference();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: cacheAccessorySessionScoPreference */
    public void lambda$cacheAccessorySessionsScoPreference$1$AlexaScoSessionCache(final AccessorySession accessorySession) {
        getAccessoryScoPreference(accessorySession).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AlexaScoSessionCache$9SCwQSpY-F7RE5OwSU5JOUWKxVE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaScoSessionCache.this.lambda$cacheAccessorySessionScoPreference$4$AlexaScoSessionCache(accessorySession, (Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AlexaScoSessionCache$NoQdxJre5gv6JtTrSuk1SRsF-cw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaScoSessionCache.this.lambda$cacheAccessorySessionScoPreference$5$AlexaScoSessionCache(accessorySession, (Throwable) obj);
            }
        });
    }

    private Single<Boolean> getAccessoryScoPreference(final AccessorySession accessorySession) {
        return accessorySession.getStateRepository().query(StateFeature.BLUETOOTH_SCO_PRIORITIZED).firstOrError().onErrorReturnItem(StateOuterClass.State.newBuilder().setBoolean(false).mo10084build()).map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AlexaScoSessionCache$AddAODNjpIRW2_7jLmZvH02R04k
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AlexaScoSessionCache.lambda$getAccessoryScoPreference$6(AccessorySession.this, (StateOuterClass.State) obj);
            }
        }).subscribeOn(Schedulers.io());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$getAccessoryScoPreference$6(AccessorySession accessorySession, StateOuterClass.State state) throws Throwable {
        boolean z = state.getBoolean();
        Logger.d("AlexaScoSessionCache: SCO preferred found for session [" + accessorySession + "] with value [" + z + "]");
        return Boolean.valueOf(z);
    }

    public void cacheAccessorySessionsScoPreference() {
        Observable.defer(new Supplier() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AlexaScoSessionCache$xUI5MKo6mLHZwpZGDwXB5dNWKsA
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return AlexaScoSessionCache.this.lambda$cacheAccessorySessionsScoPreference$0$AlexaScoSessionCache();
            }
        }).doOnNext(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AlexaScoSessionCache$z5fb1XX1eaDytmSmxfwiFFl8e0k
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaScoSessionCache.this.lambda$cacheAccessorySessionsScoPreference$1$AlexaScoSessionCache((AccessorySession) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe($$Lambda$AlexaScoSessionCache$ap1Gfw8h95B0V1n75a2Ax71TP1M.INSTANCE, $$Lambda$AlexaScoSessionCache$A0x_Tu2I0UiGOExit3gayAMdt94.INSTANCE);
    }

    public int getNumAccessoriesThatDoNotPrioritizeSco() {
        return this.scoNotPrioritizedSessionsCache.size();
    }

    public int getNumAccessoriesThatPrioritizeSco() {
        return this.scoPrioritizedSessionsCache.size();
    }

    public /* synthetic */ void lambda$cacheAccessorySessionScoPreference$4$AlexaScoSessionCache(AccessorySession accessorySession, Boolean bool) throws Throwable {
        Logger.d("AlexaScoSessionCache: Putting sco state into cache [" + bool + "]");
        (bool.booleanValue() ? this.scoPrioritizedSessionsCache : this.scoNotPrioritizedSessionsCache).add(accessorySession.getAddress());
    }

    public /* synthetic */ void lambda$cacheAccessorySessionScoPreference$5$AlexaScoSessionCache(AccessorySession accessorySession, Throwable th) throws Throwable {
        Logger.e("AlexaScoSessionCache: Could not retrieve sco preference for accessories.", th);
        this.scoNotPrioritizedSessionsCache.add(accessorySession.getAddress());
    }

    public /* synthetic */ ObservableSource lambda$cacheAccessorySessionsScoPreference$0$AlexaScoSessionCache() throws Throwable {
        return Observable.fromIterable(this.sessionSupplier.getActiveSessions());
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionConnected(Accessory accessory) {
        Logger.d("AlexaScoSessionCache: accessory session connected detected, caching SCO preference");
        lambda$cacheAccessorySessionsScoPreference$1$AlexaScoSessionCache(this.sessionSupplier.getSession(accessory));
    }

    @Override // com.amazon.alexa.accessory.AccessorySessionListener
    public void onAccessorySessionReleased(Accessory accessory) {
        Preconditions.mainThread();
        Logger.d("AlexaScoSessionCache: accessory session released detected, removing from cache");
        this.scoPrioritizedSessionsCache.remove(accessory.getAddress());
        this.scoNotPrioritizedSessionsCache.remove(accessory.getAddress());
    }
}
