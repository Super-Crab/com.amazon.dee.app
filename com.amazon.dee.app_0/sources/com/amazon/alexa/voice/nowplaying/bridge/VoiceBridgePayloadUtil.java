package com.amazon.alexa.voice.nowplaying.bridge;

import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import com.amazon.alexa.voice.ui.player.PlaybackController;
import com.amazon.alexa.voice.ui.player.PlayerCardModel;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class VoiceBridgePayloadUtil {
    private static final String TAG = "VoiceBridgePayloadUtil";

    /* loaded from: classes11.dex */
    private static class PayloadCommand {
        public static final String NEXT = "next";
        public static final String PAUSE = "pause";
        public static final String PLAY = "play";
        public static final String PREVIOUS = "previous";
        public static final String SEEK = "seek";

        private PayloadCommand() {
        }
    }

    /* loaded from: classes11.dex */
    private static class PayloadKey {
        public static final String COMMAND = "command";
        public static final String COMMAND_SEEK_VALUE = "seekPosition";
        public static final String COMMAND_VALUE = "value";
        public static final String MEDIA_PLAYER_STATUS = "playbackStatus";
        public static final String PLAYER_INFO = "playbackInfo";
        public static final String PLAYER_INFO_ART_IMAGE_URL = "artImageUrl";
        public static final String PLAYER_INFO_BACKGROUND_IMAGE_URL = "backgroundImageUrl";
        public static final String PLAYER_INFO_DESCRIPTION = "description";
        public static final String PLAYER_INFO_DETAILS = "details";
        public static final String PLAYER_INFO_ID = "mediaId";
        public static final String PLAYER_INFO_PROGRESS_VISIBLE = "isProgressVisible";
        public static final String PLAYER_INFO_PROVIDER_LOGO_ID = "providerLogoId";
        public static final String PLAYER_INFO_PROVIDER_NAME = "providerName";
        public static final String PLAYER_INFO_SOURCE = "playbackSource";
        public static final String PLAYER_INFO_TITLE = "title";

        private PayloadKey() {
        }
    }

    /* loaded from: classes11.dex */
    private static class PayloadValue {
        public static final String COMMAND_VALUE_PLAYER_INFO = "playerInfoEvent";
        public static final String COMMAND_VALUE_PLAYER_STATE = "playerStateEvent";
        public static final String EC_TO_VOX_COMMAND = "ECToVOXCommands";
        public static final String VOX_TO_EC_COMMAND = "VOXToECCommands";

        private PayloadValue() {
        }
    }

    private VoiceBridgePayloadUtil() {
    }

    public static JSONObject createAudioItemStatePayload(@NonNull AlexaPlayerInfoState alexaPlayerInfoState, @NonNull String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("command", PayloadValue.VOX_TO_EC_COMMAND);
            jSONObject.put("value", PayloadValue.COMMAND_VALUE_PLAYER_STATE);
            jSONObject.put("mediaId", str);
            jSONObject.put(PayloadKey.MEDIA_PLAYER_STATUS, alexaPlayerInfoState.name());
        } catch (JSONException e) {
            Logger.error(TAG + " JSON creation exception occurred while creating player state event bus message", e);
        }
        return jSONObject;
    }

    public static JSONObject createPlayerInfoPayload(@NonNull PlayerCardModel playerCardModel) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("command", PayloadValue.VOX_TO_EC_COMMAND);
            jSONObject.put("value", PayloadValue.COMMAND_VALUE_PLAYER_INFO);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("mediaId", playerCardModel.getAudioItemId());
            jSONObject2.put("title", playerCardModel.getTitle());
            jSONObject2.put("details", playerCardModel.getDetails());
            jSONObject2.put("description", playerCardModel.getDescription());
            jSONObject2.put(PayloadKey.PLAYER_INFO_ART_IMAGE_URL, playerCardModel.getArtImageUrl());
            jSONObject2.put(PayloadKey.PLAYER_INFO_BACKGROUND_IMAGE_URL, playerCardModel.getBackgroundImageUrl());
            jSONObject2.put(PayloadKey.PLAYER_INFO_PROVIDER_LOGO_ID, playerCardModel.getProviderLogoId());
            jSONObject2.put("providerName", playerCardModel.getProviderName());
            jSONObject2.put(PayloadKey.PLAYER_INFO_PROGRESS_VISIBLE, playerCardModel.isProgressVisible());
            jSONObject2.put(PayloadKey.PLAYER_INFO_SOURCE, playerCardModel.getPlaybackSource());
            jSONObject.put(PayloadKey.PLAYER_INFO, jSONObject2);
        } catch (JSONException e) {
            Logger.error(TAG + " JSON creation exception occurred while creating player info event bus message", e);
        }
        return jSONObject;
    }

    public static void translatePayloadToPlayerAction(@NonNull String str, @NonNull PlaybackController playbackController) {
        if (str == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("command");
            if (string != null && PayloadValue.EC_TO_VOX_COMMAND.equals(string)) {
                String string2 = jSONObject.getString("value");
                if (PayloadCommand.PLAY.equals(string2)) {
                    playbackController.play();
                } else if ("pause".equals(string2)) {
                    playbackController.pause();
                } else if ("next".equals(string2)) {
                    playbackController.next();
                } else if ("previous".equals(string2)) {
                    playbackController.previous();
                } else if (PayloadCommand.SEEK.equals(string2)) {
                    playbackController.seek(jSONObject.getLong(PayloadKey.COMMAND_SEEK_VALUE));
                }
            }
        } catch (JSONException e) {
            Logger.error(TAG + " JSON creation exception occurred while parsing event bus message coming from entertainment-channel", e);
        }
    }
}
