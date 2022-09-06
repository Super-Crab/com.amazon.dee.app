package com.amazon.alexa.accessory.capabilities.bulkdata;

import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataCapability;
import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator;
import com.amazon.alexa.accessory.capabilities.bulkdata.exceptions.BulkDataException;
import com.amazon.alexa.accessory.capabilities.bulkdata.exceptions.InvalidBulkDataRequestException;
import com.amazon.alexa.accessory.capabilities.bulkdata.identifiers.DataIdentifier;
import com.amazon.alexa.accessory.capabilities.bulkdata.manifest.CategorySpecificData;
import com.amazon.alexa.accessory.capabilities.bulkdata.manifest.IncomingBulkDataManifestEntryTask;
import com.amazon.alexa.accessory.capabilities.bulkdata.manifest.IncomingBulkDataManifestMetrics;
import com.amazon.alexa.accessory.capabilities.bulkdata.manifest.OutgoingBulkDataManifestTask;
import com.amazon.alexa.accessory.capabilities.bulkdata.session.BulkDataSession;
import com.amazon.alexa.accessory.capabilities.bulkdata.session.BulkDataSessionMetadata;
import com.amazon.alexa.accessory.capabilities.bulkdata.session.IncomingBulkDataTransferMetrics;
import com.amazon.alexa.accessory.capabilities.bulkdata.session.IncomingBulkDataTransferTask;
import com.amazon.alexa.accessory.capabilities.speech.AccessoryIdentifierProvider;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Bulkdata;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlResponseHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class BulkDataCapability extends AccessoryCapability {
    private static final int AUDIO_RELATED_PRIORITY = 5;
    private AccessoryDescriptor accessoryDescriptor;
    private AccessoryIdentifierProvider accessoryIdentifierProvider;
    private BulkDataOrchestrator bulkDataOrchestrator;
    private BulkDataApiHandle capabilityApi;
    private ControlStream controlStream;
    private Set<TaskManager.Task> createdTasks = new HashSet();
    private Disposable deviceDisposable;
    private Device.DeviceInformation deviceInformation;
    private DeviceRepositoryV2 deviceRepositoryV2;
    private String deviceType;
    private Handler mainThreadHandler;
    private TaskManager taskManager;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class BulkDataApiHandle implements BulkDataOrchestrator.BulkDataCapabilityApi {
        private BulkDataApiHandle() {
        }

        @Override // com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator.BulkDataCapabilityApi
        public void getIncomingManifest(int i, BulkDataOrchestrator.BulkDataManifestCallback bulkDataManifestCallback) {
            try {
                BulkDataCapability.this.requestIncomingBulkDataManifest(bulkDataManifestCallback, new BulkDataRequestMetadata(BulkDataCategory.fromValue(i)));
            } catch (InvalidBulkDataRequestException e) {
                bulkDataManifestCallback.onError(e);
            }
        }

        @Override // com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator.BulkDataCapabilityApi
        public String identifyAccessory() {
            return BulkDataCapability.this.accessoryIdentifierProvider.getIdentifier();
        }

        public /* synthetic */ void lambda$sendOutgoingManifest$0$BulkDataCapability$BulkDataApiHandle(final OutgoingBulkDataManifestTask outgoingBulkDataManifestTask) {
            BulkDataCapability.this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataCapability.BulkDataApiHandle.1
                @Override // java.lang.Runnable
                public void run() {
                    BulkDataCapability.this.taskManager.dispose(outgoingBulkDataManifestTask);
                }
            });
        }

        @Override // com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator.BulkDataCapabilityApi
        public void notifyOutgoingDataAvailable(int i, BulkDataOrchestrator.NotifyDataAvailableCallback notifyDataAvailableCallback) {
            try {
                BulkDataCapability.this.controlStream.addResponseHandler(Accessories.Command.NOTIFY_BULK_DATA_AVAILABLE, new OutgoingDataAvailableResponseHandler(notifyDataAvailableCallback));
            } catch (IllegalArgumentException unused) {
                notifyDataAvailableCallback.onError(new BulkDataException("Still awaiting response from a previous NOTIFY_BULK_DATA_AVAILABLE call, try again later", Common.ErrorCode.BUSY));
            }
            BulkDataCapability.this.dispatchNotifyBulkDataAvailableControlMessage(i);
        }

        @Override // com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator.BulkDataCapabilityApi
        public void sendOutgoingManifest(int i, Iterable<CategorySpecificData> iterable, final BulkDataOrchestrator.SendManifestCallback sendManifestCallback) {
            final OutgoingBulkDataManifestTask outgoingBulkDataManifestTask = new OutgoingBulkDataManifestTask(BulkDataCategory.fromValue(i), iterable, BulkDataCapability.this.controlStream, new TaskCompletionListener() { // from class: com.amazon.alexa.accessory.capabilities.bulkdata.-$$Lambda$BulkDataCapability$BulkDataApiHandle$Lus2OrXkddcPuPvosYmLkwWIpmc
                @Override // com.amazon.alexa.accessory.capabilities.bulkdata.TaskCompletionListener
                public final void onTaskComplete(TaskManager.Task task) {
                    BulkDataCapability.BulkDataApiHandle.this.lambda$sendOutgoingManifest$0$BulkDataCapability$BulkDataApiHandle((OutgoingBulkDataManifestTask) task);
                }
            }, sendManifestCallback);
            BulkDataCapability.this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataCapability.BulkDataApiHandle.2
                @Override // java.lang.Runnable
                public void run() {
                    if (BulkDataCapability.this.taskManager.schedule(outgoingBulkDataManifestTask, 4)) {
                        Logger.d("OutgoingBulkDataManifestTask scheduled");
                        return;
                    }
                    Logger.e("An error occurred scheduling OutgoingBulkDataManifestTask");
                    sendManifestCallback.onError(new BulkDataException("An error occurred scheduling OutgoingBulkDataManifestTask"));
                }
            });
        }

        @Override // com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator.BulkDataCapabilityApi
        public void startIncomingBulkDataSession(DataIdentifier dataIdentifier, int i, BulkDataSession.BlockReceivedCallback blockReceivedCallback, BulkDataSession.SessionStartCallback sessionStartCallback, BulkDataSession.SessionCompleteCallback sessionCompleteCallback, BulkDataSession.SessionAbortCallback sessionAbortCallback) {
            startIncomingBulkDataSession(dataIdentifier, i, 0, blockReceivedCallback, sessionStartCallback, sessionCompleteCallback, sessionAbortCallback);
        }

        @Override // com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator.BulkDataCapabilityApi
        public void startIncomingBulkDataSession(DataIdentifier dataIdentifier, int i, int i2, BulkDataSession.BlockReceivedCallback blockReceivedCallback, BulkDataSession.SessionStartCallback sessionStartCallback, BulkDataSession.SessionCompleteCallback sessionCompleteCallback, BulkDataSession.SessionAbortCallback sessionAbortCallback) {
            try {
                BulkDataCapability.this.initiateIncomingBulkDataRequest(dataIdentifier, new BulkDataRequestMetadata(BulkDataCategory.fromValue(i), i2), blockReceivedCallback, sessionStartCallback, sessionCompleteCallback, sessionAbortCallback);
            } catch (InvalidBulkDataRequestException e) {
                sessionStartCallback.onSessionStartError(e);
            }
        }
    }

    /* loaded from: classes.dex */
    private class IncomingDataAvailableMessageHandler implements ControlMessageHandler<Bulkdata.NotifyBulkDataAvailable> {
        private IncomingDataAvailableMessageHandler() {
        }

        @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
        public void onMessageReceived(ControlStream controlStream, Accessories.Command command, Bulkdata.NotifyBulkDataAvailable notifyBulkDataAvailable) {
            BulkDataCapability.this.bulkDataOrchestrator.onNotifyIncomingDataAvailable(BulkDataCapability.this.capabilityApi, notifyBulkDataAvailable.getCategory());
        }
    }

    /* loaded from: classes.dex */
    private class OutgoingDataAvailableResponseHandler implements ControlResponseHandler {
        private final BulkDataOrchestrator.NotifyDataAvailableCallback notifyDataAvailableCallback;

        OutgoingDataAvailableResponseHandler(BulkDataOrchestrator.NotifyDataAvailableCallback notifyDataAvailableCallback) {
            Preconditions.notNull(notifyDataAvailableCallback, "callback");
            this.notifyDataAvailableCallback = notifyDataAvailableCallback;
        }

        @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
        public void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
            if (response.getErrorCode() != Common.ErrorCode.SUCCESS) {
                this.notifyDataAvailableCallback.onError(new BulkDataException("Attempt to notify accessory of available bulk data failed", response.getErrorCode()));
            } else {
                this.notifyDataAvailableCallback.onNotifySuccess();
            }
            BulkDataCapability.this.controlStream.removeResponseHandler(this);
        }
    }

    /* loaded from: classes.dex */
    private class OutgoingManifestRequestMessageHandler implements ControlMessageHandler<Bulkdata.GetBulkDataManifest> {

        /* loaded from: classes.dex */
        public class OutgoingManifestRequestCallback implements BulkDataOrchestrator.OnRequestManifestCallback {
            private final ControlStream stream;

            OutgoingManifestRequestCallback(ControlStream controlStream) {
                Preconditions.notNull(controlStream, "stream");
                this.stream = controlStream;
            }

            @Override // com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator.OnRequestManifestCallback
            public void sendErrorResponse(Common.ErrorCode errorCode) {
                this.stream.respond(Accessories.Command.GET_BULK_DATA_MANIFEST, errorCode);
            }

            @Override // com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator.OnRequestManifestCallback
            public void sendSuccessResponse() {
                this.stream.respond(Accessories.Command.GET_BULK_DATA_MANIFEST, Common.ErrorCode.SUCCESS);
            }
        }

        private OutgoingManifestRequestMessageHandler() {
        }

        @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
        public void onMessageReceived(ControlStream controlStream, Accessories.Command command, Bulkdata.GetBulkDataManifest getBulkDataManifest) {
            int category = getBulkDataManifest.getCategory();
            Logger.d("Received bulk data manifest request for category: %d", Integer.valueOf(category));
            if (BulkDataCategory.isValidCategory(category)) {
                BulkDataCapability.this.bulkDataOrchestrator.onReceiveOutgoingManifestRequest(category, BulkDataCapability.this.capabilityApi, new OutgoingManifestRequestCallback(controlStream));
                return;
            }
            Logger.d("Received bulk data manifest request for an unsupported category: %d", Integer.valueOf(category));
            controlStream.respond(Accessories.Command.GET_BULK_DATA_MANIFEST, Common.ErrorCode.UNSUPPORTED);
        }
    }

    public BulkDataCapability(TaskManager taskManager, BulkDataOrchestrator bulkDataOrchestrator, DeviceRepositoryV2 deviceRepositoryV2, AccessoryIdentifierProvider accessoryIdentifierProvider) {
        Preconditions.notNull(taskManager, "taskManager");
        Preconditions.notNull(bulkDataOrchestrator, "bulkDataOrchestrator");
        Preconditions.notNull(deviceRepositoryV2, "deviceRepositoryV2");
        Preconditions.notNull(accessoryIdentifierProvider, "accessoryIdentifierProvider");
        this.taskManager = taskManager;
        this.bulkDataOrchestrator = bulkDataOrchestrator;
        this.deviceRepositoryV2 = deviceRepositoryV2;
        this.accessoryIdentifierProvider = accessoryIdentifierProvider;
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchNotifyBulkDataAvailableControlMessage(int i) {
        Logger.d("Sending NOTIFY_BULK_DATA_AVAILABLE message for category %d to accessory.", Integer.valueOf(i));
        this.controlStream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.NOTIFY_BULK_DATA_AVAILABLE).setNotifyBulkDataAvailable(Bulkdata.NotifyBulkDataAvailable.newBuilder().setCategory(i).mo10084build()).mo10084build()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initiateIncomingBulkDataRequest(final DataIdentifier dataIdentifier, BulkDataRequestMetadata bulkDataRequestMetadata, BulkDataSession.BlockReceivedCallback blockReceivedCallback, final BulkDataSession.SessionStartCallback sessionStartCallback, BulkDataSession.SessionCompleteCallback sessionCompleteCallback, BulkDataSession.SessionAbortCallback sessionAbortCallback) {
        final IncomingBulkDataTransferTask incomingBulkDataTransferTask = new IncomingBulkDataTransferTask(dataIdentifier, bulkDataRequestMetadata, new BulkDataSessionMetadata(blockReceivedCallback, sessionStartCallback, sessionCompleteCallback, sessionAbortCallback), this.controlStream, this.accessoryDescriptor, new TaskCompletionListener() { // from class: com.amazon.alexa.accessory.capabilities.bulkdata.-$$Lambda$BulkDataCapability$BgfLHaS7lhful6G0D4qdSPFmVYM
            @Override // com.amazon.alexa.accessory.capabilities.bulkdata.TaskCompletionListener
            public final void onTaskComplete(TaskManager.Task task) {
                BulkDataCapability.this.lambda$initiateIncomingBulkDataRequest$3$BulkDataCapability((IncomingBulkDataTransferTask) task);
            }
        }, new IncomingBulkDataTransferMetrics(this.deviceType));
        this.createdTasks.add(incomingBulkDataTransferTask);
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataCapability.2
            @Override // java.lang.Runnable
            public void run() {
                if (BulkDataCapability.this.taskManager.schedule(incomingBulkDataTransferTask, 5)) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Scheduled a bulk data transfer task for identifier: ");
                    outline107.append(dataIdentifier);
                    Logger.d(outline107.toString());
                    return;
                }
                Logger.e("An error occurred when attempting to schedule the bulk data transfer taskfor identifier: %s", dataIdentifier);
                sessionStartCallback.onSessionStartError(new BulkDataException(String.format("An error occurred when attempting to schedule the bulk data transfer task for identifier: %s", dataIdentifier.getBytes())));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestIncomingBulkDataManifest(final BulkDataOrchestrator.BulkDataManifestCallback bulkDataManifestCallback, BulkDataRequestMetadata bulkDataRequestMetadata) {
        Logger.d("Getting bulk data manifest from accessory.");
        final IncomingBulkDataManifestEntryTask incomingBulkDataManifestEntryTask = new IncomingBulkDataManifestEntryTask(bulkDataRequestMetadata, this.controlStream, new TaskCompletionListener() { // from class: com.amazon.alexa.accessory.capabilities.bulkdata.-$$Lambda$BulkDataCapability$7nzpLJUR46dJ1qH5Qvnh2qRYvJY
            @Override // com.amazon.alexa.accessory.capabilities.bulkdata.TaskCompletionListener
            public final void onTaskComplete(TaskManager.Task task) {
                BulkDataCapability.this.lambda$requestIncomingBulkDataManifest$4$BulkDataCapability((IncomingBulkDataManifestEntryTask) task);
            }
        }, bulkDataManifestCallback, new IncomingBulkDataManifestMetrics(this.deviceType));
        this.createdTasks.add(incomingBulkDataManifestEntryTask);
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataCapability.4
            @Override // java.lang.Runnable
            public void run() {
                if (BulkDataCapability.this.taskManager.schedule(incomingBulkDataManifestEntryTask, 4)) {
                    Logger.d("IncomingBulkDataManifestEntryTask scheduled");
                    return;
                }
                Logger.e("An error occurred when attempting to get the manifest from accessory");
                bulkDataManifestCallback.onError(new BulkDataException("An error occurred when attempting get the manifest from accessory"));
            }
        });
    }

    public /* synthetic */ void lambda$initiateIncomingBulkDataRequest$3$BulkDataCapability(final IncomingBulkDataTransferTask incomingBulkDataTransferTask) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataCapability.1
            @Override // java.lang.Runnable
            public void run() {
                BulkDataCapability.this.taskManager.dispose(incomingBulkDataTransferTask);
            }
        });
    }

    public /* synthetic */ void lambda$onDispose$2$BulkDataCapability(TaskManager.Task task) {
        this.taskManager.dispose(task);
    }

    public /* synthetic */ void lambda$onInitialize$0$BulkDataCapability(AccessoryDescriptor accessoryDescriptor, Device.DeviceInformation deviceInformation) throws Throwable {
        Logger.d("Bulk data capability received new Device Information");
        if (this.deviceInformation == null) {
            this.deviceInformation = deviceInformation;
            this.deviceType = deviceInformation.getDeviceType();
            Logger.d("Bulk data capability adding message handlers");
            this.capabilityApi = new BulkDataApiHandle();
            this.controlStream.addMessageHandler(Accessories.Command.NOTIFY_BULK_DATA_AVAILABLE, new IncomingDataAvailableMessageHandler());
            this.controlStream.addMessageHandler(Accessories.Command.GET_BULK_DATA_MANIFEST, new OutgoingManifestRequestMessageHandler());
            this.bulkDataOrchestrator.registerApi(this.capabilityApi);
            accessoryDescriptor.add(this.controlStream);
        }
    }

    public /* synthetic */ void lambda$requestIncomingBulkDataManifest$4$BulkDataCapability(final IncomingBulkDataManifestEntryTask incomingBulkDataManifestEntryTask) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataCapability.3
            @Override // java.lang.Runnable
            public void run() {
                BulkDataCapability.this.taskManager.dispose(incomingBulkDataManifestEntryTask);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        ControlStream controlStream = this.controlStream;
        if (controlStream != null) {
            accessoryDescriptor.remove(controlStream);
        }
        BulkDataApiHandle bulkDataApiHandle = this.capabilityApi;
        if (bulkDataApiHandle != null) {
            this.bulkDataOrchestrator.deregisterApi(bulkDataApiHandle);
        }
        for (final TaskManager.Task task : this.createdTasks) {
            this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.capabilities.bulkdata.-$$Lambda$BulkDataCapability$oJt12yEGtaVAN0n38KtXqAgoxzM
                @Override // java.lang.Runnable
                public final void run() {
                    BulkDataCapability.this.lambda$onDispose$2$BulkDataCapability(task);
                }
            });
        }
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(final AccessoryDescriptor accessoryDescriptor) {
        this.accessoryDescriptor = accessoryDescriptor;
        this.controlStream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.deviceDisposable = this.deviceRepositoryV2.queryDeviceInformation().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.bulkdata.-$$Lambda$BulkDataCapability$HfMgeuAojGtO00sETWyjy8YtsOc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                BulkDataCapability.this.lambda$onInitialize$0$BulkDataCapability(accessoryDescriptor, (Device.DeviceInformation) obj);
            }
        }, $$Lambda$BulkDataCapability$C5aVzZnelmHO9uP7mhaNkcFzRo.INSTANCE);
    }
}
