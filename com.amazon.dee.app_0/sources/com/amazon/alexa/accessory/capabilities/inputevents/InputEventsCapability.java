package com.amazon.alexa.accessory.capabilities.inputevents;

import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventHandler;
import com.amazon.alexa.accessory.capabilities.inputevents.InputEventsCapability;
import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.inputevents.InputProducer;
import com.amazon.alexa.accessory.repositories.inputevents.InputProvider;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import java.io.IOException;
/* loaded from: classes.dex */
public final class InputEventsCapability extends AccessoryCapability {
    private final InputActionHandler actionHandler;
    private final InputEventHandler inputEventHandler;
    private final InputProducer inputProducer;
    private final InputProvider inputProvider;
    private ControlStream stream;

    /* renamed from: com.amazon.alexa.accessory.capabilities.inputevents.InputEventsCapability$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$capabilities$inputevents$InputEventHandler$Result = new int[InputEventHandler.Result.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$inputevents$InputEventHandler$Result[InputEventHandler.Result.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$inputevents$InputEventHandler$Result[InputEventHandler.Result.UNSUPPORTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$inputevents$InputEventHandler$Result[InputEventHandler.Result.DISCARDED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class InputActionHandler implements InputProducer.ActionHandler {
        private final ActionQueue getQueue = new ActionQueue();
        private final ActionQueue setQueue = new ActionQueue();
        private final ActionQueue resetQueue = new ActionQueue();

        InputActionHandler() {
        }

        void cancelAllActions() {
            this.getQueue.cancelAll();
            this.setQueue.cancelAll();
            this.resetQueue.cancelAll();
        }

        @Override // com.amazon.alexa.accessory.repositories.inputevents.InputProducer.ActionHandler
        public void handleGetInputConfiguration(int i, Producer.Result<Input.InputBehaviorConfigurationSet> result) {
            Preconditions.notNull(result, "result");
            this.getQueue.enqueue(new SuccessResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_INPUT_BEHAVIOR).setGetInputBehavior(Input.GetInputBehavior.newBuilder().setDeviceId(i)).mo10084build()), InputEventsCapability.this.stream, result, Accessories.Response.PayloadCase.INPUT_BEHAVIOR_CONFIGURATION_SET, $$Lambda$ROSgcEx3JgttWdrXkOV1qCOF7_M.INSTANCE));
        }

        @Override // com.amazon.alexa.accessory.repositories.inputevents.InputProducer.ActionHandler
        public void handleResetInputConfiguration(int i, Producer.Result<Input.InputBehaviorConfigurationSet> result) {
            Preconditions.notNull(result, "result");
            this.resetQueue.enqueue(new SuccessResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.RESET_INPUT_BEHAVIOR).setResetInputBehavior(Input.ResetInputBehavior.newBuilder().setDeviceId(i)).mo10084build()), InputEventsCapability.this.stream, result, Accessories.Response.PayloadCase.INPUT_BEHAVIOR_CONFIGURATION_SET, $$Lambda$ROSgcEx3JgttWdrXkOV1qCOF7_M.INSTANCE));
        }

        @Override // com.amazon.alexa.accessory.repositories.inputevents.InputProducer.ActionHandler
        public void handleSetInputConfiguration(final int i, final Input.InputBehaviorConfiguration inputBehaviorConfiguration, Producer.Result<CompletableResult.Value> result) {
            Preconditions.notNull(inputBehaviorConfiguration, PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
            Preconditions.notNull(result, "result");
            this.setQueue.enqueue(new SuccessResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.SET_INPUT_BEHAVIOR).setSetInputBehavior(Input.SetInputBehavior.newBuilder().setConfiguration(inputBehaviorConfiguration).setDeviceId(i)).mo10084build()), InputEventsCapability.this.stream, result, Accessories.Response.PayloadCase.PAYLOAD_NOT_SET, new SuccessResponseAction.Mapper() { // from class: com.amazon.alexa.accessory.capabilities.inputevents.-$$Lambda$InputEventsCapability$InputActionHandler$DMIXxbAZrsRHkkKIIWmx9Gdazc0
                @Override // com.amazon.alexa.accessory.internal.SuccessResponseAction.Mapper
                public final Object map(Accessories.Response response) {
                    return InputEventsCapability.InputActionHandler.this.lambda$handleSetInputConfiguration$0$InputEventsCapability$InputActionHandler(i, inputBehaviorConfiguration, response);
                }
            }));
        }

        public /* synthetic */ CompletableResult.Value lambda$handleSetInputConfiguration$0$InputEventsCapability$InputActionHandler(int i, Input.InputBehaviorConfiguration inputBehaviorConfiguration, Accessories.Response response) throws IOException {
            InputEventsCapability.this.inputProvider.provideConfiguration(i, inputBehaviorConfiguration);
            return CompletableResult.Value.complete();
        }
    }

    /* loaded from: classes.dex */
    private static final class InputEventCallback implements InputEventHandler.Callback {
        private final ControlStream stream;

        InputEventCallback(ControlStream controlStream) {
            Preconditions.notNull(controlStream, "stream");
            this.stream = controlStream;
        }

        @Override // com.amazon.alexa.accessory.capabilities.inputevents.InputEventHandler.Callback
        public void onResult(InputEventHandler.Result result) {
            Common.ErrorCode errorCode = Common.ErrorCode.UNKNOWN;
            int ordinal = result.ordinal();
            if (ordinal == 0) {
                errorCode = Common.ErrorCode.SUCCESS;
            } else if (ordinal == 1) {
                errorCode = Common.ErrorCode.UNSUPPORTED;
            } else if (ordinal == 2) {
                errorCode = Common.ErrorCode.INTERNAL;
            }
            this.stream.respond(Accessories.Command.ISSUE_INPUT_EVENT, errorCode);
        }
    }

    public InputEventsCapability(InputProducer inputProducer, InputProvider inputProvider, InputEventHandler inputEventHandler) {
        Preconditions.notNull(inputProducer, "inputProducer");
        Preconditions.notNull(inputProvider, "inputProvider");
        Preconditions.notNull(inputEventHandler, "inputEventHandler");
        this.inputEventHandler = inputEventHandler;
        this.inputProducer = inputProducer;
        this.inputProvider = inputProvider;
        this.actionHandler = new InputActionHandler();
    }

    private ControlMessageHandler<Input.IssueInputEvent> getIssueInputEventHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.inputevents.-$$Lambda$InputEventsCapability$x9emmTu_F8sDBKl0bp_LqZijCUI
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                InputEventsCapability.this.lambda$getIssueInputEventHandler$0$InputEventsCapability(controlStream, command, (Input.IssueInputEvent) obj);
            }
        };
    }

    public /* synthetic */ void lambda$getIssueInputEventHandler$0$InputEventsCapability(ControlStream controlStream, Accessories.Command command, Input.IssueInputEvent issueInputEvent) throws Exception {
        this.inputEventHandler.handleEvent(issueInputEvent, new InputEventCallback(controlStream));
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        accessoryDescriptor.remove(this.stream);
        this.inputProducer.detachActionHandler(this.actionHandler);
        this.actionHandler.cancelAllActions();
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.inputProducer.attachActionHandler(this.actionHandler);
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.stream.addMessageHandler(Accessories.Command.ISSUE_INPUT_EVENT, getIssueInputEventHandler());
        accessoryDescriptor.add(this.stream);
    }
}
