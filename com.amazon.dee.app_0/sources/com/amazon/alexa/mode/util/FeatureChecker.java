package com.amazon.alexa.mode.util;

import android.util.Log;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes9.dex */
public class FeatureChecker {
    private static final String TAG = "FeatureChecker";
    private MultiFilterSubscriber userChangedSubscriber;
    private UserLogoutListener userLogoutListener;

    /* loaded from: classes9.dex */
    public interface UserLogoutListener {
        void onLogout();
    }

    public void init() {
        final IdentityService identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        if (identityService != null && eventBus != null) {
            updateStateAndNotifyObservers(identityService.getUser(FeatureChecker.class.getSimpleName()));
            this.userChangedSubscriber = eventBus.getSubscriber();
            this.userChangedSubscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.mode.util.-$$Lambda$FeatureChecker$a9O0gitPCG3LIp4E_qNb7MGBT_g
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    FeatureChecker.this.lambda$init$0$FeatureChecker(identityService, message);
                }
            });
            return;
        }
        Log.e(TAG, "Error initializing FeatureChecker | identity service or event bus are null");
    }

    public /* synthetic */ void lambda$init$0$FeatureChecker(IdentityService identityService, Message message) {
        Log.i(TAG, "onUserChanged");
        updateStateAndNotifyObservers(identityService.getUser(FeatureChecker.class.getSimpleName()));
    }

    public void subscribeForUserLogout(UserLogoutListener userLogoutListener) {
        this.userLogoutListener = userLogoutListener;
    }

    public void unInit() {
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        if (eventBus == null) {
            Log.e(TAG, "Error uninitializing FeatureChecker | event bus is null");
            return;
        }
        MultiFilterSubscriber multiFilterSubscriber = this.userChangedSubscriber;
        if (multiFilterSubscriber == null) {
            return;
        }
        eventBus.unsubscribe(multiFilterSubscriber);
    }

    void updateStateAndNotifyObservers(UserIdentity userIdentity) {
        UserLogoutListener userLogoutListener;
        if (userIdentity != null || (userLogoutListener = this.userLogoutListener) == null) {
            return;
        }
        userLogoutListener.onLogout();
    }
}
