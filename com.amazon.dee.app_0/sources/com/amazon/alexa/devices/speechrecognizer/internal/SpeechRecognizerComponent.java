package com.amazon.alexa.devices.speechrecognizer.internal;

import amazon.speech.audio.AudioStream;
import amazon.speech.audio.v2.AudioStream;
import amazon.speech.audio.v2.AudioStreamReader;
import amazon.speech.audio.v2.AudioStreamWriter;
import android.media.AudioFormat;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.Version;
import com.amazon.alexa.devices.android.internal.BaseProxyClientImpl;
import com.amazon.alexa.devices.notification.INotificationCallback;
import com.amazon.alexa.devices.notification.NotificationSubscriptionBuilder;
import com.amazon.alexa.devices.notification.internal.NotificationCapableComponent;
import com.amazon.alexa.devices.sdk.IAlexaApiGateway;
import com.amazon.alexa.devices.sdk.IAudioComponent;
import com.amazon.alexa.devices.sdk.IUtteranceProvider;
import com.amazon.alexa.devices.sdk.IWakeWordDetectionListener;
import com.amazon.alexa.devices.speechrecognizer.AudioEvent;
import com.amazon.alexa.devices.speechrecognizer.AudioEventListener;
import com.amazon.alexa.devices.speechrecognizer.AudioFormat;
import com.amazon.alexa.devices.speechrecognizer.AudioProcessingNotification;
import com.amazon.alexa.devices.speechrecognizer.SpeechRecognizerCallback;
import com.amazon.alexa.devices.speechrecognizer.UtteranceProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
@BaseProxyClientImpl.ComponentVersion(build = 0, major = 1, minor = 0)
/* loaded from: classes6.dex */
public class SpeechRecognizerComponent extends NotificationCapableComponent implements com.amazon.alexa.devices.speechrecognizer.SpeechRecognizerComponent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String SPEECH_RECOGNIZER_AUDIO_STREAM_NAME = "SpeechRecognizer Component AudioStream";
    private static final String TAG = "SpeechRecognizerComponent";
    private IAudioComponent mAudioComponent;
    private AudioOutputStream mAudioOutputStream;
    private UtteranceSession mUtteranceSession;
    private boolean mAutoCloseSession = false;
    private Semaphore mAccess = new Semaphore(1);
    private Map<UtteranceProvider, IUtteranceProvider> registeredProviders = new HashMap();

    /* renamed from: com.amazon.alexa.devices.speechrecognizer.internal.SpeechRecognizerComponent$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$devices$speechrecognizer$AudioFormat$Encoding = new int[AudioFormat.Encoding.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$devices$speechrecognizer$AudioFormat$Encoding[AudioFormat.Encoding.ENCODING_PCM_16BIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$devices$speechrecognizer$AudioFormat$Encoding[AudioFormat.Encoding.ENCODING_OPUS_16BIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$devices$speechrecognizer$AudioFormat$Encoding[AudioFormat.Encoding.ENCODING_OPUS_16BIT_16KBPS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class AudioInputStream extends InputStream {
        private AudioStreamReader mAudioStreamReader;
        private long mPosition;

        AudioInputStream(long j) throws IOException {
            this.mPosition = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @NonNull
        public AudioStreamReader getReader() throws IOException {
            if (this.mAudioStreamReader == null) {
                this.mAudioStreamReader = (AudioStreamReader) SpeechRecognizerComponent.this.mAudioOutputStream.mAudioStream.openReader();
                long j = this.mPosition;
                if (j >= 0) {
                    this.mAudioStreamReader.setPosition(j);
                }
            }
            return this.mAudioStreamReader;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            AudioStreamReader audioStreamReader = this.mAudioStreamReader;
            if (audioStreamReader != null) {
                audioStreamReader.close();
                this.mAudioStreamReader = null;
            }
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            return getReader().read();
        }

        @Override // java.io.InputStream
        public int read(@NonNull byte[] bArr, int i, int i2) throws IOException {
            return getReader().read(bArr, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class AudioOutputStream extends OutputStream {
        private AudioStream mAudioStream;
        private AudioStreamWriter mWriter;

        AudioOutputStream(AudioFormat audioFormat) {
            int sampleRate = ((audioFormat.sampleRate.getSampleRate() * 10000) / 1000) + 112000;
            int ordinal = audioFormat.encoding.ordinal();
            if (ordinal == 0) {
                this.mAudioStream = AudioStream.create(new AudioStream.AudioStreamParams().setFormat(getPCMAudioFormat(audioFormat)).mo15setName(SpeechRecognizerComponent.SPEECH_RECOGNIZER_AUDIO_STREAM_NAME).mo14setFrameCount(sampleRate).mo16setPiped(0));
            } else if (ordinal != 1 && ordinal != 2) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported Encoding used to create AudioOutputStream: ");
                outline107.append(audioFormat.encoding);
                throw new IllegalArgumentException(outline107.toString());
            } else {
                this.mAudioStream = amazon.speech.audio.v2.AudioStream.create(new AudioStream.AudioStreamParamsExtended().setEncodingExtended(SpeechRecognizerComponent.this.mapEncoding(audioFormat.encoding)).setSampleRate(audioFormat.sampleRate.getSampleRate()).setChannelMask(16).mo15setName(SpeechRecognizerComponent.SPEECH_RECOGNIZER_AUDIO_STREAM_NAME).mo14setFrameCount(sampleRate).mo16setPiped(0));
            }
        }

        private android.media.AudioFormat getPCMAudioFormat(AudioFormat audioFormat) {
            return new AudioFormat.Builder().setEncoding(SpeechRecognizerComponent.this.mapEncoding(audioFormat.encoding)).setSampleRate(audioFormat.sampleRate.getSampleRate()).setChannelMask(16).build();
        }

        @NonNull
        private AudioStreamWriter getWriter() {
            if (this.mWriter == null) {
                this.mWriter = (AudioStreamWriter) this.mAudioStream.openWriter();
            }
            return this.mWriter;
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            AudioStreamWriter audioStreamWriter = this.mWriter;
            if (audioStreamWriter != null) {
                audioStreamWriter.close();
                this.mWriter = null;
            }
        }

        public void destroy() throws IOException {
            close();
            amazon.speech.audio.v2.AudioStream audioStream = this.mAudioStream;
            if (audioStream != null) {
                audioStream.recycle();
                this.mAudioStream = null;
            }
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            getWriter().write(i);
        }

        @Override // java.io.OutputStream
        public void write(@NonNull byte[] bArr, int i, int i2) throws IOException {
            getWriter().write(bArr, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class QueryListeningStateTask extends AsyncTask<Void, Void, AudioProcessingNotification> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final SpeechRecognizerCallback<AudioProcessingNotification> mCallback;
        private AudioProcessingNotification mQueriedNotification;

        /* synthetic */ QueryListeningStateTask(SpeechRecognizerComponent speechRecognizerComponent, SpeechRecognizerCallback speechRecognizerCallback, AnonymousClass1 anonymousClass1) {
            this(speechRecognizerCallback);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Void... voidArr) {
        }

        private QueryListeningStateTask(SpeechRecognizerCallback<AudioProcessingNotification> speechRecognizerCallback) {
            this.mCallback = speechRecognizerCallback;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public AudioProcessingNotification doInBackground(Void... voidArr) {
            String unused = SpeechRecognizerComponent.TAG;
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            try {
                SpeechRecognizerComponent.super.subscribe(new NotificationSubscriptionBuilder(AudioProcessingNotification.class).setSticky(true).setCallback(new INotificationCallback<AudioProcessingNotification>() { // from class: com.amazon.alexa.devices.speechrecognizer.internal.SpeechRecognizerComponent.QueryListeningStateTask.1
                    @Override // com.amazon.alexa.devices.notification.INotificationCallback
                    public void onHandleNotification(AudioProcessingNotification audioProcessingNotification) {
                        Log.i(SpeechRecognizerComponent.TAG, "Received Notification");
                        QueryListeningStateTask.this.mQueriedNotification = audioProcessingNotification;
                        countDownLatch.countDown();
                    }
                }).build());
                countDownLatch.await(2L, TimeUnit.SECONDS);
            } catch (AlexaException | InterruptedException e) {
                Log.w(SpeechRecognizerComponent.TAG, "Failed to query notification", e);
            }
            return this.mQueriedNotification;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(AudioProcessingNotification audioProcessingNotification) {
            String unused = SpeechRecognizerComponent.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AsyncTask.onPostExecute, notification valid: ");
            outline107.append(audioProcessingNotification != null);
            outline107.toString();
            if (audioProcessingNotification != null) {
                this.mCallback.onSuccess(audioProcessingNotification);
            } else {
                this.mCallback.onFailure(new AlexaException("Failed to Query State"));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class UtteranceProviderWrapper extends IUtteranceProvider.Stub {
        private AudioEventListener mAudioEventListenerInterfaceWrapper;
        private int mEnrollId;
        private UtteranceProvider mUtteranceProvider;

        UtteranceProviderWrapper(UtteranceProvider utteranceProvider) {
            this.mUtteranceProvider = utteranceProvider;
        }

        @Override // com.amazon.alexa.devices.sdk.IUtteranceProvider
        public boolean listen(int i, String str) throws RemoteException {
            try {
                return this.mUtteranceProvider.listen(str);
            } catch (AlexaException unused) {
                return false;
            }
        }

        @Override // com.amazon.alexa.devices.sdk.IUtteranceProvider
        public int startRecognition(List<String> list, int i, IWakeWordDetectionListener iWakeWordDetectionListener) throws RemoteException {
            String unused = SpeechRecognizerComponent.TAG;
            AudioEventListener audioEventListener = this.mAudioEventListenerInterfaceWrapper;
            if (audioEventListener == null) {
                this.mAudioEventListenerInterfaceWrapper = (AudioEventListener) SpeechRecognizerComponent.this.createInterfaceProxyWrapper(AudioEventListener.class, iWakeWordDetectionListener, this, null);
            } else {
                SpeechRecognizerComponent.this.updateWrappedObject(audioEventListener, iWakeWordDetectionListener);
            }
            try {
                this.mUtteranceProvider.startUtteranceRecognition(list, i, this.mAudioEventListenerInterfaceWrapper);
                return this.mEnrollId;
            } catch (AlexaException e) {
                throw new RemoteException(e.getMessage());
            }
        }

        @Override // com.amazon.alexa.devices.sdk.IUtteranceProvider
        public boolean stopCapture(int i, String str) throws RemoteException {
            try {
                return this.mUtteranceProvider.stopAudioCapture(str);
            } catch (AlexaException e) {
                throw new RemoteException(e.getMessage());
            }
        }

        @Override // com.amazon.alexa.devices.sdk.IUtteranceProvider
        public int stopRecognition(int i) throws RemoteException {
            String unused = SpeechRecognizerComponent.TAG;
            GeneratedOutlineSupport1.outline149("stopRecognition() | enrollId = ", i);
            try {
                this.mUtteranceProvider.stopUtteranceRecognition();
                return 0;
            } catch (AlexaException e) {
                throw new RemoteException(e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class UtteranceSession {
        AudioInputStream audioInputStream;
        String utteranceId;

        public UtteranceSession(AudioEvent audioEvent) throws AlexaException {
            this.audioInputStream = SpeechRecognizerComponent.this.createAudioInputStream(audioEvent.getBeginIndex());
        }

        public void close() throws IOException {
            String unused = SpeechRecognizerComponent.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Closing session with id: ");
            outline107.append(this.utteranceId);
            outline107.toString();
            AudioInputStream audioInputStream = this.audioInputStream;
            if (audioInputStream != null) {
                audioInputStream.close();
                this.audioInputStream = null;
            }
        }
    }

    private void cleanUpAudioOutputStream() {
        AudioOutputStream audioOutputStream = this.mAudioOutputStream;
        if (audioOutputStream instanceof AudioOutputStream) {
            try {
                audioOutputStream.destroy();
            } catch (IOException e) {
                Log.e(TAG, "Unable to destroy the outputstream", e);
            }
            this.mAudioOutputStream = null;
        }
    }

    private AudioOutputStream createAudioOutputStreamInternal(@NonNull com.amazon.alexa.devices.speechrecognizer.AudioFormat audioFormat) throws AlexaException {
        if (this.mAudioComponent != null) {
            try {
                this.mAudioComponent.setAudioEncoding(mapEncoding(audioFormat.encoding));
                return new AudioOutputStream(audioFormat);
            } catch (RemoteException | IllegalArgumentException | IllegalStateException e) {
                throw new AlexaException(e);
            }
        }
        throw new AlexaException("AudioComponent is null, make sure that the ProteusRuntime is available");
    }

    private boolean doRegisterUtteranceProvider(UtteranceProvider utteranceProvider) throws RemoteException {
        IUtteranceProvider iUtteranceProvider = this.registeredProviders.get(utteranceProvider);
        if (iUtteranceProvider == null) {
            iUtteranceProvider = new UtteranceProviderWrapper(utteranceProvider);
            this.registeredProviders.put(utteranceProvider, iUtteranceProvider);
        }
        return this.mAudioComponent.registerUtteranceProvider(iUtteranceProvider);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int mapEncoding(AudioFormat.Encoding encoding) {
        int ordinal = encoding.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return 1000;
            }
            if (ordinal == 2) {
                return 1001;
            }
            throw new IllegalArgumentException("Encoding " + encoding + " not supported");
        }
        return 2;
    }

    @Override // com.amazon.alexa.devices.notification.internal.NotificationCapableComponent, com.amazon.alexa.devices.android.internal.BaseProxyClientImpl
    public void connect(IAlexaApiGateway iAlexaApiGateway, Version version) throws RemoteException, AlexaException {
        super.connect(iAlexaApiGateway, version);
        this.mAudioComponent = (IAudioComponent) getRemoteComponent(iAlexaApiGateway, IAudioComponent.class, version);
        Map<UtteranceProvider, IUtteranceProvider> map = this.registeredProviders;
        if (map != null) {
            for (UtteranceProvider utteranceProvider : map.keySet()) {
                if (!doRegisterUtteranceProvider(utteranceProvider)) {
                    String str = TAG;
                    Log.e(str, "Unable to register provider " + utteranceProvider);
                }
            }
        }
    }

    public AudioInputStream createAudioInputStream(long j) throws AlexaException {
        if (this.mAudioOutputStream != null) {
            try {
                return new AudioInputStream(j);
            } catch (IOException e) {
                throw new AlexaException(e);
            }
        }
        throw new IllegalStateException("Unable to create an Audio InputStream. Did you forget to call createAudioOutputStream first?");
    }

    @Override // com.amazon.alexa.devices.speechrecognizer.SpeechRecognizerComponent
    public OutputStream createAudioOutputStream(@NonNull com.amazon.alexa.devices.speechrecognizer.AudioFormat audioFormat) throws AlexaException {
        if (this.mAudioOutputStream == null) {
            this.mAudioOutputStream = createAudioOutputStreamInternal(audioFormat);
        }
        return this.mAudioOutputStream;
    }

    @Override // com.amazon.alexa.devices.speechrecognizer.SpeechRecognizerComponent
    public boolean deregisterUtteranceProvider(UtteranceProvider utteranceProvider) throws AlexaException {
        try {
            IUtteranceProvider remove = this.registeredProviders.remove(utteranceProvider);
            if (remove == null) {
                return false;
            }
            return this.mAudioComponent.deregisterUtteranceProvider(remove);
        } catch (RemoteException e) {
            throw new AlexaException(e);
        }
    }

    @Override // com.amazon.alexa.devices.android.internal.BaseProxyClientImpl
    public void destroy() {
        cleanUpAudioOutputStream();
        super.destroy();
    }

    @Override // com.amazon.alexa.devices.speechrecognizer.SpeechRecognizerComponent
    public void getAlexaListeningState(SpeechRecognizerCallback<AudioProcessingNotification> speechRecognizerCallback) throws AlexaException {
        new QueryListeningStateTask(this, speechRecognizerCallback, null).execute(new Void[0]);
    }

    @Override // com.amazon.alexa.devices.speechrecognizer.SpeechRecognizerComponent
    public boolean registerUtteranceProvider(UtteranceProvider utteranceProvider) throws AlexaException {
        if ($assertionsDisabled || this.mAudioComponent != null) {
            try {
                return doRegisterUtteranceProvider(utteranceProvider);
            } catch (RemoteException e) {
                throw new AlexaException(e);
            }
        }
        throw new AssertionError();
    }

    @Override // com.amazon.alexa.devices.speechrecognizer.SpeechRecognizerComponent
    public OutputStream replaceAudioOutputStream(@NonNull com.amazon.alexa.devices.speechrecognizer.AudioFormat audioFormat) throws AlexaException {
        cleanUpAudioOutputStream();
        this.mAudioOutputStream = createAudioOutputStreamInternal(audioFormat);
        return this.mAudioOutputStream;
    }

    @BaseProxyClientImpl.MethodIntercept(clazz = AudioEventListener.class, name = "startUtterance")
    public String startUtterance(IWakeWordDetectionListener iWakeWordDetectionListener, AudioEvent audioEvent) throws AlexaException {
        try {
            try {
                this.mAccess.acquire();
                if (this.mUtteranceSession != null) {
                    if (this.mAutoCloseSession) {
                        Log.w(TAG, "AutoClosing existing session after second caller called startUtterance a second time");
                        stopUtterance(iWakeWordDetectionListener, this.mUtteranceSession.utteranceId, AudioEventListener.ABORT_REASON.USER_ABORT);
                    } else {
                        this.mAutoCloseSession = true;
                        throw new AlexaException("Unable to start a new Utterance because one is already in progress. Please call stopUtterance first");
                    }
                }
                this.mAutoCloseSession = false;
                if (this.mAudioOutputStream != null) {
                    this.mUtteranceSession = new UtteranceSession(audioEvent);
                    this.mAccess.release();
                    try {
                        this.mUtteranceSession.utteranceId = iWakeWordDetectionListener.onWakeWordDetected(audioEvent.getWakeword(), audioEvent.getToken(), audioEvent.getPreRollByteIndex(), audioEvent.getBeginWakewordIndex(), audioEvent.getEndWakewordIndex(), audioEvent.getConfidence(), this.mUtteranceSession.audioInputStream.getReader(), audioEvent.getMetadataMap(), audioEvent.getInitiator() != null ? audioEvent.getInitiator().name() : null);
                        return this.mUtteranceSession.utteranceId;
                    } catch (RemoteException | IOException e) {
                        try {
                            try {
                                this.mAccess.acquire();
                                this.mUtteranceSession.close();
                                throw new AlexaException(e);
                            } catch (Throwable th) {
                                this.mUtteranceSession = null;
                                throw th;
                            }
                        } catch (IOException | InterruptedException e2) {
                            Log.e(TAG, "Unexpected error", e2);
                            throw new AlexaException(e2);
                        }
                    }
                }
                throw new AlexaException("No OutputStream created for this SpeechRecognizerComponent. Please call SpeechRecognizerComponent.createAudioOutputStream first and use it to write the audio data");
            } catch (InterruptedException e3) {
                throw new AlexaException(e3);
            }
        } finally {
            this.mAccess.release();
        }
    }

    @BaseProxyClientImpl.MethodIntercept(clazz = AudioEventListener.class, name = "stopUtterance")
    public void stopUtterance(IWakeWordDetectionListener iWakeWordDetectionListener, String str, AudioEventListener.ABORT_REASON abort_reason) throws AlexaException {
        GeneratedOutlineSupport1.outline158("Calling stopUtterance with id: ", str);
        try {
            try {
                this.mAccess.acquire();
                if (this.mUtteranceSession != null && this.mUtteranceSession.utteranceId != null) {
                    if (this.mUtteranceSession.utteranceId.equals(str)) {
                        iWakeWordDetectionListener.onStop(str, abort_reason.getReasonCode());
                        try {
                            try {
                                if (this.mUtteranceSession != null) {
                                    this.mUtteranceSession.close();
                                }
                                return;
                            } catch (IOException e) {
                                throw new AlexaException(e);
                            }
                        } finally {
                        }
                    }
                    throw new AlexaException("stopUtterance: invalid utteranceId passed");
                }
                throw new AlexaException("stopUtterance: No utterance session currently in progress");
            } catch (Throwable th) {
                try {
                    try {
                        if (this.mUtteranceSession != null) {
                            this.mUtteranceSession.close();
                        }
                        throw th;
                    } catch (IOException e2) {
                        throw new AlexaException(e2);
                    }
                } finally {
                }
            }
        } catch (RemoteException | InterruptedException e3) {
            Log.e(TAG, "Unexpected error", e3);
            throw new AlexaException(e3);
        }
    }
}
