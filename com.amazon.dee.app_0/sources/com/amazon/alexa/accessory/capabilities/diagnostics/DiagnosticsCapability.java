package com.amazon.alexa.accessory.capabilities.diagnostics;

import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.capabilities.diagnostics.DiagnosticsCapability;
import com.amazon.alexa.accessory.capabilities.diagnostics.DiagnosticsTask;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.DiagnosticsOuterClass;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.diagnostics.DiagnosticsProducer;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class DiagnosticsCapability extends AccessoryCapability {
    private final DiagnosticsActionHandler actionHandler;
    private CompositeDisposable compositeDisposable;
    private AccessoryDescriptor descriptor;
    private final DiagnosticsAvailableListener diagnosticsAvailableListener;
    private final DiagnosticsProducer producer;
    private ControlStream stream;
    private final TaskManager taskManager;
    private final List<DiagnosticsTask> tasks;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class DiagnosticsActionHandler implements DiagnosticsProducer.ActionHandler {
        DiagnosticsActionHandler() {
        }

        @Override // com.amazon.alexa.accessory.repositories.diagnostics.DiagnosticsProducer.ActionHandler
        public void handleGetDiagnostics(DiagnosticsOuterClass.DiagnosticsType diagnosticsType, Producer.Result<Source> result) {
            Preconditions.notNull(diagnosticsType, "type");
            Preconditions.notNull(result, "result");
            DiagnosticsTask diagnosticsTask = new DiagnosticsTask(DiagnosticsCapability.this.descriptor, DiagnosticsCapability.this.stream, result, diagnosticsType, new DiagnosticsTask.Callback() { // from class: com.amazon.alexa.accessory.capabilities.diagnostics.-$$Lambda$DiagnosticsCapability$DiagnosticsActionHandler$VzHYO9BOmnDcStOsvEiaHt1hLu0
                @Override // com.amazon.alexa.accessory.capabilities.diagnostics.DiagnosticsTask.Callback
                public final void onCompleted(DiagnosticsTask diagnosticsTask2) {
                    DiagnosticsCapability.DiagnosticsActionHandler.this.lambda$handleGetDiagnostics$0$DiagnosticsCapability$DiagnosticsActionHandler(diagnosticsTask2);
                }
            });
            DiagnosticsCapability.this.taskManager.schedule(diagnosticsTask, 4);
            DiagnosticsCapability.this.tasks.add(diagnosticsTask);
        }

        public /* synthetic */ void lambda$handleGetDiagnostics$0$DiagnosticsCapability$DiagnosticsActionHandler(DiagnosticsTask diagnosticsTask) {
            DiagnosticsCapability.this.taskManager.dispose(diagnosticsTask);
            DiagnosticsCapability.this.tasks.remove(diagnosticsTask);
        }
    }

    /* loaded from: classes.dex */
    public interface DiagnosticsAvailableListener {
        boolean onDiagnosticsAvailable(DiagnosticsOuterClass.DiagnosticsType diagnosticsType);
    }

    public DiagnosticsCapability(TaskManager taskManager, DiagnosticsProducer diagnosticsProducer, DiagnosticsAvailableListener diagnosticsAvailableListener) {
        Preconditions.notNull(diagnosticsProducer, "producer");
        Preconditions.notNull(taskManager, "taskManager");
        Preconditions.notNull(diagnosticsAvailableListener, "diagnosticsAvailableListener");
        this.taskManager = taskManager;
        this.producer = diagnosticsProducer;
        this.tasks = new ArrayList();
        this.compositeDisposable = new CompositeDisposable();
        this.diagnosticsAvailableListener = diagnosticsAvailableListener;
        this.actionHandler = new DiagnosticsActionHandler();
    }

    private ControlMessageHandler<DiagnosticsOuterClass.NotifyDiagnosticsAvailable> getNotifyDiagnosticsAvailableHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.diagnostics.-$$Lambda$DiagnosticsCapability$UnRn_TAaECCejJMfqrVKoqu0V-w
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                DiagnosticsCapability.this.lambda$getNotifyDiagnosticsAvailableHandler$0$DiagnosticsCapability(controlStream, command, (DiagnosticsOuterClass.NotifyDiagnosticsAvailable) obj);
            }
        };
    }

    public /* synthetic */ void lambda$getNotifyDiagnosticsAvailableHandler$0$DiagnosticsCapability(ControlStream controlStream, Accessories.Command command, DiagnosticsOuterClass.NotifyDiagnosticsAvailable notifyDiagnosticsAvailable) throws Exception {
        controlStream.respond(Accessories.Command.NOTIFY_DIAGNOSTICS_AVAILABLE, this.diagnosticsAvailableListener.onDiagnosticsAvailable(notifyDiagnosticsAvailable.getType()) ? Common.ErrorCode.SUCCESS : Common.ErrorCode.UNSUPPORTED);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.producer.detachActionHandler(this.actionHandler);
        accessoryDescriptor.remove(this.stream);
        this.compositeDisposable.clear();
        for (int size = this.tasks.size() - 1; size >= 0; size--) {
            this.taskManager.dispose(this.tasks.get(size));
        }
        this.tasks.clear();
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.producer.attachActionHandler(this.actionHandler);
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.descriptor = accessoryDescriptor;
        this.stream.addMessageHandler(Accessories.Command.NOTIFY_DIAGNOSTICS_AVAILABLE, getNotifyDiagnosticsAvailableHandler());
        accessoryDescriptor.add(this.stream);
    }
}
