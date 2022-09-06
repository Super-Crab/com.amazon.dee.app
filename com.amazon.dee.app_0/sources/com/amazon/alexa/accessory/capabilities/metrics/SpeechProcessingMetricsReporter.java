package com.amazon.alexa.accessory.capabilities.metrics;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class SpeechProcessingMetricsReporter {

    /* renamed from: com.amazon.alexa.accessory.capabilities.metrics.SpeechProcessingMetricsReporter$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$capabilities$metrics$SpeechProcessingMetricsReporter$FailureType = new int[FailureType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$metrics$SpeechProcessingMetricsReporter$FailureType[FailureType.ERROR_CODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$metrics$SpeechProcessingMetricsReporter$FailureType[FailureType.UNSUPPORTED_COMMAND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$metrics$SpeechProcessingMetricsReporter$FailureType[FailureType.SPEECH_BARGING_BLOCKED_BY_ANOTHER_ACCESSORY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$metrics$SpeechProcessingMetricsReporter$FailureType[FailureType.PROVIDE_SPEECH_FW_RESPONSE_FAILURE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$capabilities$metrics$SpeechProcessingMetricsReporter$FailureType[FailureType.EXPECT_SPEECH_FW_RESPONSE_FAILURE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes.dex */
    interface AccessoriesMetricsKeys {
        public static final String DEVICE_TYPE = "deviceType_accessory";
        public static final String FIRMWARE_VERSION = "firmware_accessory";
    }

    /* loaded from: classes.dex */
    public enum CounterType {
        SPEECH_REQUEST("speechRequest"),
        START_SPEECH_REQUEST("startSpeechWithInitiator"),
        PROVIDE_SPEECH_REQUEST("provideSpeechWithInitiator"),
        SPEECH_DIALOG_REQUESTED("speechDialogRequestedWithInitiator"),
        SPEECH_DIALOG_ACCEPTED("speechDialogAcceptedWithInitiator"),
        SPEECH_DIALOG_DENIED("speechDialogDeniedWithInitiator"),
        EXPECT_SPEECH_REQUESTED("expectSpeechRequested"),
        EXPECT_SPEECH_EXECUTED("expectSpeechExecuted"),
        START_SPEECH_SUPPRESSION("startSpeechSuppression"),
        SPEECH_REQUESTED_SUCCESS("speechRequestedSuccessfully"),
        ALEXA_CONNECTION_ALREADY_CONNECTED_IN_USER_SPEECH_PROVIDER("alexaConnectionAlreadyConnectedInUserSpeechProvider"),
        ALEXA_CONNECTION_GOT_CONNECTED_IN_USER_SPEECH_PROVIDER("alexaConnectionGotConnectedInUserSpeechProvider"),
        ALEXA_CONNECTION_NOT_CONNECTED_IN_USER_SPEECH_PROVIDER("alexaConnectionNotConnectedInUserSpeechProvider"),
        UNSUPPORTED_COMMAND_12_TASK_NULL("unsupportedCommand12TaskNull"),
        UNSUPPORTED_COMMAND_12_DIALOG_MISMATCH("unsupportedCommand12DialogMismatch"),
        UNSUPPORTED_COMMAND_12_TASK_DISPOSE("unsupportedCommand12TaskDispose"),
        FIRMWARE_MISSING_SPEECH_INITIATION_TYPE_INFO("fwMissingSpeechInitiationTypeInfo"),
        FW_INIT_STOP_SPEECH_VALID("fwInitStopSpeechValid");
        
        private final String description;

        CounterType(String str) {
            this.description = str;
        }

        public String getDescription() {
            return this.description;
        }
    }

    /* loaded from: classes.dex */
    public enum DelayType {
        SPEECH_CAPABILITY_RECEIVED_DEVICE_INFO("speechCapabilityReceivedDeviceInfo"),
        SPEECH_CAPABILITY_RECEIVED_FIRMWARE_INFO("speechCapabilityReceivedFirmwareInfo");
        
        private final String description;

        DelayType(String str) {
            this.description = str;
        }

        public String getDescription() {
            return this.description;
        }
    }

    /* loaded from: classes.dex */
    public enum FailureType {
        FIRMWARE_INFO_MISSING("firmwareInfoMissing"),
        DEVICE_INFO_MISSING("deviceInfoMissing"),
        ACCESSORY_IDENTIFIER_MISSING("accessoryIdentifierMissing"),
        AUDIO_DATA_SOURCE_MISSING("audioDataSourceMissing"),
        SPEECH_RECOGNIZER_DISPOSED("speechRecognizerDisposed"),
        SPEECH_RECOGNITION_UNSUPPORTED("speechRecognitionUnsupported"),
        AUDIO_PROCESSOR_NOT_READY("audioProcessorNotReady"),
        AUDIO_PROCESSOR_NIL("audioProcessorNil"),
        PAYLOAD_COMMAND_INVALID("payloadCommandInvalid"),
        UNSUPPORTED_COMMAND("unsupportedCommand"),
        ERROR_CODE("errorCode"),
        PROVIDE_SPEECH_FW_RESPONSE_FAILURE("provideSpeechFWResponseFailureErrorCode"),
        EXPECT_SPEECH_FW_RESPONSE_FAILURE("expectSpeechFWResponseFailureErrorCode"),
        DID_FAIL_SPEECH_RECOGNITION("didFailSpeechRecognition"),
        DID_COMPLETE_SPEECH_RECOGNITION("didCompleteSpeechRecognition"),
        NO_SPEECH_PROVIDER_AVAILABLE("noSpeechProviderAvailable"),
        SPEECH_BARGING_FROM_ANOTHER_ACCESSORY("speechBargingFromAnotherAccessory"),
        SPEECH_BARGING_BLOCKED_BY_ANOTHER_ACCESSORY("speechBargingBlockedByAnotherAccessory"),
        AMA_DEVICES_JSON_PARSING_ERROR("amaDevicesJsonParsingError"),
        ACCESSORY_AUDIO_SINK_ERROR("accessoryAudioSinkError"),
        SESSION_RELEASED_IN_SPEECH_MANAGER("sessionReleasedInSpeechProviderManager"),
        ALEXA_CONNECTION_DISCONNECT_IN_SPEECH_MANAGER("alexaConnectionDisconnectInProviderManager"),
        ERROR_REGISTERING_SPEECH_PROVIDER("errorRegisteringSpeechProvider"),
        ALEXA_CONNECTION_DISCONNECT_IN_USER_SPEECH_PROVIDER("alexaConnectionDisconnectInUserSpeechProvider"),
        ALEXA_CONNECTION_FAILED_IN_USER_SPEECH_PROVIDER("alexaConnectionFailedInUserSpeechProvider"),
        SPEECH_RECOGNITION_TASK_VOICE_STREAM_ERROR("speechRecognitionTaskVoiceStream"),
        SPEECH_RECOGNITION_TASK_ON_SPEECH_REQUEST_ERROR("speechRecognitionTaskOnSpeechRequest"),
        SPEECH_RECOGNITION_TASK_ON_EXPECT_SPEECH_ERROR("speechRecognitionTaskOnExpectSpeech"),
        SPEECH_RECOGNITION_TASK_ON_SPEECH_RECOGNITION_FAILED_ERROR("speechRecognitionTaskOnSpeechRecognitionFailed"),
        SPEECH_RECOGNIZER_V1_ON_DIALOG_ERROR("speechRecognizerV1OnDialogError"),
        SPEECH_RECOGNIZER_V1_ON_ALEXA_STATE_ERROR("speechRecognizerV1OnAlexaStateError"),
        SPEECH_RECOGNIZER_V1_ON_ALEXA_STATE_UNKNOWN("speechRecognizerV1OnAlexaStateUnknown"),
        SPEECH_RECOGNIZER_V2_ON_DIALOG_ERROR("speechRecognizerV2OnDialogError"),
        SPEECH_RECOGNIZER_V2_ON_ALEXA_STATE_ERROR("speechRecognizerV2OnAlexaStateError"),
        SPEECH_RECOGNIZER_V2_ON_ALEXA_STATE_UNKNOWN("speechRecognizerV2OnAlexaStateUnknown");
        
        private final String description;

        FailureType(String str) {
            this.description = str;
        }

        public String getDescription() {
            return this.description;
        }
    }

    /* loaded from: classes.dex */
    public enum TimerType {
        REGISTER_USER_SPEECH_PROVIDER("registerUserSpeechProvider"),
        CONNECT_ALEXA_CONNECTION_IN_REGISTER_USER_SPEECH_PROVIDER("connectAlexaConnectionInUserSpeechProvider"),
        COMMAND_TO_REPONSE_IN_START_SPEECH_HANDLER("commandToResponseInStartSpeechHandler"),
        TIME_ACCESSORY_RECOGNIZE_SPEECH_TO_START_DIALOG("timeAccessoryRecognizeSpeechToRequestDialog");
        
        private final String description;

        TimerType(String str) {
            this.description = str;
        }

        public String getDescription() {
            return this.description;
        }
    }

    private SpeechProcessingMetricsReporter() {
    }

    private static String getFormattedDescription(FailureType failureType, Object obj) {
        int ordinal = failureType.ordinal();
        if (ordinal != 17) {
            switch (ordinal) {
                case 9:
                    if (obj instanceof Accessories.Command) {
                        return failureType.getDescription() + "_" + ((Accessories.Command) obj).getNumber();
                    }
                    break;
                case 10:
                    if (obj instanceof Common.ErrorCode) {
                        return failureType.getDescription() + "_" + ((Common.ErrorCode) obj).getNumber();
                    }
                    break;
                case 11:
                case 12:
                    if (obj instanceof Common.ErrorCode) {
                        return failureType.getDescription() + "_" + ((Common.ErrorCode) obj).getNumber();
                    }
                    break;
            }
        } else if (obj instanceof String) {
            return failureType.getDescription() + "_" + obj;
        }
        return failureType.getDescription();
    }

    public static void reportCounter(CounterType counterType, String str, double d, String str2) {
        Logger.d("Accessory Speech Processing reporting metric:(%s), counterValue:(%f), speechInitiator:(%s)", counterType.description, Double.valueOf(d), str2);
        String description = counterType.getDescription();
        String str3 = MetricsConstants.Capability.ACCESSORY_SPEECH_CAPABILITY;
        if (str != null && !str.isEmpty()) {
            str3 = GeneratedOutlineSupport1.outline75(str3, ":", str);
        }
        String str4 = str3;
        if (str2 != null && !str2.isEmpty()) {
            description = GeneratedOutlineSupport1.outline75(description, ":", str2);
        }
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(str4, description, d, null);
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(MetricsConstants.Capability.ACCESSORY_SPEECH_CAPABILITY, description, d, null);
    }

    public static void reportDelay(DelayType delayType, long j, String str, Integer num) {
        Logger.d("Accessory Speech Processing reporting metric : (%s), delay: (%d)", delayType.description, Long.valueOf(j));
        HashMap hashMap = new HashMap();
        if (str != null) {
            hashMap.put("deviceType_accessory", str);
        }
        if (num != null) {
            hashMap.put("firmware_accessory", String.valueOf(num));
        }
        AccessoryMetricsServiceHolder.getInstance().get().recordTime(MetricsConstants.Speech.SPEECH_PROCESSING_DELAY_METRIC, delayType.getDescription(), j, hashMap);
    }

    public static void reportFailure(FailureType failureType, String str, Integer num, Object obj) {
        String formattedDescription = getFormattedDescription(failureType, obj);
        Logger.e("Accessory Speech Processing reporting metric : " + formattedDescription + " failureInfoObject: " + obj);
        HashMap hashMap = new HashMap();
        if (str != null) {
            hashMap.put("deviceType_accessory", str);
        }
        if (num != null) {
            hashMap.put("firmware_accessory", String.valueOf(num));
        }
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Speech.SPEECH_PROCESSING_FAILED_METRIC, formattedDescription, true, hashMap);
        if (str == null || str.isEmpty()) {
            return;
        }
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(GeneratedOutlineSupport1.outline72("AccessorySpeechProcessingFailedForDevice:", str), formattedDescription, true, hashMap);
    }

    public static void reportTimer(TimerType timerType, long j, String str, Map<String, Object> map) {
        Logger.d("Accessory Speech Processing reporting timer metric:(%s)", timerType.description);
        String description = timerType.getDescription();
        AccessoryMetricsServiceHolder.getInstance().get().recordTime(MetricsConstants.Capability.ACCESSORY_SPEECH_CAPABILITY, description, j, map);
        if (str == null || str.isEmpty()) {
            return;
        }
        AccessoryMetricsServiceHolder.getInstance().get().recordTime(GeneratedOutlineSupport1.outline75(MetricsConstants.Capability.ACCESSORY_SPEECH_CAPABILITY, ":", str), description, j, map);
    }
}
