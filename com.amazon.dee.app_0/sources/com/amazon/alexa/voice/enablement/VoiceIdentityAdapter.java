package com.amazon.alexa.voice.enablement;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import java.util.ArrayList;
import java.util.List;
import rx.functions.Action1;
/* loaded from: classes11.dex */
public class VoiceIdentityAdapter {
    private static final String KEY_EULA = "eulaSigned";
    private static final String TAG = "com.amazon.alexa.voice.enablement.VoiceIdentityAdapter";
    private static final String VOICE_STORAGE = "voice-storage";
    private final EventBus eventBus;
    private final IdentityService identityService;
    private final PersistentStorage persistentStorage;
    private final List<Subscriber.SubscriberUuid> subscriberUuids = new ArrayList();

    /* loaded from: classes11.dex */
    public interface UserIdentityChangedListener {
        void onUserChanged(UserIdentity userIdentity);
    }

    /* loaded from: classes11.dex */
    public interface UserReadyForVoiceCallback {
        void onNoUserReadyForVoice();

        void onUserReadyForVoice();
    }

    public VoiceIdentityAdapter(IdentityService identityService, PersistentStorage.Factory factory, EventBus eventBus) {
        this.identityService = identityService;
        this.persistentStorage = factory.create(VOICE_STORAGE);
        this.eventBus = eventBus;
    }

    private void cacheUserEulaState() {
        UserIdentity user = this.identityService.getUser(TAG);
        if (user == null) {
            if (!this.persistentStorage.contains(KEY_EULA)) {
                return;
            }
            this.persistentStorage.edit().remove(KEY_EULA).commit();
            return;
        }
        this.persistentStorage.edit().set(KEY_EULA, user.hasAcceptedEula()).commit();
    }

    public void checkForVoiceCapableUser(final UserReadyForVoiceCallback userReadyForVoiceCallback) {
        if (!this.identityService.isRegistered(TAG)) {
            userReadyForVoiceCallback.onNoUserReadyForVoice();
            return;
        }
        cacheUserEulaState();
        UserIdentity user = this.identityService.getUser(TAG);
        if (user != null) {
            if (isUserReadyForVoice(user)) {
                userReadyForVoiceCallback.onUserReadyForVoice();
            } else {
                this.identityService.refresh(TAG).subscribe(new Action1() { // from class: com.amazon.alexa.voice.enablement.-$$Lambda$VoiceIdentityAdapter$KejFmukxuZxa4JN6m59xlsnbtdk
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        VoiceIdentityAdapter.this.lambda$checkForVoiceCapableUser$1$VoiceIdentityAdapter(userReadyForVoiceCallback, (UserIdentity) obj);
                    }
                }, $$Lambda$VoiceIdentityAdapter$9Oul4WF0HNEbM2BeOibLD0shE.INSTANCE);
            }
        } else if (this.persistentStorage.contains(KEY_EULA) && this.persistentStorage.getBoolean(KEY_EULA)) {
            userReadyForVoiceCallback.onUserReadyForVoice();
        } else {
            UserIdentity user2 = this.identityService.getUser(TAG);
            if (user2 == null) {
                return;
            }
            if (isUserReadyForVoice(user2)) {
                userReadyForVoiceCallback.onUserReadyForVoice();
                this.persistentStorage.edit().set(KEY_EULA, true).commit();
                return;
            }
            userReadyForVoiceCallback.onNoUserReadyForVoice();
        }
    }

    public boolean isUserReadyForVoice(UserIdentity userIdentity) {
        return userIdentity != null && userIdentity.hasAcceptedEula();
    }

    public /* synthetic */ void lambda$checkForVoiceCapableUser$1$VoiceIdentityAdapter(UserReadyForVoiceCallback userReadyForVoiceCallback, UserIdentity userIdentity) {
        if (isUserReadyForVoice(userIdentity)) {
            userReadyForVoiceCallback.onUserReadyForVoice();
        } else {
            userReadyForVoiceCallback.onNoUserReadyForVoice();
        }
    }

    public /* synthetic */ void lambda$registerUserIdentityChange$3$VoiceIdentityAdapter(UserIdentityChangedListener userIdentityChangedListener, Message message) {
        if (userIdentityChangedListener != null) {
            userIdentityChangedListener.onUserChanged(this.identityService.getUser(TAG));
        }
    }

    public /* synthetic */ void lambda$subscribeToUserUpdates$0$VoiceIdentityAdapter(UserReadyForVoiceCallback userReadyForVoiceCallback, Message message) {
        if (isUserReadyForVoice(this.identityService.getUser(TAG))) {
            userReadyForVoiceCallback.onUserReadyForVoice();
        } else {
            userReadyForVoiceCallback.onNoUserReadyForVoice();
        }
    }

    public Subscriber.SubscriberUuid registerUserIdentityChange(final UserIdentityChangedListener userIdentityChangedListener) {
        MultiFilterSubscriber subscriber = this.eventBus.getSubscriber();
        Subscriber.SubscriberUuid subscriberUuid = subscriber.getSubscriberUuid();
        subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.voice.enablement.-$$Lambda$VoiceIdentityAdapter$6xbqq5QML9h6wiSnhtF07nmPmYg
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                VoiceIdentityAdapter.this.lambda$registerUserIdentityChange$3$VoiceIdentityAdapter(userIdentityChangedListener, message);
            }
        });
        return subscriberUuid;
    }

    public void release() {
        for (Subscriber.SubscriberUuid subscriberUuid : this.subscriberUuids) {
            this.eventBus.unsubscribe(subscriberUuid);
        }
        this.subscriberUuids.clear();
    }

    public void releaseIdentitySubscriber(Subscriber.SubscriberUuid subscriberUuid) {
        if (subscriberUuid != null) {
            this.eventBus.unsubscribe(subscriberUuid);
        }
    }

    public void subscribeToUserUpdates(final UserReadyForVoiceCallback userReadyForVoiceCallback) {
        MultiFilterSubscriber subscriber = this.eventBus.getSubscriber();
        Subscriber.SubscriberUuid subscriberUuid = subscriber.getSubscriberUuid();
        subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.voice.enablement.-$$Lambda$VoiceIdentityAdapter$Zb3S3BExL788SclkJ3G9P8h6P1Q
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                VoiceIdentityAdapter.this.lambda$subscribeToUserUpdates$0$VoiceIdentityAdapter(userReadyForVoiceCallback, message);
            }
        });
        this.subscriberUuids.add(subscriberUuid);
    }
}
