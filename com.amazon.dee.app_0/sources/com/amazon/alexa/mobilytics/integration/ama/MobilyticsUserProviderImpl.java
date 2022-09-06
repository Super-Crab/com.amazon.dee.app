package com.amazon.alexa.mobilytics.integration.ama;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Action1;
/* loaded from: classes9.dex */
public class MobilyticsUserProviderImpl implements MobilyticsUserProvider {
    private static final String TAG = Log.tag(MobilyticsUserProviderImpl.class);
    private final EventBus eventBus;
    private FeatureServiceV2 featureServiceV2;
    private IdentityService identityService;
    private final List<MobilyticsUserProvider.Listener> listeners = new ArrayList();

    public MobilyticsUserProviderImpl(@NonNull EventBus eventBus) {
        this.identityService = null;
        this.featureServiceV2 = null;
        this.eventBus = eventBus;
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        if (componentRegistry != null) {
            this.identityService = (IdentityService) componentRegistry.get(IdentityService.class).get();
            this.featureServiceV2 = (FeatureServiceV2) componentRegistry.get(FeatureServiceV2.class).get();
        }
        if (this.identityService != null) {
            if (this.eventBus.getSubscriber() != null) {
                this.eventBus.getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.mobilytics.integration.ama.-$$Lambda$MobilyticsUserProviderImpl$2VTxn3tCbafr0DLeJEnWBvPE3LQ
                    @Override // com.amazon.alexa.eventbus.api.MessageHandler
                    public final void handle(Message message) {
                        MobilyticsUserProviderImpl.this.lambda$new$0$MobilyticsUserProviderImpl(message);
                    }
                });
            } else {
                Log.e(TAG, "eventBus subscriber is null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onUserChanged$1(MobilyticsUser mobilyticsUser, MobilyticsUserProvider.Listener listener) {
        try {
            listener.onUserChanged(mobilyticsUser);
        } catch (Exception e) {
            Log.e(TAG, e, "Error processing listener", new Object[0]);
        }
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider
    public void addListener(@NonNull MobilyticsUserProvider.Listener listener) {
        this.listeners.add(listener);
    }

    public /* synthetic */ void lambda$new$0$MobilyticsUserProviderImpl(Message message) {
        onUserChanged(new MobilyticsUserImpl(this.identityService.getUser(TAG), this.featureServiceV2));
    }

    @VisibleForTesting
    protected void onUserChanged(@Nullable final MobilyticsUser mobilyticsUser) {
        Observable.from(this.listeners).forEach(new Action1() { // from class: com.amazon.alexa.mobilytics.integration.ama.-$$Lambda$MobilyticsUserProviderImpl$jzStJLnEezJIiJVhyX0moIUU9jY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MobilyticsUserProviderImpl.lambda$onUserChanged$1(MobilyticsUser.this, (MobilyticsUserProvider.Listener) obj);
            }
        });
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider
    public void removeListener(@NonNull MobilyticsUserProvider.Listener listener) {
        this.listeners.remove(listener);
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider
    public MobilyticsUser user() {
        return new MobilyticsUserImpl(this.identityService.getUser(TAG), this.featureServiceV2);
    }
}
