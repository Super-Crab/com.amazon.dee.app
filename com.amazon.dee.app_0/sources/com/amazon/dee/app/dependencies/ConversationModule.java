package com.amazon.dee.app.dependencies;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.routing.api.RoutingRegistryAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.R;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.api.CommsUIDelegateBase;
import com.amazon.deecomms.common.ui.main.ConversationRoutingAdapter;
import com.amazon.deecomms.conversation.CommsConversationUIService;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.conversation.ConversationRouting;
import com.amazon.deecomms.services.ConversationUIService;
import com.amazon.deecomms.services.conversation.CommsConversationUIDelegate;
import com.amazon.deecomms.services.conversation.ConversationUIDelegate;
import com.amazon.deecomms.ui.util.CommsRoutingHelper;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class ConversationModule {
    ConversationUIDelegate conversationUIDelegate;

    public ConversationModule(ConversationUIDelegate conversationUIDelegate) {
        this.conversationUIDelegate = conversationUIDelegate;
    }

    @Provides
    @MainScope
    public CommsRoutingHelper provideCommsRoutingHelper(@NonNull ConversationUIService conversationUIService, @NonNull RoutingService routingService, @NonNull CommsServiceV2 commsServiceV2, @NonNull CommsManager commsManager, @NonNull IdentityService identityService) {
        return new CommsRoutingHelper(conversationUIService, routingService, commsServiceV2, commsManager, identityService);
    }

    @Provides
    public CommsUIDelegateBase provideCommsUIDelegateBase(Activity activity, RoutingService routingService, ConversationRouting conversationRouting, AccountService accountService, RoutingRegistryAdapter routingRegistryAdapter) {
        return new CommsConversationUIDelegate(activity, R.id.conversation_container, this.conversationUIDelegate, routingService, conversationRouting, accountService, routingRegistryAdapter);
    }

    @Provides
    @MainScope
    public ConversationRouting provideConversationRouting() {
        return new ConversationRouting();
    }

    @Provides
    @MainScope
    public ConversationRoutingAdapter provideConversationRoutingAdapter(Activity activity, CommsManager commsManager, ConversationRouting conversationRouting, MetricsService metricsService, Lazy<CommsServiceV2> lazy) {
        return new ConversationRoutingAdapter(activity, R.id.conversation_container, commsManager, this.conversationUIDelegate, conversationRouting, metricsService, lazy);
    }

    @Provides
    @MainScope
    public ConversationUIService provideConversationUIService(Activity activity, IdentityService identityService, CommsManager commsManager, CommsUIDelegateBase commsUIDelegateBase, CommsDeviceSupport commsDeviceSupport) {
        return new CommsConversationUIService(identityService, commsManager, commsUIDelegateBase, commsDeviceSupport);
    }
}
