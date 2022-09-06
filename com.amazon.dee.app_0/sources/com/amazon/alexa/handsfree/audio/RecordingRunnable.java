package com.amazon.alexa.handsfree.audio;

import android.annotation.SuppressLint;
import android.media.AudioRecord;
import android.os.ConditionVariable;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.nio.ByteBuffer;
/* loaded from: classes8.dex */
public class RecordingRunnable implements Runnable {
    private static final int AUDIO_ENCODING = 2;
    private static final int BUFFER_SIZE = 320;
    private static final int BYTES_PER_SAMPLE = 2;
    private static final int CHANNEL_COUNT = 16;
    private static final int ONE_SECOND_OF_AUDIO = 32000;
    private static final int SAMPLE_RATE = 16000;
    private static final long SHUTDOWN_TIMEOUT_MS = 1000;
    private final AlexaAudioSinkWrapper mAlexaAudioSinkWrapper;
    private final AudioRecord mAudioRecord;
    private volatile boolean mContinueRecording;
    private final ConditionVariable mShutdownCondition;
    private static final String TAG = RecordingRunnable.class.getSimpleName();
    private static final int MIN_BUFFER_SIZE = AudioRecord.getMinBufferSize(16000, 16, 2);
    private static final int ACTUAL_BUFFER_SIZE = Math.max(320, MIN_BUFFER_SIZE);

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"MissingPermission"})
    public RecordingRunnable(@NonNull AlexaAudioSinkWrapper alexaAudioSinkWrapper) {
        this(alexaAudioSinkWrapper, new ConditionVariable(), new AudioRecord(1, 16000, 16, 2, ACTUAL_BUFFER_SIZE), true);
    }

    @VisibleForTesting
    boolean checkRecordingState(int i) {
        return this.mAudioRecord.getRecordingState() == i;
    }

    @Override // java.lang.Runnable
    public void run() {
        Log.i(TAG, "starting recording");
        if (!checkRecordingState(1)) {
            Log.e(TAG, "Unable to start recording. Some other process has the microphone.");
            return;
        }
        this.mAudioRecord.startRecording();
        if (!checkRecordingState(3)) {
            Log.e(TAG, "Unable to start recording.");
            return;
        }
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(ACTUAL_BUFFER_SIZE);
        while (this.mContinueRecording) {
            int read = this.mAudioRecord.read(allocateDirect, ACTUAL_BUFFER_SIZE);
            if (read == -6) {
                Log.e(TAG, "AudioRecord object no longer valid. Needs to be recreated");
                this.mContinueRecording = false;
            } else if (read == -3) {
                Log.e(TAG, "AudioRecord object not properly initialized");
                this.mContinueRecording = false;
            } else if (read == -2) {
                Log.e(TAG, "Invalid arguments to AudioRecord");
                this.mContinueRecording = false;
            } else if (read != -1) {
                this.mAlexaAudioSinkWrapper.recordAudio(allocateDirect.array(), ACTUAL_BUFFER_SIZE);
            } else {
                Log.e(TAG, "Unknown error copying data to Attachment");
                this.mContinueRecording = false;
            }
        }
        this.mAlexaAudioSinkWrapper.reset();
        this.mAudioRecord.stop();
        this.mAudioRecord.release();
        this.mShutdownCondition.open();
    }

    public void stop() {
        Log.i(TAG, "stopping recording");
        this.mShutdownCondition.close();
        this.mContinueRecording = false;
        if (!this.mShutdownCondition.block(1000L)) {
            Log.e(TAG, "Failed to stop.");
        }
    }

    @VisibleForTesting
    RecordingRunnable(@NonNull AlexaAudioSinkWrapper alexaAudioSinkWrapper, @NonNull ConditionVariable conditionVariable, @NonNull AudioRecord audioRecord, boolean z) {
        this.mAlexaAudioSinkWrapper = alexaAudioSinkWrapper;
        this.mShutdownCondition = conditionVariable;
        this.mAudioRecord = audioRecord;
        this.mContinueRecording = z;
    }
}
