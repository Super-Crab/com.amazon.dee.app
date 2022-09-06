package com.amazon.alexa.handsfree.metrics.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class IdentityServiceManager {
    private static final String TAG = "IdentityServiceManager";
    private static IdentityServiceManager sInstance;
    private final IdentityService mIdentityService;

    /* loaded from: classes8.dex */
    public interface UserChangeListener {
        void onUserChanged(@Nullable UserIdentity userIdentity);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public IdentityServiceManager(@Nullable IdentityService identityService) {
        this.mIdentityService = identityService;
    }

    public void addUserChangeListener(@NonNull final UserChangeListener userChangeListener) {
        if (this.mIdentityService == null) {
            Log.w(TAG, "IdentityService could not be found.");
        } else {
            provideEventBus().getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.handsfree.metrics.utils.IdentityServiceManager.1
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public void handle(Message message) {
                    userChangeListener.onUserChanged(IdentityServiceManager.this.mIdentityService.getUser(IdentityServiceManager.TAG));
                }
            });
        }
    }

    @Nullable
    public UserIdentity getUser() {
        IdentityService identityService = this.mIdentityService;
        if (identityService != null) {
            return identityService.getUser(TAG);
        }
        return null;
    }

    public boolean hasAcceptedEula() {
        UserIdentity user;
        IdentityService identityService = this.mIdentityService;
        return (identityService == null || (user = identityService.getUser(TAG)) == null || !user.hasAcceptedEula()) ? false : true;
    }

    @NonNull
    @VisibleForTesting
    EventBus provideEventBus() {
        return (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
    }
}
