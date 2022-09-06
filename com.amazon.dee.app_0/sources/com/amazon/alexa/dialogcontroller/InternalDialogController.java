package com.amazon.alexa.dialogcontroller;

import androidx.annotation.RequiresPermission;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaServices;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.audiocapturer.AudioCapturerProvider;
import com.amazon.alexa.dialogcontroller.BaseDialogController;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.concurrent.ExecutorService;
/* loaded from: classes7.dex */
public class InternalDialogController extends BaseDialogController {
    private final BaseDialogController.AlexaConnectionListener alexaConnectionListener;
    private final AlexaServicesConnection alexaServicesConnection;

    public InternalDialogController(AudioCapturerProvider audioCapturerProvider, AlexaServicesConnection alexaServicesConnection) {
        this(audioCapturerProvider, alexaServicesConnection, ExecutorFactory.newSingleThreadExecutor("DialogController"));
    }

    @Override // com.amazon.alexa.dialogcontroller.BaseDialogController
    protected void connect() {
        this.alexaServicesConnection.connect();
    }

    @Override // com.amazon.alexa.dialogcontroller.BaseDialogController
    protected void continueDialog(Dialog dialog, AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink) {
        AlexaServices.Recognizer.continueDialog(this.alexaServicesConnection, dialog, alexaAudioMetadata, alexaAudioSink);
    }

    @Override // com.amazon.alexa.dialogcontroller.BaseDialogController
    protected void disconnect() {
        this.alexaServicesConnection.disconnect();
    }

    @Override // com.amazon.alexa.dialogcontroller.BaseDialogController
    protected void doRelease() {
        this.alexaServicesConnection.deregisterListener(this.alexaConnectionListener);
    }

    @Override // com.amazon.alexa.dialogcontroller.BaseDialogController
    protected boolean isConnected() {
        return this.alexaServicesConnection.isConnected();
    }

    @Override // com.amazon.alexa.dialogcontroller.BaseDialogController
    @RequiresPermission("android.permission.RECORD_AUDIO")
    protected void startDialog(Dialog dialog, StartDialogData startDialogData) {
        AlexaServices.Recognizer.start(this.alexaServicesConnection, startDialogData.getDialogExtras().getInvocationType());
    }

    @Override // com.amazon.alexa.dialogcontroller.BaseDialogController
    protected void stopDialogTurn(Dialog dialog) {
        AlexaServices.Recognizer.stop(this.alexaServicesConnection);
    }

    @VisibleForTesting
    InternalDialogController(AudioCapturerProvider audioCapturerProvider, AlexaServicesConnection alexaServicesConnection, ExecutorService executorService) {
        super(audioCapturerProvider, executorService);
        Preconditions.notNull(alexaServicesConnection, "connection is null");
        this.alexaConnectionListener = new BaseDialogController.AlexaConnectionListener();
        this.alexaServicesConnection = alexaServicesConnection;
        this.alexaServicesConnection.registerListener(this.alexaConnectionListener);
    }
}
