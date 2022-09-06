package com.amazon.alexa.accessory.capabilities.speech;

import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.capabilities.metrics.SpeechProcessingMetricsReporter;
import com.amazon.alexa.accessory.capabilities.speech.SpeechSettings;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableStream;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.protocol.Speech;
import com.amazon.alexa.accessory.repositories.device.DeviceFeature;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.amazon.alexa.accessory.streams.voice.VoiceStream;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class SpeechRecognitionTask implements TaskManager.Task, SpeechSettings.Callback {
    private final AccessoryIdentifierProvider accessoryIdentifierProvider;
    private final Callback callback;
    private final AccessoryDescriptor descriptor;
    private final Device.DeviceInformation deviceInformation;
    private final Speech.Dialog dialog;
    private final Firmware.FirmwareInformation firmwareInformation;
    private final Handler handler;
    private final Speech.SpeechInitiator initiator;
    private boolean isExpectSpeechRequest;
    private final String locale;
    private Disposable provideSpeechDisposable;
    private final SpeechRecognizer recognizer;
    private boolean released;
    private final SessionIdentifierProvider sessionIdentifierProvider;
    private final Speech.SpeechSettings settings;
    private final Set<DeviceFeature> speechRelatedFeatures;
    private SpeechSession speechSession;
    private final ControlStream stream;
    private final boolean suppressEndpointEarcon;
    private final boolean suppressStartEarcon;
    private VoiceStream voiceStream;

    /* loaded from: classes.dex */
    public interface Callback {
        void onCancelled(SpeechRecognitionTask speechRecognitionTask);

        void onComplete(SpeechRecognitionTask speechRecognitionTask);

        void onDenied(SpeechRecognitionTask speechRecognitionTask);

        void onError(SpeechRecognitionTask speechRecognitionTask, Throwable th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface Recognizer {
        SpeechSession recognize(SpeechSettings speechSettings);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class SpeechMessageSettings implements SpeechSettings {
        private final AccessoryIdentifierProvider accessoryIdentifierProvider;
        private final SpeechSettings.Callback callback;
        private final Device.DeviceInformation deviceInformation;
        private final int dialogId;
        private final Firmware.FirmwareInformation firmwareInformation;
        private final Speech.SpeechInitiator initiator;
        private final String locale;
        private final SessionIdentifierProvider sessionIdentifierProvider;
        private final Speech.SpeechSettings settings;
        private final Set<DeviceFeature> speechRelatedFeatures;
        private final boolean suppressEndpointEarcon;
        private final boolean suppressStartEarcon;

        public SpeechMessageSettings(Speech.SpeechInitiator speechInitiator, Speech.SpeechSettings speechSettings, Device.DeviceInformation deviceInformation, Set<DeviceFeature> set, Firmware.FirmwareInformation firmwareInformation, int i, SpeechSettings.Callback callback, boolean z, boolean z2, SessionIdentifierProvider sessionIdentifierProvider, AccessoryIdentifierProvider accessoryIdentifierProvider, String str) {
            Preconditions.notNull(speechInitiator, "initiator");
            Preconditions.notNull(speechSettings, "settings");
            Preconditions.notNull(callback, "callback");
            Preconditions.notNull(deviceInformation, "deviceInformation");
            Preconditions.notNull(set, "speechRelatedFeatures");
            Preconditions.notNull(sessionIdentifierProvider, "sessionIdentifierProvider");
            Preconditions.notNull(accessoryIdentifierProvider, "accessoryIdentifierProvider");
            this.initiator = speechInitiator;
            this.settings = speechSettings;
            this.callback = callback;
            this.deviceInformation = deviceInformation;
            this.speechRelatedFeatures = set;
            this.firmwareInformation = firmwareInformation;
            this.dialogId = i;
            this.suppressStartEarcon = z;
            this.suppressEndpointEarcon = z2;
            this.sessionIdentifierProvider = sessionIdentifierProvider;
            this.accessoryIdentifierProvider = accessoryIdentifierProvider;
            this.locale = str;
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public AccessoryIdentifierProvider getAccessoryIdentifierProvider() {
            return this.accessoryIdentifierProvider;
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public Speech.AudioFormat getAudioFormat() {
            return this.settings.getAudioFormat();
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public Speech.AudioProfile getAudioProfile() {
            return this.settings.getAudioProfile();
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public SpeechSettings.Callback getCallback() {
            return this.callback;
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public String getDeviceFirmwareVersion() {
            Firmware.FirmwareInformation firmwareInformation = this.firmwareInformation;
            if (firmwareInformation != null) {
                return firmwareInformation.getVersionName();
            }
            return null;
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public String getDeviceName() {
            return this.deviceInformation.getName();
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public String getDeviceSerialNumber() {
            return this.deviceInformation.getSerialNumber();
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public String getDeviceType() {
            return this.deviceInformation.getDeviceType();
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public int getDialogId() {
            return this.dialogId;
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public Speech.SpeechInitiator getInitiator() {
            return this.initiator;
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public String getLocale() {
            String str = this.locale;
            return str != null ? str : "unknown";
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public SessionIdentifierProvider getSessionIdentifierProvider() {
            return this.sessionIdentifierProvider;
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public Set<DeviceFeature> getSpeechRelatedFeatures() {
            return this.speechRelatedFeatures;
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public boolean getSuppressEndpointEarcon() {
            return this.suppressEndpointEarcon;
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings
        public boolean getSuppressStartEarcon() {
            return this.suppressStartEarcon;
        }
    }

    public SpeechRecognitionTask(AccessoryDescriptor accessoryDescriptor, ControlStream controlStream, SpeechRecognizer speechRecognizer, Speech.SpeechInitiator speechInitiator, Speech.SpeechSettings speechSettings, Callback callback, Device.DeviceInformation deviceInformation, Set<DeviceFeature> set, Firmware.FirmwareInformation firmwareInformation, Speech.Dialog dialog, boolean z, boolean z2, SessionIdentifierProvider sessionIdentifierProvider, AccessoryIdentifierProvider accessoryIdentifierProvider, String str) {
        this(accessoryDescriptor, controlStream, speechRecognizer, speechInitiator, speechSettings, callback, deviceInformation, set, firmwareInformation, dialog, z, z2, sessionIdentifierProvider, accessoryIdentifierProvider, str, false);
    }

    private void abortTask() {
        Preconditions.mainThread();
        if (this.released) {
            return;
        }
        Logger.d("SpeechRecognitionTask (dialogId=%d) aborting", Integer.valueOf(this.dialog.getId()));
        SpeechSession speechSession = this.speechSession;
        if (speechSession != null) {
            speechSession.abort();
            this.speechSession = null;
        }
        releaseTask();
    }

    private void initiateSpeech() {
        Preconditions.mainThread();
        Logger.d("SpeechRecognitionTask initiateSpeech. released=%b", Boolean.valueOf(this.released));
        if (this.released) {
            return;
        }
        ObservableUtils.dispose(this.provideSpeechDisposable);
        this.provideSpeechDisposable = ObservableStream.dispatchSingleSuccessOnErrorResponse(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.PROVIDE_SPEECH).setProvideSpeech(Speech.ProvideSpeech.newBuilder().setDialog(this.dialog)).mo10084build())).filter(new Predicate() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionTask$bKx2CnNrGJyLozzMRZrP1OhGJwk
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return SpeechRecognitionTask.this.lambda$initiateSpeech$6$SpeechRecognitionTask((Accessories.Response) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionTask$C2nZ4X0jDhJUwCIW4BI1ddcSozc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return SpeechRecognitionTask.this.lambda$initiateSpeech$7$SpeechRecognitionTask((Accessories.Response) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionTask$BtJ60rsS1ULKF1c3lponaJ86oGw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeechRecognitionTask.this.lambda$initiateSpeech$8$SpeechRecognitionTask((Speech.SpeechSettings) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionTask$dKKTS9ktkWGpE_f3XuILgcetPRY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeechRecognitionTask.this.lambda$initiateSpeech$9$SpeechRecognitionTask((Throwable) obj);
            }
        });
    }

    private void recognizeSpeech(Recognizer recognizer, Speech.SpeechInitiator speechInitiator, Speech.SpeechSettings speechSettings, Device.DeviceInformation deviceInformation, Set<DeviceFeature> set) {
        Preconditions.mainThread();
        if (!this.released) {
            releaseSpeechSession();
            this.speechSession = recognizer.recognize(new SpeechMessageSettings(speechInitiator, speechSettings, deviceInformation, set, this.firmwareInformation, this.dialog.getId(), this, this.suppressStartEarcon, this.suppressEndpointEarcon, this.sessionIdentifierProvider, this.accessoryIdentifierProvider, this.locale));
            SpeechSession speechSession = this.speechSession;
            if (speechSession == null) {
                setIdleSpeechState();
                abortTask();
                this.callback.onError(this, new UnsupportedOperationException("Speech recognition session cannot be opened"));
                return;
            }
            this.voiceStream = new VoiceStream(speechSession.getSink(), new VoiceStream.Callback() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionTask$9nCuuKebMXE4c2wYuSw3JO4gDoQ
                @Override // com.amazon.alexa.accessory.streams.voice.VoiceStream.Callback
                public final void onError(Throwable th) {
                    SpeechRecognitionTask.this.lambda$recognizeSpeech$1$SpeechRecognitionTask(th);
                }
            });
            this.descriptor.add(this.voiceStream);
            return;
        }
        throw new IllegalStateException("Released!");
    }

    private void recordFailureMetric(SpeechProcessingMetricsReporter.FailureType failureType) {
        recordFailureMetric(failureType, null);
    }

    private void releaseSpeechSession() {
        Preconditions.mainThread();
        Logger.d("SpeechRecognitionTask (dialogId=%d) releasing speechSession resources", Integer.valueOf(this.dialog.getId()));
        SpeechSession speechSession = this.speechSession;
        if (speechSession != null) {
            speechSession.release();
            this.speechSession = null;
        }
        releaseVoiceStream();
    }

    private void releaseTask() {
        Preconditions.mainThread();
        if (this.released) {
            return;
        }
        Logger.d("SpeechRecognitionTask (dialogId=%d) releasing", Integer.valueOf(this.dialog.getId()));
        this.released = true;
        ObservableUtils.dispose(this.provideSpeechDisposable);
        releaseSpeechSession();
    }

    private void releaseVoiceStream() {
        Preconditions.mainThread();
        VoiceStream voiceStream = this.voiceStream;
        if (voiceStream != null) {
            voiceStream.close();
            this.descriptor.remove(this.voiceStream);
            this.voiceStream = null;
        }
    }

    private void reportEndpointSpeech() {
        this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.ENDPOINT_SPEECH).setEndpointSpeech(Speech.EndpointSpeech.newBuilder().setDialog(this.dialog)).mo10084build()));
    }

    private void setIdleSpeechState() {
        Preconditions.mainThread();
        if (this.released) {
            return;
        }
        this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.NOTIFY_SPEECH_STATE).setNotifySpeechState(Speech.NotifySpeechState.newBuilder().setState(Speech.SpeechState.IDLE)).mo10084build()));
    }

    public boolean endpoint() {
        Preconditions.mainThread();
        if (this.voiceStream != null && !this.released) {
            this.speechSession.endpointSpeech();
            return true;
        }
        Logger.e("Attempted to endpoint a speech task which isn't receiving voice data.");
        return false;
    }

    public Speech.Dialog getDialog() {
        return this.dialog;
    }

    public /* synthetic */ boolean lambda$initiateSpeech$6$SpeechRecognitionTask(Accessories.Response response) throws Throwable {
        return response.getSpeechProvider().getDialog().equals(this.dialog);
    }

    public /* synthetic */ Speech.SpeechSettings lambda$initiateSpeech$7$SpeechRecognitionTask(Accessories.Response response) throws Throwable {
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
            return response.getSpeechProvider().getSpeechSettings();
        }
        recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.EXPECT_SPEECH_FW_RESPONSE_FAILURE, response.getErrorCode());
        throw new IllegalStateException(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("Unexpected error code in initiateSpeech ")));
    }

    public /* synthetic */ void lambda$initiateSpeech$8$SpeechRecognitionTask(Speech.SpeechSettings speechSettings) throws Throwable {
        SpeechRecognizer speechRecognizer = this.recognizer;
        speechRecognizer.getClass();
        recognizeSpeech(new $$Lambda$9b0YIgyCjpw6uNo4NbsQrhFdyE(speechRecognizer), this.initiator, speechSettings, this.deviceInformation, this.speechRelatedFeatures);
    }

    public /* synthetic */ void lambda$initiateSpeech$9$SpeechRecognitionTask(Throwable th) throws Throwable {
        Logger.e("Accessory failed to initiate speech", th);
        setIdleSpeechState();
        abortTask();
        recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.SPEECH_RECOGNITION_TASK_ON_EXPECT_SPEECH_ERROR);
        this.callback.onError(this, th);
    }

    public /* synthetic */ void lambda$null$0$SpeechRecognitionTask(Throwable th) {
        Logger.e("SpeechRecognitionTask voice stream failed");
        setIdleSpeechState();
        abortTask();
        recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.SPEECH_RECOGNITION_TASK_VOICE_STREAM_ERROR);
        this.callback.onError(this, th);
    }

    public /* synthetic */ boolean lambda$onSpeechRequest$2$SpeechRecognitionTask(Accessories.Response response) throws Throwable {
        return response.getSpeechProvider().getDialog().equals(this.dialog);
    }

    public /* synthetic */ Speech.SpeechSettings lambda$onSpeechRequest$3$SpeechRecognitionTask(Accessories.Response response) throws Throwable {
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
            return response.getSpeechProvider().getSpeechSettings();
        }
        recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.PROVIDE_SPEECH_FW_RESPONSE_FAILURE, response.getErrorCode());
        throw new IllegalStateException(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("Unexpected error code ")));
    }

    public /* synthetic */ void lambda$onSpeechRequest$4$SpeechRecognitionTask(final SpeechSettings.SpeechRequest speechRequest, Speech.SpeechSettings speechSettings) throws Throwable {
        speechRequest.getClass();
        recognizeSpeech(new Recognizer() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$4Pn5wRWxC_D2JVB2W7WDT7HahE0
            @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechRecognitionTask.Recognizer
            public final SpeechSession recognize(SpeechSettings speechSettings2) {
                return SpeechSettings.SpeechRequest.this.proceed(speechSettings2);
            }
        }, Speech.SpeechInitiator.getDefaultInstance(), speechSettings, this.deviceInformation, this.speechRelatedFeatures);
    }

    public /* synthetic */ void lambda$onSpeechRequest$5$SpeechRecognitionTask(SpeechSettings.SpeechRequest speechRequest, Throwable th) throws Throwable {
        Logger.e("Accessory failed to provide speech", th);
        speechRequest.cancel();
        setIdleSpeechState();
        abortTask();
        recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.SPEECH_RECOGNITION_TASK_ON_SPEECH_REQUEST_ERROR);
        this.callback.onError(this, th);
    }

    public /* synthetic */ void lambda$recognizeSpeech$1$SpeechRecognitionTask(final Throwable th) {
        this.handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionTask$MCuZWELBhPbVzIOgPVcU9RTVCh8
            @Override // java.lang.Runnable
            public final void run() {
                SpeechRecognitionTask.this.lambda$null$0$SpeechRecognitionTask(th);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings.Callback
    public void onSpeechCompleted() {
        Preconditions.mainThread();
        Logger.d("SpeechRecognitionTask onSpeechCompleted. released=%b", Boolean.valueOf(this.released));
        if (this.released) {
            return;
        }
        setIdleSpeechState();
        releaseTask();
        this.callback.onComplete(this);
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings.Callback
    public void onSpeechProcessingStarted() {
        Preconditions.mainThread();
        Logger.d("SpeechRecognitionTask onSpeechProcessingStarted. released=%b", Boolean.valueOf(this.released));
        if (this.released) {
            return;
        }
        this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.NOTIFY_SPEECH_STATE).setNotifySpeechState(Speech.NotifySpeechState.newBuilder().setState(Speech.SpeechState.PROCESSING)).mo10084build()));
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings.Callback
    public void onSpeechRecognitionCancelled() {
        Preconditions.mainThread();
        Logger.d("SpeechRecognitionTask onSpeechRecognitionCancelled. released=%b", Boolean.valueOf(this.released));
        if (this.released) {
            return;
        }
        setIdleSpeechState();
        abortTask();
        this.callback.onCancelled(this);
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings.Callback
    public void onSpeechRecognitionDenied() {
        Preconditions.mainThread();
        Logger.d("SpeechRecognitionTask onSpeechRecognitionDenied. released=%b", Boolean.valueOf(this.released));
        if (this.released) {
            return;
        }
        setIdleSpeechState();
        abortTask();
        this.callback.onDenied(this);
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings.Callback
    public void onSpeechRecognitionFailed(Throwable th) {
        Preconditions.mainThread();
        Logger.d("SpeechRecognitionTask onSpeechRecognitionFailed. released=%b", Boolean.valueOf(this.released));
        if (this.released) {
            return;
        }
        setIdleSpeechState();
        abortTask();
        recordFailureMetric(SpeechProcessingMetricsReporter.FailureType.SPEECH_RECOGNITION_TASK_ON_SPEECH_RECOGNITION_FAILED_ERROR);
        this.callback.onError(this, th);
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings.Callback
    public void onSpeechRecognitionFinished() {
        Preconditions.mainThread();
        Logger.d("SpeechRecognitionTask onSpeechRecognitionFinished. released=%b", Boolean.valueOf(this.released));
        if (this.released) {
            return;
        }
        releaseVoiceStream();
        reportEndpointSpeech();
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings.Callback
    public void onSpeechRecognitionStarted() {
        Preconditions.mainThread();
        Logger.d("SpeechRecognitionTask onSpeechRecognitionStarted. released=%b", Boolean.valueOf(this.released));
        if (this.released) {
            return;
        }
        this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.NOTIFY_SPEECH_STATE).setNotifySpeechState(Speech.NotifySpeechState.newBuilder().setState(Speech.SpeechState.LISTENING)).mo10084build()));
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings.Callback
    public void onSpeechRequest(final SpeechSettings.SpeechRequest speechRequest) {
        Preconditions.mainThread();
        Logger.d("SpeechRecognitionTask onSpeechRequest. released=%b", Boolean.valueOf(this.released));
        if (this.released) {
            return;
        }
        ObservableUtils.dispose(this.provideSpeechDisposable);
        this.provideSpeechDisposable = ObservableStream.dispatchSingleSuccessOnErrorResponse(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.PROVIDE_SPEECH).setProvideSpeech(Speech.ProvideSpeech.newBuilder().setDialog(this.dialog)).mo10084build())).filter(new Predicate() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionTask$_7KJDV-TMYLw5kLdLXR7s8HkYRE
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return SpeechRecognitionTask.this.lambda$onSpeechRequest$2$SpeechRecognitionTask((Accessories.Response) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionTask$ywENwxYr6DoF1vhfYHG291zG4Bw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return SpeechRecognitionTask.this.lambda$onSpeechRequest$3$SpeechRecognitionTask((Accessories.Response) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionTask$625ix8Y0K-iCQAX_9sUerwSUVD8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeechRecognitionTask.this.lambda$onSpeechRequest$4$SpeechRecognitionTask(speechRequest, (Speech.SpeechSettings) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionTask$pwC2wyG7L89t28OuyaUmnPvSebI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeechRecognitionTask.this.lambda$onSpeechRequest$5$SpeechRecognitionTask(speechRequest, (Throwable) obj);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSettings.Callback
    public void onSpeechStarted() {
        Preconditions.mainThread();
        Logger.d("SpeechRecognitionTask onSpeechStarted. released=%b", Boolean.valueOf(this.released));
        if (this.released) {
            return;
        }
        this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.NOTIFY_SPEECH_STATE).setNotifySpeechState(Speech.NotifySpeechState.newBuilder().setState(Speech.SpeechState.SPEAKING)).mo10084build()));
    }

    @Override // com.amazon.alexa.accessory.TaskManager.Task
    public void onStateChanged(int i, int i2) {
        if (i2 != 1 && i2 != 2) {
            if (i2 == 3 && this.isExpectSpeechRequest) {
                initiateSpeech();
                return;
            } else if (i2 == 3) {
                SpeechRecognizer speechRecognizer = this.recognizer;
                speechRecognizer.getClass();
                recognizeSpeech(new $$Lambda$9b0YIgyCjpw6uNo4NbsQrhFdyE(speechRecognizer), this.initiator, this.settings, this.deviceInformation, this.speechRelatedFeatures);
                return;
            } else if (i2 != 4 || this.released) {
                return;
            } else {
                setIdleSpeechState();
                abortTask();
                this.callback.onCancelled(this);
                return;
            }
        }
        throw new IllegalStateException("Speech recognition task must run with at least urgent priority!");
    }

    public void releaseForBargeIn() {
        Preconditions.mainThread();
        if (this.released) {
            return;
        }
        Logger.d("SpeechRecognitionTask (dialogId=%d) releasing due to barge in", Integer.valueOf(this.dialog.getId()));
        releaseTask();
        this.callback.onCancelled(this);
    }

    public String toString() {
        return "SpeechRecognitionTask";
    }

    public SpeechRecognitionTask(AccessoryDescriptor accessoryDescriptor, ControlStream controlStream, SpeechRecognizer speechRecognizer, Speech.SpeechInitiator speechInitiator, Speech.SpeechSettings speechSettings, Callback callback, Device.DeviceInformation deviceInformation, Set<DeviceFeature> set, Firmware.FirmwareInformation firmwareInformation, Speech.Dialog dialog, boolean z, boolean z2, SessionIdentifierProvider sessionIdentifierProvider, AccessoryIdentifierProvider accessoryIdentifierProvider, String str, boolean z3) {
        Preconditions.notNull(accessoryDescriptor, "descriptor");
        Preconditions.notNull(speechRecognizer, "recognizer");
        Preconditions.notNull(speechInitiator, "initiator");
        Preconditions.notNull(speechSettings, "settings");
        Preconditions.notNull(callback, "callback");
        Preconditions.notNull(controlStream, "stream");
        Preconditions.notNull(deviceInformation, "deviceInformation");
        Preconditions.notNull(set, "speechRelatedFeatures");
        Preconditions.notNull(sessionIdentifierProvider, "sessionIdentifierProvider");
        Preconditions.notNull(accessoryIdentifierProvider, "accessoryIdentifierProvider");
        this.stream = controlStream;
        this.descriptor = accessoryDescriptor;
        this.recognizer = speechRecognizer;
        this.initiator = speechInitiator;
        this.settings = speechSettings;
        this.callback = callback;
        this.deviceInformation = deviceInformation;
        this.speechRelatedFeatures = set;
        this.firmwareInformation = firmwareInformation;
        this.dialog = dialog;
        this.suppressStartEarcon = z;
        this.suppressEndpointEarcon = z2;
        this.sessionIdentifierProvider = sessionIdentifierProvider;
        this.accessoryIdentifierProvider = accessoryIdentifierProvider;
        this.locale = str;
        this.handler = new Handler(Looper.myLooper());
        this.isExpectSpeechRequest = z3;
    }

    private void recordFailureMetric(SpeechProcessingMetricsReporter.FailureType failureType, Object obj) {
        Device.DeviceInformation deviceInformation = this.deviceInformation;
        Integer num = null;
        String deviceType = deviceInformation != null ? deviceInformation.getDeviceType() : null;
        Firmware.FirmwareInformation firmwareInformation = this.firmwareInformation;
        if (firmwareInformation != null) {
            num = Integer.valueOf(firmwareInformation.getVersion());
        }
        SpeechProcessingMetricsReporter.reportFailure(failureType, deviceType, num, obj);
    }
}
