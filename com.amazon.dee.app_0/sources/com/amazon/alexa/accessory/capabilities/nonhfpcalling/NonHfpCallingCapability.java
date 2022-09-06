package com.amazon.alexa.accessory.capabilities.nonhfpcalling;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.ErrorCodeAction;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Int64Util;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Nonhfpcalling;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.CallInfo;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallController;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallingProducer;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
/* loaded from: classes.dex */
public final class NonHfpCallingCapability extends AccessoryCapability {
    private final NonHfpCallingActionHandler actionHandler;
    private final NonHfpCallController nonHfpCallController;
    private final NonHfpCallingProducer nonHfpCallingProducer;
    private ControlStream stream;

    /* renamed from: com.amazon.alexa.accessory.capabilities.nonhfpcalling.NonHfpCallingCapability$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$CallInfo$CallDirection;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$CallInfo$CallState;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$NonHfpCallController$OperationStatus = new int[NonHfpCallController.OperationStatus.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$NonHfpCallController$OperationStatus[NonHfpCallController.OperationStatus.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$NonHfpCallController$OperationStatus[NonHfpCallController.OperationStatus.INTERNAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$NonHfpCallController$OperationStatus[NonHfpCallController.OperationStatus.UNSUPPORTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$NonHfpCallController$OperationStatus[NonHfpCallController.OperationStatus.USER_CANCELLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$NonHfpCallController$OperationStatus[NonHfpCallController.OperationStatus.NOT_FOUND.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$NonHfpCallController$OperationStatus[NonHfpCallController.OperationStatus.INVALID.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$NonHfpCallController$OperationStatus[NonHfpCallController.OperationStatus.BUSY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$NonHfpCallController$OperationStatus[NonHfpCallController.OperationStatus.UNKNOWN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$CallInfo$CallState = new int[CallInfo.CallState.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$CallInfo$CallState[CallInfo.CallState.DIALING.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$CallInfo$CallState[CallInfo.CallState.RINGING.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$CallInfo$CallState[CallInfo.CallState.ACTIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$CallInfo$CallState[CallInfo.CallState.HOLDING.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$CallInfo$CallState[CallInfo.CallState.DISCONNECTING.ordinal()] = 5;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$CallInfo$CallState[CallInfo.CallState.DISCONNECTED.ordinal()] = 6;
            } catch (NoSuchFieldError unused14) {
            }
            $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$CallInfo$CallDirection = new int[CallInfo.CallDirection.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$CallInfo$CallDirection[CallInfo.CallDirection.INCOMING.ordinal()] = 1;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$nonhfpcalling$CallInfo$CallDirection[CallInfo.CallDirection.OUTGOING.ordinal()] = 2;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    /* loaded from: classes.dex */
    class NonHfpCallingActionHandler implements NonHfpCallingProducer.ActionHandler {
        private final ActionQueue actionQueue = new ActionQueue();

        NonHfpCallingActionHandler() {
        }

        void cancelAllActions() {
            this.actionQueue.cancelAll();
        }

        @Override // com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallingProducer.ActionHandler
        public void handleUpdateCallInfo(@NonNull CallInfo callInfo, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(callInfo, "callInfo");
            Preconditions.notNull(result, "result");
            this.actionQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.UPDATE_CALL_STATE).setUpdateCallState(NonHfpCallingCapability.convertCallInfoToUpdateCallState(callInfo)).mo10084build()), NonHfpCallingCapability.this.stream, result));
        }
    }

    public NonHfpCallingCapability(NonHfpCallingProducer nonHfpCallingProducer, NonHfpCallController nonHfpCallController) {
        Preconditions.notNull(nonHfpCallingProducer, "nonHfpCallingProducer");
        Preconditions.notNull(nonHfpCallController, "callController");
        this.nonHfpCallingProducer = nonHfpCallingProducer;
        this.nonHfpCallController = nonHfpCallController;
        this.actionHandler = new NonHfpCallingActionHandler();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Nonhfpcalling.UpdateCallState convertCallInfoToUpdateCallState(CallInfo callInfo) {
        Nonhfpcalling.UpdateCallState.Builder newBuilder = Nonhfpcalling.UpdateCallState.newBuilder();
        newBuilder.setCallUuid(IOUtils.uuidToByteString(callInfo.getUuid())).setCallDirection(mapCallDirectionToProtobuf(callInfo.getCallDirection())).setCallStatus(mapCallStateToProtobuf(callInfo.getCallState()));
        newBuilder.setCallDetails(Nonhfpcalling.CallDetails.newBuilder().setConnectionTimestampMillisecondsHi(Int64Util.getHigh32(callInfo.getConnectionTimestampInMs())).setConnectionTimestampMillisecondsLo(Int64Util.getLow32(callInfo.getConnectionTimestampInMs())).setLastUpdateTimestampMillisecondsHi(Int64Util.getHigh32(callInfo.getLastUpdateTimestampInMs())).setLastUpdateTimestampMillisecondsLo(Int64Util.getLow32(callInfo.getLastUpdateTimestampInMs())).setCallerDisplayName(callInfo.getDisplayName()).setCallerNumber(callInfo.getPhoneNumber()).mo10084build());
        return newBuilder.mo10084build();
    }

    private ControlMessageHandler<Nonhfpcalling.AcceptCall> getAcceptCallHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.nonhfpcalling.-$$Lambda$NonHfpCallingCapability$jfg8HRugZmxbemsG6dMhXz8hvEA
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                NonHfpCallingCapability.this.lambda$getAcceptCallHandler$0$NonHfpCallingCapability(controlStream, command, (Nonhfpcalling.AcceptCall) obj);
            }
        };
    }

    private ControlMessageHandler<Nonhfpcalling.EndCall> getEndCallHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.nonhfpcalling.-$$Lambda$NonHfpCallingCapability$6wq2EgW1nsUgNLjLM71fovSRkQM
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                NonHfpCallingCapability.this.lambda$getEndCallHandler$2$NonHfpCallingCapability(controlStream, command, (Nonhfpcalling.EndCall) obj);
            }
        };
    }

    private ControlMessageHandler<Nonhfpcalling.RejectCall> getRejectCallHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.nonhfpcalling.-$$Lambda$NonHfpCallingCapability$MMx51NI3Ebl8S5pjpksGd-PPNMw
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                NonHfpCallingCapability.this.lambda$getRejectCallHandler$1$NonHfpCallingCapability(controlStream, command, (Nonhfpcalling.RejectCall) obj);
            }
        };
    }

    @VisibleForTesting
    static Nonhfpcalling.UpdateCallState.CallDirection mapCallDirectionToProtobuf(CallInfo.CallDirection callDirection) {
        int ordinal = callDirection.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                return Nonhfpcalling.UpdateCallState.CallDirection.UNKNOWN;
            }
            return Nonhfpcalling.UpdateCallState.CallDirection.OUTGOING;
        }
        return Nonhfpcalling.UpdateCallState.CallDirection.INCOMING;
    }

    @VisibleForTesting
    static Nonhfpcalling.UpdateCallState.CallStatus mapCallStateToProtobuf(CallInfo.CallState callState) {
        switch (callState.ordinal()) {
            case 1:
                return Nonhfpcalling.UpdateCallState.CallStatus.DIALING;
            case 2:
                return Nonhfpcalling.UpdateCallState.CallStatus.RINGING;
            case 3:
                return Nonhfpcalling.UpdateCallState.CallStatus.ACTIVE;
            case 4:
                return Nonhfpcalling.UpdateCallState.CallStatus.HOLDING;
            case 5:
                return Nonhfpcalling.UpdateCallState.CallStatus.DISCONNECTING;
            case 6:
                return Nonhfpcalling.UpdateCallState.CallStatus.DISCONNECTED;
            default:
                return Nonhfpcalling.UpdateCallState.CallStatus.IDLE;
        }
    }

    @VisibleForTesting
    static Common.ErrorCode mapOperationStatusToProtobuf(NonHfpCallController.OperationStatus operationStatus) {
        int ordinal = operationStatus.ordinal();
        if (ordinal != 0) {
            switch (ordinal) {
                case 2:
                    return Common.ErrorCode.INTERNAL;
                case 3:
                    return Common.ErrorCode.UNSUPPORTED;
                case 4:
                    return Common.ErrorCode.USER_CANCELLED;
                case 5:
                    return Common.ErrorCode.NOT_FOUND;
                case 6:
                    return Common.ErrorCode.INVALID;
                case 7:
                    return Common.ErrorCode.BUSY;
                default:
                    return Common.ErrorCode.UNKNOWN;
            }
        }
        return Common.ErrorCode.SUCCESS;
    }

    public /* synthetic */ void lambda$getAcceptCallHandler$0$NonHfpCallingCapability(ControlStream controlStream, Accessories.Command command, Nonhfpcalling.AcceptCall acceptCall) throws Exception {
        controlStream.respond(Accessories.Command.ACCEPT_CALL, mapOperationStatusToProtobuf(this.nonHfpCallController.handleAcceptCall(IOUtils.byteStringToUuid(acceptCall.getCallUuid()))));
    }

    public /* synthetic */ void lambda$getEndCallHandler$2$NonHfpCallingCapability(ControlStream controlStream, Accessories.Command command, Nonhfpcalling.EndCall endCall) throws Exception {
        controlStream.respond(Accessories.Command.END_CALL, mapOperationStatusToProtobuf(this.nonHfpCallController.handleEndCall(IOUtils.byteStringToUuid(endCall.getCallUuid()))));
    }

    public /* synthetic */ void lambda$getRejectCallHandler$1$NonHfpCallingCapability(ControlStream controlStream, Accessories.Command command, Nonhfpcalling.RejectCall rejectCall) throws Exception {
        controlStream.respond(Accessories.Command.REJECT_CALL, mapOperationStatusToProtobuf(this.nonHfpCallController.handleRejectCall(IOUtils.byteStringToUuid(rejectCall.getCallUuid()))));
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.nonHfpCallingProducer.detachActionHandler(this.actionHandler);
        this.actionHandler.cancelAllActions();
        accessoryDescriptor.remove(this.stream);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.nonHfpCallingProducer.attachActionHandler(this.actionHandler);
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.stream.addMessageHandler(Accessories.Command.ACCEPT_CALL, getAcceptCallHandler());
        this.stream.addMessageHandler(Accessories.Command.REJECT_CALL, getRejectCallHandler());
        this.stream.addMessageHandler(Accessories.Command.END_CALL, getEndCallHandler());
        accessoryDescriptor.add(this.stream);
    }
}
