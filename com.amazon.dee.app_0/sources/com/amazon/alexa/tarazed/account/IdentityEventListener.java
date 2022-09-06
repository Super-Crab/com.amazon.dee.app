package com.amazon.alexa.tarazed.account;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.tarazed.dagger.scope.TarazedIntegrationScope;
import com.amazon.alexa.tarazed.eventbus.EventBusHandler;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: IdentityEventListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/tarazed/account/IdentityEventListener;", "", "eventBus", "Lcom/amazon/alexa/eventbus/api/EventBus;", "eventBusHandler", "Lcom/amazon/alexa/tarazed/eventbus/EventBusHandler;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "(Lcom/amazon/alexa/eventbus/api/EventBus;Lcom/amazon/alexa/tarazed/eventbus/EventBusHandler;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "signoutSubscriptionId", "Lcom/amazon/alexa/eventbus/api/MultiFilterSubscriber$FilterUuid;", "subscriber", "Lcom/amazon/alexa/eventbus/api/MultiFilterSubscriber;", "startListeningForEvents", "", "stopListeningForEvents", "Companion", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
@TarazedIntegrationScope
/* loaded from: classes10.dex */
public final class IdentityEventListener {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "IdentityEventListener";
    private final EventBus eventBus;
    private final EventBusHandler eventBusHandler;
    private final TarazedSessionLogger logger;
    private MultiFilterSubscriber.FilterUuid signoutSubscriptionId;
    private MultiFilterSubscriber subscriber;

    /* compiled from: IdentityEventListener.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/tarazed/account/IdentityEventListener$Companion;", "", "()V", "TAG", "", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public IdentityEventListener(@NotNull EventBus eventBus, @NotNull EventBusHandler eventBusHandler, @NotNull TarazedSessionLogger logger) {
        Intrinsics.checkParameterIsNotNull(eventBus, "eventBus");
        Intrinsics.checkParameterIsNotNull(eventBusHandler, "eventBusHandler");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.eventBus = eventBus;
        this.eventBusHandler = eventBusHandler;
        this.logger = logger;
    }

    public final void startListeningForEvents() {
        if (this.subscriber == null) {
            this.subscriber = this.eventBus.getNewSubscriber();
        }
        if (this.signoutSubscriptionId == null) {
            MultiFilterSubscriber multiFilterSubscriber = this.subscriber;
            this.signoutSubscriptionId = multiFilterSubscriber != null ? multiFilterSubscriber.subscribeFilter(IdentityEventListener$startListeningForEvents$1.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.tarazed.account.IdentityEventListener$startListeningForEvents$2
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(@NotNull Message message) {
                    EventBusHandler eventBusHandler;
                    Intrinsics.checkParameterIsNotNull(message, "message");
                    eventBusHandler = IdentityEventListener.this.eventBusHandler;
                    eventBusHandler.handle(message);
                }
            }) : null;
            this.logger.i(TAG, "MSS started listening for user sign-out events");
        }
    }

    public final void stopListeningForEvents() {
        MultiFilterSubscriber.FilterUuid filterUuid = this.signoutSubscriptionId;
        if (filterUuid != null) {
            MultiFilterSubscriber multiFilterSubscriber = this.subscriber;
            if (multiFilterSubscriber != null) {
                multiFilterSubscriber.unsubscribeFilter(filterUuid);
            }
            this.signoutSubscriptionId = null;
            this.logger.i(TAG, "MSS stopped listening for user sign-out events");
        }
        MultiFilterSubscriber multiFilterSubscriber2 = this.subscriber;
        if (multiFilterSubscriber2 != null) {
            this.eventBus.unsubscribe(multiFilterSubscriber2);
            this.subscriber = null;
            this.logger.i(TAG, "MSS removed the sign-out event subscriber");
        }
    }
}
