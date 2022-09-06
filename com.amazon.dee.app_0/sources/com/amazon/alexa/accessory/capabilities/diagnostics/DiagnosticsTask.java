package com.amazon.alexa.accessory.capabilities.diagnostics;

import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.UIUtils;
import com.amazon.alexa.accessory.io.Pipe;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.DiagnosticsOuterClass;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.streams.control.ControlResponseHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.amazon.alexa.accessory.streams.diagnostics.DiagnosticsStream;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes.dex */
public final class DiagnosticsTask implements TaskManager.Task {
    private static final int BUFFER_CAPACITY = 2048;
    private static final long TIMEOUT_MILLIS = 5000;
    private final Callback callback;
    private boolean completed;
    private final AccessoryDescriptor descriptor;
    private DiagnosticsStream diagnosticsStream;
    private final DiagnosticsOuterClass.DiagnosticsType diagnosticsType;
    private ControlResponseHandler getDiagnosticsMessageHandler;
    private final Producer.Result<Source> result;
    private final ControlStream stream;

    /* loaded from: classes.dex */
    public interface Callback {
        void onCompleted(DiagnosticsTask diagnosticsTask);
    }

    public DiagnosticsTask(AccessoryDescriptor accessoryDescriptor, ControlStream controlStream, Producer.Result<Source> result, DiagnosticsOuterClass.DiagnosticsType diagnosticsType, Callback callback) {
        Preconditions.notNull(accessoryDescriptor, "descriptor");
        Preconditions.notNull(controlStream, "stream");
        Preconditions.notNull(result, "result");
        Preconditions.notNull(diagnosticsType, "diagnosticsType");
        Preconditions.notNull(callback, "callback");
        this.callback = callback;
        this.diagnosticsType = diagnosticsType;
        this.descriptor = accessoryDescriptor;
        this.stream = controlStream;
        this.result = result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispose() {
        if (this.completed) {
            return;
        }
        DiagnosticsStream diagnosticsStream = this.diagnosticsStream;
        if (diagnosticsStream != null) {
            this.descriptor.remove(diagnosticsStream);
        }
        ControlResponseHandler controlResponseHandler = this.getDiagnosticsMessageHandler;
        if (controlResponseHandler != null) {
            this.stream.removeResponseHandler(controlResponseHandler);
        }
        this.completed = true;
        this.callback.onCompleted(this);
    }

    private ControlResponseHandler getGetDiagnosticsHandler() {
        return new ControlResponseHandler() { // from class: com.amazon.alexa.accessory.capabilities.diagnostics.-$$Lambda$DiagnosticsTask$GxHGDuKvSuOxEaUZZcwBjcnaXGQ
            @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
            public final void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
                DiagnosticsTask.this.lambda$getGetDiagnosticsHandler$1$DiagnosticsTask(controlStream, command, response);
            }
        };
    }

    private void query() {
        this.getDiagnosticsMessageHandler = getGetDiagnosticsHandler();
        this.stream.addResponseHandler(Accessories.Command.GET_DIAGNOSTICS, this.getDiagnosticsMessageHandler);
        this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_DIAGNOSTICS).setGetDiagnostics(DiagnosticsOuterClass.GetDiagnostics.newBuilder().setType(this.diagnosticsType)).mo10084build()));
    }

    private void stop() {
        if (!this.completed) {
            this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.STOP_DIAGNOSTICS).setStopDiagnostics(DiagnosticsOuterClass.StopDiagnostics.newBuilder().setType(this.diagnosticsType).setErrorCode(Common.ErrorCode.USER_CANCELLED)).mo10084build()));
        }
        dispose();
    }

    public /* synthetic */ void lambda$getGetDiagnosticsHandler$1$DiagnosticsTask(ControlStream controlStream, Accessories.Command command, Accessories.Response response) throws Exception {
        if (this.diagnosticsStream != null) {
            this.result.completeWithError(new IOException("Received response for GetDiagnostics, but already expecting diagnostics data"));
            dispose();
        } else if (response.getErrorCode() != Common.ErrorCode.SUCCESS) {
            this.result.completeWithError(new IOException(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("Unsuccessful response in getting diagnostics: "))));
            dispose();
        } else if (response.getPayloadCase() != Accessories.Response.PayloadCase.DIAGNOSTICS) {
            this.result.completeWithError(new IllegalStateException("Response doesn't contain diagnostics payload"));
            dispose();
        } else {
            DiagnosticsOuterClass.Diagnostics diagnostics = response.getDiagnostics();
            DiagnosticsOuterClass.DiagnosticsType type = diagnostics.getType();
            DiagnosticsOuterClass.DiagnosticsType diagnosticsType = this.diagnosticsType;
            if (diagnosticsType != type) {
                this.result.completeWithError(new IllegalStateException(String.format("Unexpected diagnostics type in response. Expected %s and got %s", diagnosticsType, type)));
                dispose();
                return;
            }
            final Pipe pipe = new Pipe(2048, 5000L);
            this.result.complete(pipe);
            if (diagnostics.getSize() == 0) {
                Logger.d("Diagnostics size is 0. Closing pipe");
                IOUtils.closeQuietly(pipe);
                dispose();
                return;
            }
            this.diagnosticsStream = new DiagnosticsStream(pipe, diagnostics.getSize(), new DiagnosticsStream.Callback() { // from class: com.amazon.alexa.accessory.capabilities.diagnostics.-$$Lambda$DiagnosticsTask$lW5VrJkcHTN-Bub9S77n8pYoGn8
                @Override // com.amazon.alexa.accessory.streams.diagnostics.DiagnosticsStream.Callback
                public final void onComplete() {
                    DiagnosticsTask.this.lambda$null$0$DiagnosticsTask(pipe);
                }
            });
            this.descriptor.add(this.diagnosticsStream);
        }
    }

    public /* synthetic */ void lambda$null$0$DiagnosticsTask(Pipe pipe) {
        IOUtils.closeQuietly(pipe);
        UIUtils.schedule(new Runnable() { // from class: com.amazon.alexa.accessory.capabilities.diagnostics.-$$Lambda$DiagnosticsTask$1W9vUxkyP1jNse2ulQL88OzGP0k
            @Override // java.lang.Runnable
            public final void run() {
                DiagnosticsTask.this.dispose();
            }
        });
    }

    @Override // com.amazon.alexa.accessory.TaskManager.Task
    public void onStateChanged(int i, int i2) {
        if ((i == 0 || i == 1) && i2 == 3) {
            query();
        } else if (i != 3) {
        } else {
            stop();
        }
    }

    public String toString() {
        return "DiagnosticsTask";
    }
}
