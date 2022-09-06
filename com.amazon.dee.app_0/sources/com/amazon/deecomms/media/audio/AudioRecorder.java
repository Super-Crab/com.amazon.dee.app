package com.amazon.deecomms.media.audio;

import android.media.AudioManager;
import android.media.MediaRecorder;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.util.AudioMessageUtils;
import java.io.File;
import java.io.IOException;
/* loaded from: classes12.dex */
public class AudioRecorder {
    private static final int AUDIO_CHANNEL_COUNT_SUPPORTED;
    private static final int AUDIO_ENCODING_BIT_RATE = 96000;
    private static final int AUDIO_MESSAGE_MAXIMUM_TIME_LIMIT = 40000;
    public static final int AUDIO_MESSAGE_WARNING_TIME_LIMIT = 36000;
    public static final String AUDIO_RECORDED_CONTENT_TYPE = "audio/x-aac";
    private static final int AUDIO_SAMPLING_RATE = 44100;
    private static final CommsLogger LOG;
    private final AudioManager audioManager;
    private IAudioRecorderCallback mAudioRecorderCallback;
    private MediaRecorder mRecorder;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.amazon.deecomms.media.audio.-$$Lambda$AudioRecorder$5MIO7fYrNzHlvRcX0qaFGtp3FUs
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public final void onAudioFocusChange(int i) {
            AudioRecorder.this.lambda$new$0$AudioRecorder(i);
        }
    };
    private MessageRecordingState messageRecordingState = MessageRecordingState.NO_MESSAGE_TO_RECORD;

    /* loaded from: classes12.dex */
    public interface IAudioRecorderCallback {
        void audioInterrupted();

        void audioRecordErrorOccurred();

        void audioRecordTimeLimitReached();
    }

    /* loaded from: classes12.dex */
    public enum MessageRecordingState {
        NO_MESSAGE_TO_RECORD,
        MESSAGE_RECORDING_STOPPED,
        MESSAGE_RECORDING_INPROGRESS
    }

    static {
        AUDIO_CHANNEL_COUNT_SUPPORTED = AudioMessageUtils.requiresMonoChannelRecording() ? 1 : 2;
        LOG = CommsLogger.getLogger(Constants.LOG_TAG, AudioRecorder.class);
    }

    public AudioRecorder(AudioManager audioManager) {
        this.audioManager = audioManager;
    }

    private boolean hasMic() {
        return CommsDaggerWrapper.getComponent().getContext().getPackageManager().hasSystemFeature("android.hardware.microphone");
    }

    private void releaseMediaRecorder() {
        MediaRecorder mediaRecorder = this.mRecorder;
        if (mediaRecorder != null) {
            mediaRecorder.release();
            this.mRecorder = null;
        }
        if (this.audioManager != null) {
            LOG.i("Abandoning audio focus in AudioRecorder");
            this.audioManager.abandonAudioFocus(this.mOnAudioFocusChangeListener);
        }
    }

    public MessageRecordingState getMessageRecordingState() {
        return this.messageRecordingState;
    }

    public void handleInterruptedVoiceMessageRecording() {
        IAudioRecorderCallback iAudioRecorderCallback;
        MessageRecordingState messageRecordingState = this.messageRecordingState;
        if ((messageRecordingState == MessageRecordingState.MESSAGE_RECORDING_INPROGRESS || messageRecordingState == MessageRecordingState.MESSAGE_RECORDING_STOPPED) && (iAudioRecorderCallback = this.mAudioRecorderCallback) != null) {
            iAudioRecorderCallback.audioInterrupted();
        }
    }

    public boolean isAudioMessageRecording() {
        return this.messageRecordingState == MessageRecordingState.MESSAGE_RECORDING_INPROGRESS;
    }

    public /* synthetic */ void lambda$new$0$AudioRecorder(int i) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("AudioRecorder focus is " + i);
        if (i == -2 || i == -1 || i == -3) {
            handleInterruptedVoiceMessageRecording();
        }
    }

    public void setAudioRecorderCallback(IAudioRecorderCallback iAudioRecorderCallback) {
        this.mAudioRecorderCallback = iAudioRecorderCallback;
    }

    public void setMessageRecordingState(MessageRecordingState messageRecordingState) {
        this.messageRecordingState = messageRecordingState;
    }

    public synchronized boolean startRecording(String str) {
        if (!hasMic()) {
            LOG.w("Does not have mic, returning");
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            LOG.e("File is not existing, unable to record, returning");
            return false;
        }
        try {
            int requestAudioFocus = this.audioManager.requestAudioFocus(this.mOnAudioFocusChangeListener, 3, 4);
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("AudioFocusResult is ");
            sb.append(requestAudioFocus);
            commsLogger.i(sb.toString());
            if (requestAudioFocus == 0) {
                LOG.e("Audio focus request was rejected in AudioRecorder");
            } else if (requestAudioFocus == 1) {
                LOG.i("Start Recording");
                if (this.mRecorder == null) {
                    LOG.i("Recorder is null, initializing");
                    this.mRecorder = new MediaRecorder();
                }
                this.mRecorder.reset();
                this.mRecorder.setAudioSource(1);
                this.mRecorder.setOutputFormat(2);
                this.mRecorder.setAudioEncoder(3);
                this.mRecorder.setAudioChannels(AUDIO_CHANNEL_COUNT_SUPPORTED);
                this.mRecorder.setAudioSamplingRate(AUDIO_SAMPLING_RATE);
                this.mRecorder.setAudioEncodingBitRate(AUDIO_ENCODING_BIT_RATE);
                this.mRecorder.setOutputFile(file.getPath());
                this.mRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() { // from class: com.amazon.deecomms.media.audio.AudioRecorder.1
                    @Override // android.media.MediaRecorder.OnInfoListener
                    public void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
                        if (i != 800 || AudioRecorder.this.mAudioRecorderCallback == null) {
                            return;
                        }
                        AudioRecorder.this.mAudioRecorderCallback.audioRecordTimeLimitReached();
                    }
                });
                this.mRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() { // from class: com.amazon.deecomms.media.audio.AudioRecorder.2
                    @Override // android.media.MediaRecorder.OnErrorListener
                    public void onError(MediaRecorder mediaRecorder, int i, int i2) {
                        if (AudioRecorder.this.mAudioRecorderCallback != null) {
                            CommsLogger commsLogger2 = AudioRecorder.LOG;
                            commsLogger2.e("Error while recording audio. Error: " + i + " extra:" + i2);
                            AudioRecorder.this.mAudioRecorderCallback.audioRecordErrorOccurred();
                        }
                    }
                });
                this.mRecorder.setMaxDuration(40000);
                this.mRecorder.prepare();
                this.mRecorder.start();
                CommsLogger commsLogger2 = LOG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Started recording to file: ");
                sb2.append(LOG.sensitive(str));
                commsLogger2.i(sb2.toString());
                this.messageRecordingState = MessageRecordingState.MESSAGE_RECORDING_INPROGRESS;
                return true;
            }
        } catch (IOException | IllegalStateException e) {
            LOG.e("Exception while recording audio", e);
            this.messageRecordingState = MessageRecordingState.NO_MESSAGE_TO_RECORD;
            releaseMediaRecorder();
        }
        return false;
    }

    public synchronized boolean stopRecording() {
        boolean z = false;
        try {
        } catch (RuntimeException e) {
            LOG.e("RuntimeException occurred while stopping recording", e);
            releaseMediaRecorder();
        }
        if (this.mRecorder == null) {
            LOG.e("Recorder is null, returning");
            releaseMediaRecorder();
            return false;
        }
        if (this.messageRecordingState == MessageRecordingState.MESSAGE_RECORDING_INPROGRESS) {
            LOG.i("Stopping Recording");
            this.mRecorder.stop();
            this.messageRecordingState = MessageRecordingState.MESSAGE_RECORDING_STOPPED;
            z = true;
        }
        releaseMediaRecorder();
        return z;
    }
}
