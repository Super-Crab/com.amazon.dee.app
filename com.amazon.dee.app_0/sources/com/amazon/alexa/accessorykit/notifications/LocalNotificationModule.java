package com.amazon.alexa.accessorykit.notifications;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.notifications.LocalNotification;
import com.amazon.alexa.accessory.notifications.NotificationInteractor;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes6.dex */
public final class LocalNotificationModule {
    private static final String DEEP_LINK_KEY = "deepLink";
    private static final String TEXT_KEY = "text";
    private static final String TITLE_KEY = "title";
    private final NotificationInteractor interactor;

    public LocalNotificationModule(ReactApplicationContext reactApplicationContext) {
        this(new DefaultNotificationInteractor(reactApplicationContext.getApplicationContext()));
    }

    public void areNotificationsEnabled(Promise promise) {
        try {
            boolean areNotificationsEnabled = this.interactor.areNotificationsEnabled();
            Logger.e("LocalNotificationModule checked if notifications are enabled. Enabled: %s", Boolean.valueOf(areNotificationsEnabled));
            promise.resolve(Boolean.valueOf(areNotificationsEnabled));
        } catch (Throwable th) {
            Logger.e("LocalNotificationModule cannot check if notifications are enabled", th);
            promise.reject(th.toString(), th);
        }
    }

    public void cancel(int i, Promise promise) {
        try {
            Logger.d("LocalNotificationModule will cancel notification with id: %d", Integer.valueOf(i));
            this.interactor.cancel(i);
            promise.resolve(null);
        } catch (Throwable th) {
            Logger.e("LocalNotificationModule cannot cancel notification with id: %d", th, Integer.valueOf(i));
            promise.reject(th.toString(), th);
        }
    }

    public void show(int i, ReadableMap readableMap, Promise promise) {
        try {
            Logger.d("LocalNotificationModule will show notification with id: %d notification: %s", Integer.valueOf(i), readableMap.toString());
            this.interactor.show(i, LocalNotification.newBuilder().setDeepLink(readableMap.getString("deepLink")).setTitle(readableMap.getString("title")).setText(readableMap.getString("text")).build());
            promise.resolve(null);
        } catch (Throwable th) {
            Logger.e("LocalNotificationModule cannot show notification with id: %d notification:%s", th, Integer.valueOf(i), readableMap.toString());
            promise.reject(th.toString(), th);
        }
    }

    public LocalNotificationModule(NotificationInteractor notificationInteractor) {
        Preconditions.notNull(notificationInteractor, "interactor");
        this.interactor = notificationInteractor;
    }
}
