package com.amazon.alexa.accessory.capabilities.bulkdata.manifest;

import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator;
import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataRequestMetadata;
import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataUtils;
import com.amazon.alexa.accessory.capabilities.bulkdata.TaskCompletionListener;
import com.amazon.alexa.accessory.capabilities.bulkdata.exceptions.BulkDataException;
import com.amazon.alexa.accessory.capabilities.bulkdata.exceptions.BusyErrorCodeException;
import com.amazon.alexa.accessory.capabilities.bulkdata.identifiers.DataIdentifier;
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
public class IncomingBulkDataManifestEntryTask implements TaskManager.Task {
    private final BulkDataRequestMetadata bulkDataRequestMetadata;
    private final ControlStream controlStream;
    private final BulkDataOrchestrator.BulkDataManifestCallback manifestCallback;
    private int manifestCount = 0;
    private ControlMessageHandler<Bulkdata.BulkDataManifestEntry> messageHandler;
    private final IncomingBulkDataManifestMetrics metrics;
    private ControlResponseHandler responseHandler;
    private final TaskCompletionListener<IncomingBulkDataManifestEntryTask> taskCompletionListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.accessory.capabilities.bulkdata.manifest.IncomingBulkDataManifestEntryTask$1  reason: invalid class name */
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
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class BulkDataManifestEntriesHandler implements ControlMessageHandler<Bulkdata.BulkDataManifestEntry> {
        private final BulkDataOrchestrator.BulkDataManifestCallback callback;

        public BulkDataManifestEntriesHandler(BulkDataOrchestrator.BulkDataManifestCallback bulkDataManifestCallback) {
            this.callback = bulkDataManifestCallback;
        }

        @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
        public void onMessageReceived(ControlStream controlStream, Accessories.Command command, Bulkdata.BulkDataManifestEntry bulkDataManifestEntry) {
            String format;
            Common.ErrorCode errorCode = Common.ErrorCode.SUCCESS;
            if (bulkDataManifestEntry == null) {
                format = "Bulk data manifest entry received is null. Request for bulk data item requires a valid manifest.";
            } else {
                format = IncomingBulkDataManifestEntryTask.this.identifierFailsValidation(bulkDataManifestEntry.getIdentifier()) ? String.format("Invalid identifier: %s. Request for bulk data item needs to include a valid identifier.", BulkDataUtils.toHexString(bulkDataManifestEntry.getIdentifier(), 16)) : null;
            }
            if (format != null) {
                errorCode = Common.ErrorCode.INTERNAL;
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException(format);
                Logger.e("Something has gone wrong when handling an inbound manifest entry", illegalArgumentException);
                this.callback.onError(new BulkDataException("Something has gone wrong when handling an inbound manifest entry", illegalArgumentException));
            }
            if (errorCode == Common.ErrorCode.SUCCESS) {
                this.callback.onManifestEntryReceived(new DataIdentifier(bulkDataManifestEntry.getIdentifier().toByteArray()), bulkDataManifestEntry.getCategory(), bulkDataManifestEntry.getManifestIndex(), bulkDataManifestEntry.getManifestCount(), new CategorySpecificData(bulkDataManifestEntry.getCategorySpecificData().toByteArray()));
                IncomingBulkDataManifestEntryTask.access$208(IncomingBulkDataManifestEntryTask.this);
            }
            try {
                IncomingBulkDataManifestEntryTask.this.controlStream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.BULK_DATA_MANIFEST_ENTRY).setResponse(Accessories.Response.newBuilder().setErrorCode(errorCode).mo10084build()).mo10084build()));
            } catch (Throwable unused) {
                errorCode = Common.ErrorCode.INTERNAL;
            }
            if (errorCode != Common.ErrorCode.SUCCESS) {
                IncomingBulkDataManifestEntryTask.this.metrics.onError();
                IncomingBulkDataManifestEntryTask.this.dispose();
                return;
            }
            Logger.d("Manifest entry received (%d out of %d).", Integer.valueOf(IncomingBulkDataManifestEntryTask.this.manifestCount), Integer.valueOf(bulkDataManifestEntry.getManifestCount()));
            if (IncomingBulkDataManifestEntryTask.this.manifestCount != bulkDataManifestEntry.getManifestCount() || errorCode != Common.ErrorCode.SUCCESS) {
                return;
            }
            Logger.d("Completing manifest task.");
            IncomingBulkDataManifestEntryTask.this.metrics.onSuccess();
            this.callback.onComplete();
            IncomingBulkDataManifestEntryTask.this.dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class IncomingManifestResponseHandler implements ControlResponseHandler {
        private IncomingManifestResponseHandler() {
        }

        @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
        public void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
            int ordinal = response.getErrorCode().ordinal();
            if (ordinal != 0) {
                if (ordinal == 5) {
                    IncomingBulkDataManifestEntryTask.this.metrics.onError();
                    IncomingBulkDataManifestEntryTask.this.manifestCallback.onError(new BulkDataException("No manifests were found on the accessory.", response.getErrorCode()));
                    IncomingBulkDataManifestEntryTask.this.dispose();
                } else if (ordinal != 7) {
                    IncomingBulkDataManifestEntryTask.this.metrics.onError();
                    IncomingBulkDataManifestEntryTask.this.manifestCallback.onError(new BulkDataException("A generic exception occurred when requesting the manifest from the accessory.", response.getErrorCode()));
                    IncomingBulkDataManifestEntryTask.this.dispose();
                } else {
                    IncomingBulkDataManifestEntryTask.this.metrics.onError();
                    IncomingBulkDataManifestEntryTask.this.manifestCallback.onError(new BusyErrorCodeException("The accessory is busy, retry later."));
                    IncomingBulkDataManifestEntryTask.this.dispose();
                }
            }
        }

        /* synthetic */ IncomingManifestResponseHandler(IncomingBulkDataManifestEntryTask incomingBulkDataManifestEntryTask, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public IncomingBulkDataManifestEntryTask(BulkDataRequestMetadata bulkDataRequestMetadata, ControlStream controlStream, TaskCompletionListener<IncomingBulkDataManifestEntryTask> taskCompletionListener, BulkDataOrchestrator.BulkDataManifestCallback bulkDataManifestCallback, IncomingBulkDataManifestMetrics incomingBulkDataManifestMetrics) {
        Preconditions.notNull(bulkDataRequestMetadata, "bulkDataRequestMetadata");
        Preconditions.notNull(controlStream, "controlStream");
        Preconditions.notNull(taskCompletionListener, "taskCallback");
        Preconditions.notNull(bulkDataManifestCallback, "manifestCallback");
        Preconditions.notNull(incomingBulkDataManifestMetrics, "metrics");
        this.bulkDataRequestMetadata = bulkDataRequestMetadata;
        this.controlStream = controlStream;
        this.taskCompletionListener = taskCompletionListener;
        this.manifestCallback = bulkDataManifestCallback;
        this.metrics = incomingBulkDataManifestMetrics;
    }

    static /* synthetic */ int access$208(IncomingBulkDataManifestEntryTask incomingBulkDataManifestEntryTask) {
        int i = incomingBulkDataManifestEntryTask.manifestCount;
        incomingBulkDataManifestEntryTask.manifestCount = i + 1;
        return i;
    }

    private void cleanup() {
        ControlMessageHandler<Bulkdata.BulkDataManifestEntry> controlMessageHandler = this.messageHandler;
        if (controlMessageHandler != null) {
            this.controlStream.removeMessageHandler(controlMessageHandler);
        }
        ControlResponseHandler controlResponseHandler = this.responseHandler;
        if (controlResponseHandler != null) {
            this.controlStream.removeResponseHandler(controlResponseHandler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispose() {
        Logger.d("IncomingBulkDataManifestEntryTask disposing task");
        cleanup();
        this.taskCompletionListener.onTaskComplete(this);
    }

    private void getBulkDataManifest() {
        Logger.d("IncomingBulkDataManifestEntryTask sending request for incoming bulk data manifest.");
        this.metrics.onStart();
        try {
            this.messageHandler = new BulkDataManifestEntriesHandler(this.manifestCallback);
            this.responseHandler = new IncomingManifestResponseHandler(this, null);
            this.controlStream.addMessageHandler(Accessories.Command.BULK_DATA_MANIFEST_ENTRY, this.messageHandler);
            this.controlStream.addResponseHandler(Accessories.Command.GET_BULK_DATA_MANIFEST, this.responseHandler);
            this.controlStream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_BULK_DATA_MANIFEST).setGetBulkDataManifest(Bulkdata.GetBulkDataManifest.newBuilder().setCategory(this.bulkDataRequestMetadata.getCategory()).mo10084build()).mo10084build()));
            Logger.d("IncomingBulkDataManifestEntryTask request sent");
        } catch (Throwable th) {
            Logger.e("IncomingBulkDataManifestEntryTask encountered an exception while requesting for manifests", th);
            this.metrics.onError();
            this.manifestCallback.onError(new BulkDataException("An error occurred when attempting to issue a get manifest request to the accessory", th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean identifierFailsValidation(ByteString byteString) {
        return byteString == null || byteString.isEmpty();
    }

    @Override // com.amazon.alexa.accessory.TaskManager.Task
    public void onStateChanged(int i, int i2) {
        if (i2 == 3) {
            getBulkDataManifest();
        } else if (i2 == 4) {
            dispose();
        } else if (i2 != 1 && i2 != 2) {
        } else {
            cleanup();
        }
    }
}
