package com.amazon.alexa.accessory.notificationpublisher.transcriber;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.consumption.BaseComponent;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public final class SpeechRecognizerComponent extends BaseComponent {
    public static final int EVENT_CANCEL_SPEECH_RECOGNITION = 2;
    public static final int EVENT_ERROR_BLUETOOTH_RECORD_FAILED = 6;
    public static final int EVENT_ERROR_GENERIC = 10;
    public static final int EVENT_ERROR_NO_RECORD_AUDIO_PERMISSION = 5;
    public static final int EVENT_ERROR_NO_TRANSCRIBER = 4;
    public static final int EVENT_ERROR_RETRY_LIMIT_REACHED = 13;
    public static final int EVENT_ERROR_TRANSCRIBE = 8;
    public static final int EVENT_ERROR_TRANSCRIBE_RETRY_AVAILABLE = 7;
    public static final int EVENT_ERROR_USER = 9;
    public static final int EVENT_MANUAL_SPEECH_END_POINTING = 3;
    public static final int EVENT_START_SPEECH_RECOGNITION = 1;
    public static final int EVENT_TRANSCRIBE_CONFIDENCE_LOW = 12;
    public static final int EVENT_TRANSCRIBE_SUCCESS = 11;
    public static final float LOW_CONFIDENCE_THRESHOLD = 0.35f;
    private static final String TAG = "SpeechRecognizerComponent";
    private static SpeechRecognizerComponent speechRecognizerInstance = null;
    private static final boolean supportsLowConfidenceFlow = false;
    private final Handler backgroundHandler;
    private CancelSpeechRecognitionRunnable cancelSpeechRecognitionRunnable;
    private TranscribeListener listener;
    private final Handler mainHandler;
    private ManualSpeechEndPointingRunnable manualSpeechEndPointingRunnable;
    private StartSpeechRecognitionRunnable startSpeechRecognitionRunnable;
    private Transcriber transcriber;
    private Handler transcriptionHandler;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CancelSpeechRecognitionRunnable implements Runnable {
        private CancelSpeechRecognitionRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SpeechRecognizerComponent.speechRecognizerInstance.cancelSpeechRecognition();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ManualSpeechEndPointingRunnable implements Runnable {
        private ManualSpeechEndPointingRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SpeechRecognizerComponent.speechRecognizerInstance.signalManualSpeechEndPointing();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class StartSpeechRecognitionRunnable implements Runnable {
        private StartSpeechRecognitionRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SpeechRecognizerComponent.speechRecognizerInstance.startSpeechRecognition();
        }
    }

    /* loaded from: classes.dex */
    private static final class TranscribeListenerImpl implements TranscribeListener {
        private TranscribeListenerImpl() {
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.transcriber.TranscribeListener
        public void onTranscribeError(int i) {
            Log.i(SpeechRecognizerComponent.TAG, "onTranscribeError");
            SpeechRecognizerComponent.speechRecognizerInstance.postEventMessage(i);
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.transcriber.TranscribeListener
        public void onTranscribeResult(String str, float f) {
            String str2 = SpeechRecognizerComponent.TAG;
            Log.i(str2, "onTranscribeResult with confidence = " + f);
            Log.i(SpeechRecognizerComponent.TAG, "onTranscribeResult - Positive Confidence");
            SpeechRecognizerComponent.speechRecognizerInstance.postEventMessage(11, str);
        }
    }

    private SpeechRecognizerComponent() {
        super(8);
        this.listener = new TranscribeListenerImpl();
        this.mainHandler = new Handler(Looper.getMainLooper());
        HandlerThread handlerThread = new HandlerThread("SpeechRecognizerThread", 10);
        handlerThread.start();
        this.backgroundHandler = new Handler(handlerThread.getLooper());
        setupTranscriber();
        this.startSpeechRecognitionRunnable = new StartSpeechRecognitionRunnable();
        this.cancelSpeechRecognitionRunnable = new CancelSpeechRecognitionRunnable();
        this.manualSpeechEndPointingRunnable = new ManualSpeechEndPointingRunnable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelSpeechRecognition() {
        Log.i(TAG, "cancelSpeechRecognition");
        Transcriber transcriber = this.transcriber;
        if (transcriber != null) {
            transcriber.cancelTranscribe();
        }
    }

    public static synchronized SpeechRecognizerComponent getInstance() {
        SpeechRecognizerComponent speechRecognizerComponent;
        synchronized (SpeechRecognizerComponent.class) {
            if (speechRecognizerInstance == null) {
                speechRecognizerInstance = new SpeechRecognizerComponent();
            }
            speechRecognizerComponent = speechRecognizerInstance;
        }
        return speechRecognizerComponent;
    }

    public static synchronized void releaseInstance() {
        synchronized (SpeechRecognizerComponent.class) {
            if (speechRecognizerInstance != null) {
                speechRecognizerInstance.transcriber = null;
                speechRecognizerInstance.listener = null;
                speechRecognizerInstance.startSpeechRecognitionRunnable = null;
                speechRecognizerInstance.cancelSpeechRecognitionRunnable = null;
            }
            speechRecognizerInstance = null;
        }
    }

    private void setupTranscriber() {
        Log.i(TAG, "setupTranscriber - Creating transcriber");
        this.transcriptionHandler = this.backgroundHandler;
        this.transcriber = new TranscriberBluefront();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void signalManualSpeechEndPointing() {
        Log.i(TAG, "signalManualSpeechEndPointing");
        Transcriber transcriber = this.transcriber;
        if (transcriber != null) {
            transcriber.onManualSpeechEndPointing();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSpeechRecognition() {
        Log.i(TAG, "startSpeechRecognition");
        Transcriber transcriber = this.transcriber;
        if (transcriber == null) {
            Log.e(TAG, "startSpeechRecognition - Transcriber is null");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_TRANSCRIBER_NULL);
            postEventMessage(4);
        } else if (!transcriber.hasRecordAudioPermission()) {
            Log.e(TAG, "startSpeechRecognition - No RECORD_AUDIO permission");
            postEventMessage(5);
        } else if (this.transcriber.startTranscribe(this.listener, this.transcriptionHandler)) {
        } else {
            Log.e(TAG, "startSpeechRecognition - Bluetooth recording error");
            postEventMessage(6);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.BaseComponent
    public void handleEventMessage(int i, @Nullable Object obj) {
        if (i == 1) {
            Log.i(TAG, "handleEventMessage - EVENT_START_SPEECH_RECOGNITION");
            this.mainHandler.post(this.startSpeechRecognitionRunnable);
        } else if (i == 2) {
            Log.i(TAG, "handleEventMessage - EVENT_CANCEL_SPEECH_RECOGNITION");
            this.mainHandler.post(this.cancelSpeechRecognitionRunnable);
        } else if (i != 3) {
            GeneratedOutlineSupport1.outline151("handleEventMessage - Unhandled eventId: ", i, TAG);
        } else {
            Log.i(TAG, "handleEventMessage - EVENT_MANUAL_SPEECH_END_POINTING");
            this.mainHandler.post(this.manualSpeechEndPointingRunnable);
        }
    }

    /* renamed from: clone */
    public SpeechRecognizerComponent m362clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
