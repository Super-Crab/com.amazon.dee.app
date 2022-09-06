package com.amazon.alexa.accessory.repositories.system;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.davs.DavsI18nConfig;
import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.MaybeResult;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.LoggerUtils;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.system.SystemProducer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.processors.ReplayProcessor;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
/* loaded from: classes6.dex */
public final class MemorySystemRepository extends BaseProducer<SystemProducer.ActionHandler> implements SystemProducer, SystemRepository, SystemProvider {
    private static final String LOCALE_FORMAT = "^[a-z][a-z]-[A-Z][A-Z]$";
    private static final String TAG = "MemorySystemRepository";
    private boolean hasRequestedInitialI18nConfig;
    private final ReplayProcessor<System.Locales> localesProcessor = ReplayProcessor.createWithSize(1);
    private final BehaviorSubject<System.Users> usersSubject = BehaviorSubject.create();
    private final BehaviorSubject<DavsI18nConfig> davsI18nSubject = BehaviorSubject.create();
    private final Object lock = new Object();
    private boolean hasRequestedInitialUsers = false;

    @SuppressLint({"CheckResult"})
    private void retrieveInitialI18nConfig() {
        synchronized (this.lock) {
            if (this.hasRequestedInitialI18nConfig) {
                return;
            }
            this.hasRequestedInitialI18nConfig = true;
            Logger.d("%s: requesting I18N_Config", TAG);
            Maybe create = MaybeResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$MemorySystemRepository$10JeElJjdf6u5zkhaIWzlyeye28
                @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
                public final void call(Producer.Result result) {
                    MemorySystemRepository.this.lambda$retrieveInitialI18nConfig$3$MemorySystemRepository(result);
                }
            });
            Consumer consumer = new Consumer() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$2LHhsmzDkuBbDyvweFLXu0IJluE
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    MemorySystemRepository.this.provideI18nConfig((DavsI18nConfig) obj);
                }
            };
            Consumer<? super Throwable> consumer2 = new Consumer() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$6PoMJWxAwilQxTqUYZhdSNiEzBw
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    MemorySystemRepository.this.provideI18nConfigError((Throwable) obj);
                }
            };
            final BehaviorSubject<DavsI18nConfig> behaviorSubject = this.davsI18nSubject;
            behaviorSubject.getClass();
            create.subscribe(consumer, consumer2, new Action() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$G_RpNvYHZq-WbYDvfd2OrdS5VwU
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    BehaviorSubject.this.onComplete();
                }
            });
        }
    }

    @SuppressLint({"CheckResult"})
    private void retrieveInitialUsers() {
        synchronized (this.lock) {
            if (this.hasRequestedInitialUsers) {
                return;
            }
            this.hasRequestedInitialUsers = true;
            Logger.d("%s: requesting GET_USERS", TAG);
            SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$MemorySystemRepository$Hn6T6hTctAGWmgbEpNPtgMG2QH0
                @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
                public final void call(Producer.Result result) {
                    MemorySystemRepository.this.lambda$retrieveInitialUsers$1$MemorySystemRepository(result);
                }
            }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$iiDXfhrg1u-1IK_F_MMrqbcUKOo
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    MemorySystemRepository.this.provideUsers((System.Users) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$bigLamjkuQcof6_50Hc5AJGQhoc
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    MemorySystemRepository.this.provideUsersError((Throwable) obj);
                }
            });
        }
    }

    private void updateCache(System.Locale locale) {
        synchronized (this.lock) {
            if (!this.localesProcessor.hasThrowable() && !this.localesProcessor.hasComplete()) {
                provideLocales(System.Locales.newBuilder(this.localesProcessor.getValue()).setCurrentLocale(locale).mo10084build());
            }
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    public Single<Common.ErrorCode> connectUser(final String str) {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$MemorySystemRepository$gIKq1Tr74RyHyqDuamBECWi81uc
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemorySystemRepository.this.lambda$connectUser$6$MemorySystemRepository(str, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    public Single<Common.ErrorCode> disconnectUser(final String str) {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$MemorySystemRepository$KDVJpUXScKI2hY30H9vDILGtj2M
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemorySystemRepository.this.lambda$disconnectUser$7$MemorySystemRepository(str, result);
            }
        });
    }

    public /* synthetic */ void lambda$connectUser$6$MemorySystemRepository(String str, Producer.Result result) {
        getHandler().handleConnectUser(str, result);
    }

    public /* synthetic */ void lambda$disconnectUser$7$MemorySystemRepository(String str, Producer.Result result) {
        getHandler().handleDisconnectUser(str, result);
    }

    public /* synthetic */ void lambda$notifyDeviceBeingRemoved$9$MemorySystemRepository(Producer.Result result) {
        getHandler().handleRemoveDevice(result);
    }

    public /* synthetic */ void lambda$null$11$MemorySystemRepository(System.Locale locale, Producer.Result result) {
        getHandler().handleSetLocale(locale, result);
    }

    public /* synthetic */ void lambda$queryCurrentUser$4$MemorySystemRepository(Producer.Result result) {
        getHandler().handleGetCurrentUser(result);
    }

    public /* synthetic */ void lambda$queryDavsI18nConfig$2$MemorySystemRepository(Disposable disposable) throws Throwable {
        retrieveInitialI18nConfig();
    }

    public /* synthetic */ void lambda$queryUsers$0$MemorySystemRepository(Disposable disposable) throws Throwable {
        retrieveInitialUsers();
    }

    public /* synthetic */ void lambda$requestResetConnection$10$MemorySystemRepository(int i, boolean z, Producer.Result result) {
        getHandler().handleResetConnection(i, z, result);
    }

    public /* synthetic */ void lambda$requestSwitchUser$5$MemorySystemRepository(int i, Producer.Result result) {
        getHandler().handleSwitchUser(i, result);
    }

    public /* synthetic */ void lambda$retrieveInitialI18nConfig$3$MemorySystemRepository(Producer.Result result) {
        getHandler().handleGetI18nConfig(result);
    }

    public /* synthetic */ void lambda$retrieveInitialUsers$1$MemorySystemRepository(Producer.Result result) {
        getHandler().handleGetUsers(result);
    }

    public /* synthetic */ SingleSource lambda$setLocale$12$MemorySystemRepository(final System.Locale locale, System.Locales locales) throws Throwable {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$MemorySystemRepository$FIb6sWhDpWMFs_nM_5-VEHUVD4Y
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemorySystemRepository.this.lambda$null$11$MemorySystemRepository(locale, result);
            }
        });
    }

    public /* synthetic */ void lambda$setLocale$13$MemorySystemRepository(System.Locale locale, Common.ErrorCode errorCode) throws Throwable {
        if (errorCode == Common.ErrorCode.SUCCESS) {
            updateCache(locale);
        }
    }

    public /* synthetic */ void lambda$unpairUser$8$MemorySystemRepository(String str, Producer.Result result) {
        getHandler().handleUnpairUser(str, result);
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    public Single<Common.ErrorCode> notifyDeviceBeingRemoved() {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$MemorySystemRepository$iB3E0KwqPDPe0POoCk27U51lkiA
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemorySystemRepository.this.lambda$notifyDeviceBeingRemoved$9$MemorySystemRepository(result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemProvider
    public void provideI18nConfig(DavsI18nConfig davsI18nConfig) {
        Preconditions.notNull(davsI18nConfig, "davsI18nConfig");
        if (Logger.shouldLog(Logger.Level.DEBUG)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("provideI18nConfig: ");
            outline107.append(davsI18nConfig.toString());
            Logger.d(TAG, outline107.toString());
        }
        synchronized (this.lock) {
            if (!this.davsI18nSubject.hasThrowable() && !this.davsI18nSubject.hasComplete()) {
                this.davsI18nSubject.onNext(davsI18nConfig);
            }
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemProvider
    public void provideI18nConfigError(Throwable th) {
        synchronized (this.lock) {
            if (!this.davsI18nSubject.hasThrowable() && !this.davsI18nSubject.hasComplete()) {
                this.davsI18nSubject.onError(th);
            }
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemProvider
    public void provideLocales(System.Locales locales) {
        synchronized (this.lock) {
            if (!this.localesProcessor.hasThrowable() && !this.localesProcessor.hasComplete()) {
                this.localesProcessor.onNext(locales);
            }
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemProvider
    public void provideLocalesError(Throwable th) {
        synchronized (this.lock) {
            if (!this.localesProcessor.hasThrowable() && !this.localesProcessor.hasComplete()) {
                this.localesProcessor.onError(th);
            }
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemProvider
    public void provideUsers(System.Users users) {
        Preconditions.notNull(users, "users");
        if (Logger.shouldLog(Logger.Level.DEBUG)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("provideUsers: ");
            outline107.append(LoggerUtils.usersToString(users));
            Logger.d(TAG, outline107.toString());
        }
        synchronized (this.lock) {
            if (!this.usersSubject.hasThrowable() && !this.usersSubject.hasComplete()) {
                this.usersSubject.onNext(users);
            }
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemProvider
    public void provideUsersError(Throwable th) {
        synchronized (this.lock) {
            if (!this.usersSubject.hasThrowable() && !this.usersSubject.hasComplete()) {
                this.usersSubject.onError(th);
            }
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    public Single<System.User> queryCurrentUser() {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$MemorySystemRepository$pET4lxRfuV7LVwgXlvw8vyctV7Q
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemorySystemRepository.this.lambda$queryCurrentUser$4$MemorySystemRepository(result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    public Observable<DavsI18nConfig> queryDavsI18nConfig() {
        return this.davsI18nSubject.doOnSubscribe(new Consumer() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$MemorySystemRepository$R0Wh8IZaegqARgxw83lhTr6vlmg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MemorySystemRepository.this.lambda$queryDavsI18nConfig$2$MemorySystemRepository((Disposable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    public Flowable<System.Locales> queryLocales() {
        return this.localesProcessor.onErrorResumeNext(ObservableUtils.errorIfNotReleased()).distinctUntilChanged();
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    public Observable<System.Users> queryUsers() {
        return this.usersSubject.doOnSubscribe(new Consumer() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$MemorySystemRepository$ABiAzbkopnlqvNsvk4RUxOHX47Q
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MemorySystemRepository.this.lambda$queryUsers$0$MemorySystemRepository((Disposable) obj);
            }
        });
    }

    public void release() {
        synchronized (this.lock) {
            if (!this.usersSubject.hasComplete() && !this.usersSubject.hasThrowable()) {
                this.usersSubject.onError(new ObservableUtils.StreamReleasedException());
            }
            if (!this.localesProcessor.hasComplete() && !this.localesProcessor.hasThrowable()) {
                this.localesProcessor.onError(new ObservableUtils.StreamReleasedException());
            }
            if (!this.davsI18nSubject.hasComplete() && !this.davsI18nSubject.hasThrowable()) {
                this.davsI18nSubject.onError(new ObservableUtils.StreamReleasedException());
            }
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    public Single<Common.ErrorCode> requestResetConnection(final int i, final boolean z) {
        Preconditions.notNegative(i, "timeout");
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$MemorySystemRepository$srXdTgfpHvDHWtJ2MUTzbQuxwzA
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemorySystemRepository.this.lambda$requestResetConnection$10$MemorySystemRepository(i, z, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    public Single<Common.ErrorCode> requestSwitchUser(final int i) {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$MemorySystemRepository$9kxBjb38TWauglaoL7JbioGUJ0o
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemorySystemRepository.this.lambda$requestSwitchUser$5$MemorySystemRepository(i, result);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    public Single<Common.ErrorCode> setLocale(final System.Locale locale) {
        if (!locale.getName().matches(LOCALE_FORMAT)) {
            return Single.just(Common.ErrorCode.INVALID);
        }
        return queryLocales().firstOrError().flatMap(new Function() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$MemorySystemRepository$oS6O4eBJy6vtxgcFvrGLxZfqAFA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MemorySystemRepository.this.lambda$setLocale$12$MemorySystemRepository(locale, (System.Locales) obj);
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$MemorySystemRepository$CdHKrlU1PFU-XPRLLHg-eTAs--4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MemorySystemRepository.this.lambda$setLocale$13$MemorySystemRepository(locale, (Common.ErrorCode) obj);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    public Single<Common.ErrorCode> unpairUser(final String str) {
        return SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.system.-$$Lambda$MemorySystemRepository$KGsHOhMEsqjn_CwI9K-v2o9QRWM
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemorySystemRepository.this.lambda$unpairUser$8$MemorySystemRepository(str, result);
            }
        });
    }
}
