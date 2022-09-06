package com.amazon.alexa.accessory.capabilities.speech;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.capabilities.metrics.SpeechProcessingMetricsReporter;
import com.amazon.alexa.accessory.capabilities.speech.SpeechRecognitionTask;
import com.amazon.alexa.accessory.internal.util.CurrentTimeMillisProvider;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.protocol.Speech;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessory.repositories.device.DeviceFeature;
import com.amazon.alexa.accessory.repositories.device.Features;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2;
import com.amazon.alexa.accessory.repositories.speech.SpeechProducer;
import com.amazon.alexa.accessory.repositories.system.SystemRepository;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes.dex */
public final class SpeechRecognitionCapability extends AccessoryCapability {
    private static final String TAG = "SpeechRecognitionCapability:";
    private final AccessoryIdentifierProvider accessoryIdentifierProvider;
    private SpeechProducerActionHandler actionHandler;
    private ControlStream controlStream;
    private final CurrentTimeMillisProvider currentTimeMillisProvider;
    private AccessoryDescriptor descriptor;
    private Disposable deviceDisposable;
    private Disposable deviceFeatureDisposable;
    private Device.DeviceInformation deviceInformation;
    private final DeviceRepositoryV2 deviceRepository;
    private FeatureChecker featureChecker;
    private Disposable firmwareDisposable;
    private Firmware.FirmwareInformation firmwareInformation;
    private final FirmwareRepositoryV2 firmwareRepository;
    private Date initializationStart;
    private Speech.Dialog lastKnownDialog;
    private String locale;
    private Disposable localeDisposable;
    private final SessionIdentifierProvider sessionIdentifierProvider;
    private SpeechProducer speechProducer;
    private final SpeechRecognizer speechRecognizer;
    @VisibleForTesting
    final Set<DeviceFeature> speechRelatedFeatures;
    private final SystemRepository systemRepository;
    private SpeechRecognitionTask task;
    private final TaskManager taskManager;

    /* loaded from: classes.dex */
    private final class SpeechProducerActionHandler implements SpeechProducer.ActionHandler {
        private SpeechProducerActionHandler() {
        }

        @Override // com.amazon.alexa.accessory.repositories.speech.SpeechProducer.ActionHandler
        public void handleInitiateSpeech() {
            Logger.d("%s SpeechProducerActionHandler - handleInitiateSpeech()", SpeechRecognitionCapability.TAG);
            SpeechRecognitionCapability.this.initiateSpeech();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SpeechRecognitionCallback implements SpeechRecognitionTask.Callback {
        private final ControlStream stream;
        private final TaskManager taskManager;

        public SpeechRecognitionCallback(TaskManager taskManager, ControlStream controlStream) {
            this.taskManager = taskManager;
            this.stream = controlStream;
        }

        private boolean taskDisposedByCapability(SpeechRecognitionTask speechRecognitionTask) {
            SpeechRecognitionTask speechRecognitionTask2 = SpeechRecognitionCapability.this.task;
            return speechRecognitionTask2 == null || speechRecognitionTask2.getDialog().getId() != speechRecognitionTask.getDialog().getId();
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechRecognitionTask.Callback
        public void onCancelled(SpeechRecognitionTask speechRecognitionTask) {
            int i = 0;
            Logger.d("SpeechRecognitionCapability: task with dialogId=%d cancelled", Integer.valueOf(speechRecognitionTask.getDialog().getId()));
            this.taskManager.dispose(speechRecognitionTask);
            if (taskDisposedByCapability(speechRecognitionTask)) {
                return;
            }
            if (SpeechRecognitionCapability.this.firmwareInformation != null) {
                i = SpeechRecognitionCapability.this.firmwareInformation.getVersion();
            }
            SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.ERROR_CODE, SpeechRecognitionCapability.this.deviceInformation.getDeviceType(), Integer.valueOf(i), Common.ErrorCode.USER_CANCELLED);
            this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.STOP_SPEECH).setStopSpeech(Speech.StopSpeech.newBuilder().setErrorCode(Common.ErrorCode.USER_CANCELLED).setDialog(speechRecognitionTask.getDialog())).mo10084build()));
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechRecognitionTask.Callback
        public void onComplete(SpeechRecognitionTask speechRecognitionTask) {
            Logger.d("SpeechRecognitionCapability: task with dialogId=%d completed", Integer.valueOf(speechRecognitionTask.getDialog().getId()));
            this.taskManager.dispose(speechRecognitionTask);
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechRecognitionTask.Callback
        public void onDenied(SpeechRecognitionTask speechRecognitionTask) {
            Logger.d("SpeechRecognitionCapability: task with dialogId=%d denied", Integer.valueOf(speechRecognitionTask.getDialog().getId()));
            this.taskManager.dispose(speechRecognitionTask);
            if (taskDisposedByCapability(speechRecognitionTask)) {
                return;
            }
            this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.STOP_SPEECH).setStopSpeech(Speech.StopSpeech.newBuilder().setErrorCode(Common.ErrorCode.BUSY).setDialog(speechRecognitionTask.getDialog())).mo10084build()));
        }

        @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechRecognitionTask.Callback
        public void onError(SpeechRecognitionTask speechRecognitionTask, Throwable th) {
            int i = 0;
            Logger.e("SpeechRecognitionCapability: task with dialogId=%d failed", th, Integer.valueOf(speechRecognitionTask.getDialog().getId()));
            this.taskManager.dispose(speechRecognitionTask);
            if (taskDisposedByCapability(speechRecognitionTask)) {
                return;
            }
            Common.ErrorCode errorCode = Common.ErrorCode.INTERNAL;
            if (th instanceof UnsupportedOperationException) {
                errorCode = Common.ErrorCode.UNSUPPORTED;
            }
            if (SpeechRecognitionCapability.this.firmwareInformation != null) {
                i = SpeechRecognitionCapability.this.firmwareInformation.getVersion();
            }
            SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.ERROR_CODE, SpeechRecognitionCapability.this.deviceInformation.getDeviceType(), Integer.valueOf(i), errorCode);
            this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.STOP_SPEECH).setStopSpeech(Speech.StopSpeech.newBuilder().setErrorCode(errorCode).setDialog(speechRecognitionTask.getDialog())).mo10084build()));
        }
    }

    public SpeechRecognitionCapability(TaskManager taskManager, SpeechRecognizer speechRecognizer, DeviceRepositoryV2 deviceRepositoryV2, FirmwareRepositoryV2 firmwareRepositoryV2, SystemRepository systemRepository, SessionIdentifierProvider sessionIdentifierProvider, AccessoryIdentifierProvider accessoryIdentifierProvider, SpeechProducer speechProducer, FeatureChecker featureChecker) {
        this(taskManager, speechRecognizer, deviceRepositoryV2, firmwareRepositoryV2, systemRepository, sessionIdentifierProvider, accessoryIdentifierProvider, new CurrentTimeMillisProvider(), speechProducer, featureChecker);
    }

    private ControlMessageHandler<Speech.EndpointSpeech> getEndpointSpeechHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionCapability$FrfyhiwpMqPgx3WPnZsSPpkBERM
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                SpeechRecognitionCapability.this.lambda$getEndpointSpeechHandler$16$SpeechRecognitionCapability(controlStream, command, (Speech.EndpointSpeech) obj);
            }
        };
    }

    private ControlMessageHandler<Speech.StartSpeech> getStartSpeechHandler(final AccessoryDescriptor accessoryDescriptor) {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionCapability$YZF436ubBQ8t0d3IRHxWNaaDrMU
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                SpeechRecognitionCapability.this.lambda$getStartSpeechHandler$14$SpeechRecognitionCapability(accessoryDescriptor, controlStream, command, (Speech.StartSpeech) obj);
            }
        };
    }

    private ControlMessageHandler<Speech.StopSpeech> getStopSpeechHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionCapability$PLl4iIRwraH9M8XS4erqf4RGqHU
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                SpeechRecognitionCapability.this.lambda$getStopSpeechHandler$15$SpeechRecognitionCapability(controlStream, command, (Speech.StopSpeech) obj);
            }
        };
    }

    private void initializeSpeechRelatedFeatures() {
        this.deviceFeatureDisposable = this.deviceRepository.queryDeviceFeatures().toObservable().map($$Lambda$s_aCg1APpSfLLu8CrMBLYHtmUqU.INSTANCE).flatMap($$Lambda$tNLLyz36wpjmL1kezURjOHIEA.INSTANCE).filter($$Lambda$SpeechRecognitionCapability$RM4bUXOojo3OC90j9HF8ux3AZTE.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionCapability$cNYNBfSD5w-BC_zQFS_8EYxTZXM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeechRecognitionCapability.this.lambda$initializeSpeechRelatedFeatures$12$SpeechRecognitionCapability((DeviceFeature) obj);
            }
        }, $$Lambda$SpeechRecognitionCapability$lQBPMrtswC00jiNxeG6ayUqBLPs.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initiateSpeech() {
        Speech.Dialog defaultInstance;
        SpeechRecognitionTask speechRecognitionTask = this.task;
        this.task = null;
        if (speechRecognitionTask != null) {
            speechRecognitionTask.releaseForBargeIn();
        }
        Speech.SpeechInitiator defaultInstance2 = Speech.SpeechInitiator.getDefaultInstance();
        Speech.SpeechSettings defaultInstance3 = Speech.SpeechSettings.getDefaultInstance();
        Speech.Dialog dialog = this.lastKnownDialog;
        if (dialog != null) {
            Logger.d("%s using dialogId=%d from lastKnownDialog", TAG, Integer.valueOf(dialog.getId()));
            defaultInstance = Speech.Dialog.newBuilder().setId(this.lastKnownDialog.getId()).mo10084build();
        } else {
            defaultInstance = Speech.Dialog.getDefaultInstance();
            Logger.d("%s lastKnownDialog is null. Using default Dialog object with dialogId=%d.", TAG, Integer.valueOf(defaultInstance.getId()));
        }
        AccessoryDescriptor accessoryDescriptor = this.descriptor;
        ControlStream controlStream = this.controlStream;
        this.task = new SpeechRecognitionTask(accessoryDescriptor, controlStream, this.speechRecognizer, defaultInstance2, defaultInstance3, new SpeechRecognitionCallback(this.taskManager, controlStream), this.deviceInformation, this.speechRelatedFeatures, this.firmwareInformation, defaultInstance, false, false, this.sessionIdentifierProvider, this.accessoryIdentifierProvider, this.locale, true);
        Logger.d("%s initiateSpeech result=%b", TAG, Boolean.valueOf(this.taskManager.schedule(this.task, 9)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$initializeSpeechRelatedFeatures$11(DeviceFeature deviceFeature) throws Throwable {
        return deviceFeature.getId() == Features.DISPLAY_CAPTION_FEATURE.value() || deviceFeature.getId() == Features.DISPLAY_CARD_FEATURE.value() || deviceFeature.getId() == Features.SUPPRESSING_SPEECH_RESPONSE_FEATURE.value();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$0(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$4(Firmware.FirmwareInformation firmwareInformation, Firmware.FirmwareInformation firmwareInformation2) {
        return firmwareInformation.getDeviceId() - firmwareInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Device.DeviceInformation lambda$onInitialize$1(Set set) throws Throwable {
        return (Device.DeviceInformation) Collections.max(set, $$Lambda$SpeechRecognitionCapability$OeElj1n3wUCQB0wgdkfk9sQT2Y.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Firmware.FirmwareInformation lambda$onInitialize$5(Set set) throws Throwable {
        return (Firmware.FirmwareInformation) Collections.max(set, $$Lambda$SpeechRecognitionCapability$BU52QJbVpbAsOz9iKzeGmX1c5E.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$onInitialize$8(System.Locales locales) throws Throwable {
        String replaceAll = locales.getCurrentLocale().getName().replaceAll(ProcessIdUtil.DEFAULT_PROCESSID, "_");
        Logger.d("Retrieved locale (%s) from SystemRepository.", replaceAll);
        return replaceAll;
    }

    private void reportCounterMetric(SpeechProcessingMetricsReporter.CounterType counterType, String str) {
        SpeechProcessingMetricsReporter.reportCounter(counterType, str, 1.0d, null);
    }

    private void reportStartSpeechDuration(long j, String str) {
        SpeechProcessingMetricsReporter.reportTimer(SpeechProcessingMetricsReporter.TimerType.COMMAND_TO_REPONSE_IN_START_SPEECH_HANDLER, this.currentTimeMillisProvider.provideCurrentTimeMillis() - j, str, null);
    }

    private void reportStartSpeechToRequestDialogDuration(long j, String str) {
        SpeechProcessingMetricsReporter.reportTimer(SpeechProcessingMetricsReporter.TimerType.TIME_ACCESSORY_RECOGNIZE_SPEECH_TO_START_DIALOG, this.currentTimeMillisProvider.provideCurrentTimeMillis() - j, str, null);
    }

    private static void respondSpeechMessage(ControlStream controlStream, Accessories.Command command, Common.ErrorCode errorCode, Speech.Dialog dialog) {
        controlStream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(command).setResponse(Accessories.Response.newBuilder().setErrorCode(errorCode).setDialog(dialog)).mo10084build()));
    }

    public /* synthetic */ void lambda$getEndpointSpeechHandler$16$SpeechRecognitionCapability(ControlStream controlStream, Accessories.Command command, Speech.EndpointSpeech endpointSpeech) throws Exception {
        Speech.Dialog dialog = endpointSpeech.getDialog();
        Firmware.FirmwareInformation firmwareInformation = this.firmwareInformation;
        int version = firmwareInformation != null ? firmwareInformation.getVersion() : 0;
        SpeechRecognitionTask speechRecognitionTask = this.task;
        if (speechRecognitionTask != null && speechRecognitionTask.getDialog().equals(dialog)) {
            boolean endpoint = this.task.endpoint();
            if (!endpoint) {
                SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.UNSUPPORTED_COMMAND, this.deviceInformation.getDeviceType(), Integer.valueOf(version), Accessories.Command.ENDPOINT_SPEECH);
            }
            respondSpeechMessage(controlStream, Accessories.Command.ENDPOINT_SPEECH, endpoint ? Common.ErrorCode.SUCCESS : Common.ErrorCode.UNSUPPORTED, dialog);
            return;
        }
        SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.UNSUPPORTED_COMMAND, this.deviceInformation.getDeviceType(), Integer.valueOf(version), Accessories.Command.ENDPOINT_SPEECH);
        respondSpeechMessage(controlStream, Accessories.Command.ENDPOINT_SPEECH, Common.ErrorCode.UNSUPPORTED, dialog);
    }

    public /* synthetic */ void lambda$getStartSpeechHandler$14$SpeechRecognitionCapability(AccessoryDescriptor accessoryDescriptor, ControlStream controlStream, Accessories.Command command, Speech.StartSpeech startSpeech) throws Exception {
        Preconditions.mainThread();
        long provideCurrentTimeMillis = this.currentTimeMillisProvider.provideCurrentTimeMillis();
        if (!startSpeech.hasSettings()) {
            Firmware.FirmwareInformation firmwareInformation = this.firmwareInformation;
            SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.SPEECH_RECOGNIZER_DISPOSED, this.deviceInformation.getDeviceType(), Integer.valueOf(firmwareInformation != null ? firmwareInformation.getVersion() : 0), null);
            throw new IllegalArgumentException("Start speech does not include settings");
        } else if (Speech.SpeechInitiator.Type.WAKEWORD == startSpeech.getInitiator().getType() && !this.speechRecognizer.isSpeechRecognitionEnabled(this.deviceInformation.getDeviceType())) {
            respondSpeechMessage(controlStream, Accessories.Command.START_SPEECH, Common.ErrorCode.BUSY, startSpeech.getDialog());
            reportStartSpeechDuration(provideCurrentTimeMillis, this.deviceInformation.getDeviceType());
            SpeechProcessingMetricsReporter.reportCounter(SpeechProcessingMetricsReporter.CounterType.START_SPEECH_SUPPRESSION, this.deviceInformation.getDeviceType(), 1.0d, startSpeech.getInitiator().getType().toString());
        } else {
            SpeechRecognitionTask speechRecognitionTask = this.task;
            Speech.Dialog dialog = startSpeech.getDialog();
            this.lastKnownDialog = dialog;
            this.task = null;
            if (speechRecognitionTask != null) {
                speechRecognitionTask.releaseForBargeIn();
            }
            SpeechProcessingMetricsReporter.reportCounter(SpeechProcessingMetricsReporter.CounterType.SPEECH_REQUEST, this.deviceInformation.getDeviceType(), 1.0d, null);
            SpeechProcessingMetricsReporter.reportCounter(SpeechProcessingMetricsReporter.CounterType.START_SPEECH_REQUEST, this.deviceInformation.getDeviceType(), 1.0d, startSpeech.getInitiator().getType().toString());
            this.task = new SpeechRecognitionTask(accessoryDescriptor, this.controlStream, this.speechRecognizer, startSpeech.getInitiator(), startSpeech.getSettings(), new SpeechRecognitionCallback(this.taskManager, controlStream), this.deviceInformation, this.speechRelatedFeatures, this.firmwareInformation, dialog, startSpeech.getSuppressStartEarcon(), startSpeech.getSuppressEndpointEarcon(), this.sessionIdentifierProvider, this.accessoryIdentifierProvider, this.locale);
            boolean schedule = this.taskManager.schedule(this.task, 9);
            reportStartSpeechToRequestDialogDuration(provideCurrentTimeMillis, this.deviceInformation.getDeviceType());
            respondSpeechMessage(controlStream, Accessories.Command.START_SPEECH, schedule ? Common.ErrorCode.SUCCESS : Common.ErrorCode.BUSY, dialog);
            reportStartSpeechDuration(provideCurrentTimeMillis, this.deviceInformation.getDeviceType());
        }
    }

    public /* synthetic */ void lambda$getStopSpeechHandler$15$SpeechRecognitionCapability(ControlStream controlStream, Accessories.Command command, Speech.StopSpeech stopSpeech) throws Exception {
        Preconditions.mainThread();
        Speech.Dialog dialog = stopSpeech.getDialog();
        SpeechRecognitionTask speechRecognitionTask = this.task;
        Firmware.FirmwareInformation firmwareInformation = this.firmwareInformation;
        int version = firmwareInformation != null ? firmwareInformation.getVersion() : 0;
        if (speechRecognitionTask == null) {
            reportCounterMetric(SpeechProcessingMetricsReporter.CounterType.UNSUPPORTED_COMMAND_12_TASK_NULL, this.deviceInformation.getDeviceType());
            SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.UNSUPPORTED_COMMAND, this.deviceInformation.getDeviceType(), Integer.valueOf(version), Accessories.Command.STOP_SPEECH);
            respondSpeechMessage(controlStream, Accessories.Command.STOP_SPEECH, Common.ErrorCode.UNSUPPORTED, dialog);
        } else if (!speechRecognitionTask.getDialog().equals(dialog)) {
            reportCounterMetric(SpeechProcessingMetricsReporter.CounterType.UNSUPPORTED_COMMAND_12_DIALOG_MISMATCH, this.deviceInformation.getDeviceType());
            SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.UNSUPPORTED_COMMAND, this.deviceInformation.getDeviceType(), Integer.valueOf(version), Accessories.Command.STOP_SPEECH);
            respondSpeechMessage(controlStream, Accessories.Command.STOP_SPEECH, Common.ErrorCode.UNSUPPORTED, dialog);
        } else {
            if (this.accessoryIdentifierProvider.getIdentifier() == null) {
                SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.ACCESSORY_IDENTIFIER_MISSING, this.deviceInformation.getDeviceType(), Integer.valueOf(version), null);
                Logger.e("Unable to fetch accessory identifier for stopSpeech.");
            }
            this.task = null;
            this.speechRecognizer.stopSpeech(this.accessoryIdentifierProvider.getIdentifier());
            boolean dispose = this.taskManager.dispose(speechRecognitionTask);
            if (!dispose) {
                reportCounterMetric(SpeechProcessingMetricsReporter.CounterType.UNSUPPORTED_COMMAND_12_TASK_DISPOSE, this.deviceInformation.getDeviceType());
                SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.UNSUPPORTED_COMMAND, this.deviceInformation.getDeviceType(), Integer.valueOf(version), Accessories.Command.STOP_SPEECH);
            } else {
                reportCounterMetric(SpeechProcessingMetricsReporter.CounterType.FW_INIT_STOP_SPEECH_VALID, this.deviceInformation.getDeviceType());
            }
            respondSpeechMessage(controlStream, Accessories.Command.STOP_SPEECH, dispose ? Common.ErrorCode.SUCCESS : Common.ErrorCode.UNSUPPORTED, dialog);
        }
    }

    public /* synthetic */ void lambda$initializeSpeechRelatedFeatures$12$SpeechRecognitionCapability(DeviceFeature deviceFeature) throws Throwable {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Accessory support display feature: ");
        outline107.append(deviceFeature.getId());
        Logger.i(outline107.toString());
        this.speechRelatedFeatures.add(deviceFeature);
    }

    public /* synthetic */ void lambda$onInitialize$10$SpeechRecognitionCapability(Throwable th) throws Throwable {
        Device.DeviceInformation deviceInformation = this.deviceInformation;
        SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.FIRMWARE_INFO_MISSING, deviceInformation != null ? deviceInformation.getDeviceType() : null, null, null);
        Logger.e("Failed to get locale information from system and firmware repositories.", th);
    }

    public /* synthetic */ void lambda$onInitialize$2$SpeechRecognitionCapability(AccessoryDescriptor accessoryDescriptor, Device.DeviceInformation deviceInformation) throws Throwable {
        Logger.d("Speech recognition capability received a new Device Information");
        if (this.deviceInformation == null) {
            Logger.d("Speech recognition capability adding message handlers");
            this.controlStream.addMessageHandler(Accessories.Command.START_SPEECH, getStartSpeechHandler(accessoryDescriptor));
            this.controlStream.addMessageHandler(Accessories.Command.STOP_SPEECH, getStopSpeechHandler());
            this.controlStream.addMessageHandler(Accessories.Command.ENDPOINT_SPEECH, getEndpointSpeechHandler());
            SpeechProcessingMetricsReporter.reportDelay(SpeechProcessingMetricsReporter.DelayType.SPEECH_CAPABILITY_RECEIVED_DEVICE_INFO, new Date().getTime() - this.initializationStart.getTime(), deviceInformation.getDeviceType(), null);
        }
        this.deviceInformation = deviceInformation;
    }

    public /* synthetic */ void lambda$onInitialize$3$SpeechRecognitionCapability(Throwable th) throws Throwable {
        Firmware.FirmwareInformation firmwareInformation = this.firmwareInformation;
        SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.DEVICE_INFO_MISSING, null, Integer.valueOf(firmwareInformation != null ? firmwareInformation.getVersion() : 0), null);
        Logger.e("Failed to get device information. Speech capability will not handle messages", th);
    }

    public /* synthetic */ void lambda$onInitialize$6$SpeechRecognitionCapability(Firmware.FirmwareInformation firmwareInformation) throws Throwable {
        SpeechProcessingMetricsReporter.reportDelay(SpeechProcessingMetricsReporter.DelayType.SPEECH_CAPABILITY_RECEIVED_FIRMWARE_INFO, new Date().getTime() - this.initializationStart.getTime(), this.deviceInformation.getDeviceType(), Integer.valueOf(firmwareInformation.getVersion()));
        this.firmwareInformation = firmwareInformation;
    }

    public /* synthetic */ void lambda$onInitialize$7$SpeechRecognitionCapability(Throwable th) throws Throwable {
        Device.DeviceInformation deviceInformation = this.deviceInformation;
        SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.FIRMWARE_INFO_MISSING, deviceInformation != null ? deviceInformation.getDeviceType() : null, null, null);
        Logger.e("Failed to get firmware information.");
    }

    public /* synthetic */ void lambda$onInitialize$9$SpeechRecognitionCapability(String str) throws Throwable {
        Logger.d("Setting locale to (%s) in SpeechRecognitionCapability.", str);
        this.locale = str;
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.speechProducer.detachActionHandler(this.actionHandler);
        this.taskManager.dispose(this.task);
        accessoryDescriptor.remove(this.controlStream);
        ObservableUtils.dispose(this.deviceDisposable);
        ObservableUtils.dispose(this.firmwareDisposable);
        ObservableUtils.dispose(this.localeDisposable);
        ObservableUtils.dispose(this.deviceFeatureDisposable);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(final AccessoryDescriptor accessoryDescriptor) {
        this.descriptor = accessoryDescriptor;
        this.initializationStart = new Date();
        this.speechProducer.attachActionHandler(this.actionHandler);
        this.controlStream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.deviceDisposable = this.deviceRepository.queryDeviceInformationSet().map($$Lambda$SpeechRecognitionCapability$GE0RG_kg42y3V4NMCH3Z81OgLoc.INSTANCE).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionCapability$kGYl4XGkV0XpaeesNF3NkYn6jto
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeechRecognitionCapability.this.lambda$onInitialize$2$SpeechRecognitionCapability(accessoryDescriptor, (Device.DeviceInformation) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionCapability$VFA3iwoE8kUm2cXxfPDGZdxo22I
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeechRecognitionCapability.this.lambda$onInitialize$3$SpeechRecognitionCapability((Throwable) obj);
            }
        });
        initializeSpeechRelatedFeatures();
        this.firmwareDisposable = this.firmwareRepository.queryInformationSet().map($$Lambda$SpeechRecognitionCapability$GLkMNmSYBZi7qapTowRkZlFa3HQ.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionCapability$RjAIdCpjjxWWSHOTKwg8NOz0YT8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeechRecognitionCapability.this.lambda$onInitialize$6$SpeechRecognitionCapability((Firmware.FirmwareInformation) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionCapability$gODm_qupyxT1sY6wNZ56Ma673FY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeechRecognitionCapability.this.lambda$onInitialize$7$SpeechRecognitionCapability((Throwable) obj);
            }
        });
        this.localeDisposable = this.systemRepository.queryLocales().map($$Lambda$SpeechRecognitionCapability$2pKehByPEq6FP7Q5NB10oHrev5k.INSTANCE).onErrorResumeWith(this.firmwareRepository.queryInformationSet().map($$Lambda$0rwaWiUruyPaU1cztUfukc2TQ.INSTANCE).toFlowable()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionCapability$lebaFl_VRhALOBi9GgcoBRjB_rs
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeechRecognitionCapability.this.lambda$onInitialize$9$SpeechRecognitionCapability((String) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionCapability$BHpAMpPn64DZ4o76vzxq6tvT5kQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SpeechRecognitionCapability.this.lambda$onInitialize$10$SpeechRecognitionCapability((Throwable) obj);
            }
        });
        accessoryDescriptor.add(this.controlStream);
    }

    @VisibleForTesting
    SpeechRecognitionCapability(TaskManager taskManager, SpeechRecognizer speechRecognizer, DeviceRepositoryV2 deviceRepositoryV2, FirmwareRepositoryV2 firmwareRepositoryV2, SystemRepository systemRepository, SessionIdentifierProvider sessionIdentifierProvider, AccessoryIdentifierProvider accessoryIdentifierProvider, CurrentTimeMillisProvider currentTimeMillisProvider, SpeechProducer speechProducer, FeatureChecker featureChecker) {
        Preconditions.notNull(taskManager, "taskManager");
        Preconditions.notNull(speechRecognizer, "speechRecognizer");
        Preconditions.notNull(deviceRepositoryV2, "deviceRepository");
        Preconditions.notNull(firmwareRepositoryV2, "firmwareRepository");
        Preconditions.notNull(sessionIdentifierProvider, "sessionIdentifierProvider");
        Preconditions.notNull(accessoryIdentifierProvider, "accessoryIdentifierProvider");
        Preconditions.notNull(currentTimeMillisProvider, "currentTimeMillisProvider");
        Preconditions.notNull(speechProducer, "speechProducer");
        Preconditions.notNull(featureChecker, "featureChecker");
        this.deviceRepository = deviceRepositoryV2;
        this.firmwareRepository = firmwareRepositoryV2;
        this.systemRepository = systemRepository;
        this.taskManager = taskManager;
        this.speechRecognizer = speechRecognizer;
        this.sessionIdentifierProvider = sessionIdentifierProvider;
        this.accessoryIdentifierProvider = accessoryIdentifierProvider;
        this.currentTimeMillisProvider = currentTimeMillisProvider;
        this.actionHandler = new SpeechProducerActionHandler();
        this.speechProducer = speechProducer;
        this.lastKnownDialog = null;
        this.speechRelatedFeatures = new HashSet();
        this.featureChecker = featureChecker;
    }
}
