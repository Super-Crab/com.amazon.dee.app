package com.amazon.alexa.accessory.protocol;

import com.amazon.alexa.accessory.protocol.Ancs;
import com.amazon.alexa.accessory.protocol.Bulkdata;
import com.amazon.alexa.accessory.protocol.Calling;
import com.amazon.alexa.accessory.protocol.Cardrendering;
import com.amazon.alexa.accessory.protocol.Cbl;
import com.amazon.alexa.accessory.protocol.Central;
import com.amazon.alexa.accessory.protocol.Cloudpairing;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.DiagnosticsOuterClass;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.protocol.Fitness;
import com.amazon.alexa.accessory.protocol.Hearing;
import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.accessory.protocol.Instrumentation;
import com.amazon.alexa.accessory.protocol.Keyexchange;
import com.amazon.alexa.accessory.protocol.Mapsms;
import com.amazon.alexa.accessory.protocol.Media;
import com.amazon.alexa.accessory.protocol.Metrics;
import com.amazon.alexa.accessory.protocol.Nonhfpcalling;
import com.amazon.alexa.accessory.protocol.Notification;
import com.amazon.alexa.accessory.protocol.Speech;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessory.protocol.Translation;
import com.amazon.alexa.accessory.protocol.Transport;
import com.amazon.alexa.accessory.protocol.Voicestream;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.makernotes.LeicaMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import com.drew.metadata.iptc.IptcDirectory;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.facebook.imageutils.JfifUtil;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.servlet.http.HttpServletResponse;
/* loaded from: classes6.dex */
public final class Accessories {

    /* renamed from: com.amazon.alexa.accessory.protocol.Accessories$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase = new int[ControlEnvelope.PayloadCase.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.RESPONSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.RESET_CONNECTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SYNCHRONIZE_SETTINGS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.KEEP_ALIVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.REMOVE_DEVICE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_USERS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.UPDATE_USERS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.CONNECT_USER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.DISCONNECT_USER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.UNPAIR_USER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_CURRENT_USER.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SWITCH_CURRENT_USER.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_LOCALES.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SET_LOCALE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.LAUNCH_APP.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SET_WAKEWORDS.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_WAKEWORDS.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.UPGRADE_TRANSPORT.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SWITCH_TRANSPORT.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.START_SPEECH.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.PROVIDE_SPEECH.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.STOP_SPEECH.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.ENDPOINT_SPEECH.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.NOTIFY_SPEECH_STATE.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.FORWARD_AT_COMMAND.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.INCOMING_CALL.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_CENTRAL_INFORMATION.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_DEVICE_INFORMATION.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_DEVICE_CONFIGURATION.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.OVERRIDE_ASSISTANT.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.START_SETUP.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.COMPLETE_SETUP.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.NOTIFY_DEVICE_CONFIGURATION.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.UPDATE_DEVICE_INFORMATION.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.NOTIFY_DEVICE_INFORMATION.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_DEVICE_FEATURES.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_DIAGNOSTICS.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.STOP_DIAGNOSTICS.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.NOTIFY_DIAGNOSTICS_AVAILABLE.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_FIRMWARE_INFORMATION.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_CACHED_COMPONENT.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.RESET_CACHED_COMPONENT.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.UPDATE_COMPONENT_SEGMENT.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.APPLY_FIRMWARE.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_FIRMWARE_UPDATE_PREFERENCES.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_DEVICE_ARTIFACTS.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_ARTIFACT_FILTER.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_ARTIFACT_UPDATE_PREFERENCE.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.START_FIRMWARE_UPDATE.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.VERIFY_ARTIFACT_SIGNATURE.ordinal()] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.INITIATE_FIRMWARE_UPDATE.ordinal()] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.NOTIFY_FIRMWARE_INFORMATION.ordinal()] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.FIRMWARE_UPDATE_UNAVAILABLE.ordinal()] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.ISSUE_MEDIA_CONTROL.ordinal()] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_PLAYBACK_STATUS.ordinal()] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.REGISTER_FOR_MEDIA_EVENTS.ordinal()] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.MEDIA_EVENT_OCCURRED.ordinal()] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.PUSH_METRICS.ordinal()] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.UPDATE_METRICS_MAP.ordinal()] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_STATE.ordinal()] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SET_STATE.ordinal()] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SYNCHRONIZE_STATE.ordinal()] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_FITNESS_DATA.ordinal()] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.STOP_FITNESS_DATA.ordinal()] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.NOTIFY_FITNESS_DATA_AVAILABLE.ordinal()] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SYNC_FITNESS_SESSION.ordinal()] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.START_LIVE_FITNESS_DATA.ordinal()] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.STOP_LIVE_FITNESS_DATA.ordinal()] = 68;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.LIVE_FITNESS_DATA.ordinal()] = 69;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SUBSCRIBE_NOTIFICATION_CENTER.ordinal()] = 70;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.UNSUBSCRIBE_NOTIFICATION_CENTER.ordinal()] = 71;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.PUBLISH_CENTRAL_NOTIFICATION.ordinal()] = 72;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_CENTRAL_NOTIFICATION_ATTRIBUTES.ordinal()] = 73;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_CENTRAL_NOTIFICATION_APP_ATTRIBUTES.ordinal()] = 74;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.PERFORM_CENTRAL_NOTIFICATION_ACTION.ordinal()] = 75;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.UPDATE_CENTRAL_NOTIFICATION_ATTRIBUTES.ordinal()] = 76;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.ISSUE_INPUT_EVENT.ordinal()] = 77;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SET_INPUT_BEHAVIOR.ordinal()] = 78;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_INPUT_BEHAVIOR.ordinal()] = 79;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.RESET_INPUT_BEHAVIOR.ordinal()] = 80;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.PRINT_DEBUG.ordinal()] = 81;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.ISSUE_REMOTE_COMMAND.ordinal()] = 82;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.ISSUE_REMOTE_RESTART.ordinal()] = 83;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.ISSUE_REMOTE_RESET.ordinal()] = 84;
            } catch (NoSuchFieldError unused84) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.ISSUE_REMOTE_CLEAR_PAIRING.ordinal()] = 85;
            } catch (NoSuchFieldError unused85) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.START_TRANSLATION.ordinal()] = 86;
            } catch (NoSuchFieldError unused86) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.PROVIDE_TRANSLATION.ordinal()] = 87;
            } catch (NoSuchFieldError unused87) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.STOP_TRANSLATION.ordinal()] = 88;
            } catch (NoSuchFieldError unused88) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_BULK_DATA_MANIFEST.ordinal()] = 89;
            } catch (NoSuchFieldError unused89) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.BULK_DATA_MANIFEST_ENTRY.ordinal()] = 90;
            } catch (NoSuchFieldError unused90) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.REQUEST_BULK_DATA_TRANSFER.ordinal()] = 91;
            } catch (NoSuchFieldError unused91) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.BULK_DATA_TRANSFER_START.ordinal()] = 92;
            } catch (NoSuchFieldError unused92) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.BULK_DATA_TRANSFER_COMPLETE.ordinal()] = 93;
            } catch (NoSuchFieldError unused93) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.STOP_BULK_DATA_TRANSFER.ordinal()] = 94;
            } catch (NoSuchFieldError unused94) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.NOTIFY_BULK_DATA_AVAILABLE.ordinal()] = 95;
            } catch (NoSuchFieldError unused95) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.INITIATE_HANDSHAKE.ordinal()] = 96;
            } catch (NoSuchFieldError unused96) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.COMPLETE_HANDSHAKE.ordinal()] = 97;
            } catch (NoSuchFieldError unused97) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.USER_CONFIRMED.ordinal()] = 98;
            } catch (NoSuchFieldError unused98) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.RESET_KEY.ordinal()] = 99;
            } catch (NoSuchFieldError unused99) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.CONFIRM_RESET_KEY.ordinal()] = 100;
            } catch (NoSuchFieldError unused100) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.RESET_ROOT_KEYS.ordinal()] = 101;
            } catch (NoSuchFieldError unused101) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_AUDIOGRAM.ordinal()] = 102;
            } catch (NoSuchFieldError unused102) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SET_AUDIOGRAM.ordinal()] = 103;
            } catch (NoSuchFieldError unused103) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT.ordinal()] = 104;
            } catch (NoSuchFieldError unused104) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT.ordinal()] = 105;
            } catch (NoSuchFieldError unused105) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_CBL_LOGIN_STATE.ordinal()] = 106;
            } catch (NoSuchFieldError unused106) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_CBL_INFORMATION.ordinal()] = 107;
            } catch (NoSuchFieldError unused107) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.NOTIFY_CBL_LOGIN_STATE.ordinal()] = 108;
            } catch (NoSuchFieldError unused108) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_SMS_MESSAGE_LIST_RESPONSE.ordinal()] = 109;
            } catch (NoSuchFieldError unused109) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.NOTIFY_SMS_MESSAGE_LIST.ordinal()] = 110;
            } catch (NoSuchFieldError unused110) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_SMS_MESSAGE_LIST.ordinal()] = 111;
            } catch (NoSuchFieldError unused111) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SEND_SMS.ordinal()] = 112;
            } catch (NoSuchFieldError unused112) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SET_SMS_READ_STATUS.ordinal()] = 113;
            } catch (NoSuchFieldError unused113) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.END_OF_SMS_MESSAGE_LIST_RESPONSE.ordinal()] = 114;
            } catch (NoSuchFieldError unused114) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.INITIATE_MAP_CONNECTION.ordinal()] = 115;
            } catch (NoSuchFieldError unused115) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.START_VOICE_STREAM.ordinal()] = 116;
            } catch (NoSuchFieldError unused116) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.STOP_VOICE_STREAM.ordinal()] = 117;
            } catch (NoSuchFieldError unused117) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.DISPLAY_CONTENT.ordinal()] = 118;
            } catch (NoSuchFieldError unused118) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.UPDATE_CALL_STATE.ordinal()] = 119;
            } catch (NoSuchFieldError unused119) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.ACCEPT_CALL.ordinal()] = 120;
            } catch (NoSuchFieldError unused120) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.REJECT_CALL.ordinal()] = 121;
            } catch (NoSuchFieldError unused121) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.END_CALL.ordinal()] = 122;
            } catch (NoSuchFieldError unused122) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.ADD_NOTIFICATION.ordinal()] = 123;
            } catch (NoSuchFieldError unused123) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.UPDATE_NOTIFICATION.ordinal()] = 124;
            } catch (NoSuchFieldError unused124) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.REMOVE_NOTIFICATION.ordinal()] = 125;
            } catch (NoSuchFieldError unused125) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.EXECUTE_NOTIFICATION_ACTION.ordinal()] = 126;
            } catch (NoSuchFieldError unused126) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_CLOUD_PAIRING_ATTRIBUTES.ordinal()] = 127;
            } catch (NoSuchFieldError unused127) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.GET_CLOUD_PAIRING_STATUS.ordinal()] = 128;
            } catch (NoSuchFieldError unused128) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.SET_CLOUD_PAIRING_KEYS.ordinal()] = 129;
            } catch (NoSuchFieldError unused129) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.REPLACE_CLOUD_PAIRING_KEYS.ordinal()] = 130;
            } catch (NoSuchFieldError unused130) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.REMOVE_CLOUD_PAIRING_KEYS.ordinal()] = 131;
            } catch (NoSuchFieldError unused131) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$ControlEnvelope$PayloadCase[ControlEnvelope.PayloadCase.PAYLOAD_NOT_SET.ordinal()] = 132;
            } catch (NoSuchFieldError unused132) {
            }
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused133) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError unused134) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused135) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused136) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused137) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError unused138) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError unused139) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError unused140) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 9;
            } catch (NoSuchFieldError unused141) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 10;
            } catch (NoSuchFieldError unused142) {
            }
            $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase = new int[Response.PayloadCase.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.USER.ordinal()] = 1;
            } catch (NoSuchFieldError unused143) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.USERS.ordinal()] = 2;
            } catch (NoSuchFieldError unused144) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.WAKEWORDS.ordinal()] = 3;
            } catch (NoSuchFieldError unused145) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.LOCALES.ordinal()] = 4;
            } catch (NoSuchFieldError unused146) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.CONNECTION_DETAILS.ordinal()] = 5;
            } catch (NoSuchFieldError unused147) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.DIALOG.ordinal()] = 6;
            } catch (NoSuchFieldError unused148) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.SPEECH_PROVIDER.ordinal()] = 7;
            } catch (NoSuchFieldError unused149) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.CENTRAL_INFORMATION.ordinal()] = 8;
            } catch (NoSuchFieldError unused150) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.DEVICE_INFORMATION.ordinal()] = 9;
            } catch (NoSuchFieldError unused151) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.DEVICE_CONFIGURATION.ordinal()] = 10;
            } catch (NoSuchFieldError unused152) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.DEVICE_FEATURES.ordinal()] = 11;
            } catch (NoSuchFieldError unused153) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.DIAGNOSTICS.ordinal()] = 12;
            } catch (NoSuchFieldError unused154) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.FIRMWARE_COMPONENT.ordinal()] = 13;
            } catch (NoSuchFieldError unused155) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.FIRMWARE_INFORMATION.ordinal()] = 14;
            } catch (NoSuchFieldError unused156) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.FIRMWARE_UPDATE_PREFERENCES.ordinal()] = 15;
            } catch (NoSuchFieldError unused157) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.ARTIFACT_LIST.ordinal()] = 16;
            } catch (NoSuchFieldError unused158) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.ARTIFACT_FILTER.ordinal()] = 17;
            } catch (NoSuchFieldError unused159) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.ARTIFACT_UPDATE_PREFERENCE.ordinal()] = 18;
            } catch (NoSuchFieldError unused160) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.PLAYBACK_STATUS.ordinal()] = 19;
            } catch (NoSuchFieldError unused161) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.STATE.ordinal()] = 20;
            } catch (NoSuchFieldError unused162) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.FITNESS_DATA.ordinal()] = 21;
            } catch (NoSuchFieldError unused163) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.NOTIFICATION_CENTER_INFORMATION.ordinal()] = 22;
            } catch (NoSuchFieldError unused164) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.CENTRAL_NOTIFICATION_ATTRIBUTES.ordinal()] = 23;
            } catch (NoSuchFieldError unused165) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.CENTRAL_NOTIFICATION_APP_ATTRIBUTES.ordinal()] = 24;
            } catch (NoSuchFieldError unused166) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.INPUT_BEHAVIOR_CONFIGURATION_SET.ordinal()] = 25;
            } catch (NoSuchFieldError unused167) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.ACKNOWLEDGE_HANDSHAKE.ordinal()] = 26;
            } catch (NoSuchFieldError unused168) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.ACKNOWLEDGE_RESET_KEY.ordinal()] = 27;
            } catch (NoSuchFieldError unused169) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.AUDIOGRAM.ordinal()] = 28;
            } catch (NoSuchFieldError unused170) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.MEDIA_ENHANCEMENT_CORRECTION_AMOUNT.ordinal()] = 29;
            } catch (NoSuchFieldError unused171) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.CBL_LOGIN_STATE.ordinal()] = 30;
            } catch (NoSuchFieldError unused172) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.CBL_INFORMATION.ordinal()] = 31;
            } catch (NoSuchFieldError unused173) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.VOICE_PROVIDER.ordinal()] = 32;
            } catch (NoSuchFieldError unused174) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.CLOUD_PAIRING_ATTRIBUTES.ordinal()] = 33;
            } catch (NoSuchFieldError unused175) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.CLOUD_PAIRING_STATUS.ordinal()] = 34;
            } catch (NoSuchFieldError unused176) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Accessories$Response$PayloadCase[Response.PayloadCase.PAYLOAD_NOT_SET.ordinal()] = 35;
            } catch (NoSuchFieldError unused177) {
            }
        }
    }

    /* loaded from: classes6.dex */
    public enum Command implements Internal.EnumLite {
        NONE(0),
        RESET_CONNECTION(51),
        SYNCHRONIZE_SETTINGS(50),
        KEEP_ALIVE(55),
        REMOVE_DEVICE(56),
        GET_USERS(52),
        UPDATE_USERS(61),
        CONNECT_USER(62),
        DISCONNECT_USER(63),
        UNPAIR_USER(64),
        GET_CURRENT_USER(54),
        SWITCH_CURRENT_USER(53),
        GET_LOCALES(57),
        SET_LOCALE(58),
        LAUNCH_APP(59),
        SET_WAKEWORDS(70),
        GET_WAKEWORDS(71),
        UPGRADE_TRANSPORT(30),
        SWITCH_TRANSPORT(31),
        START_SPEECH(11),
        PROVIDE_SPEECH(10),
        STOP_SPEECH(12),
        ENDPOINT_SPEECH(13),
        NOTIFY_SPEECH_STATE(14),
        FORWARD_AT_COMMAND(40),
        INCOMING_CALL(41),
        GET_CENTRAL_INFORMATION(103),
        GET_DEVICE_INFORMATION(20),
        GET_DEVICE_CONFIGURATION(21),
        OVERRIDE_ASSISTANT(22),
        START_SETUP(23),
        COMPLETE_SETUP(24),
        NOTIFY_DEVICE_CONFIGURATION(25),
        UPDATE_DEVICE_INFORMATION(26),
        NOTIFY_DEVICE_INFORMATION(27),
        GET_DEVICE_FEATURES(28),
        GET_DIAGNOSTICS(110),
        STOP_DIAGNOSTICS(111),
        NOTIFY_DIAGNOSTICS_AVAILABLE(112),
        GET_FIRMWARE_INFORMATION(90),
        GET_CACHED_COMPONENT(92),
        RESET_CACHED_COMPONENT(93),
        UPDATE_COMPONENT_SEGMENT(94),
        APPLY_FIRMWARE(95),
        GET_FIRMWARE_UPDATE_PREFERENCES(91),
        GET_DEVICE_ARTIFACTS(96),
        GET_ARTIFACT_FILTER(97),
        GET_ARTIFACT_UPDATE_PREFERENCE(98),
        START_FIRMWARE_UPDATE(99),
        VERIFY_ARTIFACT_SIGNATURE(89),
        INITIATE_FIRMWARE_UPDATE(88),
        NOTIFY_FIRMWARE_INFORMATION(87),
        FIRMWARE_UPDATE_UNAVAILABLE(86),
        ISSUE_MEDIA_CONTROL(60),
        GET_PLAYBACK_STATUS(65),
        REGISTER_FOR_MEDIA_EVENTS(66),
        MEDIA_EVENT_OCCURRED(67),
        PUSH_METRICS(120),
        UPDATE_METRICS_MAP(121),
        GET_STATE(100),
        SET_STATE(101),
        SYNCHRONIZE_STATE(102),
        GET_FITNESS_DATA(130),
        STOP_FITNESS_DATA(131),
        NOTIFY_FITNESS_DATA_AVAILABLE(132),
        SYNC_FITNESS_SESSION(133),
        START_LIVE_FITNESS_DATA(134),
        STOP_LIVE_FITNESS_DATA(135),
        LIVE_FITNESS_DATA(136),
        SUBSCRIBE_NOTIFICATION_CENTER(150),
        UNSUBSCRIBE_NOTIFICATION_CENTER(151),
        PUBLISH_CENTRAL_NOTIFICATION(152),
        GET_CENTRAL_NOTIFICATION_ATTRIBUTES(153),
        GET_CENTRAL_NOTIFICATION_APP_ATTRIBUTES(154),
        PERFORM_CENTRAL_NOTIFICATION_ACTION(155),
        UPDATE_CENTRAL_NOTIFICATION_ATTRIBUTES(156),
        ISSUE_INPUT_EVENT(160),
        SET_INPUT_BEHAVIOR(161),
        GET_INPUT_BEHAVIOR(162),
        RESET_INPUT_BEHAVIOR(168),
        PRINT_DEBUG(163),
        ISSUE_REMOTE_COMMAND(164),
        ISSUE_REMOTE_RESTART(165),
        ISSUE_REMOTE_RESET(166),
        ISSUE_REMOTE_CLEAR_PAIRING(167),
        START_TRANSLATION(170),
        PROVIDE_TRANSLATION(171),
        STOP_TRANSLATION(172),
        GET_BULK_DATA_MANIFEST(180),
        BULK_DATA_MANIFEST_ENTRY(181),
        REQUEST_BULK_DATA_TRANSFER(182),
        BULK_DATA_TRANSFER_START(183),
        BULK_DATA_TRANSFER_COMPLETE(184),
        STOP_BULK_DATA_TRANSFER(185),
        NOTIFY_BULK_DATA_AVAILABLE(186),
        INITIATE_HANDSHAKE(200),
        COMPLETE_HANDSHAKE(201),
        USER_CONFIRMED(202),
        RESET_KEY(203),
        CONFIRM_RESET_KEY(204),
        RESET_ROOT_KEYS(205),
        GET_AUDIOGRAM(300),
        SET_AUDIOGRAM(301),
        GET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT(302),
        SET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT(303),
        GET_CBL_LOGIN_STATE(230),
        GET_CBL_INFORMATION(231),
        NOTIFY_CBL_LOGIN_STATE(232),
        GET_SMS_MESSAGE_LIST_RESPONSE(350),
        NOTIFY_SMS_MESSAGE_LIST(351),
        GET_SMS_MESSAGE_LIST(352),
        SEND_SMS(353),
        SET_SMS_READ_STATUS(354),
        END_OF_SMS_MESSAGE_LIST_RESPONSE(355),
        INITIATE_MAP_CONNECTION(356),
        START_VOICE_STREAM(360),
        STOP_VOICE_STREAM(361),
        DISPLAY_CONTENT(80),
        UPDATE_CALL_STATE(140),
        ACCEPT_CALL(141),
        REJECT_CALL(142),
        END_CALL(143),
        ADD_NOTIFICATION(190),
        UPDATE_NOTIFICATION(191),
        REMOVE_NOTIFICATION(192),
        EXECUTE_NOTIFICATION_ACTION(193),
        GET_CLOUD_PAIRING_ATTRIBUTES(370),
        GET_CLOUD_PAIRING_STATUS(371),
        SET_CLOUD_PAIRING_KEYS(372),
        REPLACE_CLOUD_PAIRING_KEYS(373),
        REMOVE_CLOUD_PAIRING_KEYS(374),
        UNRECOGNIZED(-1);
        
        public static final int ACCEPT_CALL_VALUE = 141;
        public static final int ADD_NOTIFICATION_VALUE = 190;
        public static final int APPLY_FIRMWARE_VALUE = 95;
        public static final int BULK_DATA_MANIFEST_ENTRY_VALUE = 181;
        public static final int BULK_DATA_TRANSFER_COMPLETE_VALUE = 184;
        public static final int BULK_DATA_TRANSFER_START_VALUE = 183;
        public static final int COMPLETE_HANDSHAKE_VALUE = 201;
        public static final int COMPLETE_SETUP_VALUE = 24;
        public static final int CONFIRM_RESET_KEY_VALUE = 204;
        public static final int CONNECT_USER_VALUE = 62;
        public static final int DISCONNECT_USER_VALUE = 63;
        public static final int DISPLAY_CONTENT_VALUE = 80;
        public static final int ENDPOINT_SPEECH_VALUE = 13;
        public static final int END_CALL_VALUE = 143;
        public static final int END_OF_SMS_MESSAGE_LIST_RESPONSE_VALUE = 355;
        public static final int EXECUTE_NOTIFICATION_ACTION_VALUE = 193;
        public static final int FIRMWARE_UPDATE_UNAVAILABLE_VALUE = 86;
        public static final int FORWARD_AT_COMMAND_VALUE = 40;
        public static final int GET_ARTIFACT_FILTER_VALUE = 97;
        public static final int GET_ARTIFACT_UPDATE_PREFERENCE_VALUE = 98;
        public static final int GET_AUDIOGRAM_VALUE = 300;
        public static final int GET_BULK_DATA_MANIFEST_VALUE = 180;
        public static final int GET_CACHED_COMPONENT_VALUE = 92;
        public static final int GET_CBL_INFORMATION_VALUE = 231;
        public static final int GET_CBL_LOGIN_STATE_VALUE = 230;
        public static final int GET_CENTRAL_INFORMATION_VALUE = 103;
        public static final int GET_CENTRAL_NOTIFICATION_APP_ATTRIBUTES_VALUE = 154;
        public static final int GET_CENTRAL_NOTIFICATION_ATTRIBUTES_VALUE = 153;
        public static final int GET_CLOUD_PAIRING_ATTRIBUTES_VALUE = 370;
        public static final int GET_CLOUD_PAIRING_STATUS_VALUE = 371;
        public static final int GET_CURRENT_USER_VALUE = 54;
        public static final int GET_DEVICE_ARTIFACTS_VALUE = 96;
        public static final int GET_DEVICE_CONFIGURATION_VALUE = 21;
        public static final int GET_DEVICE_FEATURES_VALUE = 28;
        public static final int GET_DEVICE_INFORMATION_VALUE = 20;
        public static final int GET_DIAGNOSTICS_VALUE = 110;
        public static final int GET_FIRMWARE_INFORMATION_VALUE = 90;
        public static final int GET_FIRMWARE_UPDATE_PREFERENCES_VALUE = 91;
        public static final int GET_FITNESS_DATA_VALUE = 130;
        public static final int GET_INPUT_BEHAVIOR_VALUE = 162;
        public static final int GET_LOCALES_VALUE = 57;
        public static final int GET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT_VALUE = 302;
        public static final int GET_PLAYBACK_STATUS_VALUE = 65;
        public static final int GET_SMS_MESSAGE_LIST_RESPONSE_VALUE = 350;
        public static final int GET_SMS_MESSAGE_LIST_VALUE = 352;
        public static final int GET_STATE_VALUE = 100;
        public static final int GET_USERS_VALUE = 52;
        public static final int GET_WAKEWORDS_VALUE = 71;
        public static final int INCOMING_CALL_VALUE = 41;
        public static final int INITIATE_FIRMWARE_UPDATE_VALUE = 88;
        public static final int INITIATE_HANDSHAKE_VALUE = 200;
        public static final int INITIATE_MAP_CONNECTION_VALUE = 356;
        public static final int ISSUE_INPUT_EVENT_VALUE = 160;
        public static final int ISSUE_MEDIA_CONTROL_VALUE = 60;
        public static final int ISSUE_REMOTE_CLEAR_PAIRING_VALUE = 167;
        public static final int ISSUE_REMOTE_COMMAND_VALUE = 164;
        public static final int ISSUE_REMOTE_RESET_VALUE = 166;
        public static final int ISSUE_REMOTE_RESTART_VALUE = 165;
        public static final int KEEP_ALIVE_VALUE = 55;
        public static final int LAUNCH_APP_VALUE = 59;
        public static final int LIVE_FITNESS_DATA_VALUE = 136;
        public static final int MEDIA_EVENT_OCCURRED_VALUE = 67;
        public static final int NONE_VALUE = 0;
        public static final int NOTIFY_BULK_DATA_AVAILABLE_VALUE = 186;
        public static final int NOTIFY_CBL_LOGIN_STATE_VALUE = 232;
        public static final int NOTIFY_DEVICE_CONFIGURATION_VALUE = 25;
        public static final int NOTIFY_DEVICE_INFORMATION_VALUE = 27;
        public static final int NOTIFY_DIAGNOSTICS_AVAILABLE_VALUE = 112;
        public static final int NOTIFY_FIRMWARE_INFORMATION_VALUE = 87;
        public static final int NOTIFY_FITNESS_DATA_AVAILABLE_VALUE = 132;
        public static final int NOTIFY_SMS_MESSAGE_LIST_VALUE = 351;
        public static final int NOTIFY_SPEECH_STATE_VALUE = 14;
        public static final int OVERRIDE_ASSISTANT_VALUE = 22;
        public static final int PERFORM_CENTRAL_NOTIFICATION_ACTION_VALUE = 155;
        public static final int PRINT_DEBUG_VALUE = 163;
        public static final int PROVIDE_SPEECH_VALUE = 10;
        public static final int PROVIDE_TRANSLATION_VALUE = 171;
        public static final int PUBLISH_CENTRAL_NOTIFICATION_VALUE = 152;
        public static final int PUSH_METRICS_VALUE = 120;
        public static final int REGISTER_FOR_MEDIA_EVENTS_VALUE = 66;
        public static final int REJECT_CALL_VALUE = 142;
        public static final int REMOVE_CLOUD_PAIRING_KEYS_VALUE = 374;
        public static final int REMOVE_DEVICE_VALUE = 56;
        public static final int REMOVE_NOTIFICATION_VALUE = 192;
        public static final int REPLACE_CLOUD_PAIRING_KEYS_VALUE = 373;
        public static final int REQUEST_BULK_DATA_TRANSFER_VALUE = 182;
        public static final int RESET_CACHED_COMPONENT_VALUE = 93;
        public static final int RESET_CONNECTION_VALUE = 51;
        public static final int RESET_INPUT_BEHAVIOR_VALUE = 168;
        public static final int RESET_KEY_VALUE = 203;
        public static final int RESET_ROOT_KEYS_VALUE = 205;
        public static final int SEND_SMS_VALUE = 353;
        public static final int SET_AUDIOGRAM_VALUE = 301;
        public static final int SET_CLOUD_PAIRING_KEYS_VALUE = 372;
        public static final int SET_INPUT_BEHAVIOR_VALUE = 161;
        public static final int SET_LOCALE_VALUE = 58;
        public static final int SET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT_VALUE = 303;
        public static final int SET_SMS_READ_STATUS_VALUE = 354;
        public static final int SET_STATE_VALUE = 101;
        public static final int SET_WAKEWORDS_VALUE = 70;
        public static final int START_FIRMWARE_UPDATE_VALUE = 99;
        public static final int START_LIVE_FITNESS_DATA_VALUE = 134;
        public static final int START_SETUP_VALUE = 23;
        public static final int START_SPEECH_VALUE = 11;
        public static final int START_TRANSLATION_VALUE = 170;
        public static final int START_VOICE_STREAM_VALUE = 360;
        public static final int STOP_BULK_DATA_TRANSFER_VALUE = 185;
        public static final int STOP_DIAGNOSTICS_VALUE = 111;
        public static final int STOP_FITNESS_DATA_VALUE = 131;
        public static final int STOP_LIVE_FITNESS_DATA_VALUE = 135;
        public static final int STOP_SPEECH_VALUE = 12;
        public static final int STOP_TRANSLATION_VALUE = 172;
        public static final int STOP_VOICE_STREAM_VALUE = 361;
        public static final int SUBSCRIBE_NOTIFICATION_CENTER_VALUE = 150;
        public static final int SWITCH_CURRENT_USER_VALUE = 53;
        public static final int SWITCH_TRANSPORT_VALUE = 31;
        public static final int SYNCHRONIZE_SETTINGS_VALUE = 50;
        public static final int SYNCHRONIZE_STATE_VALUE = 102;
        public static final int SYNC_FITNESS_SESSION_VALUE = 133;
        public static final int UNPAIR_USER_VALUE = 64;
        public static final int UNSUBSCRIBE_NOTIFICATION_CENTER_VALUE = 151;
        public static final int UPDATE_CALL_STATE_VALUE = 140;
        public static final int UPDATE_CENTRAL_NOTIFICATION_ATTRIBUTES_VALUE = 156;
        public static final int UPDATE_COMPONENT_SEGMENT_VALUE = 94;
        public static final int UPDATE_DEVICE_INFORMATION_VALUE = 26;
        public static final int UPDATE_METRICS_MAP_VALUE = 121;
        public static final int UPDATE_NOTIFICATION_VALUE = 191;
        public static final int UPDATE_USERS_VALUE = 61;
        public static final int UPGRADE_TRANSPORT_VALUE = 30;
        public static final int USER_CONFIRMED_VALUE = 202;
        public static final int VERIFY_ARTIFACT_SIGNATURE_VALUE = 89;
        private static final Internal.EnumLiteMap<Command> internalValueMap = new Internal.EnumLiteMap<Command>() { // from class: com.amazon.alexa.accessory.protocol.Accessories.Command.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            /* renamed from: findValueByNumber */
            public Command mo9850findValueByNumber(int i) {
                return Command.forNumber(i);
            }
        };
        private final int value;

        Command(int i) {
            this.value = i;
        }

        public static Command forNumber(int i) {
            if (i != 0) {
                if (i == 30) {
                    return UPGRADE_TRANSPORT;
                }
                if (i == 31) {
                    return SWITCH_TRANSPORT;
                }
                if (i == 40) {
                    return FORWARD_AT_COMMAND;
                }
                if (i == 41) {
                    return INCOMING_CALL;
                }
                if (i == 70) {
                    return SET_WAKEWORDS;
                }
                if (i == 71) {
                    return GET_WAKEWORDS;
                }
                if (i == 120) {
                    return PUSH_METRICS;
                }
                if (i != 121) {
                    switch (i) {
                        case 0:
                            break;
                        case 50:
                            return SYNCHRONIZE_SETTINGS;
                        case 51:
                            return RESET_CONNECTION;
                        case 52:
                            return GET_USERS;
                        case 53:
                            return SWITCH_CURRENT_USER;
                        case 54:
                            return GET_CURRENT_USER;
                        case 55:
                            return KEEP_ALIVE;
                        case 56:
                            return REMOVE_DEVICE;
                        case 57:
                            return GET_LOCALES;
                        case 58:
                            return SET_LOCALE;
                        case 59:
                            return LAUNCH_APP;
                        case 60:
                            return ISSUE_MEDIA_CONTROL;
                        case 61:
                            return UPDATE_USERS;
                        case 62:
                            return CONNECT_USER;
                        case 63:
                            return DISCONNECT_USER;
                        case 64:
                            return UNPAIR_USER;
                        case 65:
                            return GET_PLAYBACK_STATUS;
                        case 66:
                            return REGISTER_FOR_MEDIA_EVENTS;
                        case 67:
                            return MEDIA_EVENT_OCCURRED;
                        case 80:
                            return DISPLAY_CONTENT;
                        case 140:
                            return UPDATE_CALL_STATE;
                        case 141:
                            return ACCEPT_CALL;
                        case 142:
                            return REJECT_CALL;
                        case 143:
                            return END_CALL;
                        case 150:
                            return SUBSCRIBE_NOTIFICATION_CENTER;
                        case 151:
                            return UNSUBSCRIBE_NOTIFICATION_CENTER;
                        case 152:
                            return PUBLISH_CENTRAL_NOTIFICATION;
                        case 153:
                            return GET_CENTRAL_NOTIFICATION_ATTRIBUTES;
                        case 154:
                            return GET_CENTRAL_NOTIFICATION_APP_ATTRIBUTES;
                        case 155:
                            return PERFORM_CENTRAL_NOTIFICATION_ACTION;
                        case 156:
                            return UPDATE_CENTRAL_NOTIFICATION_ATTRIBUTES;
                        case 160:
                            return ISSUE_INPUT_EVENT;
                        case 161:
                            return SET_INPUT_BEHAVIOR;
                        case 162:
                            return GET_INPUT_BEHAVIOR;
                        case 163:
                            return PRINT_DEBUG;
                        case 164:
                            return ISSUE_REMOTE_COMMAND;
                        case 165:
                            return ISSUE_REMOTE_RESTART;
                        case 166:
                            return ISSUE_REMOTE_RESET;
                        case 167:
                            return ISSUE_REMOTE_CLEAR_PAIRING;
                        case 168:
                            return RESET_INPUT_BEHAVIOR;
                        case 170:
                            return START_TRANSLATION;
                        case 171:
                            return PROVIDE_TRANSLATION;
                        case 172:
                            return STOP_TRANSLATION;
                        case 180:
                            return GET_BULK_DATA_MANIFEST;
                        case 181:
                            return BULK_DATA_MANIFEST_ENTRY;
                        case 182:
                            return REQUEST_BULK_DATA_TRANSFER;
                        case 183:
                            return BULK_DATA_TRANSFER_START;
                        case 184:
                            return BULK_DATA_TRANSFER_COMPLETE;
                        case 185:
                            return STOP_BULK_DATA_TRANSFER;
                        case 186:
                            return NOTIFY_BULK_DATA_AVAILABLE;
                        case 190:
                            return ADD_NOTIFICATION;
                        case 191:
                            return UPDATE_NOTIFICATION;
                        case 192:
                            return REMOVE_NOTIFICATION;
                        case 193:
                            return EXECUTE_NOTIFICATION_ACTION;
                        case 200:
                            return INITIATE_HANDSHAKE;
                        case 201:
                            return COMPLETE_HANDSHAKE;
                        case 202:
                            return USER_CONFIRMED;
                        case 203:
                            return RESET_KEY;
                        case 204:
                            return CONFIRM_RESET_KEY;
                        case 205:
                            return RESET_ROOT_KEYS;
                        case 230:
                            return GET_CBL_LOGIN_STATE;
                        case 231:
                            return GET_CBL_INFORMATION;
                        case 232:
                            return NOTIFY_CBL_LOGIN_STATE;
                        case 300:
                            return GET_AUDIOGRAM;
                        case 301:
                            return SET_AUDIOGRAM;
                        case 302:
                            return GET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT;
                        case 303:
                            return SET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT;
                        case 350:
                            return GET_SMS_MESSAGE_LIST_RESPONSE;
                        case 351:
                            return NOTIFY_SMS_MESSAGE_LIST;
                        case 352:
                            return GET_SMS_MESSAGE_LIST;
                        case 353:
                            return SEND_SMS;
                        case 354:
                            return SET_SMS_READ_STATUS;
                        case 355:
                            return END_OF_SMS_MESSAGE_LIST_RESPONSE;
                        case 356:
                            return INITIATE_MAP_CONNECTION;
                        case 360:
                            return START_VOICE_STREAM;
                        case 361:
                            return STOP_VOICE_STREAM;
                        case 370:
                            return GET_CLOUD_PAIRING_ATTRIBUTES;
                        case 371:
                            return GET_CLOUD_PAIRING_STATUS;
                        case 372:
                            return SET_CLOUD_PAIRING_KEYS;
                        case 373:
                            return REPLACE_CLOUD_PAIRING_KEYS;
                        case 374:
                            return REMOVE_CLOUD_PAIRING_KEYS;
                        default:
                            switch (i) {
                                case 10:
                                    return PROVIDE_SPEECH;
                                case 11:
                                    return START_SPEECH;
                                case 12:
                                    return STOP_SPEECH;
                                case 13:
                                    return ENDPOINT_SPEECH;
                                case 14:
                                    return NOTIFY_SPEECH_STATE;
                                default:
                                    switch (i) {
                                        case 20:
                                            return GET_DEVICE_INFORMATION;
                                        case 21:
                                            return GET_DEVICE_CONFIGURATION;
                                        case 22:
                                            return OVERRIDE_ASSISTANT;
                                        case 23:
                                            return START_SETUP;
                                        case 24:
                                            return COMPLETE_SETUP;
                                        case 25:
                                            return NOTIFY_DEVICE_CONFIGURATION;
                                        case 26:
                                            return UPDATE_DEVICE_INFORMATION;
                                        case 27:
                                            return NOTIFY_DEVICE_INFORMATION;
                                        case 28:
                                            return GET_DEVICE_FEATURES;
                                        default:
                                            switch (i) {
                                                case 86:
                                                    return FIRMWARE_UPDATE_UNAVAILABLE;
                                                case 87:
                                                    return NOTIFY_FIRMWARE_INFORMATION;
                                                case 88:
                                                    return INITIATE_FIRMWARE_UPDATE;
                                                case 89:
                                                    return VERIFY_ARTIFACT_SIGNATURE;
                                                case 90:
                                                    return GET_FIRMWARE_INFORMATION;
                                                case 91:
                                                    return GET_FIRMWARE_UPDATE_PREFERENCES;
                                                case 92:
                                                    return GET_CACHED_COMPONENT;
                                                case 93:
                                                    return RESET_CACHED_COMPONENT;
                                                case 94:
                                                    return UPDATE_COMPONENT_SEGMENT;
                                                case 95:
                                                    return APPLY_FIRMWARE;
                                                case 96:
                                                    return GET_DEVICE_ARTIFACTS;
                                                case 97:
                                                    return GET_ARTIFACT_FILTER;
                                                case 98:
                                                    return GET_ARTIFACT_UPDATE_PREFERENCE;
                                                case 99:
                                                    return START_FIRMWARE_UPDATE;
                                                case 100:
                                                    return GET_STATE;
                                                case 101:
                                                    return SET_STATE;
                                                case 102:
                                                    return SYNCHRONIZE_STATE;
                                                case 103:
                                                    return GET_CENTRAL_INFORMATION;
                                                default:
                                                    switch (i) {
                                                        case 110:
                                                            return GET_DIAGNOSTICS;
                                                        case 111:
                                                            return STOP_DIAGNOSTICS;
                                                        case 112:
                                                            return NOTIFY_DIAGNOSTICS_AVAILABLE;
                                                        default:
                                                            switch (i) {
                                                                case 130:
                                                                    return GET_FITNESS_DATA;
                                                                case 131:
                                                                    return STOP_FITNESS_DATA;
                                                                case 132:
                                                                    return NOTIFY_FITNESS_DATA_AVAILABLE;
                                                                case 133:
                                                                    return SYNC_FITNESS_SESSION;
                                                                case 134:
                                                                    return START_LIVE_FITNESS_DATA;
                                                                case 135:
                                                                    return STOP_LIVE_FITNESS_DATA;
                                                                case 136:
                                                                    return LIVE_FITNESS_DATA;
                                                                default:
                                                                    return null;
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
                } else {
                    return UPDATE_METRICS_MAP;
                }
            }
            return NONE;
        }

        public static Internal.EnumLiteMap<Command> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static Command valueOf(int i) {
            return forNumber(i);
        }
    }

    /* loaded from: classes6.dex */
    public static final class ControlEnvelope extends GeneratedMessageLite<ControlEnvelope, Builder> implements ControlEnvelopeOrBuilder {
        public static final int ACCEPT_CALL_FIELD_NUMBER = 141;
        public static final int ADD_NOTIFICATION_FIELD_NUMBER = 190;
        public static final int APPLY_FIRMWARE_FIELD_NUMBER = 95;
        public static final int BULK_DATA_MANIFEST_ENTRY_FIELD_NUMBER = 181;
        public static final int BULK_DATA_TRANSFER_COMPLETE_FIELD_NUMBER = 184;
        public static final int BULK_DATA_TRANSFER_START_FIELD_NUMBER = 183;
        public static final int COMMAND_FIELD_NUMBER = 1;
        public static final int COMPLETE_HANDSHAKE_FIELD_NUMBER = 201;
        public static final int COMPLETE_SETUP_FIELD_NUMBER = 24;
        public static final int CONFIRM_RESET_KEY_FIELD_NUMBER = 204;
        public static final int CONNECT_USER_FIELD_NUMBER = 62;
        private static final ControlEnvelope DEFAULT_INSTANCE = new ControlEnvelope();
        public static final int DISCONNECT_USER_FIELD_NUMBER = 63;
        public static final int DISPLAY_CONTENT_FIELD_NUMBER = 80;
        public static final int ENDPOINT_SPEECH_FIELD_NUMBER = 13;
        public static final int END_CALL_FIELD_NUMBER = 143;
        public static final int END_OF_SMS_MESSAGE_LIST_RESPONSE_FIELD_NUMBER = 355;
        public static final int EXECUTE_NOTIFICATION_ACTION_FIELD_NUMBER = 193;
        public static final int FIRMWARE_UPDATE_UNAVAILABLE_FIELD_NUMBER = 86;
        public static final int FORWARD_AT_COMMAND_FIELD_NUMBER = 40;
        public static final int GET_ARTIFACT_FILTER_FIELD_NUMBER = 97;
        public static final int GET_ARTIFACT_UPDATE_PREFERENCE_FIELD_NUMBER = 98;
        public static final int GET_AUDIOGRAM_FIELD_NUMBER = 300;
        public static final int GET_BULK_DATA_MANIFEST_FIELD_NUMBER = 180;
        public static final int GET_CACHED_COMPONENT_FIELD_NUMBER = 92;
        public static final int GET_CBL_INFORMATION_FIELD_NUMBER = 231;
        public static final int GET_CBL_LOGIN_STATE_FIELD_NUMBER = 230;
        public static final int GET_CENTRAL_INFORMATION_FIELD_NUMBER = 103;
        public static final int GET_CENTRAL_NOTIFICATION_APP_ATTRIBUTES_FIELD_NUMBER = 154;
        public static final int GET_CENTRAL_NOTIFICATION_ATTRIBUTES_FIELD_NUMBER = 153;
        public static final int GET_CLOUD_PAIRING_ATTRIBUTES_FIELD_NUMBER = 370;
        public static final int GET_CLOUD_PAIRING_STATUS_FIELD_NUMBER = 371;
        public static final int GET_CURRENT_USER_FIELD_NUMBER = 54;
        public static final int GET_DEVICE_ARTIFACTS_FIELD_NUMBER = 96;
        public static final int GET_DEVICE_CONFIGURATION_FIELD_NUMBER = 21;
        public static final int GET_DEVICE_FEATURES_FIELD_NUMBER = 28;
        public static final int GET_DEVICE_INFORMATION_FIELD_NUMBER = 20;
        public static final int GET_DIAGNOSTICS_FIELD_NUMBER = 110;
        public static final int GET_FIRMWARE_INFORMATION_FIELD_NUMBER = 90;
        public static final int GET_FIRMWARE_UPDATE_PREFERENCES_FIELD_NUMBER = 91;
        public static final int GET_FITNESS_DATA_FIELD_NUMBER = 130;
        public static final int GET_INPUT_BEHAVIOR_FIELD_NUMBER = 162;
        public static final int GET_LOCALES_FIELD_NUMBER = 57;
        public static final int GET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT_FIELD_NUMBER = 302;
        public static final int GET_PLAYBACK_STATUS_FIELD_NUMBER = 65;
        public static final int GET_SMS_MESSAGE_LIST_FIELD_NUMBER = 352;
        public static final int GET_SMS_MESSAGE_LIST_RESPONSE_FIELD_NUMBER = 350;
        public static final int GET_STATE_FIELD_NUMBER = 100;
        public static final int GET_USERS_FIELD_NUMBER = 52;
        public static final int GET_WAKEWORDS_FIELD_NUMBER = 71;
        public static final int INCOMING_CALL_FIELD_NUMBER = 41;
        public static final int INITIATE_FIRMWARE_UPDATE_FIELD_NUMBER = 88;
        public static final int INITIATE_HANDSHAKE_FIELD_NUMBER = 200;
        public static final int INITIATE_MAP_CONNECTION_FIELD_NUMBER = 356;
        public static final int ISSUE_INPUT_EVENT_FIELD_NUMBER = 160;
        public static final int ISSUE_MEDIA_CONTROL_FIELD_NUMBER = 60;
        public static final int ISSUE_REMOTE_CLEAR_PAIRING_FIELD_NUMBER = 167;
        public static final int ISSUE_REMOTE_COMMAND_FIELD_NUMBER = 164;
        public static final int ISSUE_REMOTE_RESET_FIELD_NUMBER = 166;
        public static final int ISSUE_REMOTE_RESTART_FIELD_NUMBER = 165;
        public static final int KEEP_ALIVE_FIELD_NUMBER = 55;
        public static final int LAUNCH_APP_FIELD_NUMBER = 59;
        public static final int LIVE_FITNESS_DATA_FIELD_NUMBER = 136;
        public static final int MEDIA_EVENT_OCCURRED_FIELD_NUMBER = 67;
        public static final int NOTIFY_BULK_DATA_AVAILABLE_FIELD_NUMBER = 186;
        public static final int NOTIFY_CBL_LOGIN_STATE_FIELD_NUMBER = 232;
        public static final int NOTIFY_DEVICE_CONFIGURATION_FIELD_NUMBER = 25;
        public static final int NOTIFY_DEVICE_INFORMATION_FIELD_NUMBER = 27;
        public static final int NOTIFY_DIAGNOSTICS_AVAILABLE_FIELD_NUMBER = 112;
        public static final int NOTIFY_FIRMWARE_INFORMATION_FIELD_NUMBER = 87;
        public static final int NOTIFY_FITNESS_DATA_AVAILABLE_FIELD_NUMBER = 132;
        public static final int NOTIFY_SMS_MESSAGE_LIST_FIELD_NUMBER = 351;
        public static final int NOTIFY_SPEECH_STATE_FIELD_NUMBER = 14;
        public static final int OVERRIDE_ASSISTANT_FIELD_NUMBER = 22;
        private static volatile Parser<ControlEnvelope> PARSER = null;
        public static final int PERFORM_CENTRAL_NOTIFICATION_ACTION_FIELD_NUMBER = 155;
        public static final int PRINT_DEBUG_FIELD_NUMBER = 163;
        public static final int PROVIDE_SPEECH_FIELD_NUMBER = 10;
        public static final int PROVIDE_TRANSLATION_FIELD_NUMBER = 171;
        public static final int PUBLISH_CENTRAL_NOTIFICATION_FIELD_NUMBER = 152;
        public static final int PUSH_METRICS_FIELD_NUMBER = 120;
        public static final int REGISTER_FOR_MEDIA_EVENTS_FIELD_NUMBER = 66;
        public static final int REJECT_CALL_FIELD_NUMBER = 142;
        public static final int REMOVE_CLOUD_PAIRING_KEYS_FIELD_NUMBER = 374;
        public static final int REMOVE_DEVICE_FIELD_NUMBER = 56;
        public static final int REMOVE_NOTIFICATION_FIELD_NUMBER = 192;
        public static final int REPLACE_CLOUD_PAIRING_KEYS_FIELD_NUMBER = 373;
        public static final int REQUEST_BULK_DATA_TRANSFER_FIELD_NUMBER = 182;
        public static final int RESET_CACHED_COMPONENT_FIELD_NUMBER = 93;
        public static final int RESET_CONNECTION_FIELD_NUMBER = 51;
        public static final int RESET_INPUT_BEHAVIOR_FIELD_NUMBER = 168;
        public static final int RESET_KEY_FIELD_NUMBER = 203;
        public static final int RESET_ROOT_KEYS_FIELD_NUMBER = 205;
        public static final int RESPONSE_FIELD_NUMBER = 9;
        public static final int SEND_SMS_FIELD_NUMBER = 353;
        public static final int SET_AUDIOGRAM_FIELD_NUMBER = 301;
        public static final int SET_CLOUD_PAIRING_KEYS_FIELD_NUMBER = 372;
        public static final int SET_INPUT_BEHAVIOR_FIELD_NUMBER = 161;
        public static final int SET_LOCALE_FIELD_NUMBER = 58;
        public static final int SET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT_FIELD_NUMBER = 303;
        public static final int SET_SMS_READ_STATUS_FIELD_NUMBER = 354;
        public static final int SET_STATE_FIELD_NUMBER = 101;
        public static final int SET_WAKEWORDS_FIELD_NUMBER = 70;
        public static final int START_FIRMWARE_UPDATE_FIELD_NUMBER = 99;
        public static final int START_LIVE_FITNESS_DATA_FIELD_NUMBER = 134;
        public static final int START_SETUP_FIELD_NUMBER = 23;
        public static final int START_SPEECH_FIELD_NUMBER = 11;
        public static final int START_TRANSLATION_FIELD_NUMBER = 170;
        public static final int START_VOICE_STREAM_FIELD_NUMBER = 360;
        public static final int STOP_BULK_DATA_TRANSFER_FIELD_NUMBER = 185;
        public static final int STOP_DIAGNOSTICS_FIELD_NUMBER = 111;
        public static final int STOP_FITNESS_DATA_FIELD_NUMBER = 131;
        public static final int STOP_LIVE_FITNESS_DATA_FIELD_NUMBER = 135;
        public static final int STOP_SPEECH_FIELD_NUMBER = 12;
        public static final int STOP_TRANSLATION_FIELD_NUMBER = 172;
        public static final int STOP_VOICE_STREAM_FIELD_NUMBER = 361;
        public static final int SUBSCRIBE_NOTIFICATION_CENTER_FIELD_NUMBER = 150;
        public static final int SWITCH_CURRENT_USER_FIELD_NUMBER = 53;
        public static final int SWITCH_TRANSPORT_FIELD_NUMBER = 31;
        public static final int SYNCHRONIZE_SETTINGS_FIELD_NUMBER = 50;
        public static final int SYNCHRONIZE_STATE_FIELD_NUMBER = 102;
        public static final int SYNC_FITNESS_SESSION_FIELD_NUMBER = 133;
        public static final int UNPAIR_USER_FIELD_NUMBER = 64;
        public static final int UNSUBSCRIBE_NOTIFICATION_CENTER_FIELD_NUMBER = 151;
        public static final int UPDATE_CALL_STATE_FIELD_NUMBER = 140;
        public static final int UPDATE_CENTRAL_NOTIFICATION_ATTRIBUTES_FIELD_NUMBER = 156;
        public static final int UPDATE_COMPONENT_SEGMENT_FIELD_NUMBER = 94;
        public static final int UPDATE_DEVICE_INFORMATION_FIELD_NUMBER = 26;
        public static final int UPDATE_METRICS_MAP_FIELD_NUMBER = 121;
        public static final int UPDATE_NOTIFICATION_FIELD_NUMBER = 191;
        public static final int UPDATE_USERS_FIELD_NUMBER = 61;
        public static final int UPGRADE_TRANSPORT_FIELD_NUMBER = 30;
        public static final int USER_CONFIRMED_FIELD_NUMBER = 202;
        public static final int VERIFY_ARTIFACT_SIGNATURE_FIELD_NUMBER = 89;
        private int command_;
        private int payloadCase_ = 0;
        private Object payload_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ControlEnvelope, Builder> implements ControlEnvelopeOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearAcceptCall() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearAcceptCall();
                return this;
            }

            public Builder clearAddNotification() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearAddNotification();
                return this;
            }

            public Builder clearApplyFirmware() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearApplyFirmware();
                return this;
            }

            public Builder clearBulkDataManifestEntry() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearBulkDataManifestEntry();
                return this;
            }

            public Builder clearBulkDataTransferComplete() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearBulkDataTransferComplete();
                return this;
            }

            public Builder clearBulkDataTransferStart() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearBulkDataTransferStart();
                return this;
            }

            public Builder clearCommand() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearCommand();
                return this;
            }

            public Builder clearCompleteHandshake() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearCompleteHandshake();
                return this;
            }

            public Builder clearCompleteSetup() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearCompleteSetup();
                return this;
            }

            public Builder clearConfirmResetKey() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearConfirmResetKey();
                return this;
            }

            public Builder clearConnectUser() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearConnectUser();
                return this;
            }

            public Builder clearDisconnectUser() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearDisconnectUser();
                return this;
            }

            public Builder clearDisplayContent() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearDisplayContent();
                return this;
            }

            public Builder clearEndCall() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearEndCall();
                return this;
            }

            public Builder clearEndOfSmsMessageListResponse() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearEndOfSmsMessageListResponse();
                return this;
            }

            public Builder clearEndpointSpeech() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearEndpointSpeech();
                return this;
            }

            public Builder clearExecuteNotificationAction() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearExecuteNotificationAction();
                return this;
            }

            public Builder clearFirmwareUpdateUnavailable() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearFirmwareUpdateUnavailable();
                return this;
            }

            public Builder clearForwardAtCommand() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearForwardAtCommand();
                return this;
            }

            public Builder clearGetArtifactFilter() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetArtifactFilter();
                return this;
            }

            public Builder clearGetArtifactUpdatePreference() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetArtifactUpdatePreference();
                return this;
            }

            public Builder clearGetAudiogram() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetAudiogram();
                return this;
            }

            public Builder clearGetBulkDataManifest() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetBulkDataManifest();
                return this;
            }

            public Builder clearGetCachedComponent() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetCachedComponent();
                return this;
            }

            public Builder clearGetCblInformation() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetCblInformation();
                return this;
            }

            public Builder clearGetCblLoginState() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetCblLoginState();
                return this;
            }

            public Builder clearGetCentralInformation() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetCentralInformation();
                return this;
            }

            public Builder clearGetCentralNotificationAppAttributes() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetCentralNotificationAppAttributes();
                return this;
            }

            public Builder clearGetCentralNotificationAttributes() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetCentralNotificationAttributes();
                return this;
            }

            public Builder clearGetCloudPairingAttributes() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetCloudPairingAttributes();
                return this;
            }

            public Builder clearGetCloudPairingStatus() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetCloudPairingStatus();
                return this;
            }

            public Builder clearGetCurrentUser() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetCurrentUser();
                return this;
            }

            public Builder clearGetDeviceArtifacts() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetDeviceArtifacts();
                return this;
            }

            public Builder clearGetDeviceConfiguration() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetDeviceConfiguration();
                return this;
            }

            public Builder clearGetDeviceFeatures() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetDeviceFeatures();
                return this;
            }

            public Builder clearGetDeviceInformation() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetDeviceInformation();
                return this;
            }

            public Builder clearGetDiagnostics() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetDiagnostics();
                return this;
            }

            public Builder clearGetFirmwareInformation() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetFirmwareInformation();
                return this;
            }

            public Builder clearGetFirmwareUpdatePreferences() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetFirmwareUpdatePreferences();
                return this;
            }

            public Builder clearGetFitnessData() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetFitnessData();
                return this;
            }

            public Builder clearGetInputBehavior() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetInputBehavior();
                return this;
            }

            public Builder clearGetLocales() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetLocales();
                return this;
            }

            public Builder clearGetMediaEnhancementCorrectionAmount() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetMediaEnhancementCorrectionAmount();
                return this;
            }

            public Builder clearGetPlaybackStatus() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetPlaybackStatus();
                return this;
            }

            public Builder clearGetSmsMessageList() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetSmsMessageList();
                return this;
            }

            public Builder clearGetSmsMessageListResponse() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetSmsMessageListResponse();
                return this;
            }

            public Builder clearGetState() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetState();
                return this;
            }

            public Builder clearGetUsers() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetUsers();
                return this;
            }

            public Builder clearGetWakewords() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearGetWakewords();
                return this;
            }

            public Builder clearIncomingCall() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearIncomingCall();
                return this;
            }

            public Builder clearInitiateFirmwareUpdate() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearInitiateFirmwareUpdate();
                return this;
            }

            public Builder clearInitiateHandshake() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearInitiateHandshake();
                return this;
            }

            public Builder clearInitiateMapConnection() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearInitiateMapConnection();
                return this;
            }

            public Builder clearIssueInputEvent() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearIssueInputEvent();
                return this;
            }

            public Builder clearIssueMediaControl() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearIssueMediaControl();
                return this;
            }

            public Builder clearIssueRemoteClearPairing() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearIssueRemoteClearPairing();
                return this;
            }

            public Builder clearIssueRemoteCommand() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearIssueRemoteCommand();
                return this;
            }

            public Builder clearIssueRemoteReset() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearIssueRemoteReset();
                return this;
            }

            public Builder clearIssueRemoteRestart() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearIssueRemoteRestart();
                return this;
            }

            public Builder clearKeepAlive() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearKeepAlive();
                return this;
            }

            public Builder clearLaunchApp() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearLaunchApp();
                return this;
            }

            public Builder clearLiveFitnessData() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearLiveFitnessData();
                return this;
            }

            public Builder clearMediaEventOccurred() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearMediaEventOccurred();
                return this;
            }

            public Builder clearNotifyBulkDataAvailable() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearNotifyBulkDataAvailable();
                return this;
            }

            public Builder clearNotifyCblLoginState() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearNotifyCblLoginState();
                return this;
            }

            public Builder clearNotifyDeviceConfiguration() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearNotifyDeviceConfiguration();
                return this;
            }

            public Builder clearNotifyDeviceInformation() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearNotifyDeviceInformation();
                return this;
            }

            public Builder clearNotifyDiagnosticsAvailable() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearNotifyDiagnosticsAvailable();
                return this;
            }

            public Builder clearNotifyFirmwareInformation() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearNotifyFirmwareInformation();
                return this;
            }

            public Builder clearNotifyFitnessDataAvailable() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearNotifyFitnessDataAvailable();
                return this;
            }

            public Builder clearNotifySmsMessageList() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearNotifySmsMessageList();
                return this;
            }

            public Builder clearNotifySpeechState() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearNotifySpeechState();
                return this;
            }

            public Builder clearOverrideAssistant() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearOverrideAssistant();
                return this;
            }

            public Builder clearPayload() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearPayload();
                return this;
            }

            public Builder clearPerformCentralNotificationAction() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearPerformCentralNotificationAction();
                return this;
            }

            public Builder clearPrintDebug() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearPrintDebug();
                return this;
            }

            public Builder clearProvideSpeech() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearProvideSpeech();
                return this;
            }

            public Builder clearProvideTranslation() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearProvideTranslation();
                return this;
            }

            public Builder clearPublishCentralNotification() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearPublishCentralNotification();
                return this;
            }

            public Builder clearPushMetrics() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearPushMetrics();
                return this;
            }

            public Builder clearRegisterForMediaEvents() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearRegisterForMediaEvents();
                return this;
            }

            public Builder clearRejectCall() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearRejectCall();
                return this;
            }

            public Builder clearRemoveCloudPairingKeys() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearRemoveCloudPairingKeys();
                return this;
            }

            public Builder clearRemoveDevice() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearRemoveDevice();
                return this;
            }

            public Builder clearRemoveNotification() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearRemoveNotification();
                return this;
            }

            public Builder clearReplaceCloudPairingKeys() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearReplaceCloudPairingKeys();
                return this;
            }

            public Builder clearRequestBulkDataTransfer() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearRequestBulkDataTransfer();
                return this;
            }

            public Builder clearResetCachedComponent() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearResetCachedComponent();
                return this;
            }

            public Builder clearResetConnection() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearResetConnection();
                return this;
            }

            public Builder clearResetInputBehavior() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearResetInputBehavior();
                return this;
            }

            public Builder clearResetKey() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearResetKey();
                return this;
            }

            public Builder clearResetRootKeys() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearResetRootKeys();
                return this;
            }

            public Builder clearResponse() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearResponse();
                return this;
            }

            public Builder clearSendSms() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSendSms();
                return this;
            }

            public Builder clearSetAudiogram() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSetAudiogram();
                return this;
            }

            public Builder clearSetCloudPairingKeys() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSetCloudPairingKeys();
                return this;
            }

            public Builder clearSetInputBehavior() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSetInputBehavior();
                return this;
            }

            public Builder clearSetLocale() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSetLocale();
                return this;
            }

            public Builder clearSetMediaEnhancementCorrectionAmount() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSetMediaEnhancementCorrectionAmount();
                return this;
            }

            public Builder clearSetSmsReadStatus() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSetSmsReadStatus();
                return this;
            }

            public Builder clearSetState() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSetState();
                return this;
            }

            public Builder clearSetWakewords() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSetWakewords();
                return this;
            }

            public Builder clearStartFirmwareUpdate() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearStartFirmwareUpdate();
                return this;
            }

            public Builder clearStartLiveFitnessData() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearStartLiveFitnessData();
                return this;
            }

            public Builder clearStartSetup() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearStartSetup();
                return this;
            }

            public Builder clearStartSpeech() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearStartSpeech();
                return this;
            }

            public Builder clearStartTranslation() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearStartTranslation();
                return this;
            }

            public Builder clearStartVoiceStream() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearStartVoiceStream();
                return this;
            }

            public Builder clearStopBulkDataTransfer() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearStopBulkDataTransfer();
                return this;
            }

            public Builder clearStopDiagnostics() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearStopDiagnostics();
                return this;
            }

            public Builder clearStopFitnessData() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearStopFitnessData();
                return this;
            }

            public Builder clearStopLiveFitnessData() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearStopLiveFitnessData();
                return this;
            }

            public Builder clearStopSpeech() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearStopSpeech();
                return this;
            }

            public Builder clearStopTranslation() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearStopTranslation();
                return this;
            }

            public Builder clearStopVoiceStream() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearStopVoiceStream();
                return this;
            }

            public Builder clearSubscribeNotificationCenter() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSubscribeNotificationCenter();
                return this;
            }

            public Builder clearSwitchCurrentUser() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSwitchCurrentUser();
                return this;
            }

            public Builder clearSwitchTransport() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSwitchTransport();
                return this;
            }

            public Builder clearSyncFitnessSession() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSyncFitnessSession();
                return this;
            }

            public Builder clearSynchronizeSettings() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSynchronizeSettings();
                return this;
            }

            public Builder clearSynchronizeState() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearSynchronizeState();
                return this;
            }

            public Builder clearUnpairUser() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearUnpairUser();
                return this;
            }

            public Builder clearUnsubscribeNotificationCenter() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearUnsubscribeNotificationCenter();
                return this;
            }

            public Builder clearUpdateCallState() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearUpdateCallState();
                return this;
            }

            public Builder clearUpdateCentralNotificationAttributes() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearUpdateCentralNotificationAttributes();
                return this;
            }

            public Builder clearUpdateComponentSegment() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearUpdateComponentSegment();
                return this;
            }

            public Builder clearUpdateDeviceInformation() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearUpdateDeviceInformation();
                return this;
            }

            public Builder clearUpdateMetricsMap() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearUpdateMetricsMap();
                return this;
            }

            public Builder clearUpdateNotification() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearUpdateNotification();
                return this;
            }

            public Builder clearUpdateUsers() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearUpdateUsers();
                return this;
            }

            public Builder clearUpgradeTransport() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearUpgradeTransport();
                return this;
            }

            public Builder clearUserConfirmed() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearUserConfirmed();
                return this;
            }

            public Builder clearVerifyArtifactSignature() {
                copyOnWrite();
                ((ControlEnvelope) this.instance).clearVerifyArtifactSignature();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Nonhfpcalling.AcceptCall getAcceptCall() {
                return ((ControlEnvelope) this.instance).getAcceptCall();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Notification.AddNotification getAddNotification() {
                return ((ControlEnvelope) this.instance).getAddNotification();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Firmware.ApplyFirmware getApplyFirmware() {
                return ((ControlEnvelope) this.instance).getApplyFirmware();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Bulkdata.BulkDataManifestEntry getBulkDataManifestEntry() {
                return ((ControlEnvelope) this.instance).getBulkDataManifestEntry();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Bulkdata.BulkDataTransferComplete getBulkDataTransferComplete() {
                return ((ControlEnvelope) this.instance).getBulkDataTransferComplete();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Bulkdata.BulkDataTransferStart getBulkDataTransferStart() {
                return ((ControlEnvelope) this.instance).getBulkDataTransferStart();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Command getCommand() {
                return ((ControlEnvelope) this.instance).getCommand();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public int getCommandValue() {
                return ((ControlEnvelope) this.instance).getCommandValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Keyexchange.CompleteHandshake getCompleteHandshake() {
                return ((ControlEnvelope) this.instance).getCompleteHandshake();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Device.CompleteSetup getCompleteSetup() {
                return ((ControlEnvelope) this.instance).getCompleteSetup();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Keyexchange.ConfirmResetKey getConfirmResetKey() {
                return ((ControlEnvelope) this.instance).getConfirmResetKey();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.ConnectUser getConnectUser() {
                return ((ControlEnvelope) this.instance).getConnectUser();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.DisconnectUser getDisconnectUser() {
                return ((ControlEnvelope) this.instance).getDisconnectUser();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Cardrendering.DisplayContent getDisplayContent() {
                return ((ControlEnvelope) this.instance).getDisplayContent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Nonhfpcalling.EndCall getEndCall() {
                return ((ControlEnvelope) this.instance).getEndCall();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Mapsms.EndOfSmsMessageListResponse getEndOfSmsMessageListResponse() {
                return ((ControlEnvelope) this.instance).getEndOfSmsMessageListResponse();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Speech.EndpointSpeech getEndpointSpeech() {
                return ((ControlEnvelope) this.instance).getEndpointSpeech();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Notification.ExecuteNotificationAction getExecuteNotificationAction() {
                return ((ControlEnvelope) this.instance).getExecuteNotificationAction();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Firmware.FirmwareUpdateUnavailable getFirmwareUpdateUnavailable() {
                return ((ControlEnvelope) this.instance).getFirmwareUpdateUnavailable();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Calling.ForwardATCommand getForwardAtCommand() {
                return ((ControlEnvelope) this.instance).getForwardAtCommand();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Firmware.GetArtifactFilter getGetArtifactFilter() {
                return ((ControlEnvelope) this.instance).getGetArtifactFilter();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Firmware.GetArtifactUpdatePreference getGetArtifactUpdatePreference() {
                return ((ControlEnvelope) this.instance).getGetArtifactUpdatePreference();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Hearing.GetAudiogram getGetAudiogram() {
                return ((ControlEnvelope) this.instance).getGetAudiogram();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Bulkdata.GetBulkDataManifest getGetBulkDataManifest() {
                return ((ControlEnvelope) this.instance).getGetBulkDataManifest();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Firmware.GetCachedComponent getGetCachedComponent() {
                return ((ControlEnvelope) this.instance).getGetCachedComponent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Cbl.GetCblInformation getGetCblInformation() {
                return ((ControlEnvelope) this.instance).getGetCblInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Cbl.GetCblLoginState getGetCblLoginState() {
                return ((ControlEnvelope) this.instance).getGetCblLoginState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Central.GetCentralInformation getGetCentralInformation() {
                return ((ControlEnvelope) this.instance).getGetCentralInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Ancs.GetCentralNotificationAppAttributes getGetCentralNotificationAppAttributes() {
                return ((ControlEnvelope) this.instance).getGetCentralNotificationAppAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Ancs.GetCentralNotificationAttributes getGetCentralNotificationAttributes() {
                return ((ControlEnvelope) this.instance).getGetCentralNotificationAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Cloudpairing.GetCloudPairingAttributes getGetCloudPairingAttributes() {
                return ((ControlEnvelope) this.instance).getGetCloudPairingAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Cloudpairing.GetCloudPairingStatus getGetCloudPairingStatus() {
                return ((ControlEnvelope) this.instance).getGetCloudPairingStatus();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.GetCurrentUser getGetCurrentUser() {
                return ((ControlEnvelope) this.instance).getGetCurrentUser();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Firmware.GetDeviceArtifacts getGetDeviceArtifacts() {
                return ((ControlEnvelope) this.instance).getGetDeviceArtifacts();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Device.GetDeviceConfiguration getGetDeviceConfiguration() {
                return ((ControlEnvelope) this.instance).getGetDeviceConfiguration();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Device.GetDeviceFeatures getGetDeviceFeatures() {
                return ((ControlEnvelope) this.instance).getGetDeviceFeatures();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Device.GetDeviceInformation getGetDeviceInformation() {
                return ((ControlEnvelope) this.instance).getGetDeviceInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public DiagnosticsOuterClass.GetDiagnostics getGetDiagnostics() {
                return ((ControlEnvelope) this.instance).getGetDiagnostics();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Firmware.GetFirmwareInformation getGetFirmwareInformation() {
                return ((ControlEnvelope) this.instance).getGetFirmwareInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Firmware.GetFirmwareUpdatePreferences getGetFirmwareUpdatePreferences() {
                return ((ControlEnvelope) this.instance).getGetFirmwareUpdatePreferences();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Fitness.GetFitnessData getGetFitnessData() {
                return ((ControlEnvelope) this.instance).getGetFitnessData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Input.GetInputBehavior getGetInputBehavior() {
                return ((ControlEnvelope) this.instance).getGetInputBehavior();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.GetLocales getGetLocales() {
                return ((ControlEnvelope) this.instance).getGetLocales();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Hearing.GetMediaEnhancementCorrectionAmount getGetMediaEnhancementCorrectionAmount() {
                return ((ControlEnvelope) this.instance).getGetMediaEnhancementCorrectionAmount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Media.GetPlaybackStatus getGetPlaybackStatus() {
                return ((ControlEnvelope) this.instance).getGetPlaybackStatus();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Mapsms.GetSmsMessageList getGetSmsMessageList() {
                return ((ControlEnvelope) this.instance).getGetSmsMessageList();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Mapsms.GetSmsMessageListResponse getGetSmsMessageListResponse() {
                return ((ControlEnvelope) this.instance).getGetSmsMessageListResponse();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public StateOuterClass.GetState getGetState() {
                return ((ControlEnvelope) this.instance).getGetState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.GetUsers getGetUsers() {
                return ((ControlEnvelope) this.instance).getGetUsers();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.GetWakewords getGetWakewords() {
                return ((ControlEnvelope) this.instance).getGetWakewords();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Calling.IncomingCall getIncomingCall() {
                return ((ControlEnvelope) this.instance).getIncomingCall();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Firmware.InitiateFirmwareUpdate getInitiateFirmwareUpdate() {
                return ((ControlEnvelope) this.instance).getInitiateFirmwareUpdate();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Keyexchange.InitiateHandshake getInitiateHandshake() {
                return ((ControlEnvelope) this.instance).getInitiateHandshake();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Mapsms.InitiateMapConnection getInitiateMapConnection() {
                return ((ControlEnvelope) this.instance).getInitiateMapConnection();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Input.IssueInputEvent getIssueInputEvent() {
                return ((ControlEnvelope) this.instance).getIssueInputEvent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Media.IssueMediaControl getIssueMediaControl() {
                return ((ControlEnvelope) this.instance).getIssueMediaControl();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Instrumentation.IssueRemoteClearPairing getIssueRemoteClearPairing() {
                return ((ControlEnvelope) this.instance).getIssueRemoteClearPairing();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Instrumentation.IssueRemoteCommand getIssueRemoteCommand() {
                return ((ControlEnvelope) this.instance).getIssueRemoteCommand();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Instrumentation.IssueRemoteReset getIssueRemoteReset() {
                return ((ControlEnvelope) this.instance).getIssueRemoteReset();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Instrumentation.IssueRemoteRestart getIssueRemoteRestart() {
                return ((ControlEnvelope) this.instance).getIssueRemoteRestart();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.KeepAlive getKeepAlive() {
                return ((ControlEnvelope) this.instance).getKeepAlive();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.LaunchApp getLaunchApp() {
                return ((ControlEnvelope) this.instance).getLaunchApp();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Fitness.LiveFitnessData getLiveFitnessData() {
                return ((ControlEnvelope) this.instance).getLiveFitnessData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Media.MediaEventOccurred getMediaEventOccurred() {
                return ((ControlEnvelope) this.instance).getMediaEventOccurred();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Bulkdata.NotifyBulkDataAvailable getNotifyBulkDataAvailable() {
                return ((ControlEnvelope) this.instance).getNotifyBulkDataAvailable();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Cbl.NotifyCblLoginState getNotifyCblLoginState() {
                return ((ControlEnvelope) this.instance).getNotifyCblLoginState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Device.NotifyDeviceConfiguration getNotifyDeviceConfiguration() {
                return ((ControlEnvelope) this.instance).getNotifyDeviceConfiguration();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Device.NotifyDeviceInformation getNotifyDeviceInformation() {
                return ((ControlEnvelope) this.instance).getNotifyDeviceInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public DiagnosticsOuterClass.NotifyDiagnosticsAvailable getNotifyDiagnosticsAvailable() {
                return ((ControlEnvelope) this.instance).getNotifyDiagnosticsAvailable();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Firmware.NotifyFirmwareInformation getNotifyFirmwareInformation() {
                return ((ControlEnvelope) this.instance).getNotifyFirmwareInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Fitness.NotifyFitnessDataAvailable getNotifyFitnessDataAvailable() {
                return ((ControlEnvelope) this.instance).getNotifyFitnessDataAvailable();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Mapsms.NotifySmsMessageList getNotifySmsMessageList() {
                return ((ControlEnvelope) this.instance).getNotifySmsMessageList();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Speech.NotifySpeechState getNotifySpeechState() {
                return ((ControlEnvelope) this.instance).getNotifySpeechState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Device.OverrideAssistant getOverrideAssistant() {
                return ((ControlEnvelope) this.instance).getOverrideAssistant();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public PayloadCase getPayloadCase() {
                return ((ControlEnvelope) this.instance).getPayloadCase();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Ancs.PerformCentralNotificationAction getPerformCentralNotificationAction() {
                return ((ControlEnvelope) this.instance).getPerformCentralNotificationAction();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Instrumentation.PrintDebug getPrintDebug() {
                return ((ControlEnvelope) this.instance).getPrintDebug();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Speech.ProvideSpeech getProvideSpeech() {
                return ((ControlEnvelope) this.instance).getProvideSpeech();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Translation.ProvideTranslation getProvideTranslation() {
                return ((ControlEnvelope) this.instance).getProvideTranslation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Ancs.PublishCentralNotification getPublishCentralNotification() {
                return ((ControlEnvelope) this.instance).getPublishCentralNotification();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Metrics.PushMetrics getPushMetrics() {
                return ((ControlEnvelope) this.instance).getPushMetrics();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Media.RegisterForMediaEvents getRegisterForMediaEvents() {
                return ((ControlEnvelope) this.instance).getRegisterForMediaEvents();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Nonhfpcalling.RejectCall getRejectCall() {
                return ((ControlEnvelope) this.instance).getRejectCall();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Cloudpairing.RemoveCloudPairingKeys getRemoveCloudPairingKeys() {
                return ((ControlEnvelope) this.instance).getRemoveCloudPairingKeys();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.RemoveDevice getRemoveDevice() {
                return ((ControlEnvelope) this.instance).getRemoveDevice();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Notification.RemoveNotification getRemoveNotification() {
                return ((ControlEnvelope) this.instance).getRemoveNotification();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Cloudpairing.ReplaceCloudPairingKeys getReplaceCloudPairingKeys() {
                return ((ControlEnvelope) this.instance).getReplaceCloudPairingKeys();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Bulkdata.RequestBulkDataTransfer getRequestBulkDataTransfer() {
                return ((ControlEnvelope) this.instance).getRequestBulkDataTransfer();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Firmware.ResetCachedComponent getResetCachedComponent() {
                return ((ControlEnvelope) this.instance).getResetCachedComponent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.ResetConnection getResetConnection() {
                return ((ControlEnvelope) this.instance).getResetConnection();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Input.ResetInputBehavior getResetInputBehavior() {
                return ((ControlEnvelope) this.instance).getResetInputBehavior();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Keyexchange.ResetKey getResetKey() {
                return ((ControlEnvelope) this.instance).getResetKey();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Keyexchange.ResetRootKeys getResetRootKeys() {
                return ((ControlEnvelope) this.instance).getResetRootKeys();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Response getResponse() {
                return ((ControlEnvelope) this.instance).getResponse();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Mapsms.SendSms getSendSms() {
                return ((ControlEnvelope) this.instance).getSendSms();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Hearing.SetAudiogram getSetAudiogram() {
                return ((ControlEnvelope) this.instance).getSetAudiogram();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Cloudpairing.SetCloudPairingKeys getSetCloudPairingKeys() {
                return ((ControlEnvelope) this.instance).getSetCloudPairingKeys();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Input.SetInputBehavior getSetInputBehavior() {
                return ((ControlEnvelope) this.instance).getSetInputBehavior();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.SetLocale getSetLocale() {
                return ((ControlEnvelope) this.instance).getSetLocale();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Hearing.SetMediaEnhancementCorrectionAmount getSetMediaEnhancementCorrectionAmount() {
                return ((ControlEnvelope) this.instance).getSetMediaEnhancementCorrectionAmount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Mapsms.SetSmsReadStatus getSetSmsReadStatus() {
                return ((ControlEnvelope) this.instance).getSetSmsReadStatus();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public StateOuterClass.SetState getSetState() {
                return ((ControlEnvelope) this.instance).getSetState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.SetWakewords getSetWakewords() {
                return ((ControlEnvelope) this.instance).getSetWakewords();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Firmware.StartFirmwareUpdate getStartFirmwareUpdate() {
                return ((ControlEnvelope) this.instance).getStartFirmwareUpdate();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Fitness.StartLiveFitnessData getStartLiveFitnessData() {
                return ((ControlEnvelope) this.instance).getStartLiveFitnessData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Device.StartSetup getStartSetup() {
                return ((ControlEnvelope) this.instance).getStartSetup();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Speech.StartSpeech getStartSpeech() {
                return ((ControlEnvelope) this.instance).getStartSpeech();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Translation.StartTranslation getStartTranslation() {
                return ((ControlEnvelope) this.instance).getStartTranslation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Voicestream.StartVoiceStream getStartVoiceStream() {
                return ((ControlEnvelope) this.instance).getStartVoiceStream();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Bulkdata.StopBulkDataTransfer getStopBulkDataTransfer() {
                return ((ControlEnvelope) this.instance).getStopBulkDataTransfer();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public DiagnosticsOuterClass.StopDiagnostics getStopDiagnostics() {
                return ((ControlEnvelope) this.instance).getStopDiagnostics();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Fitness.StopFitnessData getStopFitnessData() {
                return ((ControlEnvelope) this.instance).getStopFitnessData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Fitness.StopLiveFitnessData getStopLiveFitnessData() {
                return ((ControlEnvelope) this.instance).getStopLiveFitnessData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Speech.StopSpeech getStopSpeech() {
                return ((ControlEnvelope) this.instance).getStopSpeech();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Translation.StopTranslation getStopTranslation() {
                return ((ControlEnvelope) this.instance).getStopTranslation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Voicestream.StopVoiceStream getStopVoiceStream() {
                return ((ControlEnvelope) this.instance).getStopVoiceStream();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Ancs.SubscribeNotificationCenter getSubscribeNotificationCenter() {
                return ((ControlEnvelope) this.instance).getSubscribeNotificationCenter();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.SwitchCurrentUser getSwitchCurrentUser() {
                return ((ControlEnvelope) this.instance).getSwitchCurrentUser();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Transport.SwitchTransport getSwitchTransport() {
                return ((ControlEnvelope) this.instance).getSwitchTransport();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Fitness.SyncFitnessSession getSyncFitnessSession() {
                return ((ControlEnvelope) this.instance).getSyncFitnessSession();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.SynchronizeSettings getSynchronizeSettings() {
                return ((ControlEnvelope) this.instance).getSynchronizeSettings();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public StateOuterClass.SynchronizeState getSynchronizeState() {
                return ((ControlEnvelope) this.instance).getSynchronizeState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.UnpairUser getUnpairUser() {
                return ((ControlEnvelope) this.instance).getUnpairUser();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Ancs.UnsubscribeNotificationCenter getUnsubscribeNotificationCenter() {
                return ((ControlEnvelope) this.instance).getUnsubscribeNotificationCenter();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Nonhfpcalling.UpdateCallState getUpdateCallState() {
                return ((ControlEnvelope) this.instance).getUpdateCallState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Ancs.UpdateCentralNotificationAttributes getUpdateCentralNotificationAttributes() {
                return ((ControlEnvelope) this.instance).getUpdateCentralNotificationAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Firmware.UpdateComponentSegment getUpdateComponentSegment() {
                return ((ControlEnvelope) this.instance).getUpdateComponentSegment();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Device.UpdateDeviceInformation getUpdateDeviceInformation() {
                return ((ControlEnvelope) this.instance).getUpdateDeviceInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Metrics.UpdateMetricsMap getUpdateMetricsMap() {
                return ((ControlEnvelope) this.instance).getUpdateMetricsMap();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Notification.UpdateNotification getUpdateNotification() {
                return ((ControlEnvelope) this.instance).getUpdateNotification();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public System.UpdateUsers getUpdateUsers() {
                return ((ControlEnvelope) this.instance).getUpdateUsers();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Transport.UpgradeTransport getUpgradeTransport() {
                return ((ControlEnvelope) this.instance).getUpgradeTransport();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Keyexchange.UserConfirmed getUserConfirmed() {
                return ((ControlEnvelope) this.instance).getUserConfirmed();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public Firmware.VerifyArtifactSignature getVerifyArtifactSignature() {
                return ((ControlEnvelope) this.instance).getVerifyArtifactSignature();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasAcceptCall() {
                return ((ControlEnvelope) this.instance).hasAcceptCall();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasAddNotification() {
                return ((ControlEnvelope) this.instance).hasAddNotification();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasApplyFirmware() {
                return ((ControlEnvelope) this.instance).hasApplyFirmware();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasBulkDataManifestEntry() {
                return ((ControlEnvelope) this.instance).hasBulkDataManifestEntry();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasBulkDataTransferComplete() {
                return ((ControlEnvelope) this.instance).hasBulkDataTransferComplete();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasBulkDataTransferStart() {
                return ((ControlEnvelope) this.instance).hasBulkDataTransferStart();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasCompleteHandshake() {
                return ((ControlEnvelope) this.instance).hasCompleteHandshake();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasCompleteSetup() {
                return ((ControlEnvelope) this.instance).hasCompleteSetup();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasConfirmResetKey() {
                return ((ControlEnvelope) this.instance).hasConfirmResetKey();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasConnectUser() {
                return ((ControlEnvelope) this.instance).hasConnectUser();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasDisconnectUser() {
                return ((ControlEnvelope) this.instance).hasDisconnectUser();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasDisplayContent() {
                return ((ControlEnvelope) this.instance).hasDisplayContent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasEndCall() {
                return ((ControlEnvelope) this.instance).hasEndCall();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasEndOfSmsMessageListResponse() {
                return ((ControlEnvelope) this.instance).hasEndOfSmsMessageListResponse();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasEndpointSpeech() {
                return ((ControlEnvelope) this.instance).hasEndpointSpeech();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasExecuteNotificationAction() {
                return ((ControlEnvelope) this.instance).hasExecuteNotificationAction();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasFirmwareUpdateUnavailable() {
                return ((ControlEnvelope) this.instance).hasFirmwareUpdateUnavailable();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasForwardAtCommand() {
                return ((ControlEnvelope) this.instance).hasForwardAtCommand();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetArtifactFilter() {
                return ((ControlEnvelope) this.instance).hasGetArtifactFilter();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetArtifactUpdatePreference() {
                return ((ControlEnvelope) this.instance).hasGetArtifactUpdatePreference();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetAudiogram() {
                return ((ControlEnvelope) this.instance).hasGetAudiogram();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetBulkDataManifest() {
                return ((ControlEnvelope) this.instance).hasGetBulkDataManifest();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetCachedComponent() {
                return ((ControlEnvelope) this.instance).hasGetCachedComponent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetCblInformation() {
                return ((ControlEnvelope) this.instance).hasGetCblInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetCblLoginState() {
                return ((ControlEnvelope) this.instance).hasGetCblLoginState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetCentralInformation() {
                return ((ControlEnvelope) this.instance).hasGetCentralInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetCentralNotificationAppAttributes() {
                return ((ControlEnvelope) this.instance).hasGetCentralNotificationAppAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetCentralNotificationAttributes() {
                return ((ControlEnvelope) this.instance).hasGetCentralNotificationAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetCloudPairingAttributes() {
                return ((ControlEnvelope) this.instance).hasGetCloudPairingAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetCloudPairingStatus() {
                return ((ControlEnvelope) this.instance).hasGetCloudPairingStatus();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetCurrentUser() {
                return ((ControlEnvelope) this.instance).hasGetCurrentUser();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetDeviceArtifacts() {
                return ((ControlEnvelope) this.instance).hasGetDeviceArtifacts();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetDeviceConfiguration() {
                return ((ControlEnvelope) this.instance).hasGetDeviceConfiguration();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetDeviceFeatures() {
                return ((ControlEnvelope) this.instance).hasGetDeviceFeatures();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetDeviceInformation() {
                return ((ControlEnvelope) this.instance).hasGetDeviceInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetDiagnostics() {
                return ((ControlEnvelope) this.instance).hasGetDiagnostics();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetFirmwareInformation() {
                return ((ControlEnvelope) this.instance).hasGetFirmwareInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetFirmwareUpdatePreferences() {
                return ((ControlEnvelope) this.instance).hasGetFirmwareUpdatePreferences();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetFitnessData() {
                return ((ControlEnvelope) this.instance).hasGetFitnessData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetInputBehavior() {
                return ((ControlEnvelope) this.instance).hasGetInputBehavior();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetLocales() {
                return ((ControlEnvelope) this.instance).hasGetLocales();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetMediaEnhancementCorrectionAmount() {
                return ((ControlEnvelope) this.instance).hasGetMediaEnhancementCorrectionAmount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetPlaybackStatus() {
                return ((ControlEnvelope) this.instance).hasGetPlaybackStatus();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetSmsMessageList() {
                return ((ControlEnvelope) this.instance).hasGetSmsMessageList();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetSmsMessageListResponse() {
                return ((ControlEnvelope) this.instance).hasGetSmsMessageListResponse();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetState() {
                return ((ControlEnvelope) this.instance).hasGetState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetUsers() {
                return ((ControlEnvelope) this.instance).hasGetUsers();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasGetWakewords() {
                return ((ControlEnvelope) this.instance).hasGetWakewords();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasIncomingCall() {
                return ((ControlEnvelope) this.instance).hasIncomingCall();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasInitiateFirmwareUpdate() {
                return ((ControlEnvelope) this.instance).hasInitiateFirmwareUpdate();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasInitiateHandshake() {
                return ((ControlEnvelope) this.instance).hasInitiateHandshake();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasInitiateMapConnection() {
                return ((ControlEnvelope) this.instance).hasInitiateMapConnection();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasIssueInputEvent() {
                return ((ControlEnvelope) this.instance).hasIssueInputEvent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasIssueMediaControl() {
                return ((ControlEnvelope) this.instance).hasIssueMediaControl();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasIssueRemoteClearPairing() {
                return ((ControlEnvelope) this.instance).hasIssueRemoteClearPairing();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasIssueRemoteCommand() {
                return ((ControlEnvelope) this.instance).hasIssueRemoteCommand();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasIssueRemoteReset() {
                return ((ControlEnvelope) this.instance).hasIssueRemoteReset();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasIssueRemoteRestart() {
                return ((ControlEnvelope) this.instance).hasIssueRemoteRestart();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasKeepAlive() {
                return ((ControlEnvelope) this.instance).hasKeepAlive();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasLaunchApp() {
                return ((ControlEnvelope) this.instance).hasLaunchApp();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasLiveFitnessData() {
                return ((ControlEnvelope) this.instance).hasLiveFitnessData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasMediaEventOccurred() {
                return ((ControlEnvelope) this.instance).hasMediaEventOccurred();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasNotifyBulkDataAvailable() {
                return ((ControlEnvelope) this.instance).hasNotifyBulkDataAvailable();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasNotifyCblLoginState() {
                return ((ControlEnvelope) this.instance).hasNotifyCblLoginState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasNotifyDeviceConfiguration() {
                return ((ControlEnvelope) this.instance).hasNotifyDeviceConfiguration();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasNotifyDeviceInformation() {
                return ((ControlEnvelope) this.instance).hasNotifyDeviceInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasNotifyDiagnosticsAvailable() {
                return ((ControlEnvelope) this.instance).hasNotifyDiagnosticsAvailable();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasNotifyFirmwareInformation() {
                return ((ControlEnvelope) this.instance).hasNotifyFirmwareInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasNotifyFitnessDataAvailable() {
                return ((ControlEnvelope) this.instance).hasNotifyFitnessDataAvailable();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasNotifySmsMessageList() {
                return ((ControlEnvelope) this.instance).hasNotifySmsMessageList();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasNotifySpeechState() {
                return ((ControlEnvelope) this.instance).hasNotifySpeechState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasOverrideAssistant() {
                return ((ControlEnvelope) this.instance).hasOverrideAssistant();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasPerformCentralNotificationAction() {
                return ((ControlEnvelope) this.instance).hasPerformCentralNotificationAction();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasPrintDebug() {
                return ((ControlEnvelope) this.instance).hasPrintDebug();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasProvideSpeech() {
                return ((ControlEnvelope) this.instance).hasProvideSpeech();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasProvideTranslation() {
                return ((ControlEnvelope) this.instance).hasProvideTranslation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasPublishCentralNotification() {
                return ((ControlEnvelope) this.instance).hasPublishCentralNotification();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasPushMetrics() {
                return ((ControlEnvelope) this.instance).hasPushMetrics();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasRegisterForMediaEvents() {
                return ((ControlEnvelope) this.instance).hasRegisterForMediaEvents();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasRejectCall() {
                return ((ControlEnvelope) this.instance).hasRejectCall();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasRemoveCloudPairingKeys() {
                return ((ControlEnvelope) this.instance).hasRemoveCloudPairingKeys();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasRemoveDevice() {
                return ((ControlEnvelope) this.instance).hasRemoveDevice();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasRemoveNotification() {
                return ((ControlEnvelope) this.instance).hasRemoveNotification();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasReplaceCloudPairingKeys() {
                return ((ControlEnvelope) this.instance).hasReplaceCloudPairingKeys();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasRequestBulkDataTransfer() {
                return ((ControlEnvelope) this.instance).hasRequestBulkDataTransfer();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasResetCachedComponent() {
                return ((ControlEnvelope) this.instance).hasResetCachedComponent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasResetConnection() {
                return ((ControlEnvelope) this.instance).hasResetConnection();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasResetInputBehavior() {
                return ((ControlEnvelope) this.instance).hasResetInputBehavior();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasResetKey() {
                return ((ControlEnvelope) this.instance).hasResetKey();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasResetRootKeys() {
                return ((ControlEnvelope) this.instance).hasResetRootKeys();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasResponse() {
                return ((ControlEnvelope) this.instance).hasResponse();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSendSms() {
                return ((ControlEnvelope) this.instance).hasSendSms();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSetAudiogram() {
                return ((ControlEnvelope) this.instance).hasSetAudiogram();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSetCloudPairingKeys() {
                return ((ControlEnvelope) this.instance).hasSetCloudPairingKeys();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSetInputBehavior() {
                return ((ControlEnvelope) this.instance).hasSetInputBehavior();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSetLocale() {
                return ((ControlEnvelope) this.instance).hasSetLocale();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSetMediaEnhancementCorrectionAmount() {
                return ((ControlEnvelope) this.instance).hasSetMediaEnhancementCorrectionAmount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSetSmsReadStatus() {
                return ((ControlEnvelope) this.instance).hasSetSmsReadStatus();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSetState() {
                return ((ControlEnvelope) this.instance).hasSetState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSetWakewords() {
                return ((ControlEnvelope) this.instance).hasSetWakewords();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasStartFirmwareUpdate() {
                return ((ControlEnvelope) this.instance).hasStartFirmwareUpdate();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasStartLiveFitnessData() {
                return ((ControlEnvelope) this.instance).hasStartLiveFitnessData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasStartSetup() {
                return ((ControlEnvelope) this.instance).hasStartSetup();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasStartSpeech() {
                return ((ControlEnvelope) this.instance).hasStartSpeech();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasStartTranslation() {
                return ((ControlEnvelope) this.instance).hasStartTranslation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasStartVoiceStream() {
                return ((ControlEnvelope) this.instance).hasStartVoiceStream();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasStopBulkDataTransfer() {
                return ((ControlEnvelope) this.instance).hasStopBulkDataTransfer();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasStopDiagnostics() {
                return ((ControlEnvelope) this.instance).hasStopDiagnostics();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasStopFitnessData() {
                return ((ControlEnvelope) this.instance).hasStopFitnessData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasStopLiveFitnessData() {
                return ((ControlEnvelope) this.instance).hasStopLiveFitnessData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasStopSpeech() {
                return ((ControlEnvelope) this.instance).hasStopSpeech();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasStopTranslation() {
                return ((ControlEnvelope) this.instance).hasStopTranslation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasStopVoiceStream() {
                return ((ControlEnvelope) this.instance).hasStopVoiceStream();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSubscribeNotificationCenter() {
                return ((ControlEnvelope) this.instance).hasSubscribeNotificationCenter();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSwitchCurrentUser() {
                return ((ControlEnvelope) this.instance).hasSwitchCurrentUser();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSwitchTransport() {
                return ((ControlEnvelope) this.instance).hasSwitchTransport();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSyncFitnessSession() {
                return ((ControlEnvelope) this.instance).hasSyncFitnessSession();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSynchronizeSettings() {
                return ((ControlEnvelope) this.instance).hasSynchronizeSettings();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasSynchronizeState() {
                return ((ControlEnvelope) this.instance).hasSynchronizeState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasUnpairUser() {
                return ((ControlEnvelope) this.instance).hasUnpairUser();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasUnsubscribeNotificationCenter() {
                return ((ControlEnvelope) this.instance).hasUnsubscribeNotificationCenter();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasUpdateCallState() {
                return ((ControlEnvelope) this.instance).hasUpdateCallState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasUpdateCentralNotificationAttributes() {
                return ((ControlEnvelope) this.instance).hasUpdateCentralNotificationAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasUpdateComponentSegment() {
                return ((ControlEnvelope) this.instance).hasUpdateComponentSegment();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasUpdateDeviceInformation() {
                return ((ControlEnvelope) this.instance).hasUpdateDeviceInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasUpdateMetricsMap() {
                return ((ControlEnvelope) this.instance).hasUpdateMetricsMap();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasUpdateNotification() {
                return ((ControlEnvelope) this.instance).hasUpdateNotification();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasUpdateUsers() {
                return ((ControlEnvelope) this.instance).hasUpdateUsers();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasUpgradeTransport() {
                return ((ControlEnvelope) this.instance).hasUpgradeTransport();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasUserConfirmed() {
                return ((ControlEnvelope) this.instance).hasUserConfirmed();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
            public boolean hasVerifyArtifactSignature() {
                return ((ControlEnvelope) this.instance).hasVerifyArtifactSignature();
            }

            public Builder mergeAcceptCall(Nonhfpcalling.AcceptCall acceptCall) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeAcceptCall(acceptCall);
                return this;
            }

            public Builder mergeAddNotification(Notification.AddNotification addNotification) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeAddNotification(addNotification);
                return this;
            }

            public Builder mergeApplyFirmware(Firmware.ApplyFirmware applyFirmware) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeApplyFirmware(applyFirmware);
                return this;
            }

            public Builder mergeBulkDataManifestEntry(Bulkdata.BulkDataManifestEntry bulkDataManifestEntry) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeBulkDataManifestEntry(bulkDataManifestEntry);
                return this;
            }

            public Builder mergeBulkDataTransferComplete(Bulkdata.BulkDataTransferComplete bulkDataTransferComplete) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeBulkDataTransferComplete(bulkDataTransferComplete);
                return this;
            }

            public Builder mergeBulkDataTransferStart(Bulkdata.BulkDataTransferStart bulkDataTransferStart) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeBulkDataTransferStart(bulkDataTransferStart);
                return this;
            }

            public Builder mergeCompleteHandshake(Keyexchange.CompleteHandshake completeHandshake) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeCompleteHandshake(completeHandshake);
                return this;
            }

            public Builder mergeCompleteSetup(Device.CompleteSetup completeSetup) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeCompleteSetup(completeSetup);
                return this;
            }

            public Builder mergeConfirmResetKey(Keyexchange.ConfirmResetKey confirmResetKey) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeConfirmResetKey(confirmResetKey);
                return this;
            }

            public Builder mergeConnectUser(System.ConnectUser connectUser) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeConnectUser(connectUser);
                return this;
            }

            public Builder mergeDisconnectUser(System.DisconnectUser disconnectUser) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeDisconnectUser(disconnectUser);
                return this;
            }

            public Builder mergeDisplayContent(Cardrendering.DisplayContent displayContent) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeDisplayContent(displayContent);
                return this;
            }

            public Builder mergeEndCall(Nonhfpcalling.EndCall endCall) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeEndCall(endCall);
                return this;
            }

            public Builder mergeEndOfSmsMessageListResponse(Mapsms.EndOfSmsMessageListResponse endOfSmsMessageListResponse) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeEndOfSmsMessageListResponse(endOfSmsMessageListResponse);
                return this;
            }

            public Builder mergeEndpointSpeech(Speech.EndpointSpeech endpointSpeech) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeEndpointSpeech(endpointSpeech);
                return this;
            }

            public Builder mergeExecuteNotificationAction(Notification.ExecuteNotificationAction executeNotificationAction) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeExecuteNotificationAction(executeNotificationAction);
                return this;
            }

            public Builder mergeFirmwareUpdateUnavailable(Firmware.FirmwareUpdateUnavailable firmwareUpdateUnavailable) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeFirmwareUpdateUnavailable(firmwareUpdateUnavailable);
                return this;
            }

            public Builder mergeForwardAtCommand(Calling.ForwardATCommand forwardATCommand) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeForwardAtCommand(forwardATCommand);
                return this;
            }

            public Builder mergeGetArtifactFilter(Firmware.GetArtifactFilter getArtifactFilter) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetArtifactFilter(getArtifactFilter);
                return this;
            }

            public Builder mergeGetArtifactUpdatePreference(Firmware.GetArtifactUpdatePreference getArtifactUpdatePreference) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetArtifactUpdatePreference(getArtifactUpdatePreference);
                return this;
            }

            public Builder mergeGetAudiogram(Hearing.GetAudiogram getAudiogram) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetAudiogram(getAudiogram);
                return this;
            }

            public Builder mergeGetBulkDataManifest(Bulkdata.GetBulkDataManifest getBulkDataManifest) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetBulkDataManifest(getBulkDataManifest);
                return this;
            }

            public Builder mergeGetCachedComponent(Firmware.GetCachedComponent getCachedComponent) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetCachedComponent(getCachedComponent);
                return this;
            }

            public Builder mergeGetCblInformation(Cbl.GetCblInformation getCblInformation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetCblInformation(getCblInformation);
                return this;
            }

            public Builder mergeGetCblLoginState(Cbl.GetCblLoginState getCblLoginState) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetCblLoginState(getCblLoginState);
                return this;
            }

            public Builder mergeGetCentralInformation(Central.GetCentralInformation getCentralInformation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetCentralInformation(getCentralInformation);
                return this;
            }

            public Builder mergeGetCentralNotificationAppAttributes(Ancs.GetCentralNotificationAppAttributes getCentralNotificationAppAttributes) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetCentralNotificationAppAttributes(getCentralNotificationAppAttributes);
                return this;
            }

            public Builder mergeGetCentralNotificationAttributes(Ancs.GetCentralNotificationAttributes getCentralNotificationAttributes) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetCentralNotificationAttributes(getCentralNotificationAttributes);
                return this;
            }

            public Builder mergeGetCloudPairingAttributes(Cloudpairing.GetCloudPairingAttributes getCloudPairingAttributes) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetCloudPairingAttributes(getCloudPairingAttributes);
                return this;
            }

            public Builder mergeGetCloudPairingStatus(Cloudpairing.GetCloudPairingStatus getCloudPairingStatus) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetCloudPairingStatus(getCloudPairingStatus);
                return this;
            }

            public Builder mergeGetCurrentUser(System.GetCurrentUser getCurrentUser) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetCurrentUser(getCurrentUser);
                return this;
            }

            public Builder mergeGetDeviceArtifacts(Firmware.GetDeviceArtifacts getDeviceArtifacts) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetDeviceArtifacts(getDeviceArtifacts);
                return this;
            }

            public Builder mergeGetDeviceConfiguration(Device.GetDeviceConfiguration getDeviceConfiguration) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetDeviceConfiguration(getDeviceConfiguration);
                return this;
            }

            public Builder mergeGetDeviceFeatures(Device.GetDeviceFeatures getDeviceFeatures) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetDeviceFeatures(getDeviceFeatures);
                return this;
            }

            public Builder mergeGetDeviceInformation(Device.GetDeviceInformation getDeviceInformation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetDeviceInformation(getDeviceInformation);
                return this;
            }

            public Builder mergeGetDiagnostics(DiagnosticsOuterClass.GetDiagnostics getDiagnostics) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetDiagnostics(getDiagnostics);
                return this;
            }

            public Builder mergeGetFirmwareInformation(Firmware.GetFirmwareInformation getFirmwareInformation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetFirmwareInformation(getFirmwareInformation);
                return this;
            }

            public Builder mergeGetFirmwareUpdatePreferences(Firmware.GetFirmwareUpdatePreferences getFirmwareUpdatePreferences) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetFirmwareUpdatePreferences(getFirmwareUpdatePreferences);
                return this;
            }

            public Builder mergeGetFitnessData(Fitness.GetFitnessData getFitnessData) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetFitnessData(getFitnessData);
                return this;
            }

            public Builder mergeGetInputBehavior(Input.GetInputBehavior getInputBehavior) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetInputBehavior(getInputBehavior);
                return this;
            }

            public Builder mergeGetLocales(System.GetLocales getLocales) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetLocales(getLocales);
                return this;
            }

            public Builder mergeGetMediaEnhancementCorrectionAmount(Hearing.GetMediaEnhancementCorrectionAmount getMediaEnhancementCorrectionAmount) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetMediaEnhancementCorrectionAmount(getMediaEnhancementCorrectionAmount);
                return this;
            }

            public Builder mergeGetPlaybackStatus(Media.GetPlaybackStatus getPlaybackStatus) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetPlaybackStatus(getPlaybackStatus);
                return this;
            }

            public Builder mergeGetSmsMessageList(Mapsms.GetSmsMessageList getSmsMessageList) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetSmsMessageList(getSmsMessageList);
                return this;
            }

            public Builder mergeGetSmsMessageListResponse(Mapsms.GetSmsMessageListResponse getSmsMessageListResponse) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetSmsMessageListResponse(getSmsMessageListResponse);
                return this;
            }

            public Builder mergeGetState(StateOuterClass.GetState getState) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetState(getState);
                return this;
            }

            public Builder mergeGetUsers(System.GetUsers getUsers) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetUsers(getUsers);
                return this;
            }

            public Builder mergeGetWakewords(System.GetWakewords getWakewords) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeGetWakewords(getWakewords);
                return this;
            }

            public Builder mergeIncomingCall(Calling.IncomingCall incomingCall) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeIncomingCall(incomingCall);
                return this;
            }

            public Builder mergeInitiateFirmwareUpdate(Firmware.InitiateFirmwareUpdate initiateFirmwareUpdate) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeInitiateFirmwareUpdate(initiateFirmwareUpdate);
                return this;
            }

            public Builder mergeInitiateHandshake(Keyexchange.InitiateHandshake initiateHandshake) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeInitiateHandshake(initiateHandshake);
                return this;
            }

            public Builder mergeInitiateMapConnection(Mapsms.InitiateMapConnection initiateMapConnection) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeInitiateMapConnection(initiateMapConnection);
                return this;
            }

            public Builder mergeIssueInputEvent(Input.IssueInputEvent issueInputEvent) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeIssueInputEvent(issueInputEvent);
                return this;
            }

            public Builder mergeIssueMediaControl(Media.IssueMediaControl issueMediaControl) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeIssueMediaControl(issueMediaControl);
                return this;
            }

            public Builder mergeIssueRemoteClearPairing(Instrumentation.IssueRemoteClearPairing issueRemoteClearPairing) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeIssueRemoteClearPairing(issueRemoteClearPairing);
                return this;
            }

            public Builder mergeIssueRemoteCommand(Instrumentation.IssueRemoteCommand issueRemoteCommand) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeIssueRemoteCommand(issueRemoteCommand);
                return this;
            }

            public Builder mergeIssueRemoteReset(Instrumentation.IssueRemoteReset issueRemoteReset) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeIssueRemoteReset(issueRemoteReset);
                return this;
            }

            public Builder mergeIssueRemoteRestart(Instrumentation.IssueRemoteRestart issueRemoteRestart) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeIssueRemoteRestart(issueRemoteRestart);
                return this;
            }

            public Builder mergeKeepAlive(System.KeepAlive keepAlive) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeKeepAlive(keepAlive);
                return this;
            }

            public Builder mergeLaunchApp(System.LaunchApp launchApp) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeLaunchApp(launchApp);
                return this;
            }

            public Builder mergeLiveFitnessData(Fitness.LiveFitnessData liveFitnessData) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeLiveFitnessData(liveFitnessData);
                return this;
            }

            public Builder mergeMediaEventOccurred(Media.MediaEventOccurred mediaEventOccurred) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeMediaEventOccurred(mediaEventOccurred);
                return this;
            }

            public Builder mergeNotifyBulkDataAvailable(Bulkdata.NotifyBulkDataAvailable notifyBulkDataAvailable) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeNotifyBulkDataAvailable(notifyBulkDataAvailable);
                return this;
            }

            public Builder mergeNotifyCblLoginState(Cbl.NotifyCblLoginState notifyCblLoginState) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeNotifyCblLoginState(notifyCblLoginState);
                return this;
            }

            public Builder mergeNotifyDeviceConfiguration(Device.NotifyDeviceConfiguration notifyDeviceConfiguration) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeNotifyDeviceConfiguration(notifyDeviceConfiguration);
                return this;
            }

            public Builder mergeNotifyDeviceInformation(Device.NotifyDeviceInformation notifyDeviceInformation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeNotifyDeviceInformation(notifyDeviceInformation);
                return this;
            }

            public Builder mergeNotifyDiagnosticsAvailable(DiagnosticsOuterClass.NotifyDiagnosticsAvailable notifyDiagnosticsAvailable) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeNotifyDiagnosticsAvailable(notifyDiagnosticsAvailable);
                return this;
            }

            public Builder mergeNotifyFirmwareInformation(Firmware.NotifyFirmwareInformation notifyFirmwareInformation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeNotifyFirmwareInformation(notifyFirmwareInformation);
                return this;
            }

            public Builder mergeNotifyFitnessDataAvailable(Fitness.NotifyFitnessDataAvailable notifyFitnessDataAvailable) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeNotifyFitnessDataAvailable(notifyFitnessDataAvailable);
                return this;
            }

            public Builder mergeNotifySmsMessageList(Mapsms.NotifySmsMessageList notifySmsMessageList) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeNotifySmsMessageList(notifySmsMessageList);
                return this;
            }

            public Builder mergeNotifySpeechState(Speech.NotifySpeechState notifySpeechState) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeNotifySpeechState(notifySpeechState);
                return this;
            }

            public Builder mergeOverrideAssistant(Device.OverrideAssistant overrideAssistant) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeOverrideAssistant(overrideAssistant);
                return this;
            }

            public Builder mergePerformCentralNotificationAction(Ancs.PerformCentralNotificationAction performCentralNotificationAction) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergePerformCentralNotificationAction(performCentralNotificationAction);
                return this;
            }

            public Builder mergePrintDebug(Instrumentation.PrintDebug printDebug) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergePrintDebug(printDebug);
                return this;
            }

            public Builder mergeProvideSpeech(Speech.ProvideSpeech provideSpeech) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeProvideSpeech(provideSpeech);
                return this;
            }

            public Builder mergeProvideTranslation(Translation.ProvideTranslation provideTranslation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeProvideTranslation(provideTranslation);
                return this;
            }

            public Builder mergePublishCentralNotification(Ancs.PublishCentralNotification publishCentralNotification) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergePublishCentralNotification(publishCentralNotification);
                return this;
            }

            public Builder mergePushMetrics(Metrics.PushMetrics pushMetrics) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergePushMetrics(pushMetrics);
                return this;
            }

            public Builder mergeRegisterForMediaEvents(Media.RegisterForMediaEvents registerForMediaEvents) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeRegisterForMediaEvents(registerForMediaEvents);
                return this;
            }

            public Builder mergeRejectCall(Nonhfpcalling.RejectCall rejectCall) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeRejectCall(rejectCall);
                return this;
            }

            public Builder mergeRemoveCloudPairingKeys(Cloudpairing.RemoveCloudPairingKeys removeCloudPairingKeys) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeRemoveCloudPairingKeys(removeCloudPairingKeys);
                return this;
            }

            public Builder mergeRemoveDevice(System.RemoveDevice removeDevice) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeRemoveDevice(removeDevice);
                return this;
            }

            public Builder mergeRemoveNotification(Notification.RemoveNotification removeNotification) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeRemoveNotification(removeNotification);
                return this;
            }

            public Builder mergeReplaceCloudPairingKeys(Cloudpairing.ReplaceCloudPairingKeys replaceCloudPairingKeys) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeReplaceCloudPairingKeys(replaceCloudPairingKeys);
                return this;
            }

            public Builder mergeRequestBulkDataTransfer(Bulkdata.RequestBulkDataTransfer requestBulkDataTransfer) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeRequestBulkDataTransfer(requestBulkDataTransfer);
                return this;
            }

            public Builder mergeResetCachedComponent(Firmware.ResetCachedComponent resetCachedComponent) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeResetCachedComponent(resetCachedComponent);
                return this;
            }

            public Builder mergeResetConnection(System.ResetConnection resetConnection) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeResetConnection(resetConnection);
                return this;
            }

            public Builder mergeResetInputBehavior(Input.ResetInputBehavior resetInputBehavior) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeResetInputBehavior(resetInputBehavior);
                return this;
            }

            public Builder mergeResetKey(Keyexchange.ResetKey resetKey) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeResetKey(resetKey);
                return this;
            }

            public Builder mergeResetRootKeys(Keyexchange.ResetRootKeys resetRootKeys) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeResetRootKeys(resetRootKeys);
                return this;
            }

            public Builder mergeResponse(Response response) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeResponse(response);
                return this;
            }

            public Builder mergeSendSms(Mapsms.SendSms sendSms) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSendSms(sendSms);
                return this;
            }

            public Builder mergeSetAudiogram(Hearing.SetAudiogram setAudiogram) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSetAudiogram(setAudiogram);
                return this;
            }

            public Builder mergeSetCloudPairingKeys(Cloudpairing.SetCloudPairingKeys setCloudPairingKeys) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSetCloudPairingKeys(setCloudPairingKeys);
                return this;
            }

            public Builder mergeSetInputBehavior(Input.SetInputBehavior setInputBehavior) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSetInputBehavior(setInputBehavior);
                return this;
            }

            public Builder mergeSetLocale(System.SetLocale setLocale) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSetLocale(setLocale);
                return this;
            }

            public Builder mergeSetMediaEnhancementCorrectionAmount(Hearing.SetMediaEnhancementCorrectionAmount setMediaEnhancementCorrectionAmount) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSetMediaEnhancementCorrectionAmount(setMediaEnhancementCorrectionAmount);
                return this;
            }

            public Builder mergeSetSmsReadStatus(Mapsms.SetSmsReadStatus setSmsReadStatus) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSetSmsReadStatus(setSmsReadStatus);
                return this;
            }

            public Builder mergeSetState(StateOuterClass.SetState setState) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSetState(setState);
                return this;
            }

            public Builder mergeSetWakewords(System.SetWakewords setWakewords) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSetWakewords(setWakewords);
                return this;
            }

            public Builder mergeStartFirmwareUpdate(Firmware.StartFirmwareUpdate startFirmwareUpdate) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeStartFirmwareUpdate(startFirmwareUpdate);
                return this;
            }

            public Builder mergeStartLiveFitnessData(Fitness.StartLiveFitnessData startLiveFitnessData) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeStartLiveFitnessData(startLiveFitnessData);
                return this;
            }

            public Builder mergeStartSetup(Device.StartSetup startSetup) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeStartSetup(startSetup);
                return this;
            }

            public Builder mergeStartSpeech(Speech.StartSpeech startSpeech) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeStartSpeech(startSpeech);
                return this;
            }

            public Builder mergeStartTranslation(Translation.StartTranslation startTranslation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeStartTranslation(startTranslation);
                return this;
            }

            public Builder mergeStartVoiceStream(Voicestream.StartVoiceStream startVoiceStream) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeStartVoiceStream(startVoiceStream);
                return this;
            }

            public Builder mergeStopBulkDataTransfer(Bulkdata.StopBulkDataTransfer stopBulkDataTransfer) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeStopBulkDataTransfer(stopBulkDataTransfer);
                return this;
            }

            public Builder mergeStopDiagnostics(DiagnosticsOuterClass.StopDiagnostics stopDiagnostics) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeStopDiagnostics(stopDiagnostics);
                return this;
            }

            public Builder mergeStopFitnessData(Fitness.StopFitnessData stopFitnessData) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeStopFitnessData(stopFitnessData);
                return this;
            }

            public Builder mergeStopLiveFitnessData(Fitness.StopLiveFitnessData stopLiveFitnessData) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeStopLiveFitnessData(stopLiveFitnessData);
                return this;
            }

            public Builder mergeStopSpeech(Speech.StopSpeech stopSpeech) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeStopSpeech(stopSpeech);
                return this;
            }

            public Builder mergeStopTranslation(Translation.StopTranslation stopTranslation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeStopTranslation(stopTranslation);
                return this;
            }

            public Builder mergeStopVoiceStream(Voicestream.StopVoiceStream stopVoiceStream) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeStopVoiceStream(stopVoiceStream);
                return this;
            }

            public Builder mergeSubscribeNotificationCenter(Ancs.SubscribeNotificationCenter subscribeNotificationCenter) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSubscribeNotificationCenter(subscribeNotificationCenter);
                return this;
            }

            public Builder mergeSwitchCurrentUser(System.SwitchCurrentUser switchCurrentUser) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSwitchCurrentUser(switchCurrentUser);
                return this;
            }

            public Builder mergeSwitchTransport(Transport.SwitchTransport switchTransport) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSwitchTransport(switchTransport);
                return this;
            }

            public Builder mergeSyncFitnessSession(Fitness.SyncFitnessSession syncFitnessSession) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSyncFitnessSession(syncFitnessSession);
                return this;
            }

            public Builder mergeSynchronizeSettings(System.SynchronizeSettings synchronizeSettings) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSynchronizeSettings(synchronizeSettings);
                return this;
            }

            public Builder mergeSynchronizeState(StateOuterClass.SynchronizeState synchronizeState) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeSynchronizeState(synchronizeState);
                return this;
            }

            public Builder mergeUnpairUser(System.UnpairUser unpairUser) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeUnpairUser(unpairUser);
                return this;
            }

            public Builder mergeUnsubscribeNotificationCenter(Ancs.UnsubscribeNotificationCenter unsubscribeNotificationCenter) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeUnsubscribeNotificationCenter(unsubscribeNotificationCenter);
                return this;
            }

            public Builder mergeUpdateCallState(Nonhfpcalling.UpdateCallState updateCallState) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeUpdateCallState(updateCallState);
                return this;
            }

            public Builder mergeUpdateCentralNotificationAttributes(Ancs.UpdateCentralNotificationAttributes updateCentralNotificationAttributes) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeUpdateCentralNotificationAttributes(updateCentralNotificationAttributes);
                return this;
            }

            public Builder mergeUpdateComponentSegment(Firmware.UpdateComponentSegment updateComponentSegment) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeUpdateComponentSegment(updateComponentSegment);
                return this;
            }

            public Builder mergeUpdateDeviceInformation(Device.UpdateDeviceInformation updateDeviceInformation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeUpdateDeviceInformation(updateDeviceInformation);
                return this;
            }

            public Builder mergeUpdateMetricsMap(Metrics.UpdateMetricsMap updateMetricsMap) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeUpdateMetricsMap(updateMetricsMap);
                return this;
            }

            public Builder mergeUpdateNotification(Notification.UpdateNotification updateNotification) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeUpdateNotification(updateNotification);
                return this;
            }

            public Builder mergeUpdateUsers(System.UpdateUsers updateUsers) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeUpdateUsers(updateUsers);
                return this;
            }

            public Builder mergeUpgradeTransport(Transport.UpgradeTransport upgradeTransport) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeUpgradeTransport(upgradeTransport);
                return this;
            }

            public Builder mergeUserConfirmed(Keyexchange.UserConfirmed userConfirmed) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeUserConfirmed(userConfirmed);
                return this;
            }

            public Builder mergeVerifyArtifactSignature(Firmware.VerifyArtifactSignature verifyArtifactSignature) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).mergeVerifyArtifactSignature(verifyArtifactSignature);
                return this;
            }

            public Builder setAcceptCall(Nonhfpcalling.AcceptCall acceptCall) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setAcceptCall(acceptCall);
                return this;
            }

            public Builder setAddNotification(Notification.AddNotification addNotification) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setAddNotification(addNotification);
                return this;
            }

            public Builder setApplyFirmware(Firmware.ApplyFirmware applyFirmware) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setApplyFirmware(applyFirmware);
                return this;
            }

            public Builder setBulkDataManifestEntry(Bulkdata.BulkDataManifestEntry bulkDataManifestEntry) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setBulkDataManifestEntry(bulkDataManifestEntry);
                return this;
            }

            public Builder setBulkDataTransferComplete(Bulkdata.BulkDataTransferComplete bulkDataTransferComplete) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setBulkDataTransferComplete(bulkDataTransferComplete);
                return this;
            }

            public Builder setBulkDataTransferStart(Bulkdata.BulkDataTransferStart bulkDataTransferStart) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setBulkDataTransferStart(bulkDataTransferStart);
                return this;
            }

            public Builder setCommand(Command command) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setCommand(command);
                return this;
            }

            public Builder setCommandValue(int i) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setCommandValue(i);
                return this;
            }

            public Builder setCompleteHandshake(Keyexchange.CompleteHandshake completeHandshake) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setCompleteHandshake(completeHandshake);
                return this;
            }

            public Builder setCompleteSetup(Device.CompleteSetup completeSetup) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setCompleteSetup(completeSetup);
                return this;
            }

            public Builder setConfirmResetKey(Keyexchange.ConfirmResetKey confirmResetKey) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setConfirmResetKey(confirmResetKey);
                return this;
            }

            public Builder setConnectUser(System.ConnectUser connectUser) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setConnectUser(connectUser);
                return this;
            }

            public Builder setDisconnectUser(System.DisconnectUser disconnectUser) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setDisconnectUser(disconnectUser);
                return this;
            }

            public Builder setDisplayContent(Cardrendering.DisplayContent displayContent) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setDisplayContent(displayContent);
                return this;
            }

            public Builder setEndCall(Nonhfpcalling.EndCall endCall) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setEndCall(endCall);
                return this;
            }

            public Builder setEndOfSmsMessageListResponse(Mapsms.EndOfSmsMessageListResponse endOfSmsMessageListResponse) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setEndOfSmsMessageListResponse(endOfSmsMessageListResponse);
                return this;
            }

            public Builder setEndpointSpeech(Speech.EndpointSpeech endpointSpeech) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setEndpointSpeech(endpointSpeech);
                return this;
            }

            public Builder setExecuteNotificationAction(Notification.ExecuteNotificationAction executeNotificationAction) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setExecuteNotificationAction(executeNotificationAction);
                return this;
            }

            public Builder setFirmwareUpdateUnavailable(Firmware.FirmwareUpdateUnavailable firmwareUpdateUnavailable) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setFirmwareUpdateUnavailable(firmwareUpdateUnavailable);
                return this;
            }

            public Builder setForwardAtCommand(Calling.ForwardATCommand forwardATCommand) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setForwardAtCommand(forwardATCommand);
                return this;
            }

            public Builder setGetArtifactFilter(Firmware.GetArtifactFilter getArtifactFilter) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetArtifactFilter(getArtifactFilter);
                return this;
            }

            public Builder setGetArtifactUpdatePreference(Firmware.GetArtifactUpdatePreference getArtifactUpdatePreference) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetArtifactUpdatePreference(getArtifactUpdatePreference);
                return this;
            }

            public Builder setGetAudiogram(Hearing.GetAudiogram getAudiogram) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetAudiogram(getAudiogram);
                return this;
            }

            public Builder setGetBulkDataManifest(Bulkdata.GetBulkDataManifest getBulkDataManifest) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetBulkDataManifest(getBulkDataManifest);
                return this;
            }

            public Builder setGetCachedComponent(Firmware.GetCachedComponent getCachedComponent) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCachedComponent(getCachedComponent);
                return this;
            }

            public Builder setGetCblInformation(Cbl.GetCblInformation getCblInformation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCblInformation(getCblInformation);
                return this;
            }

            public Builder setGetCblLoginState(Cbl.GetCblLoginState getCblLoginState) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCblLoginState(getCblLoginState);
                return this;
            }

            public Builder setGetCentralInformation(Central.GetCentralInformation getCentralInformation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCentralInformation(getCentralInformation);
                return this;
            }

            public Builder setGetCentralNotificationAppAttributes(Ancs.GetCentralNotificationAppAttributes getCentralNotificationAppAttributes) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCentralNotificationAppAttributes(getCentralNotificationAppAttributes);
                return this;
            }

            public Builder setGetCentralNotificationAttributes(Ancs.GetCentralNotificationAttributes getCentralNotificationAttributes) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCentralNotificationAttributes(getCentralNotificationAttributes);
                return this;
            }

            public Builder setGetCloudPairingAttributes(Cloudpairing.GetCloudPairingAttributes getCloudPairingAttributes) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCloudPairingAttributes(getCloudPairingAttributes);
                return this;
            }

            public Builder setGetCloudPairingStatus(Cloudpairing.GetCloudPairingStatus getCloudPairingStatus) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCloudPairingStatus(getCloudPairingStatus);
                return this;
            }

            public Builder setGetCurrentUser(System.GetCurrentUser getCurrentUser) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCurrentUser(getCurrentUser);
                return this;
            }

            public Builder setGetDeviceArtifacts(Firmware.GetDeviceArtifacts getDeviceArtifacts) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetDeviceArtifacts(getDeviceArtifacts);
                return this;
            }

            public Builder setGetDeviceConfiguration(Device.GetDeviceConfiguration getDeviceConfiguration) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetDeviceConfiguration(getDeviceConfiguration);
                return this;
            }

            public Builder setGetDeviceFeatures(Device.GetDeviceFeatures getDeviceFeatures) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetDeviceFeatures(getDeviceFeatures);
                return this;
            }

            public Builder setGetDeviceInformation(Device.GetDeviceInformation getDeviceInformation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetDeviceInformation(getDeviceInformation);
                return this;
            }

            public Builder setGetDiagnostics(DiagnosticsOuterClass.GetDiagnostics getDiagnostics) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetDiagnostics(getDiagnostics);
                return this;
            }

            public Builder setGetFirmwareInformation(Firmware.GetFirmwareInformation getFirmwareInformation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetFirmwareInformation(getFirmwareInformation);
                return this;
            }

            public Builder setGetFirmwareUpdatePreferences(Firmware.GetFirmwareUpdatePreferences getFirmwareUpdatePreferences) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetFirmwareUpdatePreferences(getFirmwareUpdatePreferences);
                return this;
            }

            public Builder setGetFitnessData(Fitness.GetFitnessData getFitnessData) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetFitnessData(getFitnessData);
                return this;
            }

            public Builder setGetInputBehavior(Input.GetInputBehavior getInputBehavior) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetInputBehavior(getInputBehavior);
                return this;
            }

            public Builder setGetLocales(System.GetLocales getLocales) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetLocales(getLocales);
                return this;
            }

            public Builder setGetMediaEnhancementCorrectionAmount(Hearing.GetMediaEnhancementCorrectionAmount getMediaEnhancementCorrectionAmount) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetMediaEnhancementCorrectionAmount(getMediaEnhancementCorrectionAmount);
                return this;
            }

            public Builder setGetPlaybackStatus(Media.GetPlaybackStatus getPlaybackStatus) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetPlaybackStatus(getPlaybackStatus);
                return this;
            }

            public Builder setGetSmsMessageList(Mapsms.GetSmsMessageList getSmsMessageList) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetSmsMessageList(getSmsMessageList);
                return this;
            }

            public Builder setGetSmsMessageListResponse(Mapsms.GetSmsMessageListResponse getSmsMessageListResponse) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetSmsMessageListResponse(getSmsMessageListResponse);
                return this;
            }

            public Builder setGetState(StateOuterClass.GetState getState) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetState(getState);
                return this;
            }

            public Builder setGetUsers(System.GetUsers getUsers) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetUsers(getUsers);
                return this;
            }

            public Builder setGetWakewords(System.GetWakewords getWakewords) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetWakewords(getWakewords);
                return this;
            }

            public Builder setIncomingCall(Calling.IncomingCall incomingCall) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setIncomingCall(incomingCall);
                return this;
            }

            public Builder setInitiateFirmwareUpdate(Firmware.InitiateFirmwareUpdate initiateFirmwareUpdate) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setInitiateFirmwareUpdate(initiateFirmwareUpdate);
                return this;
            }

            public Builder setInitiateHandshake(Keyexchange.InitiateHandshake initiateHandshake) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setInitiateHandshake(initiateHandshake);
                return this;
            }

            public Builder setInitiateMapConnection(Mapsms.InitiateMapConnection initiateMapConnection) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setInitiateMapConnection(initiateMapConnection);
                return this;
            }

            public Builder setIssueInputEvent(Input.IssueInputEvent issueInputEvent) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setIssueInputEvent(issueInputEvent);
                return this;
            }

            public Builder setIssueMediaControl(Media.IssueMediaControl issueMediaControl) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setIssueMediaControl(issueMediaControl);
                return this;
            }

            public Builder setIssueRemoteClearPairing(Instrumentation.IssueRemoteClearPairing issueRemoteClearPairing) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setIssueRemoteClearPairing(issueRemoteClearPairing);
                return this;
            }

            public Builder setIssueRemoteCommand(Instrumentation.IssueRemoteCommand issueRemoteCommand) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setIssueRemoteCommand(issueRemoteCommand);
                return this;
            }

            public Builder setIssueRemoteReset(Instrumentation.IssueRemoteReset issueRemoteReset) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setIssueRemoteReset(issueRemoteReset);
                return this;
            }

            public Builder setIssueRemoteRestart(Instrumentation.IssueRemoteRestart issueRemoteRestart) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setIssueRemoteRestart(issueRemoteRestart);
                return this;
            }

            public Builder setKeepAlive(System.KeepAlive keepAlive) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setKeepAlive(keepAlive);
                return this;
            }

            public Builder setLaunchApp(System.LaunchApp launchApp) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setLaunchApp(launchApp);
                return this;
            }

            public Builder setLiveFitnessData(Fitness.LiveFitnessData liveFitnessData) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setLiveFitnessData(liveFitnessData);
                return this;
            }

            public Builder setMediaEventOccurred(Media.MediaEventOccurred mediaEventOccurred) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setMediaEventOccurred(mediaEventOccurred);
                return this;
            }

            public Builder setNotifyBulkDataAvailable(Bulkdata.NotifyBulkDataAvailable notifyBulkDataAvailable) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifyBulkDataAvailable(notifyBulkDataAvailable);
                return this;
            }

            public Builder setNotifyCblLoginState(Cbl.NotifyCblLoginState notifyCblLoginState) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifyCblLoginState(notifyCblLoginState);
                return this;
            }

            public Builder setNotifyDeviceConfiguration(Device.NotifyDeviceConfiguration notifyDeviceConfiguration) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifyDeviceConfiguration(notifyDeviceConfiguration);
                return this;
            }

            public Builder setNotifyDeviceInformation(Device.NotifyDeviceInformation notifyDeviceInformation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifyDeviceInformation(notifyDeviceInformation);
                return this;
            }

            public Builder setNotifyDiagnosticsAvailable(DiagnosticsOuterClass.NotifyDiagnosticsAvailable notifyDiagnosticsAvailable) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifyDiagnosticsAvailable(notifyDiagnosticsAvailable);
                return this;
            }

            public Builder setNotifyFirmwareInformation(Firmware.NotifyFirmwareInformation notifyFirmwareInformation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifyFirmwareInformation(notifyFirmwareInformation);
                return this;
            }

            public Builder setNotifyFitnessDataAvailable(Fitness.NotifyFitnessDataAvailable notifyFitnessDataAvailable) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifyFitnessDataAvailable(notifyFitnessDataAvailable);
                return this;
            }

            public Builder setNotifySmsMessageList(Mapsms.NotifySmsMessageList notifySmsMessageList) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifySmsMessageList(notifySmsMessageList);
                return this;
            }

            public Builder setNotifySpeechState(Speech.NotifySpeechState notifySpeechState) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifySpeechState(notifySpeechState);
                return this;
            }

            public Builder setOverrideAssistant(Device.OverrideAssistant overrideAssistant) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setOverrideAssistant(overrideAssistant);
                return this;
            }

            public Builder setPerformCentralNotificationAction(Ancs.PerformCentralNotificationAction performCentralNotificationAction) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setPerformCentralNotificationAction(performCentralNotificationAction);
                return this;
            }

            public Builder setPrintDebug(Instrumentation.PrintDebug printDebug) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setPrintDebug(printDebug);
                return this;
            }

            public Builder setProvideSpeech(Speech.ProvideSpeech provideSpeech) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setProvideSpeech(provideSpeech);
                return this;
            }

            public Builder setProvideTranslation(Translation.ProvideTranslation provideTranslation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setProvideTranslation(provideTranslation);
                return this;
            }

            public Builder setPublishCentralNotification(Ancs.PublishCentralNotification publishCentralNotification) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setPublishCentralNotification(publishCentralNotification);
                return this;
            }

            public Builder setPushMetrics(Metrics.PushMetrics pushMetrics) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setPushMetrics(pushMetrics);
                return this;
            }

            public Builder setRegisterForMediaEvents(Media.RegisterForMediaEvents registerForMediaEvents) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setRegisterForMediaEvents(registerForMediaEvents);
                return this;
            }

            public Builder setRejectCall(Nonhfpcalling.RejectCall rejectCall) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setRejectCall(rejectCall);
                return this;
            }

            public Builder setRemoveCloudPairingKeys(Cloudpairing.RemoveCloudPairingKeys removeCloudPairingKeys) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setRemoveCloudPairingKeys(removeCloudPairingKeys);
                return this;
            }

            public Builder setRemoveDevice(System.RemoveDevice removeDevice) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setRemoveDevice(removeDevice);
                return this;
            }

            public Builder setRemoveNotification(Notification.RemoveNotification removeNotification) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setRemoveNotification(removeNotification);
                return this;
            }

            public Builder setReplaceCloudPairingKeys(Cloudpairing.ReplaceCloudPairingKeys replaceCloudPairingKeys) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setReplaceCloudPairingKeys(replaceCloudPairingKeys);
                return this;
            }

            public Builder setRequestBulkDataTransfer(Bulkdata.RequestBulkDataTransfer requestBulkDataTransfer) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setRequestBulkDataTransfer(requestBulkDataTransfer);
                return this;
            }

            public Builder setResetCachedComponent(Firmware.ResetCachedComponent resetCachedComponent) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setResetCachedComponent(resetCachedComponent);
                return this;
            }

            public Builder setResetConnection(System.ResetConnection resetConnection) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setResetConnection(resetConnection);
                return this;
            }

            public Builder setResetInputBehavior(Input.ResetInputBehavior resetInputBehavior) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setResetInputBehavior(resetInputBehavior);
                return this;
            }

            public Builder setResetKey(Keyexchange.ResetKey resetKey) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setResetKey(resetKey);
                return this;
            }

            public Builder setResetRootKeys(Keyexchange.ResetRootKeys resetRootKeys) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setResetRootKeys(resetRootKeys);
                return this;
            }

            public Builder setResponse(Response response) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setResponse(response);
                return this;
            }

            public Builder setSendSms(Mapsms.SendSms sendSms) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSendSms(sendSms);
                return this;
            }

            public Builder setSetAudiogram(Hearing.SetAudiogram setAudiogram) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetAudiogram(setAudiogram);
                return this;
            }

            public Builder setSetCloudPairingKeys(Cloudpairing.SetCloudPairingKeys setCloudPairingKeys) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetCloudPairingKeys(setCloudPairingKeys);
                return this;
            }

            public Builder setSetInputBehavior(Input.SetInputBehavior setInputBehavior) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetInputBehavior(setInputBehavior);
                return this;
            }

            public Builder setSetLocale(System.SetLocale setLocale) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetLocale(setLocale);
                return this;
            }

            public Builder setSetMediaEnhancementCorrectionAmount(Hearing.SetMediaEnhancementCorrectionAmount setMediaEnhancementCorrectionAmount) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetMediaEnhancementCorrectionAmount(setMediaEnhancementCorrectionAmount);
                return this;
            }

            public Builder setSetSmsReadStatus(Mapsms.SetSmsReadStatus setSmsReadStatus) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetSmsReadStatus(setSmsReadStatus);
                return this;
            }

            public Builder setSetState(StateOuterClass.SetState setState) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetState(setState);
                return this;
            }

            public Builder setSetWakewords(System.SetWakewords setWakewords) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetWakewords(setWakewords);
                return this;
            }

            public Builder setStartFirmwareUpdate(Firmware.StartFirmwareUpdate startFirmwareUpdate) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStartFirmwareUpdate(startFirmwareUpdate);
                return this;
            }

            public Builder setStartLiveFitnessData(Fitness.StartLiveFitnessData startLiveFitnessData) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStartLiveFitnessData(startLiveFitnessData);
                return this;
            }

            public Builder setStartSetup(Device.StartSetup startSetup) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStartSetup(startSetup);
                return this;
            }

            public Builder setStartSpeech(Speech.StartSpeech startSpeech) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStartSpeech(startSpeech);
                return this;
            }

            public Builder setStartTranslation(Translation.StartTranslation startTranslation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStartTranslation(startTranslation);
                return this;
            }

            public Builder setStartVoiceStream(Voicestream.StartVoiceStream startVoiceStream) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStartVoiceStream(startVoiceStream);
                return this;
            }

            public Builder setStopBulkDataTransfer(Bulkdata.StopBulkDataTransfer stopBulkDataTransfer) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStopBulkDataTransfer(stopBulkDataTransfer);
                return this;
            }

            public Builder setStopDiagnostics(DiagnosticsOuterClass.StopDiagnostics stopDiagnostics) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStopDiagnostics(stopDiagnostics);
                return this;
            }

            public Builder setStopFitnessData(Fitness.StopFitnessData stopFitnessData) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStopFitnessData(stopFitnessData);
                return this;
            }

            public Builder setStopLiveFitnessData(Fitness.StopLiveFitnessData stopLiveFitnessData) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStopLiveFitnessData(stopLiveFitnessData);
                return this;
            }

            public Builder setStopSpeech(Speech.StopSpeech stopSpeech) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStopSpeech(stopSpeech);
                return this;
            }

            public Builder setStopTranslation(Translation.StopTranslation stopTranslation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStopTranslation(stopTranslation);
                return this;
            }

            public Builder setStopVoiceStream(Voicestream.StopVoiceStream stopVoiceStream) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStopVoiceStream(stopVoiceStream);
                return this;
            }

            public Builder setSubscribeNotificationCenter(Ancs.SubscribeNotificationCenter subscribeNotificationCenter) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSubscribeNotificationCenter(subscribeNotificationCenter);
                return this;
            }

            public Builder setSwitchCurrentUser(System.SwitchCurrentUser switchCurrentUser) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSwitchCurrentUser(switchCurrentUser);
                return this;
            }

            public Builder setSwitchTransport(Transport.SwitchTransport switchTransport) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSwitchTransport(switchTransport);
                return this;
            }

            public Builder setSyncFitnessSession(Fitness.SyncFitnessSession syncFitnessSession) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSyncFitnessSession(syncFitnessSession);
                return this;
            }

            public Builder setSynchronizeSettings(System.SynchronizeSettings synchronizeSettings) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSynchronizeSettings(synchronizeSettings);
                return this;
            }

            public Builder setSynchronizeState(StateOuterClass.SynchronizeState synchronizeState) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSynchronizeState(synchronizeState);
                return this;
            }

            public Builder setUnpairUser(System.UnpairUser unpairUser) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUnpairUser(unpairUser);
                return this;
            }

            public Builder setUnsubscribeNotificationCenter(Ancs.UnsubscribeNotificationCenter unsubscribeNotificationCenter) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUnsubscribeNotificationCenter(unsubscribeNotificationCenter);
                return this;
            }

            public Builder setUpdateCallState(Nonhfpcalling.UpdateCallState updateCallState) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpdateCallState(updateCallState);
                return this;
            }

            public Builder setUpdateCentralNotificationAttributes(Ancs.UpdateCentralNotificationAttributes updateCentralNotificationAttributes) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpdateCentralNotificationAttributes(updateCentralNotificationAttributes);
                return this;
            }

            public Builder setUpdateComponentSegment(Firmware.UpdateComponentSegment updateComponentSegment) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpdateComponentSegment(updateComponentSegment);
                return this;
            }

            public Builder setUpdateDeviceInformation(Device.UpdateDeviceInformation updateDeviceInformation) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpdateDeviceInformation(updateDeviceInformation);
                return this;
            }

            public Builder setUpdateMetricsMap(Metrics.UpdateMetricsMap updateMetricsMap) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpdateMetricsMap(updateMetricsMap);
                return this;
            }

            public Builder setUpdateNotification(Notification.UpdateNotification updateNotification) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpdateNotification(updateNotification);
                return this;
            }

            public Builder setUpdateUsers(System.UpdateUsers updateUsers) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpdateUsers(updateUsers);
                return this;
            }

            public Builder setUpgradeTransport(Transport.UpgradeTransport upgradeTransport) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpgradeTransport(upgradeTransport);
                return this;
            }

            public Builder setUserConfirmed(Keyexchange.UserConfirmed userConfirmed) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUserConfirmed(userConfirmed);
                return this;
            }

            public Builder setVerifyArtifactSignature(Firmware.VerifyArtifactSignature verifyArtifactSignature) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setVerifyArtifactSignature(verifyArtifactSignature);
                return this;
            }

            private Builder() {
                super(ControlEnvelope.DEFAULT_INSTANCE);
            }

            public Builder setAcceptCall(Nonhfpcalling.AcceptCall.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setAcceptCall(builder);
                return this;
            }

            public Builder setAddNotification(Notification.AddNotification.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setAddNotification(builder);
                return this;
            }

            public Builder setApplyFirmware(Firmware.ApplyFirmware.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setApplyFirmware(builder);
                return this;
            }

            public Builder setBulkDataManifestEntry(Bulkdata.BulkDataManifestEntry.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setBulkDataManifestEntry(builder);
                return this;
            }

            public Builder setBulkDataTransferComplete(Bulkdata.BulkDataTransferComplete.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setBulkDataTransferComplete(builder);
                return this;
            }

            public Builder setBulkDataTransferStart(Bulkdata.BulkDataTransferStart.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setBulkDataTransferStart(builder);
                return this;
            }

            public Builder setCompleteHandshake(Keyexchange.CompleteHandshake.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setCompleteHandshake(builder);
                return this;
            }

            public Builder setCompleteSetup(Device.CompleteSetup.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setCompleteSetup(builder);
                return this;
            }

            public Builder setConfirmResetKey(Keyexchange.ConfirmResetKey.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setConfirmResetKey(builder);
                return this;
            }

            public Builder setConnectUser(System.ConnectUser.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setConnectUser(builder);
                return this;
            }

            public Builder setDisconnectUser(System.DisconnectUser.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setDisconnectUser(builder);
                return this;
            }

            public Builder setDisplayContent(Cardrendering.DisplayContent.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setDisplayContent(builder);
                return this;
            }

            public Builder setEndCall(Nonhfpcalling.EndCall.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setEndCall(builder);
                return this;
            }

            public Builder setEndOfSmsMessageListResponse(Mapsms.EndOfSmsMessageListResponse.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setEndOfSmsMessageListResponse(builder);
                return this;
            }

            public Builder setEndpointSpeech(Speech.EndpointSpeech.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setEndpointSpeech(builder);
                return this;
            }

            public Builder setExecuteNotificationAction(Notification.ExecuteNotificationAction.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setExecuteNotificationAction(builder);
                return this;
            }

            public Builder setFirmwareUpdateUnavailable(Firmware.FirmwareUpdateUnavailable.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setFirmwareUpdateUnavailable(builder);
                return this;
            }

            public Builder setForwardAtCommand(Calling.ForwardATCommand.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setForwardAtCommand(builder);
                return this;
            }

            public Builder setGetArtifactFilter(Firmware.GetArtifactFilter.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetArtifactFilter(builder);
                return this;
            }

            public Builder setGetArtifactUpdatePreference(Firmware.GetArtifactUpdatePreference.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetArtifactUpdatePreference(builder);
                return this;
            }

            public Builder setGetAudiogram(Hearing.GetAudiogram.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetAudiogram(builder);
                return this;
            }

            public Builder setGetBulkDataManifest(Bulkdata.GetBulkDataManifest.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetBulkDataManifest(builder);
                return this;
            }

            public Builder setGetCachedComponent(Firmware.GetCachedComponent.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCachedComponent(builder);
                return this;
            }

            public Builder setGetCblInformation(Cbl.GetCblInformation.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCblInformation(builder);
                return this;
            }

            public Builder setGetCblLoginState(Cbl.GetCblLoginState.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCblLoginState(builder);
                return this;
            }

            public Builder setGetCentralInformation(Central.GetCentralInformation.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCentralInformation(builder);
                return this;
            }

            public Builder setGetCentralNotificationAppAttributes(Ancs.GetCentralNotificationAppAttributes.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCentralNotificationAppAttributes(builder);
                return this;
            }

            public Builder setGetCentralNotificationAttributes(Ancs.GetCentralNotificationAttributes.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCentralNotificationAttributes(builder);
                return this;
            }

            public Builder setGetCloudPairingAttributes(Cloudpairing.GetCloudPairingAttributes.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCloudPairingAttributes(builder);
                return this;
            }

            public Builder setGetCloudPairingStatus(Cloudpairing.GetCloudPairingStatus.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCloudPairingStatus(builder);
                return this;
            }

            public Builder setGetCurrentUser(System.GetCurrentUser.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetCurrentUser(builder);
                return this;
            }

            public Builder setGetDeviceArtifacts(Firmware.GetDeviceArtifacts.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetDeviceArtifacts(builder);
                return this;
            }

            public Builder setGetDeviceConfiguration(Device.GetDeviceConfiguration.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetDeviceConfiguration(builder);
                return this;
            }

            public Builder setGetDeviceFeatures(Device.GetDeviceFeatures.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetDeviceFeatures(builder);
                return this;
            }

            public Builder setGetDeviceInformation(Device.GetDeviceInformation.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetDeviceInformation(builder);
                return this;
            }

            public Builder setGetDiagnostics(DiagnosticsOuterClass.GetDiagnostics.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetDiagnostics(builder);
                return this;
            }

            public Builder setGetFirmwareInformation(Firmware.GetFirmwareInformation.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetFirmwareInformation(builder);
                return this;
            }

            public Builder setGetFirmwareUpdatePreferences(Firmware.GetFirmwareUpdatePreferences.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetFirmwareUpdatePreferences(builder);
                return this;
            }

            public Builder setGetFitnessData(Fitness.GetFitnessData.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetFitnessData(builder);
                return this;
            }

            public Builder setGetInputBehavior(Input.GetInputBehavior.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetInputBehavior(builder);
                return this;
            }

            public Builder setGetLocales(System.GetLocales.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetLocales(builder);
                return this;
            }

            public Builder setGetMediaEnhancementCorrectionAmount(Hearing.GetMediaEnhancementCorrectionAmount.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetMediaEnhancementCorrectionAmount(builder);
                return this;
            }

            public Builder setGetPlaybackStatus(Media.GetPlaybackStatus.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetPlaybackStatus(builder);
                return this;
            }

            public Builder setGetSmsMessageList(Mapsms.GetSmsMessageList.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetSmsMessageList(builder);
                return this;
            }

            public Builder setGetSmsMessageListResponse(Mapsms.GetSmsMessageListResponse.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetSmsMessageListResponse(builder);
                return this;
            }

            public Builder setGetState(StateOuterClass.GetState.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetState(builder);
                return this;
            }

            public Builder setGetUsers(System.GetUsers.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetUsers(builder);
                return this;
            }

            public Builder setGetWakewords(System.GetWakewords.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setGetWakewords(builder);
                return this;
            }

            public Builder setIncomingCall(Calling.IncomingCall.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setIncomingCall(builder);
                return this;
            }

            public Builder setInitiateFirmwareUpdate(Firmware.InitiateFirmwareUpdate.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setInitiateFirmwareUpdate(builder);
                return this;
            }

            public Builder setInitiateHandshake(Keyexchange.InitiateHandshake.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setInitiateHandshake(builder);
                return this;
            }

            public Builder setInitiateMapConnection(Mapsms.InitiateMapConnection.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setInitiateMapConnection(builder);
                return this;
            }

            public Builder setIssueInputEvent(Input.IssueInputEvent.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setIssueInputEvent(builder);
                return this;
            }

            public Builder setIssueMediaControl(Media.IssueMediaControl.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setIssueMediaControl(builder);
                return this;
            }

            public Builder setIssueRemoteClearPairing(Instrumentation.IssueRemoteClearPairing.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setIssueRemoteClearPairing(builder);
                return this;
            }

            public Builder setIssueRemoteCommand(Instrumentation.IssueRemoteCommand.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setIssueRemoteCommand(builder);
                return this;
            }

            public Builder setIssueRemoteReset(Instrumentation.IssueRemoteReset.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setIssueRemoteReset(builder);
                return this;
            }

            public Builder setIssueRemoteRestart(Instrumentation.IssueRemoteRestart.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setIssueRemoteRestart(builder);
                return this;
            }

            public Builder setKeepAlive(System.KeepAlive.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setKeepAlive(builder);
                return this;
            }

            public Builder setLaunchApp(System.LaunchApp.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setLaunchApp(builder);
                return this;
            }

            public Builder setLiveFitnessData(Fitness.LiveFitnessData.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setLiveFitnessData(builder);
                return this;
            }

            public Builder setMediaEventOccurred(Media.MediaEventOccurred.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setMediaEventOccurred(builder);
                return this;
            }

            public Builder setNotifyBulkDataAvailable(Bulkdata.NotifyBulkDataAvailable.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifyBulkDataAvailable(builder);
                return this;
            }

            public Builder setNotifyCblLoginState(Cbl.NotifyCblLoginState.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifyCblLoginState(builder);
                return this;
            }

            public Builder setNotifyDeviceConfiguration(Device.NotifyDeviceConfiguration.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifyDeviceConfiguration(builder);
                return this;
            }

            public Builder setNotifyDeviceInformation(Device.NotifyDeviceInformation.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifyDeviceInformation(builder);
                return this;
            }

            public Builder setNotifyDiagnosticsAvailable(DiagnosticsOuterClass.NotifyDiagnosticsAvailable.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifyDiagnosticsAvailable(builder);
                return this;
            }

            public Builder setNotifyFirmwareInformation(Firmware.NotifyFirmwareInformation.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifyFirmwareInformation(builder);
                return this;
            }

            public Builder setNotifyFitnessDataAvailable(Fitness.NotifyFitnessDataAvailable.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifyFitnessDataAvailable(builder);
                return this;
            }

            public Builder setNotifySmsMessageList(Mapsms.NotifySmsMessageList.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifySmsMessageList(builder);
                return this;
            }

            public Builder setNotifySpeechState(Speech.NotifySpeechState.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setNotifySpeechState(builder);
                return this;
            }

            public Builder setOverrideAssistant(Device.OverrideAssistant.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setOverrideAssistant(builder);
                return this;
            }

            public Builder setPerformCentralNotificationAction(Ancs.PerformCentralNotificationAction.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setPerformCentralNotificationAction(builder);
                return this;
            }

            public Builder setPrintDebug(Instrumentation.PrintDebug.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setPrintDebug(builder);
                return this;
            }

            public Builder setProvideSpeech(Speech.ProvideSpeech.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setProvideSpeech(builder);
                return this;
            }

            public Builder setProvideTranslation(Translation.ProvideTranslation.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setProvideTranslation(builder);
                return this;
            }

            public Builder setPublishCentralNotification(Ancs.PublishCentralNotification.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setPublishCentralNotification(builder);
                return this;
            }

            public Builder setPushMetrics(Metrics.PushMetrics.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setPushMetrics(builder);
                return this;
            }

            public Builder setRegisterForMediaEvents(Media.RegisterForMediaEvents.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setRegisterForMediaEvents(builder);
                return this;
            }

            public Builder setRejectCall(Nonhfpcalling.RejectCall.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setRejectCall(builder);
                return this;
            }

            public Builder setRemoveCloudPairingKeys(Cloudpairing.RemoveCloudPairingKeys.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setRemoveCloudPairingKeys(builder);
                return this;
            }

            public Builder setRemoveDevice(System.RemoveDevice.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setRemoveDevice(builder);
                return this;
            }

            public Builder setRemoveNotification(Notification.RemoveNotification.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setRemoveNotification(builder);
                return this;
            }

            public Builder setReplaceCloudPairingKeys(Cloudpairing.ReplaceCloudPairingKeys.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setReplaceCloudPairingKeys(builder);
                return this;
            }

            public Builder setRequestBulkDataTransfer(Bulkdata.RequestBulkDataTransfer.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setRequestBulkDataTransfer(builder);
                return this;
            }

            public Builder setResetCachedComponent(Firmware.ResetCachedComponent.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setResetCachedComponent(builder);
                return this;
            }

            public Builder setResetConnection(System.ResetConnection.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setResetConnection(builder);
                return this;
            }

            public Builder setResetInputBehavior(Input.ResetInputBehavior.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setResetInputBehavior(builder);
                return this;
            }

            public Builder setResetKey(Keyexchange.ResetKey.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setResetKey(builder);
                return this;
            }

            public Builder setResetRootKeys(Keyexchange.ResetRootKeys.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setResetRootKeys(builder);
                return this;
            }

            public Builder setResponse(Response.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setResponse(builder);
                return this;
            }

            public Builder setSendSms(Mapsms.SendSms.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSendSms(builder);
                return this;
            }

            public Builder setSetAudiogram(Hearing.SetAudiogram.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetAudiogram(builder);
                return this;
            }

            public Builder setSetCloudPairingKeys(Cloudpairing.SetCloudPairingKeys.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetCloudPairingKeys(builder);
                return this;
            }

            public Builder setSetInputBehavior(Input.SetInputBehavior.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetInputBehavior(builder);
                return this;
            }

            public Builder setSetLocale(System.SetLocale.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetLocale(builder);
                return this;
            }

            public Builder setSetMediaEnhancementCorrectionAmount(Hearing.SetMediaEnhancementCorrectionAmount.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetMediaEnhancementCorrectionAmount(builder);
                return this;
            }

            public Builder setSetSmsReadStatus(Mapsms.SetSmsReadStatus.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetSmsReadStatus(builder);
                return this;
            }

            public Builder setSetState(StateOuterClass.SetState.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetState(builder);
                return this;
            }

            public Builder setSetWakewords(System.SetWakewords.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSetWakewords(builder);
                return this;
            }

            public Builder setStartFirmwareUpdate(Firmware.StartFirmwareUpdate.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStartFirmwareUpdate(builder);
                return this;
            }

            public Builder setStartLiveFitnessData(Fitness.StartLiveFitnessData.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStartLiveFitnessData(builder);
                return this;
            }

            public Builder setStartSetup(Device.StartSetup.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStartSetup(builder);
                return this;
            }

            public Builder setStartSpeech(Speech.StartSpeech.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStartSpeech(builder);
                return this;
            }

            public Builder setStartTranslation(Translation.StartTranslation.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStartTranslation(builder);
                return this;
            }

            public Builder setStartVoiceStream(Voicestream.StartVoiceStream.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStartVoiceStream(builder);
                return this;
            }

            public Builder setStopBulkDataTransfer(Bulkdata.StopBulkDataTransfer.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStopBulkDataTransfer(builder);
                return this;
            }

            public Builder setStopDiagnostics(DiagnosticsOuterClass.StopDiagnostics.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStopDiagnostics(builder);
                return this;
            }

            public Builder setStopFitnessData(Fitness.StopFitnessData.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStopFitnessData(builder);
                return this;
            }

            public Builder setStopLiveFitnessData(Fitness.StopLiveFitnessData.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStopLiveFitnessData(builder);
                return this;
            }

            public Builder setStopSpeech(Speech.StopSpeech.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStopSpeech(builder);
                return this;
            }

            public Builder setStopTranslation(Translation.StopTranslation.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStopTranslation(builder);
                return this;
            }

            public Builder setStopVoiceStream(Voicestream.StopVoiceStream.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setStopVoiceStream(builder);
                return this;
            }

            public Builder setSubscribeNotificationCenter(Ancs.SubscribeNotificationCenter.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSubscribeNotificationCenter(builder);
                return this;
            }

            public Builder setSwitchCurrentUser(System.SwitchCurrentUser.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSwitchCurrentUser(builder);
                return this;
            }

            public Builder setSwitchTransport(Transport.SwitchTransport.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSwitchTransport(builder);
                return this;
            }

            public Builder setSyncFitnessSession(Fitness.SyncFitnessSession.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSyncFitnessSession(builder);
                return this;
            }

            public Builder setSynchronizeSettings(System.SynchronizeSettings.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSynchronizeSettings(builder);
                return this;
            }

            public Builder setSynchronizeState(StateOuterClass.SynchronizeState.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setSynchronizeState(builder);
                return this;
            }

            public Builder setUnpairUser(System.UnpairUser.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUnpairUser(builder);
                return this;
            }

            public Builder setUnsubscribeNotificationCenter(Ancs.UnsubscribeNotificationCenter.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUnsubscribeNotificationCenter(builder);
                return this;
            }

            public Builder setUpdateCallState(Nonhfpcalling.UpdateCallState.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpdateCallState(builder);
                return this;
            }

            public Builder setUpdateCentralNotificationAttributes(Ancs.UpdateCentralNotificationAttributes.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpdateCentralNotificationAttributes(builder);
                return this;
            }

            public Builder setUpdateComponentSegment(Firmware.UpdateComponentSegment.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpdateComponentSegment(builder);
                return this;
            }

            public Builder setUpdateDeviceInformation(Device.UpdateDeviceInformation.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpdateDeviceInformation(builder);
                return this;
            }

            public Builder setUpdateMetricsMap(Metrics.UpdateMetricsMap.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpdateMetricsMap(builder);
                return this;
            }

            public Builder setUpdateNotification(Notification.UpdateNotification.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpdateNotification(builder);
                return this;
            }

            public Builder setUpdateUsers(System.UpdateUsers.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpdateUsers(builder);
                return this;
            }

            public Builder setUpgradeTransport(Transport.UpgradeTransport.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUpgradeTransport(builder);
                return this;
            }

            public Builder setUserConfirmed(Keyexchange.UserConfirmed.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setUserConfirmed(builder);
                return this;
            }

            public Builder setVerifyArtifactSignature(Firmware.VerifyArtifactSignature.Builder builder) {
                copyOnWrite();
                ((ControlEnvelope) this.instance).setVerifyArtifactSignature(builder);
                return this;
            }
        }

        /* loaded from: classes6.dex */
        public enum PayloadCase implements Internal.EnumLite {
            RESPONSE(9),
            RESET_CONNECTION(51),
            SYNCHRONIZE_SETTINGS(50),
            KEEP_ALIVE(55),
            REMOVE_DEVICE(56),
            GET_USERS(52),
            UPDATE_USERS(61),
            CONNECT_USER(62),
            DISCONNECT_USER(63),
            UNPAIR_USER(64),
            GET_CURRENT_USER(54),
            SWITCH_CURRENT_USER(53),
            GET_LOCALES(57),
            SET_LOCALE(58),
            LAUNCH_APP(59),
            SET_WAKEWORDS(70),
            GET_WAKEWORDS(71),
            UPGRADE_TRANSPORT(30),
            SWITCH_TRANSPORT(31),
            START_SPEECH(11),
            PROVIDE_SPEECH(10),
            STOP_SPEECH(12),
            ENDPOINT_SPEECH(13),
            NOTIFY_SPEECH_STATE(14),
            FORWARD_AT_COMMAND(40),
            INCOMING_CALL(41),
            GET_CENTRAL_INFORMATION(103),
            GET_DEVICE_INFORMATION(20),
            GET_DEVICE_CONFIGURATION(21),
            OVERRIDE_ASSISTANT(22),
            START_SETUP(23),
            COMPLETE_SETUP(24),
            NOTIFY_DEVICE_CONFIGURATION(25),
            UPDATE_DEVICE_INFORMATION(26),
            NOTIFY_DEVICE_INFORMATION(27),
            GET_DEVICE_FEATURES(28),
            GET_DIAGNOSTICS(110),
            STOP_DIAGNOSTICS(111),
            NOTIFY_DIAGNOSTICS_AVAILABLE(112),
            GET_FIRMWARE_INFORMATION(90),
            GET_CACHED_COMPONENT(92),
            RESET_CACHED_COMPONENT(93),
            UPDATE_COMPONENT_SEGMENT(94),
            APPLY_FIRMWARE(95),
            GET_FIRMWARE_UPDATE_PREFERENCES(91),
            GET_DEVICE_ARTIFACTS(96),
            GET_ARTIFACT_FILTER(97),
            GET_ARTIFACT_UPDATE_PREFERENCE(98),
            START_FIRMWARE_UPDATE(99),
            VERIFY_ARTIFACT_SIGNATURE(89),
            INITIATE_FIRMWARE_UPDATE(88),
            NOTIFY_FIRMWARE_INFORMATION(87),
            FIRMWARE_UPDATE_UNAVAILABLE(86),
            ISSUE_MEDIA_CONTROL(60),
            GET_PLAYBACK_STATUS(65),
            REGISTER_FOR_MEDIA_EVENTS(66),
            MEDIA_EVENT_OCCURRED(67),
            PUSH_METRICS(120),
            UPDATE_METRICS_MAP(121),
            GET_STATE(100),
            SET_STATE(101),
            SYNCHRONIZE_STATE(102),
            GET_FITNESS_DATA(130),
            STOP_FITNESS_DATA(131),
            NOTIFY_FITNESS_DATA_AVAILABLE(132),
            SYNC_FITNESS_SESSION(133),
            START_LIVE_FITNESS_DATA(134),
            STOP_LIVE_FITNESS_DATA(135),
            LIVE_FITNESS_DATA(136),
            SUBSCRIBE_NOTIFICATION_CENTER(150),
            UNSUBSCRIBE_NOTIFICATION_CENTER(151),
            PUBLISH_CENTRAL_NOTIFICATION(152),
            GET_CENTRAL_NOTIFICATION_ATTRIBUTES(153),
            GET_CENTRAL_NOTIFICATION_APP_ATTRIBUTES(154),
            PERFORM_CENTRAL_NOTIFICATION_ACTION(155),
            UPDATE_CENTRAL_NOTIFICATION_ATTRIBUTES(156),
            ISSUE_INPUT_EVENT(160),
            SET_INPUT_BEHAVIOR(161),
            GET_INPUT_BEHAVIOR(162),
            RESET_INPUT_BEHAVIOR(168),
            PRINT_DEBUG(163),
            ISSUE_REMOTE_COMMAND(164),
            ISSUE_REMOTE_RESTART(165),
            ISSUE_REMOTE_RESET(166),
            ISSUE_REMOTE_CLEAR_PAIRING(167),
            START_TRANSLATION(170),
            PROVIDE_TRANSLATION(171),
            STOP_TRANSLATION(172),
            GET_BULK_DATA_MANIFEST(180),
            BULK_DATA_MANIFEST_ENTRY(181),
            REQUEST_BULK_DATA_TRANSFER(182),
            BULK_DATA_TRANSFER_START(183),
            BULK_DATA_TRANSFER_COMPLETE(184),
            STOP_BULK_DATA_TRANSFER(185),
            NOTIFY_BULK_DATA_AVAILABLE(186),
            INITIATE_HANDSHAKE(200),
            COMPLETE_HANDSHAKE(201),
            USER_CONFIRMED(202),
            RESET_KEY(203),
            CONFIRM_RESET_KEY(204),
            RESET_ROOT_KEYS(205),
            GET_AUDIOGRAM(300),
            SET_AUDIOGRAM(301),
            GET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT(302),
            SET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT(303),
            GET_CBL_LOGIN_STATE(230),
            GET_CBL_INFORMATION(231),
            NOTIFY_CBL_LOGIN_STATE(232),
            GET_SMS_MESSAGE_LIST_RESPONSE(350),
            NOTIFY_SMS_MESSAGE_LIST(351),
            GET_SMS_MESSAGE_LIST(352),
            SEND_SMS(353),
            SET_SMS_READ_STATUS(354),
            END_OF_SMS_MESSAGE_LIST_RESPONSE(355),
            INITIATE_MAP_CONNECTION(356),
            START_VOICE_STREAM(360),
            STOP_VOICE_STREAM(361),
            DISPLAY_CONTENT(80),
            UPDATE_CALL_STATE(140),
            ACCEPT_CALL(141),
            REJECT_CALL(142),
            END_CALL(143),
            ADD_NOTIFICATION(190),
            UPDATE_NOTIFICATION(191),
            REMOVE_NOTIFICATION(192),
            EXECUTE_NOTIFICATION_ACTION(193),
            GET_CLOUD_PAIRING_ATTRIBUTES(370),
            GET_CLOUD_PAIRING_STATUS(371),
            SET_CLOUD_PAIRING_KEYS(372),
            REPLACE_CLOUD_PAIRING_KEYS(373),
            REMOVE_CLOUD_PAIRING_KEYS(374),
            PAYLOAD_NOT_SET(0);
            
            private final int value;

            PayloadCase(int i) {
                this.value = i;
            }

            public static PayloadCase forNumber(int i) {
                if (i != 0) {
                    if (i == 30) {
                        return UPGRADE_TRANSPORT;
                    }
                    if (i == 31) {
                        return SWITCH_TRANSPORT;
                    }
                    if (i == 40) {
                        return FORWARD_AT_COMMAND;
                    }
                    if (i == 41) {
                        return INCOMING_CALL;
                    }
                    if (i == 70) {
                        return SET_WAKEWORDS;
                    }
                    if (i == 71) {
                        return GET_WAKEWORDS;
                    }
                    if (i == 120) {
                        return PUSH_METRICS;
                    }
                    if (i != 121) {
                        switch (i) {
                            case 0:
                                break;
                            case 50:
                                return SYNCHRONIZE_SETTINGS;
                            case 51:
                                return RESET_CONNECTION;
                            case 52:
                                return GET_USERS;
                            case 53:
                                return SWITCH_CURRENT_USER;
                            case 54:
                                return GET_CURRENT_USER;
                            case 55:
                                return KEEP_ALIVE;
                            case 56:
                                return REMOVE_DEVICE;
                            case 57:
                                return GET_LOCALES;
                            case 58:
                                return SET_LOCALE;
                            case 59:
                                return LAUNCH_APP;
                            case 60:
                                return ISSUE_MEDIA_CONTROL;
                            case 61:
                                return UPDATE_USERS;
                            case 62:
                                return CONNECT_USER;
                            case 63:
                                return DISCONNECT_USER;
                            case 64:
                                return UNPAIR_USER;
                            case 65:
                                return GET_PLAYBACK_STATUS;
                            case 66:
                                return REGISTER_FOR_MEDIA_EVENTS;
                            case 67:
                                return MEDIA_EVENT_OCCURRED;
                            case 80:
                                return DISPLAY_CONTENT;
                            case 140:
                                return UPDATE_CALL_STATE;
                            case 141:
                                return ACCEPT_CALL;
                            case 142:
                                return REJECT_CALL;
                            case 143:
                                return END_CALL;
                            case 150:
                                return SUBSCRIBE_NOTIFICATION_CENTER;
                            case 151:
                                return UNSUBSCRIBE_NOTIFICATION_CENTER;
                            case 152:
                                return PUBLISH_CENTRAL_NOTIFICATION;
                            case 153:
                                return GET_CENTRAL_NOTIFICATION_ATTRIBUTES;
                            case 154:
                                return GET_CENTRAL_NOTIFICATION_APP_ATTRIBUTES;
                            case 155:
                                return PERFORM_CENTRAL_NOTIFICATION_ACTION;
                            case 156:
                                return UPDATE_CENTRAL_NOTIFICATION_ATTRIBUTES;
                            case 160:
                                return ISSUE_INPUT_EVENT;
                            case 161:
                                return SET_INPUT_BEHAVIOR;
                            case 162:
                                return GET_INPUT_BEHAVIOR;
                            case 163:
                                return PRINT_DEBUG;
                            case 164:
                                return ISSUE_REMOTE_COMMAND;
                            case 165:
                                return ISSUE_REMOTE_RESTART;
                            case 166:
                                return ISSUE_REMOTE_RESET;
                            case 167:
                                return ISSUE_REMOTE_CLEAR_PAIRING;
                            case 168:
                                return RESET_INPUT_BEHAVIOR;
                            case 170:
                                return START_TRANSLATION;
                            case 171:
                                return PROVIDE_TRANSLATION;
                            case 172:
                                return STOP_TRANSLATION;
                            case 180:
                                return GET_BULK_DATA_MANIFEST;
                            case 181:
                                return BULK_DATA_MANIFEST_ENTRY;
                            case 182:
                                return REQUEST_BULK_DATA_TRANSFER;
                            case 183:
                                return BULK_DATA_TRANSFER_START;
                            case 184:
                                return BULK_DATA_TRANSFER_COMPLETE;
                            case 185:
                                return STOP_BULK_DATA_TRANSFER;
                            case 186:
                                return NOTIFY_BULK_DATA_AVAILABLE;
                            case 190:
                                return ADD_NOTIFICATION;
                            case 191:
                                return UPDATE_NOTIFICATION;
                            case 192:
                                return REMOVE_NOTIFICATION;
                            case 193:
                                return EXECUTE_NOTIFICATION_ACTION;
                            case 200:
                                return INITIATE_HANDSHAKE;
                            case 201:
                                return COMPLETE_HANDSHAKE;
                            case 202:
                                return USER_CONFIRMED;
                            case 203:
                                return RESET_KEY;
                            case 204:
                                return CONFIRM_RESET_KEY;
                            case 205:
                                return RESET_ROOT_KEYS;
                            case 230:
                                return GET_CBL_LOGIN_STATE;
                            case 231:
                                return GET_CBL_INFORMATION;
                            case 232:
                                return NOTIFY_CBL_LOGIN_STATE;
                            case 300:
                                return GET_AUDIOGRAM;
                            case 301:
                                return SET_AUDIOGRAM;
                            case 302:
                                return GET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT;
                            case 303:
                                return SET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT;
                            case 350:
                                return GET_SMS_MESSAGE_LIST_RESPONSE;
                            case 351:
                                return NOTIFY_SMS_MESSAGE_LIST;
                            case 352:
                                return GET_SMS_MESSAGE_LIST;
                            case 353:
                                return SEND_SMS;
                            case 354:
                                return SET_SMS_READ_STATUS;
                            case 355:
                                return END_OF_SMS_MESSAGE_LIST_RESPONSE;
                            case 356:
                                return INITIATE_MAP_CONNECTION;
                            case 360:
                                return START_VOICE_STREAM;
                            case 361:
                                return STOP_VOICE_STREAM;
                            case 370:
                                return GET_CLOUD_PAIRING_ATTRIBUTES;
                            case 371:
                                return GET_CLOUD_PAIRING_STATUS;
                            case 372:
                                return SET_CLOUD_PAIRING_KEYS;
                            case 373:
                                return REPLACE_CLOUD_PAIRING_KEYS;
                            case 374:
                                return REMOVE_CLOUD_PAIRING_KEYS;
                            default:
                                switch (i) {
                                    case 9:
                                        return RESPONSE;
                                    case 10:
                                        return PROVIDE_SPEECH;
                                    case 11:
                                        return START_SPEECH;
                                    case 12:
                                        return STOP_SPEECH;
                                    case 13:
                                        return ENDPOINT_SPEECH;
                                    case 14:
                                        return NOTIFY_SPEECH_STATE;
                                    default:
                                        switch (i) {
                                            case 20:
                                                return GET_DEVICE_INFORMATION;
                                            case 21:
                                                return GET_DEVICE_CONFIGURATION;
                                            case 22:
                                                return OVERRIDE_ASSISTANT;
                                            case 23:
                                                return START_SETUP;
                                            case 24:
                                                return COMPLETE_SETUP;
                                            case 25:
                                                return NOTIFY_DEVICE_CONFIGURATION;
                                            case 26:
                                                return UPDATE_DEVICE_INFORMATION;
                                            case 27:
                                                return NOTIFY_DEVICE_INFORMATION;
                                            case 28:
                                                return GET_DEVICE_FEATURES;
                                            default:
                                                switch (i) {
                                                    case 86:
                                                        return FIRMWARE_UPDATE_UNAVAILABLE;
                                                    case 87:
                                                        return NOTIFY_FIRMWARE_INFORMATION;
                                                    case 88:
                                                        return INITIATE_FIRMWARE_UPDATE;
                                                    case 89:
                                                        return VERIFY_ARTIFACT_SIGNATURE;
                                                    case 90:
                                                        return GET_FIRMWARE_INFORMATION;
                                                    case 91:
                                                        return GET_FIRMWARE_UPDATE_PREFERENCES;
                                                    case 92:
                                                        return GET_CACHED_COMPONENT;
                                                    case 93:
                                                        return RESET_CACHED_COMPONENT;
                                                    case 94:
                                                        return UPDATE_COMPONENT_SEGMENT;
                                                    case 95:
                                                        return APPLY_FIRMWARE;
                                                    case 96:
                                                        return GET_DEVICE_ARTIFACTS;
                                                    case 97:
                                                        return GET_ARTIFACT_FILTER;
                                                    case 98:
                                                        return GET_ARTIFACT_UPDATE_PREFERENCE;
                                                    case 99:
                                                        return START_FIRMWARE_UPDATE;
                                                    case 100:
                                                        return GET_STATE;
                                                    case 101:
                                                        return SET_STATE;
                                                    case 102:
                                                        return SYNCHRONIZE_STATE;
                                                    case 103:
                                                        return GET_CENTRAL_INFORMATION;
                                                    default:
                                                        switch (i) {
                                                            case 110:
                                                                return GET_DIAGNOSTICS;
                                                            case 111:
                                                                return STOP_DIAGNOSTICS;
                                                            case 112:
                                                                return NOTIFY_DIAGNOSTICS_AVAILABLE;
                                                            default:
                                                                switch (i) {
                                                                    case 130:
                                                                        return GET_FITNESS_DATA;
                                                                    case 131:
                                                                        return STOP_FITNESS_DATA;
                                                                    case 132:
                                                                        return NOTIFY_FITNESS_DATA_AVAILABLE;
                                                                    case 133:
                                                                        return SYNC_FITNESS_SESSION;
                                                                    case 134:
                                                                        return START_LIVE_FITNESS_DATA;
                                                                    case 135:
                                                                        return STOP_LIVE_FITNESS_DATA;
                                                                    case 136:
                                                                        return LIVE_FITNESS_DATA;
                                                                    default:
                                                                        return null;
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                    } else {
                        return UPDATE_METRICS_MAP;
                    }
                }
                return PAYLOAD_NOT_SET;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static PayloadCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private ControlEnvelope() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAcceptCall() {
            if (this.payloadCase_ == 141) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAddNotification() {
            if (this.payloadCase_ == 190) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearApplyFirmware() {
            if (this.payloadCase_ == 95) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBulkDataManifestEntry() {
            if (this.payloadCase_ == 181) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBulkDataTransferComplete() {
            if (this.payloadCase_ == 184) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBulkDataTransferStart() {
            if (this.payloadCase_ == 183) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCommand() {
            this.command_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCompleteHandshake() {
            if (this.payloadCase_ == 201) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCompleteSetup() {
            if (this.payloadCase_ == 24) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConfirmResetKey() {
            if (this.payloadCase_ == 204) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConnectUser() {
            if (this.payloadCase_ == 62) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDisconnectUser() {
            if (this.payloadCase_ == 63) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDisplayContent() {
            if (this.payloadCase_ == 80) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEndCall() {
            if (this.payloadCase_ == 143) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEndOfSmsMessageListResponse() {
            if (this.payloadCase_ == 355) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEndpointSpeech() {
            if (this.payloadCase_ == 13) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearExecuteNotificationAction() {
            if (this.payloadCase_ == 193) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFirmwareUpdateUnavailable() {
            if (this.payloadCase_ == 86) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearForwardAtCommand() {
            if (this.payloadCase_ == 40) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetArtifactFilter() {
            if (this.payloadCase_ == 97) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetArtifactUpdatePreference() {
            if (this.payloadCase_ == 98) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetAudiogram() {
            if (this.payloadCase_ == 300) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetBulkDataManifest() {
            if (this.payloadCase_ == 180) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetCachedComponent() {
            if (this.payloadCase_ == 92) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetCblInformation() {
            if (this.payloadCase_ == 231) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetCblLoginState() {
            if (this.payloadCase_ == 230) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetCentralInformation() {
            if (this.payloadCase_ == 103) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetCentralNotificationAppAttributes() {
            if (this.payloadCase_ == 154) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetCentralNotificationAttributes() {
            if (this.payloadCase_ == 153) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetCloudPairingAttributes() {
            if (this.payloadCase_ == 370) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetCloudPairingStatus() {
            if (this.payloadCase_ == 371) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetCurrentUser() {
            if (this.payloadCase_ == 54) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetDeviceArtifacts() {
            if (this.payloadCase_ == 96) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetDeviceConfiguration() {
            if (this.payloadCase_ == 21) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetDeviceFeatures() {
            if (this.payloadCase_ == 28) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetDeviceInformation() {
            if (this.payloadCase_ == 20) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetDiagnostics() {
            if (this.payloadCase_ == 110) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetFirmwareInformation() {
            if (this.payloadCase_ == 90) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetFirmwareUpdatePreferences() {
            if (this.payloadCase_ == 91) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetFitnessData() {
            if (this.payloadCase_ == 130) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetInputBehavior() {
            if (this.payloadCase_ == 162) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetLocales() {
            if (this.payloadCase_ == 57) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetMediaEnhancementCorrectionAmount() {
            if (this.payloadCase_ == 302) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetPlaybackStatus() {
            if (this.payloadCase_ == 65) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetSmsMessageList() {
            if (this.payloadCase_ == 352) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetSmsMessageListResponse() {
            if (this.payloadCase_ == 350) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetState() {
            if (this.payloadCase_ == 100) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetUsers() {
            if (this.payloadCase_ == 52) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGetWakewords() {
            if (this.payloadCase_ == 71) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIncomingCall() {
            if (this.payloadCase_ == 41) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInitiateFirmwareUpdate() {
            if (this.payloadCase_ == 88) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInitiateHandshake() {
            if (this.payloadCase_ == 200) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInitiateMapConnection() {
            if (this.payloadCase_ == 356) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIssueInputEvent() {
            if (this.payloadCase_ == 160) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIssueMediaControl() {
            if (this.payloadCase_ == 60) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIssueRemoteClearPairing() {
            if (this.payloadCase_ == 167) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIssueRemoteCommand() {
            if (this.payloadCase_ == 164) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIssueRemoteReset() {
            if (this.payloadCase_ == 166) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIssueRemoteRestart() {
            if (this.payloadCase_ == 165) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearKeepAlive() {
            if (this.payloadCase_ == 55) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLaunchApp() {
            if (this.payloadCase_ == 59) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLiveFitnessData() {
            if (this.payloadCase_ == 136) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMediaEventOccurred() {
            if (this.payloadCase_ == 67) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotifyBulkDataAvailable() {
            if (this.payloadCase_ == 186) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotifyCblLoginState() {
            if (this.payloadCase_ == 232) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotifyDeviceConfiguration() {
            if (this.payloadCase_ == 25) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotifyDeviceInformation() {
            if (this.payloadCase_ == 27) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotifyDiagnosticsAvailable() {
            if (this.payloadCase_ == 112) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotifyFirmwareInformation() {
            if (this.payloadCase_ == 87) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotifyFitnessDataAvailable() {
            if (this.payloadCase_ == 132) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotifySmsMessageList() {
            if (this.payloadCase_ == 351) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotifySpeechState() {
            if (this.payloadCase_ == 14) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOverrideAssistant() {
            if (this.payloadCase_ == 22) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPayload() {
            this.payloadCase_ = 0;
            this.payload_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPerformCentralNotificationAction() {
            if (this.payloadCase_ == 155) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPrintDebug() {
            if (this.payloadCase_ == 163) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearProvideSpeech() {
            if (this.payloadCase_ == 10) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearProvideTranslation() {
            if (this.payloadCase_ == 171) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPublishCentralNotification() {
            if (this.payloadCase_ == 152) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPushMetrics() {
            if (this.payloadCase_ == 120) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRegisterForMediaEvents() {
            if (this.payloadCase_ == 66) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRejectCall() {
            if (this.payloadCase_ == 142) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRemoveCloudPairingKeys() {
            if (this.payloadCase_ == 374) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRemoveDevice() {
            if (this.payloadCase_ == 56) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRemoveNotification() {
            if (this.payloadCase_ == 192) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearReplaceCloudPairingKeys() {
            if (this.payloadCase_ == 373) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRequestBulkDataTransfer() {
            if (this.payloadCase_ == 182) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearResetCachedComponent() {
            if (this.payloadCase_ == 93) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearResetConnection() {
            if (this.payloadCase_ == 51) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearResetInputBehavior() {
            if (this.payloadCase_ == 168) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearResetKey() {
            if (this.payloadCase_ == 203) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearResetRootKeys() {
            if (this.payloadCase_ == 205) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearResponse() {
            if (this.payloadCase_ == 9) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSendSms() {
            if (this.payloadCase_ == 353) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSetAudiogram() {
            if (this.payloadCase_ == 301) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSetCloudPairingKeys() {
            if (this.payloadCase_ == 372) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSetInputBehavior() {
            if (this.payloadCase_ == 161) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSetLocale() {
            if (this.payloadCase_ == 58) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSetMediaEnhancementCorrectionAmount() {
            if (this.payloadCase_ == 303) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSetSmsReadStatus() {
            if (this.payloadCase_ == 354) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSetState() {
            if (this.payloadCase_ == 101) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSetWakewords() {
            if (this.payloadCase_ == 70) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStartFirmwareUpdate() {
            if (this.payloadCase_ == 99) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStartLiveFitnessData() {
            if (this.payloadCase_ == 134) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStartSetup() {
            if (this.payloadCase_ == 23) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStartSpeech() {
            if (this.payloadCase_ == 11) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStartTranslation() {
            if (this.payloadCase_ == 170) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStartVoiceStream() {
            if (this.payloadCase_ == 360) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStopBulkDataTransfer() {
            if (this.payloadCase_ == 185) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStopDiagnostics() {
            if (this.payloadCase_ == 111) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStopFitnessData() {
            if (this.payloadCase_ == 131) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStopLiveFitnessData() {
            if (this.payloadCase_ == 135) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStopSpeech() {
            if (this.payloadCase_ == 12) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStopTranslation() {
            if (this.payloadCase_ == 172) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStopVoiceStream() {
            if (this.payloadCase_ == 361) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSubscribeNotificationCenter() {
            if (this.payloadCase_ == 150) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSwitchCurrentUser() {
            if (this.payloadCase_ == 53) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSwitchTransport() {
            if (this.payloadCase_ == 31) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSyncFitnessSession() {
            if (this.payloadCase_ == 133) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSynchronizeSettings() {
            if (this.payloadCase_ == 50) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSynchronizeState() {
            if (this.payloadCase_ == 102) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUnpairUser() {
            if (this.payloadCase_ == 64) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUnsubscribeNotificationCenter() {
            if (this.payloadCase_ == 151) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUpdateCallState() {
            if (this.payloadCase_ == 140) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUpdateCentralNotificationAttributes() {
            if (this.payloadCase_ == 156) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUpdateComponentSegment() {
            if (this.payloadCase_ == 94) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUpdateDeviceInformation() {
            if (this.payloadCase_ == 26) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUpdateMetricsMap() {
            if (this.payloadCase_ == 121) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUpdateNotification() {
            if (this.payloadCase_ == 191) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUpdateUsers() {
            if (this.payloadCase_ == 61) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUpgradeTransport() {
            if (this.payloadCase_ == 30) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUserConfirmed() {
            if (this.payloadCase_ == 202) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVerifyArtifactSignature() {
            if (this.payloadCase_ == 89) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        public static ControlEnvelope getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAcceptCall(Nonhfpcalling.AcceptCall acceptCall) {
            if (this.payloadCase_ == 141 && this.payload_ != Nonhfpcalling.AcceptCall.getDefaultInstance()) {
                this.payload_ = Nonhfpcalling.AcceptCall.newBuilder((Nonhfpcalling.AcceptCall) this.payload_).mergeFrom((Nonhfpcalling.AcceptCall.Builder) acceptCall).mo10085buildPartial();
            } else {
                this.payload_ = acceptCall;
            }
            this.payloadCase_ = 141;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAddNotification(Notification.AddNotification addNotification) {
            if (this.payloadCase_ == 190 && this.payload_ != Notification.AddNotification.getDefaultInstance()) {
                this.payload_ = Notification.AddNotification.newBuilder((Notification.AddNotification) this.payload_).mergeFrom((Notification.AddNotification.Builder) addNotification).mo10085buildPartial();
            } else {
                this.payload_ = addNotification;
            }
            this.payloadCase_ = 190;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeApplyFirmware(Firmware.ApplyFirmware applyFirmware) {
            if (this.payloadCase_ == 95 && this.payload_ != Firmware.ApplyFirmware.getDefaultInstance()) {
                this.payload_ = Firmware.ApplyFirmware.newBuilder((Firmware.ApplyFirmware) this.payload_).mergeFrom((Firmware.ApplyFirmware.Builder) applyFirmware).mo10085buildPartial();
            } else {
                this.payload_ = applyFirmware;
            }
            this.payloadCase_ = 95;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeBulkDataManifestEntry(Bulkdata.BulkDataManifestEntry bulkDataManifestEntry) {
            if (this.payloadCase_ == 181 && this.payload_ != Bulkdata.BulkDataManifestEntry.getDefaultInstance()) {
                this.payload_ = Bulkdata.BulkDataManifestEntry.newBuilder((Bulkdata.BulkDataManifestEntry) this.payload_).mergeFrom((Bulkdata.BulkDataManifestEntry.Builder) bulkDataManifestEntry).mo10085buildPartial();
            } else {
                this.payload_ = bulkDataManifestEntry;
            }
            this.payloadCase_ = 181;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeBulkDataTransferComplete(Bulkdata.BulkDataTransferComplete bulkDataTransferComplete) {
            if (this.payloadCase_ == 184 && this.payload_ != Bulkdata.BulkDataTransferComplete.getDefaultInstance()) {
                this.payload_ = Bulkdata.BulkDataTransferComplete.newBuilder((Bulkdata.BulkDataTransferComplete) this.payload_).mergeFrom((Bulkdata.BulkDataTransferComplete.Builder) bulkDataTransferComplete).mo10085buildPartial();
            } else {
                this.payload_ = bulkDataTransferComplete;
            }
            this.payloadCase_ = 184;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeBulkDataTransferStart(Bulkdata.BulkDataTransferStart bulkDataTransferStart) {
            if (this.payloadCase_ == 183 && this.payload_ != Bulkdata.BulkDataTransferStart.getDefaultInstance()) {
                this.payload_ = Bulkdata.BulkDataTransferStart.newBuilder((Bulkdata.BulkDataTransferStart) this.payload_).mergeFrom((Bulkdata.BulkDataTransferStart.Builder) bulkDataTransferStart).mo10085buildPartial();
            } else {
                this.payload_ = bulkDataTransferStart;
            }
            this.payloadCase_ = 183;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCompleteHandshake(Keyexchange.CompleteHandshake completeHandshake) {
            if (this.payloadCase_ == 201 && this.payload_ != Keyexchange.CompleteHandshake.getDefaultInstance()) {
                this.payload_ = Keyexchange.CompleteHandshake.newBuilder((Keyexchange.CompleteHandshake) this.payload_).mergeFrom((Keyexchange.CompleteHandshake.Builder) completeHandshake).mo10085buildPartial();
            } else {
                this.payload_ = completeHandshake;
            }
            this.payloadCase_ = 201;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCompleteSetup(Device.CompleteSetup completeSetup) {
            if (this.payloadCase_ == 24 && this.payload_ != Device.CompleteSetup.getDefaultInstance()) {
                this.payload_ = Device.CompleteSetup.newBuilder((Device.CompleteSetup) this.payload_).mergeFrom((Device.CompleteSetup.Builder) completeSetup).mo10085buildPartial();
            } else {
                this.payload_ = completeSetup;
            }
            this.payloadCase_ = 24;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeConfirmResetKey(Keyexchange.ConfirmResetKey confirmResetKey) {
            if (this.payloadCase_ == 204 && this.payload_ != Keyexchange.ConfirmResetKey.getDefaultInstance()) {
                this.payload_ = Keyexchange.ConfirmResetKey.newBuilder((Keyexchange.ConfirmResetKey) this.payload_).mergeFrom((Keyexchange.ConfirmResetKey.Builder) confirmResetKey).mo10085buildPartial();
            } else {
                this.payload_ = confirmResetKey;
            }
            this.payloadCase_ = 204;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeConnectUser(System.ConnectUser connectUser) {
            if (this.payloadCase_ == 62 && this.payload_ != System.ConnectUser.getDefaultInstance()) {
                this.payload_ = System.ConnectUser.newBuilder((System.ConnectUser) this.payload_).mergeFrom((System.ConnectUser.Builder) connectUser).mo10085buildPartial();
            } else {
                this.payload_ = connectUser;
            }
            this.payloadCase_ = 62;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDisconnectUser(System.DisconnectUser disconnectUser) {
            if (this.payloadCase_ == 63 && this.payload_ != System.DisconnectUser.getDefaultInstance()) {
                this.payload_ = System.DisconnectUser.newBuilder((System.DisconnectUser) this.payload_).mergeFrom((System.DisconnectUser.Builder) disconnectUser).mo10085buildPartial();
            } else {
                this.payload_ = disconnectUser;
            }
            this.payloadCase_ = 63;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDisplayContent(Cardrendering.DisplayContent displayContent) {
            if (this.payloadCase_ == 80 && this.payload_ != Cardrendering.DisplayContent.getDefaultInstance()) {
                this.payload_ = Cardrendering.DisplayContent.newBuilder((Cardrendering.DisplayContent) this.payload_).mergeFrom((Cardrendering.DisplayContent.Builder) displayContent).mo10085buildPartial();
            } else {
                this.payload_ = displayContent;
            }
            this.payloadCase_ = 80;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeEndCall(Nonhfpcalling.EndCall endCall) {
            if (this.payloadCase_ == 143 && this.payload_ != Nonhfpcalling.EndCall.getDefaultInstance()) {
                this.payload_ = Nonhfpcalling.EndCall.newBuilder((Nonhfpcalling.EndCall) this.payload_).mergeFrom((Nonhfpcalling.EndCall.Builder) endCall).mo10085buildPartial();
            } else {
                this.payload_ = endCall;
            }
            this.payloadCase_ = 143;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeEndOfSmsMessageListResponse(Mapsms.EndOfSmsMessageListResponse endOfSmsMessageListResponse) {
            if (this.payloadCase_ == 355 && this.payload_ != Mapsms.EndOfSmsMessageListResponse.getDefaultInstance()) {
                this.payload_ = Mapsms.EndOfSmsMessageListResponse.newBuilder((Mapsms.EndOfSmsMessageListResponse) this.payload_).mergeFrom((Mapsms.EndOfSmsMessageListResponse.Builder) endOfSmsMessageListResponse).mo10085buildPartial();
            } else {
                this.payload_ = endOfSmsMessageListResponse;
            }
            this.payloadCase_ = 355;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeEndpointSpeech(Speech.EndpointSpeech endpointSpeech) {
            if (this.payloadCase_ == 13 && this.payload_ != Speech.EndpointSpeech.getDefaultInstance()) {
                this.payload_ = Speech.EndpointSpeech.newBuilder((Speech.EndpointSpeech) this.payload_).mergeFrom((Speech.EndpointSpeech.Builder) endpointSpeech).mo10085buildPartial();
            } else {
                this.payload_ = endpointSpeech;
            }
            this.payloadCase_ = 13;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeExecuteNotificationAction(Notification.ExecuteNotificationAction executeNotificationAction) {
            if (this.payloadCase_ == 193 && this.payload_ != Notification.ExecuteNotificationAction.getDefaultInstance()) {
                this.payload_ = Notification.ExecuteNotificationAction.newBuilder((Notification.ExecuteNotificationAction) this.payload_).mergeFrom((Notification.ExecuteNotificationAction.Builder) executeNotificationAction).mo10085buildPartial();
            } else {
                this.payload_ = executeNotificationAction;
            }
            this.payloadCase_ = 193;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFirmwareUpdateUnavailable(Firmware.FirmwareUpdateUnavailable firmwareUpdateUnavailable) {
            if (this.payloadCase_ == 86 && this.payload_ != Firmware.FirmwareUpdateUnavailable.getDefaultInstance()) {
                this.payload_ = Firmware.FirmwareUpdateUnavailable.newBuilder((Firmware.FirmwareUpdateUnavailable) this.payload_).mergeFrom((Firmware.FirmwareUpdateUnavailable.Builder) firmwareUpdateUnavailable).mo10085buildPartial();
            } else {
                this.payload_ = firmwareUpdateUnavailable;
            }
            this.payloadCase_ = 86;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeForwardAtCommand(Calling.ForwardATCommand forwardATCommand) {
            if (this.payloadCase_ == 40 && this.payload_ != Calling.ForwardATCommand.getDefaultInstance()) {
                this.payload_ = Calling.ForwardATCommand.newBuilder((Calling.ForwardATCommand) this.payload_).mergeFrom((Calling.ForwardATCommand.Builder) forwardATCommand).mo10085buildPartial();
            } else {
                this.payload_ = forwardATCommand;
            }
            this.payloadCase_ = 40;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetArtifactFilter(Firmware.GetArtifactFilter getArtifactFilter) {
            if (this.payloadCase_ == 97 && this.payload_ != Firmware.GetArtifactFilter.getDefaultInstance()) {
                this.payload_ = Firmware.GetArtifactFilter.newBuilder((Firmware.GetArtifactFilter) this.payload_).mergeFrom((Firmware.GetArtifactFilter.Builder) getArtifactFilter).mo10085buildPartial();
            } else {
                this.payload_ = getArtifactFilter;
            }
            this.payloadCase_ = 97;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetArtifactUpdatePreference(Firmware.GetArtifactUpdatePreference getArtifactUpdatePreference) {
            if (this.payloadCase_ == 98 && this.payload_ != Firmware.GetArtifactUpdatePreference.getDefaultInstance()) {
                this.payload_ = Firmware.GetArtifactUpdatePreference.newBuilder((Firmware.GetArtifactUpdatePreference) this.payload_).mergeFrom((Firmware.GetArtifactUpdatePreference.Builder) getArtifactUpdatePreference).mo10085buildPartial();
            } else {
                this.payload_ = getArtifactUpdatePreference;
            }
            this.payloadCase_ = 98;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetAudiogram(Hearing.GetAudiogram getAudiogram) {
            if (this.payloadCase_ == 300 && this.payload_ != Hearing.GetAudiogram.getDefaultInstance()) {
                this.payload_ = Hearing.GetAudiogram.newBuilder((Hearing.GetAudiogram) this.payload_).mergeFrom((Hearing.GetAudiogram.Builder) getAudiogram).mo10085buildPartial();
            } else {
                this.payload_ = getAudiogram;
            }
            this.payloadCase_ = 300;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetBulkDataManifest(Bulkdata.GetBulkDataManifest getBulkDataManifest) {
            if (this.payloadCase_ == 180 && this.payload_ != Bulkdata.GetBulkDataManifest.getDefaultInstance()) {
                this.payload_ = Bulkdata.GetBulkDataManifest.newBuilder((Bulkdata.GetBulkDataManifest) this.payload_).mergeFrom((Bulkdata.GetBulkDataManifest.Builder) getBulkDataManifest).mo10085buildPartial();
            } else {
                this.payload_ = getBulkDataManifest;
            }
            this.payloadCase_ = 180;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetCachedComponent(Firmware.GetCachedComponent getCachedComponent) {
            if (this.payloadCase_ == 92 && this.payload_ != Firmware.GetCachedComponent.getDefaultInstance()) {
                this.payload_ = Firmware.GetCachedComponent.newBuilder((Firmware.GetCachedComponent) this.payload_).mergeFrom((Firmware.GetCachedComponent.Builder) getCachedComponent).mo10085buildPartial();
            } else {
                this.payload_ = getCachedComponent;
            }
            this.payloadCase_ = 92;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetCblInformation(Cbl.GetCblInformation getCblInformation) {
            if (this.payloadCase_ == 231 && this.payload_ != Cbl.GetCblInformation.getDefaultInstance()) {
                this.payload_ = Cbl.GetCblInformation.newBuilder((Cbl.GetCblInformation) this.payload_).mergeFrom((Cbl.GetCblInformation.Builder) getCblInformation).mo10085buildPartial();
            } else {
                this.payload_ = getCblInformation;
            }
            this.payloadCase_ = 231;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetCblLoginState(Cbl.GetCblLoginState getCblLoginState) {
            if (this.payloadCase_ == 230 && this.payload_ != Cbl.GetCblLoginState.getDefaultInstance()) {
                this.payload_ = Cbl.GetCblLoginState.newBuilder((Cbl.GetCblLoginState) this.payload_).mergeFrom((Cbl.GetCblLoginState.Builder) getCblLoginState).mo10085buildPartial();
            } else {
                this.payload_ = getCblLoginState;
            }
            this.payloadCase_ = 230;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetCentralInformation(Central.GetCentralInformation getCentralInformation) {
            if (this.payloadCase_ == 103 && this.payload_ != Central.GetCentralInformation.getDefaultInstance()) {
                this.payload_ = Central.GetCentralInformation.newBuilder((Central.GetCentralInformation) this.payload_).mergeFrom((Central.GetCentralInformation.Builder) getCentralInformation).mo10085buildPartial();
            } else {
                this.payload_ = getCentralInformation;
            }
            this.payloadCase_ = 103;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetCentralNotificationAppAttributes(Ancs.GetCentralNotificationAppAttributes getCentralNotificationAppAttributes) {
            if (this.payloadCase_ == 154 && this.payload_ != Ancs.GetCentralNotificationAppAttributes.getDefaultInstance()) {
                this.payload_ = Ancs.GetCentralNotificationAppAttributes.newBuilder((Ancs.GetCentralNotificationAppAttributes) this.payload_).mergeFrom((Ancs.GetCentralNotificationAppAttributes.Builder) getCentralNotificationAppAttributes).mo10085buildPartial();
            } else {
                this.payload_ = getCentralNotificationAppAttributes;
            }
            this.payloadCase_ = 154;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetCentralNotificationAttributes(Ancs.GetCentralNotificationAttributes getCentralNotificationAttributes) {
            if (this.payloadCase_ == 153 && this.payload_ != Ancs.GetCentralNotificationAttributes.getDefaultInstance()) {
                this.payload_ = Ancs.GetCentralNotificationAttributes.newBuilder((Ancs.GetCentralNotificationAttributes) this.payload_).mergeFrom((Ancs.GetCentralNotificationAttributes.Builder) getCentralNotificationAttributes).mo10085buildPartial();
            } else {
                this.payload_ = getCentralNotificationAttributes;
            }
            this.payloadCase_ = 153;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetCloudPairingAttributes(Cloudpairing.GetCloudPairingAttributes getCloudPairingAttributes) {
            if (this.payloadCase_ == 370 && this.payload_ != Cloudpairing.GetCloudPairingAttributes.getDefaultInstance()) {
                this.payload_ = Cloudpairing.GetCloudPairingAttributes.newBuilder((Cloudpairing.GetCloudPairingAttributes) this.payload_).mergeFrom((Cloudpairing.GetCloudPairingAttributes.Builder) getCloudPairingAttributes).mo10085buildPartial();
            } else {
                this.payload_ = getCloudPairingAttributes;
            }
            this.payloadCase_ = 370;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetCloudPairingStatus(Cloudpairing.GetCloudPairingStatus getCloudPairingStatus) {
            if (this.payloadCase_ == 371 && this.payload_ != Cloudpairing.GetCloudPairingStatus.getDefaultInstance()) {
                this.payload_ = Cloudpairing.GetCloudPairingStatus.newBuilder((Cloudpairing.GetCloudPairingStatus) this.payload_).mergeFrom((Cloudpairing.GetCloudPairingStatus.Builder) getCloudPairingStatus).mo10085buildPartial();
            } else {
                this.payload_ = getCloudPairingStatus;
            }
            this.payloadCase_ = 371;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetCurrentUser(System.GetCurrentUser getCurrentUser) {
            if (this.payloadCase_ == 54 && this.payload_ != System.GetCurrentUser.getDefaultInstance()) {
                this.payload_ = System.GetCurrentUser.newBuilder((System.GetCurrentUser) this.payload_).mergeFrom((System.GetCurrentUser.Builder) getCurrentUser).mo10085buildPartial();
            } else {
                this.payload_ = getCurrentUser;
            }
            this.payloadCase_ = 54;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetDeviceArtifacts(Firmware.GetDeviceArtifacts getDeviceArtifacts) {
            if (this.payloadCase_ == 96 && this.payload_ != Firmware.GetDeviceArtifacts.getDefaultInstance()) {
                this.payload_ = Firmware.GetDeviceArtifacts.newBuilder((Firmware.GetDeviceArtifacts) this.payload_).mergeFrom((Firmware.GetDeviceArtifacts.Builder) getDeviceArtifacts).mo10085buildPartial();
            } else {
                this.payload_ = getDeviceArtifacts;
            }
            this.payloadCase_ = 96;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetDeviceConfiguration(Device.GetDeviceConfiguration getDeviceConfiguration) {
            if (this.payloadCase_ == 21 && this.payload_ != Device.GetDeviceConfiguration.getDefaultInstance()) {
                this.payload_ = Device.GetDeviceConfiguration.newBuilder((Device.GetDeviceConfiguration) this.payload_).mergeFrom((Device.GetDeviceConfiguration.Builder) getDeviceConfiguration).mo10085buildPartial();
            } else {
                this.payload_ = getDeviceConfiguration;
            }
            this.payloadCase_ = 21;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetDeviceFeatures(Device.GetDeviceFeatures getDeviceFeatures) {
            if (this.payloadCase_ == 28 && this.payload_ != Device.GetDeviceFeatures.getDefaultInstance()) {
                this.payload_ = Device.GetDeviceFeatures.newBuilder((Device.GetDeviceFeatures) this.payload_).mergeFrom((Device.GetDeviceFeatures.Builder) getDeviceFeatures).mo10085buildPartial();
            } else {
                this.payload_ = getDeviceFeatures;
            }
            this.payloadCase_ = 28;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetDeviceInformation(Device.GetDeviceInformation getDeviceInformation) {
            if (this.payloadCase_ == 20 && this.payload_ != Device.GetDeviceInformation.getDefaultInstance()) {
                this.payload_ = Device.GetDeviceInformation.newBuilder((Device.GetDeviceInformation) this.payload_).mergeFrom((Device.GetDeviceInformation.Builder) getDeviceInformation).mo10085buildPartial();
            } else {
                this.payload_ = getDeviceInformation;
            }
            this.payloadCase_ = 20;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetDiagnostics(DiagnosticsOuterClass.GetDiagnostics getDiagnostics) {
            if (this.payloadCase_ == 110 && this.payload_ != DiagnosticsOuterClass.GetDiagnostics.getDefaultInstance()) {
                this.payload_ = DiagnosticsOuterClass.GetDiagnostics.newBuilder((DiagnosticsOuterClass.GetDiagnostics) this.payload_).mergeFrom((DiagnosticsOuterClass.GetDiagnostics.Builder) getDiagnostics).mo10085buildPartial();
            } else {
                this.payload_ = getDiagnostics;
            }
            this.payloadCase_ = 110;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetFirmwareInformation(Firmware.GetFirmwareInformation getFirmwareInformation) {
            if (this.payloadCase_ == 90 && this.payload_ != Firmware.GetFirmwareInformation.getDefaultInstance()) {
                this.payload_ = Firmware.GetFirmwareInformation.newBuilder((Firmware.GetFirmwareInformation) this.payload_).mergeFrom((Firmware.GetFirmwareInformation.Builder) getFirmwareInformation).mo10085buildPartial();
            } else {
                this.payload_ = getFirmwareInformation;
            }
            this.payloadCase_ = 90;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetFirmwareUpdatePreferences(Firmware.GetFirmwareUpdatePreferences getFirmwareUpdatePreferences) {
            if (this.payloadCase_ == 91 && this.payload_ != Firmware.GetFirmwareUpdatePreferences.getDefaultInstance()) {
                this.payload_ = Firmware.GetFirmwareUpdatePreferences.newBuilder((Firmware.GetFirmwareUpdatePreferences) this.payload_).mergeFrom((Firmware.GetFirmwareUpdatePreferences.Builder) getFirmwareUpdatePreferences).mo10085buildPartial();
            } else {
                this.payload_ = getFirmwareUpdatePreferences;
            }
            this.payloadCase_ = 91;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetFitnessData(Fitness.GetFitnessData getFitnessData) {
            if (this.payloadCase_ == 130 && this.payload_ != Fitness.GetFitnessData.getDefaultInstance()) {
                this.payload_ = Fitness.GetFitnessData.newBuilder((Fitness.GetFitnessData) this.payload_).mergeFrom((Fitness.GetFitnessData.Builder) getFitnessData).mo10085buildPartial();
            } else {
                this.payload_ = getFitnessData;
            }
            this.payloadCase_ = 130;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetInputBehavior(Input.GetInputBehavior getInputBehavior) {
            if (this.payloadCase_ == 162 && this.payload_ != Input.GetInputBehavior.getDefaultInstance()) {
                this.payload_ = Input.GetInputBehavior.newBuilder((Input.GetInputBehavior) this.payload_).mergeFrom((Input.GetInputBehavior.Builder) getInputBehavior).mo10085buildPartial();
            } else {
                this.payload_ = getInputBehavior;
            }
            this.payloadCase_ = 162;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetLocales(System.GetLocales getLocales) {
            if (this.payloadCase_ == 57 && this.payload_ != System.GetLocales.getDefaultInstance()) {
                this.payload_ = System.GetLocales.newBuilder((System.GetLocales) this.payload_).mergeFrom((System.GetLocales.Builder) getLocales).mo10085buildPartial();
            } else {
                this.payload_ = getLocales;
            }
            this.payloadCase_ = 57;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetMediaEnhancementCorrectionAmount(Hearing.GetMediaEnhancementCorrectionAmount getMediaEnhancementCorrectionAmount) {
            if (this.payloadCase_ == 302 && this.payload_ != Hearing.GetMediaEnhancementCorrectionAmount.getDefaultInstance()) {
                this.payload_ = Hearing.GetMediaEnhancementCorrectionAmount.newBuilder((Hearing.GetMediaEnhancementCorrectionAmount) this.payload_).mergeFrom((Hearing.GetMediaEnhancementCorrectionAmount.Builder) getMediaEnhancementCorrectionAmount).mo10085buildPartial();
            } else {
                this.payload_ = getMediaEnhancementCorrectionAmount;
            }
            this.payloadCase_ = 302;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetPlaybackStatus(Media.GetPlaybackStatus getPlaybackStatus) {
            if (this.payloadCase_ == 65 && this.payload_ != Media.GetPlaybackStatus.getDefaultInstance()) {
                this.payload_ = Media.GetPlaybackStatus.newBuilder((Media.GetPlaybackStatus) this.payload_).mergeFrom((Media.GetPlaybackStatus.Builder) getPlaybackStatus).mo10085buildPartial();
            } else {
                this.payload_ = getPlaybackStatus;
            }
            this.payloadCase_ = 65;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetSmsMessageList(Mapsms.GetSmsMessageList getSmsMessageList) {
            if (this.payloadCase_ == 352 && this.payload_ != Mapsms.GetSmsMessageList.getDefaultInstance()) {
                this.payload_ = Mapsms.GetSmsMessageList.newBuilder((Mapsms.GetSmsMessageList) this.payload_).mergeFrom((Mapsms.GetSmsMessageList.Builder) getSmsMessageList).mo10085buildPartial();
            } else {
                this.payload_ = getSmsMessageList;
            }
            this.payloadCase_ = 352;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetSmsMessageListResponse(Mapsms.GetSmsMessageListResponse getSmsMessageListResponse) {
            if (this.payloadCase_ == 350 && this.payload_ != Mapsms.GetSmsMessageListResponse.getDefaultInstance()) {
                this.payload_ = Mapsms.GetSmsMessageListResponse.newBuilder((Mapsms.GetSmsMessageListResponse) this.payload_).mergeFrom((Mapsms.GetSmsMessageListResponse.Builder) getSmsMessageListResponse).mo10085buildPartial();
            } else {
                this.payload_ = getSmsMessageListResponse;
            }
            this.payloadCase_ = 350;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetState(StateOuterClass.GetState getState) {
            if (this.payloadCase_ == 100 && this.payload_ != StateOuterClass.GetState.getDefaultInstance()) {
                this.payload_ = StateOuterClass.GetState.newBuilder((StateOuterClass.GetState) this.payload_).mergeFrom((StateOuterClass.GetState.Builder) getState).mo10085buildPartial();
            } else {
                this.payload_ = getState;
            }
            this.payloadCase_ = 100;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetUsers(System.GetUsers getUsers) {
            if (this.payloadCase_ == 52 && this.payload_ != System.GetUsers.getDefaultInstance()) {
                this.payload_ = System.GetUsers.newBuilder((System.GetUsers) this.payload_).mergeFrom((System.GetUsers.Builder) getUsers).mo10085buildPartial();
            } else {
                this.payload_ = getUsers;
            }
            this.payloadCase_ = 52;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGetWakewords(System.GetWakewords getWakewords) {
            if (this.payloadCase_ == 71 && this.payload_ != System.GetWakewords.getDefaultInstance()) {
                this.payload_ = System.GetWakewords.newBuilder((System.GetWakewords) this.payload_).mergeFrom((System.GetWakewords.Builder) getWakewords).mo10085buildPartial();
            } else {
                this.payload_ = getWakewords;
            }
            this.payloadCase_ = 71;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeIncomingCall(Calling.IncomingCall incomingCall) {
            if (this.payloadCase_ == 41 && this.payload_ != Calling.IncomingCall.getDefaultInstance()) {
                this.payload_ = Calling.IncomingCall.newBuilder((Calling.IncomingCall) this.payload_).mergeFrom((Calling.IncomingCall.Builder) incomingCall).mo10085buildPartial();
            } else {
                this.payload_ = incomingCall;
            }
            this.payloadCase_ = 41;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeInitiateFirmwareUpdate(Firmware.InitiateFirmwareUpdate initiateFirmwareUpdate) {
            if (this.payloadCase_ == 88 && this.payload_ != Firmware.InitiateFirmwareUpdate.getDefaultInstance()) {
                this.payload_ = Firmware.InitiateFirmwareUpdate.newBuilder((Firmware.InitiateFirmwareUpdate) this.payload_).mergeFrom((Firmware.InitiateFirmwareUpdate.Builder) initiateFirmwareUpdate).mo10085buildPartial();
            } else {
                this.payload_ = initiateFirmwareUpdate;
            }
            this.payloadCase_ = 88;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeInitiateHandshake(Keyexchange.InitiateHandshake initiateHandshake) {
            if (this.payloadCase_ == 200 && this.payload_ != Keyexchange.InitiateHandshake.getDefaultInstance()) {
                this.payload_ = Keyexchange.InitiateHandshake.newBuilder((Keyexchange.InitiateHandshake) this.payload_).mergeFrom((Keyexchange.InitiateHandshake.Builder) initiateHandshake).mo10085buildPartial();
            } else {
                this.payload_ = initiateHandshake;
            }
            this.payloadCase_ = 200;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeInitiateMapConnection(Mapsms.InitiateMapConnection initiateMapConnection) {
            if (this.payloadCase_ == 356 && this.payload_ != Mapsms.InitiateMapConnection.getDefaultInstance()) {
                this.payload_ = Mapsms.InitiateMapConnection.newBuilder((Mapsms.InitiateMapConnection) this.payload_).mergeFrom((Mapsms.InitiateMapConnection.Builder) initiateMapConnection).mo10085buildPartial();
            } else {
                this.payload_ = initiateMapConnection;
            }
            this.payloadCase_ = 356;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeIssueInputEvent(Input.IssueInputEvent issueInputEvent) {
            if (this.payloadCase_ == 160 && this.payload_ != Input.IssueInputEvent.getDefaultInstance()) {
                this.payload_ = Input.IssueInputEvent.newBuilder((Input.IssueInputEvent) this.payload_).mergeFrom((Input.IssueInputEvent.Builder) issueInputEvent).mo10085buildPartial();
            } else {
                this.payload_ = issueInputEvent;
            }
            this.payloadCase_ = 160;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeIssueMediaControl(Media.IssueMediaControl issueMediaControl) {
            if (this.payloadCase_ == 60 && this.payload_ != Media.IssueMediaControl.getDefaultInstance()) {
                this.payload_ = Media.IssueMediaControl.newBuilder((Media.IssueMediaControl) this.payload_).mergeFrom((Media.IssueMediaControl.Builder) issueMediaControl).mo10085buildPartial();
            } else {
                this.payload_ = issueMediaControl;
            }
            this.payloadCase_ = 60;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeIssueRemoteClearPairing(Instrumentation.IssueRemoteClearPairing issueRemoteClearPairing) {
            if (this.payloadCase_ == 167 && this.payload_ != Instrumentation.IssueRemoteClearPairing.getDefaultInstance()) {
                this.payload_ = Instrumentation.IssueRemoteClearPairing.newBuilder((Instrumentation.IssueRemoteClearPairing) this.payload_).mergeFrom((Instrumentation.IssueRemoteClearPairing.Builder) issueRemoteClearPairing).mo10085buildPartial();
            } else {
                this.payload_ = issueRemoteClearPairing;
            }
            this.payloadCase_ = 167;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeIssueRemoteCommand(Instrumentation.IssueRemoteCommand issueRemoteCommand) {
            if (this.payloadCase_ == 164 && this.payload_ != Instrumentation.IssueRemoteCommand.getDefaultInstance()) {
                this.payload_ = Instrumentation.IssueRemoteCommand.newBuilder((Instrumentation.IssueRemoteCommand) this.payload_).mergeFrom((Instrumentation.IssueRemoteCommand.Builder) issueRemoteCommand).mo10085buildPartial();
            } else {
                this.payload_ = issueRemoteCommand;
            }
            this.payloadCase_ = 164;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeIssueRemoteReset(Instrumentation.IssueRemoteReset issueRemoteReset) {
            if (this.payloadCase_ == 166 && this.payload_ != Instrumentation.IssueRemoteReset.getDefaultInstance()) {
                this.payload_ = Instrumentation.IssueRemoteReset.newBuilder((Instrumentation.IssueRemoteReset) this.payload_).mergeFrom((Instrumentation.IssueRemoteReset.Builder) issueRemoteReset).mo10085buildPartial();
            } else {
                this.payload_ = issueRemoteReset;
            }
            this.payloadCase_ = 166;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeIssueRemoteRestart(Instrumentation.IssueRemoteRestart issueRemoteRestart) {
            if (this.payloadCase_ == 165 && this.payload_ != Instrumentation.IssueRemoteRestart.getDefaultInstance()) {
                this.payload_ = Instrumentation.IssueRemoteRestart.newBuilder((Instrumentation.IssueRemoteRestart) this.payload_).mergeFrom((Instrumentation.IssueRemoteRestart.Builder) issueRemoteRestart).mo10085buildPartial();
            } else {
                this.payload_ = issueRemoteRestart;
            }
            this.payloadCase_ = 165;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeKeepAlive(System.KeepAlive keepAlive) {
            if (this.payloadCase_ == 55 && this.payload_ != System.KeepAlive.getDefaultInstance()) {
                this.payload_ = System.KeepAlive.newBuilder((System.KeepAlive) this.payload_).mergeFrom((System.KeepAlive.Builder) keepAlive).mo10085buildPartial();
            } else {
                this.payload_ = keepAlive;
            }
            this.payloadCase_ = 55;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeLaunchApp(System.LaunchApp launchApp) {
            if (this.payloadCase_ == 59 && this.payload_ != System.LaunchApp.getDefaultInstance()) {
                this.payload_ = System.LaunchApp.newBuilder((System.LaunchApp) this.payload_).mergeFrom((System.LaunchApp.Builder) launchApp).mo10085buildPartial();
            } else {
                this.payload_ = launchApp;
            }
            this.payloadCase_ = 59;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeLiveFitnessData(Fitness.LiveFitnessData liveFitnessData) {
            if (this.payloadCase_ == 136 && this.payload_ != Fitness.LiveFitnessData.getDefaultInstance()) {
                this.payload_ = Fitness.LiveFitnessData.newBuilder((Fitness.LiveFitnessData) this.payload_).mergeFrom((Fitness.LiveFitnessData.Builder) liveFitnessData).mo10085buildPartial();
            } else {
                this.payload_ = liveFitnessData;
            }
            this.payloadCase_ = 136;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeMediaEventOccurred(Media.MediaEventOccurred mediaEventOccurred) {
            if (this.payloadCase_ == 67 && this.payload_ != Media.MediaEventOccurred.getDefaultInstance()) {
                this.payload_ = Media.MediaEventOccurred.newBuilder((Media.MediaEventOccurred) this.payload_).mergeFrom((Media.MediaEventOccurred.Builder) mediaEventOccurred).mo10085buildPartial();
            } else {
                this.payload_ = mediaEventOccurred;
            }
            this.payloadCase_ = 67;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNotifyBulkDataAvailable(Bulkdata.NotifyBulkDataAvailable notifyBulkDataAvailable) {
            if (this.payloadCase_ == 186 && this.payload_ != Bulkdata.NotifyBulkDataAvailable.getDefaultInstance()) {
                this.payload_ = Bulkdata.NotifyBulkDataAvailable.newBuilder((Bulkdata.NotifyBulkDataAvailable) this.payload_).mergeFrom((Bulkdata.NotifyBulkDataAvailable.Builder) notifyBulkDataAvailable).mo10085buildPartial();
            } else {
                this.payload_ = notifyBulkDataAvailable;
            }
            this.payloadCase_ = 186;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNotifyCblLoginState(Cbl.NotifyCblLoginState notifyCblLoginState) {
            if (this.payloadCase_ == 232 && this.payload_ != Cbl.NotifyCblLoginState.getDefaultInstance()) {
                this.payload_ = Cbl.NotifyCblLoginState.newBuilder((Cbl.NotifyCblLoginState) this.payload_).mergeFrom((Cbl.NotifyCblLoginState.Builder) notifyCblLoginState).mo10085buildPartial();
            } else {
                this.payload_ = notifyCblLoginState;
            }
            this.payloadCase_ = 232;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNotifyDeviceConfiguration(Device.NotifyDeviceConfiguration notifyDeviceConfiguration) {
            if (this.payloadCase_ == 25 && this.payload_ != Device.NotifyDeviceConfiguration.getDefaultInstance()) {
                this.payload_ = Device.NotifyDeviceConfiguration.newBuilder((Device.NotifyDeviceConfiguration) this.payload_).mergeFrom((Device.NotifyDeviceConfiguration.Builder) notifyDeviceConfiguration).mo10085buildPartial();
            } else {
                this.payload_ = notifyDeviceConfiguration;
            }
            this.payloadCase_ = 25;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNotifyDeviceInformation(Device.NotifyDeviceInformation notifyDeviceInformation) {
            if (this.payloadCase_ == 27 && this.payload_ != Device.NotifyDeviceInformation.getDefaultInstance()) {
                this.payload_ = Device.NotifyDeviceInformation.newBuilder((Device.NotifyDeviceInformation) this.payload_).mergeFrom((Device.NotifyDeviceInformation.Builder) notifyDeviceInformation).mo10085buildPartial();
            } else {
                this.payload_ = notifyDeviceInformation;
            }
            this.payloadCase_ = 27;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNotifyDiagnosticsAvailable(DiagnosticsOuterClass.NotifyDiagnosticsAvailable notifyDiagnosticsAvailable) {
            if (this.payloadCase_ == 112 && this.payload_ != DiagnosticsOuterClass.NotifyDiagnosticsAvailable.getDefaultInstance()) {
                this.payload_ = DiagnosticsOuterClass.NotifyDiagnosticsAvailable.newBuilder((DiagnosticsOuterClass.NotifyDiagnosticsAvailable) this.payload_).mergeFrom((DiagnosticsOuterClass.NotifyDiagnosticsAvailable.Builder) notifyDiagnosticsAvailable).mo10085buildPartial();
            } else {
                this.payload_ = notifyDiagnosticsAvailable;
            }
            this.payloadCase_ = 112;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNotifyFirmwareInformation(Firmware.NotifyFirmwareInformation notifyFirmwareInformation) {
            if (this.payloadCase_ == 87 && this.payload_ != Firmware.NotifyFirmwareInformation.getDefaultInstance()) {
                this.payload_ = Firmware.NotifyFirmwareInformation.newBuilder((Firmware.NotifyFirmwareInformation) this.payload_).mergeFrom((Firmware.NotifyFirmwareInformation.Builder) notifyFirmwareInformation).mo10085buildPartial();
            } else {
                this.payload_ = notifyFirmwareInformation;
            }
            this.payloadCase_ = 87;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNotifyFitnessDataAvailable(Fitness.NotifyFitnessDataAvailable notifyFitnessDataAvailable) {
            if (this.payloadCase_ == 132 && this.payload_ != Fitness.NotifyFitnessDataAvailable.getDefaultInstance()) {
                this.payload_ = Fitness.NotifyFitnessDataAvailable.newBuilder((Fitness.NotifyFitnessDataAvailable) this.payload_).mergeFrom((Fitness.NotifyFitnessDataAvailable.Builder) notifyFitnessDataAvailable).mo10085buildPartial();
            } else {
                this.payload_ = notifyFitnessDataAvailable;
            }
            this.payloadCase_ = 132;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNotifySmsMessageList(Mapsms.NotifySmsMessageList notifySmsMessageList) {
            if (this.payloadCase_ == 351 && this.payload_ != Mapsms.NotifySmsMessageList.getDefaultInstance()) {
                this.payload_ = Mapsms.NotifySmsMessageList.newBuilder((Mapsms.NotifySmsMessageList) this.payload_).mergeFrom((Mapsms.NotifySmsMessageList.Builder) notifySmsMessageList).mo10085buildPartial();
            } else {
                this.payload_ = notifySmsMessageList;
            }
            this.payloadCase_ = 351;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNotifySpeechState(Speech.NotifySpeechState notifySpeechState) {
            if (this.payloadCase_ == 14 && this.payload_ != Speech.NotifySpeechState.getDefaultInstance()) {
                this.payload_ = Speech.NotifySpeechState.newBuilder((Speech.NotifySpeechState) this.payload_).mergeFrom((Speech.NotifySpeechState.Builder) notifySpeechState).mo10085buildPartial();
            } else {
                this.payload_ = notifySpeechState;
            }
            this.payloadCase_ = 14;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeOverrideAssistant(Device.OverrideAssistant overrideAssistant) {
            if (this.payloadCase_ == 22 && this.payload_ != Device.OverrideAssistant.getDefaultInstance()) {
                this.payload_ = Device.OverrideAssistant.newBuilder((Device.OverrideAssistant) this.payload_).mergeFrom((Device.OverrideAssistant.Builder) overrideAssistant).mo10085buildPartial();
            } else {
                this.payload_ = overrideAssistant;
            }
            this.payloadCase_ = 22;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePerformCentralNotificationAction(Ancs.PerformCentralNotificationAction performCentralNotificationAction) {
            if (this.payloadCase_ == 155 && this.payload_ != Ancs.PerformCentralNotificationAction.getDefaultInstance()) {
                this.payload_ = Ancs.PerformCentralNotificationAction.newBuilder((Ancs.PerformCentralNotificationAction) this.payload_).mergeFrom((Ancs.PerformCentralNotificationAction.Builder) performCentralNotificationAction).mo10085buildPartial();
            } else {
                this.payload_ = performCentralNotificationAction;
            }
            this.payloadCase_ = 155;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePrintDebug(Instrumentation.PrintDebug printDebug) {
            if (this.payloadCase_ == 163 && this.payload_ != Instrumentation.PrintDebug.getDefaultInstance()) {
                this.payload_ = Instrumentation.PrintDebug.newBuilder((Instrumentation.PrintDebug) this.payload_).mergeFrom((Instrumentation.PrintDebug.Builder) printDebug).mo10085buildPartial();
            } else {
                this.payload_ = printDebug;
            }
            this.payloadCase_ = 163;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeProvideSpeech(Speech.ProvideSpeech provideSpeech) {
            if (this.payloadCase_ == 10 && this.payload_ != Speech.ProvideSpeech.getDefaultInstance()) {
                this.payload_ = Speech.ProvideSpeech.newBuilder((Speech.ProvideSpeech) this.payload_).mergeFrom((Speech.ProvideSpeech.Builder) provideSpeech).mo10085buildPartial();
            } else {
                this.payload_ = provideSpeech;
            }
            this.payloadCase_ = 10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeProvideTranslation(Translation.ProvideTranslation provideTranslation) {
            if (this.payloadCase_ == 171 && this.payload_ != Translation.ProvideTranslation.getDefaultInstance()) {
                this.payload_ = Translation.ProvideTranslation.newBuilder((Translation.ProvideTranslation) this.payload_).mergeFrom((Translation.ProvideTranslation.Builder) provideTranslation).mo10085buildPartial();
            } else {
                this.payload_ = provideTranslation;
            }
            this.payloadCase_ = 171;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePublishCentralNotification(Ancs.PublishCentralNotification publishCentralNotification) {
            if (this.payloadCase_ == 152 && this.payload_ != Ancs.PublishCentralNotification.getDefaultInstance()) {
                this.payload_ = Ancs.PublishCentralNotification.newBuilder((Ancs.PublishCentralNotification) this.payload_).mergeFrom((Ancs.PublishCentralNotification.Builder) publishCentralNotification).mo10085buildPartial();
            } else {
                this.payload_ = publishCentralNotification;
            }
            this.payloadCase_ = 152;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePushMetrics(Metrics.PushMetrics pushMetrics) {
            if (this.payloadCase_ == 120 && this.payload_ != Metrics.PushMetrics.getDefaultInstance()) {
                this.payload_ = Metrics.PushMetrics.newBuilder((Metrics.PushMetrics) this.payload_).mergeFrom((Metrics.PushMetrics.Builder) pushMetrics).mo10085buildPartial();
            } else {
                this.payload_ = pushMetrics;
            }
            this.payloadCase_ = 120;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeRegisterForMediaEvents(Media.RegisterForMediaEvents registerForMediaEvents) {
            if (this.payloadCase_ == 66 && this.payload_ != Media.RegisterForMediaEvents.getDefaultInstance()) {
                this.payload_ = Media.RegisterForMediaEvents.newBuilder((Media.RegisterForMediaEvents) this.payload_).mergeFrom((Media.RegisterForMediaEvents.Builder) registerForMediaEvents).mo10085buildPartial();
            } else {
                this.payload_ = registerForMediaEvents;
            }
            this.payloadCase_ = 66;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeRejectCall(Nonhfpcalling.RejectCall rejectCall) {
            if (this.payloadCase_ == 142 && this.payload_ != Nonhfpcalling.RejectCall.getDefaultInstance()) {
                this.payload_ = Nonhfpcalling.RejectCall.newBuilder((Nonhfpcalling.RejectCall) this.payload_).mergeFrom((Nonhfpcalling.RejectCall.Builder) rejectCall).mo10085buildPartial();
            } else {
                this.payload_ = rejectCall;
            }
            this.payloadCase_ = 142;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeRemoveCloudPairingKeys(Cloudpairing.RemoveCloudPairingKeys removeCloudPairingKeys) {
            if (this.payloadCase_ == 374 && this.payload_ != Cloudpairing.RemoveCloudPairingKeys.getDefaultInstance()) {
                this.payload_ = Cloudpairing.RemoveCloudPairingKeys.newBuilder((Cloudpairing.RemoveCloudPairingKeys) this.payload_).mergeFrom((Cloudpairing.RemoveCloudPairingKeys.Builder) removeCloudPairingKeys).mo10085buildPartial();
            } else {
                this.payload_ = removeCloudPairingKeys;
            }
            this.payloadCase_ = 374;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeRemoveDevice(System.RemoveDevice removeDevice) {
            if (this.payloadCase_ == 56 && this.payload_ != System.RemoveDevice.getDefaultInstance()) {
                this.payload_ = System.RemoveDevice.newBuilder((System.RemoveDevice) this.payload_).mergeFrom((System.RemoveDevice.Builder) removeDevice).mo10085buildPartial();
            } else {
                this.payload_ = removeDevice;
            }
            this.payloadCase_ = 56;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeRemoveNotification(Notification.RemoveNotification removeNotification) {
            if (this.payloadCase_ == 192 && this.payload_ != Notification.RemoveNotification.getDefaultInstance()) {
                this.payload_ = Notification.RemoveNotification.newBuilder((Notification.RemoveNotification) this.payload_).mergeFrom((Notification.RemoveNotification.Builder) removeNotification).mo10085buildPartial();
            } else {
                this.payload_ = removeNotification;
            }
            this.payloadCase_ = 192;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeReplaceCloudPairingKeys(Cloudpairing.ReplaceCloudPairingKeys replaceCloudPairingKeys) {
            if (this.payloadCase_ == 373 && this.payload_ != Cloudpairing.ReplaceCloudPairingKeys.getDefaultInstance()) {
                this.payload_ = Cloudpairing.ReplaceCloudPairingKeys.newBuilder((Cloudpairing.ReplaceCloudPairingKeys) this.payload_).mergeFrom((Cloudpairing.ReplaceCloudPairingKeys.Builder) replaceCloudPairingKeys).mo10085buildPartial();
            } else {
                this.payload_ = replaceCloudPairingKeys;
            }
            this.payloadCase_ = 373;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeRequestBulkDataTransfer(Bulkdata.RequestBulkDataTransfer requestBulkDataTransfer) {
            if (this.payloadCase_ == 182 && this.payload_ != Bulkdata.RequestBulkDataTransfer.getDefaultInstance()) {
                this.payload_ = Bulkdata.RequestBulkDataTransfer.newBuilder((Bulkdata.RequestBulkDataTransfer) this.payload_).mergeFrom((Bulkdata.RequestBulkDataTransfer.Builder) requestBulkDataTransfer).mo10085buildPartial();
            } else {
                this.payload_ = requestBulkDataTransfer;
            }
            this.payloadCase_ = 182;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeResetCachedComponent(Firmware.ResetCachedComponent resetCachedComponent) {
            if (this.payloadCase_ == 93 && this.payload_ != Firmware.ResetCachedComponent.getDefaultInstance()) {
                this.payload_ = Firmware.ResetCachedComponent.newBuilder((Firmware.ResetCachedComponent) this.payload_).mergeFrom((Firmware.ResetCachedComponent.Builder) resetCachedComponent).mo10085buildPartial();
            } else {
                this.payload_ = resetCachedComponent;
            }
            this.payloadCase_ = 93;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeResetConnection(System.ResetConnection resetConnection) {
            if (this.payloadCase_ == 51 && this.payload_ != System.ResetConnection.getDefaultInstance()) {
                this.payload_ = System.ResetConnection.newBuilder((System.ResetConnection) this.payload_).mergeFrom((System.ResetConnection.Builder) resetConnection).mo10085buildPartial();
            } else {
                this.payload_ = resetConnection;
            }
            this.payloadCase_ = 51;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeResetInputBehavior(Input.ResetInputBehavior resetInputBehavior) {
            if (this.payloadCase_ == 168 && this.payload_ != Input.ResetInputBehavior.getDefaultInstance()) {
                this.payload_ = Input.ResetInputBehavior.newBuilder((Input.ResetInputBehavior) this.payload_).mergeFrom((Input.ResetInputBehavior.Builder) resetInputBehavior).mo10085buildPartial();
            } else {
                this.payload_ = resetInputBehavior;
            }
            this.payloadCase_ = 168;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeResetKey(Keyexchange.ResetKey resetKey) {
            if (this.payloadCase_ == 203 && this.payload_ != Keyexchange.ResetKey.getDefaultInstance()) {
                this.payload_ = Keyexchange.ResetKey.newBuilder((Keyexchange.ResetKey) this.payload_).mergeFrom((Keyexchange.ResetKey.Builder) resetKey).mo10085buildPartial();
            } else {
                this.payload_ = resetKey;
            }
            this.payloadCase_ = 203;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeResetRootKeys(Keyexchange.ResetRootKeys resetRootKeys) {
            if (this.payloadCase_ == 205 && this.payload_ != Keyexchange.ResetRootKeys.getDefaultInstance()) {
                this.payload_ = Keyexchange.ResetRootKeys.newBuilder((Keyexchange.ResetRootKeys) this.payload_).mergeFrom((Keyexchange.ResetRootKeys.Builder) resetRootKeys).mo10085buildPartial();
            } else {
                this.payload_ = resetRootKeys;
            }
            this.payloadCase_ = 205;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeResponse(Response response) {
            if (this.payloadCase_ == 9 && this.payload_ != Response.getDefaultInstance()) {
                this.payload_ = Response.newBuilder((Response) this.payload_).mergeFrom((Response.Builder) response).mo10085buildPartial();
            } else {
                this.payload_ = response;
            }
            this.payloadCase_ = 9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSendSms(Mapsms.SendSms sendSms) {
            if (this.payloadCase_ == 353 && this.payload_ != Mapsms.SendSms.getDefaultInstance()) {
                this.payload_ = Mapsms.SendSms.newBuilder((Mapsms.SendSms) this.payload_).mergeFrom((Mapsms.SendSms.Builder) sendSms).mo10085buildPartial();
            } else {
                this.payload_ = sendSms;
            }
            this.payloadCase_ = 353;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSetAudiogram(Hearing.SetAudiogram setAudiogram) {
            if (this.payloadCase_ == 301 && this.payload_ != Hearing.SetAudiogram.getDefaultInstance()) {
                this.payload_ = Hearing.SetAudiogram.newBuilder((Hearing.SetAudiogram) this.payload_).mergeFrom((Hearing.SetAudiogram.Builder) setAudiogram).mo10085buildPartial();
            } else {
                this.payload_ = setAudiogram;
            }
            this.payloadCase_ = 301;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSetCloudPairingKeys(Cloudpairing.SetCloudPairingKeys setCloudPairingKeys) {
            if (this.payloadCase_ == 372 && this.payload_ != Cloudpairing.SetCloudPairingKeys.getDefaultInstance()) {
                this.payload_ = Cloudpairing.SetCloudPairingKeys.newBuilder((Cloudpairing.SetCloudPairingKeys) this.payload_).mergeFrom((Cloudpairing.SetCloudPairingKeys.Builder) setCloudPairingKeys).mo10085buildPartial();
            } else {
                this.payload_ = setCloudPairingKeys;
            }
            this.payloadCase_ = 372;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSetInputBehavior(Input.SetInputBehavior setInputBehavior) {
            if (this.payloadCase_ == 161 && this.payload_ != Input.SetInputBehavior.getDefaultInstance()) {
                this.payload_ = Input.SetInputBehavior.newBuilder((Input.SetInputBehavior) this.payload_).mergeFrom((Input.SetInputBehavior.Builder) setInputBehavior).mo10085buildPartial();
            } else {
                this.payload_ = setInputBehavior;
            }
            this.payloadCase_ = 161;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSetLocale(System.SetLocale setLocale) {
            if (this.payloadCase_ == 58 && this.payload_ != System.SetLocale.getDefaultInstance()) {
                this.payload_ = System.SetLocale.newBuilder((System.SetLocale) this.payload_).mergeFrom((System.SetLocale.Builder) setLocale).mo10085buildPartial();
            } else {
                this.payload_ = setLocale;
            }
            this.payloadCase_ = 58;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSetMediaEnhancementCorrectionAmount(Hearing.SetMediaEnhancementCorrectionAmount setMediaEnhancementCorrectionAmount) {
            if (this.payloadCase_ == 303 && this.payload_ != Hearing.SetMediaEnhancementCorrectionAmount.getDefaultInstance()) {
                this.payload_ = Hearing.SetMediaEnhancementCorrectionAmount.newBuilder((Hearing.SetMediaEnhancementCorrectionAmount) this.payload_).mergeFrom((Hearing.SetMediaEnhancementCorrectionAmount.Builder) setMediaEnhancementCorrectionAmount).mo10085buildPartial();
            } else {
                this.payload_ = setMediaEnhancementCorrectionAmount;
            }
            this.payloadCase_ = 303;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSetSmsReadStatus(Mapsms.SetSmsReadStatus setSmsReadStatus) {
            if (this.payloadCase_ == 354 && this.payload_ != Mapsms.SetSmsReadStatus.getDefaultInstance()) {
                this.payload_ = Mapsms.SetSmsReadStatus.newBuilder((Mapsms.SetSmsReadStatus) this.payload_).mergeFrom((Mapsms.SetSmsReadStatus.Builder) setSmsReadStatus).mo10085buildPartial();
            } else {
                this.payload_ = setSmsReadStatus;
            }
            this.payloadCase_ = 354;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSetState(StateOuterClass.SetState setState) {
            if (this.payloadCase_ == 101 && this.payload_ != StateOuterClass.SetState.getDefaultInstance()) {
                this.payload_ = StateOuterClass.SetState.newBuilder((StateOuterClass.SetState) this.payload_).mergeFrom((StateOuterClass.SetState.Builder) setState).mo10085buildPartial();
            } else {
                this.payload_ = setState;
            }
            this.payloadCase_ = 101;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSetWakewords(System.SetWakewords setWakewords) {
            if (this.payloadCase_ == 70 && this.payload_ != System.SetWakewords.getDefaultInstance()) {
                this.payload_ = System.SetWakewords.newBuilder((System.SetWakewords) this.payload_).mergeFrom((System.SetWakewords.Builder) setWakewords).mo10085buildPartial();
            } else {
                this.payload_ = setWakewords;
            }
            this.payloadCase_ = 70;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStartFirmwareUpdate(Firmware.StartFirmwareUpdate startFirmwareUpdate) {
            if (this.payloadCase_ == 99 && this.payload_ != Firmware.StartFirmwareUpdate.getDefaultInstance()) {
                this.payload_ = Firmware.StartFirmwareUpdate.newBuilder((Firmware.StartFirmwareUpdate) this.payload_).mergeFrom((Firmware.StartFirmwareUpdate.Builder) startFirmwareUpdate).mo10085buildPartial();
            } else {
                this.payload_ = startFirmwareUpdate;
            }
            this.payloadCase_ = 99;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStartLiveFitnessData(Fitness.StartLiveFitnessData startLiveFitnessData) {
            if (this.payloadCase_ == 134 && this.payload_ != Fitness.StartLiveFitnessData.getDefaultInstance()) {
                this.payload_ = Fitness.StartLiveFitnessData.newBuilder((Fitness.StartLiveFitnessData) this.payload_).mergeFrom((Fitness.StartLiveFitnessData.Builder) startLiveFitnessData).mo10085buildPartial();
            } else {
                this.payload_ = startLiveFitnessData;
            }
            this.payloadCase_ = 134;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStartSetup(Device.StartSetup startSetup) {
            if (this.payloadCase_ == 23 && this.payload_ != Device.StartSetup.getDefaultInstance()) {
                this.payload_ = Device.StartSetup.newBuilder((Device.StartSetup) this.payload_).mergeFrom((Device.StartSetup.Builder) startSetup).mo10085buildPartial();
            } else {
                this.payload_ = startSetup;
            }
            this.payloadCase_ = 23;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStartSpeech(Speech.StartSpeech startSpeech) {
            if (this.payloadCase_ == 11 && this.payload_ != Speech.StartSpeech.getDefaultInstance()) {
                this.payload_ = Speech.StartSpeech.newBuilder((Speech.StartSpeech) this.payload_).mergeFrom((Speech.StartSpeech.Builder) startSpeech).mo10085buildPartial();
            } else {
                this.payload_ = startSpeech;
            }
            this.payloadCase_ = 11;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStartTranslation(Translation.StartTranslation startTranslation) {
            if (this.payloadCase_ == 170 && this.payload_ != Translation.StartTranslation.getDefaultInstance()) {
                this.payload_ = Translation.StartTranslation.newBuilder((Translation.StartTranslation) this.payload_).mergeFrom((Translation.StartTranslation.Builder) startTranslation).mo10085buildPartial();
            } else {
                this.payload_ = startTranslation;
            }
            this.payloadCase_ = 170;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStartVoiceStream(Voicestream.StartVoiceStream startVoiceStream) {
            if (this.payloadCase_ == 360 && this.payload_ != Voicestream.StartVoiceStream.getDefaultInstance()) {
                this.payload_ = Voicestream.StartVoiceStream.newBuilder((Voicestream.StartVoiceStream) this.payload_).mergeFrom((Voicestream.StartVoiceStream.Builder) startVoiceStream).mo10085buildPartial();
            } else {
                this.payload_ = startVoiceStream;
            }
            this.payloadCase_ = 360;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStopBulkDataTransfer(Bulkdata.StopBulkDataTransfer stopBulkDataTransfer) {
            if (this.payloadCase_ == 185 && this.payload_ != Bulkdata.StopBulkDataTransfer.getDefaultInstance()) {
                this.payload_ = Bulkdata.StopBulkDataTransfer.newBuilder((Bulkdata.StopBulkDataTransfer) this.payload_).mergeFrom((Bulkdata.StopBulkDataTransfer.Builder) stopBulkDataTransfer).mo10085buildPartial();
            } else {
                this.payload_ = stopBulkDataTransfer;
            }
            this.payloadCase_ = 185;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStopDiagnostics(DiagnosticsOuterClass.StopDiagnostics stopDiagnostics) {
            if (this.payloadCase_ == 111 && this.payload_ != DiagnosticsOuterClass.StopDiagnostics.getDefaultInstance()) {
                this.payload_ = DiagnosticsOuterClass.StopDiagnostics.newBuilder((DiagnosticsOuterClass.StopDiagnostics) this.payload_).mergeFrom((DiagnosticsOuterClass.StopDiagnostics.Builder) stopDiagnostics).mo10085buildPartial();
            } else {
                this.payload_ = stopDiagnostics;
            }
            this.payloadCase_ = 111;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStopFitnessData(Fitness.StopFitnessData stopFitnessData) {
            if (this.payloadCase_ == 131 && this.payload_ != Fitness.StopFitnessData.getDefaultInstance()) {
                this.payload_ = Fitness.StopFitnessData.newBuilder((Fitness.StopFitnessData) this.payload_).mergeFrom((Fitness.StopFitnessData.Builder) stopFitnessData).mo10085buildPartial();
            } else {
                this.payload_ = stopFitnessData;
            }
            this.payloadCase_ = 131;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStopLiveFitnessData(Fitness.StopLiveFitnessData stopLiveFitnessData) {
            if (this.payloadCase_ == 135 && this.payload_ != Fitness.StopLiveFitnessData.getDefaultInstance()) {
                this.payload_ = Fitness.StopLiveFitnessData.newBuilder((Fitness.StopLiveFitnessData) this.payload_).mergeFrom((Fitness.StopLiveFitnessData.Builder) stopLiveFitnessData).mo10085buildPartial();
            } else {
                this.payload_ = stopLiveFitnessData;
            }
            this.payloadCase_ = 135;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStopSpeech(Speech.StopSpeech stopSpeech) {
            if (this.payloadCase_ == 12 && this.payload_ != Speech.StopSpeech.getDefaultInstance()) {
                this.payload_ = Speech.StopSpeech.newBuilder((Speech.StopSpeech) this.payload_).mergeFrom((Speech.StopSpeech.Builder) stopSpeech).mo10085buildPartial();
            } else {
                this.payload_ = stopSpeech;
            }
            this.payloadCase_ = 12;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStopTranslation(Translation.StopTranslation stopTranslation) {
            if (this.payloadCase_ == 172 && this.payload_ != Translation.StopTranslation.getDefaultInstance()) {
                this.payload_ = Translation.StopTranslation.newBuilder((Translation.StopTranslation) this.payload_).mergeFrom((Translation.StopTranslation.Builder) stopTranslation).mo10085buildPartial();
            } else {
                this.payload_ = stopTranslation;
            }
            this.payloadCase_ = 172;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeStopVoiceStream(Voicestream.StopVoiceStream stopVoiceStream) {
            if (this.payloadCase_ == 361 && this.payload_ != Voicestream.StopVoiceStream.getDefaultInstance()) {
                this.payload_ = Voicestream.StopVoiceStream.newBuilder((Voicestream.StopVoiceStream) this.payload_).mergeFrom((Voicestream.StopVoiceStream.Builder) stopVoiceStream).mo10085buildPartial();
            } else {
                this.payload_ = stopVoiceStream;
            }
            this.payloadCase_ = 361;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSubscribeNotificationCenter(Ancs.SubscribeNotificationCenter subscribeNotificationCenter) {
            if (this.payloadCase_ == 150 && this.payload_ != Ancs.SubscribeNotificationCenter.getDefaultInstance()) {
                this.payload_ = Ancs.SubscribeNotificationCenter.newBuilder((Ancs.SubscribeNotificationCenter) this.payload_).mergeFrom((Ancs.SubscribeNotificationCenter.Builder) subscribeNotificationCenter).mo10085buildPartial();
            } else {
                this.payload_ = subscribeNotificationCenter;
            }
            this.payloadCase_ = 150;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSwitchCurrentUser(System.SwitchCurrentUser switchCurrentUser) {
            if (this.payloadCase_ == 53 && this.payload_ != System.SwitchCurrentUser.getDefaultInstance()) {
                this.payload_ = System.SwitchCurrentUser.newBuilder((System.SwitchCurrentUser) this.payload_).mergeFrom((System.SwitchCurrentUser.Builder) switchCurrentUser).mo10085buildPartial();
            } else {
                this.payload_ = switchCurrentUser;
            }
            this.payloadCase_ = 53;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSwitchTransport(Transport.SwitchTransport switchTransport) {
            if (this.payloadCase_ == 31 && this.payload_ != Transport.SwitchTransport.getDefaultInstance()) {
                this.payload_ = Transport.SwitchTransport.newBuilder((Transport.SwitchTransport) this.payload_).mergeFrom((Transport.SwitchTransport.Builder) switchTransport).mo10085buildPartial();
            } else {
                this.payload_ = switchTransport;
            }
            this.payloadCase_ = 31;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSyncFitnessSession(Fitness.SyncFitnessSession syncFitnessSession) {
            if (this.payloadCase_ == 133 && this.payload_ != Fitness.SyncFitnessSession.getDefaultInstance()) {
                this.payload_ = Fitness.SyncFitnessSession.newBuilder((Fitness.SyncFitnessSession) this.payload_).mergeFrom((Fitness.SyncFitnessSession.Builder) syncFitnessSession).mo10085buildPartial();
            } else {
                this.payload_ = syncFitnessSession;
            }
            this.payloadCase_ = 133;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSynchronizeSettings(System.SynchronizeSettings synchronizeSettings) {
            if (this.payloadCase_ == 50 && this.payload_ != System.SynchronizeSettings.getDefaultInstance()) {
                this.payload_ = System.SynchronizeSettings.newBuilder((System.SynchronizeSettings) this.payload_).mergeFrom((System.SynchronizeSettings.Builder) synchronizeSettings).mo10085buildPartial();
            } else {
                this.payload_ = synchronizeSettings;
            }
            this.payloadCase_ = 50;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSynchronizeState(StateOuterClass.SynchronizeState synchronizeState) {
            if (this.payloadCase_ == 102 && this.payload_ != StateOuterClass.SynchronizeState.getDefaultInstance()) {
                this.payload_ = StateOuterClass.SynchronizeState.newBuilder((StateOuterClass.SynchronizeState) this.payload_).mergeFrom((StateOuterClass.SynchronizeState.Builder) synchronizeState).mo10085buildPartial();
            } else {
                this.payload_ = synchronizeState;
            }
            this.payloadCase_ = 102;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUnpairUser(System.UnpairUser unpairUser) {
            if (this.payloadCase_ == 64 && this.payload_ != System.UnpairUser.getDefaultInstance()) {
                this.payload_ = System.UnpairUser.newBuilder((System.UnpairUser) this.payload_).mergeFrom((System.UnpairUser.Builder) unpairUser).mo10085buildPartial();
            } else {
                this.payload_ = unpairUser;
            }
            this.payloadCase_ = 64;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUnsubscribeNotificationCenter(Ancs.UnsubscribeNotificationCenter unsubscribeNotificationCenter) {
            if (this.payloadCase_ == 151 && this.payload_ != Ancs.UnsubscribeNotificationCenter.getDefaultInstance()) {
                this.payload_ = Ancs.UnsubscribeNotificationCenter.newBuilder((Ancs.UnsubscribeNotificationCenter) this.payload_).mergeFrom((Ancs.UnsubscribeNotificationCenter.Builder) unsubscribeNotificationCenter).mo10085buildPartial();
            } else {
                this.payload_ = unsubscribeNotificationCenter;
            }
            this.payloadCase_ = 151;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUpdateCallState(Nonhfpcalling.UpdateCallState updateCallState) {
            if (this.payloadCase_ == 140 && this.payload_ != Nonhfpcalling.UpdateCallState.getDefaultInstance()) {
                this.payload_ = Nonhfpcalling.UpdateCallState.newBuilder((Nonhfpcalling.UpdateCallState) this.payload_).mergeFrom((Nonhfpcalling.UpdateCallState.Builder) updateCallState).mo10085buildPartial();
            } else {
                this.payload_ = updateCallState;
            }
            this.payloadCase_ = 140;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUpdateCentralNotificationAttributes(Ancs.UpdateCentralNotificationAttributes updateCentralNotificationAttributes) {
            if (this.payloadCase_ == 156 && this.payload_ != Ancs.UpdateCentralNotificationAttributes.getDefaultInstance()) {
                this.payload_ = Ancs.UpdateCentralNotificationAttributes.newBuilder((Ancs.UpdateCentralNotificationAttributes) this.payload_).mergeFrom((Ancs.UpdateCentralNotificationAttributes.Builder) updateCentralNotificationAttributes).mo10085buildPartial();
            } else {
                this.payload_ = updateCentralNotificationAttributes;
            }
            this.payloadCase_ = 156;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUpdateComponentSegment(Firmware.UpdateComponentSegment updateComponentSegment) {
            if (this.payloadCase_ == 94 && this.payload_ != Firmware.UpdateComponentSegment.getDefaultInstance()) {
                this.payload_ = Firmware.UpdateComponentSegment.newBuilder((Firmware.UpdateComponentSegment) this.payload_).mergeFrom((Firmware.UpdateComponentSegment.Builder) updateComponentSegment).mo10085buildPartial();
            } else {
                this.payload_ = updateComponentSegment;
            }
            this.payloadCase_ = 94;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUpdateDeviceInformation(Device.UpdateDeviceInformation updateDeviceInformation) {
            if (this.payloadCase_ == 26 && this.payload_ != Device.UpdateDeviceInformation.getDefaultInstance()) {
                this.payload_ = Device.UpdateDeviceInformation.newBuilder((Device.UpdateDeviceInformation) this.payload_).mergeFrom((Device.UpdateDeviceInformation.Builder) updateDeviceInformation).mo10085buildPartial();
            } else {
                this.payload_ = updateDeviceInformation;
            }
            this.payloadCase_ = 26;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUpdateMetricsMap(Metrics.UpdateMetricsMap updateMetricsMap) {
            if (this.payloadCase_ == 121 && this.payload_ != Metrics.UpdateMetricsMap.getDefaultInstance()) {
                this.payload_ = Metrics.UpdateMetricsMap.newBuilder((Metrics.UpdateMetricsMap) this.payload_).mergeFrom((Metrics.UpdateMetricsMap.Builder) updateMetricsMap).mo10085buildPartial();
            } else {
                this.payload_ = updateMetricsMap;
            }
            this.payloadCase_ = 121;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUpdateNotification(Notification.UpdateNotification updateNotification) {
            if (this.payloadCase_ == 191 && this.payload_ != Notification.UpdateNotification.getDefaultInstance()) {
                this.payload_ = Notification.UpdateNotification.newBuilder((Notification.UpdateNotification) this.payload_).mergeFrom((Notification.UpdateNotification.Builder) updateNotification).mo10085buildPartial();
            } else {
                this.payload_ = updateNotification;
            }
            this.payloadCase_ = 191;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUpdateUsers(System.UpdateUsers updateUsers) {
            if (this.payloadCase_ == 61 && this.payload_ != System.UpdateUsers.getDefaultInstance()) {
                this.payload_ = System.UpdateUsers.newBuilder((System.UpdateUsers) this.payload_).mergeFrom((System.UpdateUsers.Builder) updateUsers).mo10085buildPartial();
            } else {
                this.payload_ = updateUsers;
            }
            this.payloadCase_ = 61;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUpgradeTransport(Transport.UpgradeTransport upgradeTransport) {
            if (this.payloadCase_ == 30 && this.payload_ != Transport.UpgradeTransport.getDefaultInstance()) {
                this.payload_ = Transport.UpgradeTransport.newBuilder((Transport.UpgradeTransport) this.payload_).mergeFrom((Transport.UpgradeTransport.Builder) upgradeTransport).mo10085buildPartial();
            } else {
                this.payload_ = upgradeTransport;
            }
            this.payloadCase_ = 30;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUserConfirmed(Keyexchange.UserConfirmed userConfirmed) {
            if (this.payloadCase_ == 202 && this.payload_ != Keyexchange.UserConfirmed.getDefaultInstance()) {
                this.payload_ = Keyexchange.UserConfirmed.newBuilder((Keyexchange.UserConfirmed) this.payload_).mergeFrom((Keyexchange.UserConfirmed.Builder) userConfirmed).mo10085buildPartial();
            } else {
                this.payload_ = userConfirmed;
            }
            this.payloadCase_ = 202;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeVerifyArtifactSignature(Firmware.VerifyArtifactSignature verifyArtifactSignature) {
            if (this.payloadCase_ == 89 && this.payload_ != Firmware.VerifyArtifactSignature.getDefaultInstance()) {
                this.payload_ = Firmware.VerifyArtifactSignature.newBuilder((Firmware.VerifyArtifactSignature) this.payload_).mergeFrom((Firmware.VerifyArtifactSignature.Builder) verifyArtifactSignature).mo10085buildPartial();
            } else {
                this.payload_ = verifyArtifactSignature;
            }
            this.payloadCase_ = 89;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ControlEnvelope parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ControlEnvelope) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ControlEnvelope parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ControlEnvelope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ControlEnvelope> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAcceptCall(Nonhfpcalling.AcceptCall acceptCall) {
            if (acceptCall != null) {
                this.payload_ = acceptCall;
                this.payloadCase_ = 141;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAddNotification(Notification.AddNotification addNotification) {
            if (addNotification != null) {
                this.payload_ = addNotification;
                this.payloadCase_ = 190;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setApplyFirmware(Firmware.ApplyFirmware applyFirmware) {
            if (applyFirmware != null) {
                this.payload_ = applyFirmware;
                this.payloadCase_ = 95;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBulkDataManifestEntry(Bulkdata.BulkDataManifestEntry bulkDataManifestEntry) {
            if (bulkDataManifestEntry != null) {
                this.payload_ = bulkDataManifestEntry;
                this.payloadCase_ = 181;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBulkDataTransferComplete(Bulkdata.BulkDataTransferComplete bulkDataTransferComplete) {
            if (bulkDataTransferComplete != null) {
                this.payload_ = bulkDataTransferComplete;
                this.payloadCase_ = 184;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBulkDataTransferStart(Bulkdata.BulkDataTransferStart bulkDataTransferStart) {
            if (bulkDataTransferStart != null) {
                this.payload_ = bulkDataTransferStart;
                this.payloadCase_ = 183;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCommand(Command command) {
            if (command != null) {
                this.command_ = command.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCommandValue(int i) {
            this.command_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCompleteHandshake(Keyexchange.CompleteHandshake completeHandshake) {
            if (completeHandshake != null) {
                this.payload_ = completeHandshake;
                this.payloadCase_ = 201;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCompleteSetup(Device.CompleteSetup completeSetup) {
            if (completeSetup != null) {
                this.payload_ = completeSetup;
                this.payloadCase_ = 24;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConfirmResetKey(Keyexchange.ConfirmResetKey confirmResetKey) {
            if (confirmResetKey != null) {
                this.payload_ = confirmResetKey;
                this.payloadCase_ = 204;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnectUser(System.ConnectUser connectUser) {
            if (connectUser != null) {
                this.payload_ = connectUser;
                this.payloadCase_ = 62;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDisconnectUser(System.DisconnectUser disconnectUser) {
            if (disconnectUser != null) {
                this.payload_ = disconnectUser;
                this.payloadCase_ = 63;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDisplayContent(Cardrendering.DisplayContent displayContent) {
            if (displayContent != null) {
                this.payload_ = displayContent;
                this.payloadCase_ = 80;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEndCall(Nonhfpcalling.EndCall endCall) {
            if (endCall != null) {
                this.payload_ = endCall;
                this.payloadCase_ = 143;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEndOfSmsMessageListResponse(Mapsms.EndOfSmsMessageListResponse endOfSmsMessageListResponse) {
            if (endOfSmsMessageListResponse != null) {
                this.payload_ = endOfSmsMessageListResponse;
                this.payloadCase_ = 355;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEndpointSpeech(Speech.EndpointSpeech endpointSpeech) {
            if (endpointSpeech != null) {
                this.payload_ = endpointSpeech;
                this.payloadCase_ = 13;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExecuteNotificationAction(Notification.ExecuteNotificationAction executeNotificationAction) {
            if (executeNotificationAction != null) {
                this.payload_ = executeNotificationAction;
                this.payloadCase_ = 193;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirmwareUpdateUnavailable(Firmware.FirmwareUpdateUnavailable firmwareUpdateUnavailable) {
            if (firmwareUpdateUnavailable != null) {
                this.payload_ = firmwareUpdateUnavailable;
                this.payloadCase_ = 86;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setForwardAtCommand(Calling.ForwardATCommand forwardATCommand) {
            if (forwardATCommand != null) {
                this.payload_ = forwardATCommand;
                this.payloadCase_ = 40;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetArtifactFilter(Firmware.GetArtifactFilter getArtifactFilter) {
            if (getArtifactFilter != null) {
                this.payload_ = getArtifactFilter;
                this.payloadCase_ = 97;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetArtifactUpdatePreference(Firmware.GetArtifactUpdatePreference getArtifactUpdatePreference) {
            if (getArtifactUpdatePreference != null) {
                this.payload_ = getArtifactUpdatePreference;
                this.payloadCase_ = 98;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetAudiogram(Hearing.GetAudiogram getAudiogram) {
            if (getAudiogram != null) {
                this.payload_ = getAudiogram;
                this.payloadCase_ = 300;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetBulkDataManifest(Bulkdata.GetBulkDataManifest getBulkDataManifest) {
            if (getBulkDataManifest != null) {
                this.payload_ = getBulkDataManifest;
                this.payloadCase_ = 180;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCachedComponent(Firmware.GetCachedComponent getCachedComponent) {
            if (getCachedComponent != null) {
                this.payload_ = getCachedComponent;
                this.payloadCase_ = 92;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCblInformation(Cbl.GetCblInformation getCblInformation) {
            if (getCblInformation != null) {
                this.payload_ = getCblInformation;
                this.payloadCase_ = 231;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCblLoginState(Cbl.GetCblLoginState getCblLoginState) {
            if (getCblLoginState != null) {
                this.payload_ = getCblLoginState;
                this.payloadCase_ = 230;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCentralInformation(Central.GetCentralInformation getCentralInformation) {
            if (getCentralInformation != null) {
                this.payload_ = getCentralInformation;
                this.payloadCase_ = 103;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCentralNotificationAppAttributes(Ancs.GetCentralNotificationAppAttributes getCentralNotificationAppAttributes) {
            if (getCentralNotificationAppAttributes != null) {
                this.payload_ = getCentralNotificationAppAttributes;
                this.payloadCase_ = 154;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCentralNotificationAttributes(Ancs.GetCentralNotificationAttributes getCentralNotificationAttributes) {
            if (getCentralNotificationAttributes != null) {
                this.payload_ = getCentralNotificationAttributes;
                this.payloadCase_ = 153;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCloudPairingAttributes(Cloudpairing.GetCloudPairingAttributes getCloudPairingAttributes) {
            if (getCloudPairingAttributes != null) {
                this.payload_ = getCloudPairingAttributes;
                this.payloadCase_ = 370;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCloudPairingStatus(Cloudpairing.GetCloudPairingStatus getCloudPairingStatus) {
            if (getCloudPairingStatus != null) {
                this.payload_ = getCloudPairingStatus;
                this.payloadCase_ = 371;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCurrentUser(System.GetCurrentUser getCurrentUser) {
            if (getCurrentUser != null) {
                this.payload_ = getCurrentUser;
                this.payloadCase_ = 54;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetDeviceArtifacts(Firmware.GetDeviceArtifacts getDeviceArtifacts) {
            if (getDeviceArtifacts != null) {
                this.payload_ = getDeviceArtifacts;
                this.payloadCase_ = 96;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetDeviceConfiguration(Device.GetDeviceConfiguration getDeviceConfiguration) {
            if (getDeviceConfiguration != null) {
                this.payload_ = getDeviceConfiguration;
                this.payloadCase_ = 21;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetDeviceFeatures(Device.GetDeviceFeatures getDeviceFeatures) {
            if (getDeviceFeatures != null) {
                this.payload_ = getDeviceFeatures;
                this.payloadCase_ = 28;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetDeviceInformation(Device.GetDeviceInformation getDeviceInformation) {
            if (getDeviceInformation != null) {
                this.payload_ = getDeviceInformation;
                this.payloadCase_ = 20;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetDiagnostics(DiagnosticsOuterClass.GetDiagnostics getDiagnostics) {
            if (getDiagnostics != null) {
                this.payload_ = getDiagnostics;
                this.payloadCase_ = 110;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetFirmwareInformation(Firmware.GetFirmwareInformation getFirmwareInformation) {
            if (getFirmwareInformation != null) {
                this.payload_ = getFirmwareInformation;
                this.payloadCase_ = 90;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetFirmwareUpdatePreferences(Firmware.GetFirmwareUpdatePreferences getFirmwareUpdatePreferences) {
            if (getFirmwareUpdatePreferences != null) {
                this.payload_ = getFirmwareUpdatePreferences;
                this.payloadCase_ = 91;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetFitnessData(Fitness.GetFitnessData getFitnessData) {
            if (getFitnessData != null) {
                this.payload_ = getFitnessData;
                this.payloadCase_ = 130;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetInputBehavior(Input.GetInputBehavior getInputBehavior) {
            if (getInputBehavior != null) {
                this.payload_ = getInputBehavior;
                this.payloadCase_ = 162;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetLocales(System.GetLocales getLocales) {
            if (getLocales != null) {
                this.payload_ = getLocales;
                this.payloadCase_ = 57;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetMediaEnhancementCorrectionAmount(Hearing.GetMediaEnhancementCorrectionAmount getMediaEnhancementCorrectionAmount) {
            if (getMediaEnhancementCorrectionAmount != null) {
                this.payload_ = getMediaEnhancementCorrectionAmount;
                this.payloadCase_ = 302;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetPlaybackStatus(Media.GetPlaybackStatus getPlaybackStatus) {
            if (getPlaybackStatus != null) {
                this.payload_ = getPlaybackStatus;
                this.payloadCase_ = 65;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetSmsMessageList(Mapsms.GetSmsMessageList getSmsMessageList) {
            if (getSmsMessageList != null) {
                this.payload_ = getSmsMessageList;
                this.payloadCase_ = 352;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetSmsMessageListResponse(Mapsms.GetSmsMessageListResponse getSmsMessageListResponse) {
            if (getSmsMessageListResponse != null) {
                this.payload_ = getSmsMessageListResponse;
                this.payloadCase_ = 350;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetState(StateOuterClass.GetState getState) {
            if (getState != null) {
                this.payload_ = getState;
                this.payloadCase_ = 100;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetUsers(System.GetUsers getUsers) {
            if (getUsers != null) {
                this.payload_ = getUsers;
                this.payloadCase_ = 52;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetWakewords(System.GetWakewords getWakewords) {
            if (getWakewords != null) {
                this.payload_ = getWakewords;
                this.payloadCase_ = 71;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIncomingCall(Calling.IncomingCall incomingCall) {
            if (incomingCall != null) {
                this.payload_ = incomingCall;
                this.payloadCase_ = 41;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInitiateFirmwareUpdate(Firmware.InitiateFirmwareUpdate initiateFirmwareUpdate) {
            if (initiateFirmwareUpdate != null) {
                this.payload_ = initiateFirmwareUpdate;
                this.payloadCase_ = 88;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInitiateHandshake(Keyexchange.InitiateHandshake initiateHandshake) {
            if (initiateHandshake != null) {
                this.payload_ = initiateHandshake;
                this.payloadCase_ = 200;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInitiateMapConnection(Mapsms.InitiateMapConnection initiateMapConnection) {
            if (initiateMapConnection != null) {
                this.payload_ = initiateMapConnection;
                this.payloadCase_ = 356;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIssueInputEvent(Input.IssueInputEvent issueInputEvent) {
            if (issueInputEvent != null) {
                this.payload_ = issueInputEvent;
                this.payloadCase_ = 160;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIssueMediaControl(Media.IssueMediaControl issueMediaControl) {
            if (issueMediaControl != null) {
                this.payload_ = issueMediaControl;
                this.payloadCase_ = 60;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIssueRemoteClearPairing(Instrumentation.IssueRemoteClearPairing issueRemoteClearPairing) {
            if (issueRemoteClearPairing != null) {
                this.payload_ = issueRemoteClearPairing;
                this.payloadCase_ = 167;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIssueRemoteCommand(Instrumentation.IssueRemoteCommand issueRemoteCommand) {
            if (issueRemoteCommand != null) {
                this.payload_ = issueRemoteCommand;
                this.payloadCase_ = 164;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIssueRemoteReset(Instrumentation.IssueRemoteReset issueRemoteReset) {
            if (issueRemoteReset != null) {
                this.payload_ = issueRemoteReset;
                this.payloadCase_ = 166;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIssueRemoteRestart(Instrumentation.IssueRemoteRestart issueRemoteRestart) {
            if (issueRemoteRestart != null) {
                this.payload_ = issueRemoteRestart;
                this.payloadCase_ = 165;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKeepAlive(System.KeepAlive keepAlive) {
            if (keepAlive != null) {
                this.payload_ = keepAlive;
                this.payloadCase_ = 55;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLaunchApp(System.LaunchApp launchApp) {
            if (launchApp != null) {
                this.payload_ = launchApp;
                this.payloadCase_ = 59;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLiveFitnessData(Fitness.LiveFitnessData liveFitnessData) {
            if (liveFitnessData != null) {
                this.payload_ = liveFitnessData;
                this.payloadCase_ = 136;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMediaEventOccurred(Media.MediaEventOccurred mediaEventOccurred) {
            if (mediaEventOccurred != null) {
                this.payload_ = mediaEventOccurred;
                this.payloadCase_ = 67;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifyBulkDataAvailable(Bulkdata.NotifyBulkDataAvailable notifyBulkDataAvailable) {
            if (notifyBulkDataAvailable != null) {
                this.payload_ = notifyBulkDataAvailable;
                this.payloadCase_ = 186;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifyCblLoginState(Cbl.NotifyCblLoginState notifyCblLoginState) {
            if (notifyCblLoginState != null) {
                this.payload_ = notifyCblLoginState;
                this.payloadCase_ = 232;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifyDeviceConfiguration(Device.NotifyDeviceConfiguration notifyDeviceConfiguration) {
            if (notifyDeviceConfiguration != null) {
                this.payload_ = notifyDeviceConfiguration;
                this.payloadCase_ = 25;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifyDeviceInformation(Device.NotifyDeviceInformation notifyDeviceInformation) {
            if (notifyDeviceInformation != null) {
                this.payload_ = notifyDeviceInformation;
                this.payloadCase_ = 27;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifyDiagnosticsAvailable(DiagnosticsOuterClass.NotifyDiagnosticsAvailable notifyDiagnosticsAvailable) {
            if (notifyDiagnosticsAvailable != null) {
                this.payload_ = notifyDiagnosticsAvailable;
                this.payloadCase_ = 112;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifyFirmwareInformation(Firmware.NotifyFirmwareInformation notifyFirmwareInformation) {
            if (notifyFirmwareInformation != null) {
                this.payload_ = notifyFirmwareInformation;
                this.payloadCase_ = 87;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifyFitnessDataAvailable(Fitness.NotifyFitnessDataAvailable notifyFitnessDataAvailable) {
            if (notifyFitnessDataAvailable != null) {
                this.payload_ = notifyFitnessDataAvailable;
                this.payloadCase_ = 132;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifySmsMessageList(Mapsms.NotifySmsMessageList notifySmsMessageList) {
            if (notifySmsMessageList != null) {
                this.payload_ = notifySmsMessageList;
                this.payloadCase_ = 351;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifySpeechState(Speech.NotifySpeechState notifySpeechState) {
            if (notifySpeechState != null) {
                this.payload_ = notifySpeechState;
                this.payloadCase_ = 14;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOverrideAssistant(Device.OverrideAssistant overrideAssistant) {
            if (overrideAssistant != null) {
                this.payload_ = overrideAssistant;
                this.payloadCase_ = 22;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPerformCentralNotificationAction(Ancs.PerformCentralNotificationAction performCentralNotificationAction) {
            if (performCentralNotificationAction != null) {
                this.payload_ = performCentralNotificationAction;
                this.payloadCase_ = 155;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPrintDebug(Instrumentation.PrintDebug printDebug) {
            if (printDebug != null) {
                this.payload_ = printDebug;
                this.payloadCase_ = 163;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProvideSpeech(Speech.ProvideSpeech provideSpeech) {
            if (provideSpeech != null) {
                this.payload_ = provideSpeech;
                this.payloadCase_ = 10;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProvideTranslation(Translation.ProvideTranslation provideTranslation) {
            if (provideTranslation != null) {
                this.payload_ = provideTranslation;
                this.payloadCase_ = 171;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPublishCentralNotification(Ancs.PublishCentralNotification publishCentralNotification) {
            if (publishCentralNotification != null) {
                this.payload_ = publishCentralNotification;
                this.payloadCase_ = 152;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPushMetrics(Metrics.PushMetrics pushMetrics) {
            if (pushMetrics != null) {
                this.payload_ = pushMetrics;
                this.payloadCase_ = 120;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRegisterForMediaEvents(Media.RegisterForMediaEvents registerForMediaEvents) {
            if (registerForMediaEvents != null) {
                this.payload_ = registerForMediaEvents;
                this.payloadCase_ = 66;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRejectCall(Nonhfpcalling.RejectCall rejectCall) {
            if (rejectCall != null) {
                this.payload_ = rejectCall;
                this.payloadCase_ = 142;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRemoveCloudPairingKeys(Cloudpairing.RemoveCloudPairingKeys removeCloudPairingKeys) {
            if (removeCloudPairingKeys != null) {
                this.payload_ = removeCloudPairingKeys;
                this.payloadCase_ = 374;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRemoveDevice(System.RemoveDevice removeDevice) {
            if (removeDevice != null) {
                this.payload_ = removeDevice;
                this.payloadCase_ = 56;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRemoveNotification(Notification.RemoveNotification removeNotification) {
            if (removeNotification != null) {
                this.payload_ = removeNotification;
                this.payloadCase_ = 192;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setReplaceCloudPairingKeys(Cloudpairing.ReplaceCloudPairingKeys replaceCloudPairingKeys) {
            if (replaceCloudPairingKeys != null) {
                this.payload_ = replaceCloudPairingKeys;
                this.payloadCase_ = 373;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRequestBulkDataTransfer(Bulkdata.RequestBulkDataTransfer requestBulkDataTransfer) {
            if (requestBulkDataTransfer != null) {
                this.payload_ = requestBulkDataTransfer;
                this.payloadCase_ = 182;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResetCachedComponent(Firmware.ResetCachedComponent resetCachedComponent) {
            if (resetCachedComponent != null) {
                this.payload_ = resetCachedComponent;
                this.payloadCase_ = 93;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResetConnection(System.ResetConnection resetConnection) {
            if (resetConnection != null) {
                this.payload_ = resetConnection;
                this.payloadCase_ = 51;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResetInputBehavior(Input.ResetInputBehavior resetInputBehavior) {
            if (resetInputBehavior != null) {
                this.payload_ = resetInputBehavior;
                this.payloadCase_ = 168;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResetKey(Keyexchange.ResetKey resetKey) {
            if (resetKey != null) {
                this.payload_ = resetKey;
                this.payloadCase_ = 203;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResetRootKeys(Keyexchange.ResetRootKeys resetRootKeys) {
            if (resetRootKeys != null) {
                this.payload_ = resetRootKeys;
                this.payloadCase_ = 205;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResponse(Response response) {
            if (response != null) {
                this.payload_ = response;
                this.payloadCase_ = 9;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSendSms(Mapsms.SendSms sendSms) {
            if (sendSms != null) {
                this.payload_ = sendSms;
                this.payloadCase_ = 353;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetAudiogram(Hearing.SetAudiogram setAudiogram) {
            if (setAudiogram != null) {
                this.payload_ = setAudiogram;
                this.payloadCase_ = 301;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetCloudPairingKeys(Cloudpairing.SetCloudPairingKeys setCloudPairingKeys) {
            if (setCloudPairingKeys != null) {
                this.payload_ = setCloudPairingKeys;
                this.payloadCase_ = 372;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetInputBehavior(Input.SetInputBehavior setInputBehavior) {
            if (setInputBehavior != null) {
                this.payload_ = setInputBehavior;
                this.payloadCase_ = 161;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetLocale(System.SetLocale setLocale) {
            if (setLocale != null) {
                this.payload_ = setLocale;
                this.payloadCase_ = 58;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetMediaEnhancementCorrectionAmount(Hearing.SetMediaEnhancementCorrectionAmount setMediaEnhancementCorrectionAmount) {
            if (setMediaEnhancementCorrectionAmount != null) {
                this.payload_ = setMediaEnhancementCorrectionAmount;
                this.payloadCase_ = 303;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetSmsReadStatus(Mapsms.SetSmsReadStatus setSmsReadStatus) {
            if (setSmsReadStatus != null) {
                this.payload_ = setSmsReadStatus;
                this.payloadCase_ = 354;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetState(StateOuterClass.SetState setState) {
            if (setState != null) {
                this.payload_ = setState;
                this.payloadCase_ = 101;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetWakewords(System.SetWakewords setWakewords) {
            if (setWakewords != null) {
                this.payload_ = setWakewords;
                this.payloadCase_ = 70;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartFirmwareUpdate(Firmware.StartFirmwareUpdate startFirmwareUpdate) {
            if (startFirmwareUpdate != null) {
                this.payload_ = startFirmwareUpdate;
                this.payloadCase_ = 99;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartLiveFitnessData(Fitness.StartLiveFitnessData startLiveFitnessData) {
            if (startLiveFitnessData != null) {
                this.payload_ = startLiveFitnessData;
                this.payloadCase_ = 134;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartSetup(Device.StartSetup startSetup) {
            if (startSetup != null) {
                this.payload_ = startSetup;
                this.payloadCase_ = 23;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartSpeech(Speech.StartSpeech startSpeech) {
            if (startSpeech != null) {
                this.payload_ = startSpeech;
                this.payloadCase_ = 11;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartTranslation(Translation.StartTranslation startTranslation) {
            if (startTranslation != null) {
                this.payload_ = startTranslation;
                this.payloadCase_ = 170;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartVoiceStream(Voicestream.StartVoiceStream startVoiceStream) {
            if (startVoiceStream != null) {
                this.payload_ = startVoiceStream;
                this.payloadCase_ = 360;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStopBulkDataTransfer(Bulkdata.StopBulkDataTransfer stopBulkDataTransfer) {
            if (stopBulkDataTransfer != null) {
                this.payload_ = stopBulkDataTransfer;
                this.payloadCase_ = 185;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStopDiagnostics(DiagnosticsOuterClass.StopDiagnostics stopDiagnostics) {
            if (stopDiagnostics != null) {
                this.payload_ = stopDiagnostics;
                this.payloadCase_ = 111;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStopFitnessData(Fitness.StopFitnessData stopFitnessData) {
            if (stopFitnessData != null) {
                this.payload_ = stopFitnessData;
                this.payloadCase_ = 131;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStopLiveFitnessData(Fitness.StopLiveFitnessData stopLiveFitnessData) {
            if (stopLiveFitnessData != null) {
                this.payload_ = stopLiveFitnessData;
                this.payloadCase_ = 135;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStopSpeech(Speech.StopSpeech stopSpeech) {
            if (stopSpeech != null) {
                this.payload_ = stopSpeech;
                this.payloadCase_ = 12;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStopTranslation(Translation.StopTranslation stopTranslation) {
            if (stopTranslation != null) {
                this.payload_ = stopTranslation;
                this.payloadCase_ = 172;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStopVoiceStream(Voicestream.StopVoiceStream stopVoiceStream) {
            if (stopVoiceStream != null) {
                this.payload_ = stopVoiceStream;
                this.payloadCase_ = 361;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSubscribeNotificationCenter(Ancs.SubscribeNotificationCenter subscribeNotificationCenter) {
            if (subscribeNotificationCenter != null) {
                this.payload_ = subscribeNotificationCenter;
                this.payloadCase_ = 150;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSwitchCurrentUser(System.SwitchCurrentUser switchCurrentUser) {
            if (switchCurrentUser != null) {
                this.payload_ = switchCurrentUser;
                this.payloadCase_ = 53;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSwitchTransport(Transport.SwitchTransport switchTransport) {
            if (switchTransport != null) {
                this.payload_ = switchTransport;
                this.payloadCase_ = 31;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSyncFitnessSession(Fitness.SyncFitnessSession syncFitnessSession) {
            if (syncFitnessSession != null) {
                this.payload_ = syncFitnessSession;
                this.payloadCase_ = 133;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSynchronizeSettings(System.SynchronizeSettings synchronizeSettings) {
            if (synchronizeSettings != null) {
                this.payload_ = synchronizeSettings;
                this.payloadCase_ = 50;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSynchronizeState(StateOuterClass.SynchronizeState synchronizeState) {
            if (synchronizeState != null) {
                this.payload_ = synchronizeState;
                this.payloadCase_ = 102;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUnpairUser(System.UnpairUser unpairUser) {
            if (unpairUser != null) {
                this.payload_ = unpairUser;
                this.payloadCase_ = 64;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUnsubscribeNotificationCenter(Ancs.UnsubscribeNotificationCenter unsubscribeNotificationCenter) {
            if (unsubscribeNotificationCenter != null) {
                this.payload_ = unsubscribeNotificationCenter;
                this.payloadCase_ = 151;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateCallState(Nonhfpcalling.UpdateCallState updateCallState) {
            if (updateCallState != null) {
                this.payload_ = updateCallState;
                this.payloadCase_ = 140;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateCentralNotificationAttributes(Ancs.UpdateCentralNotificationAttributes updateCentralNotificationAttributes) {
            if (updateCentralNotificationAttributes != null) {
                this.payload_ = updateCentralNotificationAttributes;
                this.payloadCase_ = 156;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateComponentSegment(Firmware.UpdateComponentSegment updateComponentSegment) {
            if (updateComponentSegment != null) {
                this.payload_ = updateComponentSegment;
                this.payloadCase_ = 94;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateDeviceInformation(Device.UpdateDeviceInformation updateDeviceInformation) {
            if (updateDeviceInformation != null) {
                this.payload_ = updateDeviceInformation;
                this.payloadCase_ = 26;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateMetricsMap(Metrics.UpdateMetricsMap updateMetricsMap) {
            if (updateMetricsMap != null) {
                this.payload_ = updateMetricsMap;
                this.payloadCase_ = 121;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateNotification(Notification.UpdateNotification updateNotification) {
            if (updateNotification != null) {
                this.payload_ = updateNotification;
                this.payloadCase_ = 191;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateUsers(System.UpdateUsers updateUsers) {
            if (updateUsers != null) {
                this.payload_ = updateUsers;
                this.payloadCase_ = 61;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpgradeTransport(Transport.UpgradeTransport upgradeTransport) {
            if (upgradeTransport != null) {
                this.payload_ = upgradeTransport;
                this.payloadCase_ = 30;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUserConfirmed(Keyexchange.UserConfirmed userConfirmed) {
            if (userConfirmed != null) {
                this.payload_ = userConfirmed;
                this.payloadCase_ = 202;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVerifyArtifactSignature(Firmware.VerifyArtifactSignature verifyArtifactSignature) {
            if (verifyArtifactSignature != null) {
                this.payload_ = verifyArtifactSignature;
                this.payloadCase_ = 89;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            int i;
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ControlEnvelope controlEnvelope = (ControlEnvelope) obj2;
                    this.command_ = visitor.visitInt(this.command_ != 0, this.command_, controlEnvelope.command_ != 0, controlEnvelope.command_);
                    switch (controlEnvelope.getPayloadCase().ordinal()) {
                        case 0:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 9, this.payload_, controlEnvelope.payload_);
                            break;
                        case 1:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 51, this.payload_, controlEnvelope.payload_);
                            break;
                        case 2:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 50, this.payload_, controlEnvelope.payload_);
                            break;
                        case 3:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 55, this.payload_, controlEnvelope.payload_);
                            break;
                        case 4:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 56, this.payload_, controlEnvelope.payload_);
                            break;
                        case 5:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 52, this.payload_, controlEnvelope.payload_);
                            break;
                        case 6:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 61, this.payload_, controlEnvelope.payload_);
                            break;
                        case 7:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 62, this.payload_, controlEnvelope.payload_);
                            break;
                        case 8:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 63, this.payload_, controlEnvelope.payload_);
                            break;
                        case 9:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 64, this.payload_, controlEnvelope.payload_);
                            break;
                        case 10:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 54, this.payload_, controlEnvelope.payload_);
                            break;
                        case 11:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 53, this.payload_, controlEnvelope.payload_);
                            break;
                        case 12:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 57, this.payload_, controlEnvelope.payload_);
                            break;
                        case 13:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 58, this.payload_, controlEnvelope.payload_);
                            break;
                        case 14:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 59, this.payload_, controlEnvelope.payload_);
                            break;
                        case 15:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 70, this.payload_, controlEnvelope.payload_);
                            break;
                        case 16:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 71, this.payload_, controlEnvelope.payload_);
                            break;
                        case 17:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 30, this.payload_, controlEnvelope.payload_);
                            break;
                        case 18:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 31, this.payload_, controlEnvelope.payload_);
                            break;
                        case 19:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 11, this.payload_, controlEnvelope.payload_);
                            break;
                        case 20:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 10, this.payload_, controlEnvelope.payload_);
                            break;
                        case 21:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 12, this.payload_, controlEnvelope.payload_);
                            break;
                        case 22:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 13, this.payload_, controlEnvelope.payload_);
                            break;
                        case 23:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 14, this.payload_, controlEnvelope.payload_);
                            break;
                        case 24:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 40, this.payload_, controlEnvelope.payload_);
                            break;
                        case 25:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 41, this.payload_, controlEnvelope.payload_);
                            break;
                        case 26:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 103, this.payload_, controlEnvelope.payload_);
                            break;
                        case 27:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 20, this.payload_, controlEnvelope.payload_);
                            break;
                        case 28:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 21, this.payload_, controlEnvelope.payload_);
                            break;
                        case 29:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 22, this.payload_, controlEnvelope.payload_);
                            break;
                        case 30:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 23, this.payload_, controlEnvelope.payload_);
                            break;
                        case 31:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 24, this.payload_, controlEnvelope.payload_);
                            break;
                        case 32:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 25, this.payload_, controlEnvelope.payload_);
                            break;
                        case 33:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 26, this.payload_, controlEnvelope.payload_);
                            break;
                        case 34:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 27, this.payload_, controlEnvelope.payload_);
                            break;
                        case 35:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 28, this.payload_, controlEnvelope.payload_);
                            break;
                        case 36:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 110, this.payload_, controlEnvelope.payload_);
                            break;
                        case 37:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 111, this.payload_, controlEnvelope.payload_);
                            break;
                        case 38:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 112, this.payload_, controlEnvelope.payload_);
                            break;
                        case 39:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 90, this.payload_, controlEnvelope.payload_);
                            break;
                        case 40:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 92, this.payload_, controlEnvelope.payload_);
                            break;
                        case 41:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 93, this.payload_, controlEnvelope.payload_);
                            break;
                        case 42:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 94, this.payload_, controlEnvelope.payload_);
                            break;
                        case 43:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 95, this.payload_, controlEnvelope.payload_);
                            break;
                        case 44:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 91, this.payload_, controlEnvelope.payload_);
                            break;
                        case 45:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 96, this.payload_, controlEnvelope.payload_);
                            break;
                        case 46:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 97, this.payload_, controlEnvelope.payload_);
                            break;
                        case 47:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 98, this.payload_, controlEnvelope.payload_);
                            break;
                        case 48:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 99, this.payload_, controlEnvelope.payload_);
                            break;
                        case 49:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 89, this.payload_, controlEnvelope.payload_);
                            break;
                        case 50:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 88, this.payload_, controlEnvelope.payload_);
                            break;
                        case 51:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 87, this.payload_, controlEnvelope.payload_);
                            break;
                        case 52:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 86, this.payload_, controlEnvelope.payload_);
                            break;
                        case 53:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 60, this.payload_, controlEnvelope.payload_);
                            break;
                        case 54:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 65, this.payload_, controlEnvelope.payload_);
                            break;
                        case 55:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 66, this.payload_, controlEnvelope.payload_);
                            break;
                        case 56:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 67, this.payload_, controlEnvelope.payload_);
                            break;
                        case 57:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 120, this.payload_, controlEnvelope.payload_);
                            break;
                        case 58:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 121, this.payload_, controlEnvelope.payload_);
                            break;
                        case 59:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 100, this.payload_, controlEnvelope.payload_);
                            break;
                        case 60:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 101, this.payload_, controlEnvelope.payload_);
                            break;
                        case 61:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 102, this.payload_, controlEnvelope.payload_);
                            break;
                        case 62:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 130, this.payload_, controlEnvelope.payload_);
                            break;
                        case 63:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 131, this.payload_, controlEnvelope.payload_);
                            break;
                        case 64:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 132, this.payload_, controlEnvelope.payload_);
                            break;
                        case 65:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 133, this.payload_, controlEnvelope.payload_);
                            break;
                        case 66:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 134, this.payload_, controlEnvelope.payload_);
                            break;
                        case 67:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 135, this.payload_, controlEnvelope.payload_);
                            break;
                        case 68:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 136, this.payload_, controlEnvelope.payload_);
                            break;
                        case 69:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 150, this.payload_, controlEnvelope.payload_);
                            break;
                        case 70:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 151, this.payload_, controlEnvelope.payload_);
                            break;
                        case 71:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 152, this.payload_, controlEnvelope.payload_);
                            break;
                        case 72:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 153, this.payload_, controlEnvelope.payload_);
                            break;
                        case 73:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 154, this.payload_, controlEnvelope.payload_);
                            break;
                        case 74:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 155, this.payload_, controlEnvelope.payload_);
                            break;
                        case 75:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 156, this.payload_, controlEnvelope.payload_);
                            break;
                        case 76:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 160, this.payload_, controlEnvelope.payload_);
                            break;
                        case 77:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 161, this.payload_, controlEnvelope.payload_);
                            break;
                        case 78:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 162, this.payload_, controlEnvelope.payload_);
                            break;
                        case 79:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 168, this.payload_, controlEnvelope.payload_);
                            break;
                        case 80:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 163, this.payload_, controlEnvelope.payload_);
                            break;
                        case 81:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 164, this.payload_, controlEnvelope.payload_);
                            break;
                        case 82:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 165, this.payload_, controlEnvelope.payload_);
                            break;
                        case 83:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 166, this.payload_, controlEnvelope.payload_);
                            break;
                        case 84:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 167, this.payload_, controlEnvelope.payload_);
                            break;
                        case 85:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 170, this.payload_, controlEnvelope.payload_);
                            break;
                        case 86:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 171, this.payload_, controlEnvelope.payload_);
                            break;
                        case 87:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 172, this.payload_, controlEnvelope.payload_);
                            break;
                        case 88:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 180, this.payload_, controlEnvelope.payload_);
                            break;
                        case 89:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 181, this.payload_, controlEnvelope.payload_);
                            break;
                        case 90:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 182, this.payload_, controlEnvelope.payload_);
                            break;
                        case 91:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 183, this.payload_, controlEnvelope.payload_);
                            break;
                        case 92:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 184, this.payload_, controlEnvelope.payload_);
                            break;
                        case 93:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 185, this.payload_, controlEnvelope.payload_);
                            break;
                        case 94:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 186, this.payload_, controlEnvelope.payload_);
                            break;
                        case 95:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 200, this.payload_, controlEnvelope.payload_);
                            break;
                        case 96:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 201, this.payload_, controlEnvelope.payload_);
                            break;
                        case 97:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 202, this.payload_, controlEnvelope.payload_);
                            break;
                        case 98:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 203, this.payload_, controlEnvelope.payload_);
                            break;
                        case 99:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 204, this.payload_, controlEnvelope.payload_);
                            break;
                        case 100:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 205, this.payload_, controlEnvelope.payload_);
                            break;
                        case 101:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 300, this.payload_, controlEnvelope.payload_);
                            break;
                        case 102:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 301, this.payload_, controlEnvelope.payload_);
                            break;
                        case 103:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 302, this.payload_, controlEnvelope.payload_);
                            break;
                        case 104:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 303, this.payload_, controlEnvelope.payload_);
                            break;
                        case 105:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 230, this.payload_, controlEnvelope.payload_);
                            break;
                        case 106:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 231, this.payload_, controlEnvelope.payload_);
                            break;
                        case 107:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 232, this.payload_, controlEnvelope.payload_);
                            break;
                        case 108:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 350, this.payload_, controlEnvelope.payload_);
                            break;
                        case 109:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 351, this.payload_, controlEnvelope.payload_);
                            break;
                        case 110:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 352, this.payload_, controlEnvelope.payload_);
                            break;
                        case 111:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 353, this.payload_, controlEnvelope.payload_);
                            break;
                        case 112:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 354, this.payload_, controlEnvelope.payload_);
                            break;
                        case 113:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 355, this.payload_, controlEnvelope.payload_);
                            break;
                        case 114:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 356, this.payload_, controlEnvelope.payload_);
                            break;
                        case 115:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 360, this.payload_, controlEnvelope.payload_);
                            break;
                        case 116:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 361, this.payload_, controlEnvelope.payload_);
                            break;
                        case 117:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 80, this.payload_, controlEnvelope.payload_);
                            break;
                        case 118:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 140, this.payload_, controlEnvelope.payload_);
                            break;
                        case 119:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 141, this.payload_, controlEnvelope.payload_);
                            break;
                        case 120:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 142, this.payload_, controlEnvelope.payload_);
                            break;
                        case 121:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 143, this.payload_, controlEnvelope.payload_);
                            break;
                        case 122:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 190, this.payload_, controlEnvelope.payload_);
                            break;
                        case 123:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 191, this.payload_, controlEnvelope.payload_);
                            break;
                        case 124:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 192, this.payload_, controlEnvelope.payload_);
                            break;
                        case 125:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 193, this.payload_, controlEnvelope.payload_);
                            break;
                        case 126:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 370, this.payload_, controlEnvelope.payload_);
                            break;
                        case 127:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 371, this.payload_, controlEnvelope.payload_);
                            break;
                        case 128:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 372, this.payload_, controlEnvelope.payload_);
                            break;
                        case 129:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 373, this.payload_, controlEnvelope.payload_);
                            break;
                        case 130:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 374, this.payload_, controlEnvelope.payload_);
                            break;
                        case 131:
                            visitor.visitOneofNotSet(this.payloadCase_ != 0);
                            break;
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE && (i = controlEnvelope.payloadCase_) != 0) {
                        this.payloadCase_ = i;
                    }
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    if (extensionRegistryLite == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            switch (readTag) {
                                case 0:
                                    break;
                                case 8:
                                    this.command_ = codedInputStream.readEnum();
                                    continue;
                                case 74:
                                    Response.Builder mo10081toBuilder = this.payloadCase_ == 9 ? ((Response) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Response.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((Response.Builder) ((Response) this.payload_));
                                        this.payload_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 9;
                                    continue;
                                case 82:
                                    Speech.ProvideSpeech.Builder mo10081toBuilder2 = this.payloadCase_ == 10 ? ((Speech.ProvideSpeech) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Speech.ProvideSpeech.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder2 != null) {
                                        mo10081toBuilder2.mergeFrom((Speech.ProvideSpeech.Builder) ((Speech.ProvideSpeech) this.payload_));
                                        this.payload_ = mo10081toBuilder2.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 10;
                                    continue;
                                case 90:
                                    Speech.StartSpeech.Builder mo10081toBuilder3 = this.payloadCase_ == 11 ? ((Speech.StartSpeech) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Speech.StartSpeech.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder3 != null) {
                                        mo10081toBuilder3.mergeFrom((Speech.StartSpeech.Builder) ((Speech.StartSpeech) this.payload_));
                                        this.payload_ = mo10081toBuilder3.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 11;
                                    continue;
                                case 98:
                                    Speech.StopSpeech.Builder mo10081toBuilder4 = this.payloadCase_ == 12 ? ((Speech.StopSpeech) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Speech.StopSpeech.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder4 != null) {
                                        mo10081toBuilder4.mergeFrom((Speech.StopSpeech.Builder) ((Speech.StopSpeech) this.payload_));
                                        this.payload_ = mo10081toBuilder4.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 12;
                                    continue;
                                case 106:
                                    Speech.EndpointSpeech.Builder mo10081toBuilder5 = this.payloadCase_ == 13 ? ((Speech.EndpointSpeech) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Speech.EndpointSpeech.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder5 != null) {
                                        mo10081toBuilder5.mergeFrom((Speech.EndpointSpeech.Builder) ((Speech.EndpointSpeech) this.payload_));
                                        this.payload_ = mo10081toBuilder5.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 13;
                                    continue;
                                case 114:
                                    Speech.NotifySpeechState.Builder mo10081toBuilder6 = this.payloadCase_ == 14 ? ((Speech.NotifySpeechState) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Speech.NotifySpeechState.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder6 != null) {
                                        mo10081toBuilder6.mergeFrom((Speech.NotifySpeechState.Builder) ((Speech.NotifySpeechState) this.payload_));
                                        this.payload_ = mo10081toBuilder6.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 14;
                                    continue;
                                case 162:
                                    Device.GetDeviceInformation.Builder mo10081toBuilder7 = this.payloadCase_ == 20 ? ((Device.GetDeviceInformation) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Device.GetDeviceInformation.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder7 != null) {
                                        mo10081toBuilder7.mergeFrom((Device.GetDeviceInformation.Builder) ((Device.GetDeviceInformation) this.payload_));
                                        this.payload_ = mo10081toBuilder7.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 20;
                                    continue;
                                case 170:
                                    Device.GetDeviceConfiguration.Builder mo10081toBuilder8 = this.payloadCase_ == 21 ? ((Device.GetDeviceConfiguration) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Device.GetDeviceConfiguration.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder8 != null) {
                                        mo10081toBuilder8.mergeFrom((Device.GetDeviceConfiguration.Builder) ((Device.GetDeviceConfiguration) this.payload_));
                                        this.payload_ = mo10081toBuilder8.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 21;
                                    continue;
                                case 178:
                                    Device.OverrideAssistant.Builder mo10081toBuilder9 = this.payloadCase_ == 22 ? ((Device.OverrideAssistant) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Device.OverrideAssistant.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder9 != null) {
                                        mo10081toBuilder9.mergeFrom((Device.OverrideAssistant.Builder) ((Device.OverrideAssistant) this.payload_));
                                        this.payload_ = mo10081toBuilder9.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 22;
                                    continue;
                                case 186:
                                    Device.StartSetup.Builder mo10081toBuilder10 = this.payloadCase_ == 23 ? ((Device.StartSetup) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Device.StartSetup.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder10 != null) {
                                        mo10081toBuilder10.mergeFrom((Device.StartSetup.Builder) ((Device.StartSetup) this.payload_));
                                        this.payload_ = mo10081toBuilder10.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 23;
                                    continue;
                                case 194:
                                    Device.CompleteSetup.Builder mo10081toBuilder11 = this.payloadCase_ == 24 ? ((Device.CompleteSetup) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Device.CompleteSetup.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder11 != null) {
                                        mo10081toBuilder11.mergeFrom((Device.CompleteSetup.Builder) ((Device.CompleteSetup) this.payload_));
                                        this.payload_ = mo10081toBuilder11.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 24;
                                    continue;
                                case 202:
                                    Device.NotifyDeviceConfiguration.Builder mo10081toBuilder12 = this.payloadCase_ == 25 ? ((Device.NotifyDeviceConfiguration) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Device.NotifyDeviceConfiguration.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder12 != null) {
                                        mo10081toBuilder12.mergeFrom((Device.NotifyDeviceConfiguration.Builder) ((Device.NotifyDeviceConfiguration) this.payload_));
                                        this.payload_ = mo10081toBuilder12.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 25;
                                    continue;
                                case 210:
                                    Device.UpdateDeviceInformation.Builder mo10081toBuilder13 = this.payloadCase_ == 26 ? ((Device.UpdateDeviceInformation) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Device.UpdateDeviceInformation.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder13 != null) {
                                        mo10081toBuilder13.mergeFrom((Device.UpdateDeviceInformation.Builder) ((Device.UpdateDeviceInformation) this.payload_));
                                        this.payload_ = mo10081toBuilder13.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 26;
                                    continue;
                                case JfifUtil.MARKER_SOS /* 218 */:
                                    Device.NotifyDeviceInformation.Builder mo10081toBuilder14 = this.payloadCase_ == 27 ? ((Device.NotifyDeviceInformation) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Device.NotifyDeviceInformation.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder14 != null) {
                                        mo10081toBuilder14.mergeFrom((Device.NotifyDeviceInformation.Builder) ((Device.NotifyDeviceInformation) this.payload_));
                                        this.payload_ = mo10081toBuilder14.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 27;
                                    continue;
                                case 226:
                                    Device.GetDeviceFeatures.Builder mo10081toBuilder15 = this.payloadCase_ == 28 ? ((Device.GetDeviceFeatures) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Device.GetDeviceFeatures.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder15 != null) {
                                        mo10081toBuilder15.mergeFrom((Device.GetDeviceFeatures.Builder) ((Device.GetDeviceFeatures) this.payload_));
                                        this.payload_ = mo10081toBuilder15.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 28;
                                    continue;
                                case 242:
                                    Transport.UpgradeTransport.Builder mo10081toBuilder16 = this.payloadCase_ == 30 ? ((Transport.UpgradeTransport) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Transport.UpgradeTransport.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder16 != null) {
                                        mo10081toBuilder16.mergeFrom((Transport.UpgradeTransport.Builder) ((Transport.UpgradeTransport) this.payload_));
                                        this.payload_ = mo10081toBuilder16.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 30;
                                    continue;
                                case 250:
                                    Transport.SwitchTransport.Builder mo10081toBuilder17 = this.payloadCase_ == 31 ? ((Transport.SwitchTransport) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Transport.SwitchTransport.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder17 != null) {
                                        mo10081toBuilder17.mergeFrom((Transport.SwitchTransport.Builder) ((Transport.SwitchTransport) this.payload_));
                                        this.payload_ = mo10081toBuilder17.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 31;
                                    continue;
                                case ExifDirectoryBase.TAG_TILE_WIDTH /* 322 */:
                                    Calling.ForwardATCommand.Builder mo10081toBuilder18 = this.payloadCase_ == 40 ? ((Calling.ForwardATCommand) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Calling.ForwardATCommand.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder18 != null) {
                                        mo10081toBuilder18.mergeFrom((Calling.ForwardATCommand.Builder) ((Calling.ForwardATCommand) this.payload_));
                                        this.payload_ = mo10081toBuilder18.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 40;
                                    continue;
                                case ExifDirectoryBase.TAG_SUB_IFD_OFFSET /* 330 */:
                                    Calling.IncomingCall.Builder mo10081toBuilder19 = this.payloadCase_ == 41 ? ((Calling.IncomingCall) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Calling.IncomingCall.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder19 != null) {
                                        mo10081toBuilder19.mergeFrom((Calling.IncomingCall.Builder) ((Calling.IncomingCall) this.payload_));
                                        this.payload_ = mo10081toBuilder19.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 41;
                                    continue;
                                case HttpServletResponse.SC_PAYMENT_REQUIRED /* 402 */:
                                    System.SynchronizeSettings.Builder mo10081toBuilder20 = this.payloadCase_ == 50 ? ((System.SynchronizeSettings) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.SynchronizeSettings.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder20 != null) {
                                        mo10081toBuilder20.mergeFrom((System.SynchronizeSettings.Builder) ((System.SynchronizeSettings) this.payload_));
                                        this.payload_ = mo10081toBuilder20.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 50;
                                    continue;
                                case HttpServletResponse.SC_GONE /* 410 */:
                                    System.ResetConnection.Builder mo10081toBuilder21 = this.payloadCase_ == 51 ? ((System.ResetConnection) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.ResetConnection.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder21 != null) {
                                        mo10081toBuilder21.mergeFrom((System.ResetConnection.Builder) ((System.ResetConnection) this.payload_));
                                        this.payload_ = mo10081toBuilder21.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 51;
                                    continue;
                                case 418:
                                    System.GetUsers.Builder mo10081toBuilder22 = this.payloadCase_ == 52 ? ((System.GetUsers) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.GetUsers.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder22 != null) {
                                        mo10081toBuilder22.mergeFrom((System.GetUsers.Builder) ((System.GetUsers) this.payload_));
                                        this.payload_ = mo10081toBuilder22.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 52;
                                    continue;
                                case 426:
                                    System.SwitchCurrentUser.Builder mo10081toBuilder23 = this.payloadCase_ == 53 ? ((System.SwitchCurrentUser) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.SwitchCurrentUser.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder23 != null) {
                                        mo10081toBuilder23.mergeFrom((System.SwitchCurrentUser.Builder) ((System.SwitchCurrentUser) this.payload_));
                                        this.payload_ = mo10081toBuilder23.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 53;
                                    continue;
                                case 434:
                                    System.GetCurrentUser.Builder mo10081toBuilder24 = this.payloadCase_ == 54 ? ((System.GetCurrentUser) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.GetCurrentUser.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder24 != null) {
                                        mo10081toBuilder24.mergeFrom((System.GetCurrentUser.Builder) ((System.GetCurrentUser) this.payload_));
                                        this.payload_ = mo10081toBuilder24.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 54;
                                    continue;
                                case 442:
                                    System.KeepAlive.Builder mo10081toBuilder25 = this.payloadCase_ == 55 ? ((System.KeepAlive) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.KeepAlive.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder25 != null) {
                                        mo10081toBuilder25.mergeFrom((System.KeepAlive.Builder) ((System.KeepAlive) this.payload_));
                                        this.payload_ = mo10081toBuilder25.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 55;
                                    continue;
                                case 450:
                                    System.RemoveDevice.Builder mo10081toBuilder26 = this.payloadCase_ == 56 ? ((System.RemoveDevice) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.RemoveDevice.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder26 != null) {
                                        mo10081toBuilder26.mergeFrom((System.RemoveDevice.Builder) ((System.RemoveDevice) this.payload_));
                                        this.payload_ = mo10081toBuilder26.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 56;
                                    continue;
                                case 458:
                                    System.GetLocales.Builder mo10081toBuilder27 = this.payloadCase_ == 57 ? ((System.GetLocales) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.GetLocales.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder27 != null) {
                                        mo10081toBuilder27.mergeFrom((System.GetLocales.Builder) ((System.GetLocales) this.payload_));
                                        this.payload_ = mo10081toBuilder27.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 57;
                                    continue;
                                case 466:
                                    System.SetLocale.Builder mo10081toBuilder28 = this.payloadCase_ == 58 ? ((System.SetLocale) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.SetLocale.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder28 != null) {
                                        mo10081toBuilder28.mergeFrom((System.SetLocale.Builder) ((System.SetLocale) this.payload_));
                                        this.payload_ = mo10081toBuilder28.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 58;
                                    continue;
                                case 474:
                                    System.LaunchApp.Builder mo10081toBuilder29 = this.payloadCase_ == 59 ? ((System.LaunchApp) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.LaunchApp.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder29 != null) {
                                        mo10081toBuilder29.mergeFrom((System.LaunchApp.Builder) ((System.LaunchApp) this.payload_));
                                        this.payload_ = mo10081toBuilder29.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 59;
                                    continue;
                                case 482:
                                    Media.IssueMediaControl.Builder mo10081toBuilder30 = this.payloadCase_ == 60 ? ((Media.IssueMediaControl) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Media.IssueMediaControl.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder30 != null) {
                                        mo10081toBuilder30.mergeFrom((Media.IssueMediaControl.Builder) ((Media.IssueMediaControl) this.payload_));
                                        this.payload_ = mo10081toBuilder30.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 60;
                                    continue;
                                case 490:
                                    System.UpdateUsers.Builder mo10081toBuilder31 = this.payloadCase_ == 61 ? ((System.UpdateUsers) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.UpdateUsers.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder31 != null) {
                                        mo10081toBuilder31.mergeFrom((System.UpdateUsers.Builder) ((System.UpdateUsers) this.payload_));
                                        this.payload_ = mo10081toBuilder31.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 61;
                                    continue;
                                case 498:
                                    System.ConnectUser.Builder mo10081toBuilder32 = this.payloadCase_ == 62 ? ((System.ConnectUser) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.ConnectUser.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder32 != null) {
                                        mo10081toBuilder32.mergeFrom((System.ConnectUser.Builder) ((System.ConnectUser) this.payload_));
                                        this.payload_ = mo10081toBuilder32.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 62;
                                    continue;
                                case 506:
                                    System.DisconnectUser.Builder mo10081toBuilder33 = this.payloadCase_ == 63 ? ((System.DisconnectUser) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.DisconnectUser.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder33 != null) {
                                        mo10081toBuilder33.mergeFrom((System.DisconnectUser.Builder) ((System.DisconnectUser) this.payload_));
                                        this.payload_ = mo10081toBuilder33.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 63;
                                    continue;
                                case 514:
                                    System.UnpairUser.Builder mo10081toBuilder34 = this.payloadCase_ == 64 ? ((System.UnpairUser) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.UnpairUser.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder34 != null) {
                                        mo10081toBuilder34.mergeFrom((System.UnpairUser.Builder) ((System.UnpairUser) this.payload_));
                                        this.payload_ = mo10081toBuilder34.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 64;
                                    continue;
                                case 522:
                                    Media.GetPlaybackStatus.Builder mo10081toBuilder35 = this.payloadCase_ == 65 ? ((Media.GetPlaybackStatus) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Media.GetPlaybackStatus.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder35 != null) {
                                        mo10081toBuilder35.mergeFrom((Media.GetPlaybackStatus.Builder) ((Media.GetPlaybackStatus) this.payload_));
                                        this.payload_ = mo10081toBuilder35.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 65;
                                    continue;
                                case 530:
                                    Media.RegisterForMediaEvents.Builder mo10081toBuilder36 = this.payloadCase_ == 66 ? ((Media.RegisterForMediaEvents) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Media.RegisterForMediaEvents.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder36 != null) {
                                        mo10081toBuilder36.mergeFrom((Media.RegisterForMediaEvents.Builder) ((Media.RegisterForMediaEvents) this.payload_));
                                        this.payload_ = mo10081toBuilder36.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 66;
                                    continue;
                                case IptcDirectory.TAG_CONTENT_LOCATION_CODE /* 538 */:
                                    Media.MediaEventOccurred.Builder mo10081toBuilder37 = this.payloadCase_ == 67 ? ((Media.MediaEventOccurred) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Media.MediaEventOccurred.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder37 != null) {
                                        mo10081toBuilder37.mergeFrom((Media.MediaEventOccurred.Builder) ((Media.MediaEventOccurred) this.payload_));
                                        this.payload_ = mo10081toBuilder37.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 67;
                                    continue;
                                case IptcDirectory.TAG_REFERENCE_NUMBER /* 562 */:
                                    System.SetWakewords.Builder mo10081toBuilder38 = this.payloadCase_ == 70 ? ((System.SetWakewords) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.SetWakewords.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder38 != null) {
                                        mo10081toBuilder38.mergeFrom((System.SetWakewords.Builder) ((System.SetWakewords) this.payload_));
                                        this.payload_ = mo10081toBuilder38.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 70;
                                    continue;
                                case 570:
                                    System.GetWakewords.Builder mo10081toBuilder39 = this.payloadCase_ == 71 ? ((System.GetWakewords) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(System.GetWakewords.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder39 != null) {
                                        mo10081toBuilder39.mergeFrom((System.GetWakewords.Builder) ((System.GetWakewords) this.payload_));
                                        this.payload_ = mo10081toBuilder39.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 71;
                                    continue;
                                case IptcDirectory.TAG_IMAGE_TYPE /* 642 */:
                                    Cardrendering.DisplayContent.Builder mo10081toBuilder40 = this.payloadCase_ == 80 ? ((Cardrendering.DisplayContent) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Cardrendering.DisplayContent.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder40 != null) {
                                        mo10081toBuilder40.mergeFrom((Cardrendering.DisplayContent.Builder) ((Cardrendering.DisplayContent) this.payload_));
                                        this.payload_ = mo10081toBuilder40.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 80;
                                    continue;
                                case 690:
                                    Firmware.FirmwareUpdateUnavailable.Builder mo10081toBuilder41 = this.payloadCase_ == 86 ? ((Firmware.FirmwareUpdateUnavailable) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Firmware.FirmwareUpdateUnavailable.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder41 != null) {
                                        mo10081toBuilder41.mergeFrom((Firmware.FirmwareUpdateUnavailable.Builder) ((Firmware.FirmwareUpdateUnavailable) this.payload_));
                                        this.payload_ = mo10081toBuilder41.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 86;
                                    continue;
                                case IptcDirectory.TAG_SHORT_DOCUMENT_ID /* 698 */:
                                    Firmware.NotifyFirmwareInformation.Builder mo10081toBuilder42 = this.payloadCase_ == 87 ? ((Firmware.NotifyFirmwareInformation) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Firmware.NotifyFirmwareInformation.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder42 != null) {
                                        mo10081toBuilder42.mergeFrom((Firmware.NotifyFirmwareInformation.Builder) ((Firmware.NotifyFirmwareInformation) this.payload_));
                                        this.payload_ = mo10081toBuilder42.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 87;
                                    continue;
                                case 706:
                                    Firmware.InitiateFirmwareUpdate.Builder mo10081toBuilder43 = this.payloadCase_ == 88 ? ((Firmware.InitiateFirmwareUpdate) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Firmware.InitiateFirmwareUpdate.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder43 != null) {
                                        mo10081toBuilder43.mergeFrom((Firmware.InitiateFirmwareUpdate.Builder) ((Firmware.InitiateFirmwareUpdate) this.payload_));
                                        this.payload_ = mo10081toBuilder43.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 88;
                                    continue;
                                case IptcDirectory.TAG_OBJECT_PREVIEW_DATA /* 714 */:
                                    Firmware.VerifyArtifactSignature.Builder mo10081toBuilder44 = this.payloadCase_ == 89 ? ((Firmware.VerifyArtifactSignature) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Firmware.VerifyArtifactSignature.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder44 != null) {
                                        mo10081toBuilder44.mergeFrom((Firmware.VerifyArtifactSignature.Builder) ((Firmware.VerifyArtifactSignature) this.payload_));
                                        this.payload_ = mo10081toBuilder44.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 89;
                                    continue;
                                case 722:
                                    Firmware.GetFirmwareInformation.Builder mo10081toBuilder45 = this.payloadCase_ == 90 ? ((Firmware.GetFirmwareInformation) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Firmware.GetFirmwareInformation.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder45 != null) {
                                        mo10081toBuilder45.mergeFrom((Firmware.GetFirmwareInformation.Builder) ((Firmware.GetFirmwareInformation) this.payload_));
                                        this.payload_ = mo10081toBuilder45.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 90;
                                    continue;
                                case 730:
                                    Firmware.GetFirmwareUpdatePreferences.Builder mo10081toBuilder46 = this.payloadCase_ == 91 ? ((Firmware.GetFirmwareUpdatePreferences) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Firmware.GetFirmwareUpdatePreferences.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder46 != null) {
                                        mo10081toBuilder46.mergeFrom((Firmware.GetFirmwareUpdatePreferences.Builder) ((Firmware.GetFirmwareUpdatePreferences) this.payload_));
                                        this.payload_ = mo10081toBuilder46.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 91;
                                    continue;
                                case 738:
                                    Firmware.GetCachedComponent.Builder mo10081toBuilder47 = this.payloadCase_ == 92 ? ((Firmware.GetCachedComponent) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Firmware.GetCachedComponent.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder47 != null) {
                                        mo10081toBuilder47.mergeFrom((Firmware.GetCachedComponent.Builder) ((Firmware.GetCachedComponent) this.payload_));
                                        this.payload_ = mo10081toBuilder47.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 92;
                                    continue;
                                case 746:
                                    Firmware.ResetCachedComponent.Builder mo10081toBuilder48 = this.payloadCase_ == 93 ? ((Firmware.ResetCachedComponent) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Firmware.ResetCachedComponent.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder48 != null) {
                                        mo10081toBuilder48.mergeFrom((Firmware.ResetCachedComponent.Builder) ((Firmware.ResetCachedComponent) this.payload_));
                                        this.payload_ = mo10081toBuilder48.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 93;
                                    continue;
                                case 754:
                                    Firmware.UpdateComponentSegment.Builder mo10081toBuilder49 = this.payloadCase_ == 94 ? ((Firmware.UpdateComponentSegment) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Firmware.UpdateComponentSegment.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder49 != null) {
                                        mo10081toBuilder49.mergeFrom((Firmware.UpdateComponentSegment.Builder) ((Firmware.UpdateComponentSegment) this.payload_));
                                        this.payload_ = mo10081toBuilder49.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 94;
                                    continue;
                                case 762:
                                    Firmware.ApplyFirmware.Builder mo10081toBuilder50 = this.payloadCase_ == 95 ? ((Firmware.ApplyFirmware) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Firmware.ApplyFirmware.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder50 != null) {
                                        mo10081toBuilder50.mergeFrom((Firmware.ApplyFirmware.Builder) ((Firmware.ApplyFirmware) this.payload_));
                                        this.payload_ = mo10081toBuilder50.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 95;
                                    continue;
                                case 770:
                                    Firmware.GetDeviceArtifacts.Builder mo10081toBuilder51 = this.payloadCase_ == 96 ? ((Firmware.GetDeviceArtifacts) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Firmware.GetDeviceArtifacts.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder51 != null) {
                                        mo10081toBuilder51.mergeFrom((Firmware.GetDeviceArtifacts.Builder) ((Firmware.GetDeviceArtifacts) this.payload_));
                                        this.payload_ = mo10081toBuilder51.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 96;
                                    continue;
                                case 778:
                                    Firmware.GetArtifactFilter.Builder mo10081toBuilder52 = this.payloadCase_ == 97 ? ((Firmware.GetArtifactFilter) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Firmware.GetArtifactFilter.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder52 != null) {
                                        mo10081toBuilder52.mergeFrom((Firmware.GetArtifactFilter.Builder) ((Firmware.GetArtifactFilter) this.payload_));
                                        this.payload_ = mo10081toBuilder52.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 97;
                                    continue;
                                case LeicaMakernoteDirectory.TAG_MEASURED_LV /* 786 */:
                                    Firmware.GetArtifactUpdatePreference.Builder mo10081toBuilder53 = this.payloadCase_ == 98 ? ((Firmware.GetArtifactUpdatePreference) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Firmware.GetArtifactUpdatePreference.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder53 != null) {
                                        mo10081toBuilder53.mergeFrom((Firmware.GetArtifactUpdatePreference.Builder) ((Firmware.GetArtifactUpdatePreference) this.payload_));
                                        this.payload_ = mo10081toBuilder53.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 98;
                                    continue;
                                case 794:
                                    Firmware.StartFirmwareUpdate.Builder mo10081toBuilder54 = this.payloadCase_ == 99 ? ((Firmware.StartFirmwareUpdate) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Firmware.StartFirmwareUpdate.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder54 != null) {
                                        mo10081toBuilder54.mergeFrom((Firmware.StartFirmwareUpdate.Builder) ((Firmware.StartFirmwareUpdate) this.payload_));
                                        this.payload_ = mo10081toBuilder54.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 99;
                                    continue;
                                case LeicaMakernoteDirectory.TAG_WB_RED_LEVEL /* 802 */:
                                    StateOuterClass.GetState.Builder mo10081toBuilder55 = this.payloadCase_ == 100 ? ((StateOuterClass.GetState) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(StateOuterClass.GetState.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder55 != null) {
                                        mo10081toBuilder55.mergeFrom((StateOuterClass.GetState.Builder) ((StateOuterClass.GetState) this.payload_));
                                        this.payload_ = mo10081toBuilder55.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 100;
                                    continue;
                                case 810:
                                    StateOuterClass.SetState.Builder mo10081toBuilder56 = this.payloadCase_ == 101 ? ((StateOuterClass.SetState) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(StateOuterClass.SetState.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder56 != null) {
                                        mo10081toBuilder56.mergeFrom((StateOuterClass.SetState.Builder) ((StateOuterClass.SetState) this.payload_));
                                        this.payload_ = mo10081toBuilder56.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 101;
                                    continue;
                                case LeicaMakernoteDirectory.TAG_CONTROLLER_BOARD_VERSION /* 818 */:
                                    StateOuterClass.SynchronizeState.Builder mo10081toBuilder57 = this.payloadCase_ == 102 ? ((StateOuterClass.SynchronizeState) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(StateOuterClass.SynchronizeState.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder57 != null) {
                                        mo10081toBuilder57.mergeFrom((StateOuterClass.SynchronizeState.Builder) ((StateOuterClass.SynchronizeState) this.payload_));
                                        this.payload_ = mo10081toBuilder57.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 102;
                                    continue;
                                case 826:
                                    Central.GetCentralInformation.Builder mo10081toBuilder58 = this.payloadCase_ == 103 ? ((Central.GetCentralInformation) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Central.GetCentralInformation.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder58 != null) {
                                        mo10081toBuilder58.mergeFrom((Central.GetCentralInformation.Builder) ((Central.GetCentralInformation) this.payload_));
                                        this.payload_ = mo10081toBuilder58.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 103;
                                    continue;
                                case 882:
                                    DiagnosticsOuterClass.GetDiagnostics.Builder mo10081toBuilder59 = this.payloadCase_ == 110 ? ((DiagnosticsOuterClass.GetDiagnostics) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(DiagnosticsOuterClass.GetDiagnostics.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder59 != null) {
                                        mo10081toBuilder59.mergeFrom((DiagnosticsOuterClass.GetDiagnostics.Builder) ((DiagnosticsOuterClass.GetDiagnostics) this.payload_));
                                        this.payload_ = mo10081toBuilder59.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 110;
                                    continue;
                                case 890:
                                    DiagnosticsOuterClass.StopDiagnostics.Builder mo10081toBuilder60 = this.payloadCase_ == 111 ? ((DiagnosticsOuterClass.StopDiagnostics) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(DiagnosticsOuterClass.StopDiagnostics.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder60 != null) {
                                        mo10081toBuilder60.mergeFrom((DiagnosticsOuterClass.StopDiagnostics.Builder) ((DiagnosticsOuterClass.StopDiagnostics) this.payload_));
                                        this.payload_ = mo10081toBuilder60.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 111;
                                    continue;
                                case 898:
                                    DiagnosticsOuterClass.NotifyDiagnosticsAvailable.Builder mo10081toBuilder61 = this.payloadCase_ == 112 ? ((DiagnosticsOuterClass.NotifyDiagnosticsAvailable) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(DiagnosticsOuterClass.NotifyDiagnosticsAvailable.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder61 != null) {
                                        mo10081toBuilder61.mergeFrom((DiagnosticsOuterClass.NotifyDiagnosticsAvailable.Builder) ((DiagnosticsOuterClass.NotifyDiagnosticsAvailable) this.payload_));
                                        this.payload_ = mo10081toBuilder61.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 112;
                                    continue;
                                case 962:
                                    Metrics.PushMetrics.Builder mo10081toBuilder62 = this.payloadCase_ == 120 ? ((Metrics.PushMetrics) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Metrics.PushMetrics.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder62 != null) {
                                        mo10081toBuilder62.mergeFrom((Metrics.PushMetrics.Builder) ((Metrics.PushMetrics) this.payload_));
                                        this.payload_ = mo10081toBuilder62.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 120;
                                    continue;
                                case 970:
                                    Metrics.UpdateMetricsMap.Builder mo10081toBuilder63 = this.payloadCase_ == 121 ? ((Metrics.UpdateMetricsMap) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Metrics.UpdateMetricsMap.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder63 != null) {
                                        mo10081toBuilder63.mergeFrom((Metrics.UpdateMetricsMap.Builder) ((Metrics.UpdateMetricsMap) this.payload_));
                                        this.payload_ = mo10081toBuilder63.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 121;
                                    continue;
                                case 1042:
                                    Fitness.GetFitnessData.Builder mo10081toBuilder64 = this.payloadCase_ == 130 ? ((Fitness.GetFitnessData) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Fitness.GetFitnessData.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder64 != null) {
                                        mo10081toBuilder64.mergeFrom((Fitness.GetFitnessData.Builder) ((Fitness.GetFitnessData) this.payload_));
                                        this.payload_ = mo10081toBuilder64.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 130;
                                    continue;
                                case PhotoshopDirectory.TAG_SLICES /* 1050 */:
                                    Fitness.StopFitnessData.Builder mo10081toBuilder65 = this.payloadCase_ == 131 ? ((Fitness.StopFitnessData) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Fitness.StopFitnessData.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder65 != null) {
                                        mo10081toBuilder65.mergeFrom((Fitness.StopFitnessData.Builder) ((Fitness.StopFitnessData) this.payload_));
                                        this.payload_ = mo10081toBuilder65.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 131;
                                    continue;
                                case PhotoshopDirectory.TAG_EXIF_DATA_1 /* 1058 */:
                                    Fitness.NotifyFitnessDataAvailable.Builder mo10081toBuilder66 = this.payloadCase_ == 132 ? ((Fitness.NotifyFitnessDataAvailable) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Fitness.NotifyFitnessDataAvailable.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder66 != null) {
                                        mo10081toBuilder66.mergeFrom((Fitness.NotifyFitnessDataAvailable.Builder) ((Fitness.NotifyFitnessDataAvailable) this.payload_));
                                        this.payload_ = mo10081toBuilder66.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 132;
                                    continue;
                                case PhotoshopDirectory.TAG_ALTERNATE_DUOTONE_COLORS /* 1066 */:
                                    Fitness.SyncFitnessSession.Builder mo10081toBuilder67 = this.payloadCase_ == 133 ? ((Fitness.SyncFitnessSession) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Fitness.SyncFitnessSession.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder67 != null) {
                                        mo10081toBuilder67.mergeFrom((Fitness.SyncFitnessSession.Builder) ((Fitness.SyncFitnessSession) this.payload_));
                                        this.payload_ = mo10081toBuilder67.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 133;
                                    continue;
                                case PhotoshopDirectory.TAG_MEASUREMENT_SCALE /* 1074 */:
                                    Fitness.StartLiveFitnessData.Builder mo10081toBuilder68 = this.payloadCase_ == 134 ? ((Fitness.StartLiveFitnessData) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Fitness.StartLiveFitnessData.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder68 != null) {
                                        mo10081toBuilder68.mergeFrom((Fitness.StartLiveFitnessData.Builder) ((Fitness.StartLiveFitnessData) this.payload_));
                                        this.payload_ = mo10081toBuilder68.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 134;
                                    continue;
                                case PhotoshopDirectory.TAG_PRINT_INFO_2 /* 1082 */:
                                    Fitness.StopLiveFitnessData.Builder mo10081toBuilder69 = this.payloadCase_ == 135 ? ((Fitness.StopLiveFitnessData) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Fitness.StopLiveFitnessData.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder69 != null) {
                                        mo10081toBuilder69.mergeFrom((Fitness.StopLiveFitnessData.Builder) ((Fitness.StopLiveFitnessData) this.payload_));
                                        this.payload_ = mo10081toBuilder69.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 135;
                                    continue;
                                case 1090:
                                    Fitness.LiveFitnessData.Builder mo10081toBuilder70 = this.payloadCase_ == 136 ? ((Fitness.LiveFitnessData) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Fitness.LiveFitnessData.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder70 != null) {
                                        mo10081toBuilder70.mergeFrom((Fitness.LiveFitnessData.Builder) ((Fitness.LiveFitnessData) this.payload_));
                                        this.payload_ = mo10081toBuilder70.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 136;
                                    continue;
                                case 1122:
                                    Nonhfpcalling.UpdateCallState.Builder mo10081toBuilder71 = this.payloadCase_ == 140 ? ((Nonhfpcalling.UpdateCallState) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Nonhfpcalling.UpdateCallState.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder71 != null) {
                                        mo10081toBuilder71.mergeFrom((Nonhfpcalling.UpdateCallState.Builder) ((Nonhfpcalling.UpdateCallState) this.payload_));
                                        this.payload_ = mo10081toBuilder71.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 140;
                                    continue;
                                case 1130:
                                    Nonhfpcalling.AcceptCall.Builder mo10081toBuilder72 = this.payloadCase_ == 141 ? ((Nonhfpcalling.AcceptCall) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Nonhfpcalling.AcceptCall.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder72 != null) {
                                        mo10081toBuilder72.mergeFrom((Nonhfpcalling.AcceptCall.Builder) ((Nonhfpcalling.AcceptCall) this.payload_));
                                        this.payload_ = mo10081toBuilder72.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 141;
                                    continue;
                                case 1138:
                                    Nonhfpcalling.RejectCall.Builder mo10081toBuilder73 = this.payloadCase_ == 142 ? ((Nonhfpcalling.RejectCall) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Nonhfpcalling.RejectCall.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder73 != null) {
                                        mo10081toBuilder73.mergeFrom((Nonhfpcalling.RejectCall.Builder) ((Nonhfpcalling.RejectCall) this.payload_));
                                        this.payload_ = mo10081toBuilder73.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 142;
                                    continue;
                                case 1146:
                                    Nonhfpcalling.EndCall.Builder mo10081toBuilder74 = this.payloadCase_ == 143 ? ((Nonhfpcalling.EndCall) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Nonhfpcalling.EndCall.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder74 != null) {
                                        mo10081toBuilder74.mergeFrom((Nonhfpcalling.EndCall.Builder) ((Nonhfpcalling.EndCall) this.payload_));
                                        this.payload_ = mo10081toBuilder74.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 143;
                                    continue;
                                case 1202:
                                    Ancs.SubscribeNotificationCenter.Builder mo10081toBuilder75 = this.payloadCase_ == 150 ? ((Ancs.SubscribeNotificationCenter) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Ancs.SubscribeNotificationCenter.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder75 != null) {
                                        mo10081toBuilder75.mergeFrom((Ancs.SubscribeNotificationCenter.Builder) ((Ancs.SubscribeNotificationCenter) this.payload_));
                                        this.payload_ = mo10081toBuilder75.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 150;
                                    continue;
                                case 1210:
                                    Ancs.UnsubscribeNotificationCenter.Builder mo10081toBuilder76 = this.payloadCase_ == 151 ? ((Ancs.UnsubscribeNotificationCenter) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Ancs.UnsubscribeNotificationCenter.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder76 != null) {
                                        mo10081toBuilder76.mergeFrom((Ancs.UnsubscribeNotificationCenter.Builder) ((Ancs.UnsubscribeNotificationCenter) this.payload_));
                                        this.payload_ = mo10081toBuilder76.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 151;
                                    continue;
                                case 1218:
                                    Ancs.PublishCentralNotification.Builder mo10081toBuilder77 = this.payloadCase_ == 152 ? ((Ancs.PublishCentralNotification) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Ancs.PublishCentralNotification.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder77 != null) {
                                        mo10081toBuilder77.mergeFrom((Ancs.PublishCentralNotification.Builder) ((Ancs.PublishCentralNotification) this.payload_));
                                        this.payload_ = mo10081toBuilder77.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 152;
                                    continue;
                                case 1226:
                                    Ancs.GetCentralNotificationAttributes.Builder mo10081toBuilder78 = this.payloadCase_ == 153 ? ((Ancs.GetCentralNotificationAttributes) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Ancs.GetCentralNotificationAttributes.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder78 != null) {
                                        mo10081toBuilder78.mergeFrom((Ancs.GetCentralNotificationAttributes.Builder) ((Ancs.GetCentralNotificationAttributes) this.payload_));
                                        this.payload_ = mo10081toBuilder78.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 153;
                                    continue;
                                case 1234:
                                    Ancs.GetCentralNotificationAppAttributes.Builder mo10081toBuilder79 = this.payloadCase_ == 154 ? ((Ancs.GetCentralNotificationAppAttributes) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Ancs.GetCentralNotificationAppAttributes.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder79 != null) {
                                        mo10081toBuilder79.mergeFrom((Ancs.GetCentralNotificationAppAttributes.Builder) ((Ancs.GetCentralNotificationAppAttributes) this.payload_));
                                        this.payload_ = mo10081toBuilder79.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 154;
                                    continue;
                                case 1242:
                                    Ancs.PerformCentralNotificationAction.Builder mo10081toBuilder80 = this.payloadCase_ == 155 ? ((Ancs.PerformCentralNotificationAction) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Ancs.PerformCentralNotificationAction.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder80 != null) {
                                        mo10081toBuilder80.mergeFrom((Ancs.PerformCentralNotificationAction.Builder) ((Ancs.PerformCentralNotificationAction) this.payload_));
                                        this.payload_ = mo10081toBuilder80.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 155;
                                    continue;
                                case 1250:
                                    Ancs.UpdateCentralNotificationAttributes.Builder mo10081toBuilder81 = this.payloadCase_ == 156 ? ((Ancs.UpdateCentralNotificationAttributes) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Ancs.UpdateCentralNotificationAttributes.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder81 != null) {
                                        mo10081toBuilder81.mergeFrom((Ancs.UpdateCentralNotificationAttributes.Builder) ((Ancs.UpdateCentralNotificationAttributes) this.payload_));
                                        this.payload_ = mo10081toBuilder81.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 156;
                                    continue;
                                case OlympusCameraSettingsMakernoteDirectory.TagWhiteBalanceBracket /* 1282 */:
                                    Input.IssueInputEvent.Builder mo10081toBuilder82 = this.payloadCase_ == 160 ? ((Input.IssueInputEvent) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Input.IssueInputEvent.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder82 != null) {
                                        mo10081toBuilder82.mergeFrom((Input.IssueInputEvent.Builder) ((Input.IssueInputEvent) this.payload_));
                                        this.payload_ = mo10081toBuilder82.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 160;
                                    continue;
                                case OlympusCameraSettingsMakernoteDirectory.TagNoiseReduction /* 1290 */:
                                    Input.SetInputBehavior.Builder mo10081toBuilder83 = this.payloadCase_ == 161 ? ((Input.SetInputBehavior) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Input.SetInputBehavior.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder83 != null) {
                                        mo10081toBuilder83.mergeFrom((Input.SetInputBehavior.Builder) ((Input.SetInputBehavior) this.payload_));
                                        this.payload_ = mo10081toBuilder83.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 161;
                                    continue;
                                case 1298:
                                    Input.GetInputBehavior.Builder mo10081toBuilder84 = this.payloadCase_ == 162 ? ((Input.GetInputBehavior) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Input.GetInputBehavior.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder84 != null) {
                                        mo10081toBuilder84.mergeFrom((Input.GetInputBehavior.Builder) ((Input.GetInputBehavior) this.payload_));
                                        this.payload_ = mo10081toBuilder84.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 162;
                                    continue;
                                case 1306:
                                    Instrumentation.PrintDebug.Builder mo10081toBuilder85 = this.payloadCase_ == 163 ? ((Instrumentation.PrintDebug) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Instrumentation.PrintDebug.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder85 != null) {
                                        mo10081toBuilder85.mergeFrom((Instrumentation.PrintDebug.Builder) ((Instrumentation.PrintDebug) this.payload_));
                                        this.payload_ = mo10081toBuilder85.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 163;
                                    continue;
                                case OlympusCameraSettingsMakernoteDirectory.TagPictureModeHue /* 1314 */:
                                    Instrumentation.IssueRemoteCommand.Builder mo10081toBuilder86 = this.payloadCase_ == 164 ? ((Instrumentation.IssueRemoteCommand) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Instrumentation.IssueRemoteCommand.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder86 != null) {
                                        mo10081toBuilder86.mergeFrom((Instrumentation.IssueRemoteCommand.Builder) ((Instrumentation.IssueRemoteCommand) this.payload_));
                                        this.payload_ = mo10081toBuilder86.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 164;
                                    continue;
                                case 1322:
                                    Instrumentation.IssueRemoteRestart.Builder mo10081toBuilder87 = this.payloadCase_ == 165 ? ((Instrumentation.IssueRemoteRestart) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Instrumentation.IssueRemoteRestart.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder87 != null) {
                                        mo10081toBuilder87.mergeFrom((Instrumentation.IssueRemoteRestart.Builder) ((Instrumentation.IssueRemoteRestart) this.payload_));
                                        this.payload_ = mo10081toBuilder87.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 165;
                                    continue;
                                case OlympusCameraSettingsMakernoteDirectory.TagColorCreatorEffect /* 1330 */:
                                    Instrumentation.IssueRemoteReset.Builder mo10081toBuilder88 = this.payloadCase_ == 166 ? ((Instrumentation.IssueRemoteReset) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Instrumentation.IssueRemoteReset.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder88 != null) {
                                        mo10081toBuilder88.mergeFrom((Instrumentation.IssueRemoteReset.Builder) ((Instrumentation.IssueRemoteReset) this.payload_));
                                        this.payload_ = mo10081toBuilder88.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 166;
                                    continue;
                                case 1338:
                                    Instrumentation.IssueRemoteClearPairing.Builder mo10081toBuilder89 = this.payloadCase_ == 167 ? ((Instrumentation.IssueRemoteClearPairing) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Instrumentation.IssueRemoteClearPairing.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder89 != null) {
                                        mo10081toBuilder89.mergeFrom((Instrumentation.IssueRemoteClearPairing.Builder) ((Instrumentation.IssueRemoteClearPairing) this.payload_));
                                        this.payload_ = mo10081toBuilder89.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 167;
                                    continue;
                                case 1346:
                                    Input.ResetInputBehavior.Builder mo10081toBuilder90 = this.payloadCase_ == 168 ? ((Input.ResetInputBehavior) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Input.ResetInputBehavior.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder90 != null) {
                                        mo10081toBuilder90.mergeFrom((Input.ResetInputBehavior.Builder) ((Input.ResetInputBehavior) this.payload_));
                                        this.payload_ = mo10081toBuilder90.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 168;
                                    continue;
                                case 1362:
                                    Translation.StartTranslation.Builder mo10081toBuilder91 = this.payloadCase_ == 170 ? ((Translation.StartTranslation) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Translation.StartTranslation.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder91 != null) {
                                        mo10081toBuilder91.mergeFrom((Translation.StartTranslation.Builder) ((Translation.StartTranslation) this.payload_));
                                        this.payload_ = mo10081toBuilder91.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 170;
                                    continue;
                                case 1370:
                                    Translation.ProvideTranslation.Builder mo10081toBuilder92 = this.payloadCase_ == 171 ? ((Translation.ProvideTranslation) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Translation.ProvideTranslation.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder92 != null) {
                                        mo10081toBuilder92.mergeFrom((Translation.ProvideTranslation.Builder) ((Translation.ProvideTranslation) this.payload_));
                                        this.payload_ = mo10081toBuilder92.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 171;
                                    continue;
                                case 1378:
                                    Translation.StopTranslation.Builder mo10081toBuilder93 = this.payloadCase_ == 172 ? ((Translation.StopTranslation) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Translation.StopTranslation.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder93 != null) {
                                        mo10081toBuilder93.mergeFrom((Translation.StopTranslation.Builder) ((Translation.StopTranslation) this.payload_));
                                        this.payload_ = mo10081toBuilder93.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 172;
                                    continue;
                                case 1442:
                                    Bulkdata.GetBulkDataManifest.Builder mo10081toBuilder94 = this.payloadCase_ == 180 ? ((Bulkdata.GetBulkDataManifest) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Bulkdata.GetBulkDataManifest.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder94 != null) {
                                        mo10081toBuilder94.mergeFrom((Bulkdata.GetBulkDataManifest.Builder) ((Bulkdata.GetBulkDataManifest) this.payload_));
                                        this.payload_ = mo10081toBuilder94.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 180;
                                    continue;
                                case 1450:
                                    Bulkdata.BulkDataManifestEntry.Builder mo10081toBuilder95 = this.payloadCase_ == 181 ? ((Bulkdata.BulkDataManifestEntry) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Bulkdata.BulkDataManifestEntry.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder95 != null) {
                                        mo10081toBuilder95.mergeFrom((Bulkdata.BulkDataManifestEntry.Builder) ((Bulkdata.BulkDataManifestEntry) this.payload_));
                                        this.payload_ = mo10081toBuilder95.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 181;
                                    continue;
                                case 1458:
                                    Bulkdata.RequestBulkDataTransfer.Builder mo10081toBuilder96 = this.payloadCase_ == 182 ? ((Bulkdata.RequestBulkDataTransfer) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Bulkdata.RequestBulkDataTransfer.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder96 != null) {
                                        mo10081toBuilder96.mergeFrom((Bulkdata.RequestBulkDataTransfer.Builder) ((Bulkdata.RequestBulkDataTransfer) this.payload_));
                                        this.payload_ = mo10081toBuilder96.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 182;
                                    continue;
                                case 1466:
                                    Bulkdata.BulkDataTransferStart.Builder mo10081toBuilder97 = this.payloadCase_ == 183 ? ((Bulkdata.BulkDataTransferStart) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Bulkdata.BulkDataTransferStart.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder97 != null) {
                                        mo10081toBuilder97.mergeFrom((Bulkdata.BulkDataTransferStart.Builder) ((Bulkdata.BulkDataTransferStart) this.payload_));
                                        this.payload_ = mo10081toBuilder97.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 183;
                                    continue;
                                case 1474:
                                    Bulkdata.BulkDataTransferComplete.Builder mo10081toBuilder98 = this.payloadCase_ == 184 ? ((Bulkdata.BulkDataTransferComplete) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Bulkdata.BulkDataTransferComplete.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder98 != null) {
                                        mo10081toBuilder98.mergeFrom((Bulkdata.BulkDataTransferComplete.Builder) ((Bulkdata.BulkDataTransferComplete) this.payload_));
                                        this.payload_ = mo10081toBuilder98.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 184;
                                    continue;
                                case 1482:
                                    Bulkdata.StopBulkDataTransfer.Builder mo10081toBuilder99 = this.payloadCase_ == 185 ? ((Bulkdata.StopBulkDataTransfer) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Bulkdata.StopBulkDataTransfer.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder99 != null) {
                                        mo10081toBuilder99.mergeFrom((Bulkdata.StopBulkDataTransfer.Builder) ((Bulkdata.StopBulkDataTransfer) this.payload_));
                                        this.payload_ = mo10081toBuilder99.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 185;
                                    continue;
                                case 1490:
                                    Bulkdata.NotifyBulkDataAvailable.Builder mo10081toBuilder100 = this.payloadCase_ == 186 ? ((Bulkdata.NotifyBulkDataAvailable) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Bulkdata.NotifyBulkDataAvailable.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder100 != null) {
                                        mo10081toBuilder100.mergeFrom((Bulkdata.NotifyBulkDataAvailable.Builder) ((Bulkdata.NotifyBulkDataAvailable) this.payload_));
                                        this.payload_ = mo10081toBuilder100.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 186;
                                    continue;
                                case 1522:
                                    Notification.AddNotification.Builder mo10081toBuilder101 = this.payloadCase_ == 190 ? ((Notification.AddNotification) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Notification.AddNotification.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder101 != null) {
                                        mo10081toBuilder101.mergeFrom((Notification.AddNotification.Builder) ((Notification.AddNotification) this.payload_));
                                        this.payload_ = mo10081toBuilder101.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 190;
                                    continue;
                                case 1530:
                                    Notification.UpdateNotification.Builder mo10081toBuilder102 = this.payloadCase_ == 191 ? ((Notification.UpdateNotification) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Notification.UpdateNotification.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder102 != null) {
                                        mo10081toBuilder102.mergeFrom((Notification.UpdateNotification.Builder) ((Notification.UpdateNotification) this.payload_));
                                        this.payload_ = mo10081toBuilder102.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 191;
                                    continue;
                                case 1538:
                                    Notification.RemoveNotification.Builder mo10081toBuilder103 = this.payloadCase_ == 192 ? ((Notification.RemoveNotification) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Notification.RemoveNotification.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder103 != null) {
                                        mo10081toBuilder103.mergeFrom((Notification.RemoveNotification.Builder) ((Notification.RemoveNotification) this.payload_));
                                        this.payload_ = mo10081toBuilder103.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 192;
                                    continue;
                                case 1546:
                                    Notification.ExecuteNotificationAction.Builder mo10081toBuilder104 = this.payloadCase_ == 193 ? ((Notification.ExecuteNotificationAction) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Notification.ExecuteNotificationAction.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder104 != null) {
                                        mo10081toBuilder104.mergeFrom((Notification.ExecuteNotificationAction.Builder) ((Notification.ExecuteNotificationAction) this.payload_));
                                        this.payload_ = mo10081toBuilder104.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 193;
                                    continue;
                                case 1602:
                                    Keyexchange.InitiateHandshake.Builder mo10081toBuilder105 = this.payloadCase_ == 200 ? ((Keyexchange.InitiateHandshake) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Keyexchange.InitiateHandshake.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder105 != null) {
                                        mo10081toBuilder105.mergeFrom((Keyexchange.InitiateHandshake.Builder) ((Keyexchange.InitiateHandshake) this.payload_));
                                        this.payload_ = mo10081toBuilder105.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 200;
                                    continue;
                                case 1610:
                                    Keyexchange.CompleteHandshake.Builder mo10081toBuilder106 = this.payloadCase_ == 201 ? ((Keyexchange.CompleteHandshake) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Keyexchange.CompleteHandshake.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder106 != null) {
                                        mo10081toBuilder106.mergeFrom((Keyexchange.CompleteHandshake.Builder) ((Keyexchange.CompleteHandshake) this.payload_));
                                        this.payload_ = mo10081toBuilder106.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 201;
                                    continue;
                                case 1618:
                                    Keyexchange.UserConfirmed.Builder mo10081toBuilder107 = this.payloadCase_ == 202 ? ((Keyexchange.UserConfirmed) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Keyexchange.UserConfirmed.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder107 != null) {
                                        mo10081toBuilder107.mergeFrom((Keyexchange.UserConfirmed.Builder) ((Keyexchange.UserConfirmed) this.payload_));
                                        this.payload_ = mo10081toBuilder107.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 202;
                                    continue;
                                case 1626:
                                    Keyexchange.ResetKey.Builder mo10081toBuilder108 = this.payloadCase_ == 203 ? ((Keyexchange.ResetKey) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Keyexchange.ResetKey.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder108 != null) {
                                        mo10081toBuilder108.mergeFrom((Keyexchange.ResetKey.Builder) ((Keyexchange.ResetKey) this.payload_));
                                        this.payload_ = mo10081toBuilder108.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 203;
                                    continue;
                                case 1634:
                                    Keyexchange.ConfirmResetKey.Builder mo10081toBuilder109 = this.payloadCase_ == 204 ? ((Keyexchange.ConfirmResetKey) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Keyexchange.ConfirmResetKey.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder109 != null) {
                                        mo10081toBuilder109.mergeFrom((Keyexchange.ConfirmResetKey.Builder) ((Keyexchange.ConfirmResetKey) this.payload_));
                                        this.payload_ = mo10081toBuilder109.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 204;
                                    continue;
                                case 1642:
                                    Keyexchange.ResetRootKeys.Builder mo10081toBuilder110 = this.payloadCase_ == 205 ? ((Keyexchange.ResetRootKeys) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Keyexchange.ResetRootKeys.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder110 != null) {
                                        mo10081toBuilder110.mergeFrom((Keyexchange.ResetRootKeys.Builder) ((Keyexchange.ResetRootKeys) this.payload_));
                                        this.payload_ = mo10081toBuilder110.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 205;
                                    continue;
                                case 1842:
                                    Cbl.GetCblLoginState.Builder mo10081toBuilder111 = this.payloadCase_ == 230 ? ((Cbl.GetCblLoginState) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Cbl.GetCblLoginState.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder111 != null) {
                                        mo10081toBuilder111.mergeFrom((Cbl.GetCblLoginState.Builder) ((Cbl.GetCblLoginState) this.payload_));
                                        this.payload_ = mo10081toBuilder111.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 230;
                                    continue;
                                case 1850:
                                    Cbl.GetCblInformation.Builder mo10081toBuilder112 = this.payloadCase_ == 231 ? ((Cbl.GetCblInformation) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Cbl.GetCblInformation.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder112 != null) {
                                        mo10081toBuilder112.mergeFrom((Cbl.GetCblInformation.Builder) ((Cbl.GetCblInformation) this.payload_));
                                        this.payload_ = mo10081toBuilder112.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 231;
                                    continue;
                                case 1858:
                                    Cbl.NotifyCblLoginState.Builder mo10081toBuilder113 = this.payloadCase_ == 232 ? ((Cbl.NotifyCblLoginState) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Cbl.NotifyCblLoginState.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder113 != null) {
                                        mo10081toBuilder113.mergeFrom((Cbl.NotifyCblLoginState.Builder) ((Cbl.NotifyCblLoginState) this.payload_));
                                        this.payload_ = mo10081toBuilder113.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 232;
                                    continue;
                                case 2402:
                                    Hearing.GetAudiogram.Builder mo10081toBuilder114 = this.payloadCase_ == 300 ? ((Hearing.GetAudiogram) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Hearing.GetAudiogram.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder114 != null) {
                                        mo10081toBuilder114.mergeFrom((Hearing.GetAudiogram.Builder) ((Hearing.GetAudiogram) this.payload_));
                                        this.payload_ = mo10081toBuilder114.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 300;
                                    continue;
                                case 2410:
                                    Hearing.SetAudiogram.Builder mo10081toBuilder115 = this.payloadCase_ == 301 ? ((Hearing.SetAudiogram) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Hearing.SetAudiogram.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder115 != null) {
                                        mo10081toBuilder115.mergeFrom((Hearing.SetAudiogram.Builder) ((Hearing.SetAudiogram) this.payload_));
                                        this.payload_ = mo10081toBuilder115.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 301;
                                    continue;
                                case 2418:
                                    Hearing.GetMediaEnhancementCorrectionAmount.Builder mo10081toBuilder116 = this.payloadCase_ == 302 ? ((Hearing.GetMediaEnhancementCorrectionAmount) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Hearing.GetMediaEnhancementCorrectionAmount.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder116 != null) {
                                        mo10081toBuilder116.mergeFrom((Hearing.GetMediaEnhancementCorrectionAmount.Builder) ((Hearing.GetMediaEnhancementCorrectionAmount) this.payload_));
                                        this.payload_ = mo10081toBuilder116.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 302;
                                    continue;
                                case 2426:
                                    Hearing.SetMediaEnhancementCorrectionAmount.Builder mo10081toBuilder117 = this.payloadCase_ == 303 ? ((Hearing.SetMediaEnhancementCorrectionAmount) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Hearing.SetMediaEnhancementCorrectionAmount.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder117 != null) {
                                        mo10081toBuilder117.mergeFrom((Hearing.SetMediaEnhancementCorrectionAmount.Builder) ((Hearing.SetMediaEnhancementCorrectionAmount) this.payload_));
                                        this.payload_ = mo10081toBuilder117.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 303;
                                    continue;
                                case 2802:
                                    Mapsms.GetSmsMessageListResponse.Builder mo10081toBuilder118 = this.payloadCase_ == 350 ? ((Mapsms.GetSmsMessageListResponse) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Mapsms.GetSmsMessageListResponse.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder118 != null) {
                                        mo10081toBuilder118.mergeFrom((Mapsms.GetSmsMessageListResponse.Builder) ((Mapsms.GetSmsMessageListResponse) this.payload_));
                                        this.payload_ = mo10081toBuilder118.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 350;
                                    continue;
                                case 2810:
                                    Mapsms.NotifySmsMessageList.Builder mo10081toBuilder119 = this.payloadCase_ == 351 ? ((Mapsms.NotifySmsMessageList) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Mapsms.NotifySmsMessageList.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder119 != null) {
                                        mo10081toBuilder119.mergeFrom((Mapsms.NotifySmsMessageList.Builder) ((Mapsms.NotifySmsMessageList) this.payload_));
                                        this.payload_ = mo10081toBuilder119.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 351;
                                    continue;
                                case 2818:
                                    Mapsms.GetSmsMessageList.Builder mo10081toBuilder120 = this.payloadCase_ == 352 ? ((Mapsms.GetSmsMessageList) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Mapsms.GetSmsMessageList.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder120 != null) {
                                        mo10081toBuilder120.mergeFrom((Mapsms.GetSmsMessageList.Builder) ((Mapsms.GetSmsMessageList) this.payload_));
                                        this.payload_ = mo10081toBuilder120.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 352;
                                    continue;
                                case 2826:
                                    Mapsms.SendSms.Builder mo10081toBuilder121 = this.payloadCase_ == 353 ? ((Mapsms.SendSms) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Mapsms.SendSms.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder121 != null) {
                                        mo10081toBuilder121.mergeFrom((Mapsms.SendSms.Builder) ((Mapsms.SendSms) this.payload_));
                                        this.payload_ = mo10081toBuilder121.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 353;
                                    continue;
                                case 2834:
                                    Mapsms.SetSmsReadStatus.Builder mo10081toBuilder122 = this.payloadCase_ == 354 ? ((Mapsms.SetSmsReadStatus) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Mapsms.SetSmsReadStatus.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder122 != null) {
                                        mo10081toBuilder122.mergeFrom((Mapsms.SetSmsReadStatus.Builder) ((Mapsms.SetSmsReadStatus) this.payload_));
                                        this.payload_ = mo10081toBuilder122.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 354;
                                    continue;
                                case 2842:
                                    Mapsms.EndOfSmsMessageListResponse.Builder mo10081toBuilder123 = this.payloadCase_ == 355 ? ((Mapsms.EndOfSmsMessageListResponse) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Mapsms.EndOfSmsMessageListResponse.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder123 != null) {
                                        mo10081toBuilder123.mergeFrom((Mapsms.EndOfSmsMessageListResponse.Builder) ((Mapsms.EndOfSmsMessageListResponse) this.payload_));
                                        this.payload_ = mo10081toBuilder123.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 355;
                                    continue;
                                case 2850:
                                    Mapsms.InitiateMapConnection.Builder mo10081toBuilder124 = this.payloadCase_ == 356 ? ((Mapsms.InitiateMapConnection) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Mapsms.InitiateMapConnection.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder124 != null) {
                                        mo10081toBuilder124.mergeFrom((Mapsms.InitiateMapConnection.Builder) ((Mapsms.InitiateMapConnection) this.payload_));
                                        this.payload_ = mo10081toBuilder124.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 356;
                                    continue;
                                case 2882:
                                    Voicestream.StartVoiceStream.Builder mo10081toBuilder125 = this.payloadCase_ == 360 ? ((Voicestream.StartVoiceStream) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Voicestream.StartVoiceStream.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder125 != null) {
                                        mo10081toBuilder125.mergeFrom((Voicestream.StartVoiceStream.Builder) ((Voicestream.StartVoiceStream) this.payload_));
                                        this.payload_ = mo10081toBuilder125.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 360;
                                    continue;
                                case 2890:
                                    Voicestream.StopVoiceStream.Builder mo10081toBuilder126 = this.payloadCase_ == 361 ? ((Voicestream.StopVoiceStream) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Voicestream.StopVoiceStream.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder126 != null) {
                                        mo10081toBuilder126.mergeFrom((Voicestream.StopVoiceStream.Builder) ((Voicestream.StopVoiceStream) this.payload_));
                                        this.payload_ = mo10081toBuilder126.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 361;
                                    continue;
                                case 2962:
                                    Cloudpairing.GetCloudPairingAttributes.Builder mo10081toBuilder127 = this.payloadCase_ == 370 ? ((Cloudpairing.GetCloudPairingAttributes) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Cloudpairing.GetCloudPairingAttributes.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder127 != null) {
                                        mo10081toBuilder127.mergeFrom((Cloudpairing.GetCloudPairingAttributes.Builder) ((Cloudpairing.GetCloudPairingAttributes) this.payload_));
                                        this.payload_ = mo10081toBuilder127.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 370;
                                    continue;
                                case 2970:
                                    Cloudpairing.GetCloudPairingStatus.Builder mo10081toBuilder128 = this.payloadCase_ == 371 ? ((Cloudpairing.GetCloudPairingStatus) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Cloudpairing.GetCloudPairingStatus.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder128 != null) {
                                        mo10081toBuilder128.mergeFrom((Cloudpairing.GetCloudPairingStatus.Builder) ((Cloudpairing.GetCloudPairingStatus) this.payload_));
                                        this.payload_ = mo10081toBuilder128.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 371;
                                    continue;
                                case 2978:
                                    Cloudpairing.SetCloudPairingKeys.Builder mo10081toBuilder129 = this.payloadCase_ == 372 ? ((Cloudpairing.SetCloudPairingKeys) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Cloudpairing.SetCloudPairingKeys.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder129 != null) {
                                        mo10081toBuilder129.mergeFrom((Cloudpairing.SetCloudPairingKeys.Builder) ((Cloudpairing.SetCloudPairingKeys) this.payload_));
                                        this.payload_ = mo10081toBuilder129.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 372;
                                    continue;
                                case 2986:
                                    Cloudpairing.ReplaceCloudPairingKeys.Builder mo10081toBuilder130 = this.payloadCase_ == 373 ? ((Cloudpairing.ReplaceCloudPairingKeys) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Cloudpairing.ReplaceCloudPairingKeys.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder130 != null) {
                                        mo10081toBuilder130.mergeFrom((Cloudpairing.ReplaceCloudPairingKeys.Builder) ((Cloudpairing.ReplaceCloudPairingKeys) this.payload_));
                                        this.payload_ = mo10081toBuilder130.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 373;
                                    continue;
                                case 2994:
                                    Cloudpairing.RemoveCloudPairingKeys.Builder mo10081toBuilder131 = this.payloadCase_ == 374 ? ((Cloudpairing.RemoveCloudPairingKeys) this.payload_).mo10081toBuilder() : null;
                                    this.payload_ = codedInputStream.readMessage(Cloudpairing.RemoveCloudPairingKeys.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder131 != null) {
                                        mo10081toBuilder131.mergeFrom((Cloudpairing.RemoveCloudPairingKeys.Builder) ((Cloudpairing.RemoveCloudPairingKeys) this.payload_));
                                        this.payload_ = mo10081toBuilder131.mo10085buildPartial();
                                    }
                                    this.payloadCase_ = 374;
                                    continue;
                                default:
                                    if (!parseUnknownField(readTag, codedInputStream)) {
                                        break;
                                    } else {
                                        continue;
                                    }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new ControlEnvelope();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (ControlEnvelope.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Nonhfpcalling.AcceptCall getAcceptCall() {
            if (this.payloadCase_ == 141) {
                return (Nonhfpcalling.AcceptCall) this.payload_;
            }
            return Nonhfpcalling.AcceptCall.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Notification.AddNotification getAddNotification() {
            if (this.payloadCase_ == 190) {
                return (Notification.AddNotification) this.payload_;
            }
            return Notification.AddNotification.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Firmware.ApplyFirmware getApplyFirmware() {
            if (this.payloadCase_ == 95) {
                return (Firmware.ApplyFirmware) this.payload_;
            }
            return Firmware.ApplyFirmware.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Bulkdata.BulkDataManifestEntry getBulkDataManifestEntry() {
            if (this.payloadCase_ == 181) {
                return (Bulkdata.BulkDataManifestEntry) this.payload_;
            }
            return Bulkdata.BulkDataManifestEntry.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Bulkdata.BulkDataTransferComplete getBulkDataTransferComplete() {
            if (this.payloadCase_ == 184) {
                return (Bulkdata.BulkDataTransferComplete) this.payload_;
            }
            return Bulkdata.BulkDataTransferComplete.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Bulkdata.BulkDataTransferStart getBulkDataTransferStart() {
            if (this.payloadCase_ == 183) {
                return (Bulkdata.BulkDataTransferStart) this.payload_;
            }
            return Bulkdata.BulkDataTransferStart.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Command getCommand() {
            Command forNumber = Command.forNumber(this.command_);
            return forNumber == null ? Command.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public int getCommandValue() {
            return this.command_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Keyexchange.CompleteHandshake getCompleteHandshake() {
            if (this.payloadCase_ == 201) {
                return (Keyexchange.CompleteHandshake) this.payload_;
            }
            return Keyexchange.CompleteHandshake.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Device.CompleteSetup getCompleteSetup() {
            if (this.payloadCase_ == 24) {
                return (Device.CompleteSetup) this.payload_;
            }
            return Device.CompleteSetup.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Keyexchange.ConfirmResetKey getConfirmResetKey() {
            if (this.payloadCase_ == 204) {
                return (Keyexchange.ConfirmResetKey) this.payload_;
            }
            return Keyexchange.ConfirmResetKey.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.ConnectUser getConnectUser() {
            if (this.payloadCase_ == 62) {
                return (System.ConnectUser) this.payload_;
            }
            return System.ConnectUser.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.DisconnectUser getDisconnectUser() {
            if (this.payloadCase_ == 63) {
                return (System.DisconnectUser) this.payload_;
            }
            return System.DisconnectUser.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Cardrendering.DisplayContent getDisplayContent() {
            if (this.payloadCase_ == 80) {
                return (Cardrendering.DisplayContent) this.payload_;
            }
            return Cardrendering.DisplayContent.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Nonhfpcalling.EndCall getEndCall() {
            if (this.payloadCase_ == 143) {
                return (Nonhfpcalling.EndCall) this.payload_;
            }
            return Nonhfpcalling.EndCall.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Mapsms.EndOfSmsMessageListResponse getEndOfSmsMessageListResponse() {
            if (this.payloadCase_ == 355) {
                return (Mapsms.EndOfSmsMessageListResponse) this.payload_;
            }
            return Mapsms.EndOfSmsMessageListResponse.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Speech.EndpointSpeech getEndpointSpeech() {
            if (this.payloadCase_ == 13) {
                return (Speech.EndpointSpeech) this.payload_;
            }
            return Speech.EndpointSpeech.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Notification.ExecuteNotificationAction getExecuteNotificationAction() {
            if (this.payloadCase_ == 193) {
                return (Notification.ExecuteNotificationAction) this.payload_;
            }
            return Notification.ExecuteNotificationAction.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Firmware.FirmwareUpdateUnavailable getFirmwareUpdateUnavailable() {
            if (this.payloadCase_ == 86) {
                return (Firmware.FirmwareUpdateUnavailable) this.payload_;
            }
            return Firmware.FirmwareUpdateUnavailable.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Calling.ForwardATCommand getForwardAtCommand() {
            if (this.payloadCase_ == 40) {
                return (Calling.ForwardATCommand) this.payload_;
            }
            return Calling.ForwardATCommand.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Firmware.GetArtifactFilter getGetArtifactFilter() {
            if (this.payloadCase_ == 97) {
                return (Firmware.GetArtifactFilter) this.payload_;
            }
            return Firmware.GetArtifactFilter.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Firmware.GetArtifactUpdatePreference getGetArtifactUpdatePreference() {
            if (this.payloadCase_ == 98) {
                return (Firmware.GetArtifactUpdatePreference) this.payload_;
            }
            return Firmware.GetArtifactUpdatePreference.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Hearing.GetAudiogram getGetAudiogram() {
            if (this.payloadCase_ == 300) {
                return (Hearing.GetAudiogram) this.payload_;
            }
            return Hearing.GetAudiogram.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Bulkdata.GetBulkDataManifest getGetBulkDataManifest() {
            if (this.payloadCase_ == 180) {
                return (Bulkdata.GetBulkDataManifest) this.payload_;
            }
            return Bulkdata.GetBulkDataManifest.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Firmware.GetCachedComponent getGetCachedComponent() {
            if (this.payloadCase_ == 92) {
                return (Firmware.GetCachedComponent) this.payload_;
            }
            return Firmware.GetCachedComponent.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Cbl.GetCblInformation getGetCblInformation() {
            if (this.payloadCase_ == 231) {
                return (Cbl.GetCblInformation) this.payload_;
            }
            return Cbl.GetCblInformation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Cbl.GetCblLoginState getGetCblLoginState() {
            if (this.payloadCase_ == 230) {
                return (Cbl.GetCblLoginState) this.payload_;
            }
            return Cbl.GetCblLoginState.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Central.GetCentralInformation getGetCentralInformation() {
            if (this.payloadCase_ == 103) {
                return (Central.GetCentralInformation) this.payload_;
            }
            return Central.GetCentralInformation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Ancs.GetCentralNotificationAppAttributes getGetCentralNotificationAppAttributes() {
            if (this.payloadCase_ == 154) {
                return (Ancs.GetCentralNotificationAppAttributes) this.payload_;
            }
            return Ancs.GetCentralNotificationAppAttributes.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Ancs.GetCentralNotificationAttributes getGetCentralNotificationAttributes() {
            if (this.payloadCase_ == 153) {
                return (Ancs.GetCentralNotificationAttributes) this.payload_;
            }
            return Ancs.GetCentralNotificationAttributes.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Cloudpairing.GetCloudPairingAttributes getGetCloudPairingAttributes() {
            if (this.payloadCase_ == 370) {
                return (Cloudpairing.GetCloudPairingAttributes) this.payload_;
            }
            return Cloudpairing.GetCloudPairingAttributes.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Cloudpairing.GetCloudPairingStatus getGetCloudPairingStatus() {
            if (this.payloadCase_ == 371) {
                return (Cloudpairing.GetCloudPairingStatus) this.payload_;
            }
            return Cloudpairing.GetCloudPairingStatus.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.GetCurrentUser getGetCurrentUser() {
            if (this.payloadCase_ == 54) {
                return (System.GetCurrentUser) this.payload_;
            }
            return System.GetCurrentUser.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Firmware.GetDeviceArtifacts getGetDeviceArtifacts() {
            if (this.payloadCase_ == 96) {
                return (Firmware.GetDeviceArtifacts) this.payload_;
            }
            return Firmware.GetDeviceArtifacts.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Device.GetDeviceConfiguration getGetDeviceConfiguration() {
            if (this.payloadCase_ == 21) {
                return (Device.GetDeviceConfiguration) this.payload_;
            }
            return Device.GetDeviceConfiguration.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Device.GetDeviceFeatures getGetDeviceFeatures() {
            if (this.payloadCase_ == 28) {
                return (Device.GetDeviceFeatures) this.payload_;
            }
            return Device.GetDeviceFeatures.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Device.GetDeviceInformation getGetDeviceInformation() {
            if (this.payloadCase_ == 20) {
                return (Device.GetDeviceInformation) this.payload_;
            }
            return Device.GetDeviceInformation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public DiagnosticsOuterClass.GetDiagnostics getGetDiagnostics() {
            if (this.payloadCase_ == 110) {
                return (DiagnosticsOuterClass.GetDiagnostics) this.payload_;
            }
            return DiagnosticsOuterClass.GetDiagnostics.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Firmware.GetFirmwareInformation getGetFirmwareInformation() {
            if (this.payloadCase_ == 90) {
                return (Firmware.GetFirmwareInformation) this.payload_;
            }
            return Firmware.GetFirmwareInformation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Firmware.GetFirmwareUpdatePreferences getGetFirmwareUpdatePreferences() {
            if (this.payloadCase_ == 91) {
                return (Firmware.GetFirmwareUpdatePreferences) this.payload_;
            }
            return Firmware.GetFirmwareUpdatePreferences.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Fitness.GetFitnessData getGetFitnessData() {
            if (this.payloadCase_ == 130) {
                return (Fitness.GetFitnessData) this.payload_;
            }
            return Fitness.GetFitnessData.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Input.GetInputBehavior getGetInputBehavior() {
            if (this.payloadCase_ == 162) {
                return (Input.GetInputBehavior) this.payload_;
            }
            return Input.GetInputBehavior.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.GetLocales getGetLocales() {
            if (this.payloadCase_ == 57) {
                return (System.GetLocales) this.payload_;
            }
            return System.GetLocales.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Hearing.GetMediaEnhancementCorrectionAmount getGetMediaEnhancementCorrectionAmount() {
            if (this.payloadCase_ == 302) {
                return (Hearing.GetMediaEnhancementCorrectionAmount) this.payload_;
            }
            return Hearing.GetMediaEnhancementCorrectionAmount.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Media.GetPlaybackStatus getGetPlaybackStatus() {
            if (this.payloadCase_ == 65) {
                return (Media.GetPlaybackStatus) this.payload_;
            }
            return Media.GetPlaybackStatus.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Mapsms.GetSmsMessageList getGetSmsMessageList() {
            if (this.payloadCase_ == 352) {
                return (Mapsms.GetSmsMessageList) this.payload_;
            }
            return Mapsms.GetSmsMessageList.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Mapsms.GetSmsMessageListResponse getGetSmsMessageListResponse() {
            if (this.payloadCase_ == 350) {
                return (Mapsms.GetSmsMessageListResponse) this.payload_;
            }
            return Mapsms.GetSmsMessageListResponse.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public StateOuterClass.GetState getGetState() {
            if (this.payloadCase_ == 100) {
                return (StateOuterClass.GetState) this.payload_;
            }
            return StateOuterClass.GetState.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.GetUsers getGetUsers() {
            if (this.payloadCase_ == 52) {
                return (System.GetUsers) this.payload_;
            }
            return System.GetUsers.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.GetWakewords getGetWakewords() {
            if (this.payloadCase_ == 71) {
                return (System.GetWakewords) this.payload_;
            }
            return System.GetWakewords.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Calling.IncomingCall getIncomingCall() {
            if (this.payloadCase_ == 41) {
                return (Calling.IncomingCall) this.payload_;
            }
            return Calling.IncomingCall.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Firmware.InitiateFirmwareUpdate getInitiateFirmwareUpdate() {
            if (this.payloadCase_ == 88) {
                return (Firmware.InitiateFirmwareUpdate) this.payload_;
            }
            return Firmware.InitiateFirmwareUpdate.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Keyexchange.InitiateHandshake getInitiateHandshake() {
            if (this.payloadCase_ == 200) {
                return (Keyexchange.InitiateHandshake) this.payload_;
            }
            return Keyexchange.InitiateHandshake.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Mapsms.InitiateMapConnection getInitiateMapConnection() {
            if (this.payloadCase_ == 356) {
                return (Mapsms.InitiateMapConnection) this.payload_;
            }
            return Mapsms.InitiateMapConnection.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Input.IssueInputEvent getIssueInputEvent() {
            if (this.payloadCase_ == 160) {
                return (Input.IssueInputEvent) this.payload_;
            }
            return Input.IssueInputEvent.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Media.IssueMediaControl getIssueMediaControl() {
            if (this.payloadCase_ == 60) {
                return (Media.IssueMediaControl) this.payload_;
            }
            return Media.IssueMediaControl.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Instrumentation.IssueRemoteClearPairing getIssueRemoteClearPairing() {
            if (this.payloadCase_ == 167) {
                return (Instrumentation.IssueRemoteClearPairing) this.payload_;
            }
            return Instrumentation.IssueRemoteClearPairing.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Instrumentation.IssueRemoteCommand getIssueRemoteCommand() {
            if (this.payloadCase_ == 164) {
                return (Instrumentation.IssueRemoteCommand) this.payload_;
            }
            return Instrumentation.IssueRemoteCommand.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Instrumentation.IssueRemoteReset getIssueRemoteReset() {
            if (this.payloadCase_ == 166) {
                return (Instrumentation.IssueRemoteReset) this.payload_;
            }
            return Instrumentation.IssueRemoteReset.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Instrumentation.IssueRemoteRestart getIssueRemoteRestart() {
            if (this.payloadCase_ == 165) {
                return (Instrumentation.IssueRemoteRestart) this.payload_;
            }
            return Instrumentation.IssueRemoteRestart.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.KeepAlive getKeepAlive() {
            if (this.payloadCase_ == 55) {
                return (System.KeepAlive) this.payload_;
            }
            return System.KeepAlive.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.LaunchApp getLaunchApp() {
            if (this.payloadCase_ == 59) {
                return (System.LaunchApp) this.payload_;
            }
            return System.LaunchApp.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Fitness.LiveFitnessData getLiveFitnessData() {
            if (this.payloadCase_ == 136) {
                return (Fitness.LiveFitnessData) this.payload_;
            }
            return Fitness.LiveFitnessData.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Media.MediaEventOccurred getMediaEventOccurred() {
            if (this.payloadCase_ == 67) {
                return (Media.MediaEventOccurred) this.payload_;
            }
            return Media.MediaEventOccurred.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Bulkdata.NotifyBulkDataAvailable getNotifyBulkDataAvailable() {
            if (this.payloadCase_ == 186) {
                return (Bulkdata.NotifyBulkDataAvailable) this.payload_;
            }
            return Bulkdata.NotifyBulkDataAvailable.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Cbl.NotifyCblLoginState getNotifyCblLoginState() {
            if (this.payloadCase_ == 232) {
                return (Cbl.NotifyCblLoginState) this.payload_;
            }
            return Cbl.NotifyCblLoginState.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Device.NotifyDeviceConfiguration getNotifyDeviceConfiguration() {
            if (this.payloadCase_ == 25) {
                return (Device.NotifyDeviceConfiguration) this.payload_;
            }
            return Device.NotifyDeviceConfiguration.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Device.NotifyDeviceInformation getNotifyDeviceInformation() {
            if (this.payloadCase_ == 27) {
                return (Device.NotifyDeviceInformation) this.payload_;
            }
            return Device.NotifyDeviceInformation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public DiagnosticsOuterClass.NotifyDiagnosticsAvailable getNotifyDiagnosticsAvailable() {
            if (this.payloadCase_ == 112) {
                return (DiagnosticsOuterClass.NotifyDiagnosticsAvailable) this.payload_;
            }
            return DiagnosticsOuterClass.NotifyDiagnosticsAvailable.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Firmware.NotifyFirmwareInformation getNotifyFirmwareInformation() {
            if (this.payloadCase_ == 87) {
                return (Firmware.NotifyFirmwareInformation) this.payload_;
            }
            return Firmware.NotifyFirmwareInformation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Fitness.NotifyFitnessDataAvailable getNotifyFitnessDataAvailable() {
            if (this.payloadCase_ == 132) {
                return (Fitness.NotifyFitnessDataAvailable) this.payload_;
            }
            return Fitness.NotifyFitnessDataAvailable.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Mapsms.NotifySmsMessageList getNotifySmsMessageList() {
            if (this.payloadCase_ == 351) {
                return (Mapsms.NotifySmsMessageList) this.payload_;
            }
            return Mapsms.NotifySmsMessageList.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Speech.NotifySpeechState getNotifySpeechState() {
            if (this.payloadCase_ == 14) {
                return (Speech.NotifySpeechState) this.payload_;
            }
            return Speech.NotifySpeechState.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Device.OverrideAssistant getOverrideAssistant() {
            if (this.payloadCase_ == 22) {
                return (Device.OverrideAssistant) this.payload_;
            }
            return Device.OverrideAssistant.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public PayloadCase getPayloadCase() {
            return PayloadCase.forNumber(this.payloadCase_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Ancs.PerformCentralNotificationAction getPerformCentralNotificationAction() {
            if (this.payloadCase_ == 155) {
                return (Ancs.PerformCentralNotificationAction) this.payload_;
            }
            return Ancs.PerformCentralNotificationAction.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Instrumentation.PrintDebug getPrintDebug() {
            if (this.payloadCase_ == 163) {
                return (Instrumentation.PrintDebug) this.payload_;
            }
            return Instrumentation.PrintDebug.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Speech.ProvideSpeech getProvideSpeech() {
            if (this.payloadCase_ == 10) {
                return (Speech.ProvideSpeech) this.payload_;
            }
            return Speech.ProvideSpeech.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Translation.ProvideTranslation getProvideTranslation() {
            if (this.payloadCase_ == 171) {
                return (Translation.ProvideTranslation) this.payload_;
            }
            return Translation.ProvideTranslation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Ancs.PublishCentralNotification getPublishCentralNotification() {
            if (this.payloadCase_ == 152) {
                return (Ancs.PublishCentralNotification) this.payload_;
            }
            return Ancs.PublishCentralNotification.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Metrics.PushMetrics getPushMetrics() {
            if (this.payloadCase_ == 120) {
                return (Metrics.PushMetrics) this.payload_;
            }
            return Metrics.PushMetrics.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Media.RegisterForMediaEvents getRegisterForMediaEvents() {
            if (this.payloadCase_ == 66) {
                return (Media.RegisterForMediaEvents) this.payload_;
            }
            return Media.RegisterForMediaEvents.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Nonhfpcalling.RejectCall getRejectCall() {
            if (this.payloadCase_ == 142) {
                return (Nonhfpcalling.RejectCall) this.payload_;
            }
            return Nonhfpcalling.RejectCall.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Cloudpairing.RemoveCloudPairingKeys getRemoveCloudPairingKeys() {
            if (this.payloadCase_ == 374) {
                return (Cloudpairing.RemoveCloudPairingKeys) this.payload_;
            }
            return Cloudpairing.RemoveCloudPairingKeys.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.RemoveDevice getRemoveDevice() {
            if (this.payloadCase_ == 56) {
                return (System.RemoveDevice) this.payload_;
            }
            return System.RemoveDevice.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Notification.RemoveNotification getRemoveNotification() {
            if (this.payloadCase_ == 192) {
                return (Notification.RemoveNotification) this.payload_;
            }
            return Notification.RemoveNotification.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Cloudpairing.ReplaceCloudPairingKeys getReplaceCloudPairingKeys() {
            if (this.payloadCase_ == 373) {
                return (Cloudpairing.ReplaceCloudPairingKeys) this.payload_;
            }
            return Cloudpairing.ReplaceCloudPairingKeys.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Bulkdata.RequestBulkDataTransfer getRequestBulkDataTransfer() {
            if (this.payloadCase_ == 182) {
                return (Bulkdata.RequestBulkDataTransfer) this.payload_;
            }
            return Bulkdata.RequestBulkDataTransfer.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Firmware.ResetCachedComponent getResetCachedComponent() {
            if (this.payloadCase_ == 93) {
                return (Firmware.ResetCachedComponent) this.payload_;
            }
            return Firmware.ResetCachedComponent.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.ResetConnection getResetConnection() {
            if (this.payloadCase_ == 51) {
                return (System.ResetConnection) this.payload_;
            }
            return System.ResetConnection.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Input.ResetInputBehavior getResetInputBehavior() {
            if (this.payloadCase_ == 168) {
                return (Input.ResetInputBehavior) this.payload_;
            }
            return Input.ResetInputBehavior.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Keyexchange.ResetKey getResetKey() {
            if (this.payloadCase_ == 203) {
                return (Keyexchange.ResetKey) this.payload_;
            }
            return Keyexchange.ResetKey.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Keyexchange.ResetRootKeys getResetRootKeys() {
            if (this.payloadCase_ == 205) {
                return (Keyexchange.ResetRootKeys) this.payload_;
            }
            return Keyexchange.ResetRootKeys.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Response getResponse() {
            if (this.payloadCase_ == 9) {
                return (Response) this.payload_;
            }
            return Response.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Mapsms.SendSms getSendSms() {
            if (this.payloadCase_ == 353) {
                return (Mapsms.SendSms) this.payload_;
            }
            return Mapsms.SendSms.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.command_ != Command.NONE.getNumber()) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.command_);
            }
            if (this.payloadCase_ == 9) {
                i2 += CodedOutputStream.computeMessageSize(9, (Response) this.payload_);
            }
            if (this.payloadCase_ == 10) {
                i2 += CodedOutputStream.computeMessageSize(10, (Speech.ProvideSpeech) this.payload_);
            }
            if (this.payloadCase_ == 11) {
                i2 += CodedOutputStream.computeMessageSize(11, (Speech.StartSpeech) this.payload_);
            }
            if (this.payloadCase_ == 12) {
                i2 += CodedOutputStream.computeMessageSize(12, (Speech.StopSpeech) this.payload_);
            }
            if (this.payloadCase_ == 13) {
                i2 += CodedOutputStream.computeMessageSize(13, (Speech.EndpointSpeech) this.payload_);
            }
            if (this.payloadCase_ == 14) {
                i2 += CodedOutputStream.computeMessageSize(14, (Speech.NotifySpeechState) this.payload_);
            }
            if (this.payloadCase_ == 20) {
                i2 += CodedOutputStream.computeMessageSize(20, (Device.GetDeviceInformation) this.payload_);
            }
            if (this.payloadCase_ == 21) {
                i2 += CodedOutputStream.computeMessageSize(21, (Device.GetDeviceConfiguration) this.payload_);
            }
            if (this.payloadCase_ == 22) {
                i2 += CodedOutputStream.computeMessageSize(22, (Device.OverrideAssistant) this.payload_);
            }
            if (this.payloadCase_ == 23) {
                i2 += CodedOutputStream.computeMessageSize(23, (Device.StartSetup) this.payload_);
            }
            if (this.payloadCase_ == 24) {
                i2 += CodedOutputStream.computeMessageSize(24, (Device.CompleteSetup) this.payload_);
            }
            if (this.payloadCase_ == 25) {
                i2 += CodedOutputStream.computeMessageSize(25, (Device.NotifyDeviceConfiguration) this.payload_);
            }
            if (this.payloadCase_ == 26) {
                i2 += CodedOutputStream.computeMessageSize(26, (Device.UpdateDeviceInformation) this.payload_);
            }
            if (this.payloadCase_ == 27) {
                i2 += CodedOutputStream.computeMessageSize(27, (Device.NotifyDeviceInformation) this.payload_);
            }
            if (this.payloadCase_ == 28) {
                i2 += CodedOutputStream.computeMessageSize(28, (Device.GetDeviceFeatures) this.payload_);
            }
            if (this.payloadCase_ == 30) {
                i2 += CodedOutputStream.computeMessageSize(30, (Transport.UpgradeTransport) this.payload_);
            }
            if (this.payloadCase_ == 31) {
                i2 += CodedOutputStream.computeMessageSize(31, (Transport.SwitchTransport) this.payload_);
            }
            if (this.payloadCase_ == 40) {
                i2 += CodedOutputStream.computeMessageSize(40, (Calling.ForwardATCommand) this.payload_);
            }
            if (this.payloadCase_ == 41) {
                i2 += CodedOutputStream.computeMessageSize(41, (Calling.IncomingCall) this.payload_);
            }
            if (this.payloadCase_ == 50) {
                i2 += CodedOutputStream.computeMessageSize(50, (System.SynchronizeSettings) this.payload_);
            }
            if (this.payloadCase_ == 51) {
                i2 += CodedOutputStream.computeMessageSize(51, (System.ResetConnection) this.payload_);
            }
            if (this.payloadCase_ == 52) {
                i2 += CodedOutputStream.computeMessageSize(52, (System.GetUsers) this.payload_);
            }
            if (this.payloadCase_ == 53) {
                i2 += CodedOutputStream.computeMessageSize(53, (System.SwitchCurrentUser) this.payload_);
            }
            if (this.payloadCase_ == 54) {
                i2 += CodedOutputStream.computeMessageSize(54, (System.GetCurrentUser) this.payload_);
            }
            if (this.payloadCase_ == 55) {
                i2 += CodedOutputStream.computeMessageSize(55, (System.KeepAlive) this.payload_);
            }
            if (this.payloadCase_ == 56) {
                i2 += CodedOutputStream.computeMessageSize(56, (System.RemoveDevice) this.payload_);
            }
            if (this.payloadCase_ == 57) {
                i2 += CodedOutputStream.computeMessageSize(57, (System.GetLocales) this.payload_);
            }
            if (this.payloadCase_ == 58) {
                i2 += CodedOutputStream.computeMessageSize(58, (System.SetLocale) this.payload_);
            }
            if (this.payloadCase_ == 59) {
                i2 += CodedOutputStream.computeMessageSize(59, (System.LaunchApp) this.payload_);
            }
            if (this.payloadCase_ == 60) {
                i2 += CodedOutputStream.computeMessageSize(60, (Media.IssueMediaControl) this.payload_);
            }
            if (this.payloadCase_ == 61) {
                i2 += CodedOutputStream.computeMessageSize(61, (System.UpdateUsers) this.payload_);
            }
            if (this.payloadCase_ == 62) {
                i2 += CodedOutputStream.computeMessageSize(62, (System.ConnectUser) this.payload_);
            }
            if (this.payloadCase_ == 63) {
                i2 += CodedOutputStream.computeMessageSize(63, (System.DisconnectUser) this.payload_);
            }
            if (this.payloadCase_ == 64) {
                i2 += CodedOutputStream.computeMessageSize(64, (System.UnpairUser) this.payload_);
            }
            if (this.payloadCase_ == 65) {
                i2 += CodedOutputStream.computeMessageSize(65, (Media.GetPlaybackStatus) this.payload_);
            }
            if (this.payloadCase_ == 66) {
                i2 += CodedOutputStream.computeMessageSize(66, (Media.RegisterForMediaEvents) this.payload_);
            }
            if (this.payloadCase_ == 67) {
                i2 += CodedOutputStream.computeMessageSize(67, (Media.MediaEventOccurred) this.payload_);
            }
            if (this.payloadCase_ == 70) {
                i2 += CodedOutputStream.computeMessageSize(70, (System.SetWakewords) this.payload_);
            }
            if (this.payloadCase_ == 71) {
                i2 += CodedOutputStream.computeMessageSize(71, (System.GetWakewords) this.payload_);
            }
            if (this.payloadCase_ == 80) {
                i2 += CodedOutputStream.computeMessageSize(80, (Cardrendering.DisplayContent) this.payload_);
            }
            if (this.payloadCase_ == 86) {
                i2 += CodedOutputStream.computeMessageSize(86, (Firmware.FirmwareUpdateUnavailable) this.payload_);
            }
            if (this.payloadCase_ == 87) {
                i2 += CodedOutputStream.computeMessageSize(87, (Firmware.NotifyFirmwareInformation) this.payload_);
            }
            if (this.payloadCase_ == 88) {
                i2 += CodedOutputStream.computeMessageSize(88, (Firmware.InitiateFirmwareUpdate) this.payload_);
            }
            if (this.payloadCase_ == 89) {
                i2 += CodedOutputStream.computeMessageSize(89, (Firmware.VerifyArtifactSignature) this.payload_);
            }
            if (this.payloadCase_ == 90) {
                i2 += CodedOutputStream.computeMessageSize(90, (Firmware.GetFirmwareInformation) this.payload_);
            }
            if (this.payloadCase_ == 91) {
                i2 += CodedOutputStream.computeMessageSize(91, (Firmware.GetFirmwareUpdatePreferences) this.payload_);
            }
            if (this.payloadCase_ == 92) {
                i2 += CodedOutputStream.computeMessageSize(92, (Firmware.GetCachedComponent) this.payload_);
            }
            if (this.payloadCase_ == 93) {
                i2 += CodedOutputStream.computeMessageSize(93, (Firmware.ResetCachedComponent) this.payload_);
            }
            if (this.payloadCase_ == 94) {
                i2 += CodedOutputStream.computeMessageSize(94, (Firmware.UpdateComponentSegment) this.payload_);
            }
            if (this.payloadCase_ == 95) {
                i2 += CodedOutputStream.computeMessageSize(95, (Firmware.ApplyFirmware) this.payload_);
            }
            if (this.payloadCase_ == 96) {
                i2 += CodedOutputStream.computeMessageSize(96, (Firmware.GetDeviceArtifacts) this.payload_);
            }
            if (this.payloadCase_ == 97) {
                i2 += CodedOutputStream.computeMessageSize(97, (Firmware.GetArtifactFilter) this.payload_);
            }
            if (this.payloadCase_ == 98) {
                i2 += CodedOutputStream.computeMessageSize(98, (Firmware.GetArtifactUpdatePreference) this.payload_);
            }
            if (this.payloadCase_ == 99) {
                i2 += CodedOutputStream.computeMessageSize(99, (Firmware.StartFirmwareUpdate) this.payload_);
            }
            if (this.payloadCase_ == 100) {
                i2 += CodedOutputStream.computeMessageSize(100, (StateOuterClass.GetState) this.payload_);
            }
            if (this.payloadCase_ == 101) {
                i2 += CodedOutputStream.computeMessageSize(101, (StateOuterClass.SetState) this.payload_);
            }
            if (this.payloadCase_ == 102) {
                i2 += CodedOutputStream.computeMessageSize(102, (StateOuterClass.SynchronizeState) this.payload_);
            }
            if (this.payloadCase_ == 103) {
                i2 += CodedOutputStream.computeMessageSize(103, (Central.GetCentralInformation) this.payload_);
            }
            if (this.payloadCase_ == 110) {
                i2 += CodedOutputStream.computeMessageSize(110, (DiagnosticsOuterClass.GetDiagnostics) this.payload_);
            }
            if (this.payloadCase_ == 111) {
                i2 += CodedOutputStream.computeMessageSize(111, (DiagnosticsOuterClass.StopDiagnostics) this.payload_);
            }
            if (this.payloadCase_ == 112) {
                i2 += CodedOutputStream.computeMessageSize(112, (DiagnosticsOuterClass.NotifyDiagnosticsAvailable) this.payload_);
            }
            if (this.payloadCase_ == 120) {
                i2 += CodedOutputStream.computeMessageSize(120, (Metrics.PushMetrics) this.payload_);
            }
            if (this.payloadCase_ == 121) {
                i2 += CodedOutputStream.computeMessageSize(121, (Metrics.UpdateMetricsMap) this.payload_);
            }
            if (this.payloadCase_ == 130) {
                i2 += CodedOutputStream.computeMessageSize(130, (Fitness.GetFitnessData) this.payload_);
            }
            if (this.payloadCase_ == 131) {
                i2 += CodedOutputStream.computeMessageSize(131, (Fitness.StopFitnessData) this.payload_);
            }
            if (this.payloadCase_ == 132) {
                i2 += CodedOutputStream.computeMessageSize(132, (Fitness.NotifyFitnessDataAvailable) this.payload_);
            }
            if (this.payloadCase_ == 133) {
                i2 += CodedOutputStream.computeMessageSize(133, (Fitness.SyncFitnessSession) this.payload_);
            }
            if (this.payloadCase_ == 134) {
                i2 += CodedOutputStream.computeMessageSize(134, (Fitness.StartLiveFitnessData) this.payload_);
            }
            if (this.payloadCase_ == 135) {
                i2 += CodedOutputStream.computeMessageSize(135, (Fitness.StopLiveFitnessData) this.payload_);
            }
            if (this.payloadCase_ == 136) {
                i2 += CodedOutputStream.computeMessageSize(136, (Fitness.LiveFitnessData) this.payload_);
            }
            if (this.payloadCase_ == 140) {
                i2 += CodedOutputStream.computeMessageSize(140, (Nonhfpcalling.UpdateCallState) this.payload_);
            }
            if (this.payloadCase_ == 141) {
                i2 += CodedOutputStream.computeMessageSize(141, (Nonhfpcalling.AcceptCall) this.payload_);
            }
            if (this.payloadCase_ == 142) {
                i2 += CodedOutputStream.computeMessageSize(142, (Nonhfpcalling.RejectCall) this.payload_);
            }
            if (this.payloadCase_ == 143) {
                i2 += CodedOutputStream.computeMessageSize(143, (Nonhfpcalling.EndCall) this.payload_);
            }
            if (this.payloadCase_ == 150) {
                i2 += CodedOutputStream.computeMessageSize(150, (Ancs.SubscribeNotificationCenter) this.payload_);
            }
            if (this.payloadCase_ == 151) {
                i2 += CodedOutputStream.computeMessageSize(151, (Ancs.UnsubscribeNotificationCenter) this.payload_);
            }
            if (this.payloadCase_ == 152) {
                i2 += CodedOutputStream.computeMessageSize(152, (Ancs.PublishCentralNotification) this.payload_);
            }
            if (this.payloadCase_ == 153) {
                i2 += CodedOutputStream.computeMessageSize(153, (Ancs.GetCentralNotificationAttributes) this.payload_);
            }
            if (this.payloadCase_ == 154) {
                i2 += CodedOutputStream.computeMessageSize(154, (Ancs.GetCentralNotificationAppAttributes) this.payload_);
            }
            if (this.payloadCase_ == 155) {
                i2 += CodedOutputStream.computeMessageSize(155, (Ancs.PerformCentralNotificationAction) this.payload_);
            }
            if (this.payloadCase_ == 156) {
                i2 += CodedOutputStream.computeMessageSize(156, (Ancs.UpdateCentralNotificationAttributes) this.payload_);
            }
            if (this.payloadCase_ == 160) {
                i2 += CodedOutputStream.computeMessageSize(160, (Input.IssueInputEvent) this.payload_);
            }
            if (this.payloadCase_ == 161) {
                i2 += CodedOutputStream.computeMessageSize(161, (Input.SetInputBehavior) this.payload_);
            }
            if (this.payloadCase_ == 162) {
                i2 += CodedOutputStream.computeMessageSize(162, (Input.GetInputBehavior) this.payload_);
            }
            if (this.payloadCase_ == 163) {
                i2 += CodedOutputStream.computeMessageSize(163, (Instrumentation.PrintDebug) this.payload_);
            }
            if (this.payloadCase_ == 164) {
                i2 += CodedOutputStream.computeMessageSize(164, (Instrumentation.IssueRemoteCommand) this.payload_);
            }
            if (this.payloadCase_ == 165) {
                i2 += CodedOutputStream.computeMessageSize(165, (Instrumentation.IssueRemoteRestart) this.payload_);
            }
            if (this.payloadCase_ == 166) {
                i2 += CodedOutputStream.computeMessageSize(166, (Instrumentation.IssueRemoteReset) this.payload_);
            }
            if (this.payloadCase_ == 167) {
                i2 += CodedOutputStream.computeMessageSize(167, (Instrumentation.IssueRemoteClearPairing) this.payload_);
            }
            if (this.payloadCase_ == 168) {
                i2 += CodedOutputStream.computeMessageSize(168, (Input.ResetInputBehavior) this.payload_);
            }
            if (this.payloadCase_ == 170) {
                i2 += CodedOutputStream.computeMessageSize(170, (Translation.StartTranslation) this.payload_);
            }
            if (this.payloadCase_ == 171) {
                i2 += CodedOutputStream.computeMessageSize(171, (Translation.ProvideTranslation) this.payload_);
            }
            if (this.payloadCase_ == 172) {
                i2 += CodedOutputStream.computeMessageSize(172, (Translation.StopTranslation) this.payload_);
            }
            if (this.payloadCase_ == 180) {
                i2 += CodedOutputStream.computeMessageSize(180, (Bulkdata.GetBulkDataManifest) this.payload_);
            }
            if (this.payloadCase_ == 181) {
                i2 += CodedOutputStream.computeMessageSize(181, (Bulkdata.BulkDataManifestEntry) this.payload_);
            }
            if (this.payloadCase_ == 182) {
                i2 += CodedOutputStream.computeMessageSize(182, (Bulkdata.RequestBulkDataTransfer) this.payload_);
            }
            if (this.payloadCase_ == 183) {
                i2 += CodedOutputStream.computeMessageSize(183, (Bulkdata.BulkDataTransferStart) this.payload_);
            }
            if (this.payloadCase_ == 184) {
                i2 += CodedOutputStream.computeMessageSize(184, (Bulkdata.BulkDataTransferComplete) this.payload_);
            }
            if (this.payloadCase_ == 185) {
                i2 += CodedOutputStream.computeMessageSize(185, (Bulkdata.StopBulkDataTransfer) this.payload_);
            }
            if (this.payloadCase_ == 186) {
                i2 += CodedOutputStream.computeMessageSize(186, (Bulkdata.NotifyBulkDataAvailable) this.payload_);
            }
            if (this.payloadCase_ == 190) {
                i2 += CodedOutputStream.computeMessageSize(190, (Notification.AddNotification) this.payload_);
            }
            if (this.payloadCase_ == 191) {
                i2 += CodedOutputStream.computeMessageSize(191, (Notification.UpdateNotification) this.payload_);
            }
            if (this.payloadCase_ == 192) {
                i2 += CodedOutputStream.computeMessageSize(192, (Notification.RemoveNotification) this.payload_);
            }
            if (this.payloadCase_ == 193) {
                i2 += CodedOutputStream.computeMessageSize(193, (Notification.ExecuteNotificationAction) this.payload_);
            }
            if (this.payloadCase_ == 200) {
                i2 += CodedOutputStream.computeMessageSize(200, (Keyexchange.InitiateHandshake) this.payload_);
            }
            if (this.payloadCase_ == 201) {
                i2 += CodedOutputStream.computeMessageSize(201, (Keyexchange.CompleteHandshake) this.payload_);
            }
            if (this.payloadCase_ == 202) {
                i2 += CodedOutputStream.computeMessageSize(202, (Keyexchange.UserConfirmed) this.payload_);
            }
            if (this.payloadCase_ == 203) {
                i2 += CodedOutputStream.computeMessageSize(203, (Keyexchange.ResetKey) this.payload_);
            }
            if (this.payloadCase_ == 204) {
                i2 += CodedOutputStream.computeMessageSize(204, (Keyexchange.ConfirmResetKey) this.payload_);
            }
            if (this.payloadCase_ == 205) {
                i2 += CodedOutputStream.computeMessageSize(205, (Keyexchange.ResetRootKeys) this.payload_);
            }
            if (this.payloadCase_ == 230) {
                i2 += CodedOutputStream.computeMessageSize(230, (Cbl.GetCblLoginState) this.payload_);
            }
            if (this.payloadCase_ == 231) {
                i2 += CodedOutputStream.computeMessageSize(231, (Cbl.GetCblInformation) this.payload_);
            }
            if (this.payloadCase_ == 232) {
                i2 += CodedOutputStream.computeMessageSize(232, (Cbl.NotifyCblLoginState) this.payload_);
            }
            if (this.payloadCase_ == 300) {
                i2 += CodedOutputStream.computeMessageSize(300, (Hearing.GetAudiogram) this.payload_);
            }
            if (this.payloadCase_ == 301) {
                i2 += CodedOutputStream.computeMessageSize(301, (Hearing.SetAudiogram) this.payload_);
            }
            if (this.payloadCase_ == 302) {
                i2 += CodedOutputStream.computeMessageSize(302, (Hearing.GetMediaEnhancementCorrectionAmount) this.payload_);
            }
            if (this.payloadCase_ == 303) {
                i2 += CodedOutputStream.computeMessageSize(303, (Hearing.SetMediaEnhancementCorrectionAmount) this.payload_);
            }
            if (this.payloadCase_ == 350) {
                i2 += CodedOutputStream.computeMessageSize(350, (Mapsms.GetSmsMessageListResponse) this.payload_);
            }
            if (this.payloadCase_ == 351) {
                i2 += CodedOutputStream.computeMessageSize(351, (Mapsms.NotifySmsMessageList) this.payload_);
            }
            if (this.payloadCase_ == 352) {
                i2 += CodedOutputStream.computeMessageSize(352, (Mapsms.GetSmsMessageList) this.payload_);
            }
            if (this.payloadCase_ == 353) {
                i2 += CodedOutputStream.computeMessageSize(353, (Mapsms.SendSms) this.payload_);
            }
            if (this.payloadCase_ == 354) {
                i2 += CodedOutputStream.computeMessageSize(354, (Mapsms.SetSmsReadStatus) this.payload_);
            }
            if (this.payloadCase_ == 355) {
                i2 += CodedOutputStream.computeMessageSize(355, (Mapsms.EndOfSmsMessageListResponse) this.payload_);
            }
            if (this.payloadCase_ == 356) {
                i2 += CodedOutputStream.computeMessageSize(356, (Mapsms.InitiateMapConnection) this.payload_);
            }
            if (this.payloadCase_ == 360) {
                i2 += CodedOutputStream.computeMessageSize(360, (Voicestream.StartVoiceStream) this.payload_);
            }
            if (this.payloadCase_ == 361) {
                i2 += CodedOutputStream.computeMessageSize(361, (Voicestream.StopVoiceStream) this.payload_);
            }
            if (this.payloadCase_ == 370) {
                i2 += CodedOutputStream.computeMessageSize(370, (Cloudpairing.GetCloudPairingAttributes) this.payload_);
            }
            if (this.payloadCase_ == 371) {
                i2 += CodedOutputStream.computeMessageSize(371, (Cloudpairing.GetCloudPairingStatus) this.payload_);
            }
            if (this.payloadCase_ == 372) {
                i2 += CodedOutputStream.computeMessageSize(372, (Cloudpairing.SetCloudPairingKeys) this.payload_);
            }
            if (this.payloadCase_ == 373) {
                i2 += CodedOutputStream.computeMessageSize(373, (Cloudpairing.ReplaceCloudPairingKeys) this.payload_);
            }
            if (this.payloadCase_ == 374) {
                i2 += CodedOutputStream.computeMessageSize(374, (Cloudpairing.RemoveCloudPairingKeys) this.payload_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Hearing.SetAudiogram getSetAudiogram() {
            if (this.payloadCase_ == 301) {
                return (Hearing.SetAudiogram) this.payload_;
            }
            return Hearing.SetAudiogram.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Cloudpairing.SetCloudPairingKeys getSetCloudPairingKeys() {
            if (this.payloadCase_ == 372) {
                return (Cloudpairing.SetCloudPairingKeys) this.payload_;
            }
            return Cloudpairing.SetCloudPairingKeys.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Input.SetInputBehavior getSetInputBehavior() {
            if (this.payloadCase_ == 161) {
                return (Input.SetInputBehavior) this.payload_;
            }
            return Input.SetInputBehavior.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.SetLocale getSetLocale() {
            if (this.payloadCase_ == 58) {
                return (System.SetLocale) this.payload_;
            }
            return System.SetLocale.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Hearing.SetMediaEnhancementCorrectionAmount getSetMediaEnhancementCorrectionAmount() {
            if (this.payloadCase_ == 303) {
                return (Hearing.SetMediaEnhancementCorrectionAmount) this.payload_;
            }
            return Hearing.SetMediaEnhancementCorrectionAmount.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Mapsms.SetSmsReadStatus getSetSmsReadStatus() {
            if (this.payloadCase_ == 354) {
                return (Mapsms.SetSmsReadStatus) this.payload_;
            }
            return Mapsms.SetSmsReadStatus.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public StateOuterClass.SetState getSetState() {
            if (this.payloadCase_ == 101) {
                return (StateOuterClass.SetState) this.payload_;
            }
            return StateOuterClass.SetState.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.SetWakewords getSetWakewords() {
            if (this.payloadCase_ == 70) {
                return (System.SetWakewords) this.payload_;
            }
            return System.SetWakewords.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Firmware.StartFirmwareUpdate getStartFirmwareUpdate() {
            if (this.payloadCase_ == 99) {
                return (Firmware.StartFirmwareUpdate) this.payload_;
            }
            return Firmware.StartFirmwareUpdate.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Fitness.StartLiveFitnessData getStartLiveFitnessData() {
            if (this.payloadCase_ == 134) {
                return (Fitness.StartLiveFitnessData) this.payload_;
            }
            return Fitness.StartLiveFitnessData.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Device.StartSetup getStartSetup() {
            if (this.payloadCase_ == 23) {
                return (Device.StartSetup) this.payload_;
            }
            return Device.StartSetup.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Speech.StartSpeech getStartSpeech() {
            if (this.payloadCase_ == 11) {
                return (Speech.StartSpeech) this.payload_;
            }
            return Speech.StartSpeech.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Translation.StartTranslation getStartTranslation() {
            if (this.payloadCase_ == 170) {
                return (Translation.StartTranslation) this.payload_;
            }
            return Translation.StartTranslation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Voicestream.StartVoiceStream getStartVoiceStream() {
            if (this.payloadCase_ == 360) {
                return (Voicestream.StartVoiceStream) this.payload_;
            }
            return Voicestream.StartVoiceStream.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Bulkdata.StopBulkDataTransfer getStopBulkDataTransfer() {
            if (this.payloadCase_ == 185) {
                return (Bulkdata.StopBulkDataTransfer) this.payload_;
            }
            return Bulkdata.StopBulkDataTransfer.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public DiagnosticsOuterClass.StopDiagnostics getStopDiagnostics() {
            if (this.payloadCase_ == 111) {
                return (DiagnosticsOuterClass.StopDiagnostics) this.payload_;
            }
            return DiagnosticsOuterClass.StopDiagnostics.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Fitness.StopFitnessData getStopFitnessData() {
            if (this.payloadCase_ == 131) {
                return (Fitness.StopFitnessData) this.payload_;
            }
            return Fitness.StopFitnessData.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Fitness.StopLiveFitnessData getStopLiveFitnessData() {
            if (this.payloadCase_ == 135) {
                return (Fitness.StopLiveFitnessData) this.payload_;
            }
            return Fitness.StopLiveFitnessData.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Speech.StopSpeech getStopSpeech() {
            if (this.payloadCase_ == 12) {
                return (Speech.StopSpeech) this.payload_;
            }
            return Speech.StopSpeech.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Translation.StopTranslation getStopTranslation() {
            if (this.payloadCase_ == 172) {
                return (Translation.StopTranslation) this.payload_;
            }
            return Translation.StopTranslation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Voicestream.StopVoiceStream getStopVoiceStream() {
            if (this.payloadCase_ == 361) {
                return (Voicestream.StopVoiceStream) this.payload_;
            }
            return Voicestream.StopVoiceStream.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Ancs.SubscribeNotificationCenter getSubscribeNotificationCenter() {
            if (this.payloadCase_ == 150) {
                return (Ancs.SubscribeNotificationCenter) this.payload_;
            }
            return Ancs.SubscribeNotificationCenter.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.SwitchCurrentUser getSwitchCurrentUser() {
            if (this.payloadCase_ == 53) {
                return (System.SwitchCurrentUser) this.payload_;
            }
            return System.SwitchCurrentUser.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Transport.SwitchTransport getSwitchTransport() {
            if (this.payloadCase_ == 31) {
                return (Transport.SwitchTransport) this.payload_;
            }
            return Transport.SwitchTransport.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Fitness.SyncFitnessSession getSyncFitnessSession() {
            if (this.payloadCase_ == 133) {
                return (Fitness.SyncFitnessSession) this.payload_;
            }
            return Fitness.SyncFitnessSession.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.SynchronizeSettings getSynchronizeSettings() {
            if (this.payloadCase_ == 50) {
                return (System.SynchronizeSettings) this.payload_;
            }
            return System.SynchronizeSettings.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public StateOuterClass.SynchronizeState getSynchronizeState() {
            if (this.payloadCase_ == 102) {
                return (StateOuterClass.SynchronizeState) this.payload_;
            }
            return StateOuterClass.SynchronizeState.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.UnpairUser getUnpairUser() {
            if (this.payloadCase_ == 64) {
                return (System.UnpairUser) this.payload_;
            }
            return System.UnpairUser.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Ancs.UnsubscribeNotificationCenter getUnsubscribeNotificationCenter() {
            if (this.payloadCase_ == 151) {
                return (Ancs.UnsubscribeNotificationCenter) this.payload_;
            }
            return Ancs.UnsubscribeNotificationCenter.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Nonhfpcalling.UpdateCallState getUpdateCallState() {
            if (this.payloadCase_ == 140) {
                return (Nonhfpcalling.UpdateCallState) this.payload_;
            }
            return Nonhfpcalling.UpdateCallState.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Ancs.UpdateCentralNotificationAttributes getUpdateCentralNotificationAttributes() {
            if (this.payloadCase_ == 156) {
                return (Ancs.UpdateCentralNotificationAttributes) this.payload_;
            }
            return Ancs.UpdateCentralNotificationAttributes.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Firmware.UpdateComponentSegment getUpdateComponentSegment() {
            if (this.payloadCase_ == 94) {
                return (Firmware.UpdateComponentSegment) this.payload_;
            }
            return Firmware.UpdateComponentSegment.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Device.UpdateDeviceInformation getUpdateDeviceInformation() {
            if (this.payloadCase_ == 26) {
                return (Device.UpdateDeviceInformation) this.payload_;
            }
            return Device.UpdateDeviceInformation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Metrics.UpdateMetricsMap getUpdateMetricsMap() {
            if (this.payloadCase_ == 121) {
                return (Metrics.UpdateMetricsMap) this.payload_;
            }
            return Metrics.UpdateMetricsMap.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Notification.UpdateNotification getUpdateNotification() {
            if (this.payloadCase_ == 191) {
                return (Notification.UpdateNotification) this.payload_;
            }
            return Notification.UpdateNotification.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public System.UpdateUsers getUpdateUsers() {
            if (this.payloadCase_ == 61) {
                return (System.UpdateUsers) this.payload_;
            }
            return System.UpdateUsers.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Transport.UpgradeTransport getUpgradeTransport() {
            if (this.payloadCase_ == 30) {
                return (Transport.UpgradeTransport) this.payload_;
            }
            return Transport.UpgradeTransport.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Keyexchange.UserConfirmed getUserConfirmed() {
            if (this.payloadCase_ == 202) {
                return (Keyexchange.UserConfirmed) this.payload_;
            }
            return Keyexchange.UserConfirmed.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public Firmware.VerifyArtifactSignature getVerifyArtifactSignature() {
            if (this.payloadCase_ == 89) {
                return (Firmware.VerifyArtifactSignature) this.payload_;
            }
            return Firmware.VerifyArtifactSignature.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasAcceptCall() {
            return this.payloadCase_ == 141;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasAddNotification() {
            return this.payloadCase_ == 190;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasApplyFirmware() {
            return this.payloadCase_ == 95;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasBulkDataManifestEntry() {
            return this.payloadCase_ == 181;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasBulkDataTransferComplete() {
            return this.payloadCase_ == 184;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasBulkDataTransferStart() {
            return this.payloadCase_ == 183;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasCompleteHandshake() {
            return this.payloadCase_ == 201;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasCompleteSetup() {
            return this.payloadCase_ == 24;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasConfirmResetKey() {
            return this.payloadCase_ == 204;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasConnectUser() {
            return this.payloadCase_ == 62;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasDisconnectUser() {
            return this.payloadCase_ == 63;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasDisplayContent() {
            return this.payloadCase_ == 80;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasEndCall() {
            return this.payloadCase_ == 143;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasEndOfSmsMessageListResponse() {
            return this.payloadCase_ == 355;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasEndpointSpeech() {
            return this.payloadCase_ == 13;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasExecuteNotificationAction() {
            return this.payloadCase_ == 193;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasFirmwareUpdateUnavailable() {
            return this.payloadCase_ == 86;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasForwardAtCommand() {
            return this.payloadCase_ == 40;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetArtifactFilter() {
            return this.payloadCase_ == 97;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetArtifactUpdatePreference() {
            return this.payloadCase_ == 98;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetAudiogram() {
            return this.payloadCase_ == 300;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetBulkDataManifest() {
            return this.payloadCase_ == 180;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetCachedComponent() {
            return this.payloadCase_ == 92;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetCblInformation() {
            return this.payloadCase_ == 231;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetCblLoginState() {
            return this.payloadCase_ == 230;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetCentralInformation() {
            return this.payloadCase_ == 103;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetCentralNotificationAppAttributes() {
            return this.payloadCase_ == 154;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetCentralNotificationAttributes() {
            return this.payloadCase_ == 153;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetCloudPairingAttributes() {
            return this.payloadCase_ == 370;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetCloudPairingStatus() {
            return this.payloadCase_ == 371;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetCurrentUser() {
            return this.payloadCase_ == 54;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetDeviceArtifacts() {
            return this.payloadCase_ == 96;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetDeviceConfiguration() {
            return this.payloadCase_ == 21;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetDeviceFeatures() {
            return this.payloadCase_ == 28;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetDeviceInformation() {
            return this.payloadCase_ == 20;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetDiagnostics() {
            return this.payloadCase_ == 110;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetFirmwareInformation() {
            return this.payloadCase_ == 90;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetFirmwareUpdatePreferences() {
            return this.payloadCase_ == 91;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetFitnessData() {
            return this.payloadCase_ == 130;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetInputBehavior() {
            return this.payloadCase_ == 162;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetLocales() {
            return this.payloadCase_ == 57;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetMediaEnhancementCorrectionAmount() {
            return this.payloadCase_ == 302;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetPlaybackStatus() {
            return this.payloadCase_ == 65;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetSmsMessageList() {
            return this.payloadCase_ == 352;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetSmsMessageListResponse() {
            return this.payloadCase_ == 350;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetState() {
            return this.payloadCase_ == 100;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetUsers() {
            return this.payloadCase_ == 52;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasGetWakewords() {
            return this.payloadCase_ == 71;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasIncomingCall() {
            return this.payloadCase_ == 41;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasInitiateFirmwareUpdate() {
            return this.payloadCase_ == 88;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasInitiateHandshake() {
            return this.payloadCase_ == 200;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasInitiateMapConnection() {
            return this.payloadCase_ == 356;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasIssueInputEvent() {
            return this.payloadCase_ == 160;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasIssueMediaControl() {
            return this.payloadCase_ == 60;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasIssueRemoteClearPairing() {
            return this.payloadCase_ == 167;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasIssueRemoteCommand() {
            return this.payloadCase_ == 164;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasIssueRemoteReset() {
            return this.payloadCase_ == 166;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasIssueRemoteRestart() {
            return this.payloadCase_ == 165;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasKeepAlive() {
            return this.payloadCase_ == 55;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasLaunchApp() {
            return this.payloadCase_ == 59;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasLiveFitnessData() {
            return this.payloadCase_ == 136;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasMediaEventOccurred() {
            return this.payloadCase_ == 67;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasNotifyBulkDataAvailable() {
            return this.payloadCase_ == 186;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasNotifyCblLoginState() {
            return this.payloadCase_ == 232;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasNotifyDeviceConfiguration() {
            return this.payloadCase_ == 25;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasNotifyDeviceInformation() {
            return this.payloadCase_ == 27;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasNotifyDiagnosticsAvailable() {
            return this.payloadCase_ == 112;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasNotifyFirmwareInformation() {
            return this.payloadCase_ == 87;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasNotifyFitnessDataAvailable() {
            return this.payloadCase_ == 132;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasNotifySmsMessageList() {
            return this.payloadCase_ == 351;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasNotifySpeechState() {
            return this.payloadCase_ == 14;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasOverrideAssistant() {
            return this.payloadCase_ == 22;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasPerformCentralNotificationAction() {
            return this.payloadCase_ == 155;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasPrintDebug() {
            return this.payloadCase_ == 163;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasProvideSpeech() {
            return this.payloadCase_ == 10;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasProvideTranslation() {
            return this.payloadCase_ == 171;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasPublishCentralNotification() {
            return this.payloadCase_ == 152;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasPushMetrics() {
            return this.payloadCase_ == 120;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasRegisterForMediaEvents() {
            return this.payloadCase_ == 66;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasRejectCall() {
            return this.payloadCase_ == 142;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasRemoveCloudPairingKeys() {
            return this.payloadCase_ == 374;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasRemoveDevice() {
            return this.payloadCase_ == 56;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasRemoveNotification() {
            return this.payloadCase_ == 192;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasReplaceCloudPairingKeys() {
            return this.payloadCase_ == 373;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasRequestBulkDataTransfer() {
            return this.payloadCase_ == 182;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasResetCachedComponent() {
            return this.payloadCase_ == 93;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasResetConnection() {
            return this.payloadCase_ == 51;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasResetInputBehavior() {
            return this.payloadCase_ == 168;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasResetKey() {
            return this.payloadCase_ == 203;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasResetRootKeys() {
            return this.payloadCase_ == 205;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasResponse() {
            return this.payloadCase_ == 9;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSendSms() {
            return this.payloadCase_ == 353;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSetAudiogram() {
            return this.payloadCase_ == 301;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSetCloudPairingKeys() {
            return this.payloadCase_ == 372;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSetInputBehavior() {
            return this.payloadCase_ == 161;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSetLocale() {
            return this.payloadCase_ == 58;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSetMediaEnhancementCorrectionAmount() {
            return this.payloadCase_ == 303;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSetSmsReadStatus() {
            return this.payloadCase_ == 354;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSetState() {
            return this.payloadCase_ == 101;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSetWakewords() {
            return this.payloadCase_ == 70;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasStartFirmwareUpdate() {
            return this.payloadCase_ == 99;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasStartLiveFitnessData() {
            return this.payloadCase_ == 134;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasStartSetup() {
            return this.payloadCase_ == 23;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasStartSpeech() {
            return this.payloadCase_ == 11;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasStartTranslation() {
            return this.payloadCase_ == 170;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasStartVoiceStream() {
            return this.payloadCase_ == 360;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasStopBulkDataTransfer() {
            return this.payloadCase_ == 185;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasStopDiagnostics() {
            return this.payloadCase_ == 111;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasStopFitnessData() {
            return this.payloadCase_ == 131;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasStopLiveFitnessData() {
            return this.payloadCase_ == 135;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasStopSpeech() {
            return this.payloadCase_ == 12;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasStopTranslation() {
            return this.payloadCase_ == 172;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasStopVoiceStream() {
            return this.payloadCase_ == 361;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSubscribeNotificationCenter() {
            return this.payloadCase_ == 150;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSwitchCurrentUser() {
            return this.payloadCase_ == 53;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSwitchTransport() {
            return this.payloadCase_ == 31;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSyncFitnessSession() {
            return this.payloadCase_ == 133;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSynchronizeSettings() {
            return this.payloadCase_ == 50;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasSynchronizeState() {
            return this.payloadCase_ == 102;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasUnpairUser() {
            return this.payloadCase_ == 64;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasUnsubscribeNotificationCenter() {
            return this.payloadCase_ == 151;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasUpdateCallState() {
            return this.payloadCase_ == 140;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasUpdateCentralNotificationAttributes() {
            return this.payloadCase_ == 156;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasUpdateComponentSegment() {
            return this.payloadCase_ == 94;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasUpdateDeviceInformation() {
            return this.payloadCase_ == 26;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasUpdateMetricsMap() {
            return this.payloadCase_ == 121;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasUpdateNotification() {
            return this.payloadCase_ == 191;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasUpdateUsers() {
            return this.payloadCase_ == 61;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasUpgradeTransport() {
            return this.payloadCase_ == 30;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasUserConfirmed() {
            return this.payloadCase_ == 202;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ControlEnvelopeOrBuilder
        public boolean hasVerifyArtifactSignature() {
            return this.payloadCase_ == 89;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.command_ != Command.NONE.getNumber()) {
                codedOutputStream.writeEnum(1, this.command_);
            }
            if (this.payloadCase_ == 9) {
                codedOutputStream.writeMessage(9, (Response) this.payload_);
            }
            if (this.payloadCase_ == 10) {
                codedOutputStream.writeMessage(10, (Speech.ProvideSpeech) this.payload_);
            }
            if (this.payloadCase_ == 11) {
                codedOutputStream.writeMessage(11, (Speech.StartSpeech) this.payload_);
            }
            if (this.payloadCase_ == 12) {
                codedOutputStream.writeMessage(12, (Speech.StopSpeech) this.payload_);
            }
            if (this.payloadCase_ == 13) {
                codedOutputStream.writeMessage(13, (Speech.EndpointSpeech) this.payload_);
            }
            if (this.payloadCase_ == 14) {
                codedOutputStream.writeMessage(14, (Speech.NotifySpeechState) this.payload_);
            }
            if (this.payloadCase_ == 20) {
                codedOutputStream.writeMessage(20, (Device.GetDeviceInformation) this.payload_);
            }
            if (this.payloadCase_ == 21) {
                codedOutputStream.writeMessage(21, (Device.GetDeviceConfiguration) this.payload_);
            }
            if (this.payloadCase_ == 22) {
                codedOutputStream.writeMessage(22, (Device.OverrideAssistant) this.payload_);
            }
            if (this.payloadCase_ == 23) {
                codedOutputStream.writeMessage(23, (Device.StartSetup) this.payload_);
            }
            if (this.payloadCase_ == 24) {
                codedOutputStream.writeMessage(24, (Device.CompleteSetup) this.payload_);
            }
            if (this.payloadCase_ == 25) {
                codedOutputStream.writeMessage(25, (Device.NotifyDeviceConfiguration) this.payload_);
            }
            if (this.payloadCase_ == 26) {
                codedOutputStream.writeMessage(26, (Device.UpdateDeviceInformation) this.payload_);
            }
            if (this.payloadCase_ == 27) {
                codedOutputStream.writeMessage(27, (Device.NotifyDeviceInformation) this.payload_);
            }
            if (this.payloadCase_ == 28) {
                codedOutputStream.writeMessage(28, (Device.GetDeviceFeatures) this.payload_);
            }
            if (this.payloadCase_ == 30) {
                codedOutputStream.writeMessage(30, (Transport.UpgradeTransport) this.payload_);
            }
            if (this.payloadCase_ == 31) {
                codedOutputStream.writeMessage(31, (Transport.SwitchTransport) this.payload_);
            }
            if (this.payloadCase_ == 40) {
                codedOutputStream.writeMessage(40, (Calling.ForwardATCommand) this.payload_);
            }
            if (this.payloadCase_ == 41) {
                codedOutputStream.writeMessage(41, (Calling.IncomingCall) this.payload_);
            }
            if (this.payloadCase_ == 50) {
                codedOutputStream.writeMessage(50, (System.SynchronizeSettings) this.payload_);
            }
            if (this.payloadCase_ == 51) {
                codedOutputStream.writeMessage(51, (System.ResetConnection) this.payload_);
            }
            if (this.payloadCase_ == 52) {
                codedOutputStream.writeMessage(52, (System.GetUsers) this.payload_);
            }
            if (this.payloadCase_ == 53) {
                codedOutputStream.writeMessage(53, (System.SwitchCurrentUser) this.payload_);
            }
            if (this.payloadCase_ == 54) {
                codedOutputStream.writeMessage(54, (System.GetCurrentUser) this.payload_);
            }
            if (this.payloadCase_ == 55) {
                codedOutputStream.writeMessage(55, (System.KeepAlive) this.payload_);
            }
            if (this.payloadCase_ == 56) {
                codedOutputStream.writeMessage(56, (System.RemoveDevice) this.payload_);
            }
            if (this.payloadCase_ == 57) {
                codedOutputStream.writeMessage(57, (System.GetLocales) this.payload_);
            }
            if (this.payloadCase_ == 58) {
                codedOutputStream.writeMessage(58, (System.SetLocale) this.payload_);
            }
            if (this.payloadCase_ == 59) {
                codedOutputStream.writeMessage(59, (System.LaunchApp) this.payload_);
            }
            if (this.payloadCase_ == 60) {
                codedOutputStream.writeMessage(60, (Media.IssueMediaControl) this.payload_);
            }
            if (this.payloadCase_ == 61) {
                codedOutputStream.writeMessage(61, (System.UpdateUsers) this.payload_);
            }
            if (this.payloadCase_ == 62) {
                codedOutputStream.writeMessage(62, (System.ConnectUser) this.payload_);
            }
            if (this.payloadCase_ == 63) {
                codedOutputStream.writeMessage(63, (System.DisconnectUser) this.payload_);
            }
            if (this.payloadCase_ == 64) {
                codedOutputStream.writeMessage(64, (System.UnpairUser) this.payload_);
            }
            if (this.payloadCase_ == 65) {
                codedOutputStream.writeMessage(65, (Media.GetPlaybackStatus) this.payload_);
            }
            if (this.payloadCase_ == 66) {
                codedOutputStream.writeMessage(66, (Media.RegisterForMediaEvents) this.payload_);
            }
            if (this.payloadCase_ == 67) {
                codedOutputStream.writeMessage(67, (Media.MediaEventOccurred) this.payload_);
            }
            if (this.payloadCase_ == 70) {
                codedOutputStream.writeMessage(70, (System.SetWakewords) this.payload_);
            }
            if (this.payloadCase_ == 71) {
                codedOutputStream.writeMessage(71, (System.GetWakewords) this.payload_);
            }
            if (this.payloadCase_ == 80) {
                codedOutputStream.writeMessage(80, (Cardrendering.DisplayContent) this.payload_);
            }
            if (this.payloadCase_ == 86) {
                codedOutputStream.writeMessage(86, (Firmware.FirmwareUpdateUnavailable) this.payload_);
            }
            if (this.payloadCase_ == 87) {
                codedOutputStream.writeMessage(87, (Firmware.NotifyFirmwareInformation) this.payload_);
            }
            if (this.payloadCase_ == 88) {
                codedOutputStream.writeMessage(88, (Firmware.InitiateFirmwareUpdate) this.payload_);
            }
            if (this.payloadCase_ == 89) {
                codedOutputStream.writeMessage(89, (Firmware.VerifyArtifactSignature) this.payload_);
            }
            if (this.payloadCase_ == 90) {
                codedOutputStream.writeMessage(90, (Firmware.GetFirmwareInformation) this.payload_);
            }
            if (this.payloadCase_ == 91) {
                codedOutputStream.writeMessage(91, (Firmware.GetFirmwareUpdatePreferences) this.payload_);
            }
            if (this.payloadCase_ == 92) {
                codedOutputStream.writeMessage(92, (Firmware.GetCachedComponent) this.payload_);
            }
            if (this.payloadCase_ == 93) {
                codedOutputStream.writeMessage(93, (Firmware.ResetCachedComponent) this.payload_);
            }
            if (this.payloadCase_ == 94) {
                codedOutputStream.writeMessage(94, (Firmware.UpdateComponentSegment) this.payload_);
            }
            if (this.payloadCase_ == 95) {
                codedOutputStream.writeMessage(95, (Firmware.ApplyFirmware) this.payload_);
            }
            if (this.payloadCase_ == 96) {
                codedOutputStream.writeMessage(96, (Firmware.GetDeviceArtifacts) this.payload_);
            }
            if (this.payloadCase_ == 97) {
                codedOutputStream.writeMessage(97, (Firmware.GetArtifactFilter) this.payload_);
            }
            if (this.payloadCase_ == 98) {
                codedOutputStream.writeMessage(98, (Firmware.GetArtifactUpdatePreference) this.payload_);
            }
            if (this.payloadCase_ == 99) {
                codedOutputStream.writeMessage(99, (Firmware.StartFirmwareUpdate) this.payload_);
            }
            if (this.payloadCase_ == 100) {
                codedOutputStream.writeMessage(100, (StateOuterClass.GetState) this.payload_);
            }
            if (this.payloadCase_ == 101) {
                codedOutputStream.writeMessage(101, (StateOuterClass.SetState) this.payload_);
            }
            if (this.payloadCase_ == 102) {
                codedOutputStream.writeMessage(102, (StateOuterClass.SynchronizeState) this.payload_);
            }
            if (this.payloadCase_ == 103) {
                codedOutputStream.writeMessage(103, (Central.GetCentralInformation) this.payload_);
            }
            if (this.payloadCase_ == 110) {
                codedOutputStream.writeMessage(110, (DiagnosticsOuterClass.GetDiagnostics) this.payload_);
            }
            if (this.payloadCase_ == 111) {
                codedOutputStream.writeMessage(111, (DiagnosticsOuterClass.StopDiagnostics) this.payload_);
            }
            if (this.payloadCase_ == 112) {
                codedOutputStream.writeMessage(112, (DiagnosticsOuterClass.NotifyDiagnosticsAvailable) this.payload_);
            }
            if (this.payloadCase_ == 120) {
                codedOutputStream.writeMessage(120, (Metrics.PushMetrics) this.payload_);
            }
            if (this.payloadCase_ == 121) {
                codedOutputStream.writeMessage(121, (Metrics.UpdateMetricsMap) this.payload_);
            }
            if (this.payloadCase_ == 130) {
                codedOutputStream.writeMessage(130, (Fitness.GetFitnessData) this.payload_);
            }
            if (this.payloadCase_ == 131) {
                codedOutputStream.writeMessage(131, (Fitness.StopFitnessData) this.payload_);
            }
            if (this.payloadCase_ == 132) {
                codedOutputStream.writeMessage(132, (Fitness.NotifyFitnessDataAvailable) this.payload_);
            }
            if (this.payloadCase_ == 133) {
                codedOutputStream.writeMessage(133, (Fitness.SyncFitnessSession) this.payload_);
            }
            if (this.payloadCase_ == 134) {
                codedOutputStream.writeMessage(134, (Fitness.StartLiveFitnessData) this.payload_);
            }
            if (this.payloadCase_ == 135) {
                codedOutputStream.writeMessage(135, (Fitness.StopLiveFitnessData) this.payload_);
            }
            if (this.payloadCase_ == 136) {
                codedOutputStream.writeMessage(136, (Fitness.LiveFitnessData) this.payload_);
            }
            if (this.payloadCase_ == 140) {
                codedOutputStream.writeMessage(140, (Nonhfpcalling.UpdateCallState) this.payload_);
            }
            if (this.payloadCase_ == 141) {
                codedOutputStream.writeMessage(141, (Nonhfpcalling.AcceptCall) this.payload_);
            }
            if (this.payloadCase_ == 142) {
                codedOutputStream.writeMessage(142, (Nonhfpcalling.RejectCall) this.payload_);
            }
            if (this.payloadCase_ == 143) {
                codedOutputStream.writeMessage(143, (Nonhfpcalling.EndCall) this.payload_);
            }
            if (this.payloadCase_ == 150) {
                codedOutputStream.writeMessage(150, (Ancs.SubscribeNotificationCenter) this.payload_);
            }
            if (this.payloadCase_ == 151) {
                codedOutputStream.writeMessage(151, (Ancs.UnsubscribeNotificationCenter) this.payload_);
            }
            if (this.payloadCase_ == 152) {
                codedOutputStream.writeMessage(152, (Ancs.PublishCentralNotification) this.payload_);
            }
            if (this.payloadCase_ == 153) {
                codedOutputStream.writeMessage(153, (Ancs.GetCentralNotificationAttributes) this.payload_);
            }
            if (this.payloadCase_ == 154) {
                codedOutputStream.writeMessage(154, (Ancs.GetCentralNotificationAppAttributes) this.payload_);
            }
            if (this.payloadCase_ == 155) {
                codedOutputStream.writeMessage(155, (Ancs.PerformCentralNotificationAction) this.payload_);
            }
            if (this.payloadCase_ == 156) {
                codedOutputStream.writeMessage(156, (Ancs.UpdateCentralNotificationAttributes) this.payload_);
            }
            if (this.payloadCase_ == 160) {
                codedOutputStream.writeMessage(160, (Input.IssueInputEvent) this.payload_);
            }
            if (this.payloadCase_ == 161) {
                codedOutputStream.writeMessage(161, (Input.SetInputBehavior) this.payload_);
            }
            if (this.payloadCase_ == 162) {
                codedOutputStream.writeMessage(162, (Input.GetInputBehavior) this.payload_);
            }
            if (this.payloadCase_ == 163) {
                codedOutputStream.writeMessage(163, (Instrumentation.PrintDebug) this.payload_);
            }
            if (this.payloadCase_ == 164) {
                codedOutputStream.writeMessage(164, (Instrumentation.IssueRemoteCommand) this.payload_);
            }
            if (this.payloadCase_ == 165) {
                codedOutputStream.writeMessage(165, (Instrumentation.IssueRemoteRestart) this.payload_);
            }
            if (this.payloadCase_ == 166) {
                codedOutputStream.writeMessage(166, (Instrumentation.IssueRemoteReset) this.payload_);
            }
            if (this.payloadCase_ == 167) {
                codedOutputStream.writeMessage(167, (Instrumentation.IssueRemoteClearPairing) this.payload_);
            }
            if (this.payloadCase_ == 168) {
                codedOutputStream.writeMessage(168, (Input.ResetInputBehavior) this.payload_);
            }
            if (this.payloadCase_ == 170) {
                codedOutputStream.writeMessage(170, (Translation.StartTranslation) this.payload_);
            }
            if (this.payloadCase_ == 171) {
                codedOutputStream.writeMessage(171, (Translation.ProvideTranslation) this.payload_);
            }
            if (this.payloadCase_ == 172) {
                codedOutputStream.writeMessage(172, (Translation.StopTranslation) this.payload_);
            }
            if (this.payloadCase_ == 180) {
                codedOutputStream.writeMessage(180, (Bulkdata.GetBulkDataManifest) this.payload_);
            }
            if (this.payloadCase_ == 181) {
                codedOutputStream.writeMessage(181, (Bulkdata.BulkDataManifestEntry) this.payload_);
            }
            if (this.payloadCase_ == 182) {
                codedOutputStream.writeMessage(182, (Bulkdata.RequestBulkDataTransfer) this.payload_);
            }
            if (this.payloadCase_ == 183) {
                codedOutputStream.writeMessage(183, (Bulkdata.BulkDataTransferStart) this.payload_);
            }
            if (this.payloadCase_ == 184) {
                codedOutputStream.writeMessage(184, (Bulkdata.BulkDataTransferComplete) this.payload_);
            }
            if (this.payloadCase_ == 185) {
                codedOutputStream.writeMessage(185, (Bulkdata.StopBulkDataTransfer) this.payload_);
            }
            if (this.payloadCase_ == 186) {
                codedOutputStream.writeMessage(186, (Bulkdata.NotifyBulkDataAvailable) this.payload_);
            }
            if (this.payloadCase_ == 190) {
                codedOutputStream.writeMessage(190, (Notification.AddNotification) this.payload_);
            }
            if (this.payloadCase_ == 191) {
                codedOutputStream.writeMessage(191, (Notification.UpdateNotification) this.payload_);
            }
            if (this.payloadCase_ == 192) {
                codedOutputStream.writeMessage(192, (Notification.RemoveNotification) this.payload_);
            }
            if (this.payloadCase_ == 193) {
                codedOutputStream.writeMessage(193, (Notification.ExecuteNotificationAction) this.payload_);
            }
            if (this.payloadCase_ == 200) {
                codedOutputStream.writeMessage(200, (Keyexchange.InitiateHandshake) this.payload_);
            }
            if (this.payloadCase_ == 201) {
                codedOutputStream.writeMessage(201, (Keyexchange.CompleteHandshake) this.payload_);
            }
            if (this.payloadCase_ == 202) {
                codedOutputStream.writeMessage(202, (Keyexchange.UserConfirmed) this.payload_);
            }
            if (this.payloadCase_ == 203) {
                codedOutputStream.writeMessage(203, (Keyexchange.ResetKey) this.payload_);
            }
            if (this.payloadCase_ == 204) {
                codedOutputStream.writeMessage(204, (Keyexchange.ConfirmResetKey) this.payload_);
            }
            if (this.payloadCase_ == 205) {
                codedOutputStream.writeMessage(205, (Keyexchange.ResetRootKeys) this.payload_);
            }
            if (this.payloadCase_ == 230) {
                codedOutputStream.writeMessage(230, (Cbl.GetCblLoginState) this.payload_);
            }
            if (this.payloadCase_ == 231) {
                codedOutputStream.writeMessage(231, (Cbl.GetCblInformation) this.payload_);
            }
            if (this.payloadCase_ == 232) {
                codedOutputStream.writeMessage(232, (Cbl.NotifyCblLoginState) this.payload_);
            }
            if (this.payloadCase_ == 300) {
                codedOutputStream.writeMessage(300, (Hearing.GetAudiogram) this.payload_);
            }
            if (this.payloadCase_ == 301) {
                codedOutputStream.writeMessage(301, (Hearing.SetAudiogram) this.payload_);
            }
            if (this.payloadCase_ == 302) {
                codedOutputStream.writeMessage(302, (Hearing.GetMediaEnhancementCorrectionAmount) this.payload_);
            }
            if (this.payloadCase_ == 303) {
                codedOutputStream.writeMessage(303, (Hearing.SetMediaEnhancementCorrectionAmount) this.payload_);
            }
            if (this.payloadCase_ == 350) {
                codedOutputStream.writeMessage(350, (Mapsms.GetSmsMessageListResponse) this.payload_);
            }
            if (this.payloadCase_ == 351) {
                codedOutputStream.writeMessage(351, (Mapsms.NotifySmsMessageList) this.payload_);
            }
            if (this.payloadCase_ == 352) {
                codedOutputStream.writeMessage(352, (Mapsms.GetSmsMessageList) this.payload_);
            }
            if (this.payloadCase_ == 353) {
                codedOutputStream.writeMessage(353, (Mapsms.SendSms) this.payload_);
            }
            if (this.payloadCase_ == 354) {
                codedOutputStream.writeMessage(354, (Mapsms.SetSmsReadStatus) this.payload_);
            }
            if (this.payloadCase_ == 355) {
                codedOutputStream.writeMessage(355, (Mapsms.EndOfSmsMessageListResponse) this.payload_);
            }
            if (this.payloadCase_ == 356) {
                codedOutputStream.writeMessage(356, (Mapsms.InitiateMapConnection) this.payload_);
            }
            if (this.payloadCase_ == 360) {
                codedOutputStream.writeMessage(360, (Voicestream.StartVoiceStream) this.payload_);
            }
            if (this.payloadCase_ == 361) {
                codedOutputStream.writeMessage(361, (Voicestream.StopVoiceStream) this.payload_);
            }
            if (this.payloadCase_ == 370) {
                codedOutputStream.writeMessage(370, (Cloudpairing.GetCloudPairingAttributes) this.payload_);
            }
            if (this.payloadCase_ == 371) {
                codedOutputStream.writeMessage(371, (Cloudpairing.GetCloudPairingStatus) this.payload_);
            }
            if (this.payloadCase_ == 372) {
                codedOutputStream.writeMessage(372, (Cloudpairing.SetCloudPairingKeys) this.payload_);
            }
            if (this.payloadCase_ == 373) {
                codedOutputStream.writeMessage(373, (Cloudpairing.ReplaceCloudPairingKeys) this.payload_);
            }
            if (this.payloadCase_ == 374) {
                codedOutputStream.writeMessage(374, (Cloudpairing.RemoveCloudPairingKeys) this.payload_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ControlEnvelope controlEnvelope) {
            return DEFAULT_INSTANCE.createBuilder(controlEnvelope);
        }

        public static ControlEnvelope parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ControlEnvelope) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ControlEnvelope parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ControlEnvelope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ControlEnvelope parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ControlEnvelope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ControlEnvelope parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ControlEnvelope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAcceptCall(Nonhfpcalling.AcceptCall.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 141;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAddNotification(Notification.AddNotification.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 190;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setApplyFirmware(Firmware.ApplyFirmware.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 95;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBulkDataManifestEntry(Bulkdata.BulkDataManifestEntry.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 181;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBulkDataTransferComplete(Bulkdata.BulkDataTransferComplete.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 184;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBulkDataTransferStart(Bulkdata.BulkDataTransferStart.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 183;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCompleteHandshake(Keyexchange.CompleteHandshake.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 201;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCompleteSetup(Device.CompleteSetup.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 24;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConfirmResetKey(Keyexchange.ConfirmResetKey.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 204;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnectUser(System.ConnectUser.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 62;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDisconnectUser(System.DisconnectUser.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 63;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDisplayContent(Cardrendering.DisplayContent.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 80;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEndCall(Nonhfpcalling.EndCall.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 143;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEndOfSmsMessageListResponse(Mapsms.EndOfSmsMessageListResponse.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 355;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEndpointSpeech(Speech.EndpointSpeech.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 13;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExecuteNotificationAction(Notification.ExecuteNotificationAction.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 193;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirmwareUpdateUnavailable(Firmware.FirmwareUpdateUnavailable.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 86;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setForwardAtCommand(Calling.ForwardATCommand.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 40;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetArtifactFilter(Firmware.GetArtifactFilter.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 97;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetArtifactUpdatePreference(Firmware.GetArtifactUpdatePreference.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 98;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetAudiogram(Hearing.GetAudiogram.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 300;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetBulkDataManifest(Bulkdata.GetBulkDataManifest.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 180;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCachedComponent(Firmware.GetCachedComponent.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 92;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCblInformation(Cbl.GetCblInformation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 231;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCblLoginState(Cbl.GetCblLoginState.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 230;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCentralInformation(Central.GetCentralInformation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 103;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCentralNotificationAppAttributes(Ancs.GetCentralNotificationAppAttributes.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 154;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCentralNotificationAttributes(Ancs.GetCentralNotificationAttributes.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 153;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCloudPairingAttributes(Cloudpairing.GetCloudPairingAttributes.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 370;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCloudPairingStatus(Cloudpairing.GetCloudPairingStatus.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 371;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetCurrentUser(System.GetCurrentUser.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 54;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetDeviceArtifacts(Firmware.GetDeviceArtifacts.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 96;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetDeviceConfiguration(Device.GetDeviceConfiguration.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 21;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetDeviceFeatures(Device.GetDeviceFeatures.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 28;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetDeviceInformation(Device.GetDeviceInformation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 20;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetDiagnostics(DiagnosticsOuterClass.GetDiagnostics.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 110;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetFirmwareInformation(Firmware.GetFirmwareInformation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 90;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetFirmwareUpdatePreferences(Firmware.GetFirmwareUpdatePreferences.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 91;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetFitnessData(Fitness.GetFitnessData.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 130;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetInputBehavior(Input.GetInputBehavior.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 162;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetLocales(System.GetLocales.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 57;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetMediaEnhancementCorrectionAmount(Hearing.GetMediaEnhancementCorrectionAmount.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 302;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetPlaybackStatus(Media.GetPlaybackStatus.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 65;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetSmsMessageList(Mapsms.GetSmsMessageList.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 352;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetSmsMessageListResponse(Mapsms.GetSmsMessageListResponse.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 350;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetState(StateOuterClass.GetState.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 100;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetUsers(System.GetUsers.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 52;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGetWakewords(System.GetWakewords.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 71;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIncomingCall(Calling.IncomingCall.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 41;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInitiateFirmwareUpdate(Firmware.InitiateFirmwareUpdate.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 88;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInitiateHandshake(Keyexchange.InitiateHandshake.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 200;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInitiateMapConnection(Mapsms.InitiateMapConnection.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 356;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIssueInputEvent(Input.IssueInputEvent.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 160;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIssueMediaControl(Media.IssueMediaControl.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 60;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIssueRemoteClearPairing(Instrumentation.IssueRemoteClearPairing.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 167;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIssueRemoteCommand(Instrumentation.IssueRemoteCommand.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 164;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIssueRemoteReset(Instrumentation.IssueRemoteReset.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 166;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIssueRemoteRestart(Instrumentation.IssueRemoteRestart.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 165;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKeepAlive(System.KeepAlive.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 55;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLaunchApp(System.LaunchApp.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 59;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLiveFitnessData(Fitness.LiveFitnessData.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 136;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMediaEventOccurred(Media.MediaEventOccurred.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 67;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifyBulkDataAvailable(Bulkdata.NotifyBulkDataAvailable.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 186;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifyCblLoginState(Cbl.NotifyCblLoginState.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 232;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifyDeviceConfiguration(Device.NotifyDeviceConfiguration.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 25;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifyDeviceInformation(Device.NotifyDeviceInformation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 27;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifyDiagnosticsAvailable(DiagnosticsOuterClass.NotifyDiagnosticsAvailable.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 112;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifyFirmwareInformation(Firmware.NotifyFirmwareInformation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 87;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifyFitnessDataAvailable(Fitness.NotifyFitnessDataAvailable.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 132;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifySmsMessageList(Mapsms.NotifySmsMessageList.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 351;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotifySpeechState(Speech.NotifySpeechState.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 14;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOverrideAssistant(Device.OverrideAssistant.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 22;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPerformCentralNotificationAction(Ancs.PerformCentralNotificationAction.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 155;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPrintDebug(Instrumentation.PrintDebug.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 163;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProvideSpeech(Speech.ProvideSpeech.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setProvideTranslation(Translation.ProvideTranslation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 171;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPublishCentralNotification(Ancs.PublishCentralNotification.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 152;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPushMetrics(Metrics.PushMetrics.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 120;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRegisterForMediaEvents(Media.RegisterForMediaEvents.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 66;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRejectCall(Nonhfpcalling.RejectCall.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 142;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRemoveCloudPairingKeys(Cloudpairing.RemoveCloudPairingKeys.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 374;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRemoveDevice(System.RemoveDevice.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 56;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRemoveNotification(Notification.RemoveNotification.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 192;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setReplaceCloudPairingKeys(Cloudpairing.ReplaceCloudPairingKeys.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 373;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRequestBulkDataTransfer(Bulkdata.RequestBulkDataTransfer.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 182;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResetCachedComponent(Firmware.ResetCachedComponent.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 93;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResetConnection(System.ResetConnection.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 51;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResetInputBehavior(Input.ResetInputBehavior.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 168;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResetKey(Keyexchange.ResetKey.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 203;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResetRootKeys(Keyexchange.ResetRootKeys.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 205;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResponse(Response.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSendSms(Mapsms.SendSms.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 353;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetAudiogram(Hearing.SetAudiogram.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 301;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetCloudPairingKeys(Cloudpairing.SetCloudPairingKeys.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 372;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetInputBehavior(Input.SetInputBehavior.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 161;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetLocale(System.SetLocale.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 58;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetMediaEnhancementCorrectionAmount(Hearing.SetMediaEnhancementCorrectionAmount.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 303;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetSmsReadStatus(Mapsms.SetSmsReadStatus.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 354;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetState(StateOuterClass.SetState.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 101;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetWakewords(System.SetWakewords.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 70;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartFirmwareUpdate(Firmware.StartFirmwareUpdate.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 99;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartLiveFitnessData(Fitness.StartLiveFitnessData.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 134;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartSetup(Device.StartSetup.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 23;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartSpeech(Speech.StartSpeech.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 11;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartTranslation(Translation.StartTranslation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 170;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStartVoiceStream(Voicestream.StartVoiceStream.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 360;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStopBulkDataTransfer(Bulkdata.StopBulkDataTransfer.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 185;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStopDiagnostics(DiagnosticsOuterClass.StopDiagnostics.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 111;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStopFitnessData(Fitness.StopFitnessData.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 131;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStopLiveFitnessData(Fitness.StopLiveFitnessData.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 135;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStopSpeech(Speech.StopSpeech.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 12;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStopTranslation(Translation.StopTranslation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 172;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStopVoiceStream(Voicestream.StopVoiceStream.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 361;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSubscribeNotificationCenter(Ancs.SubscribeNotificationCenter.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 150;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSwitchCurrentUser(System.SwitchCurrentUser.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 53;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSwitchTransport(Transport.SwitchTransport.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 31;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSyncFitnessSession(Fitness.SyncFitnessSession.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 133;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSynchronizeSettings(System.SynchronizeSettings.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 50;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSynchronizeState(StateOuterClass.SynchronizeState.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 102;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUnpairUser(System.UnpairUser.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 64;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUnsubscribeNotificationCenter(Ancs.UnsubscribeNotificationCenter.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 151;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateCallState(Nonhfpcalling.UpdateCallState.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 140;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateCentralNotificationAttributes(Ancs.UpdateCentralNotificationAttributes.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 156;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateComponentSegment(Firmware.UpdateComponentSegment.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 94;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateDeviceInformation(Device.UpdateDeviceInformation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 26;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateMetricsMap(Metrics.UpdateMetricsMap.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 121;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateNotification(Notification.UpdateNotification.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 191;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpdateUsers(System.UpdateUsers.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 61;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUpgradeTransport(Transport.UpgradeTransport.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 30;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUserConfirmed(Keyexchange.UserConfirmed.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 202;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVerifyArtifactSignature(Firmware.VerifyArtifactSignature.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 89;
        }

        public static ControlEnvelope parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ControlEnvelope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ControlEnvelope parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ControlEnvelope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ControlEnvelope parseFrom(InputStream inputStream) throws IOException {
            return (ControlEnvelope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ControlEnvelope parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ControlEnvelope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ControlEnvelope parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ControlEnvelope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ControlEnvelope parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ControlEnvelope) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ControlEnvelopeOrBuilder extends MessageLiteOrBuilder {
        Nonhfpcalling.AcceptCall getAcceptCall();

        Notification.AddNotification getAddNotification();

        Firmware.ApplyFirmware getApplyFirmware();

        Bulkdata.BulkDataManifestEntry getBulkDataManifestEntry();

        Bulkdata.BulkDataTransferComplete getBulkDataTransferComplete();

        Bulkdata.BulkDataTransferStart getBulkDataTransferStart();

        Command getCommand();

        int getCommandValue();

        Keyexchange.CompleteHandshake getCompleteHandshake();

        Device.CompleteSetup getCompleteSetup();

        Keyexchange.ConfirmResetKey getConfirmResetKey();

        System.ConnectUser getConnectUser();

        System.DisconnectUser getDisconnectUser();

        Cardrendering.DisplayContent getDisplayContent();

        Nonhfpcalling.EndCall getEndCall();

        Mapsms.EndOfSmsMessageListResponse getEndOfSmsMessageListResponse();

        Speech.EndpointSpeech getEndpointSpeech();

        Notification.ExecuteNotificationAction getExecuteNotificationAction();

        Firmware.FirmwareUpdateUnavailable getFirmwareUpdateUnavailable();

        Calling.ForwardATCommand getForwardAtCommand();

        Firmware.GetArtifactFilter getGetArtifactFilter();

        Firmware.GetArtifactUpdatePreference getGetArtifactUpdatePreference();

        Hearing.GetAudiogram getGetAudiogram();

        Bulkdata.GetBulkDataManifest getGetBulkDataManifest();

        Firmware.GetCachedComponent getGetCachedComponent();

        Cbl.GetCblInformation getGetCblInformation();

        Cbl.GetCblLoginState getGetCblLoginState();

        Central.GetCentralInformation getGetCentralInformation();

        Ancs.GetCentralNotificationAppAttributes getGetCentralNotificationAppAttributes();

        Ancs.GetCentralNotificationAttributes getGetCentralNotificationAttributes();

        Cloudpairing.GetCloudPairingAttributes getGetCloudPairingAttributes();

        Cloudpairing.GetCloudPairingStatus getGetCloudPairingStatus();

        System.GetCurrentUser getGetCurrentUser();

        Firmware.GetDeviceArtifacts getGetDeviceArtifacts();

        Device.GetDeviceConfiguration getGetDeviceConfiguration();

        Device.GetDeviceFeatures getGetDeviceFeatures();

        Device.GetDeviceInformation getGetDeviceInformation();

        DiagnosticsOuterClass.GetDiagnostics getGetDiagnostics();

        Firmware.GetFirmwareInformation getGetFirmwareInformation();

        Firmware.GetFirmwareUpdatePreferences getGetFirmwareUpdatePreferences();

        Fitness.GetFitnessData getGetFitnessData();

        Input.GetInputBehavior getGetInputBehavior();

        System.GetLocales getGetLocales();

        Hearing.GetMediaEnhancementCorrectionAmount getGetMediaEnhancementCorrectionAmount();

        Media.GetPlaybackStatus getGetPlaybackStatus();

        Mapsms.GetSmsMessageList getGetSmsMessageList();

        Mapsms.GetSmsMessageListResponse getGetSmsMessageListResponse();

        StateOuterClass.GetState getGetState();

        System.GetUsers getGetUsers();

        System.GetWakewords getGetWakewords();

        Calling.IncomingCall getIncomingCall();

        Firmware.InitiateFirmwareUpdate getInitiateFirmwareUpdate();

        Keyexchange.InitiateHandshake getInitiateHandshake();

        Mapsms.InitiateMapConnection getInitiateMapConnection();

        Input.IssueInputEvent getIssueInputEvent();

        Media.IssueMediaControl getIssueMediaControl();

        Instrumentation.IssueRemoteClearPairing getIssueRemoteClearPairing();

        Instrumentation.IssueRemoteCommand getIssueRemoteCommand();

        Instrumentation.IssueRemoteReset getIssueRemoteReset();

        Instrumentation.IssueRemoteRestart getIssueRemoteRestart();

        System.KeepAlive getKeepAlive();

        System.LaunchApp getLaunchApp();

        Fitness.LiveFitnessData getLiveFitnessData();

        Media.MediaEventOccurred getMediaEventOccurred();

        Bulkdata.NotifyBulkDataAvailable getNotifyBulkDataAvailable();

        Cbl.NotifyCblLoginState getNotifyCblLoginState();

        Device.NotifyDeviceConfiguration getNotifyDeviceConfiguration();

        Device.NotifyDeviceInformation getNotifyDeviceInformation();

        DiagnosticsOuterClass.NotifyDiagnosticsAvailable getNotifyDiagnosticsAvailable();

        Firmware.NotifyFirmwareInformation getNotifyFirmwareInformation();

        Fitness.NotifyFitnessDataAvailable getNotifyFitnessDataAvailable();

        Mapsms.NotifySmsMessageList getNotifySmsMessageList();

        Speech.NotifySpeechState getNotifySpeechState();

        Device.OverrideAssistant getOverrideAssistant();

        ControlEnvelope.PayloadCase getPayloadCase();

        Ancs.PerformCentralNotificationAction getPerformCentralNotificationAction();

        Instrumentation.PrintDebug getPrintDebug();

        Speech.ProvideSpeech getProvideSpeech();

        Translation.ProvideTranslation getProvideTranslation();

        Ancs.PublishCentralNotification getPublishCentralNotification();

        Metrics.PushMetrics getPushMetrics();

        Media.RegisterForMediaEvents getRegisterForMediaEvents();

        Nonhfpcalling.RejectCall getRejectCall();

        Cloudpairing.RemoveCloudPairingKeys getRemoveCloudPairingKeys();

        System.RemoveDevice getRemoveDevice();

        Notification.RemoveNotification getRemoveNotification();

        Cloudpairing.ReplaceCloudPairingKeys getReplaceCloudPairingKeys();

        Bulkdata.RequestBulkDataTransfer getRequestBulkDataTransfer();

        Firmware.ResetCachedComponent getResetCachedComponent();

        System.ResetConnection getResetConnection();

        Input.ResetInputBehavior getResetInputBehavior();

        Keyexchange.ResetKey getResetKey();

        Keyexchange.ResetRootKeys getResetRootKeys();

        Response getResponse();

        Mapsms.SendSms getSendSms();

        Hearing.SetAudiogram getSetAudiogram();

        Cloudpairing.SetCloudPairingKeys getSetCloudPairingKeys();

        Input.SetInputBehavior getSetInputBehavior();

        System.SetLocale getSetLocale();

        Hearing.SetMediaEnhancementCorrectionAmount getSetMediaEnhancementCorrectionAmount();

        Mapsms.SetSmsReadStatus getSetSmsReadStatus();

        StateOuterClass.SetState getSetState();

        System.SetWakewords getSetWakewords();

        Firmware.StartFirmwareUpdate getStartFirmwareUpdate();

        Fitness.StartLiveFitnessData getStartLiveFitnessData();

        Device.StartSetup getStartSetup();

        Speech.StartSpeech getStartSpeech();

        Translation.StartTranslation getStartTranslation();

        Voicestream.StartVoiceStream getStartVoiceStream();

        Bulkdata.StopBulkDataTransfer getStopBulkDataTransfer();

        DiagnosticsOuterClass.StopDiagnostics getStopDiagnostics();

        Fitness.StopFitnessData getStopFitnessData();

        Fitness.StopLiveFitnessData getStopLiveFitnessData();

        Speech.StopSpeech getStopSpeech();

        Translation.StopTranslation getStopTranslation();

        Voicestream.StopVoiceStream getStopVoiceStream();

        Ancs.SubscribeNotificationCenter getSubscribeNotificationCenter();

        System.SwitchCurrentUser getSwitchCurrentUser();

        Transport.SwitchTransport getSwitchTransport();

        Fitness.SyncFitnessSession getSyncFitnessSession();

        System.SynchronizeSettings getSynchronizeSettings();

        StateOuterClass.SynchronizeState getSynchronizeState();

        System.UnpairUser getUnpairUser();

        Ancs.UnsubscribeNotificationCenter getUnsubscribeNotificationCenter();

        Nonhfpcalling.UpdateCallState getUpdateCallState();

        Ancs.UpdateCentralNotificationAttributes getUpdateCentralNotificationAttributes();

        Firmware.UpdateComponentSegment getUpdateComponentSegment();

        Device.UpdateDeviceInformation getUpdateDeviceInformation();

        Metrics.UpdateMetricsMap getUpdateMetricsMap();

        Notification.UpdateNotification getUpdateNotification();

        System.UpdateUsers getUpdateUsers();

        Transport.UpgradeTransport getUpgradeTransport();

        Keyexchange.UserConfirmed getUserConfirmed();

        Firmware.VerifyArtifactSignature getVerifyArtifactSignature();

        boolean hasAcceptCall();

        boolean hasAddNotification();

        boolean hasApplyFirmware();

        boolean hasBulkDataManifestEntry();

        boolean hasBulkDataTransferComplete();

        boolean hasBulkDataTransferStart();

        boolean hasCompleteHandshake();

        boolean hasCompleteSetup();

        boolean hasConfirmResetKey();

        boolean hasConnectUser();

        boolean hasDisconnectUser();

        boolean hasDisplayContent();

        boolean hasEndCall();

        boolean hasEndOfSmsMessageListResponse();

        boolean hasEndpointSpeech();

        boolean hasExecuteNotificationAction();

        boolean hasFirmwareUpdateUnavailable();

        boolean hasForwardAtCommand();

        boolean hasGetArtifactFilter();

        boolean hasGetArtifactUpdatePreference();

        boolean hasGetAudiogram();

        boolean hasGetBulkDataManifest();

        boolean hasGetCachedComponent();

        boolean hasGetCblInformation();

        boolean hasGetCblLoginState();

        boolean hasGetCentralInformation();

        boolean hasGetCentralNotificationAppAttributes();

        boolean hasGetCentralNotificationAttributes();

        boolean hasGetCloudPairingAttributes();

        boolean hasGetCloudPairingStatus();

        boolean hasGetCurrentUser();

        boolean hasGetDeviceArtifacts();

        boolean hasGetDeviceConfiguration();

        boolean hasGetDeviceFeatures();

        boolean hasGetDeviceInformation();

        boolean hasGetDiagnostics();

        boolean hasGetFirmwareInformation();

        boolean hasGetFirmwareUpdatePreferences();

        boolean hasGetFitnessData();

        boolean hasGetInputBehavior();

        boolean hasGetLocales();

        boolean hasGetMediaEnhancementCorrectionAmount();

        boolean hasGetPlaybackStatus();

        boolean hasGetSmsMessageList();

        boolean hasGetSmsMessageListResponse();

        boolean hasGetState();

        boolean hasGetUsers();

        boolean hasGetWakewords();

        boolean hasIncomingCall();

        boolean hasInitiateFirmwareUpdate();

        boolean hasInitiateHandshake();

        boolean hasInitiateMapConnection();

        boolean hasIssueInputEvent();

        boolean hasIssueMediaControl();

        boolean hasIssueRemoteClearPairing();

        boolean hasIssueRemoteCommand();

        boolean hasIssueRemoteReset();

        boolean hasIssueRemoteRestart();

        boolean hasKeepAlive();

        boolean hasLaunchApp();

        boolean hasLiveFitnessData();

        boolean hasMediaEventOccurred();

        boolean hasNotifyBulkDataAvailable();

        boolean hasNotifyCblLoginState();

        boolean hasNotifyDeviceConfiguration();

        boolean hasNotifyDeviceInformation();

        boolean hasNotifyDiagnosticsAvailable();

        boolean hasNotifyFirmwareInformation();

        boolean hasNotifyFitnessDataAvailable();

        boolean hasNotifySmsMessageList();

        boolean hasNotifySpeechState();

        boolean hasOverrideAssistant();

        boolean hasPerformCentralNotificationAction();

        boolean hasPrintDebug();

        boolean hasProvideSpeech();

        boolean hasProvideTranslation();

        boolean hasPublishCentralNotification();

        boolean hasPushMetrics();

        boolean hasRegisterForMediaEvents();

        boolean hasRejectCall();

        boolean hasRemoveCloudPairingKeys();

        boolean hasRemoveDevice();

        boolean hasRemoveNotification();

        boolean hasReplaceCloudPairingKeys();

        boolean hasRequestBulkDataTransfer();

        boolean hasResetCachedComponent();

        boolean hasResetConnection();

        boolean hasResetInputBehavior();

        boolean hasResetKey();

        boolean hasResetRootKeys();

        boolean hasResponse();

        boolean hasSendSms();

        boolean hasSetAudiogram();

        boolean hasSetCloudPairingKeys();

        boolean hasSetInputBehavior();

        boolean hasSetLocale();

        boolean hasSetMediaEnhancementCorrectionAmount();

        boolean hasSetSmsReadStatus();

        boolean hasSetState();

        boolean hasSetWakewords();

        boolean hasStartFirmwareUpdate();

        boolean hasStartLiveFitnessData();

        boolean hasStartSetup();

        boolean hasStartSpeech();

        boolean hasStartTranslation();

        boolean hasStartVoiceStream();

        boolean hasStopBulkDataTransfer();

        boolean hasStopDiagnostics();

        boolean hasStopFitnessData();

        boolean hasStopLiveFitnessData();

        boolean hasStopSpeech();

        boolean hasStopTranslation();

        boolean hasStopVoiceStream();

        boolean hasSubscribeNotificationCenter();

        boolean hasSwitchCurrentUser();

        boolean hasSwitchTransport();

        boolean hasSyncFitnessSession();

        boolean hasSynchronizeSettings();

        boolean hasSynchronizeState();

        boolean hasUnpairUser();

        boolean hasUnsubscribeNotificationCenter();

        boolean hasUpdateCallState();

        boolean hasUpdateCentralNotificationAttributes();

        boolean hasUpdateComponentSegment();

        boolean hasUpdateDeviceInformation();

        boolean hasUpdateMetricsMap();

        boolean hasUpdateNotification();

        boolean hasUpdateUsers();

        boolean hasUpgradeTransport();

        boolean hasUserConfirmed();

        boolean hasVerifyArtifactSignature();
    }

    /* loaded from: classes6.dex */
    public static final class Response extends GeneratedMessageLite<Response, Builder> implements ResponseOrBuilder {
        public static final int ACKNOWLEDGE_HANDSHAKE_FIELD_NUMBER = 22;
        public static final int ACKNOWLEDGE_RESET_KEY_FIELD_NUMBER = 23;
        public static final int ARTIFACT_FILTER_FIELD_NUMBER = 31;
        public static final int ARTIFACT_LIST_FIELD_NUMBER = 30;
        public static final int ARTIFACT_UPDATE_PREFERENCE_FIELD_NUMBER = 32;
        public static final int AUDIOGRAM_FIELD_NUMBER = 50;
        public static final int CBL_INFORMATION_FIELD_NUMBER = 221;
        public static final int CBL_LOGIN_STATE_FIELD_NUMBER = 220;
        public static final int CENTRAL_INFORMATION_FIELD_NUMBER = 13;
        public static final int CENTRAL_NOTIFICATION_APP_ATTRIBUTES_FIELD_NUMBER = 19;
        public static final int CENTRAL_NOTIFICATION_ATTRIBUTES_FIELD_NUMBER = 18;
        public static final int CLOUD_PAIRING_ATTRIBUTES_FIELD_NUMBER = 55;
        public static final int CLOUD_PAIRING_STATUS_FIELD_NUMBER = 56;
        public static final int CONNECTION_DETAILS_FIELD_NUMBER = 8;
        private static final Response DEFAULT_INSTANCE = new Response();
        public static final int DEVICE_CONFIGURATION_FIELD_NUMBER = 10;
        public static final int DEVICE_FEATURES_FIELD_NUMBER = 28;
        public static final int DEVICE_INFORMATION_FIELD_NUMBER = 3;
        public static final int DIAGNOSTICS_FIELD_NUMBER = 9;
        public static final int DIALOG_FIELD_NUMBER = 14;
        public static final int ERROR_CODE_FIELD_NUMBER = 1;
        public static final int FIRMWARE_COMPONENT_FIELD_NUMBER = 2;
        public static final int FIRMWARE_INFORMATION_FIELD_NUMBER = 5;
        public static final int FIRMWARE_UPDATE_PREFERENCES_FIELD_NUMBER = 6;
        public static final int FITNESS_DATA_FIELD_NUMBER = 16;
        public static final int INPUT_BEHAVIOR_CONFIGURATION_SET_FIELD_NUMBER = 20;
        public static final int LOCALES_FIELD_NUMBER = 21;
        public static final int MEDIA_ENHANCEMENT_CORRECTION_AMOUNT_FIELD_NUMBER = 51;
        public static final int NOTIFICATION_CENTER_INFORMATION_FIELD_NUMBER = 17;
        private static volatile Parser<Response> PARSER = null;
        public static final int PLAYBACK_STATUS_FIELD_NUMBER = 60;
        public static final int SPEECH_PROVIDER_FIELD_NUMBER = 15;
        public static final int STATE_FIELD_NUMBER = 7;
        public static final int USERS_FIELD_NUMBER = 11;
        public static final int USER_FIELD_NUMBER = 12;
        public static final int VOICE_PROVIDER_FIELD_NUMBER = 362;
        public static final int WAKEWORDS_FIELD_NUMBER = 33;
        private int errorCode_;
        private int payloadCase_ = 0;
        private Object payload_;

        /* loaded from: classes6.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Response, Builder> implements ResponseOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder clearAcknowledgeHandshake() {
                copyOnWrite();
                ((Response) this.instance).clearAcknowledgeHandshake();
                return this;
            }

            public Builder clearAcknowledgeResetKey() {
                copyOnWrite();
                ((Response) this.instance).clearAcknowledgeResetKey();
                return this;
            }

            public Builder clearArtifactFilter() {
                copyOnWrite();
                ((Response) this.instance).clearArtifactFilter();
                return this;
            }

            public Builder clearArtifactList() {
                copyOnWrite();
                ((Response) this.instance).clearArtifactList();
                return this;
            }

            public Builder clearArtifactUpdatePreference() {
                copyOnWrite();
                ((Response) this.instance).clearArtifactUpdatePreference();
                return this;
            }

            public Builder clearAudiogram() {
                copyOnWrite();
                ((Response) this.instance).clearAudiogram();
                return this;
            }

            public Builder clearCblInformation() {
                copyOnWrite();
                ((Response) this.instance).clearCblInformation();
                return this;
            }

            public Builder clearCblLoginState() {
                copyOnWrite();
                ((Response) this.instance).clearCblLoginState();
                return this;
            }

            public Builder clearCentralInformation() {
                copyOnWrite();
                ((Response) this.instance).clearCentralInformation();
                return this;
            }

            public Builder clearCentralNotificationAppAttributes() {
                copyOnWrite();
                ((Response) this.instance).clearCentralNotificationAppAttributes();
                return this;
            }

            public Builder clearCentralNotificationAttributes() {
                copyOnWrite();
                ((Response) this.instance).clearCentralNotificationAttributes();
                return this;
            }

            public Builder clearCloudPairingAttributes() {
                copyOnWrite();
                ((Response) this.instance).clearCloudPairingAttributes();
                return this;
            }

            public Builder clearCloudPairingStatus() {
                copyOnWrite();
                ((Response) this.instance).clearCloudPairingStatus();
                return this;
            }

            public Builder clearConnectionDetails() {
                copyOnWrite();
                ((Response) this.instance).clearConnectionDetails();
                return this;
            }

            public Builder clearDeviceConfiguration() {
                copyOnWrite();
                ((Response) this.instance).clearDeviceConfiguration();
                return this;
            }

            public Builder clearDeviceFeatures() {
                copyOnWrite();
                ((Response) this.instance).clearDeviceFeatures();
                return this;
            }

            public Builder clearDeviceInformation() {
                copyOnWrite();
                ((Response) this.instance).clearDeviceInformation();
                return this;
            }

            public Builder clearDiagnostics() {
                copyOnWrite();
                ((Response) this.instance).clearDiagnostics();
                return this;
            }

            public Builder clearDialog() {
                copyOnWrite();
                ((Response) this.instance).clearDialog();
                return this;
            }

            public Builder clearErrorCode() {
                copyOnWrite();
                ((Response) this.instance).clearErrorCode();
                return this;
            }

            public Builder clearFirmwareComponent() {
                copyOnWrite();
                ((Response) this.instance).clearFirmwareComponent();
                return this;
            }

            public Builder clearFirmwareInformation() {
                copyOnWrite();
                ((Response) this.instance).clearFirmwareInformation();
                return this;
            }

            public Builder clearFirmwareUpdatePreferences() {
                copyOnWrite();
                ((Response) this.instance).clearFirmwareUpdatePreferences();
                return this;
            }

            public Builder clearFitnessData() {
                copyOnWrite();
                ((Response) this.instance).clearFitnessData();
                return this;
            }

            public Builder clearInputBehaviorConfigurationSet() {
                copyOnWrite();
                ((Response) this.instance).clearInputBehaviorConfigurationSet();
                return this;
            }

            public Builder clearLocales() {
                copyOnWrite();
                ((Response) this.instance).clearLocales();
                return this;
            }

            public Builder clearMediaEnhancementCorrectionAmount() {
                copyOnWrite();
                ((Response) this.instance).clearMediaEnhancementCorrectionAmount();
                return this;
            }

            public Builder clearNotificationCenterInformation() {
                copyOnWrite();
                ((Response) this.instance).clearNotificationCenterInformation();
                return this;
            }

            public Builder clearPayload() {
                copyOnWrite();
                ((Response) this.instance).clearPayload();
                return this;
            }

            public Builder clearPlaybackStatus() {
                copyOnWrite();
                ((Response) this.instance).clearPlaybackStatus();
                return this;
            }

            public Builder clearSpeechProvider() {
                copyOnWrite();
                ((Response) this.instance).clearSpeechProvider();
                return this;
            }

            public Builder clearState() {
                copyOnWrite();
                ((Response) this.instance).clearState();
                return this;
            }

            public Builder clearUser() {
                copyOnWrite();
                ((Response) this.instance).clearUser();
                return this;
            }

            public Builder clearUsers() {
                copyOnWrite();
                ((Response) this.instance).clearUsers();
                return this;
            }

            public Builder clearVoiceProvider() {
                copyOnWrite();
                ((Response) this.instance).clearVoiceProvider();
                return this;
            }

            public Builder clearWakewords() {
                copyOnWrite();
                ((Response) this.instance).clearWakewords();
                return this;
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Keyexchange.AcknowledgeHandshake getAcknowledgeHandshake() {
                return ((Response) this.instance).getAcknowledgeHandshake();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Keyexchange.AcknowledgeResetKey getAcknowledgeResetKey() {
                return ((Response) this.instance).getAcknowledgeResetKey();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Firmware.ArtifactFilter getArtifactFilter() {
                return ((Response) this.instance).getArtifactFilter();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Firmware.ArtifactList getArtifactList() {
                return ((Response) this.instance).getArtifactList();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Firmware.ArtifactUpdatePreference getArtifactUpdatePreference() {
                return ((Response) this.instance).getArtifactUpdatePreference();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Hearing.Audiogram getAudiogram() {
                return ((Response) this.instance).getAudiogram();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Cbl.CblInformation getCblInformation() {
                return ((Response) this.instance).getCblInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Cbl.CblLoginState getCblLoginState() {
                return ((Response) this.instance).getCblLoginState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Central.CentralInformation getCentralInformation() {
                return ((Response) this.instance).getCentralInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Ancs.CentralNotificationAppAttributes getCentralNotificationAppAttributes() {
                return ((Response) this.instance).getCentralNotificationAppAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Ancs.CentralNotificationAttributes getCentralNotificationAttributes() {
                return ((Response) this.instance).getCentralNotificationAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Cloudpairing.CloudPairingAttributes getCloudPairingAttributes() {
                return ((Response) this.instance).getCloudPairingAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Cloudpairing.CloudPairingStatus getCloudPairingStatus() {
                return ((Response) this.instance).getCloudPairingStatus();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Transport.ConnectionDetails getConnectionDetails() {
                return ((Response) this.instance).getConnectionDetails();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Device.DeviceConfiguration getDeviceConfiguration() {
                return ((Response) this.instance).getDeviceConfiguration();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Device.DeviceFeatures getDeviceFeatures() {
                return ((Response) this.instance).getDeviceFeatures();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Device.DeviceInformation getDeviceInformation() {
                return ((Response) this.instance).getDeviceInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public DiagnosticsOuterClass.Diagnostics getDiagnostics() {
                return ((Response) this.instance).getDiagnostics();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Speech.Dialog getDialog() {
                return ((Response) this.instance).getDialog();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Common.ErrorCode getErrorCode() {
                return ((Response) this.instance).getErrorCode();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public int getErrorCodeValue() {
                return ((Response) this.instance).getErrorCodeValue();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Firmware.FirmwareComponent getFirmwareComponent() {
                return ((Response) this.instance).getFirmwareComponent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Firmware.FirmwareInformation getFirmwareInformation() {
                return ((Response) this.instance).getFirmwareInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Firmware.FirmwareUpdatePreferences getFirmwareUpdatePreferences() {
                return ((Response) this.instance).getFirmwareUpdatePreferences();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Fitness.FitnessData getFitnessData() {
                return ((Response) this.instance).getFitnessData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Input.InputBehaviorConfigurationSet getInputBehaviorConfigurationSet() {
                return ((Response) this.instance).getInputBehaviorConfigurationSet();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public System.Locales getLocales() {
                return ((Response) this.instance).getLocales();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Hearing.MediaEnhancementCorrectionAmount getMediaEnhancementCorrectionAmount() {
                return ((Response) this.instance).getMediaEnhancementCorrectionAmount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Ancs.NotificationCenterInformation getNotificationCenterInformation() {
                return ((Response) this.instance).getNotificationCenterInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public PayloadCase getPayloadCase() {
                return ((Response) this.instance).getPayloadCase();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Media.PlaybackStatus getPlaybackStatus() {
                return ((Response) this.instance).getPlaybackStatus();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Speech.SpeechProvider getSpeechProvider() {
                return ((Response) this.instance).getSpeechProvider();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public StateOuterClass.State getState() {
                return ((Response) this.instance).getState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public System.User getUser() {
                return ((Response) this.instance).getUser();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public System.Users getUsers() {
                return ((Response) this.instance).getUsers();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public Voicestream.VoiceProvider getVoiceProvider() {
                return ((Response) this.instance).getVoiceProvider();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public System.Wakewords getWakewords() {
                return ((Response) this.instance).getWakewords();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasAcknowledgeHandshake() {
                return ((Response) this.instance).hasAcknowledgeHandshake();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasAcknowledgeResetKey() {
                return ((Response) this.instance).hasAcknowledgeResetKey();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasArtifactFilter() {
                return ((Response) this.instance).hasArtifactFilter();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasArtifactList() {
                return ((Response) this.instance).hasArtifactList();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasArtifactUpdatePreference() {
                return ((Response) this.instance).hasArtifactUpdatePreference();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasAudiogram() {
                return ((Response) this.instance).hasAudiogram();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasCblInformation() {
                return ((Response) this.instance).hasCblInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasCblLoginState() {
                return ((Response) this.instance).hasCblLoginState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasCentralInformation() {
                return ((Response) this.instance).hasCentralInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasCentralNotificationAppAttributes() {
                return ((Response) this.instance).hasCentralNotificationAppAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasCentralNotificationAttributes() {
                return ((Response) this.instance).hasCentralNotificationAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasCloudPairingAttributes() {
                return ((Response) this.instance).hasCloudPairingAttributes();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasCloudPairingStatus() {
                return ((Response) this.instance).hasCloudPairingStatus();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasConnectionDetails() {
                return ((Response) this.instance).hasConnectionDetails();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasDeviceConfiguration() {
                return ((Response) this.instance).hasDeviceConfiguration();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasDeviceFeatures() {
                return ((Response) this.instance).hasDeviceFeatures();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasDeviceInformation() {
                return ((Response) this.instance).hasDeviceInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasDiagnostics() {
                return ((Response) this.instance).hasDiagnostics();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasDialog() {
                return ((Response) this.instance).hasDialog();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasFirmwareComponent() {
                return ((Response) this.instance).hasFirmwareComponent();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasFirmwareInformation() {
                return ((Response) this.instance).hasFirmwareInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasFirmwareUpdatePreferences() {
                return ((Response) this.instance).hasFirmwareUpdatePreferences();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasFitnessData() {
                return ((Response) this.instance).hasFitnessData();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasInputBehaviorConfigurationSet() {
                return ((Response) this.instance).hasInputBehaviorConfigurationSet();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasLocales() {
                return ((Response) this.instance).hasLocales();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasMediaEnhancementCorrectionAmount() {
                return ((Response) this.instance).hasMediaEnhancementCorrectionAmount();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasNotificationCenterInformation() {
                return ((Response) this.instance).hasNotificationCenterInformation();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasPlaybackStatus() {
                return ((Response) this.instance).hasPlaybackStatus();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasSpeechProvider() {
                return ((Response) this.instance).hasSpeechProvider();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasState() {
                return ((Response) this.instance).hasState();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasUser() {
                return ((Response) this.instance).hasUser();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasUsers() {
                return ((Response) this.instance).hasUsers();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasVoiceProvider() {
                return ((Response) this.instance).hasVoiceProvider();
            }

            @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
            public boolean hasWakewords() {
                return ((Response) this.instance).hasWakewords();
            }

            public Builder mergeAcknowledgeHandshake(Keyexchange.AcknowledgeHandshake acknowledgeHandshake) {
                copyOnWrite();
                ((Response) this.instance).mergeAcknowledgeHandshake(acknowledgeHandshake);
                return this;
            }

            public Builder mergeAcknowledgeResetKey(Keyexchange.AcknowledgeResetKey acknowledgeResetKey) {
                copyOnWrite();
                ((Response) this.instance).mergeAcknowledgeResetKey(acknowledgeResetKey);
                return this;
            }

            public Builder mergeArtifactFilter(Firmware.ArtifactFilter artifactFilter) {
                copyOnWrite();
                ((Response) this.instance).mergeArtifactFilter(artifactFilter);
                return this;
            }

            public Builder mergeArtifactList(Firmware.ArtifactList artifactList) {
                copyOnWrite();
                ((Response) this.instance).mergeArtifactList(artifactList);
                return this;
            }

            public Builder mergeArtifactUpdatePreference(Firmware.ArtifactUpdatePreference artifactUpdatePreference) {
                copyOnWrite();
                ((Response) this.instance).mergeArtifactUpdatePreference(artifactUpdatePreference);
                return this;
            }

            public Builder mergeAudiogram(Hearing.Audiogram audiogram) {
                copyOnWrite();
                ((Response) this.instance).mergeAudiogram(audiogram);
                return this;
            }

            public Builder mergeCblInformation(Cbl.CblInformation cblInformation) {
                copyOnWrite();
                ((Response) this.instance).mergeCblInformation(cblInformation);
                return this;
            }

            public Builder mergeCblLoginState(Cbl.CblLoginState cblLoginState) {
                copyOnWrite();
                ((Response) this.instance).mergeCblLoginState(cblLoginState);
                return this;
            }

            public Builder mergeCentralInformation(Central.CentralInformation centralInformation) {
                copyOnWrite();
                ((Response) this.instance).mergeCentralInformation(centralInformation);
                return this;
            }

            public Builder mergeCentralNotificationAppAttributes(Ancs.CentralNotificationAppAttributes centralNotificationAppAttributes) {
                copyOnWrite();
                ((Response) this.instance).mergeCentralNotificationAppAttributes(centralNotificationAppAttributes);
                return this;
            }

            public Builder mergeCentralNotificationAttributes(Ancs.CentralNotificationAttributes centralNotificationAttributes) {
                copyOnWrite();
                ((Response) this.instance).mergeCentralNotificationAttributes(centralNotificationAttributes);
                return this;
            }

            public Builder mergeCloudPairingAttributes(Cloudpairing.CloudPairingAttributes cloudPairingAttributes) {
                copyOnWrite();
                ((Response) this.instance).mergeCloudPairingAttributes(cloudPairingAttributes);
                return this;
            }

            public Builder mergeCloudPairingStatus(Cloudpairing.CloudPairingStatus cloudPairingStatus) {
                copyOnWrite();
                ((Response) this.instance).mergeCloudPairingStatus(cloudPairingStatus);
                return this;
            }

            public Builder mergeConnectionDetails(Transport.ConnectionDetails connectionDetails) {
                copyOnWrite();
                ((Response) this.instance).mergeConnectionDetails(connectionDetails);
                return this;
            }

            public Builder mergeDeviceConfiguration(Device.DeviceConfiguration deviceConfiguration) {
                copyOnWrite();
                ((Response) this.instance).mergeDeviceConfiguration(deviceConfiguration);
                return this;
            }

            public Builder mergeDeviceFeatures(Device.DeviceFeatures deviceFeatures) {
                copyOnWrite();
                ((Response) this.instance).mergeDeviceFeatures(deviceFeatures);
                return this;
            }

            public Builder mergeDeviceInformation(Device.DeviceInformation deviceInformation) {
                copyOnWrite();
                ((Response) this.instance).mergeDeviceInformation(deviceInformation);
                return this;
            }

            public Builder mergeDiagnostics(DiagnosticsOuterClass.Diagnostics diagnostics) {
                copyOnWrite();
                ((Response) this.instance).mergeDiagnostics(diagnostics);
                return this;
            }

            public Builder mergeDialog(Speech.Dialog dialog) {
                copyOnWrite();
                ((Response) this.instance).mergeDialog(dialog);
                return this;
            }

            public Builder mergeFirmwareComponent(Firmware.FirmwareComponent firmwareComponent) {
                copyOnWrite();
                ((Response) this.instance).mergeFirmwareComponent(firmwareComponent);
                return this;
            }

            public Builder mergeFirmwareInformation(Firmware.FirmwareInformation firmwareInformation) {
                copyOnWrite();
                ((Response) this.instance).mergeFirmwareInformation(firmwareInformation);
                return this;
            }

            public Builder mergeFirmwareUpdatePreferences(Firmware.FirmwareUpdatePreferences firmwareUpdatePreferences) {
                copyOnWrite();
                ((Response) this.instance).mergeFirmwareUpdatePreferences(firmwareUpdatePreferences);
                return this;
            }

            public Builder mergeFitnessData(Fitness.FitnessData fitnessData) {
                copyOnWrite();
                ((Response) this.instance).mergeFitnessData(fitnessData);
                return this;
            }

            public Builder mergeInputBehaviorConfigurationSet(Input.InputBehaviorConfigurationSet inputBehaviorConfigurationSet) {
                copyOnWrite();
                ((Response) this.instance).mergeInputBehaviorConfigurationSet(inputBehaviorConfigurationSet);
                return this;
            }

            public Builder mergeLocales(System.Locales locales) {
                copyOnWrite();
                ((Response) this.instance).mergeLocales(locales);
                return this;
            }

            public Builder mergeMediaEnhancementCorrectionAmount(Hearing.MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount) {
                copyOnWrite();
                ((Response) this.instance).mergeMediaEnhancementCorrectionAmount(mediaEnhancementCorrectionAmount);
                return this;
            }

            public Builder mergeNotificationCenterInformation(Ancs.NotificationCenterInformation notificationCenterInformation) {
                copyOnWrite();
                ((Response) this.instance).mergeNotificationCenterInformation(notificationCenterInformation);
                return this;
            }

            public Builder mergePlaybackStatus(Media.PlaybackStatus playbackStatus) {
                copyOnWrite();
                ((Response) this.instance).mergePlaybackStatus(playbackStatus);
                return this;
            }

            public Builder mergeSpeechProvider(Speech.SpeechProvider speechProvider) {
                copyOnWrite();
                ((Response) this.instance).mergeSpeechProvider(speechProvider);
                return this;
            }

            public Builder mergeState(StateOuterClass.State state) {
                copyOnWrite();
                ((Response) this.instance).mergeState(state);
                return this;
            }

            public Builder mergeUser(System.User user) {
                copyOnWrite();
                ((Response) this.instance).mergeUser(user);
                return this;
            }

            public Builder mergeUsers(System.Users users) {
                copyOnWrite();
                ((Response) this.instance).mergeUsers(users);
                return this;
            }

            public Builder mergeVoiceProvider(Voicestream.VoiceProvider voiceProvider) {
                copyOnWrite();
                ((Response) this.instance).mergeVoiceProvider(voiceProvider);
                return this;
            }

            public Builder mergeWakewords(System.Wakewords wakewords) {
                copyOnWrite();
                ((Response) this.instance).mergeWakewords(wakewords);
                return this;
            }

            public Builder setAcknowledgeHandshake(Keyexchange.AcknowledgeHandshake acknowledgeHandshake) {
                copyOnWrite();
                ((Response) this.instance).setAcknowledgeHandshake(acknowledgeHandshake);
                return this;
            }

            public Builder setAcknowledgeResetKey(Keyexchange.AcknowledgeResetKey acknowledgeResetKey) {
                copyOnWrite();
                ((Response) this.instance).setAcknowledgeResetKey(acknowledgeResetKey);
                return this;
            }

            public Builder setArtifactFilter(Firmware.ArtifactFilter artifactFilter) {
                copyOnWrite();
                ((Response) this.instance).setArtifactFilter(artifactFilter);
                return this;
            }

            public Builder setArtifactList(Firmware.ArtifactList artifactList) {
                copyOnWrite();
                ((Response) this.instance).setArtifactList(artifactList);
                return this;
            }

            public Builder setArtifactUpdatePreference(Firmware.ArtifactUpdatePreference artifactUpdatePreference) {
                copyOnWrite();
                ((Response) this.instance).setArtifactUpdatePreference(artifactUpdatePreference);
                return this;
            }

            public Builder setAudiogram(Hearing.Audiogram audiogram) {
                copyOnWrite();
                ((Response) this.instance).setAudiogram(audiogram);
                return this;
            }

            public Builder setCblInformation(Cbl.CblInformation cblInformation) {
                copyOnWrite();
                ((Response) this.instance).setCblInformation(cblInformation);
                return this;
            }

            public Builder setCblLoginState(Cbl.CblLoginState cblLoginState) {
                copyOnWrite();
                ((Response) this.instance).setCblLoginState(cblLoginState);
                return this;
            }

            public Builder setCentralInformation(Central.CentralInformation centralInformation) {
                copyOnWrite();
                ((Response) this.instance).setCentralInformation(centralInformation);
                return this;
            }

            public Builder setCentralNotificationAppAttributes(Ancs.CentralNotificationAppAttributes centralNotificationAppAttributes) {
                copyOnWrite();
                ((Response) this.instance).setCentralNotificationAppAttributes(centralNotificationAppAttributes);
                return this;
            }

            public Builder setCentralNotificationAttributes(Ancs.CentralNotificationAttributes centralNotificationAttributes) {
                copyOnWrite();
                ((Response) this.instance).setCentralNotificationAttributes(centralNotificationAttributes);
                return this;
            }

            public Builder setCloudPairingAttributes(Cloudpairing.CloudPairingAttributes cloudPairingAttributes) {
                copyOnWrite();
                ((Response) this.instance).setCloudPairingAttributes(cloudPairingAttributes);
                return this;
            }

            public Builder setCloudPairingStatus(Cloudpairing.CloudPairingStatus cloudPairingStatus) {
                copyOnWrite();
                ((Response) this.instance).setCloudPairingStatus(cloudPairingStatus);
                return this;
            }

            public Builder setConnectionDetails(Transport.ConnectionDetails connectionDetails) {
                copyOnWrite();
                ((Response) this.instance).setConnectionDetails(connectionDetails);
                return this;
            }

            public Builder setDeviceConfiguration(Device.DeviceConfiguration deviceConfiguration) {
                copyOnWrite();
                ((Response) this.instance).setDeviceConfiguration(deviceConfiguration);
                return this;
            }

            public Builder setDeviceFeatures(Device.DeviceFeatures deviceFeatures) {
                copyOnWrite();
                ((Response) this.instance).setDeviceFeatures(deviceFeatures);
                return this;
            }

            public Builder setDeviceInformation(Device.DeviceInformation deviceInformation) {
                copyOnWrite();
                ((Response) this.instance).setDeviceInformation(deviceInformation);
                return this;
            }

            public Builder setDiagnostics(DiagnosticsOuterClass.Diagnostics diagnostics) {
                copyOnWrite();
                ((Response) this.instance).setDiagnostics(diagnostics);
                return this;
            }

            public Builder setDialog(Speech.Dialog dialog) {
                copyOnWrite();
                ((Response) this.instance).setDialog(dialog);
                return this;
            }

            public Builder setErrorCode(Common.ErrorCode errorCode) {
                copyOnWrite();
                ((Response) this.instance).setErrorCode(errorCode);
                return this;
            }

            public Builder setErrorCodeValue(int i) {
                copyOnWrite();
                ((Response) this.instance).setErrorCodeValue(i);
                return this;
            }

            public Builder setFirmwareComponent(Firmware.FirmwareComponent firmwareComponent) {
                copyOnWrite();
                ((Response) this.instance).setFirmwareComponent(firmwareComponent);
                return this;
            }

            public Builder setFirmwareInformation(Firmware.FirmwareInformation firmwareInformation) {
                copyOnWrite();
                ((Response) this.instance).setFirmwareInformation(firmwareInformation);
                return this;
            }

            public Builder setFirmwareUpdatePreferences(Firmware.FirmwareUpdatePreferences firmwareUpdatePreferences) {
                copyOnWrite();
                ((Response) this.instance).setFirmwareUpdatePreferences(firmwareUpdatePreferences);
                return this;
            }

            public Builder setFitnessData(Fitness.FitnessData fitnessData) {
                copyOnWrite();
                ((Response) this.instance).setFitnessData(fitnessData);
                return this;
            }

            public Builder setInputBehaviorConfigurationSet(Input.InputBehaviorConfigurationSet inputBehaviorConfigurationSet) {
                copyOnWrite();
                ((Response) this.instance).setInputBehaviorConfigurationSet(inputBehaviorConfigurationSet);
                return this;
            }

            public Builder setLocales(System.Locales locales) {
                copyOnWrite();
                ((Response) this.instance).setLocales(locales);
                return this;
            }

            public Builder setMediaEnhancementCorrectionAmount(Hearing.MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount) {
                copyOnWrite();
                ((Response) this.instance).setMediaEnhancementCorrectionAmount(mediaEnhancementCorrectionAmount);
                return this;
            }

            public Builder setNotificationCenterInformation(Ancs.NotificationCenterInformation notificationCenterInformation) {
                copyOnWrite();
                ((Response) this.instance).setNotificationCenterInformation(notificationCenterInformation);
                return this;
            }

            public Builder setPlaybackStatus(Media.PlaybackStatus playbackStatus) {
                copyOnWrite();
                ((Response) this.instance).setPlaybackStatus(playbackStatus);
                return this;
            }

            public Builder setSpeechProvider(Speech.SpeechProvider speechProvider) {
                copyOnWrite();
                ((Response) this.instance).setSpeechProvider(speechProvider);
                return this;
            }

            public Builder setState(StateOuterClass.State state) {
                copyOnWrite();
                ((Response) this.instance).setState(state);
                return this;
            }

            public Builder setUser(System.User user) {
                copyOnWrite();
                ((Response) this.instance).setUser(user);
                return this;
            }

            public Builder setUsers(System.Users users) {
                copyOnWrite();
                ((Response) this.instance).setUsers(users);
                return this;
            }

            public Builder setVoiceProvider(Voicestream.VoiceProvider voiceProvider) {
                copyOnWrite();
                ((Response) this.instance).setVoiceProvider(voiceProvider);
                return this;
            }

            public Builder setWakewords(System.Wakewords wakewords) {
                copyOnWrite();
                ((Response) this.instance).setWakewords(wakewords);
                return this;
            }

            private Builder() {
                super(Response.DEFAULT_INSTANCE);
            }

            public Builder setAcknowledgeHandshake(Keyexchange.AcknowledgeHandshake.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setAcknowledgeHandshake(builder);
                return this;
            }

            public Builder setAcknowledgeResetKey(Keyexchange.AcknowledgeResetKey.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setAcknowledgeResetKey(builder);
                return this;
            }

            public Builder setArtifactFilter(Firmware.ArtifactFilter.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setArtifactFilter(builder);
                return this;
            }

            public Builder setArtifactList(Firmware.ArtifactList.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setArtifactList(builder);
                return this;
            }

            public Builder setArtifactUpdatePreference(Firmware.ArtifactUpdatePreference.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setArtifactUpdatePreference(builder);
                return this;
            }

            public Builder setAudiogram(Hearing.Audiogram.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setAudiogram(builder);
                return this;
            }

            public Builder setCblInformation(Cbl.CblInformation.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setCblInformation(builder);
                return this;
            }

            public Builder setCblLoginState(Cbl.CblLoginState.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setCblLoginState(builder);
                return this;
            }

            public Builder setCentralInformation(Central.CentralInformation.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setCentralInformation(builder);
                return this;
            }

            public Builder setCentralNotificationAppAttributes(Ancs.CentralNotificationAppAttributes.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setCentralNotificationAppAttributes(builder);
                return this;
            }

            public Builder setCentralNotificationAttributes(Ancs.CentralNotificationAttributes.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setCentralNotificationAttributes(builder);
                return this;
            }

            public Builder setCloudPairingAttributes(Cloudpairing.CloudPairingAttributes.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setCloudPairingAttributes(builder);
                return this;
            }

            public Builder setCloudPairingStatus(Cloudpairing.CloudPairingStatus.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setCloudPairingStatus(builder);
                return this;
            }

            public Builder setConnectionDetails(Transport.ConnectionDetails.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setConnectionDetails(builder);
                return this;
            }

            public Builder setDeviceConfiguration(Device.DeviceConfiguration.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setDeviceConfiguration(builder);
                return this;
            }

            public Builder setDeviceFeatures(Device.DeviceFeatures.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setDeviceFeatures(builder);
                return this;
            }

            public Builder setDeviceInformation(Device.DeviceInformation.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setDeviceInformation(builder);
                return this;
            }

            public Builder setDiagnostics(DiagnosticsOuterClass.Diagnostics.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setDiagnostics(builder);
                return this;
            }

            public Builder setDialog(Speech.Dialog.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setDialog(builder);
                return this;
            }

            public Builder setFirmwareComponent(Firmware.FirmwareComponent.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setFirmwareComponent(builder);
                return this;
            }

            public Builder setFirmwareInformation(Firmware.FirmwareInformation.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setFirmwareInformation(builder);
                return this;
            }

            public Builder setFirmwareUpdatePreferences(Firmware.FirmwareUpdatePreferences.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setFirmwareUpdatePreferences(builder);
                return this;
            }

            public Builder setFitnessData(Fitness.FitnessData.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setFitnessData(builder);
                return this;
            }

            public Builder setInputBehaviorConfigurationSet(Input.InputBehaviorConfigurationSet.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setInputBehaviorConfigurationSet(builder);
                return this;
            }

            public Builder setLocales(System.Locales.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setLocales(builder);
                return this;
            }

            public Builder setMediaEnhancementCorrectionAmount(Hearing.MediaEnhancementCorrectionAmount.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setMediaEnhancementCorrectionAmount(builder);
                return this;
            }

            public Builder setNotificationCenterInformation(Ancs.NotificationCenterInformation.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setNotificationCenterInformation(builder);
                return this;
            }

            public Builder setPlaybackStatus(Media.PlaybackStatus.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setPlaybackStatus(builder);
                return this;
            }

            public Builder setSpeechProvider(Speech.SpeechProvider.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setSpeechProvider(builder);
                return this;
            }

            public Builder setState(StateOuterClass.State.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setState(builder);
                return this;
            }

            public Builder setUser(System.User.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setUser(builder);
                return this;
            }

            public Builder setUsers(System.Users.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setUsers(builder);
                return this;
            }

            public Builder setVoiceProvider(Voicestream.VoiceProvider.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setVoiceProvider(builder);
                return this;
            }

            public Builder setWakewords(System.Wakewords.Builder builder) {
                copyOnWrite();
                ((Response) this.instance).setWakewords(builder);
                return this;
            }
        }

        /* loaded from: classes6.dex */
        public enum PayloadCase implements Internal.EnumLite {
            USER(12),
            USERS(11),
            WAKEWORDS(33),
            LOCALES(21),
            CONNECTION_DETAILS(8),
            DIALOG(14),
            SPEECH_PROVIDER(15),
            CENTRAL_INFORMATION(13),
            DEVICE_INFORMATION(3),
            DEVICE_CONFIGURATION(10),
            DEVICE_FEATURES(28),
            DIAGNOSTICS(9),
            FIRMWARE_COMPONENT(2),
            FIRMWARE_INFORMATION(5),
            FIRMWARE_UPDATE_PREFERENCES(6),
            ARTIFACT_LIST(30),
            ARTIFACT_FILTER(31),
            ARTIFACT_UPDATE_PREFERENCE(32),
            PLAYBACK_STATUS(60),
            STATE(7),
            FITNESS_DATA(16),
            NOTIFICATION_CENTER_INFORMATION(17),
            CENTRAL_NOTIFICATION_ATTRIBUTES(18),
            CENTRAL_NOTIFICATION_APP_ATTRIBUTES(19),
            INPUT_BEHAVIOR_CONFIGURATION_SET(20),
            ACKNOWLEDGE_HANDSHAKE(22),
            ACKNOWLEDGE_RESET_KEY(23),
            AUDIOGRAM(50),
            MEDIA_ENHANCEMENT_CORRECTION_AMOUNT(51),
            CBL_LOGIN_STATE(220),
            CBL_INFORMATION(221),
            VOICE_PROVIDER(Response.VOICE_PROVIDER_FIELD_NUMBER),
            CLOUD_PAIRING_ATTRIBUTES(55),
            CLOUD_PAIRING_STATUS(56),
            PAYLOAD_NOT_SET(0);
            
            private final int value;

            PayloadCase(int i) {
                this.value = i;
            }

            public static PayloadCase forNumber(int i) {
                if (i != 0) {
                    if (i == 28) {
                        return DEVICE_FEATURES;
                    }
                    if (i == 60) {
                        return PLAYBACK_STATUS;
                    }
                    if (i == 362) {
                        return VOICE_PROVIDER;
                    }
                    if (i == 2) {
                        return FIRMWARE_COMPONENT;
                    }
                    if (i == 3) {
                        return DEVICE_INFORMATION;
                    }
                    if (i == 50) {
                        return AUDIOGRAM;
                    }
                    if (i == 51) {
                        return MEDIA_ENHANCEMENT_CORRECTION_AMOUNT;
                    }
                    if (i == 55) {
                        return CLOUD_PAIRING_ATTRIBUTES;
                    }
                    if (i == 56) {
                        return CLOUD_PAIRING_STATUS;
                    }
                    if (i == 220) {
                        return CBL_LOGIN_STATE;
                    }
                    if (i != 221) {
                        switch (i) {
                            case 5:
                                return FIRMWARE_INFORMATION;
                            case 6:
                                return FIRMWARE_UPDATE_PREFERENCES;
                            case 7:
                                return STATE;
                            case 8:
                                return CONNECTION_DETAILS;
                            case 9:
                                return DIAGNOSTICS;
                            case 10:
                                return DEVICE_CONFIGURATION;
                            case 11:
                                return USERS;
                            case 12:
                                return USER;
                            case 13:
                                return CENTRAL_INFORMATION;
                            case 14:
                                return DIALOG;
                            case 15:
                                return SPEECH_PROVIDER;
                            case 16:
                                return FITNESS_DATA;
                            case 17:
                                return NOTIFICATION_CENTER_INFORMATION;
                            case 18:
                                return CENTRAL_NOTIFICATION_ATTRIBUTES;
                            case 19:
                                return CENTRAL_NOTIFICATION_APP_ATTRIBUTES;
                            case 20:
                                return INPUT_BEHAVIOR_CONFIGURATION_SET;
                            case 21:
                                return LOCALES;
                            case 22:
                                return ACKNOWLEDGE_HANDSHAKE;
                            case 23:
                                return ACKNOWLEDGE_RESET_KEY;
                            default:
                                switch (i) {
                                    case 30:
                                        return ARTIFACT_LIST;
                                    case 31:
                                        return ARTIFACT_FILTER;
                                    case 32:
                                        return ARTIFACT_UPDATE_PREFERENCE;
                                    case 33:
                                        return WAKEWORDS;
                                    default:
                                        return null;
                                }
                        }
                    }
                    return CBL_INFORMATION;
                }
                return PAYLOAD_NOT_SET;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static PayloadCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private Response() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAcknowledgeHandshake() {
            if (this.payloadCase_ == 22) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAcknowledgeResetKey() {
            if (this.payloadCase_ == 23) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactFilter() {
            if (this.payloadCase_ == 31) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactList() {
            if (this.payloadCase_ == 30) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArtifactUpdatePreference() {
            if (this.payloadCase_ == 32) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAudiogram() {
            if (this.payloadCase_ == 50) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCblInformation() {
            if (this.payloadCase_ == 221) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCblLoginState() {
            if (this.payloadCase_ == 220) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCentralInformation() {
            if (this.payloadCase_ == 13) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCentralNotificationAppAttributes() {
            if (this.payloadCase_ == 19) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCentralNotificationAttributes() {
            if (this.payloadCase_ == 18) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCloudPairingAttributes() {
            if (this.payloadCase_ == 55) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCloudPairingStatus() {
            if (this.payloadCase_ == 56) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConnectionDetails() {
            if (this.payloadCase_ == 8) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceConfiguration() {
            if (this.payloadCase_ == 10) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceFeatures() {
            if (this.payloadCase_ == 28) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceInformation() {
            if (this.payloadCase_ == 3) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDiagnostics() {
            if (this.payloadCase_ == 9) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDialog() {
            if (this.payloadCase_ == 14) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearErrorCode() {
            this.errorCode_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFirmwareComponent() {
            if (this.payloadCase_ == 2) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFirmwareInformation() {
            if (this.payloadCase_ == 5) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFirmwareUpdatePreferences() {
            if (this.payloadCase_ == 6) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFitnessData() {
            if (this.payloadCase_ == 16) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInputBehaviorConfigurationSet() {
            if (this.payloadCase_ == 20) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLocales() {
            if (this.payloadCase_ == 21) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMediaEnhancementCorrectionAmount() {
            if (this.payloadCase_ == 51) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNotificationCenterInformation() {
            if (this.payloadCase_ == 17) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPayload() {
            this.payloadCase_ = 0;
            this.payload_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPlaybackStatus() {
            if (this.payloadCase_ == 60) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSpeechProvider() {
            if (this.payloadCase_ == 15) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearState() {
            if (this.payloadCase_ == 7) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUser() {
            if (this.payloadCase_ == 12) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUsers() {
            if (this.payloadCase_ == 11) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVoiceProvider() {
            if (this.payloadCase_ == 362) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearWakewords() {
            if (this.payloadCase_ == 33) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        public static Response getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAcknowledgeHandshake(Keyexchange.AcknowledgeHandshake acknowledgeHandshake) {
            if (this.payloadCase_ == 22 && this.payload_ != Keyexchange.AcknowledgeHandshake.getDefaultInstance()) {
                this.payload_ = Keyexchange.AcknowledgeHandshake.newBuilder((Keyexchange.AcknowledgeHandshake) this.payload_).mergeFrom((Keyexchange.AcknowledgeHandshake.Builder) acknowledgeHandshake).mo10085buildPartial();
            } else {
                this.payload_ = acknowledgeHandshake;
            }
            this.payloadCase_ = 22;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAcknowledgeResetKey(Keyexchange.AcknowledgeResetKey acknowledgeResetKey) {
            if (this.payloadCase_ == 23 && this.payload_ != Keyexchange.AcknowledgeResetKey.getDefaultInstance()) {
                this.payload_ = Keyexchange.AcknowledgeResetKey.newBuilder((Keyexchange.AcknowledgeResetKey) this.payload_).mergeFrom((Keyexchange.AcknowledgeResetKey.Builder) acknowledgeResetKey).mo10085buildPartial();
            } else {
                this.payload_ = acknowledgeResetKey;
            }
            this.payloadCase_ = 23;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeArtifactFilter(Firmware.ArtifactFilter artifactFilter) {
            if (this.payloadCase_ == 31 && this.payload_ != Firmware.ArtifactFilter.getDefaultInstance()) {
                this.payload_ = Firmware.ArtifactFilter.newBuilder((Firmware.ArtifactFilter) this.payload_).mergeFrom((Firmware.ArtifactFilter.Builder) artifactFilter).mo10085buildPartial();
            } else {
                this.payload_ = artifactFilter;
            }
            this.payloadCase_ = 31;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeArtifactList(Firmware.ArtifactList artifactList) {
            if (this.payloadCase_ == 30 && this.payload_ != Firmware.ArtifactList.getDefaultInstance()) {
                this.payload_ = Firmware.ArtifactList.newBuilder((Firmware.ArtifactList) this.payload_).mergeFrom((Firmware.ArtifactList.Builder) artifactList).mo10085buildPartial();
            } else {
                this.payload_ = artifactList;
            }
            this.payloadCase_ = 30;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeArtifactUpdatePreference(Firmware.ArtifactUpdatePreference artifactUpdatePreference) {
            if (this.payloadCase_ == 32 && this.payload_ != Firmware.ArtifactUpdatePreference.getDefaultInstance()) {
                this.payload_ = Firmware.ArtifactUpdatePreference.newBuilder((Firmware.ArtifactUpdatePreference) this.payload_).mergeFrom((Firmware.ArtifactUpdatePreference.Builder) artifactUpdatePreference).mo10085buildPartial();
            } else {
                this.payload_ = artifactUpdatePreference;
            }
            this.payloadCase_ = 32;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAudiogram(Hearing.Audiogram audiogram) {
            if (this.payloadCase_ == 50 && this.payload_ != Hearing.Audiogram.getDefaultInstance()) {
                this.payload_ = Hearing.Audiogram.newBuilder((Hearing.Audiogram) this.payload_).mergeFrom((Hearing.Audiogram.Builder) audiogram).mo10085buildPartial();
            } else {
                this.payload_ = audiogram;
            }
            this.payloadCase_ = 50;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCblInformation(Cbl.CblInformation cblInformation) {
            if (this.payloadCase_ == 221 && this.payload_ != Cbl.CblInformation.getDefaultInstance()) {
                this.payload_ = Cbl.CblInformation.newBuilder((Cbl.CblInformation) this.payload_).mergeFrom((Cbl.CblInformation.Builder) cblInformation).mo10085buildPartial();
            } else {
                this.payload_ = cblInformation;
            }
            this.payloadCase_ = 221;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCblLoginState(Cbl.CblLoginState cblLoginState) {
            if (this.payloadCase_ == 220 && this.payload_ != Cbl.CblLoginState.getDefaultInstance()) {
                this.payload_ = Cbl.CblLoginState.newBuilder((Cbl.CblLoginState) this.payload_).mergeFrom((Cbl.CblLoginState.Builder) cblLoginState).mo10085buildPartial();
            } else {
                this.payload_ = cblLoginState;
            }
            this.payloadCase_ = 220;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCentralInformation(Central.CentralInformation centralInformation) {
            if (this.payloadCase_ == 13 && this.payload_ != Central.CentralInformation.getDefaultInstance()) {
                this.payload_ = Central.CentralInformation.newBuilder((Central.CentralInformation) this.payload_).mergeFrom((Central.CentralInformation.Builder) centralInformation).mo10085buildPartial();
            } else {
                this.payload_ = centralInformation;
            }
            this.payloadCase_ = 13;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCentralNotificationAppAttributes(Ancs.CentralNotificationAppAttributes centralNotificationAppAttributes) {
            if (this.payloadCase_ == 19 && this.payload_ != Ancs.CentralNotificationAppAttributes.getDefaultInstance()) {
                this.payload_ = Ancs.CentralNotificationAppAttributes.newBuilder((Ancs.CentralNotificationAppAttributes) this.payload_).mergeFrom((Ancs.CentralNotificationAppAttributes.Builder) centralNotificationAppAttributes).mo10085buildPartial();
            } else {
                this.payload_ = centralNotificationAppAttributes;
            }
            this.payloadCase_ = 19;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCentralNotificationAttributes(Ancs.CentralNotificationAttributes centralNotificationAttributes) {
            if (this.payloadCase_ == 18 && this.payload_ != Ancs.CentralNotificationAttributes.getDefaultInstance()) {
                this.payload_ = Ancs.CentralNotificationAttributes.newBuilder((Ancs.CentralNotificationAttributes) this.payload_).mergeFrom((Ancs.CentralNotificationAttributes.Builder) centralNotificationAttributes).mo10085buildPartial();
            } else {
                this.payload_ = centralNotificationAttributes;
            }
            this.payloadCase_ = 18;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCloudPairingAttributes(Cloudpairing.CloudPairingAttributes cloudPairingAttributes) {
            if (this.payloadCase_ == 55 && this.payload_ != Cloudpairing.CloudPairingAttributes.getDefaultInstance()) {
                this.payload_ = Cloudpairing.CloudPairingAttributes.newBuilder((Cloudpairing.CloudPairingAttributes) this.payload_).mergeFrom((Cloudpairing.CloudPairingAttributes.Builder) cloudPairingAttributes).mo10085buildPartial();
            } else {
                this.payload_ = cloudPairingAttributes;
            }
            this.payloadCase_ = 55;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCloudPairingStatus(Cloudpairing.CloudPairingStatus cloudPairingStatus) {
            if (this.payloadCase_ == 56 && this.payload_ != Cloudpairing.CloudPairingStatus.getDefaultInstance()) {
                this.payload_ = Cloudpairing.CloudPairingStatus.newBuilder((Cloudpairing.CloudPairingStatus) this.payload_).mergeFrom((Cloudpairing.CloudPairingStatus.Builder) cloudPairingStatus).mo10085buildPartial();
            } else {
                this.payload_ = cloudPairingStatus;
            }
            this.payloadCase_ = 56;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeConnectionDetails(Transport.ConnectionDetails connectionDetails) {
            if (this.payloadCase_ == 8 && this.payload_ != Transport.ConnectionDetails.getDefaultInstance()) {
                this.payload_ = Transport.ConnectionDetails.newBuilder((Transport.ConnectionDetails) this.payload_).mergeFrom((Transport.ConnectionDetails.Builder) connectionDetails).mo10085buildPartial();
            } else {
                this.payload_ = connectionDetails;
            }
            this.payloadCase_ = 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDeviceConfiguration(Device.DeviceConfiguration deviceConfiguration) {
            if (this.payloadCase_ == 10 && this.payload_ != Device.DeviceConfiguration.getDefaultInstance()) {
                this.payload_ = Device.DeviceConfiguration.newBuilder((Device.DeviceConfiguration) this.payload_).mergeFrom((Device.DeviceConfiguration.Builder) deviceConfiguration).mo10085buildPartial();
            } else {
                this.payload_ = deviceConfiguration;
            }
            this.payloadCase_ = 10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDeviceFeatures(Device.DeviceFeatures deviceFeatures) {
            if (this.payloadCase_ == 28 && this.payload_ != Device.DeviceFeatures.getDefaultInstance()) {
                this.payload_ = Device.DeviceFeatures.newBuilder((Device.DeviceFeatures) this.payload_).mergeFrom((Device.DeviceFeatures.Builder) deviceFeatures).mo10085buildPartial();
            } else {
                this.payload_ = deviceFeatures;
            }
            this.payloadCase_ = 28;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDeviceInformation(Device.DeviceInformation deviceInformation) {
            if (this.payloadCase_ == 3 && this.payload_ != Device.DeviceInformation.getDefaultInstance()) {
                this.payload_ = Device.DeviceInformation.newBuilder((Device.DeviceInformation) this.payload_).mergeFrom((Device.DeviceInformation.Builder) deviceInformation).mo10085buildPartial();
            } else {
                this.payload_ = deviceInformation;
            }
            this.payloadCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDiagnostics(DiagnosticsOuterClass.Diagnostics diagnostics) {
            if (this.payloadCase_ == 9 && this.payload_ != DiagnosticsOuterClass.Diagnostics.getDefaultInstance()) {
                this.payload_ = DiagnosticsOuterClass.Diagnostics.newBuilder((DiagnosticsOuterClass.Diagnostics) this.payload_).mergeFrom((DiagnosticsOuterClass.Diagnostics.Builder) diagnostics).mo10085buildPartial();
            } else {
                this.payload_ = diagnostics;
            }
            this.payloadCase_ = 9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDialog(Speech.Dialog dialog) {
            if (this.payloadCase_ == 14 && this.payload_ != Speech.Dialog.getDefaultInstance()) {
                this.payload_ = Speech.Dialog.newBuilder((Speech.Dialog) this.payload_).mergeFrom((Speech.Dialog.Builder) dialog).mo10085buildPartial();
            } else {
                this.payload_ = dialog;
            }
            this.payloadCase_ = 14;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFirmwareComponent(Firmware.FirmwareComponent firmwareComponent) {
            if (this.payloadCase_ == 2 && this.payload_ != Firmware.FirmwareComponent.getDefaultInstance()) {
                this.payload_ = Firmware.FirmwareComponent.newBuilder((Firmware.FirmwareComponent) this.payload_).mergeFrom((Firmware.FirmwareComponent.Builder) firmwareComponent).mo10085buildPartial();
            } else {
                this.payload_ = firmwareComponent;
            }
            this.payloadCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFirmwareInformation(Firmware.FirmwareInformation firmwareInformation) {
            if (this.payloadCase_ == 5 && this.payload_ != Firmware.FirmwareInformation.getDefaultInstance()) {
                this.payload_ = Firmware.FirmwareInformation.newBuilder((Firmware.FirmwareInformation) this.payload_).mergeFrom((Firmware.FirmwareInformation.Builder) firmwareInformation).mo10085buildPartial();
            } else {
                this.payload_ = firmwareInformation;
            }
            this.payloadCase_ = 5;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFirmwareUpdatePreferences(Firmware.FirmwareUpdatePreferences firmwareUpdatePreferences) {
            if (this.payloadCase_ == 6 && this.payload_ != Firmware.FirmwareUpdatePreferences.getDefaultInstance()) {
                this.payload_ = Firmware.FirmwareUpdatePreferences.newBuilder((Firmware.FirmwareUpdatePreferences) this.payload_).mergeFrom((Firmware.FirmwareUpdatePreferences.Builder) firmwareUpdatePreferences).mo10085buildPartial();
            } else {
                this.payload_ = firmwareUpdatePreferences;
            }
            this.payloadCase_ = 6;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFitnessData(Fitness.FitnessData fitnessData) {
            if (this.payloadCase_ == 16 && this.payload_ != Fitness.FitnessData.getDefaultInstance()) {
                this.payload_ = Fitness.FitnessData.newBuilder((Fitness.FitnessData) this.payload_).mergeFrom((Fitness.FitnessData.Builder) fitnessData).mo10085buildPartial();
            } else {
                this.payload_ = fitnessData;
            }
            this.payloadCase_ = 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeInputBehaviorConfigurationSet(Input.InputBehaviorConfigurationSet inputBehaviorConfigurationSet) {
            if (this.payloadCase_ == 20 && this.payload_ != Input.InputBehaviorConfigurationSet.getDefaultInstance()) {
                this.payload_ = Input.InputBehaviorConfigurationSet.newBuilder((Input.InputBehaviorConfigurationSet) this.payload_).mergeFrom((Input.InputBehaviorConfigurationSet.Builder) inputBehaviorConfigurationSet).mo10085buildPartial();
            } else {
                this.payload_ = inputBehaviorConfigurationSet;
            }
            this.payloadCase_ = 20;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeLocales(System.Locales locales) {
            if (this.payloadCase_ == 21 && this.payload_ != System.Locales.getDefaultInstance()) {
                this.payload_ = System.Locales.newBuilder((System.Locales) this.payload_).mergeFrom((System.Locales.Builder) locales).mo10085buildPartial();
            } else {
                this.payload_ = locales;
            }
            this.payloadCase_ = 21;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeMediaEnhancementCorrectionAmount(Hearing.MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount) {
            if (this.payloadCase_ == 51 && this.payload_ != Hearing.MediaEnhancementCorrectionAmount.getDefaultInstance()) {
                this.payload_ = Hearing.MediaEnhancementCorrectionAmount.newBuilder((Hearing.MediaEnhancementCorrectionAmount) this.payload_).mergeFrom((Hearing.MediaEnhancementCorrectionAmount.Builder) mediaEnhancementCorrectionAmount).mo10085buildPartial();
            } else {
                this.payload_ = mediaEnhancementCorrectionAmount;
            }
            this.payloadCase_ = 51;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeNotificationCenterInformation(Ancs.NotificationCenterInformation notificationCenterInformation) {
            if (this.payloadCase_ == 17 && this.payload_ != Ancs.NotificationCenterInformation.getDefaultInstance()) {
                this.payload_ = Ancs.NotificationCenterInformation.newBuilder((Ancs.NotificationCenterInformation) this.payload_).mergeFrom((Ancs.NotificationCenterInformation.Builder) notificationCenterInformation).mo10085buildPartial();
            } else {
                this.payload_ = notificationCenterInformation;
            }
            this.payloadCase_ = 17;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePlaybackStatus(Media.PlaybackStatus playbackStatus) {
            if (this.payloadCase_ == 60 && this.payload_ != Media.PlaybackStatus.getDefaultInstance()) {
                this.payload_ = Media.PlaybackStatus.newBuilder((Media.PlaybackStatus) this.payload_).mergeFrom((Media.PlaybackStatus.Builder) playbackStatus).mo10085buildPartial();
            } else {
                this.payload_ = playbackStatus;
            }
            this.payloadCase_ = 60;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSpeechProvider(Speech.SpeechProvider speechProvider) {
            if (this.payloadCase_ == 15 && this.payload_ != Speech.SpeechProvider.getDefaultInstance()) {
                this.payload_ = Speech.SpeechProvider.newBuilder((Speech.SpeechProvider) this.payload_).mergeFrom((Speech.SpeechProvider.Builder) speechProvider).mo10085buildPartial();
            } else {
                this.payload_ = speechProvider;
            }
            this.payloadCase_ = 15;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeState(StateOuterClass.State state) {
            if (this.payloadCase_ == 7 && this.payload_ != StateOuterClass.State.getDefaultInstance()) {
                this.payload_ = StateOuterClass.State.newBuilder((StateOuterClass.State) this.payload_).mergeFrom((StateOuterClass.State.Builder) state).mo10085buildPartial();
            } else {
                this.payload_ = state;
            }
            this.payloadCase_ = 7;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUser(System.User user) {
            if (this.payloadCase_ == 12 && this.payload_ != System.User.getDefaultInstance()) {
                this.payload_ = System.User.newBuilder((System.User) this.payload_).mergeFrom((System.User.Builder) user).mo10085buildPartial();
            } else {
                this.payload_ = user;
            }
            this.payloadCase_ = 12;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUsers(System.Users users) {
            if (this.payloadCase_ == 11 && this.payload_ != System.Users.getDefaultInstance()) {
                this.payload_ = System.Users.newBuilder((System.Users) this.payload_).mergeFrom((System.Users.Builder) users).mo10085buildPartial();
            } else {
                this.payload_ = users;
            }
            this.payloadCase_ = 11;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeVoiceProvider(Voicestream.VoiceProvider voiceProvider) {
            if (this.payloadCase_ == 362 && this.payload_ != Voicestream.VoiceProvider.getDefaultInstance()) {
                this.payload_ = Voicestream.VoiceProvider.newBuilder((Voicestream.VoiceProvider) this.payload_).mergeFrom((Voicestream.VoiceProvider.Builder) voiceProvider).mo10085buildPartial();
            } else {
                this.payload_ = voiceProvider;
            }
            this.payloadCase_ = VOICE_PROVIDER_FIELD_NUMBER;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeWakewords(System.Wakewords wakewords) {
            if (this.payloadCase_ == 33 && this.payload_ != System.Wakewords.getDefaultInstance()) {
                this.payload_ = System.Wakewords.newBuilder((System.Wakewords) this.payload_).mergeFrom((System.Wakewords.Builder) wakewords).mo10085buildPartial();
            } else {
                this.payload_ = wakewords;
            }
            this.payloadCase_ = 33;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Response parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Response) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Response parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Response> parser() {
            return DEFAULT_INSTANCE.mo9954getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAcknowledgeHandshake(Keyexchange.AcknowledgeHandshake acknowledgeHandshake) {
            if (acknowledgeHandshake != null) {
                this.payload_ = acknowledgeHandshake;
                this.payloadCase_ = 22;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAcknowledgeResetKey(Keyexchange.AcknowledgeResetKey acknowledgeResetKey) {
            if (acknowledgeResetKey != null) {
                this.payload_ = acknowledgeResetKey;
                this.payloadCase_ = 23;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactFilter(Firmware.ArtifactFilter artifactFilter) {
            if (artifactFilter != null) {
                this.payload_ = artifactFilter;
                this.payloadCase_ = 31;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactList(Firmware.ArtifactList artifactList) {
            if (artifactList != null) {
                this.payload_ = artifactList;
                this.payloadCase_ = 30;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactUpdatePreference(Firmware.ArtifactUpdatePreference artifactUpdatePreference) {
            if (artifactUpdatePreference != null) {
                this.payload_ = artifactUpdatePreference;
                this.payloadCase_ = 32;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAudiogram(Hearing.Audiogram audiogram) {
            if (audiogram != null) {
                this.payload_ = audiogram;
                this.payloadCase_ = 50;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCblInformation(Cbl.CblInformation cblInformation) {
            if (cblInformation != null) {
                this.payload_ = cblInformation;
                this.payloadCase_ = 221;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCblLoginState(Cbl.CblLoginState cblLoginState) {
            if (cblLoginState != null) {
                this.payload_ = cblLoginState;
                this.payloadCase_ = 220;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCentralInformation(Central.CentralInformation centralInformation) {
            if (centralInformation != null) {
                this.payload_ = centralInformation;
                this.payloadCase_ = 13;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCentralNotificationAppAttributes(Ancs.CentralNotificationAppAttributes centralNotificationAppAttributes) {
            if (centralNotificationAppAttributes != null) {
                this.payload_ = centralNotificationAppAttributes;
                this.payloadCase_ = 19;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCentralNotificationAttributes(Ancs.CentralNotificationAttributes centralNotificationAttributes) {
            if (centralNotificationAttributes != null) {
                this.payload_ = centralNotificationAttributes;
                this.payloadCase_ = 18;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCloudPairingAttributes(Cloudpairing.CloudPairingAttributes cloudPairingAttributes) {
            if (cloudPairingAttributes != null) {
                this.payload_ = cloudPairingAttributes;
                this.payloadCase_ = 55;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCloudPairingStatus(Cloudpairing.CloudPairingStatus cloudPairingStatus) {
            if (cloudPairingStatus != null) {
                this.payload_ = cloudPairingStatus;
                this.payloadCase_ = 56;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnectionDetails(Transport.ConnectionDetails connectionDetails) {
            if (connectionDetails != null) {
                this.payload_ = connectionDetails;
                this.payloadCase_ = 8;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceConfiguration(Device.DeviceConfiguration deviceConfiguration) {
            if (deviceConfiguration != null) {
                this.payload_ = deviceConfiguration;
                this.payloadCase_ = 10;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceFeatures(Device.DeviceFeatures deviceFeatures) {
            if (deviceFeatures != null) {
                this.payload_ = deviceFeatures;
                this.payloadCase_ = 28;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceInformation(Device.DeviceInformation deviceInformation) {
            if (deviceInformation != null) {
                this.payload_ = deviceInformation;
                this.payloadCase_ = 3;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDiagnostics(DiagnosticsOuterClass.Diagnostics diagnostics) {
            if (diagnostics != null) {
                this.payload_ = diagnostics;
                this.payloadCase_ = 9;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDialog(Speech.Dialog dialog) {
            if (dialog != null) {
                this.payload_ = dialog;
                this.payloadCase_ = 14;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorCode(Common.ErrorCode errorCode) {
            if (errorCode != null) {
                this.errorCode_ = errorCode.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorCodeValue(int i) {
            this.errorCode_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirmwareComponent(Firmware.FirmwareComponent firmwareComponent) {
            if (firmwareComponent != null) {
                this.payload_ = firmwareComponent;
                this.payloadCase_ = 2;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirmwareInformation(Firmware.FirmwareInformation firmwareInformation) {
            if (firmwareInformation != null) {
                this.payload_ = firmwareInformation;
                this.payloadCase_ = 5;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirmwareUpdatePreferences(Firmware.FirmwareUpdatePreferences firmwareUpdatePreferences) {
            if (firmwareUpdatePreferences != null) {
                this.payload_ = firmwareUpdatePreferences;
                this.payloadCase_ = 6;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFitnessData(Fitness.FitnessData fitnessData) {
            if (fitnessData != null) {
                this.payload_ = fitnessData;
                this.payloadCase_ = 16;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInputBehaviorConfigurationSet(Input.InputBehaviorConfigurationSet inputBehaviorConfigurationSet) {
            if (inputBehaviorConfigurationSet != null) {
                this.payload_ = inputBehaviorConfigurationSet;
                this.payloadCase_ = 20;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLocales(System.Locales locales) {
            if (locales != null) {
                this.payload_ = locales;
                this.payloadCase_ = 21;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMediaEnhancementCorrectionAmount(Hearing.MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount) {
            if (mediaEnhancementCorrectionAmount != null) {
                this.payload_ = mediaEnhancementCorrectionAmount;
                this.payloadCase_ = 51;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationCenterInformation(Ancs.NotificationCenterInformation notificationCenterInformation) {
            if (notificationCenterInformation != null) {
                this.payload_ = notificationCenterInformation;
                this.payloadCase_ = 17;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPlaybackStatus(Media.PlaybackStatus playbackStatus) {
            if (playbackStatus != null) {
                this.payload_ = playbackStatus;
                this.payloadCase_ = 60;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSpeechProvider(Speech.SpeechProvider speechProvider) {
            if (speechProvider != null) {
                this.payload_ = speechProvider;
                this.payloadCase_ = 15;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setState(StateOuterClass.State state) {
            if (state != null) {
                this.payload_ = state;
                this.payloadCase_ = 7;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUser(System.User user) {
            if (user != null) {
                this.payload_ = user;
                this.payloadCase_ = 12;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUsers(System.Users users) {
            if (users != null) {
                this.payload_ = users;
                this.payloadCase_ = 11;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVoiceProvider(Voicestream.VoiceProvider voiceProvider) {
            if (voiceProvider != null) {
                this.payload_ = voiceProvider;
                this.payloadCase_ = VOICE_PROVIDER_FIELD_NUMBER;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWakewords(System.Wakewords wakewords) {
            if (wakewords != null) {
                this.payload_ = wakewords;
                this.payloadCase_ = 33;
                return;
            }
            throw new NullPointerException();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            int i;
            boolean z = false;
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case 1:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    Response response = (Response) obj2;
                    this.errorCode_ = visitor.visitInt(this.errorCode_ != 0, this.errorCode_, response.errorCode_ != 0, response.errorCode_);
                    switch (response.getPayloadCase().ordinal()) {
                        case 0:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 12, this.payload_, response.payload_);
                            break;
                        case 1:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 11, this.payload_, response.payload_);
                            break;
                        case 2:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 33, this.payload_, response.payload_);
                            break;
                        case 3:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 21, this.payload_, response.payload_);
                            break;
                        case 4:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 8, this.payload_, response.payload_);
                            break;
                        case 5:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 14, this.payload_, response.payload_);
                            break;
                        case 6:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 15, this.payload_, response.payload_);
                            break;
                        case 7:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 13, this.payload_, response.payload_);
                            break;
                        case 8:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 3, this.payload_, response.payload_);
                            break;
                        case 9:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 10, this.payload_, response.payload_);
                            break;
                        case 10:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 28, this.payload_, response.payload_);
                            break;
                        case 11:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 9, this.payload_, response.payload_);
                            break;
                        case 12:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 2, this.payload_, response.payload_);
                            break;
                        case 13:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 5, this.payload_, response.payload_);
                            break;
                        case 14:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 6, this.payload_, response.payload_);
                            break;
                        case 15:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 30, this.payload_, response.payload_);
                            break;
                        case 16:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 31, this.payload_, response.payload_);
                            break;
                        case 17:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 32, this.payload_, response.payload_);
                            break;
                        case 18:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 60, this.payload_, response.payload_);
                            break;
                        case 19:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 7, this.payload_, response.payload_);
                            break;
                        case 20:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 16, this.payload_, response.payload_);
                            break;
                        case 21:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 17, this.payload_, response.payload_);
                            break;
                        case 22:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 18, this.payload_, response.payload_);
                            break;
                        case 23:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 19, this.payload_, response.payload_);
                            break;
                        case 24:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 20, this.payload_, response.payload_);
                            break;
                        case 25:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 22, this.payload_, response.payload_);
                            break;
                        case 26:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 23, this.payload_, response.payload_);
                            break;
                        case 27:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 50, this.payload_, response.payload_);
                            break;
                        case 28:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 51, this.payload_, response.payload_);
                            break;
                        case 29:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 220, this.payload_, response.payload_);
                            break;
                        case 30:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 221, this.payload_, response.payload_);
                            break;
                        case 31:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 362, this.payload_, response.payload_);
                            break;
                        case 32:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 55, this.payload_, response.payload_);
                            break;
                        case 33:
                            this.payload_ = visitor.visitOneofMessage(this.payloadCase_ == 56, this.payload_, response.payload_);
                            break;
                        case 34:
                            visitor.visitOneofNotSet(this.payloadCase_ != 0);
                            break;
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE && (i = response.payloadCase_) != 0) {
                        this.payloadCase_ = i;
                    }
                    return this;
                case 2:
                    return (byte) 1;
                case 3:
                    return null;
                case 4:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    if (extensionRegistryLite == null) {
                        throw new NullPointerException();
                    }
                    while (!z) {
                        try {
                            try {
                                int readTag = codedInputStream.readTag();
                                switch (readTag) {
                                    case 0:
                                        break;
                                    case 8:
                                        this.errorCode_ = codedInputStream.readEnum();
                                        continue;
                                    case 18:
                                        Firmware.FirmwareComponent.Builder mo10081toBuilder = this.payloadCase_ == 2 ? ((Firmware.FirmwareComponent) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Firmware.FirmwareComponent.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder != null) {
                                            mo10081toBuilder.mergeFrom((Firmware.FirmwareComponent.Builder) ((Firmware.FirmwareComponent) this.payload_));
                                            this.payload_ = mo10081toBuilder.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 2;
                                        continue;
                                    case 26:
                                        Device.DeviceInformation.Builder mo10081toBuilder2 = this.payloadCase_ == 3 ? ((Device.DeviceInformation) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Device.DeviceInformation.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder2 != null) {
                                            mo10081toBuilder2.mergeFrom((Device.DeviceInformation.Builder) ((Device.DeviceInformation) this.payload_));
                                            this.payload_ = mo10081toBuilder2.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 3;
                                        continue;
                                    case 42:
                                        Firmware.FirmwareInformation.Builder mo10081toBuilder3 = this.payloadCase_ == 5 ? ((Firmware.FirmwareInformation) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Firmware.FirmwareInformation.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder3 != null) {
                                            mo10081toBuilder3.mergeFrom((Firmware.FirmwareInformation.Builder) ((Firmware.FirmwareInformation) this.payload_));
                                            this.payload_ = mo10081toBuilder3.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 5;
                                        continue;
                                    case 50:
                                        Firmware.FirmwareUpdatePreferences.Builder mo10081toBuilder4 = this.payloadCase_ == 6 ? ((Firmware.FirmwareUpdatePreferences) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Firmware.FirmwareUpdatePreferences.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder4 != null) {
                                            mo10081toBuilder4.mergeFrom((Firmware.FirmwareUpdatePreferences.Builder) ((Firmware.FirmwareUpdatePreferences) this.payload_));
                                            this.payload_ = mo10081toBuilder4.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 6;
                                        continue;
                                    case 58:
                                        StateOuterClass.State.Builder mo10081toBuilder5 = this.payloadCase_ == 7 ? ((StateOuterClass.State) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(StateOuterClass.State.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder5 != null) {
                                            mo10081toBuilder5.mergeFrom((StateOuterClass.State.Builder) ((StateOuterClass.State) this.payload_));
                                            this.payload_ = mo10081toBuilder5.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 7;
                                        continue;
                                    case 66:
                                        Transport.ConnectionDetails.Builder mo10081toBuilder6 = this.payloadCase_ == 8 ? ((Transport.ConnectionDetails) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Transport.ConnectionDetails.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder6 != null) {
                                            mo10081toBuilder6.mergeFrom((Transport.ConnectionDetails.Builder) ((Transport.ConnectionDetails) this.payload_));
                                            this.payload_ = mo10081toBuilder6.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 8;
                                        continue;
                                    case 74:
                                        DiagnosticsOuterClass.Diagnostics.Builder mo10081toBuilder7 = this.payloadCase_ == 9 ? ((DiagnosticsOuterClass.Diagnostics) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(DiagnosticsOuterClass.Diagnostics.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder7 != null) {
                                            mo10081toBuilder7.mergeFrom((DiagnosticsOuterClass.Diagnostics.Builder) ((DiagnosticsOuterClass.Diagnostics) this.payload_));
                                            this.payload_ = mo10081toBuilder7.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 9;
                                        continue;
                                    case 82:
                                        Device.DeviceConfiguration.Builder mo10081toBuilder8 = this.payloadCase_ == 10 ? ((Device.DeviceConfiguration) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Device.DeviceConfiguration.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder8 != null) {
                                            mo10081toBuilder8.mergeFrom((Device.DeviceConfiguration.Builder) ((Device.DeviceConfiguration) this.payload_));
                                            this.payload_ = mo10081toBuilder8.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 10;
                                        continue;
                                    case 90:
                                        System.Users.Builder mo10081toBuilder9 = this.payloadCase_ == 11 ? ((System.Users) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(System.Users.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder9 != null) {
                                            mo10081toBuilder9.mergeFrom((System.Users.Builder) ((System.Users) this.payload_));
                                            this.payload_ = mo10081toBuilder9.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 11;
                                        continue;
                                    case 98:
                                        System.User.Builder mo10081toBuilder10 = this.payloadCase_ == 12 ? ((System.User) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(System.User.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder10 != null) {
                                            mo10081toBuilder10.mergeFrom((System.User.Builder) ((System.User) this.payload_));
                                            this.payload_ = mo10081toBuilder10.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 12;
                                        continue;
                                    case 106:
                                        Central.CentralInformation.Builder mo10081toBuilder11 = this.payloadCase_ == 13 ? ((Central.CentralInformation) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Central.CentralInformation.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder11 != null) {
                                            mo10081toBuilder11.mergeFrom((Central.CentralInformation.Builder) ((Central.CentralInformation) this.payload_));
                                            this.payload_ = mo10081toBuilder11.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 13;
                                        continue;
                                    case 114:
                                        Speech.Dialog.Builder mo10081toBuilder12 = this.payloadCase_ == 14 ? ((Speech.Dialog) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Speech.Dialog.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder12 != null) {
                                            mo10081toBuilder12.mergeFrom((Speech.Dialog.Builder) ((Speech.Dialog) this.payload_));
                                            this.payload_ = mo10081toBuilder12.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 14;
                                        continue;
                                    case 122:
                                        Speech.SpeechProvider.Builder mo10081toBuilder13 = this.payloadCase_ == 15 ? ((Speech.SpeechProvider) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Speech.SpeechProvider.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder13 != null) {
                                            mo10081toBuilder13.mergeFrom((Speech.SpeechProvider.Builder) ((Speech.SpeechProvider) this.payload_));
                                            this.payload_ = mo10081toBuilder13.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 15;
                                        continue;
                                    case 130:
                                        Fitness.FitnessData.Builder mo10081toBuilder14 = this.payloadCase_ == 16 ? ((Fitness.FitnessData) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Fitness.FitnessData.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder14 != null) {
                                            mo10081toBuilder14.mergeFrom((Fitness.FitnessData.Builder) ((Fitness.FitnessData) this.payload_));
                                            this.payload_ = mo10081toBuilder14.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 16;
                                        continue;
                                    case 138:
                                        Ancs.NotificationCenterInformation.Builder mo10081toBuilder15 = this.payloadCase_ == 17 ? ((Ancs.NotificationCenterInformation) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Ancs.NotificationCenterInformation.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder15 != null) {
                                            mo10081toBuilder15.mergeFrom((Ancs.NotificationCenterInformation.Builder) ((Ancs.NotificationCenterInformation) this.payload_));
                                            this.payload_ = mo10081toBuilder15.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 17;
                                        continue;
                                    case 146:
                                        Ancs.CentralNotificationAttributes.Builder mo10081toBuilder16 = this.payloadCase_ == 18 ? ((Ancs.CentralNotificationAttributes) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Ancs.CentralNotificationAttributes.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder16 != null) {
                                            mo10081toBuilder16.mergeFrom((Ancs.CentralNotificationAttributes.Builder) ((Ancs.CentralNotificationAttributes) this.payload_));
                                            this.payload_ = mo10081toBuilder16.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 18;
                                        continue;
                                    case 154:
                                        Ancs.CentralNotificationAppAttributes.Builder mo10081toBuilder17 = this.payloadCase_ == 19 ? ((Ancs.CentralNotificationAppAttributes) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Ancs.CentralNotificationAppAttributes.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder17 != null) {
                                            mo10081toBuilder17.mergeFrom((Ancs.CentralNotificationAppAttributes.Builder) ((Ancs.CentralNotificationAppAttributes) this.payload_));
                                            this.payload_ = mo10081toBuilder17.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 19;
                                        continue;
                                    case 162:
                                        Input.InputBehaviorConfigurationSet.Builder mo10081toBuilder18 = this.payloadCase_ == 20 ? ((Input.InputBehaviorConfigurationSet) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Input.InputBehaviorConfigurationSet.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder18 != null) {
                                            mo10081toBuilder18.mergeFrom((Input.InputBehaviorConfigurationSet.Builder) ((Input.InputBehaviorConfigurationSet) this.payload_));
                                            this.payload_ = mo10081toBuilder18.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 20;
                                        continue;
                                    case 170:
                                        System.Locales.Builder mo10081toBuilder19 = this.payloadCase_ == 21 ? ((System.Locales) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(System.Locales.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder19 != null) {
                                            mo10081toBuilder19.mergeFrom((System.Locales.Builder) ((System.Locales) this.payload_));
                                            this.payload_ = mo10081toBuilder19.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 21;
                                        continue;
                                    case 178:
                                        Keyexchange.AcknowledgeHandshake.Builder mo10081toBuilder20 = this.payloadCase_ == 22 ? ((Keyexchange.AcknowledgeHandshake) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Keyexchange.AcknowledgeHandshake.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder20 != null) {
                                            mo10081toBuilder20.mergeFrom((Keyexchange.AcknowledgeHandshake.Builder) ((Keyexchange.AcknowledgeHandshake) this.payload_));
                                            this.payload_ = mo10081toBuilder20.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 22;
                                        continue;
                                    case 186:
                                        Keyexchange.AcknowledgeResetKey.Builder mo10081toBuilder21 = this.payloadCase_ == 23 ? ((Keyexchange.AcknowledgeResetKey) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Keyexchange.AcknowledgeResetKey.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder21 != null) {
                                            mo10081toBuilder21.mergeFrom((Keyexchange.AcknowledgeResetKey.Builder) ((Keyexchange.AcknowledgeResetKey) this.payload_));
                                            this.payload_ = mo10081toBuilder21.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 23;
                                        continue;
                                    case 226:
                                        Device.DeviceFeatures.Builder mo10081toBuilder22 = this.payloadCase_ == 28 ? ((Device.DeviceFeatures) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Device.DeviceFeatures.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder22 != null) {
                                            mo10081toBuilder22.mergeFrom((Device.DeviceFeatures.Builder) ((Device.DeviceFeatures) this.payload_));
                                            this.payload_ = mo10081toBuilder22.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 28;
                                        continue;
                                    case 242:
                                        Firmware.ArtifactList.Builder mo10081toBuilder23 = this.payloadCase_ == 30 ? ((Firmware.ArtifactList) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Firmware.ArtifactList.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder23 != null) {
                                            mo10081toBuilder23.mergeFrom((Firmware.ArtifactList.Builder) ((Firmware.ArtifactList) this.payload_));
                                            this.payload_ = mo10081toBuilder23.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 30;
                                        continue;
                                    case 250:
                                        Firmware.ArtifactFilter.Builder mo10081toBuilder24 = this.payloadCase_ == 31 ? ((Firmware.ArtifactFilter) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Firmware.ArtifactFilter.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder24 != null) {
                                            mo10081toBuilder24.mergeFrom((Firmware.ArtifactFilter.Builder) ((Firmware.ArtifactFilter) this.payload_));
                                            this.payload_ = mo10081toBuilder24.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 31;
                                        continue;
                                    case 258:
                                        Firmware.ArtifactUpdatePreference.Builder mo10081toBuilder25 = this.payloadCase_ == 32 ? ((Firmware.ArtifactUpdatePreference) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Firmware.ArtifactUpdatePreference.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder25 != null) {
                                            mo10081toBuilder25.mergeFrom((Firmware.ArtifactUpdatePreference.Builder) ((Firmware.ArtifactUpdatePreference) this.payload_));
                                            this.payload_ = mo10081toBuilder25.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 32;
                                        continue;
                                    case 266:
                                        System.Wakewords.Builder mo10081toBuilder26 = this.payloadCase_ == 33 ? ((System.Wakewords) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(System.Wakewords.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder26 != null) {
                                            mo10081toBuilder26.mergeFrom((System.Wakewords.Builder) ((System.Wakewords) this.payload_));
                                            this.payload_ = mo10081toBuilder26.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 33;
                                        continue;
                                    case HttpServletResponse.SC_PAYMENT_REQUIRED /* 402 */:
                                        Hearing.Audiogram.Builder mo10081toBuilder27 = this.payloadCase_ == 50 ? ((Hearing.Audiogram) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Hearing.Audiogram.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder27 != null) {
                                            mo10081toBuilder27.mergeFrom((Hearing.Audiogram.Builder) ((Hearing.Audiogram) this.payload_));
                                            this.payload_ = mo10081toBuilder27.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 50;
                                        continue;
                                    case HttpServletResponse.SC_GONE /* 410 */:
                                        Hearing.MediaEnhancementCorrectionAmount.Builder mo10081toBuilder28 = this.payloadCase_ == 51 ? ((Hearing.MediaEnhancementCorrectionAmount) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Hearing.MediaEnhancementCorrectionAmount.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder28 != null) {
                                            mo10081toBuilder28.mergeFrom((Hearing.MediaEnhancementCorrectionAmount.Builder) ((Hearing.MediaEnhancementCorrectionAmount) this.payload_));
                                            this.payload_ = mo10081toBuilder28.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 51;
                                        continue;
                                    case 442:
                                        Cloudpairing.CloudPairingAttributes.Builder mo10081toBuilder29 = this.payloadCase_ == 55 ? ((Cloudpairing.CloudPairingAttributes) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Cloudpairing.CloudPairingAttributes.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder29 != null) {
                                            mo10081toBuilder29.mergeFrom((Cloudpairing.CloudPairingAttributes.Builder) ((Cloudpairing.CloudPairingAttributes) this.payload_));
                                            this.payload_ = mo10081toBuilder29.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 55;
                                        continue;
                                    case 450:
                                        Cloudpairing.CloudPairingStatus.Builder mo10081toBuilder30 = this.payloadCase_ == 56 ? ((Cloudpairing.CloudPairingStatus) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Cloudpairing.CloudPairingStatus.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder30 != null) {
                                            mo10081toBuilder30.mergeFrom((Cloudpairing.CloudPairingStatus.Builder) ((Cloudpairing.CloudPairingStatus) this.payload_));
                                            this.payload_ = mo10081toBuilder30.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 56;
                                        continue;
                                    case 482:
                                        Media.PlaybackStatus.Builder mo10081toBuilder31 = this.payloadCase_ == 60 ? ((Media.PlaybackStatus) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Media.PlaybackStatus.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder31 != null) {
                                            mo10081toBuilder31.mergeFrom((Media.PlaybackStatus.Builder) ((Media.PlaybackStatus) this.payload_));
                                            this.payload_ = mo10081toBuilder31.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 60;
                                        continue;
                                    case 1762:
                                        Cbl.CblLoginState.Builder mo10081toBuilder32 = this.payloadCase_ == 220 ? ((Cbl.CblLoginState) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Cbl.CblLoginState.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder32 != null) {
                                            mo10081toBuilder32.mergeFrom((Cbl.CblLoginState.Builder) ((Cbl.CblLoginState) this.payload_));
                                            this.payload_ = mo10081toBuilder32.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 220;
                                        continue;
                                    case 1770:
                                        Cbl.CblInformation.Builder mo10081toBuilder33 = this.payloadCase_ == 221 ? ((Cbl.CblInformation) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Cbl.CblInformation.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder33 != null) {
                                            mo10081toBuilder33.mergeFrom((Cbl.CblInformation.Builder) ((Cbl.CblInformation) this.payload_));
                                            this.payload_ = mo10081toBuilder33.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = 221;
                                        continue;
                                    case 2898:
                                        Voicestream.VoiceProvider.Builder mo10081toBuilder34 = this.payloadCase_ == 362 ? ((Voicestream.VoiceProvider) this.payload_).mo10081toBuilder() : null;
                                        this.payload_ = codedInputStream.readMessage(Voicestream.VoiceProvider.parser(), extensionRegistryLite);
                                        if (mo10081toBuilder34 != null) {
                                            mo10081toBuilder34.mergeFrom((Voicestream.VoiceProvider.Builder) ((Voicestream.VoiceProvider) this.payload_));
                                            this.payload_ = mo10081toBuilder34.mo10085buildPartial();
                                        }
                                        this.payloadCase_ = VOICE_PROVIDER_FIELD_NUMBER;
                                        continue;
                                    default:
                                        if (!parseUnknownField(readTag, codedInputStream)) {
                                            break;
                                        } else {
                                            continue;
                                        }
                                }
                                z = true;
                            } catch (IOException e) {
                                throw new RuntimeException(new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this));
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 5:
                    return null;
                case 6:
                    return new Response();
                case 7:
                    return new Builder(null);
                case 8:
                    break;
                case 9:
                    if (PARSER == null) {
                        synchronized (Response.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Keyexchange.AcknowledgeHandshake getAcknowledgeHandshake() {
            if (this.payloadCase_ == 22) {
                return (Keyexchange.AcknowledgeHandshake) this.payload_;
            }
            return Keyexchange.AcknowledgeHandshake.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Keyexchange.AcknowledgeResetKey getAcknowledgeResetKey() {
            if (this.payloadCase_ == 23) {
                return (Keyexchange.AcknowledgeResetKey) this.payload_;
            }
            return Keyexchange.AcknowledgeResetKey.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Firmware.ArtifactFilter getArtifactFilter() {
            if (this.payloadCase_ == 31) {
                return (Firmware.ArtifactFilter) this.payload_;
            }
            return Firmware.ArtifactFilter.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Firmware.ArtifactList getArtifactList() {
            if (this.payloadCase_ == 30) {
                return (Firmware.ArtifactList) this.payload_;
            }
            return Firmware.ArtifactList.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Firmware.ArtifactUpdatePreference getArtifactUpdatePreference() {
            if (this.payloadCase_ == 32) {
                return (Firmware.ArtifactUpdatePreference) this.payload_;
            }
            return Firmware.ArtifactUpdatePreference.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Hearing.Audiogram getAudiogram() {
            if (this.payloadCase_ == 50) {
                return (Hearing.Audiogram) this.payload_;
            }
            return Hearing.Audiogram.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Cbl.CblInformation getCblInformation() {
            if (this.payloadCase_ == 221) {
                return (Cbl.CblInformation) this.payload_;
            }
            return Cbl.CblInformation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Cbl.CblLoginState getCblLoginState() {
            if (this.payloadCase_ == 220) {
                return (Cbl.CblLoginState) this.payload_;
            }
            return Cbl.CblLoginState.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Central.CentralInformation getCentralInformation() {
            if (this.payloadCase_ == 13) {
                return (Central.CentralInformation) this.payload_;
            }
            return Central.CentralInformation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Ancs.CentralNotificationAppAttributes getCentralNotificationAppAttributes() {
            if (this.payloadCase_ == 19) {
                return (Ancs.CentralNotificationAppAttributes) this.payload_;
            }
            return Ancs.CentralNotificationAppAttributes.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Ancs.CentralNotificationAttributes getCentralNotificationAttributes() {
            if (this.payloadCase_ == 18) {
                return (Ancs.CentralNotificationAttributes) this.payload_;
            }
            return Ancs.CentralNotificationAttributes.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Cloudpairing.CloudPairingAttributes getCloudPairingAttributes() {
            if (this.payloadCase_ == 55) {
                return (Cloudpairing.CloudPairingAttributes) this.payload_;
            }
            return Cloudpairing.CloudPairingAttributes.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Cloudpairing.CloudPairingStatus getCloudPairingStatus() {
            if (this.payloadCase_ == 56) {
                return (Cloudpairing.CloudPairingStatus) this.payload_;
            }
            return Cloudpairing.CloudPairingStatus.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Transport.ConnectionDetails getConnectionDetails() {
            if (this.payloadCase_ == 8) {
                return (Transport.ConnectionDetails) this.payload_;
            }
            return Transport.ConnectionDetails.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Device.DeviceConfiguration getDeviceConfiguration() {
            if (this.payloadCase_ == 10) {
                return (Device.DeviceConfiguration) this.payload_;
            }
            return Device.DeviceConfiguration.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Device.DeviceFeatures getDeviceFeatures() {
            if (this.payloadCase_ == 28) {
                return (Device.DeviceFeatures) this.payload_;
            }
            return Device.DeviceFeatures.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Device.DeviceInformation getDeviceInformation() {
            if (this.payloadCase_ == 3) {
                return (Device.DeviceInformation) this.payload_;
            }
            return Device.DeviceInformation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public DiagnosticsOuterClass.Diagnostics getDiagnostics() {
            if (this.payloadCase_ == 9) {
                return (DiagnosticsOuterClass.Diagnostics) this.payload_;
            }
            return DiagnosticsOuterClass.Diagnostics.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Speech.Dialog getDialog() {
            if (this.payloadCase_ == 14) {
                return (Speech.Dialog) this.payload_;
            }
            return Speech.Dialog.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Common.ErrorCode getErrorCode() {
            Common.ErrorCode forNumber = Common.ErrorCode.forNumber(this.errorCode_);
            return forNumber == null ? Common.ErrorCode.UNRECOGNIZED : forNumber;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public int getErrorCodeValue() {
            return this.errorCode_;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Firmware.FirmwareComponent getFirmwareComponent() {
            if (this.payloadCase_ == 2) {
                return (Firmware.FirmwareComponent) this.payload_;
            }
            return Firmware.FirmwareComponent.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Firmware.FirmwareInformation getFirmwareInformation() {
            if (this.payloadCase_ == 5) {
                return (Firmware.FirmwareInformation) this.payload_;
            }
            return Firmware.FirmwareInformation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Firmware.FirmwareUpdatePreferences getFirmwareUpdatePreferences() {
            if (this.payloadCase_ == 6) {
                return (Firmware.FirmwareUpdatePreferences) this.payload_;
            }
            return Firmware.FirmwareUpdatePreferences.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Fitness.FitnessData getFitnessData() {
            if (this.payloadCase_ == 16) {
                return (Fitness.FitnessData) this.payload_;
            }
            return Fitness.FitnessData.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Input.InputBehaviorConfigurationSet getInputBehaviorConfigurationSet() {
            if (this.payloadCase_ == 20) {
                return (Input.InputBehaviorConfigurationSet) this.payload_;
            }
            return Input.InputBehaviorConfigurationSet.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public System.Locales getLocales() {
            if (this.payloadCase_ == 21) {
                return (System.Locales) this.payload_;
            }
            return System.Locales.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Hearing.MediaEnhancementCorrectionAmount getMediaEnhancementCorrectionAmount() {
            if (this.payloadCase_ == 51) {
                return (Hearing.MediaEnhancementCorrectionAmount) this.payload_;
            }
            return Hearing.MediaEnhancementCorrectionAmount.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Ancs.NotificationCenterInformation getNotificationCenterInformation() {
            if (this.payloadCase_ == 17) {
                return (Ancs.NotificationCenterInformation) this.payload_;
            }
            return Ancs.NotificationCenterInformation.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public PayloadCase getPayloadCase() {
            return PayloadCase.forNumber(this.payloadCase_);
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Media.PlaybackStatus getPlaybackStatus() {
            if (this.payloadCase_ == 60) {
                return (Media.PlaybackStatus) this.payload_;
            }
            return Media.PlaybackStatus.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.errorCode_ != Common.ErrorCode.SUCCESS.getNumber()) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.errorCode_);
            }
            if (this.payloadCase_ == 2) {
                i2 += CodedOutputStream.computeMessageSize(2, (Firmware.FirmwareComponent) this.payload_);
            }
            if (this.payloadCase_ == 3) {
                i2 += CodedOutputStream.computeMessageSize(3, (Device.DeviceInformation) this.payload_);
            }
            if (this.payloadCase_ == 5) {
                i2 += CodedOutputStream.computeMessageSize(5, (Firmware.FirmwareInformation) this.payload_);
            }
            if (this.payloadCase_ == 6) {
                i2 += CodedOutputStream.computeMessageSize(6, (Firmware.FirmwareUpdatePreferences) this.payload_);
            }
            if (this.payloadCase_ == 7) {
                i2 += CodedOutputStream.computeMessageSize(7, (StateOuterClass.State) this.payload_);
            }
            if (this.payloadCase_ == 8) {
                i2 += CodedOutputStream.computeMessageSize(8, (Transport.ConnectionDetails) this.payload_);
            }
            if (this.payloadCase_ == 9) {
                i2 += CodedOutputStream.computeMessageSize(9, (DiagnosticsOuterClass.Diagnostics) this.payload_);
            }
            if (this.payloadCase_ == 10) {
                i2 += CodedOutputStream.computeMessageSize(10, (Device.DeviceConfiguration) this.payload_);
            }
            if (this.payloadCase_ == 11) {
                i2 += CodedOutputStream.computeMessageSize(11, (System.Users) this.payload_);
            }
            if (this.payloadCase_ == 12) {
                i2 += CodedOutputStream.computeMessageSize(12, (System.User) this.payload_);
            }
            if (this.payloadCase_ == 13) {
                i2 += CodedOutputStream.computeMessageSize(13, (Central.CentralInformation) this.payload_);
            }
            if (this.payloadCase_ == 14) {
                i2 += CodedOutputStream.computeMessageSize(14, (Speech.Dialog) this.payload_);
            }
            if (this.payloadCase_ == 15) {
                i2 += CodedOutputStream.computeMessageSize(15, (Speech.SpeechProvider) this.payload_);
            }
            if (this.payloadCase_ == 16) {
                i2 += CodedOutputStream.computeMessageSize(16, (Fitness.FitnessData) this.payload_);
            }
            if (this.payloadCase_ == 17) {
                i2 += CodedOutputStream.computeMessageSize(17, (Ancs.NotificationCenterInformation) this.payload_);
            }
            if (this.payloadCase_ == 18) {
                i2 += CodedOutputStream.computeMessageSize(18, (Ancs.CentralNotificationAttributes) this.payload_);
            }
            if (this.payloadCase_ == 19) {
                i2 += CodedOutputStream.computeMessageSize(19, (Ancs.CentralNotificationAppAttributes) this.payload_);
            }
            if (this.payloadCase_ == 20) {
                i2 += CodedOutputStream.computeMessageSize(20, (Input.InputBehaviorConfigurationSet) this.payload_);
            }
            if (this.payloadCase_ == 21) {
                i2 += CodedOutputStream.computeMessageSize(21, (System.Locales) this.payload_);
            }
            if (this.payloadCase_ == 22) {
                i2 += CodedOutputStream.computeMessageSize(22, (Keyexchange.AcknowledgeHandshake) this.payload_);
            }
            if (this.payloadCase_ == 23) {
                i2 += CodedOutputStream.computeMessageSize(23, (Keyexchange.AcknowledgeResetKey) this.payload_);
            }
            if (this.payloadCase_ == 28) {
                i2 += CodedOutputStream.computeMessageSize(28, (Device.DeviceFeatures) this.payload_);
            }
            if (this.payloadCase_ == 30) {
                i2 += CodedOutputStream.computeMessageSize(30, (Firmware.ArtifactList) this.payload_);
            }
            if (this.payloadCase_ == 31) {
                i2 += CodedOutputStream.computeMessageSize(31, (Firmware.ArtifactFilter) this.payload_);
            }
            if (this.payloadCase_ == 32) {
                i2 += CodedOutputStream.computeMessageSize(32, (Firmware.ArtifactUpdatePreference) this.payload_);
            }
            if (this.payloadCase_ == 33) {
                i2 += CodedOutputStream.computeMessageSize(33, (System.Wakewords) this.payload_);
            }
            if (this.payloadCase_ == 50) {
                i2 += CodedOutputStream.computeMessageSize(50, (Hearing.Audiogram) this.payload_);
            }
            if (this.payloadCase_ == 51) {
                i2 += CodedOutputStream.computeMessageSize(51, (Hearing.MediaEnhancementCorrectionAmount) this.payload_);
            }
            if (this.payloadCase_ == 55) {
                i2 += CodedOutputStream.computeMessageSize(55, (Cloudpairing.CloudPairingAttributes) this.payload_);
            }
            if (this.payloadCase_ == 56) {
                i2 += CodedOutputStream.computeMessageSize(56, (Cloudpairing.CloudPairingStatus) this.payload_);
            }
            if (this.payloadCase_ == 60) {
                i2 += CodedOutputStream.computeMessageSize(60, (Media.PlaybackStatus) this.payload_);
            }
            if (this.payloadCase_ == 220) {
                i2 += CodedOutputStream.computeMessageSize(220, (Cbl.CblLoginState) this.payload_);
            }
            if (this.payloadCase_ == 221) {
                i2 += CodedOutputStream.computeMessageSize(221, (Cbl.CblInformation) this.payload_);
            }
            if (this.payloadCase_ == 362) {
                i2 += CodedOutputStream.computeMessageSize(VOICE_PROVIDER_FIELD_NUMBER, (Voicestream.VoiceProvider) this.payload_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Speech.SpeechProvider getSpeechProvider() {
            if (this.payloadCase_ == 15) {
                return (Speech.SpeechProvider) this.payload_;
            }
            return Speech.SpeechProvider.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public StateOuterClass.State getState() {
            if (this.payloadCase_ == 7) {
                return (StateOuterClass.State) this.payload_;
            }
            return StateOuterClass.State.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public System.User getUser() {
            if (this.payloadCase_ == 12) {
                return (System.User) this.payload_;
            }
            return System.User.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public System.Users getUsers() {
            if (this.payloadCase_ == 11) {
                return (System.Users) this.payload_;
            }
            return System.Users.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public Voicestream.VoiceProvider getVoiceProvider() {
            if (this.payloadCase_ == 362) {
                return (Voicestream.VoiceProvider) this.payload_;
            }
            return Voicestream.VoiceProvider.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public System.Wakewords getWakewords() {
            if (this.payloadCase_ == 33) {
                return (System.Wakewords) this.payload_;
            }
            return System.Wakewords.getDefaultInstance();
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasAcknowledgeHandshake() {
            return this.payloadCase_ == 22;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasAcknowledgeResetKey() {
            return this.payloadCase_ == 23;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasArtifactFilter() {
            return this.payloadCase_ == 31;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasArtifactList() {
            return this.payloadCase_ == 30;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasArtifactUpdatePreference() {
            return this.payloadCase_ == 32;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasAudiogram() {
            return this.payloadCase_ == 50;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasCblInformation() {
            return this.payloadCase_ == 221;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasCblLoginState() {
            return this.payloadCase_ == 220;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasCentralInformation() {
            return this.payloadCase_ == 13;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasCentralNotificationAppAttributes() {
            return this.payloadCase_ == 19;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasCentralNotificationAttributes() {
            return this.payloadCase_ == 18;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasCloudPairingAttributes() {
            return this.payloadCase_ == 55;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasCloudPairingStatus() {
            return this.payloadCase_ == 56;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasConnectionDetails() {
            return this.payloadCase_ == 8;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasDeviceConfiguration() {
            return this.payloadCase_ == 10;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasDeviceFeatures() {
            return this.payloadCase_ == 28;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasDeviceInformation() {
            return this.payloadCase_ == 3;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasDiagnostics() {
            return this.payloadCase_ == 9;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasDialog() {
            return this.payloadCase_ == 14;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasFirmwareComponent() {
            return this.payloadCase_ == 2;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasFirmwareInformation() {
            return this.payloadCase_ == 5;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasFirmwareUpdatePreferences() {
            return this.payloadCase_ == 6;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasFitnessData() {
            return this.payloadCase_ == 16;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasInputBehaviorConfigurationSet() {
            return this.payloadCase_ == 20;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasLocales() {
            return this.payloadCase_ == 21;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasMediaEnhancementCorrectionAmount() {
            return this.payloadCase_ == 51;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasNotificationCenterInformation() {
            return this.payloadCase_ == 17;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasPlaybackStatus() {
            return this.payloadCase_ == 60;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasSpeechProvider() {
            return this.payloadCase_ == 15;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasState() {
            return this.payloadCase_ == 7;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasUser() {
            return this.payloadCase_ == 12;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasUsers() {
            return this.payloadCase_ == 11;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasVoiceProvider() {
            return this.payloadCase_ == 362;
        }

        @Override // com.amazon.alexa.accessory.protocol.Accessories.ResponseOrBuilder
        public boolean hasWakewords() {
            return this.payloadCase_ == 33;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.errorCode_ != Common.ErrorCode.SUCCESS.getNumber()) {
                codedOutputStream.writeEnum(1, this.errorCode_);
            }
            if (this.payloadCase_ == 2) {
                codedOutputStream.writeMessage(2, (Firmware.FirmwareComponent) this.payload_);
            }
            if (this.payloadCase_ == 3) {
                codedOutputStream.writeMessage(3, (Device.DeviceInformation) this.payload_);
            }
            if (this.payloadCase_ == 5) {
                codedOutputStream.writeMessage(5, (Firmware.FirmwareInformation) this.payload_);
            }
            if (this.payloadCase_ == 6) {
                codedOutputStream.writeMessage(6, (Firmware.FirmwareUpdatePreferences) this.payload_);
            }
            if (this.payloadCase_ == 7) {
                codedOutputStream.writeMessage(7, (StateOuterClass.State) this.payload_);
            }
            if (this.payloadCase_ == 8) {
                codedOutputStream.writeMessage(8, (Transport.ConnectionDetails) this.payload_);
            }
            if (this.payloadCase_ == 9) {
                codedOutputStream.writeMessage(9, (DiagnosticsOuterClass.Diagnostics) this.payload_);
            }
            if (this.payloadCase_ == 10) {
                codedOutputStream.writeMessage(10, (Device.DeviceConfiguration) this.payload_);
            }
            if (this.payloadCase_ == 11) {
                codedOutputStream.writeMessage(11, (System.Users) this.payload_);
            }
            if (this.payloadCase_ == 12) {
                codedOutputStream.writeMessage(12, (System.User) this.payload_);
            }
            if (this.payloadCase_ == 13) {
                codedOutputStream.writeMessage(13, (Central.CentralInformation) this.payload_);
            }
            if (this.payloadCase_ == 14) {
                codedOutputStream.writeMessage(14, (Speech.Dialog) this.payload_);
            }
            if (this.payloadCase_ == 15) {
                codedOutputStream.writeMessage(15, (Speech.SpeechProvider) this.payload_);
            }
            if (this.payloadCase_ == 16) {
                codedOutputStream.writeMessage(16, (Fitness.FitnessData) this.payload_);
            }
            if (this.payloadCase_ == 17) {
                codedOutputStream.writeMessage(17, (Ancs.NotificationCenterInformation) this.payload_);
            }
            if (this.payloadCase_ == 18) {
                codedOutputStream.writeMessage(18, (Ancs.CentralNotificationAttributes) this.payload_);
            }
            if (this.payloadCase_ == 19) {
                codedOutputStream.writeMessage(19, (Ancs.CentralNotificationAppAttributes) this.payload_);
            }
            if (this.payloadCase_ == 20) {
                codedOutputStream.writeMessage(20, (Input.InputBehaviorConfigurationSet) this.payload_);
            }
            if (this.payloadCase_ == 21) {
                codedOutputStream.writeMessage(21, (System.Locales) this.payload_);
            }
            if (this.payloadCase_ == 22) {
                codedOutputStream.writeMessage(22, (Keyexchange.AcknowledgeHandshake) this.payload_);
            }
            if (this.payloadCase_ == 23) {
                codedOutputStream.writeMessage(23, (Keyexchange.AcknowledgeResetKey) this.payload_);
            }
            if (this.payloadCase_ == 28) {
                codedOutputStream.writeMessage(28, (Device.DeviceFeatures) this.payload_);
            }
            if (this.payloadCase_ == 30) {
                codedOutputStream.writeMessage(30, (Firmware.ArtifactList) this.payload_);
            }
            if (this.payloadCase_ == 31) {
                codedOutputStream.writeMessage(31, (Firmware.ArtifactFilter) this.payload_);
            }
            if (this.payloadCase_ == 32) {
                codedOutputStream.writeMessage(32, (Firmware.ArtifactUpdatePreference) this.payload_);
            }
            if (this.payloadCase_ == 33) {
                codedOutputStream.writeMessage(33, (System.Wakewords) this.payload_);
            }
            if (this.payloadCase_ == 50) {
                codedOutputStream.writeMessage(50, (Hearing.Audiogram) this.payload_);
            }
            if (this.payloadCase_ == 51) {
                codedOutputStream.writeMessage(51, (Hearing.MediaEnhancementCorrectionAmount) this.payload_);
            }
            if (this.payloadCase_ == 55) {
                codedOutputStream.writeMessage(55, (Cloudpairing.CloudPairingAttributes) this.payload_);
            }
            if (this.payloadCase_ == 56) {
                codedOutputStream.writeMessage(56, (Cloudpairing.CloudPairingStatus) this.payload_);
            }
            if (this.payloadCase_ == 60) {
                codedOutputStream.writeMessage(60, (Media.PlaybackStatus) this.payload_);
            }
            if (this.payloadCase_ == 220) {
                codedOutputStream.writeMessage(220, (Cbl.CblLoginState) this.payload_);
            }
            if (this.payloadCase_ == 221) {
                codedOutputStream.writeMessage(221, (Cbl.CblInformation) this.payload_);
            }
            if (this.payloadCase_ == 362) {
                codedOutputStream.writeMessage(VOICE_PROVIDER_FIELD_NUMBER, (Voicestream.VoiceProvider) this.payload_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(Response response) {
            return DEFAULT_INSTANCE.createBuilder(response);
        }

        public static Response parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Response) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Response parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Response parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Response parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAcknowledgeHandshake(Keyexchange.AcknowledgeHandshake.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 22;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAcknowledgeResetKey(Keyexchange.AcknowledgeResetKey.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 23;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactFilter(Firmware.ArtifactFilter.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 31;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactList(Firmware.ArtifactList.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 30;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArtifactUpdatePreference(Firmware.ArtifactUpdatePreference.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 32;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAudiogram(Hearing.Audiogram.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 50;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCblInformation(Cbl.CblInformation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 221;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCblLoginState(Cbl.CblLoginState.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 220;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCentralInformation(Central.CentralInformation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 13;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCentralNotificationAppAttributes(Ancs.CentralNotificationAppAttributes.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 19;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCentralNotificationAttributes(Ancs.CentralNotificationAttributes.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 18;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCloudPairingAttributes(Cloudpairing.CloudPairingAttributes.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 55;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCloudPairingStatus(Cloudpairing.CloudPairingStatus.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 56;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConnectionDetails(Transport.ConnectionDetails.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceConfiguration(Device.DeviceConfiguration.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceFeatures(Device.DeviceFeatures.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 28;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceInformation(Device.DeviceInformation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDiagnostics(DiagnosticsOuterClass.Diagnostics.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDialog(Speech.Dialog.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 14;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirmwareComponent(Firmware.FirmwareComponent.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirmwareInformation(Firmware.FirmwareInformation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 5;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFirmwareUpdatePreferences(Firmware.FirmwareUpdatePreferences.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 6;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFitnessData(Fitness.FitnessData.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInputBehaviorConfigurationSet(Input.InputBehaviorConfigurationSet.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 20;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLocales(System.Locales.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 21;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMediaEnhancementCorrectionAmount(Hearing.MediaEnhancementCorrectionAmount.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 51;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNotificationCenterInformation(Ancs.NotificationCenterInformation.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 17;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPlaybackStatus(Media.PlaybackStatus.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 60;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSpeechProvider(Speech.SpeechProvider.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 15;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setState(StateOuterClass.State.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 7;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUser(System.User.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 12;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUsers(System.Users.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 11;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVoiceProvider(Voicestream.VoiceProvider.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = VOICE_PROVIDER_FIELD_NUMBER;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWakewords(System.Wakewords.Builder builder) {
            this.payload_ = builder.mo10084build();
            this.payloadCase_ = 33;
        }

        public static Response parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Response parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Response parseFrom(InputStream inputStream) throws IOException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Response parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Response parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Response parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Response) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes6.dex */
    public interface ResponseOrBuilder extends MessageLiteOrBuilder {
        Keyexchange.AcknowledgeHandshake getAcknowledgeHandshake();

        Keyexchange.AcknowledgeResetKey getAcknowledgeResetKey();

        Firmware.ArtifactFilter getArtifactFilter();

        Firmware.ArtifactList getArtifactList();

        Firmware.ArtifactUpdatePreference getArtifactUpdatePreference();

        Hearing.Audiogram getAudiogram();

        Cbl.CblInformation getCblInformation();

        Cbl.CblLoginState getCblLoginState();

        Central.CentralInformation getCentralInformation();

        Ancs.CentralNotificationAppAttributes getCentralNotificationAppAttributes();

        Ancs.CentralNotificationAttributes getCentralNotificationAttributes();

        Cloudpairing.CloudPairingAttributes getCloudPairingAttributes();

        Cloudpairing.CloudPairingStatus getCloudPairingStatus();

        Transport.ConnectionDetails getConnectionDetails();

        Device.DeviceConfiguration getDeviceConfiguration();

        Device.DeviceFeatures getDeviceFeatures();

        Device.DeviceInformation getDeviceInformation();

        DiagnosticsOuterClass.Diagnostics getDiagnostics();

        Speech.Dialog getDialog();

        Common.ErrorCode getErrorCode();

        int getErrorCodeValue();

        Firmware.FirmwareComponent getFirmwareComponent();

        Firmware.FirmwareInformation getFirmwareInformation();

        Firmware.FirmwareUpdatePreferences getFirmwareUpdatePreferences();

        Fitness.FitnessData getFitnessData();

        Input.InputBehaviorConfigurationSet getInputBehaviorConfigurationSet();

        System.Locales getLocales();

        Hearing.MediaEnhancementCorrectionAmount getMediaEnhancementCorrectionAmount();

        Ancs.NotificationCenterInformation getNotificationCenterInformation();

        Response.PayloadCase getPayloadCase();

        Media.PlaybackStatus getPlaybackStatus();

        Speech.SpeechProvider getSpeechProvider();

        StateOuterClass.State getState();

        System.User getUser();

        System.Users getUsers();

        Voicestream.VoiceProvider getVoiceProvider();

        System.Wakewords getWakewords();

        boolean hasAcknowledgeHandshake();

        boolean hasAcknowledgeResetKey();

        boolean hasArtifactFilter();

        boolean hasArtifactList();

        boolean hasArtifactUpdatePreference();

        boolean hasAudiogram();

        boolean hasCblInformation();

        boolean hasCblLoginState();

        boolean hasCentralInformation();

        boolean hasCentralNotificationAppAttributes();

        boolean hasCentralNotificationAttributes();

        boolean hasCloudPairingAttributes();

        boolean hasCloudPairingStatus();

        boolean hasConnectionDetails();

        boolean hasDeviceConfiguration();

        boolean hasDeviceFeatures();

        boolean hasDeviceInformation();

        boolean hasDiagnostics();

        boolean hasDialog();

        boolean hasFirmwareComponent();

        boolean hasFirmwareInformation();

        boolean hasFirmwareUpdatePreferences();

        boolean hasFitnessData();

        boolean hasInputBehaviorConfigurationSet();

        boolean hasLocales();

        boolean hasMediaEnhancementCorrectionAmount();

        boolean hasNotificationCenterInformation();

        boolean hasPlaybackStatus();

        boolean hasSpeechProvider();

        boolean hasState();

        boolean hasUser();

        boolean hasUsers();

        boolean hasVoiceProvider();

        boolean hasWakewords();
    }

    private Accessories() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
