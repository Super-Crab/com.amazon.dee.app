package com.amazon.alexa.dialogcontroller;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaAudioProviderConnection;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.Recognizer;
import com.amazon.alexa.audiocapturer.AudioCapturerProvider;
import com.amazon.alexa.dialogcontroller.BaseDialogController;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.concurrent.ExecutorService;
/* loaded from: classes7.dex */
public class ExternalDialogController extends BaseDialogController {
    private final BaseDialogController.AlexaConnectionListener alexaConnectionListener;
    private final AlexaAudioProviderConnection audioProviderConnection;

    public ExternalDialogController(AudioCapturerProvider audioCapturerProvider, AlexaAudioProviderConnection alexaAudioProviderConnection) {
        this(audioCapturerProvider, alexaAudioProviderConnection, ExecutorFactory.newSingleThreadExecutor("DialogController"));
    }

    @Override // com.amazon.alexa.dialogcontroller.BaseDialogController
    protected void connect() {
        this.audioProviderConnection.connect();
    }

    @Override // com.amazon.alexa.dialogcontroller.BaseDialogController
    protected void continueDialog(Dialog dialog, AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink) {
        Recognizer.continueDialog(this.audioProviderConnection, dialog, alexaAudioMetadata, alexaAudioSink);
    }

    @Override // com.amazon.alexa.dialogcontroller.BaseDialogController
    protected void disconnect() {
        this.audioProviderConnection.disconnect();
    }

    @Override // com.amazon.alexa.dialogcontroller.BaseDialogController
    protected void doRelease() {
        this.audioProviderConnection.deregisterListener(this.alexaConnectionListener);
    }

    @Override // com.amazon.alexa.dialogcontroller.BaseDialogController
    protected boolean isConnected() {
        return this.audioProviderConnection.isConnected();
    }

    @Override // com.amazon.alexa.dialogcontroller.BaseDialogController
    protected void startDialog(Dialog dialog, StartDialogData startDialogData) {
        Preconditions.notNull(startDialogData.getAudioSink(), "External speech provider should provide audio sink");
        Recognizer.startDialog(this.audioProviderConnection, dialog, startDialogData.getAlexaAudioMetadata(), startDialogData.getAudioSink(), startDialogData.getDialogExtras());
    }

    @Override // com.amazon.alexa.dialogcontroller.BaseDialogController
    protected void stopDialogTurn(Dialog dialog) {
        Recognizer.stopDialogTurn(this.audioProviderConnection, dialog);
    }

    @VisibleForTesting
    protected ExternalDialogController(AudioCapturerProvider audioCapturerProvider, AlexaAudioProviderConnection alexaAudioProviderConnection, ExecutorService executorService) {
        super(audioCapturerProvider, executorService);
        Preconditions.notNull(alexaAudioProviderConnection, "connection is null");
        this.alexaConnectionListener = new BaseDialogController.AlexaConnectionListener();
        this.audioProviderConnection = alexaAudioProviderConnection;
        this.audioProviderConnection.registerListener(this.alexaConnectionListener);
    }
}
