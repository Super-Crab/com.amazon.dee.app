package com.amazon.alexa.accessory.notificationpublisher;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.UserIdentity;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes.dex */
public final class UserChangeService {
    private static final String TAG = "UserChangeService";
    private static UserChangeService listenerInstance;
    @VisibleForTesting
    UserIdentity currentUser = null;
    @VisibleForTesting
    Set<UserChangeListener> listenerSet = new HashSet();
    @VisibleForTesting
    MultiFilterSubscriber userChangedSubscriber;

    private UserChangeService() {
    }

    public static synchronized UserChangeService getInstance() {
        UserChangeService userChangeService;
        synchronized (UserChangeService.class) {
            if (listenerInstance == null) {
                Log.d(TAG, "getInstance - Creating new instance");
                listenerInstance = new UserChangeService();
            }
            userChangeService = listenerInstance;
        }
        return userChangeService;
    }

    public static synchronized void releaseInstance() {
        synchronized (UserChangeService.class) {
            Log.d(TAG, "releaseInstance");
            if (listenerInstance != null) {
                listenerInstance.stop();
                listenerInstance.currentUser = null;
                listenerInstance.listenerSet.clear();
                listenerInstance.listenerSet = null;
                listenerInstance = null;
            }
        }
    }

    public boolean addListener(@NonNull UserChangeListener userChangeListener) {
        return this.listenerSet.add(userChangeListener);
    }

    public /* synthetic */ void lambda$start$0$UserChangeService(Message message) {
        Log.i(TAG, "Received IDENTITY_CHANGED event");
        onHandlerIdentityChangedEvent();
    }

    @VisibleForTesting
    void onHandlerIdentityChangedEvent() {
        UserIdentity user = DependencyProvider.getIdentityService().getUser(TAG);
        try {
            try {
                if (this.currentUser == null) {
                    if (user != null) {
                        Log.i(TAG, "User login");
                        for (UserChangeListener userChangeListener : this.listenerSet) {
                            userChangeListener.onUserLogin(user);
                        }
                    }
                } else if (user == null) {
                    Log.i(TAG, "User logout");
                    for (UserChangeListener userChangeListener2 : this.listenerSet) {
                        userChangeListener2.onUserLogout(this.currentUser);
                    }
                } else if (this.currentUser.getDirectedId().equals(user.getDirectedId())) {
                    Log.i(TAG, "User profile updated");
                    for (UserChangeListener userChangeListener3 : this.listenerSet) {
                        userChangeListener3.onUserProfileUpdated(user);
                    }
                } else {
                    Log.d(TAG, String.format(Locale.US, "User switched without logout! before: %s, after: %s", this.currentUser.getDirectedId(), user.getDirectedId()));
                }
            } catch (Exception e) {
                Log.e(TAG, "Failed to handle IDENTITY_CHANGED event.", e);
            }
        } finally {
            this.currentUser = user;
        }
    }

    public boolean removeListener(@NonNull UserChangeListener userChangeListener) {
        return this.listenerSet.remove(userChangeListener);
    }

    public void start() {
        this.currentUser = DependencyProvider.getIdentityService().getUser(TAG);
        this.userChangedSubscriber = DependencyProvider.getEventBus().getSubscriber();
        this.userChangedSubscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$UserChangeService$C35wtjoJ4RCxHG57mCjT14xsh2w
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                UserChangeService.this.lambda$start$0$UserChangeService(message);
            }
        });
    }

    public void stop() {
        if (this.userChangedSubscriber != null) {
            DependencyProvider.getEventBus().unsubscribe(this.userChangedSubscriber);
            this.userChangedSubscriber = null;
        }
    }
}
