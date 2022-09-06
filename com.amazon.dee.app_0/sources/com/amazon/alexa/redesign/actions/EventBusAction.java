package com.amazon.alexa.redesign.actions;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import java.util.Map;
/* loaded from: classes10.dex */
public class EventBusAction extends Action {
    private final EventBus eventBus;
    private final Message message;

    public EventBusAction(@NonNull Message message, @NonNull EventBus eventBus, @NonNull String str, @NonNull String str2) {
        super(ActionFactory.OPENURL_ACTION, str, str2);
        if (message != null) {
            if (eventBus == null) {
                throw new IllegalArgumentException("OpenUrlAction: eventBus must not be null");
            }
            if (str != null) {
                this.eventBus = eventBus;
                this.message = message;
                return;
            }
            throw new IllegalArgumentException("OpenUrlAction: metaActionType must not be null");
        }
        throw new IllegalArgumentException("OpenUrlAction: message must not be null");
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public void execute() {
        this.eventBus.publish(this.message);
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public void execute(Map<String, Object> map) {
        this.eventBus.publish(this.message);
    }
}
