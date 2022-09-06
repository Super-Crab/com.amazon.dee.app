package com.amazon.alexa.accessory.capabilities.fitness;

import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.capabilities.fitness.GetFitnessDataTask;
import com.amazon.alexa.accessory.capabilities.fitness.SetFitnessSessionTask;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Fitness;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.fitness.FitnessDataSource;
import com.amazon.alexa.accessory.repositories.fitness.FitnessProducer;
import com.amazon.alexa.accessory.repositories.fitness.FitnessProvider;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSession;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlResponseHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
/* loaded from: classes.dex */
public final class FitnessCapability extends AccessoryCapability implements FitnessProducer.ActionHandler {
    private AccessoryDescriptor descriptor;
    private final FitnessProducer fitnessProducer;
    private final FitnessProvider fitnessProvider;
    private ControlMessageHandler<Fitness.StopLiveFitnessData> incomingStopLiveFitnessDataHandler;
    private ControlMessageHandler<Fitness.LiveFitnessData> liveFitnessDataHandler;
    private final Deque<TaskManager.Task> pendingOrRunningTasks;
    private ControlStream stream;
    private final TaskManager taskManager;

    public FitnessCapability(TaskManager taskManager, FitnessProducer fitnessProducer, FitnessProvider fitnessProvider) {
        Preconditions.notNull(taskManager, "taskManager");
        Preconditions.notNull(fitnessProducer, "fitnessProducer");
        Preconditions.notNull(fitnessProvider, "fitnessProvider");
        this.taskManager = taskManager;
        this.fitnessProducer = fitnessProducer;
        this.fitnessProvider = fitnessProvider;
        this.pendingOrRunningTasks = new ArrayDeque();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ControlMessageHandler<Fitness.StopLiveFitnessData> buildIncomingStopLiveFitnessDataHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$FitnessCapability$SnbYf1KWM6mNv2zRffNh5OsC06s
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                FitnessCapability.this.lambda$buildIncomingStopLiveFitnessDataHandler$8$FitnessCapability(controlStream, command, (Fitness.StopLiveFitnessData) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ControlMessageHandler<Fitness.LiveFitnessData> buildLiveFitnessDataHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$FitnessCapability$95Gq2gOLBocVj9giNZIC2ICbOrM
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                FitnessCapability.this.lambda$buildLiveFitnessDataHandler$6$FitnessCapability(controlStream, command, (Fitness.LiveFitnessData) obj);
            }
        };
    }

    private ControlMessageHandler<Fitness.NotifyFitnessDataAvailable> buildNotifyFitnessDataAvailableHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$FitnessCapability$t__BbgR90dqpuRt_GB3nV2FqPfU
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                FitnessCapability.this.lambda$buildNotifyFitnessDataAvailableHandler$1$FitnessCapability(controlStream, command, (Fitness.NotifyFitnessDataAvailable) obj);
            }
        };
    }

    private ControlResponseHandler buildStartLiveFitnessResponseHandler(final Producer.Result<CompletableResult.Value> result) {
        return new ControlResponseHandler() { // from class: com.amazon.alexa.accessory.capabilities.fitness.FitnessCapability.1
            @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
            public void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
                Logger.d("handleStartLiveFitnessDataResponse(%s)", response);
                if (Common.ErrorCode.SUCCESS == response.getErrorCode()) {
                    result.complete(CompletableResult.Value.complete());
                    FitnessCapability fitnessCapability = FitnessCapability.this;
                    fitnessCapability.liveFitnessDataHandler = fitnessCapability.buildLiveFitnessDataHandler();
                    FitnessCapability fitnessCapability2 = FitnessCapability.this;
                    fitnessCapability2.incomingStopLiveFitnessDataHandler = fitnessCapability2.buildIncomingStopLiveFitnessDataHandler();
                    controlStream.addMessageHandler(Accessories.Command.LIVE_FITNESS_DATA, FitnessCapability.this.liveFitnessDataHandler);
                    controlStream.addMessageHandler(Accessories.Command.STOP_LIVE_FITNESS_DATA, FitnessCapability.this.incomingStopLiveFitnessDataHandler);
                } else {
                    Logger.d("StartLiveFitnessData failed with error code: %s", response.getErrorCode());
                    result.completeWithError(new IllegalStateException(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("Unsuccessful Response to StartLiveFitnessData command: "))));
                }
                controlStream.removeResponseHandler(this);
            }
        };
    }

    private ControlResponseHandler buildStopLiveFitnessResponseHandler(final Producer.Result<CompletableResult.Value> result) {
        return new ControlResponseHandler() { // from class: com.amazon.alexa.accessory.capabilities.fitness.FitnessCapability.2
            @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
            public void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
                Logger.d("handleStopLiveFitnessDataResponse(%s)", response);
                if (Common.ErrorCode.SUCCESS == response.getErrorCode()) {
                    result.complete(CompletableResult.Value.complete());
                    FitnessCapability.this.removeLiveAndStopFitnessDataMessageHandlers();
                } else {
                    Logger.d("StopLiveFitnessData failed, will not remove live / stop fitness data messagehandlers. Error code: %s", response.getErrorCode());
                    result.completeWithError(new IllegalStateException(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("Unsuccessful Response to StopLiveFitnessData command: "))));
                }
                controlStream.removeResponseHandler(this);
            }
        };
    }

    private ControlMessageHandler<Fitness.SyncFitnessSession> buildSyncFitnessSessionHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$FitnessCapability$JJKjseuN7Bv18iNibPHzpyDIx-k
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                FitnessCapability.this.lambda$buildSyncFitnessSessionHandler$4$FitnessCapability(controlStream, command, (Fitness.SyncFitnessSession) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeLiveAndStopFitnessDataMessageHandlers() {
        ControlMessageHandler<Fitness.LiveFitnessData> controlMessageHandler = this.liveFitnessDataHandler;
        if (controlMessageHandler != null) {
            this.stream.removeMessageHandler(controlMessageHandler);
            this.liveFitnessDataHandler = null;
        }
        ControlMessageHandler<Fitness.StopLiveFitnessData> controlMessageHandler2 = this.incomingStopLiveFitnessDataHandler;
        if (controlMessageHandler2 != null) {
            this.stream.removeMessageHandler(controlMessageHandler2);
            this.incomingStopLiveFitnessDataHandler = null;
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessProducer.ActionHandler
    public void handleGetFitnessData(byte[] bArr, Producer.Result<FitnessDataSource> result) {
        GetFitnessDataTask getFitnessDataTask = new GetFitnessDataTask(this.descriptor, this.stream, bArr, result, new GetFitnessDataTask.Callback() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$FitnessCapability$q1YrSVS4wHX72qCgYMyDFAX5Les
            @Override // com.amazon.alexa.accessory.capabilities.fitness.GetFitnessDataTask.Callback
            public final void onDisposed(GetFitnessDataTask getFitnessDataTask2) {
                FitnessCapability.this.lambda$handleGetFitnessData$9$FitnessCapability(getFitnessDataTask2);
            }
        });
        this.taskManager.schedule(getFitnessDataTask, 4);
        this.pendingOrRunningTasks.addLast(getFitnessDataTask);
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessProducer.ActionHandler
    public void handleSetFitnessSession(FitnessSession fitnessSession, Producer.Result<CompletableResult.Value> result) {
        Logger.d("handleSetFitnessSession(%s, ...)", fitnessSession);
        SetFitnessSessionTask setFitnessSessionTask = new SetFitnessSessionTask(fitnessSession, this.stream, result, new SetFitnessSessionTask.Callback() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$FitnessCapability$OlPTN1V0thxGuxsU-QLaV0ItfEM
            @Override // com.amazon.alexa.accessory.capabilities.fitness.SetFitnessSessionTask.Callback
            public final void onDisposed(SetFitnessSessionTask setFitnessSessionTask2) {
                FitnessCapability.this.lambda$handleSetFitnessSession$10$FitnessCapability(setFitnessSessionTask2);
            }
        });
        this.taskManager.schedule(setFitnessSessionTask, 4);
        this.pendingOrRunningTasks.addLast(setFitnessSessionTask);
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessProducer.ActionHandler
    public void handleStartLiveFitnessData(List<Integer> list, Producer.Result<CompletableResult.Value> result) {
        Preconditions.notNull(list, "categories");
        Preconditions.notNull(result, "result");
        this.stream.addResponseHandler(Accessories.Command.START_LIVE_FITNESS_DATA, buildStartLiveFitnessResponseHandler(result));
        this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.START_LIVE_FITNESS_DATA).setStartLiveFitnessData(Fitness.StartLiveFitnessData.newBuilder().addAllCategory(list)).mo10084build()));
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessProducer.ActionHandler
    public void handleStopLiveFitnessData(List<Integer> list, Producer.Result<CompletableResult.Value> result) {
        Preconditions.notNull(list, "categories");
        Preconditions.notNull(result, "result");
        this.stream.addResponseHandler(Accessories.Command.STOP_LIVE_FITNESS_DATA, buildStopLiveFitnessResponseHandler(result));
        this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.STOP_LIVE_FITNESS_DATA).setStopLiveFitnessData(Fitness.StopLiveFitnessData.newBuilder().addAllCategory(list)).mo10084build()));
    }

    public /* synthetic */ void lambda$buildIncomingStopLiveFitnessDataHandler$8$FitnessCapability(final ControlStream controlStream, Accessories.Command command, Fitness.StopLiveFitnessData stopLiveFitnessData) throws Exception {
        this.fitnessProvider.onStopLiveFitnessData(stopLiveFitnessData, new FitnessProvider.OnSuccessListener() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$FitnessCapability$sPDbkTE7YatVksED26V2EBWjqOo
            @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessProvider.OnSuccessListener
            public final void onSuccess() {
                ControlStream.this.respond(Accessories.Command.STOP_LIVE_FITNESS_DATA, Common.ErrorCode.SUCCESS);
            }
        });
        removeLiveAndStopFitnessDataMessageHandlers();
    }

    public /* synthetic */ void lambda$buildLiveFitnessDataHandler$6$FitnessCapability(final ControlStream controlStream, Accessories.Command command, Fitness.LiveFitnessData liveFitnessData) throws Exception {
        this.fitnessProvider.onLiveFitnessData(liveFitnessData, new FitnessProvider.OnSuccessListener() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$FitnessCapability$5O4mP0FQvIbOeDVL6DY5A91qfL8
            @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessProvider.OnSuccessListener
            public final void onSuccess() {
                ControlStream.this.respond(Accessories.Command.LIVE_FITNESS_DATA, Common.ErrorCode.SUCCESS);
            }
        });
    }

    public /* synthetic */ void lambda$buildNotifyFitnessDataAvailableHandler$1$FitnessCapability(final ControlStream controlStream, Accessories.Command command, Fitness.NotifyFitnessDataAvailable notifyFitnessDataAvailable) throws Exception {
        this.fitnessProvider.onFitnessDataAvailable(new FitnessProvider.OnSuccessListener() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$FitnessCapability$i4ntAmoN4MMdAQOGKWzXrmKoEUU
            @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessProvider.OnSuccessListener
            public final void onSuccess() {
                ControlStream.this.respond(Accessories.Command.NOTIFY_FITNESS_DATA_AVAILABLE, Common.ErrorCode.SUCCESS);
            }
        });
    }

    public /* synthetic */ void lambda$buildSyncFitnessSessionHandler$4$FitnessCapability(final ControlStream controlStream, Accessories.Command command, Fitness.SyncFitnessSession syncFitnessSession) throws Exception {
        try {
            this.fitnessProvider.onSyncFitnessSession(FitnessSessionHelper.unmarshal(syncFitnessSession), new FitnessProvider.OnSuccessListener() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$FitnessCapability$2lbLKnSeO4C_8D24M0EX7811ow8
                @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessProvider.OnSuccessListener
                public final void onSuccess() {
                    ControlStream.this.respond(Accessories.Command.SYNC_FITNESS_SESSION, Common.ErrorCode.SUCCESS);
                }
            }, new FitnessProvider.OnFailureListener() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$FitnessCapability$KyBVNRRELB1K-_0D8pZALQ0Wggw
                @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessProvider.OnFailureListener
                public final void onFailure() {
                    ControlStream.this.respond(Accessories.Command.SYNC_FITNESS_SESSION, Common.ErrorCode.INVALID);
                }
            });
        } catch (IOException unused) {
            controlStream.respond(Accessories.Command.SYNC_FITNESS_SESSION, Common.ErrorCode.INVALID);
        }
    }

    public /* synthetic */ void lambda$handleGetFitnessData$9$FitnessCapability(GetFitnessDataTask getFitnessDataTask) {
        this.taskManager.dispose(getFitnessDataTask);
        this.pendingOrRunningTasks.remove(getFitnessDataTask);
    }

    public /* synthetic */ void lambda$handleSetFitnessSession$10$FitnessCapability(SetFitnessSessionTask setFitnessSessionTask) {
        this.taskManager.dispose(setFitnessSessionTask);
        this.pendingOrRunningTasks.remove(setFitnessSessionTask);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.fitnessProducer.detachActionHandler(this);
        removeLiveAndStopFitnessDataMessageHandlers();
        accessoryDescriptor.remove(this.stream);
        while (true) {
            TaskManager.Task pollLast = this.pendingOrRunningTasks.pollLast();
            if (pollLast != null) {
                this.taskManager.dispose(pollLast);
            } else {
                return;
            }
        }
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.fitnessProducer.attachActionHandler(this);
        this.descriptor = accessoryDescriptor;
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.stream.addMessageHandler(Accessories.Command.NOTIFY_FITNESS_DATA_AVAILABLE, buildNotifyFitnessDataAvailableHandler());
        this.stream.addMessageHandler(Accessories.Command.SYNC_FITNESS_SESSION, buildSyncFitnessSessionHandler());
        accessoryDescriptor.add(this.stream);
    }
}
