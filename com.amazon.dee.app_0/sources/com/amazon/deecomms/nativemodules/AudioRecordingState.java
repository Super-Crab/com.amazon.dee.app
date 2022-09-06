package com.amazon.deecomms.nativemodules;

import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.media.audio.AudioContentManager;
import com.amazon.deecomms.media.audio.AudioRecorder;
import java.io.File;
/* loaded from: classes12.dex */
class AudioRecordingState implements AudioRecorder.IAudioRecorderCallback {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AudioRecordingState.class);
    private final File audioRecordFile;
    private final AudioRecorder audioRecorder;
    private boolean encounteredError;
    private boolean reachedTimeLimit = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioRecordingState(@NonNull AudioContentManager audioContentManager, @NonNull AudioRecorder audioRecorder) {
        LOG.d("Creating recording state");
        this.audioRecorder = audioRecorder;
        this.audioRecordFile = audioContentManager.createLocalMsgAudioFile(System.currentTimeMillis());
        audioRecorder.setAudioRecorderCallback(this);
    }

    @Override // com.amazon.deecomms.media.audio.AudioRecorder.IAudioRecorderCallback
    public void audioInterrupted() {
        LOG.i("Audio recording interrupted");
        this.encounteredError = true;
        CommsEventEmitterBridge.notifyAudioRecorderDidFinish(false, this.audioRecordFile.getPath(), false);
    }

    @Override // com.amazon.deecomms.media.audio.AudioRecorder.IAudioRecorderCallback
    public void audioRecordErrorOccurred() {
        LOG.i("Audio recording error occurred");
        this.encounteredError = true;
        CommsEventEmitterBridge.notifyAudioRecorderDidFinish(false, this.audioRecordFile.getPath(), false);
    }

    @Override // com.amazon.deecomms.media.audio.AudioRecorder.IAudioRecorderCallback
    public void audioRecordTimeLimitReached() {
        LOG.i("Reached maximum recording time limit");
        this.reachedTimeLimit = true;
        CommsEventEmitterBridge.notifyAudioRecorderDidFinish(true, this.audioRecordFile.getPath(), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void close(boolean z) {
        LOG.d("Closing recording state");
        if (this.audioRecorder.isAudioMessageRecording()) {
            this.audioRecorder.stopRecording();
        }
        this.audioRecorder.setAudioRecorderCallback(null);
        if (z) {
            if (this.audioRecordFile.delete()) {
                LOG.d("Deleted audio recording");
            } else {
                LOG.w("Could not delete audio recording");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File getAudioRecordFile() {
        return this.audioRecordFile;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasEncounteredError() {
        return this.encounteredError;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean wasTimeLimitReached() {
        return this.reachedTimeLimit;
    }
}
