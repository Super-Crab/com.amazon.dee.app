package com.amazon.alexa.accessory.capabilities.calling;

import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.capabilities.calling.CallRecipient;
import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.ErrorCodeAction;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Calling;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.calling.ATCommand;
import com.amazon.alexa.accessory.repositories.calling.CallingProducer;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
/* loaded from: classes.dex */
public final class CallingCapability extends AccessoryCapability {
    private final CallingActionHandler actionHandler;
    private final CallRecipient callRecipient;
    private final CallingProducer callingProducer;
    private ControlStream stream;

    /* loaded from: classes.dex */
    final class CallingActionHandler implements CallingProducer.ActionHandler {
        private final ActionQueue actionQueue = new ActionQueue();

        CallingActionHandler() {
        }

        void cancelAllActions() {
            this.actionQueue.cancelAll();
        }

        @Override // com.amazon.alexa.accessory.repositories.calling.CallingProducer.ActionHandler
        public void handleForwardAtCommand(ATCommand aTCommand, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(aTCommand, "command");
            Preconditions.notNull(result, "result");
            this.actionQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.FORWARD_AT_COMMAND).setForwardAtCommand(Calling.ForwardATCommand.newBuilder().setCommand(aTCommand.toString())).mo10084build()), CallingCapability.this.stream, result));
        }
    }

    public CallingCapability(CallingProducer callingProducer, CallRecipient callRecipient) {
        Preconditions.notNull(callingProducer, "callingProducer");
        Preconditions.notNull(callRecipient, "callRecipient");
        this.callingProducer = callingProducer;
        this.callRecipient = callRecipient;
        this.actionHandler = new CallingActionHandler();
    }

    private ControlMessageHandler<Calling.IncomingCall> getIncomingCallHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.calling.-$$Lambda$CallingCapability$lsyogvg8beUcHwOKK85q-KZJ-9g
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                CallingCapability.this.lambda$getIncomingCallHandler$0$CallingCapability(controlStream, command, (Calling.IncomingCall) obj);
            }
        };
    }

    public /* synthetic */ void lambda$getIncomingCallHandler$0$CallingCapability(ControlStream controlStream, Accessories.Command command, final Calling.IncomingCall incomingCall) throws Exception {
        CallRecipient callRecipient = this.callRecipient;
        incomingCall.getClass();
        callRecipient.handleIncomingCall(new CallRecipient.Call() { // from class: com.amazon.alexa.accessory.capabilities.calling.-$$Lambda$hoDfixzFqADCjYYyr8ItVOFmXVs
            @Override // com.amazon.alexa.accessory.capabilities.calling.CallRecipient.Call
            public final String getCallerNumber() {
                return Calling.IncomingCall.this.getCallerNumber();
            }
        });
        controlStream.respond(Accessories.Command.INCOMING_CALL, Common.ErrorCode.SUCCESS);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.callingProducer.detachActionHandler(this.actionHandler);
        this.actionHandler.cancelAllActions();
        accessoryDescriptor.remove(this.stream);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.callingProducer.attachActionHandler(this.actionHandler);
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.stream.addMessageHandler(Accessories.Command.INCOMING_CALL, getIncomingCallHandler());
        accessoryDescriptor.add(this.stream);
    }
}
