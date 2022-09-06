package com.amazon.alexa.accessorykit;

import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public final class AlexaUserSupplier implements UserSupplier {
    private static final String ACCESS_TOKEN_NONNULL_FOR_USER_METRIC_NAME = "AccessoryAccessTokenNonNullForUser";
    private static final String ACCESS_TOKEN_PRESENT_FOR_USER_METRIC_NAME = "AccessoryAccessTokenPresentForUser";
    @VisibleForTesting
    static final String CALLING_CLASS = "com.amazon.alexa.accessorykit.AlexaUserSupplier";
    private static final String COMPONENT_NAME = "Accessory";
    private static final String EMPTY_ACCESS_TOKEN = "";
    private static final String TAG = "AlexaUserSupplier";
    private final AccessoryMetricsService accessoryMetricsService;
    private final EventBus eventBus;
    private boolean hasSubscribedToEventBusUserChangedEvents;
    private final IdentityService identityService;
    private final Object lock;
    private final long timeoutSeconds;
    private final Subject<Object> userChangedSubject;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static final class AccessTokenNotAvailableException extends Exception {
        private AccessTokenNotAvailableException() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static final class DirectedIdNotAvailableException extends Exception {
        private DirectedIdNotAvailableException() {
        }
    }

    public AlexaUserSupplier(IdentityService identityService, AccessoryMetricsService accessoryMetricsService, long j, EventBus eventBus) {
        Preconditions.notNull(identityService, "identityService");
        Preconditions.notNull(accessoryMetricsService, "accessoryMetricsService");
        Preconditions.notNull(eventBus, "eventBus");
        this.identityService = identityService;
        this.timeoutSeconds = j;
        this.accessoryMetricsService = accessoryMetricsService;
        this.eventBus = eventBus;
        this.userChangedSubject = PublishSubject.create().toSerialized();
        this.lock = new Object();
    }

    private static void assertNotMainThread() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException("Current thread must not be main thread");
        Logger.e("Throwing ", illegalStateException);
        throw illegalStateException;
    }

    @NonNull
    private String getAccessTokenSynchronous() throws AccessTokenNotAvailableException {
        assertNotMainThread();
        String accessToken = this.identityService.getAccessToken(CALLING_CLASS);
        recordAccessTokenNonNullForUser(this.accessoryMetricsService, accessToken != null);
        recordAccessTokenPresentForUser(this.accessoryMetricsService, true ^ "".equals(accessToken));
        if (accessToken == null || accessToken.isEmpty()) {
            throw new AccessTokenNotAvailableException();
        }
        return accessToken;
    }

    private Single<String> getAccessTokenWithTimeout() {
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AlexaUserSupplier$gu3-ncIeWJ10JO8MeSt3XRTqxts
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return AlexaUserSupplier.this.lambda$getAccessTokenWithTimeout$5$AlexaUserSupplier();
            }
        }).timeout(this.timeoutSeconds, TimeUnit.SECONDS);
    }

    @NonNull
    private String getDirectedIdSynchronous() throws DirectedIdNotAvailableException {
        assertNotMainThread();
        UserIdentity user = this.identityService.getUser(CALLING_CLASS);
        if (user != null && user.getDirectedId() != null && !user.getDirectedId().isEmpty()) {
            return user.getDirectedId();
        }
        Logger.e("%s user is not available in getDirectedIdSynchronous", TAG);
        throw new DirectedIdNotAvailableException();
    }

    private Single<String> getDirectedIdWithTimeout() {
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AlexaUserSupplier$ANae3lKjRWz7dfRvNcTDfcKHnpo
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return AlexaUserSupplier.this.lambda$getDirectedIdWithTimeout$4$AlexaUserSupplier();
            }
        }).timeout(this.timeoutSeconds, TimeUnit.SECONDS);
    }

    private Single<User> getUserRx2() {
        return getDirectedIdWithTimeout().observeOn(Schedulers.io()).flatMap(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AlexaUserSupplier$0ojZGUmFr21ICSIQuTdgZaUHn_I
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AlexaUserSupplier.this.lambda$getUserRx2$3$AlexaUserSupplier((String) obj);
            }
        }).onErrorReturnItem(User.ABSENT).subscribeOn(Schedulers.io());
    }

    static /* synthetic */ User lambda$null$2(String str, String str2) throws Throwable {
        return new User(str2, str);
    }

    private static void recordAccessTokenNonNullForUser(AccessoryMetricsService accessoryMetricsService, boolean z) {
        accessoryMetricsService.recordOccurrence(ACCESS_TOKEN_NONNULL_FOR_USER_METRIC_NAME, "Accessory", z, null);
    }

    private static void recordAccessTokenPresentForUser(AccessoryMetricsService accessoryMetricsService, boolean z) {
        accessoryMetricsService.recordOccurrence(ACCESS_TOKEN_PRESENT_FOR_USER_METRIC_NAME, "Accessory", z, null);
    }

    private Observable<Object> subscribeToIdentityChangedEvents() {
        synchronized (this.lock) {
            if (this.hasSubscribedToEventBusUserChangedEvents) {
                return this.userChangedSubject;
            }
            this.hasSubscribedToEventBusUserChangedEvents = true;
            MultiFilterSubscriber subscriber = this.eventBus.getSubscriber();
            Subscriber.SubscriberUuid subscriberUuid = subscriber.getSubscriberUuid();
            subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AlexaUserSupplier$xHFDjmSxKn5tUocnw9ojxkXvemY
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    AlexaUserSupplier.this.lambda$subscribeToIdentityChangedEvents$1$AlexaUserSupplier(message);
                }
            });
            Logger.d("Subscribed to eventBus for IDENTITY_CHANGED uuid: %s", subscriberUuid.getUuid().toString());
            return this.userChangedSubject;
        }
    }

    public /* synthetic */ SingleSource lambda$getAccessTokenWithTimeout$5$AlexaUserSupplier() throws Throwable {
        return Single.just(getAccessTokenSynchronous());
    }

    public /* synthetic */ SingleSource lambda$getDirectedIdWithTimeout$4$AlexaUserSupplier() throws Throwable {
        return Single.just(getDirectedIdSynchronous());
    }

    public /* synthetic */ SingleSource lambda$getUserRx2$3$AlexaUserSupplier(final String str) throws Throwable {
        return getAccessTokenWithTimeout().map(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AlexaUserSupplier$qmbEGLp2WHbu72xGu1RdSOH5urw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return new User((String) obj, str);
            }
        }).onErrorReturnItem(new User(null, str));
    }

    public /* synthetic */ SingleSource lambda$queryUser$0$AlexaUserSupplier(Object obj) throws Throwable {
        return getUserRx2();
    }

    public /* synthetic */ void lambda$subscribeToIdentityChangedEvents$1$AlexaUserSupplier(Message message) {
        Logger.d("identity changed");
        this.userChangedSubject.onNext(new Object());
    }

    @Override // com.amazon.alexa.accessory.UserSupplier
    public Observable<User> queryUser() {
        return getUserRx2().toObservable().concatWith(subscribeToIdentityChangedEvents().observeOn(Schedulers.io()).flatMapSingle(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AlexaUserSupplier$bzA3Bm1bveqrWzKEgPedPOiKIzk
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AlexaUserSupplier.this.lambda$queryUser$0$AlexaUserSupplier(obj);
            }
        })).subscribeOn(Schedulers.io());
    }
}
