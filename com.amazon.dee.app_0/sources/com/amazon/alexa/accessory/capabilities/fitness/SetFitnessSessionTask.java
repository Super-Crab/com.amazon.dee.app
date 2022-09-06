package com.amazon.alexa.accessory.capabilities.fitness;

import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSession;
import com.amazon.alexa.accessory.streams.control.ControlResponseHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class SetFitnessSessionTask implements TaskManager.Task {
    private final Callback callback;
    private final FitnessSession fitnessSession;
    private final Producer.Result<CompletableResult.Value> result;
    private final ControlStream stream;
    private ControlResponseHandler syncFitnessDataResponseHandler;

    /* loaded from: classes.dex */
    public interface Callback {
        void onDisposed(SetFitnessSessionTask setFitnessSessionTask);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SetFitnessSessionTask(FitnessSession fitnessSession, ControlStream controlStream, Producer.Result<CompletableResult.Value> result, Callback callback) {
        Preconditions.notNull(fitnessSession, "fitnessSession");
        Preconditions.notNull(callback, "fitnessSession");
        Preconditions.notNull(controlStream, "stream");
        Preconditions.notNull(result, "result");
        this.fitnessSession = fitnessSession;
        this.callback = callback;
        this.stream = controlStream;
        this.result = result;
    }

    private void handleSyncFitnessSessionResponse(Accessories.Response response) {
        Logger.d("SetFitnessSessionTask.handleSyncFitnessSessionResponse(...)");
        if (Common.ErrorCode.SUCCESS == response.getErrorCode()) {
            Logger.d("SetFitnessSessionTask: SyncFitnessSession successful");
            this.result.complete(CompletableResult.Value.complete());
        } else {
            Logger.d("SetFitnessSessionTask: SyncFitnessSession failed. Response: %s", response.getErrorCode());
            this.result.completeWithError(new IllegalStateException(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("Unsuccessful Response to SyncFitnessSession Command: "))));
        }
        dispose();
    }

    private void invokeSyncFitnessSession() {
        Logger.d("SetFitnessSessionTask.invokeSyncFitnessSession()");
        this.syncFitnessDataResponseHandler = new ControlResponseHandler() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$SetFitnessSessionTask$PyBDmrRYNnDwzntR4VCqcQlMhC8
            @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
            public final void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
                SetFitnessSessionTask.this.lambda$invokeSyncFitnessSession$0$SetFitnessSessionTask(controlStream, command, response);
            }
        };
        this.stream.addResponseHandler(Accessories.Command.SYNC_FITNESS_SESSION, this.syncFitnessDataResponseHandler);
        try {
            this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.SYNC_FITNESS_SESSION).setSyncFitnessSession(FitnessSessionHelper.marshal(this.fitnessSession)).mo10084build()));
        } catch (IOException e) {
            this.result.completeWithError(e);
            dispose();
        }
    }

    void dispose() {
        Logger.d("SetFitnessSessionTask.dispose()");
        ControlResponseHandler controlResponseHandler = this.syncFitnessDataResponseHandler;
        if (controlResponseHandler != null) {
            this.stream.removeResponseHandler(controlResponseHandler);
        }
        this.callback.onDisposed(this);
    }

    public /* synthetic */ void lambda$invokeSyncFitnessSession$0$SetFitnessSessionTask(ControlStream controlStream, Accessories.Command command, Accessories.Response response) throws Exception {
        handleSyncFitnessSessionResponse(response);
    }

    @Override // com.amazon.alexa.accessory.TaskManager.Task
    public void onStateChanged(int i, int i2) {
        if (i == 0 && 3 == i2) {
            invokeSyncFitnessSession();
        } else if (1 != i || 3 != i2) {
        } else {
            invokeSyncFitnessSession();
        }
    }

    public String toString() {
        return "SetFitnessSessionTask";
    }
}
