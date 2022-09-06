package com.amazon.alexa.voice.settings;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.voice.sdk.AlexaStateTracker;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.UUID;
/* loaded from: classes11.dex */
public class HighPriorityActivityHandler implements SettingsHandler {
    @VisibleForTesting
    static final String HPA_END = "voice::state_idle";
    @VisibleForTesting
    static final String HPA_START = "voice::state_active";
    private static final String TAG = "HPAHandler";
    private final AlexaStateTracker alexaStateTracker;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final EventBus eventBus;
    private UUID subscriptionId;

    public HighPriorityActivityHandler(EventBus eventBus, AlexaStateTracker alexaStateTracker) {
        this.eventBus = eventBus;
        this.alexaStateTracker = alexaStateTracker;
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public UUID getSubscriptionId() {
        return this.subscriptionId;
    }

    public CompositeDisposable getSubscriptions() {
        return this.disposables;
    }

    @Override // com.amazon.alexa.eventbus.api.MessageHandler
    public void handle(@NonNull Message message) {
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public boolean isEventTypeSupported(String str) {
        return false;
    }

    public /* synthetic */ void lambda$startSubscription$0$HighPriorityActivityHandler(Boolean bool) throws Throwable {
        String str = bool.booleanValue() ? HPA_END : HPA_START;
        this.eventBus.publish(new Message.Builder().setEventType(str).build());
        String str2 = "Responding to AlexaState change, emitting message : " + str;
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public void startSubscription(UUID uuid) {
        this.subscriptionId = uuid;
        this.disposables.add(this.alexaStateTracker.onIdle().subscribe(new Consumer() { // from class: com.amazon.alexa.voice.settings.-$$Lambda$HighPriorityActivityHandler$ybnjzgIGpfkjLHaS7U_nizOibCY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                HighPriorityActivityHandler.this.lambda$startSubscription$0$HighPriorityActivityHandler((Boolean) obj);
            }
        }));
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public void stopSubscription() {
        this.disposables.dispose();
        this.subscriptionId = null;
    }
}
