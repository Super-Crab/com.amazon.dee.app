package com.amazon.alexa.accessory.capabilities.fitness;

import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.UIUtils;
import com.amazon.alexa.accessory.io.Pipe;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Fitness;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.fitness.FitnessDataSource;
import com.amazon.alexa.accessory.streams.control.ControlResponseHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.amazon.alexa.accessory.streams.fitness.FitnessStream;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import java.io.IOException;
/* loaded from: classes.dex */
public final class GetFitnessDataTask implements TaskManager.Task {
    private static final int BUFFER_CAPACITY = 2048;
    private static final long TIMEOUT_MILLIS = 5000;
    private final Callback callback;
    private boolean completed;
    private final Object[] completedLock;
    private final byte[] continuationToken;
    private final AccessoryDescriptor descriptor;
    private Pipe fitnessPipe;
    private FitnessStream fitnessStream;
    private ControlResponseHandler getFitnessDataResponseHandler;
    private final Producer.Result<FitnessDataSource> result;
    private final ControlStream stream;

    /* loaded from: classes.dex */
    public interface Callback {
        void onDisposed(GetFitnessDataTask getFitnessDataTask);
    }

    public GetFitnessDataTask(AccessoryDescriptor accessoryDescriptor, ControlStream controlStream, byte[] bArr, Producer.Result<FitnessDataSource> result, Callback callback) {
        Preconditions.notNull(accessoryDescriptor, "descriptor");
        Preconditions.notNull(controlStream, "stream");
        Preconditions.notNull(result, "result");
        Preconditions.notNull(callback, "callback");
        this.descriptor = accessoryDescriptor;
        this.stream = controlStream;
        this.continuationToken = bArr;
        this.result = result;
        this.callback = callback;
        this.completedLock = new Object[0];
        this.completed = false;
    }

    private FitnessDataSource extractFitnessDataSource(Accessories.Response response) throws IOException {
        if (Common.ErrorCode.SUCCESS == response.getErrorCode()) {
            if (response.getPayloadCase() == Accessories.Response.PayloadCase.FITNESS_DATA) {
                Fitness.FitnessData fitnessData = response.getFitnessData();
                Fitness.FitnessDataFormat format = fitnessData.getFormat();
                if (Fitness.FitnessDataFormat.UNRECOGNIZED != format && Fitness.FitnessDataFormat.FITNESS_DATA_FORMAT_UNKNOWN != format) {
                    int size = fitnessData.getSize();
                    if (size >= 0) {
                        ByteString sha256Checksum = fitnessData.getSha256Checksum();
                        byte[] bArr = null;
                        byte[] byteArray = (sha256Checksum == null || sha256Checksum.size() <= 0) ? null : sha256Checksum.toByteArray();
                        ByteString continuationToken = fitnessData.getContinuationToken();
                        if (continuationToken != null && continuationToken.size() > 0) {
                            bArr = continuationToken.toByteArray();
                        }
                        this.fitnessPipe = new Pipe(2048, 5000L);
                        if (size == 0) {
                            Logger.d("Fitness data size is 0. Closing pipe");
                            synchronized (this.completedLock) {
                                this.completed = true;
                            }
                            dispose();
                        } else {
                            this.fitnessStream = new FitnessStream(this.fitnessPipe, fitnessData.getSize(), new FitnessStream.Callback() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$GetFitnessDataTask$i5c9MMtTDZYIdWwUncXuyd0M3S0
                                @Override // com.amazon.alexa.accessory.streams.fitness.FitnessStream.Callback
                                public final void onComplete() {
                                    GetFitnessDataTask.this.lambda$extractFitnessDataSource$1$GetFitnessDataTask();
                                }
                            });
                            this.descriptor.add(this.fitnessStream);
                        }
                        return new FitnessDataSource(this.fitnessPipe, format, byteArray, bArr);
                    }
                    throw new IOException(GeneratedOutlineSupport1.outline52("Received negative Fitness data size '", size, "'"));
                }
                throw new IllegalStateException("Unexpected Fitness data format '" + format + "'");
            }
            throw new IllegalStateException("Response doesn't contain a Fitness payload");
        }
        throw new IOException(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("Unsuccessful response in getting diagnostics: ")));
    }

    private void handleGetFitnessDataResponse(Accessories.Response response) {
        try {
            this.result.complete(extractFitnessDataSource(response));
        } catch (IOException | RuntimeException e) {
            this.result.completeWithError(e);
            dispose();
        }
    }

    private void invokeGetFitnessData() {
        this.getFitnessDataResponseHandler = new ControlResponseHandler() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$GetFitnessDataTask$rd7WoxSaNXLb2DoBFRRSN1W2y3k
            @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
            public final void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
                GetFitnessDataTask.this.lambda$invokeGetFitnessData$0$GetFitnessDataTask(controlStream, command, response);
            }
        };
        this.stream.addResponseHandler(Accessories.Command.GET_FITNESS_DATA, this.getFitnessDataResponseHandler);
        Fitness.GetFitnessData.Builder newBuilder = Fitness.GetFitnessData.newBuilder();
        byte[] bArr = this.continuationToken;
        if (bArr != null && bArr.length > 0) {
            newBuilder.setContinuationToken(ByteString.copyFrom(bArr));
        }
        this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_FITNESS_DATA).setGetFitnessData(newBuilder).mo10084build()));
    }

    private void invokeStopFitnessData() {
        synchronized (this.completedLock) {
            if (!this.completed) {
                this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.STOP_FITNESS_DATA).setStopFitnessData(Fitness.StopFitnessData.newBuilder()).mo10084build()));
            }
            dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispose() {
        FitnessStream fitnessStream = this.fitnessStream;
        if (fitnessStream != null) {
            this.descriptor.remove(fitnessStream);
        }
        IOUtils.closeQuietly(this.fitnessPipe);
        ControlResponseHandler controlResponseHandler = this.getFitnessDataResponseHandler;
        if (controlResponseHandler != null) {
            this.stream.removeResponseHandler(controlResponseHandler);
        }
        this.callback.onDisposed(this);
    }

    public /* synthetic */ void lambda$extractFitnessDataSource$1$GetFitnessDataTask() {
        synchronized (this.completedLock) {
            this.completed = true;
        }
        UIUtils.schedule(new Runnable() { // from class: com.amazon.alexa.accessory.capabilities.fitness.-$$Lambda$5Z7nyo2c1Ez-KBC85GnTPAOs_b8
            @Override // java.lang.Runnable
            public final void run() {
                GetFitnessDataTask.this.dispose();
            }
        });
    }

    public /* synthetic */ void lambda$invokeGetFitnessData$0$GetFitnessDataTask(ControlStream controlStream, Accessories.Command command, Accessories.Response response) throws Exception {
        handleGetFitnessDataResponse(response);
    }

    @Override // com.amazon.alexa.accessory.TaskManager.Task
    public void onStateChanged(int i, int i2) {
        if (3 == i2 && (i == 0 || 1 == i)) {
            invokeGetFitnessData();
        } else if (3 != i) {
        } else {
            invokeStopFitnessData();
        }
    }

    public String toString() {
        return "GetFitnessDataTask";
    }
}
