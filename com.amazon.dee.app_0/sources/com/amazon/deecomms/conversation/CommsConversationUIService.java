package com.amazon.deecomms.conversation;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsUIDelegateBase;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.services.ConversationUIService;
import rx.Subscription;
/* loaded from: classes12.dex */
public class CommsConversationUIService implements ConversationUIService {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsConversationUIService.class);
    CommsDeviceSupport commsDeviceSupport;
    CommsManager commsManager;
    CommsUIDelegateBase commsUIDelegate;
    IdentityService identityService;
    private EventBus mEventBus;
    private MultiFilterSubscriber.FilterUuid userChangedEventSubscription;
    Subscription userChangedSubscription;

    public CommsConversationUIService(IdentityService identityService, CommsManager commsManager, CommsUIDelegateBase commsUIDelegateBase, CommsDeviceSupport commsDeviceSupport, EventBus eventBus) {
        this.identityService = identityService;
        this.commsManager = commsManager;
        this.commsUIDelegate = commsUIDelegateBase;
        this.commsDeviceSupport = commsDeviceSupport;
        this.mEventBus = eventBus;
    }

    @Override // com.amazon.deecomms.services.ConversationUIService
    public void handleAudioMessageRecordingInterruption() {
        this.commsManager.handleInterruptedAudioMessageRecording();
    }

    @Override // com.amazon.deecomms.services.ConversationUIService
    public boolean isContactListAtTopOfBackstack(RouteContext[] routeContextArr) {
        Bundle bundle;
        if (routeContextArr.length == 0) {
            return false;
        }
        RouteContext routeContext = routeContextArr[routeContextArr.length - 1];
        if (!RouteName.CONVERSATIONS_CONTACT_LIST.equals(routeContext.getRoute().getName()) || (bundle = routeContext.getBundle("arguments")) == null) {
            return false;
        }
        return Constants.FRAGMENT_CONTACT_CARD.equals(bundle.getString(Constants.CONTACT_LIST_LAUNCH_FRAGMENT));
    }

    public /* synthetic */ void lambda$start$1$CommsConversationUIService(FragmentManager fragmentManager, Fragment fragment, Message message) {
        userChangedCallback(fragmentManager, fragment, this.identityService.getUser(CommsConversationUIService.class.getName()));
    }

    protected void removeConversationFragmentOnLogout(FragmentManager fragmentManager, Fragment fragment) {
        if (fragment != null) {
            LOG.d("Removing comms fragment following sign-out.");
            fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
        }
    }

    @Override // com.amazon.deecomms.services.ConversationUIService
    public void start(final FragmentManager fragmentManager, final Fragment fragment) {
        if (!this.commsDeviceSupport.check()) {
            LOG.d("Comms not supported.");
            return;
        }
        this.commsManager.setCommsUIDelegate(this.commsUIDelegate);
        this.userChangedEventSubscription = this.mEventBus.getSubscriber().subscribeFilter($$Lambda$CommsConversationUIService$HCNh4Sy2bo3Q9it3bqfnNvTHp5o.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.conversation.-$$Lambda$CommsConversationUIService$42J4tnEk2Estss4Ovjjbk55xh8o
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                CommsConversationUIService.this.lambda$start$1$CommsConversationUIService(fragmentManager, fragment, message);
            }
        });
    }

    @Override // com.amazon.deecomms.services.ConversationUIService
    public void stop() {
        if (!this.commsDeviceSupport.check()) {
            LOG.d("Comms not supported.");
            return;
        }
        this.commsManager.clearCommsUIDelegate();
        Subscription subscription = this.userChangedSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
            this.userChangedSubscription = null;
        }
        if (this.userChangedEventSubscription == null) {
            return;
        }
        this.mEventBus.getSubscriber().unsubscribeFilter(this.userChangedEventSubscription);
        this.userChangedEventSubscription = null;
    }

    @VisibleForTesting
    void userChangedCallback(FragmentManager fragmentManager, Fragment fragment, @Nullable UserIdentity userIdentity) {
        if (userIdentity == null) {
            removeConversationFragmentOnLogout(fragmentManager, fragment);
        }
    }

    public CommsConversationUIService(IdentityService identityService, CommsManager commsManager, CommsUIDelegateBase commsUIDelegateBase, CommsDeviceSupport commsDeviceSupport) {
        this(identityService, commsManager, commsUIDelegateBase, commsDeviceSupport, CommsDaggerWrapper.getComponent().getEventBus());
    }
}
