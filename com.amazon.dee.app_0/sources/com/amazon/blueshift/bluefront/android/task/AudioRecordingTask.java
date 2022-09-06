package com.amazon.blueshift.bluefront.android.task;

import android.os.AsyncTask;
import android.util.Log;
import com.amazon.blueshift.bluefront.android.SpeechClientException;
import com.amazon.blueshift.bluefront.android.audio.AudioRecorder;
import com.amazon.blueshift.bluefront.android.audio.AudioSourceListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
/* loaded from: classes11.dex */
public class AudioRecordingTask extends AsyncTask<Void, Progress<AudioRecordingTaskState>, Void> {
    private static final String TAG = AudioRecordingTask.class.getCanonicalName();
    private final AudioRecorder mAudioRecorder;
    private final AudioRecordingTaskListener mListener;

    /* renamed from: com.amazon.blueshift.bluefront.android.task.AudioRecordingTask$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$blueshift$bluefront$android$task$AudioRecordingTask$AudioRecordingTaskState = new int[AudioRecordingTaskState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$blueshift$bluefront$android$task$AudioRecordingTask$AudioRecordingTaskState[AudioRecordingTaskState.ReadyForSpeech.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$blueshift$bluefront$android$task$AudioRecordingTask$AudioRecordingTaskState[AudioRecordingTaskState.BeginningOfSpeech.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$blueshift$bluefront$android$task$AudioRecordingTask$AudioRecordingTaskState[AudioRecordingTaskState.BufferReceived.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$blueshift$bluefront$android$task$AudioRecordingTask$AudioRecordingTaskState[AudioRecordingTaskState.RmsChanged.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$blueshift$bluefront$android$task$AudioRecordingTask$AudioRecordingTaskState[AudioRecordingTaskState.SilenceDetected.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$blueshift$bluefront$android$task$AudioRecordingTask$AudioRecordingTaskState[AudioRecordingTaskState.Error.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$blueshift$bluefront$android$task$AudioRecordingTask$AudioRecordingTaskState[AudioRecordingTaskState.NoSpeechTimeout.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$blueshift$bluefront$android$task$AudioRecordingTask$AudioRecordingTaskState[AudioRecordingTaskState.MaxSpeechTimeout.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* loaded from: classes11.dex */
    private class AudioRecorderListener implements AudioSourceListener {
        private AudioRecorderListener() {
        }

        @Override // com.amazon.blueshift.bluefront.android.audio.AudioSourceListener
        public void onBeginningOfSpeech() {
            AudioRecordingTask.this.publishProgress(new Progress(AudioRecordingTaskState.BeginningOfSpeech));
        }

        @Override // com.amazon.blueshift.bluefront.android.audio.AudioSourceListener
        public void onBufferReceived(byte[] bArr) {
            AudioRecordingTask.this.publishProgress(new Progress(AudioRecordingTaskState.BufferReceived, bArr));
        }

        @Override // com.amazon.blueshift.bluefront.android.audio.AudioSourceListener
        public void onMaxSpeechTimeout() {
            AudioRecordingTask.this.publishProgress(new Progress(AudioRecordingTaskState.MaxSpeechTimeout));
        }

        @Override // com.amazon.blueshift.bluefront.android.audio.AudioSourceListener
        public void onNoSpeechTimeout() {
            AudioRecordingTask.this.publishProgress(new Progress(AudioRecordingTaskState.NoSpeechTimeout));
        }

        @Override // com.amazon.blueshift.bluefront.android.audio.AudioSourceListener
        public void onReadyForSpeech() {
            AudioRecordingTask.this.publishProgress(new Progress(AudioRecordingTaskState.ReadyForSpeech));
        }

        @Override // com.amazon.blueshift.bluefront.android.audio.AudioSourceListener
        public void onRmsChanged(float f) {
            AudioRecordingTask.this.publishProgress(new Progress(AudioRecordingTaskState.RmsChanged, Float.valueOf(f)));
        }

        @Override // com.amazon.blueshift.bluefront.android.audio.AudioSourceListener
        public void onSilenceDetected() {
            AudioRecordingTask.this.publishProgress(new Progress(AudioRecordingTaskState.SilenceDetected));
        }

        /* synthetic */ AudioRecorderListener(AudioRecordingTask audioRecordingTask, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* loaded from: classes11.dex */
    public enum AudioRecordingTaskState {
        ReadyForSpeech,
        BeginningOfSpeech,
        BufferReceived,
        RmsChanged,
        SilenceDetected,
        EndOfSpeech,
        Error,
        NoSpeechTimeout,
        MaxSpeechTimeout
    }

    public AudioRecordingTask(AudioRecorder audioRecorder, AudioRecordingTaskListener audioRecordingTaskListener) {
        this.mAudioRecorder = (AudioRecorder) Preconditions.checkNotNull(audioRecorder, "Audio recorder cannot be null.");
        this.mListener = (AudioRecordingTaskListener) Preconditions.checkNotNull(audioRecordingTaskListener, "Listener for this task cannot be null");
        this.mAudioRecorder.setAudioSourceListener(new AudioRecorderListener(this, null));
    }

    void stopRecording() {
        this.mAudioRecorder.cancel();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(Void... voidArr) {
        try {
            this.mAudioRecorder.startRecording();
            return null;
        } catch (Exception e) {
            publishProgress(new Progress(AudioRecordingTaskState.Error, e));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onProgressUpdate(Progress<AudioRecordingTaskState>... progressArr) {
        Preconditions.checkNotNull(progressArr, "Updates cannot be null.");
        boolean z = true;
        if (progressArr.length != 1) {
            z = false;
        }
        Preconditions.checkArgument(z, "Only one update is allowed.");
        Progress<AudioRecordingTaskState> progress = progressArr[0];
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onProgressUpdate: ");
        outline107.append(progress.getState().toString());
        outline107.toString();
        Object payload = progress.getPayload();
        if (this.mListener != null) {
            switch (progress.getState().ordinal()) {
                case 0:
                    this.mListener.onReadyForSpeech();
                    return;
                case 1:
                    this.mListener.onBeginningOfSpeech();
                    return;
                case 2:
                    if (payload == null || !(payload instanceof byte[])) {
                        return;
                    }
                    this.mListener.onBufferReceived((byte[]) payload);
                    return;
                case 3:
                    if (payload == null || !(payload instanceof Float)) {
                        return;
                    }
                    this.mListener.onRmsChanged(((Float) payload).floatValue());
                    return;
                case 4:
                    this.mListener.onSilenceDetected();
                    return;
                case 5:
                default:
                    this.mListener.onError(new SpeechClientException("Received an unknown progress update"));
                    return;
                case 6:
                    if (payload == null || !(payload instanceof SpeechClientException)) {
                        return;
                    }
                    this.mListener.onError((SpeechClientException) payload);
                    return;
                case 7:
                    this.mListener.onNoSpeechTimeout();
                    return;
                case 8:
                    this.mListener.onMaxSpeechTimeout();
                    return;
            }
        }
        Log.w(TAG, "Audio recording task listener is null.");
    }
}
