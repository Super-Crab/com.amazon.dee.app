package com.amazon.alexa.accessory.avsclient.locale;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.avsclient.locale.AccessoryLocaleSynchronizer;
import com.amazon.alexa.accessory.avsclient.locale.LocaleSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.RedactionUtil;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessorykit.ModelTransformer;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class AccessoryLocaleSynchronizer {
    private static final String TAG = "AccessoryLocaleSynchronizer";
    private System.Locale lastKnownLocale;
    private final Map<String, System.Locale> lastSetLocales;
    private final LocaleListener localeListener;
    private final LocaleSupplier localeSupplier;
    private final SessionListener sessionListener;
    private final SessionSupplier sessionSupplier;
    private final UnmatchedLocaleNotifier unmatchedLocaleNotifier;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class LocaleListener implements LocaleSupplier.Listener {
        private LocaleListener() {
        }

        public /* synthetic */ ObservableSource lambda$onLocale$0$AccessoryLocaleSynchronizer$LocaleListener() throws Throwable {
            return Observable.fromIterable(AccessoryLocaleSynchronizer.this.sessionSupplier.getActiveSessions());
        }

        public /* synthetic */ MaybeSource lambda$onLocale$1$AccessoryLocaleSynchronizer$LocaleListener(System.Locale locale, AccessorySession accessorySession) throws Throwable {
            return AccessoryLocaleSynchronizer.this.setLocaleForSession(accessorySession, locale).onErrorResumeWith(Maybe.empty());
        }

        @Override // com.amazon.alexa.accessory.avsclient.locale.LocaleSupplier.Listener
        @SuppressLint({"CheckResult"})
        public void onLocale(AlexaLocale alexaLocale) {
            Preconditions.mainThread();
            if (alexaLocale == null) {
                Logger.e("%s: A null locale was received. Clearing cache for accessory locale synchronizer.", AccessoryLocaleSynchronizer.TAG);
                AccessoryLocaleSynchronizer.this.lastKnownLocale = null;
                return;
            }
            final System.Locale mo10084build = System.Locale.newBuilder().setName(alexaLocale.getName()).mo10084build();
            if (AccessoryLocaleSynchronizer.this.lastKnownLocale == null) {
                for (AccessorySession accessorySession : AccessoryLocaleSynchronizer.this.sessionSupplier.getActiveSessions()) {
                    Logger.d("%s: Checking if accessory %s supports locale %s", AccessoryLocaleSynchronizer.TAG, accessorySession.getAddress(), mo10084build.getName());
                    AccessoryLocaleSynchronizer.this.unmatchedLocaleNotifier.conditionallyDisplayNotification(accessorySession, mo10084build);
                }
            }
            AccessoryLocaleSynchronizer.this.lastKnownLocale = mo10084build;
            Logger.d("%s: Locale changed. Now trying to set locale to %s for connected sessions", AccessoryLocaleSynchronizer.TAG, mo10084build.getName());
            Observable.defer(new Supplier() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$AccessoryLocaleSynchronizer$LocaleListener$vSHUFnAbjpd-TQnXh5BhiMRd2Y8
                @Override // io.reactivex.rxjava3.functions.Supplier
                /* renamed from: get */
                public final Object mo10365get() {
                    return AccessoryLocaleSynchronizer.LocaleListener.this.lambda$onLocale$0$AccessoryLocaleSynchronizer$LocaleListener();
                }
            }).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$AccessoryLocaleSynchronizer$LocaleListener$GVvqH0wDneM90fbiPafPvn_R8Kw
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return AccessoryLocaleSynchronizer.LocaleListener.this.lambda$onLocale$1$AccessoryLocaleSynchronizer$LocaleListener(mo10084build, (AccessorySession) obj);
                }
            }).toList().ignoreElement().subscribe(new Action() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$AccessoryLocaleSynchronizer$LocaleListener$gm9Xi3tCk9E_T4OI3ZTJd2fEBwo
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    Logger.d("%s: Finished setting locale to %s for connected sessions", AccessoryLocaleSynchronizer.TAG, System.Locale.this.getName());
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$AccessoryLocaleSynchronizer$LocaleListener$qPEdxayBw1f7LszkdrLrhlDKv7Q
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    Logger.e("%s: Failed to set locale to %s for connected sessions. This should not happen.", (Throwable) obj, AccessoryLocaleSynchronizer.TAG, System.Locale.this.getName());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SessionListener extends AccessorySessionListener {
        private SessionListener() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onAccessorySessionConnected$1(Accessory accessory, System.Locale locale, Throwable th) throws Throwable {
            Logger.d("%s: ERROR: Setting locale to %s for a newly connected session with %s resulted in an error. ", th, AccessoryLocaleSynchronizer.TAG, accessory, locale.getName());
            Logger.e("%s: Setting locale to %s for a newly connected session with %s resulted in an error. ", th, AccessoryLocaleSynchronizer.TAG, RedactionUtil.redact(accessory), locale.getName());
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        @SuppressLint({"CheckResult"})
        public void onAccessorySessionConnected(final Accessory accessory) {
            Preconditions.mainThread();
            Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
            AccessorySession session = AccessoryLocaleSynchronizer.this.sessionSupplier.getSession(accessory);
            if (AccessoryLocaleSynchronizer.this.lastKnownLocale != null) {
                final System.Locale locale = AccessoryLocaleSynchronizer.this.lastKnownLocale;
                Logger.d("%s: Setting locale to %s for a newly connected session with %s", AccessoryLocaleSynchronizer.TAG, locale.getName(), accessory);
                AccessoryLocaleSynchronizer.this.setLocaleForSession(session, locale).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$AccessoryLocaleSynchronizer$SessionListener$zNGTG4Ty0-bjeJRmCs8NFLiRM08
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Object obj) {
                        Logger.d("%s: Setting locale to %s for a newly connected session with %s resulted in error code %s", AccessoryLocaleSynchronizer.TAG, System.Locale.this.getName(), accessory, (Common.ErrorCode) obj);
                    }
                }, new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$AccessoryLocaleSynchronizer$SessionListener$fg1xvliLXgzFbbSzjbHZDXQ-QTo
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Object obj) {
                        AccessoryLocaleSynchronizer.SessionListener.lambda$onAccessorySessionConnected$1(Accessory.this, locale, (Throwable) obj);
                    }
                });
                if (session == null) {
                    return;
                }
                AccessoryLocaleSynchronizer.this.unmatchedLocaleNotifier.conditionallyDisplayNotification(session, locale);
                return;
            }
            Logger.d("%s: A new session connected, but no current locale is known. Will syncrhonize when a locale becomes available.", AccessoryLocaleSynchronizer.TAG);
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionReleased(Accessory accessory) {
            Preconditions.mainThread();
            Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
            AccessoryLocaleSynchronizer.this.lastSetLocales.remove(accessory.getAddress());
        }
    }

    public AccessoryLocaleSynchronizer(LocaleSupplier localeSupplier, SessionSupplier sessionSupplier, UnmatchedLocaleNotifier unmatchedLocaleNotifier) {
        Preconditions.notNull(localeSupplier, "localeSupplier");
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(unmatchedLocaleNotifier, "unmatchedLocaleNotifier");
        this.localeSupplier = localeSupplier;
        this.sessionSupplier = sessionSupplier;
        this.unmatchedLocaleNotifier = unmatchedLocaleNotifier;
        this.sessionListener = new SessionListener();
        this.localeListener = new LocaleListener();
        this.lastSetLocales = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(System.Locale locale, AccessorySession accessorySession, Throwable th) throws Throwable {
        Logger.d("%s: ERROR: Failed to set locale to %s for session with %s", th, TAG, locale.getName(), accessorySession.getConnectedAccessory());
        Logger.e("%s: Failed to set locale to %s for session with %s", th, TAG, locale.getName(), RedactionUtil.redact(accessorySession.getConnectedAccessory()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Maybe<Common.ErrorCode> setLocaleForSession(final AccessorySession accessorySession, final System.Locale locale) {
        return Maybe.defer(new Supplier() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$AccessoryLocaleSynchronizer$JW-d7W1hBMdo2lKkwWZaS5EIHTI
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return AccessoryLocaleSynchronizer.this.lambda$setLocaleForSession$2$AccessoryLocaleSynchronizer(accessorySession, locale);
            }
        });
    }

    public void activate() {
        Preconditions.mainThread();
        this.unmatchedLocaleNotifier.activate();
        this.sessionSupplier.addSessionListener(this.sessionListener);
        this.localeSupplier.registerLocaleListener(this.localeListener);
    }

    public /* synthetic */ MaybeSource lambda$setLocaleForSession$2$AccessoryLocaleSynchronizer(final AccessorySession accessorySession, final System.Locale locale) throws Throwable {
        Preconditions.mainThread();
        System.Locale locale2 = this.lastSetLocales.get(accessorySession.getAccessory().getAddress());
        if (locale.equals(locale2)) {
            Logger.d("%s: Will not attempt to set locale to %s for %s since it has already been requested.", TAG, locale2.getName(), accessorySession.getAccessory());
            return Maybe.empty();
        }
        this.lastSetLocales.put(accessorySession.getAccessory().getAddress(), locale);
        return accessorySession.getSystemRepository().setLocale(locale).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$AccessoryLocaleSynchronizer$pBxAZfBvz0ddK0o-7kC1otaKOPY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryLocaleSynchronizer.lambda$null$0(System.Locale.this, accessorySession, (Throwable) obj);
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$AccessoryLocaleSynchronizer$e-0-WhQFGfjVxbplX00nr7oM8uo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Logger.d("%s: Setting locale to %s for session with %s resulted in error code %s", AccessoryLocaleSynchronizer.TAG, System.Locale.this.getName(), accessorySession.getConnectedAccessory(), (Common.ErrorCode) obj);
            }
        }).toMaybe();
    }
}
