package com.amazon.alexa.accessory.streams.control;

import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.io.SinkOutputStream;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.io.SourceInputStream;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.streams.control.ControlMessage;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class ProtobufControlMessage implements ControlMessage {
    private final Accessories.ControlEnvelope envelope;

    /* renamed from: com.amazon.alexa.accessory.streams.control.ProtobufControlMessage$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase = new int[Accessories.ControlEnvelope.PayloadCase.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.RESPONSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.PROVIDE_SPEECH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.START_SPEECH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.STOP_SPEECH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.ENDPOINT_SPEECH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.NOTIFY_SPEECH_STATE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_DEVICE_INFORMATION.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_DEVICE_CONFIGURATION.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.OVERRIDE_ASSISTANT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.START_SETUP.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.COMPLETE_SETUP.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.UPDATE_DEVICE_INFORMATION.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.NOTIFY_DEVICE_INFORMATION.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.NOTIFY_DEVICE_CONFIGURATION.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.UPGRADE_TRANSPORT.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.SWITCH_TRANSPORT.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.FORWARD_AT_COMMAND.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.INCOMING_CALL.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_FIRMWARE_INFORMATION.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_FIRMWARE_UPDATE_PREFERENCES.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_CACHED_COMPONENT.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.RESET_CACHED_COMPONENT.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.UPDATE_COMPONENT_SEGMENT.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.APPLY_FIRMWARE.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.SYNCHRONIZE_STATE.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_STATE.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.SET_STATE.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_DIAGNOSTICS.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.STOP_DIAGNOSTICS.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.PUSH_METRICS.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.NOTIFY_DIAGNOSTICS_AVAILABLE.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.UPDATE_METRICS_MAP.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.RESET_CONNECTION.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.SYNCHRONIZE_SETTINGS.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.KEEP_ALIVE.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_USERS.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.UPDATE_USERS.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_CURRENT_USER.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.SWITCH_CURRENT_USER.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_LOCALES.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.SET_LOCALE.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.REMOVE_DEVICE.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_FITNESS_DATA.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.STOP_FITNESS_DATA.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.NOTIFY_FITNESS_DATA_AVAILABLE.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.SYNC_FITNESS_SESSION.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.START_LIVE_FITNESS_DATA.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.LIVE_FITNESS_DATA.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.STOP_LIVE_FITNESS_DATA.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.PRINT_DEBUG.ordinal()] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.ISSUE_REMOTE_COMMAND.ordinal()] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.ISSUE_REMOTE_RESET.ordinal()] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.ISSUE_REMOTE_CLEAR_PAIRING.ordinal()] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.ISSUE_REMOTE_RESTART.ordinal()] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.ISSUE_INPUT_EVENT.ordinal()] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_INPUT_BEHAVIOR.ordinal()] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.SET_INPUT_BEHAVIOR.ordinal()] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.RESET_INPUT_BEHAVIOR.ordinal()] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_CENTRAL_INFORMATION.ordinal()] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.STOP_TRANSLATION.ordinal()] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.START_TRANSLATION.ordinal()] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.PROVIDE_TRANSLATION.ordinal()] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.ISSUE_MEDIA_CONTROL.ordinal()] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.REGISTER_FOR_MEDIA_EVENTS.ordinal()] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.MEDIA_EVENT_OCCURRED.ordinal()] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_PLAYBACK_STATUS.ordinal()] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.NOTIFY_BULK_DATA_AVAILABLE.ordinal()] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_BULK_DATA_MANIFEST.ordinal()] = 68;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.BULK_DATA_MANIFEST_ENTRY.ordinal()] = 69;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.REQUEST_BULK_DATA_TRANSFER.ordinal()] = 70;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.BULK_DATA_TRANSFER_START.ordinal()] = 71;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.BULK_DATA_TRANSFER_COMPLETE.ordinal()] = 72;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.STOP_BULK_DATA_TRANSFER.ordinal()] = 73;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.INITIATE_HANDSHAKE.ordinal()] = 74;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.COMPLETE_HANDSHAKE.ordinal()] = 75;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.RESET_KEY.ordinal()] = 76;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.CONFIRM_RESET_KEY.ordinal()] = 77;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.RESET_ROOT_KEYS.ordinal()] = 78;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_CBL_LOGIN_STATE.ordinal()] = 79;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_CBL_INFORMATION.ordinal()] = 80;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.NOTIFY_CBL_LOGIN_STATE.ordinal()] = 81;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.UPDATE_CALL_STATE.ordinal()] = 82;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.ACCEPT_CALL.ordinal()] = 83;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.REJECT_CALL.ordinal()] = 84;
            } catch (NoSuchFieldError unused84) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.END_CALL.ordinal()] = 85;
            } catch (NoSuchFieldError unused85) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_AUDIOGRAM.ordinal()] = 86;
            } catch (NoSuchFieldError unused86) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.SET_AUDIOGRAM.ordinal()] = 87;
            } catch (NoSuchFieldError unused87) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.GET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT.ordinal()] = 88;
            } catch (NoSuchFieldError unused88) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.SET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT.ordinal()] = 89;
            } catch (NoSuchFieldError unused89) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.DISPLAY_CONTENT.ordinal()] = 90;
            } catch (NoSuchFieldError unused90) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.ADD_NOTIFICATION.ordinal()] = 91;
            } catch (NoSuchFieldError unused91) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.UPDATE_NOTIFICATION.ordinal()] = 92;
            } catch (NoSuchFieldError unused92) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.REMOVE_NOTIFICATION.ordinal()] = 93;
            } catch (NoSuchFieldError unused93) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.EXECUTE_NOTIFICATION_ACTION.ordinal()] = 94;
            } catch (NoSuchFieldError unused94) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[Accessories.ControlEnvelope.PayloadCase.NOTIFY_FIRMWARE_INFORMATION.ordinal()] = 95;
            } catch (NoSuchFieldError unused95) {
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class Factory implements ControlMessage.Factory<ProtobufControlMessage> {
        @Override // com.amazon.alexa.accessory.streams.control.ControlMessage.Factory
        public ProtobufControlMessage create(Source source) throws IOException {
            return new ProtobufControlMessage(Accessories.ControlEnvelope.parseFrom(new SourceInputStream(source)));
        }
    }

    public ProtobufControlMessage(Accessories.ControlEnvelope controlEnvelope) {
        this.envelope = controlEnvelope;
    }

    @Override // com.amazon.alexa.accessory.streams.control.ControlMessage
    public Accessories.Command getCommand() {
        return this.envelope.getCommand();
    }

    @Override // com.amazon.alexa.accessory.streams.control.ControlMessage
    public <T> T getPayload() {
        int ordinal = this.envelope.getPayloadCase().ordinal();
        if (ordinal != 51) {
            switch (ordinal) {
                case 0:
                    return (T) this.envelope.getResponse();
                case 1:
                    return (T) this.envelope.getResetConnection();
                case 2:
                    return (T) this.envelope.getSynchronizeSettings();
                case 3:
                    return (T) this.envelope.getKeepAlive();
                case 4:
                    return (T) this.envelope.getRemoveDevice();
                case 5:
                    return (T) this.envelope.getGetUsers();
                case 6:
                    return (T) this.envelope.getUpdateUsers();
                default:
                    switch (ordinal) {
                        case 10:
                            return (T) this.envelope.getGetCurrentUser();
                        case 11:
                            return (T) this.envelope.getSwitchCurrentUser();
                        case 12:
                            return (T) this.envelope.getGetLocales();
                        case 13:
                            return (T) this.envelope.getSetLocale();
                        default:
                            switch (ordinal) {
                                case 17:
                                    return (T) this.envelope.getUpgradeTransport();
                                case 18:
                                    return (T) this.envelope.getSwitchTransport();
                                case 19:
                                    return (T) this.envelope.getStartSpeech();
                                case 20:
                                    return (T) this.envelope.getProvideSpeech();
                                case 21:
                                    return (T) this.envelope.getStopSpeech();
                                case 22:
                                    return (T) this.envelope.getEndpointSpeech();
                                case 23:
                                    return (T) this.envelope.getNotifySpeechState();
                                case 24:
                                    return (T) this.envelope.getForwardAtCommand();
                                case 25:
                                    return (T) this.envelope.getIncomingCall();
                                case 26:
                                    return (T) this.envelope.getGetCentralInformation();
                                case 27:
                                    return (T) this.envelope.getGetDeviceInformation();
                                case 28:
                                    return (T) this.envelope.getGetDeviceConfiguration();
                                case 29:
                                    return (T) this.envelope.getOverrideAssistant();
                                case 30:
                                    return (T) this.envelope.getStartSetup();
                                case 31:
                                    return (T) this.envelope.getCompleteSetup();
                                case 32:
                                    return (T) this.envelope.getNotifyDeviceConfiguration();
                                case 33:
                                    return (T) this.envelope.getUpdateDeviceInformation();
                                case 34:
                                    return (T) this.envelope.getNotifyDeviceInformation();
                                default:
                                    switch (ordinal) {
                                        case 36:
                                            return (T) this.envelope.getGetDiagnostics();
                                        case 37:
                                            return (T) this.envelope.getStopDiagnostics();
                                        case 38:
                                            return (T) this.envelope.getNotifyDiagnosticsAvailable();
                                        case 39:
                                            return (T) this.envelope.getGetFirmwareInformation();
                                        case 40:
                                            return (T) this.envelope.getGetCachedComponent();
                                        case 41:
                                            return (T) this.envelope.getResetCachedComponent();
                                        case 42:
                                            return (T) this.envelope.getUpdateComponentSegment();
                                        case 43:
                                            return (T) this.envelope.getApplyFirmware();
                                        case 44:
                                            return (T) this.envelope.getGetFirmwareUpdatePreferences();
                                        default:
                                            switch (ordinal) {
                                                case 53:
                                                    return (T) this.envelope.getIssueMediaControl();
                                                case 54:
                                                    return (T) this.envelope.getGetPlaybackStatus();
                                                case 55:
                                                    return (T) this.envelope.getRegisterForMediaEvents();
                                                case 56:
                                                    return (T) this.envelope.getMediaEventOccurred();
                                                case 57:
                                                    return (T) this.envelope.getPushMetrics();
                                                case 58:
                                                    return (T) this.envelope.getUpdateMetricsMap();
                                                case 59:
                                                    return (T) this.envelope.getGetState();
                                                case 60:
                                                    return (T) this.envelope.getSetState();
                                                case 61:
                                                    return (T) this.envelope.getSynchronizeState();
                                                case 62:
                                                    return (T) this.envelope.getGetFitnessData();
                                                case 63:
                                                    return (T) this.envelope.getStopFitnessData();
                                                case 64:
                                                    return (T) this.envelope.getNotifyFitnessDataAvailable();
                                                case 65:
                                                    return (T) this.envelope.getSyncFitnessSession();
                                                case 66:
                                                    return (T) this.envelope.getStartLiveFitnessData();
                                                case 67:
                                                    return (T) this.envelope.getStopLiveFitnessData();
                                                case 68:
                                                    return (T) this.envelope.getLiveFitnessData();
                                                default:
                                                    switch (ordinal) {
                                                        case 76:
                                                            return (T) this.envelope.getIssueInputEvent();
                                                        case 77:
                                                            return (T) this.envelope.getSetInputBehavior();
                                                        case 78:
                                                            return (T) this.envelope.getGetInputBehavior();
                                                        case 79:
                                                            return (T) this.envelope.getResetInputBehavior();
                                                        case 80:
                                                            return (T) this.envelope.getPrintDebug();
                                                        case 81:
                                                            return (T) this.envelope.getIssueRemoteCommand();
                                                        case 82:
                                                            return (T) this.envelope.getIssueRemoteRestart();
                                                        case 83:
                                                            return (T) this.envelope.getIssueRemoteReset();
                                                        case 84:
                                                            return (T) this.envelope.getIssueRemoteClearPairing();
                                                        case 85:
                                                            return (T) this.envelope.getStartTranslation();
                                                        case 86:
                                                            return (T) this.envelope.getProvideTranslation();
                                                        case 87:
                                                            return (T) this.envelope.getStopTranslation();
                                                        case 88:
                                                            return (T) this.envelope.getGetBulkDataManifest();
                                                        case 89:
                                                            return (T) this.envelope.getBulkDataManifestEntry();
                                                        case 90:
                                                            return (T) this.envelope.getRequestBulkDataTransfer();
                                                        case 91:
                                                            return (T) this.envelope.getBulkDataTransferStart();
                                                        case 92:
                                                            return (T) this.envelope.getBulkDataTransferComplete();
                                                        case 93:
                                                            return (T) this.envelope.getStopBulkDataTransfer();
                                                        case 94:
                                                            return (T) this.envelope.getNotifyBulkDataAvailable();
                                                        case 95:
                                                            return (T) this.envelope.getInitiateHandshake();
                                                        case 96:
                                                            return (T) this.envelope.getCompleteHandshake();
                                                        default:
                                                            switch (ordinal) {
                                                                case 98:
                                                                    return (T) this.envelope.getResetKey();
                                                                case 99:
                                                                    return (T) this.envelope.getConfirmResetKey();
                                                                case 100:
                                                                    return (T) this.envelope.getResetRootKeys();
                                                                case 101:
                                                                    return (T) this.envelope.getGetAudiogram();
                                                                case 102:
                                                                    return (T) this.envelope.getSetAudiogram();
                                                                case 103:
                                                                    return (T) this.envelope.getGetMediaEnhancementCorrectionAmount();
                                                                case 104:
                                                                    return (T) this.envelope.getSetMediaEnhancementCorrectionAmount();
                                                                case 105:
                                                                    return (T) this.envelope.getGetCblLoginState();
                                                                case 106:
                                                                    return (T) this.envelope.getGetCblInformation();
                                                                case 107:
                                                                    return (T) this.envelope.getNotifyCblLoginState();
                                                                default:
                                                                    switch (ordinal) {
                                                                        case 117:
                                                                            return (T) this.envelope.getDisplayContent();
                                                                        case 118:
                                                                            return (T) this.envelope.getUpdateCallState();
                                                                        case 119:
                                                                            return (T) this.envelope.getAcceptCall();
                                                                        case 120:
                                                                            return (T) this.envelope.getRejectCall();
                                                                        case 121:
                                                                            return (T) this.envelope.getEndCall();
                                                                        case 122:
                                                                            return (T) this.envelope.getAddNotification();
                                                                        case 123:
                                                                            return (T) this.envelope.getUpdateNotification();
                                                                        case 124:
                                                                            return (T) this.envelope.getRemoveNotification();
                                                                        case 125:
                                                                            return (T) this.envelope.getExecuteNotificationAction();
                                                                        default:
                                                                            return null;
                                                                    }
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
            }
        }
        return (T) this.envelope.getNotifyFirmwareInformation();
    }

    @Override // com.amazon.alexa.accessory.streams.control.ControlMessage
    public boolean isResponse() {
        return this.envelope.getPayloadCase() == Accessories.ControlEnvelope.PayloadCase.RESPONSE;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProtobufControlMessage{command=");
        outline107.append(getCommand());
        outline107.append(", payload=");
        outline107.append(getPayload());
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    @Override // com.amazon.alexa.accessory.streams.control.ControlMessage
    public void write(Sink sink) throws IOException {
        this.envelope.writeTo(new SinkOutputStream(sink));
    }
}
