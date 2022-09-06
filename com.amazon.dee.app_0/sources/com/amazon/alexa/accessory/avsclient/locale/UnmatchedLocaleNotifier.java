package com.amazon.alexa.accessory.avsclient.locale;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.R;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.notifications.LocalNotification;
import com.amazon.alexa.accessory.notifications.NotificationInteractor;
import com.amazon.alexa.accessory.persistence.FileBackedJsonRxMapStore;
import com.amazon.alexa.accessory.persistence.RxMapStore;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.google.common.collect.Lists;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Function4;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class UnmatchedLocaleNotifier {
    private static final Set<String> BLOCKLISTED_DEVICE_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList("A303PJF6ISQ7IC", "A3HND3J60V1OXX", "A1388YOZ88W373", "A13W6HQIHKEN3Z")));
    private static final String DEEP_LINK_LOCALE_PICKER = "voice/language-settings";
    private static final String ECHO_AUTO = "A303PJF6ISQ7IC";
    public static final String G = "A13W6HQIHKEN3Z";
    private static final String HARK = "A3HND3J60V1OXX";
    public static final String INTENT_ACTION = "com.amazon.alexa.accessory.LOCALE_NOTIFIED";
    public static final String INTENT_EXTRA_ADDRESS_KEY = "address";
    public static final String INTENT_EXTRA_LOCALE_KEY = "locale";
    public static final String INTENT_PACKAGE = "com.amazon.dee.app";
    private static final String M = "A1388YOZ88W373";
    private static final long NOTIFICATION_DELAY_SECONDS = 1;
    private static final String PREFERENCES_RELATIVE_PATH = "accessories/localeNotifications.json";
    private static final String TAG = "UnmatchedLocaleNotifier";
    private final BroadcastReceiver broadcastReceiver;
    private final Context context;
    private final DeviceSupplierV2 deviceSupplier;
    private final FeatureChecker featureChecker;
    private boolean isActive = false;
    private final NotificationInteractor notificationInteractor;
    private final RxMapStore<String, LocaleNotifiedData> notificationsDisplayedStore;
    private final UserSupplier userSupplier;

    /* loaded from: classes.dex */
    class LocaleNotifiedBroadcastReceiver extends BroadcastReceiver {
        LocaleNotifiedBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String string = intent.getExtras().getString("address");
            String string2 = intent.getExtras().getString("locale");
            Logger.d("%s: Received broadcast that a notification was shown for accessory '%s' and locale '%s'.", UnmatchedLocaleNotifier.TAG, string, string2);
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                UnmatchedLocaleNotifier.this.handleNotificationDisplayed(string, System.Locale.newBuilder().setName(string2).mo10084build());
            } else {
                Logger.d("%s: Cannot continue because address or locale is null or empty.", UnmatchedLocaleNotifier.TAG);
            }
        }
    }

    public UnmatchedLocaleNotifier(Context context, NotificationInteractor notificationInteractor, FeatureChecker featureChecker, UserSupplier userSupplier, DeviceSupplierV2 deviceSupplierV2) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(notificationInteractor, "notificationInteractor");
        Preconditions.notNull(featureChecker, "featureChecker");
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(deviceSupplierV2, "deviceSupplier");
        this.context = context;
        this.notificationInteractor = notificationInteractor;
        this.featureChecker = featureChecker;
        this.userSupplier = userSupplier;
        this.deviceSupplier = deviceSupplierV2;
        this.broadcastReceiver = new LocaleNotifiedBroadcastReceiver();
        this.notificationsDisplayedStore = new FileBackedJsonRxMapStore(new File(context.getFilesDir(), PREFERENCES_RELATIVE_PATH), LocaleNotifiedData.FACTORY, "localeNotifications", "directedId", "addressLocales");
    }

    private LocalNotification createLocalNotification(String str) {
        return LocalNotification.newBuilder().setTitle(this.context.getString(R.string.accessory_notification_mismatch_language_title, str)).setText(this.context.getString(R.string.accessory_notification_mismatch_language_content)).setDeepLink("voice/language-settings").build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: findElementsForAddress */
    public List<LocaleNotifiedData> lambda$null$12$UnmatchedLocaleNotifier(Set<LocaleNotifiedData> set, String str) {
        ArrayList newArrayList = Lists.newArrayList();
        for (LocaleNotifiedData localeNotifiedData : set) {
            if (localeNotifiedData.getAddress().equals(str)) {
                newArrayList.add(localeNotifiedData);
            }
        }
        return newArrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SingleSource lambda$handleNotificationDisplayed$11(User user) throws Throwable {
        return user == User.ABSENT ? Single.error(new IllegalStateException("User is absent!")) : Single.just(user.getDirectedCustomerId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$isBlocklisted$6(Set set) throws Throwable {
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            Device.DeviceInformation deviceInformation = (Device.DeviceInformation) it2.next();
            if (BLOCKLISTED_DEVICE_TYPES.contains(deviceInformation.getDeviceType())) {
                Logger.d("%s: Device is blocklisted for notification because it is of type %s", TAG, deviceInformation.getDeviceType());
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$null$8(String str, System.Locale locale, Set set) throws Throwable {
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            LocaleNotifiedData localeNotifiedData = (LocaleNotifiedData) it2.next();
            Logger.d("%s: Previously notified for " + localeNotifiedData, TAG);
            if (localeNotifiedData.getAddress().equals(str) && localeNotifiedData.getLocale().equals(locale)) {
                return true;
            }
        }
        Logger.d("%s: We have not notified for %s and %s", TAG, str, locale.getName());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: removeElementsFromStore */
    public List<Completable> lambda$null$13$UnmatchedLocaleNotifier(List<LocaleNotifiedData> list, String str) {
        ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(list.size());
        for (LocaleNotifiedData localeNotifiedData : list) {
            Logger.d("%s: Removing element %s from the notification store.", TAG, localeNotifiedData);
            newArrayListWithCapacity.add(this.notificationsDisplayedStore.delete(str, localeNotifiedData));
        }
        return newArrayListWithCapacity;
    }

    public void activate() {
        Preconditions.mainThread();
        if (this.isActive) {
            return;
        }
        this.userSupplier.queryUser().subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$23xu9mMLr1STZVxlm74Zq9o22oc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                UnmatchedLocaleNotifier.this.lambda$activate$0$UnmatchedLocaleNotifier((User) obj);
            }
        });
        this.context.registerReceiver(this.broadcastReceiver, new IntentFilter(INTENT_ACTION), "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION", null);
        this.isActive = true;
    }

    @SuppressLint({"CheckResult"})
    public Single<Boolean> conditionallyDisplayNotification(final AccessorySession accessorySession, final System.Locale locale) {
        Preconditions.notNull(accessorySession, "session");
        Preconditions.notNull(locale, "voxLocale");
        Preconditions.precondition(this.isActive, "This class must be activated before using it!");
        Logger.d("%s: Conditionally displaying notification for locale %s", TAG, locale.getName());
        Single<Boolean> cache = Single.zip(accessorySession.getSystemRepository().queryLocales().firstOrError(), isLastShownLocale(accessorySession.getAddress(), locale), hasDeviceBeenSetUp(accessorySession.getAddress()), isBlocklisted(accessorySession), new Function4() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$ODWkuMF1vmrz-CHQ0XFC7GSA5g8
            @Override // io.reactivex.rxjava3.functions.Function4
            public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
                return UnmatchedLocaleNotifier.this.lambda$conditionallyDisplayNotification$1$UnmatchedLocaleNotifier(accessorySession, locale, (System.Locales) obj, (Boolean) obj2, (Boolean) obj3, (Boolean) obj4);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$_AmcpdI_cReHfe_cgzUPa8P3hHY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return UnmatchedLocaleNotifier.this.lambda$conditionallyDisplayNotification$2$UnmatchedLocaleNotifier(accessorySession, (Boolean) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$jaKUJ6uqvSzsjWWIwlSdvR3FudE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return UnmatchedLocaleNotifier.this.lambda$conditionallyDisplayNotification$3$UnmatchedLocaleNotifier(locale, accessorySession, (Boolean) obj);
            }
        }).cache();
        cache.subscribe($$Lambda$UnmatchedLocaleNotifier$kMaRt9n_jTVU4g6QnOzGaQzfUTk.INSTANCE, $$Lambda$UnmatchedLocaleNotifier$16LdZpdaJQkTGYm41NdKDvVEctA.INSTANCE);
        return cache;
    }

    @VisibleForTesting
    Single<Boolean> displayNotification(Accessory accessory) {
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        if (this.notificationInteractor.areNotificationsEnabled()) {
            final LocalNotification createLocalNotification = createLocalNotification(accessory.getName());
            final int hashCode = accessory.getAddress().hashCode();
            return Completable.complete().delay(1L, TimeUnit.SECONDS).doOnComplete(new Action() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$2sbu3UOq4CQJubstlZsJsZjndY8
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    UnmatchedLocaleNotifier.this.lambda$displayNotification$10$UnmatchedLocaleNotifier(hashCode, createLocalNotification);
                }
            }).toSingleDefault(true);
        }
        return Single.just(false);
    }

    @SuppressLint({"CheckResult"})
    @VisibleForTesting
    void handleNotificationDisplayed(final String str, final System.Locale locale) {
        Preconditions.notEmpty(str, "address");
        Preconditions.notNull(locale, "locale");
        this.userSupplier.queryUser().firstOrError().flatMap($$Lambda$UnmatchedLocaleNotifier$WsacaHqjHVvZim2winOagmmCQ.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$BUkK5VGjlUM2Wr8nBbokOb-ZTeQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                UnmatchedLocaleNotifier.this.lambda$handleNotificationDisplayed$16$UnmatchedLocaleNotifier(str, locale, (String) obj);
            }
        });
    }

    @VisibleForTesting
    Single<Boolean> hasDeviceBeenSetUp(String str) {
        Preconditions.notEmpty(str, "address");
        return this.deviceSupplier.hasDeviceGroup(str);
    }

    Single<Boolean> isBlocklisted(AccessorySession accessorySession) {
        return accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().map($$Lambda$UnmatchedLocaleNotifier$TK3nZlFZkVsuAfcVqLs1opaBqWQ.INSTANCE).onErrorReturn($$Lambda$UnmatchedLocaleNotifier$y2cJzYsybBYdDnFWXIlLxgnSkAI.INSTANCE);
    }

    @VisibleForTesting
    Single<Boolean> isLastShownLocale(final String str, final System.Locale locale) {
        Preconditions.notEmpty(str, "address");
        Preconditions.notNull(locale, "locale");
        return this.userSupplier.queryUser().firstOrError().flatMap(new Function() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$K1LkFjgnE1hen0U2opYE4kN4HTw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return UnmatchedLocaleNotifier.this.lambda$isLastShownLocale$9$UnmatchedLocaleNotifier(str, locale, (User) obj);
            }
        });
    }

    public /* synthetic */ Boolean lambda$conditionallyDisplayNotification$1$UnmatchedLocaleNotifier(AccessorySession accessorySession, System.Locale locale, System.Locales locales, Boolean bool, Boolean bool2, Boolean bool3) throws Throwable {
        return Boolean.valueOf(shouldDisplayNotification(accessorySession.getAddress(), locale, locales.getSupportedLocalesList(), bool.booleanValue(), bool2.booleanValue(), bool3.booleanValue()));
    }

    public /* synthetic */ SingleSource lambda$conditionallyDisplayNotification$2$UnmatchedLocaleNotifier(AccessorySession accessorySession, Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            return displayNotification(accessorySession.getAccessory());
        }
        return Single.just(false);
    }

    public /* synthetic */ Boolean lambda$conditionallyDisplayNotification$3$UnmatchedLocaleNotifier(System.Locale locale, AccessorySession accessorySession, Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            Logger.d("%s: A notification was shown because locale %s isn't supported on device %s", TAG, locale.getName(), accessorySession.getAddress());
            handleNotificationDisplayed(accessorySession.getAddress(), locale);
        }
        return bool;
    }

    public /* synthetic */ void lambda$displayNotification$10$UnmatchedLocaleNotifier(int i, LocalNotification localNotification) throws Throwable {
        this.notificationInteractor.show(i, localNotification);
    }

    public /* synthetic */ void lambda$handleNotificationDisplayed$16$UnmatchedLocaleNotifier(final String str, final System.Locale locale, final String str2) throws Throwable {
        this.notificationsDisplayedStore.get(str2).map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$4vRH-M4IRDchAtPU15GACBHCf7Y
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return UnmatchedLocaleNotifier.this.lambda$null$12$UnmatchedLocaleNotifier(str, (Set) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$8zoBT30shzm3HiHkqdmocJV_P2c
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return UnmatchedLocaleNotifier.this.lambda$null$13$UnmatchedLocaleNotifier(str2, (List) obj);
            }
        }).flatMapCompletable($$Lambda$2vbaKQwBXPbDE2k6nSJKg20RnA.INSTANCE).andThen(this.notificationsDisplayedStore.put(str2, new LocaleNotifiedData(str, locale))).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$F5OEisT8XSu_yo45uJ5AZgx0n6M
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                LocaleNotifiedData localeNotifiedData = (LocaleNotifiedData) obj;
                Logger.d("%s: Persisted that we notified on %s.", UnmatchedLocaleNotifier.TAG, System.Locale.this.getName());
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$2EMNnVqMSHncRJhQiW4Iv9dv9qM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                Logger.d("%s: Unable to persist that we notified on %s", UnmatchedLocaleNotifier.TAG, System.Locale.this.getName());
            }
        });
    }

    public /* synthetic */ SingleSource lambda$isLastShownLocale$9$UnmatchedLocaleNotifier(final String str, final System.Locale locale, User user) throws Throwable {
        if (user == User.ABSENT) {
            Logger.d("%s: No user, the notification for locale was not previously shown", TAG);
            return Single.just(false);
        }
        return this.notificationsDisplayedStore.get(user.getDirectedCustomerId()).map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.locale.-$$Lambda$UnmatchedLocaleNotifier$fdczpTp5lM2kmTLQEu_OogStaCM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return UnmatchedLocaleNotifier.lambda$null$8(str, locale, (Set) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"CheckResult"})
    @VisibleForTesting
    /* renamed from: onUserChanged */
    public void lambda$activate$0$UnmatchedLocaleNotifier(User user) {
        if (user != User.ABSENT) {
            return;
        }
        this.notificationsDisplayedStore.clear().subscribe($$Lambda$UnmatchedLocaleNotifier$i6kJ6VUX9wKkITZp7Hi29W3ylEw.INSTANCE);
    }

    @VisibleForTesting
    boolean shouldDisplayNotification(String str, System.Locale locale, List<System.Locale> list, boolean z, boolean z2, boolean z3) {
        Preconditions.notEmpty(str, "address");
        Preconditions.notNull(locale, "voxLocale");
        Preconditions.notNull(list, "supportedLocales");
        if (!this.featureChecker.hasAccess(AccessoryFeature.ALEXA_VOX_ANDROID_LOCALE_SETTING)) {
            Logger.d("%s: User is not in the locale experimental group.", TAG);
            return false;
        } else if (!z2) {
            Logger.d("%s: Device has not been set up; suppressing the locale notification", TAG);
            return false;
        } else if (z) {
            Logger.d("%s: Locale mismatch notification has already been shown for %s", TAG, locale.getName());
            return false;
        } else if (z3) {
            Logger.d("%s: Device is blocklisted from the locale notification.", TAG);
            return false;
        } else {
            boolean contains = list.contains(locale);
            if (contains) {
                Logger.d("%s: Locale mismatch notification will not be shown because %s is supported.", TAG, locale.getName());
            }
            return !contains;
        }
    }
}
