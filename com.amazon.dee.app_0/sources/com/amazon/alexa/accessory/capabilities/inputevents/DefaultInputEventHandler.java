package com.amazon.alexa.accessory.capabilities.inputevents;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventHandler;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Input;
/* loaded from: classes.dex */
public final class DefaultInputEventHandler implements InputEventHandler {
    private final AccessorySession accessorySession;
    private final InputEventsHandler inputEventsHandler;

    public DefaultInputEventHandler(AccessorySession accessorySession, InputEventsHandler inputEventsHandler) {
        Preconditions.notNull(accessorySession, "accessorySession");
        Preconditions.notNull(inputEventsHandler, "inputEventsHandler");
        this.accessorySession = accessorySession;
        this.inputEventsHandler = inputEventsHandler;
    }

    @Override // com.amazon.alexa.accessory.capabilities.inputevents.InputEventHandler
    public void handleEvent(Input.IssueInputEvent issueInputEvent, InputEventHandler.Callback callback) {
        this.inputEventsHandler.onInputEventTriggered(this.accessorySession, new SimpleInputEvent(issueInputEvent.getAction(), issueInputEvent.getSource(), issueInputEvent.getBehavior(), issueInputEvent.getDeviceId()), callback);
    }
}
