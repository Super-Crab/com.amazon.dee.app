package com.amazon.blueshift.bluefront.android.audio;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.os.SystemClock;
import android.util.Log;
import com.amazon.blueshift.bluefront.android.SpeechClientException;
import com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoder;
import com.amazon.blueshift.bluefront.android.audio.encoder.AudioEncoderException;
import com.amazon.blueshift.bluefront.android.audio.encoder.BufferedAudioEncoder;
import com.amazon.blueshift.bluefront.android.audio.encoder.L16PcmEncoder;
import com.amazon.blueshift.bluefront.android.audio.encoder.OpusEncoder;
import com.amazon.blueshift.bluefront.android.vad.DnnVAD;
import com.amazon.blueshift.bluefront.android.vad.VoiceActivityDetector;
import com.amazon.blueshift.bluefront.android.vad.WebRtcVAD;
import com.amazon.blueshift.bluefront.android.vad.config.DnnVADConfig;
import com.amazon.blueshift.bluefront.android.vad.config.WebRtcVADConfig;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class AudioRecorder extends AudioSource {
    private static final String ANDROID_PERMISSION_RECORD_AUDIO = "android.permission.RECORD_AUDIO";
    protected static final int SAMPLE_SIZE = 16;
    private static final String TAG = "com.amazon.blueshift.bluefront.android.audio.AudioRecorder";
    private final AudioEncoder mAudioEncoder;
    private final AudioTimeouts mAudioTimeouts;
    private PipedInputStream mConsumerStream;
    private final Context mContext;
    private final int mNumSamplesPerRead;
    private PipedOutputStream mProducerStream;
    private long mRecordStartTime;
    private AudioRecordWrapper mRecorder;
    private int mSamplesCountInRecPosNotificationPeriod;
    private double mSumOfSampleSquaresInRecPosNotificationPeriod;
    private final VoiceActivityDetector mVAD;

    /* loaded from: classes11.dex */
    public static class Builder {
        private AudioEncoder mAudioEncoder;
        private AudioRecordWrapper mAudioRecord;
        private final Context mContext;
        private WebRtcVADConfig mWebRtcVADConfig;
        private DnnVADConfig mDnnVADConfig = new DnnVADConfig();
        private AudioTimeouts mAudioTimeouts = new AudioTimeouts();

        public Builder(Context context) throws AudioEncoderException {
            Preconditions.checkNotNull(context, "Context cannot be null.");
            this.mContext = context;
            this.mAudioEncoder = new BufferedAudioEncoder(new OpusEncoder());
        }

        public Builder audioEncoder(AudioEncoder audioEncoder) {
            Preconditions.checkNotNull(audioEncoder, "AudioEncoder cannot be null.");
            if (audioEncoder instanceof BufferedAudioEncoder) {
                this.mAudioEncoder = audioEncoder;
            } else {
                this.mAudioEncoder = new BufferedAudioEncoder(audioEncoder);
            }
            return this;
        }

        public Builder audioRecord(AudioRecordWrapper audioRecordWrapper) {
            Preconditions.checkNotNull(audioRecordWrapper, "audioRecord cannot be null.");
            this.mAudioRecord = audioRecordWrapper;
            return this;
        }

        public Builder audioTimeouts(AudioTimeouts audioTimeouts) {
            Preconditions.checkNotNull(audioTimeouts, "AudioTimeouts cannot ne null.");
            this.mAudioTimeouts = audioTimeouts;
            return this;
        }

        public AudioRecorder build() throws Exception {
            VoiceActivityDetector dnnVAD;
            WebRtcVADConfig webRtcVADConfig = this.mWebRtcVADConfig;
            if (webRtcVADConfig != null) {
                dnnVAD = new WebRtcVAD(16000, webRtcVADConfig);
            } else {
                dnnVAD = new DnnVAD(16000, this.mDnnVADConfig);
            }
            return new AudioRecorder(this.mContext, this.mAudioEncoder, dnnVAD, this.mAudioTimeouts, this.mAudioRecord);
        }

        public Builder dnnVADConfig(DnnVADConfig dnnVADConfig) {
            Preconditions.checkNotNull(dnnVADConfig, "DnnVadConfig cannot be null.");
            this.mDnnVADConfig = dnnVADConfig;
            return this;
        }

        public Builder webRtcVADConfig(WebRtcVADConfig webRtcVADConfig) {
            Preconditions.checkNotNull(webRtcVADConfig, "WebRtcVADConfig cannot be null.");
            this.mWebRtcVADConfig = webRtcVADConfig;
            return this;
        }
    }

    /* loaded from: classes11.dex */
    private class RecordPositionChangeListener implements AudioRecord.OnRecordPositionUpdateListener {
        private static final double DEFAULT_WEIGHT = -3.1d;
        private double max;
        private double min;

        private RecordPositionChangeListener() {
            this.min = DEFAULT_WEIGHT;
            this.max = DEFAULT_WEIGHT;
        }

        @Override // android.media.AudioRecord.OnRecordPositionUpdateListener
        public void onMarkerReached(AudioRecord audioRecord) {
        }

        @Override // android.media.AudioRecord.OnRecordPositionUpdateListener
        public void onPeriodicNotification(AudioRecord audioRecord) {
            if (AudioRecorder.this.mSamplesCountInRecPosNotificationPeriod <= 0) {
                return;
            }
            double log10 = Math.log10(Math.sqrt(AudioRecorder.this.mSumOfSampleSquaresInRecPosNotificationPeriod / AudioRecorder.this.mSamplesCountInRecPosNotificationPeriod) / 32767.0d);
            AudioRecorder.this.mSumOfSampleSquaresInRecPosNotificationPeriod = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            AudioRecorder.this.mSamplesCountInRecPosNotificationPeriod = 0;
            if (log10 < this.min && log10 > -200000.0d) {
                this.min = log10;
            }
            if (log10 > this.max) {
                this.max = log10;
            }
            AudioRecorder.this.getAudioSourceListener().onRmsChanged((float) ((log10 + 3.2d) / 2.0d));
        }
    }

    private void destroyAudioRecorder() {
        this.mVAD.close();
        this.mAudioEncoder.close();
        String str = TAG;
        AudioRecordWrapper audioRecordWrapper = this.mRecorder;
        if (audioRecordWrapper != null) {
            audioRecordWrapper.stopRecordingForAVS();
            this.mRecorder = null;
            ((AudioManager) this.mContext.getSystemService("audio")).abandonAudioFocus(null);
            return;
        }
        Log.w(str, "Recorder is null.");
    }

    private void setupStreamPipe() throws SpeechClientException {
        int seconds = ((int) TimeUnit.MILLISECONDS.toSeconds(this.mAudioTimeouts.getMaxSpeechTimeout() + this.mAudioTimeouts.getNoSpeechTimeout())) * 16000 * 2;
        this.mProducerStream = new PipedOutputStream();
        try {
            this.mConsumerStream = new PipedInputStream(this.mProducerStream, seconds);
        } catch (IOException e) {
            throw new SpeechClientException("Error openning consumer stream", e);
        }
    }

    private void startAudioRecorder() throws SpeechClientException {
        this.mRecordStartTime = SystemClock.elapsedRealtime();
        if (this.mRecorder.getState() == 1) {
            try {
                ((AudioManager) this.mContext.getSystemService("audio")).requestAudioFocus(null, 3, 2);
                this.mRecorder.startRecordingForAVS();
                getAudioSourceListener().onReadyForSpeech();
                return;
            } catch (IllegalStateException e) {
                Log.e(TAG, "Exception starting recording", e);
                destroyAudioRecorder();
                throw new SpeechClientException("Exception starting recording", e);
            }
        }
        Log.e(TAG, "Failed to initiate recorder.");
        throw new SpeechClientException("Failed to initiate recorder.");
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.AudioSource
    public int getChunkSize() {
        return this.mAudioEncoder.getPacketSize();
    }

    @Override // com.amazon.blueshift.bluefront.android.audio.AudioSource
    public InputStream getConsumerStream() {
        return this.mConsumerStream;
    }

    public void startRecording() throws Exception {
        VoiceActivityDetector.VADState vADState;
        long j;
        startAudioRecorder();
        short[] sArr = new short[this.mNumSamplesPerRead];
        VoiceActivityDetector.VADState vADState2 = VoiceActivityDetector.VADState.NOT_STARTPOINTED;
        AudioSourceListener audioSourceListener = getAudioSourceListener();
        long j2 = this.mRecordStartTime;
        L16PcmEncoder l16PcmEncoder = new L16PcmEncoder();
        int i = 0;
        long j3 = j2;
        boolean z = false;
        boolean z2 = false;
        while (!isCancelled() && vADState2 != VoiceActivityDetector.VADState.ENDPOINTED) {
            try {
                if (this.mRecorder != null) {
                    int read = this.mRecorder.read(sArr, i, this.mNumSamplesPerRead);
                    if (-3 != read) {
                        if (read > 0) {
                            byte[] encode = this.mAudioEncoder.encode(sArr, read);
                            try {
                                this.mProducerStream.write(encode, i, encode.length);
                                this.mProducerStream.flush();
                                for (int i2 = i; i2 < read; i2++) {
                                    this.mSumOfSampleSquaresInRecPosNotificationPeriod += sArr[i2] * sArr[i2];
                                }
                                this.mSamplesCountInRecPosNotificationPeriod += read;
                                audioSourceListener.onBufferReceived(l16PcmEncoder.encode(sArr, read));
                                vADState = this.mVAD.processSamples(sArr, read);
                            } catch (IOException e) {
                                throw new SpeechClientException("Error writing to audio upload output stream", e);
                            }
                        } else {
                            vADState = vADState2;
                        }
                        long elapsedRealtime = SystemClock.elapsedRealtime() - j3;
                        if (vADState2 == VoiceActivityDetector.VADState.NOT_STARTPOINTED) {
                            if (vADState == VoiceActivityDetector.VADState.STARTPOINTED) {
                                audioSourceListener.onBeginningOfSpeech();
                            } else if (!z) {
                                j = j3;
                                if (elapsedRealtime >= this.mAudioTimeouts.getNoSpeechTimeout()) {
                                    Log.i(TAG, "No speech timeout fired");
                                    audioSourceListener.onNoSpeechTimeout();
                                    z = true;
                                }
                            }
                            j = j3;
                        } else {
                            j = j3;
                            if (vADState2 == VoiceActivityDetector.VADState.STARTPOINTED) {
                                if (vADState == VoiceActivityDetector.VADState.ENDPOINTED) {
                                    audioSourceListener.onSilenceDetected();
                                } else if (!z2 && elapsedRealtime >= this.mAudioTimeouts.getMaxSpeechTimeout()) {
                                    Log.i(TAG, "Max speech timeout fired");
                                    audioSourceListener.onMaxSpeechTimeout();
                                    z2 = true;
                                }
                            } else {
                                throw new SpeechClientException("Invalid VAD state transition while processing audio");
                            }
                        }
                        if (vADState2 != vADState) {
                            j3 = SystemClock.elapsedRealtime();
                            vADState2 = vADState;
                        } else {
                            j3 = j;
                        }
                        i = 0;
                    } else {
                        throw new SpeechClientException("AudioRecord - Invalid Operation");
                    }
                } else {
                    Log.e(TAG, "Recorder is null.");
                    throw new SpeechClientException("Recorder null");
                }
            } finally {
                this.mProducerStream.close();
                l16PcmEncoder.close();
                destroyAudioRecorder();
            }
        }
    }

    private AudioRecorder(Context context, AudioEncoder audioEncoder, VoiceActivityDetector voiceActivityDetector, AudioTimeouts audioTimeouts, AudioRecordWrapper audioRecordWrapper) throws Exception {
        this(context, audioEncoder, voiceActivityDetector, audioTimeouts, AudioRecord.getMinBufferSize(16000, 16, 2), audioRecordWrapper);
    }

    @VisibleForTesting
    protected AudioRecorder(Context context, AudioEncoder audioEncoder, VoiceActivityDetector voiceActivityDetector, AudioTimeouts audioTimeouts, int i, AudioRecordWrapper audioRecordWrapper) throws Exception {
        super(audioEncoder.getMediaType());
        Preconditions.checkNotNull(context, "Context cannot be null");
        Preconditions.checkNotNull(audioEncoder, "AudioEncoder cannot be null");
        Preconditions.checkNotNull(voiceActivityDetector, "VAD cannot be null");
        Preconditions.checkNotNull(audioTimeouts, "Audio timeouts cannot be null");
        Preconditions.checkArgument(i > 0, "Num samples per read must be greater than 0");
        if (context.checkCallingOrSelfPermission(ANDROID_PERMISSION_RECORD_AUDIO) != -1) {
            this.mContext = context;
            this.mAudioEncoder = audioEncoder;
            this.mVAD = voiceActivityDetector;
            this.mAudioTimeouts = audioTimeouts;
            this.mNumSamplesPerRead = i;
            this.mRecorder = audioRecordWrapper;
            this.mRecorder.setRecordPositionUpdateListener(new RecordPositionChangeListener());
            setupStreamPipe();
            return;
        }
        throw new SecurityException("Insufficient permissions to start ASR.");
    }
}
