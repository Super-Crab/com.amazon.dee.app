package com.amazon.alexa.accessory.capabilities.instrumentation;

import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.ErrorCodeAction;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Instrumentation;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.instrumentation.InstrumentationProducer;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
/* loaded from: classes.dex */
public final class InstrumentationCapability extends AccessoryCapability {
    private final InstrumentationActionHandler actionHandler;
    private ControlStream controlStream;
    private final DataFormatter formatter;
    private final DataPrinter printer;
    private final InstrumentationProducer producer;

    /* loaded from: classes.dex */
    final class InstrumentationActionHandler implements InstrumentationProducer.ActionHandler {
        private final ActionQueue issueRemoteCommandQueue = new ActionQueue();
        private final ActionQueue issueRemoteRestartQueue = new ActionQueue();
        private final ActionQueue issueRemoteResetQueue = new ActionQueue();
        private final ActionQueue issueRemoteClearPairingQueue = new ActionQueue();

        InstrumentationActionHandler() {
        }

        void cancelAllActions() {
            this.issueRemoteCommandQueue.cancelAll();
            this.issueRemoteRestartQueue.cancelAll();
            this.issueRemoteResetQueue.cancelAll();
            this.issueRemoteClearPairingQueue.cancelAll();
        }

        @Override // com.amazon.alexa.accessory.repositories.instrumentation.InstrumentationProducer.ActionHandler
        public void handleIssueRemoteClearPairing(Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(result, "result");
            this.issueRemoteClearPairingQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.ISSUE_REMOTE_CLEAR_PAIRING).setIssueRemoteClearPairing(Instrumentation.IssueRemoteClearPairing.getDefaultInstance()).mo10084build()), InstrumentationCapability.this.controlStream, result));
        }

        @Override // com.amazon.alexa.accessory.repositories.instrumentation.InstrumentationProducer.ActionHandler
        public void handleIssueRemoteCommand(String str, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(str, "commandLine");
            Preconditions.notNull(result, "result");
            this.issueRemoteCommandQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.ISSUE_REMOTE_COMMAND).setIssueRemoteCommand(Instrumentation.IssueRemoteCommand.newBuilder().setCommandLine(str)).mo10084build()), InstrumentationCapability.this.controlStream, result));
        }

        @Override // com.amazon.alexa.accessory.repositories.instrumentation.InstrumentationProducer.ActionHandler
        public void handleIssueRemoteReset(Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(result, "result");
            this.issueRemoteResetQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.ISSUE_REMOTE_RESET).setIssueRemoteReset(Instrumentation.IssueRemoteReset.getDefaultInstance()).mo10084build()), InstrumentationCapability.this.controlStream, result));
        }

        @Override // com.amazon.alexa.accessory.repositories.instrumentation.InstrumentationProducer.ActionHandler
        public void handleIssueRemoteRestart(Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(result, "result");
            this.issueRemoteRestartQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.ISSUE_REMOTE_RESTART).setIssueRemoteRestart(Instrumentation.IssueRemoteRestart.getDefaultInstance()).mo10084build()), InstrumentationCapability.this.controlStream, result));
        }
    }

    public InstrumentationCapability(InstrumentationProducer instrumentationProducer, DataFormatter dataFormatter, DataPrinter dataPrinter) {
        Preconditions.notNull(instrumentationProducer, "producer");
        Preconditions.notNull(dataFormatter, "formatter");
        Preconditions.notNull(dataPrinter, "printer");
        this.producer = instrumentationProducer;
        this.formatter = dataFormatter;
        this.printer = dataPrinter;
        this.actionHandler = new InstrumentationActionHandler();
    }

    private ControlMessageHandler<Instrumentation.PrintDebug> getPrintDebugHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.instrumentation.-$$Lambda$InstrumentationCapability$JEOSaekMuPKYp-0bfj84RqAtHuA
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                InstrumentationCapability.this.lambda$getPrintDebugHandler$0$InstrumentationCapability(controlStream, command, (Instrumentation.PrintDebug) obj);
            }
        };
    }

    public /* synthetic */ void lambda$getPrintDebugHandler$0$InstrumentationCapability(ControlStream controlStream, Accessories.Command command, Instrumentation.PrintDebug printDebug) throws Exception {
        String format = this.formatter.format(printDebug.getData().toByteArray(), OutputConfiguration.parse(printDebug.getFlags()));
        if (format == null) {
            controlStream.respond(command, Common.ErrorCode.UNSUPPORTED);
            return;
        }
        this.printer.print(format);
        controlStream.respond(command, Common.ErrorCode.SUCCESS);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.producer.detachActionHandler(this.actionHandler);
        this.actionHandler.cancelAllActions();
        accessoryDescriptor.remove(this.controlStream);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.controlStream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.controlStream.addMessageHandler(Accessories.Command.PRINT_DEBUG, getPrintDebugHandler());
        accessoryDescriptor.add(this.controlStream);
        this.producer.attachActionHandler(this.actionHandler);
    }
}
