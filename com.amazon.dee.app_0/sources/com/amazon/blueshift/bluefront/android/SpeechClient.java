package com.amazon.blueshift.bluefront.android;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;
import com.amazon.blueshift.bluefront.android.SpeechRequestListener;
import com.amazon.blueshift.bluefront.android.audio.AudioRecorder;
import com.amazon.blueshift.bluefront.android.audio.AudioSource;
import com.amazon.blueshift.bluefront.android.audio.AudioSourcePart;
import com.amazon.blueshift.bluefront.android.common.BluefrontCredential;
import com.amazon.blueshift.bluefront.android.http.AwsV2SigningProtocol;
import com.amazon.blueshift.bluefront.android.http.MultipartRequest;
import com.amazon.blueshift.bluefront.android.http.SigningProtocol;
import com.amazon.blueshift.bluefront.android.http.URLWrapper;
import com.amazon.blueshift.bluefront.android.metrics.MetricKey;
import com.amazon.blueshift.bluefront.android.metrics.MetricsCollector;
import com.amazon.blueshift.bluefront.android.metrics.SpeechMetricsConfiguration;
import com.amazon.blueshift.bluefront.android.request.SpeechRequest;
import com.amazon.blueshift.bluefront.android.task.AudioRecordingTask;
import com.amazon.blueshift.bluefront.android.task.AudioRecordingTaskListener;
import com.amazon.blueshift.bluefront.android.task.MultipartRequestTask;
import com.amazon.blueshift.bluefront.android.task.MultipartRequestTaskListener;
import com.amazon.blueshift.bluefront.android.task.TaskHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import javax.net.ssl.HttpsURLConnection;
/* loaded from: classes11.dex */
public final class SpeechClient<T> {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String TAG = "com.amazon.blueshift.bluefront.android.SpeechClient";
    private final AudioSource mAudioSource;
    private MultipartRequestTask mAudioStreamingTask;
    private final Optional<BluefrontCredential> mCredentials;
    private final AtomicBoolean mIsListening;
    private final MetricsCollector mMetricsCollector;
    private final Optional<URLWrapper> mSignedURL;
    private final SpeechRequest<T> mSpeechRequest;
    private SpeechRequestListener<T> mSpeechRequestListener;
    private AtomicBoolean mStartedProcessingRecordedAudio;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class AudioRecordingAndStreamingTaskListenerImpl implements AudioRecordingTaskListener, MultipartRequestTaskListener {
        private AudioRecordingAndStreamingTaskListenerImpl() {
        }

        @Override // com.amazon.blueshift.bluefront.android.task.AudioRecordingTaskListener
        public void onBeginningOfSpeech() {
            SpeechClient.this.mSpeechRequestListener.onBeginningOfSpeech();
        }

        @Override // com.amazon.blueshift.bluefront.android.task.AudioRecordingTaskListener
        public void onBufferReceived(byte[] bArr) {
            if (!SpeechClient.this.mStartedProcessingRecordedAudio.getAndSet(true)) {
                SpeechClient.this.mMetricsCollector.stopTimer(MetricKey.TIME_TO_START_PROCESSING_AUDIO);
            }
            SpeechClient.this.mSpeechRequestListener.onBufferReceived(bArr);
        }

        @Override // com.amazon.blueshift.bluefront.android.task.AudioRecordingTaskListener, com.amazon.blueshift.bluefront.android.task.MultipartRequestTaskListener
        public void onError(SpeechClientException speechClientException) {
            if (speechClientException instanceof SpeechClientConnectionException) {
                SpeechClient.this.mMetricsCollector.requestConnectionFailed();
            } else {
                SpeechClient.this.mMetricsCollector.requestReturnedError();
            }
            SpeechClient.this.stopRecording();
            SpeechClient.this.mSpeechRequestListener.onError(speechClientException);
            SpeechClient.this.finishMetrics(false);
        }

        @Override // com.amazon.blueshift.bluefront.android.task.AudioRecordingTaskListener
        public void onMaxSpeechTimeout() {
            SpeechClient.this.stopRecording();
            SpeechClient.this.mSpeechRequestListener.onMaxSpeechTimeout();
        }

        @Override // com.amazon.blueshift.bluefront.android.task.AudioRecordingTaskListener
        public void onNoSpeechTimeout() {
            SpeechClient.this.mSpeechRequestListener.onNoSpeechTimeout();
        }

        @Override // com.amazon.blueshift.bluefront.android.task.AudioRecordingTaskListener
        public void onReadyForSpeech() {
            SpeechClient.this.mSpeechRequestListener.onReadyForSpeech(null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.blueshift.bluefront.android.task.MultipartRequestTaskListener
        public void onResponse(byte[] bArr) {
            try {
                new String(bArr, Charsets.UTF_8);
                String unused = SpeechClient.TAG;
                SpeechClient.this.mSpeechRequestListener.onBluefrontResults(SpeechClient.MAPPER.readValue(bArr, SpeechClient.this.mSpeechRequest.getOutputType()));
                SpeechClient.this.finishMetrics(true);
            } catch (IOException e) {
                onError(new SpeechClientException("Failed to parse JSON response.", e));
            }
        }

        @Override // com.amazon.blueshift.bluefront.android.task.AudioRecordingTaskListener
        public void onRmsChanged(float f) {
            SpeechClient.this.mSpeechRequestListener.onRmsChanged(f);
        }

        @Override // com.amazon.blueshift.bluefront.android.task.AudioRecordingTaskListener
        public void onSilenceDetected() {
            SpeechClient.this.mMetricsCollector.requestAutomaticallyEndpointed();
            SpeechClient.this.stopRecording();
            SpeechClient.this.mSpeechRequestListener.onSilenceDetected();
        }
    }

    public SpeechClient(SpeechRequest<T> speechRequest, AudioSource audioSource) {
        this(speechRequest, audioSource, null, null, createMetricsCollector(speechRequest));
    }

    public static void configure(SpeechMetricsConfiguration speechMetricsConfiguration) {
        MetricsCollector.configure(speechMetricsConfiguration);
    }

    private HttpsURLConnection createConnection(SigningProtocol signingProtocol) throws SpeechClientException {
        URLWrapper unsignedUrl;
        try {
            if (this.mSignedURL.isPresent()) {
                unsignedUrl = this.mSignedURL.get();
            } else if (this.mCredentials.isPresent()) {
                unsignedUrl = signingProtocol.sign(this.mSpeechRequest.getRequestUri(), this.mCredentials.get());
            } else {
                unsignedUrl = getUnsignedUrl(this.mSpeechRequest.getRequestUri());
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Request URL: ");
            outline107.append(unsignedUrl.toString());
            outline107.toString();
            this.mMetricsCollector.startTimer(MetricKey.CONNECTION_TIME);
            try {
                try {
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) unsignedUrl.openConnection();
                    this.mMetricsCollector.stopTimer(MetricKey.CONNECTION_TIME);
                    httpsURLConnection.setConnectTimeout(this.mSpeechRequest.getTimeouts().getConnectionTimeoutMillis());
                    httpsURLConnection.setReadTimeout(this.mSpeechRequest.getTimeouts().getResponseTimeoutMillis());
                    httpsURLConnection.setChunkedStreamingMode(this.mAudioSource.getChunkSize());
                    for (Map.Entry<String, String> entry : this.mSpeechRequest.getHeaders().entrySet()) {
                        httpsURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                    return httpsURLConnection;
                } catch (IOException e) {
                    throw new SpeechClientConnectionException("Error establishing a connection", e);
                }
            } catch (Throwable th) {
                this.mMetricsCollector.stopTimer(MetricKey.CONNECTION_TIME);
                throw th;
            }
        } catch (MalformedURLException e2) {
            throw new SpeechClientException(e2);
        }
    }

    private static MetricsCollector createMetricsCollector(SpeechRequest<?> speechRequest) {
        return new MetricsCollector(speechRequest.getDevice().getContext(), Optional.fromNullable(speechRequest.getDevice().getId()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishMetrics(boolean z) {
        this.mMetricsCollector.stopTimer(MetricKey.TOTAL_REQUEST_TIME);
        if (z) {
            this.mMetricsCollector.stopTimer(MetricKey.ENDPOINT_TO_RESPONSE_TIME);
        } else {
            this.mMetricsCollector.cancelTimer(MetricKey.ENDPOINT_TO_RESPONSE_TIME);
        }
        this.mMetricsCollector.publish();
    }

    @VisibleForTesting
    static URLWrapper getUnsignedUrl(Uri uri) throws MalformedURLException {
        return new URLWrapper(uri.toString());
    }

    private void initializeMetricsCollector() {
        this.mStartedProcessingRecordedAudio = new AtomicBoolean(false);
        this.mMetricsCollector.addMetadata(MetricKey.LOCALE, this.mSpeechRequest.getLocale());
        this.mMetricsCollector.addMetadata(MetricKey.CARRIER, this.mSpeechRequest.getDevice().getCarrier());
        this.mMetricsCollector.addMetadata(MetricKey.NETWORK_TYPE, this.mSpeechRequest.getDevice().getConnectedNetworkType().toString());
        if (this.mSpeechRequest.getRequestId() != null) {
            this.mMetricsCollector.addMetadata(MetricKey.REQUEST_ID, this.mSpeechRequest.getRequestId());
        }
    }

    private void initializeSpeechRequestListener() {
        this.mSpeechRequestListener = new SpeechRequestListener.NullSpeechRequestListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopRecording() {
        this.mMetricsCollector.startTimer(MetricKey.ENDPOINT_TO_RESPONSE_TIME);
        this.mAudioSource.cancel();
        this.mIsListening.set(false);
    }

    public void cancel() {
        initializeSpeechRequestListener();
        stopRecording();
        MultipartRequestTask multipartRequestTask = this.mAudioStreamingTask;
        if (multipartRequestTask != null && !multipartRequestTask.isCancelled()) {
            this.mAudioStreamingTask.cancel(false);
            this.mAudioStreamingTask = null;
        }
        this.mMetricsCollector.requestCancelled();
        finishMetrics(false);
    }

    public SpeechRequestListener<T> getSpeechRequestListener() {
        return this.mSpeechRequestListener;
    }

    public boolean isListening() {
        return this.mIsListening.get();
    }

    public void setSpeechRequestListener(SpeechRequestListener<T> speechRequestListener) {
        Preconditions.checkNotNull(speechRequestListener, "Speech request listener cannot be null.");
        this.mSpeechRequestListener = speechRequestListener;
    }

    @SuppressLint({"NewApi"})
    public void startListening() {
        startListening(new AwsV2SigningProtocol());
    }

    public void stopListening() {
        if (this.mIsListening.get()) {
            stopRecording();
            this.mSpeechRequestListener.onEndOfSpeech();
            return;
        }
        Log.i(TAG, "No recognition is running");
    }

    public SpeechClient(SpeechRequest<T> speechRequest, AudioSource audioSource, @Nullable BluefrontCredential bluefrontCredential) {
        this(speechRequest, audioSource, bluefrontCredential, null, createMetricsCollector(speechRequest));
    }

    @SuppressLint({"NewApi"})
    void startListening(SigningProtocol signingProtocol) {
        if (!(this.mSpeechRequestListener instanceof SpeechRequestListener.NullSpeechRequestListener)) {
            if (this.mIsListening.get()) {
                this.mSpeechRequestListener.onError(new SpeechClientException("SpeechClient is busy."));
                return;
            }
            this.mIsListening.set(true);
            this.mMetricsCollector.startTimer(MetricKey.TOTAL_REQUEST_TIME);
            AudioRecordingAndStreamingTaskListenerImpl audioRecordingAndStreamingTaskListenerImpl = new AudioRecordingAndStreamingTaskListenerImpl();
            AudioSource audioSource = this.mAudioSource;
            if (audioSource instanceof AudioRecorder) {
                TaskHelper.execute(new AudioRecordingTask((AudioRecorder) audioSource, audioRecordingAndStreamingTaskListenerImpl));
            }
            AudioSourcePart audioSourcePart = new AudioSourcePart(this.mAudioSource);
            try {
                this.mAudioStreamingTask = new MultipartRequestTask(new MultipartRequest(createConnection(signingProtocol), this.mSpeechRequest.createContextPart(), this.mSpeechRequest.createParametersPart(), audioSourcePart), audioRecordingAndStreamingTaskListenerImpl);
                TaskHelper.execute(this.mAudioStreamingTask);
                return;
            } catch (SpeechClientException e) {
                audioRecordingAndStreamingTaskListenerImpl.onError(e);
                return;
            }
        }
        throw new IllegalStateException("No SpeechRequestListener set for this SpeechClient");
    }

    public SpeechClient(SpeechRequest<T> speechRequest, AudioSource audioSource, @Nullable URLWrapper uRLWrapper) {
        this(speechRequest, audioSource, null, uRLWrapper, createMetricsCollector(speechRequest));
    }

    SpeechClient(SpeechRequest<T> speechRequest, AudioSource audioSource, @Nullable BluefrontCredential bluefrontCredential, @Nullable URLWrapper uRLWrapper, MetricsCollector metricsCollector) {
        this.mIsListening = new AtomicBoolean(false);
        Preconditions.checkNotNull(speechRequest, "Speech request cannot be null.");
        Preconditions.checkNotNull(audioSource, "AudioSource cannot be null");
        Preconditions.checkNotNull(metricsCollector, "MetricsCollector cannot be null.");
        this.mSpeechRequest = speechRequest;
        this.mAudioSource = audioSource;
        this.mCredentials = Optional.fromNullable(bluefrontCredential);
        this.mSignedURL = Optional.fromNullable(uRLWrapper);
        this.mMetricsCollector = metricsCollector;
        initializeMetricsCollector();
        initializeSpeechRequestListener();
    }
}
