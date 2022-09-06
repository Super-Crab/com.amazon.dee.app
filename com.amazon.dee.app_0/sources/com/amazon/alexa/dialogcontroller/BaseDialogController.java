package com.amazon.alexa.dialogcontroller;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaAudioProviderConnection;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaProfile;
import com.amazon.alexa.api.AlexaWakeWord;
import com.amazon.alexa.audiocapturer.AudioCapturer;
import com.amazon.alexa.audiocapturer.AudioCapturerProvider;
import com.amazon.alexa.audiocapturer.RecordingRunnable;
import com.amazon.alexa.mode.debug.EmulateConnection;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes7.dex */
public abstract class BaseDialogController implements DialogController {
    protected static final String TAG = "BaseDialogController";
    private AudioCapturer audioCapturer;
    private final AudioCapturerProvider audioCapturerProvider;
    private final ExecutorService callbacksExecutor;
    private final AtomicReference<StartDialogData> startDialogData = new AtomicReference<>();
    private final AtomicReference<Dialog> currentDialog = new AtomicReference<>();
    private final Set<DialogLifecycleCallbacks> lifecycleCallbacks = new CopyOnWriteArraySet();

    /* loaded from: classes7.dex */
    public final class AlexaConnectionListener implements AlexaAudioProviderConnection.ConnectionListener {
        public AlexaConnectionListener() {
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            synchronized (BaseDialogController.this) {
                BaseDialogController.this.onConnected();
            }
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
            synchronized (BaseDialogController.this) {
                BaseDialogController.this.onConnectingFailed(alexaConnectingFailedReason, str);
            }
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
            synchronized (BaseDialogController.this) {
                BaseDialogController.this.onDisconnected();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseDialogController(AudioCapturerProvider audioCapturerProvider, ExecutorService executorService) {
        this.audioCapturerProvider = audioCapturerProvider;
        this.callbacksExecutor = executorService;
    }

    private boolean isCurrentDialog(Dialog dialog) {
        return isInDialog() && getCurrentDialog().equals(dialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        String str2 = TAG;
        Log.w(str2, "onConnectingFailed. Reason: " + alexaConnectingFailedReason + " Message: " + str);
        this.startDialogData.set(null);
    }

    private void resetState() {
        Log.i(TAG, "reset state");
        stopRecording(getCurrentDialog());
        this.currentDialog.set(null);
    }

    @Override // com.amazon.alexa.dialogcontroller.DialogController
    public synchronized void addLifecycleCallbacks(DialogLifecycleCallbacks dialogLifecycleCallbacks) {
        this.lifecycleCallbacks.add(dialogLifecycleCallbacks);
    }

    protected abstract void connect();

    protected abstract void continueDialog(Dialog dialog, AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink);

    @VisibleForTesting
    protected AudioCapturer createAudioCapturer() {
        return this.audioCapturerProvider.getAudioCapturer();
    }

    @VisibleForTesting
    protected AlexaAudioSink createAudioSink() throws IOException {
        return new AlexaAudioSink();
    }

    @VisibleForTesting
    Dialog createNewDialog() {
        Dialog dialog = new Dialog(this);
        this.currentDialog.set(dialog);
        String str = TAG;
        Log.i(str, "Created new dialog: " + dialog);
        return dialog;
    }

    protected abstract void disconnect();

    protected abstract void doRelease();

    @VisibleForTesting
    synchronized boolean doStartDialog(StartDialogData startDialogData) {
        Log.i(TAG, "doStartDialog");
        startDialog(createNewDialog(), startDialogData);
        return true;
    }

    @VisibleForTesting
    Dialog getCurrentDialog() {
        return this.currentDialog.get();
    }

    protected abstract boolean isConnected();

    @Override // com.amazon.alexa.dialogcontroller.DialogController
    public synchronized boolean isInDialog() {
        return getCurrentDialog() != null;
    }

    public synchronized boolean isInTurn() {
        boolean z;
        if (isInDialog()) {
            if (getCurrentDialog().isInTurn()) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    @Override // com.amazon.alexa.dialogcontroller.DialogController
    public synchronized boolean isInterruptable() {
        return !isInTurn();
    }

    @Override // com.amazon.alexa.dialogcontroller.DialogController
    public synchronized boolean isMultiturn() {
        boolean z;
        if (isInDialog()) {
            if (getCurrentDialog().isMultiturn()) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    @VisibleForTesting
    void onConnected() {
        Log.i(TAG, "onConnected");
        StartDialogData andSet = this.startDialogData.getAndSet(null);
        if (andSet != null) {
            doStartDialog(andSet);
        }
    }

    public synchronized void onDialogFinished(Dialog dialog) {
        if (!isCurrentDialog(dialog)) {
            String str = TAG;
            Log.w(str, "got onDialogTurnStarted for the wrong dialog " + dialog + " ignoring");
            return;
        }
        Log.i(TAG, "onDialogFinished");
        resetState();
        this.callbacksExecutor.execute(new Runnable() { // from class: com.amazon.alexa.dialogcontroller.BaseDialogController.5
            @Override // java.lang.Runnable
            public void run() {
                for (DialogLifecycleCallbacks dialogLifecycleCallbacks : BaseDialogController.this.lifecycleCallbacks) {
                    dialogLifecycleCallbacks.onDialogFinished();
                }
            }
        });
    }

    public synchronized void onDialogStarted(Dialog dialog) {
        if (!isCurrentDialog(dialog)) {
            String str = TAG;
            Log.w(str, "got onDialogStarted for the wrong dialog " + dialog + " ignoring");
            return;
        }
        Log.i(TAG, "onDialogStarted");
        this.callbacksExecutor.execute(new Runnable() { // from class: com.amazon.alexa.dialogcontroller.BaseDialogController.4
            @Override // java.lang.Runnable
            public void run() {
                for (DialogLifecycleCallbacks dialogLifecycleCallbacks : BaseDialogController.this.lifecycleCallbacks) {
                    dialogLifecycleCallbacks.onDialogStarted();
                }
            }
        });
    }

    public synchronized void onDialogTurnFinished(Dialog dialog) {
        if (!isCurrentDialog(dialog)) {
            String str = TAG;
            Log.w(str, "got onDialogTurnFinished for the wrong dialog " + dialog + " ignoring");
            return;
        }
        Log.i(TAG, "onDialogTurnFinished");
        this.callbacksExecutor.execute(new Runnable() { // from class: com.amazon.alexa.dialogcontroller.BaseDialogController.3
            @Override // java.lang.Runnable
            public void run() {
                for (DialogLifecycleCallbacks dialogLifecycleCallbacks : BaseDialogController.this.lifecycleCallbacks) {
                    dialogLifecycleCallbacks.onDialogTurnFinished();
                }
            }
        });
    }

    public synchronized void onDialogTurnStarted(Dialog dialog) {
        if (!isCurrentDialog(dialog)) {
            String str = TAG;
            Log.w(str, "got onDialogTurnStarted for the wrong dialog " + dialog + " ignoring");
            return;
        }
        Log.i(TAG, "onDialogTurnStarted");
        this.callbacksExecutor.execute(new Runnable() { // from class: com.amazon.alexa.dialogcontroller.BaseDialogController.2
            @Override // java.lang.Runnable
            public void run() {
                for (DialogLifecycleCallbacks dialogLifecycleCallbacks : BaseDialogController.this.lifecycleCallbacks) {
                    dialogLifecycleCallbacks.onDialogTurnStarted();
                }
            }
        });
    }

    @VisibleForTesting
    void onDisconnected() {
        Log.i(TAG, "onDisconnected");
        this.startDialogData.set(null);
    }

    @Override // com.amazon.alexa.dialogcontroller.DialogController
    public final synchronized void release() {
        resetState();
        doRelease();
    }

    @Override // com.amazon.alexa.dialogcontroller.DialogController
    public synchronized void removeLifecycleCallbacks(DialogLifecycleCallbacks dialogLifecycleCallbacks) {
        this.lifecycleCallbacks.remove(dialogLifecycleCallbacks);
    }

    protected abstract void startDialog(Dialog dialog, StartDialogData startDialogData);

    @Override // com.amazon.alexa.dialogcontroller.DialogController
    public synchronized boolean startDialog(AlexaDialogExtras alexaDialogExtras) {
        String str = TAG;
        Log.i(str, "startDialog via " + alexaDialogExtras.getInvocationType());
        stopRecording(getCurrentDialog());
        return startDialog(alexaDialogExtras, null, null);
    }

    public synchronized void startRecordingNextDialogTurn(Dialog dialog) {
        Log.i(TAG, "startRecordingNextDialogTurn");
        if (!isCurrentDialog(dialog)) {
            String str = TAG;
            Log.w(str, "got startRecordingNextDialogTurn for the wrong dialog " + dialog + " ignoring");
            return;
        }
        stopRecording(getCurrentDialog());
        this.audioCapturer = createAudioCapturer();
        AlexaAudioSink createAudioSink = createAudioSink();
        if (this.audioCapturer.startCapturing(createAudioSink)) {
            continueDialog(getCurrentDialog(), new AlexaAudioMetadata(AlexaProfile.NEAR_FIELD, RecordingRunnable.USER_SPEECH_AUDIO_FORMAT), createAudioSink);
        } else {
            Log.w(TAG, "Failed to start recording audio");
            createAudioSink.abandon();
        }
    }

    @Override // com.amazon.alexa.dialogcontroller.DialogController
    public synchronized void stopCurrentDialogTurn() {
        Log.i(TAG, "stopCurrentDialogTurn");
        stopRecording(getCurrentDialog());
        if (isInTurn()) {
            stopDialogTurn(getCurrentDialog());
        }
    }

    protected abstract void stopDialogTurn(Dialog dialog);

    public synchronized void stopRecording(Dialog dialog) {
        if (!isCurrentDialog(dialog)) {
            String str = TAG;
            Log.w(str, "got stop recording for the wrong dialog " + dialog + " ignoring");
            return;
        }
        Log.i(TAG, "stopRecording");
        if (this.audioCapturer != null) {
            this.audioCapturer.stopCapturing();
            this.audioCapturer = null;
        }
        this.callbacksExecutor.execute(new Runnable() { // from class: com.amazon.alexa.dialogcontroller.BaseDialogController.1
            @Override // java.lang.Runnable
            public void run() {
                for (DialogLifecycleCallbacks dialogLifecycleCallbacks : BaseDialogController.this.lifecycleCallbacks) {
                    dialogLifecycleCallbacks.onStopRecording();
                }
            }
        });
    }

    @Override // com.amazon.alexa.dialogcontroller.DialogController
    public synchronized boolean startDialog(AlexaDialogExtras alexaDialogExtras, @Nullable AlexaAudioSink alexaAudioSink, @Nullable AlexaWakeWord alexaWakeWord) {
        Log.i(TAG, "startDialog");
        if (isInDialog()) {
            stopDialogTurn(getCurrentDialog());
        }
        StartDialogData startDialogData = new StartDialogData(alexaDialogExtras, alexaAudioSink, new AlexaAudioMetadata(AlexaProfile.NEAR_FIELD, alexaWakeWord, RecordingRunnable.USER_SPEECH_AUDIO_FORMAT));
        if (isConnected()) {
            return doStartDialog(startDialogData);
        }
        Log.i(TAG, EmulateConnection.EXTRA_CONNECT);
        this.startDialogData.set(startDialogData);
        connect();
        return true;
    }
}
