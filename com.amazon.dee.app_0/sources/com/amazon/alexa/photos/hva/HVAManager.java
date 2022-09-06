package com.amazon.alexa.photos.hva;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.photos.metrics.CloudDriveMetrics;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.dee.app.metrics.MetricsService;
import com.google.common.collect.Sets;
import dagger.Lazy;
import java.util.Set;
/* loaded from: classes9.dex */
public class HVAManager {
    private static final String TAG = "HVAManager";
    private static final String TARAZED_SESSION_END = "tarazed::session::end";
    private static final String TARAZED_SESSION_START = "tarazed::session::start";
    @NonNull
    private final Lazy<CloudDriveMetrics> cloudDriveMetrics;
    @NonNull
    @VisibleForTesting
    final SetEventFilter endedEventFilter;
    @NonNull
    @VisibleForTesting
    final MultiFilterSubscriber endedEventFilterSubscriber;
    @NonNull
    private final LazyComponent<EventBus> eventBus;
    @NonNull
    private final Lazy<MetricsService> metricsService;
    @NonNull
    @VisibleForTesting
    final SetEventFilter startedEventFilter;
    @NonNull
    @VisibleForTesting
    final MultiFilterSubscriber startedEventFilterSubscriber;
    private static final String COMMS_CALLING_DROPIN_START = "comms::calling::dropin_start";
    private static final String COMMS_ANNOUNCEMENT_UPLOAD_START = "comms::announcement::upload_start";
    private static final String COMMS_MESSAGING_UPLOAD_START = "comms::messaging::upload_start";
    private static final String COMMS_MESSAGING_RECORD_START = "comms::messaging::record_start";
    private static final String COMMS_CALLING_A2A_START = "comms::calling::a2a_start";
    private static final String VOX_INTERACTION_START = "voice::state_active";
    private static final String ENTERTAINMENT_MEDIA_START = "mecs::media::play";
    static final Set<String> STARTED_INTAKE_EVENTS = Sets.newHashSet("tarazed::session::start", COMMS_CALLING_DROPIN_START, COMMS_ANNOUNCEMENT_UPLOAD_START, COMMS_MESSAGING_UPLOAD_START, COMMS_MESSAGING_RECORD_START, COMMS_CALLING_A2A_START, VOX_INTERACTION_START, ENTERTAINMENT_MEDIA_START);
    private static final String COMMS_CALLING_DROPIN_END = "comms::calling::dropin_end";
    private static final String COMMS_ANNOUNCEMENT_UPLOAD_END = "comms::announcement::upload_end";
    private static final String COMMS_MESSAGING_UPLOAD_END = "comms::messaging::upload_end";
    private static final String COMMS_MESSAGING_RECORD_END = "comms::messaging::record_end";
    private static final String COMMS_CALLING_A2A_END = "comms::calling::a2a_end";
    private static final String VOX_INTERACTION_END = "voice::state_idle";
    private static final String ENTERTAINMENT_MEDIA_END = "mecs::media::pause";
    static final Set<String> ENDED_INTAKE_EVENTS = Sets.newHashSet("tarazed::session::end", COMMS_CALLING_DROPIN_END, COMMS_ANNOUNCEMENT_UPLOAD_END, COMMS_MESSAGING_UPLOAD_END, COMMS_MESSAGING_RECORD_END, COMMS_CALLING_A2A_END, VOX_INTERACTION_END, ENTERTAINMENT_MEDIA_END);

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HVAManager(@NonNull final Lazy<EventBus> lazy, @NonNull Lazy<MetricsService> lazy2) {
        this(new LazyComponent() { // from class: com.amazon.alexa.photos.hva.-$$Lambda$Tbgn48vpwGzMZYrHpyqIAjiy7pY
            @Override // com.amazon.alexa.protocols.service.api.LazyComponent, javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return (EventBus) Lazy.this.mo358get();
            }
        }, $$Lambda$HVAManager$Cy050FnmyCi3id2vQWD2RJlY8ss.INSTANCE, lazy2);
        lazy.getClass();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CloudDriveMetrics lambda$new$0() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MetricsService lambda$new$1() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEndedMessage(@NonNull Message message) {
        processMessage(message, HVAEvent.ENDED_OUT_BOUND.getValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStartedMessage(@NonNull Message message) {
        processMessage(message, HVAEvent.STARTED_OUT_BOUND.getValue());
    }

    private void recordEvent(String str) {
        if (this.metricsService.mo358get() != null) {
            this.metricsService.mo358get().recordEvent(str, TAG, null);
        } else if (this.cloudDriveMetrics.mo358get() == null) {
        } else {
            this.cloudDriveMetrics.mo358get().recordEvent(TAG, str);
        }
    }

    @VisibleForTesting
    void processMessage(@NonNull Message message, @NonNull String str) {
        recordEvent(message.getEventType());
        this.eventBus.mo10268get().publish(new Message.Builder().setEventType(str).build());
    }

    public void unsubscribe() {
        this.eventBus.mo10268get().unsubscribe(this.startedEventFilterSubscriber);
        this.eventBus.mo10268get().unsubscribe(this.endedEventFilterSubscriber);
    }

    public HVAManager(@NonNull ComponentRegistry componentRegistry, @NonNull Lazy<CloudDriveMetrics> lazy) {
        this(componentRegistry.getLazy(EventBus.class), lazy, $$Lambda$HVAManager$izxbPW5SZ4kYEJOxtBWSicAGw.INSTANCE);
    }

    private HVAManager(@NonNull LazyComponent<EventBus> lazyComponent, @NonNull Lazy<CloudDriveMetrics> lazy, @NonNull Lazy<MetricsService> lazy2) {
        this.eventBus = lazyComponent;
        this.cloudDriveMetrics = lazy;
        this.metricsService = lazy2;
        this.startedEventFilter = new SetEventFilter(STARTED_INTAKE_EVENTS);
        this.endedEventFilter = new SetEventFilter(ENDED_INTAKE_EVENTS);
        this.startedEventFilterSubscriber = lazyComponent.mo10268get().getSubscriber();
        this.startedEventFilterSubscriber.subscribeFilter(this.startedEventFilter, new MessageHandler() { // from class: com.amazon.alexa.photos.hva.-$$Lambda$HVAManager$XSmXWZtoAG_ZJ9Uqz9j-R-Wi1oY
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                HVAManager.this.onStartedMessage(message);
            }
        });
        this.endedEventFilterSubscriber = lazyComponent.mo10268get().getSubscriber();
        this.endedEventFilterSubscriber.subscribeFilter(this.endedEventFilter, new MessageHandler() { // from class: com.amazon.alexa.photos.hva.-$$Lambda$HVAManager$3wCt4CRXQFEnj2QkqupwVa-5az4
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                HVAManager.this.onEndedMessage(message);
            }
        });
    }
}
