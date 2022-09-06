package com.amazon.alexa.accessory.capabilities.bulkdata.session;

import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataRequestMetadata;
import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataUtils;
import com.amazon.alexa.accessory.capabilities.bulkdata.TaskCompletionListener;
import com.amazon.alexa.accessory.capabilities.bulkdata.exceptions.BulkDataException;
import com.amazon.alexa.accessory.capabilities.bulkdata.exceptions.BusyErrorCodeException;
import com.amazon.alexa.accessory.capabilities.bulkdata.exceptions.IdentifierNotFoundException;
import com.amazon.alexa.accessory.capabilities.bulkdata.exceptions.InvalidBulkDataRequestException;
import com.amazon.alexa.accessory.capabilities.bulkdata.identifiers.DataIdentifier;
import com.amazon.alexa.accessory.capabilities.bulkdata.session.BulkDataSession;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Bulkdata;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlResponseHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.google.protobuf.ByteString;
/* loaded from: classes.dex */
public class IncomingBulkDataTransferTask implements TaskManager.Task {
    private ControlResponseHandler abortSessionResponseHandler;
    private final AccessoryDescriptor accessoryDescriptor;
    private final BulkDataRequestMetadata bulkDataRequestMetadata;
    private BulkDataSession bulkDataSession;
    private final BulkDataSessionMetadata bulkDataSessionMetadata;
    private ControlResponseHandler completeSessionResponseHandler;
    private final ControlStream controlStream;
    private final DataIdentifier dataIdentifier;
    private final IncomingBulkDataTransferMetrics metrics;
    private ControlResponseHandler requestDataResponseHandler;
    private ControlMessageHandler<Bulkdata.BulkDataTransferStart> startTransferMessageHandler;
    private ControlMessageHandler<Bulkdata.StopBulkDataTransfer> stopTransferMessageHandler;
    private final TaskCompletionListener<IncomingBulkDataTransferTask> taskCompletionListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.accessory.capabilities.bulkdata.session.IncomingBulkDataTransferTask$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Common$ErrorCode = new int[Common.ErrorCode.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Common$ErrorCode[Common.ErrorCode.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Common$ErrorCode[Common.ErrorCode.NOT_FOUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Common$ErrorCode[Common.ErrorCode.BUSY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Common$ErrorCode[Common.ErrorCode.INVALID.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class RequestBulkDataResponseHandler implements ControlResponseHandler {
        private final BulkDataSession.SessionStartCallback sessionStartCallback;

        public RequestBulkDataResponseHandler(BulkDataSession.SessionStartCallback sessionStartCallback) {
            Preconditions.notNull(sessionStartCallback, "sessionStartCallback");
            this.sessionStartCallback = sessionStartCallback;
        }

        @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
        public void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
            int ordinal = response.getErrorCode().ordinal();
            if (ordinal != 0) {
                if (ordinal == 5) {
                    this.sessionStartCallback.onSessionStartError(new IdentifierNotFoundException(String.format("The requested identifier %s could not be found on the accessory", IncomingBulkDataTransferTask.this.dataIdentifier)));
                    IncomingBulkDataTransferTask.this.metrics.onError();
                    IncomingBulkDataTransferTask.this.dispose();
                } else if (ordinal == 6) {
                    this.sessionStartCallback.onSessionStartError(new InvalidBulkDataRequestException("The requested identifier is not valid."));
                    IncomingBulkDataTransferTask.this.metrics.onError();
                    IncomingBulkDataTransferTask.this.dispose();
                } else if (ordinal != 7) {
                    this.sessionStartCallback.onSessionStartError(new BulkDataException("A generic exception occurred when requesting data from the accessory.", response.getErrorCode()));
                    IncomingBulkDataTransferTask.this.metrics.onError();
                    IncomingBulkDataTransferTask.this.dispose();
                } else {
                    this.sessionStartCallback.onSessionStartError(new BusyErrorCodeException("The accessory is busy, retry later."));
                    IncomingBulkDataTransferTask.this.metrics.onError();
                    IncomingBulkDataTransferTask.this.dispose();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SessionAbortResponseHandler implements ControlResponseHandler {
        private SessionAbortResponseHandler() {
        }

        @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
        public void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
            if (response.getErrorCode().ordinal() != 0) {
                Logger.e(String.format("Did not successfully abort session from the accessory for identifier: %s", IncomingBulkDataTransferTask.this.dataIdentifier));
                IncomingBulkDataTransferTask.this.bulkDataSessionMetadata.getSessionAbortCallback().onSessionAbortedError(new BulkDataException("There was a problem attempting to abort the session", response.getErrorCode()));
            } else {
                IncomingBulkDataTransferTask.this.bulkDataSessionMetadata.getSessionAbortCallback().onSessionAborted(new BulkDataException("Session was aborted."));
            }
            IncomingBulkDataTransferTask.this.metrics.onError();
            IncomingBulkDataTransferTask.this.dispose();
        }

        /* synthetic */ SessionAbortResponseHandler(IncomingBulkDataTransferTask incomingBulkDataTransferTask, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SessionCompleteResponseHandler implements ControlResponseHandler {
        private SessionCompleteResponseHandler() {
        }

        @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
        public void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
            if (response.getErrorCode().ordinal() == 0) {
                IncomingBulkDataTransferTask.this.bulkDataSessionMetadata.getSessionCompleteCallback().onSessionComplete();
                IncomingBulkDataTransferTask.this.metrics.onSuccess();
            } else {
                Logger.e(String.format("Did not successfully complete session from the accessory for identifier: %s", IncomingBulkDataTransferTask.this.dataIdentifier));
                IncomingBulkDataTransferTask.this.bulkDataSessionMetadata.getSessionCompleteCallback().onSessionCompleteError(new BulkDataException("There was a problem attempting to complete the session", response.getErrorCode()));
                IncomingBulkDataTransferTask.this.metrics.onError();
            }
            IncomingBulkDataTransferTask.this.dispose();
        }

        /* synthetic */ SessionCompleteResponseHandler(IncomingBulkDataTransferTask incomingBulkDataTransferTask, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class StartIncomingDataTransferMessageHandler implements ControlMessageHandler<Bulkdata.BulkDataTransferStart> {
        private StartIncomingDataTransferMessageHandler() {
        }

        /* synthetic */ StartIncomingDataTransferMessageHandler(IncomingBulkDataTransferTask incomingBulkDataTransferTask, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
        public void onMessageReceived(ControlStream controlStream, Accessories.Command command, Bulkdata.BulkDataTransferStart bulkDataTransferStart) {
            Common.ErrorCode errorCode;
            Common.ErrorCode errorCode2 = Common.ErrorCode.SUCCESS;
            BulkDataSession.State state = IncomingBulkDataTransferTask.this.bulkDataSession.getState();
            if (bulkDataTransferStart != null) {
                try {
                } catch (Throwable th) {
                    Logger.e("An error occurred when receiving/processing the inbound bulk data", th);
                    Common.ErrorCode errorCode3 = Common.ErrorCode.INTERNAL;
                    if (state == BulkDataSession.State.CREATED) {
                        IncomingBulkDataTransferTask.this.bulkDataSessionMetadata.getSessionStartCallback().onSessionStartError(new BulkDataException("An error occurred when attempting to start the session", th));
                    } else {
                        IncomingBulkDataTransferTask.this.bulkDataSessionMetadata.getBlockReceivedCallback().onBlockReceivedError(new BulkDataException("An error occurred when attempting to receive the current block", th));
                    }
                    errorCode2 = errorCode3;
                }
                if (!IncomingBulkDataTransferTask.this.identifierFailsValidation(bulkDataTransferStart.getIdentifier())) {
                    if (state == BulkDataSession.State.CREATED) {
                        Logger.d("Session not started, starting session");
                        if (IncomingBulkDataTransferTask.this.bulkDataSession == null) {
                            Logger.e(String.format("Bulk data session was null when message was received. Unable to pass session to consumer for identifier %s. Disposing task.", IncomingBulkDataTransferTask.this.dataIdentifier));
                            IncomingBulkDataTransferTask.this.metrics.onError();
                            IncomingBulkDataTransferTask.this.dispose();
                            return;
                        }
                        IncomingBulkDataTransferTask.this.bulkDataSession.start(IncomingBulkDataTransferTask.this.bulkDataSessionMetadata.getSessionStartCallback(), bulkDataTransferStart.getBlockCount());
                    }
                    IncomingBulkDataTransferTask.this.bulkDataSessionMetadata.getBlockReceivedCallback().onBlockReceived(bulkDataTransferStart.getBlockIndex(), bulkDataTransferStart.getSize());
                    try {
                        IncomingBulkDataTransferTask.this.controlStream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.BULK_DATA_TRANSFER_START).setResponse(Accessories.Response.newBuilder().setErrorCode(errorCode2).mo10084build()).mo10084build()));
                        if (errorCode2 == errorCode) {
                            return;
                        }
                    } catch (Throwable th2) {
                        try {
                            Logger.e("An error occurred while sending response to the accessory", th2);
                            if (Common.ErrorCode.INTERNAL == Common.ErrorCode.SUCCESS) {
                                return;
                            }
                        } finally {
                            if (errorCode2 == Common.ErrorCode.SUCCESS) {
                            }
                        }
                    }
                    IncomingBulkDataTransferTask.this.metrics.onError();
                    IncomingBulkDataTransferTask.this.dispose();
                    return;
                }
            }
            throw new IllegalArgumentException("Request for bulk data item needs to include a valid identifier" + BulkDataUtils.toHexString(bulkDataTransferStart.getIdentifier(), 16));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class StopIncomingDataTransferMessageHandler implements ControlMessageHandler<Bulkdata.StopBulkDataTransfer> {
        private StopIncomingDataTransferMessageHandler() {
        }

        /* synthetic */ StopIncomingDataTransferMessageHandler(IncomingBulkDataTransferTask incomingBulkDataTransferTask, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
        public void onMessageReceived(ControlStream controlStream, Accessories.Command command, Bulkdata.StopBulkDataTransfer stopBulkDataTransfer) {
            try {
                IncomingBulkDataTransferTask.this.controlStream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.STOP_BULK_DATA_TRANSFER).setResponse(Accessories.Response.newBuilder().setErrorCode(Common.ErrorCode.SUCCESS).mo10084build()).mo10084build()));
            } catch (Throwable th) {
                Logger.e("An error occurred while responding to a STOP_BULK_DATA_TRANSFER messagefrom the accessory", th);
            }
            IncomingBulkDataTransferTask.this.bulkDataSessionMetadata.getBlockReceivedCallback().onBlockReceivedError(new BulkDataException("Received request to abort bulk data transfer by the accessory", Common.ErrorCode.INTERNAL));
            Logger.d("Received request to abort bulk data transfer by the accessory for " + IncomingBulkDataTransferTask.this.bulkDataSession.getSessionIdentifier());
            IncomingBulkDataTransferTask.this.metrics.onError();
            IncomingBulkDataTransferTask.this.dispose();
        }
    }

    public IncomingBulkDataTransferTask(DataIdentifier dataIdentifier, BulkDataRequestMetadata bulkDataRequestMetadata, BulkDataSessionMetadata bulkDataSessionMetadata, ControlStream controlStream, AccessoryDescriptor accessoryDescriptor, TaskCompletionListener<IncomingBulkDataTransferTask> taskCompletionListener, IncomingBulkDataTransferMetrics incomingBulkDataTransferMetrics) {
        Preconditions.notNull(dataIdentifier, "dataIdentifier");
        Preconditions.notNull(bulkDataRequestMetadata, "bulkDataRequestMetadata");
        Preconditions.notNull(bulkDataSessionMetadata, "bulkDataSessionMetadata");
        Preconditions.notNull(controlStream, "controlStream");
        Preconditions.notNull(accessoryDescriptor, "accessoryDescriptor");
        Preconditions.notNull(taskCompletionListener, "taskCallback");
        Preconditions.notNull(incomingBulkDataTransferMetrics, "metrics");
        this.dataIdentifier = dataIdentifier;
        this.bulkDataRequestMetadata = bulkDataRequestMetadata;
        this.bulkDataSessionMetadata = bulkDataSessionMetadata;
        this.controlStream = controlStream;
        this.taskCompletionListener = taskCompletionListener;
        this.accessoryDescriptor = accessoryDescriptor;
        this.metrics = incomingBulkDataTransferMetrics;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean identifierFailsValidation(ByteString byteString) {
        return byteString == null || byteString.isEmpty();
    }

    private void initiateBulkDataTransfer() {
        this.metrics.onStart();
        if (this.bulkDataSession == null) {
            this.bulkDataSession = new DefaultBulkDataSession(this.dataIdentifier, this.bulkDataRequestMetadata, this.controlStream, this.accessoryDescriptor);
        }
        this.startTransferMessageHandler = new StartIncomingDataTransferMessageHandler(this, null);
        this.stopTransferMessageHandler = new StopIncomingDataTransferMessageHandler(this, null);
        this.requestDataResponseHandler = new RequestBulkDataResponseHandler(this.bulkDataSessionMetadata.getSessionStartCallback());
        this.completeSessionResponseHandler = new SessionCompleteResponseHandler(this, null);
        this.abortSessionResponseHandler = new SessionAbortResponseHandler(this, null);
        this.controlStream.addMessageHandler(Accessories.Command.BULK_DATA_TRANSFER_START, this.startTransferMessageHandler);
        this.controlStream.addMessageHandler(Accessories.Command.STOP_BULK_DATA_TRANSFER, this.stopTransferMessageHandler);
        this.controlStream.addResponseHandler(Accessories.Command.REQUEST_BULK_DATA_TRANSFER, this.requestDataResponseHandler);
        this.controlStream.addResponseHandler(Accessories.Command.BULK_DATA_TRANSFER_COMPLETE, this.completeSessionResponseHandler);
        this.controlStream.addResponseHandler(Accessories.Command.STOP_BULK_DATA_TRANSFER, this.abortSessionResponseHandler);
        Logger.d("Initiating bulkdata transfer for " + this.bulkDataSession.getSessionIdentifier());
        this.controlStream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.REQUEST_BULK_DATA_TRANSFER).setRequestBulkDataTransfer(Bulkdata.RequestBulkDataTransfer.newBuilder().setIdentifier(ByteString.copyFrom(this.bulkDataSession.getDataIdentifier().getBytes())).setValidateIntegrity(false).setBlockStartIndex(this.bulkDataRequestMetadata.getBlockStartIndex()).setCategory(this.bulkDataRequestMetadata.getCategory()).mo10084build()).mo10084build()));
    }

    private void tearDownTaskResources() {
        ControlMessageHandler<Bulkdata.BulkDataTransferStart> controlMessageHandler = this.startTransferMessageHandler;
        if (controlMessageHandler != null) {
            this.controlStream.removeMessageHandler(controlMessageHandler);
        }
        ControlMessageHandler<Bulkdata.StopBulkDataTransfer> controlMessageHandler2 = this.stopTransferMessageHandler;
        if (controlMessageHandler2 != null) {
            this.controlStream.removeMessageHandler(controlMessageHandler2);
        }
        ControlResponseHandler controlResponseHandler = this.requestDataResponseHandler;
        if (controlResponseHandler != null) {
            this.controlStream.removeResponseHandler(controlResponseHandler);
        }
        ControlResponseHandler controlResponseHandler2 = this.completeSessionResponseHandler;
        if (controlResponseHandler2 != null) {
            this.controlStream.removeResponseHandler(controlResponseHandler2);
        }
        ControlResponseHandler controlResponseHandler3 = this.abortSessionResponseHandler;
        if (controlResponseHandler3 != null) {
            this.controlStream.removeResponseHandler(controlResponseHandler3);
        }
    }

    public void dispose() {
        Logger.e("Disposing BulkDataTransferTask");
        tearDownTaskResources();
        BulkDataSession bulkDataSession = this.bulkDataSession;
        if (bulkDataSession != null) {
            bulkDataSession.abort();
        }
        this.taskCompletionListener.onTaskComplete(this);
    }

    @Override // com.amazon.alexa.accessory.TaskManager.Task
    public void onStateChanged(int i, int i2) {
        if (i2 == 3) {
            initiateBulkDataTransfer();
        } else if (i2 == 4) {
            dispose();
        } else if (i2 != 1 && i2 != 2) {
        } else {
            tearDownTaskResources();
        }
    }
}
