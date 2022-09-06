package com.amazon.alexa.accessory.repositories.state;

import amazon.speech.simclient.SimError;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SanyoMakernoteDirectory;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes6.dex */
public final class StateFeature {
    private final int value;
    public static final StateFeature AUXILIARY_CONNECTED = from(256);
    public static final StateFeature BLUETOOTH_A2DP_ENABLED = from(304);
    public static final StateFeature BLUETOOTH_HFP_ENABLED = from(305);
    public static final StateFeature BLUETOOTH_A2DP_CONNECTED = from(306);
    public static final StateFeature BLUETOOTH_HFP_CONNECTED = from(307);
    public static final StateFeature BLUETOOTH_CLASSIC_DISCOVERABLE = from(308);
    public static final StateFeature BLUETOOTH_A2DP_ACTIVE = from(309);
    public static final StateFeature BLUETOOTH_HFP_ACTIVE = from(310);
    public static final StateFeature BLUETOOTH_SCO_PRIORITIZED = from(311);
    public static final StateFeature DEVICE_CALIBRATION_REQUIRED = from(512);
    public static final StateFeature DEVICE_THEME = from(513);
    public static final StateFeature DEVICE_DND_ENABLED = from(514);
    public static final StateFeature DEVICE_NETWORK_CONNECTIVITY_STATUS = from(515);
    public static final StateFeature DEVICE_PRIVACY_MODE_ENABLED = from(516);
    public static final StateFeature ACTIVE_NOISE_CANCELLATION_LEVEL = from(517);
    public static final StateFeature PASSTHROUGH_LEVEL_DEPRECATED = from(518);
    public static final StateFeature SETUP_MODE_ENABLED = from(519);
    public static final StateFeature EXTERNAL_MICROPHONE_ENABLED = from(520);
    public static final StateFeature FEEDBACK_ACTIVE_NOISE_CANCELLATION_LEVEL = from(521);
    public static final StateFeature ACTIVE_NOISE_CANCELLATION_ENABLED = from(528);
    public static final StateFeature PASSTHROUGH_ENABLED = from(529);
    public static final StateFeature PASSTHROUGH_LEVEL = from(530);
    public static final StateFeature SIDETONE_ENABLED = from(531);
    public static final StateFeature SIDETONE_LEVEL = from(532);
    public static final StateFeature PAIRING_MODE = from(SanyoMakernoteDirectory.TAG_RECORD_SHUTTER_RELEASE);
    public static final StateFeature SHARING_MODE_ENABLED = from(SanyoMakernoteDirectory.TAG_FLICKER_REDUCE);
    public static final StateFeature POWER_MODE = from(537);
    public static final StateFeature AUTO_VOLUME_CONFIG = from(544);
    public static final StateFeature AUTO_VOLUME_ACTIONS = from(545);
    public static final StateFeature AUTO_VOLUME_CALIBRATION_LEVEL = from(546);
    public static final StateFeature PRESENCE_SLEEP_MODE_ENABLED = from(547);
    public static final StateFeature PRESENCE_SLEEP_MODE_CONFIGURATION = from(SanyoMakernoteDirectory.TAG_SEQUENCE_SHOT_INTERVAL);
    public static final StateFeature ACCESSORY_PRESENCE_ENABLED = from(549);
    public static final StateFeature MESSAGE_NOTIFICATION = from(768);
    public static final StateFeature CALL_NOTIFICATION = from(769);
    public static final StateFeature REMOTE_NOTIFICATION = from(770);
    public static final StateFeature START_OF_SPEECH_EARCON_ENABLED = from(848);
    public static final StateFeature END_OF_SPEECH_EARCON_ENABLED = from(849);
    public static final StateFeature WAKEWORD_DETECTION_ENABLED = from(850);
    public static final StateFeature ALEXA_FOLLOW_UP_MODE_ENABLED = from(851);
    public static final StateFeature TOP_CONTACT_ENABLED = from(853);
    public static final StateFeature EASTER_EGG_ENABLED = from(870);
    public static final StateFeature FOCUS_FILTER_MESSAGE_STATE = from(880);
    public static final StateFeature SELECTED_WAKEWORD = from(DeviceConfigConstants.BUFFER_SIZE_IN_SAMPLES_896);
    public static final StateFeature STATUS_LED_LOCATION = from(912);
    public static final StateFeature VOLUME_CHANGED_NOTIFICATION = from(1025);
    public static final StateFeature EQUALIZER_BASS = from(SimError.LISTEN_ERROR_BUSY);
    public static final StateFeature EQUALIZER_MID = from(SimError.LISTEN_ERROR_TIMEOUT);
    public static final StateFeature EQUALIZER_TREBLE = from(SimError.LISTEN_ERROR_UNKNOWN);
    public static final StateFeature DEVICE_POWERED_ON = from(1280);
    public static final StateFeature BACK_OFF_TTS_MESSAGE = from(1281);
    public static final StateFeature CONNECTION_PROGRESS_SWEEPING_LIGHT_TIME = from(OlympusCameraSettingsMakernoteDirectory.TagWhiteBalanceBracket);
    public static final StateFeature CONNECTION_SUCCEEDED = from(1283);
    public static final StateFeature CONNECTION_PROGRESS_SWEEPING_LIGHT_LED_COLOR = from(OlympusCameraSettingsMakernoteDirectory.TagModifiedSaturation);
    public static final StateFeature SUCCESS_EARCON_MESSAGE = from(OlympusCameraSettingsMakernoteDirectory.TagContrastSetting);
    public static final StateFeature RINGING_STATE = from(1296);
    public static final StateFeature MEDIA_ENHANCEMENT_ENABLED = from(OlympusCameraSettingsMakernoteDirectory.TagPictureMode);
    public static final StateFeature HEARING_ASSESSMENT_MODE_ENABLED = from(OlympusCameraSettingsMakernoteDirectory.TagPictureModeSaturation);
    public static final StateFeature NOTIFICATION_FORWARDING_ENABLED = from(1537);
    public static final StateFeature AUDIO_PLAYBACK_STATUS = from(1568);
    public static final StateFeature CHARGING_STATUS = from(1792);

    private StateFeature(int i) {
        this.value = i;
    }

    public static StateFeature from(int i) {
        return new StateFeature(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && StateFeature.class == obj.getClass() && this.value == ((StateFeature) obj).value;
    }

    public int hashCode() {
        return this.value;
    }

    public int toInteger() {
        return this.value;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline85(GeneratedOutlineSupport1.outline107("StateFeature{"), this.value, JsonReaderKt.END_OBJ);
    }
}
