package com.amazon.alexa.accessory.frames;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.frames.connectivity.ConnectivityManager;
import com.amazon.alexa.accessory.frames.eventbus.EventBusManager;
import com.amazon.alexa.accessory.frames.topContact.TopContactManager;
import com.amazon.alexa.accessory.notificationpublisher.UserChangeListener;
import com.amazon.alexa.accessory.notificationpublisher.UserChangeService;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
/* loaded from: classes.dex */
public class TopContact {
    private static final String TAG = "TopContact";
    @VisibleForTesting
    boolean hasInitialized = false;

    public TopContact() {
        if (DependencyProvider.getIdentityService().isAuthenticated(TAG)) {
            initAfterUserAuthenticated();
        }
        registerMainActivityLifecycleObserver();
        addUserChangeListener();
    }

    private synchronized void addUserChangeListener() {
        UserChangeService.getInstance().addListener(new UserChangeListener() { // from class: com.amazon.alexa.accessory.frames.TopContact.1
            @Override // com.amazon.alexa.accessory.notificationpublisher.UserChangeListener
            public void onUserLogin(UserIdentity userIdentity) {
                Log.i(TopContact.TAG, "UserChangeService -- onUserLogin");
                TopContact.this.initAfterUserAuthenticated();
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.UserChangeListener
            public void onUserLogout(UserIdentity userIdentity) {
                Log.i(TopContact.TAG, "UserChangeService -- onUserLogout");
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.UserChangeListener
            public void onUserProfileUpdated(UserIdentity userIdentity) {
                String str = TopContact.TAG;
                Log.d(str, "UserChangeService -- onUserProfileUpdated " + userIdentity);
            }
        });
    }

    private synchronized void registerMainActivityLifecycleObserver() {
        ((MainActivityLifecycleObserverRegistrar) ComponentRegistry.getInstance().get(MainActivityLifecycleObserverRegistrar.class).get()).addObserver(new MainActivityLifecycleObserver() { // from class: com.amazon.alexa.accessory.frames.TopContact.2
            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityCreated() {
                Log.i(TopContact.TAG, "mainActivityLifecycleService -- onActivityCreated");
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityDestroy() {
                Log.i(TopContact.TAG, "mainActivityLifecycleService -- onActivityDestroy");
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityPause() {
                Log.i(TopContact.TAG, "mainActivityLifecycleService -- onActivityPause");
                TopContactManager.getInstance().deleteExpiredTopContactAudio();
                if (!TopContactManager.topContactFlowInprogress.get()) {
                    TopContactManager.getInstance().requestContactDetails();
                }
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityResume() {
                Log.i(TopContact.TAG, "mainActivityLifecycleService -- onActivityResume");
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityStart() {
                Log.i(TopContact.TAG, "mainActivityLifecycleService -- onActivityStart");
            }

            @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
            public void onActivityStop() {
                Log.i(TopContact.TAG, "mainActivityLifecycleService -- onActivityStop");
            }
        });
    }

    synchronized void initAfterUserAuthenticated() {
        if (this.hasInitialized) {
            Log.i(TAG, "initAfterLogin -- Already initialized, skipping.");
            return;
        }
        try {
            ConnectivityManager.initConnectivityModule(DependencyProvider.getContext(), DependencyProvider.getClientAccessories());
            EventBusManager.subscribeToEventBusMessages();
            this.hasInitialized = true;
        } catch (Exception e) {
            Log.w(TAG, "initAfterLogin Failed.", e);
        }
    }
}
