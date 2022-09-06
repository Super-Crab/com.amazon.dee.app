package com.amazon.alexa.audiocapturer;

import android.media.AudioRecord;
import android.os.ConditionVariable;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.utils.audio.AcousticEchoCancelerWrapper;
import com.amazon.alexa.utils.audio.AcousticEchoCancelerWrapperFactory;
import com.amazon.alexa.utils.validation.Preconditions;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
/* loaded from: classes6.dex */
public class RecordingRunnable implements Runnable {
    private static final int AUDIO_ENCODING = 2;
    private static final int BUFFER_SIZE = 320;
    private static final int BYTES_PER_SAMPLE = 2;
    private static final int CHANNEL_COUNT = 16;
    private static final int ONE_SECOND_OF_AUDIO = 32000;
    private static final int SAMPLE_RATE = 16000;
    private static final long SHUTDOWN_TIMEOUT_MS = 1000;
    public static final String USER_SPEECH_AUDIO_FORMAT = "AUDIO_L16_RATE_16000_CHANNELS_1";
    private final AcousticEchoCancelerWrapperFactory acousticEchoCancelerWrapperFactory;
    private final AlexaAudioSink alexaAudioSink;
    private volatile boolean continueRecording;
    private volatile boolean isDone;
    private final RecordingListener recordingListener;
    private final ConditionVariable shutdownCondition;
    private static final String TAG = RecordingRunnable.class.getSimpleName();
    private static final int MIN_BUFFER_SIZE = AudioRecord.getMinBufferSize(16000, 16, 2);
    public static final int ACTUAL_BUFFER_SIZE = Math.max(320, MIN_BUFFER_SIZE);

    /* loaded from: classes6.dex */
    public interface RecordingListener {
        void onRecordingError(Throwable th);

        void onRecordingStarted();

        void onRecordingStopped();
    }

    public RecordingRunnable(AlexaAudioSink alexaAudioSink, RecordingListener recordingListener, AcousticEchoCancelerWrapperFactory acousticEchoCancelerWrapperFactory) {
        Preconditions.notNull(alexaAudioSink, "Audio sink is null");
        Preconditions.notNull(recordingListener, "RecordingListener is null");
        this.alexaAudioSink = alexaAudioSink;
        this.recordingListener = recordingListener;
        this.acousticEchoCancelerWrapperFactory = acousticEchoCancelerWrapperFactory;
        this.shutdownCondition = new ConditionVariable();
        this.continueRecording = true;
    }

    @VisibleForTesting
    ByteBuffer allocateByteBuffer() {
        return ByteBuffer.allocateDirect(ACTUAL_BUFFER_SIZE);
    }

    @VisibleForTesting
    AudioRecord createAudioRecord() {
        return new AudioRecord(1, 16000, 16, 2, ACTUAL_BUFFER_SIZE);
    }

    @VisibleForTesting
    byte[] getArray(ByteBuffer byteBuffer) {
        return byteBuffer.array();
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override // java.lang.Runnable
    public void run() {
        AudioRecord createAudioRecord = createAudioRecord();
        AcousticEchoCancelerWrapper create = this.acousticEchoCancelerWrapperFactory.create(null);
        try {
            try {
            } catch (Exception e) {
                Log.e(TAG, "Unable to start recording", e);
                this.recordingListener.onRecordingError(e);
                this.alexaAudioSink.abandon();
            }
            if (createAudioRecord.getRecordingState() == 1) {
                create = this.acousticEchoCancelerWrapperFactory.create(createAudioRecord);
                create.setEnabled(true);
                String str = "AEC enabled = " + create.getEnabled();
                createAudioRecord.startRecording();
                if (createAudioRecord.getRecordingState() == 3) {
                    this.recordingListener.onRecordingStarted();
                    try {
                        OutputStream openForWriting = this.alexaAudioSink.openForWriting();
                        ByteBuffer allocateByteBuffer = allocateByteBuffer();
                        int i = 0;
                        while (this.continueRecording && i >= 0) {
                            allocateByteBuffer.position(0);
                            i = createAudioRecord.read(getArray(allocateByteBuffer), 0, ACTUAL_BUFFER_SIZE);
                            if (i == -6) {
                                throw new IOException("AudioRecord object no longer valid. Needs to be recreated");
                            }
                            if (i == -3) {
                                throw new IOException("AudioRecord object not properly initialized");
                            }
                            if (i == -2) {
                                throw new IOException("Invalid arguments to AudioRecord");
                            }
                            if (i == -1) {
                                throw new IOException("Unknown error copying data to Attachment");
                            }
                            if (i >= 0) {
                                openForWriting.write(getArray(allocateByteBuffer), 0, i);
                                openForWriting.flush();
                            }
                        }
                        if (openForWriting != null) {
                            openForWriting.close();
                        }
                        return;
                    } finally {
                        this.alexaAudioSink.close();
                        Log.e(TAG, "closed");
                        this.shutdownCondition.open();
                    }
                }
                throw new IllegalStateException("Audio recording didn't start");
            }
            throw new IllegalStateException("Some other process has the microphone");
        } finally {
            createAudioRecord.stop();
            create.release();
            createAudioRecord.release();
            this.recordingListener.onRecordingStopped();
        }
    }

    public void stop() {
        this.shutdownCondition.close();
        this.continueRecording = false;
        if (!this.shutdownCondition.block(1000L)) {
            Log.e(TAG, "Failed to stop.");
        }
        this.isDone = true;
    }

    public RecordingRunnable(AlexaAudioSink alexaAudioSink, RecordingListener recordingListener) {
        this(alexaAudioSink, recordingListener, new AcousticEchoCancelerWrapperFactory());
    }
}
