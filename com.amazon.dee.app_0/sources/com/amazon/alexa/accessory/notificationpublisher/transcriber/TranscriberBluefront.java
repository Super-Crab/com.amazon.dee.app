package com.amazon.alexa.accessory.notificationpublisher.transcriber;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.transcriber.bluefront.SpeechClientManager;
import com.amazon.alexa.accessory.notificationpublisher.transcriber.bluefront.SpeechRecognizer;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.PermissionChecker;
import com.amazon.bluefront.api.common.Arrf;
import com.amazon.bluefront.api.common.Hypothesis;
import com.amazon.blueshift.bluefront.android.SpeechClientException;
import com.amazon.blueshift.bluefront.android.SpeechRequestListener;
import com.amazon.blueshift.bluefront.android.audio.AudioRecordProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class TranscriberBluefront implements Transcriber, SpeechRequestListener<Arrf> {
    private static final String AUDIO_RECORD_HTTPRequest_ERROR_MESSAGE = "Failed to execute HTTP request";
    private static final String AUDIO_RECORD_INIT_FAILED_ERROR_MESSAGE = "Failed to initiate recorder";
    private static final String AUDIO_RECORD_TIMEOUT_ERROR_MESSAGE = "Processing Timeout";
    private static final int BLUEFRONT_SPEECH_CAPTURE_START_DELAY = 800;
    private static final String TAG = TranscriberBluefront.class.getSimpleName();
    private BluetoothAudioRecorder btAudioRecorder;
    private SpeechRecognizer speechRecognizer;
    private Handler transcribeHandler;
    private TranscribeListener transcribeListener;
    private Runnable transcribeRunnable;
    private String requestId = "";
    private AtomicBoolean isUserSpeaking = new AtomicBoolean(false);
    private boolean userCanceled = false;

    /* loaded from: classes.dex */
    private class TranscribeRunnable implements Runnable {
        private TranscribeRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TranscriberBluefront.this.startListening();
        }
    }

    private void beginUserSpeaking() {
        this.isUserSpeaking.set(true);
    }

    private void endUserSpeaking() {
        Log.i(TAG, "endUserSpeaking");
        this.isUserSpeaking.set(false);
        try {
            this.transcribeHandler.removeCallbacks(this.transcribeRunnable);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("endUserSpeaking - Exception in thread cleanup - ", e, TAG);
        }
        try {
            Log.i(TAG, "endUserSpeaking - Close microphone");
            this.btAudioRecorder.closeMicrophone();
        } catch (Exception e2) {
            GeneratedOutlineSupport1.outline157("endUserSpeaking - Exception when closing microphone - ", e2, TAG);
        }
    }

    private synchronized void sendTranscriberErrorAndEndSpeechSession(boolean z) {
        Log.i(TAG, "sendTranscriberErrorAndEndSpeechSession");
        endUserSpeaking();
        SpeechClientManager.destroyClient();
        if (this.transcribeListener != null && !this.userCanceled) {
            this.transcribeListener.onTranscribeError(z ? 7 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startListening() {
        Log.i(TAG, "startListening");
        this.userCanceled = false;
        try {
            this.requestId = this.speechRecognizer.startListening();
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_RECORDING_STARTED);
        } catch (Exception e) {
            MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(MetricsConstants.REPLY_RECORDING_EXCEPTION);
            outline107.append(e.getClass().getSimpleName());
            metricsRecorder.recordCounter(outline107.toString(), MetricsRecorder.customAttributesForException(e, 512));
            sendTranscriberErrorAndEndSpeechSession(false);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.transcriber.Transcriber
    public void cancelTranscribe() {
        Log.i(TAG, "cancelTranscribe");
        MetricsRecorder.getInstance().cancelTimer(MetricsConstants.REPLY_TRANSCRIPTION_LATENCY, this.requestId);
        SpeechRecognizer speechRecognizer = this.speechRecognizer;
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
        }
        this.userCanceled = true;
        endUserSpeaking();
        SpeechClientManager.destroyClient();
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.transcriber.Transcriber
    public boolean hasRecordAudioPermission() {
        return PermissionChecker.hasRecordAudioPermission();
    }

    @Override // android.speech.RecognitionListener
    public void onBeginningOfSpeech() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onBeginningOfSpeech - Request ID: ");
        outline107.append(this.requestId);
        Log.i(str, outline107.toString());
        beginUserSpeaking();
    }

    @Override // android.speech.RecognitionListener
    public void onBufferReceived(byte[] bArr) {
    }

    @Override // com.amazon.blueshift.bluefront.android.SpeechRequestListener, android.speech.RecognitionListener
    public void onEndOfSpeech() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onEndOfSpeech - Request ID: ");
        outline107.append(this.requestId);
        Log.d(str, outline107.toString());
        MetricsRecorder.getInstance().startTimer(MetricsConstants.REPLY_TRANSCRIPTION_LATENCY, this.requestId);
        endUserSpeaking();
    }

    @Override // com.amazon.blueshift.bluefront.android.SpeechRequestListener
    public void onError(SpeechClientException speechClientException) {
        boolean z;
        String str = TAG;
        Log.e(str, "onError - " + speechClientException);
        MetricsRecorder.getInstance().cancelTimer(MetricsConstants.REPLY_TRANSCRIPTION_LATENCY, this.requestId);
        if (speechClientException.getMessage().contains(AUDIO_RECORD_INIT_FAILED_ERROR_MESSAGE)) {
            Log.e(TAG, "onError - Recorder wasn't initialized, this is not a retry-able error");
            z = false;
            Log.i(TAG, "onError - Releasing AudioRecord");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_TRANSCRIPTION_ERROR_INIT);
            AudioRecordProvider.INSTANCE.releaseAudioRecord();
        } else {
            if (!speechClientException.getMessage().contains(AUDIO_RECORD_TIMEOUT_ERROR_MESSAGE) && !speechClientException.getMessage().contains(AUDIO_RECORD_HTTPRequest_ERROR_MESSAGE)) {
                String str2 = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onError");
                outline107.append(speechClientException.getMessage());
                Log.e(str2, outline107.toString());
                MetricsRecorder metricsRecorder = MetricsRecorder.getInstance();
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(MetricsConstants.REPLY_TRANSCRIPTION_ERROR);
                outline1072.append(speechClientException.getClass().getSimpleName());
                metricsRecorder.recordCounter(outline1072.toString(), MetricsRecorder.customAttributesForException(speechClientException, 512));
            } else {
                String str3 = TAG;
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("onError - Recorder threw network error");
                outline1073.append(speechClientException.getMessage());
                Log.e(str3, outline1073.toString());
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_TRANSCRIPTION_ERROR_NETWORK);
            }
            z = true;
        }
        sendTranscriberErrorAndEndSpeechSession(z);
    }

    @Override // android.speech.RecognitionListener
    public void onEvent(int i, Bundle bundle) {
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.transcriber.Transcriber
    public void onManualSpeechEndPointing() {
        Log.i(TAG, "onManualSpeechEndPointing");
        if (!this.isUserSpeaking.get()) {
            Log.i(TAG, "onManualSpeechEndPointing - Ignore, user is not speaking");
            return;
        }
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_MANUAL_ENDPOINT);
        MetricsRecorder.getInstance().startTimer(MetricsConstants.REPLY_TRANSCRIPTION_LATENCY, this.requestId);
        SpeechRecognizer speechRecognizer = this.speechRecognizer;
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
        }
        endUserSpeaking();
    }

    @Override // com.amazon.blueshift.bluefront.android.SpeechRequestListener
    public void onMaxSpeechTimeout() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Max speech timeout received - Request ID: ");
        outline107.append(this.requestId);
        Log.i(str, outline107.toString());
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_MAX_SPEECH_TIMEOUT);
    }

    @Override // com.amazon.blueshift.bluefront.android.SpeechRequestListener
    public void onNoSpeechTimeout() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No speech timeout received - Request ID: ");
        outline107.append(this.requestId);
        Log.i(str, outline107.toString());
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_NO_SPEECH_TIMEOUT);
        SpeechClientManager.destroyClient();
        endUserSpeaking();
        this.transcribeListener.onTranscribeError(9);
    }

    @Override // android.speech.RecognitionListener
    public void onPartialResults(Bundle bundle) {
    }

    @Override // android.speech.RecognitionListener
    public void onReadyForSpeech(Bundle bundle) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onReadyForSpeech - Request ID: ");
        outline107.append(this.requestId);
        Log.i(str, outline107.toString());
    }

    @Override // android.speech.RecognitionListener
    @SuppressLint({"InlinedApi"})
    public void onResults(Bundle bundle) {
    }

    @Override // android.speech.RecognitionListener
    public void onRmsChanged(float f) {
    }

    @Override // com.amazon.blueshift.bluefront.android.SpeechRequestListener
    public void onSilenceDetected() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onSilenceDetected - Request ID: ");
        outline107.append(this.requestId);
        Log.i(str, outline107.toString());
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.transcriber.Transcriber
    public boolean startTranscribe(TranscribeListener transcribeListener, Handler handler) throws IllegalArgumentException {
        Log.i(TAG, "startTranscribe");
        this.transcribeListener = transcribeListener;
        Context context = DependencyProvider.getContext();
        if (context == null) {
            Log.e(TAG, "startTranscribe - Context is null, cannot transcribe");
            return false;
        } else if (SpeechClientManager.isListening()) {
            Log.e(TAG, "startTranscribe - Speech client is already listening, cannot start a new request");
            return false;
        } else {
            this.btAudioRecorder = new BluetoothAudioRecorder();
            if (!this.btAudioRecorder.openMicrophone()) {
                Log.e(TAG, "startTranscribe - Failed to open microphone, cannot transcribe");
                return false;
            } else if (handler != null && handler.getLooper() != Looper.getMainLooper()) {
                this.speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
                this.speechRecognizer.setRecognitionListener(this);
                this.transcribeHandler = handler;
                this.transcribeRunnable = new TranscribeRunnable();
                this.transcribeHandler.postDelayed(this.transcribeRunnable, 800L);
                return true;
            } else {
                Log.e(TAG, "startTranscribe - Null or main thread handler");
                throw new IllegalArgumentException("Handler cannot be null or cannot be main thread's Handler");
            }
        }
    }

    @Override // com.amazon.blueshift.bluefront.android.SpeechRequestListener
    public void onBluefrontResults(Arrf arrf) {
        MetricsRecorder.getInstance().pauseAndRecordTimer(MetricsConstants.REPLY_TRANSCRIPTION_LATENCY, this.requestId);
        if (arrf == null) {
            Log.w(TAG, "onBluefrontResults - Results is null");
            sendTranscriberErrorAndEndSpeechSession(true);
        } else if (arrf.getUtterances() != null && !arrf.getUtterances().isEmpty()) {
            String id = arrf.getId() != null ? arrf.getId() : "null";
            Log.d(TAG, "onBluefrontResults - Utterance ID: " + id);
            for (Hypothesis hypothesis : arrf.getUtterances().get(0).getHypotheses()) {
                String utterance = hypothesis.getUtterance();
                float confidence = hypothesis.getConfidence();
                if (!Strings.isNullOrEmpty(utterance)) {
                    Log.i(TAG, "onBluefrontResults - Got transcribed text");
                    String postProcessTranscribedText = new TranscribePostProcessor().postProcessTranscribedText(utterance);
                    GeneratedOutlineSupport1.outline165("onBluefrontResults - Got transcribed text - ", postProcessTranscribedText, TAG);
                    Log.d(TAG, "onBluefrontResults - Top confidence is: " + confidence);
                    this.transcribeListener.onTranscribeResult(postProcessTranscribedText, confidence);
                    endUserSpeaking();
                    return;
                }
            }
            Log.w(TAG, "onBluefrontResults - Unexpected state, we did not find results");
            sendTranscriberErrorAndEndSpeechSession(true);
        } else {
            Log.w(TAG, "onBluefrontResults - Results utterances is null or empty");
            sendTranscriberErrorAndEndSpeechSession(true);
        }
    }

    @Override // android.speech.RecognitionListener
    public void onError(int i) {
        String str = TAG;
        Log.e(str, "onError - " + i);
        MetricsRecorder.getInstance().cancelTimer(MetricsConstants.REPLY_TRANSCRIPTION_LATENCY, this.requestId);
        sendTranscriberErrorAndEndSpeechSession(true);
    }
}
