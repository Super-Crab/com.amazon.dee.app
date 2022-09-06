package com.amazon.alexa.accessory.capabilities.display;

import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.ErrorCodeAction;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Cardrendering;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.display.DisplayContentProducer;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
/* loaded from: classes.dex */
public final class DisplayContentCapability extends AccessoryCapability {
    private final DisplayActionHandler actionHandler;
    private final DisplayContentProducer displayProducer;
    private ControlStream stream;

    /* loaded from: classes.dex */
    private final class DisplayActionHandler implements DisplayContentProducer.ActionHandler {
        private final ActionQueue displayQueue = new ActionQueue();

        DisplayActionHandler() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancelAllActions() {
            this.displayQueue.cancelAll();
        }

        @Override // com.amazon.alexa.accessory.repositories.display.DisplayContentProducer.ActionHandler
        public void handleSetDisplayContent(Cardrendering.DisplayContent displayContent, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(displayContent, "displayContent");
            Preconditions.notNull(result, "result");
            this.displayQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.DISPLAY_CONTENT).setDisplayContent(displayContent).mo10084build()), DisplayContentCapability.this.stream, result));
        }
    }

    public DisplayContentCapability(DisplayContentProducer displayContentProducer) {
        Preconditions.notNull(displayContentProducer, "displayProducer");
        this.displayProducer = displayContentProducer;
        this.actionHandler = new DisplayActionHandler();
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.displayProducer.detachActionHandler(this.actionHandler);
        this.actionHandler.cancelAllActions();
        accessoryDescriptor.remove(this.stream);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.displayProducer.attachActionHandler(this.actionHandler);
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        accessoryDescriptor.add(this.stream);
    }
}
